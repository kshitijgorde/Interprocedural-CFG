// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.amq;

import java.util.concurrent.CountedCompleter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Spliterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.LockSupport;
import sun.misc.Contended;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.IntBinaryOperator;
import java.util.function.ToIntBiFunction;
import java.util.function.LongBinaryOperator;
import java.util.function.ToLongBiFunction;
import java.util.function.DoubleBinaryOperator;
import java.util.function.ToDoubleBiFunction;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Enumeration;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import sun.misc.Unsafe;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.concurrent.ConcurrentMap;
import java.util.AbstractMap;
import org.slf4j.LoggerFactory;
import org.apache.activemq.command.ProducerId;
import org.apache.activemq.store.kahadaptor.KahaReferenceStoreAdapter;
import org.apache.activemq.usage.Usage;
import org.apache.activemq.command.JournalTransaction;
import org.apache.activemq.command.JournalTopicAck;
import org.apache.activemq.command.JournalQueueAck;
import org.apache.activemq.command.SubscriptionInfo;
import org.apache.activemq.command.Message;
import org.apache.activemq.filter.MessageEvaluationContext;
import org.apache.activemq.filter.NonCachedMessageEvaluationContext;
import org.apache.activemq.util.ByteSequence;
import org.apache.activemq.command.DataStructure;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.store.TransactionStore;
import org.apache.activemq.store.TopicReferenceStore;
import org.apache.activemq.store.TopicMessageStore;
import org.apache.activemq.store.ReferenceStore;
import org.apache.activemq.store.MessageStore;
import org.apache.activemq.command.ActiveMQDestination;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.activemq.kaha.impl.async.Location;
import org.apache.activemq.thread.Task;
import org.apache.activemq.util.IOExceptionSupport;
import java.util.Date;
import org.apache.activemq.command.JournalTrace;
import java.io.IOException;
import org.apache.activemq.util.IOHelper;
import org.apache.activemq.kaha.impl.index.hash.HashIndex;
import org.apache.activemq.openwire.OpenWireFormat;
import java.nio.channels.FileLock;
import java.io.RandomAccessFile;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.activemq.broker.BrokerService;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.CountDownLatch;
import org.apache.activemq.thread.TaskRunner;
import org.apache.activemq.usage.SystemUsage;
import org.apache.activemq.wireformat.WireFormat;
import org.apache.activemq.thread.TaskRunnerFactory;
import org.apache.activemq.store.ReferenceStoreAdapter;
import org.apache.activemq.kaha.impl.async.AsyncDataManager;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.command.ActiveMQQueue;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.activemq.thread.Scheduler;
import org.slf4j.Logger;
import org.apache.activemq.broker.BrokerServiceAware;
import org.apache.activemq.usage.UsageListener;
import org.apache.activemq.store.PersistenceAdapter;

public class AMQPersistenceAdapter implements PersistenceAdapter, UsageListener, BrokerServiceAware
{
    private static final Logger LOG;
    private Scheduler scheduler;
    private final ConcurrentHashMap<ActiveMQQueue, AMQMessageStore> queues;
    private final ConcurrentHashMap<ActiveMQTopic, AMQTopicMessageStore> topics;
    private static final String PROPERTY_PREFIX = "org.apache.activemq.store.amq";
    private static final boolean BROKEN_FILE_LOCK;
    private static final boolean DISABLE_LOCKING;
    private static final int JOURNAL_LOCKED_WAIT_DELAY = 10000;
    private AsyncDataManager asyncDataManager;
    private ReferenceStoreAdapter referenceStoreAdapter;
    private TaskRunnerFactory taskRunnerFactory;
    private WireFormat wireFormat;
    private SystemUsage usageManager;
    private long checkpointInterval;
    private int maxCheckpointMessageAddSize;
    private final AMQTransactionStore transactionStore;
    private TaskRunner checkpointTask;
    private CountDownLatch nextCheckpointCountDownLatch;
    private final AtomicBoolean started;
    private Runnable periodicCheckpointTask;
    private Runnable periodicCleanupTask;
    private boolean deleteAllMessages;
    private boolean syncOnWrite;
    private boolean syncOnTransaction;
    private String brokerName;
    private File directory;
    private File directoryArchive;
    private BrokerService brokerService;
    private final AtomicLong storeSize;
    private boolean persistentIndex;
    private boolean useNio;
    private boolean archiveDataLogs;
    private long cleanupInterval;
    private int maxFileLength;
    private int indexBinSize;
    private int indexKeySize;
    private int indexPageSize;
    private int indexMaxBinSize;
    private int indexLoadFactor;
    private int maxReferenceFileLength;
    private final Map<AMQMessageStore, Map<Integer, AtomicInteger>> dataFilesInProgress;
    private RandomAccessFile lockFile;
    private FileLock lock;
    private boolean disableLocking;
    private boolean failIfJournalIsLocked;
    private boolean lockLogged;
    private boolean lockAquired;
    private boolean recoverReferenceStore;
    private boolean forceRecoverReferenceStore;
    private boolean useDedicatedTaskRunner;
    private int journalThreadPriority;
    
    public AMQPersistenceAdapter() {
        this.queues = new ConcurrentHashMap<ActiveMQQueue, AMQMessageStore>();
        this.topics = new ConcurrentHashMap<ActiveMQTopic, AMQTopicMessageStore>();
        this.wireFormat = new OpenWireFormat();
        this.checkpointInterval = 20000L;
        this.maxCheckpointMessageAddSize = 4096;
        this.transactionStore = new AMQTransactionStore(this);
        this.nextCheckpointCountDownLatch = new CountDownLatch(1);
        this.started = new AtomicBoolean(false);
        this.syncOnTransaction = true;
        this.brokerName = "";
        this.storeSize = new AtomicLong();
        this.persistentIndex = true;
        this.useNio = true;
        this.archiveDataLogs = false;
        this.cleanupInterval = 30000L;
        this.maxFileLength = 33554432;
        this.indexBinSize = HashIndex.DEFAULT_BIN_SIZE;
        this.indexKeySize = HashIndex.DEFAULT_KEY_SIZE;
        this.indexPageSize = HashIndex.DEFAULT_PAGE_SIZE;
        this.indexMaxBinSize = HashIndex.MAXIMUM_CAPACITY;
        this.indexLoadFactor = HashIndex.DEFAULT_LOAD_FACTOR;
        this.maxReferenceFileLength = 2097152;
        this.dataFilesInProgress = new ConcurrentHashMap<AMQMessageStore, Map<Integer, AtomicInteger>>();
        this.disableLocking = AMQPersistenceAdapter.DISABLE_LOCKING;
        this.recoverReferenceStore = true;
        this.forceRecoverReferenceStore = false;
        this.useDedicatedTaskRunner = false;
        this.journalThreadPriority = 10;
    }
    
    public String getBrokerName() {
        return this.brokerName;
    }
    
    @Override
    public void setBrokerName(final String brokerName) {
        this.brokerName = brokerName;
        if (this.referenceStoreAdapter != null) {
            this.referenceStoreAdapter.setBrokerName(brokerName);
        }
    }
    
    public BrokerService getBrokerService() {
        return this.brokerService;
    }
    
    @Override
    public void setBrokerService(final BrokerService brokerService) {
        this.brokerService = brokerService;
    }
    
    @Override
    public synchronized void start() throws Exception {
        if (!this.started.compareAndSet(false, true)) {
            return;
        }
        if (this.directory == null) {
            if (this.brokerService != null) {
                this.directory = this.brokerService.getBrokerDataDirectory();
            }
            else {
                this.directory = new File(IOHelper.getDefaultDataDirectory(), IOHelper.toFileSystemSafeName(this.brokerName));
                (this.directory = new File(this.directory, "amqstore")).getAbsolutePath();
            }
        }
        if (this.directoryArchive == null) {
            this.directoryArchive = new File(this.directory, "archive");
        }
        if (this.brokerService != null) {
            this.taskRunnerFactory = this.brokerService.getTaskRunnerFactory();
            this.scheduler = this.brokerService.getScheduler();
        }
        else {
            this.taskRunnerFactory = new TaskRunnerFactory("AMQPersistenceAdaptor Task", this.getJournalThreadPriority(), true, 1000, this.isUseDedicatedTaskRunner());
            this.scheduler = new Scheduler("AMQPersistenceAdapter Scheduler");
        }
        IOHelper.mkdirs(this.directory);
        this.lockFile = new RandomAccessFile(new File(this.directory, "lock"), "rw");
        this.lock();
        AMQPersistenceAdapter.LOG.info("AMQStore starting using directory: " + this.directory);
        if (this.archiveDataLogs) {
            IOHelper.mkdirs(this.directoryArchive);
        }
        if (this.usageManager != null) {
            this.usageManager.getMemoryUsage().addUsageListener(this);
        }
        if (this.asyncDataManager == null) {
            this.asyncDataManager = this.createAsyncDataManager();
        }
        if (this.referenceStoreAdapter == null) {
            this.referenceStoreAdapter = this.createReferenceStoreAdapter();
        }
        this.referenceStoreAdapter.setDirectory(new File(this.directory, "kr-store"));
        this.referenceStoreAdapter.setBrokerName(this.getBrokerName());
        this.referenceStoreAdapter.setUsageManager(this.usageManager);
        this.referenceStoreAdapter.setMaxDataFileLength(this.getMaxReferenceFileLength());
        if (this.failIfJournalIsLocked) {
            this.asyncDataManager.lock();
        }
        else {
            while (true) {
                try {
                    this.asyncDataManager.lock();
                }
                catch (IOException e) {
                    AMQPersistenceAdapter.LOG.info("Journal is locked... waiting 10 seconds for the journal to be unlocked.", e);
                    try {
                        Thread.sleep(10000L);
                    }
                    catch (InterruptedException ex) {}
                    continue;
                }
                break;
            }
        }
        this.asyncDataManager.start();
        if (this.deleteAllMessages) {
            this.asyncDataManager.delete();
            try {
                final JournalTrace trace = new JournalTrace();
                trace.setMessage("DELETED " + new Date());
                final Location location = this.asyncDataManager.write(this.wireFormat.marshal(trace), false);
                this.asyncDataManager.setMark(location, true);
                AMQPersistenceAdapter.LOG.info("Journal deleted: ");
                this.deleteAllMessages = false;
            }
            catch (IOException e) {
                throw e;
            }
            catch (Throwable e2) {
                throw IOExceptionSupport.create(e2);
            }
            this.referenceStoreAdapter.deleteAllMessages();
        }
        this.referenceStoreAdapter.start();
        final Set<Integer> files = this.referenceStoreAdapter.getReferenceFileIdsInUse();
        AMQPersistenceAdapter.LOG.info("Active data files: " + files);
        this.checkpointTask = this.taskRunnerFactory.createTaskRunner(new Task() {
            @Override
            public boolean iterate() {
                AMQPersistenceAdapter.this.doCheckpoint();
                return false;
            }
        }, "ActiveMQ Journal Checkpoint Worker");
        this.createTransactionStore();
        if (this.isForceRecoverReferenceStore() || (this.isRecoverReferenceStore() && !this.referenceStoreAdapter.isStoreValid())) {
            AMQPersistenceAdapter.LOG.warn("The ReferenceStore is not valid - recovering ...");
            this.recover();
            AMQPersistenceAdapter.LOG.info("Finished recovering the ReferenceStore");
        }
        else {
            final Location location = this.writeTraceMessage("RECOVERED " + new Date(), true);
            this.asyncDataManager.setMark(location, true);
            this.getTransactionStore().setPreparedTransactions(this.referenceStoreAdapter.retrievePreparedState());
        }
        this.periodicCheckpointTask = new Runnable() {
            @Override
            public void run() {
                AMQPersistenceAdapter.this.checkpoint(false);
            }
        };
        this.scheduler.executePeriodically(this.periodicCheckpointTask, this.getCheckpointInterval());
        this.periodicCleanupTask = new Runnable() {
            @Override
            public void run() {
                AMQPersistenceAdapter.this.cleanup();
            }
        };
        this.scheduler.executePeriodically(this.periodicCleanupTask, this.getCleanupInterval());
        if (this.lockAquired && this.lockLogged) {
            AMQPersistenceAdapter.LOG.info("Aquired lock for AMQ Store" + this.getDirectory());
            if (this.brokerService != null) {
                this.brokerService.getBroker().nowMasterBroker();
            }
        }
    }
    
    @Override
    public void stop() throws Exception {
        if (!this.started.compareAndSet(true, false)) {
            return;
        }
        this.unlock();
        if (this.lockFile != null) {
            this.lockFile.close();
            this.lockFile = null;
        }
        this.usageManager.getMemoryUsage().removeUsageListener(this);
        synchronized (this) {
            this.scheduler.cancel(this.periodicCheckpointTask);
            this.scheduler.cancel(this.periodicCleanupTask);
        }
        for (final AMQMessageStore ms : this.queues.values()) {
            ms.stop();
        }
        for (final AMQTopicMessageStore ms2 : this.topics.values()) {
            ms2.stop();
        }
        this.checkpoint(true);
        synchronized (this) {
            this.checkpointTask.shutdown();
        }
        this.referenceStoreAdapter.savePreparedState(this.getTransactionStore().getPreparedTransactions());
        this.queues.clear();
        this.topics.clear();
        IOException firstException = null;
        this.referenceStoreAdapter.stop();
        this.referenceStoreAdapter = null;
        if (this.brokerService == null) {
            this.taskRunnerFactory.shutdown();
            this.scheduler.stop();
        }
        try {
            AMQPersistenceAdapter.LOG.debug("Journal close");
            this.asyncDataManager.close();
        }
        catch (Exception e) {
            firstException = IOExceptionSupport.create("Failed to close journals: " + e, e);
        }
        if (firstException != null) {
            throw firstException;
        }
    }
    
    @Override
    public void checkpoint(final boolean sync) {
        try {
            if (this.asyncDataManager == null) {
                throw new IllegalStateException("Journal is closed.");
            }
            CountDownLatch latch = null;
            synchronized (this) {
                latch = this.nextCheckpointCountDownLatch;
                this.checkpointTask.wakeup();
            }
            if (sync) {
                if (AMQPersistenceAdapter.LOG.isDebugEnabled()) {
                    AMQPersistenceAdapter.LOG.debug("Waitng for checkpoint to complete.");
                }
                latch.await();
            }
            this.referenceStoreAdapter.checkpoint(sync);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            AMQPersistenceAdapter.LOG.warn("Request to start checkpoint failed: " + e, e);
        }
        catch (IOException e2) {
            AMQPersistenceAdapter.LOG.error("checkpoint failed: " + e2, e2);
        }
    }
    
    public boolean doCheckpoint() {
        CountDownLatch latch = null;
        synchronized (this) {
            latch = this.nextCheckpointCountDownLatch;
            this.nextCheckpointCountDownLatch = new CountDownLatch(1);
        }
        try {
            if (AMQPersistenceAdapter.LOG.isDebugEnabled()) {
                AMQPersistenceAdapter.LOG.debug("Checkpoint started.");
            }
            Location newMark;
            final Location currentMark = newMark = this.asyncDataManager.getMark();
            for (final AMQMessageStore ms : this.queues.values()) {
                final Location mark = ms.getMark();
                if (mark != null && (newMark == null || mark.compareTo(newMark) > 0)) {
                    newMark = mark;
                }
            }
            for (final AMQTopicMessageStore ms2 : this.topics.values()) {
                final Location mark2 = ms2.getMark();
                if (mark2 != null && (newMark == null || mark2.compareTo(newMark) > 0)) {
                    newMark = mark2;
                }
            }
            try {
                if (newMark != currentMark) {
                    if (AMQPersistenceAdapter.LOG.isDebugEnabled()) {
                        AMQPersistenceAdapter.LOG.debug("Marking journal at: " + newMark);
                    }
                    this.asyncDataManager.setMark(newMark, false);
                    this.writeTraceMessage("CHECKPOINT " + new Date(), true);
                }
            }
            catch (Exception e) {
                AMQPersistenceAdapter.LOG.error("Failed to mark the Journal: " + e, e);
            }
            if (AMQPersistenceAdapter.LOG.isDebugEnabled()) {
                AMQPersistenceAdapter.LOG.debug("Checkpoint done.");
            }
        }
        finally {
            latch.countDown();
        }
        return true;
    }
    
    public void cleanup() {
        try {
            final Set<Integer> inProgress = new HashSet<Integer>();
            if (AMQPersistenceAdapter.LOG.isDebugEnabled()) {
                AMQPersistenceAdapter.LOG.debug("dataFilesInProgress.values: (" + this.dataFilesInProgress.values().size() + ") " + this.dataFilesInProgress.values());
            }
            for (final Map<Integer, AtomicInteger> set : this.dataFilesInProgress.values()) {
                inProgress.addAll(set.keySet());
            }
            Integer lastDataFile = this.asyncDataManager.getCurrentDataFileId();
            inProgress.add(lastDataFile);
            lastDataFile = this.asyncDataManager.getMark().getDataFileId();
            inProgress.addAll(this.referenceStoreAdapter.getReferenceFileIdsInUse());
            final Location lastActiveTx = this.transactionStore.checkpoint();
            if (lastActiveTx != null) {
                lastDataFile = Math.min(lastDataFile, lastActiveTx.getDataFileId());
            }
            AMQPersistenceAdapter.LOG.debug("lastDataFile: " + lastDataFile);
            this.asyncDataManager.consolidateDataFilesNotIn(inProgress, Integer.valueOf(lastDataFile - 1));
        }
        catch (IOException e) {
            AMQPersistenceAdapter.LOG.error("Could not cleanup data files: " + e, e);
        }
    }
    
    @Override
    public Set<ActiveMQDestination> getDestinations() {
        final Set<ActiveMQDestination> destinations = new HashSet<ActiveMQDestination>(this.referenceStoreAdapter.getDestinations());
        destinations.addAll(this.queues.keySet());
        destinations.addAll(this.topics.keySet());
        return destinations;
    }
    
    MessageStore createMessageStore(final ActiveMQDestination destination) throws IOException {
        if (destination.isQueue()) {
            return this.createQueueMessageStore((ActiveMQQueue)destination);
        }
        return this.createTopicMessageStore((ActiveMQTopic)destination);
    }
    
    @Override
    public MessageStore createQueueMessageStore(final ActiveMQQueue destination) throws IOException {
        AMQMessageStore store = this.queues.get(destination);
        if (store == null) {
            final ReferenceStore checkpointStore = this.referenceStoreAdapter.createQueueReferenceStore(destination);
            store = new AMQMessageStore(this, checkpointStore, destination);
            try {
                store.start();
            }
            catch (Exception e) {
                throw IOExceptionSupport.create(e);
            }
            this.queues.put(destination, store);
        }
        return store;
    }
    
    @Override
    public TopicMessageStore createTopicMessageStore(final ActiveMQTopic destinationName) throws IOException {
        AMQTopicMessageStore store = this.topics.get(destinationName);
        if (store == null) {
            final TopicReferenceStore checkpointStore = this.referenceStoreAdapter.createTopicReferenceStore(destinationName);
            store = new AMQTopicMessageStore(this, checkpointStore, destinationName);
            try {
                store.start();
            }
            catch (Exception e) {
                throw IOExceptionSupport.create(e);
            }
            this.topics.put(destinationName, store);
        }
        return store;
    }
    
    @Override
    public void removeQueueMessageStore(final ActiveMQQueue destination) {
        final AMQMessageStore store = this.queues.remove(destination);
        this.referenceStoreAdapter.removeQueueMessageStore(destination);
    }
    
    @Override
    public void removeTopicMessageStore(final ActiveMQTopic destination) {
        this.topics.remove(destination);
    }
    
    @Override
    public TransactionStore createTransactionStore() throws IOException {
        return this.transactionStore;
    }
    
    @Override
    public long getLastMessageBrokerSequenceId() throws IOException {
        return this.referenceStoreAdapter.getLastMessageBrokerSequenceId();
    }
    
    @Override
    public void beginTransaction(final ConnectionContext context) throws IOException {
        this.referenceStoreAdapter.beginTransaction(context);
    }
    
    @Override
    public void commitTransaction(final ConnectionContext context) throws IOException {
        this.referenceStoreAdapter.commitTransaction(context);
    }
    
    @Override
    public void rollbackTransaction(final ConnectionContext context) throws IOException {
        this.referenceStoreAdapter.rollbackTransaction(context);
    }
    
    public boolean isPersistentIndex() {
        return this.persistentIndex;
    }
    
    public void setPersistentIndex(final boolean persistentIndex) {
        this.persistentIndex = persistentIndex;
    }
    
    public DataStructure readCommand(final Location location) throws IOException {
        try {
            final ByteSequence packet = this.asyncDataManager.read(location);
            return (DataStructure)this.wireFormat.unmarshal(packet);
        }
        catch (IOException e) {
            throw this.createReadException(location, e);
        }
    }
    
    private void recover() throws IllegalStateException, IOException {
        this.referenceStoreAdapter.clearMessages();
        Location pos = null;
        int redoCounter = 0;
        AMQPersistenceAdapter.LOG.info("Journal Recovery Started from: " + this.asyncDataManager);
        final long start = System.currentTimeMillis();
        final ConnectionContext context = new ConnectionContext(new NonCachedMessageEvaluationContext());
        while ((pos = this.asyncDataManager.getNextLocation(pos)) != null) {
            final ByteSequence data = this.asyncDataManager.read(pos);
            final DataStructure c = (DataStructure)this.wireFormat.unmarshal(data);
            if (c instanceof Message) {
                final Message message = (Message)c;
                final AMQMessageStore store = (AMQMessageStore)this.createMessageStore(message.getDestination());
                if (message.isInTransaction()) {
                    this.transactionStore.addMessage(store, message, pos);
                }
                else {
                    if (!store.replayAddMessage(context, message, pos)) {
                        continue;
                    }
                    ++redoCounter;
                }
            }
            else {
                switch (c.getDataStructureType()) {
                    case 55: {
                        this.referenceStoreAdapter.recoverSubscription((SubscriptionInfo)c);
                        continue;
                    }
                    case 52: {
                        final JournalQueueAck command = (JournalQueueAck)c;
                        final AMQMessageStore store = (AMQMessageStore)this.createMessageStore(command.getDestination());
                        if (command.getMessageAck().isInTransaction()) {
                            this.transactionStore.removeMessage(store, command.getMessageAck(), pos);
                        }
                        else {
                            if (!store.replayRemoveMessage(context, command.getMessageAck())) {
                                continue;
                            }
                            ++redoCounter;
                        }
                        continue;
                    }
                    case 50: {
                        final JournalTopicAck command2 = (JournalTopicAck)c;
                        final AMQTopicMessageStore store2 = (AMQTopicMessageStore)this.createMessageStore(command2.getDestination());
                        if (command2.getTransactionId() != null) {
                            this.transactionStore.acknowledge(store2, command2, pos);
                        }
                        else {
                            if (!store2.replayAcknowledge(context, command2.getClientId(), command2.getSubscritionName(), command2.getMessageId())) {
                                continue;
                            }
                            ++redoCounter;
                        }
                        continue;
                    }
                    case 54: {
                        final JournalTransaction command3 = (JournalTransaction)c;
                        try {
                            switch (command3.getType()) {
                                case 1: {
                                    this.transactionStore.replayPrepare(command3.getTransactionId());
                                    continue;
                                }
                                case 2:
                                case 4: {
                                    final AMQTx tx = this.transactionStore.replayCommit(command3.getTransactionId(), command3.getWasPrepared());
                                    if (tx == null) {
                                        continue;
                                    }
                                    tx.getOperations();
                                    for (final AMQTxOperation op : tx.getOperations()) {
                                        if (op.replay(this, context)) {
                                            ++redoCounter;
                                        }
                                    }
                                    continue;
                                }
                                case 3:
                                case 5: {
                                    this.transactionStore.replayRollback(command3.getTransactionId());
                                    continue;
                                }
                                default: {
                                    throw new IOException("Invalid journal command type: " + command3.getType());
                                }
                            }
                        }
                        catch (IOException e) {
                            AMQPersistenceAdapter.LOG.error("Recovery Failure: Could not replay: " + c + ", reason: " + e, e);
                        }
                        continue;
                    }
                    case 53: {
                        final JournalTrace trace = (JournalTrace)c;
                        AMQPersistenceAdapter.LOG.debug("TRACE Entry: " + trace.getMessage());
                        continue;
                    }
                    default: {
                        AMQPersistenceAdapter.LOG.error("Unknown type of record in transaction log which will be discarded: " + c);
                        continue;
                    }
                }
            }
        }
        final Location location = this.writeTraceMessage("RECOVERED " + new Date(), true);
        this.asyncDataManager.setMark(location, true);
        final long end = System.currentTimeMillis();
        AMQPersistenceAdapter.LOG.info("Recovered " + redoCounter + " operations from redo log in " + (end - start) / 1000.0f + " seconds.");
    }
    
    private IOException createReadException(final Location location, final Exception e) {
        return IOExceptionSupport.create("Failed to read to journal for: " + location + ". Reason: " + e, e);
    }
    
    protected IOException createWriteException(final DataStructure packet, final Exception e) {
        return IOExceptionSupport.create("Failed to write to journal for: " + packet + ". Reason: " + e, e);
    }
    
    protected IOException createWriteException(final String command, final Exception e) {
        return IOExceptionSupport.create("Failed to write to journal for command: " + command + ". Reason: " + e, e);
    }
    
    protected IOException createRecoveryFailedException(final Exception e) {
        return IOExceptionSupport.create("Failed to recover from journal. Reason: " + e, e);
    }
    
    public Location writeCommand(final DataStructure command, final boolean syncHint) throws IOException {
        return this.writeCommand(command, syncHint, false);
    }
    
    public Location writeCommand(final DataStructure command, final boolean syncHint, final boolean forceSync) throws IOException {
        try {
            return this.asyncDataManager.write(this.wireFormat.marshal(command), forceSync || (syncHint && this.syncOnWrite));
        }
        catch (IOException ioe) {
            AMQPersistenceAdapter.LOG.error("Failed to write command: " + command + ". Reason: " + ioe, ioe);
            this.brokerService.handleIOException(ioe);
            throw ioe;
        }
    }
    
    private Location writeTraceMessage(final String message, final boolean sync) throws IOException {
        final JournalTrace trace = new JournalTrace();
        trace.setMessage(message);
        return this.writeCommand(trace, sync);
    }
    
    @Override
    public void onUsageChanged(final Usage usage, int oldPercentUsage, int newPercentUsage) {
        newPercentUsage = newPercentUsage / 10 * 10;
        oldPercentUsage = oldPercentUsage / 10 * 10;
        if (newPercentUsage >= 70 && oldPercentUsage < newPercentUsage) {
            this.checkpoint(false);
        }
    }
    
    public AMQTransactionStore getTransactionStore() {
        return this.transactionStore;
    }
    
    @Override
    public synchronized void deleteAllMessages() throws IOException {
        this.deleteAllMessages = true;
    }
    
    @Override
    public String toString() {
        return "AMQPersistenceAdapter(" + this.directory + ")";
    }
    
    protected AsyncDataManager createAsyncDataManager() {
        final AsyncDataManager manager = new AsyncDataManager(this.storeSize);
        manager.setDirectory(new File(this.directory, "journal"));
        manager.setDirectoryArchive(this.getDirectoryArchive());
        manager.setArchiveDataLogs(this.isArchiveDataLogs());
        manager.setMaxFileLength(this.maxFileLength);
        manager.setUseNio(this.useNio);
        return manager;
    }
    
    protected KahaReferenceStoreAdapter createReferenceStoreAdapter() throws IOException {
        final KahaReferenceStoreAdapter adaptor = new KahaReferenceStoreAdapter(this.storeSize);
        adaptor.setPersistentIndex(this.isPersistentIndex());
        adaptor.setIndexBinSize(this.getIndexBinSize());
        adaptor.setIndexKeySize(this.getIndexKeySize());
        adaptor.setIndexPageSize(this.getIndexPageSize());
        adaptor.setIndexMaxBinSize(this.getIndexMaxBinSize());
        adaptor.setIndexLoadFactor(this.getIndexLoadFactor());
        return adaptor;
    }
    
    public AsyncDataManager getAsyncDataManager() {
        return this.asyncDataManager;
    }
    
    public void setAsyncDataManager(final AsyncDataManager asyncDataManager) {
        this.asyncDataManager = asyncDataManager;
    }
    
    public ReferenceStoreAdapter getReferenceStoreAdapter() {
        return this.referenceStoreAdapter;
    }
    
    public TaskRunnerFactory getTaskRunnerFactory() {
        return this.taskRunnerFactory;
    }
    
    public void setTaskRunnerFactory(final TaskRunnerFactory taskRunnerFactory) {
        this.taskRunnerFactory = taskRunnerFactory;
    }
    
    public WireFormat getWireFormat() {
        return this.wireFormat;
    }
    
    public void setWireFormat(final WireFormat wireFormat) {
        this.wireFormat = wireFormat;
    }
    
    public SystemUsage getUsageManager() {
        return this.usageManager;
    }
    
    @Override
    public void setUsageManager(final SystemUsage usageManager) {
        this.usageManager = usageManager;
    }
    
    public int getMaxCheckpointMessageAddSize() {
        return this.maxCheckpointMessageAddSize;
    }
    
    public void setMaxCheckpointMessageAddSize(final int maxCheckpointMessageAddSize) {
        this.maxCheckpointMessageAddSize = maxCheckpointMessageAddSize;
    }
    
    public synchronized File getDirectory() {
        return this.directory;
    }
    
    @Override
    public synchronized void setDirectory(final File directory) {
        this.directory = directory;
    }
    
    public boolean isSyncOnWrite() {
        return this.syncOnWrite;
    }
    
    public void setSyncOnWrite(final boolean syncOnWrite) {
        this.syncOnWrite = syncOnWrite;
    }
    
    public boolean isSyncOnTransaction() {
        return this.syncOnTransaction;
    }
    
    public void setSyncOnTransaction(final boolean syncOnTransaction) {
        this.syncOnTransaction = syncOnTransaction;
    }
    
    public void setReferenceStoreAdapter(final ReferenceStoreAdapter referenceStoreAdapter) {
        this.referenceStoreAdapter = referenceStoreAdapter;
    }
    
    @Override
    public long size() {
        return this.storeSize.get();
    }
    
    public boolean isUseNio() {
        return this.useNio;
    }
    
    public void setUseNio(final boolean useNio) {
        this.useNio = useNio;
    }
    
    public int getMaxFileLength() {
        return this.maxFileLength;
    }
    
    public void setMaxFileLength(final int maxFileLength) {
        this.maxFileLength = maxFileLength;
    }
    
    public long getCleanupInterval() {
        return this.cleanupInterval;
    }
    
    public void setCleanupInterval(final long cleanupInterval) {
        this.cleanupInterval = cleanupInterval;
    }
    
    public long getCheckpointInterval() {
        return this.checkpointInterval;
    }
    
    public void setCheckpointInterval(final long checkpointInterval) {
        this.checkpointInterval = checkpointInterval;
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
    
    public int getIndexMaxBinSize() {
        return this.indexMaxBinSize;
    }
    
    public void setIndexMaxBinSize(final int maxBinSize) {
        this.indexMaxBinSize = maxBinSize;
    }
    
    public void setIndexPageSize(final int indexPageSize) {
        this.indexPageSize = indexPageSize;
    }
    
    public void setIndexLoadFactor(final int factor) {
        this.indexLoadFactor = factor;
    }
    
    public int getIndexLoadFactor() {
        return this.indexLoadFactor;
    }
    
    public int getMaxReferenceFileLength() {
        return this.maxReferenceFileLength;
    }
    
    public void setMaxReferenceFileLength(final int maxReferenceFileLength) {
        this.maxReferenceFileLength = maxReferenceFileLength;
    }
    
    public File getDirectoryArchive() {
        return this.directoryArchive;
    }
    
    public void setDirectoryArchive(final File directoryArchive) {
        this.directoryArchive = directoryArchive;
    }
    
    public boolean isArchiveDataLogs() {
        return this.archiveDataLogs;
    }
    
    public void setArchiveDataLogs(final boolean archiveDataLogs) {
        this.archiveDataLogs = archiveDataLogs;
    }
    
    public boolean isDisableLocking() {
        return this.disableLocking;
    }
    
    public void setDisableLocking(final boolean disableLocking) {
        this.disableLocking = disableLocking;
    }
    
    public boolean isRecoverReferenceStore() {
        return this.recoverReferenceStore;
    }
    
    public void setRecoverReferenceStore(final boolean recoverReferenceStore) {
        this.recoverReferenceStore = recoverReferenceStore;
    }
    
    public boolean isForceRecoverReferenceStore() {
        return this.forceRecoverReferenceStore;
    }
    
    public void setForceRecoverReferenceStore(final boolean forceRecoverReferenceStore) {
        this.forceRecoverReferenceStore = forceRecoverReferenceStore;
    }
    
    public boolean isUseDedicatedTaskRunner() {
        return this.useDedicatedTaskRunner;
    }
    
    public void setUseDedicatedTaskRunner(final boolean useDedicatedTaskRunner) {
        this.useDedicatedTaskRunner = useDedicatedTaskRunner;
    }
    
    public int getJournalThreadPriority() {
        return this.journalThreadPriority;
    }
    
    public void setJournalThreadPriority(final int journalThreadPriority) {
        this.journalThreadPriority = journalThreadPriority;
    }
    
    protected void addInProgressDataFile(final AMQMessageStore store, final int dataFileId) {
        Map<Integer, AtomicInteger> map = this.dataFilesInProgress.get(store);
        if (map == null) {
            map = new ConcurrentHashMap<Integer, AtomicInteger>();
            this.dataFilesInProgress.put(store, map);
        }
        AtomicInteger count = map.get(dataFileId);
        if (count == null) {
            count = new AtomicInteger(0);
            map.put(dataFileId, count);
        }
        count.incrementAndGet();
    }
    
    protected void removeInProgressDataFile(final AMQMessageStore store, final int dataFileId) {
        final Map<Integer, AtomicInteger> map = this.dataFilesInProgress.get(store);
        if (map != null) {
            final AtomicInteger count = map.get(dataFileId);
            if (count != null) {
                final int newCount = count.decrementAndGet();
                if (newCount <= 0) {
                    map.remove(dataFileId);
                }
            }
            if (map.isEmpty()) {
                this.dataFilesInProgress.remove(store);
            }
        }
    }
    
    protected void lock() throws Exception {
        this.lockLogged = false;
        this.lockAquired = false;
        do {
            if (this.doLock()) {
                this.lockAquired = true;
            }
            else {
                if (!this.lockLogged) {
                    AMQPersistenceAdapter.LOG.warn("Waiting to Lock the Store " + this.getDirectory());
                    this.lockLogged = true;
                }
                Thread.sleep(1000L);
            }
        } while (!this.lockAquired && !this.disableLocking);
    }
    
    private synchronized void unlock() throws IOException {
        if (!this.disableLocking && null != this.lock) {
            System.getProperties().remove(this.getPropertyKey());
            System.clearProperty(this.getPropertyKey());
            assert System.getProperty(this.getPropertyKey()) == null;
            if (this.lock.isValid()) {
                this.lock.release();
                this.lock.channel().close();
            }
            this.lock = null;
        }
    }
    
    protected boolean doLock() throws IOException {
        boolean result = true;
        if (!this.disableLocking && this.directory != null && this.lock == null) {
            final String key = this.getPropertyKey();
            final String property = System.getProperty(key);
            if (null == property) {
                if (!AMQPersistenceAdapter.BROKEN_FILE_LOCK) {
                    this.lock = this.lockFile.getChannel().tryLock(0L, this.lockFile.getChannel().size(), false);
                    if (this.lock == null) {
                        result = false;
                    }
                    else {
                        System.setProperty(key, new Date().toString());
                    }
                }
            }
            else {
                result = false;
            }
        }
        return result;
    }
    
    private String getPropertyKey() throws IOException {
        return this.getClass().getName() + ".lock." + this.directory.getCanonicalPath();
    }
    
    @Override
    public long getLastProducerSequenceId(final ProducerId id) {
        return -1L;
    }
    
    static {
        LOG = LoggerFactory.getLogger(AMQPersistenceAdapter.class);
        BROKEN_FILE_LOCK = "true".equals(System.getProperty("org.apache.activemq.store.amq.FileLockBroken", "false"));
        DISABLE_LOCKING = "true".equals(System.getProperty("org.apache.activemq.store.amq.DisableLocking", "false"));
    }
}
