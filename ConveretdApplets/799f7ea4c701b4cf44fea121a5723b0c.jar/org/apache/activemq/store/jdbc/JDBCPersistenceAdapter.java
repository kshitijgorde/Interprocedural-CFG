// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.jdbc;

import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.io.File;
import org.apache.activemq.usage.SystemUsage;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.store.jdbc.adapter.DefaultJDBCAdapter;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.apache.activemq.command.ProducerId;
import org.apache.activemq.util.IOExceptionSupport;
import org.apache.activemq.util.ByteSequence;
import org.apache.activemq.command.Message;
import org.apache.activemq.store.TransactionStore;
import org.apache.activemq.store.TopicMessageStore;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.store.MessageStore;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.MessageId;
import java.util.Collections;
import java.sql.SQLException;
import java.io.IOException;
import org.apache.activemq.command.ActiveMQDestination;
import java.util.Set;
import org.apache.activemq.openwire.OpenWireFormat;
import org.apache.activemq.util.LongSequenceGenerator;
import org.apache.activemq.ActiveMQMessageAudit;
import javax.sql.DataSource;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.apache.activemq.store.memory.MemoryTransactionStore;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.wireformat.WireFormat;
import org.apache.activemq.util.FactoryFinder;
import org.slf4j.Logger;
import org.apache.activemq.broker.BrokerServiceAware;
import org.apache.activemq.store.PersistenceAdapter;

public class JDBCPersistenceAdapter extends DataSourceSupport implements PersistenceAdapter, BrokerServiceAware
{
    private static final Logger LOG;
    private static FactoryFinder adapterFactoryFinder;
    private static FactoryFinder lockFactoryFinder;
    private WireFormat wireFormat;
    private BrokerService brokerService;
    private Statements statements;
    private JDBCAdapter adapter;
    private MemoryTransactionStore transactionStore;
    private ScheduledThreadPoolExecutor clockDaemon;
    private ScheduledFuture<?> cleanupTicket;
    private ScheduledFuture<?> keepAliveTicket;
    private int cleanupPeriod;
    private boolean useExternalMessageReferences;
    private boolean useDatabaseLock;
    private long lockKeepAlivePeriod;
    private long lockAcquireSleepInterval;
    private DatabaseLocker databaseLocker;
    private boolean createTablesOnStartup;
    private DataSource lockDataSource;
    private int transactionIsolation;
    protected int maxProducersToAudit;
    protected int maxAuditDepth;
    protected boolean enableAudit;
    protected int auditRecoveryDepth;
    protected ActiveMQMessageAudit audit;
    protected LongSequenceGenerator sequenceGenerator;
    protected int maxRows;
    
    public JDBCPersistenceAdapter() {
        this.wireFormat = new OpenWireFormat();
        this.cleanupPeriod = 300000;
        this.useDatabaseLock = true;
        this.lockKeepAlivePeriod = 30000L;
        this.lockAcquireSleepInterval = 1000L;
        this.createTablesOnStartup = true;
        this.maxProducersToAudit = 1024;
        this.maxAuditDepth = 1000;
        this.enableAudit = false;
        this.auditRecoveryDepth = 1024;
        this.sequenceGenerator = new LongSequenceGenerator();
        this.maxRows = 10000;
    }
    
    public JDBCPersistenceAdapter(final DataSource ds, final WireFormat wireFormat) {
        super(ds);
        this.wireFormat = new OpenWireFormat();
        this.cleanupPeriod = 300000;
        this.useDatabaseLock = true;
        this.lockKeepAlivePeriod = 30000L;
        this.lockAcquireSleepInterval = 1000L;
        this.createTablesOnStartup = true;
        this.maxProducersToAudit = 1024;
        this.maxAuditDepth = 1000;
        this.enableAudit = false;
        this.auditRecoveryDepth = 1024;
        this.sequenceGenerator = new LongSequenceGenerator();
        this.maxRows = 10000;
        this.wireFormat = wireFormat;
    }
    
    @Override
    public Set<ActiveMQDestination> getDestinations() {
        TransactionContext c = null;
        try {
            c = this.getTransactionContext();
            return this.getAdapter().doGetDestinations(c);
        }
        catch (IOException e2) {
            return this.emptyDestinationSet();
        }
        catch (SQLException e) {
            log("JDBC Failure: ", e);
            return this.emptyDestinationSet();
        }
        finally {
            if (c != null) {
                try {
                    c.close();
                }
                catch (Throwable t) {}
            }
        }
    }
    
    private Set<ActiveMQDestination> emptyDestinationSet() {
        return (Set<ActiveMQDestination>)Collections.EMPTY_SET;
    }
    
    protected void createMessageAudit() {
        if (this.enableAudit && this.audit == null) {
            this.audit = new ActiveMQMessageAudit(this.maxAuditDepth, this.maxProducersToAudit);
            TransactionContext c = null;
            try {
                c = this.getTransactionContext();
                this.getAdapter().doMessageIdScan(c, this.auditRecoveryDepth, new JDBCMessageIdScanListener() {
                    @Override
                    public void messageId(final MessageId id) {
                        JDBCPersistenceAdapter.this.audit.isDuplicate(id);
                    }
                });
            }
            catch (Exception e) {
                JDBCPersistenceAdapter.LOG.error("Failed to reload store message audit for JDBC persistence adapter", e);
            }
            finally {
                if (c != null) {
                    try {
                        c.close();
                    }
                    catch (Throwable t) {}
                }
            }
        }
    }
    
    public void initSequenceIdGenerator() {
        TransactionContext c = null;
        try {
            c = this.getTransactionContext();
            this.getAdapter().doMessageIdScan(c, this.auditRecoveryDepth, new JDBCMessageIdScanListener() {
                @Override
                public void messageId(final MessageId id) {
                    JDBCPersistenceAdapter.this.audit.isDuplicate(id);
                }
            });
        }
        catch (Exception e) {
            JDBCPersistenceAdapter.LOG.error("Failed to reload store message audit for JDBC persistence adapter", e);
        }
        finally {
            if (c != null) {
                try {
                    c.close();
                }
                catch (Throwable t) {}
            }
        }
    }
    
    @Override
    public MessageStore createQueueMessageStore(final ActiveMQQueue destination) throws IOException {
        MessageStore rc = new JDBCMessageStore(this, this.getAdapter(), this.wireFormat, destination, this.audit);
        if (this.transactionStore != null) {
            rc = this.transactionStore.proxy(rc);
        }
        return rc;
    }
    
    @Override
    public TopicMessageStore createTopicMessageStore(final ActiveMQTopic destination) throws IOException {
        TopicMessageStore rc = new JDBCTopicMessageStore(this, this.getAdapter(), this.wireFormat, destination, this.audit);
        if (this.transactionStore != null) {
            rc = this.transactionStore.proxy(rc);
        }
        return rc;
    }
    
    @Override
    public void removeQueueMessageStore(final ActiveMQQueue destination) {
    }
    
    @Override
    public void removeTopicMessageStore(final ActiveMQTopic destination) {
    }
    
    @Override
    public TransactionStore createTransactionStore() throws IOException {
        if (this.transactionStore == null) {
            this.transactionStore = new MemoryTransactionStore(this);
        }
        return this.transactionStore;
    }
    
    @Override
    public long getLastMessageBrokerSequenceId() throws IOException {
        final TransactionContext c = this.getTransactionContext();
        try {
            final long seq = this.getAdapter().doGetLastMessageStoreSequenceId(c);
            this.sequenceGenerator.setLastSequenceId(seq);
            long brokerSeq = 0L;
            if (seq != 0L) {
                final byte[] msg = this.getAdapter().doGetMessageById(c, seq);
                if (msg != null) {
                    final Message last = (Message)this.wireFormat.unmarshal(new ByteSequence(msg));
                    brokerSeq = last.getMessageId().getBrokerSequenceId();
                }
                else {
                    JDBCPersistenceAdapter.LOG.warn("Broker sequence id wasn't recovered properly, possible duplicates!");
                }
            }
            return brokerSeq;
        }
        catch (SQLException e) {
            log("JDBC Failure: ", e);
            throw IOExceptionSupport.create("Failed to get last broker message id: " + e, e);
        }
        finally {
            c.close();
        }
    }
    
    @Override
    public long getLastProducerSequenceId(final ProducerId id) throws IOException {
        final TransactionContext c = this.getTransactionContext();
        try {
            return this.getAdapter().doGetLastProducerSequenceId(c, id);
        }
        catch (SQLException e) {
            log("JDBC Failure: ", e);
            throw IOExceptionSupport.create("Failed to get last broker message id: " + e, e);
        }
        finally {
            c.close();
        }
    }
    
    @Override
    public void start() throws Exception {
        this.getAdapter().setUseExternalMessageReferences(this.isUseExternalMessageReferences());
        if (this.isCreateTablesOnStartup()) {
            final TransactionContext transactionContext = this.getTransactionContext();
            transactionContext.begin();
            try {
                this.getAdapter().doCreateTables(transactionContext);
            }
            catch (SQLException e) {
                JDBCPersistenceAdapter.LOG.warn("Cannot create tables due to: " + e);
                log("Failure Details: ", e);
            }
            finally {
                transactionContext.commit();
            }
        }
        if (this.isUseDatabaseLock()) {
            final DatabaseLocker service = this.getDatabaseLocker();
            if (service == null) {
                JDBCPersistenceAdapter.LOG.warn("No databaseLocker configured for the JDBC Persistence Adapter");
            }
            else {
                service.start();
                if (this.lockKeepAlivePeriod > 0L) {
                    this.keepAliveTicket = this.getScheduledThreadPoolExecutor().scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            JDBCPersistenceAdapter.this.databaseLockKeepAlive();
                        }
                    }, this.lockKeepAlivePeriod, this.lockKeepAlivePeriod, TimeUnit.MILLISECONDS);
                }
                if (this.brokerService != null) {
                    this.brokerService.getBroker().nowMasterBroker();
                }
            }
        }
        this.cleanup();
        if (this.cleanupPeriod > 0) {
            this.cleanupTicket = this.getScheduledThreadPoolExecutor().scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    JDBCPersistenceAdapter.this.cleanup();
                }
            }, this.cleanupPeriod, this.cleanupPeriod, TimeUnit.MILLISECONDS);
        }
        this.createMessageAudit();
    }
    
    @Override
    public synchronized void stop() throws Exception {
        if (this.cleanupTicket != null) {
            this.cleanupTicket.cancel(true);
            this.cleanupTicket = null;
        }
        if (this.keepAliveTicket != null) {
            this.keepAliveTicket.cancel(false);
            this.keepAliveTicket = null;
        }
        final DatabaseLocker service = this.getDatabaseLocker();
        if (service != null) {
            service.stop();
        }
    }
    
    public void cleanup() {
        TransactionContext c = null;
        try {
            JDBCPersistenceAdapter.LOG.debug("Cleaning up old messages.");
            c = this.getTransactionContext();
            this.getAdapter().doDeleteOldMessages(c, false);
            this.getAdapter().doDeleteOldMessages(c, true);
        }
        catch (IOException e) {
            JDBCPersistenceAdapter.LOG.warn("Old message cleanup failed due to: " + e, e);
        }
        catch (SQLException e2) {
            JDBCPersistenceAdapter.LOG.warn("Old message cleanup failed due to: " + e2);
            log("Failure Details: ", e2);
        }
        finally {
            if (c != null) {
                try {
                    c.close();
                }
                catch (Throwable t) {}
            }
            JDBCPersistenceAdapter.LOG.debug("Cleanup done.");
        }
    }
    
    public void setScheduledThreadPoolExecutor(final ScheduledThreadPoolExecutor clockDaemon) {
        this.clockDaemon = clockDaemon;
    }
    
    public ScheduledThreadPoolExecutor getScheduledThreadPoolExecutor() {
        if (this.clockDaemon == null) {
            this.clockDaemon = new ScheduledThreadPoolExecutor(5, new ThreadFactory() {
                @Override
                public Thread newThread(final Runnable runnable) {
                    final Thread thread = new Thread(runnable, "ActiveMQ Cleanup Timer");
                    thread.setDaemon(true);
                    return thread;
                }
            });
        }
        return this.clockDaemon;
    }
    
    public JDBCAdapter getAdapter() throws IOException {
        if (this.adapter == null) {
            this.setAdapter(this.createAdapter());
        }
        return this.adapter;
    }
    
    public DatabaseLocker getDatabaseLocker() throws IOException {
        if (this.databaseLocker == null && this.isUseDatabaseLock()) {
            this.setDatabaseLocker(this.loadDataBaseLocker());
        }
        return this.databaseLocker;
    }
    
    public void setDatabaseLocker(final DatabaseLocker locker) throws IOException {
        (this.databaseLocker = locker).setPersistenceAdapter(this);
        this.databaseLocker.setLockAcquireSleepInterval(this.getLockAcquireSleepInterval());
    }
    
    public DataSource getLockDataSource() throws IOException {
        if (this.lockDataSource == null) {
            this.lockDataSource = this.getDataSource();
            if (this.lockDataSource == null) {
                throw new IllegalArgumentException("No dataSource property has been configured");
            }
        }
        else {
            JDBCPersistenceAdapter.LOG.info("Using a separate dataSource for locking: " + this.lockDataSource);
        }
        return this.lockDataSource;
    }
    
    public void setLockDataSource(final DataSource dataSource) {
        this.lockDataSource = dataSource;
    }
    
    public BrokerService getBrokerService() {
        return this.brokerService;
    }
    
    @Override
    public void setBrokerService(final BrokerService brokerService) {
        this.brokerService = brokerService;
    }
    
    protected JDBCAdapter createAdapter() throws IOException {
        this.adapter = (JDBCAdapter)this.loadAdapter(JDBCPersistenceAdapter.adapterFactoryFinder, "adapter");
        if (this.adapter == null) {
            this.adapter = new DefaultJDBCAdapter();
            JDBCPersistenceAdapter.LOG.debug("Using default JDBC Adapter: " + this.adapter);
        }
        return this.adapter;
    }
    
    private Object loadAdapter(final FactoryFinder finder, final String kind) throws IOException {
        Object adapter = null;
        final TransactionContext c = this.getTransactionContext();
        try {
            String dirverName = c.getConnection().getMetaData().getDriverName();
            dirverName = dirverName.replaceAll("[^a-zA-Z0-9\\-]", "_").toLowerCase();
            try {
                adapter = finder.newInstance(dirverName);
                JDBCPersistenceAdapter.LOG.info("Database " + kind + " driver override recognized for : [" + dirverName + "] - adapter: " + adapter.getClass());
            }
            catch (Throwable e2) {
                JDBCPersistenceAdapter.LOG.info("Database " + kind + " driver override not found for : [" + dirverName + "].  Will use default implementation.");
            }
        }
        catch (SQLException e) {
            JDBCPersistenceAdapter.LOG.warn("JDBC error occurred while trying to detect database type for overrides. Will use default implementations: " + e.getMessage());
            log("Failure Details: ", e);
        }
        finally {
            c.close();
        }
        return adapter;
    }
    
    public void setAdapter(final JDBCAdapter adapter) {
        (this.adapter = adapter).setStatements(this.getStatements());
        this.adapter.setMaxRows(this.getMaxRows());
    }
    
    public WireFormat getWireFormat() {
        return this.wireFormat;
    }
    
    public void setWireFormat(final WireFormat wireFormat) {
        this.wireFormat = wireFormat;
    }
    
    public TransactionContext getTransactionContext(final ConnectionContext context) throws IOException {
        if (context == null) {
            return this.getTransactionContext();
        }
        TransactionContext answer = (TransactionContext)context.getLongTermStoreContext();
        if (answer == null) {
            answer = this.getTransactionContext();
            context.setLongTermStoreContext(answer);
        }
        return answer;
    }
    
    public TransactionContext getTransactionContext() throws IOException {
        final TransactionContext answer = new TransactionContext(this);
        if (this.transactionIsolation > 0) {
            answer.setTransactionIsolation(this.transactionIsolation);
        }
        return answer;
    }
    
    @Override
    public void beginTransaction(final ConnectionContext context) throws IOException {
        final TransactionContext transactionContext = this.getTransactionContext(context);
        transactionContext.begin();
    }
    
    @Override
    public void commitTransaction(final ConnectionContext context) throws IOException {
        final TransactionContext transactionContext = this.getTransactionContext(context);
        transactionContext.commit();
    }
    
    @Override
    public void rollbackTransaction(final ConnectionContext context) throws IOException {
        final TransactionContext transactionContext = this.getTransactionContext(context);
        transactionContext.rollback();
    }
    
    public int getCleanupPeriod() {
        return this.cleanupPeriod;
    }
    
    public void setCleanupPeriod(final int cleanupPeriod) {
        this.cleanupPeriod = cleanupPeriod;
    }
    
    @Override
    public void deleteAllMessages() throws IOException {
        final TransactionContext c = this.getTransactionContext();
        try {
            this.getAdapter().doDropTables(c);
            this.getAdapter().setUseExternalMessageReferences(this.isUseExternalMessageReferences());
            this.getAdapter().doCreateTables(c);
            JDBCPersistenceAdapter.LOG.info("Persistence store purged.");
        }
        catch (SQLException e) {
            log("JDBC Failure: ", e);
            throw IOExceptionSupport.create(e);
        }
        finally {
            c.close();
        }
    }
    
    public boolean isUseExternalMessageReferences() {
        return this.useExternalMessageReferences;
    }
    
    public void setUseExternalMessageReferences(final boolean useExternalMessageReferences) {
        this.useExternalMessageReferences = useExternalMessageReferences;
    }
    
    public boolean isCreateTablesOnStartup() {
        return this.createTablesOnStartup;
    }
    
    public void setCreateTablesOnStartup(final boolean createTablesOnStartup) {
        this.createTablesOnStartup = createTablesOnStartup;
    }
    
    public boolean isUseDatabaseLock() {
        return this.useDatabaseLock;
    }
    
    public void setUseDatabaseLock(final boolean useDatabaseLock) {
        this.useDatabaseLock = useDatabaseLock;
    }
    
    public static void log(final String msg, SQLException e) {
        String s;
        for (s = msg + e.getMessage(); e.getNextException() != null; e = e.getNextException(), s = s + ", due to: " + e.getMessage()) {}
        JDBCPersistenceAdapter.LOG.warn(s, e);
    }
    
    public Statements getStatements() {
        if (this.statements == null) {
            this.statements = new Statements();
        }
        return this.statements;
    }
    
    public void setStatements(final Statements statements) {
        this.statements = statements;
    }
    
    @Override
    public void setUsageManager(final SystemUsage usageManager) {
    }
    
    protected void databaseLockKeepAlive() {
        boolean stop = false;
        try {
            final DatabaseLocker locker = this.getDatabaseLocker();
            if (locker != null && !locker.keepAlive()) {
                stop = true;
            }
        }
        catch (IOException e) {
            JDBCPersistenceAdapter.LOG.error("Failed to get database when trying keepalive: " + e, e);
        }
        if (stop) {
            this.stopBroker();
        }
    }
    
    protected void stopBroker() {
        JDBCPersistenceAdapter.LOG.info("No longer able to keep the exclusive lock so giving up being a master");
        try {
            this.brokerService.stop();
        }
        catch (Exception e) {
            JDBCPersistenceAdapter.LOG.warn("Failure occurred while stopping broker");
        }
    }
    
    protected DatabaseLocker loadDataBaseLocker() throws IOException {
        DatabaseLocker locker = (DefaultDatabaseLocker)this.loadAdapter(JDBCPersistenceAdapter.lockFactoryFinder, "lock");
        if (locker == null) {
            locker = new DefaultDatabaseLocker();
            JDBCPersistenceAdapter.LOG.debug("Using default JDBC Locker: " + locker);
        }
        return locker;
    }
    
    @Override
    public void setBrokerName(final String brokerName) {
    }
    
    @Override
    public String toString() {
        return "JDBCPersistenceAdapter(" + super.toString() + ")";
    }
    
    @Override
    public void setDirectory(final File dir) {
    }
    
    @Override
    public void checkpoint(final boolean sync) throws IOException {
        Connection connection = null;
        try {
            connection = this.getDataSource().getConnection();
        }
        catch (SQLException e) {
            JDBCPersistenceAdapter.LOG.debug("Could not get JDBC connection for checkpoint: " + e);
            throw IOExceptionSupport.create(e);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (Throwable t) {}
            }
        }
    }
    
    @Override
    public long size() {
        return 0L;
    }
    
    public long getLockKeepAlivePeriod() {
        return this.lockKeepAlivePeriod;
    }
    
    public void setLockKeepAlivePeriod(final long lockKeepAlivePeriod) {
        this.lockKeepAlivePeriod = lockKeepAlivePeriod;
    }
    
    public long getLockAcquireSleepInterval() {
        return this.lockAcquireSleepInterval;
    }
    
    public void setLockAcquireSleepInterval(final long lockAcquireSleepInterval) {
        this.lockAcquireSleepInterval = lockAcquireSleepInterval;
    }
    
    public void setTransactionIsolation(final int transactionIsolation) {
        this.transactionIsolation = transactionIsolation;
    }
    
    public int getMaxProducersToAudit() {
        return this.maxProducersToAudit;
    }
    
    public void setMaxProducersToAudit(final int maxProducersToAudit) {
        this.maxProducersToAudit = maxProducersToAudit;
    }
    
    public int getMaxAuditDepth() {
        return this.maxAuditDepth;
    }
    
    public void setMaxAuditDepth(final int maxAuditDepth) {
        this.maxAuditDepth = maxAuditDepth;
    }
    
    public boolean isEnableAudit() {
        return this.enableAudit;
    }
    
    public void setEnableAudit(final boolean enableAudit) {
        this.enableAudit = enableAudit;
    }
    
    public int getAuditRecoveryDepth() {
        return this.auditRecoveryDepth;
    }
    
    public void setAuditRecoveryDepth(final int auditRecoveryDepth) {
        this.auditRecoveryDepth = auditRecoveryDepth;
    }
    
    public long getNextSequenceId() {
        synchronized (this.sequenceGenerator) {
            return this.sequenceGenerator.getNextSequenceId();
        }
    }
    
    public int getMaxRows() {
        return this.maxRows;
    }
    
    public void setMaxRows(final int maxRows) {
        this.maxRows = maxRows;
    }
    
    static {
        LOG = LoggerFactory.getLogger(JDBCPersistenceAdapter.class);
        JDBCPersistenceAdapter.adapterFactoryFinder = new FactoryFinder("META-INF/services/org/apache/activemq/store/jdbc/");
        JDBCPersistenceAdapter.lockFactoryFinder = new FactoryFinder("META-INF/services/org/apache/activemq/store/jdbc/lock/");
    }
}
