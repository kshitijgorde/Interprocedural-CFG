// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region;

import java.util.concurrent.Delayed;
import org.slf4j.LoggerFactory;
import org.apache.activemq.command.MessageDispatchNotification;
import org.apache.activemq.command.ActiveMQMessage;
import java.util.HashSet;
import org.apache.activemq.security.SecurityContext;
import javax.jms.InvalidSelectorException;
import javax.jms.JMSException;
import org.apache.activemq.filter.BooleanExpression;
import org.apache.activemq.selector.SelectorParser;
import org.apache.activemq.filter.MessageEvaluationContext;
import org.apache.activemq.filter.NonCachedMessageEvaluationContext;
import java.util.concurrent.TimeUnit;
import org.slf4j.MDC;
import org.apache.activemq.util.BrokerSupport;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.apache.activemq.command.MessageAck;
import java.util.AbstractList;
import java.util.concurrent.Future;
import java.util.concurrent.CancellationException;
import org.apache.activemq.transaction.Synchronization;
import org.apache.activemq.command.ExceptionResponse;
import org.apache.activemq.command.Response;
import org.apache.activemq.command.ProducerInfo;
import javax.jms.ResourceAllocationException;
import org.apache.activemq.usage.Usage;
import org.apache.activemq.command.Command;
import org.apache.activemq.command.ProducerAck;
import org.apache.activemq.broker.ProducerBrokerExchange;
import org.apache.activemq.command.ConsumerId;
import java.util.Iterator;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.command.Message;
import java.io.IOException;
import org.apache.activemq.store.MessageRecoveryListener;
import org.apache.activemq.broker.region.cursors.StoreQueueCursor;
import org.apache.activemq.broker.region.cursors.VMPendingMessageCursor;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.activemq.broker.region.group.MessageGroupHashBucketFactory;
import org.apache.activemq.broker.region.policy.RoundRobinDispatchPolicy;
import java.util.ArrayList;
import org.apache.activemq.store.MessageStore;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.broker.BrokerService;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.concurrent.DelayQueue;
import org.apache.activemq.thread.Scheduler;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.CountDownLatch;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import org.apache.activemq.broker.region.group.MessageGroupMapFactory;
import org.apache.activemq.broker.region.policy.DispatchPolicy;
import org.apache.activemq.broker.region.group.MessageGroupMap;
import org.apache.activemq.command.MessageId;
import java.util.LinkedHashMap;
import org.apache.activemq.broker.region.cursors.PendingMessageCursor;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.activemq.thread.TaskRunner;
import org.apache.activemq.thread.TaskRunnerFactory;
import org.slf4j.Logger;
import org.apache.activemq.usage.UsageListener;
import org.apache.activemq.thread.Task;

public class Queue extends BaseDestination implements Task, UsageListener
{
    protected static final Logger LOG;
    protected final TaskRunnerFactory taskFactory;
    protected TaskRunner taskRunner;
    private final ReentrantReadWriteLock consumersLock;
    protected final List<Subscription> consumers;
    private final ReentrantReadWriteLock messagesLock;
    protected PendingMessageCursor messages;
    private final ReentrantReadWriteLock pagedInMessagesLock;
    private final LinkedHashMap<MessageId, QueueMessageReference> pagedInMessages;
    private final ReentrantReadWriteLock pagedInPendingDispatchLock;
    private List<QueueMessageReference> pagedInPendingDispatch;
    private List<QueueMessageReference> redeliveredWaitingDispatch;
    private MessageGroupMap messageGroupOwners;
    private DispatchPolicy dispatchPolicy;
    private MessageGroupMapFactory messageGroupMapFactory;
    final Lock sendLock;
    private ExecutorService executor;
    protected final Map<MessageId, Runnable> messagesWaitingForSpace;
    private boolean useConsumerPriority;
    private boolean strictOrderDispatch;
    private final QueueDispatchSelector dispatchSelector;
    private boolean optimizedDispatch;
    private boolean firstConsumer;
    private int timeBeforeDispatchStarts;
    private int consumersBeforeDispatchStarts;
    private CountDownLatch consumersBeforeStartsLatch;
    private final AtomicLong pendingWakeups;
    private boolean allConsumersExclusiveByDefault;
    private final Runnable sendMessagesWaitingForSpaceTask;
    private final Runnable expireMessagesTask;
    private final Object iteratingMutex;
    private final Scheduler scheduler;
    DelayQueue<TimeoutMessage> flowControlTimeoutMessages;
    private final FlowControlTimeoutTask flowControlTimeoutTask;
    private static final Comparator<Subscription> orderedCompare;
    LinkedList<BrowserDispatch> browserDispatches;
    
    public Queue(final BrokerService brokerService, final ActiveMQDestination destination, final MessageStore store, final DestinationStatistics parentStats, final TaskRunnerFactory taskFactory) throws Exception {
        super(brokerService, store, destination, parentStats);
        this.consumersLock = new ReentrantReadWriteLock();
        this.consumers = new ArrayList<Subscription>(50);
        this.messagesLock = new ReentrantReadWriteLock();
        this.pagedInMessagesLock = new ReentrantReadWriteLock();
        this.pagedInMessages = new LinkedHashMap<MessageId, QueueMessageReference>();
        this.pagedInPendingDispatchLock = new ReentrantReadWriteLock();
        this.pagedInPendingDispatch = new ArrayList<QueueMessageReference>(100);
        this.redeliveredWaitingDispatch = new ArrayList<QueueMessageReference>();
        this.dispatchPolicy = new RoundRobinDispatchPolicy();
        this.messageGroupMapFactory = new MessageGroupHashBucketFactory();
        this.sendLock = new ReentrantLock();
        this.messagesWaitingForSpace = Collections.synchronizedMap(new LinkedHashMap<MessageId, Runnable>());
        this.useConsumerPriority = true;
        this.strictOrderDispatch = false;
        this.optimizedDispatch = false;
        this.firstConsumer = false;
        this.timeBeforeDispatchStarts = 0;
        this.consumersBeforeDispatchStarts = 0;
        this.pendingWakeups = new AtomicLong();
        this.allConsumersExclusiveByDefault = false;
        this.sendMessagesWaitingForSpaceTask = new Runnable() {
            @Override
            public void run() {
                Queue.this.asyncWakeup();
            }
        };
        this.expireMessagesTask = new Runnable() {
            @Override
            public void run() {
                Queue.this.expireMessages();
            }
        };
        this.iteratingMutex = new Object() {};
        this.flowControlTimeoutMessages = new DelayQueue<TimeoutMessage>();
        this.flowControlTimeoutTask = new FlowControlTimeoutTask();
        this.browserDispatches = new LinkedList<BrowserDispatch>();
        this.taskFactory = taskFactory;
        this.dispatchSelector = new QueueDispatchSelector(destination);
        this.scheduler = brokerService.getBroker().getScheduler();
    }
    
    @Override
    public List<Subscription> getConsumers() {
        this.consumersLock.readLock().lock();
        try {
            return new ArrayList<Subscription>(this.consumers);
        }
        finally {
            this.consumersLock.readLock().unlock();
        }
    }
    
    @Override
    public void initialize() throws Exception {
        if (this.messages == null) {
            if (this.destination.isTemporary() || this.broker == null || this.store == null) {
                this.messages = new VMPendingMessageCursor(this.isPrioritizedMessages());
            }
            else {
                this.messages = new StoreQueueCursor(this.broker, this);
            }
        }
        if (this.messages instanceof VMPendingMessageCursor) {
            this.systemUsage = this.brokerService.getSystemUsage();
            this.memoryUsage.setParent(this.systemUsage.getMemoryUsage());
        }
        this.taskRunner = this.taskFactory.createTaskRunner(this, "Queue:" + this.destination.getPhysicalName());
        super.initialize();
        if (this.store != null) {
            this.messages.setSystemUsage(this.systemUsage);
            this.messages.setEnableAudit(this.isEnableAudit());
            this.messages.setMaxAuditDepth(this.getMaxAuditDepth());
            this.messages.setMaxProducersToAudit(this.getMaxProducersToAudit());
            this.messages.setUseCache(this.isUseCache());
            this.messages.setMemoryUsageHighWaterMark(this.getCursorMemoryHighWaterMark());
            if (this.messages.isRecoveryRequired()) {
                this.store.recover(new MessageRecoveryListener() {
                    double totalMessageCount = Queue.this.store.getMessageCount();
                    int recoveredMessageCount = 0;
                    
                    @Override
                    public boolean recoverMessage(final Message message) {
                        if (++this.recoveredMessageCount % 50000 == 0) {
                            Queue.LOG.info("cursor for " + Queue.this.getActiveMQDestination().getQualifiedName() + " has recovered " + this.recoveredMessageCount + " messages. " + (int)(this.recoveredMessageCount * 100 / this.totalMessageCount) + "% complete");
                        }
                        if (message.isExpired()) {
                            if (Queue.this.broker.isExpired(message)) {
                                Queue.this.messageExpired(Queue.this.createConnectionContext(), Queue.this.createMessageReference(message));
                                Queue.this.destinationStatistics.getMessages().increment();
                            }
                            return true;
                        }
                        if (this.hasSpace()) {
                            message.setRegionDestination(Queue.this);
                            Queue.this.messagesLock.writeLock().lock();
                            try {
                                Queue.this.messages.addMessageLast(message);
                            }
                            catch (Exception e) {
                                Queue.LOG.error("Failed to add message to cursor", e);
                            }
                            finally {
                                Queue.this.messagesLock.writeLock().unlock();
                            }
                            Queue.this.destinationStatistics.getMessages().increment();
                            return true;
                        }
                        return false;
                    }
                    
                    @Override
                    public boolean recoverMessageReference(final MessageId messageReference) throws Exception {
                        throw new RuntimeException("Should not be called.");
                    }
                    
                    @Override
                    public boolean hasSpace() {
                        return true;
                    }
                    
                    @Override
                    public boolean isDuplicate(final MessageId id) {
                        return false;
                    }
                });
            }
            else {
                final int messageCount = this.store.getMessageCount();
                this.destinationStatistics.getMessages().setCount(messageCount);
            }
        }
    }
    
    @Override
    public void addSubscription(final ConnectionContext context, final Subscription sub) throws Exception {
        if (Queue.LOG.isDebugEnabled()) {
            Queue.LOG.debug(this.getActiveMQDestination().getQualifiedName() + " add sub: " + sub + ", dequeues: " + this.getDestinationStatistics().getDequeues().getCount() + ", dispatched: " + this.getDestinationStatistics().getDispatched().getCount() + ", inflight: " + this.getDestinationStatistics().getInflight().getCount());
        }
        super.addSubscription(context, sub);
        this.pagedInPendingDispatchLock.writeLock().lock();
        try {
            sub.add(context, this);
            this.consumersLock.writeLock().lock();
            try {
                if (this.consumers.size() == 0) {
                    this.firstConsumer = true;
                    if (this.consumersBeforeDispatchStarts != 0) {
                        this.consumersBeforeStartsLatch = new CountDownLatch(this.consumersBeforeDispatchStarts - 1);
                    }
                }
                else if (this.consumersBeforeStartsLatch != null) {
                    this.consumersBeforeStartsLatch.countDown();
                }
                this.addToConsumerList(sub);
                if (sub.getConsumerInfo().isExclusive() || this.isAllConsumersExclusiveByDefault()) {
                    Subscription exclusiveConsumer = this.dispatchSelector.getExclusiveConsumer();
                    if (exclusiveConsumer == null) {
                        exclusiveConsumer = sub;
                    }
                    else if (sub.getConsumerInfo().getPriority() == 127 || sub.getConsumerInfo().getPriority() > exclusiveConsumer.getConsumerInfo().getPriority()) {
                        exclusiveConsumer = sub;
                    }
                    this.dispatchSelector.setExclusiveConsumer(exclusiveConsumer);
                }
            }
            finally {
                this.consumersLock.writeLock().unlock();
            }
            if (sub instanceof QueueBrowserSubscription) {
                final QueueBrowserSubscription browserSubscription = (QueueBrowserSubscription)sub;
                this.pagedInMessagesLock.readLock().lock();
                try {
                    final BrowserDispatch browserDispatch = new BrowserDispatch(browserSubscription);
                    this.browserDispatches.addLast(browserDispatch);
                }
                finally {
                    this.pagedInMessagesLock.readLock().unlock();
                }
            }
            if (!this.optimizedDispatch && !this.isSlave()) {
                this.wakeup();
            }
        }
        finally {
            this.pagedInPendingDispatchLock.writeLock().unlock();
        }
        if (this.optimizedDispatch || this.isSlave()) {
            this.wakeup();
        }
    }
    
    @Override
    public void removeSubscription(final ConnectionContext context, final Subscription sub, final long lastDeiveredSequenceId) throws Exception {
        super.removeSubscription(context, sub, lastDeiveredSequenceId);
        this.pagedInPendingDispatchLock.writeLock().lock();
        try {
            if (Queue.LOG.isDebugEnabled()) {
                Queue.LOG.debug(this.getActiveMQDestination().getQualifiedName() + " remove sub: " + sub + ", lastDeliveredSeqId: " + lastDeiveredSequenceId + ", dequeues: " + this.getDestinationStatistics().getDequeues().getCount() + ", dispatched: " + this.getDestinationStatistics().getDispatched().getCount() + ", inflight: " + this.getDestinationStatistics().getInflight().getCount());
            }
            this.consumersLock.writeLock().lock();
            try {
                this.removeFromConsumerList(sub);
                if (sub.getConsumerInfo().isExclusive()) {
                    Subscription exclusiveConsumer = this.dispatchSelector.getExclusiveConsumer();
                    if (exclusiveConsumer == sub) {
                        exclusiveConsumer = null;
                        for (final Subscription s : this.consumers) {
                            if (s.getConsumerInfo().isExclusive() && (exclusiveConsumer == null || s.getConsumerInfo().getPriority() > exclusiveConsumer.getConsumerInfo().getPriority())) {
                                exclusiveConsumer = s;
                            }
                        }
                        this.dispatchSelector.setExclusiveConsumer(exclusiveConsumer);
                    }
                }
                else if (this.isAllConsumersExclusiveByDefault()) {
                    Subscription exclusiveConsumer = null;
                    for (final Subscription s : this.consumers) {
                        if (exclusiveConsumer == null || s.getConsumerInfo().getPriority() > exclusiveConsumer.getConsumerInfo().getPriority()) {
                            exclusiveConsumer = s;
                        }
                    }
                    this.dispatchSelector.setExclusiveConsumer(exclusiveConsumer);
                }
                final ConsumerId consumerId = sub.getConsumerInfo().getConsumerId();
                this.getMessageGroupOwners().removeConsumer(consumerId);
                boolean markAsRedelivered = false;
                MessageReference lastDeliveredRef = null;
                final List<MessageReference> unAckedMessages = sub.remove(context, this);
                if (lastDeiveredSequenceId != 0L) {
                    for (final MessageReference ref : unAckedMessages) {
                        if (ref.getMessageId().getBrokerSequenceId() == lastDeiveredSequenceId) {
                            lastDeliveredRef = ref;
                            markAsRedelivered = true;
                            Queue.LOG.debug("found lastDeliveredSeqID: " + lastDeiveredSequenceId + ", message reference: " + ref.getMessageId());
                            break;
                        }
                    }
                }
                for (final MessageReference ref : unAckedMessages) {
                    final QueueMessageReference qmr = (QueueMessageReference)ref;
                    if (qmr.getLockOwner() == sub) {
                        qmr.unlock();
                        if (lastDeiveredSequenceId == 0L) {
                            qmr.incrementRedeliveryCounter();
                        }
                        else {
                            if (markAsRedelivered) {
                                qmr.incrementRedeliveryCounter();
                            }
                            if (ref == lastDeliveredRef) {
                                markAsRedelivered = false;
                            }
                        }
                    }
                    this.redeliveredWaitingDispatch.add(qmr);
                }
                if (!this.redeliveredWaitingDispatch.isEmpty()) {
                    this.doDispatch(new ArrayList<QueueMessageReference>());
                }
            }
            finally {
                this.consumersLock.writeLock().unlock();
            }
            if (!this.optimizedDispatch && !this.isSlave()) {
                this.wakeup();
            }
        }
        finally {
            this.pagedInPendingDispatchLock.writeLock().unlock();
        }
        if (this.optimizedDispatch || this.isSlave()) {
            this.wakeup();
        }
    }
    
    @Override
    public void send(final ProducerBrokerExchange producerExchange, final Message message) throws Exception {
        final ConnectionContext context = producerExchange.getConnectionContext();
        message.setRegionDestination(this);
        final ProducerInfo producerInfo = producerExchange.getProducerState().getInfo();
        final boolean sendProducerAck = !message.isResponseRequired() && producerInfo.getWindowSize() > 0 && !context.isInRecoveryMode();
        if (message.isExpired()) {
            this.broker.getRoot().messageExpired(context, message, null);
            if (sendProducerAck) {
                final ProducerAck ack = new ProducerAck(producerInfo.getProducerId(), message.getSize());
                context.getConnection().dispatchAsync(ack);
            }
            return;
        }
        if (this.memoryUsage.isFull()) {
            this.isFull(context, this.memoryUsage);
            this.fastProducer(context, producerInfo);
            if (this.isProducerFlowControl() && context.isProducerFlowControl()) {
                if (this.warnOnProducerFlowControl) {
                    this.warnOnProducerFlowControl = false;
                    Queue.LOG.info("Usage Manager Memory Limit (" + this.memoryUsage.getLimit() + ") reached on " + this.getActiveMQDestination().getQualifiedName() + ". Producers will be throttled to the rate at which messages are removed from this destination to prevent flooding it." + " See http://activemq.apache.org/producer-flow-control.html for more info");
                }
                if (this.systemUsage.isSendFailIfNoSpace()) {
                    throw new ResourceAllocationException("Usage Manager Memory Limit reached. Stopping producer (" + message.getProducerId() + ") to prevent flooding " + this.getActiveMQDestination().getQualifiedName() + "." + " See http://activemq.apache.org/producer-flow-control.html for more info");
                }
                if (producerInfo.getWindowSize() > 0 || message.isResponseRequired()) {
                    final ProducerBrokerExchange producerExchangeCopy = producerExchange.copy();
                    synchronized (this.messagesWaitingForSpace) {
                        if (!this.flowControlTimeoutTask.isAlive()) {
                            this.flowControlTimeoutTask.setName(this.getName() + " Producer Flow Control Timeout Task");
                            this.flowControlTimeoutTask.start();
                        }
                        this.messagesWaitingForSpace.put(message.getMessageId(), new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    if (message.isExpired()) {
                                        Queue.LOG.error("expired waiting for space..");
                                        Queue.this.broker.messageExpired(context, message, null);
                                        Queue.this.destinationStatistics.getExpired().increment();
                                    }
                                    else {
                                        Queue.this.doMessageSend(producerExchangeCopy, message);
                                    }
                                    if (sendProducerAck) {
                                        final ProducerAck ack = new ProducerAck(producerInfo.getProducerId(), message.getSize());
                                        context.getConnection().dispatchAsync(ack);
                                    }
                                    else {
                                        final Response response = new Response();
                                        response.setCorrelationId(message.getCommandId());
                                        context.getConnection().dispatchAsync(response);
                                    }
                                }
                                catch (Exception e) {
                                    if (!sendProducerAck && !context.isInRecoveryMode()) {
                                        final ExceptionResponse response2 = new ExceptionResponse(e);
                                        response2.setCorrelationId(message.getCommandId());
                                        context.getConnection().dispatchAsync(response2);
                                    }
                                    else {
                                        Queue.LOG.debug("unexpected exception on deferred send of :" + message, e);
                                    }
                                }
                            }
                        });
                        if (this.systemUsage.getSendFailIfNoSpaceAfterTimeout() != 0L) {
                            this.flowControlTimeoutMessages.add(new TimeoutMessage(message, context, this.systemUsage.getSendFailIfNoSpaceAfterTimeout()));
                        }
                        this.registerCallbackForNotFullNotification();
                        context.setDontSendReponse(true);
                        return;
                    }
                }
                if (this.memoryUsage.isFull()) {
                    this.waitForSpace(context, this.memoryUsage, "Usage Manager Memory Limit is full. Producer (" + message.getProducerId() + ") stopped to prevent flooding " + this.getActiveMQDestination().getQualifiedName() + "." + " See http://activemq.apache.org/producer-flow-control.html for more info");
                }
                if (message.isExpired()) {
                    if (Queue.LOG.isDebugEnabled()) {
                        Queue.LOG.debug("Expired message: " + message);
                    }
                    this.broker.getRoot().messageExpired(context, message, null);
                    return;
                }
            }
        }
        this.doMessageSend(producerExchange, message);
        if (sendProducerAck) {
            final ProducerAck ack = new ProducerAck(producerInfo.getProducerId(), message.getSize());
            context.getConnection().dispatchAsync(ack);
        }
    }
    
    private void registerCallbackForNotFullNotification() {
        if (!this.memoryUsage.notifyCallbackWhenNotFull(this.sendMessagesWaitingForSpaceTask)) {
            this.sendMessagesWaitingForSpaceTask.run();
        }
    }
    
    void doMessageSend(final ProducerBrokerExchange producerExchange, final Message message) throws IOException, Exception {
        final ConnectionContext context = producerExchange.getConnectionContext();
        Future<Object> result = null;
        this.checkUsage(context, message);
        this.sendLock.lockInterruptibly();
        try {
            if (this.store != null && message.isPersistent()) {
                message.getMessageId().setBrokerSequenceId(this.getDestinationSequenceId());
                if (this.messages.isCacheEnabled()) {
                    result = this.store.asyncAddQueueMessage(context, message);
                }
                else {
                    this.store.addMessage(context, message);
                }
                if (this.isReduceMemoryFootprint()) {
                    message.clearMarshalledState();
                }
            }
            if (context.isInTransaction()) {
                message.incrementReferenceCount();
                context.getTransaction().addSynchronization(new Synchronization() {
                    @Override
                    public void afterCommit() throws Exception {
                        Queue.this.sendLock.lockInterruptibly();
                        try {
                            if (Queue.this.broker.isExpired(message)) {
                                Queue.this.broker.messageExpired(context, message, null);
                                Queue.this.destinationStatistics.getExpired().increment();
                                return;
                            }
                            Queue.this.sendMessage(message);
                        }
                        finally {
                            Queue.this.sendLock.unlock();
                            message.decrementReferenceCount();
                        }
                        Queue.this.messageSent(context, message);
                    }
                    
                    @Override
                    public void afterRollback() throws Exception {
                        message.decrementReferenceCount();
                    }
                });
            }
            else {
                this.sendMessage(message);
            }
        }
        finally {
            this.sendLock.unlock();
        }
        if (!context.isInTransaction()) {
            this.messageSent(context, message);
        }
        if (result != null && !result.isCancelled()) {
            try {
                result.get();
            }
            catch (CancellationException ex) {}
        }
    }
    
    private void checkUsage(final ConnectionContext context, final Message message) throws ResourceAllocationException, IOException, InterruptedException {
        if (message.isPersistent()) {
            if (this.store != null && this.systemUsage.getStoreUsage().isFull(this.getStoreUsageHighWaterMark())) {
                final String logMessage = "Usage Manager Store is Full, " + this.getStoreUsageHighWaterMark() + "% of " + this.systemUsage.getStoreUsage().getLimit() + ". Stopping producer (" + message.getProducerId() + ") to prevent flooding " + this.getActiveMQDestination().getQualifiedName() + "." + " See http://activemq.apache.org/producer-flow-control.html for more info";
                this.waitForSpace(context, this.systemUsage.getStoreUsage(), this.getStoreUsageHighWaterMark(), logMessage);
            }
        }
        else if (this.messages.getSystemUsage() != null && this.systemUsage.getTempUsage().isFull()) {
            final String logMessage = "Usage Manager Temp Store is Full (" + this.systemUsage.getTempUsage().getPercentUsage() + "% of " + this.systemUsage.getTempUsage().getLimit() + "). Stopping producer (" + message.getProducerId() + ") to prevent flooding " + this.getActiveMQDestination().getQualifiedName() + "." + " See http://activemq.apache.org/producer-flow-control.html for more info";
            this.waitForSpace(context, this.messages.getSystemUsage().getTempUsage(), logMessage);
        }
    }
    
    private void expireMessages() {
        if (Queue.LOG.isDebugEnabled()) {
            Queue.LOG.debug(this.getActiveMQDestination().getQualifiedName() + " expiring messages ..");
        }
        final List<Message> browsedMessages = new AbstractList<Message>() {
            int size = 0;
            
            @Override
            public void add(final int index, final Message element) {
                ++this.size;
            }
            
            @Override
            public int size() {
                return this.size;
            }
            
            @Override
            public Message get(final int index) {
                return null;
            }
        };
        this.doBrowse(browsedMessages, this.getMaxExpirePageSize());
        this.asyncWakeup();
        if (Queue.LOG.isDebugEnabled()) {
            Queue.LOG.debug(this.getActiveMQDestination().getQualifiedName() + " expiring messages done.");
        }
    }
    
    @Override
    public void gc() {
    }
    
    @Override
    public void acknowledge(final ConnectionContext context, final Subscription sub, MessageAck ack, final MessageReference node) throws IOException {
        this.messageConsumed(context, node);
        if (this.store != null && node.isPersistent()) {
            if (ack.getMessageCount() > 0) {
                final MessageAck a = new MessageAck();
                ack.copy(a);
                ack = a;
                ack.setFirstMessageId(node.getMessageId());
                ack.setLastMessageId(node.getMessageId());
                ack.setMessageCount(1);
            }
            this.store.removeAsyncMessage(context, ack);
        }
    }
    
    Message loadMessage(final MessageId messageId) throws IOException {
        Message msg = null;
        if (this.store != null) {
            msg = this.store.getMessage(messageId);
            if (msg != null) {
                msg.setRegionDestination(this);
            }
        }
        return msg;
    }
    
    @Override
    public String toString() {
        int size = 0;
        this.messagesLock.readLock().lock();
        try {
            size = this.messages.size();
        }
        finally {
            this.messagesLock.readLock().unlock();
        }
        return "Queue: destination=" + this.destination.getPhysicalName() + ", subscriptions=" + this.consumers.size() + ", memory=" + this.memoryUsage.getPercentUsage() + "%, size=" + size + ", in flight groups=" + this.messageGroupOwners;
    }
    
    @Override
    public void start() throws Exception {
        if (this.memoryUsage != null) {
            this.memoryUsage.start();
        }
        if (this.systemUsage.getStoreUsage() != null) {
            this.systemUsage.getStoreUsage().start();
        }
        this.systemUsage.getMemoryUsage().addUsageListener(this);
        this.messages.start();
        if (this.getExpireMessagesPeriod() > 0L) {
            this.scheduler.schedualPeriodically(this.expireMessagesTask, this.getExpireMessagesPeriod());
        }
        this.doPageIn(false);
    }
    
    @Override
    public void stop() throws Exception {
        if (this.taskRunner != null) {
            this.taskRunner.shutdown();
        }
        if (this.executor != null) {
            this.executor.shutdownNow();
        }
        this.scheduler.cancel(this.expireMessagesTask);
        if (this.flowControlTimeoutTask.isAlive()) {
            this.flowControlTimeoutTask.interrupt();
        }
        if (this.messages != null) {
            this.messages.stop();
        }
        this.systemUsage.getMemoryUsage().removeUsageListener(this);
        if (this.memoryUsage != null) {
            this.memoryUsage.stop();
        }
        if (this.store != null) {
            this.store.stop();
        }
    }
    
    @Override
    public ActiveMQDestination getActiveMQDestination() {
        return this.destination;
    }
    
    public MessageGroupMap getMessageGroupOwners() {
        if (this.messageGroupOwners == null) {
            this.messageGroupOwners = this.getMessageGroupMapFactory().createMessageGroupMap();
        }
        return this.messageGroupOwners;
    }
    
    public DispatchPolicy getDispatchPolicy() {
        return this.dispatchPolicy;
    }
    
    public void setDispatchPolicy(final DispatchPolicy dispatchPolicy) {
        this.dispatchPolicy = dispatchPolicy;
    }
    
    public MessageGroupMapFactory getMessageGroupMapFactory() {
        return this.messageGroupMapFactory;
    }
    
    public void setMessageGroupMapFactory(final MessageGroupMapFactory messageGroupMapFactory) {
        this.messageGroupMapFactory = messageGroupMapFactory;
    }
    
    public PendingMessageCursor getMessages() {
        return this.messages;
    }
    
    public void setMessages(final PendingMessageCursor messages) {
        this.messages = messages;
    }
    
    public boolean isUseConsumerPriority() {
        return this.useConsumerPriority;
    }
    
    public void setUseConsumerPriority(final boolean useConsumerPriority) {
        this.useConsumerPriority = useConsumerPriority;
    }
    
    public boolean isStrictOrderDispatch() {
        return this.strictOrderDispatch;
    }
    
    public void setStrictOrderDispatch(final boolean strictOrderDispatch) {
        this.strictOrderDispatch = strictOrderDispatch;
    }
    
    public boolean isOptimizedDispatch() {
        return this.optimizedDispatch;
    }
    
    public void setOptimizedDispatch(final boolean optimizedDispatch) {
        this.optimizedDispatch = optimizedDispatch;
    }
    
    public int getTimeBeforeDispatchStarts() {
        return this.timeBeforeDispatchStarts;
    }
    
    public void setTimeBeforeDispatchStarts(final int timeBeforeDispatchStarts) {
        this.timeBeforeDispatchStarts = timeBeforeDispatchStarts;
    }
    
    public int getConsumersBeforeDispatchStarts() {
        return this.consumersBeforeDispatchStarts;
    }
    
    public void setConsumersBeforeDispatchStarts(final int consumersBeforeDispatchStarts) {
        this.consumersBeforeDispatchStarts = consumersBeforeDispatchStarts;
    }
    
    public void setAllConsumersExclusiveByDefault(final boolean allConsumersExclusiveByDefault) {
        this.allConsumersExclusiveByDefault = allConsumersExclusiveByDefault;
    }
    
    public boolean isAllConsumersExclusiveByDefault() {
        return this.allConsumersExclusiveByDefault;
    }
    
    private QueueMessageReference createMessageReference(final Message message) {
        final QueueMessageReference result = new IndirectMessageReference(message);
        return result;
    }
    
    @Override
    public Message[] browse() {
        final List<Message> browseList = new ArrayList<Message>();
        this.doBrowse(browseList, this.getMaxBrowsePageSize());
        return browseList.toArray(new Message[browseList.size()]);
    }
    
    public void doBrowse(final List<Message> browseList, final int max) {
        final ConnectionContext connectionContext = this.createConnectionContext();
        try {
            this.pageInMessages(false);
            final List<MessageReference> toExpire = new ArrayList<MessageReference>();
            this.pagedInPendingDispatchLock.writeLock().lock();
            try {
                this.addAll(this.pagedInPendingDispatch, browseList, max, toExpire);
                for (final MessageReference ref : toExpire) {
                    this.pagedInPendingDispatch.remove(ref);
                    if (this.broker.isExpired(ref)) {
                        Queue.LOG.debug("expiring from pagedInPending: " + ref);
                        this.messageExpired(connectionContext, ref);
                    }
                }
            }
            finally {
                this.pagedInPendingDispatchLock.writeLock().unlock();
            }
            toExpire.clear();
            this.pagedInMessagesLock.readLock().lock();
            try {
                this.addAll(this.pagedInMessages.values(), browseList, max, toExpire);
            }
            finally {
                this.pagedInMessagesLock.readLock().unlock();
            }
            for (final MessageReference ref : toExpire) {
                if (this.broker.isExpired(ref)) {
                    Queue.LOG.debug("expiring from pagedInMessages: " + ref);
                    this.messageExpired(connectionContext, ref);
                }
                else {
                    this.pagedInMessagesLock.writeLock().lock();
                    try {
                        this.pagedInMessages.remove(ref.getMessageId());
                    }
                    finally {
                        this.pagedInMessagesLock.writeLock().unlock();
                    }
                }
            }
            if (browseList.size() < this.getMaxBrowsePageSize()) {
                this.messagesLock.writeLock().lock();
                try {
                    try {
                        this.messages.reset();
                        while (this.messages.hasNext() && browseList.size() < max) {
                            final MessageReference node = this.messages.next();
                            if (node.isExpired()) {
                                if (this.broker.isExpired(node)) {
                                    Queue.LOG.debug("expiring from messages: " + node);
                                    this.messageExpired(connectionContext, this.createMessageReference(node.getMessage()));
                                }
                                this.messages.remove();
                            }
                            else {
                                this.messages.rollback(node.getMessageId());
                                if (!browseList.contains(node.getMessage())) {
                                    browseList.add(node.getMessage());
                                }
                            }
                            node.decrementReferenceCount();
                        }
                    }
                    finally {
                        this.messages.release();
                    }
                }
                finally {
                    this.messagesLock.writeLock().unlock();
                }
            }
        }
        catch (Exception e) {
            Queue.LOG.error("Problem retrieving message for browse", e);
        }
    }
    
    private void addAll(final Collection<QueueMessageReference> refs, final List<Message> l, final int maxBrowsePageSize, final List<MessageReference> toExpire) throws Exception {
        final Iterator<QueueMessageReference> i = refs.iterator();
        while (i.hasNext() && l.size() < this.getMaxBrowsePageSize()) {
            final QueueMessageReference ref = i.next();
            if (ref.isExpired()) {
                toExpire.add(ref);
            }
            else {
                if (l.contains(ref.getMessage())) {
                    continue;
                }
                l.add(ref.getMessage());
            }
        }
    }
    
    public QueueMessageReference getMessage(final String id) {
        final MessageId msgId = new MessageId(id);
        this.pagedInMessagesLock.readLock().lock();
        try {
            final QueueMessageReference ref = this.pagedInMessages.get(msgId);
            if (ref != null) {
                return ref;
            }
        }
        finally {
            this.pagedInMessagesLock.readLock().unlock();
        }
        this.messagesLock.readLock().lock();
        try {
            try {
                this.messages.reset();
                while (this.messages.hasNext()) {
                    final MessageReference mr = this.messages.next();
                    final QueueMessageReference qmr = this.createMessageReference(mr.getMessage());
                    qmr.decrementReferenceCount();
                    this.messages.rollback(qmr.getMessageId());
                    if (msgId.equals(qmr.getMessageId())) {
                        return qmr;
                    }
                }
            }
            finally {
                this.messages.release();
            }
        }
        finally {
            this.messagesLock.readLock().unlock();
        }
        return null;
    }
    
    public void purge() throws Exception {
        final ConnectionContext c = this.createConnectionContext();
        List<MessageReference> list = null;
        do {
            this.doPageIn(true);
            this.pagedInMessagesLock.readLock().lock();
            try {
                list = new ArrayList<MessageReference>(this.pagedInMessages.values());
            }
            finally {
                this.pagedInMessagesLock.readLock().unlock();
            }
            for (final MessageReference ref : list) {
                try {
                    final QueueMessageReference r = (QueueMessageReference)ref;
                    this.removeMessage(c, r);
                }
                catch (IOException ex) {}
            }
        } while (!list.isEmpty() && this.destinationStatistics.getMessages().getCount() > 0L);
        if (this.destinationStatistics.getMessages().getCount() > 0L) {
            Queue.LOG.warn(this.getActiveMQDestination().getQualifiedName() + " after purge complete, message count stats report: " + this.destinationStatistics.getMessages().getCount());
        }
        this.gc();
        this.destinationStatistics.getMessages().setCount(0L);
        this.getMessages().clear();
    }
    
    public boolean removeMessage(final String messageId) throws Exception {
        return this.removeMatchingMessages(this.createMessageIdFilter(messageId), 1) > 0;
    }
    
    public int removeMatchingMessages(final String selector) throws Exception {
        return this.removeMatchingMessages(selector, -1);
    }
    
    public int removeMatchingMessages(final String selector, final int maximumMessages) throws Exception {
        return this.removeMatchingMessages(this.createSelectorFilter(selector), maximumMessages);
    }
    
    public int removeMatchingMessages(final MessageReferenceFilter filter, final int maximumMessages) throws Exception {
        int movedCounter = 0;
        final Set<MessageReference> set = new CopyOnWriteArraySet<MessageReference>();
        final ConnectionContext context = this.createConnectionContext();
        do {
            this.doPageIn(true);
            this.pagedInMessagesLock.readLock().lock();
            try {
                set.addAll(this.pagedInMessages.values());
            }
            finally {
                this.pagedInMessagesLock.readLock().unlock();
            }
            final List<MessageReference> list = new ArrayList<MessageReference>(set);
            for (final MessageReference ref : list) {
                final IndirectMessageReference r = (IndirectMessageReference)ref;
                if (filter.evaluate(context, r)) {
                    this.removeMessage(context, r);
                    set.remove(r);
                    if (++movedCounter >= maximumMessages && maximumMessages > 0) {
                        return movedCounter;
                    }
                    continue;
                }
            }
        } while (set.size() < this.destinationStatistics.getMessages().getCount());
        return movedCounter;
    }
    
    public boolean copyMessageTo(final ConnectionContext context, final String messageId, final ActiveMQDestination dest) throws Exception {
        return this.copyMatchingMessages(context, this.createMessageIdFilter(messageId), dest, 1) > 0;
    }
    
    public int copyMatchingMessagesTo(final ConnectionContext context, final String selector, final ActiveMQDestination dest) throws Exception {
        return this.copyMatchingMessagesTo(context, selector, dest, -1);
    }
    
    public int copyMatchingMessagesTo(final ConnectionContext context, final String selector, final ActiveMQDestination dest, final int maximumMessages) throws Exception {
        return this.copyMatchingMessages(context, this.createSelectorFilter(selector), dest, maximumMessages);
    }
    
    public int copyMatchingMessages(final ConnectionContext context, final MessageReferenceFilter filter, final ActiveMQDestination dest, final int maximumMessages) throws Exception {
        int movedCounter = 0;
        int count = 0;
        final Set<MessageReference> set = new CopyOnWriteArraySet<MessageReference>();
        do {
            final int oldMaxSize = this.getMaxPageSize();
            this.setMaxPageSize((int)this.destinationStatistics.getMessages().getCount());
            this.doPageIn(true);
            this.setMaxPageSize(oldMaxSize);
            this.pagedInMessagesLock.readLock().lock();
            try {
                set.addAll(this.pagedInMessages.values());
            }
            finally {
                this.pagedInMessagesLock.readLock().unlock();
            }
            final List<MessageReference> list = new ArrayList<MessageReference>(set);
            for (final MessageReference ref : list) {
                final IndirectMessageReference r = (IndirectMessageReference)ref;
                if (filter.evaluate(context, r)) {
                    r.incrementReferenceCount();
                    try {
                        final Message m = r.getMessage();
                        BrokerSupport.resend(context, m, dest);
                        if (++movedCounter >= maximumMessages && maximumMessages > 0) {
                            return movedCounter;
                        }
                    }
                    finally {
                        r.decrementReferenceCount();
                    }
                }
                ++count;
            }
        } while (count < this.destinationStatistics.getMessages().getCount());
        return movedCounter;
    }
    
    public boolean moveMessageTo(final ConnectionContext context, final QueueMessageReference m, final ActiveMQDestination dest) throws Exception {
        BrokerSupport.resend(context, m.getMessage(), dest);
        this.removeMessage(context, m);
        this.messagesLock.writeLock().lock();
        try {
            this.messages.rollback(m.getMessageId());
        }
        finally {
            this.messagesLock.writeLock().unlock();
        }
        return true;
    }
    
    public boolean moveMessageTo(final ConnectionContext context, final String messageId, final ActiveMQDestination dest) throws Exception {
        return this.moveMatchingMessagesTo(context, this.createMessageIdFilter(messageId), dest, 1) > 0;
    }
    
    public int moveMatchingMessagesTo(final ConnectionContext context, final String selector, final ActiveMQDestination dest) throws Exception {
        return this.moveMatchingMessagesTo(context, selector, dest, Integer.MAX_VALUE);
    }
    
    public int moveMatchingMessagesTo(final ConnectionContext context, final String selector, final ActiveMQDestination dest, final int maximumMessages) throws Exception {
        return this.moveMatchingMessagesTo(context, this.createSelectorFilter(selector), dest, maximumMessages);
    }
    
    public int moveMatchingMessagesTo(final ConnectionContext context, final MessageReferenceFilter filter, final ActiveMQDestination dest, final int maximumMessages) throws Exception {
        int movedCounter = 0;
        final Set<QueueMessageReference> set = new CopyOnWriteArraySet<QueueMessageReference>();
        do {
            this.doPageIn(true);
            this.pagedInMessagesLock.readLock().lock();
            try {
                set.addAll(this.pagedInMessages.values());
            }
            finally {
                this.pagedInMessagesLock.readLock().unlock();
            }
            final List<QueueMessageReference> list = new ArrayList<QueueMessageReference>(set);
            for (final QueueMessageReference ref : list) {
                if (filter.evaluate(context, ref)) {
                    this.moveMessageTo(context, ref, dest);
                    set.remove(ref);
                    if (++movedCounter >= maximumMessages && maximumMessages > 0) {
                        return movedCounter;
                    }
                    continue;
                }
            }
        } while (set.size() < this.destinationStatistics.getMessages().getCount() && set.size() < maximumMessages);
        return movedCounter;
    }
    
    BrowserDispatch getNextBrowserDispatch() {
        this.pagedInMessagesLock.readLock().lock();
        try {
            if (this.browserDispatches.isEmpty()) {
                return null;
            }
            return this.browserDispatches.removeFirst();
        }
        finally {
            this.pagedInMessagesLock.readLock().unlock();
        }
    }
    
    @Override
    public boolean iterate() {
        MDC.put("activemq.destination", this.getName());
        boolean pageInMoreMessages = false;
        synchronized (this.iteratingMutex) {
            synchronized (this.messagesWaitingForSpace) {
                final Iterator<Runnable> it = this.messagesWaitingForSpace.values().iterator();
                while (it.hasNext()) {
                    if (this.memoryUsage.isFull()) {
                        this.registerCallbackForNotFullNotification();
                        break;
                    }
                    final Runnable op = it.next();
                    it.remove();
                    op.run();
                }
            }
            if (this.firstConsumer) {
                this.firstConsumer = false;
                try {
                    if (this.consumersBeforeDispatchStarts > 0) {
                        int timeout = 1000;
                        if (this.timeBeforeDispatchStarts > 0) {
                            timeout = this.timeBeforeDispatchStarts;
                        }
                        if (this.consumersBeforeStartsLatch.await(timeout, TimeUnit.MILLISECONDS)) {
                            if (Queue.LOG.isDebugEnabled()) {
                                Queue.LOG.debug(this.consumers.size() + " consumers subscribed. Starting dispatch.");
                            }
                        }
                        else if (Queue.LOG.isDebugEnabled()) {
                            Queue.LOG.debug(timeout + " ms elapsed and " + this.consumers.size() + " consumers subscribed. Starting dispatch.");
                        }
                    }
                    if (this.timeBeforeDispatchStarts > 0 && this.consumersBeforeDispatchStarts <= 0) {
                        this.iteratingMutex.wait(this.timeBeforeDispatchStarts);
                        if (Queue.LOG.isDebugEnabled()) {
                            Queue.LOG.debug(this.timeBeforeDispatchStarts + " ms elapsed. Starting dispatch.");
                        }
                    }
                }
                catch (Exception e) {
                    Queue.LOG.error(e.toString());
                }
            }
            BrowserDispatch pendingBrowserDispatch = this.getNextBrowserDispatch();
            this.messagesLock.readLock().lock();
            try {
                pageInMoreMessages |= !this.messages.isEmpty();
            }
            finally {
                this.messagesLock.readLock().unlock();
            }
            this.pagedInPendingDispatchLock.readLock().lock();
            try {
                pageInMoreMessages |= !this.pagedInPendingDispatch.isEmpty();
            }
            finally {
                this.pagedInPendingDispatchLock.readLock().unlock();
            }
            Label_0523: {
                if (!pageInMoreMessages && pendingBrowserDispatch == null) {
                    if (this.redeliveredWaitingDispatch.isEmpty()) {
                        break Label_0523;
                    }
                }
                try {
                    this.pageInMessages(pendingBrowserDispatch != null);
                }
                catch (Throwable e2) {
                    Queue.LOG.error("Failed to page in more queue messages ", e2);
                }
            }
            if (pendingBrowserDispatch != null) {
                ArrayList<QueueMessageReference> alreadyDispatchedMessages = null;
                this.pagedInMessagesLock.readLock().lock();
                try {
                    alreadyDispatchedMessages = new ArrayList<QueueMessageReference>(this.pagedInMessages.values());
                }
                finally {
                    this.pagedInMessagesLock.readLock().unlock();
                }
                if (Queue.LOG.isDebugEnabled()) {
                    Queue.LOG.debug("dispatch to browser: " + pendingBrowserDispatch.getBrowser() + ", already dispatched/paged count: " + alreadyDispatchedMessages.size());
                }
                while (true) {
                    try {
                        final MessageEvaluationContext msgContext = new NonCachedMessageEvaluationContext();
                        msgContext.setDestination(this.destination);
                        final QueueBrowserSubscription browser = pendingBrowserDispatch.getBrowser();
                        for (final QueueMessageReference node : alreadyDispatchedMessages) {
                            if (!node.isAcked()) {
                                msgContext.setMessageReference(node);
                                if (!browser.matches(node, msgContext)) {
                                    continue;
                                }
                                browser.add(node);
                            }
                        }
                        pendingBrowserDispatch.done();
                    }
                    catch (Exception e3) {
                        Queue.LOG.warn("exception on dispatch to browser: " + pendingBrowserDispatch.getBrowser(), e3);
                    }
                    if ((pendingBrowserDispatch = this.getNextBrowserDispatch()) == null) {
                        break;
                    }
                }
            }
            if (this.pendingWakeups.get() > 0L) {
                this.pendingWakeups.decrementAndGet();
            }
            MDC.remove("activemq.destination");
            return this.pendingWakeups.get() > 0L;
        }
    }
    
    protected MessageReferenceFilter createMessageIdFilter(final String messageId) {
        return new MessageReferenceFilter() {
            @Override
            public boolean evaluate(final ConnectionContext context, final MessageReference r) {
                return messageId.equals(r.getMessageId().toString());
            }
            
            @Override
            public String toString() {
                return "MessageIdFilter: " + messageId;
            }
        };
    }
    
    protected MessageReferenceFilter createSelectorFilter(final String selector) throws InvalidSelectorException {
        final BooleanExpression selectorExpression = SelectorParser.parse(selector);
        return new MessageReferenceFilter() {
            @Override
            public boolean evaluate(final ConnectionContext context, final MessageReference r) throws JMSException {
                final MessageEvaluationContext messageEvaluationContext = context.getMessageEvaluationContext();
                messageEvaluationContext.setMessageReference(r);
                if (messageEvaluationContext.getDestination() == null) {
                    messageEvaluationContext.setDestination(Queue.this.getActiveMQDestination());
                }
                return selectorExpression.matches(messageEvaluationContext);
            }
        };
    }
    
    protected void removeMessage(final ConnectionContext c, final QueueMessageReference r) throws IOException {
        this.removeMessage(c, null, r);
        this.pagedInPendingDispatchLock.writeLock().lock();
        try {
            this.pagedInPendingDispatch.remove(r);
        }
        finally {
            this.pagedInPendingDispatchLock.writeLock().unlock();
        }
    }
    
    protected void removeMessage(final ConnectionContext c, final Subscription subs, final QueueMessageReference r) throws IOException {
        final MessageAck ack = new MessageAck();
        ack.setAckType((byte)2);
        ack.setDestination(this.destination);
        ack.setMessageID(r.getMessageId());
        this.removeMessage(c, subs, r, ack);
    }
    
    protected void removeMessage(final ConnectionContext context, final Subscription sub, final QueueMessageReference reference, final MessageAck ack) throws IOException {
        reference.setAcked(true);
        if (!ack.isInTransaction()) {
            this.acknowledge(context, sub, ack, reference);
            this.getDestinationStatistics().getDequeues().increment();
            this.dropMessage(reference);
        }
        else {
            try {
                this.acknowledge(context, sub, ack, reference);
            }
            finally {
                context.getTransaction().addSynchronization(new Synchronization() {
                    @Override
                    public void afterCommit() throws Exception {
                        Queue.this.getDestinationStatistics().getDequeues().increment();
                        Queue.this.dropMessage(reference);
                        Queue.this.wakeup();
                    }
                    
                    @Override
                    public void afterRollback() throws Exception {
                        reference.setAcked(false);
                    }
                });
            }
        }
        if (ack.isPoisonAck()) {
            this.messagesLock.writeLock().lock();
            try {
                this.messages.rollback(reference.getMessageId());
            }
            finally {
                this.messagesLock.writeLock().unlock();
            }
        }
    }
    
    private void dropMessage(final QueueMessageReference reference) {
        reference.drop();
        this.destinationStatistics.getMessages().decrement();
        this.pagedInMessagesLock.writeLock().lock();
        try {
            this.pagedInMessages.remove(reference.getMessageId());
        }
        finally {
            this.pagedInMessagesLock.writeLock().unlock();
        }
    }
    
    public void messageExpired(final ConnectionContext context, final MessageReference reference) {
        this.messageExpired(context, null, reference);
    }
    
    @Override
    public void messageExpired(final ConnectionContext context, final Subscription subs, final MessageReference reference) {
        if (Queue.LOG.isDebugEnabled()) {
            Queue.LOG.debug("message expired: " + reference);
        }
        this.broker.messageExpired(context, reference, subs);
        this.destinationStatistics.getExpired().increment();
        try {
            this.removeMessage(context, subs, (QueueMessageReference)reference);
        }
        catch (IOException e) {
            Queue.LOG.error("Failed to remove expired Message from the store ", e);
        }
    }
    
    protected ConnectionContext createConnectionContext() {
        final ConnectionContext answer = new ConnectionContext(new NonCachedMessageEvaluationContext());
        answer.setBroker(this.broker);
        answer.getMessageEvaluationContext().setDestination(this.getActiveMQDestination());
        answer.setSecurityContext(SecurityContext.BROKER_SECURITY_CONTEXT);
        return answer;
    }
    
    final void sendMessage(final Message msg) throws Exception {
        this.messagesLock.writeLock().lock();
        try {
            this.messages.addMessageLast(msg);
        }
        finally {
            this.messagesLock.writeLock().unlock();
        }
    }
    
    final void messageSent(final ConnectionContext context, final Message msg) throws Exception {
        this.destinationStatistics.getEnqueues().increment();
        this.destinationStatistics.getMessages().increment();
        this.messageDelivered(context, msg);
        this.consumersLock.readLock().lock();
        try {
            if (this.consumers.isEmpty()) {
                this.onMessageWithNoConsumers(context, msg);
            }
        }
        finally {
            this.consumersLock.readLock().unlock();
        }
        if (Queue.LOG.isTraceEnabled()) {
            Queue.LOG.trace("Message " + msg.getMessageId() + " sent to " + this.destination);
        }
        this.wakeup();
    }
    
    @Override
    public void wakeup() {
        if (this.optimizedDispatch || this.isSlave()) {
            this.iterate();
            this.pendingWakeups.incrementAndGet();
        }
        else {
            this.asyncWakeup();
        }
    }
    
    private void asyncWakeup() {
        try {
            this.pendingWakeups.incrementAndGet();
            this.taskRunner.wakeup();
        }
        catch (InterruptedException e) {
            Queue.LOG.warn("Async task tunner failed to wakeup ", e);
        }
    }
    
    private boolean isSlave() {
        return this.broker.getBrokerService().isSlave();
    }
    
    private void doPageIn(final boolean force) throws Exception {
        final List<QueueMessageReference> newlyPaged = this.doPageInForDispatch(force);
        this.pagedInPendingDispatchLock.writeLock().lock();
        try {
            if (this.pagedInPendingDispatch.isEmpty()) {
                this.pagedInPendingDispatch.addAll(newlyPaged);
            }
            else {
                for (final QueueMessageReference qmr : newlyPaged) {
                    if (!this.pagedInPendingDispatch.contains(qmr)) {
                        this.pagedInPendingDispatch.add(qmr);
                    }
                }
            }
        }
        finally {
            this.pagedInPendingDispatchLock.writeLock().unlock();
        }
    }
    
    private List<QueueMessageReference> doPageInForDispatch(final boolean force) throws Exception {
        List<QueueMessageReference> result = null;
        List<QueueMessageReference> resultList = null;
        int toPageIn = Math.min(this.getMaxPageSize(), this.messages.size());
        if (Queue.LOG.isDebugEnabled()) {
            Queue.LOG.debug(this.destination.getPhysicalName() + " toPageIn: " + toPageIn + ", Inflight: " + this.destinationStatistics.getInflight().getCount() + ", pagedInMessages.size " + this.pagedInMessages.size() + ", enqueueCount: " + this.destinationStatistics.getEnqueues().getCount() + ", dequeueCount: " + this.destinationStatistics.getDequeues().getCount());
        }
        if (this.isLazyDispatch() && !force) {
            toPageIn = Math.min(this.getConsumerMessageCountBeforeFull(), toPageIn);
        }
        int pagedInPendingSize = 0;
        this.pagedInPendingDispatchLock.readLock().lock();
        try {
            pagedInPendingSize = this.pagedInPendingDispatch.size();
        }
        finally {
            this.pagedInPendingDispatchLock.readLock().unlock();
        }
        if (toPageIn > 0 && (force || (!this.consumers.isEmpty() && pagedInPendingSize < this.getMaxPageSize()))) {
            int count = 0;
            result = new ArrayList<QueueMessageReference>(toPageIn);
            this.messagesLock.writeLock().lock();
            try {
                try {
                    this.messages.setMaxBatchSize(toPageIn);
                    this.messages.reset();
                    while (this.messages.hasNext() && count < toPageIn) {
                        final MessageReference node = this.messages.next();
                        this.messages.remove();
                        final QueueMessageReference ref = this.createMessageReference(node.getMessage());
                        if (ref.isExpired()) {
                            if (this.broker.isExpired(ref)) {
                                this.messageExpired(this.createConnectionContext(), ref);
                            }
                            else {
                                ref.decrementReferenceCount();
                            }
                        }
                        else {
                            result.add(ref);
                            ++count;
                        }
                    }
                }
                finally {
                    this.messages.release();
                }
            }
            finally {
                this.messagesLock.writeLock().unlock();
            }
            this.pagedInMessagesLock.writeLock().lock();
            try {
                resultList = new ArrayList<QueueMessageReference>(result.size());
                for (final QueueMessageReference ref : result) {
                    if (!this.pagedInMessages.containsKey(ref.getMessageId())) {
                        this.pagedInMessages.put(ref.getMessageId(), ref);
                        resultList.add(ref);
                    }
                    else {
                        ref.decrementReferenceCount();
                    }
                }
            }
            finally {
                this.pagedInMessagesLock.writeLock().unlock();
            }
        }
        else {
            resultList = new ArrayList<QueueMessageReference>();
        }
        return resultList;
    }
    
    private void doDispatch(final List<QueueMessageReference> list) throws Exception {
        boolean doWakeUp = false;
        this.pagedInPendingDispatchLock.writeLock().lock();
        try {
            if (!this.redeliveredWaitingDispatch.isEmpty()) {
                this.redeliveredWaitingDispatch = this.doActualDispatch(this.redeliveredWaitingDispatch);
            }
            if (!this.pagedInPendingDispatch.isEmpty()) {
                this.pagedInPendingDispatch = this.doActualDispatch(this.pagedInPendingDispatch);
            }
            if (list != null && !list.isEmpty()) {
                if (this.pagedInPendingDispatch.isEmpty()) {
                    this.pagedInPendingDispatch.addAll(this.doActualDispatch(list));
                }
                else {
                    for (final QueueMessageReference qmr : list) {
                        if (!this.pagedInPendingDispatch.contains(qmr)) {
                            this.pagedInPendingDispatch.add(qmr);
                        }
                    }
                    doWakeUp = true;
                }
            }
        }
        finally {
            this.pagedInPendingDispatchLock.writeLock().unlock();
        }
        if (doWakeUp) {
            this.asyncWakeup();
        }
    }
    
    private List<QueueMessageReference> doActualDispatch(final List<QueueMessageReference> list) throws Exception {
        this.consumersLock.writeLock().lock();
        List<Subscription> consumers;
        try {
            if (this.consumers.isEmpty() || this.isSlave()) {
                return list;
            }
            consumers = new ArrayList<Subscription>(this.consumers);
        }
        finally {
            this.consumersLock.writeLock().unlock();
        }
        final List<QueueMessageReference> rc = new ArrayList<QueueMessageReference>(list.size());
        final Set<Subscription> fullConsumers = new HashSet<Subscription>(this.consumers.size());
        for (final MessageReference node : list) {
            Subscription target = null;
            int interestCount = 0;
            for (final Subscription s : consumers) {
                if (s instanceof QueueBrowserSubscription) {
                    ++interestCount;
                }
                else if (this.dispatchSelector.canSelect(s, node)) {
                    if (!fullConsumers.contains(s)) {
                        if (!s.isFull()) {
                            if (this.assignMessageGroup(s, (QueueMessageReference)node)) {
                                s.add(node);
                                target = s;
                                break;
                            }
                        }
                        else {
                            fullConsumers.add(s);
                        }
                    }
                    ++interestCount;
                }
                else {
                    if (node.isDropped() || ((QueueMessageReference)node).isAcked() || (node.isDropped() && !s.getConsumerInfo().isBrowser())) {
                        continue;
                    }
                    ++interestCount;
                }
            }
            if ((target == null && interestCount > 0) || consumers.size() == 0) {
                rc.add((QueueMessageReference)node);
            }
            if (target != null && !this.strictOrderDispatch && consumers.size() > 1 && !this.dispatchSelector.isExclusiveConsumer(target)) {
                this.consumersLock.writeLock().lock();
                try {
                    if (!this.removeFromConsumerList(target)) {
                        continue;
                    }
                    this.addToConsumerList(target);
                    consumers = new ArrayList<Subscription>(this.consumers);
                }
                finally {
                    this.consumersLock.writeLock().unlock();
                }
            }
        }
        return rc;
    }
    
    protected boolean assignMessageGroup(final Subscription subscription, final QueueMessageReference node) throws Exception {
        boolean result = true;
        final String groupId = node.getGroupID();
        final int sequence = node.getGroupSequence();
        if (groupId != null) {
            final MessageGroupMap messageGroupOwners = this.getMessageGroupOwners();
            if (sequence == 1) {
                this.assignGroup(subscription, messageGroupOwners, node, groupId);
            }
            else {
                final ConsumerId groupOwner = messageGroupOwners.get(groupId);
                if (groupOwner == null) {
                    this.assignGroup(subscription, messageGroupOwners, node, groupId);
                }
                else if (groupOwner.equals(subscription.getConsumerInfo().getConsumerId())) {
                    if (sequence < 0) {
                        messageGroupOwners.removeGroup(groupId);
                    }
                }
                else {
                    result = false;
                }
            }
        }
        return result;
    }
    
    protected void assignGroup(final Subscription subs, final MessageGroupMap messageGroupOwners, final MessageReference n, final String groupId) throws IOException {
        messageGroupOwners.put(groupId, subs.getConsumerInfo().getConsumerId());
        final Message message = n.getMessage();
        if (message instanceof ActiveMQMessage) {
            final ActiveMQMessage activeMessage = (ActiveMQMessage)message;
            try {
                activeMessage.setBooleanProperty("JMSXGroupFirstForConsumer", true, false);
            }
            catch (JMSException e) {
                Queue.LOG.warn("Failed to set boolean header: " + e, e);
            }
        }
    }
    
    protected void pageInMessages(final boolean force) throws Exception {
        this.doDispatch(this.doPageInForDispatch(force));
    }
    
    private void addToConsumerList(final Subscription sub) {
        if (this.useConsumerPriority) {
            this.consumers.add(sub);
            Collections.sort(this.consumers, Queue.orderedCompare);
        }
        else {
            this.consumers.add(sub);
        }
    }
    
    private boolean removeFromConsumerList(final Subscription sub) {
        return this.consumers.remove(sub);
    }
    
    private int getConsumerMessageCountBeforeFull() throws Exception {
        int total = 0;
        boolean zeroPrefetch = false;
        this.consumersLock.readLock().lock();
        try {
            for (final Subscription s : this.consumers) {
                zeroPrefetch |= (s.getPrefetchSize() == 0);
                final int countBeforeFull = s.countBeforeFull();
                total += countBeforeFull;
            }
        }
        finally {
            this.consumersLock.readLock().unlock();
        }
        if (total == 0 && zeroPrefetch) {
            total = 1;
        }
        return total;
    }
    
    @Override
    public void processDispatchNotification(final MessageDispatchNotification messageDispatchNotification) throws Exception {
        final Subscription sub = this.getMatchingSubscription(messageDispatchNotification);
        if (sub != null) {
            final MessageReference message = this.getMatchingMessage(messageDispatchNotification);
            sub.add(message);
            sub.processMessageDispatchNotification(messageDispatchNotification);
        }
    }
    
    private QueueMessageReference getMatchingMessage(final MessageDispatchNotification messageDispatchNotification) throws Exception {
        QueueMessageReference message = null;
        final MessageId messageId = messageDispatchNotification.getMessageId();
        this.pagedInPendingDispatchLock.writeLock().lock();
        try {
            for (final QueueMessageReference ref : this.pagedInPendingDispatch) {
                if (messageId.equals(ref.getMessageId())) {
                    message = ref;
                    this.pagedInPendingDispatch.remove(ref);
                    break;
                }
            }
        }
        finally {
            this.pagedInPendingDispatchLock.writeLock().unlock();
        }
        if (message == null) {
            this.pagedInMessagesLock.readLock().lock();
            try {
                message = this.pagedInMessages.get(messageId);
            }
            finally {
                this.pagedInMessagesLock.readLock().unlock();
            }
        }
        if (message == null) {
            this.messagesLock.writeLock().lock();
            try {
                try {
                    this.messages.setMaxBatchSize(this.getMaxPageSize());
                    this.messages.reset();
                    while (this.messages.hasNext()) {
                        final MessageReference node = this.messages.next();
                        this.messages.remove();
                        if (messageId.equals(node.getMessageId())) {
                            message = this.createMessageReference(node.getMessage());
                            break;
                        }
                    }
                }
                finally {
                    this.messages.release();
                }
            }
            finally {
                this.messagesLock.writeLock().unlock();
            }
        }
        if (message == null) {
            final Message msg = this.loadMessage(messageId);
            if (msg != null) {
                message = this.createMessageReference(msg);
            }
        }
        if (message == null) {
            throw new JMSException("Slave broker out of sync with master - Message: " + messageDispatchNotification.getMessageId() + " on " + messageDispatchNotification.getDestination() + " does not exist among pending(" + this.pagedInPendingDispatch.size() + ") for subscription: " + messageDispatchNotification.getConsumerId());
        }
        return message;
    }
    
    private Subscription getMatchingSubscription(final MessageDispatchNotification messageDispatchNotification) throws JMSException {
        Subscription sub = null;
        this.consumersLock.readLock().lock();
        try {
            for (final Subscription s : this.consumers) {
                if (messageDispatchNotification.getConsumerId().equals(s.getConsumerInfo().getConsumerId())) {
                    sub = s;
                    break;
                }
            }
        }
        finally {
            this.consumersLock.readLock().unlock();
        }
        return sub;
    }
    
    @Override
    public void onUsageChanged(final Usage usage, final int oldPercentUsage, final int newPercentUsage) {
        if (oldPercentUsage > newPercentUsage) {
            this.asyncWakeup();
        }
    }
    
    @Override
    protected Logger getLog() {
        return Queue.LOG;
    }
    
    static {
        LOG = LoggerFactory.getLogger(Queue.class);
        orderedCompare = new Comparator<Subscription>() {
            @Override
            public int compare(final Subscription s1, final Subscription s2) {
                return s2.getConsumerInfo().getPriority() - s1.getConsumerInfo().getPriority();
            }
        };
    }
    
    class TimeoutMessage implements Delayed
    {
        Message message;
        ConnectionContext context;
        long trigger;
        
        public TimeoutMessage(final Message message, final ConnectionContext context, final long delay) {
            this.message = message;
            this.context = context;
            this.trigger = System.currentTimeMillis() + delay;
        }
        
        @Override
        public long getDelay(final TimeUnit unit) {
            final long n = this.trigger - System.currentTimeMillis();
            return unit.convert(n, TimeUnit.MILLISECONDS);
        }
        
        @Override
        public int compareTo(final Delayed delayed) {
            final long other = ((TimeoutMessage)delayed).trigger;
            int returnValue;
            if (this.trigger < other) {
                returnValue = -1;
            }
            else if (this.trigger > other) {
                returnValue = 1;
            }
            else {
                returnValue = 0;
            }
            return returnValue;
        }
    }
    
    class FlowControlTimeoutTask extends Thread
    {
        @Override
        public void run() {
            try {
                while (true) {
                    final TimeoutMessage timeout = Queue.this.flowControlTimeoutMessages.take();
                    if (timeout != null) {
                        synchronized (Queue.this.messagesWaitingForSpace) {
                            if (Queue.this.messagesWaitingForSpace.remove(timeout.message.getMessageId()) == null) {
                                continue;
                            }
                            final ExceptionResponse response = new ExceptionResponse(new ResourceAllocationException("Usage Manager Memory Limit reached. Stopping producer (" + timeout.message.getProducerId() + ") to prevent flooding " + Queue.this.getActiveMQDestination().getQualifiedName() + "." + " See http://activemq.apache.org/producer-flow-control.html for more info"));
                            response.setCorrelationId(timeout.message.getCommandId());
                            timeout.context.getConnection().dispatchAsync(response);
                        }
                    }
                }
            }
            catch (InterruptedException e) {
                if (Queue.LOG.isDebugEnabled()) {
                    Queue.LOG.debug(this.getName() + "Producer Flow Control Timeout Task is stopping");
                }
            }
        }
    }
    
    final class QueueThread extends Thread
    {
        final Queue queue;
        
        public QueueThread(final Runnable runnable, final String name, final Queue queue) {
            super(runnable, name);
            this.queue = queue;
        }
    }
    
    class BrowserDispatch
    {
        QueueBrowserSubscription browser;
        
        public BrowserDispatch(final QueueBrowserSubscription browserSubscription) {
            (this.browser = browserSubscription).incrementQueueRef();
        }
        
        void done() {
            try {
                this.browser.decrementQueueRef();
            }
            catch (Exception e) {
                Queue.LOG.warn("decrement ref on browser: " + this.browser, e);
            }
        }
        
        public QueueBrowserSubscription getBrowser() {
            return this.browser;
        }
    }
}
