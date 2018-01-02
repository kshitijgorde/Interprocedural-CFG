// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.amq;

import org.apache.activemq.command.Message;
import org.apache.activemq.broker.region.MessageReference;
import org.apache.activemq.filter.MessageEvaluationContext;
import org.apache.activemq.selector.SelectorParser;
import org.apache.activemq.filter.BooleanExpression;
import org.slf4j.LoggerFactory;
import org.apache.activemq.util.IOExceptionSupport;
import org.apache.activemq.kaha.impl.async.Location;
import org.apache.activemq.transaction.Synchronization;
import org.apache.activemq.util.SubscriptionKey;
import org.apache.activemq.command.JournalTopicAck;
import org.apache.activemq.command.MessageAck;
import org.apache.activemq.command.MessageId;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.command.DataStructure;
import java.io.IOException;
import org.apache.activemq.command.SubscriptionInfo;
import org.apache.activemq.store.MessageStore;
import org.apache.activemq.store.MessageRecoveryListener;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.store.ReferenceStore;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.store.TopicReferenceStore;
import org.slf4j.Logger;
import org.apache.activemq.store.TopicMessageStore;

public class AMQTopicMessageStore extends AMQMessageStore implements TopicMessageStore
{
    private static final Logger LOG;
    private TopicReferenceStore topicReferenceStore;
    
    public AMQTopicMessageStore(final AMQPersistenceAdapter adapter, final TopicReferenceStore topicReferenceStore, final ActiveMQTopic destinationName) {
        super(adapter, topicReferenceStore, destinationName);
        this.topicReferenceStore = topicReferenceStore;
    }
    
    @Override
    public void recoverSubscription(final String clientId, final String subscriptionName, final MessageRecoveryListener listener) throws Exception {
        this.flush();
        this.topicReferenceStore.recoverSubscription(clientId, subscriptionName, new RecoveryListenerAdapter(this, listener));
    }
    
    @Override
    public void recoverNextMessages(final String clientId, final String subscriptionName, final int maxReturned, final MessageRecoveryListener listener) throws Exception {
        final RecoveryListenerAdapter recoveryListener = new RecoveryListenerAdapter(this, listener);
        this.topicReferenceStore.recoverNextMessages(clientId, subscriptionName, maxReturned, recoveryListener);
        if (recoveryListener.size() == 0) {
            this.flush();
            this.topicReferenceStore.recoverNextMessages(clientId, subscriptionName, maxReturned, recoveryListener);
        }
    }
    
    @Override
    public SubscriptionInfo lookupSubscription(final String clientId, final String subscriptionName) throws IOException {
        return this.topicReferenceStore.lookupSubscription(clientId, subscriptionName);
    }
    
    @Override
    public void addSubsciption(final SubscriptionInfo subscriptionInfo, final boolean retroactive) throws IOException {
        this.peristenceAdapter.writeCommand(subscriptionInfo, false);
        this.topicReferenceStore.addSubsciption(subscriptionInfo, retroactive);
    }
    
    @Override
    public void acknowledge(final ConnectionContext context, final String clientId, final String subscriptionName, final MessageId messageId, final MessageAck originalAck) throws IOException {
        final boolean debug = AMQTopicMessageStore.LOG.isDebugEnabled();
        final JournalTopicAck ack = new JournalTopicAck();
        ack.setDestination(this.destination);
        ack.setMessageId(messageId);
        ack.setMessageSequenceId(messageId.getBrokerSequenceId());
        ack.setSubscritionName(subscriptionName);
        ack.setClientId(clientId);
        ack.setTransactionId((context.getTransaction() != null) ? context.getTransaction().getTransactionId() : null);
        final Location location = this.peristenceAdapter.writeCommand(ack, false);
        final SubscriptionKey key = new SubscriptionKey(clientId, subscriptionName);
        if (!context.isInTransaction()) {
            if (debug) {
                AMQTopicMessageStore.LOG.debug("Journalled acknowledge for: " + messageId + ", at: " + location);
            }
            this.acknowledge(context, messageId, location, clientId, subscriptionName);
        }
        else {
            if (debug) {
                AMQTopicMessageStore.LOG.debug("Journalled transacted acknowledge for: " + messageId + ", at: " + location);
            }
            this.lock.lock();
            try {
                this.inFlightTxLocations.add(location);
            }
            finally {
                this.lock.unlock();
            }
            this.transactionStore.acknowledge(this, ack, location);
            context.getTransaction().addSynchronization(new Synchronization() {
                @Override
                public void afterCommit() throws Exception {
                    if (debug) {
                        AMQTopicMessageStore.LOG.debug("Transacted acknowledge commit for: " + messageId + ", at: " + location);
                    }
                    AMQTopicMessageStore.this.lock.lock();
                    try {
                        AMQTopicMessageStore.this.inFlightTxLocations.remove(location);
                        AMQTopicMessageStore.this.acknowledge(context, messageId, location, clientId, subscriptionName);
                    }
                    finally {
                        AMQTopicMessageStore.this.lock.unlock();
                    }
                }
                
                @Override
                public void afterRollback() throws Exception {
                    if (debug) {
                        AMQTopicMessageStore.LOG.debug("Transacted acknowledge rollback for: " + messageId + ", at: " + location);
                    }
                    AMQTopicMessageStore.this.lock.lock();
                    try {
                        AMQTopicMessageStore.this.inFlightTxLocations.remove(location);
                    }
                    finally {
                        AMQTopicMessageStore.this.lock.unlock();
                    }
                }
            });
        }
    }
    
    public boolean replayAcknowledge(final ConnectionContext context, final String clientId, final String subscritionName, final MessageId messageId) {
        try {
            final SubscriptionInfo sub = this.topicReferenceStore.lookupSubscription(clientId, subscritionName);
            if (sub != null) {
                this.topicReferenceStore.acknowledge(context, clientId, subscritionName, messageId, null);
                return true;
            }
        }
        catch (Throwable e) {
            AMQTopicMessageStore.LOG.debug("Could not replay acknowledge for message '" + messageId + "'.  Message may have already been acknowledged. reason: " + e);
        }
        return false;
    }
    
    protected void acknowledge(final ConnectionContext context, final MessageId messageId, final Location location, final String clientId, final String subscriptionName) throws IOException {
        MessageAck ack = null;
        this.lock.lock();
        try {
            this.lastLocation = location;
        }
        finally {
            this.lock.unlock();
        }
        if (this.topicReferenceStore.acknowledgeReference(context, clientId, subscriptionName, messageId)) {
            ack = new MessageAck();
            ack.setLastMessageId(messageId);
        }
        if (ack != null) {
            this.removeMessage(context, ack);
        }
    }
    
    public TopicReferenceStore getTopicReferenceStore() {
        return this.topicReferenceStore;
    }
    
    @Override
    public void deleteSubscription(final String clientId, final String subscriptionName) throws IOException {
        this.topicReferenceStore.deleteSubscription(clientId, subscriptionName);
    }
    
    @Override
    public SubscriptionInfo[] getAllSubscriptions() throws IOException {
        return this.topicReferenceStore.getAllSubscriptions();
    }
    
    @Override
    public int getMessageCount(final String clientId, final String subscriberName) throws IOException {
        this.flush();
        final SubscriptionInfo info = this.lookupSubscription(clientId, subscriberName);
        try {
            final MessageCounter counter = new MessageCounter(info, this);
            this.topicReferenceStore.recoverSubscription(clientId, subscriberName, counter);
            return counter.count;
        }
        catch (Exception e) {
            throw IOExceptionSupport.create(e);
        }
    }
    
    @Override
    public void resetBatching(final String clientId, final String subscriptionName) {
        this.topicReferenceStore.resetBatching(clientId, subscriptionName);
    }
    
    static {
        LOG = LoggerFactory.getLogger(AMQTopicMessageStore.class);
    }
    
    private class MessageCounter implements MessageRecoveryListener
    {
        int count;
        SubscriptionInfo info;
        BooleanExpression selectorExpression;
        TopicMessageStore store;
        
        public MessageCounter(final SubscriptionInfo info, final TopicMessageStore store) throws Exception {
            this.count = 0;
            this.info = info;
            if (info != null) {
                final String selector = info.getSelector();
                if (selector != null) {
                    this.selectorExpression = SelectorParser.parse(selector);
                }
            }
            this.store = store;
        }
        
        @Override
        public boolean recoverMessageReference(final MessageId ref) throws Exception {
            if (this.selectorExpression != null) {
                final MessageEvaluationContext ctx = new MessageEvaluationContext();
                ctx.setMessageReference(this.store.getMessage(ref));
                if (this.selectorExpression.matches(ctx)) {
                    ++this.count;
                }
            }
            else {
                ++this.count;
            }
            return true;
        }
        
        @Override
        public boolean recoverMessage(final Message message) throws Exception {
            if (this.selectorExpression != null) {
                final MessageEvaluationContext ctx = new MessageEvaluationContext();
                ctx.setMessageReference(this.store.getMessage(message.getMessageId()));
                if (this.selectorExpression.matches(ctx)) {
                    ++this.count;
                }
            }
            else {
                ++this.count;
            }
            return true;
        }
        
        @Override
        public boolean isDuplicate(final MessageId ref) {
            return false;
        }
        
        @Override
        public boolean hasSpace() {
            return true;
        }
    }
}
