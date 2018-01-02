// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.amq;

import org.apache.activemq.util.IOHelper;
import org.apache.activemq.store.PersistenceAdapter;
import org.apache.activemq.kaha.impl.index.hash.HashIndex;
import org.apache.activemq.thread.TaskRunnerFactory;
import org.apache.activemq.store.ReferenceStoreAdapter;
import java.io.File;
import org.apache.activemq.store.PersistenceAdapterFactory;

public class AMQPersistenceAdapterFactory implements PersistenceAdapterFactory
{
    static final int DEFAULT_MAX_REFERNCE_FILE_LENGTH = 2097152;
    private File dataDirectory;
    private int journalThreadPriority;
    private String brokerName;
    private ReferenceStoreAdapter referenceStoreAdapter;
    private boolean syncOnWrite;
    private boolean syncOnTransaction;
    private boolean persistentIndex;
    private boolean useNio;
    private int maxFileLength;
    private long cleanupInterval;
    private int indexBinSize;
    private int indexKeySize;
    private int indexPageSize;
    private int indexMaxBinSize;
    private int indexLoadFactor;
    private int maxReferenceFileLength;
    private boolean recoverReferenceStore;
    private boolean forceRecoverReferenceStore;
    private long checkpointInterval;
    private boolean useDedicatedTaskRunner;
    private TaskRunnerFactory taskRunnerFactory;
    
    public AMQPersistenceAdapterFactory() {
        this.journalThreadPriority = 10;
        this.brokerName = "localhost";
        this.syncOnTransaction = true;
        this.persistentIndex = true;
        this.useNio = true;
        this.maxFileLength = 33554432;
        this.cleanupInterval = 30000L;
        this.indexBinSize = HashIndex.DEFAULT_BIN_SIZE;
        this.indexKeySize = HashIndex.DEFAULT_KEY_SIZE;
        this.indexPageSize = HashIndex.DEFAULT_PAGE_SIZE;
        this.indexMaxBinSize = HashIndex.MAXIMUM_CAPACITY;
        this.indexLoadFactor = HashIndex.DEFAULT_LOAD_FACTOR;
        this.maxReferenceFileLength = 2097152;
        this.recoverReferenceStore = true;
        this.forceRecoverReferenceStore = false;
        this.checkpointInterval = 20000L;
    }
    
    @Override
    public PersistenceAdapter createPersistenceAdapter() {
        final AMQPersistenceAdapter result = new AMQPersistenceAdapter();
        result.setDirectory(this.getDataDirectory());
        result.setTaskRunnerFactory(this.getTaskRunnerFactory());
        result.setBrokerName(this.getBrokerName());
        result.setSyncOnWrite(this.isSyncOnWrite());
        result.setPersistentIndex(this.isPersistentIndex());
        result.setReferenceStoreAdapter(this.getReferenceStoreAdapter());
        result.setUseNio(this.isUseNio());
        result.setMaxFileLength(this.getMaxFileLength());
        result.setCleanupInterval(this.getCleanupInterval());
        result.setCheckpointInterval(this.getCheckpointInterval());
        result.setIndexBinSize(this.getIndexBinSize());
        result.setIndexKeySize(this.getIndexKeySize());
        result.setIndexPageSize(this.getIndexPageSize());
        result.setIndexMaxBinSize(this.getIndexMaxBinSize());
        result.setIndexLoadFactor(this.getIndexLoadFactor());
        result.setMaxReferenceFileLength(this.getMaxReferenceFileLength());
        result.setForceRecoverReferenceStore(this.isForceRecoverReferenceStore());
        result.setRecoverReferenceStore(this.isRecoverReferenceStore());
        result.setUseDedicatedTaskRunner(this.isUseDedicatedTaskRunner());
        result.setJournalThreadPriority(this.getJournalThreadPriority());
        return result;
    }
    
    public long getCleanupInterval() {
        return this.cleanupInterval;
    }
    
    public void setCleanupInterval(final long val) {
        this.cleanupInterval = val;
    }
    
    public File getDataDirectory() {
        if (this.dataDirectory == null) {
            this.dataDirectory = new File(IOHelper.getDefaultDataDirectory(), IOHelper.toFileSystemSafeName(this.brokerName));
        }
        return this.dataDirectory;
    }
    
    public void setDataDirectory(final File dataDirectory) {
        this.dataDirectory = dataDirectory;
    }
    
    public boolean isUseDedicatedTaskRunner() {
        return this.useDedicatedTaskRunner;
    }
    
    public void setUseDedicatedTaskRunner(final boolean useDedicatedTaskRunner) {
        this.useDedicatedTaskRunner = useDedicatedTaskRunner;
    }
    
    public TaskRunnerFactory getTaskRunnerFactory() {
        return this.taskRunnerFactory;
    }
    
    public void setTaskRunnerFactory(final TaskRunnerFactory taskRunnerFactory) {
        this.taskRunnerFactory = taskRunnerFactory;
    }
    
    public int getJournalThreadPriority() {
        return this.journalThreadPriority;
    }
    
    public void setJournalThreadPriority(final int journalThreadPriority) {
        this.journalThreadPriority = journalThreadPriority;
    }
    
    public String getBrokerName() {
        return this.brokerName;
    }
    
    public void setBrokerName(final String brokerName) {
        this.brokerName = brokerName;
    }
    
    public ReferenceStoreAdapter getReferenceStoreAdapter() {
        return this.referenceStoreAdapter;
    }
    
    public void setReferenceStoreAdapter(final ReferenceStoreAdapter referenceStoreAdapter) {
        this.referenceStoreAdapter = referenceStoreAdapter;
    }
    
    public boolean isPersistentIndex() {
        return this.persistentIndex;
    }
    
    public void setPersistentIndex(final boolean persistentIndex) {
        this.persistentIndex = persistentIndex;
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
    
    public void setIndexMaxBinSize(final int indexMaxBinSize) {
        this.indexMaxBinSize = indexMaxBinSize;
    }
    
    public int getIndexLoadFactor() {
        return this.indexLoadFactor;
    }
    
    public void setIndexLoadFactor(final int indexLoadFactor) {
        this.indexLoadFactor = indexLoadFactor;
    }
    
    public int getMaxReferenceFileLength() {
        return this.maxReferenceFileLength;
    }
    
    public void setMaxReferenceFileLength(final int maxReferenceFileLength) {
        this.maxReferenceFileLength = maxReferenceFileLength;
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
    
    public long getCheckpointInterval() {
        return this.checkpointInterval;
    }
    
    public void setCheckpointInterval(final long checkpointInterval) {
        this.checkpointInterval = checkpointInterval;
    }
}
