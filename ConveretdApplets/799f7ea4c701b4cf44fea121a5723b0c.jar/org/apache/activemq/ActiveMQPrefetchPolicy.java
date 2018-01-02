// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.io.Serializable;

public class ActiveMQPrefetchPolicy implements Serializable
{
    public static final int MAX_PREFETCH_SIZE = 32766;
    public static final int DEFAULT_QUEUE_PREFETCH = 1000;
    public static final int DEFAULT_QUEUE_BROWSER_PREFETCH = 500;
    public static final int DEFAULT_DURABLE_TOPIC_PREFETCH = 100;
    public static final int DEFAULT_OPTIMIZE_DURABLE_TOPIC_PREFETCH = 1000;
    public static final int DEFAULT_INPUT_STREAM_PREFETCH = 100;
    public static final int DEFAULT_TOPIC_PREFETCH = 32766;
    private static final Logger LOG;
    private int queuePrefetch;
    private int queueBrowserPrefetch;
    private int topicPrefetch;
    private int durableTopicPrefetch;
    private int optimizeDurableTopicPrefetch;
    private int inputStreamPrefetch;
    private int maximumPendingMessageLimit;
    
    public ActiveMQPrefetchPolicy() {
        this.queuePrefetch = 1000;
        this.queueBrowserPrefetch = 500;
        this.topicPrefetch = 32766;
        this.durableTopicPrefetch = 100;
        this.optimizeDurableTopicPrefetch = 1000;
        this.inputStreamPrefetch = 100;
    }
    
    public int getDurableTopicPrefetch() {
        return this.durableTopicPrefetch;
    }
    
    public void setDurableTopicPrefetch(final int durableTopicPrefetch) {
        this.durableTopicPrefetch = this.getMaxPrefetchLimit(durableTopicPrefetch);
    }
    
    public int getQueuePrefetch() {
        return this.queuePrefetch;
    }
    
    public void setQueuePrefetch(final int queuePrefetch) {
        this.queuePrefetch = this.getMaxPrefetchLimit(queuePrefetch);
    }
    
    public int getQueueBrowserPrefetch() {
        return this.queueBrowserPrefetch;
    }
    
    public void setQueueBrowserPrefetch(final int queueBrowserPrefetch) {
        this.queueBrowserPrefetch = this.getMaxPrefetchLimit(queueBrowserPrefetch);
    }
    
    public int getTopicPrefetch() {
        return this.topicPrefetch;
    }
    
    public void setTopicPrefetch(final int topicPrefetch) {
        this.topicPrefetch = this.getMaxPrefetchLimit(topicPrefetch);
    }
    
    public int getOptimizeDurableTopicPrefetch() {
        return this.optimizeDurableTopicPrefetch;
    }
    
    public void setOptimizeDurableTopicPrefetch(final int optimizeAcknowledgePrefetch) {
        this.optimizeDurableTopicPrefetch = optimizeAcknowledgePrefetch;
    }
    
    public int getMaximumPendingMessageLimit() {
        return this.maximumPendingMessageLimit;
    }
    
    public void setMaximumPendingMessageLimit(final int maximumPendingMessageLimit) {
        this.maximumPendingMessageLimit = maximumPendingMessageLimit;
    }
    
    private int getMaxPrefetchLimit(final int value) {
        final int result = Math.min(value, 32766);
        if (result < value) {
            ActiveMQPrefetchPolicy.LOG.warn("maximum prefetch limit has been reset from " + value + " to " + 32766);
        }
        return result;
    }
    
    public void setAll(final int i) {
        this.durableTopicPrefetch = i;
        this.queueBrowserPrefetch = i;
        this.queuePrefetch = i;
        this.topicPrefetch = i;
        this.inputStreamPrefetch = 1;
        this.optimizeDurableTopicPrefetch = i;
    }
    
    public int getInputStreamPrefetch() {
        return this.inputStreamPrefetch;
    }
    
    public void setInputStreamPrefetch(final int inputStreamPrefetch) {
        this.inputStreamPrefetch = this.getMaxPrefetchLimit(inputStreamPrefetch);
    }
    
    @Override
    public boolean equals(final Object object) {
        if (object instanceof ActiveMQPrefetchPolicy) {
            final ActiveMQPrefetchPolicy other = (ActiveMQPrefetchPolicy)object;
            return this.queuePrefetch == other.queuePrefetch && this.queueBrowserPrefetch == other.queueBrowserPrefetch && this.topicPrefetch == other.topicPrefetch && this.durableTopicPrefetch == other.durableTopicPrefetch && this.optimizeDurableTopicPrefetch == other.optimizeDurableTopicPrefetch && this.inputStreamPrefetch == other.inputStreamPrefetch;
        }
        return false;
    }
    
    static {
        LOG = LoggerFactory.getLogger(ActiveMQPrefetchPolicy.class);
    }
}
