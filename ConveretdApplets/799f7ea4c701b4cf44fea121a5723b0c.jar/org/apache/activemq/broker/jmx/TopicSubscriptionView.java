// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.jmx;

import org.apache.activemq.broker.region.DurableTopicSubscription;
import org.apache.activemq.broker.region.Subscription;
import org.apache.activemq.broker.region.TopicSubscription;

public class TopicSubscriptionView extends SubscriptionView implements TopicSubscriptionViewMBean
{
    public TopicSubscriptionView(final String clientId, final TopicSubscription subs) {
        super(clientId, subs);
    }
    
    protected TopicSubscription getTopicSubscription() {
        return (TopicSubscription)this.subscription;
    }
    
    @Override
    public int getDiscardedCount() {
        final TopicSubscription topicSubscription = this.getTopicSubscription();
        return (topicSubscription != null) ? topicSubscription.discarded() : 0;
    }
    
    @Override
    public int getMaximumPendingQueueSize() {
        final TopicSubscription topicSubscription = this.getTopicSubscription();
        return (topicSubscription != null) ? topicSubscription.getMaximumPendingMessages() : 0;
    }
    
    @Override
    public void setMaximumPendingQueueSize(final int max) {
        final TopicSubscription topicSubscription = this.getTopicSubscription();
        if (topicSubscription != null) {
            topicSubscription.setMaximumPendingMessages(max);
        }
    }
    
    @Override
    public boolean isActive() {
        if (this.subscription instanceof DurableTopicSubscription) {
            return ((DurableTopicSubscription)this.subscription).isActive();
        }
        return super.isActive();
    }
}
