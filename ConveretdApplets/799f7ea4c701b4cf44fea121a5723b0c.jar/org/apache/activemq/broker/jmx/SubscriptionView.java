// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.jmx;

import org.apache.activemq.filter.DestinationFilter;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.command.ActiveMQQueue;
import javax.jms.InvalidSelectorException;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ConsumerInfo;
import org.apache.activemq.broker.region.Subscription;

public class SubscriptionView implements SubscriptionViewMBean
{
    protected final Subscription subscription;
    protected final String clientId;
    
    public SubscriptionView(final String clientId, final Subscription subs) {
        this.clientId = clientId;
        this.subscription = subs;
    }
    
    @Override
    public String getClientId() {
        return this.clientId;
    }
    
    @Override
    public String getConnectionId() {
        final ConsumerInfo info = this.getConsumerInfo();
        if (info != null) {
            return info.getConsumerId().getConnectionId();
        }
        return "NOTSET";
    }
    
    @Override
    public long getSessionId() {
        final ConsumerInfo info = this.getConsumerInfo();
        if (info != null) {
            return info.getConsumerId().getSessionId();
        }
        return 0L;
    }
    
    @Override
    public long getSubcriptionId() {
        final ConsumerInfo info = this.getConsumerInfo();
        if (info != null) {
            return info.getConsumerId().getValue();
        }
        return 0L;
    }
    
    @Override
    public String getDestinationName() {
        final ConsumerInfo info = this.getConsumerInfo();
        if (info != null) {
            final ActiveMQDestination dest = info.getDestination();
            return dest.getPhysicalName();
        }
        return "NOTSET";
    }
    
    @Override
    public String getSelector() {
        if (this.subscription != null) {
            return this.subscription.getSelector();
        }
        return null;
    }
    
    @Override
    public void setSelector(final String selector) throws InvalidSelectorException, UnsupportedOperationException {
        if (this.subscription != null) {
            this.subscription.setSelector(selector);
            return;
        }
        throw new UnsupportedOperationException("No subscription object");
    }
    
    @Override
    public boolean isDestinationQueue() {
        final ConsumerInfo info = this.getConsumerInfo();
        if (info != null) {
            final ActiveMQDestination dest = info.getDestination();
            return dest.isQueue();
        }
        return false;
    }
    
    @Override
    public boolean isDestinationTopic() {
        final ConsumerInfo info = this.getConsumerInfo();
        if (info != null) {
            final ActiveMQDestination dest = info.getDestination();
            return dest.isTopic();
        }
        return false;
    }
    
    @Override
    public boolean isDestinationTemporary() {
        final ConsumerInfo info = this.getConsumerInfo();
        if (info != null) {
            final ActiveMQDestination dest = info.getDestination();
            return dest.isTemporary();
        }
        return false;
    }
    
    @Override
    public boolean isActive() {
        return true;
    }
    
    public void gc() {
        if (this.subscription != null) {
            this.subscription.gc();
        }
    }
    
    @Override
    public boolean isRetroactive() {
        final ConsumerInfo info = this.getConsumerInfo();
        return info != null && info.isRetroactive();
    }
    
    @Override
    public boolean isExclusive() {
        final ConsumerInfo info = this.getConsumerInfo();
        return info != null && info.isExclusive();
    }
    
    @Override
    public boolean isDurable() {
        final ConsumerInfo info = this.getConsumerInfo();
        return info != null && info.isDurable();
    }
    
    @Override
    public boolean isNoLocal() {
        final ConsumerInfo info = this.getConsumerInfo();
        return info != null && info.isNoLocal();
    }
    
    @Override
    public int getMaximumPendingMessageLimit() {
        final ConsumerInfo info = this.getConsumerInfo();
        return (info != null) ? info.getMaximumPendingMessageLimit() : 0;
    }
    
    @Override
    public byte getPriority() {
        final ConsumerInfo info = this.getConsumerInfo();
        return (byte)((info != null) ? info.getPriority() : 0);
    }
    
    @Override
    public String getSubcriptionName() {
        final ConsumerInfo info = this.getConsumerInfo();
        return (info != null) ? info.getSubscriptionName() : null;
    }
    
    @Override
    public int getPendingQueueSize() {
        return (this.subscription != null) ? this.subscription.getPendingQueueSize() : 0;
    }
    
    @Override
    public int getDispatchedQueueSize() {
        return (this.subscription != null) ? this.subscription.getDispatchedQueueSize() : 0;
    }
    
    @Override
    public int getMessageCountAwaitingAcknowledge() {
        return this.getDispatchedQueueSize();
    }
    
    @Override
    public long getDispatchedCounter() {
        return (this.subscription != null) ? this.subscription.getDispatchedCounter() : 0L;
    }
    
    @Override
    public long getEnqueueCounter() {
        return (this.subscription != null) ? this.subscription.getEnqueueCounter() : 0L;
    }
    
    @Override
    public long getDequeueCounter() {
        return (this.subscription != null) ? this.subscription.getDequeueCounter() : 0L;
    }
    
    protected ConsumerInfo getConsumerInfo() {
        return (this.subscription != null) ? this.subscription.getConsumerInfo() : null;
    }
    
    @Override
    public String toString() {
        return "SubscriptionView: " + this.getClientId() + ":" + this.getConnectionId();
    }
    
    @Override
    public int getPrefetchSize() {
        return (this.subscription != null) ? this.subscription.getPrefetchSize() : 0;
    }
    
    @Override
    public boolean isMatchingQueue(final String queueName) {
        return this.isDestinationQueue() && this.matchesDestination(new ActiveMQQueue(queueName));
    }
    
    @Override
    public boolean isMatchingTopic(final String topicName) {
        return this.isDestinationTopic() && this.matchesDestination(new ActiveMQTopic(topicName));
    }
    
    public boolean matchesDestination(final ActiveMQDestination destination) {
        final ActiveMQDestination subscriptionDestination = this.subscription.getActiveMQDestination();
        final DestinationFilter filter = DestinationFilter.parseFilter(subscriptionDestination);
        return filter.matches(destination);
    }
}
