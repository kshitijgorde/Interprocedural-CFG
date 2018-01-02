// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker;

import java.net.UnknownHostException;
import org.apache.activemq.util.InetAddressUtil;
import org.slf4j.LoggerFactory;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Set;
import org.apache.activemq.util.MDCHelper;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.SynchronousQueue;
import org.apache.activemq.util.BrokerSupport;
import org.apache.activemq.transport.TransportFactory;
import org.apache.activemq.store.kahadb.KahaDBPersistenceAdapter;
import org.apache.activemq.store.memory.MemoryPersistenceAdapter;
import org.apache.activemq.broker.jmx.JobSchedulerViewMBean;
import org.apache.activemq.broker.cluster.ConnectionSplitBroker;
import org.apache.activemq.advisory.AdvisoryBroker;
import org.apache.activemq.broker.jmx.JobSchedulerView;
import org.apache.activemq.broker.scheduler.SchedulerBroker;
import org.apache.activemq.broker.region.virtual.MirroredQueue;
import org.apache.activemq.broker.region.virtual.VirtualDestination;
import org.apache.activemq.broker.region.virtual.VirtualTopic;
import org.apache.activemq.broker.region.virtual.VirtualDestinationInterceptor;
import org.apache.activemq.broker.region.RegionBroker;
import org.apache.activemq.broker.region.DestinationFactoryImpl;
import org.apache.activemq.broker.region.CompositeDestinationInterceptor;
import org.apache.activemq.broker.jmx.JmsConnectorView;
import org.apache.activemq.broker.jmx.FTConnectorView;
import org.apache.activemq.broker.jmx.ProxyConnectorView;
import org.apache.activemq.broker.jmx.NetworkConnectorViewMBean;
import org.apache.activemq.broker.jmx.NetworkConnectorView;
import javax.management.MalformedObjectNameException;
import org.apache.activemq.util.JMXSupport;
import org.apache.activemq.broker.jmx.ConnectorViewMBean;
import org.apache.activemq.util.IOExceptionSupport;
import org.apache.activemq.broker.jmx.AnnotatedMBean;
import org.apache.activemq.broker.jmx.ConnectorView;
import java.net.URISyntaxException;
import java.util.Collection;
import java.io.IOException;
import org.apache.activemq.util.IOHelper;
import org.apache.activemq.ActiveMQConnectionMetaData;
import java.util.concurrent.TimeUnit;
import org.apache.activemq.broker.region.Destination;
import javax.annotation.PreDestroy;
import org.apache.activemq.selector.SelectorParser;
import org.apache.activemq.transport.vm.VMTransportFactory;
import org.apache.activemq.util.ServiceStopper;
import org.apache.activemq.util.DefaultIOExceptionHandler;
import org.apache.activemq.broker.jmx.ManagedRegionBroker;
import org.apache.activemq.ConfigurationException;
import org.slf4j.MDC;
import javax.annotation.PostConstruct;
import java.util.Iterator;
import org.apache.activemq.network.ConnectionFilter;
import java.util.Map;
import java.util.HashMap;
import org.apache.activemq.util.URISupport;
import org.apache.activemq.network.DiscoveryNetworkConnector;
import javax.jms.IllegalStateException;
import org.apache.activemq.transport.TransportServer;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.activemq.thread.Scheduler;
import org.apache.activemq.util.IOExceptionHandler;
import org.apache.activemq.store.kahadb.plist.PListStore;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.broker.region.DestinationInterceptor;
import org.apache.activemq.command.BrokerId;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.activemq.broker.region.policy.PolicyMap;
import java.net.URI;
import org.apache.activemq.broker.ft.MasterConnector;
import org.apache.activemq.network.jms.JmsConnector;
import org.apache.activemq.proxy.ProxyConnector;
import org.apache.activemq.network.NetworkConnector;
import java.util.List;
import org.apache.activemq.security.MessageAuthorizationPolicy;
import org.apache.activemq.broker.region.DestinationFactory;
import org.apache.activemq.store.PersistenceAdapterFactory;
import org.apache.activemq.store.PersistenceAdapter;
import org.apache.activemq.usage.SystemUsage;
import org.apache.activemq.thread.TaskRunnerFactory;
import javax.management.ObjectName;
import org.apache.activemq.broker.jmx.ManagementContext;
import org.apache.activemq.broker.jmx.BrokerView;
import java.io.File;
import org.slf4j.Logger;
import java.util.concurrent.CountDownLatch;
import org.apache.activemq.Service;

public class BrokerService implements Service
{
    protected CountDownLatch slaveStartSignal;
    public static final String DEFAULT_PORT = "61616";
    public static final String LOCAL_HOST_NAME;
    public static final String DEFAULT_BROKER_NAME = "localhost";
    private static final Logger LOG;
    private static final long serialVersionUID = 7353129142305630237L;
    private boolean useJmx;
    private boolean enableStatistics;
    private boolean persistent;
    private boolean populateJMSXUserID;
    private boolean useAuthenticatedPrincipalForJMSXUserID;
    private boolean useShutdownHook;
    private boolean useLoggingForShutdownErrors;
    private boolean shutdownOnMasterFailure;
    private boolean shutdownOnSlaveFailure;
    private boolean waitForSlave;
    private long waitForSlaveTimeout;
    private boolean passiveSlave;
    private String brokerName;
    private File dataDirectoryFile;
    private File tmpDataDirectory;
    private Broker broker;
    private BrokerView adminView;
    private ManagementContext managementContext;
    private ObjectName brokerObjectName;
    private TaskRunnerFactory taskRunnerFactory;
    private TaskRunnerFactory persistenceTaskRunnerFactory;
    private SystemUsage systemUsage;
    private SystemUsage producerSystemUsage;
    private SystemUsage consumerSystemUsaage;
    private PersistenceAdapter persistenceAdapter;
    private PersistenceAdapterFactory persistenceFactory;
    protected DestinationFactory destinationFactory;
    private MessageAuthorizationPolicy messageAuthorizationPolicy;
    private final List<TransportConnector> transportConnectors;
    private final List<NetworkConnector> networkConnectors;
    private final List<ProxyConnector> proxyConnectors;
    private final List<JmsConnector> jmsConnectors;
    private final List<Service> services;
    private MasterConnector masterConnector;
    private String masterConnectorURI;
    private transient Thread shutdownHook;
    private String[] transportConnectorURIs;
    private String[] networkConnectorURIs;
    private JmsConnector[] jmsBridgeConnectors;
    private boolean deleteAllMessagesOnStartup;
    private boolean advisorySupport;
    private URI vmConnectorURI;
    private String defaultSocketURIString;
    private PolicyMap destinationPolicy;
    private final AtomicBoolean started;
    private final AtomicBoolean stopped;
    private BrokerPlugin[] plugins;
    private boolean keepDurableSubsActive;
    private boolean useVirtualTopics;
    private boolean useMirroredQueues;
    private boolean useTempMirroredQueues;
    private BrokerId brokerId;
    private DestinationInterceptor[] destinationInterceptors;
    private ActiveMQDestination[] destinations;
    private PListStore tempDataStore;
    private int persistenceThreadPriority;
    private boolean useLocalHostBrokerName;
    private final CountDownLatch stoppedLatch;
    private final CountDownLatch startedLatch;
    private boolean supportFailOver;
    private Broker regionBroker;
    private int producerSystemUsagePortion;
    private int consumerSystemUsagePortion;
    private boolean splitSystemUsageForProducersConsumers;
    private boolean monitorConnectionSplits;
    private int taskRunnerPriority;
    private boolean dedicatedTaskRunner;
    private boolean cacheTempDestinations;
    private int timeBeforePurgeTempDestinations;
    private final List<Runnable> shutdownHooks;
    private boolean systemExitOnShutdown;
    private int systemExitOnShutdownExitCode;
    private SslContext sslContext;
    private boolean forceStart;
    private IOExceptionHandler ioExceptionHandler;
    private boolean schedulerSupport;
    private File schedulerDirectoryFile;
    private Scheduler scheduler;
    private ThreadPoolExecutor executor;
    private boolean slave;
    private int schedulePeriodForDestinationPurge;
    private BrokerContext brokerContext;
    private boolean networkConnectorStartAsync;
    
    public BrokerService() {
        this.slaveStartSignal = new CountDownLatch(1);
        this.useJmx = true;
        this.enableStatistics = true;
        this.persistent = true;
        this.useShutdownHook = true;
        this.waitForSlaveTimeout = 600000L;
        this.brokerName = "localhost";
        this.transportConnectors = new CopyOnWriteArrayList<TransportConnector>();
        this.networkConnectors = new CopyOnWriteArrayList<NetworkConnector>();
        this.proxyConnectors = new CopyOnWriteArrayList<ProxyConnector>();
        this.jmsConnectors = new CopyOnWriteArrayList<JmsConnector>();
        this.services = new ArrayList<Service>();
        this.advisorySupport = true;
        this.started = new AtomicBoolean(false);
        this.stopped = new AtomicBoolean(false);
        this.keepDurableSubsActive = true;
        this.useVirtualTopics = true;
        this.useMirroredQueues = false;
        this.useTempMirroredQueues = true;
        this.persistenceThreadPriority = 10;
        this.stoppedLatch = new CountDownLatch(1);
        this.startedLatch = new CountDownLatch(1);
        this.producerSystemUsagePortion = 60;
        this.consumerSystemUsagePortion = 40;
        this.monitorConnectionSplits = false;
        this.taskRunnerPriority = 5;
        this.cacheTempDestinations = false;
        this.timeBeforePurgeTempDestinations = 5000;
        this.shutdownHooks = new ArrayList<Runnable>();
        this.forceStart = false;
        this.schedulerSupport = false;
        this.slave = true;
        this.schedulePeriodForDestinationPurge = 5000;
        this.networkConnectorStartAsync = false;
    }
    
    @Override
    public String toString() {
        return "BrokerService[" + this.getBrokerName() + "]";
    }
    
    public TransportConnector addConnector(final String bindAddress) throws Exception {
        return this.addConnector(new URI(bindAddress));
    }
    
    public TransportConnector addConnector(final URI bindAddress) throws Exception {
        return this.addConnector(this.createTransportConnector(bindAddress));
    }
    
    public TransportConnector addConnector(final TransportServer transport) throws Exception {
        return this.addConnector(new TransportConnector(transport));
    }
    
    public TransportConnector addConnector(final TransportConnector connector) throws Exception {
        this.transportConnectors.add(connector);
        return connector;
    }
    
    public boolean removeConnector(final TransportConnector connector) throws Exception {
        final boolean rc = this.transportConnectors.remove(connector);
        if (rc) {
            this.unregisterConnectorMBean(connector);
        }
        return rc;
    }
    
    public NetworkConnector addNetworkConnector(final String discoveryAddress) throws Exception {
        return this.addNetworkConnector(new URI(discoveryAddress));
    }
    
    public ProxyConnector addProxyConnector(final String bindAddress) throws Exception {
        return this.addProxyConnector(new URI(bindAddress));
    }
    
    public NetworkConnector addNetworkConnector(final URI discoveryAddress) throws Exception {
        if (!this.isAdvisorySupport()) {
            throw new IllegalStateException("Networks require advisory messages to function - advisories are currently disabled");
        }
        final NetworkConnector connector = new DiscoveryNetworkConnector(discoveryAddress);
        return this.addNetworkConnector(connector);
    }
    
    public ProxyConnector addProxyConnector(final URI bindAddress) throws Exception {
        final ProxyConnector connector = new ProxyConnector();
        connector.setBind(bindAddress);
        connector.setRemote(new URI("fanout:multicast://default"));
        return this.addProxyConnector(connector);
    }
    
    public NetworkConnector addNetworkConnector(final NetworkConnector connector) throws Exception {
        connector.setBrokerService(this);
        URI uri = this.getVmConnectorURI();
        final Map<String, String> map = new HashMap<String, String>(URISupport.parseParameters(uri));
        map.put("network", "true");
        uri = URISupport.createURIWithQuery(uri, URISupport.createQueryString(map));
        connector.setLocalUri(uri);
        connector.setConnectionFilter(new ConnectionFilter() {
            @Override
            public boolean connectTo(final URI location) {
                final List<TransportConnector> transportConnectors = BrokerService.this.getTransportConnectors();
                final Iterator<TransportConnector> iter = transportConnectors.iterator();
                while (iter.hasNext()) {
                    try {
                        final TransportConnector tc = iter.next();
                        if (location.equals(tc.getConnectUri())) {
                            return false;
                        }
                        continue;
                    }
                    catch (Throwable e) {}
                }
                return true;
            }
        });
        this.networkConnectors.add(connector);
        if (this.isUseJmx()) {
            this.registerNetworkConnectorMBean(connector);
        }
        return connector;
    }
    
    public boolean removeNetworkConnector(final NetworkConnector connector) {
        final boolean answer = this.networkConnectors.remove(connector);
        if (answer) {
            this.unregisterNetworkConnectorMBean(connector);
        }
        return answer;
    }
    
    public ProxyConnector addProxyConnector(final ProxyConnector connector) throws Exception {
        final URI uri = this.getVmConnectorURI();
        connector.setLocalUri(uri);
        this.proxyConnectors.add(connector);
        if (this.isUseJmx()) {
            this.registerProxyConnectorMBean(connector);
        }
        return connector;
    }
    
    public JmsConnector addJmsConnector(final JmsConnector connector) throws Exception {
        connector.setBrokerService(this);
        this.jmsConnectors.add(connector);
        if (this.isUseJmx()) {
            this.registerJmsConnectorMBean(connector);
        }
        return connector;
    }
    
    public JmsConnector removeJmsConnector(final JmsConnector connector) {
        if (this.jmsConnectors.remove(connector)) {
            return connector;
        }
        return null;
    }
    
    public String getMasterConnectorURI() {
        return this.masterConnectorURI;
    }
    
    public void setMasterConnectorURI(final String masterConnectorURI) {
        this.masterConnectorURI = masterConnectorURI;
    }
    
    public boolean isSlave() {
        return (this.masterConnector != null && this.masterConnector.isSlave()) || (this.masterConnector != null && this.masterConnector.isStoppedBeforeStart()) || (this.masterConnector == null && this.slave);
    }
    
    public void masterFailed() {
        if (this.shutdownOnMasterFailure) {
            BrokerService.LOG.error("The Master has failed ... shutting down");
            try {
                this.stop();
            }
            catch (Exception e) {
                BrokerService.LOG.error("Failed to stop for master failure", e);
            }
        }
        else {
            BrokerService.LOG.warn("Master Failed - starting all connectors");
            try {
                this.startAllConnectors();
                this.broker.nowMasterBroker();
            }
            catch (Exception e) {
                BrokerService.LOG.error("Failed to startAllConnectors", e);
            }
        }
    }
    
    public boolean isStarted() {
        return this.started.get();
    }
    
    public void start(final boolean force) throws Exception {
        this.forceStart = force;
        this.stopped.set(false);
        this.started.set(false);
        this.start();
    }
    
    protected boolean shouldAutostart() {
        return true;
    }
    
    @PostConstruct
    public void autoStart() throws Exception {
        if (this.shouldAutostart()) {
            this.start();
        }
    }
    
    @Override
    public void start() throws Exception {
        if (this.stopped.get() || !this.started.compareAndSet(false, true)) {
            return;
        }
        MDC.put("activemq.broker", this.brokerName);
        try {
            if (this.systemExitOnShutdown && this.useShutdownHook) {
                throw new ConfigurationException("'useShutdownHook' property cannot be be used with 'systemExitOnShutdown', please turn it off (useShutdownHook=false)");
            }
            this.processHelperProperties();
            if (this.isUseJmx()) {
                this.startManagementContext();
            }
            this.getPersistenceAdapter().setUsageManager(this.getProducerSystemUsage());
            this.getPersistenceAdapter().setBrokerName(this.getBrokerName());
            BrokerService.LOG.info("Using Persistence Adapter: " + this.getPersistenceAdapter());
            if (this.deleteAllMessagesOnStartup) {
                this.deleteAllMessages();
            }
            this.getPersistenceAdapter().start();
            this.slave = false;
            this.startDestinations();
            this.addShutdownHook();
            this.getBroker().start();
            if (this.isUseJmx()) {
                if (this.getManagementContext().isCreateConnector() && !this.getManagementContext().isConnectorStarted()) {
                    this.managementContext.stop();
                    this.startManagementContext();
                }
                final ManagedRegionBroker managedBroker = (ManagedRegionBroker)this.regionBroker;
                managedBroker.setContextBroker(this.broker);
                this.adminView.setBroker(managedBroker);
            }
            BrokerRegistry.getInstance().bind(this.getBrokerName(), this);
            for (final Service service : this.services) {
                if (service instanceof MasterConnector) {
                    this.configureService(service);
                    service.start();
                }
            }
            if (!this.isSlave() && (this.masterConnector == null || !this.isShutdownOnMasterFailure())) {
                this.startAllConnectors();
            }
            if (!this.stopped.get() && this.isUseJmx() && this.masterConnector != null) {
                this.registerFTConnectorMBean(this.masterConnector);
            }
            if (this.brokerId == null) {
                this.brokerId = this.broker.getBrokerId();
            }
            if (this.ioExceptionHandler == null) {
                this.setIoExceptionHandler(new DefaultIOExceptionHandler());
            }
            BrokerService.LOG.info("ActiveMQ JMS Message Broker (" + this.getBrokerName() + ", " + this.brokerId + ") started");
            this.getBroker().brokerServiceStarted();
            this.startedLatch.countDown();
        }
        catch (Exception e) {
            BrokerService.LOG.error("Failed to start ActiveMQ JMS Message Broker. Reason: " + e, e);
            try {
                if (!this.stopped.get()) {
                    this.stop();
                }
            }
            catch (Exception ex) {
                BrokerService.LOG.warn("Failed to stop broker after failure in start ", ex);
            }
            throw e;
        }
        finally {
            MDC.remove("activemq.broker");
        }
    }
    
    @PreDestroy
    @Override
    public void stop() throws Exception {
        if (!this.started.get()) {
            return;
        }
        MDC.put("activemq.broker", this.brokerName);
        if (this.systemExitOnShutdown) {
            new Thread() {
                @Override
                public void run() {
                    System.exit(BrokerService.this.systemExitOnShutdownExitCode);
                }
            }.start();
        }
        BrokerService.LOG.info("ActiveMQ Message Broker (" + this.getBrokerName() + ", " + this.brokerId + ") is shutting down");
        this.removeShutdownHook();
        final ServiceStopper stopper = new ServiceStopper();
        if (this.services != null) {
            for (final Service service : this.services) {
                stopper.stop(service);
            }
        }
        this.stopAllConnectors(stopper);
        BrokerRegistry.getInstance().unbind(this.getBrokerName());
        VMTransportFactory.stopped(this.getBrokerName());
        if (this.broker != null) {
            stopper.stop(this.broker);
            this.broker = null;
        }
        if (this.tempDataStore != null) {
            this.tempDataStore.stop();
            this.tempDataStore = null;
        }
        stopper.stop(this.persistenceAdapter);
        this.persistenceAdapter = null;
        this.slave = true;
        if (this.isUseJmx()) {
            stopper.stop(this.getManagementContext());
            this.managementContext = null;
        }
        SelectorParser.clearCache();
        this.stopped.set(true);
        this.stoppedLatch.countDown();
        if (this.masterConnectorURI == null) {
            if (this.slaveStartSignal.getCount() == 1L) {
                this.started.set(false);
                this.slaveStartSignal.countDown();
            }
        }
        else {
            for (final Service service : this.services) {
                if (service instanceof MasterConnector) {
                    final MasterConnector mConnector = (MasterConnector)service;
                    if (mConnector.isSlave()) {
                        continue;
                    }
                    this.started.set(false);
                    mConnector.stopBeforeConnected();
                }
            }
        }
        if (this.taskRunnerFactory != null) {
            this.taskRunnerFactory.shutdown();
            this.taskRunnerFactory = null;
        }
        if (this.scheduler != null) {
            this.scheduler.stop();
            this.scheduler = null;
        }
        if (this.executor != null) {
            this.executor.shutdownNow();
            this.executor = null;
        }
        this.destinationInterceptors = null;
        this.destinationFactory = null;
        BrokerService.LOG.info("ActiveMQ JMS Message Broker (" + this.getBrokerName() + ", " + this.brokerId + ") stopped");
        synchronized (this.shutdownHooks) {
            for (final Runnable hook : this.shutdownHooks) {
                try {
                    hook.run();
                }
                catch (Throwable e) {
                    stopper.onException(hook, e);
                }
            }
        }
        MDC.remove("activemq.broker");
        stopper.throwFirstException();
    }
    
    public boolean checkQueueSize(final String queueName) {
        long count = 0L;
        long queueSize = 0L;
        final Map<ActiveMQDestination, Destination> destinationMap = this.regionBroker.getDestinationMap();
        for (final Map.Entry<ActiveMQDestination, Destination> entry : destinationMap.entrySet()) {
            if (entry.getKey().isQueue() && entry.getValue().getName().matches(queueName)) {
                queueSize = entry.getValue().getDestinationStatistics().getMessages().getCount();
                count += queueSize;
                if (queueSize <= 0L) {
                    continue;
                }
                BrokerService.LOG.info("Queue has pending message:" + entry.getValue().getName() + " queueSize is:" + queueSize);
            }
        }
        return count == 0L;
    }
    
    public void stopGracefully(final String connectorName, final String queueName, final long timeout, long pollInterval) throws Exception {
        if (this.isUseJmx()) {
            if (connectorName == null || queueName == null || timeout <= 0L) {
                throw new Exception("connectorName and queueName cannot be null and timeout should be >0 for stopGracefully.");
            }
            if (pollInterval <= 0L) {
                pollInterval = 30L;
            }
            BrokerService.LOG.info("Stop gracefully with connectorName:" + connectorName + " queueName:" + queueName + " timeout:" + timeout + " pollInterval:" + pollInterval);
            for (int i = 0; i < this.transportConnectors.size(); ++i) {
                final TransportConnector connector = this.transportConnectors.get(i);
                if (connector != null && connector.getName() != null && connector.getName().matches(connectorName)) {
                    connector.stop();
                }
            }
            final long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < timeout * 1000L) {
                if (this.checkQueueSize(queueName)) {
                    this.stop();
                    break;
                }
                Thread.sleep(pollInterval * 1000L);
            }
            if (this.stopped.get()) {
                BrokerService.LOG.info("Successfully stop the broker.");
            }
            else {
                BrokerService.LOG.info("There is still pending message on the queue. Please check and stop the broker manually.");
            }
        }
    }
    
    public void waitUntilStopped() {
        while (this.isStarted() && !this.stopped.get()) {
            try {
                this.stoppedLatch.await();
            }
            catch (InterruptedException e) {}
        }
    }
    
    public boolean waitUntilStarted() {
        boolean waitSucceeded = false;
        while (this.isStarted() && !this.stopped.get() && !waitSucceeded) {
            try {
                waitSucceeded = this.startedLatch.await(100L, TimeUnit.MILLISECONDS);
            }
            catch (InterruptedException ignore) {}
        }
        return waitSucceeded;
    }
    
    public Broker getBroker() throws Exception {
        if (this.broker == null) {
            BrokerService.LOG.info("ActiveMQ " + ActiveMQConnectionMetaData.PROVIDER_VERSION + " JMS Message Broker (" + this.getBrokerName() + ") is starting");
            BrokerService.LOG.info("For help or more information please see: http://activemq.apache.org/");
            this.broker = this.createBroker();
        }
        return this.broker;
    }
    
    public BrokerView getAdminView() throws Exception {
        if (this.adminView == null) {
            this.getBroker();
        }
        return this.adminView;
    }
    
    public void setAdminView(final BrokerView adminView) {
        this.adminView = adminView;
    }
    
    public String getBrokerName() {
        return this.brokerName;
    }
    
    public void setBrokerName(final String brokerName) {
        if (brokerName == null) {
            throw new NullPointerException("The broker name cannot be null");
        }
        final String str = brokerName.replaceAll("[^a-zA-Z0-9\\.\\_\\-\\:]", "_");
        if (!str.equals(brokerName)) {
            BrokerService.LOG.error("Broker Name: " + brokerName + " contained illegal characters - replaced with " + str);
        }
        this.brokerName = str.trim();
    }
    
    public PersistenceAdapterFactory getPersistenceFactory() {
        return this.persistenceFactory;
    }
    
    public File getDataDirectoryFile() {
        if (this.dataDirectoryFile == null) {
            this.dataDirectoryFile = new File(IOHelper.getDefaultDataDirectory());
        }
        return this.dataDirectoryFile;
    }
    
    public File getBrokerDataDirectory() {
        final String brokerDir = this.getBrokerName();
        return new File(this.getDataDirectoryFile(), brokerDir);
    }
    
    public void setDataDirectory(final String dataDirectory) {
        this.setDataDirectoryFile(new File(dataDirectory));
    }
    
    public void setDataDirectoryFile(final File dataDirectoryFile) {
        this.dataDirectoryFile = dataDirectoryFile;
    }
    
    public File getTmpDataDirectory() {
        if (this.tmpDataDirectory == null) {
            this.tmpDataDirectory = new File(this.getBrokerDataDirectory(), "tmp_storage");
        }
        return this.tmpDataDirectory;
    }
    
    public void setTmpDataDirectory(final File tmpDataDirectory) {
        this.tmpDataDirectory = tmpDataDirectory;
    }
    
    public void setPersistenceFactory(final PersistenceAdapterFactory persistenceFactory) {
        this.persistenceFactory = persistenceFactory;
    }
    
    public void setDestinationFactory(final DestinationFactory destinationFactory) {
        this.destinationFactory = destinationFactory;
    }
    
    public boolean isPersistent() {
        return this.persistent;
    }
    
    public void setPersistent(final boolean persistent) {
        this.persistent = persistent;
    }
    
    public boolean isPopulateJMSXUserID() {
        return this.populateJMSXUserID;
    }
    
    public void setPopulateJMSXUserID(final boolean populateJMSXUserID) {
        this.populateJMSXUserID = populateJMSXUserID;
    }
    
    public SystemUsage getSystemUsage() {
        try {
            if (this.systemUsage == null) {
                (this.systemUsage = new SystemUsage("Main", this.getPersistenceAdapter(), this.getTempDataStore())).setExecutor(this.getExecutor());
                this.systemUsage.getMemoryUsage().setLimit(67108864L);
                this.systemUsage.getTempUsage().setLimit(107374182400L);
                this.systemUsage.getStoreUsage().setLimit(107374182400L);
                this.addService(this.systemUsage);
            }
            return this.systemUsage;
        }
        catch (IOException e) {
            BrokerService.LOG.error("Cannot create SystemUsage", e);
            throw new RuntimeException("Fatally failed to create SystemUsage" + e.getMessage());
        }
    }
    
    public void setSystemUsage(final SystemUsage memoryManager) {
        if (this.systemUsage != null) {
            this.removeService(this.systemUsage);
        }
        this.systemUsage = memoryManager;
        if (this.systemUsage.getExecutor() == null) {
            this.systemUsage.setExecutor(this.getExecutor());
        }
        this.addService(this.systemUsage);
    }
    
    public SystemUsage getConsumerSystemUsage() throws IOException {
        if (this.consumerSystemUsaage == null) {
            if (this.splitSystemUsageForProducersConsumers) {
                this.consumerSystemUsaage = new SystemUsage(this.getSystemUsage(), "Consumer");
                final float portion = this.consumerSystemUsagePortion / 100.0f;
                this.consumerSystemUsaage.getMemoryUsage().setUsagePortion(portion);
                this.addService(this.consumerSystemUsaage);
            }
            else {
                this.consumerSystemUsaage = this.getSystemUsage();
            }
        }
        return this.consumerSystemUsaage;
    }
    
    public void setConsumerSystemUsage(final SystemUsage consumerSystemUsaage) {
        if (this.consumerSystemUsaage != null) {
            this.removeService(this.consumerSystemUsaage);
        }
        this.addService(this.consumerSystemUsaage = consumerSystemUsaage);
    }
    
    public SystemUsage getProducerSystemUsage() throws IOException {
        if (this.producerSystemUsage == null) {
            if (this.splitSystemUsageForProducersConsumers) {
                this.producerSystemUsage = new SystemUsage(this.getSystemUsage(), "Producer");
                final float portion = this.producerSystemUsagePortion / 100.0f;
                this.producerSystemUsage.getMemoryUsage().setUsagePortion(portion);
                this.addService(this.producerSystemUsage);
            }
            else {
                this.producerSystemUsage = this.getSystemUsage();
            }
        }
        return this.producerSystemUsage;
    }
    
    public void setProducerSystemUsage(final SystemUsage producerUsageManager) {
        if (this.producerSystemUsage != null) {
            this.removeService(this.producerSystemUsage);
        }
        this.addService(this.producerSystemUsage = producerUsageManager);
    }
    
    public PersistenceAdapter getPersistenceAdapter() throws IOException {
        if (this.persistenceAdapter == null) {
            this.configureService(this.persistenceAdapter = this.createPersistenceAdapter());
            this.persistenceAdapter = this.registerPersistenceAdapterMBean(this.persistenceAdapter);
        }
        return this.persistenceAdapter;
    }
    
    public void setPersistenceAdapter(final PersistenceAdapter persistenceAdapter) throws IOException {
        this.configureService(this.persistenceAdapter = persistenceAdapter);
        this.persistenceAdapter = this.registerPersistenceAdapterMBean(persistenceAdapter);
    }
    
    public TaskRunnerFactory getTaskRunnerFactory() {
        if (this.taskRunnerFactory == null) {
            this.taskRunnerFactory = new TaskRunnerFactory("BrokerService[" + this.getBrokerName() + "] Task", this.getTaskRunnerPriority(), true, 1000, this.isDedicatedTaskRunner());
        }
        return this.taskRunnerFactory;
    }
    
    public void setTaskRunnerFactory(final TaskRunnerFactory taskRunnerFactory) {
        this.taskRunnerFactory = taskRunnerFactory;
    }
    
    public TaskRunnerFactory getPersistenceTaskRunnerFactory() {
        if (this.taskRunnerFactory == null) {
            this.persistenceTaskRunnerFactory = new TaskRunnerFactory("Persistence Adaptor Task", this.persistenceThreadPriority, true, 1000, this.isDedicatedTaskRunner());
        }
        return this.persistenceTaskRunnerFactory;
    }
    
    public void setPersistenceTaskRunnerFactory(final TaskRunnerFactory persistenceTaskRunnerFactory) {
        this.persistenceTaskRunnerFactory = persistenceTaskRunnerFactory;
    }
    
    public boolean isUseJmx() {
        return this.useJmx;
    }
    
    public boolean isEnableStatistics() {
        return this.enableStatistics;
    }
    
    public void setEnableStatistics(final boolean enableStatistics) {
        this.enableStatistics = enableStatistics;
    }
    
    public void setUseJmx(final boolean useJmx) {
        this.useJmx = useJmx;
    }
    
    public ObjectName getBrokerObjectName() throws IOException {
        if (this.brokerObjectName == null) {
            this.brokerObjectName = this.createBrokerObjectName();
        }
        return this.brokerObjectName;
    }
    
    public void setBrokerObjectName(final ObjectName brokerObjectName) {
        this.brokerObjectName = brokerObjectName;
    }
    
    public ManagementContext getManagementContext() {
        if (this.managementContext == null) {
            this.managementContext = new ManagementContext();
        }
        return this.managementContext;
    }
    
    public void setManagementContext(final ManagementContext managementContext) {
        this.managementContext = managementContext;
    }
    
    public NetworkConnector getNetworkConnectorByName(final String connectorName) {
        for (final NetworkConnector connector : this.networkConnectors) {
            if (connector.getName().equals(connectorName)) {
                return connector;
            }
        }
        return null;
    }
    
    public String[] getNetworkConnectorURIs() {
        return this.networkConnectorURIs;
    }
    
    public void setNetworkConnectorURIs(final String[] networkConnectorURIs) {
        this.networkConnectorURIs = networkConnectorURIs;
    }
    
    public TransportConnector getConnectorByName(final String connectorName) {
        for (final TransportConnector connector : this.transportConnectors) {
            if (connector.getName().equals(connectorName)) {
                return connector;
            }
        }
        return null;
    }
    
    public Map<String, String> getTransportConnectorURIsAsMap() {
        final Map<String, String> answer = new HashMap<String, String>();
        for (final TransportConnector connector : this.transportConnectors) {
            try {
                final URI uri = connector.getConnectUri();
                final String scheme = uri.getScheme();
                if (scheme == null) {
                    continue;
                }
                answer.put(scheme.toLowerCase(), uri.toString());
            }
            catch (Exception e) {
                BrokerService.LOG.debug("Failed to read URI to build transportURIsAsMap", e);
            }
        }
        return answer;
    }
    
    public String[] getTransportConnectorURIs() {
        return this.transportConnectorURIs;
    }
    
    public void setTransportConnectorURIs(final String[] transportConnectorURIs) {
        this.transportConnectorURIs = transportConnectorURIs;
    }
    
    public JmsConnector[] getJmsBridgeConnectors() {
        return this.jmsBridgeConnectors;
    }
    
    public void setJmsBridgeConnectors(final JmsConnector[] jmsConnectors) {
        this.jmsBridgeConnectors = jmsConnectors;
    }
    
    public Service[] getServices() {
        return this.services.toArray(new Service[0]);
    }
    
    public void setServices(final Service[] services) {
        this.services.clear();
        if (services != null) {
            for (int i = 0; i < services.length; ++i) {
                this.services.add(services[i]);
            }
        }
    }
    
    public void addService(final Service service) {
        this.services.add(service);
    }
    
    public void removeService(final Service service) {
        this.services.remove(service);
    }
    
    public boolean isUseLoggingForShutdownErrors() {
        return this.useLoggingForShutdownErrors;
    }
    
    public void setUseLoggingForShutdownErrors(final boolean useLoggingForShutdownErrors) {
        this.useLoggingForShutdownErrors = useLoggingForShutdownErrors;
    }
    
    public boolean isUseShutdownHook() {
        return this.useShutdownHook;
    }
    
    public void setUseShutdownHook(final boolean useShutdownHook) {
        this.useShutdownHook = useShutdownHook;
    }
    
    public boolean isAdvisorySupport() {
        return this.advisorySupport;
    }
    
    public void setAdvisorySupport(final boolean advisorySupport) {
        this.advisorySupport = advisorySupport;
    }
    
    public List<TransportConnector> getTransportConnectors() {
        return new ArrayList<TransportConnector>(this.transportConnectors);
    }
    
    public void setTransportConnectors(final List<TransportConnector> transportConnectors) throws Exception {
        for (final TransportConnector connector : transportConnectors) {
            this.addConnector(connector);
        }
    }
    
    public List<NetworkConnector> getNetworkConnectors() {
        return new ArrayList<NetworkConnector>(this.networkConnectors);
    }
    
    public List<ProxyConnector> getProxyConnectors() {
        return new ArrayList<ProxyConnector>(this.proxyConnectors);
    }
    
    public void setNetworkConnectors(final List networkConnectors) throws Exception {
        for (final NetworkConnector connector : networkConnectors) {
            this.addNetworkConnector(connector);
        }
    }
    
    public void setProxyConnectors(final List proxyConnectors) throws Exception {
        for (final ProxyConnector connector : proxyConnectors) {
            this.addProxyConnector(connector);
        }
    }
    
    public PolicyMap getDestinationPolicy() {
        return this.destinationPolicy;
    }
    
    public void setDestinationPolicy(final PolicyMap policyMap) {
        this.destinationPolicy = policyMap;
    }
    
    public BrokerPlugin[] getPlugins() {
        return this.plugins;
    }
    
    public void setPlugins(final BrokerPlugin[] plugins) {
        this.plugins = plugins;
    }
    
    public MessageAuthorizationPolicy getMessageAuthorizationPolicy() {
        return this.messageAuthorizationPolicy;
    }
    
    public void setMessageAuthorizationPolicy(final MessageAuthorizationPolicy messageAuthorizationPolicy) {
        this.messageAuthorizationPolicy = messageAuthorizationPolicy;
    }
    
    public void deleteAllMessages() throws IOException {
        this.getPersistenceAdapter().deleteAllMessages();
    }
    
    public boolean isDeleteAllMessagesOnStartup() {
        return this.deleteAllMessagesOnStartup;
    }
    
    public void setDeleteAllMessagesOnStartup(final boolean deletePersistentMessagesOnStartup) {
        this.deleteAllMessagesOnStartup = deletePersistentMessagesOnStartup;
    }
    
    public URI getVmConnectorURI() {
        if (this.vmConnectorURI == null) {
            try {
                this.vmConnectorURI = new URI("vm://" + this.getBrokerName().replaceAll("[^a-zA-Z0-9\\.\\_\\-]", "_"));
            }
            catch (URISyntaxException e) {
                BrokerService.LOG.error("Badly formed URI from " + this.getBrokerName(), e);
            }
        }
        return this.vmConnectorURI;
    }
    
    public void setVmConnectorURI(final URI vmConnectorURI) {
        this.vmConnectorURI = vmConnectorURI;
    }
    
    public String getDefaultSocketURIString() {
        if (this.started.get()) {
            if (this.defaultSocketURIString == null) {
                for (final TransportConnector tc : this.transportConnectors) {
                    String result = null;
                    try {
                        result = tc.getPublishableConnectString();
                    }
                    catch (Exception e) {
                        BrokerService.LOG.warn("Failed to get the ConnectURI for " + tc, e);
                    }
                    if (result != null) {
                        this.defaultSocketURIString = result;
                        break;
                    }
                }
            }
            return this.defaultSocketURIString;
        }
        return null;
    }
    
    public boolean isShutdownOnMasterFailure() {
        return this.shutdownOnMasterFailure;
    }
    
    public void setShutdownOnMasterFailure(final boolean shutdownOnMasterFailure) {
        this.shutdownOnMasterFailure = shutdownOnMasterFailure;
    }
    
    public boolean isKeepDurableSubsActive() {
        return this.keepDurableSubsActive;
    }
    
    public void setKeepDurableSubsActive(final boolean keepDurableSubsActive) {
        this.keepDurableSubsActive = keepDurableSubsActive;
    }
    
    public boolean isUseVirtualTopics() {
        return this.useVirtualTopics;
    }
    
    public void setUseVirtualTopics(final boolean useVirtualTopics) {
        this.useVirtualTopics = useVirtualTopics;
    }
    
    public DestinationInterceptor[] getDestinationInterceptors() {
        return this.destinationInterceptors;
    }
    
    public boolean isUseMirroredQueues() {
        return this.useMirroredQueues;
    }
    
    public void setUseMirroredQueues(final boolean useMirroredQueues) {
        this.useMirroredQueues = useMirroredQueues;
    }
    
    public void setDestinationInterceptors(final DestinationInterceptor[] destinationInterceptors) {
        this.destinationInterceptors = destinationInterceptors;
    }
    
    public ActiveMQDestination[] getDestinations() {
        return this.destinations;
    }
    
    public void setDestinations(final ActiveMQDestination[] destinations) {
        this.destinations = destinations;
    }
    
    public synchronized PListStore getTempDataStore() {
        if (this.tempDataStore == null) {
            if (!this.isPersistent()) {
                return null;
            }
            boolean result = true;
            boolean empty = true;
            try {
                final File directory = this.getTmpDataDirectory();
                if (directory.exists() && directory.isDirectory()) {
                    final File[] files = directory.listFiles();
                    if (files != null && files.length > 0) {
                        empty = false;
                        for (int i = 0; i < files.length; ++i) {
                            final File file = files[i];
                            if (!file.isDirectory()) {
                                result &= file.delete();
                            }
                        }
                    }
                }
                if (!empty) {
                    final String str = result ? "Successfully deleted" : "Failed to delete";
                    BrokerService.LOG.info(str + " temporary storage");
                }
                (this.tempDataStore = new PListStore()).setDirectory(this.getTmpDataDirectory());
                this.tempDataStore.start();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return this.tempDataStore;
    }
    
    public void setTempDataStore(final PListStore tempDataStore) {
        this.tempDataStore = tempDataStore;
    }
    
    public int getPersistenceThreadPriority() {
        return this.persistenceThreadPriority;
    }
    
    public void setPersistenceThreadPriority(final int persistenceThreadPriority) {
        this.persistenceThreadPriority = persistenceThreadPriority;
    }
    
    public boolean isUseLocalHostBrokerName() {
        return this.useLocalHostBrokerName;
    }
    
    public void setUseLocalHostBrokerName(final boolean useLocalHostBrokerName) {
        this.useLocalHostBrokerName = useLocalHostBrokerName;
        if ((useLocalHostBrokerName && !this.started.get() && this.brokerName == null) || this.brokerName == "localhost") {
            this.brokerName = BrokerService.LOCAL_HOST_NAME;
        }
    }
    
    public boolean isSupportFailOver() {
        return this.supportFailOver;
    }
    
    public void setSupportFailOver(final boolean supportFailOver) {
        this.supportFailOver = supportFailOver;
    }
    
    public Destination getDestination(final ActiveMQDestination destination) throws Exception {
        return this.getBroker().addDestination(this.getAdminConnectionContext(), destination, false);
    }
    
    public void removeDestination(final ActiveMQDestination destination) throws Exception {
        this.getBroker().removeDestination(this.getAdminConnectionContext(), destination, 0L);
    }
    
    public int getProducerSystemUsagePortion() {
        return this.producerSystemUsagePortion;
    }
    
    public void setProducerSystemUsagePortion(final int producerSystemUsagePortion) {
        this.producerSystemUsagePortion = producerSystemUsagePortion;
    }
    
    public int getConsumerSystemUsagePortion() {
        return this.consumerSystemUsagePortion;
    }
    
    public void setConsumerSystemUsagePortion(final int consumerSystemUsagePortion) {
        this.consumerSystemUsagePortion = consumerSystemUsagePortion;
    }
    
    public boolean isSplitSystemUsageForProducersConsumers() {
        return this.splitSystemUsageForProducersConsumers;
    }
    
    public void setSplitSystemUsageForProducersConsumers(final boolean splitSystemUsageForProducersConsumers) {
        this.splitSystemUsageForProducersConsumers = splitSystemUsageForProducersConsumers;
    }
    
    public boolean isMonitorConnectionSplits() {
        return this.monitorConnectionSplits;
    }
    
    public void setMonitorConnectionSplits(final boolean monitorConnectionSplits) {
        this.monitorConnectionSplits = monitorConnectionSplits;
    }
    
    public int getTaskRunnerPriority() {
        return this.taskRunnerPriority;
    }
    
    public void setTaskRunnerPriority(final int taskRunnerPriority) {
        this.taskRunnerPriority = taskRunnerPriority;
    }
    
    public boolean isDedicatedTaskRunner() {
        return this.dedicatedTaskRunner;
    }
    
    public void setDedicatedTaskRunner(final boolean dedicatedTaskRunner) {
        this.dedicatedTaskRunner = dedicatedTaskRunner;
    }
    
    public boolean isCacheTempDestinations() {
        return this.cacheTempDestinations;
    }
    
    public void setCacheTempDestinations(final boolean cacheTempDestinations) {
        this.cacheTempDestinations = cacheTempDestinations;
    }
    
    public int getTimeBeforePurgeTempDestinations() {
        return this.timeBeforePurgeTempDestinations;
    }
    
    public void setTimeBeforePurgeTempDestinations(final int timeBeforePurgeTempDestinations) {
        this.timeBeforePurgeTempDestinations = timeBeforePurgeTempDestinations;
    }
    
    public boolean isUseTempMirroredQueues() {
        return this.useTempMirroredQueues;
    }
    
    public void setUseTempMirroredQueues(final boolean useTempMirroredQueues) {
        this.useTempMirroredQueues = useTempMirroredQueues;
    }
    
    protected void processHelperProperties() throws Exception {
        boolean masterServiceExists = false;
        if (this.transportConnectorURIs != null) {
            for (int i = 0; i < this.transportConnectorURIs.length; ++i) {
                final String uri = this.transportConnectorURIs[i];
                this.addConnector(uri);
            }
        }
        if (this.networkConnectorURIs != null) {
            for (int i = 0; i < this.networkConnectorURIs.length; ++i) {
                final String uri = this.networkConnectorURIs[i];
                this.addNetworkConnector(uri);
            }
        }
        if (this.jmsBridgeConnectors != null) {
            for (int i = 0; i < this.jmsBridgeConnectors.length; ++i) {
                this.addJmsConnector(this.jmsBridgeConnectors[i]);
            }
        }
        for (final Service service : this.services) {
            if (service instanceof MasterConnector) {
                masterServiceExists = true;
                break;
            }
        }
        if (this.masterConnectorURI != null) {
            if (masterServiceExists) {
                throw new java.lang.IllegalStateException("Cannot specify masterConnectorURI when a masterConnector is already registered via the services property");
            }
            this.addService(new MasterConnector(this.masterConnectorURI));
        }
    }
    
    public void stopAllConnectors(final ServiceStopper stopper) {
        for (final NetworkConnector connector : this.getNetworkConnectors()) {
            this.unregisterNetworkConnectorMBean(connector);
            stopper.stop(connector);
        }
        for (final ProxyConnector connector2 : this.getProxyConnectors()) {
            stopper.stop(connector2);
        }
        for (final JmsConnector connector3 : this.jmsConnectors) {
            stopper.stop(connector3);
        }
        for (final TransportConnector connector4 : this.getTransportConnectors()) {
            stopper.stop(connector4);
        }
    }
    
    protected TransportConnector registerConnectorMBean(TransportConnector connector) throws IOException {
        try {
            final ObjectName objectName = this.createConnectorObjectName(connector);
            connector = connector.asManagedConnector(this.getManagementContext(), objectName);
            final ConnectorViewMBean view = new ConnectorView(connector);
            AnnotatedMBean.registerMBean(this.getManagementContext(), view, objectName);
            return connector;
        }
        catch (Throwable e) {
            throw IOExceptionSupport.create("Transport Connector could not be registered in JMX: " + e.getMessage(), e);
        }
    }
    
    protected void unregisterConnectorMBean(final TransportConnector connector) throws IOException {
        if (this.isUseJmx()) {
            try {
                final ObjectName objectName = this.createConnectorObjectName(connector);
                this.getManagementContext().unregisterMBean(objectName);
            }
            catch (Throwable e) {
                throw IOExceptionSupport.create("Transport Connector could not be unregistered in JMX: " + e.getMessage(), e);
            }
        }
    }
    
    protected PersistenceAdapter registerPersistenceAdapterMBean(final PersistenceAdapter adaptor) throws IOException {
        return adaptor;
    }
    
    protected void unregisterPersistenceAdapterMBean(final PersistenceAdapter adaptor) throws IOException {
        if (this.isUseJmx()) {}
    }
    
    private ObjectName createConnectorObjectName(final TransportConnector connector) throws MalformedObjectNameException {
        return new ObjectName(this.getManagementContext().getJmxDomainName() + ":" + "BrokerName=" + JMXSupport.encodeObjectNamePart(this.getBrokerName()) + "," + "Type=Connector," + "ConnectorName=" + JMXSupport.encodeObjectNamePart(connector.getName()));
    }
    
    protected void registerNetworkConnectorMBean(final NetworkConnector connector) throws IOException {
        final NetworkConnectorViewMBean view = new NetworkConnectorView(connector);
        try {
            final ObjectName objectName = this.createNetworkConnectorObjectName(connector);
            connector.setObjectName(objectName);
            AnnotatedMBean.registerMBean(this.getManagementContext(), view, objectName);
        }
        catch (Throwable e) {
            throw IOExceptionSupport.create("Network Connector could not be registered in JMX: " + e.getMessage(), e);
        }
    }
    
    protected ObjectName createNetworkConnectorObjectName(final NetworkConnector connector) throws MalformedObjectNameException {
        return new ObjectName(this.getManagementContext().getJmxDomainName() + ":" + "BrokerName=" + JMXSupport.encodeObjectNamePart(this.getBrokerName()) + "," + "Type=NetworkConnector," + "NetworkConnectorName=" + JMXSupport.encodeObjectNamePart(connector.getName()));
    }
    
    public ObjectName createDuplexNetworkConnectorObjectName(final String transport) throws MalformedObjectNameException {
        return new ObjectName(this.getManagementContext().getJmxDomainName() + ":" + "BrokerName=" + JMXSupport.encodeObjectNamePart(this.getBrokerName()) + "," + "Type=NetworkConnector," + "NetworkConnectorName=duplex" + JMXSupport.encodeObjectNamePart(transport));
    }
    
    protected void unregisterNetworkConnectorMBean(final NetworkConnector connector) {
        if (this.isUseJmx()) {
            try {
                final ObjectName objectName = this.createNetworkConnectorObjectName(connector);
                this.getManagementContext().unregisterMBean(objectName);
            }
            catch (Exception e) {
                BrokerService.LOG.error("Network Connector could not be unregistered from JMX: " + e, e);
            }
        }
    }
    
    protected void registerProxyConnectorMBean(final ProxyConnector connector) throws IOException {
        final ProxyConnectorView view = new ProxyConnectorView(connector);
        try {
            final ObjectName objectName = new ObjectName(this.getManagementContext().getJmxDomainName() + ":" + "BrokerName=" + JMXSupport.encodeObjectNamePart(this.getBrokerName()) + "," + "Type=ProxyConnector," + "ProxyConnectorName=" + JMXSupport.encodeObjectNamePart(connector.getName()));
            AnnotatedMBean.registerMBean(this.getManagementContext(), view, objectName);
        }
        catch (Throwable e) {
            throw IOExceptionSupport.create("Broker could not be registered in JMX: " + e.getMessage(), e);
        }
    }
    
    protected void registerFTConnectorMBean(final MasterConnector connector) throws IOException {
        final FTConnectorView view = new FTConnectorView(connector);
        try {
            final ObjectName objectName = new ObjectName(this.getManagementContext().getJmxDomainName() + ":" + "BrokerName=" + JMXSupport.encodeObjectNamePart(this.getBrokerName()) + "," + "Type=MasterConnector");
            AnnotatedMBean.registerMBean(this.getManagementContext(), view, objectName);
        }
        catch (Throwable e) {
            throw IOExceptionSupport.create("Broker could not be registered in JMX: " + e.getMessage(), e);
        }
    }
    
    protected void registerJmsConnectorMBean(final JmsConnector connector) throws IOException {
        final JmsConnectorView view = new JmsConnectorView(connector);
        try {
            final ObjectName objectName = new ObjectName(this.getManagementContext().getJmxDomainName() + ":" + "BrokerName=" + JMXSupport.encodeObjectNamePart(this.getBrokerName()) + "," + "Type=JmsConnector," + "JmsConnectorName=" + JMXSupport.encodeObjectNamePart(connector.getName()));
            AnnotatedMBean.registerMBean(this.getManagementContext(), view, objectName);
        }
        catch (Throwable e) {
            throw IOExceptionSupport.create("Broker could not be registered in JMX: " + e.getMessage(), e);
        }
    }
    
    protected Broker createBroker() throws Exception {
        this.regionBroker = this.createRegionBroker();
        Broker broker = this.addInterceptors(this.regionBroker);
        broker = new MutableBrokerFilter(broker) {
            Broker old;
            
            @Override
            public void stop() throws Exception {
                (this.old = this.next.getAndSet(new ErrorBroker("Broker has been stopped: " + this) {
                    @Override
                    public void stop() throws Exception {
                    }
                })).stop();
            }
            
            @Override
            public void start() throws Exception {
                if (BrokerService.this.forceStart && this.old != null) {
                    this.next.set(this.old);
                }
                this.getNext().start();
            }
        };
        return broker;
    }
    
    protected Broker createRegionBroker() throws Exception {
        if (this.destinationInterceptors == null) {
            this.destinationInterceptors = this.createDefaultDestinationInterceptor();
        }
        this.configureServices(this.destinationInterceptors);
        final DestinationInterceptor destinationInterceptor = new CompositeDestinationInterceptor(this.destinationInterceptors);
        if (this.destinationFactory == null) {
            this.destinationFactory = new DestinationFactoryImpl(this, this.getTaskRunnerFactory(), this.getPersistenceAdapter());
        }
        return this.createRegionBroker(destinationInterceptor);
    }
    
    protected Broker createRegionBroker(final DestinationInterceptor destinationInterceptor) throws IOException {
        RegionBroker regionBroker;
        if (this.isUseJmx()) {
            regionBroker = new ManagedRegionBroker(this, this.getManagementContext(), this.getBrokerObjectName(), this.getTaskRunnerFactory(), this.getConsumerSystemUsage(), this.destinationFactory, destinationInterceptor, this.getScheduler(), this.getExecutor());
        }
        else {
            regionBroker = new RegionBroker(this, this.getTaskRunnerFactory(), this.getConsumerSystemUsage(), this.destinationFactory, destinationInterceptor, this.getScheduler(), this.getExecutor());
        }
        this.destinationFactory.setRegionBroker(regionBroker);
        regionBroker.setKeepDurableSubsActive(this.keepDurableSubsActive);
        regionBroker.setBrokerName(this.getBrokerName());
        regionBroker.getDestinationStatistics().setEnabled(this.enableStatistics);
        if (this.brokerId != null) {
            regionBroker.setBrokerId(this.brokerId);
        }
        return regionBroker;
    }
    
    protected DestinationInterceptor[] createDefaultDestinationInterceptor() {
        final List<DestinationInterceptor> answer = new ArrayList<DestinationInterceptor>();
        if (this.isUseVirtualTopics()) {
            final VirtualDestinationInterceptor interceptor = new VirtualDestinationInterceptor();
            final VirtualTopic virtualTopic = new VirtualTopic();
            virtualTopic.setName("VirtualTopic.>");
            final VirtualDestination[] virtualDestinations = { virtualTopic };
            interceptor.setVirtualDestinations(virtualDestinations);
            answer.add(interceptor);
        }
        if (this.isUseMirroredQueues()) {
            final MirroredQueue interceptor2 = new MirroredQueue();
            answer.add(interceptor2);
        }
        final DestinationInterceptor[] array = new DestinationInterceptor[answer.size()];
        answer.toArray(array);
        return array;
    }
    
    protected Broker addInterceptors(Broker broker) throws Exception {
        if (this.isSchedulerSupport()) {
            final SchedulerBroker sb = new SchedulerBroker(broker, this.getSchedulerDirectoryFile());
            if (this.isUseJmx()) {
                final JobSchedulerViewMBean view = new JobSchedulerView(sb.getJobScheduler());
                try {
                    final ObjectName objectName = new ObjectName(this.getManagementContext().getJmxDomainName() + ":" + "BrokerName=" + JMXSupport.encodeObjectNamePart(this.getBrokerName()) + "," + "Type=jobScheduler," + "jobSchedulerName=JMS");
                    AnnotatedMBean.registerMBean(this.getManagementContext(), view, objectName);
                    this.adminView.setJMSJobScheduler(objectName);
                }
                catch (Throwable e) {
                    throw IOExceptionSupport.create("JobScheduler could not be registered in JMX: " + e.getMessage(), e);
                }
            }
            broker = sb;
        }
        if (this.isAdvisorySupport()) {
            broker = new AdvisoryBroker(broker);
        }
        broker = new CompositeDestinationBroker(broker);
        broker = new TransactionBroker(broker, this.getPersistenceAdapter().createTransactionStore());
        if (this.isPopulateJMSXUserID()) {
            final UserIDBroker userIDBroker = new UserIDBroker(broker);
            userIDBroker.setUseAuthenticatePrincipal(this.isUseAuthenticatedPrincipalForJMSXUserID());
            broker = userIDBroker;
        }
        if (this.isMonitorConnectionSplits()) {
            broker = new ConnectionSplitBroker(broker);
        }
        if (this.plugins != null) {
            for (int i = 0; i < this.plugins.length; ++i) {
                final BrokerPlugin plugin = this.plugins[i];
                broker = plugin.installPlugin(broker);
            }
        }
        return broker;
    }
    
    protected PersistenceAdapter createPersistenceAdapter() throws IOException {
        if (!this.isPersistent()) {
            return new MemoryPersistenceAdapter();
        }
        final PersistenceAdapterFactory fac = this.getPersistenceFactory();
        if (fac != null) {
            return fac.createPersistenceAdapter();
        }
        final KahaDBPersistenceAdapter adaptor = new KahaDBPersistenceAdapter();
        final File dir = new File(this.getBrokerDataDirectory(), "KahaDB");
        adaptor.setDirectory(dir);
        return adaptor;
    }
    
    protected ObjectName createBrokerObjectName() throws IOException {
        try {
            return new ObjectName(this.getManagementContext().getJmxDomainName() + ":" + "BrokerName=" + JMXSupport.encodeObjectNamePart(this.getBrokerName()) + "," + "Type=Broker");
        }
        catch (Throwable e) {
            throw IOExceptionSupport.create("Invalid JMX broker name: " + this.brokerName, e);
        }
    }
    
    protected TransportConnector createTransportConnector(final URI brokerURI) throws Exception {
        final TransportServer transport = TransportFactory.bind(this, brokerURI);
        return new TransportConnector(transport);
    }
    
    protected Object getPort(final Map options) {
        Object port = options.get("port");
        if (port == null) {
            port = "61616";
            BrokerService.LOG.warn("No port specified so defaulting to: " + port);
        }
        return port;
    }
    
    protected void addShutdownHook() {
        if (this.useShutdownHook) {
            this.shutdownHook = new Thread("ActiveMQ ShutdownHook") {
                @Override
                public void run() {
                    BrokerService.this.containerShutdown();
                }
            };
            Runtime.getRuntime().addShutdownHook(this.shutdownHook);
        }
    }
    
    protected void removeShutdownHook() {
        if (this.shutdownHook != null) {
            try {
                Runtime.getRuntime().removeShutdownHook(this.shutdownHook);
            }
            catch (Exception e) {
                BrokerService.LOG.debug("Caught exception, must be shutting down: " + e);
            }
        }
    }
    
    public void setShutdownHooks(final List<Runnable> hooks) throws Exception {
        for (final Runnable hook : hooks) {
            this.addShutdownHook(hook);
        }
    }
    
    protected void containerShutdown() {
        try {
            this.stop();
        }
        catch (IOException e) {
            final Throwable linkedException = e.getCause();
            if (linkedException != null) {
                this.logError("Failed to shut down: " + e + ". Reason: " + linkedException, linkedException);
            }
            else {
                this.logError("Failed to shut down: " + e, e);
            }
            if (!this.useLoggingForShutdownErrors) {
                e.printStackTrace(System.err);
            }
        }
        catch (Exception e2) {
            this.logError("Failed to shut down: " + e2, e2);
        }
    }
    
    protected void logError(final String message, final Throwable e) {
        if (this.useLoggingForShutdownErrors) {
            BrokerService.LOG.error("Failed to shut down: " + e);
        }
        else {
            System.err.println("Failed to shut down: " + e);
        }
    }
    
    protected void startDestinations() throws Exception {
        if (this.destinations != null) {
            final ConnectionContext adminConnectionContext = this.getAdminConnectionContext();
            for (int i = 0; i < this.destinations.length; ++i) {
                final ActiveMQDestination destination = this.destinations[i];
                this.getBroker().addDestination(adminConnectionContext, destination, true);
            }
        }
    }
    
    public ConnectionContext getAdminConnectionContext() throws Exception {
        return BrokerSupport.getConnectionContext(this.getBroker());
    }
    
    protected void waitForSlave() {
        try {
            if (!this.slaveStartSignal.await(this.waitForSlaveTimeout, TimeUnit.MILLISECONDS)) {
                throw new java.lang.IllegalStateException("Gave up waiting for slave to start after " + this.waitForSlaveTimeout + " milliseconds.");
            }
        }
        catch (InterruptedException e) {
            BrokerService.LOG.error("Exception waiting for slave:" + e);
        }
    }
    
    protected void slaveConnectionEstablished() {
        this.slaveStartSignal.countDown();
    }
    
    protected void startManagementContext() throws Exception {
        this.getManagementContext().start();
        this.adminView = new BrokerView(this, null);
        final ObjectName objectName = this.getBrokerObjectName();
        AnnotatedMBean.registerMBean(this.getManagementContext(), this.adminView, objectName);
    }
    
    public void startAllConnectors() throws Exception {
        if (!this.isSlave()) {
            final Set<ActiveMQDestination> durableDestinations = this.getBroker().getDurableDestinations();
            final List<TransportConnector> al = new ArrayList<TransportConnector>();
            for (final TransportConnector connector : this.getTransportConnectors()) {
                connector.setBrokerService(this);
                al.add(this.startTransportConnector(connector));
            }
            if (al.size() > 0) {
                this.transportConnectors.clear();
                this.setTransportConnectors(al);
            }
            URI uri = this.getVmConnectorURI();
            final Map<String, String> map = new HashMap<String, String>(URISupport.parseParameters(uri));
            map.put("network", "true");
            map.put("async", "false");
            uri = URISupport.createURIWithQuery(uri, URISupport.createQueryString(map));
            if (this.isWaitForSlave()) {
                this.waitForSlave();
            }
            if (!this.stopped.get()) {
                ThreadPoolExecutor networkConnectorStartExecutor = null;
                if (this.isNetworkConnectorStartAsync()) {
                    networkConnectorStartExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
                        int count = 0;
                        
                        @Override
                        public Thread newThread(final Runnable runnable) {
                            final Thread thread = new Thread(runnable, "NetworkConnector Start Thread-" + this.count++);
                            thread.setDaemon(true);
                            return thread;
                        }
                    });
                }
                for (final NetworkConnector connector2 : this.getNetworkConnectors()) {
                    connector2.setLocalUri(uri);
                    connector2.setBrokerName(this.getBrokerName());
                    connector2.setDurableDestinations(durableDestinations);
                    if (this.getDefaultSocketURIString() != null) {
                        connector2.setBrokerURL(this.getDefaultSocketURIString());
                    }
                    if (networkConnectorStartExecutor != null) {
                        final Map context = MDCHelper.getCopyOfContextMap();
                        networkConnectorStartExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    MDCHelper.setContextMap(context);
                                    BrokerService.LOG.info("Async start of " + connector2);
                                    connector2.start();
                                }
                                catch (Exception e) {
                                    BrokerService.LOG.error("Async start of network connector: " + connector2 + " failed", e);
                                }
                            }
                        });
                    }
                    else {
                        connector2.start();
                    }
                }
                if (networkConnectorStartExecutor != null) {
                    networkConnectorStartExecutor.shutdown();
                    networkConnectorStartExecutor = null;
                }
                for (final ProxyConnector connector3 : this.getProxyConnectors()) {
                    connector3.start();
                }
                for (final JmsConnector connector4 : this.jmsConnectors) {
                    connector4.start();
                }
                for (final Service service : this.services) {
                    this.configureService(service);
                    service.start();
                }
            }
        }
    }
    
    protected TransportConnector startTransportConnector(TransportConnector connector) throws Exception {
        connector.setTaskRunnerFactory(this.getTaskRunnerFactory());
        final MessageAuthorizationPolicy policy = this.getMessageAuthorizationPolicy();
        if (policy != null) {
            connector.setMessageAuthorizationPolicy(policy);
        }
        if (this.isUseJmx()) {
            connector = this.registerConnectorMBean(connector);
        }
        connector.getStatistics().setEnabled(this.enableStatistics);
        connector.start();
        return connector;
    }
    
    protected void configureServices(final Object[] services) {
        for (final Object service : services) {
            this.configureService(service);
        }
    }
    
    protected void configureService(final Object service) {
        if (service instanceof BrokerServiceAware) {
            final BrokerServiceAware serviceAware = (BrokerServiceAware)service;
            serviceAware.setBrokerService(this);
        }
        if (this.masterConnector == null && service instanceof MasterConnector) {
            this.masterConnector = (MasterConnector)service;
            this.supportFailOver = true;
        }
    }
    
    public void handleIOException(final IOException exception) {
        if (this.ioExceptionHandler != null) {
            this.ioExceptionHandler.handle(exception);
        }
        else {
            BrokerService.LOG.info("Ignoring IO exception, " + exception, exception);
        }
    }
    
    protected void startDestinationsInPersistenceStore(final Broker broker) throws Exception {
        final Set destinations = this.destinationFactory.getDestinations();
        if (destinations != null) {
            final Iterator iter = destinations.iterator();
            ConnectionContext adminConnectionContext = broker.getAdminConnectionContext();
            if (adminConnectionContext == null) {
                final ConnectionContext context = new ConnectionContext();
                context.setBroker(broker);
                adminConnectionContext = context;
                broker.setAdminConnectionContext(adminConnectionContext);
            }
            while (iter.hasNext()) {
                final ActiveMQDestination destination = iter.next();
                broker.addDestination(adminConnectionContext, destination, false);
            }
        }
    }
    
    protected synchronized ThreadPoolExecutor getExecutor() {
        if (this.executor == null) {
            this.executor = new ThreadPoolExecutor(1, 10, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
                @Override
                public Thread newThread(final Runnable runnable) {
                    final Thread thread = new Thread(runnable, "Usage Async Task");
                    thread.setDaemon(true);
                    return thread;
                }
            });
        }
        return this.executor;
    }
    
    public synchronized Scheduler getScheduler() {
        if (this.scheduler == null) {
            this.scheduler = new Scheduler("ActiveMQ Broker[" + this.getBrokerName() + "] Scheduler");
            try {
                this.scheduler.start();
            }
            catch (Exception e) {
                BrokerService.LOG.error("Failed to start Scheduler ", e);
            }
        }
        return this.scheduler;
    }
    
    public Broker getRegionBroker() {
        return this.regionBroker;
    }
    
    public void setRegionBroker(final Broker regionBroker) {
        this.regionBroker = regionBroker;
    }
    
    public void addShutdownHook(final Runnable hook) {
        synchronized (this.shutdownHooks) {
            this.shutdownHooks.add(hook);
        }
    }
    
    public void removeShutdownHook(final Runnable hook) {
        synchronized (this.shutdownHooks) {
            this.shutdownHooks.remove(hook);
        }
    }
    
    public boolean isSystemExitOnShutdown() {
        return this.systemExitOnShutdown;
    }
    
    public void setSystemExitOnShutdown(final boolean systemExitOnShutdown) {
        this.systemExitOnShutdown = systemExitOnShutdown;
    }
    
    public int getSystemExitOnShutdownExitCode() {
        return this.systemExitOnShutdownExitCode;
    }
    
    public void setSystemExitOnShutdownExitCode(final int systemExitOnShutdownExitCode) {
        this.systemExitOnShutdownExitCode = systemExitOnShutdownExitCode;
    }
    
    public SslContext getSslContext() {
        return this.sslContext;
    }
    
    public void setSslContext(final SslContext sslContext) {
        this.sslContext = sslContext;
    }
    
    public boolean isShutdownOnSlaveFailure() {
        return this.shutdownOnSlaveFailure;
    }
    
    public void setShutdownOnSlaveFailure(final boolean shutdownOnSlaveFailure) {
        this.shutdownOnSlaveFailure = shutdownOnSlaveFailure;
    }
    
    public boolean isWaitForSlave() {
        return this.waitForSlave;
    }
    
    public void setWaitForSlave(final boolean waitForSlave) {
        this.waitForSlave = waitForSlave;
    }
    
    public long getWaitForSlaveTimeout() {
        return this.waitForSlaveTimeout;
    }
    
    public void setWaitForSlaveTimeout(final long waitForSlaveTimeout) {
        this.waitForSlaveTimeout = waitForSlaveTimeout;
    }
    
    public CountDownLatch getSlaveStartSignal() {
        return this.slaveStartSignal;
    }
    
    public boolean isPassiveSlave() {
        return this.passiveSlave;
    }
    
    public void setPassiveSlave(final boolean passiveSlave) {
        this.passiveSlave = passiveSlave;
    }
    
    public void setIoExceptionHandler(final IOExceptionHandler ioExceptionHandler) {
        this.configureService(ioExceptionHandler);
        this.ioExceptionHandler = ioExceptionHandler;
    }
    
    public IOExceptionHandler getIoExceptionHandler() {
        return this.ioExceptionHandler;
    }
    
    public boolean isSchedulerSupport() {
        return this.schedulerSupport;
    }
    
    public void setSchedulerSupport(final boolean schedulerSupport) {
        this.schedulerSupport = schedulerSupport;
    }
    
    public File getSchedulerDirectoryFile() {
        if (this.schedulerDirectoryFile == null) {
            this.schedulerDirectoryFile = new File(this.getBrokerDataDirectory(), "scheduler");
        }
        return this.schedulerDirectoryFile;
    }
    
    public void setSchedulerDirectoryFile(final File schedulerDirectory) {
        this.schedulerDirectoryFile = schedulerDirectory;
    }
    
    public void setSchedulerDirectory(final String schedulerDirectory) {
        this.setSchedulerDirectoryFile(new File(schedulerDirectory));
    }
    
    public int getSchedulePeriodForDestinationPurge() {
        return this.schedulePeriodForDestinationPurge;
    }
    
    public void setSchedulePeriodForDestinationPurge(final int schedulePeriodForDestinationPurge) {
        this.schedulePeriodForDestinationPurge = schedulePeriodForDestinationPurge;
    }
    
    public BrokerContext getBrokerContext() {
        return this.brokerContext;
    }
    
    public void setBrokerContext(final BrokerContext brokerContext) {
        this.brokerContext = brokerContext;
    }
    
    public void setBrokerId(final String brokerId) {
        this.brokerId = new BrokerId(brokerId);
    }
    
    public boolean isUseAuthenticatedPrincipalForJMSXUserID() {
        return this.useAuthenticatedPrincipalForJMSXUserID;
    }
    
    public void setUseAuthenticatedPrincipalForJMSXUserID(final boolean useAuthenticatedPrincipalForJMSXUserID) {
        this.useAuthenticatedPrincipalForJMSXUserID = useAuthenticatedPrincipalForJMSXUserID;
    }
    
    public boolean isNetworkConnectorStartAsync() {
        return this.networkConnectorStartAsync;
    }
    
    public void setNetworkConnectorStartAsync(final boolean networkConnectorStartAsync) {
        this.networkConnectorStartAsync = networkConnectorStartAsync;
    }
    
    static {
        LOG = LoggerFactory.getLogger(BrokerService.class);
        String localHostName = "localhost";
        try {
            localHostName = InetAddressUtil.getLocalHostName();
        }
        catch (UnknownHostException e) {
            BrokerService.LOG.error("Failed to resolve localhost");
        }
        LOCAL_HOST_NAME = localHostName;
    }
}
