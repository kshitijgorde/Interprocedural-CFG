// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.network;

import org.slf4j.LoggerFactory;
import org.apache.activemq.command.NetworkBridgeFilter;
import org.apache.activemq.filter.BooleanExpression;
import org.apache.activemq.broker.region.AbstractRegion;
import org.apache.activemq.broker.region.RegionBroker;
import java.util.Iterator;
import java.util.Collection;
import java.util.List;
import org.apache.activemq.broker.region.Subscription;
import org.apache.activemq.filter.DestinationFilter;
import org.apache.activemq.broker.region.MessageReference;
import org.apache.activemq.filter.MessageEvaluationContext;
import org.apache.activemq.transport.FutureResponse;
import org.apache.activemq.transport.ResponseCallback;
import java.util.Arrays;
import org.apache.activemq.command.TransactionId;
import org.apache.activemq.command.Message;
import org.apache.activemq.command.RemoveInfo;
import org.apache.activemq.command.ActiveMQTempDestination;
import org.apache.activemq.command.DestinationInfo;
import org.apache.activemq.command.DataStructure;
import org.apache.activemq.command.Response;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ConnectionError;
import org.apache.activemq.command.MessageAck;
import org.apache.activemq.command.MessageDispatch;
import org.apache.activemq.util.ServiceSupport;
import java.security.GeneralSecurityException;
import org.apache.activemq.broker.Connection;
import org.apache.activemq.Service;
import org.apache.activemq.util.ServiceStopper;
import java.util.concurrent.TimeUnit;
import org.apache.activemq.command.ShutdownInfo;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.advisory.AdvisorySupport;
import org.apache.activemq.util.MarshallingSupport;
import org.apache.activemq.util.IntrospectionSupport;
import java.util.Properties;
import java.security.cert.X509Certificate;
import org.apache.activemq.command.ExceptionResponse;
import org.apache.activemq.transport.tcp.SslTransport;
import org.apache.activemq.transport.TransportFilter;
import org.apache.activemq.command.ConnectionId;
import java.util.Map;
import org.apache.activemq.util.MDCHelper;
import org.apache.activemq.transport.TransportDisposedIOException;
import org.apache.activemq.transport.TransportListener;
import java.io.IOException;
import org.apache.activemq.transport.DefaultTransportListener;
import org.apache.activemq.command.Command;
import org.apache.activemq.thread.DefaultThreadPools;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnection;
import org.apache.activemq.command.BrokerInfo;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.CountDownLatch;
import org.apache.activemq.command.ConsumerId;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.BrokerId;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.activemq.command.ConsumerInfo;
import org.apache.activemq.command.ProducerInfo;
import org.apache.activemq.command.SessionInfo;
import org.apache.activemq.command.ConnectionInfo;
import org.apache.activemq.util.LongSequenceGenerator;
import org.apache.activemq.util.IdGenerator;
import org.apache.activemq.transport.Transport;
import org.apache.activemq.thread.TaskRunnerFactory;
import org.slf4j.Logger;
import org.apache.activemq.broker.BrokerServiceAware;

public abstract class DemandForwardingBridgeSupport implements NetworkBridge, BrokerServiceAware
{
    private static final Logger LOG;
    private final TaskRunnerFactory asyncTaskRunner;
    protected static final String DURABLE_SUB_PREFIX = "NC-DS_";
    protected final Transport localBroker;
    protected final Transport remoteBroker;
    protected final IdGenerator idGenerator;
    protected final LongSequenceGenerator consumerIdGenerator;
    protected ConnectionInfo localConnectionInfo;
    protected ConnectionInfo remoteConnectionInfo;
    protected SessionInfo localSessionInfo;
    protected ProducerInfo producerInfo;
    protected String remoteBrokerName;
    protected String localClientId;
    protected ConsumerInfo demandConsumerInfo;
    protected int demandConsumerDispatched;
    protected final AtomicBoolean localBridgeStarted;
    protected final AtomicBoolean remoteBridgeStarted;
    protected AtomicBoolean disposed;
    protected BrokerId localBrokerId;
    protected ActiveMQDestination[] excludedDestinations;
    protected ActiveMQDestination[] dynamicallyIncludedDestinations;
    protected ActiveMQDestination[] staticallyIncludedDestinations;
    protected ActiveMQDestination[] durableDestinations;
    protected final ConcurrentHashMap<ConsumerId, DemandSubscription> subscriptionMapByLocalId;
    protected final ConcurrentHashMap<ConsumerId, DemandSubscription> subscriptionMapByRemoteId;
    protected final BrokerId[] localBrokerPath;
    protected CountDownLatch startedLatch;
    protected CountDownLatch localStartedLatch;
    protected CountDownLatch remoteBrokerNameKnownLatch;
    protected CountDownLatch localBrokerIdKnownLatch;
    protected final AtomicBoolean remoteInterupted;
    protected final AtomicBoolean lastConnectSucceeded;
    protected NetworkBridgeConfiguration configuration;
    final AtomicLong enqueueCounter;
    final AtomicLong dequeueCounter;
    private NetworkBridgeListener networkBridgeListener;
    private boolean createdByDuplex;
    private BrokerInfo localBrokerInfo;
    private BrokerInfo remoteBrokerInfo;
    private final AtomicBoolean started;
    private TransportConnection duplexInitiatingConnection;
    private BrokerService brokerService;
    
    public DemandForwardingBridgeSupport(final NetworkBridgeConfiguration configuration, final Transport localBroker, final Transport remoteBroker) {
        this.asyncTaskRunner = DefaultThreadPools.getDefaultTaskRunnerFactory();
        this.idGenerator = new IdGenerator();
        this.consumerIdGenerator = new LongSequenceGenerator();
        this.remoteBrokerName = "Unknown";
        this.localBridgeStarted = new AtomicBoolean(false);
        this.remoteBridgeStarted = new AtomicBoolean(false);
        this.disposed = new AtomicBoolean();
        this.subscriptionMapByLocalId = new ConcurrentHashMap<ConsumerId, DemandSubscription>();
        this.subscriptionMapByRemoteId = new ConcurrentHashMap<ConsumerId, DemandSubscription>();
        this.localBrokerPath = new BrokerId[] { null };
        this.startedLatch = new CountDownLatch(2);
        this.localStartedLatch = new CountDownLatch(1);
        this.remoteBrokerNameKnownLatch = new CountDownLatch(1);
        this.localBrokerIdKnownLatch = new CountDownLatch(1);
        this.remoteInterupted = new AtomicBoolean(false);
        this.lastConnectSucceeded = new AtomicBoolean(false);
        this.enqueueCounter = new AtomicLong();
        this.dequeueCounter = new AtomicLong();
        this.started = new AtomicBoolean();
        this.brokerService = null;
        this.configuration = configuration;
        this.localBroker = localBroker;
        this.remoteBroker = remoteBroker;
    }
    
    public void duplexStart(final TransportConnection connection, final BrokerInfo localBrokerInfo, final BrokerInfo remoteBrokerInfo) throws Exception {
        this.localBrokerInfo = localBrokerInfo;
        this.remoteBrokerInfo = remoteBrokerInfo;
        this.duplexInitiatingConnection = connection;
        this.start();
        this.serviceRemoteCommand(remoteBrokerInfo);
    }
    
    @Override
    public void start() throws Exception {
        if (this.started.compareAndSet(false, true)) {
            this.localBroker.setTransportListener(new DefaultTransportListener() {
                @Override
                public void onCommand(final Object o) {
                    final Command command = (Command)o;
                    DemandForwardingBridgeSupport.this.serviceLocalCommand(command);
                }
                
                @Override
                public void onException(final IOException error) {
                    DemandForwardingBridgeSupport.this.serviceLocalException(error);
                }
            });
            this.remoteBroker.setTransportListener(new TransportListener() {
                @Override
                public void onCommand(final Object o) {
                    final Command command = (Command)o;
                    DemandForwardingBridgeSupport.this.serviceRemoteCommand(command);
                }
                
                @Override
                public void onException(final IOException error) {
                    DemandForwardingBridgeSupport.this.serviceRemoteException(error);
                }
                
                @Override
                public void transportInterupted() {
                    if (DemandForwardingBridgeSupport.this.remoteInterupted.compareAndSet(false, true)) {
                        DemandForwardingBridgeSupport.LOG.info("Outbound transport to " + DemandForwardingBridgeSupport.this.remoteBrokerName + " interrupted.");
                        if (DemandForwardingBridgeSupport.this.localBridgeStarted.get()) {
                            DemandForwardingBridgeSupport.this.clearDownSubscriptions();
                            synchronized (DemandForwardingBridgeSupport.this) {
                                try {
                                    DemandForwardingBridgeSupport.this.localBroker.oneway(DemandForwardingBridgeSupport.this.localConnectionInfo.createRemoveCommand());
                                }
                                catch (TransportDisposedIOException td) {
                                    DemandForwardingBridgeSupport.LOG.debug("local broker is now disposed", td);
                                }
                                catch (IOException e) {
                                    DemandForwardingBridgeSupport.LOG.warn("Caught exception from local start", e);
                                }
                            }
                        }
                        DemandForwardingBridgeSupport.this.localBridgeStarted.set(false);
                        DemandForwardingBridgeSupport.this.remoteBridgeStarted.set(false);
                        DemandForwardingBridgeSupport.this.startedLatch = new CountDownLatch(2);
                        DemandForwardingBridgeSupport.this.localStartedLatch = new CountDownLatch(1);
                    }
                }
                
                @Override
                public void transportResumed() {
                    if (DemandForwardingBridgeSupport.this.remoteInterupted.compareAndSet(true, false)) {
                        if (!DemandForwardingBridgeSupport.this.lastConnectSucceeded.get()) {
                            try {
                                DemandForwardingBridgeSupport.LOG.debug("Previous connection was never fully established. Sleeping for second to avoid busy loop.");
                                Thread.sleep(1000L);
                            }
                            catch (InterruptedException e2) {
                                Thread.currentThread().interrupt();
                            }
                        }
                        DemandForwardingBridgeSupport.this.lastConnectSucceeded.set(false);
                        try {
                            DemandForwardingBridgeSupport.this.startLocalBridge();
                            DemandForwardingBridgeSupport.this.remoteBridgeStarted.set(true);
                            DemandForwardingBridgeSupport.this.startedLatch.countDown();
                            DemandForwardingBridgeSupport.LOG.info("Outbound transport to " + DemandForwardingBridgeSupport.this.remoteBrokerName + " resumed");
                        }
                        catch (Throwable e) {
                            DemandForwardingBridgeSupport.LOG.error("Caught exception  from local start in resume transport", e);
                            DemandForwardingBridgeSupport.this.serviceLocalException(e);
                        }
                    }
                }
            });
            this.localBroker.start();
            this.remoteBroker.start();
            if (this.disposed.get()) {
                DemandForwardingBridgeSupport.LOG.warn("Bridge was disposed before the start() method was fully executed.");
                throw new TransportDisposedIOException();
            }
            try {
                this.triggerRemoteStartBridge();
            }
            catch (IOException e) {
                DemandForwardingBridgeSupport.LOG.warn("Caught exception from remote start", e);
            }
        }
    }
    
    protected void triggerLocalStartBridge() throws IOException {
        final Map context = MDCHelper.getCopyOfContextMap();
        this.asyncTaskRunner.execute(new Runnable() {
            @Override
            public void run() {
                MDCHelper.setContextMap(context);
                final String originalName = Thread.currentThread().getName();
                Thread.currentThread().setName("StartLocalBridge: localBroker=" + DemandForwardingBridgeSupport.this.localBroker);
                try {
                    DemandForwardingBridgeSupport.this.startLocalBridge();
                }
                catch (Throwable e) {
                    DemandForwardingBridgeSupport.this.serviceLocalException(e);
                }
                finally {
                    Thread.currentThread().setName(originalName);
                }
            }
        });
    }
    
    protected void triggerRemoteStartBridge() throws IOException {
        final Map context = MDCHelper.getCopyOfContextMap();
        this.asyncTaskRunner.execute(new Runnable() {
            @Override
            public void run() {
                MDCHelper.setContextMap(context);
                final String originalName = Thread.currentThread().getName();
                Thread.currentThread().setName("StartRemotelBridge: localBroker=" + DemandForwardingBridgeSupport.this.localBroker);
                try {
                    DemandForwardingBridgeSupport.this.startRemoteBridge();
                }
                catch (Exception e) {
                    DemandForwardingBridgeSupport.this.serviceRemoteException(e);
                }
                finally {
                    Thread.currentThread().setName(originalName);
                }
            }
        });
    }
    
    protected void startLocalBridge() throws Throwable {
        if (this.localBridgeStarted.compareAndSet(false, true)) {
            synchronized (this) {
                if (DemandForwardingBridgeSupport.LOG.isTraceEnabled()) {
                    DemandForwardingBridgeSupport.LOG.trace(this.configuration.getBrokerName() + " starting local Bridge, localBroker=" + this.localBroker);
                }
                this.remoteBrokerNameKnownLatch.await();
                if (!this.disposed.get()) {
                    (this.localConnectionInfo = new ConnectionInfo()).setConnectionId(new ConnectionId(this.idGenerator.generateId()));
                    this.localClientId = this.configuration.getName() + "_" + this.remoteBrokerName + "_inbound_" + this.configuration.getBrokerName();
                    this.localConnectionInfo.setClientId(this.localClientId);
                    this.localConnectionInfo.setUserName(this.configuration.getUserName());
                    this.localConnectionInfo.setPassword(this.configuration.getPassword());
                    Transport originalTransport;
                    for (originalTransport = this.remoteBroker; originalTransport instanceof TransportFilter; originalTransport = ((TransportFilter)originalTransport).getNext()) {}
                    if (originalTransport instanceof SslTransport) {
                        final X509Certificate[] peerCerts = ((SslTransport)originalTransport).getPeerCertificates();
                        this.localConnectionInfo.setTransportContext(peerCerts);
                    }
                    final Object resp = this.localBroker.request(this.localConnectionInfo);
                    if (resp instanceof ExceptionResponse) {
                        throw ((ExceptionResponse)resp).getException();
                    }
                    this.localSessionInfo = new SessionInfo(this.localConnectionInfo, 1L);
                    this.localBroker.oneway(this.localSessionInfo);
                    this.brokerService.getBroker().networkBridgeStarted(this.remoteBrokerInfo, this.createdByDuplex);
                    final NetworkBridgeListener l = this.networkBridgeListener;
                    if (l != null) {
                        l.onStart(this);
                    }
                    DemandForwardingBridgeSupport.LOG.info("Network connection between " + this.localBroker + " and " + this.remoteBroker + "(" + this.remoteBrokerName + ") has been established.");
                }
                else {
                    DemandForwardingBridgeSupport.LOG.warn("Bridge was disposed before the startLocalBridge() method was fully executed.");
                }
                this.startedLatch.countDown();
                this.localStartedLatch.countDown();
                if (!this.disposed.get()) {
                    this.setupStaticDestinations();
                }
                else {
                    DemandForwardingBridgeSupport.LOG.warn("Network connection between " + this.localBroker + " and " + this.remoteBroker + "(" + this.remoteBrokerName + ") was interrupted during establishment.");
                }
            }
        }
    }
    
    protected void startRemoteBridge() throws Exception {
        if (this.remoteBridgeStarted.compareAndSet(false, true)) {
            if (DemandForwardingBridgeSupport.LOG.isTraceEnabled()) {
                DemandForwardingBridgeSupport.LOG.trace(this.configuration.getBrokerName() + " starting remote Bridge, localBroker=" + this.localBroker);
            }
            synchronized (this) {
                if (!this.isCreatedByDuplex()) {
                    final BrokerInfo brokerInfo = new BrokerInfo();
                    brokerInfo.setBrokerName(this.configuration.getBrokerName());
                    brokerInfo.setBrokerURL(this.configuration.getBrokerURL());
                    brokerInfo.setNetworkConnection(true);
                    brokerInfo.setDuplexConnection(this.configuration.isDuplex());
                    final Properties props = new Properties();
                    IntrospectionSupport.getProperties(this.configuration, props, null);
                    final String str = MarshallingSupport.propertiesToString(props);
                    brokerInfo.setNetworkProperties(str);
                    brokerInfo.setBrokerId(this.localBrokerId);
                    this.remoteBroker.oneway(brokerInfo);
                }
                if (this.remoteConnectionInfo != null) {
                    this.remoteBroker.oneway(this.remoteConnectionInfo.createRemoveCommand());
                }
                (this.remoteConnectionInfo = new ConnectionInfo()).setConnectionId(new ConnectionId(this.idGenerator.generateId()));
                this.remoteConnectionInfo.setClientId(this.configuration.getName() + "_" + this.configuration.getBrokerName() + "_outbound");
                this.remoteConnectionInfo.setUserName(this.configuration.getUserName());
                this.remoteConnectionInfo.setPassword(this.configuration.getPassword());
                this.remoteBroker.oneway(this.remoteConnectionInfo);
                final SessionInfo remoteSessionInfo = new SessionInfo(this.remoteConnectionInfo, 1L);
                this.remoteBroker.oneway(remoteSessionInfo);
                (this.producerInfo = new ProducerInfo(remoteSessionInfo, 1L)).setResponseRequired(false);
                this.remoteBroker.oneway(this.producerInfo);
                (this.demandConsumerInfo = new ConsumerInfo(remoteSessionInfo, 1L)).setDispatchAsync(this.configuration.isDispatchAsync());
                String advisoryTopic = "ActiveMQ.Advisory.Consumer." + this.configuration.getDestinationFilter();
                if (this.configuration.isBridgeTempDestinations()) {
                    advisoryTopic = advisoryTopic + "," + AdvisorySupport.TEMP_DESTINATION_COMPOSITE_ADVISORY_TOPIC;
                }
                this.demandConsumerInfo.setDestination(new ActiveMQTopic(advisoryTopic));
                this.demandConsumerInfo.setPrefetchSize(this.configuration.getPrefetchSize());
                this.remoteBroker.oneway(this.demandConsumerInfo);
                this.startedLatch.countDown();
                if (!this.disposed.get()) {
                    this.triggerLocalStartBridge();
                }
            }
        }
    }
    
    @Override
    public void stop() throws Exception {
        if (this.started.compareAndSet(true, false)) {
            if (this.disposed.compareAndSet(false, true)) {
                DemandForwardingBridgeSupport.LOG.debug(" stopping " + this.configuration.getBrokerName() + " bridge to " + this.remoteBrokerName);
                final NetworkBridgeListener l = this.networkBridgeListener;
                if (l != null) {
                    l.onStop(this);
                }
                try {
                    this.remoteBridgeStarted.set(false);
                    final CountDownLatch sendShutdown = new CountDownLatch(1);
                    final Map map = MDCHelper.getCopyOfContextMap();
                    this.asyncTaskRunner.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                MDCHelper.setContextMap(map);
                                DemandForwardingBridgeSupport.this.localBroker.oneway(new ShutdownInfo());
                                sendShutdown.countDown();
                                DemandForwardingBridgeSupport.this.remoteBroker.oneway(new ShutdownInfo());
                            }
                            catch (Throwable e) {
                                DemandForwardingBridgeSupport.LOG.debug("Caught exception sending shutdown", e);
                            }
                            finally {
                                sendShutdown.countDown();
                            }
                        }
                    });
                    if (!sendShutdown.await(10L, TimeUnit.SECONDS)) {
                        DemandForwardingBridgeSupport.LOG.info("Network Could not shutdown in a timely manner");
                    }
                }
                finally {
                    final ServiceStopper ss = new ServiceStopper();
                    ss.stop(this.remoteBroker);
                    ss.stop(this.localBroker);
                    this.startedLatch.countDown();
                    this.startedLatch.countDown();
                    this.localStartedLatch.countDown();
                    ss.throwFirstException();
                }
            }
            this.brokerService.getBroker().removeBroker(null, this.remoteBrokerInfo);
            this.brokerService.getBroker().networkBridgeStopped(this.remoteBrokerInfo);
            DemandForwardingBridgeSupport.LOG.info(this.configuration.getBrokerName() + " bridge to " + this.remoteBrokerName + " stopped");
            this.remoteBrokerNameKnownLatch.countDown();
        }
    }
    
    @Override
    public void serviceRemoteException(final Throwable error) {
        if (!this.disposed.get()) {
            if (error instanceof SecurityException || error instanceof GeneralSecurityException) {
                DemandForwardingBridgeSupport.LOG.error("Network connection between " + this.localBroker + " and " + this.remoteBroker + " shutdown due to a remote error: " + error);
            }
            else {
                DemandForwardingBridgeSupport.LOG.warn("Network connection between " + this.localBroker + " and " + this.remoteBroker + " shutdown due to a remote error: " + error);
            }
            DemandForwardingBridgeSupport.LOG.debug("The remote Exception was: " + error, error);
            final Map map = MDCHelper.getCopyOfContextMap();
            this.asyncTaskRunner.execute(new Runnable() {
                @Override
                public void run() {
                    MDCHelper.setContextMap(map);
                    ServiceSupport.dispose(DemandForwardingBridgeSupport.this.getControllingService());
                }
            });
            this.fireBridgeFailed();
        }
    }
    
    protected void serviceRemoteCommand(final Command command) {
        if (!this.disposed.get()) {
            try {
                if (command.isMessageDispatch()) {
                    this.waitStarted();
                    final MessageDispatch md = (MessageDispatch)command;
                    this.serviceRemoteConsumerAdvisory(md.getMessage().getDataStructure());
                    ++this.demandConsumerDispatched;
                    if (this.demandConsumerDispatched > this.demandConsumerInfo.getPrefetchSize() * 0.75) {
                        this.remoteBroker.oneway(new MessageAck(md, (byte)2, this.demandConsumerDispatched));
                        this.demandConsumerDispatched = 0;
                    }
                }
                else if (command.isBrokerInfo()) {
                    this.lastConnectSucceeded.set(true);
                    this.remoteBrokerInfo = (BrokerInfo)command;
                    final Properties props = MarshallingSupport.stringToProperties(this.remoteBrokerInfo.getNetworkProperties());
                    try {
                        IntrospectionSupport.getProperties(this.configuration, props, null);
                        if (this.configuration.getExcludedDestinations() != null) {
                            this.excludedDestinations = this.configuration.getExcludedDestinations().toArray(new ActiveMQDestination[this.configuration.getExcludedDestinations().size()]);
                        }
                        if (this.configuration.getStaticallyIncludedDestinations() != null) {
                            this.staticallyIncludedDestinations = this.configuration.getStaticallyIncludedDestinations().toArray(new ActiveMQDestination[this.configuration.getStaticallyIncludedDestinations().size()]);
                        }
                        if (this.configuration.getDynamicallyIncludedDestinations() != null) {
                            this.dynamicallyIncludedDestinations = this.configuration.getDynamicallyIncludedDestinations().toArray(new ActiveMQDestination[this.configuration.getDynamicallyIncludedDestinations().size()]);
                        }
                    }
                    catch (Throwable t) {
                        DemandForwardingBridgeSupport.LOG.error("Error mapping remote destinations", t);
                    }
                    this.serviceRemoteBrokerInfo(command);
                    this.localBroker.oneway(command);
                    this.brokerService.getBroker().addBroker(null, this.remoteBrokerInfo);
                }
                else if (command.getClass() == ConnectionError.class) {
                    final ConnectionError ce = (ConnectionError)command;
                    this.serviceRemoteException(ce.getException());
                }
                else if (this.isDuplex()) {
                    if (command.isMessage()) {
                        final ActiveMQMessage message = (ActiveMQMessage)command;
                        if (AdvisorySupport.isConsumerAdvisoryTopic(message.getDestination()) || AdvisorySupport.isDestinationAdvisoryTopic(message.getDestination())) {
                            this.serviceRemoteConsumerAdvisory(message.getDataStructure());
                        }
                        else {
                            if (!this.isPermissableDestination(message.getDestination(), true)) {
                                return;
                            }
                            if (message.isResponseRequired()) {
                                final Response reply = new Response();
                                reply.setCorrelationId(message.getCommandId());
                                this.localBroker.oneway(message);
                                this.remoteBroker.oneway(reply);
                            }
                            else {
                                this.localBroker.oneway(message);
                            }
                        }
                    }
                    else {
                        switch (command.getDataStructureType()) {
                            case 3:
                            case 4:
                            case 6: {
                                this.localBroker.oneway(command);
                                break;
                            }
                            case 5: {
                                this.localStartedLatch.await();
                                if (!this.started.get()) {
                                    DemandForwardingBridgeSupport.LOG.warn("Stopping - ignoring ConsumerInfo: " + command);
                                    break;
                                }
                                if (!this.addConsumerInfo((ConsumerInfo)command)) {
                                    if (DemandForwardingBridgeSupport.LOG.isDebugEnabled()) {
                                        DemandForwardingBridgeSupport.LOG.debug("Ignoring ConsumerInfo: " + command);
                                        break;
                                    }
                                    break;
                                }
                                else {
                                    if (DemandForwardingBridgeSupport.LOG.isTraceEnabled()) {
                                        DemandForwardingBridgeSupport.LOG.trace("Adding ConsumerInfo: " + command);
                                        break;
                                    }
                                    break;
                                }
                                break;
                            }
                            case 11: {
                                DemandForwardingBridgeSupport.LOG.info("Stopping network bridge on shutdown of remote broker");
                                this.serviceRemoteException(new IOException(command.toString()));
                                break;
                            }
                            default: {
                                if (DemandForwardingBridgeSupport.LOG.isDebugEnabled()) {
                                    DemandForwardingBridgeSupport.LOG.debug("Ignoring remote command: " + command);
                                    break;
                                }
                                break;
                            }
                        }
                    }
                }
                else {
                    switch (command.getDataStructureType()) {
                        case 1:
                        case 10:
                        case 11: {
                            break;
                        }
                        default: {
                            DemandForwardingBridgeSupport.LOG.warn("Unexpected remote command: " + command);
                            break;
                        }
                    }
                }
            }
            catch (Throwable e) {
                if (DemandForwardingBridgeSupport.LOG.isDebugEnabled()) {
                    DemandForwardingBridgeSupport.LOG.debug("Exception processing remote command: " + command, e);
                }
                this.serviceRemoteException(e);
            }
        }
    }
    
    private void serviceRemoteConsumerAdvisory(final DataStructure data) throws IOException {
        final int networkTTL = this.configuration.getNetworkTTL();
        if (data.getClass() == ConsumerInfo.class) {
            final ConsumerInfo info = (ConsumerInfo)data;
            final BrokerId[] path = info.getBrokerPath();
            if (info.isBrowser()) {
                if (DemandForwardingBridgeSupport.LOG.isDebugEnabled()) {
                    DemandForwardingBridgeSupport.LOG.info(this.configuration.getBrokerName() + " Ignoring sub from " + this.remoteBrokerName + ", browsers explicitly suppressed");
                }
                return;
            }
            if (path != null && path.length >= networkTTL) {
                if (DemandForwardingBridgeSupport.LOG.isDebugEnabled()) {
                    DemandForwardingBridgeSupport.LOG.debug(this.configuration.getBrokerName() + " Ignoring sub from " + this.remoteBrokerName + ", restricted to " + networkTTL + " network hops only : " + info);
                }
                return;
            }
            if (contains(path, this.localBrokerPath[0])) {
                if (DemandForwardingBridgeSupport.LOG.isDebugEnabled()) {
                    DemandForwardingBridgeSupport.LOG.debug(this.configuration.getBrokerName() + " Ignoring sub from " + this.remoteBrokerName + ", already routed through this broker once : " + info);
                }
                return;
            }
            if (!this.isPermissableDestination(info.getDestination())) {
                if (DemandForwardingBridgeSupport.LOG.isDebugEnabled()) {
                    DemandForwardingBridgeSupport.LOG.debug(this.configuration.getBrokerName() + " Ignoring sub from " + this.remoteBrokerName + ", destination " + info.getDestination() + " is not permiited :" + info);
                }
                return;
            }
            synchronized (this.brokerService.getVmConnectorURI()) {
                if (this.addConsumerInfo(info)) {
                    if (DemandForwardingBridgeSupport.LOG.isDebugEnabled()) {
                        DemandForwardingBridgeSupport.LOG.debug(this.configuration.getBrokerName() + " bridging sub on " + this.localBroker + " from " + this.remoteBrokerName + " : " + info);
                    }
                }
                else if (DemandForwardingBridgeSupport.LOG.isDebugEnabled()) {
                    DemandForwardingBridgeSupport.LOG.debug(this.configuration.getBrokerName() + " Ignoring sub from " + this.remoteBrokerName + " as already subscribed to matching destination : " + info);
                }
            }
        }
        else if (data.getClass() == DestinationInfo.class) {
            final DestinationInfo destInfo = (DestinationInfo)data;
            final BrokerId[] path = destInfo.getBrokerPath();
            if (path != null && path.length >= networkTTL) {
                if (DemandForwardingBridgeSupport.LOG.isDebugEnabled()) {
                    DemandForwardingBridgeSupport.LOG.debug(this.configuration.getBrokerName() + " Ignoring destination " + destInfo + " restricted to " + networkTTL + " network hops only");
                }
                return;
            }
            if (contains(destInfo.getBrokerPath(), this.localBrokerPath[0])) {
                if (DemandForwardingBridgeSupport.LOG.isDebugEnabled()) {
                    DemandForwardingBridgeSupport.LOG.debug(this.configuration.getBrokerName() + " Ignoring destination " + destInfo + " already routed through this broker once");
                }
                return;
            }
            destInfo.setConnectionId(this.localConnectionInfo.getConnectionId());
            if (destInfo.getDestination() instanceof ActiveMQTempDestination) {
                final ActiveMQTempDestination tempDest = (ActiveMQTempDestination)destInfo.getDestination();
                tempDest.setConnectionId(this.localSessionInfo.getSessionId().getConnectionId());
            }
            destInfo.setBrokerPath(this.appendToBrokerPath(destInfo.getBrokerPath(), this.getRemoteBrokerPath()));
            if (DemandForwardingBridgeSupport.LOG.isTraceEnabled()) {
                DemandForwardingBridgeSupport.LOG.trace("bridging destination control command: " + destInfo);
            }
            this.localBroker.oneway(destInfo);
        }
        else if (data.getClass() == RemoveInfo.class) {
            final ConsumerId id = (ConsumerId)((RemoveInfo)data).getObjectId();
            this.removeDemandSubscription(id);
        }
    }
    
    @Override
    public void serviceLocalException(final Throwable error) {
        if (!this.disposed.get()) {
            DemandForwardingBridgeSupport.LOG.info("Network connection between " + this.localBroker + " and " + this.remoteBroker + " shutdown due to a local error: " + error);
            DemandForwardingBridgeSupport.LOG.debug("The local Exception was:" + error, error);
            final Map map = MDCHelper.getCopyOfContextMap();
            this.asyncTaskRunner.execute(new Runnable() {
                @Override
                public void run() {
                    MDCHelper.setContextMap(map);
                    ServiceSupport.dispose(DemandForwardingBridgeSupport.this.getControllingService());
                }
            });
            this.fireBridgeFailed();
        }
    }
    
    protected Service getControllingService() {
        return (Service)((this.duplexInitiatingConnection != null) ? this.duplexInitiatingConnection : this);
    }
    
    protected void addSubscription(final DemandSubscription sub) throws IOException {
        if (sub != null) {
            this.localBroker.oneway(sub.getLocalInfo());
        }
    }
    
    protected void removeSubscription(final DemandSubscription sub) throws IOException {
        if (sub != null) {
            if (DemandForwardingBridgeSupport.LOG.isDebugEnabled()) {
                DemandForwardingBridgeSupport.LOG.debug(this.configuration.getBrokerName() + " remove local subscription for remote " + sub.getRemoteInfo().getConsumerId());
            }
            this.subscriptionMapByLocalId.remove(sub.getLocalInfo().getConsumerId());
            final Map map = MDCHelper.getCopyOfContextMap();
            this.asyncTaskRunner.execute(new Runnable() {
                @Override
                public void run() {
                    MDCHelper.setContextMap(map);
                    sub.waitForCompletion();
                    try {
                        DemandForwardingBridgeSupport.this.localBroker.oneway(sub.getLocalInfo().createRemoveCommand());
                    }
                    catch (IOException e) {
                        DemandForwardingBridgeSupport.LOG.warn("failed to deliver remove command for local subscription, for remote " + sub.getRemoteInfo().getConsumerId(), e);
                    }
                }
            });
        }
    }
    
    protected Message configureMessage(final MessageDispatch md) {
        final Message message = md.getMessage().copy();
        message.setBrokerPath(this.appendToBrokerPath(message.getBrokerPath(), this.localBrokerPath));
        message.setProducerId(this.producerInfo.getProducerId());
        message.setDestination(md.getDestination());
        if (message.getOriginalTransactionId() == null) {
            message.setOriginalTransactionId(message.getTransactionId());
        }
        message.setTransactionId(null);
        return message;
    }
    
    protected void serviceLocalCommand(final Command command) {
        if (!this.disposed.get()) {
            try {
                if (command.isMessageDispatch()) {
                    this.enqueueCounter.incrementAndGet();
                    final MessageDispatch md = (MessageDispatch)command;
                    final DemandSubscription sub = this.subscriptionMapByLocalId.get(md.getConsumerId());
                    if (sub != null && md.getMessage() != null && sub.incrementOutstandingResponses()) {
                        if (this.suppressMessageDispatch(md, sub)) {
                            if (DemandForwardingBridgeSupport.LOG.isDebugEnabled()) {
                                DemandForwardingBridgeSupport.LOG.debug(this.configuration.getBrokerName() + " message not forwarded to " + this.remoteBrokerName + " because message came from there or fails networkTTL, brokerPath: " + Arrays.toString(md.getMessage().getBrokerPath()) + ", message: " + md.getMessage());
                            }
                            try {
                                this.localBroker.oneway(new MessageAck(md, (byte)4, 1));
                            }
                            finally {
                                sub.decrementOutstandingResponses();
                            }
                            return;
                        }
                        final Message message = this.configureMessage(md);
                        if (DemandForwardingBridgeSupport.LOG.isDebugEnabled()) {
                            DemandForwardingBridgeSupport.LOG.debug("bridging (" + this.configuration.getBrokerName() + " -> " + this.remoteBrokerName + ", consumer: " + md.getConsumerId() + ", destination " + message.getDestination() + ", brokerPath: " + Arrays.toString(message.getBrokerPath()) + ", message: " + message);
                        }
                        if (!message.isResponseRequired()) {
                            try {
                                this.remoteBroker.oneway(message);
                                this.localBroker.oneway(new MessageAck(md, (byte)4, 1));
                                this.dequeueCounter.incrementAndGet();
                            }
                            finally {
                                sub.decrementOutstandingResponses();
                            }
                        }
                        else {
                            final ResponseCallback callback = new ResponseCallback() {
                                @Override
                                public void onCompletion(final FutureResponse future) {
                                    try {
                                        final Response response = future.getResult();
                                        if (response.isException()) {
                                            final ExceptionResponse er = (ExceptionResponse)response;
                                            DemandForwardingBridgeSupport.this.serviceLocalException(er.getException());
                                        }
                                        else {
                                            DemandForwardingBridgeSupport.this.localBroker.oneway(new MessageAck(md, (byte)4, 1));
                                            DemandForwardingBridgeSupport.this.dequeueCounter.incrementAndGet();
                                        }
                                    }
                                    catch (IOException e) {
                                        DemandForwardingBridgeSupport.this.serviceLocalException(e);
                                    }
                                    finally {
                                        sub.decrementOutstandingResponses();
                                    }
                                }
                            };
                            this.remoteBroker.asyncRequest(message, callback);
                        }
                    }
                    else if (DemandForwardingBridgeSupport.LOG.isDebugEnabled()) {
                        DemandForwardingBridgeSupport.LOG.debug("No subscription registered with this network bridge for consumerId " + md.getConsumerId() + " for message: " + md.getMessage());
                    }
                }
                else if (command.isBrokerInfo()) {
                    this.localBrokerInfo = (BrokerInfo)command;
                    this.serviceLocalBrokerInfo(command);
                }
                else if (command.isShutdownInfo()) {
                    DemandForwardingBridgeSupport.LOG.info(this.configuration.getBrokerName() + " Shutting down");
                    if (!this.remoteInterupted.get()) {
                        this.stop();
                    }
                }
                else if (command.getClass() == ConnectionError.class) {
                    final ConnectionError ce = (ConnectionError)command;
                    this.serviceLocalException(ce.getException());
                }
                else {
                    switch (command.getDataStructureType()) {
                        case 1: {
                            break;
                        }
                        default: {
                            DemandForwardingBridgeSupport.LOG.warn("Unexpected local command: " + command);
                            break;
                        }
                    }
                }
            }
            catch (Throwable e) {
                DemandForwardingBridgeSupport.LOG.warn("Caught an exception processing local command", e);
                this.serviceLocalException(e);
            }
        }
    }
    
    private boolean suppressMessageDispatch(final MessageDispatch md, final DemandSubscription sub) throws Exception {
        boolean suppress = false;
        final Object consumerInfo = md.getMessage().getDataStructure();
        if (consumerInfo != null && consumerInfo instanceof ConsumerInfo) {
            suppress = contains(((ConsumerInfo)consumerInfo).getBrokerPath(), this.remoteBrokerInfo.getBrokerId());
        }
        if (!suppress && sub.getLocalInfo().isDurable()) {
            final MessageEvaluationContext messageEvalContext = new MessageEvaluationContext();
            messageEvalContext.setMessageReference(md.getMessage());
            suppress = !this.createNetworkBridgeFilter(null).matches(messageEvalContext);
        }
        return suppress;
    }
    
    public ActiveMQDestination[] getDynamicallyIncludedDestinations() {
        return this.dynamicallyIncludedDestinations;
    }
    
    public void setDynamicallyIncludedDestinations(final ActiveMQDestination[] dynamicallyIncludedDestinations) {
        this.dynamicallyIncludedDestinations = dynamicallyIncludedDestinations;
    }
    
    public ActiveMQDestination[] getExcludedDestinations() {
        return this.excludedDestinations;
    }
    
    public void setExcludedDestinations(final ActiveMQDestination[] excludedDestinations) {
        this.excludedDestinations = excludedDestinations;
    }
    
    public ActiveMQDestination[] getStaticallyIncludedDestinations() {
        return this.staticallyIncludedDestinations;
    }
    
    public void setStaticallyIncludedDestinations(final ActiveMQDestination[] staticallyIncludedDestinations) {
        this.staticallyIncludedDestinations = staticallyIncludedDestinations;
    }
    
    public ActiveMQDestination[] getDurableDestinations() {
        return this.durableDestinations;
    }
    
    public void setDurableDestinations(final ActiveMQDestination[] durableDestinations) {
        this.durableDestinations = durableDestinations;
    }
    
    public Transport getLocalBroker() {
        return this.localBroker;
    }
    
    public Transport getRemoteBroker() {
        return this.remoteBroker;
    }
    
    public boolean isCreatedByDuplex() {
        return this.createdByDuplex;
    }
    
    public void setCreatedByDuplex(final boolean createdByDuplex) {
        this.createdByDuplex = createdByDuplex;
    }
    
    public static boolean contains(final BrokerId[] brokerPath, final BrokerId brokerId) {
        if (brokerPath != null) {
            for (int i = 0; i < brokerPath.length; ++i) {
                if (brokerId.equals(brokerPath[i])) {
                    return true;
                }
            }
        }
        return false;
    }
    
    protected BrokerId[] appendToBrokerPath(final BrokerId[] brokerPath, final BrokerId[] pathsToAppend) {
        if (brokerPath == null || brokerPath.length == 0) {
            return pathsToAppend;
        }
        final BrokerId[] rc = new BrokerId[brokerPath.length + pathsToAppend.length];
        System.arraycopy(brokerPath, 0, rc, 0, brokerPath.length);
        System.arraycopy(pathsToAppend, 0, rc, brokerPath.length, pathsToAppend.length);
        return rc;
    }
    
    protected BrokerId[] appendToBrokerPath(final BrokerId[] brokerPath, final BrokerId idToAppend) {
        if (brokerPath == null || brokerPath.length == 0) {
            return new BrokerId[] { idToAppend };
        }
        final BrokerId[] rc = new BrokerId[brokerPath.length + 1];
        System.arraycopy(brokerPath, 0, rc, 0, brokerPath.length);
        rc[brokerPath.length] = idToAppend;
        return rc;
    }
    
    protected boolean isPermissableDestination(final ActiveMQDestination destination) {
        return this.isPermissableDestination(destination, false);
    }
    
    protected boolean isPermissableDestination(final ActiveMQDestination destination, final boolean allowTemporary) {
        if (destination.isTemporary()) {
            return allowTemporary || this.configuration.isBridgeTempDestinations();
        }
        ActiveMQDestination[] dests = this.excludedDestinations;
        if (dests != null && dests.length > 0) {
            for (int i = 0; i < dests.length; ++i) {
                final ActiveMQDestination match = dests[i];
                final DestinationFilter exclusionFilter = DestinationFilter.parseFilter(match);
                if (match != null && exclusionFilter.matches(destination) && dests[i].getDestinationType() == destination.getDestinationType()) {
                    return false;
                }
            }
        }
        dests = this.dynamicallyIncludedDestinations;
        if (dests != null && dests.length > 0) {
            for (int i = 0; i < dests.length; ++i) {
                final ActiveMQDestination match = dests[i];
                final DestinationFilter inclusionFilter = DestinationFilter.parseFilter(match);
                if (match != null && inclusionFilter.matches(destination) && dests[i].getDestinationType() == destination.getDestinationType()) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
    
    protected void setupStaticDestinations() {
        final ActiveMQDestination[] dests = this.staticallyIncludedDestinations;
        if (dests != null) {
            for (int i = 0; i < dests.length; ++i) {
                final ActiveMQDestination dest = dests[i];
                final DemandSubscription sub = this.createDemandSubscription(dest);
                try {
                    this.addSubscription(sub);
                }
                catch (IOException e) {
                    DemandForwardingBridgeSupport.LOG.error("Failed to add static destination " + dest, e);
                }
                if (DemandForwardingBridgeSupport.LOG.isTraceEnabled()) {
                    DemandForwardingBridgeSupport.LOG.trace("bridging messages for static destination: " + dest);
                }
            }
        }
    }
    
    protected boolean addConsumerInfo(final ConsumerInfo consumerInfo) throws IOException {
        boolean consumerAdded = false;
        final ConsumerInfo info = consumerInfo.copy();
        this.addRemoteBrokerToBrokerPath(info);
        final DemandSubscription sub = this.createDemandSubscription(info);
        if (sub != null) {
            if (this.duplicateSuppressionIsRequired(sub)) {
                this.undoMapRegistration(sub);
            }
            else {
                this.addSubscription(sub);
                consumerAdded = true;
            }
        }
        return consumerAdded;
    }
    
    private void undoMapRegistration(final DemandSubscription sub) {
        this.subscriptionMapByLocalId.remove(sub.getLocalInfo().getConsumerId());
        this.subscriptionMapByRemoteId.remove(sub.getRemoteInfo().getConsumerId());
    }
    
    private boolean duplicateSuppressionIsRequired(final DemandSubscription candidate) {
        final ConsumerInfo consumerInfo = candidate.getRemoteInfo();
        boolean suppress = false;
        if ((consumerInfo.getDestination().isQueue() && !this.configuration.isSuppressDuplicateQueueSubscriptions()) || (consumerInfo.getDestination().isTopic() && !this.configuration.isSuppressDuplicateTopicSubscriptions())) {
            return suppress;
        }
        final List<ConsumerId> candidateConsumers = consumerInfo.getNetworkConsumerIds();
        final Collection<Subscription> currentSubs = this.getRegionSubscriptions(consumerInfo.getDestination().isTopic());
        for (final Subscription sub : currentSubs) {
            final List<ConsumerId> networkConsumers = sub.getConsumerInfo().getNetworkConsumerIds();
            if (!networkConsumers.isEmpty() && this.matchFound(candidateConsumers, networkConsumers)) {
                suppress = this.hasLowerPriority(sub, candidate.getLocalInfo());
                break;
            }
        }
        return suppress;
    }
    
    private boolean hasLowerPriority(final Subscription existingSub, final ConsumerInfo candidateInfo) {
        boolean suppress = false;
        if (existingSub.getConsumerInfo().getPriority() >= candidateInfo.getPriority()) {
            if (DemandForwardingBridgeSupport.LOG.isDebugEnabled()) {
                DemandForwardingBridgeSupport.LOG.debug(this.configuration.getBrokerName() + " Ignoring duplicate subscription from " + this.remoteBrokerName + ", sub: " + candidateInfo + " is duplicated by network subscription with equal or higher network priority: " + existingSub.getConsumerInfo() + ", networkComsumerIds: " + existingSub.getConsumerInfo().getNetworkConsumerIds());
            }
            suppress = true;
        }
        else {
            try {
                this.removeDuplicateSubscription(existingSub);
                if (DemandForwardingBridgeSupport.LOG.isDebugEnabled()) {
                    DemandForwardingBridgeSupport.LOG.debug(this.configuration.getBrokerName() + " Replacing duplicate subscription " + existingSub.getConsumerInfo() + " with sub from " + this.remoteBrokerName + ", which has a higher priority, new sub: " + candidateInfo + ", networkComsumerIds: " + candidateInfo.getNetworkConsumerIds());
                }
            }
            catch (IOException e) {
                DemandForwardingBridgeSupport.LOG.error("Failed to remove duplicated sub as a result of sub with higher priority, sub: " + existingSub, e);
            }
        }
        return suppress;
    }
    
    private void removeDuplicateSubscription(final Subscription existingSub) throws IOException {
        for (final NetworkConnector connector : this.brokerService.getNetworkConnectors()) {
            if (connector.removeDemandSubscription(existingSub.getConsumerInfo().getConsumerId())) {
                break;
            }
        }
    }
    
    private boolean matchFound(final List<ConsumerId> candidateConsumers, final List<ConsumerId> networkConsumers) {
        boolean found = false;
        for (final ConsumerId aliasConsumer : networkConsumers) {
            if (candidateConsumers.contains(aliasConsumer)) {
                found = true;
                break;
            }
        }
        return found;
    }
    
    private final Collection<Subscription> getRegionSubscriptions(final boolean isTopic) {
        final RegionBroker region = (RegionBroker)this.brokerService.getRegionBroker();
        final AbstractRegion abstractRegion = (AbstractRegion)(isTopic ? region.getTopicRegion() : region.getQueueRegion());
        return abstractRegion.getSubscriptions().values();
    }
    
    protected DemandSubscription createDemandSubscription(final ConsumerInfo info) throws IOException {
        info.addNetworkConsumerId(info.getConsumerId());
        return this.doCreateDemandSubscription(info);
    }
    
    protected DemandSubscription doCreateDemandSubscription(final ConsumerInfo info) throws IOException {
        final DemandSubscription result = new DemandSubscription(info);
        result.getLocalInfo().setConsumerId(new ConsumerId(this.localSessionInfo.getSessionId(), this.consumerIdGenerator.getNextSequenceId()));
        if (info.getDestination().isTemporary()) {
            final ActiveMQTempDestination dest = (ActiveMQTempDestination)result.getLocalInfo().getDestination();
            dest.setConnectionId(this.localConnectionInfo.getConnectionId().toString());
        }
        if (this.configuration.isDecreaseNetworkConsumerPriority()) {
            byte priority = -5;
            if (info.getBrokerPath() != null && info.getBrokerPath().length > 1) {
                priority -= (byte)(info.getBrokerPath().length + 1);
            }
            result.getLocalInfo().setPriority(priority);
            if (DemandForwardingBridgeSupport.LOG.isDebugEnabled()) {
                DemandForwardingBridgeSupport.LOG.debug(this.configuration.getBrokerName() + " using priority :" + priority + " for subscription: " + info);
            }
        }
        this.configureDemandSubscription(info, result);
        return result;
    }
    
    protected final DemandSubscription createDemandSubscription(final ActiveMQDestination destination) {
        final ConsumerInfo info = new ConsumerInfo();
        info.setDestination(destination);
        info.setConsumerId(new ConsumerId(this.localSessionInfo.getSessionId(), this.consumerIdGenerator.getNextSequenceId()));
        DemandSubscription result = null;
        try {
            result = this.createDemandSubscription(info);
        }
        catch (IOException e) {
            DemandForwardingBridgeSupport.LOG.error("Failed to create DemandSubscription ", e);
        }
        if (result != null) {
            result.getLocalInfo().setPriority((byte)(-5));
        }
        return result;
    }
    
    protected void configureDemandSubscription(final ConsumerInfo info, final DemandSubscription sub) throws IOException {
        sub.getLocalInfo().setDispatchAsync(this.configuration.isDispatchAsync());
        sub.getLocalInfo().setPrefetchSize(this.configuration.getPrefetchSize());
        this.subscriptionMapByLocalId.put(sub.getLocalInfo().getConsumerId(), sub);
        this.subscriptionMapByRemoteId.put(sub.getRemoteInfo().getConsumerId(), sub);
        if (!info.isDurable()) {
            sub.getLocalInfo().setAdditionalPredicate(this.createNetworkBridgeFilter(info));
        }
    }
    
    protected void removeDemandSubscription(final ConsumerId id) throws IOException {
        final DemandSubscription sub = this.subscriptionMapByRemoteId.remove(id);
        if (DemandForwardingBridgeSupport.LOG.isDebugEnabled()) {
            DemandForwardingBridgeSupport.LOG.debug(this.configuration.getBrokerName() + " remove request on " + this.localBroker + " from " + this.remoteBrokerName + " , consumer id: " + id + ", matching sub: " + sub);
        }
        if (sub != null) {
            this.removeSubscription(sub);
            if (DemandForwardingBridgeSupport.LOG.isDebugEnabled()) {
                DemandForwardingBridgeSupport.LOG.debug(this.configuration.getBrokerName() + " removed sub on " + this.localBroker + " from " + this.remoteBrokerName + " :  " + sub.getRemoteInfo());
            }
        }
    }
    
    protected boolean removeDemandSubscriptionByLocalId(final ConsumerId consumerId) {
        boolean removeDone = false;
        final DemandSubscription sub = this.subscriptionMapByLocalId.get(consumerId);
        if (sub != null) {
            try {
                this.removeDemandSubscription(sub.getRemoteInfo().getConsumerId());
                removeDone = true;
            }
            catch (IOException e) {
                DemandForwardingBridgeSupport.LOG.debug("removeDemandSubscriptionByLocalId failed for localId: " + consumerId, e);
            }
        }
        return removeDone;
    }
    
    protected void waitStarted() throws InterruptedException {
        this.startedLatch.await();
        this.localBrokerIdKnownLatch.await();
    }
    
    protected void clearDownSubscriptions() {
        this.subscriptionMapByLocalId.clear();
        this.subscriptionMapByRemoteId.clear();
    }
    
    protected abstract NetworkBridgeFilter createNetworkBridgeFilter(final ConsumerInfo p0) throws IOException;
    
    protected abstract void serviceLocalBrokerInfo(final Command p0) throws InterruptedException;
    
    protected abstract void addRemoteBrokerToBrokerPath(final ConsumerInfo p0) throws IOException;
    
    protected abstract void serviceRemoteBrokerInfo(final Command p0) throws IOException;
    
    protected abstract BrokerId[] getRemoteBrokerPath();
    
    @Override
    public void setNetworkBridgeListener(final NetworkBridgeListener listener) {
        this.networkBridgeListener = listener;
    }
    
    private void fireBridgeFailed() {
        final NetworkBridgeListener l = this.networkBridgeListener;
        if (l != null) {
            l.bridgeFailed();
        }
    }
    
    @Override
    public String getRemoteAddress() {
        return this.remoteBroker.getRemoteAddress();
    }
    
    @Override
    public String getLocalAddress() {
        return this.localBroker.getRemoteAddress();
    }
    
    @Override
    public String getRemoteBrokerName() {
        return (this.remoteBrokerInfo == null) ? null : this.remoteBrokerInfo.getBrokerName();
    }
    
    @Override
    public String getLocalBrokerName() {
        return (this.localBrokerInfo == null) ? null : this.localBrokerInfo.getBrokerName();
    }
    
    @Override
    public long getDequeueCounter() {
        return this.dequeueCounter.get();
    }
    
    @Override
    public long getEnqueueCounter() {
        return this.enqueueCounter.get();
    }
    
    protected boolean isDuplex() {
        return this.configuration.isDuplex() || this.createdByDuplex;
    }
    
    @Override
    public void setBrokerService(final BrokerService brokerService) {
        this.brokerService = brokerService;
    }
    
    static {
        LOG = LoggerFactory.getLogger(DemandForwardingBridgeSupport.class);
    }
}
