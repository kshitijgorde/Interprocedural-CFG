// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadaptor;

import org.slf4j.LoggerFactory;
import org.apache.activemq.command.ProducerId;
import org.apache.activemq.util.IOHelper;
import org.apache.activemq.usage.SystemUsage;
import org.apache.activemq.kaha.MessageMarshaller;
import org.apache.activemq.kaha.MessageIdMarshaller;
import org.apache.activemq.kaha.StoreFactory;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.kaha.impl.StoreLockedExcpetion;
import java.util.Map;
import org.apache.activemq.wireformat.WireFormat;
import org.apache.activemq.kaha.CommandMarshaller;
import org.apache.activemq.store.TransactionStore;
import org.apache.activemq.kaha.ListContainer;
import org.apache.activemq.command.SubscriptionInfo;
import org.apache.activemq.command.Message;
import org.apache.activemq.command.MessageId;
import org.apache.activemq.kaha.MapContainer;
import org.apache.activemq.kaha.Marshaller;
import java.util.Iterator;
import java.io.IOException;
import org.apache.activemq.kaha.ContainerId;
import java.util.HashSet;
import java.util.Set;
import org.apache.activemq.broker.BrokerService;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.activemq.kaha.Store;
import java.io.File;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.store.MessageStore;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.store.TopicMessageStore;
import org.apache.activemq.command.ActiveMQTopic;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.activemq.openwire.OpenWireFormat;
import org.slf4j.Logger;
import org.apache.activemq.broker.BrokerServiceAware;
import org.apache.activemq.store.PersistenceAdapter;

public class KahaPersistenceAdapter implements PersistenceAdapter, BrokerServiceAware
{
    private static final int STORE_LOCKED_WAIT_DELAY = 10000;
    private static final Logger LOG;
    private static final String PREPARED_TRANSACTIONS_NAME = "PreparedTransactions";
    protected OpenWireFormat wireFormat;
    protected KahaTransactionStore transactionStore;
    protected ConcurrentHashMap<ActiveMQTopic, TopicMessageStore> topics;
    protected ConcurrentHashMap<ActiveMQQueue, MessageStore> queues;
    protected ConcurrentHashMap<ActiveMQDestination, MessageStore> messageStores;
    private long maxDataFileLength;
    private File directory;
    private String brokerName;
    private Store theStore;
    private boolean initialized;
    private final AtomicLong storeSize;
    private boolean persistentIndex;
    private BrokerService brokerService;
    
    public KahaPersistenceAdapter(final AtomicLong size) {
        this.wireFormat = new OpenWireFormat();
        this.topics = new ConcurrentHashMap<ActiveMQTopic, TopicMessageStore>();
        this.queues = new ConcurrentHashMap<ActiveMQQueue, MessageStore>();
        this.messageStores = new ConcurrentHashMap<ActiveMQDestination, MessageStore>();
        this.maxDataFileLength = 33554432L;
        this.persistentIndex = true;
        this.storeSize = size;
    }
    
    public KahaPersistenceAdapter() {
        this(new AtomicLong());
    }
    
    @Override
    public Set<ActiveMQDestination> getDestinations() {
        final Set<ActiveMQDestination> rc = new HashSet<ActiveMQDestination>();
        try {
            final Store store = this.getStore();
            for (final ContainerId id : store.getMapContainerIds()) {
                final Object obj = id.getKey();
                if (obj instanceof ActiveMQDestination) {
                    rc.add((ActiveMQDestination)obj);
                }
            }
        }
        catch (IOException e) {
            KahaPersistenceAdapter.LOG.error("Failed to get destinations ", e);
        }
        return rc;
    }
    
    @Override
    public synchronized MessageStore createQueueMessageStore(final ActiveMQQueue destination) throws IOException {
        MessageStore rc = this.queues.get(destination);
        if (rc == null) {
            rc = new KahaMessageStore(this.getMapContainer(destination, "queue-data"), destination);
            this.messageStores.put(destination, rc);
            if (this.transactionStore != null) {
                rc = this.transactionStore.proxy(rc);
            }
            this.queues.put(destination, rc);
        }
        return rc;
    }
    
    @Override
    public synchronized TopicMessageStore createTopicMessageStore(final ActiveMQTopic destination) throws IOException {
        TopicMessageStore rc = this.topics.get(destination);
        if (rc == null) {
            final Store store = this.getStore();
            final MapContainer messageContainer = this.getMapContainer(destination, "topic-data");
            final MapContainer subsContainer = this.getSubsMapContainer(destination.toString() + "-Subscriptions", "topic-subs");
            final ListContainer<TopicSubAck> ackContainer = (ListContainer<TopicSubAck>)store.getListContainer(destination.toString(), "topic-acks");
            ackContainer.setMarshaller(new TopicSubAckMarshaller());
            rc = new KahaTopicMessageStore(store, messageContainer, ackContainer, subsContainer, destination);
            this.messageStores.put(destination, rc);
            if (this.transactionStore != null) {
                rc = this.transactionStore.proxy(rc);
            }
            this.topics.put(destination, rc);
        }
        return rc;
    }
    
    @Override
    public void removeQueueMessageStore(final ActiveMQQueue destination) {
        this.queues.remove(destination);
        try {
            if (this.theStore != null) {
                this.theStore.deleteMapContainer(destination, "queue-data");
            }
        }
        catch (IOException e) {
            KahaPersistenceAdapter.LOG.error("Failed to remove store map container for queue:" + destination, e);
        }
    }
    
    @Override
    public void removeTopicMessageStore(final ActiveMQTopic destination) {
        this.topics.remove(destination);
    }
    
    protected MessageStore retrieveMessageStore(final Object id) {
        final MessageStore result = this.messageStores.get(id);
        return result;
    }
    
    @Override
    public TransactionStore createTransactionStore() throws IOException {
        if (this.transactionStore == null) {
            while (true) {
                try {
                    final Store store = this.getStore();
                    final MapContainer container = store.getMapContainer("PreparedTransactions", "transactions");
                    container.setKeyMarshaller(new CommandMarshaller(this.wireFormat));
                    container.setValueMarshaller(new TransactionMarshaller(this.wireFormat));
                    container.load();
                    (this.transactionStore = new KahaTransactionStore(this, container)).setBrokerService(this.brokerService);
                }
                catch (StoreLockedExcpetion e) {
                    KahaPersistenceAdapter.LOG.info("Store is locked... waiting 10 seconds for the Store to be unlocked.");
                    try {
                        Thread.sleep(10000L);
                    }
                    catch (InterruptedException ex) {}
                    continue;
                }
                break;
            }
        }
        return this.transactionStore;
    }
    
    @Override
    public void beginTransaction(final ConnectionContext context) {
    }
    
    @Override
    public void commitTransaction(final ConnectionContext context) throws IOException {
        if (this.theStore != null) {
            this.theStore.force();
        }
    }
    
    @Override
    public void rollbackTransaction(final ConnectionContext context) {
    }
    
    @Override
    public void start() throws Exception {
        this.initialize();
    }
    
    @Override
    public void stop() throws Exception {
        if (this.theStore != null) {
            this.theStore.close();
        }
    }
    
    @Override
    public long getLastMessageBrokerSequenceId() throws IOException {
        return 0L;
    }
    
    @Override
    public void deleteAllMessages() throws IOException {
        if (this.theStore != null) {
            if (this.theStore.isInitialized()) {
                this.theStore.clear();
            }
            else {
                this.theStore.delete();
            }
        }
        else {
            StoreFactory.delete(this.getStoreDirectory());
        }
    }
    
    protected MapContainer<MessageId, Message> getMapContainer(final Object id, final String containerName) throws IOException {
        final Store store = this.getStore();
        final MapContainer<MessageId, Message> container = (MapContainer<MessageId, Message>)store.getMapContainer(id, containerName);
        container.setKeyMarshaller(new MessageIdMarshaller());
        container.setValueMarshaller(new MessageMarshaller(this.wireFormat));
        container.load();
        return container;
    }
    
    protected MapContainer getSubsMapContainer(final Object id, final String containerName) throws IOException {
        final Store store = this.getStore();
        final MapContainer container = store.getMapContainer(id, containerName);
        container.setKeyMarshaller(Store.STRING_MARSHALLER);
        container.setValueMarshaller(this.createMessageMarshaller());
        container.load();
        return container;
    }
    
    protected Marshaller<Object> createMessageMarshaller() {
        return new CommandMarshaller(this.wireFormat);
    }
    
    protected ListContainer<TopicSubAck> getListContainer(final Object id, final String containerName) throws IOException {
        final Store store = this.getStore();
        final ListContainer<TopicSubAck> container = (ListContainer<TopicSubAck>)store.getListContainer(id, containerName);
        container.setMarshaller(this.createMessageMarshaller());
        container.load();
        return container;
    }
    
    @Override
    public void setUsageManager(final SystemUsage usageManager) {
    }
    
    public long getMaxDataFileLength() {
        return this.maxDataFileLength;
    }
    
    public boolean isPersistentIndex() {
        return this.persistentIndex;
    }
    
    public void setPersistentIndex(final boolean persistentIndex) {
        this.persistentIndex = persistentIndex;
    }
    
    public void setMaxDataFileLength(final long maxDataFileLength) {
        this.maxDataFileLength = maxDataFileLength;
    }
    
    protected final synchronized Store getStore() throws IOException {
        if (this.theStore == null) {
            this.theStore = this.createStore();
        }
        return this.theStore;
    }
    
    protected final Store createStore() throws IOException {
        final Store result = StoreFactory.open(this.getStoreDirectory(), "rw", this.storeSize);
        result.setMaxDataFileLength(this.maxDataFileLength);
        result.setPersistentIndex(this.isPersistentIndex());
        result.setDefaultContainerName("container-roots");
        return result;
    }
    
    private String getStoreName() {
        this.initialize();
        return this.directory.getAbsolutePath();
    }
    
    private File getStoreDirectory() {
        this.initialize();
        return this.directory;
    }
    
    @Override
    public String toString() {
        return "KahaPersistenceAdapter(" + this.getStoreName() + ")";
    }
    
    @Override
    public void setBrokerName(final String brokerName) {
        this.brokerName = brokerName;
    }
    
    public String getBrokerName() {
        return this.brokerName;
    }
    
    public File getDirectory() {
        return this.directory;
    }
    
    @Override
    public void setDirectory(final File directory) {
        this.directory = directory;
    }
    
    @Override
    public void checkpoint(final boolean sync) throws IOException {
        if (sync) {
            this.getStore().force();
        }
    }
    
    @Override
    public long size() {
        return this.storeSize.get();
    }
    
    private void initialize() {
        if (!this.initialized) {
            this.initialized = true;
            if (this.directory == null) {
                File file = new File(IOHelper.getDefaultDataDirectory());
                file = new File(file, IOHelper.toFileSystemSafeName(this.brokerName) + "-kahastore");
                this.setDirectory(file);
            }
            try {
                IOHelper.mkdirs(this.directory);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.wireFormat.setCacheEnabled(false);
            this.wireFormat.setTightEncodingEnabled(true);
        }
    }
    
    @Override
    public void setBrokerService(final BrokerService brokerService) {
        this.brokerService = brokerService;
    }
    
    @Override
    public long getLastProducerSequenceId(final ProducerId id) {
        return -1L;
    }
    
    static {
        LOG = LoggerFactory.getLogger(KahaPersistenceAdapter.class);
    }
}
