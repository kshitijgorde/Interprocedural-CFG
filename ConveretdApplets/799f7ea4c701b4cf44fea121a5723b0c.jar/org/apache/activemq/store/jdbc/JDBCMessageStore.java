// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.jdbc;

import org.slf4j.LoggerFactory;
import org.apache.activemq.store.MessageRecoveryListener;
import org.apache.activemq.command.MessageAck;
import org.apache.activemq.util.ByteSequence;
import org.apache.activemq.command.MessageId;
import java.sql.SQLException;
import java.io.IOException;
import org.apache.activemq.util.IOExceptionSupport;
import org.apache.activemq.util.ByteSequenceData;
import org.apache.activemq.broker.region.MessageReference;
import org.apache.activemq.command.Message;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.ActiveMQMessageAudit;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.activemq.wireformat.WireFormat;
import org.slf4j.Logger;
import org.apache.activemq.store.AbstractMessageStore;

public class JDBCMessageStore extends AbstractMessageStore
{
    private static final Logger LOG;
    protected final WireFormat wireFormat;
    protected final JDBCAdapter adapter;
    protected final JDBCPersistenceAdapter persistenceAdapter;
    protected AtomicLong lastRecoveredSequenceId;
    protected AtomicLong lastRecoveredPriority;
    protected ActiveMQMessageAudit audit;
    
    public JDBCMessageStore(final JDBCPersistenceAdapter persistenceAdapter, final JDBCAdapter adapter, final WireFormat wireFormat, final ActiveMQDestination destination, final ActiveMQMessageAudit audit) {
        super(destination);
        this.lastRecoveredSequenceId = new AtomicLong(-1L);
        this.lastRecoveredPriority = new AtomicLong(126L);
        this.persistenceAdapter = persistenceAdapter;
        this.adapter = adapter;
        this.wireFormat = wireFormat;
        this.audit = audit;
    }
    
    @Override
    public void addMessage(final ConnectionContext context, final Message message) throws IOException {
        final MessageId messageId = message.getMessageId();
        if (this.audit != null && this.audit.isDuplicate(message)) {
            if (JDBCMessageStore.LOG.isDebugEnabled()) {
                JDBCMessageStore.LOG.debug(this.destination.getPhysicalName() + " ignoring duplicated (add) message, already stored: " + messageId);
            }
            return;
        }
        final long sequenceId = this.persistenceAdapter.getNextSequenceId();
        byte[] data;
        try {
            final ByteSequence packet = this.wireFormat.marshal(message);
            data = ByteSequenceData.toByteArray(packet);
        }
        catch (IOException e) {
            throw IOExceptionSupport.create("Failed to broker message: " + messageId + " in container: " + e, e);
        }
        final TransactionContext c = this.persistenceAdapter.getTransactionContext(context);
        try {
            this.adapter.doAddMessage(c, sequenceId, messageId, this.destination, data, message.getExpiration(), message.getPriority());
        }
        catch (SQLException e2) {
            JDBCPersistenceAdapter.log("JDBC Failure: ", e2);
            throw IOExceptionSupport.create("Failed to broker message: " + messageId + " in container: " + e2, e2);
        }
        finally {
            c.close();
        }
        this.onAdd(sequenceId, message.getPriority());
    }
    
    protected void onAdd(final long sequenceId, final byte priority) {
    }
    
    public void addMessageReference(final ConnectionContext context, final MessageId messageId, final long expirationTime, final String messageRef) throws IOException {
        final TransactionContext c = this.persistenceAdapter.getTransactionContext(context);
        try {
            this.adapter.doAddMessageReference(c, this.persistenceAdapter.getNextSequenceId(), messageId, this.destination, expirationTime, messageRef);
        }
        catch (SQLException e) {
            JDBCPersistenceAdapter.log("JDBC Failure: ", e);
            throw IOExceptionSupport.create("Failed to broker message: " + messageId + " in container: " + e, e);
        }
        finally {
            c.close();
        }
    }
    
    @Override
    public Message getMessage(final MessageId messageId) throws IOException {
        final TransactionContext c = this.persistenceAdapter.getTransactionContext();
        try {
            final byte[] data = this.adapter.doGetMessage(c, messageId);
            if (data == null) {
                return null;
            }
            final Message answer = (Message)this.wireFormat.unmarshal(new ByteSequence(data));
            return answer;
        }
        catch (IOException e) {
            throw IOExceptionSupport.create("Failed to broker message: " + messageId + " in container: " + e, e);
        }
        catch (SQLException e2) {
            JDBCPersistenceAdapter.log("JDBC Failure: ", e2);
            throw IOExceptionSupport.create("Failed to broker message: " + messageId + " in container: " + e2, e2);
        }
        finally {
            c.close();
        }
    }
    
    public String getMessageReference(final MessageId messageId) throws IOException {
        final long id = messageId.getBrokerSequenceId();
        final TransactionContext c = this.persistenceAdapter.getTransactionContext();
        try {
            return this.adapter.doGetMessageReference(c, id);
        }
        catch (IOException e) {
            throw IOExceptionSupport.create("Failed to broker message: " + messageId + " in container: " + e, e);
        }
        catch (SQLException e2) {
            JDBCPersistenceAdapter.log("JDBC Failure: ", e2);
            throw IOExceptionSupport.create("Failed to broker message: " + messageId + " in container: " + e2, e2);
        }
        finally {
            c.close();
        }
    }
    
    @Override
    public void removeMessage(final ConnectionContext context, final MessageAck ack) throws IOException {
        final long seq = this.getStoreSequenceIdForMessageId(ack.getLastMessageId())[0];
        final TransactionContext c = this.persistenceAdapter.getTransactionContext(context);
        try {
            this.adapter.doRemoveMessage(c, seq);
        }
        catch (SQLException e) {
            JDBCPersistenceAdapter.log("JDBC Failure: ", e);
            throw IOExceptionSupport.create("Failed to broker message: " + ack.getLastMessageId() + " in container: " + e, e);
        }
        finally {
            c.close();
        }
    }
    
    @Override
    public void recover(final MessageRecoveryListener listener) throws Exception {
        TransactionContext c = this.persistenceAdapter.getTransactionContext();
        try {
            c = this.persistenceAdapter.getTransactionContext();
            this.adapter.doRecover(c, this.destination, new JDBCMessageRecoveryListener() {
                @Override
                public boolean recoverMessage(final long sequenceId, final byte[] data) throws Exception {
                    final Message msg = (Message)JDBCMessageStore.this.wireFormat.unmarshal(new ByteSequence(data));
                    msg.getMessageId().setBrokerSequenceId(sequenceId);
                    return listener.recoverMessage(msg);
                }
                
                @Override
                public boolean recoverMessageReference(final String reference) throws Exception {
                    return listener.recoverMessageReference(new MessageId(reference));
                }
            });
        }
        catch (SQLException e) {
            JDBCPersistenceAdapter.log("JDBC Failure: ", e);
            throw IOExceptionSupport.create("Failed to recover container. Reason: " + e, e);
        }
        finally {
            c.close();
        }
    }
    
    @Override
    public void removeAllMessages(final ConnectionContext context) throws IOException {
        final TransactionContext c = this.persistenceAdapter.getTransactionContext(context);
        try {
            this.adapter.doRemoveAllMessages(c, this.destination);
        }
        catch (SQLException e) {
            JDBCPersistenceAdapter.log("JDBC Failure: ", e);
            throw IOExceptionSupport.create("Failed to broker remove all messages: " + e, e);
        }
        finally {
            c.close();
        }
    }
    
    @Override
    public int getMessageCount() throws IOException {
        int result = 0;
        final TransactionContext c = this.persistenceAdapter.getTransactionContext();
        try {
            result = this.adapter.doGetMessageCount(c, this.destination);
        }
        catch (SQLException e) {
            JDBCPersistenceAdapter.log("JDBC Failure: ", e);
            throw IOExceptionSupport.create("Failed to get Message Count: " + this.destination + ". Reason: " + e, e);
        }
        finally {
            c.close();
        }
        return result;
    }
    
    @Override
    public void recoverNextMessages(final int maxReturned, final MessageRecoveryListener listener) throws Exception {
        final TransactionContext c = this.persistenceAdapter.getTransactionContext();
        try {
            this.adapter.doRecoverNextMessages(c, this.destination, this.lastRecoveredSequenceId.get(), this.lastRecoveredPriority.get(), maxReturned, this.isPrioritizedMessages(), new JDBCMessageRecoveryListener() {
                @Override
                public boolean recoverMessage(final long sequenceId, final byte[] data) throws Exception {
                    if (listener.hasSpace()) {
                        final Message msg = (Message)JDBCMessageStore.this.wireFormat.unmarshal(new ByteSequence(data));
                        msg.getMessageId().setBrokerSequenceId(sequenceId);
                        listener.recoverMessage(msg);
                        JDBCMessageStore.this.lastRecoveredSequenceId.set(sequenceId);
                        JDBCMessageStore.this.lastRecoveredPriority.set(msg.getPriority());
                        return true;
                    }
                    return false;
                }
                
                @Override
                public boolean recoverMessageReference(final String reference) throws Exception {
                    if (listener.hasSpace()) {
                        listener.recoverMessageReference(new MessageId(reference));
                        return true;
                    }
                    return false;
                }
            });
        }
        catch (SQLException e) {
            JDBCPersistenceAdapter.log("JDBC Failure: ", e);
        }
        finally {
            c.close();
        }
    }
    
    @Override
    public void resetBatching() {
        if (JDBCMessageStore.LOG.isTraceEnabled()) {
            JDBCMessageStore.LOG.trace(this.destination.getPhysicalName() + " resetBatching, existing last recovered seqId: " + this.lastRecoveredSequenceId.get());
        }
        this.lastRecoveredSequenceId.set(-1L);
        this.lastRecoveredPriority.set(126L);
    }
    
    @Override
    public void setBatch(final MessageId messageId) {
        try {
            final long[] storedValues = this.getStoreSequenceIdForMessageId(messageId);
            this.lastRecoveredSequenceId.set(storedValues[0]);
            this.lastRecoveredPriority.set(storedValues[1]);
        }
        catch (IOException ignoredAsAlreadyLogged) {
            this.lastRecoveredSequenceId.set(-1L);
            this.lastRecoveredPriority.set(126L);
        }
        if (JDBCMessageStore.LOG.isTraceEnabled()) {
            JDBCMessageStore.LOG.trace(this.destination.getPhysicalName() + " setBatch: new sequenceId: " + this.lastRecoveredSequenceId.get() + ", priority: " + this.lastRecoveredPriority.get());
        }
    }
    
    private long[] getStoreSequenceIdForMessageId(final MessageId messageId) throws IOException {
        long[] result = { -1L, 126L };
        final TransactionContext c = this.persistenceAdapter.getTransactionContext();
        try {
            result = this.adapter.getStoreSequenceId(c, this.destination, messageId);
        }
        catch (SQLException e) {
            JDBCPersistenceAdapter.log("JDBC Failure: ", e);
            throw IOExceptionSupport.create("Failed to get store sequenceId for messageId: " + messageId + ", on: " + this.destination + ". Reason: " + e, e);
        }
        finally {
            c.close();
        }
        return result;
    }
    
    @Override
    public void setPrioritizedMessages(final boolean prioritizedMessages) {
        super.setPrioritizedMessages(prioritizedMessages);
    }
    
    static {
        LOG = LoggerFactory.getLogger(JDBCMessageStore.class);
    }
    
    class Duration
    {
        static final int LIMIT = 100;
        final long start;
        final String name;
        
        Duration(final String name) {
            this.start = System.currentTimeMillis();
            this.name = name;
        }
        
        void end() {
            this.end(null);
        }
        
        void end(final Object o) {
            final long duration = System.currentTimeMillis() - this.start;
            if (duration > 100L) {
                System.err.println(this.name + " took a long time: " + duration + "ms " + o);
            }
        }
    }
}
