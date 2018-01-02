// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region.policy;

import org.slf4j.LoggerFactory;
import org.apache.activemq.broker.region.group.MessageGroupHashBucketFactory;
import org.apache.activemq.broker.region.QueueSubscription;
import org.apache.activemq.broker.region.QueueBrowserSubscription;
import org.apache.activemq.broker.region.DurableTopicSubscription;
import org.apache.activemq.broker.region.Subscription;
import org.apache.activemq.broker.region.TopicSubscription;
import org.apache.activemq.usage.SystemUsage;
import org.apache.activemq.broker.region.Topic;
import org.apache.activemq.broker.region.cursors.PendingMessageCursor;
import org.apache.activemq.broker.region.BaseDestination;
import org.apache.activemq.broker.region.Queue;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.region.Destination;
import org.apache.activemq.broker.region.group.MessageGroupMapFactory;
import org.slf4j.Logger;
import org.apache.activemq.filter.DestinationMapEntry;

public class PolicyEntry extends DestinationMapEntry
{
    private static final Logger LOG;
    private DispatchPolicy dispatchPolicy;
    private SubscriptionRecoveryPolicy subscriptionRecoveryPolicy;
    private boolean sendAdvisoryIfNoConsumers;
    private DeadLetterStrategy deadLetterStrategy;
    private PendingMessageLimitStrategy pendingMessageLimitStrategy;
    private MessageEvictionStrategy messageEvictionStrategy;
    private long memoryLimit;
    private MessageGroupMapFactory messageGroupMapFactory;
    private PendingQueueMessageStoragePolicy pendingQueuePolicy;
    private PendingDurableSubscriberMessageStoragePolicy pendingDurableSubscriberPolicy;
    private PendingSubscriberMessageStoragePolicy pendingSubscriberPolicy;
    private int maxProducersToAudit;
    private int maxAuditDepth;
    private int maxQueueAuditDepth;
    private boolean enableAudit;
    private boolean producerFlowControl;
    private long blockedProducerWarningInterval;
    private boolean optimizedDispatch;
    private int maxPageSize;
    private int maxBrowsePageSize;
    private boolean useCache;
    private long minimumMessageSize;
    private boolean useConsumerPriority;
    private boolean strictOrderDispatch;
    private boolean lazyDispatch;
    private int timeBeforeDispatchStarts;
    private int consumersBeforeDispatchStarts;
    private boolean advisoryForSlowConsumers;
    private boolean advisdoryForFastProducers;
    private boolean advisoryForDiscardingMessages;
    private boolean advisoryWhenFull;
    private boolean advisoryForDelivery;
    private boolean advisoryForConsumed;
    private long expireMessagesPeriod;
    private int maxExpirePageSize;
    private int queuePrefetch;
    private int queueBrowserPrefetch;
    private int topicPrefetch;
    private int durableTopicPrefetch;
    private boolean usePrefetchExtension;
    private int cursorMemoryHighWaterMark;
    private int storeUsageHighWaterMark;
    private SlowConsumerStrategy slowConsumerStrategy;
    private boolean prioritizedMessages;
    private boolean allConsumersExclusiveByDefault;
    private boolean gcInactiveDestinations;
    private long inactiveTimoutBeforeGC;
    private boolean reduceMemoryFootprint;
    
    public PolicyEntry() {
        this.deadLetterStrategy = Destination.DEFAULT_DEAD_LETTER_STRATEGY;
        this.maxProducersToAudit = 64;
        this.maxAuditDepth = 2048;
        this.maxQueueAuditDepth = 2048;
        this.enableAudit = true;
        this.producerFlowControl = true;
        this.blockedProducerWarningInterval = 30000L;
        this.optimizedDispatch = false;
        this.maxPageSize = 200;
        this.maxBrowsePageSize = 400;
        this.useCache = true;
        this.minimumMessageSize = 1024L;
        this.useConsumerPriority = true;
        this.strictOrderDispatch = false;
        this.lazyDispatch = false;
        this.timeBeforeDispatchStarts = 0;
        this.consumersBeforeDispatchStarts = 0;
        this.expireMessagesPeriod = 30000L;
        this.maxExpirePageSize = 400;
        this.queuePrefetch = 1000;
        this.queueBrowserPrefetch = 500;
        this.topicPrefetch = 32766;
        this.durableTopicPrefetch = 100;
        this.usePrefetchExtension = true;
        this.cursorMemoryHighWaterMark = 70;
        this.storeUsageHighWaterMark = 100;
        this.inactiveTimoutBeforeGC = 60000L;
    }
    
    public void configure(final Broker broker, final Queue queue) {
        this.baseConfiguration(broker, queue);
        if (this.dispatchPolicy != null) {
            queue.setDispatchPolicy(this.dispatchPolicy);
        }
        queue.setDeadLetterStrategy(this.getDeadLetterStrategy());
        queue.setMessageGroupMapFactory(this.getMessageGroupMapFactory());
        if (this.memoryLimit > 0L) {
            queue.getMemoryUsage().setLimit(this.memoryLimit);
        }
        if (this.pendingQueuePolicy != null) {
            final PendingMessageCursor messages = this.pendingQueuePolicy.getQueuePendingMessageCursor(broker, queue);
            queue.setMessages(messages);
        }
        queue.setUseConsumerPriority(this.isUseConsumerPriority());
        queue.setStrictOrderDispatch(this.isStrictOrderDispatch());
        queue.setOptimizedDispatch(this.isOptimizedDispatch());
        queue.setLazyDispatch(this.isLazyDispatch());
        queue.setTimeBeforeDispatchStarts(this.getTimeBeforeDispatchStarts());
        queue.setConsumersBeforeDispatchStarts(this.getConsumersBeforeDispatchStarts());
        queue.setAllConsumersExclusiveByDefault(this.isAllConsumersExclusiveByDefault());
    }
    
    public void configure(final Broker broker, final Topic topic) {
        this.baseConfiguration(broker, topic);
        if (this.dispatchPolicy != null) {
            topic.setDispatchPolicy(this.dispatchPolicy);
        }
        topic.setDeadLetterStrategy(this.getDeadLetterStrategy());
        if (this.subscriptionRecoveryPolicy != null) {
            final SubscriptionRecoveryPolicy srp = this.subscriptionRecoveryPolicy.copy();
            srp.setBroker(broker);
            topic.setSubscriptionRecoveryPolicy(srp);
        }
        if (this.memoryLimit > 0L) {
            topic.getMemoryUsage().setLimit(this.memoryLimit);
        }
        topic.setLazyDispatch(this.isLazyDispatch());
    }
    
    public void baseConfiguration(final Broker broker, final BaseDestination destination) {
        destination.setProducerFlowControl(this.isProducerFlowControl());
        destination.setBlockedProducerWarningInterval(this.getBlockedProducerWarningInterval());
        destination.setEnableAudit(this.isEnableAudit());
        destination.setMaxAuditDepth(this.getMaxQueueAuditDepth());
        destination.setMaxProducersToAudit(this.getMaxProducersToAudit());
        destination.setMaxPageSize(this.getMaxPageSize());
        destination.setMaxBrowsePageSize(this.getMaxBrowsePageSize());
        destination.setUseCache(this.isUseCache());
        destination.setMinimumMessageSize((int)this.getMinimumMessageSize());
        destination.setAdvisoryForConsumed(this.isAdvisoryForConsumed());
        destination.setAdvisoryForDelivery(this.isAdvisoryForDelivery());
        destination.setAdvisoryForDiscardingMessages(this.isAdvisoryForDiscardingMessages());
        destination.setAdvisoryForSlowConsumers(this.isAdvisoryForSlowConsumers());
        destination.setAdvisdoryForFastProducers(this.isAdvisdoryForFastProducers());
        destination.setAdvisoryWhenFull(this.isAdvisoryWhenFull());
        destination.setSendAdvisoryIfNoConsumers(this.sendAdvisoryIfNoConsumers);
        destination.setExpireMessagesPeriod(this.getExpireMessagesPeriod());
        destination.setMaxExpirePageSize(this.getMaxExpirePageSize());
        destination.setCursorMemoryHighWaterMark(this.getCursorMemoryHighWaterMark());
        destination.setStoreUsageHighWaterMark(this.getStoreUsageHighWaterMark());
        final SlowConsumerStrategy scs = this.getSlowConsumerStrategy();
        if (scs != null) {
            scs.setBrokerService(broker);
        }
        destination.setSlowConsumerStrategy(scs);
        destination.setPrioritizedMessages(this.isPrioritizedMessages());
        destination.setGcIfInactive(this.isGcInactiveDestinations());
        destination.setInactiveTimoutBeforeGC(this.getInactiveTimoutBeforeGC());
        destination.setReduceMemoryFootprint(this.isReduceMemoryFootprint());
    }
    
    public void configure(final Broker broker, final SystemUsage memoryManager, final TopicSubscription subscription) {
        final int prefetch = subscription.getConsumerInfo().getPrefetchSize();
        if (prefetch == 32766) {
            subscription.getConsumerInfo().setPrefetchSize(this.getTopicPrefetch());
        }
        if (this.pendingMessageLimitStrategy != null) {
            int value = this.pendingMessageLimitStrategy.getMaximumPendingMessageLimit(subscription);
            final int consumerLimit = subscription.getInfo().getMaximumPendingMessageLimit();
            if (consumerLimit > 0 && (value < 0 || consumerLimit < value)) {
                value = consumerLimit;
            }
            if (value >= 0) {
                if (PolicyEntry.LOG.isDebugEnabled()) {
                    PolicyEntry.LOG.debug("Setting the maximumPendingMessages size to: " + value + " for consumer: " + subscription.getInfo().getConsumerId());
                }
                subscription.setMaximumPendingMessages(value);
            }
        }
        if (this.messageEvictionStrategy != null) {
            subscription.setMessageEvictionStrategy(this.messageEvictionStrategy);
        }
        if (this.pendingSubscriberPolicy != null) {
            final String name = subscription.getContext().getClientId() + "_" + subscription.getConsumerInfo().getConsumerId();
            final int maxBatchSize = subscription.getConsumerInfo().getPrefetchSize();
            subscription.setMatched(this.pendingSubscriberPolicy.getSubscriberPendingMessageCursor(broker, name, maxBatchSize, subscription));
        }
        if (this.enableAudit) {
            subscription.setEnableAudit(this.enableAudit);
            subscription.setMaxProducersToAudit(this.maxProducersToAudit);
            subscription.setMaxAuditDepth(this.maxAuditDepth);
        }
    }
    
    public void configure(final Broker broker, final SystemUsage memoryManager, final DurableTopicSubscription sub) {
        final String clientId = sub.getSubscriptionKey().getClientId();
        final String subName = sub.getSubscriptionKey().getSubscriptionName();
        final int prefetch = sub.getPrefetchSize();
        sub.setCursorMemoryHighWaterMark(this.getCursorMemoryHighWaterMark());
        if (prefetch == 100 || prefetch == 1000) {
            sub.setPrefetchSize(this.getDurableTopicPrefetch());
        }
        if (this.pendingDurableSubscriberPolicy != null) {
            final PendingMessageCursor cursor = this.pendingDurableSubscriberPolicy.getSubscriberPendingMessageCursor(broker, clientId, subName, sub.getPrefetchSize(), sub);
            cursor.setSystemUsage(memoryManager);
            sub.setPending(cursor);
        }
        final int auditDepth = this.getMaxAuditDepth();
        if (auditDepth == 2048 && this.isPrioritizedMessages()) {
            sub.setMaxAuditDepth(auditDepth * 10);
        }
        else {
            sub.setMaxAuditDepth(auditDepth);
        }
        sub.setMaxProducersToAudit(this.getMaxProducersToAudit());
        sub.setUsePrefetchExtension(this.isUsePrefetchExtension());
    }
    
    public void configure(final Broker broker, final SystemUsage memoryManager, final QueueBrowserSubscription sub) {
        final int prefetch = sub.getPrefetchSize();
        if (prefetch == 500) {
            sub.setPrefetchSize(this.getQueueBrowserPrefetch());
        }
        sub.setCursorMemoryHighWaterMark(this.getCursorMemoryHighWaterMark());
        sub.setUsePrefetchExtension(this.isUsePrefetchExtension());
    }
    
    public void configure(final Broker broker, final SystemUsage memoryManager, final QueueSubscription sub) {
        final int prefetch = sub.getPrefetchSize();
        if (prefetch == 1000) {
            sub.setPrefetchSize(this.getQueuePrefetch());
        }
        sub.setCursorMemoryHighWaterMark(this.getCursorMemoryHighWaterMark());
        sub.setUsePrefetchExtension(this.isUsePrefetchExtension());
    }
    
    public DispatchPolicy getDispatchPolicy() {
        return this.dispatchPolicy;
    }
    
    public void setDispatchPolicy(final DispatchPolicy policy) {
        this.dispatchPolicy = policy;
    }
    
    public SubscriptionRecoveryPolicy getSubscriptionRecoveryPolicy() {
        return this.subscriptionRecoveryPolicy;
    }
    
    public void setSubscriptionRecoveryPolicy(final SubscriptionRecoveryPolicy subscriptionRecoveryPolicy) {
        this.subscriptionRecoveryPolicy = subscriptionRecoveryPolicy;
    }
    
    public boolean isSendAdvisoryIfNoConsumers() {
        return this.sendAdvisoryIfNoConsumers;
    }
    
    public void setSendAdvisoryIfNoConsumers(final boolean sendAdvisoryIfNoConsumers) {
        this.sendAdvisoryIfNoConsumers = sendAdvisoryIfNoConsumers;
    }
    
    public DeadLetterStrategy getDeadLetterStrategy() {
        return this.deadLetterStrategy;
    }
    
    public void setDeadLetterStrategy(final DeadLetterStrategy deadLetterStrategy) {
        this.deadLetterStrategy = deadLetterStrategy;
    }
    
    public PendingMessageLimitStrategy getPendingMessageLimitStrategy() {
        return this.pendingMessageLimitStrategy;
    }
    
    public void setPendingMessageLimitStrategy(final PendingMessageLimitStrategy pendingMessageLimitStrategy) {
        this.pendingMessageLimitStrategy = pendingMessageLimitStrategy;
    }
    
    public MessageEvictionStrategy getMessageEvictionStrategy() {
        return this.messageEvictionStrategy;
    }
    
    public void setMessageEvictionStrategy(final MessageEvictionStrategy messageEvictionStrategy) {
        this.messageEvictionStrategy = messageEvictionStrategy;
    }
    
    public long getMemoryLimit() {
        return this.memoryLimit;
    }
    
    public void setMemoryLimit(final long memoryLimit) {
        this.memoryLimit = memoryLimit;
    }
    
    public MessageGroupMapFactory getMessageGroupMapFactory() {
        if (this.messageGroupMapFactory == null) {
            this.messageGroupMapFactory = new MessageGroupHashBucketFactory();
        }
        return this.messageGroupMapFactory;
    }
    
    public void setMessageGroupMapFactory(final MessageGroupMapFactory messageGroupMapFactory) {
        this.messageGroupMapFactory = messageGroupMapFactory;
    }
    
    public PendingDurableSubscriberMessageStoragePolicy getPendingDurableSubscriberPolicy() {
        return this.pendingDurableSubscriberPolicy;
    }
    
    public void setPendingDurableSubscriberPolicy(final PendingDurableSubscriberMessageStoragePolicy pendingDurableSubscriberPolicy) {
        this.pendingDurableSubscriberPolicy = pendingDurableSubscriberPolicy;
    }
    
    public PendingQueueMessageStoragePolicy getPendingQueuePolicy() {
        return this.pendingQueuePolicy;
    }
    
    public void setPendingQueuePolicy(final PendingQueueMessageStoragePolicy pendingQueuePolicy) {
        this.pendingQueuePolicy = pendingQueuePolicy;
    }
    
    public PendingSubscriberMessageStoragePolicy getPendingSubscriberPolicy() {
        return this.pendingSubscriberPolicy;
    }
    
    public void setPendingSubscriberPolicy(final PendingSubscriberMessageStoragePolicy pendingSubscriberPolicy) {
        this.pendingSubscriberPolicy = pendingSubscriberPolicy;
    }
    
    public boolean isProducerFlowControl() {
        return this.producerFlowControl;
    }
    
    public void setProducerFlowControl(final boolean producerFlowControl) {
        this.producerFlowControl = producerFlowControl;
    }
    
    public void setBlockedProducerWarningInterval(final long blockedProducerWarningInterval) {
        this.blockedProducerWarningInterval = blockedProducerWarningInterval;
    }
    
    public long getBlockedProducerWarningInterval() {
        return this.blockedProducerWarningInterval;
    }
    
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
    
    public boolean isEnableAudit() {
        return this.enableAudit;
    }
    
    public void setEnableAudit(final boolean enableAudit) {
        this.enableAudit = enableAudit;
    }
    
    public int getMaxQueueAuditDepth() {
        return this.maxQueueAuditDepth;
    }
    
    public void setMaxQueueAuditDepth(final int maxQueueAuditDepth) {
        this.maxQueueAuditDepth = maxQueueAuditDepth;
    }
    
    public boolean isOptimizedDispatch() {
        return this.optimizedDispatch;
    }
    
    public void setOptimizedDispatch(final boolean optimizedDispatch) {
        this.optimizedDispatch = optimizedDispatch;
    }
    
    public int getMaxPageSize() {
        return this.maxPageSize;
    }
    
    public void setMaxPageSize(final int maxPageSize) {
        this.maxPageSize = maxPageSize;
    }
    
    public int getMaxBrowsePageSize() {
        return this.maxBrowsePageSize;
    }
    
    public void setMaxBrowsePageSize(final int maxPageSize) {
        this.maxBrowsePageSize = maxPageSize;
    }
    
    public boolean isUseCache() {
        return this.useCache;
    }
    
    public void setUseCache(final boolean useCache) {
        this.useCache = useCache;
    }
    
    public long getMinimumMessageSize() {
        return this.minimumMessageSize;
    }
    
    public void setMinimumMessageSize(final long minimumMessageSize) {
        this.minimumMessageSize = minimumMessageSize;
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
    
    public boolean isLazyDispatch() {
        return this.lazyDispatch;
    }
    
    public void setLazyDispatch(final boolean lazyDispatch) {
        this.lazyDispatch = lazyDispatch;
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
    
    public boolean isAdvisoryForSlowConsumers() {
        return this.advisoryForSlowConsumers;
    }
    
    public void setAdvisoryForSlowConsumers(final boolean advisoryForSlowConsumers) {
        this.advisoryForSlowConsumers = advisoryForSlowConsumers;
    }
    
    public boolean isAdvisoryForDiscardingMessages() {
        return this.advisoryForDiscardingMessages;
    }
    
    public void setAdvisoryForDiscardingMessages(final boolean advisoryForDiscardingMessages) {
        this.advisoryForDiscardingMessages = advisoryForDiscardingMessages;
    }
    
    public boolean isAdvisoryWhenFull() {
        return this.advisoryWhenFull;
    }
    
    public void setAdvisoryWhenFull(final boolean advisoryWhenFull) {
        this.advisoryWhenFull = advisoryWhenFull;
    }
    
    public boolean isAdvisoryForDelivery() {
        return this.advisoryForDelivery;
    }
    
    public void setAdvisoryForDelivery(final boolean advisoryForDelivery) {
        this.advisoryForDelivery = advisoryForDelivery;
    }
    
    public boolean isAdvisoryForConsumed() {
        return this.advisoryForConsumed;
    }
    
    public void setAdvisoryForConsumed(final boolean advisoryForConsumed) {
        this.advisoryForConsumed = advisoryForConsumed;
    }
    
    public boolean isAdvisdoryForFastProducers() {
        return this.advisdoryForFastProducers;
    }
    
    public void setAdvisdoryForFastProducers(final boolean advisdoryForFastProducers) {
        this.advisdoryForFastProducers = advisdoryForFastProducers;
    }
    
    public void setMaxExpirePageSize(final int maxExpirePageSize) {
        this.maxExpirePageSize = maxExpirePageSize;
    }
    
    public int getMaxExpirePageSize() {
        return this.maxExpirePageSize;
    }
    
    public void setExpireMessagesPeriod(final long expireMessagesPeriod) {
        this.expireMessagesPeriod = expireMessagesPeriod;
    }
    
    public long getExpireMessagesPeriod() {
        return this.expireMessagesPeriod;
    }
    
    public int getQueuePrefetch() {
        return this.queuePrefetch;
    }
    
    public void setQueuePrefetch(final int queuePrefetch) {
        this.queuePrefetch = queuePrefetch;
    }
    
    public int getQueueBrowserPrefetch() {
        return this.queueBrowserPrefetch;
    }
    
    public void setQueueBrowserPrefetch(final int queueBrowserPrefetch) {
        this.queueBrowserPrefetch = queueBrowserPrefetch;
    }
    
    public int getTopicPrefetch() {
        return this.topicPrefetch;
    }
    
    public void setTopicPrefetch(final int topicPrefetch) {
        this.topicPrefetch = topicPrefetch;
    }
    
    public int getDurableTopicPrefetch() {
        return this.durableTopicPrefetch;
    }
    
    public void setDurableTopicPrefetch(final int durableTopicPrefetch) {
        this.durableTopicPrefetch = durableTopicPrefetch;
    }
    
    public boolean isUsePrefetchExtension() {
        return this.usePrefetchExtension;
    }
    
    public void setUsePrefetchExtension(final boolean usePrefetchExtension) {
        this.usePrefetchExtension = usePrefetchExtension;
    }
    
    public int getCursorMemoryHighWaterMark() {
        return this.cursorMemoryHighWaterMark;
    }
    
    public void setCursorMemoryHighWaterMark(final int cursorMemoryHighWaterMark) {
        this.cursorMemoryHighWaterMark = cursorMemoryHighWaterMark;
    }
    
    public void setStoreUsageHighWaterMark(final int storeUsageHighWaterMark) {
        this.storeUsageHighWaterMark = storeUsageHighWaterMark;
    }
    
    public int getStoreUsageHighWaterMark() {
        return this.storeUsageHighWaterMark;
    }
    
    public void setSlowConsumerStrategy(final SlowConsumerStrategy slowConsumerStrategy) {
        this.slowConsumerStrategy = slowConsumerStrategy;
    }
    
    public SlowConsumerStrategy getSlowConsumerStrategy() {
        return this.slowConsumerStrategy;
    }
    
    public boolean isPrioritizedMessages() {
        return this.prioritizedMessages;
    }
    
    public void setPrioritizedMessages(final boolean prioritizedMessages) {
        this.prioritizedMessages = prioritizedMessages;
    }
    
    public void setAllConsumersExclusiveByDefault(final boolean allConsumersExclusiveByDefault) {
        this.allConsumersExclusiveByDefault = allConsumersExclusiveByDefault;
    }
    
    public boolean isAllConsumersExclusiveByDefault() {
        return this.allConsumersExclusiveByDefault;
    }
    
    public boolean isGcInactiveDestinations() {
        return this.gcInactiveDestinations;
    }
    
    public void setGcInactiveDestinations(final boolean gcInactiveDestinations) {
        this.gcInactiveDestinations = gcInactiveDestinations;
    }
    
    public long getInactiveTimoutBeforeGC() {
        return this.inactiveTimoutBeforeGC;
    }
    
    public void setInactiveTimoutBeforeGC(final long inactiveTimoutBeforeGC) {
        this.inactiveTimoutBeforeGC = inactiveTimoutBeforeGC;
    }
    
    public boolean isReduceMemoryFootprint() {
        return this.reduceMemoryFootprint;
    }
    
    public void setReduceMemoryFootprint(final boolean reduceMemoryFootprint) {
        this.reduceMemoryFootprint = reduceMemoryFootprint;
    }
    
    static {
        LOG = LoggerFactory.getLogger(PolicyEntry.class);
    }
}
