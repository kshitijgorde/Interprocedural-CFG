// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region;

import org.slf4j.LoggerFactory;
import org.apache.activemq.command.MessageDispatch;
import org.apache.activemq.command.Message;
import org.apache.activemq.command.Command;
import org.apache.activemq.command.ConsumerControl;
import org.apache.activemq.command.Response;
import org.apache.activemq.command.MessagePull;
import javax.jms.JMSException;
import org.apache.activemq.transaction.Synchronization;
import org.apache.activemq.command.MessageAck;
import org.apache.activemq.command.MessageDispatchNotification;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import org.apache.activemq.broker.region.cursors.FilePendingMessageCursor;
import org.apache.activemq.broker.region.cursors.VMPendingMessageCursor;
import org.apache.activemq.broker.region.policy.OldestMessageEvictionStrategy;
import org.apache.activemq.command.ConsumerInfo;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.ActiveMQMessageAudit;
import org.apache.activemq.broker.region.policy.MessageEvictionStrategy;
import org.apache.activemq.usage.SystemUsage;
import org.apache.activemq.broker.region.cursors.PendingMessageCursor;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;

public class TopicSubscription extends AbstractSubscription
{
    private static final Logger LOG;
    private static final AtomicLong CURSOR_NAME_COUNTER;
    protected PendingMessageCursor matched;
    protected final SystemUsage usageManager;
    protected AtomicLong dispatchedCounter;
    boolean singleDestination;
    Destination destination;
    private int maximumPendingMessages;
    private MessageEvictionStrategy messageEvictionStrategy;
    private int discarded;
    private final Object matchedListMutex;
    private final AtomicLong enqueueCounter;
    private final AtomicLong dequeueCounter;
    private int memoryUsageHighWaterMark;
    protected int maxProducersToAudit;
    protected int maxAuditDepth;
    protected boolean enableAudit;
    protected ActiveMQMessageAudit audit;
    protected boolean active;
    
    public TopicSubscription(final Broker broker, final ConnectionContext context, final ConsumerInfo info, final SystemUsage usageManager) throws Exception {
        super(broker, context, info);
        this.dispatchedCounter = new AtomicLong();
        this.singleDestination = true;
        this.maximumPendingMessages = -1;
        this.messageEvictionStrategy = new OldestMessageEvictionStrategy();
        this.matchedListMutex = new Object();
        this.enqueueCounter = new AtomicLong(0L);
        this.dequeueCounter = new AtomicLong(0L);
        this.memoryUsageHighWaterMark = 95;
        this.maxProducersToAudit = 1024;
        this.maxAuditDepth = 1000;
        this.enableAudit = false;
        this.active = false;
        this.usageManager = usageManager;
        final String matchedName = "TopicSubscription:" + TopicSubscription.CURSOR_NAME_COUNTER.getAndIncrement() + "[" + info.getConsumerId().toString() + "]";
        if (info.getDestination().isTemporary() || broker == null || broker.getTempDataStore() == null) {
            this.matched = new VMPendingMessageCursor(false);
        }
        else {
            this.matched = new FilePendingMessageCursor(broker, matchedName, false);
        }
    }
    
    public void init() throws Exception {
        this.matched.setSystemUsage(this.usageManager);
        this.matched.setMemoryUsageHighWaterMark(this.getCursorMemoryHighWaterMark());
        this.matched.start();
        if (this.enableAudit) {
            this.audit = new ActiveMQMessageAudit(this.maxAuditDepth, this.maxProducersToAudit);
        }
        this.active = true;
    }
    
    @Override
    public void add(final MessageReference node) throws Exception {
        if (this.isDuplicate(node)) {
            return;
        }
        this.enqueueCounter.incrementAndGet();
        if (!this.isFull() && this.matched.isEmpty() && !this.isSlave()) {
            this.dispatch(node);
            this.setSlowConsumer(false);
        }
        else {
            if (!this.isSlowConsumer()) {
                this.setSlowConsumer(true);
                for (final Destination dest : this.destinations) {
                    dest.slowConsumer(this.getContext(), this);
                }
            }
            if (this.maximumPendingMessages != 0) {
                boolean warnedAboutWait = false;
                while (this.active) {
                    synchronized (this.matchedListMutex) {
                        while (this.matched.isFull()) {
                            if (this.getContext().getStopping().get()) {
                                TopicSubscription.LOG.warn(this.toString() + ": stopped waiting for space in pendingMessage cursor for: " + node.getMessageId());
                                this.enqueueCounter.decrementAndGet();
                                return;
                            }
                            if (!warnedAboutWait) {
                                TopicSubscription.LOG.info(this.toString() + ": Pending message cursor [" + this.matched + "] is full, temp usage (" + this.matched.getSystemUsage().getTempUsage().getPercentUsage() + "%) or memory usage (" + this.matched.getSystemUsage().getMemoryUsage().getPercentUsage() + "%) limit reached, blocking message add() pending the release of resources.");
                                warnedAboutWait = true;
                            }
                            this.matchedListMutex.wait(20L);
                        }
                        if (this.matched.tryAddMessageLast(node, 10L)) {
                            break;
                        }
                        continue;
                    }
                }
                synchronized (this.matchedListMutex) {
                    if (this.maximumPendingMessages > 0) {
                        int max = this.messageEvictionStrategy.getEvictExpiredMessagesHighWatermark();
                        if (this.maximumPendingMessages > 0 && this.maximumPendingMessages < max) {
                            max = this.maximumPendingMessages;
                        }
                        if (!this.matched.isEmpty() && this.matched.size() > max) {
                            this.removeExpiredMessages();
                        }
                        while (!this.matched.isEmpty() && this.matched.size() > this.maximumPendingMessages) {
                            int pageInSize = this.matched.size() - this.maximumPendingMessages;
                            pageInSize = Math.max(1000, pageInSize);
                            LinkedList<MessageReference> list = null;
                            MessageReference[] oldMessages = null;
                            synchronized (this.matched) {
                                list = this.matched.pageInList(pageInSize);
                                oldMessages = this.messageEvictionStrategy.evictMessages(list);
                                for (final MessageReference ref : list) {
                                    ref.decrementReferenceCount();
                                }
                            }
                            int messagesToEvict = 0;
                            if (oldMessages != null) {
                                messagesToEvict = oldMessages.length;
                                for (final MessageReference oldMessage : oldMessages) {
                                    this.discard(oldMessage);
                                }
                            }
                            if (messagesToEvict == 0) {
                                TopicSubscription.LOG.warn("No messages to evict returned from eviction strategy: " + this.messageEvictionStrategy);
                                break;
                            }
                        }
                    }
                }
                this.dispatchMatched();
            }
        }
    }
    
    private boolean isDuplicate(final MessageReference node) {
        boolean duplicate = false;
        if (this.enableAudit && this.audit != null) {
            duplicate = this.audit.isDuplicate(node);
            if (TopicSubscription.LOG.isDebugEnabled() && duplicate) {
                TopicSubscription.LOG.debug("ignoring duplicate add: " + node.getMessageId());
            }
        }
        return duplicate;
    }
    
    protected void removeExpiredMessages() throws IOException {
        try {
            this.matched.reset();
            while (this.matched.hasNext()) {
                final MessageReference node = this.matched.next();
                node.decrementReferenceCount();
                if (this.broker.isExpired(node)) {
                    this.matched.remove();
                    this.dispatchedCounter.incrementAndGet();
                    node.decrementReferenceCount();
                    node.getRegionDestination().getDestinationStatistics().getExpired().increment();
                    this.broker.messageExpired(this.getContext(), node, this);
                    break;
                }
            }
        }
        finally {
            this.matched.release();
        }
    }
    
    @Override
    public void processMessageDispatchNotification(final MessageDispatchNotification mdn) {
        synchronized (this.matchedListMutex) {
            try {
                this.matched.reset();
                while (this.matched.hasNext()) {
                    final MessageReference node = this.matched.next();
                    node.decrementReferenceCount();
                    if (node.getMessageId().equals(mdn.getMessageId())) {
                        this.matched.remove();
                        this.dispatchedCounter.incrementAndGet();
                        node.decrementReferenceCount();
                        break;
                    }
                }
            }
            finally {
                this.matched.release();
            }
        }
    }
    
    @Override
    public synchronized void acknowledge(final ConnectionContext context, final MessageAck ack) throws Exception {
        if (ack.isStandardAck() || ack.isPoisonAck() || ack.isIndividualAck()) {
            if (context.isInTransaction()) {
                context.getTransaction().addSynchronization(new Synchronization() {
                    @Override
                    public void afterCommit() throws Exception {
                        synchronized (TopicSubscription.this) {
                            if (TopicSubscription.this.singleDestination && TopicSubscription.this.destination != null) {
                                TopicSubscription.this.destination.getDestinationStatistics().getDequeues().add(ack.getMessageCount());
                            }
                        }
                        TopicSubscription.this.dequeueCounter.addAndGet(ack.getMessageCount());
                        TopicSubscription.this.dispatchMatched();
                    }
                });
            }
            else {
                if (this.singleDestination && this.destination != null) {
                    this.destination.getDestinationStatistics().getDequeues().add(ack.getMessageCount());
                    this.destination.getDestinationStatistics().getInflight().subtract(ack.getMessageCount());
                }
                this.dequeueCounter.addAndGet(ack.getMessageCount());
            }
            this.dispatchMatched();
            return;
        }
        if (ack.isDeliveredAck()) {
            if (this.destination != null && !ack.isInTransaction()) {
                this.destination.getDestinationStatistics().getDequeues().add(ack.getMessageCount());
                this.destination.getDestinationStatistics().getInflight().subtract(ack.getMessageCount());
            }
            this.dequeueCounter.addAndGet(ack.getMessageCount());
            this.dispatchMatched();
            return;
        }
        if (ack.isRedeliveredAck()) {
            return;
        }
        throw new JMSException("Invalid acknowledgment: " + ack);
    }
    
    @Override
    public Response pullMessage(final ConnectionContext context, final MessagePull pull) throws Exception {
        return null;
    }
    
    @Override
    public int getPendingQueueSize() {
        return this.matched();
    }
    
    @Override
    public int getDispatchedQueueSize() {
        return (int)(this.dispatchedCounter.get() - this.dequeueCounter.get());
    }
    
    public int getMaximumPendingMessages() {
        return this.maximumPendingMessages;
    }
    
    @Override
    public long getDispatchedCounter() {
        return this.dispatchedCounter.get();
    }
    
    @Override
    public long getEnqueueCounter() {
        return this.enqueueCounter.get();
    }
    
    @Override
    public long getDequeueCounter() {
        return this.dequeueCounter.get();
    }
    
    public int discarded() {
        synchronized (this.matchedListMutex) {
            return this.discarded;
        }
    }
    
    public int matched() {
        synchronized (this.matchedListMutex) {
            return this.matched.size();
        }
    }
    
    public void setMaximumPendingMessages(final int maximumPendingMessages) {
        this.maximumPendingMessages = maximumPendingMessages;
    }
    
    public MessageEvictionStrategy getMessageEvictionStrategy() {
        return this.messageEvictionStrategy;
    }
    
    public void setMessageEvictionStrategy(final MessageEvictionStrategy messageEvictionStrategy) {
        this.messageEvictionStrategy = messageEvictionStrategy;
    }
    
    public int getMaxProducersToAudit() {
        return this.maxProducersToAudit;
    }
    
    public synchronized void setMaxProducersToAudit(final int maxProducersToAudit) {
        this.maxProducersToAudit = maxProducersToAudit;
        if (this.audit != null) {
            this.audit.setMaximumNumberOfProducersToTrack(maxProducersToAudit);
        }
    }
    
    public int getMaxAuditDepth() {
        return this.maxAuditDepth;
    }
    
    public synchronized void setMaxAuditDepth(final int maxAuditDepth) {
        this.maxAuditDepth = maxAuditDepth;
        if (this.audit != null) {
            this.audit.setAuditDepth(maxAuditDepth);
        }
    }
    
    public boolean isEnableAudit() {
        return this.enableAudit;
    }
    
    public synchronized void setEnableAudit(final boolean enableAudit) {
        this.enableAudit = enableAudit;
        if (enableAudit && this.audit == null) {
            this.audit = new ActiveMQMessageAudit(this.maxAuditDepth, this.maxProducersToAudit);
        }
    }
    
    @Override
    public boolean isFull() {
        return this.getDispatchedQueueSize() >= this.info.getPrefetchSize();
    }
    
    @Override
    public int getInFlightSize() {
        return this.getDispatchedQueueSize();
    }
    
    @Override
    public boolean isLowWaterMark() {
        return this.getDispatchedQueueSize() <= this.info.getPrefetchSize() * 0.4;
    }
    
    @Override
    public boolean isHighWaterMark() {
        return this.getDispatchedQueueSize() >= this.info.getPrefetchSize() * 0.9;
    }
    
    public void setMemoryUsageHighWaterMark(final int memoryUsageHighWaterMark) {
        this.memoryUsageHighWaterMark = memoryUsageHighWaterMark;
    }
    
    public int getMemoryUsageHighWaterMark() {
        return this.memoryUsageHighWaterMark;
    }
    
    public SystemUsage getUsageManager() {
        return this.usageManager;
    }
    
    public PendingMessageCursor getMatched() {
        return this.matched;
    }
    
    public void setMatched(final PendingMessageCursor matched) {
        this.matched = matched;
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
    
    private void dispatchMatched() throws IOException {
        synchronized (this.matchedListMutex) {
            if (!this.matched.isEmpty() && !this.isFull()) {
                try {
                    this.matched.reset();
                    while (this.matched.hasNext() && !this.isFull()) {
                        final MessageReference message = this.matched.next();
                        message.decrementReferenceCount();
                        this.matched.remove();
                        if (message.isExpired()) {
                            this.discard(message);
                        }
                        else {
                            this.dispatch(message);
                        }
                    }
                }
                finally {
                    this.matched.release();
                }
            }
        }
    }
    
    private void dispatch(final MessageReference node) throws IOException {
        final Message message = (Message)node;
        node.incrementReferenceCount();
        final MessageDispatch md = new MessageDispatch();
        md.setMessage(message);
        md.setConsumerId(this.info.getConsumerId());
        md.setDestination(node.getRegionDestination().getActiveMQDestination());
        this.dispatchedCounter.incrementAndGet();
        if (this.singleDestination) {
            if (this.destination == null) {
                this.destination = node.getRegionDestination();
            }
            else if (this.destination != node.getRegionDestination()) {
                this.singleDestination = false;
            }
        }
        if (this.info.isDispatchAsync()) {
            md.setTransmitCallback(new Runnable() {
                @Override
                public void run() {
                    node.getRegionDestination().getDestinationStatistics().getDispatched().increment();
                    node.getRegionDestination().getDestinationStatistics().getInflight().increment();
                    node.decrementReferenceCount();
                }
            });
            this.context.getConnection().dispatchAsync(md);
        }
        else {
            this.context.getConnection().dispatchSync(md);
            node.getRegionDestination().getDestinationStatistics().getDispatched().increment();
            node.getRegionDestination().getDestinationStatistics().getInflight().increment();
            node.decrementReferenceCount();
        }
    }
    
    private void discard(final MessageReference message) {
        message.decrementReferenceCount();
        this.matched.remove(message);
        ++this.discarded;
        if (this.destination != null) {
            this.destination.getDestinationStatistics().getDequeues().increment();
        }
        if (TopicSubscription.LOG.isDebugEnabled()) {
            TopicSubscription.LOG.debug("Discarding message " + message);
        }
        final Destination dest = message.getRegionDestination();
        if (dest != null) {
            dest.messageDiscarded(this.getContext(), this, message);
        }
        this.broker.getRoot().sendToDeadLetterQueue(this.getContext(), message, this);
    }
    
    @Override
    public String toString() {
        return "TopicSubscription: consumer=" + this.info.getConsumerId() + ", destinations=" + this.destinations.size() + ", dispatched=" + this.getDispatchedQueueSize() + ", delivered=" + this.getDequeueCounter() + ", matched=" + this.matched() + ", discarded=" + this.discarded();
    }
    
    @Override
    public void destroy() {
        this.active = false;
        synchronized (this.matchedListMutex) {
            try {
                this.matched.destroy();
            }
            catch (Exception e) {
                TopicSubscription.LOG.warn("Failed to destroy cursor", e);
            }
        }
        this.setSlowConsumer(false);
    }
    
    @Override
    public int getPrefetchSize() {
        return this.info.getPrefetchSize();
    }
    
    static {
        LOG = LoggerFactory.getLogger(TopicSubscription.class);
        CURSOR_NAME_COUNTER = new AtomicLong(0L);
    }
}
