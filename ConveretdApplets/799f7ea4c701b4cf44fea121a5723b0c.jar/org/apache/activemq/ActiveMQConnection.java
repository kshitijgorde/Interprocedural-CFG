// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq;

import org.slf4j.LoggerFactory;
import org.apache.activemq.transport.failover.FailoverTransport;
import org.apache.activemq.command.MessageId;
import org.apache.activemq.command.ActiveMQMessage;
import javax.jms.InvalidDestinationException;
import org.apache.activemq.command.RemoveSubscriptionInfo;
import java.io.OutputStream;
import java.io.InputStream;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.DestinationInfo;
import org.apache.activemq.command.ActiveMQTempQueue;
import org.apache.activemq.command.ActiveMQTempTopic;
import org.apache.activemq.state.CommandVisitor;
import org.apache.activemq.command.WireFormatInfo;
import org.apache.activemq.command.ConsumerControl;
import org.apache.activemq.command.ConnectionControl;
import org.apache.activemq.command.ControlCommand;
import org.apache.activemq.command.ConnectionError;
import org.apache.activemq.command.ProducerAck;
import org.apache.activemq.command.Message;
import org.apache.activemq.command.MessageDispatch;
import org.apache.activemq.state.CommandVisitorAdapter;
import org.apache.activemq.management.StatsImpl;
import org.apache.activemq.command.ExceptionResponse;
import org.apache.activemq.command.Response;
import javax.jms.QueueSession;
import javax.jms.Queue;
import javax.jms.TopicSession;
import org.apache.activemq.util.IntrospectionSupport;
import java.util.Map;
import java.util.HashMap;
import javax.jms.Destination;
import org.apache.activemq.command.ConsumerInfo;
import javax.jms.ConnectionConsumer;
import javax.jms.ServerSessionPool;
import javax.jms.Topic;
import org.apache.activemq.command.RemoveInfo;
import org.apache.activemq.util.ServiceSupport;
import org.apache.activemq.command.ShutdownInfo;
import org.apache.activemq.command.Command;
import org.apache.activemq.util.JMSExceptionSupport;
import java.util.Iterator;
import javax.jms.ConnectionMetaData;
import javax.jms.IllegalStateException;
import javax.jms.Session;
import java.net.URI;
import java.net.URISyntaxException;
import javax.jms.JMSException;
import java.util.List;
import javax.jms.XAConnection;
import org.apache.activemq.command.ConnectionId;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.apache.activemq.thread.Scheduler;
import org.apache.activemq.advisory.DestinationSource;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.IOException;
import org.apache.activemq.command.BrokerInfo;
import java.util.concurrent.CountDownLatch;
import org.apache.activemq.command.SessionId;
import org.apache.activemq.util.LongSequenceGenerator;
import org.apache.activemq.command.ProducerId;
import org.apache.activemq.command.ConsumerId;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.activemq.management.JMSConnectionStatsImpl;
import org.apache.activemq.management.JMSStatsImpl;
import org.apache.activemq.transport.Transport;
import org.apache.activemq.blob.BlobTransferPolicy;
import javax.jms.ExceptionListener;
import org.apache.activemq.command.ConnectionInfo;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.activemq.thread.TaskRunnerFactory;
import org.apache.activemq.command.ActiveMQTempDestination;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.activemq.util.IdGenerator;
import org.slf4j.Logger;
import org.apache.activemq.transport.TransportListener;
import org.apache.activemq.management.StatsCapable;
import javax.jms.QueueConnection;
import javax.jms.TopicConnection;
import javax.jms.Connection;

public class ActiveMQConnection implements Connection, TopicConnection, QueueConnection, StatsCapable, Closeable, StreamConnection, TransportListener, EnhancedConnection
{
    public static final String DEFAULT_USER;
    public static final String DEFAULT_PASSWORD;
    public static final String DEFAULT_BROKER_URL = "failover://tcp://localhost:61616";
    private static final Logger LOG;
    private static final IdGenerator CONNECTION_ID_GENERATOR;
    public final ConcurrentHashMap<ActiveMQTempDestination, ActiveMQTempDestination> activeTempDestinations;
    protected boolean dispatchAsync;
    protected boolean alwaysSessionAsync;
    private TaskRunnerFactory sessionTaskRunner;
    private final ThreadPoolExecutor executor;
    private final ConnectionInfo info;
    private ExceptionListener exceptionListener;
    private ClientInternalExceptionListener clientInternalExceptionListener;
    private boolean clientIDSet;
    private boolean isConnectionInfoSentToBroker;
    private boolean userSpecifiedClientID;
    private ActiveMQPrefetchPolicy prefetchPolicy;
    private BlobTransferPolicy blobTransferPolicy;
    private RedeliveryPolicy redeliveryPolicy;
    private MessageTransformer transformer;
    private boolean disableTimeStampsByDefault;
    private boolean optimizedMessageDispatch;
    private boolean copyMessageOnSend;
    private boolean useCompression;
    private boolean objectMessageSerializationDefered;
    private boolean useAsyncSend;
    private boolean optimizeAcknowledge;
    private boolean nestedMapAndListEnabled;
    private boolean useRetroactiveConsumer;
    private boolean exclusiveConsumer;
    private boolean alwaysSyncSend;
    private int closeTimeout;
    private boolean watchTopicAdvisories;
    private long warnAboutUnstartedConnectionTimeout;
    private int sendTimeout;
    private boolean sendAcksAsync;
    private boolean checkForDuplicates;
    private final Transport transport;
    private final IdGenerator clientIdGenerator;
    private final JMSStatsImpl factoryStats;
    private final JMSConnectionStatsImpl stats;
    private final AtomicBoolean started;
    private final AtomicBoolean closing;
    private final AtomicBoolean closed;
    private final AtomicBoolean transportFailed;
    private final CopyOnWriteArrayList<ActiveMQSession> sessions;
    private final CopyOnWriteArrayList<ActiveMQConnectionConsumer> connectionConsumers;
    private final CopyOnWriteArrayList<ActiveMQInputStream> inputStreams;
    private final CopyOnWriteArrayList<ActiveMQOutputStream> outputStreams;
    private final CopyOnWriteArrayList<TransportListener> transportListeners;
    private final ConcurrentHashMap<ConsumerId, ActiveMQDispatcher> dispatchers;
    private final ConcurrentHashMap<ProducerId, ActiveMQMessageProducer> producers;
    private final LongSequenceGenerator sessionIdGenerator;
    private final SessionId connectionSessionId;
    private final LongSequenceGenerator consumerIdGenerator;
    private final LongSequenceGenerator producerIdGenerator;
    private final LongSequenceGenerator tempDestinationIdGenerator;
    private final LongSequenceGenerator localTransactionIdGenerator;
    private AdvisoryConsumer advisoryConsumer;
    private final CountDownLatch brokerInfoReceived;
    private BrokerInfo brokerInfo;
    private IOException firstFailureError;
    private int producerWindowSize;
    private final AtomicInteger protocolVersion;
    private final long timeCreated;
    private final ConnectionAudit connectionAudit;
    private DestinationSource destinationSource;
    private final Object ensureConnectionInfoSentMutex;
    private boolean useDedicatedTaskRunner;
    protected volatile CountDownLatch transportInterruptionProcessingComplete;
    private long consumerFailoverRedeliveryWaitPeriod;
    private final Scheduler scheduler;
    private boolean messagePrioritySupported;
    
    protected ActiveMQConnection(final Transport transport, final IdGenerator clientIdGenerator, final JMSStatsImpl factoryStats) throws Exception {
        this.activeTempDestinations = new ConcurrentHashMap<ActiveMQTempDestination, ActiveMQTempDestination>();
        this.dispatchAsync = true;
        this.alwaysSessionAsync = true;
        this.prefetchPolicy = new ActiveMQPrefetchPolicy();
        this.optimizedMessageDispatch = true;
        this.copyMessageOnSend = true;
        this.nestedMapAndListEnabled = true;
        this.closeTimeout = 15000;
        this.watchTopicAdvisories = true;
        this.warnAboutUnstartedConnectionTimeout = 500L;
        this.sendTimeout = 0;
        this.sendAcksAsync = true;
        this.checkForDuplicates = true;
        this.started = new AtomicBoolean(false);
        this.closing = new AtomicBoolean(false);
        this.closed = new AtomicBoolean(false);
        this.transportFailed = new AtomicBoolean(false);
        this.sessions = new CopyOnWriteArrayList<ActiveMQSession>();
        this.connectionConsumers = new CopyOnWriteArrayList<ActiveMQConnectionConsumer>();
        this.inputStreams = new CopyOnWriteArrayList<ActiveMQInputStream>();
        this.outputStreams = new CopyOnWriteArrayList<ActiveMQOutputStream>();
        this.transportListeners = new CopyOnWriteArrayList<TransportListener>();
        this.dispatchers = new ConcurrentHashMap<ConsumerId, ActiveMQDispatcher>();
        this.producers = new ConcurrentHashMap<ProducerId, ActiveMQMessageProducer>();
        this.sessionIdGenerator = new LongSequenceGenerator();
        this.consumerIdGenerator = new LongSequenceGenerator();
        this.producerIdGenerator = new LongSequenceGenerator();
        this.tempDestinationIdGenerator = new LongSequenceGenerator();
        this.localTransactionIdGenerator = new LongSequenceGenerator();
        this.brokerInfoReceived = new CountDownLatch(1);
        this.producerWindowSize = 0;
        this.protocolVersion = new AtomicInteger(7);
        this.connectionAudit = new ConnectionAudit();
        this.ensureConnectionInfoSentMutex = new Object();
        this.messagePrioritySupported = true;
        this.transport = transport;
        this.clientIdGenerator = clientIdGenerator;
        this.factoryStats = factoryStats;
        this.executor = new ThreadPoolExecutor(1, 1, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(final Runnable r) {
                final Thread thread = new Thread(r, "ActiveMQ Connection Executor: " + transport);
                thread.setDaemon(true);
                return thread;
            }
        });
        final String uniqueId = ActiveMQConnection.CONNECTION_ID_GENERATOR.generateId();
        (this.info = new ConnectionInfo(new ConnectionId(uniqueId))).setManageable(true);
        this.info.setFaultTolerant(transport.isFaultTolerant());
        this.connectionSessionId = new SessionId(this.info.getConnectionId(), -1L);
        this.transport.setTransportListener(this);
        this.stats = new JMSConnectionStatsImpl(this.sessions, this instanceof XAConnection);
        this.factoryStats.addConnection(this);
        this.timeCreated = System.currentTimeMillis();
        this.connectionAudit.setCheckForDuplicates(transport.isFaultTolerant());
        (this.scheduler = new Scheduler("ActiveMQConnection[" + uniqueId + "] Scheduler")).start();
    }
    
    protected void setUserName(final String userName) {
        this.info.setUserName(userName);
    }
    
    protected void setPassword(final String password) {
        this.info.setPassword(password);
    }
    
    public static ActiveMQConnection makeConnection() throws JMSException {
        final ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        return (ActiveMQConnection)factory.createConnection();
    }
    
    public static ActiveMQConnection makeConnection(final String uri) throws JMSException, URISyntaxException {
        final ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(uri);
        return (ActiveMQConnection)factory.createConnection();
    }
    
    public static ActiveMQConnection makeConnection(final String user, final String password, final String uri) throws JMSException, URISyntaxException {
        final ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(user, password, new URI(uri));
        return (ActiveMQConnection)factory.createConnection();
    }
    
    public JMSConnectionStatsImpl getConnectionStats() {
        return this.stats;
    }
    
    @Override
    public Session createSession(final boolean transacted, final int acknowledgeMode) throws JMSException {
        this.checkClosedOrFailed();
        this.ensureConnectionInfoSent();
        if (!transacted) {
            if (acknowledgeMode == 0) {
                throw new JMSException("acknowledgeMode SESSION_TRANSACTED cannot be used for an non-transacted Session");
            }
            if (acknowledgeMode < 0 || acknowledgeMode > 4) {
                throw new JMSException("invalid acknowledgeMode: " + acknowledgeMode + ". Valid values are Session.AUTO_ACKNOWLEDGE (1), " + "Session.CLIENT_ACKNOWLEDGE (2), Session.DUPS_OK_ACKNOWLEDGE (3), ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE (4) or for transacted sessions Session.SESSION_TRANSACTED (0)");
            }
        }
        return new ActiveMQSession(this, this.getNextSessionId(), transacted ? 0 : ((acknowledgeMode == 0) ? 1 : acknowledgeMode), this.isDispatchAsync(), this.isAlwaysSessionAsync());
    }
    
    protected SessionId getNextSessionId() {
        return new SessionId(this.info.getConnectionId(), this.sessionIdGenerator.getNextSequenceId());
    }
    
    @Override
    public String getClientID() throws JMSException {
        this.checkClosedOrFailed();
        return this.info.getClientId();
    }
    
    @Override
    public void setClientID(final String newClientID) throws JMSException {
        this.checkClosedOrFailed();
        if (this.clientIDSet) {
            throw new IllegalStateException("The clientID has already been set");
        }
        if (this.isConnectionInfoSentToBroker) {
            throw new IllegalStateException("Setting clientID on a used Connection is not allowed");
        }
        this.info.setClientId(newClientID);
        this.userSpecifiedClientID = true;
        this.ensureConnectionInfoSent();
    }
    
    public void setDefaultClientID(final String clientID) throws JMSException {
        this.info.setClientId(clientID);
        this.userSpecifiedClientID = true;
    }
    
    @Override
    public ConnectionMetaData getMetaData() throws JMSException {
        this.checkClosedOrFailed();
        return ActiveMQConnectionMetaData.INSTANCE;
    }
    
    @Override
    public ExceptionListener getExceptionListener() throws JMSException {
        this.checkClosedOrFailed();
        return this.exceptionListener;
    }
    
    @Override
    public void setExceptionListener(final ExceptionListener listener) throws JMSException {
        this.checkClosedOrFailed();
        this.exceptionListener = listener;
    }
    
    public ClientInternalExceptionListener getClientInternalExceptionListener() {
        return this.clientInternalExceptionListener;
    }
    
    public void setClientInternalExceptionListener(final ClientInternalExceptionListener listener) {
        this.clientInternalExceptionListener = listener;
    }
    
    @Override
    public void start() throws JMSException {
        this.checkClosedOrFailed();
        this.ensureConnectionInfoSent();
        if (this.started.compareAndSet(false, true)) {
            for (final ActiveMQSession session : this.sessions) {
                session.start();
            }
        }
    }
    
    @Override
    public void stop() throws JMSException {
        this.checkClosedOrFailed();
        if (this.started.compareAndSet(true, false)) {
            synchronized (this.sessions) {
                for (final ActiveMQSession s : this.sessions) {
                    s.stop();
                }
            }
        }
    }
    
    @Override
    public void close() throws JMSException {
        try {
            if (!this.closed.get() && !this.transportFailed.get()) {
                this.stop();
            }
            synchronized (this) {
                if (!this.closed.get()) {
                    this.closing.set(true);
                    if (this.destinationSource != null) {
                        this.destinationSource.stop();
                        this.destinationSource = null;
                    }
                    if (this.advisoryConsumer != null) {
                        this.advisoryConsumer.dispose();
                        this.advisoryConsumer = null;
                    }
                    if (this.scheduler != null) {
                        try {
                            this.scheduler.stop();
                        }
                        catch (Exception e) {
                            final JMSException ex = JMSExceptionSupport.create(e);
                            throw ex;
                        }
                    }
                    long lastDeliveredSequenceId = 0L;
                    for (final ActiveMQSession s : this.sessions) {
                        s.dispose();
                        lastDeliveredSequenceId = Math.max(lastDeliveredSequenceId, s.getLastDeliveredSequenceId());
                    }
                    for (final ActiveMQConnectionConsumer c : this.connectionConsumers) {
                        c.dispose();
                    }
                    for (final ActiveMQInputStream c2 : this.inputStreams) {
                        c2.dispose();
                    }
                    for (final ActiveMQOutputStream c3 : this.outputStreams) {
                        c3.dispose();
                    }
                    for (final ActiveMQTempDestination c4 : this.activeTempDestinations.values()) {
                        c4.delete();
                    }
                    if (this.isConnectionInfoSentToBroker) {
                        final RemoveInfo removeCommand = this.info.createRemoveCommand();
                        removeCommand.setLastDeliveredSequenceId(lastDeliveredSequenceId);
                        this.doSyncSendPacket(this.info.createRemoveCommand(), this.closeTimeout);
                        this.doAsyncSendPacket(new ShutdownInfo());
                    }
                    ServiceSupport.dispose(this.transport);
                    this.started.set(false);
                    if (this.sessionTaskRunner != null) {
                        this.sessionTaskRunner.shutdown();
                    }
                    this.closed.set(true);
                    this.closing.set(false);
                }
            }
        }
        finally {
            try {
                if (this.executor != null) {
                    this.executor.shutdown();
                }
            }
            catch (Throwable e2) {
                ActiveMQConnection.LOG.error("Error shutting down thread pool " + e2, e2);
            }
            this.factoryStats.removeConnection(this);
        }
    }
    
    @Override
    public ConnectionConsumer createDurableConnectionConsumer(final Topic topic, final String subscriptionName, final String messageSelector, final ServerSessionPool sessionPool, final int maxMessages) throws JMSException {
        return this.createDurableConnectionConsumer(topic, subscriptionName, messageSelector, sessionPool, maxMessages, false);
    }
    
    public ConnectionConsumer createDurableConnectionConsumer(final Topic topic, final String subscriptionName, final String messageSelector, final ServerSessionPool sessionPool, final int maxMessages, final boolean noLocal) throws JMSException {
        this.checkClosedOrFailed();
        this.ensureConnectionInfoSent();
        final SessionId sessionId = new SessionId(this.info.getConnectionId(), -1L);
        final ConsumerInfo info = new ConsumerInfo(new ConsumerId(sessionId, this.consumerIdGenerator.getNextSequenceId()));
        info.setDestination(ActiveMQMessageTransformation.transformDestination(topic));
        info.setSubscriptionName(subscriptionName);
        info.setSelector(messageSelector);
        info.setPrefetchSize(maxMessages);
        info.setDispatchAsync(this.isDispatchAsync());
        if (info.getDestination().getOptions() != null) {
            final Map<String, String> options = new HashMap<String, String>(info.getDestination().getOptions());
            IntrospectionSupport.setProperties(this.info, options, "consumer.");
        }
        return new ActiveMQConnectionConsumer(this, sessionPool, info);
    }
    
    public boolean isStarted() {
        return this.started.get();
    }
    
    public boolean isClosed() {
        return this.closed.get();
    }
    
    public boolean isClosing() {
        return this.closing.get();
    }
    
    public boolean isTransportFailed() {
        return this.transportFailed.get();
    }
    
    public ActiveMQPrefetchPolicy getPrefetchPolicy() {
        return this.prefetchPolicy;
    }
    
    public void setPrefetchPolicy(final ActiveMQPrefetchPolicy prefetchPolicy) {
        this.prefetchPolicy = prefetchPolicy;
    }
    
    public Transport getTransportChannel() {
        return this.transport;
    }
    
    public String getInitializedClientID() throws JMSException {
        this.ensureConnectionInfoSent();
        return this.info.getClientId();
    }
    
    public boolean isDisableTimeStampsByDefault() {
        return this.disableTimeStampsByDefault;
    }
    
    public void setDisableTimeStampsByDefault(final boolean timeStampsDisableByDefault) {
        this.disableTimeStampsByDefault = timeStampsDisableByDefault;
    }
    
    public boolean isOptimizedMessageDispatch() {
        return this.optimizedMessageDispatch;
    }
    
    public void setOptimizedMessageDispatch(final boolean dispatchOptimizedMessage) {
        this.optimizedMessageDispatch = dispatchOptimizedMessage;
    }
    
    public int getCloseTimeout() {
        return this.closeTimeout;
    }
    
    public void setCloseTimeout(final int closeTimeout) {
        this.closeTimeout = closeTimeout;
    }
    
    public ConnectionInfo getConnectionInfo() {
        return this.info;
    }
    
    public boolean isUseRetroactiveConsumer() {
        return this.useRetroactiveConsumer;
    }
    
    public void setUseRetroactiveConsumer(final boolean useRetroactiveConsumer) {
        this.useRetroactiveConsumer = useRetroactiveConsumer;
    }
    
    public boolean isNestedMapAndListEnabled() {
        return this.nestedMapAndListEnabled;
    }
    
    public void setNestedMapAndListEnabled(final boolean structuredMapsEnabled) {
        this.nestedMapAndListEnabled = structuredMapsEnabled;
    }
    
    public boolean isExclusiveConsumer() {
        return this.exclusiveConsumer;
    }
    
    public void setExclusiveConsumer(final boolean exclusiveConsumer) {
        this.exclusiveConsumer = exclusiveConsumer;
    }
    
    public void addTransportListener(final TransportListener transportListener) {
        this.transportListeners.add(transportListener);
    }
    
    public void removeTransportListener(final TransportListener transportListener) {
        this.transportListeners.remove(transportListener);
    }
    
    public boolean isUseDedicatedTaskRunner() {
        return this.useDedicatedTaskRunner;
    }
    
    public void setUseDedicatedTaskRunner(final boolean useDedicatedTaskRunner) {
        this.useDedicatedTaskRunner = useDedicatedTaskRunner;
    }
    
    public TaskRunnerFactory getSessionTaskRunner() {
        synchronized (this) {
            if (this.sessionTaskRunner == null) {
                this.sessionTaskRunner = new TaskRunnerFactory("ActiveMQ Session Task", 7, false, 1000, this.isUseDedicatedTaskRunner());
            }
        }
        return this.sessionTaskRunner;
    }
    
    public void setSessionTaskRunner(final TaskRunnerFactory sessionTaskRunner) {
        this.sessionTaskRunner = sessionTaskRunner;
    }
    
    public MessageTransformer getTransformer() {
        return this.transformer;
    }
    
    public void setTransformer(final MessageTransformer transformer) {
        this.transformer = transformer;
    }
    
    public boolean isStatsEnabled() {
        return this.stats.isEnabled();
    }
    
    public void setStatsEnabled(final boolean statsEnabled) {
        this.stats.setEnabled(statsEnabled);
    }
    
    @Override
    public DestinationSource getDestinationSource() throws JMSException {
        if (this.destinationSource == null) {
            (this.destinationSource = new DestinationSource(this)).start();
        }
        return this.destinationSource;
    }
    
    protected void addSession(final ActiveMQSession session) throws JMSException {
        this.sessions.add(session);
        if (this.sessions.size() > 1 || session.isTransacted()) {
            this.optimizedMessageDispatch = false;
        }
    }
    
    protected void removeSession(final ActiveMQSession session) {
        this.sessions.remove(session);
        this.removeDispatcher(session);
    }
    
    protected void addConnectionConsumer(final ActiveMQConnectionConsumer connectionConsumer) throws JMSException {
        this.connectionConsumers.add(connectionConsumer);
    }
    
    protected void removeConnectionConsumer(final ActiveMQConnectionConsumer connectionConsumer) {
        this.connectionConsumers.remove(connectionConsumer);
        this.removeDispatcher(connectionConsumer);
    }
    
    @Override
    public TopicSession createTopicSession(final boolean transacted, final int acknowledgeMode) throws JMSException {
        return new ActiveMQTopicSession((TopicSession)this.createSession(transacted, acknowledgeMode));
    }
    
    @Override
    public ConnectionConsumer createConnectionConsumer(final Topic topic, final String messageSelector, final ServerSessionPool sessionPool, final int maxMessages) throws JMSException {
        return this.createConnectionConsumer(topic, messageSelector, sessionPool, maxMessages, false);
    }
    
    @Override
    public ConnectionConsumer createConnectionConsumer(final Queue queue, final String messageSelector, final ServerSessionPool sessionPool, final int maxMessages) throws JMSException {
        return this.createConnectionConsumer(queue, messageSelector, sessionPool, maxMessages, false);
    }
    
    @Override
    public ConnectionConsumer createConnectionConsumer(final Destination destination, final String messageSelector, final ServerSessionPool sessionPool, final int maxMessages) throws JMSException {
        return this.createConnectionConsumer(destination, messageSelector, sessionPool, maxMessages, false);
    }
    
    public ConnectionConsumer createConnectionConsumer(final Destination destination, final String messageSelector, final ServerSessionPool sessionPool, final int maxMessages, final boolean noLocal) throws JMSException {
        this.checkClosedOrFailed();
        this.ensureConnectionInfoSent();
        final ConsumerId consumerId = this.createConsumerId();
        final ConsumerInfo consumerInfo = new ConsumerInfo(consumerId);
        consumerInfo.setDestination(ActiveMQMessageTransformation.transformDestination(destination));
        consumerInfo.setSelector(messageSelector);
        consumerInfo.setPrefetchSize(maxMessages);
        consumerInfo.setNoLocal(noLocal);
        consumerInfo.setDispatchAsync(this.isDispatchAsync());
        if (consumerInfo.getDestination().getOptions() != null) {
            final Map<String, String> options = new HashMap<String, String>(consumerInfo.getDestination().getOptions());
            IntrospectionSupport.setProperties(consumerInfo, options, "consumer.");
        }
        return new ActiveMQConnectionConsumer(this, sessionPool, consumerInfo);
    }
    
    private ConsumerId createConsumerId() {
        return new ConsumerId(this.connectionSessionId, this.consumerIdGenerator.getNextSequenceId());
    }
    
    private ProducerId createProducerId() {
        return new ProducerId(this.connectionSessionId, this.producerIdGenerator.getNextSequenceId());
    }
    
    @Override
    public QueueSession createQueueSession(final boolean transacted, final int acknowledgeMode) throws JMSException {
        return new ActiveMQQueueSession((QueueSession)this.createSession(transacted, acknowledgeMode));
    }
    
    public void checkClientIDWasManuallySpecified() throws JMSException {
        if (!this.userSpecifiedClientID) {
            throw new JMSException("You cannot create a durable subscriber without specifying a unique clientID on a Connection");
        }
    }
    
    public void asyncSendPacket(final Command command) throws JMSException {
        if (this.isClosed()) {
            throw new ConnectionClosedException();
        }
        this.doAsyncSendPacket(command);
    }
    
    private void doAsyncSendPacket(final Command command) throws JMSException {
        try {
            this.transport.oneway(command);
        }
        catch (IOException e) {
            throw JMSExceptionSupport.create(e);
        }
    }
    
    public Response syncSendPacket(final Command command) throws JMSException {
        if (this.isClosed()) {
            throw new ConnectionClosedException();
        }
        try {
            final Response response = (Response)this.transport.request(command);
            if (response.isException()) {
                final ExceptionResponse er = (ExceptionResponse)response;
                if (er.getException() instanceof JMSException) {
                    throw (JMSException)er.getException();
                }
                if (this.isClosed() || this.closing.get()) {
                    ActiveMQConnection.LOG.debug("Received an exception but connection is closing");
                }
                JMSException jmsEx = null;
                try {
                    jmsEx = JMSExceptionSupport.create(er.getException());
                }
                catch (Throwable e) {
                    ActiveMQConnection.LOG.error("Caught an exception trying to create a JMSException for " + er.getException(), e);
                }
                if (jmsEx != null) {
                    throw jmsEx;
                }
            }
            return response;
        }
        catch (IOException e2) {
            throw JMSExceptionSupport.create(e2);
        }
    }
    
    public Response syncSendPacket(final Command command, final int timeout) throws JMSException {
        if (this.isClosed() || this.closing.get()) {
            throw new ConnectionClosedException();
        }
        return this.doSyncSendPacket(command, timeout);
    }
    
    private Response doSyncSendPacket(final Command command, final int timeout) throws JMSException {
        try {
            final Response response = (Response)((timeout > 0) ? this.transport.request(command, timeout) : this.transport.request(command));
            if (response == null || !response.isException()) {
                return response;
            }
            final ExceptionResponse er = (ExceptionResponse)response;
            if (er.getException() instanceof JMSException) {
                throw (JMSException)er.getException();
            }
            throw JMSExceptionSupport.create(er.getException());
        }
        catch (IOException e) {
            throw JMSExceptionSupport.create(e);
        }
    }
    
    @Override
    public StatsImpl getStats() {
        return this.stats;
    }
    
    protected synchronized void checkClosedOrFailed() throws JMSException {
        this.checkClosed();
        if (this.transportFailed.get()) {
            throw new ConnectionFailedException(this.firstFailureError);
        }
    }
    
    protected synchronized void checkClosed() throws JMSException {
        if (this.closed.get()) {
            throw new ConnectionClosedException();
        }
    }
    
    protected void ensureConnectionInfoSent() throws JMSException {
        synchronized (this.ensureConnectionInfoSentMutex) {
            if (this.isConnectionInfoSentToBroker || this.closed.get()) {
                return;
            }
            if (this.info.getClientId() == null || this.info.getClientId().trim().length() == 0) {
                this.info.setClientId(this.clientIdGenerator.generateId());
            }
            this.syncSendPacket(this.info.copy());
            this.isConnectionInfoSentToBroker = true;
            final ConsumerId consumerId = new ConsumerId(new SessionId(this.info.getConnectionId(), -1L), this.consumerIdGenerator.getNextSequenceId());
            if (this.watchTopicAdvisories) {
                this.advisoryConsumer = new AdvisoryConsumer(this, consumerId);
            }
        }
    }
    
    public synchronized boolean isWatchTopicAdvisories() {
        return this.watchTopicAdvisories;
    }
    
    public synchronized void setWatchTopicAdvisories(final boolean watchTopicAdvisories) {
        this.watchTopicAdvisories = watchTopicAdvisories;
    }
    
    public boolean isUseAsyncSend() {
        return this.useAsyncSend;
    }
    
    public void setUseAsyncSend(final boolean useAsyncSend) {
        this.useAsyncSend = useAsyncSend;
    }
    
    public boolean isAlwaysSyncSend() {
        return this.alwaysSyncSend;
    }
    
    public void setAlwaysSyncSend(final boolean alwaysSyncSend) {
        this.alwaysSyncSend = alwaysSyncSend;
    }
    
    public boolean isMessagePrioritySupported() {
        return this.messagePrioritySupported;
    }
    
    public void setMessagePrioritySupported(final boolean messagePrioritySupported) {
        this.messagePrioritySupported = messagePrioritySupported;
    }
    
    public void cleanup() throws JMSException {
        if (this.advisoryConsumer != null && !this.isTransportFailed()) {
            this.advisoryConsumer.dispose();
            this.advisoryConsumer = null;
        }
        for (final ActiveMQSession s : this.sessions) {
            s.dispose();
        }
        for (final ActiveMQConnectionConsumer c : this.connectionConsumers) {
            c.dispose();
        }
        for (final ActiveMQInputStream c2 : this.inputStreams) {
            c2.dispose();
        }
        for (final ActiveMQOutputStream c3 : this.outputStreams) {
            c3.dispose();
        }
        if (this.isConnectionInfoSentToBroker) {
            if (!this.transportFailed.get() && !this.closing.get()) {
                this.syncSendPacket(this.info.createRemoveCommand());
            }
            this.isConnectionInfoSentToBroker = false;
        }
        if (this.userSpecifiedClientID) {
            this.info.setClientId(null);
            this.userSpecifiedClientID = false;
        }
        this.clientIDSet = false;
        this.started.set(false);
    }
    
    public void changeUserInfo(final String userName, final String password) throws JMSException {
        if (this.isConnectionInfoSentToBroker) {
            throw new IllegalStateException("changeUserInfo used Connection is not allowed");
        }
        this.info.setUserName(userName);
        this.info.setPassword(password);
    }
    
    public String getResourceManagerId() throws JMSException {
        this.waitForBrokerInfo();
        if (this.brokerInfo == null) {
            throw new JMSException("Connection failed before Broker info was received.");
        }
        return this.brokerInfo.getBrokerId().getValue();
    }
    
    public String getBrokerName() {
        try {
            this.brokerInfoReceived.await(5L, TimeUnit.SECONDS);
            if (this.brokerInfo == null) {
                return null;
            }
            return this.brokerInfo.getBrokerName();
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }
    
    public BrokerInfo getBrokerInfo() {
        return this.brokerInfo;
    }
    
    public RedeliveryPolicy getRedeliveryPolicy() throws JMSException {
        return this.redeliveryPolicy;
    }
    
    public void setRedeliveryPolicy(final RedeliveryPolicy redeliveryPolicy) {
        this.redeliveryPolicy = redeliveryPolicy;
    }
    
    public BlobTransferPolicy getBlobTransferPolicy() {
        if (this.blobTransferPolicy == null) {
            this.blobTransferPolicy = this.createBlobTransferPolicy();
        }
        return this.blobTransferPolicy;
    }
    
    public void setBlobTransferPolicy(final BlobTransferPolicy blobTransferPolicy) {
        this.blobTransferPolicy = blobTransferPolicy;
    }
    
    public boolean isAlwaysSessionAsync() {
        return this.alwaysSessionAsync;
    }
    
    public void setAlwaysSessionAsync(final boolean alwaysSessionAsync) {
        this.alwaysSessionAsync = alwaysSessionAsync;
    }
    
    public boolean isOptimizeAcknowledge() {
        return this.optimizeAcknowledge;
    }
    
    public void setOptimizeAcknowledge(final boolean optimizeAcknowledge) {
        this.optimizeAcknowledge = optimizeAcknowledge;
    }
    
    public long getWarnAboutUnstartedConnectionTimeout() {
        return this.warnAboutUnstartedConnectionTimeout;
    }
    
    public void setWarnAboutUnstartedConnectionTimeout(final long warnAboutUnstartedConnectionTimeout) {
        this.warnAboutUnstartedConnectionTimeout = warnAboutUnstartedConnectionTimeout;
    }
    
    public int getSendTimeout() {
        return this.sendTimeout;
    }
    
    public void setSendTimeout(final int sendTimeout) {
        this.sendTimeout = sendTimeout;
    }
    
    public boolean isSendAcksAsync() {
        return this.sendAcksAsync;
    }
    
    public void setSendAcksAsync(final boolean sendAcksAsync) {
        this.sendAcksAsync = sendAcksAsync;
    }
    
    public long getTimeCreated() {
        return this.timeCreated;
    }
    
    private void waitForBrokerInfo() throws JMSException {
        try {
            this.brokerInfoReceived.await();
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw JMSExceptionSupport.create(e);
        }
    }
    
    public Transport getTransport() {
        return this.transport;
    }
    
    public void addProducer(final ProducerId producerId, final ActiveMQMessageProducer producer) {
        this.producers.put(producerId, producer);
    }
    
    public void removeProducer(final ProducerId producerId) {
        this.producers.remove(producerId);
    }
    
    public void addDispatcher(final ConsumerId consumerId, final ActiveMQDispatcher dispatcher) {
        this.dispatchers.put(consumerId, dispatcher);
    }
    
    public void removeDispatcher(final ConsumerId consumerId) {
        this.dispatchers.remove(consumerId);
    }
    
    @Override
    public void onCommand(final Object o) {
        final Command command = (Command)o;
        if (!this.closed.get() && command != null) {
            try {
                command.visit(new CommandVisitorAdapter() {
                    @Override
                    public Response processMessageDispatch(final MessageDispatch md) throws Exception {
                        ActiveMQConnection.this.waitForTransportInterruptionProcessingToComplete();
                        final ActiveMQDispatcher dispatcher = ActiveMQConnection.this.dispatchers.get(md.getConsumerId());
                        if (dispatcher != null) {
                            Message msg = md.getMessage();
                            if (msg != null) {
                                msg = msg.copy();
                                msg.setReadOnlyBody(true);
                                msg.setReadOnlyProperties(true);
                                msg.setRedeliveryCounter(md.getRedeliveryCounter());
                                msg.setConnection(ActiveMQConnection.this);
                                md.setMessage(msg);
                            }
                            dispatcher.dispatch(md);
                        }
                        return null;
                    }
                    
                    @Override
                    public Response processProducerAck(final ProducerAck pa) throws Exception {
                        if (pa != null && pa.getProducerId() != null) {
                            final ActiveMQMessageProducer producer = ActiveMQConnection.this.producers.get(pa.getProducerId());
                            if (producer != null) {
                                producer.onProducerAck(pa);
                            }
                        }
                        return null;
                    }
                    
                    @Override
                    public Response processBrokerInfo(final BrokerInfo info) throws Exception {
                        ActiveMQConnection.this.brokerInfo = info;
                        ActiveMQConnection.this.brokerInfoReceived.countDown();
                        ActiveMQConnection.access$472(ActiveMQConnection.this, ActiveMQConnection.this.brokerInfo.isFaultTolerantConfiguration() ? 0 : 1);
                        ActiveMQConnection.this.getBlobTransferPolicy().setBrokerUploadUrl(info.getBrokerUploadUrl());
                        return null;
                    }
                    
                    @Override
                    public Response processConnectionError(final ConnectionError error) throws Exception {
                        ActiveMQConnection.this.executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                ActiveMQConnection.this.onAsyncException(error.getException());
                            }
                        });
                        return null;
                    }
                    
                    @Override
                    public Response processControlCommand(final ControlCommand command) throws Exception {
                        ActiveMQConnection.this.onControlCommand(command);
                        return null;
                    }
                    
                    @Override
                    public Response processConnectionControl(final ConnectionControl control) throws Exception {
                        ActiveMQConnection.this.onConnectionControl((ConnectionControl)command);
                        return null;
                    }
                    
                    @Override
                    public Response processConsumerControl(final ConsumerControl control) throws Exception {
                        ActiveMQConnection.this.onConsumerControl((ConsumerControl)command);
                        return null;
                    }
                    
                    @Override
                    public Response processWireFormat(final WireFormatInfo info) throws Exception {
                        ActiveMQConnection.this.onWireFormatInfo((WireFormatInfo)command);
                        return null;
                    }
                });
            }
            catch (Exception e) {
                this.onClientInternalException(e);
            }
        }
        for (final TransportListener listener : this.transportListeners) {
            listener.onCommand(command);
        }
    }
    
    protected void onWireFormatInfo(final WireFormatInfo info) {
        this.protocolVersion.set(info.getVersion());
    }
    
    public void onClientInternalException(final Throwable error) {
        if (!this.closed.get() && !this.closing.get()) {
            if (this.clientInternalExceptionListener != null) {
                this.executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        ActiveMQConnection.this.clientInternalExceptionListener.onException(error);
                    }
                });
            }
            else {
                ActiveMQConnection.LOG.debug("Async client internal exception occurred with no exception listener registered: " + error, error);
            }
        }
    }
    
    public void onAsyncException(Throwable error) {
        if (!this.closed.get() && !this.closing.get()) {
            if (this.exceptionListener != null) {
                if (!(error instanceof JMSException)) {
                    error = JMSExceptionSupport.create(error);
                }
                final JMSException e = (JMSException)error;
                this.executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        ActiveMQConnection.this.exceptionListener.onException(e);
                    }
                });
            }
            else {
                ActiveMQConnection.LOG.debug("Async exception with no exception listener: " + error, error);
            }
        }
    }
    
    @Override
    public void onException(final IOException error) {
        this.onAsyncException(error);
        if (!this.closing.get() && !this.closed.get()) {
            this.executor.execute(new Runnable() {
                @Override
                public void run() {
                    ActiveMQConnection.this.transportFailed(error);
                    ServiceSupport.dispose(ActiveMQConnection.this.transport);
                    ActiveMQConnection.this.brokerInfoReceived.countDown();
                    try {
                        ActiveMQConnection.this.cleanup();
                    }
                    catch (JMSException e) {
                        ActiveMQConnection.LOG.warn("Exception during connection cleanup, " + e, e);
                    }
                    for (final TransportListener listener : ActiveMQConnection.this.transportListeners) {
                        listener.onException(error);
                    }
                }
            });
        }
    }
    
    @Override
    public void transportInterupted() {
        this.transportInterruptionProcessingComplete = new CountDownLatch(this.dispatchers.size() - ((this.advisoryConsumer != null) ? 1 : 0));
        if (ActiveMQConnection.LOG.isDebugEnabled()) {
            ActiveMQConnection.LOG.debug("transport interrupted, dispatchers: " + this.transportInterruptionProcessingComplete.getCount());
        }
        this.signalInterruptionProcessingNeeded();
        for (final ActiveMQSession s : this.sessions) {
            s.clearMessagesInProgress();
        }
        for (final ActiveMQConnectionConsumer connectionConsumer : this.connectionConsumers) {
            connectionConsumer.clearMessagesInProgress();
        }
        for (final TransportListener listener : this.transportListeners) {
            listener.transportInterupted();
        }
    }
    
    @Override
    public void transportResumed() {
        for (final TransportListener listener : this.transportListeners) {
            listener.transportResumed();
        }
    }
    
    protected ActiveMQTempDestination createTempDestination(final boolean topic) throws JMSException {
        ActiveMQTempDestination dest;
        if (topic) {
            dest = new ActiveMQTempTopic(this.info.getConnectionId(), this.tempDestinationIdGenerator.getNextSequenceId());
        }
        else {
            dest = new ActiveMQTempQueue(this.info.getConnectionId(), this.tempDestinationIdGenerator.getNextSequenceId());
        }
        final DestinationInfo info = new DestinationInfo();
        info.setConnectionId(this.info.getConnectionId());
        info.setOperationType((byte)0);
        info.setDestination(dest);
        this.syncSendPacket(info);
        dest.setConnection(this);
        this.activeTempDestinations.put(dest, dest);
        return dest;
    }
    
    public void deleteTempDestination(final ActiveMQTempDestination destination) throws JMSException {
        this.checkClosedOrFailed();
        for (final ActiveMQSession s : this.sessions) {
            if (s.isInUse(destination)) {
                throw new JMSException("A consumer is consuming from the temporary destination");
            }
        }
        this.activeTempDestinations.remove(destination);
        final DestinationInfo destInfo = new DestinationInfo();
        destInfo.setConnectionId(this.info.getConnectionId());
        destInfo.setOperationType((byte)1);
        destInfo.setDestination(destination);
        destInfo.setTimeout(0L);
        this.syncSendPacket(destInfo);
    }
    
    public boolean isDeleted(final ActiveMQDestination dest) {
        return this.advisoryConsumer != null && !this.activeTempDestinations.contains(dest);
    }
    
    public boolean isCopyMessageOnSend() {
        return this.copyMessageOnSend;
    }
    
    public LongSequenceGenerator getLocalTransactionIdGenerator() {
        return this.localTransactionIdGenerator;
    }
    
    public boolean isUseCompression() {
        return this.useCompression;
    }
    
    public void setUseCompression(final boolean useCompression) {
        this.useCompression = useCompression;
    }
    
    public void destroyDestination(final ActiveMQDestination destination) throws JMSException {
        this.checkClosedOrFailed();
        this.ensureConnectionInfoSent();
        final DestinationInfo info = new DestinationInfo();
        info.setConnectionId(this.info.getConnectionId());
        info.setOperationType((byte)1);
        info.setDestination(destination);
        info.setTimeout(0L);
        this.syncSendPacket(info);
    }
    
    public boolean isDispatchAsync() {
        return this.dispatchAsync;
    }
    
    public void setDispatchAsync(final boolean asyncDispatch) {
        this.dispatchAsync = asyncDispatch;
    }
    
    public boolean isObjectMessageSerializationDefered() {
        return this.objectMessageSerializationDefered;
    }
    
    public void setObjectMessageSerializationDefered(final boolean objectMessageSerializationDefered) {
        this.objectMessageSerializationDefered = objectMessageSerializationDefered;
    }
    
    @Override
    public InputStream createInputStream(final Destination dest) throws JMSException {
        return this.createInputStream(dest, null);
    }
    
    @Override
    public InputStream createInputStream(final Destination dest, final String messageSelector) throws JMSException {
        return this.createInputStream(dest, messageSelector, false);
    }
    
    @Override
    public InputStream createInputStream(final Destination dest, final String messageSelector, final boolean noLocal) throws JMSException {
        return this.createInputStream(dest, messageSelector, noLocal, -1L);
    }
    
    @Override
    public InputStream createInputStream(final Destination dest, final String messageSelector, final boolean noLocal, final long timeout) throws JMSException {
        return this.doCreateInputStream(dest, messageSelector, noLocal, null, timeout);
    }
    
    @Override
    public InputStream createDurableInputStream(final Topic dest, final String name) throws JMSException {
        return this.createInputStream(dest, null, false);
    }
    
    @Override
    public InputStream createDurableInputStream(final Topic dest, final String name, final String messageSelector) throws JMSException {
        return this.createDurableInputStream(dest, name, messageSelector, false);
    }
    
    @Override
    public InputStream createDurableInputStream(final Topic dest, final String name, final String messageSelector, final boolean noLocal) throws JMSException {
        return this.createDurableInputStream(dest, name, messageSelector, noLocal, -1L);
    }
    
    @Override
    public InputStream createDurableInputStream(final Topic dest, final String name, final String messageSelector, final boolean noLocal, final long timeout) throws JMSException {
        return this.doCreateInputStream(dest, messageSelector, noLocal, name, timeout);
    }
    
    private InputStream doCreateInputStream(final Destination dest, final String messageSelector, final boolean noLocal, final String subName, final long timeout) throws JMSException {
        this.checkClosedOrFailed();
        this.ensureConnectionInfoSent();
        return new ActiveMQInputStream(this, this.createConsumerId(), ActiveMQDestination.transform(dest), messageSelector, noLocal, subName, this.prefetchPolicy.getInputStreamPrefetch(), timeout);
    }
    
    @Override
    public OutputStream createOutputStream(final Destination dest) throws JMSException {
        return this.createOutputStream(dest, null, 2, 4, 0L);
    }
    
    public OutputStream createNonPersistentOutputStream(final Destination dest) throws JMSException {
        return this.createOutputStream(dest, null, 1, 4, 0L);
    }
    
    @Override
    public OutputStream createOutputStream(final Destination dest, final Map<String, Object> streamProperties, final int deliveryMode, final int priority, final long timeToLive) throws JMSException {
        this.checkClosedOrFailed();
        this.ensureConnectionInfoSent();
        return new ActiveMQOutputStream(this, this.createProducerId(), ActiveMQDestination.transform(dest), streamProperties, deliveryMode, priority, timeToLive);
    }
    
    @Override
    public void unsubscribe(final String name) throws InvalidDestinationException, JMSException {
        this.checkClosedOrFailed();
        final RemoveSubscriptionInfo rsi = new RemoveSubscriptionInfo();
        rsi.setConnectionId(this.getConnectionInfo().getConnectionId());
        rsi.setSubscriptionName(name);
        rsi.setClientId(this.getConnectionInfo().getClientId());
        this.syncSendPacket(rsi);
    }
    
    void send(final ActiveMQDestination destination, final ActiveMQMessage msg, final MessageId messageId, final int deliveryMode, final int priority, final long timeToLive, final boolean async) throws JMSException {
        this.checkClosedOrFailed();
        if (destination.isTemporary() && this.isDeleted(destination)) {
            throw new JMSException("Cannot publish to a deleted Destination: " + destination);
        }
        msg.setJMSDestination(destination);
        msg.setJMSDeliveryMode(deliveryMode);
        long expiration = 0L;
        if (!this.isDisableTimeStampsByDefault()) {
            final long timeStamp = System.currentTimeMillis();
            msg.setJMSTimestamp(timeStamp);
            if (timeToLive > 0L) {
                expiration = timeToLive + timeStamp;
            }
        }
        msg.setJMSExpiration(expiration);
        msg.setJMSPriority(priority);
        msg.setJMSRedelivered(false);
        msg.setMessageId(messageId);
        msg.onSend();
        msg.setProducerId(msg.getMessageId().getProducerId());
        if (ActiveMQConnection.LOG.isDebugEnabled()) {
            ActiveMQConnection.LOG.debug("Sending message: " + msg);
        }
        if (async) {
            this.asyncSendPacket(msg);
        }
        else {
            this.syncSendPacket(msg);
        }
    }
    
    public void addOutputStream(final ActiveMQOutputStream stream) {
        this.outputStreams.add(stream);
    }
    
    public void removeOutputStream(final ActiveMQOutputStream stream) {
        this.outputStreams.remove(stream);
    }
    
    public void addInputStream(final ActiveMQInputStream stream) {
        this.inputStreams.add(stream);
    }
    
    public void removeInputStream(final ActiveMQInputStream stream) {
        this.inputStreams.remove(stream);
    }
    
    protected void onControlCommand(final ControlCommand command) {
        final String text = command.getCommand();
        if (text != null && text.equals("shutdown")) {
            ActiveMQConnection.LOG.info("JVM told to shutdown");
            System.exit(0);
        }
    }
    
    protected void onConnectionControl(final ConnectionControl command) {
        if (command.isFaultTolerant()) {
            this.optimizeAcknowledge = false;
            for (final ActiveMQSession s : this.sessions) {
                s.setOptimizeAcknowledge(false);
            }
        }
    }
    
    protected void onConsumerControl(final ConsumerControl command) {
        if (command.isClose()) {
            for (final ActiveMQSession s : this.sessions) {
                s.close(command.getConsumerId());
            }
        }
        else {
            for (final ActiveMQSession s : this.sessions) {
                s.setPrefetchSize(command.getConsumerId(), command.getPrefetch());
            }
        }
    }
    
    protected void transportFailed(final IOException error) {
        this.transportFailed.set(true);
        if (this.firstFailureError == null) {
            this.firstFailureError = error;
        }
    }
    
    public void setCopyMessageOnSend(final boolean copyMessageOnSend) {
        this.copyMessageOnSend = copyMessageOnSend;
    }
    
    @Override
    public String toString() {
        return "ActiveMQConnection {id=" + this.info.getConnectionId() + ",clientId=" + this.info.getClientId() + ",started=" + this.started.get() + "}";
    }
    
    protected BlobTransferPolicy createBlobTransferPolicy() {
        return new BlobTransferPolicy();
    }
    
    public int getProtocolVersion() {
        return this.protocolVersion.get();
    }
    
    public int getProducerWindowSize() {
        return this.producerWindowSize;
    }
    
    public void setProducerWindowSize(final int producerWindowSize) {
        this.producerWindowSize = producerWindowSize;
    }
    
    public void setAuditDepth(final int auditDepth) {
        this.connectionAudit.setAuditDepth(auditDepth);
    }
    
    public void setAuditMaximumProducerNumber(final int auditMaximumProducerNumber) {
        this.connectionAudit.setAuditMaximumProducerNumber(auditMaximumProducerNumber);
    }
    
    protected void removeDispatcher(final ActiveMQDispatcher dispatcher) {
        this.connectionAudit.removeDispatcher(dispatcher);
    }
    
    protected boolean isDuplicate(final ActiveMQDispatcher dispatcher, final Message message) {
        return this.checkForDuplicates && this.connectionAudit.isDuplicate(dispatcher, message);
    }
    
    protected void rollbackDuplicate(final ActiveMQDispatcher dispatcher, final Message message) {
        this.connectionAudit.rollbackDuplicate(dispatcher, message);
    }
    
    public IOException getFirstFailureError() {
        return this.firstFailureError;
    }
    
    protected void waitForTransportInterruptionProcessingToComplete() throws InterruptedException {
        final CountDownLatch cdl = this.transportInterruptionProcessingComplete;
        if (cdl != null) {
            if (!this.closed.get() && !this.transportFailed.get() && cdl.getCount() > 0L) {
                ActiveMQConnection.LOG.warn("dispatch paused, waiting for outstanding dispatch interruption processing (" + cdl.getCount() + ") to complete..");
                cdl.await(10L, TimeUnit.SECONDS);
            }
            this.signalInterruptionProcessingComplete();
        }
    }
    
    protected void transportInterruptionProcessingComplete() {
        final CountDownLatch cdl = this.transportInterruptionProcessingComplete;
        if (cdl != null) {
            cdl.countDown();
            try {
                this.signalInterruptionProcessingComplete();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private void signalInterruptionProcessingComplete() throws InterruptedException {
        final CountDownLatch cdl = this.transportInterruptionProcessingComplete;
        if (cdl.getCount() == 0L) {
            if (ActiveMQConnection.LOG.isDebugEnabled()) {
                ActiveMQConnection.LOG.debug("transportInterruptionProcessingComplete for: " + this.getConnectionInfo().getConnectionId());
            }
            this.transportInterruptionProcessingComplete = null;
            final FailoverTransport failoverTransport = this.transport.narrow(FailoverTransport.class);
            if (failoverTransport != null) {
                failoverTransport.connectionInterruptProcessingComplete(this.getConnectionInfo().getConnectionId());
                if (ActiveMQConnection.LOG.isDebugEnabled()) {
                    ActiveMQConnection.LOG.debug("notified failover transport (" + failoverTransport + ") of interruption completion for: " + this.getConnectionInfo().getConnectionId());
                }
            }
        }
    }
    
    private void signalInterruptionProcessingNeeded() {
        final FailoverTransport failoverTransport = this.transport.narrow(FailoverTransport.class);
        if (failoverTransport != null) {
            failoverTransport.getStateTracker().transportInterrupted(this.getConnectionInfo().getConnectionId());
            if (ActiveMQConnection.LOG.isDebugEnabled()) {
                ActiveMQConnection.LOG.debug("notified failover transport (" + failoverTransport + ") of pending interruption processing for: " + this.getConnectionInfo().getConnectionId());
            }
        }
    }
    
    public void setConsumerFailoverRedeliveryWaitPeriod(final long consumerFailoverRedeliveryWaitPeriod) {
        this.consumerFailoverRedeliveryWaitPeriod = consumerFailoverRedeliveryWaitPeriod;
    }
    
    public long getConsumerFailoverRedeliveryWaitPeriod() {
        return this.consumerFailoverRedeliveryWaitPeriod;
    }
    
    protected Scheduler getScheduler() {
        return this.scheduler;
    }
    
    protected ThreadPoolExecutor getExecutor() {
        return this.executor;
    }
    
    public boolean isCheckForDuplicates() {
        return this.checkForDuplicates;
    }
    
    public void setCheckForDuplicates(final boolean checkForDuplicates) {
        this.checkForDuplicates = checkForDuplicates;
    }
    
    static /* synthetic */ boolean access$472(final ActiveMQConnection x0, final int x1) {
        return x0.optimizeAcknowledge = ((byte)((x0.optimizeAcknowledge ? 1 : 0) & x1) != 0);
    }
    
    static {
        DEFAULT_USER = ActiveMQConnectionFactory.DEFAULT_USER;
        DEFAULT_PASSWORD = ActiveMQConnectionFactory.DEFAULT_PASSWORD;
        LOG = LoggerFactory.getLogger(ActiveMQConnection.class);
        CONNECTION_ID_GENERATOR = new IdGenerator();
    }
}
