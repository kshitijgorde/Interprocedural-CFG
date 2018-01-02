// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region.cursors;

import org.slf4j.LoggerFactory;
import org.apache.activemq.store.MessageRecoveryListener;
import org.apache.activemq.filter.MessageEvaluationContext;
import org.apache.activemq.broker.region.MessageReference;
import org.apache.activemq.filter.NonCachedMessageEvaluationContext;
import org.apache.activemq.command.Message;
import org.apache.activemq.command.MessageId;
import org.apache.activemq.broker.region.Destination;
import org.apache.activemq.broker.region.Topic;
import org.apache.activemq.broker.region.Subscription;
import org.apache.activemq.store.TopicMessageStore;
import org.slf4j.Logger;

class TopicStorePrefetch extends AbstractStoreCursor
{
    private static final Logger LOG;
    private final TopicMessageStore store;
    private final String clientId;
    private final String subscriberName;
    private final Subscription subscription;
    private int currentLowestPriority;
    
    public TopicStorePrefetch(final Subscription subscription, final Topic topic, final String clientId, final String subscriberName) {
        super(topic);
        this.subscription = subscription;
        this.store = (TopicMessageStore)topic.getMessageStore();
        this.clientId = clientId;
        this.subscriberName = subscriberName;
        this.maxProducersToAudit = 32;
        this.maxAuditDepth = 10000;
        this.resetCurrentLowestPriority();
    }
    
    private void resetCurrentLowestPriority() {
        this.currentLowestPriority = 9;
    }
    
    public synchronized int getCurrentLowestPriority() {
        return this.currentLowestPriority;
    }
    
    @Override
    public boolean recoverMessageReference(final MessageId messageReference) throws Exception {
        throw new RuntimeException("Not supported");
    }
    
    @Override
    public synchronized boolean recoverMessage(final Message message, final boolean cached) throws Exception {
        if (TopicStorePrefetch.LOG.isTraceEnabled()) {
            TopicStorePrefetch.LOG.trace("recover: " + message.getMessageId() + ", priority: " + message.getPriority());
        }
        boolean recovered = false;
        final MessageEvaluationContext messageEvaluationContext = new NonCachedMessageEvaluationContext();
        messageEvaluationContext.setMessageReference(message);
        if (this.subscription.matches(message, messageEvaluationContext)) {
            recovered = super.recoverMessage(message, cached);
            if (recovered) {
                this.currentLowestPriority = Math.min(this.currentLowestPriority, message.getPriority());
            }
        }
        return recovered;
    }
    
    @Override
    protected synchronized int getStoreSize() {
        try {
            return this.store.getMessageCount(this.clientId, this.subscriberName);
        }
        catch (Exception e) {
            TopicStorePrefetch.LOG.error(this + " Failed to get the outstanding message count from the store", e);
            throw new RuntimeException(e);
        }
    }
    
    @Override
    protected synchronized boolean isStoreEmpty() {
        try {
            final boolean empty = this.store.isEmpty();
            if (empty) {
                this.resetCurrentLowestPriority();
            }
            return empty;
        }
        catch (Exception e) {
            TopicStorePrefetch.LOG.error("Failed to get message count", e);
            throw new RuntimeException(e);
        }
    }
    
    @Override
    protected void resetBatch() {
        this.store.resetBatching(this.clientId, this.subscriberName);
    }
    
    @Override
    public synchronized void gc() {
        super.gc();
        this.resetCurrentLowestPriority();
    }
    
    @Override
    protected void doFillBatch() throws Exception {
        this.store.recoverNextMessages(this.clientId, this.subscriberName, this.maxBatchSize, this);
    }
    
    @Override
    public String toString() {
        return "TopicStorePrefetch(" + this.clientId + "," + this.subscriberName + ")" + super.toString();
    }
    
    static {
        LOG = LoggerFactory.getLogger(TopicStorePrefetch.class);
    }
}
