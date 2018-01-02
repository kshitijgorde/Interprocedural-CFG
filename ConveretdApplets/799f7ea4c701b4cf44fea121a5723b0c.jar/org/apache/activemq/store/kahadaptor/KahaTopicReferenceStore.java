// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadaptor;

import org.slf4j.LoggerFactory;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;
import org.apache.activemq.store.MessageRecoveryListener;
import org.apache.activemq.command.MessageAck;
import org.apache.activemq.kaha.Marshaller;
import org.apache.activemq.kaha.StoreEntry;
import org.apache.activemq.store.ReferenceStore;
import org.apache.activemq.command.Message;
import org.apache.activemq.broker.ConnectionContext;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.MessageId;
import org.apache.activemq.kaha.Store;
import org.apache.activemq.command.SubscriptionInfo;
import org.apache.activemq.kaha.MapContainer;
import java.util.Map;
import org.apache.activemq.kaha.ListContainer;
import org.slf4j.Logger;
import org.apache.activemq.store.TopicReferenceStore;

public class KahaTopicReferenceStore extends KahaReferenceStore implements TopicReferenceStore
{
    private static final Logger LOG;
    protected ListContainer<TopicSubAck> ackContainer;
    protected Map<String, TopicSubContainer> subscriberMessages;
    private MapContainer<String, SubscriptionInfo> subscriberContainer;
    private Store store;
    private static final String TOPIC_SUB_NAME = "tsn";
    
    public KahaTopicReferenceStore(final Store store, final KahaReferenceStoreAdapter adapter, final MapContainer<MessageId, ReferenceRecord> messageContainer, final ListContainer<TopicSubAck> ackContainer, final MapContainer<String, SubscriptionInfo> subsContainer, final ActiveMQDestination destination) throws IOException {
        super(adapter, messageContainer, destination);
        this.subscriberMessages = new ConcurrentHashMap<String, TopicSubContainer>();
        this.store = store;
        this.ackContainer = ackContainer;
        this.subscriberContainer = subsContainer;
        for (final SubscriptionInfo info : this.subscriberContainer.values()) {
            this.addSubscriberMessageContainer(info.getClientId(), info.getSubscriptionName());
        }
    }
    
    @Override
    public void dispose(final ConnectionContext context) {
        super.dispose(context);
        this.subscriberContainer.delete();
    }
    
    @Override
    protected MessageId getMessageId(final Object object) {
        return new MessageId(((ReferenceRecord)object).getMessageId());
    }
    
    @Override
    public void addMessage(final ConnectionContext context, final Message message) throws IOException {
        throw new RuntimeException("Use addMessageReference instead");
    }
    
    @Override
    public Message getMessage(final MessageId identity) throws IOException {
        throw new RuntimeException("Use addMessageReference instead");
    }
    
    @Override
    public boolean addMessageReference(final ConnectionContext context, final MessageId messageId, final ReferenceStore.ReferenceData data) {
        boolean uniqueReferenceAdded = false;
        this.lock.lock();
        try {
            final ReferenceRecord record = new ReferenceRecord(messageId.toString(), data);
            final int subscriberCount = this.subscriberMessages.size();
            if (subscriberCount > 0 && !this.isDuplicate(messageId)) {
                final StoreEntry messageEntry = this.messageContainer.place(messageId, record);
                this.addInterest(record);
                uniqueReferenceAdded = true;
                final TopicSubAck tsa = new TopicSubAck();
                tsa.setCount(subscriberCount);
                tsa.setMessageEntry(messageEntry);
                final StoreEntry ackEntry = this.ackContainer.placeLast(tsa);
                for (final TopicSubContainer container : this.subscriberMessages.values()) {
                    final ConsumerMessageRef ref = new ConsumerMessageRef();
                    ref.setAckEntry(ackEntry);
                    ref.setMessageEntry(messageEntry);
                    ref.setMessageId(messageId);
                    container.add(ref);
                }
                if (KahaTopicReferenceStore.LOG.isTraceEnabled()) {
                    KahaTopicReferenceStore.LOG.trace(this.destination.getPhysicalName() + " add reference: " + messageId);
                }
            }
            else if (KahaTopicReferenceStore.LOG.isTraceEnabled()) {
                KahaTopicReferenceStore.LOG.trace("no subscribers or duplicate add for: " + messageId);
            }
        }
        finally {
            this.lock.unlock();
        }
        return uniqueReferenceAdded;
    }
    
    @Override
    public ReferenceStore.ReferenceData getMessageReference(final MessageId identity) throws IOException {
        final ReferenceRecord result = this.messageContainer.get(identity);
        if (result == null) {
            return null;
        }
        return result.getData();
    }
    
    public void addReferenceFileIdsInUse() {
        for (StoreEntry entry = this.ackContainer.getFirst(); entry != null; entry = this.ackContainer.getNext(entry)) {
            final TopicSubAck subAck = this.ackContainer.get(entry);
            if (subAck.getCount() > 0) {
                final ReferenceRecord rr = this.messageContainer.getValue(subAck.getMessageEntry());
                this.addInterest(rr);
            }
        }
    }
    
    protected MapContainer addSubscriberMessageContainer(final String clientId, final String subscriptionName) throws IOException {
        final String containerName = this.getSubscriptionContainerName(this.getSubscriptionKey(clientId, subscriptionName));
        final MapContainer container = this.store.getMapContainer(containerName, containerName);
        container.setKeyMarshaller(Store.MESSAGEID_MARSHALLER);
        final Marshaller marshaller = new ConsumerMessageRefMarshaller();
        container.setValueMarshaller(marshaller);
        final TopicSubContainer tsc = new TopicSubContainer(container);
        this.subscriberMessages.put(this.getSubscriptionKey(clientId, subscriptionName), tsc);
        return container;
    }
    
    @Override
    public boolean acknowledgeReference(final ConnectionContext context, final String clientId, final String subscriptionName, final MessageId messageId) throws IOException {
        boolean removeMessage = false;
        this.lock.lock();
        try {
            final String key = this.getSubscriptionKey(clientId, subscriptionName);
            final TopicSubContainer container = this.subscriberMessages.get(key);
            if (container != null) {
                ConsumerMessageRef ref = null;
                if ((ref = container.remove(messageId)) != null) {
                    StoreEntry entry = ref.getAckEntry();
                    entry = this.ackContainer.refresh(entry);
                    final TopicSubAck tsa = this.ackContainer.get(entry);
                    if (tsa != null) {
                        if (tsa.decrementCount() <= 0) {
                            this.ackContainer.remove(entry);
                            final ReferenceRecord rr = this.messageContainer.get(messageId);
                            if (rr != null) {
                                entry = tsa.getMessageEntry();
                                entry = this.messageContainer.refresh(entry);
                                this.messageContainer.remove(entry);
                                this.removeInterest(rr);
                                removeMessage = true;
                                this.dispatchAudit.isDuplicate(messageId);
                            }
                        }
                        else {
                            this.ackContainer.update(entry, tsa);
                        }
                    }
                    if (KahaTopicReferenceStore.LOG.isTraceEnabled()) {
                        KahaTopicReferenceStore.LOG.trace(this.destination.getPhysicalName() + " remove: " + messageId);
                    }
                }
                else if (this.ackContainer.isEmpty() || this.subscriberMessages.size() == 1 || this.isUnreferencedBySubscribers(key, this.subscriberMessages, messageId)) {
                    removeMessage = true;
                    this.dispatchAudit.isDuplicate(messageId);
                    if (KahaTopicReferenceStore.LOG.isDebugEnabled()) {
                        KahaTopicReferenceStore.LOG.debug(this.destination.getPhysicalName() + " remove with no outstanding reference (ack before add): " + messageId);
                    }
                }
            }
        }
        finally {
            this.lock.unlock();
        }
        return removeMessage;
    }
    
    private boolean isUnreferencedBySubscribers(final String key, final Map<String, TopicSubContainer> subscriberContainers, final MessageId messageId) {
        boolean isUnreferenced = true;
        for (final Map.Entry<String, TopicSubContainer> entry : subscriberContainers.entrySet()) {
            if (!key.equals(entry.getKey()) && !entry.getValue().isEmpty()) {
                final TopicSubContainer container = entry.getValue();
                for (final ConsumerMessageRef ref : container) {
                    if (messageId.equals(ref.getMessageId())) {
                        isUnreferenced = false;
                        break;
                    }
                }
            }
        }
        return isUnreferenced;
    }
    
    @Override
    public void acknowledge(final ConnectionContext context, final String clientId, final String subscriptionName, final MessageId messageId, final MessageAck ack) throws IOException {
        this.acknowledgeReference(context, clientId, subscriptionName, messageId);
    }
    
    @Override
    public void addSubsciption(final SubscriptionInfo info, final boolean retroactive) throws IOException {
        final String key = this.getSubscriptionKey(info.getClientId(), info.getSubscriptionName());
        this.lock.lock();
        try {
            if (!this.subscriberContainer.containsKey(key)) {
                this.subscriberContainer.put(key, info);
                this.adapter.addSubscriberState(info);
            }
            this.addSubscriberMessageContainer(info.getClientId(), info.getSubscriptionName());
            if (retroactive) {}
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void deleteSubscription(final String clientId, final String subscriptionName) throws IOException {
        this.lock.lock();
        try {
            final SubscriptionInfo info = this.lookupSubscription(clientId, subscriptionName);
            if (info != null) {
                this.adapter.removeSubscriberState(info);
            }
            this.removeSubscriberMessageContainer(clientId, subscriptionName);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public SubscriptionInfo[] getAllSubscriptions() throws IOException {
        final SubscriptionInfo[] result = this.subscriberContainer.values().toArray(new SubscriptionInfo[this.subscriberContainer.size()]);
        return result;
    }
    
    @Override
    public int getMessageCount(final String clientId, final String subscriberName) throws IOException {
        final String key = this.getSubscriptionKey(clientId, subscriberName);
        final TopicSubContainer container = this.subscriberMessages.get(key);
        return (container != null) ? container.size() : 0;
    }
    
    @Override
    public SubscriptionInfo lookupSubscription(final String clientId, final String subscriptionName) throws IOException {
        return this.subscriberContainer.get(this.getSubscriptionKey(clientId, subscriptionName));
    }
    
    @Override
    public void recoverNextMessages(final String clientId, final String subscriptionName, final int maxReturned, final MessageRecoveryListener listener) throws Exception {
        final String key = this.getSubscriptionKey(clientId, subscriptionName);
        this.lock.lock();
        try {
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
                        final ReferenceRecord msg = this.messageContainer.getValue(consumerRef.getMessageEntry());
                        if (msg != null) {
                            if (this.recoverReference(listener, msg)) {
                                ++count;
                                container.setBatchEntry(msg.getMessageId(), entry);
                            }
                        }
                        else {
                            container.reset();
                        }
                        entry = container.getNextEntry(entry);
                    } while (entry != null && count < maxReturned && listener.hasSpace());
                }
            }
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void recoverSubscription(final String clientId, final String subscriptionName, final MessageRecoveryListener listener) throws Exception {
        final String key = this.getSubscriptionKey(clientId, subscriptionName);
        final TopicSubContainer container = this.subscriberMessages.get(key);
        if (container != null) {
            for (final ConsumerMessageRef ref : container) {
                final ReferenceRecord msg = this.messageContainer.getValue(ref.getMessageEntry());
                if (msg != null && !this.recoverReference(listener, msg)) {
                    break;
                }
            }
        }
    }
    
    @Override
    public void resetBatching(final String clientId, final String subscriptionName) {
        this.lock.lock();
        try {
            final String key = this.getSubscriptionKey(clientId, subscriptionName);
            final TopicSubContainer topicSubContainer = this.subscriberMessages.get(key);
            if (topicSubContainer != null) {
                topicSubContainer.reset();
            }
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void removeAllMessages(final ConnectionContext context) throws IOException {
        this.lock.lock();
        try {
            final Set<String> tmpSet = new HashSet<String>(this.subscriberContainer.keySet());
            for (final String key : tmpSet) {
                final TopicSubContainer container = this.subscriberMessages.get(key);
                if (container != null) {
                    container.clear();
                }
            }
            this.ackContainer.clear();
        }
        finally {
            this.lock.unlock();
        }
        super.removeAllMessages(context);
    }
    
    protected void removeSubscriberMessageContainer(final String clientId, final String subscriptionName) throws IOException {
        final String subscriberKey = this.getSubscriptionKey(clientId, subscriptionName);
        final String containerName = this.getSubscriptionContainerName(subscriberKey);
        this.subscriberContainer.remove(subscriberKey);
        final TopicSubContainer container = this.subscriberMessages.remove(subscriberKey);
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
        }
        this.store.deleteMapContainer(containerName, containerName);
    }
    
    protected String getSubscriptionKey(final String clientId, final String subscriberName) {
        final StringBuffer buffer = new StringBuffer();
        buffer.append(clientId).append(":");
        final String name = (subscriberName != null) ? subscriberName : "NOT_SET";
        return buffer.append(name).toString();
    }
    
    private String getSubscriptionContainerName(final String subscriptionKey) {
        final StringBuffer result = new StringBuffer("tsn");
        result.append(this.destination.getQualifiedName());
        result.append(subscriptionKey);
        return result.toString();
    }
    
    static {
        LOG = LoggerFactory.getLogger(KahaTopicReferenceStore.class);
    }
}
