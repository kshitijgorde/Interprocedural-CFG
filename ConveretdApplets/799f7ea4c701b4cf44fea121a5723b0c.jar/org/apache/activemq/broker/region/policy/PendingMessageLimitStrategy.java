// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region.policy;

import org.apache.activemq.broker.region.TopicSubscription;

public interface PendingMessageLimitStrategy
{
    int getMaximumPendingMessageLimit(final TopicSubscription p0);
}
