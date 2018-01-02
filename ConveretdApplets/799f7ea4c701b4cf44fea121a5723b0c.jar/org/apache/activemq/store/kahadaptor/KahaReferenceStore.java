// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadaptor;

import org.slf4j.LoggerFactory;
import java.util.Iterator;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;
import org.apache.activemq.kaha.MessageAckWithLocation;
import org.apache.activemq.command.MessageAck;
import org.apache.activemq.store.MessageRecoveryListener;
import org.apache.activemq.command.Message;
import org.apache.activemq.broker.ConnectionContext;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.activemq.command.ActiveMQDestination;
import java.util.concurrent.locks.Lock;
import org.apache.activemq.kaha.StoreEntry;
import org.apache.activemq.ActiveMQMessageAudit;
import org.apache.activemq.command.MessageId;
import org.apache.activemq.kaha.MapContainer;
import org.slf4j.Logger;
import org.apache.activemq.store.ReferenceStore;
import org.apache.activemq.store.AbstractMessageStore;

public class KahaReferenceStore extends AbstractMessageStore implements ReferenceStore
{
    private static final Logger LOG;
    protected final MapContainer<MessageId, ReferenceRecord> messageContainer;
    protected KahaReferenceStoreAdapter adapter;
    protected ActiveMQMessageAudit dispatchAudit;
    private StoreEntry batchEntry;
    private String lastBatchId;
    protected final Lock lock;
    
    public KahaReferenceStore(final KahaReferenceStoreAdapter adapter, final MapContainer<MessageId, ReferenceRecord> container, final ActiveMQDestination destination) throws IOException {
        super(destination);
        this.dispatchAudit = new ActiveMQMessageAudit();
        this.lock = new ReentrantLock();
        this.adapter = adapter;
        this.messageContainer = container;
    }
    
    @Override
    public Lock getStoreLock() {
        return this.lock;
    }
    
    @Override
    public void dispose(final ConnectionContext context) {
        super.dispose(context);
        this.messageContainer.delete();
        this.adapter.removeReferenceStore(this);
    }
    
    protected MessageId getMessageId(final Object object) {
        return new MessageId(((ReferenceRecord)object).getMessageId());
    }
    
    @Override
    public void addMessage(final ConnectionContext context, final Message message) throws IOException {
        throw new RuntimeException("Use addMessageReference instead");
    }
    
    @Override
    public Message getMessage(final MessageId identity) throws IOException {
        throw new RuntimeException("Use addMessageReference instead");
    }
    
    protected final boolean recoverReference(final MessageRecoveryListener listener, final ReferenceRecord record) throws Exception {
        final MessageId id = new MessageId(record.getMessageId());
        return listener.hasSpace() && listener.recoverMessageReference(id);
    }
    
    @Override
    public void recover(final MessageRecoveryListener listener) throws Exception {
        this.lock.lock();
        try {
            for (StoreEntry entry = this.messageContainer.getFirst(); entry != null; entry = this.messageContainer.getNext(entry)) {
                final ReferenceRecord record = this.messageContainer.getValue(entry);
                if (!this.recoverReference(listener, record)) {
                    break;
                }
            }
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void recoverNextMessages(final int maxReturned, final MessageRecoveryListener listener) throws Exception {
        this.lock.lock();
        try {
            StoreEntry entry = this.batchEntry;
            if (entry == null) {
                entry = this.messageContainer.getFirst();
            }
            else {
                entry = this.messageContainer.refresh(entry);
                if (entry != null) {
                    entry = this.messageContainer.getNext(entry);
                }
            }
            if (entry != null) {
                int count = 0;
                do {
                    final ReferenceRecord msg = this.messageContainer.getValue(entry);
                    if (msg != null) {
                        if (this.recoverReference(listener, msg)) {
                            ++count;
                            this.lastBatchId = msg.getMessageId();
                        }
                        else if (!listener.isDuplicate(new MessageId(msg.getMessageId()))) {
                            if (KahaReferenceStore.LOG.isDebugEnabled()) {
                                KahaReferenceStore.LOG.debug(this.destination.getQualifiedName() + " did not recover (will retry) message: " + msg.getMessageId());
                                break;
                            }
                            break;
                        }
                        else if (KahaReferenceStore.LOG.isDebugEnabled()) {
                            KahaReferenceStore.LOG.debug(this.destination.getQualifiedName() + " skipping duplicate, " + msg.getMessageId());
                        }
                    }
                    else {
                        this.lastBatchId = null;
                    }
                    this.batchEntry = entry;
                    entry = this.messageContainer.getNext(entry);
                } while (entry != null && count < maxReturned && listener.hasSpace());
            }
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public boolean addMessageReference(final ConnectionContext context, final MessageId messageId, final ReferenceData data) throws IOException {
        boolean uniqueueReferenceAdded = false;
        this.lock.lock();
        try {
            if (!this.isDuplicate(messageId)) {
                final ReferenceRecord record = new ReferenceRecord(messageId.toString(), data);
                this.messageContainer.put(messageId, record);
                uniqueueReferenceAdded = true;
                this.addInterest(record);
                if (KahaReferenceStore.LOG.isDebugEnabled()) {
                    KahaReferenceStore.LOG.debug(this.destination.getPhysicalName() + " add: " + messageId);
                }
            }
        }
        finally {
            this.lock.unlock();
        }
        return uniqueueReferenceAdded;
    }
    
    protected boolean isDuplicate(final MessageId messageId) {
        boolean duplicate = this.messageContainer.containsKey(messageId);
        if (!duplicate) {
            duplicate = this.dispatchAudit.isDuplicate(messageId);
            if (duplicate && KahaReferenceStore.LOG.isDebugEnabled()) {
                KahaReferenceStore.LOG.debug(this.destination.getPhysicalName() + " ignoring duplicated (add) message reference, already dispatched: " + messageId);
            }
        }
        else if (KahaReferenceStore.LOG.isDebugEnabled()) {
            KahaReferenceStore.LOG.debug(this.destination.getPhysicalName() + " ignoring duplicated (add) message reference, already in store: " + messageId);
        }
        return duplicate;
    }
    
    @Override
    public ReferenceData getMessageReference(final MessageId identity) throws IOException {
        this.lock.lock();
        try {
            final ReferenceRecord result = this.messageContainer.get(identity);
            if (result == null) {
                return null;
            }
            return result.getData();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void removeMessage(final ConnectionContext context, final MessageAck ack) throws IOException {
        this.lock.lock();
        try {
            final MessageId msgId = ack.getLastMessageId();
            final StoreEntry entry = this.messageContainer.getEntry(msgId);
            if (entry != null) {
                final ReferenceRecord rr = this.messageContainer.remove(msgId);
                if (rr != null) {
                    this.removeInterest(rr);
                    if (ack instanceof MessageAckWithLocation) {
                        this.recordAckFileReferences((MessageAckWithLocation)ack, rr.getData().getFileId());
                    }
                    this.dispatchAudit.isDuplicate(msgId);
                    if (KahaReferenceStore.LOG.isDebugEnabled()) {
                        KahaReferenceStore.LOG.debug(this.destination.getPhysicalName() + " remove reference: " + msgId);
                    }
                    if (this.messageContainer.isEmpty() || (this.lastBatchId != null && this.lastBatchId.equals(msgId.toString())) || (this.batchEntry != null && this.batchEntry.equals(entry))) {
                        this.resetBatching();
                    }
                }
            }
        }
        finally {
            this.lock.unlock();
        }
    }
    
    private void recordAckFileReferences(final MessageAckWithLocation ack, final int messageFileId) {
        this.adapter.recordAckFileReferences(ack.location.getDataFileId(), messageFileId);
    }
    
    @Override
    public void removeAllMessages(final ConnectionContext context) throws IOException {
        this.lock.lock();
        try {
            final Set<MessageId> tmpSet = new HashSet<MessageId>(this.messageContainer.keySet());
            final MessageAck ack = new MessageAck();
            for (final MessageId id : tmpSet) {
                ack.setLastMessageId(id);
                this.removeMessage(null, ack);
            }
            this.resetBatching();
            this.messageContainer.clear();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    public void delete() {
        this.lock.lock();
        try {
            this.messageContainer.clear();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void resetBatching() {
        this.lock.lock();
        try {
            this.batchEntry = null;
            this.lastBatchId = null;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public int getMessageCount() {
        return this.messageContainer.size();
    }
    
    public boolean isSupportForCursors() {
        return true;
    }
    
    @Override
    public boolean supportsExternalBatchControl() {
        return true;
    }
    
    void removeInterest(final ReferenceRecord rr) {
        this.adapter.removeInterestInRecordFile(rr.getData().getFileId());
    }
    
    void addInterest(final ReferenceRecord rr) {
        this.adapter.addInterestInRecordFile(rr.getData().getFileId());
    }
    
    @Override
    public void setBatch(final MessageId startAfter) {
        this.lock.lock();
        try {
            this.batchEntry = this.messageContainer.getEntry(startAfter);
            if (KahaReferenceStore.LOG.isDebugEnabled()) {
                KahaReferenceStore.LOG.debug("setBatch: " + startAfter);
            }
        }
        finally {
            this.lock.unlock();
        }
    }
    
    static {
        LOG = LoggerFactory.getLogger(KahaReferenceStore.class);
    }
}
