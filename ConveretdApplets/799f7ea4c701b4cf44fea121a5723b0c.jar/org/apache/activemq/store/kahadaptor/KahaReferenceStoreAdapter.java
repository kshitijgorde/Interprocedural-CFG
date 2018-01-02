// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadaptor;

import org.slf4j.LoggerFactory;
import org.apache.activemq.kaha.StoreFactory;
import org.apache.activemq.util.IOHelper;
import java.io.File;
import java.util.Iterator;
import java.util.Collection;
import java.util.HashSet;
import org.apache.activemq.kaha.MessageIdMarshaller;
import org.apache.activemq.command.MessageId;
import org.apache.activemq.store.TopicReferenceStore;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.store.ReferenceStore;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.wireformat.WireFormat;
import org.apache.activemq.kaha.Marshaller;
import org.apache.activemq.kaha.CommandMarshaller;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.activemq.store.TopicMessageStore;
import org.apache.activemq.command.ActiveMQTopic;
import java.io.IOException;
import org.apache.activemq.store.MessageStore;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.kaha.impl.index.hash.HashIndex;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Set;
import org.apache.activemq.kaha.Store;
import org.apache.activemq.command.SubscriptionInfo;
import org.apache.activemq.kaha.ListContainer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;
import org.apache.activemq.store.amq.AMQTx;
import org.apache.activemq.command.TransactionId;
import org.apache.activemq.kaha.MapContainer;
import org.slf4j.Logger;
import org.apache.activemq.store.ReferenceStoreAdapter;

public class KahaReferenceStoreAdapter extends KahaPersistenceAdapter implements ReferenceStoreAdapter
{
    private static final Logger LOG;
    private static final String STORE_STATE = "store-state";
    private static final String QUEUE_DATA = "queue-data";
    private static final String INDEX_VERSION_NAME = "INDEX_VERSION";
    private static final Integer INDEX_VERSION;
    private static final String RECORD_REFERENCES = "record-references";
    private static final String TRANSACTIONS = "transactions-state";
    private MapContainer stateMap;
    private MapContainer<TransactionId, AMQTx> preparedTransactions;
    private Map<Integer, AtomicInteger> recordReferences;
    private ListContainer<SubscriptionInfo> durableSubscribers;
    private boolean storeValid;
    private Store stateStore;
    private boolean persistentIndex;
    private int indexBinSize;
    private int indexKeySize;
    private int indexPageSize;
    private int indexMaxBinSize;
    private int indexLoadFactor;
    Map<Integer, Set<Integer>> ackMessageFileMap;
    
    public KahaReferenceStoreAdapter(final AtomicLong size) {
        super(size);
        this.recordReferences = new HashMap<Integer, AtomicInteger>();
        this.persistentIndex = true;
        this.indexBinSize = HashIndex.DEFAULT_BIN_SIZE;
        this.indexKeySize = HashIndex.DEFAULT_KEY_SIZE;
        this.indexPageSize = HashIndex.DEFAULT_PAGE_SIZE;
        this.indexMaxBinSize = HashIndex.MAXIMUM_CAPACITY;
        this.indexLoadFactor = HashIndex.DEFAULT_LOAD_FACTOR;
        this.ackMessageFileMap = new HashMap<Integer, Set<Integer>>();
    }
    
    @Override
    public synchronized MessageStore createQueueMessageStore(final ActiveMQQueue destination) throws IOException {
        throw new RuntimeException("Use createQueueReferenceStore instead");
    }
    
    @Override
    public synchronized TopicMessageStore createTopicMessageStore(final ActiveMQTopic destination) throws IOException {
        throw new RuntimeException("Use createTopicReferenceStore instead");
    }
    
    @Override
    public synchronized void start() throws Exception {
        super.start();
        final Store store = this.getStateStore();
        final boolean empty = store.getMapContainerIds().isEmpty();
        (this.stateMap = store.getMapContainer("state", "store-state")).load();
        this.storeValid = true;
        if (!empty) {
            final AtomicBoolean status = this.stateMap.get("store-state");
            if (status != null) {
                this.storeValid = status.get();
            }
            if (this.storeValid) {
                final Integer indexVersion = this.stateMap.get("INDEX_VERSION");
                if (indexVersion == null || indexVersion < KahaReferenceStoreAdapter.INDEX_VERSION) {
                    this.storeValid = false;
                    KahaReferenceStoreAdapter.LOG.warn("Indexes at an older version - need to regenerate");
                }
            }
            if (this.storeValid && this.stateMap.containsKey("record-references")) {
                this.recordReferences = this.stateMap.get("record-references");
            }
        }
        this.stateMap.put("store-state", new AtomicBoolean());
        this.stateMap.put("INDEX_VERSION", KahaReferenceStoreAdapter.INDEX_VERSION);
        (this.durableSubscribers = (ListContainer<SubscriptionInfo>)store.getListContainer("durableSubscribers")).setMarshaller(new CommandMarshaller());
        (this.preparedTransactions = (MapContainer<TransactionId, AMQTx>)store.getMapContainer("transactions", "transactions-state", false)).setKeyMarshaller(Store.COMMAND_MARSHALLER);
        this.preparedTransactions.setValueMarshaller(new AMQTxMarshaller(this.wireFormat));
    }
    
    @Override
    public synchronized void stop() throws Exception {
        this.stateMap.put("record-references", this.recordReferences);
        this.stateMap.put("store-state", new AtomicBoolean(true));
        this.stateMap.put("INDEX_VERSION", KahaReferenceStoreAdapter.INDEX_VERSION);
        if (this.stateStore != null) {
            this.stateStore.close();
            this.stateStore = null;
            this.stateMap = null;
        }
        super.stop();
    }
    
    @Override
    public void commitTransaction(final ConnectionContext context) throws IOException {
    }
    
    @Override
    public boolean isStoreValid() {
        return this.storeValid;
    }
    
    @Override
    public ReferenceStore createQueueReferenceStore(final ActiveMQQueue destination) throws IOException {
        ReferenceStore rc = this.queues.get(destination);
        if (rc == null) {
            rc = new KahaReferenceStore(this, this.getMapReferenceContainer(destination, "queue-data"), destination);
            this.messageStores.put(destination, rc);
            this.queues.put(destination, rc);
        }
        return rc;
    }
    
    @Override
    public TopicReferenceStore createTopicReferenceStore(final ActiveMQTopic destination) throws IOException {
        TopicReferenceStore rc = this.topics.get(destination);
        if (rc == null) {
            final Store store = this.getStore();
            final MapContainer messageContainer = this.getMapReferenceContainer(destination.getPhysicalName(), "topic-data");
            final MapContainer subsContainer = this.getSubsMapContainer(destination.getPhysicalName() + "-Subscriptions", "blob");
            final ListContainer<TopicSubAck> ackContainer = (ListContainer<TopicSubAck>)store.getListContainer(destination.getPhysicalName(), "topic-acks");
            ackContainer.setMarshaller(new TopicSubAckMarshaller());
            rc = new KahaTopicReferenceStore(store, this, messageContainer, ackContainer, subsContainer, destination);
            this.messageStores.put(destination, rc);
            this.topics.put(destination, rc);
        }
        return rc;
    }
    
    public void removeReferenceStore(final KahaReferenceStore referenceStore) {
        final ActiveMQDestination destination = referenceStore.getDestination();
        if (destination.isQueue()) {
            this.queues.remove(destination);
            try {
                this.getStore().deleteMapContainer(destination, "queue-data");
            }
            catch (IOException e) {
                KahaReferenceStoreAdapter.LOG.error("Failed to delete queue-data map container for destination: " + destination, e);
            }
        }
        else {
            this.topics.remove(destination);
        }
        this.messageStores.remove(destination);
    }
    
    protected MapContainer<MessageId, ReferenceRecord> getMapReferenceContainer(final Object id, final String containerName) throws IOException {
        final Store store = this.getStore();
        final MapContainer<MessageId, ReferenceRecord> container = (MapContainer<MessageId, ReferenceRecord>)store.getMapContainer(id, containerName, this.persistentIndex);
        container.setIndexBinSize(this.getIndexBinSize());
        container.setIndexKeySize(this.getIndexKeySize());
        container.setIndexPageSize(this.getIndexPageSize());
        container.setIndexMaxBinSize(this.getIndexMaxBinSize());
        container.setIndexLoadFactor(this.getIndexLoadFactor());
        container.setKeyMarshaller(new MessageIdMarshaller());
        container.setValueMarshaller(new ReferenceRecordMarshaller());
        container.load();
        return container;
    }
    
    synchronized void addInterestInRecordFile(final int recordNumber) {
        final Integer key = recordNumber;
        AtomicInteger rr = this.recordReferences.get(key);
        if (rr == null) {
            rr = new AtomicInteger();
            this.recordReferences.put(key, rr);
        }
        rr.incrementAndGet();
    }
    
    synchronized void removeInterestInRecordFile(final int recordNumber) {
        final Integer key = recordNumber;
        final AtomicInteger rr = this.recordReferences.get(key);
        if (rr != null && rr.decrementAndGet() <= 0) {
            this.recordReferences.remove(key);
        }
    }
    
    @Override
    public synchronized Set<Integer> getReferenceFileIdsInUse() throws IOException {
        final Set inUse = new HashSet(this.recordReferences.keySet());
        final Iterator<Map.Entry<Integer, Set<Integer>>> ackReferences = this.ackMessageFileMap.entrySet().iterator();
        while (ackReferences.hasNext()) {
            final Map.Entry<Integer, Set<Integer>> ackReference = ackReferences.next();
            if (!inUse.contains(ackReference.getKey())) {
                for (final Integer referencedFileId : ackReference.getValue()) {
                    if (inUse.contains(referencedFileId)) {
                        inUse.add(ackReference.getKey());
                        KahaReferenceStoreAdapter.LOG.debug("not removing data file: " + ackReference.getKey() + " as contained ack(s) refer to referencedFileId file: " + ackReference.getValue());
                        break;
                    }
                }
            }
            if (!inUse.contains(ackReference.getKey())) {
                ackReferences.remove();
            }
        }
        return (Set<Integer>)inUse;
    }
    
    public synchronized void recordAckFileReferences(final int ackDataFileId, final int messageFileId) {
        Set<Integer> referenceFileIds = this.ackMessageFileMap.get(ackDataFileId);
        if (referenceFileIds == null) {
            referenceFileIds = new HashSet<Integer>();
            referenceFileIds.add(messageFileId);
            this.ackMessageFileMap.put(ackDataFileId, referenceFileIds);
        }
        else {
            final Integer id = messageFileId;
            if (!referenceFileIds.contains(id)) {
                referenceFileIds.add(id);
            }
        }
    }
    
    @Override
    public void clearMessages() throws IOException {
        super.deleteAllMessages();
    }
    
    @Override
    public void recoverState() throws IOException {
        final Set<SubscriptionInfo> set = new HashSet<SubscriptionInfo>(this.durableSubscribers);
        for (final SubscriptionInfo info : set) {
            KahaReferenceStoreAdapter.LOG.info("Recovering subscriber state for durable subscriber: " + info);
            final TopicReferenceStore ts = this.createTopicReferenceStore((ActiveMQTopic)info.getDestination());
            ts.addSubsciption(info, false);
        }
    }
    
    @Override
    public void recoverSubscription(final SubscriptionInfo info) throws IOException {
        final TopicReferenceStore ts = this.createTopicReferenceStore((ActiveMQTopic)info.getDestination());
        KahaReferenceStoreAdapter.LOG.info("Recovering subscriber state for durable subscriber: " + info);
        ts.addSubsciption(info, false);
    }
    
    @Override
    public Map<TransactionId, AMQTx> retrievePreparedState() throws IOException {
        final Map<TransactionId, AMQTx> result = new HashMap<TransactionId, AMQTx>();
        this.preparedTransactions.load();
        for (final TransactionId key : this.preparedTransactions.keySet()) {
            final AMQTx value = this.preparedTransactions.get(key);
            result.put(key, value);
        }
        return result;
    }
    
    @Override
    public void savePreparedState(final Map<TransactionId, AMQTx> map) throws IOException {
        this.preparedTransactions.clear();
        for (final Map.Entry<TransactionId, AMQTx> entry : map.entrySet()) {
            this.preparedTransactions.put(entry.getKey(), entry.getValue());
        }
    }
    
    @Override
    public synchronized void setDirectory(final File directory) {
        final File file = new File(directory, "data");
        super.setDirectory(file);
        this.stateStore = this.createStateStore(directory);
    }
    
    protected synchronized Store getStateStore() throws IOException {
        if (this.stateStore == null) {
            final File stateDirectory = new File(this.getDirectory(), "kr-state");
            IOHelper.mkdirs(stateDirectory);
            this.stateStore = this.createStateStore(this.getDirectory());
        }
        return this.stateStore;
    }
    
    @Override
    public void deleteAllMessages() throws IOException {
        super.deleteAllMessages();
        if (this.stateStore != null) {
            if (this.stateStore.isInitialized()) {
                this.stateStore.clear();
            }
            else {
                this.stateStore.delete();
            }
        }
        else {
            final File stateDirectory = new File(this.getDirectory(), "kr-state");
            StoreFactory.delete(stateDirectory);
        }
    }
    
    @Override
    public boolean isPersistentIndex() {
        return this.persistentIndex;
    }
    
    @Override
    public void setPersistentIndex(final boolean persistentIndex) {
        this.persistentIndex = persistentIndex;
    }
    
    private Store createStateStore(final File directory) {
        final File stateDirectory = new File(directory, "state");
        try {
            IOHelper.mkdirs(stateDirectory);
            return StoreFactory.open(stateDirectory, "rw");
        }
        catch (IOException e) {
            KahaReferenceStoreAdapter.LOG.error("Failed to create the state store", e);
            return null;
        }
    }
    
    protected void addSubscriberState(final SubscriptionInfo info) throws IOException {
        this.durableSubscribers.add(info);
    }
    
    protected void removeSubscriberState(final SubscriptionInfo info) {
        this.durableSubscribers.remove(info);
    }
    
    public int getIndexBinSize() {
        return this.indexBinSize;
    }
    
    public void setIndexBinSize(final int indexBinSize) {
        this.indexBinSize = indexBinSize;
    }
    
    public int getIndexKeySize() {
        return this.indexKeySize;
    }
    
    public void setIndexKeySize(final int indexKeySize) {
        this.indexKeySize = indexKeySize;
    }
    
    public int getIndexPageSize() {
        return this.indexPageSize;
    }
    
    public void setIndexPageSize(final int indexPageSize) {
        this.indexPageSize = indexPageSize;
    }
    
    public int getIndexMaxBinSize() {
        return this.indexMaxBinSize;
    }
    
    public void setIndexMaxBinSize(final int maxBinSize) {
        this.indexMaxBinSize = maxBinSize;
    }
    
    public int getIndexLoadFactor() {
        return this.indexLoadFactor;
    }
    
    public void setIndexLoadFactor(final int loadFactor) {
        this.indexLoadFactor = loadFactor;
    }
    
    static {
        LOG = LoggerFactory.getLogger(KahaReferenceStoreAdapter.class);
        INDEX_VERSION = new Integer(7);
    }
}
