// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.amq;

import org.slf4j.LoggerFactory;
import org.apache.activemq.store.MessageStore;
import org.apache.activemq.store.MessageRecoveryListener;
import java.util.Collections;
import java.util.Iterator;
import org.apache.activemq.util.Callback;
import java.util.Collection;
import org.apache.activemq.command.JournalQueueAck;
import org.apache.activemq.command.MessageAck;
import java.io.InterruptedIOException;
import java.io.IOException;
import org.apache.activemq.transaction.Synchronization;
import org.apache.activemq.command.DataStructure;
import org.apache.activemq.command.Message;
import org.apache.activemq.usage.MemoryUsage;
import org.apache.activemq.thread.Task;
import org.apache.activemq.store.PersistenceAdapter;
import org.apache.activemq.filter.MessageEvaluationContext;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.filter.NonCachedMessageEvaluationContext;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashSet;
import org.apache.activemq.command.ActiveMQDestination;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.activemq.kaha.MessageAckWithLocation;
import java.util.List;
import org.apache.activemq.command.MessageId;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.apache.activemq.thread.TaskRunner;
import java.util.Set;
import org.apache.activemq.kaha.impl.async.Location;
import org.apache.activemq.util.TransactionTemplate;
import org.apache.activemq.store.ReferenceStore;
import org.slf4j.Logger;
import org.apache.activemq.store.AbstractMessageStore;

public class AMQMessageStore extends AbstractMessageStore
{
    private static final Logger LOG;
    protected final AMQPersistenceAdapter peristenceAdapter;
    protected final AMQTransactionStore transactionStore;
    protected final ReferenceStore referenceStore;
    protected final TransactionTemplate transactionTemplate;
    protected Location lastLocation;
    protected Location lastWrittenLocation;
    protected Set<Location> inFlightTxLocations;
    protected final TaskRunner asyncWriteTask;
    protected CountDownLatch flushLatch;
    private Map<MessageId, ReferenceStore.ReferenceData> messages;
    private List<MessageAckWithLocation> messageAcks;
    private Map<MessageId, ReferenceStore.ReferenceData> cpAddedMessageIds;
    private final boolean debug;
    private final AtomicReference<Location> mark;
    protected final Lock lock;
    
    public AMQMessageStore(final AMQPersistenceAdapter adapter, final ReferenceStore referenceStore, final ActiveMQDestination destination) {
        super(destination);
        this.inFlightTxLocations = new HashSet<Location>();
        this.messages = new LinkedHashMap<MessageId, ReferenceStore.ReferenceData>();
        this.messageAcks = new ArrayList<MessageAckWithLocation>();
        this.debug = AMQMessageStore.LOG.isDebugEnabled();
        this.mark = new AtomicReference<Location>();
        this.peristenceAdapter = adapter;
        this.lock = referenceStore.getStoreLock();
        this.transactionStore = adapter.getTransactionStore();
        this.referenceStore = referenceStore;
        this.transactionTemplate = new TransactionTemplate(adapter, new ConnectionContext(new NonCachedMessageEvaluationContext()));
        this.asyncWriteTask = adapter.getTaskRunnerFactory().createTaskRunner(new Task() {
            @Override
            public boolean iterate() {
                AMQMessageStore.this.asyncWrite();
                return false;
            }
        }, "Checkpoint: " + destination);
    }
    
    @Override
    public void setMemoryUsage(final MemoryUsage memoryUsage) {
        this.referenceStore.setMemoryUsage(memoryUsage);
    }
    
    @Override
    public final void addMessage(final ConnectionContext context, final Message message) throws IOException {
        final MessageId id = message.getMessageId();
        final Location location = this.peristenceAdapter.writeCommand(message, message.isResponseRequired());
        if (!context.isInTransaction()) {
            if (this.debug) {
                AMQMessageStore.LOG.debug("Journalled message add for: " + id + ", at: " + location);
            }
            this.peristenceAdapter.addInProgressDataFile(this, location.getDataFileId());
            this.addMessage(message, location);
        }
        else {
            if (this.debug) {
                AMQMessageStore.LOG.debug("Journalled transacted message add for: " + id + ", at: " + location);
            }
            this.lock.lock();
            try {
                this.inFlightTxLocations.add(location);
            }
            finally {
                this.lock.unlock();
            }
            this.transactionStore.addMessage(this, message, location);
            context.getTransaction().addSynchronization(new Synchronization() {
                @Override
                public void afterCommit() throws Exception {
                    if (AMQMessageStore.this.debug) {
                        AMQMessageStore.LOG.debug("Transacted message add commit for: " + id + ", at: " + location);
                    }
                    AMQMessageStore.this.lock.lock();
                    try {
                        AMQMessageStore.this.inFlightTxLocations.remove(location);
                    }
                    finally {
                        AMQMessageStore.this.lock.unlock();
                    }
                    AMQMessageStore.this.addMessage(message, location);
                }
                
                @Override
                public void afterRollback() throws Exception {
                    if (AMQMessageStore.this.debug) {
                        AMQMessageStore.LOG.debug("Transacted message add rollback for: " + id + ", at: " + location);
                    }
                    AMQMessageStore.this.lock.lock();
                    try {
                        AMQMessageStore.this.inFlightTxLocations.remove(location);
                    }
                    finally {
                        AMQMessageStore.this.lock.unlock();
                    }
                }
            });
        }
    }
    
    final void addMessage(final Message message, final Location location) throws InterruptedIOException {
        final ReferenceStore.ReferenceData data = new ReferenceStore.ReferenceData();
        data.setExpiration(message.getExpiration());
        data.setFileId(location.getDataFileId());
        data.setOffset(location.getOffset());
        this.lock.lock();
        try {
            this.lastLocation = location;
            final ReferenceStore.ReferenceData prev = this.messages.put(message.getMessageId(), data);
            if (prev != null) {
                this.peristenceAdapter.removeInProgressDataFile(this, prev.getFileId());
            }
        }
        finally {
            this.lock.unlock();
        }
        if (this.messages.size() > this.peristenceAdapter.getMaxCheckpointMessageAddSize()) {
            this.flush();
        }
        else {
            try {
                this.asyncWriteTask.wakeup();
            }
            catch (InterruptedException e) {
                throw new InterruptedIOException();
            }
        }
    }
    
    public boolean replayAddMessage(final ConnectionContext context, final Message message, final Location location) {
        final MessageId id = message.getMessageId();
        try {
            ReferenceStore.ReferenceData data = this.referenceStore.getMessageReference(id);
            if (data == null) {
                data = new ReferenceStore.ReferenceData();
                data.setExpiration(message.getExpiration());
                data.setFileId(location.getDataFileId());
                data.setOffset(location.getOffset());
                this.referenceStore.addMessageReference(context, id, data);
                return true;
            }
        }
        catch (Throwable e) {
            AMQMessageStore.LOG.warn("Could not replay add for message '" + id + "'.  Message may have already been added. reason: " + e, e);
        }
        return false;
    }
    
    @Override
    public void removeMessage(final ConnectionContext context, final MessageAck ack) throws IOException {
        final JournalQueueAck remove = new JournalQueueAck();
        remove.setDestination(this.destination);
        remove.setMessageAck(ack);
        final Location location = this.peristenceAdapter.writeCommand(remove, ack.isResponseRequired());
        if (!context.isInTransaction()) {
            if (this.debug) {
                AMQMessageStore.LOG.debug("Journalled message remove for: " + ack.getLastMessageId() + ", at: " + location);
            }
            this.removeMessage(ack, location);
        }
        else {
            if (this.debug) {
                AMQMessageStore.LOG.debug("Journalled transacted message remove for: " + ack.getLastMessageId() + ", at: " + location);
            }
            this.lock.lock();
            try {
                this.inFlightTxLocations.add(location);
            }
            finally {
                this.lock.unlock();
            }
            this.transactionStore.removeMessage(this, ack, location);
            context.getTransaction().addSynchronization(new Synchronization() {
                @Override
                public void afterCommit() throws Exception {
                    if (AMQMessageStore.this.debug) {
                        AMQMessageStore.LOG.debug("Transacted message remove commit for: " + ack.getLastMessageId() + ", at: " + location);
                    }
                    AMQMessageStore.this.lock.lock();
                    try {
                        AMQMessageStore.this.inFlightTxLocations.remove(location);
                    }
                    finally {
                        AMQMessageStore.this.lock.unlock();
                    }
                    AMQMessageStore.this.removeMessage(ack, location);
                }
                
                @Override
                public void afterRollback() throws Exception {
                    if (AMQMessageStore.this.debug) {
                        AMQMessageStore.LOG.debug("Transacted message remove rollback for: " + ack.getLastMessageId() + ", at: " + location);
                    }
                    AMQMessageStore.this.lock.lock();
                    try {
                        AMQMessageStore.this.inFlightTxLocations.remove(location);
                    }
                    finally {
                        AMQMessageStore.this.lock.unlock();
                    }
                }
            });
        }
    }
    
    final void removeMessage(final MessageAck ack, final Location location) throws InterruptedIOException {
        this.lock.lock();
        ReferenceStore.ReferenceData data;
        try {
            this.lastLocation = location;
            final MessageId id = ack.getLastMessageId();
            data = this.messages.remove(id);
            if (data == null) {
                this.messageAcks.add(new MessageAckWithLocation(ack, location));
            }
            else {
                this.peristenceAdapter.removeInProgressDataFile(this, data.getFileId());
            }
        }
        finally {
            this.lock.unlock();
        }
        if (this.messageAcks.size() > this.peristenceAdapter.getMaxCheckpointMessageAddSize()) {
            this.flush();
        }
        else if (data == null) {
            try {
                this.asyncWriteTask.wakeup();
            }
            catch (InterruptedException e) {
                throw new InterruptedIOException();
            }
        }
    }
    
    public boolean replayRemoveMessage(final ConnectionContext context, final MessageAck messageAck) {
        try {
            final ReferenceStore.ReferenceData t = this.referenceStore.getMessageReference(messageAck.getLastMessageId());
            if (t != null) {
                this.referenceStore.removeMessage(context, messageAck);
                return true;
            }
        }
        catch (Throwable e) {
            AMQMessageStore.LOG.warn("Could not replay acknowledge for message '" + messageAck.getLastMessageId() + "'.  Message may have already been acknowledged. reason: " + e);
        }
        return false;
    }
    
    public void flush() throws InterruptedIOException {
        if (AMQMessageStore.LOG.isDebugEnabled()) {
            AMQMessageStore.LOG.debug("flush starting ...");
        }
        this.lock.lock();
        CountDownLatch countDown;
        try {
            if (this.lastWrittenLocation == this.lastLocation) {
                return;
            }
            if (this.flushLatch == null) {
                this.flushLatch = new CountDownLatch(1);
            }
            countDown = this.flushLatch;
        }
        finally {
            this.lock.unlock();
        }
        try {
            this.asyncWriteTask.wakeup();
            countDown.await();
        }
        catch (InterruptedException e) {
            throw new InterruptedIOException();
        }
        if (AMQMessageStore.LOG.isDebugEnabled()) {
            AMQMessageStore.LOG.debug("flush finished");
        }
    }
    
    synchronized void asyncWrite() {
        try {
            this.lock.lock();
            CountDownLatch countDown;
            try {
                countDown = this.flushLatch;
                this.flushLatch = null;
            }
            finally {
                this.lock.unlock();
            }
            this.mark.set(this.doAsyncWrite());
            if (countDown != null) {
                countDown.countDown();
            }
        }
        catch (IOException e) {
            AMQMessageStore.LOG.error("Checkpoint failed: " + e, e);
        }
    }
    
    protected Location doAsyncWrite() throws IOException {
        final int maxCheckpointMessageAddSize = this.peristenceAdapter.getMaxCheckpointMessageAddSize();
        this.lock.lock();
        List<MessageAckWithLocation> cpRemovedMessageLocations;
        List<Location> cpActiveJournalLocations;
        Location lastLocation;
        try {
            this.cpAddedMessageIds = this.messages;
            cpRemovedMessageLocations = this.messageAcks;
            cpActiveJournalLocations = new ArrayList<Location>(this.inFlightTxLocations);
            this.messages = new LinkedHashMap<MessageId, ReferenceStore.ReferenceData>();
            this.messageAcks = new ArrayList<MessageAckWithLocation>();
            lastLocation = this.lastLocation;
        }
        finally {
            this.lock.unlock();
        }
        if (AMQMessageStore.LOG.isDebugEnabled()) {
            AMQMessageStore.LOG.debug("Doing batch update... adding: " + this.cpAddedMessageIds.size() + " removing: " + cpRemovedMessageLocations.size() + " ");
        }
        this.transactionTemplate.run(new Callback() {
            @Override
            public void execute() throws Exception {
                int size = 0;
                final PersistenceAdapter persitanceAdapter = AMQMessageStore.this.transactionTemplate.getPersistenceAdapter();
                final ConnectionContext context = AMQMessageStore.this.transactionTemplate.getContext();
                for (final Map.Entry<MessageId, ReferenceStore.ReferenceData> entry : AMQMessageStore.this.cpAddedMessageIds.entrySet()) {
                    try {
                        if (AMQMessageStore.this.referenceStore.addMessageReference(context, entry.getKey(), entry.getValue())) {
                            if (AMQMessageStore.LOG.isDebugEnabled()) {
                                AMQMessageStore.LOG.debug("adding message ref:" + entry.getKey());
                            }
                            ++size;
                        }
                        else if (AMQMessageStore.LOG.isDebugEnabled()) {
                            AMQMessageStore.LOG.debug("not adding duplicate reference: " + entry.getKey() + ", " + entry.getValue());
                        }
                        AMQMessageStore.this.peristenceAdapter.removeInProgressDataFile(AMQMessageStore.this, entry.getValue().getFileId());
                    }
                    catch (Throwable e) {
                        AMQMessageStore.LOG.warn("Message could not be added to long term store: " + e.getMessage(), e);
                    }
                    if (size >= maxCheckpointMessageAddSize) {
                        persitanceAdapter.commitTransaction(context);
                        persitanceAdapter.beginTransaction(context);
                        size = 0;
                    }
                }
                persitanceAdapter.commitTransaction(context);
                persitanceAdapter.beginTransaction(context);
                for (final MessageAckWithLocation ack : cpRemovedMessageLocations) {
                    try {
                        AMQMessageStore.this.referenceStore.removeMessage(AMQMessageStore.this.transactionTemplate.getContext(), ack);
                    }
                    catch (Throwable e2) {
                        AMQMessageStore.LOG.warn("Message could not be removed from long term store: " + e2.getMessage(), e2);
                    }
                }
            }
        });
        AMQMessageStore.LOG.debug("Batch update done. lastLocation:" + lastLocation);
        this.lock.lock();
        try {
            this.cpAddedMessageIds = null;
            this.lastWrittenLocation = lastLocation;
        }
        finally {
            this.lock.unlock();
        }
        if (cpActiveJournalLocations.size() > 0) {
            Collections.sort(cpActiveJournalLocations);
            return cpActiveJournalLocations.get(0);
        }
        return lastLocation;
    }
    
    @Override
    public Message getMessage(final MessageId identity) throws IOException {
        final Location location = this.getLocation(identity);
        if (location != null) {
            final DataStructure rc = this.peristenceAdapter.readCommand(location);
            try {
                return (Message)rc;
            }
            catch (ClassCastException e) {
                throw new IOException("Could not read message " + identity + " at location " + location + ", expected a message, but got: " + rc);
            }
        }
        return null;
    }
    
    protected Location getLocation(final MessageId messageId) throws IOException {
        ReferenceStore.ReferenceData data = null;
        this.lock.lock();
        try {
            data = this.messages.get(messageId);
            if (data == null && this.cpAddedMessageIds != null) {
                data = this.cpAddedMessageIds.get(messageId);
            }
        }
        finally {
            this.lock.unlock();
        }
        if (data == null) {
            data = this.referenceStore.getMessageReference(messageId);
            if (data == null) {
                return null;
            }
        }
        final Location location = new Location();
        location.setDataFileId(data.getFileId());
        location.setOffset(data.getOffset());
        return location;
    }
    
    @Override
    public void recover(final MessageRecoveryListener listener) throws Exception {
        this.flush();
        this.referenceStore.recover(new RecoveryListenerAdapter(this, listener));
    }
    
    @Override
    public void start() throws Exception {
        this.referenceStore.start();
    }
    
    @Override
    public void stop() throws Exception {
        this.flush();
        this.asyncWriteTask.shutdown();
        this.referenceStore.stop();
    }
    
    public ReferenceStore getReferenceStore() {
        return this.referenceStore;
    }
    
    @Override
    public void removeAllMessages(final ConnectionContext context) throws IOException {
        this.flush();
        this.referenceStore.removeAllMessages(context);
    }
    
    public void addMessageReference(final ConnectionContext context, final MessageId messageId, final long expirationTime, final String messageRef) throws IOException {
        throw new IOException("The journal does not support message references.");
    }
    
    public String getMessageReference(final MessageId identity) throws IOException {
        throw new IOException("The journal does not support message references.");
    }
    
    @Override
    public int getMessageCount() throws IOException {
        this.flush();
        return this.referenceStore.getMessageCount();
    }
    
    @Override
    public void recoverNextMessages(final int maxReturned, final MessageRecoveryListener listener) throws Exception {
        final RecoveryListenerAdapter recoveryListener = new RecoveryListenerAdapter(this, listener);
        this.referenceStore.recoverNextMessages(maxReturned, recoveryListener);
        if (recoveryListener.size() == 0 && recoveryListener.hasSpace()) {
            this.flush();
            this.referenceStore.recoverNextMessages(maxReturned, recoveryListener);
        }
    }
    
    Message getMessage(final ReferenceStore.ReferenceData data) throws IOException {
        final Location location = new Location();
        location.setDataFileId(data.getFileId());
        location.setOffset(data.getOffset());
        final DataStructure rc = this.peristenceAdapter.readCommand(location);
        try {
            return (Message)rc;
        }
        catch (ClassCastException e) {
            throw new IOException("Could not read message  at location " + location + ", expected a message, but got: " + rc);
        }
    }
    
    @Override
    public void resetBatching() {
        this.referenceStore.resetBatching();
    }
    
    public Location getMark() {
        return this.mark.get();
    }
    
    @Override
    public void dispose(final ConnectionContext context) {
        try {
            this.flush();
        }
        catch (InterruptedIOException e) {
            Thread.currentThread().interrupt();
        }
        this.referenceStore.dispose(context);
        super.dispose(context);
    }
    
    @Override
    public void setBatch(final MessageId messageId) {
        try {
            this.flush();
        }
        catch (InterruptedIOException e) {
            AMQMessageStore.LOG.debug("flush on setBatch resulted in exception", e);
        }
        this.getReferenceStore().setBatch(messageId);
    }
    
    static {
        LOG = LoggerFactory.getLogger(AMQMessageStore.class);
    }
}
