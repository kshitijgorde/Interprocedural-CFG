// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.jdbc.adapter;

import org.slf4j.LoggerFactory;
import org.apache.activemq.command.ProducerId;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import org.apache.activemq.command.SubscriptionInfo;
import java.util.Iterator;
import java.util.LinkedList;
import org.apache.activemq.store.jdbc.JDBCMessageIdScanListener;
import org.apache.activemq.store.jdbc.JDBCMessageRecoveryListener;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.MessageId;
import java.io.IOException;
import java.sql.Statement;
import org.apache.activemq.store.jdbc.JDBCPersistenceAdapter;
import org.apache.activemq.store.jdbc.TransactionContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReadWriteLock;
import org.apache.activemq.store.jdbc.Statements;
import org.slf4j.Logger;
import org.apache.activemq.store.jdbc.JDBCAdapter;

public class DefaultJDBCAdapter implements JDBCAdapter
{
    private static final Logger LOG;
    public static final int MAX_ROWS = 10000;
    protected Statements statements;
    protected boolean batchStatments;
    protected boolean prioritizedMessages;
    protected ReadWriteLock cleanupExclusiveLock;
    protected int maxRows;
    
    public DefaultJDBCAdapter() {
        this.batchStatments = true;
        this.cleanupExclusiveLock = new ReentrantReadWriteLock();
        this.maxRows = 10000;
    }
    
    protected void setBinaryData(final PreparedStatement s, final int index, final byte[] data) throws SQLException {
        s.setBytes(index, data);
    }
    
    protected byte[] getBinaryData(final ResultSet rs, final int index) throws SQLException {
        return rs.getBytes(index);
    }
    
    @Override
    public void doCreateTables(final TransactionContext c) throws SQLException, IOException {
        Statement s = null;
        this.cleanupExclusiveLock.writeLock().lock();
        try {
            boolean alreadyExists = false;
            ResultSet rs = null;
            try {
                rs = c.getConnection().getMetaData().getTables(null, null, this.statements.getFullMessageTableName(), new String[] { "TABLE" });
                alreadyExists = rs.next();
            }
            catch (Throwable ignore) {}
            finally {
                close(rs);
            }
            s = c.getConnection().createStatement();
            final String[] createStatments = this.statements.getCreateSchemaStatements();
            for (int i = 0; i < createStatments.length; ++i) {
                try {
                    DefaultJDBCAdapter.LOG.debug("Executing SQL: " + createStatments[i]);
                    s.execute(createStatments[i]);
                }
                catch (SQLException e) {
                    if (alreadyExists) {
                        DefaultJDBCAdapter.LOG.debug("Could not create JDBC tables; The message table already existed. Failure was: " + createStatments[i] + " Message: " + e.getMessage() + " SQLState: " + e.getSQLState() + " Vendor code: " + e.getErrorCode());
                    }
                    else {
                        DefaultJDBCAdapter.LOG.warn("Could not create JDBC tables; they could already exist. Failure was: " + createStatments[i] + " Message: " + e.getMessage() + " SQLState: " + e.getSQLState() + " Vendor code: " + e.getErrorCode());
                        JDBCPersistenceAdapter.log("Failure details: ", e);
                    }
                }
            }
            c.getConnection().commit();
        }
        finally {
            this.cleanupExclusiveLock.writeLock().unlock();
            try {
                s.close();
            }
            catch (Throwable t) {}
        }
    }
    
    @Override
    public void doDropTables(final TransactionContext c) throws SQLException, IOException {
        Statement s = null;
        this.cleanupExclusiveLock.writeLock().lock();
        try {
            s = c.getConnection().createStatement();
            final String[] dropStatments = this.statements.getDropSchemaStatements();
            for (int i = 0; i < dropStatments.length; ++i) {
                try {
                    DefaultJDBCAdapter.LOG.debug("Executing SQL: " + dropStatments[i]);
                    s.execute(dropStatments[i]);
                }
                catch (SQLException e) {
                    DefaultJDBCAdapter.LOG.warn("Could not drop JDBC tables; they may not exist. Failure was: " + dropStatments[i] + " Message: " + e.getMessage() + " SQLState: " + e.getSQLState() + " Vendor code: " + e.getErrorCode());
                    JDBCPersistenceAdapter.log("Failure details: ", e);
                }
            }
            c.getConnection().commit();
        }
        finally {
            this.cleanupExclusiveLock.writeLock().unlock();
            try {
                s.close();
            }
            catch (Throwable t) {}
        }
    }
    
    @Override
    public long doGetLastMessageStoreSequenceId(final TransactionContext c) throws SQLException, IOException {
        PreparedStatement s = null;
        ResultSet rs = null;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            s = c.getConnection().prepareStatement(this.statements.getFindLastSequenceIdInMsgsStatement());
            rs = s.executeQuery();
            long seq1 = 0L;
            if (rs.next()) {
                seq1 = rs.getLong(1);
            }
            rs.close();
            s.close();
            s = c.getConnection().prepareStatement(this.statements.getFindLastSequenceIdInAcksStatement());
            rs = s.executeQuery();
            long seq2 = 0L;
            if (rs.next()) {
                seq2 = rs.getLong(1);
            }
            final long seq3 = Math.max(seq1, seq2);
            return seq3;
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            close(rs);
            close(s);
        }
    }
    
    @Override
    public byte[] doGetMessageById(final TransactionContext c, final long storeSequenceId) throws SQLException, IOException {
        PreparedStatement s = null;
        ResultSet rs = null;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            s = c.getConnection().prepareStatement(this.statements.getFindMessageByIdStatement());
            s.setLong(1, storeSequenceId);
            rs = s.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return this.getBinaryData(rs, 1);
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            close(rs);
            close(s);
        }
    }
    
    @Override
    public void doAddMessage(final TransactionContext c, final long sequence, final MessageId messageID, final ActiveMQDestination destination, final byte[] data, final long expiration, final byte priority) throws SQLException, IOException {
        PreparedStatement s = c.getAddMessageStatement();
        this.cleanupExclusiveLock.readLock().lock();
        try {
            if (s == null) {
                s = c.getConnection().prepareStatement(this.statements.getAddMessageStatement());
                if (this.batchStatments) {
                    c.setAddMessageStatement(s);
                }
            }
            s.setLong(1, sequence);
            s.setString(2, messageID.getProducerId().toString());
            s.setLong(3, messageID.getProducerSequenceId());
            s.setString(4, destination.getQualifiedName());
            s.setLong(5, expiration);
            s.setLong(6, priority);
            this.setBinaryData(s, 7, data);
            if (this.batchStatments) {
                s.addBatch();
            }
            else if (s.executeUpdate() != 1) {
                throw new SQLException("Failed add a message");
            }
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            if (!this.batchStatments && s != null) {
                s.close();
            }
        }
    }
    
    @Override
    public void doAddMessageReference(final TransactionContext c, final long sequence, final MessageId messageID, final ActiveMQDestination destination, final long expirationTime, final String messageRef) throws SQLException, IOException {
        PreparedStatement s = c.getAddMessageStatement();
        this.cleanupExclusiveLock.readLock().lock();
        try {
            if (s == null) {
                s = c.getConnection().prepareStatement(this.statements.getAddMessageStatement());
                if (this.batchStatments) {
                    c.setAddMessageStatement(s);
                }
            }
            s.setLong(1, messageID.getBrokerSequenceId());
            s.setString(2, messageID.getProducerId().toString());
            s.setLong(3, messageID.getProducerSequenceId());
            s.setString(4, destination.getQualifiedName());
            s.setLong(5, expirationTime);
            s.setString(6, messageRef);
            if (this.batchStatments) {
                s.addBatch();
            }
            else if (s.executeUpdate() != 1) {
                throw new SQLException("Failed add a message");
            }
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            if (!this.batchStatments) {
                s.close();
            }
        }
    }
    
    @Override
    public long[] getStoreSequenceId(final TransactionContext c, final ActiveMQDestination destination, final MessageId messageID) throws SQLException, IOException {
        PreparedStatement s = null;
        ResultSet rs = null;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            s = c.getConnection().prepareStatement(this.statements.getFindMessageSequenceIdStatement());
            s.setString(1, messageID.getProducerId().toString());
            s.setLong(2, messageID.getProducerSequenceId());
            s.setString(3, destination.getQualifiedName());
            rs = s.executeQuery();
            if (!rs.next()) {
                return new long[] { 0L, 0L };
            }
            return new long[] { rs.getLong(1), rs.getLong(2) };
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            close(rs);
            close(s);
        }
    }
    
    @Override
    public byte[] doGetMessage(final TransactionContext c, final MessageId id) throws SQLException, IOException {
        PreparedStatement s = null;
        ResultSet rs = null;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            s = c.getConnection().prepareStatement(this.statements.getFindMessageStatement());
            s.setString(1, id.getProducerId().toString());
            s.setLong(2, id.getProducerSequenceId());
            rs = s.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return this.getBinaryData(rs, 1);
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            close(rs);
            close(s);
        }
    }
    
    @Override
    public String doGetMessageReference(final TransactionContext c, final long seq) throws SQLException, IOException {
        PreparedStatement s = null;
        ResultSet rs = null;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            s = c.getConnection().prepareStatement(this.statements.getFindMessageStatement());
            s.setLong(1, seq);
            rs = s.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return rs.getString(1);
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            close(rs);
            close(s);
        }
    }
    
    @Override
    public void doRemoveMessage(final TransactionContext c, final long seq) throws SQLException, IOException {
        PreparedStatement s = c.getRemovedMessageStatement();
        this.cleanupExclusiveLock.readLock().lock();
        try {
            if (s == null) {
                s = c.getConnection().prepareStatement(this.statements.getRemoveMessageStatement());
                if (this.batchStatments) {
                    c.setRemovedMessageStatement(s);
                }
            }
            s.setLong(1, seq);
            if (this.batchStatments) {
                s.addBatch();
            }
            else if (s.executeUpdate() != 1) {
                throw new SQLException("Failed to remove message");
            }
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            if (!this.batchStatments && s != null) {
                s.close();
            }
        }
    }
    
    @Override
    public void doRecover(final TransactionContext c, final ActiveMQDestination destination, final JDBCMessageRecoveryListener listener) throws Exception {
        PreparedStatement s = null;
        ResultSet rs = null;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            s = c.getConnection().prepareStatement(this.statements.getFindAllMessagesStatement());
            s.setString(1, destination.getQualifiedName());
            rs = s.executeQuery();
            if (this.statements.isUseExternalMessageReferences()) {
                while (rs.next()) {
                    if (!listener.recoverMessageReference(rs.getString(2))) {
                        break;
                    }
                }
            }
            else {
                while (rs.next() && listener.recoverMessage(rs.getLong(1), this.getBinaryData(rs, 2))) {}
            }
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            close(rs);
            close(s);
        }
    }
    
    @Override
    public void doMessageIdScan(final TransactionContext c, final int limit, final JDBCMessageIdScanListener listener) throws SQLException, IOException {
        PreparedStatement s = null;
        ResultSet rs = null;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            s = c.getConnection().prepareStatement(this.statements.getFindAllMessageIdsStatement());
            s.setMaxRows(limit);
            rs = s.executeQuery();
            final LinkedList<MessageId> reverseOrderIds = new LinkedList<MessageId>();
            while (rs.next()) {
                reverseOrderIds.addFirst(new MessageId(rs.getString(2), rs.getLong(3)));
            }
            if (DefaultJDBCAdapter.LOG.isDebugEnabled()) {
                DefaultJDBCAdapter.LOG.debug("messageIdScan with limit (" + limit + "), resulted in: " + reverseOrderIds.size() + " ids");
            }
            for (final MessageId id : reverseOrderIds) {
                listener.messageId(id);
            }
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            close(rs);
            close(s);
        }
    }
    
    @Override
    public void doSetLastAckWithPriority(final TransactionContext c, final ActiveMQDestination destination, final String clientId, final String subscriptionName, final long seq, final long prio) throws SQLException, IOException {
        PreparedStatement s = c.getUpdateLastAckStatement();
        this.cleanupExclusiveLock.readLock().lock();
        try {
            if (s == null) {
                s = c.getConnection().prepareStatement(this.statements.getUpdateLastPriorityAckRowOfDurableSubStatement());
                if (this.batchStatments) {
                    c.setUpdateLastAckStatement(s);
                }
            }
            s.setLong(1, seq);
            s.setString(2, destination.getQualifiedName());
            s.setString(3, clientId);
            s.setString(4, subscriptionName);
            s.setLong(5, prio);
            if (this.batchStatments) {
                s.addBatch();
            }
            else if (s.executeUpdate() != 1) {
                throw new SQLException("Failed update last ack with priority: " + prio + ", for sub: " + subscriptionName);
            }
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            if (!this.batchStatments) {
                close(s);
            }
        }
    }
    
    @Override
    public void doSetLastAck(final TransactionContext c, final ActiveMQDestination destination, final String clientId, final String subscriptionName, final long seq, final long priority) throws SQLException, IOException {
        PreparedStatement s = c.getUpdateLastAckStatement();
        this.cleanupExclusiveLock.readLock().lock();
        try {
            if (s == null) {
                s = c.getConnection().prepareStatement(this.statements.getUpdateDurableLastAckStatement());
                if (this.batchStatments) {
                    c.setUpdateLastAckStatement(s);
                }
            }
            s.setLong(1, seq);
            s.setString(2, destination.getQualifiedName());
            s.setString(3, clientId);
            s.setString(4, subscriptionName);
            if (this.batchStatments) {
                s.addBatch();
            }
            else if (s.executeUpdate() != 1) {
                throw new IOException("Could not update last ack seq : " + seq + ", for sub: " + subscriptionName);
            }
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            if (!this.batchStatments) {
                close(s);
            }
        }
    }
    
    @Override
    public void doRecoverSubscription(final TransactionContext c, final ActiveMQDestination destination, final String clientId, final String subscriptionName, final JDBCMessageRecoveryListener listener) throws Exception {
        PreparedStatement s = null;
        ResultSet rs = null;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            s = c.getConnection().prepareStatement(this.statements.getFindAllDurableSubMessagesStatement());
            s.setString(1, destination.getQualifiedName());
            s.setString(2, clientId);
            s.setString(3, subscriptionName);
            rs = s.executeQuery();
            if (this.statements.isUseExternalMessageReferences()) {
                while (rs.next()) {
                    if (!listener.recoverMessageReference(rs.getString(2))) {
                        break;
                    }
                }
            }
            else {
                while (rs.next() && listener.recoverMessage(rs.getLong(1), this.getBinaryData(rs, 2))) {}
            }
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            close(rs);
            close(s);
        }
    }
    
    @Override
    public void doRecoverNextMessages(final TransactionContext c, final ActiveMQDestination destination, final String clientId, final String subscriptionName, final long seq, final long priority, final int maxReturned, final JDBCMessageRecoveryListener listener) throws Exception {
        PreparedStatement s = null;
        ResultSet rs = null;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            s = c.getConnection().prepareStatement(this.statements.getFindDurableSubMessagesStatement());
            s.setMaxRows(Math.max(maxReturned * 2, this.maxRows));
            s.setString(1, destination.getQualifiedName());
            s.setString(2, clientId);
            s.setString(3, subscriptionName);
            s.setLong(4, seq);
            rs = s.executeQuery();
            int count = 0;
            if (this.statements.isUseExternalMessageReferences()) {
                while (rs.next() && count < maxReturned) {
                    if (listener.recoverMessageReference(rs.getString(1))) {
                        ++count;
                    }
                }
            }
            else {
                while (rs.next() && count < maxReturned) {
                    if (listener.recoverMessage(rs.getLong(1), this.getBinaryData(rs, 2))) {
                        ++count;
                    }
                }
            }
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            close(rs);
            close(s);
        }
    }
    
    @Override
    public void doRecoverNextMessagesWithPriority(final TransactionContext c, final ActiveMQDestination destination, final String clientId, final String subscriptionName, final long seq, final long priority, final int maxReturned, final JDBCMessageRecoveryListener listener) throws Exception {
        PreparedStatement s = null;
        ResultSet rs = null;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            s = c.getConnection().prepareStatement(this.statements.getFindDurableSubMessagesByPriorityStatement());
            s.setMaxRows(this.maxRows);
            s.setString(1, destination.getQualifiedName());
            s.setString(2, clientId);
            s.setString(3, subscriptionName);
            s.setLong(4, seq);
            s.setLong(5, priority);
            rs = s.executeQuery();
            int count = 0;
            if (this.statements.isUseExternalMessageReferences()) {
                while (rs.next() && count < maxReturned) {
                    if (listener.recoverMessageReference(rs.getString(1))) {
                        ++count;
                    }
                }
            }
            else {
                while (rs.next() && count < maxReturned) {
                    if (listener.recoverMessage(rs.getLong(1), this.getBinaryData(rs, 2))) {
                        ++count;
                    }
                }
            }
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            close(rs);
            close(s);
        }
    }
    
    @Override
    public int doGetDurableSubscriberMessageCount(final TransactionContext c, final ActiveMQDestination destination, final String clientId, final String subscriptionName, final boolean isPrioritizedMessages) throws SQLException, IOException {
        PreparedStatement s = null;
        ResultSet rs = null;
        int result = 0;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            if (isPrioritizedMessages) {
                s = c.getConnection().prepareStatement(this.statements.getDurableSubscriberMessageCountStatementWithPriority());
            }
            else {
                s = c.getConnection().prepareStatement(this.statements.getDurableSubscriberMessageCountStatement());
            }
            s.setString(1, destination.getQualifiedName());
            s.setString(2, clientId);
            s.setString(3, subscriptionName);
            rs = s.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            close(rs);
            close(s);
        }
        return result;
    }
    
    @Override
    public void doSetSubscriberEntry(final TransactionContext c, final SubscriptionInfo info, final boolean retroactive, final boolean isPrioritizedMessages) throws SQLException, IOException {
        PreparedStatement s = null;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            long lastMessageId = -1L;
            if (!retroactive) {
                s = c.getConnection().prepareStatement(this.statements.getFindLastSequenceIdInMsgsStatement());
                ResultSet rs = null;
                try {
                    rs = s.executeQuery();
                    if (rs.next()) {
                        lastMessageId = rs.getLong(1);
                    }
                }
                finally {
                    close(rs);
                    close(s);
                }
            }
            s = c.getConnection().prepareStatement(this.statements.getCreateDurableSubStatement());
            int maxPriority = 1;
            if (isPrioritizedMessages) {
                maxPriority = 10;
            }
            for (int priority = 0; priority < maxPriority; ++priority) {
                s.setString(1, info.getDestination().getQualifiedName());
                s.setString(2, info.getClientId());
                s.setString(3, info.getSubscriptionName());
                s.setString(4, info.getSelector());
                s.setLong(5, lastMessageId);
                s.setString(6, info.getSubscribedDestination().getQualifiedName());
                s.setLong(7, priority);
                if (s.executeUpdate() != 1) {
                    throw new IOException("Could not create durable subscription for: " + info.getClientId());
                }
            }
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            close(s);
        }
    }
    
    @Override
    public SubscriptionInfo doGetSubscriberEntry(final TransactionContext c, final ActiveMQDestination destination, final String clientId, final String subscriptionName) throws SQLException, IOException {
        PreparedStatement s = null;
        ResultSet rs = null;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            s = c.getConnection().prepareStatement(this.statements.getFindDurableSubStatement());
            s.setString(1, destination.getQualifiedName());
            s.setString(2, clientId);
            s.setString(3, subscriptionName);
            rs = s.executeQuery();
            if (!rs.next()) {
                return null;
            }
            final SubscriptionInfo subscription = new SubscriptionInfo();
            subscription.setDestination(destination);
            subscription.setClientId(clientId);
            subscription.setSubscriptionName(subscriptionName);
            subscription.setSelector(rs.getString(1));
            subscription.setSubscribedDestination(ActiveMQDestination.createDestination(rs.getString(2), (byte)1));
            return subscription;
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            close(rs);
            close(s);
        }
    }
    
    @Override
    public SubscriptionInfo[] doGetAllSubscriptions(final TransactionContext c, final ActiveMQDestination destination) throws SQLException, IOException {
        PreparedStatement s = null;
        ResultSet rs = null;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            s = c.getConnection().prepareStatement(this.statements.getFindAllDurableSubsStatement());
            s.setString(1, destination.getQualifiedName());
            rs = s.executeQuery();
            final ArrayList<SubscriptionInfo> rc = new ArrayList<SubscriptionInfo>();
            while (rs.next()) {
                final SubscriptionInfo subscription = new SubscriptionInfo();
                subscription.setDestination(destination);
                subscription.setSelector(rs.getString(1));
                subscription.setSubscriptionName(rs.getString(2));
                subscription.setClientId(rs.getString(3));
                subscription.setSubscribedDestination(ActiveMQDestination.createDestination(rs.getString(4), (byte)1));
                rc.add(subscription);
            }
            return rc.toArray(new SubscriptionInfo[rc.size()]);
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            close(rs);
            close(s);
        }
    }
    
    @Override
    public void doRemoveAllMessages(final TransactionContext c, final ActiveMQDestination destinationName) throws SQLException, IOException {
        PreparedStatement s = null;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            s = c.getConnection().prepareStatement(this.statements.getRemoveAllMessagesStatement());
            s.setString(1, destinationName.getQualifiedName());
            s.executeUpdate();
            s.close();
            s = c.getConnection().prepareStatement(this.statements.getRemoveAllSubscriptionsStatement());
            s.setString(1, destinationName.getQualifiedName());
            s.executeUpdate();
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            close(s);
        }
    }
    
    @Override
    public void doDeleteSubscription(final TransactionContext c, final ActiveMQDestination destination, final String clientId, final String subscriptionName) throws SQLException, IOException {
        PreparedStatement s = null;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            s = c.getConnection().prepareStatement(this.statements.getDeleteSubscriptionStatement());
            s.setString(1, destination.getQualifiedName());
            s.setString(2, clientId);
            s.setString(3, subscriptionName);
            s.executeUpdate();
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            close(s);
        }
    }
    
    @Override
    public void doDeleteOldMessages(final TransactionContext c, final boolean isPrioritizedMessages) throws SQLException, IOException {
        PreparedStatement s = null;
        this.cleanupExclusiveLock.writeLock().lock();
        try {
            if (isPrioritizedMessages) {
                DefaultJDBCAdapter.LOG.debug("Executing SQL: " + this.statements.getDeleteOldMessagesStatementWithPriority());
                s = c.getConnection().prepareStatement(this.statements.getDeleteOldMessagesStatementWithPriority());
            }
            else {
                DefaultJDBCAdapter.LOG.debug("Executing SQL: " + this.statements.getDeleteOldMessagesStatement());
                s = c.getConnection().prepareStatement(this.statements.getDeleteOldMessagesStatement());
            }
            s.setLong(1, System.currentTimeMillis());
            final int i = s.executeUpdate();
            DefaultJDBCAdapter.LOG.debug("Deleted " + i + " old message(s).");
        }
        finally {
            this.cleanupExclusiveLock.writeLock().unlock();
            close(s);
        }
    }
    
    @Override
    public long doGetLastAckedDurableSubscriberMessageId(final TransactionContext c, final ActiveMQDestination destination, final String clientId, final String subscriberName) throws SQLException, IOException {
        PreparedStatement s = null;
        ResultSet rs = null;
        long result = -1L;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            s = c.getConnection().prepareStatement(this.statements.getLastAckedDurableSubscriberMessageStatement());
            s.setString(1, destination.getQualifiedName());
            s.setString(2, clientId);
            s.setString(3, subscriberName);
            rs = s.executeQuery();
            if (rs.next()) {
                result = rs.getLong(1);
            }
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            close(rs);
            close(s);
        }
        return result;
    }
    
    private static void close(final PreparedStatement s) {
        try {
            s.close();
        }
        catch (Throwable t) {}
    }
    
    private static void close(final ResultSet rs) {
        try {
            rs.close();
        }
        catch (Throwable t) {}
    }
    
    @Override
    public Set<ActiveMQDestination> doGetDestinations(final TransactionContext c) throws SQLException, IOException {
        final HashSet<ActiveMQDestination> rc = new HashSet<ActiveMQDestination>();
        PreparedStatement s = null;
        ResultSet rs = null;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            s = c.getConnection().prepareStatement(this.statements.getFindAllDestinationsStatement());
            rs = s.executeQuery();
            while (rs.next()) {
                rc.add(ActiveMQDestination.createDestination(rs.getString(1), (byte)1));
            }
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            close(rs);
            close(s);
        }
        return rc;
    }
    
    public boolean isBatchStatments() {
        return this.batchStatments;
    }
    
    public void setBatchStatments(final boolean batchStatments) {
        this.batchStatments = batchStatments;
    }
    
    @Override
    public void setUseExternalMessageReferences(final boolean useExternalMessageReferences) {
        this.statements.setUseExternalMessageReferences(useExternalMessageReferences);
    }
    
    public Statements getStatements() {
        return this.statements;
    }
    
    @Override
    public void setStatements(final Statements statements) {
        this.statements = statements;
    }
    
    @Override
    public int getMaxRows() {
        return this.maxRows;
    }
    
    @Override
    public void setMaxRows(final int maxRows) {
        this.maxRows = maxRows;
    }
    
    public byte[] doGetNextDurableSubscriberMessageStatement(final TransactionContext c, final ActiveMQDestination destination, final String clientId, final String subscriberName) throws SQLException, IOException {
        PreparedStatement s = null;
        ResultSet rs = null;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            s = c.getConnection().prepareStatement(this.statements.getNextDurableSubscriberMessageStatement());
            s.setString(1, destination.getQualifiedName());
            s.setString(2, clientId);
            s.setString(3, subscriberName);
            rs = s.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return this.getBinaryData(rs, 1);
        }
        finally {
            close(rs);
            this.cleanupExclusiveLock.readLock().unlock();
            close(s);
        }
    }
    
    @Override
    public int doGetMessageCount(final TransactionContext c, final ActiveMQDestination destination) throws SQLException, IOException {
        PreparedStatement s = null;
        ResultSet rs = null;
        int result = 0;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            s = c.getConnection().prepareStatement(this.statements.getDestinationMessageCountStatement());
            s.setString(1, destination.getQualifiedName());
            rs = s.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            close(rs);
            close(s);
        }
        return result;
    }
    
    @Override
    public void doRecoverNextMessages(final TransactionContext c, final ActiveMQDestination destination, final long nextSeq, final long priority, final int maxReturned, final boolean isPrioritizedMessages, final JDBCMessageRecoveryListener listener) throws Exception {
        PreparedStatement s = null;
        ResultSet rs = null;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            if (isPrioritizedMessages) {
                s = c.getConnection().prepareStatement(this.statements.getFindNextMessagesByPriorityStatement());
            }
            else {
                s = c.getConnection().prepareStatement(this.statements.getFindNextMessagesStatement());
            }
            s.setMaxRows(Math.max(maxReturned * 2, this.maxRows));
            s.setString(1, destination.getQualifiedName());
            s.setLong(2, nextSeq);
            if (isPrioritizedMessages) {
                s.setLong(3, priority);
                s.setLong(4, priority);
            }
            rs = s.executeQuery();
            int count = 0;
            if (this.statements.isUseExternalMessageReferences()) {
                while (rs.next() && count < maxReturned) {
                    if (!listener.recoverMessageReference(rs.getString(1))) {
                        DefaultJDBCAdapter.LOG.debug("Stopped recover next messages");
                        break;
                    }
                    ++count;
                }
            }
            else {
                while (rs.next() && count < maxReturned) {
                    if (!listener.recoverMessage(rs.getLong(1), this.getBinaryData(rs, 2))) {
                        DefaultJDBCAdapter.LOG.debug("Stopped recover next messages");
                        break;
                    }
                    ++count;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            close(rs);
            close(s);
        }
    }
    
    @Override
    public long doGetLastProducerSequenceId(final TransactionContext c, final ProducerId id) throws SQLException, IOException {
        PreparedStatement s = null;
        ResultSet rs = null;
        this.cleanupExclusiveLock.readLock().lock();
        try {
            s = c.getConnection().prepareStatement(this.statements.getLastProducerSequenceIdStatement());
            s.setString(1, id.toString());
            rs = s.executeQuery();
            long seq = -1L;
            if (rs.next()) {
                seq = rs.getLong(1);
            }
            return seq;
        }
        finally {
            this.cleanupExclusiveLock.readLock().unlock();
            close(rs);
            close(s);
        }
    }
    
    static {
        LOG = LoggerFactory.getLogger(DefaultJDBCAdapter.class);
    }
}
