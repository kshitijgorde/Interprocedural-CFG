// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadaptor;

import org.apache.activemq.kaha.Marshaller;
import org.apache.activemq.store.MessageRecoveryListener;
import org.apache.activemq.command.MessageAck;
import org.apache.activemq.kaha.StoreEntry;
import org.apache.activemq.broker.ConnectionContext;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.Message;
import org.apache.activemq.command.MessageId;
import org.apache.activemq.kaha.MapContainer;
import org.apache.activemq.kaha.Store;
import org.apache.activemq.command.SubscriptionInfo;
import java.util.Map;
import org.apache.activemq.kaha.ListContainer;
import org.apache.activemq.store.TopicMessageStore;

public class KahaTopicMessageStore extends KahaMessageStore implements TopicMessageStore
{
    protected ListContainer<TopicSubAck> ackContainer;
    protected Map<Object, TopicSubContainer> subscriberMessages;
    private Map<String, SubscriptionInfo> subscriberContainer;
    private Store store;
    
    public KahaTopicMessageStore(final Store store, final MapContainer<MessageId, Message> messageContainer, final ListContainer<TopicSubAck> ackContainer, final MapContainer<String, SubscriptionInfo> subsContainer, final ActiveMQDestination destination) throws IOException {
        super(messageContainer, destination);
        this.subscriberMessages = new ConcurrentHashMap<Object, TopicSubContainer>();
        this.store = store;
        this.ackContainer = ackContainer;
        this.subscriberContainer = subsContainer;
        for (final Object key : this.subscriberContainer.keySet()) {
            this.addSubscriberMessageContainer(key);
        }
    }
    
    @Override
    public synchronized void addMessage(final ConnectionContext context, final Message message) throws IOException {
        final int subscriberCount = this.subscriberMessages.size();
        if (subscriberCount > 0) {
            final MessageId id = message.getMessageId();
            final StoreEntry messageEntry = this.messageContainer.place(id, message);
            final TopicSubAck tsa = new TopicSubAck();
            tsa.setCount(subscriberCount);
            tsa.setMessageEntry(messageEntry);
            final StoreEntry ackEntry = this.ackContainer.placeLast(tsa);
            for (final TopicSubContainer container : this.subscriberMessages.values()) {
                final ConsumerMessageRef ref = new ConsumerMessageRef();
                ref.setAckEntry(ackEntry);
                ref.setMessageEntry(messageEntry);
                ref.setMessageId(id);
                container.add(ref);
            }
        }
    }
    
    @Override
    public synchronized void acknowledge(final ConnectionContext context, final String clientId, final String subscriptionName, final MessageId messageId, final MessageAck ack) throws IOException {
        final String subcriberId = this.getSubscriptionKey(clientId, subscriptionName);
        final TopicSubContainer container = this.subscriberMessages.get(subcriberId);
        if (container != null) {
            final ConsumerMessageRef ref = container.remove(messageId);
            if (container.isEmpty()) {
                container.reset();
            }
            if (ref != null) {
                final TopicSubAck tsa = this.ackContainer.get(ref.getAckEntry());
                if (tsa != null) {
                    if (tsa.decrementCount() <= 0) {
                        StoreEntry entry = ref.getAckEntry();
                        entry = this.ackContainer.refresh(entry);
                        this.ackContainer.remove(entry);
                        entry = tsa.getMessageEntry();
                        entry = this.messageContainer.refresh(entry);
                        this.messageContainer.remove(entry);
                    }
                    else {
                        this.ackContainer.update(ref.getAckEntry(), tsa);
                    }
                }
            }
        }
    }
    
    @Override
    public synchronized SubscriptionInfo lookupSubscription(final String clientId, final String subscriptionName) throws IOException {
        return this.subscriberContainer.get(this.getSubscriptionKey(clientId, subscriptionName));
    }
    
    @Override
    public synchronized void addSubsciption(final SubscriptionInfo info, final boolean retroactive) throws IOException {
        final String key = this.getSubscriptionKey(info.getClientId(), info.getSubscriptionName());
        if (!this.subscriberContainer.containsKey(key)) {
            this.subscriberContainer.put(key, info);
        }
        this.addSubscriberMessageContainer(key);
    }
    
    @Override
    public synchronized void deleteSubscription(final String clientId, final String subscriptionName) throws IOException {
        final String key = this.getSubscriptionKey(clientId, subscriptionName);
        this.removeSubscriberMessageContainer(key);
    }
    
    @Override
    public synchronized void recoverSubscription(final String clientId, final String subscriptionName, final MessageRecoveryListener listener) throws Exception {
        final String key = this.getSubscriptionKey(clientId, subscriptionName);
        final TopicSubContainer container = this.subscriberMessages.get(key);
        if (container != null) {
            for (final ConsumerMessageRef ref : container) {
                final Message msg = this.messageContainer.get((MessageId)ref.getMessageEntry());
                if (msg != null && !this.recoverMessage(listener, msg)) {
                    break;
                }
            }
        }
    }
    
    @Override
    public synchronized void recoverNextMessages(final String clientId, final String subscriptionName, final int maxReturned, final MessageRecoveryListener listener) throws Exception {
        final String key = this.getSubscriptionKey(clientId, subscriptionName);
        final TopicSubContainer container = this.subscriberMessages.get(key);
        if (container != null) {
            int count = 0;
            StoreEntry entry = container.getBatchEntry();
            if (entry == null) {
                entry = container.getEntry();
            }
            else {
                entry = container.refreshEntry(entry);
                if (entry != null) {
                    entry = container.getNextEntry(entry);
                }
            }
            if (entry != null) {
                do {
                    final ConsumerMessageRef consumerRef = container.get(entry);
                    final Message msg = this.messageContainer.getValue(consumerRef.getMessageEntry());
                    if (msg != null) {
                        this.recoverMessage(listener, msg);
                        ++count;
                        container.setBatchEntry(msg.getMessageId().toString(), entry);
                    }
                    else {
                        container.reset();
                    }
                    entry = container.getNextEntry(entry);
                } while (entry != null && count < maxReturned && listener.hasSpace());
            }
        }
    }
    
    @Override
    public synchronized void delete() {
        super.delete();
        this.ackContainer.clear();
        this.subscriberContainer.clear();
    }
    
    @Override
    public SubscriptionInfo[] getAllSubscriptions() throws IOException {
        return this.subscriberContainer.values().toArray(new SubscriptionInfo[this.subscriberContainer.size()]);
    }
    
    protected String getSubscriptionKey(final String clientId, final String subscriberName) {
        String result = clientId + ":";
        result += ((subscriberName != null) ? subscriberName : "NOT_SET");
        return result;
    }
    
    protected MapContainer addSubscriberMessageContainer(final Object key) throws IOException {
        final MapContainer container = this.store.getMapContainer(key, "topic-subs");
        container.setKeyMarshaller(Store.MESSAGEID_MARSHALLER);
        final Marshaller marshaller = new ConsumerMessageRefMarshaller();
        container.setValueMarshaller(marshaller);
        final TopicSubContainer tsc = new TopicSubContainer(container);
        this.subscriberMessages.put(key, tsc);
        return container;
    }
    
    protected synchronized void removeSubscriberMessageContainer(final Object key) throws IOException {
        this.subscriberContainer.remove(key);
        final TopicSubContainer container = this.subscriberMessages.remove(key);
        if (container != null) {
            for (final ConsumerMessageRef ref : container) {
                if (ref != null) {
                    final TopicSubAck tsa = this.ackContainer.get(ref.getAckEntry());
                    if (tsa == null) {
                        continue;
                    }
                    if (tsa.decrementCount() <= 0) {
                        this.ackContainer.remove(ref.getAckEntry());
                        this.messageContainer.remove(tsa.getMessageEntry());
                    }
                    else {
                        this.ackContainer.update(ref.getAckEntry(), tsa);
                    }
                }
            }
            container.clear();
        }
        this.store.deleteListContainer(key, "topic-subs");
    }
    
    @Override
    public synchronized int getMessageCount(final String clientId, final String subscriberName) throws IOException {
        final String key = this.getSubscriptionKey(clientId, subscriberName);
        final TopicSubContainer container = this.subscriberMessages.get(key);
        return (container != null) ? container.size() : 0;
    }
    
    @Override
    public synchronized void removeAllMessages(final ConnectionContext context) throws IOException {
        this.messageContainer.clear();
        this.ackContainer.clear();
        for (final TopicSubContainer container : this.subscriberMessages.values()) {
            container.clear();
        }
    }
    
    @Override
    public synchronized void resetBatching(final String clientId, final String subscriptionName) {
        final String key = this.getSubscriptionKey(clientId, subscriptionName);
        final TopicSubContainer topicSubContainer = this.subscriberMessages.get(key);
        if (topicSubContainer != null) {
            topicSubContainer.reset();
        }
    }
}
