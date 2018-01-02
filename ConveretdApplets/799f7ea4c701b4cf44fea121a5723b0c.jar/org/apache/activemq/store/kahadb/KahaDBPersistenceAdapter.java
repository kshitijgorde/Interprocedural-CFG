// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.kahadb;

import org.apache.activemq.broker.BrokerService;
import java.io.File;
import org.apache.activemq.usage.SystemUsage;
import org.apache.activemq.command.ProducerId;
import org.apache.activemq.command.ActiveMQDestination;
import java.util.Set;
import org.apache.activemq.store.TransactionStore;
import org.apache.activemq.store.TopicMessageStore;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.store.MessageStore;
import org.apache.activemq.command.ActiveMQQueue;
import java.io.IOException;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.broker.BrokerServiceAware;
import org.apache.activemq.store.PersistenceAdapter;

public class KahaDBPersistenceAdapter implements PersistenceAdapter, BrokerServiceAware
{
    private final KahaDBStore letter;
    
    public KahaDBPersistenceAdapter() {
        this.letter = new KahaDBStore();
    }
    
    @Override
    public void beginTransaction(final ConnectionContext context) throws IOException {
        this.letter.beginTransaction(context);
    }
    
    @Override
    public void checkpoint(final boolean sync) throws IOException {
        this.letter.checkpoint(sync);
    }
    
    @Override
    public void commitTransaction(final ConnectionContext context) throws IOException {
        this.letter.commitTransaction(context);
    }
    
    @Override
    public MessageStore createQueueMessageStore(final ActiveMQQueue destination) throws IOException {
        return this.letter.createQueueMessageStore(destination);
    }
    
    @Override
    public TopicMessageStore createTopicMessageStore(final ActiveMQTopic destination) throws IOException {
        return this.letter.createTopicMessageStore(destination);
    }
    
    @Override
    public TransactionStore createTransactionStore() throws IOException {
        return this.letter.createTransactionStore();
    }
    
    @Override
    public void deleteAllMessages() throws IOException {
        this.letter.deleteAllMessages();
    }
    
    @Override
    public Set<ActiveMQDestination> getDestinations() {
        return this.letter.getDestinations();
    }
    
    @Override
    public long getLastMessageBrokerSequenceId() throws IOException {
        return this.letter.getLastMessageBrokerSequenceId();
    }
    
    @Override
    public long getLastProducerSequenceId(final ProducerId id) throws IOException {
        return this.letter.getLastProducerSequenceId(id);
    }
    
    @Override
    public void removeQueueMessageStore(final ActiveMQQueue destination) {
        this.letter.removeQueueMessageStore(destination);
    }
    
    @Override
    public void removeTopicMessageStore(final ActiveMQTopic destination) {
        this.letter.removeTopicMessageStore(destination);
    }
    
    @Override
    public void rollbackTransaction(final ConnectionContext context) throws IOException {
        this.letter.rollbackTransaction(context);
    }
    
    @Override
    public void setBrokerName(final String brokerName) {
        this.letter.setBrokerName(brokerName);
    }
    
    @Override
    public void setUsageManager(final SystemUsage usageManager) {
        this.letter.setUsageManager(usageManager);
    }
    
    @Override
    public long size() {
        return this.letter.size();
    }
    
    @Override
    public void start() throws Exception {
        this.letter.start();
    }
    
    @Override
    public void stop() throws Exception {
        this.letter.stop();
    }
    
    public int getJournalMaxFileLength() {
        return this.letter.getJournalMaxFileLength();
    }
    
    public void setJournalMaxFileLength(final int journalMaxFileLength) {
        this.letter.setJournalMaxFileLength(journalMaxFileLength);
    }
    
    public void setMaxFailoverProducersToTrack(final int maxFailoverProducersToTrack) {
        this.letter.setMaxFailoverProducersToTrack(maxFailoverProducersToTrack);
    }
    
    public int getMaxFailoverProducersToTrack() {
        return this.letter.getMaxFailoverProducersToTrack();
    }
    
    public void setFailoverProducersAuditDepth(final int failoverProducersAuditDepth) {
        this.letter.setFailoverProducersAuditDepth(failoverProducersAuditDepth);
    }
    
    public int getFailoverProducersAuditDepth() {
        return this.getFailoverProducersAuditDepth();
    }
    
    public long getCheckpointInterval() {
        return this.letter.getCheckpointInterval();
    }
    
    public void setCheckpointInterval(final long checkpointInterval) {
        this.letter.setCheckpointInterval(checkpointInterval);
    }
    
    public long getCleanupInterval() {
        return this.letter.getCleanupInterval();
    }
    
    public void setCleanupInterval(final long cleanupInterval) {
        this.letter.setCleanupInterval(cleanupInterval);
    }
    
    public int getIndexWriteBatchSize() {
        return this.letter.getIndexWriteBatchSize();
    }
    
    public void setIndexWriteBatchSize(final int indexWriteBatchSize) {
        this.letter.setIndexWriteBatchSize(indexWriteBatchSize);
    }
    
    public int getJournalMaxWriteBatchSize() {
        return this.letter.getJournalMaxWriteBatchSize();
    }
    
    public void setJournalMaxWriteBatchSize(final int journalMaxWriteBatchSize) {
        this.letter.setJournalMaxWriteBatchSize(journalMaxWriteBatchSize);
    }
    
    public boolean isEnableIndexWriteAsync() {
        return this.letter.isEnableIndexWriteAsync();
    }
    
    public void setEnableIndexWriteAsync(final boolean enableIndexWriteAsync) {
        this.letter.setEnableIndexWriteAsync(enableIndexWriteAsync);
    }
    
    public File getDirectory() {
        return this.letter.getDirectory();
    }
    
    @Override
    public void setDirectory(final File dir) {
        this.letter.setDirectory(dir);
    }
    
    public boolean isEnableJournalDiskSyncs() {
        return this.letter.isEnableJournalDiskSyncs();
    }
    
    public void setEnableJournalDiskSyncs(final boolean enableJournalDiskSyncs) {
        this.letter.setEnableJournalDiskSyncs(enableJournalDiskSyncs);
    }
    
    public int getIndexCacheSize() {
        return this.letter.getIndexCacheSize();
    }
    
    public void setIndexCacheSize(final int indexCacheSize) {
        this.letter.setIndexCacheSize(indexCacheSize);
    }
    
    public boolean isIgnoreMissingJournalfiles() {
        return this.letter.isIgnoreMissingJournalfiles();
    }
    
    public void setIgnoreMissingJournalfiles(final boolean ignoreMissingJournalfiles) {
        this.letter.setIgnoreMissingJournalfiles(ignoreMissingJournalfiles);
    }
    
    public boolean isChecksumJournalFiles() {
        return this.letter.isChecksumJournalFiles();
    }
    
    public boolean isCheckForCorruptJournalFiles() {
        return this.letter.isCheckForCorruptJournalFiles();
    }
    
    public void setChecksumJournalFiles(final boolean checksumJournalFiles) {
        this.letter.setChecksumJournalFiles(checksumJournalFiles);
    }
    
    public void setCheckForCorruptJournalFiles(final boolean checkForCorruptJournalFiles) {
        this.letter.setCheckForCorruptJournalFiles(checkForCorruptJournalFiles);
    }
    
    @Override
    public void setBrokerService(final BrokerService brokerService) {
        this.letter.setBrokerService(brokerService);
    }
    
    public boolean isArchiveDataLogs() {
        return this.letter.isArchiveDataLogs();
    }
    
    public void setArchiveDataLogs(final boolean archiveDataLogs) {
        this.letter.setArchiveDataLogs(archiveDataLogs);
    }
    
    public File getDirectoryArchive() {
        return this.letter.getDirectoryArchive();
    }
    
    public void setDirectoryArchive(final File directoryArchive) {
        this.letter.setDirectoryArchive(directoryArchive);
    }
    
    public boolean isConcurrentStoreAndDispatchQueues() {
        return this.letter.isConcurrentStoreAndDispatchQueues();
    }
    
    public void setConcurrentStoreAndDispatchQueues(final boolean concurrentStoreAndDispatch) {
        this.letter.setConcurrentStoreAndDispatchQueues(concurrentStoreAndDispatch);
    }
    
    public boolean isConcurrentStoreAndDispatchTopics() {
        return this.letter.isConcurrentStoreAndDispatchTopics();
    }
    
    public void setConcurrentStoreAndDispatchTopics(final boolean concurrentStoreAndDispatch) {
        this.letter.setConcurrentStoreAndDispatchTopics(concurrentStoreAndDispatch);
    }
    
    public int getMaxAsyncJobs() {
        return this.letter.getMaxAsyncJobs();
    }
    
    public void setMaxAsyncJobs(final int maxAsyncJobs) {
        this.letter.setMaxAsyncJobs(maxAsyncJobs);
    }
    
    public int getDatabaseLockedWaitDelay() {
        return this.letter.getDatabaseLockedWaitDelay();
    }
    
    public void setDatabaseLockedWaitDelay(final int databaseLockedWaitDelay) {
        this.letter.setDatabaseLockedWaitDelay(databaseLockedWaitDelay);
    }
    
    public boolean getForceRecoverIndex() {
        return this.letter.getForceRecoverIndex();
    }
    
    public void setForceRecoverIndex(final boolean forceRecoverIndex) {
        this.letter.setForceRecoverIndex(forceRecoverIndex);
    }
    
    public KahaDBStore getStore() {
        return this.letter;
    }
    
    @Override
    public String toString() {
        final String path = (this.getDirectory() != null) ? this.getDirectory().getAbsolutePath() : "DIRECTORY_NOT_SET";
        return "KahaDBPersistenceAdapter[" + path + "]";
    }
}
