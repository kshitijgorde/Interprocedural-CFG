// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region;

import org.slf4j.LoggerFactory;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.apache.activemq.command.MessageAck;
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
import org.apache.activemq.command.MessageId;
import java.io.IOException;
import org.apache.activemq.command.Message;
import org.apache.activemq.filter.MessageEvaluationContext;
import org.apache.activemq.store.MessageRecoveryListener;
import org.apache.activemq.filter.NonCachedMessageEvaluationContext;
import org.apache.activemq.command.SubscriptionInfo;
import org.apache.activemq.broker.ConnectionContext;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import org.apache.activemq.broker.region.policy.NoSubscriptionRecoveryPolicy;
import org.apache.activemq.broker.region.policy.SimpleDispatchPolicy;
import org.apache.activemq.store.MessageStore;
import org.apache.activemq.thread.TaskRunnerFactory;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.broker.BrokerService;
import java.util.LinkedList;
import org.apache.activemq.thread.TaskRunner;
import org.apache.activemq.util.SubscriptionKey;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.activemq.broker.region.policy.SubscriptionRecoveryPolicy;
import org.apache.activemq.broker.region.policy.DispatchPolicy;
import org.apache.activemq.thread.Valve;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.activemq.store.TopicMessageStore;
import org.slf4j.Logger;
import org.apache.activemq.thread.Task;

public class Topic extends BaseDestination implements Task
{
    protected static final Logger LOG;
    private final TopicMessageStore topicStore;
    protected final CopyOnWriteArrayList<Subscription> consumers;
    protected final Valve dispatchValve;
    private DispatchPolicy dispatchPolicy;
    private SubscriptionRecoveryPolicy subscriptionRecoveryPolicy;
    private final ConcurrentHashMap<SubscriptionKey, DurableTopicSubscription> durableSubcribers;
    private final TaskRunner taskRunner;
    private final LinkedList<Runnable> messagesWaitingForSpace;
    private final Runnable sendMessagesWaitingForSpaceTask;
    
    public Topic(final BrokerService brokerService, final ActiveMQDestination destination, final TopicMessageStore store, final DestinationStatistics parentStats, final TaskRunnerFactory taskFactory) throws Exception {
        super(brokerService, store, destination, parentStats);
        this.consumers = new CopyOnWriteArrayList<Subscription>();
        this.dispatchValve = new Valve(true);
        this.dispatchPolicy = new SimpleDispatchPolicy();
        this.durableSubcribers = new ConcurrentHashMap<SubscriptionKey, DurableTopicSubscription>();
        this.messagesWaitingForSpace = new LinkedList<Runnable>();
        this.sendMessagesWaitingForSpaceTask = new Runnable() {
            @Override
            public void run() {
                try {
                    Topic.this.taskRunner.wakeup();
                }
                catch (InterruptedException ex) {}
            }
        };
        this.topicStore = store;
        this.subscriptionRecoveryPolicy = new NoSubscriptionRecoveryPolicy();
        this.taskRunner = taskFactory.createTaskRunner(this, "Topic  " + destination.getPhysicalName());
    }
    
    @Override
    public void initialize() throws Exception {
        super.initialize();
        if (this.store != null) {}
    }
    
    @Override
    public List<Subscription> getConsumers() {
        synchronized (this.consumers) {
            return new ArrayList<Subscription>(this.consumers);
        }
    }
    
    public boolean lock(final MessageReference node, final LockOwner sub) {
        return true;
    }
    
    @Override
    public void addSubscription(final ConnectionContext context, final Subscription sub) throws Exception {
        super.addSubscription(context, sub);
        if (!sub.getConsumerInfo().isDurable()) {
            if (sub.getConsumerInfo().isRetroactive()) {
                this.dispatchValve.turnOff();
                try {
                    synchronized (this.consumers) {
                        sub.add(context, this);
                        this.consumers.add(sub);
                    }
                    this.subscriptionRecoveryPolicy.recover(context, this, sub);
                }
                finally {
                    this.dispatchValve.turnOn();
                }
            }
            else {
                synchronized (this.consumers) {
                    sub.add(context, this);
                    this.consumers.add(sub);
                }
            }
        }
        else {
            sub.add(context, this);
            final DurableTopicSubscription dsub = (DurableTopicSubscription)sub;
            this.durableSubcribers.put(dsub.getSubscriptionKey(), dsub);
        }
    }
    
    @Override
    public void removeSubscription(final ConnectionContext context, final Subscription sub, final long lastDeliveredSequenceId) throws Exception {
        if (!sub.getConsumerInfo().isDurable()) {
            super.removeSubscription(context, sub, lastDeliveredSequenceId);
            synchronized (this.consumers) {
                this.consumers.remove(sub);
            }
        }
        sub.remove(context, this);
    }
    
    public void deleteSubscription(final ConnectionContext context, final SubscriptionKey key) throws Exception {
        if (this.topicStore != null) {
            this.topicStore.deleteSubscription(key.clientId, key.subscriptionName);
            final DurableTopicSubscription removed = this.durableSubcribers.remove(key);
            if (removed != null) {
                this.destinationStatistics.getConsumers().decrement();
                removed.deactivate(false);
                this.consumers.remove(removed);
            }
        }
    }
    
    public void activate(final ConnectionContext context, final DurableTopicSubscription subscription) throws Exception {
        this.dispatchValve.turnOff();
        try {
            if (this.topicStore == null) {
                return;
            }
            final String clientId = subscription.getSubscriptionKey().getClientId();
            final String subscriptionName = subscription.getSubscriptionKey().getSubscriptionName();
            final String selector = subscription.getConsumerInfo().getSelector();
            SubscriptionInfo info = this.topicStore.lookupSubscription(clientId, subscriptionName);
            if (info != null) {
                final String s1 = info.getSelector();
                if ((s1 == null ^ selector == null) || (s1 != null && !s1.equals(selector))) {
                    this.topicStore.deleteSubscription(clientId, subscriptionName);
                    info = null;
                }
                else {
                    synchronized (this.consumers) {
                        this.consumers.add(subscription);
                    }
                }
            }
            if (info == null) {
                info = new SubscriptionInfo();
                info.setClientId(clientId);
                info.setSelector(selector);
                info.setSubscriptionName(subscriptionName);
                info.setDestination(this.getActiveMQDestination());
                info.setSubscribedDestination(subscription.getConsumerInfo().getDestination());
                synchronized (this.consumers) {
                    this.consumers.add(subscription);
                    this.topicStore.addSubsciption(info, subscription.getConsumerInfo().isRetroactive());
                }
            }
            final MessageEvaluationContext msgContext = new NonCachedMessageEvaluationContext();
            msgContext.setDestination(this.destination);
            if (subscription.isRecoveryRequired()) {
                this.topicStore.recoverSubscription(clientId, subscriptionName, new MessageRecoveryListener() {
                    @Override
                    public boolean recoverMessage(final Message message) throws Exception {
                        message.setRegionDestination(Topic.this);
                        try {
                            msgContext.setMessageReference(message);
                            if (subscription.matches(message, msgContext)) {
                                subscription.add(message);
                            }
                        }
                        catch (IOException e) {
                            Topic.LOG.error("Failed to recover this message " + message);
                        }
                        return true;
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
        }
        finally {
            this.dispatchValve.turnOn();
        }
    }
    
    public void deactivate(final ConnectionContext context, final DurableTopicSubscription sub) throws Exception {
        synchronized (this.consumers) {
            this.consumers.remove(sub);
        }
        sub.remove(context, this);
    }
    
    protected void recoverRetroactiveMessages(final ConnectionContext context, final Subscription subscription) throws Exception {
        if (subscription.getConsumerInfo().isRetroactive()) {
            this.subscriptionRecoveryPolicy.recover(context, this, subscription);
        }
    }
    
    @Override
    public void send(final ProducerBrokerExchange producerExchange, final Message message) throws Exception {
        final ConnectionContext context = producerExchange.getConnectionContext();
        final ProducerInfo producerInfo = producerExchange.getProducerState().getInfo();
        final boolean sendProducerAck = !message.isResponseRequired() && producerInfo.getWindowSize() > 0 && !context.isInRecoveryMode();
        if (message.isExpired()) {
            this.broker.messageExpired(context, message, null);
            this.getDestinationStatistics().getExpired().increment();
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
                    Topic.LOG.info("Usage Manager memory limit (" + this.memoryUsage.getLimit() + ") reached for " + this.getActiveMQDestination().getQualifiedName() + ". Producers will be throttled to the rate at which messages are removed from this destination to prevent flooding it." + " See http://activemq.apache.org/producer-flow-control.html for more info");
                }
                if (this.systemUsage.isSendFailIfNoSpace()) {
                    throw new ResourceAllocationException("Usage Manager memory limit (" + this.memoryUsage.getLimit() + ") reached. Stopping producer (" + message.getProducerId() + ") to prevent flooding " + this.getActiveMQDestination().getQualifiedName() + "." + " See http://activemq.apache.org/producer-flow-control.html for more info");
                }
                if (producerInfo.getWindowSize() > 0 || message.isResponseRequired()) {
                    synchronized (this.messagesWaitingForSpace) {
                        this.messagesWaitingForSpace.add(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    if (message.isExpired()) {
                                        Topic.this.broker.messageExpired(context, message, null);
                                        Topic.this.getDestinationStatistics().getExpired().increment();
                                    }
                                    else {
                                        Topic.this.doMessageSend(producerExchange, message);
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
                                }
                            }
                        });
                        this.registerCallbackForNotFullNotification();
                        context.setDontSendReponse(true);
                        return;
                    }
                }
                if (this.memoryUsage.isFull()) {
                    if (context.isInTransaction()) {
                        int count = 0;
                        while (!this.memoryUsage.waitForSpace(1000L)) {
                            if (context.getStopping().get()) {
                                throw new IOException("Connection closed, send aborted.");
                            }
                            if (count <= 2 || !context.isInTransaction()) {
                                continue;
                            }
                            count = 0;
                            final int size = context.getTransaction().size();
                            Topic.LOG.warn("Waiting for space to send  transacted message - transaction elements = " + size + " need more space to commit. Message = " + message);
                        }
                    }
                    else {
                        this.waitForSpace(context, this.memoryUsage, "Usage Manager memory limit reached. Stopping producer (" + message.getProducerId() + ") to prevent flooding " + this.getActiveMQDestination().getQualifiedName() + "." + " See http://activemq.apache.org/producer-flow-control.html for more info");
                    }
                }
                if (message.isExpired()) {
                    this.getDestinationStatistics().getExpired().increment();
                    if (Topic.LOG.isDebugEnabled()) {
                        Topic.LOG.debug("Expired message: " + message);
                    }
                    return;
                }
            }
        }
        this.doMessageSend(producerExchange, message);
        this.messageDelivered(context, message);
        if (sendProducerAck) {
            final ProducerAck ack = new ProducerAck(producerInfo.getProducerId(), message.getSize());
            context.getConnection().dispatchAsync(ack);
        }
    }
    
    synchronized void doMessageSend(final ProducerBrokerExchange producerExchange, final Message message) throws IOException, Exception {
        final ConnectionContext context = producerExchange.getConnectionContext();
        message.setRegionDestination(this);
        message.getMessageId().setBrokerSequenceId(this.getDestinationSequenceId());
        Future<Object> result = null;
        if (this.topicStore != null && message.isPersistent() && !this.canOptimizeOutPersistence()) {
            if (this.systemUsage.getStoreUsage().isFull(this.getStoreUsageHighWaterMark())) {
                final String logMessage = "Usage Manager Store is Full, " + this.getStoreUsageHighWaterMark() + "% of " + this.systemUsage.getStoreUsage().getLimit() + ". Stopping producer (" + message.getProducerId() + ") to prevent flooding " + this.getActiveMQDestination().getQualifiedName() + "." + " See http://activemq.apache.org/producer-flow-control.html for more info";
                if (this.systemUsage.isSendFailIfNoSpace()) {
                    throw new ResourceAllocationException(logMessage);
                }
                this.waitForSpace(context, this.systemUsage.getStoreUsage(), this.getStoreUsageHighWaterMark(), logMessage);
            }
            result = this.topicStore.asyncAddTopicMessage(context, message);
        }
        message.incrementReferenceCount();
        if (context.isInTransaction()) {
            context.getTransaction().addSynchronization(new Synchronization() {
                @Override
                public void afterCommit() throws Exception {
                    if (Topic.this.broker.isExpired(message)) {
                        Topic.this.getDestinationStatistics().getExpired().increment();
                        Topic.this.broker.messageExpired(context, message, null);
                        message.decrementReferenceCount();
                        return;
                    }
                    try {
                        Topic.this.dispatch(context, message);
                    }
                    finally {
                        message.decrementReferenceCount();
                    }
                }
            });
        }
        else {
            try {
                this.dispatch(context, message);
            }
            finally {
                message.decrementReferenceCount();
            }
        }
        if (result != null && !result.isCancelled()) {
            try {
                result.get();
            }
            catch (CancellationException ex) {}
        }
    }
    
    private boolean canOptimizeOutPersistence() {
        return this.durableSubcribers.size() == 0;
    }
    
    @Override
    public String toString() {
        return "Topic: destination=" + this.destination.getPhysicalName() + ", subscriptions=" + this.consumers.size();
    }
    
    @Override
    public void acknowledge(final ConnectionContext context, final Subscription sub, final MessageAck ack, final MessageReference node) throws IOException {
        if (this.topicStore != null && node.isPersistent()) {
            final DurableTopicSubscription dsub = (DurableTopicSubscription)sub;
            final SubscriptionKey key = dsub.getSubscriptionKey();
            this.topicStore.acknowledge(context, key.getClientId(), key.getSubscriptionName(), node.getMessageId(), ack);
        }
        this.messageConsumed(context, node);
    }
    
    @Override
    public void gc() {
    }
    
    public Message loadMessage(final MessageId messageId) throws IOException {
        return (this.topicStore != null) ? this.topicStore.getMessage(messageId) : null;
    }
    
    @Override
    public void start() throws Exception {
        this.subscriptionRecoveryPolicy.start();
        if (this.memoryUsage != null) {
            this.memoryUsage.start();
        }
    }
    
    @Override
    public void stop() throws Exception {
        if (this.taskRunner != null) {
            this.taskRunner.shutdown();
        }
        this.subscriptionRecoveryPolicy.stop();
        if (this.memoryUsage != null) {
            this.memoryUsage.stop();
        }
        if (this.topicStore != null) {
            this.topicStore.stop();
        }
    }
    
    @Override
    public Message[] browse() {
        final Set<Message> result = new CopyOnWriteArraySet<Message>();
        try {
            if (this.topicStore != null) {
                this.topicStore.recover(new MessageRecoveryListener() {
                    @Override
                    public boolean recoverMessage(final Message message) throws Exception {
                        result.add(message);
                        return true;
                    }
                    
                    @Override
                    public boolean recoverMessageReference(final MessageId messageReference) throws Exception {
                        return true;
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
                final Message[] msgs = this.subscriptionRecoveryPolicy.browse(this.getActiveMQDestination());
                if (msgs != null) {
                    for (int i = 0; i < msgs.length; ++i) {
                        result.add(msgs[i]);
                    }
                }
            }
        }
        catch (Throwable e) {
            Topic.LOG.warn("Failed to browse Topic: " + this.getActiveMQDestination().getPhysicalName(), e);
        }
        return result.toArray(new Message[result.size()]);
    }
    
    @Override
    public boolean iterate() {
        synchronized (this.messagesWaitingForSpace) {
            while (!this.memoryUsage.isFull() && !this.messagesWaitingForSpace.isEmpty()) {
                final Runnable op = this.messagesWaitingForSpace.removeFirst();
                op.run();
            }
            if (!this.messagesWaitingForSpace.isEmpty()) {
                this.registerCallbackForNotFullNotification();
            }
        }
        return false;
    }
    
    private void registerCallbackForNotFullNotification() {
        if (!this.memoryUsage.notifyCallbackWhenNotFull(this.sendMessagesWaitingForSpaceTask)) {
            this.sendMessagesWaitingForSpaceTask.run();
        }
    }
    
    public DispatchPolicy getDispatchPolicy() {
        return this.dispatchPolicy;
    }
    
    public void setDispatchPolicy(final DispatchPolicy dispatchPolicy) {
        this.dispatchPolicy = dispatchPolicy;
    }
    
    public SubscriptionRecoveryPolicy getSubscriptionRecoveryPolicy() {
        return this.subscriptionRecoveryPolicy;
    }
    
    public void setSubscriptionRecoveryPolicy(final SubscriptionRecoveryPolicy subscriptionRecoveryPolicy) {
        this.subscriptionRecoveryPolicy = subscriptionRecoveryPolicy;
    }
    
    @Override
    public final void wakeup() {
    }
    
    protected void dispatch(final ConnectionContext context, final Message message) throws Exception {
        this.destinationStatistics.getEnqueues().increment();
        this.dispatchValve.increment();
        MessageEvaluationContext msgContext = null;
        try {
            if (!this.subscriptionRecoveryPolicy.add(context, message)) {
                return;
            }
            synchronized (this.consumers) {
                if (this.consumers.isEmpty()) {
                    this.onMessageWithNoConsumers(context, message);
                    return;
                }
            }
            msgContext = context.getMessageEvaluationContext();
            msgContext.setDestination(this.destination);
            msgContext.setMessageReference(message);
            if (!this.dispatchPolicy.dispatch(message, msgContext, this.consumers)) {
                this.onMessageWithNoConsumers(context, message);
            }
        }
        finally {
            this.dispatchValve.decrement();
            if (msgContext != null) {
                msgContext.clear();
            }
        }
    }
    
    @Override
    public void messageExpired(final ConnectionContext context, final Subscription subs, final MessageReference reference) {
        this.broker.messageExpired(context, reference, subs);
        this.destinationStatistics.getEnqueues().decrement();
        this.destinationStatistics.getExpired().increment();
        final MessageAck ack = new MessageAck();
        ack.setAckType((byte)2);
        ack.setDestination(this.destination);
        ack.setMessageID(reference.getMessageId());
        try {
            this.acknowledge(context, subs, ack, reference);
        }
        catch (IOException e) {
            Topic.LOG.error("Failed to remove expired Message from the store ", e);
        }
    }
    
    @Override
    protected Logger getLog() {
        return Topic.LOG;
    }
    
    static {
        LOG = LoggerFactory.getLogger(Topic.class);
    }
}
