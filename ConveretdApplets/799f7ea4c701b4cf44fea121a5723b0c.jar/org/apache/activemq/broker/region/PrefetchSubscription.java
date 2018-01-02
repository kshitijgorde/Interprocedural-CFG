// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region;

import org.slf4j.LoggerFactory;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ConsumerControl;
import org.apache.activemq.command.MessageDispatch;
import org.apache.activemq.command.Command;
import org.apache.activemq.command.Message;
import java.util.Collection;
import java.io.IOException;
import org.apache.activemq.command.MessageId;
import org.apache.activemq.transaction.Synchronization;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.apache.activemq.command.MessageAck;
import javax.jms.JMSException;
import org.apache.activemq.command.MessageDispatchNotification;
import java.util.Iterator;
import org.apache.activemq.command.Response;
import org.apache.activemq.command.MessagePull;
import org.apache.activemq.broker.region.cursors.VMPendingMessageCursor;
import javax.jms.InvalidSelectorException;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.activemq.command.ConsumerInfo;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.broker.Broker;
import java.util.concurrent.CountDownLatch;
import org.apache.activemq.usage.SystemUsage;
import java.util.List;
import org.apache.activemq.broker.region.cursors.PendingMessageCursor;
import org.apache.activemq.thread.Scheduler;
import org.slf4j.Logger;

public abstract class PrefetchSubscription extends AbstractSubscription
{
    private static final Logger LOG;
    protected final Scheduler scheduler;
    protected PendingMessageCursor pending;
    protected final List<MessageReference> dispatched;
    protected int prefetchExtension;
    protected boolean usePrefetchExtension;
    protected long enqueueCounter;
    protected long dispatchCounter;
    protected long dequeueCounter;
    private int maxProducersToAudit;
    private int maxAuditDepth;
    protected final SystemUsage usageManager;
    protected final Object pendingLock;
    private final Object dispatchLock;
    private final CountDownLatch okForAckAsDispatchDone;
    
    public PrefetchSubscription(final Broker broker, final SystemUsage usageManager, final ConnectionContext context, final ConsumerInfo info, final PendingMessageCursor cursor) throws InvalidSelectorException {
        super(broker, context, info);
        this.dispatched = new CopyOnWriteArrayList<MessageReference>();
        this.usePrefetchExtension = true;
        this.maxProducersToAudit = 32;
        this.maxAuditDepth = 2048;
        this.pendingLock = new Object();
        this.dispatchLock = new Object();
        this.okForAckAsDispatchDone = new CountDownLatch(1);
        this.usageManager = usageManager;
        this.pending = cursor;
        this.scheduler = broker.getScheduler();
    }
    
    public PrefetchSubscription(final Broker broker, final SystemUsage usageManager, final ConnectionContext context, final ConsumerInfo info) throws InvalidSelectorException {
        this(broker, usageManager, context, info, new VMPendingMessageCursor(false));
    }
    
    @Override
    public Response pullMessage(final ConnectionContext context, final MessagePull pull) throws Exception {
        if (this.getPrefetchSize() == 0 && !this.isSlave()) {
            final long dispatchCounterBeforePull;
            synchronized (this) {
                ++this.prefetchExtension;
                dispatchCounterBeforePull = this.dispatchCounter;
            }
            for (final Destination dest : this.destinations) {
                dest.iterate();
            }
            this.dispatchPending();
            synchronized (this) {
                if (dispatchCounterBeforePull == this.dispatchCounter) {
                    if (pull.getTimeout() == -1L) {
                        this.add(QueueMessageReference.NULL_MESSAGE);
                        this.dispatchPending();
                    }
                    if (pull.getTimeout() > 0L) {
                        this.scheduler.executeAfterDelay(new Runnable() {
                            @Override
                            public void run() {
                                PrefetchSubscription.this.pullTimeout(dispatchCounterBeforePull);
                            }
                        }, pull.getTimeout());
                    }
                }
            }
        }
        return null;
    }
    
    final void pullTimeout(final long dispatchCounterBeforePull) {
        synchronized (this.pendingLock) {
            if (dispatchCounterBeforePull == this.dispatchCounter) {
                try {
                    this.add(QueueMessageReference.NULL_MESSAGE);
                    this.dispatchPending();
                }
                catch (Exception e) {
                    this.context.getConnection().serviceException(e);
                }
            }
        }
    }
    
    @Override
    public void add(final MessageReference node) throws Exception {
        synchronized (this.pendingLock) {
            if (!this.destinations.contains(node.getRegionDestination()) && node != QueueMessageReference.NULL_MESSAGE) {
                return;
            }
            ++this.enqueueCounter;
            this.pending.addMessageLast(node);
        }
        this.dispatchPending();
    }
    
    @Override
    public void processMessageDispatchNotification(final MessageDispatchNotification mdn) throws Exception {
        synchronized (this.pendingLock) {
            try {
                this.pending.reset();
                while (this.pending.hasNext()) {
                    final MessageReference node = this.pending.next();
                    node.decrementReferenceCount();
                    if (node.getMessageId().equals(mdn.getMessageId())) {
                        synchronized (this.dispatchLock) {
                            this.pending.remove();
                            this.createMessageDispatch(node, node.getMessage());
                            this.dispatched.add(node);
                            this.onDispatch(node, node.getMessage());
                        }
                        return;
                    }
                }
            }
            finally {
                this.pending.release();
            }
        }
        throw new JMSException("Slave broker out of sync with master: Dispatched message (" + mdn.getMessageId() + ") was not in the pending list for " + mdn.getConsumerId() + " on " + mdn.getDestination().getPhysicalName());
    }
    
    @Override
    public final void acknowledge(final ConnectionContext context, final MessageAck ack) throws Exception {
        boolean callDispatchMatched = false;
        Destination destination = null;
        if (!this.isSlave() && !this.okForAckAsDispatchDone.await(0L, TimeUnit.MILLISECONDS)) {
            PrefetchSubscription.LOG.warn("Ignoring ack received before dispatch; result of failover with an outstanding ack. Acked messages will be replayed if present on this broker. Ignored ack: " + ack);
            return;
        }
        if (PrefetchSubscription.LOG.isTraceEnabled()) {
            PrefetchSubscription.LOG.trace("ack:" + ack);
        }
        synchronized (this.dispatchLock) {
            if (ack.isStandardAck()) {
                this.assertAckMatchesDispatched(ack);
                int index = 0;
                boolean inAckRange = false;
                final List<MessageReference> removeList = new ArrayList<MessageReference>();
                for (final MessageReference node : this.dispatched) {
                    final MessageId messageId = node.getMessageId();
                    if (ack.getFirstMessageId() == null || ack.getFirstMessageId().equals(messageId)) {
                        inAckRange = true;
                    }
                    if (inAckRange) {
                        if (!context.isInTransaction()) {
                            ++this.dequeueCounter;
                            node.getRegionDestination().getDestinationStatistics().getInflight().decrement();
                            removeList.add(node);
                        }
                        else {
                            context.getTransaction().addSynchronization(new Synchronization() {
                                @Override
                                public void afterCommit() throws Exception {
                                    synchronized (PrefetchSubscription.this.dispatchLock) {
                                        final PrefetchSubscription this$0 = PrefetchSubscription.this;
                                        ++this$0.dequeueCounter;
                                        PrefetchSubscription.this.dispatched.remove(node);
                                        node.getRegionDestination().getDestinationStatistics().getInflight().decrement();
                                    }
                                }
                                
                                @Override
                                public void afterRollback() throws Exception {
                                    synchronized (PrefetchSubscription.this.dispatchLock) {
                                        if (PrefetchSubscription.this.isSlave()) {
                                            node.getRegionDestination().getDestinationStatistics().getInflight().decrement();
                                        }
                                    }
                                }
                            });
                        }
                        ++index;
                        this.acknowledge(context, ack, node);
                        if (ack.getLastMessageId().equals(messageId)) {
                            if (this.getPrefetchSize() == 0) {
                                this.prefetchExtension = Math.max(0, this.prefetchExtension - index);
                            }
                            else if (this.usePrefetchExtension && context.isInTransaction()) {
                                this.prefetchExtension = Math.max(this.prefetchExtension, index);
                            }
                            destination = node.getRegionDestination();
                            callDispatchMatched = true;
                            break;
                        }
                        continue;
                    }
                }
                for (final MessageReference node : removeList) {
                    this.dispatched.remove(node);
                }
                if (!callDispatchMatched) {
                    PrefetchSubscription.LOG.warn("Could not correlate acknowledgment with dispatched message: " + ack);
                }
            }
            else if (ack.isIndividualAck()) {
                for (final MessageReference node2 : this.dispatched) {
                    final MessageId messageId2 = node2.getMessageId();
                    if (ack.getLastMessageId().equals(messageId2)) {
                        ++this.dequeueCounter;
                        node2.getRegionDestination().getDestinationStatistics().getInflight().decrement();
                        destination = node2.getRegionDestination();
                        this.acknowledge(context, ack, node2);
                        this.dispatched.remove(node2);
                        this.prefetchExtension = Math.max(0, this.prefetchExtension - 1);
                        callDispatchMatched = true;
                        break;
                    }
                }
            }
            else if (ack.isDeliveredAck()) {
                int index = 0;
                for (final MessageReference node3 : this.dispatched) {
                    if (node3.isExpired()) {
                        if (this.broker.isExpired(node3)) {
                            node3.getRegionDestination().messageExpired(context, this, node3);
                        }
                        this.dispatched.remove(node3);
                        node3.getRegionDestination().getDestinationStatistics().getInflight().decrement();
                    }
                    if (ack.getLastMessageId().equals(node3.getMessageId())) {
                        if (this.usePrefetchExtension) {
                            this.prefetchExtension = Math.max(this.prefetchExtension, index + 1);
                        }
                        destination = node3.getRegionDestination();
                        callDispatchMatched = true;
                        break;
                    }
                    ++index;
                }
                if (!callDispatchMatched) {
                    throw new JMSException("Could not correlate acknowledgment with dispatched message: " + ack);
                }
            }
            else if (ack.isRedeliveredAck()) {
                boolean inAckRange2 = false;
                for (final MessageReference node3 : this.dispatched) {
                    final MessageId messageId3 = node3.getMessageId();
                    if (ack.getFirstMessageId() == null || ack.getFirstMessageId().equals(messageId3)) {
                        inAckRange2 = true;
                    }
                    if (inAckRange2 && ack.getLastMessageId().equals(messageId3)) {
                        destination = node3.getRegionDestination();
                        callDispatchMatched = true;
                        break;
                    }
                }
                if (!callDispatchMatched) {
                    throw new JMSException("Could not correlate acknowledgment with dispatched message: " + ack);
                }
            }
            else if (ack.isPoisonAck()) {
                if (ack.isInTransaction()) {
                    throw new JMSException("Poison ack cannot be transacted: " + ack);
                }
                int index = 0;
                boolean inAckRange = false;
                final List<MessageReference> removeList = new ArrayList<MessageReference>();
                for (final MessageReference node : this.dispatched) {
                    final MessageId messageId = node.getMessageId();
                    if (ack.getFirstMessageId() == null || ack.getFirstMessageId().equals(messageId)) {
                        inAckRange = true;
                    }
                    if (inAckRange) {
                        if (ack.getPoisonCause() != null) {
                            node.getMessage().setProperty("dlqDeliveryFailureCause", ack.getPoisonCause().toString());
                        }
                        this.sendToDLQ(context, node);
                        node.getRegionDestination().getDestinationStatistics().getInflight().decrement();
                        removeList.add(node);
                        ++this.dequeueCounter;
                        ++index;
                        this.acknowledge(context, ack, node);
                        if (ack.getLastMessageId().equals(messageId)) {
                            this.prefetchExtension = Math.max(0, this.prefetchExtension - (index + 1));
                            destination = node.getRegionDestination();
                            callDispatchMatched = true;
                            break;
                        }
                        continue;
                    }
                }
                for (final MessageReference node : removeList) {
                    this.dispatched.remove(node);
                }
                if (!callDispatchMatched) {
                    throw new JMSException("Could not correlate acknowledgment with dispatched message: " + ack);
                }
            }
        }
        if (callDispatchMatched && destination != null) {
            destination.wakeup();
            this.dispatchPending();
        }
        else {
            if (this.isSlave()) {
                throw new JMSException("Slave broker out of sync with master: Acknowledgment (" + ack + ") was not in the dispatch list: " + this.dispatched);
            }
            PrefetchSubscription.LOG.debug("Acknowledgment out of sync (Normally occurs when failover connection reconnects): " + ack);
        }
    }
    
    protected void assertAckMatchesDispatched(final MessageAck ack) throws JMSException {
        final MessageId firstAckedMsg = ack.getFirstMessageId();
        final MessageId lastAckedMsg = ack.getLastMessageId();
        int checkCount = 0;
        boolean checkFoundStart = false;
        boolean checkFoundEnd = false;
        for (final MessageReference node : this.dispatched) {
            if (firstAckedMsg == null) {
                checkFoundStart = true;
            }
            else if (!checkFoundStart && firstAckedMsg.equals(node.getMessageId())) {
                checkFoundStart = true;
            }
            if (checkFoundStart) {
                ++checkCount;
            }
            if (lastAckedMsg != null && lastAckedMsg.equals(node.getMessageId())) {
                checkFoundEnd = true;
                break;
            }
        }
        if (!checkFoundStart && firstAckedMsg != null) {
            throw new JMSException("Unmatched acknowledge: " + ack + "; Could not find Message-ID " + firstAckedMsg + " in dispatched-list (start of ack)");
        }
        if (!checkFoundEnd && lastAckedMsg != null) {
            throw new JMSException("Unmatched acknowledge: " + ack + "; Could not find Message-ID " + lastAckedMsg + " in dispatched-list (end of ack)");
        }
        if (ack.getMessageCount() != checkCount && !ack.isInTransaction()) {
            throw new JMSException("Unmatched acknowledge: " + ack + "; Expected message count (" + ack.getMessageCount() + ") differs from count in dispatched-list (" + checkCount + ")");
        }
    }
    
    protected void sendToDLQ(final ConnectionContext context, final MessageReference node) throws IOException, Exception {
        this.broker.getRoot().sendToDeadLetterQueue(context, node, this);
    }
    
    @Override
    public int getInFlightSize() {
        return this.dispatched.size();
    }
    
    @Override
    public boolean isFull() {
        return this.dispatched.size() - this.prefetchExtension >= this.info.getPrefetchSize();
    }
    
    @Override
    public boolean isLowWaterMark() {
        return this.dispatched.size() - this.prefetchExtension <= this.info.getPrefetchSize() * 0.4;
    }
    
    @Override
    public boolean isHighWaterMark() {
        return this.dispatched.size() - this.prefetchExtension >= this.info.getPrefetchSize() * 0.9;
    }
    
    @Override
    public int countBeforeFull() {
        return this.info.getPrefetchSize() + this.prefetchExtension - this.dispatched.size();
    }
    
    @Override
    public int getPendingQueueSize() {
        return this.pending.size();
    }
    
    @Override
    public int getDispatchedQueueSize() {
        return this.dispatched.size();
    }
    
    @Override
    public long getDequeueCounter() {
        return this.dequeueCounter;
    }
    
    @Override
    public long getDispatchedCounter() {
        return this.dispatchCounter;
    }
    
    @Override
    public long getEnqueueCounter() {
        return this.enqueueCounter;
    }
    
    @Override
    public boolean isRecoveryRequired() {
        return this.pending.isRecoveryRequired();
    }
    
    public PendingMessageCursor getPending() {
        return this.pending;
    }
    
    public void setPending(final PendingMessageCursor pending) {
        this.pending = pending;
        if (this.pending != null) {
            this.pending.setSystemUsage(this.usageManager);
            this.pending.setMemoryUsageHighWaterMark(this.getCursorMemoryHighWaterMark());
        }
    }
    
    @Override
    public void add(final ConnectionContext context, final Destination destination) throws Exception {
        synchronized (this.pendingLock) {
            super.add(context, destination);
            this.pending.add(context, destination);
        }
    }
    
    @Override
    public List<MessageReference> remove(final ConnectionContext context, final Destination destination) throws Exception {
        final List<MessageReference> rc = new ArrayList<MessageReference>();
        synchronized (this.pendingLock) {
            super.remove(context, destination);
            rc.addAll(this.pending.remove(context, destination));
            synchronized (this.dispatchLock) {
                final ArrayList<MessageReference> references = new ArrayList<MessageReference>();
                for (final MessageReference r : this.dispatched) {
                    if (r.getRegionDestination() == destination) {
                        references.add(r);
                    }
                }
                rc.addAll(references);
                destination.getDestinationStatistics().getDispatched().subtract(references.size());
                destination.getDestinationStatistics().getInflight().subtract(references.size());
                this.dispatched.removeAll(references);
            }
        }
        return rc;
    }
    
    protected void dispatchPending() throws IOException {
        if (!this.isSlave()) {
            synchronized (this.pendingLock) {
                try {
                    int numberToDispatch = this.countBeforeFull();
                    if (numberToDispatch > 0) {
                        this.setSlowConsumer(false);
                        this.setPendingBatchSize(this.pending, numberToDispatch);
                        int count = 0;
                        this.pending.reset();
                        while (this.pending.hasNext() && !this.isFull() && count < numberToDispatch) {
                            final MessageReference node = this.pending.next();
                            if (node == null) {
                                break;
                            }
                            synchronized (this.dispatchLock) {
                                this.pending.remove();
                                node.decrementReferenceCount();
                                if (this.isDropped(node) || !this.canDispatch(node)) {
                                    continue;
                                }
                                if (node != QueueMessageReference.NULL_MESSAGE && node.isExpired()) {
                                    ++numberToDispatch;
                                    if (!this.broker.isExpired(node)) {
                                        continue;
                                    }
                                    node.getRegionDestination().messageExpired(this.context, this, node);
                                }
                                else {
                                    this.dispatch(node);
                                    ++count;
                                }
                            }
                        }
                    }
                    else if (!this.isSlowConsumer()) {
                        this.setSlowConsumer(true);
                        for (final Destination dest : this.destinations) {
                            dest.slowConsumer(this.context, this);
                        }
                    }
                }
                finally {
                    this.pending.release();
                }
            }
        }
    }
    
    protected void setPendingBatchSize(final PendingMessageCursor pending, final int numberToDispatch) {
        pending.setMaxBatchSize(numberToDispatch);
    }
    
    protected boolean dispatch(final MessageReference node) throws IOException {
        final Message message = node.getMessage();
        if (message == null) {
            return false;
        }
        this.okForAckAsDispatchDone.countDown();
        if (!this.isSlave()) {
            final MessageDispatch md = this.createMessageDispatch(node, message);
            if (node != QueueMessageReference.NULL_MESSAGE) {
                ++this.dispatchCounter;
                this.dispatched.add(node);
            }
            else {
                this.prefetchExtension = Math.max(0, this.prefetchExtension - 1);
            }
            if (this.info.isDispatchAsync()) {
                md.setTransmitCallback(new Runnable() {
                    @Override
                    public void run() {
                        PrefetchSubscription.this.onDispatch(node, message);
                    }
                });
                this.context.getConnection().dispatchAsync(md);
            }
            else {
                this.context.getConnection().dispatchSync(md);
                this.onDispatch(node, message);
            }
            return true;
        }
        return false;
    }
    
    protected void onDispatch(final MessageReference node, final Message message) {
        if (node.getRegionDestination() != null && node != QueueMessageReference.NULL_MESSAGE) {
            node.getRegionDestination().getDestinationStatistics().getDispatched().increment();
            node.getRegionDestination().getDestinationStatistics().getInflight().increment();
            if (PrefetchSubscription.LOG.isTraceEnabled()) {
                PrefetchSubscription.LOG.trace(this.info.getConsumerId() + " dispatched: " + message.getMessageId() + " - " + message.getDestination() + ", dispatched: " + this.dispatchCounter + ", inflight: " + this.dispatched.size());
            }
        }
        if (this.info.isDispatchAsync()) {
            try {
                this.dispatchPending();
            }
            catch (IOException e) {
                this.context.getConnection().serviceExceptionAsync(e);
            }
        }
    }
    
    @Override
    public void updateConsumerPrefetch(final int newPrefetch) {
        if (this.context != null && this.context.getConnection() != null && this.context.getConnection().isManageable()) {
            final ConsumerControl cc = new ConsumerControl();
            cc.setConsumerId(this.info.getConsumerId());
            cc.setPrefetch(newPrefetch);
            this.context.getConnection().dispatchAsync(cc);
        }
    }
    
    protected MessageDispatch createMessageDispatch(final MessageReference node, final Message message) {
        if (node == QueueMessageReference.NULL_MESSAGE) {
            final MessageDispatch md = new MessageDispatch();
            md.setMessage(null);
            md.setConsumerId(this.info.getConsumerId());
            md.setDestination(null);
            return md;
        }
        final MessageDispatch md = new MessageDispatch();
        md.setConsumerId(this.info.getConsumerId());
        md.setDestination(node.getRegionDestination().getActiveMQDestination());
        md.setMessage(message);
        md.setRedeliveryCounter(node.getRedeliveryCounter());
        return md;
    }
    
    protected abstract boolean canDispatch(final MessageReference p0) throws IOException;
    
    protected abstract boolean isDropped(final MessageReference p0);
    
    protected abstract void acknowledge(final ConnectionContext p0, final MessageAck p1, final MessageReference p2) throws IOException;
    
    public int getMaxProducersToAudit() {
        return this.maxProducersToAudit;
    }
    
    public void setMaxProducersToAudit(final int maxProducersToAudit) {
        this.maxProducersToAudit = maxProducersToAudit;
    }
    
    public int getMaxAuditDepth() {
        return this.maxAuditDepth;
    }
    
    public void setMaxAuditDepth(final int maxAuditDepth) {
        this.maxAuditDepth = maxAuditDepth;
    }
    
    public boolean isUsePrefetchExtension() {
        return this.usePrefetchExtension;
    }
    
    public void setUsePrefetchExtension(final boolean usePrefetchExtension) {
        this.usePrefetchExtension = usePrefetchExtension;
    }
    
    static {
        LOG = LoggerFactory.getLogger(PrefetchSubscription.class);
    }
}
