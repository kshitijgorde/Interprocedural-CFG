// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region.policy;

import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.broker.region.DurableTopicSubscription;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.broker.region.Subscription;
import org.apache.activemq.command.Message;

public class IndividualDeadLetterStrategy extends AbstractDeadLetterStrategy
{
    private String topicPrefix;
    private String queuePrefix;
    private boolean useQueueForQueueMessages;
    private boolean useQueueForTopicMessages;
    private boolean destinationPerDurableSubscriber;
    
    public IndividualDeadLetterStrategy() {
        this.topicPrefix = "ActiveMQ.DLQ.Topic.";
        this.queuePrefix = "ActiveMQ.DLQ.Queue.";
        this.useQueueForQueueMessages = true;
        this.useQueueForTopicMessages = true;
    }
    
    @Override
    public ActiveMQDestination getDeadLetterQueueFor(final Message message, final Subscription subscription) {
        if (message.getDestination().isQueue()) {
            return this.createDestination(message, this.queuePrefix, this.useQueueForQueueMessages, subscription);
        }
        return this.createDestination(message, this.topicPrefix, this.useQueueForTopicMessages, subscription);
    }
    
    public String getQueuePrefix() {
        return this.queuePrefix;
    }
    
    public void setQueuePrefix(final String queuePrefix) {
        this.queuePrefix = queuePrefix;
    }
    
    public String getTopicPrefix() {
        return this.topicPrefix;
    }
    
    public void setTopicPrefix(final String topicPrefix) {
        this.topicPrefix = topicPrefix;
    }
    
    public boolean isUseQueueForQueueMessages() {
        return this.useQueueForQueueMessages;
    }
    
    public void setUseQueueForQueueMessages(final boolean useQueueForQueueMessages) {
        this.useQueueForQueueMessages = useQueueForQueueMessages;
    }
    
    public boolean isUseQueueForTopicMessages() {
        return this.useQueueForTopicMessages;
    }
    
    public void setUseQueueForTopicMessages(final boolean useQueueForTopicMessages) {
        this.useQueueForTopicMessages = useQueueForTopicMessages;
    }
    
    public boolean isDestinationPerDurableSubscriber() {
        return this.destinationPerDurableSubscriber;
    }
    
    public void setDestinationPerDurableSubscriber(final boolean destinationPerDurableSubscriber) {
        this.destinationPerDurableSubscriber = destinationPerDurableSubscriber;
    }
    
    protected ActiveMQDestination createDestination(final Message message, final String prefix, final boolean useQueue, final Subscription subscription) {
        String name = prefix + message.getDestination().getPhysicalName();
        if (this.destinationPerDurableSubscriber && subscription instanceof DurableTopicSubscription) {
            name = name + "." + ((DurableTopicSubscription)subscription).getSubscriptionKey();
        }
        if (useQueue) {
            return new ActiveMQQueue(name);
        }
        return new ActiveMQTopic(name);
    }
}
