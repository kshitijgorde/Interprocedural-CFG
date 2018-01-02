// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.journal;

import org.slf4j.LoggerFactory;
import org.apache.activeio.journal.active.JournalLockedException;
import org.apache.activeio.journal.active.JournalImpl;
import org.apache.activemq.store.jdbc.Statements;
import org.apache.activemq.store.jdbc.JDBCAdapter;
import java.io.IOException;
import org.apache.activemq.store.PersistenceAdapter;
import org.apache.activemq.store.jdbc.JDBCPersistenceAdapter;
import java.io.File;
import org.apache.activeio.journal.Journal;
import org.apache.activemq.thread.TaskRunnerFactory;
import org.slf4j.Logger;
import org.apache.activemq.store.PersistenceAdapterFactory;
import org.apache.activemq.store.jdbc.DataSourceSupport;

public class JournalPersistenceAdapterFactory extends DataSourceSupport implements PersistenceAdapterFactory
{
    private static final int JOURNAL_LOCKED_WAIT_DELAY = 10000;
    private static final Logger LOG;
    private int journalLogFileSize;
    private int journalLogFiles;
    private TaskRunnerFactory taskRunnerFactory;
    private Journal journal;
    private boolean useJournal;
    private boolean useQuickJournal;
    private File journalArchiveDirectory;
    private boolean failIfJournalIsLocked;
    private int journalThreadPriority;
    private JDBCPersistenceAdapter jdbcPersistenceAdapter;
    private boolean useDedicatedTaskRunner;
    
    public JournalPersistenceAdapterFactory() {
        this.journalLogFileSize = 20971520;
        this.journalLogFiles = 2;
        this.useJournal = true;
        this.journalThreadPriority = 10;
        this.jdbcPersistenceAdapter = new JDBCPersistenceAdapter();
    }
    
    @Override
    public PersistenceAdapter createPersistenceAdapter() throws IOException {
        this.jdbcPersistenceAdapter.setDataSource(this.getDataSource());
        if (!this.useJournal) {
            return this.jdbcPersistenceAdapter;
        }
        return new JournalPersistenceAdapter(this.getJournal(), this.jdbcPersistenceAdapter, this.getTaskRunnerFactory());
    }
    
    public int getJournalLogFiles() {
        return this.journalLogFiles;
    }
    
    public void setJournalLogFiles(final int journalLogFiles) {
        this.journalLogFiles = journalLogFiles;
    }
    
    public int getJournalLogFileSize() {
        return this.journalLogFileSize;
    }
    
    public void setJournalLogFileSize(final int journalLogFileSize) {
        this.journalLogFileSize = journalLogFileSize;
    }
    
    public JDBCPersistenceAdapter getJdbcAdapter() {
        return this.jdbcPersistenceAdapter;
    }
    
    public void setJdbcAdapter(final JDBCPersistenceAdapter jdbcAdapter) {
        this.jdbcPersistenceAdapter = jdbcAdapter;
    }
    
    public boolean isUseJournal() {
        return this.useJournal;
    }
    
    public void setUseJournal(final boolean useJournal) {
        this.useJournal = useJournal;
    }
    
    public boolean isUseDedicatedTaskRunner() {
        return this.useDedicatedTaskRunner;
    }
    
    public void setUseDedicatedTaskRunner(final boolean useDedicatedTaskRunner) {
        this.useDedicatedTaskRunner = useDedicatedTaskRunner;
    }
    
    public TaskRunnerFactory getTaskRunnerFactory() {
        if (this.taskRunnerFactory == null) {
            this.taskRunnerFactory = new TaskRunnerFactory("Persistence Adaptor Task", this.journalThreadPriority, true, 1000, this.isUseDedicatedTaskRunner());
        }
        return this.taskRunnerFactory;
    }
    
    public void setTaskRunnerFactory(final TaskRunnerFactory taskRunnerFactory) {
        this.taskRunnerFactory = taskRunnerFactory;
    }
    
    public Journal getJournal() throws IOException {
        if (this.journal == null) {
            this.createJournal();
        }
        return this.journal;
    }
    
    public void setJournal(final Journal journal) {
        this.journal = journal;
    }
    
    public File getJournalArchiveDirectory() {
        if (this.journalArchiveDirectory == null && this.useQuickJournal) {
            this.journalArchiveDirectory = new File(this.getDataDirectoryFile(), "journal");
        }
        return this.journalArchiveDirectory;
    }
    
    public void setJournalArchiveDirectory(final File journalArchiveDirectory) {
        this.journalArchiveDirectory = journalArchiveDirectory;
    }
    
    public boolean isUseQuickJournal() {
        return this.useQuickJournal;
    }
    
    public void setUseQuickJournal(final boolean useQuickJournal) {
        this.useQuickJournal = useQuickJournal;
    }
    
    public JDBCAdapter getAdapter() throws IOException {
        return this.jdbcPersistenceAdapter.getAdapter();
    }
    
    public void setAdapter(final JDBCAdapter adapter) {
        this.jdbcPersistenceAdapter.setAdapter(adapter);
    }
    
    public Statements getStatements() {
        return this.jdbcPersistenceAdapter.getStatements();
    }
    
    public void setStatements(final Statements statements) {
        this.jdbcPersistenceAdapter.setStatements(statements);
    }
    
    public boolean isUseDatabaseLock() {
        return this.jdbcPersistenceAdapter.isUseDatabaseLock();
    }
    
    public void setUseDatabaseLock(final boolean useDatabaseLock) {
        this.jdbcPersistenceAdapter.setUseDatabaseLock(useDatabaseLock);
    }
    
    public boolean isCreateTablesOnStartup() {
        return this.jdbcPersistenceAdapter.isCreateTablesOnStartup();
    }
    
    public void setCreateTablesOnStartup(final boolean createTablesOnStartup) {
        this.jdbcPersistenceAdapter.setCreateTablesOnStartup(createTablesOnStartup);
    }
    
    public int getJournalThreadPriority() {
        return this.journalThreadPriority;
    }
    
    public void setJournalThreadPriority(final int journalThreadPriority) {
        this.journalThreadPriority = journalThreadPriority;
    }
    
    protected void createJournal() throws IOException {
        final File journalDir = new File(this.getDataDirectoryFile(), "journal").getCanonicalFile();
        if (this.failIfJournalIsLocked) {
            this.journal = (Journal)new JournalImpl(journalDir, this.journalLogFiles, this.journalLogFileSize, this.getJournalArchiveDirectory());
        }
        else {
            while (true) {
                try {
                    this.journal = (Journal)new JournalImpl(journalDir, this.journalLogFiles, this.journalLogFileSize, this.getJournalArchiveDirectory());
                }
                catch (JournalLockedException e) {
                    JournalPersistenceAdapterFactory.LOG.info("Journal is locked... waiting 10 seconds for the journal to be unlocked.");
                    try {
                        Thread.sleep(10000L);
                    }
                    catch (InterruptedException ex) {}
                    continue;
                }
                break;
            }
        }
    }
    
    static {
        LOG = LoggerFactory.getLogger(JournalPersistenceAdapterFactory.class);
    }
}
