// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region;

import org.slf4j.LoggerFactory;
import java.util.Iterator;
import java.util.List;
import org.apache.activemq.broker.TransportConnector;
import org.apache.activemq.command.ConsumerControl;
import org.apache.activemq.broker.region.policy.DeadLetterStrategy;
import org.apache.activemq.util.BrokerSupport;
import java.net.URI;
import org.apache.activemq.store.kahadb.plist.PListStore;
import org.apache.activemq.Service;
import org.apache.activemq.command.MessageDispatchNotification;
import org.apache.activemq.command.MessageDispatch;
import org.apache.activemq.util.InetAddressUtil;
import org.apache.activemq.command.TransactionId;
import org.apache.activemq.command.Response;
import org.apache.activemq.command.MessagePull;
import org.apache.activemq.command.MessageAck;
import org.apache.activemq.broker.ConsumerBrokerExchange;
import org.apache.activemq.command.Message;
import org.apache.activemq.broker.ProducerBrokerExchange;
import org.apache.activemq.command.RemoveSubscriptionInfo;
import org.apache.activemq.command.ConsumerInfo;
import org.apache.activemq.command.ProducerInfo;
import org.apache.activemq.command.DestinationInfo;
import javax.jms.JMSException;
import java.util.Collection;
import java.util.ArrayList;
import javax.jms.InvalidClientIDException;
import org.apache.activemq.command.ConnectionInfo;
import org.apache.activemq.broker.region.policy.PolicyMap;
import org.apache.activemq.util.ServiceStopper;
import org.apache.activemq.broker.Broker;
import java.util.Set;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Collections;
import java.util.HashMap;
import org.apache.activemq.usage.SystemUsage;
import org.apache.activemq.thread.TaskRunnerFactory;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.activemq.thread.Scheduler;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.util.LongSequenceGenerator;
import org.apache.activemq.command.BrokerInfo;
import org.apache.activemq.command.BrokerId;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.broker.Connection;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.state.ConnectionState;
import org.apache.activemq.command.ConnectionId;
import java.util.Map;
import org.apache.activemq.util.IdGenerator;
import org.slf4j.Logger;
import org.apache.activemq.broker.EmptyBroker;

public class RegionBroker extends EmptyBroker
{
    public static final String ORIGINAL_EXPIRATION = "originalExpiration";
    private static final Logger LOG;
    private static final IdGenerator BROKER_ID_GENERATOR;
    protected final DestinationStatistics destinationStatistics;
    protected DestinationFactory destinationFactory;
    protected final Map<ConnectionId, ConnectionState> connectionStates;
    private final Region queueRegion;
    private final Region topicRegion;
    private final Region tempQueueRegion;
    private final Region tempTopicRegion;
    protected final BrokerService brokerService;
    private boolean started;
    private boolean keepDurableSubsActive;
    private final CopyOnWriteArrayList<Connection> connections;
    private final Map<ActiveMQDestination, Destination> destinations;
    private final Map<BrokerId, BrokerInfo> brokerInfos;
    private final LongSequenceGenerator sequenceGenerator;
    private BrokerId brokerId;
    private String brokerName;
    private final Map<String, ConnectionContext> clientIdSet;
    private final DestinationInterceptor destinationInterceptor;
    private ConnectionContext adminConnectionContext;
    private final Scheduler scheduler;
    private final ThreadPoolExecutor executor;
    private final Runnable purgeInactiveDestinationsTask;
    
    public RegionBroker(final BrokerService brokerService, final TaskRunnerFactory taskRunnerFactory, final SystemUsage memoryManager, final DestinationFactory destinationFactory, final DestinationInterceptor destinationInterceptor, final Scheduler scheduler, final ThreadPoolExecutor executor) throws IOException {
        this.destinationStatistics = new DestinationStatistics();
        this.connectionStates = Collections.synchronizedMap(new HashMap<ConnectionId, ConnectionState>());
        this.connections = new CopyOnWriteArrayList<Connection>();
        this.destinations = new ConcurrentHashMap<ActiveMQDestination, Destination>();
        this.brokerInfos = new HashMap<BrokerId, BrokerInfo>();
        this.sequenceGenerator = new LongSequenceGenerator();
        this.clientIdSet = new HashMap<String, ConnectionContext>();
        this.purgeInactiveDestinationsTask = new Runnable() {
            @Override
            public void run() {
                RegionBroker.this.purgeInactiveDestinations();
            }
        };
        this.brokerService = brokerService;
        this.executor = executor;
        this.scheduler = scheduler;
        if (destinationFactory == null) {
            throw new IllegalArgumentException("null destinationFactory");
        }
        this.sequenceGenerator.setLastSequenceId(destinationFactory.getLastMessageBrokerSequenceId());
        this.destinationFactory = destinationFactory;
        this.queueRegion = this.createQueueRegion(memoryManager, taskRunnerFactory, destinationFactory);
        this.topicRegion = this.createTopicRegion(memoryManager, taskRunnerFactory, destinationFactory);
        this.destinationInterceptor = destinationInterceptor;
        this.tempQueueRegion = this.createTempQueueRegion(memoryManager, taskRunnerFactory, destinationFactory);
        this.tempTopicRegion = this.createTempTopicRegion(memoryManager, taskRunnerFactory, destinationFactory);
    }
    
    @Override
    public Map<ActiveMQDestination, Destination> getDestinationMap() {
        final Map<ActiveMQDestination, Destination> answer = this.getQueueRegion().getDestinationMap();
        answer.putAll(this.getTopicRegion().getDestinationMap());
        return answer;
    }
    
    @Override
    public Set<Destination> getDestinations(final ActiveMQDestination destination) {
        switch (destination.getDestinationType()) {
            case 1: {
                return this.queueRegion.getDestinations(destination);
            }
            case 2: {
                return this.topicRegion.getDestinations(destination);
            }
            case 5: {
                return this.tempQueueRegion.getDestinations(destination);
            }
            case 6: {
                return this.tempTopicRegion.getDestinations(destination);
            }
            default: {
                return Collections.emptySet();
            }
        }
    }
    
    @Override
    public Broker getAdaptor(final Class type) {
        if (type.isInstance(this)) {
            return this;
        }
        return null;
    }
    
    public Region getQueueRegion() {
        return this.queueRegion;
    }
    
    public Region getTempQueueRegion() {
        return this.tempQueueRegion;
    }
    
    public Region getTempTopicRegion() {
        return this.tempTopicRegion;
    }
    
    public Region getTopicRegion() {
        return this.topicRegion;
    }
    
    protected Region createTempTopicRegion(final SystemUsage memoryManager, final TaskRunnerFactory taskRunnerFactory, final DestinationFactory destinationFactory) {
        return new TempTopicRegion(this, this.destinationStatistics, memoryManager, taskRunnerFactory, destinationFactory);
    }
    
    protected Region createTempQueueRegion(final SystemUsage memoryManager, final TaskRunnerFactory taskRunnerFactory, final DestinationFactory destinationFactory) {
        return new TempQueueRegion(this, this.brokerService, this.destinationStatistics, memoryManager, taskRunnerFactory, destinationFactory);
    }
    
    protected Region createTopicRegion(final SystemUsage memoryManager, final TaskRunnerFactory taskRunnerFactory, final DestinationFactory destinationFactory) {
        return new TopicRegion(this, this.destinationStatistics, memoryManager, taskRunnerFactory, destinationFactory);
    }
    
    protected Region createQueueRegion(final SystemUsage memoryManager, final TaskRunnerFactory taskRunnerFactory, final DestinationFactory destinationFactory) {
        return new QueueRegion(this, this.destinationStatistics, memoryManager, taskRunnerFactory, destinationFactory);
    }
    
    @Override
    public void start() throws Exception {
        ((TopicRegion)this.topicRegion).setKeepDurableSubsActive(this.keepDurableSubsActive);
        this.started = true;
        this.queueRegion.start();
        this.topicRegion.start();
        this.tempQueueRegion.start();
        this.tempTopicRegion.start();
        final int period = this.brokerService.getSchedulePeriodForDestinationPurge();
        if (period > 0) {
            this.scheduler.executePeriodically(this.purgeInactiveDestinationsTask, period);
        }
    }
    
    @Override
    public void stop() throws Exception {
        this.started = false;
        this.scheduler.cancel(this.purgeInactiveDestinationsTask);
        final ServiceStopper ss = new ServiceStopper();
        this.doStop(ss);
        ss.throwFirstException();
        this.clientIdSet.clear();
        this.connections.clear();
        this.destinations.clear();
        this.brokerInfos.clear();
    }
    
    public PolicyMap getDestinationPolicy() {
        return (this.brokerService != null) ? this.brokerService.getDestinationPolicy() : null;
    }
    
    @Override
    public void addConnection(final ConnectionContext context, final ConnectionInfo info) throws Exception {
        final String clientId = info.getClientId();
        if (clientId == null) {
            throw new InvalidClientIDException("No clientID specified for connection request");
        }
        synchronized (this.clientIdSet) {
            final ConnectionContext oldContext = this.clientIdSet.get(clientId);
            if (oldContext != null) {
                if (!context.isFaultTolerant()) {
                    if (!context.isNetworkConnection()) {
                        throw new InvalidClientIDException("Broker: " + this.getBrokerName() + " - Client: " + clientId + " already connected from " + oldContext.getConnection().getRemoteAddress());
                    }
                }
                try {
                    this.removeConnection(oldContext, info, new Exception("remove stale client"));
                }
                catch (Exception e) {
                    RegionBroker.LOG.warn("Failed to remove stale connection ", e);
                }
            }
            else {
                this.clientIdSet.put(clientId, context);
            }
        }
        this.connections.add(context.getConnection());
    }
    
    @Override
    public void removeConnection(final ConnectionContext context, final ConnectionInfo info, final Throwable error) throws Exception {
        final String clientId = info.getClientId();
        if (clientId == null) {
            throw new InvalidClientIDException("No clientID specified for connection disconnect request");
        }
        synchronized (this.clientIdSet) {
            final ConnectionContext oldValue = this.clientIdSet.get(clientId);
            if (oldValue == context && this.isEqual(oldValue.getConnectionId(), info.getConnectionId())) {
                this.clientIdSet.remove(clientId);
            }
        }
        this.connections.remove(context.getConnection());
    }
    
    protected boolean isEqual(final ConnectionId connectionId, final ConnectionId connectionId2) {
        return connectionId == connectionId2 || (connectionId != null && connectionId.equals(connectionId2));
    }
    
    @Override
    public Connection[] getClients() throws Exception {
        final ArrayList<Connection> l = new ArrayList<Connection>(this.connections);
        final Connection[] rc = new Connection[l.size()];
        l.toArray(rc);
        return rc;
    }
    
    @Override
    public Destination addDestination(final ConnectionContext context, final ActiveMQDestination destination, final boolean create) throws Exception {
        Destination answer = this.destinations.get(destination);
        if (answer != null) {
            return answer;
        }
        switch (destination.getDestinationType()) {
            case 1: {
                answer = this.queueRegion.addDestination(context, destination, true);
                break;
            }
            case 2: {
                answer = this.topicRegion.addDestination(context, destination, true);
                break;
            }
            case 5: {
                answer = this.tempQueueRegion.addDestination(context, destination, create);
                break;
            }
            case 6: {
                answer = this.tempTopicRegion.addDestination(context, destination, create);
                break;
            }
            default: {
                throw this.createUnknownDestinationTypeException(destination);
            }
        }
        this.destinations.put(destination, answer);
        return answer;
    }
    
    @Override
    public void removeDestination(final ConnectionContext context, final ActiveMQDestination destination, final long timeout) throws Exception {
        if (this.destinations.containsKey(destination)) {
            switch (destination.getDestinationType()) {
                case 1: {
                    this.queueRegion.removeDestination(context, destination, timeout);
                    this.removeAdvisoryTopics("Queue.", context, destination, timeout);
                    break;
                }
                case 2: {
                    this.topicRegion.removeDestination(context, destination, timeout);
                    this.removeAdvisoryTopics("Topic.", context, destination, timeout);
                    break;
                }
                case 5: {
                    this.tempQueueRegion.removeDestination(context, destination, timeout);
                    break;
                }
                case 6: {
                    this.tempTopicRegion.removeDestination(context, destination, timeout);
                    break;
                }
                default: {
                    throw this.createUnknownDestinationTypeException(destination);
                }
            }
            this.destinations.remove(destination);
        }
    }
    
    public void removeAdvisoryTopics(final String destinationType, final ConnectionContext context, final ActiveMQDestination destination, final long timeout) throws Exception {
        if (this.brokerService.isAdvisorySupport()) {
            final String producerAdvisoryTopic = "ActiveMQ.Advisory.Producer." + destinationType + destination.getPhysicalName();
            final String consumerAdvisoryTopic = "ActiveMQ.Advisory.Consumer." + destinationType + destination.getPhysicalName();
            final ActiveMQDestination[] arr$;
            final ActiveMQDestination[] dests = arr$ = this.getDestinations();
            for (final ActiveMQDestination dest : arr$) {
                final String name = dest.getPhysicalName();
                Label_0139: {
                    if (!name.equals(producerAdvisoryTopic)) {
                        if (!name.equals(consumerAdvisoryTopic)) {
                            break Label_0139;
                        }
                    }
                    try {
                        this.removeDestination(context, dest, timeout);
                    }
                    catch (JMSException ex) {}
                }
            }
        }
    }
    
    @Override
    public void addDestinationInfo(final ConnectionContext context, final DestinationInfo info) throws Exception {
        this.addDestination(context, info.getDestination(), true);
    }
    
    @Override
    public void removeDestinationInfo(final ConnectionContext context, final DestinationInfo info) throws Exception {
        this.removeDestination(context, info.getDestination(), info.getTimeout());
    }
    
    @Override
    public ActiveMQDestination[] getDestinations() throws Exception {
        final ArrayList<ActiveMQDestination> l = new ArrayList<ActiveMQDestination>(this.getDestinationMap().keySet());
        final ActiveMQDestination[] rc = new ActiveMQDestination[l.size()];
        l.toArray(rc);
        return rc;
    }
    
    @Override
    public void addProducer(final ConnectionContext context, final ProducerInfo info) throws Exception {
        final ActiveMQDestination destination = info.getDestination();
        synchronized (this.purgeInactiveDestinationsTask) {
            if (destination != null) {
                context.getBroker().addDestination(context, destination, false);
                switch (destination.getDestinationType()) {
                    case 1: {
                        this.queueRegion.addProducer(context, info);
                        break;
                    }
                    case 2: {
                        this.topicRegion.addProducer(context, info);
                        break;
                    }
                    case 5: {
                        this.tempQueueRegion.addProducer(context, info);
                        break;
                    }
                    case 6: {
                        this.tempTopicRegion.addProducer(context, info);
                        break;
                    }
                }
            }
        }
    }
    
    @Override
    public void removeProducer(final ConnectionContext context, final ProducerInfo info) throws Exception {
        final ActiveMQDestination destination = info.getDestination();
        synchronized (this.purgeInactiveDestinationsTask) {
            if (destination != null) {
                switch (destination.getDestinationType()) {
                    case 1: {
                        this.queueRegion.removeProducer(context, info);
                        break;
                    }
                    case 2: {
                        this.topicRegion.removeProducer(context, info);
                        break;
                    }
                    case 5: {
                        this.tempQueueRegion.removeProducer(context, info);
                        break;
                    }
                    case 6: {
                        this.tempTopicRegion.removeProducer(context, info);
                        break;
                    }
                }
            }
        }
    }
    
    @Override
    public Subscription addConsumer(final ConnectionContext context, final ConsumerInfo info) throws Exception {
        final ActiveMQDestination destination = info.getDestination();
        if (this.destinationInterceptor != null) {
            this.destinationInterceptor.create(this, context, destination);
        }
        synchronized (this.purgeInactiveDestinationsTask) {
            switch (destination.getDestinationType()) {
                case 1: {
                    return this.queueRegion.addConsumer(context, info);
                }
                case 2: {
                    return this.topicRegion.addConsumer(context, info);
                }
                case 5: {
                    return this.tempQueueRegion.addConsumer(context, info);
                }
                case 6: {
                    return this.tempTopicRegion.addConsumer(context, info);
                }
                default: {
                    throw this.createUnknownDestinationTypeException(destination);
                }
            }
        }
    }
    
    @Override
    public void removeConsumer(final ConnectionContext context, final ConsumerInfo info) throws Exception {
        final ActiveMQDestination destination = info.getDestination();
        synchronized (this.purgeInactiveDestinationsTask) {
            switch (destination.getDestinationType()) {
                case 1: {
                    this.queueRegion.removeConsumer(context, info);
                    break;
                }
                case 2: {
                    this.topicRegion.removeConsumer(context, info);
                    break;
                }
                case 5: {
                    this.tempQueueRegion.removeConsumer(context, info);
                    break;
                }
                case 6: {
                    this.tempTopicRegion.removeConsumer(context, info);
                    break;
                }
                default: {
                    throw this.createUnknownDestinationTypeException(destination);
                }
            }
        }
    }
    
    @Override
    public void removeSubscription(final ConnectionContext context, final RemoveSubscriptionInfo info) throws Exception {
        synchronized (this.purgeInactiveDestinationsTask) {
            this.topicRegion.removeSubscription(context, info);
        }
    }
    
    @Override
    public void send(final ProducerBrokerExchange producerExchange, final Message message) throws Exception {
        message.setBrokerInTime(System.currentTimeMillis());
        if (producerExchange.isMutable() || producerExchange.getRegion() == null || (producerExchange.getRegion() != null && producerExchange.getRegion().getDestinationMap().get(message.getDestination()) == null)) {
            final ActiveMQDestination destination = message.getDestination();
            producerExchange.getConnectionContext().getBroker().addDestination(producerExchange.getConnectionContext(), destination, false);
            Region region = null;
            switch (destination.getDestinationType()) {
                case 1: {
                    region = this.queueRegion;
                    break;
                }
                case 2: {
                    region = this.topicRegion;
                    break;
                }
                case 5: {
                    region = this.tempQueueRegion;
                    break;
                }
                case 6: {
                    region = this.tempTopicRegion;
                    break;
                }
                default: {
                    throw this.createUnknownDestinationTypeException(destination);
                }
            }
            producerExchange.setRegion(region);
            producerExchange.setRegionDestination(null);
        }
        producerExchange.getRegion().send(producerExchange, message);
    }
    
    @Override
    public void acknowledge(final ConsumerBrokerExchange consumerExchange, final MessageAck ack) throws Exception {
        if (consumerExchange.isWildcard() || consumerExchange.getRegion() == null) {
            final ActiveMQDestination destination = ack.getDestination();
            Region region = null;
            switch (destination.getDestinationType()) {
                case 1: {
                    region = this.queueRegion;
                    break;
                }
                case 2: {
                    region = this.topicRegion;
                    break;
                }
                case 5: {
                    region = this.tempQueueRegion;
                    break;
                }
                case 6: {
                    region = this.tempTopicRegion;
                    break;
                }
                default: {
                    throw this.createUnknownDestinationTypeException(destination);
                }
            }
            consumerExchange.setRegion(region);
        }
        consumerExchange.getRegion().acknowledge(consumerExchange, ack);
    }
    
    @Override
    public Response messagePull(final ConnectionContext context, final MessagePull pull) throws Exception {
        final ActiveMQDestination destination = pull.getDestination();
        switch (destination.getDestinationType()) {
            case 1: {
                return this.queueRegion.messagePull(context, pull);
            }
            case 2: {
                return this.topicRegion.messagePull(context, pull);
            }
            case 5: {
                return this.tempQueueRegion.messagePull(context, pull);
            }
            case 6: {
                return this.tempTopicRegion.messagePull(context, pull);
            }
            default: {
                throw this.createUnknownDestinationTypeException(destination);
            }
        }
    }
    
    @Override
    public TransactionId[] getPreparedTransactions(final ConnectionContext context) throws Exception {
        throw new IllegalAccessException("Transaction operation not implemented by this broker.");
    }
    
    @Override
    public void beginTransaction(final ConnectionContext context, final TransactionId xid) throws Exception {
        throw new IllegalAccessException("Transaction operation not implemented by this broker.");
    }
    
    @Override
    public int prepareTransaction(final ConnectionContext context, final TransactionId xid) throws Exception {
        throw new IllegalAccessException("Transaction operation not implemented by this broker.");
    }
    
    @Override
    public void rollbackTransaction(final ConnectionContext context, final TransactionId xid) throws Exception {
        throw new IllegalAccessException("Transaction operation not implemented by this broker.");
    }
    
    @Override
    public void commitTransaction(final ConnectionContext context, final TransactionId xid, final boolean onePhase) throws Exception {
        throw new IllegalAccessException("Transaction operation not implemented by this broker.");
    }
    
    @Override
    public void forgetTransaction(final ConnectionContext context, final TransactionId transactionId) throws Exception {
        throw new IllegalAccessException("Transaction operation not implemented by this broker.");
    }
    
    @Override
    public void gc() {
        this.queueRegion.gc();
        this.topicRegion.gc();
    }
    
    @Override
    public BrokerId getBrokerId() {
        if (this.brokerId == null) {
            this.brokerId = new BrokerId(RegionBroker.BROKER_ID_GENERATOR.generateId());
        }
        return this.brokerId;
    }
    
    public void setBrokerId(final BrokerId brokerId) {
        this.brokerId = brokerId;
    }
    
    @Override
    public String getBrokerName() {
        if (this.brokerName == null) {
            try {
                this.brokerName = InetAddressUtil.getLocalHostName().toLowerCase();
            }
            catch (Exception e) {
                this.brokerName = "localhost";
            }
        }
        return this.brokerName;
    }
    
    public void setBrokerName(final String brokerName) {
        this.brokerName = brokerName;
    }
    
    public DestinationStatistics getDestinationStatistics() {
        return this.destinationStatistics;
    }
    
    protected JMSException createUnknownDestinationTypeException(final ActiveMQDestination destination) {
        return new JMSException("Unknown destination type: " + destination.getDestinationType());
    }
    
    @Override
    public synchronized void addBroker(final Connection connection, final BrokerInfo info) {
        BrokerInfo existing = this.brokerInfos.get(info.getBrokerId());
        if (existing == null) {
            existing = info.copy();
            existing.setPeerBrokerInfos(null);
            this.brokerInfos.put(info.getBrokerId(), existing);
        }
        existing.incrementRefCount();
        RegionBroker.LOG.debug(this.getBrokerName() + " addBroker:" + info.getBrokerName() + " brokerInfo size : " + this.brokerInfos.size());
        this.addBrokerInClusterUpdate();
    }
    
    @Override
    public synchronized void removeBroker(final Connection connection, final BrokerInfo info) {
        if (info != null) {
            final BrokerInfo existing = this.brokerInfos.get(info.getBrokerId());
            if (existing != null && existing.decrementRefCount() == 0) {
                this.brokerInfos.remove(info.getBrokerId());
            }
            RegionBroker.LOG.debug(this.getBrokerName() + " removeBroker:" + info.getBrokerName() + " brokerInfo size : " + this.brokerInfos.size());
            this.removeBrokerInClusterUpdate();
        }
    }
    
    @Override
    public synchronized BrokerInfo[] getPeerBrokerInfos() {
        BrokerInfo[] result = new BrokerInfo[this.brokerInfos.size()];
        result = this.brokerInfos.values().toArray(result);
        return result;
    }
    
    @Override
    public void preProcessDispatch(final MessageDispatch messageDispatch) {
        final Message message = messageDispatch.getMessage();
        if (message != null) {
            final long endTime = System.currentTimeMillis();
            message.setBrokerOutTime(endTime);
            if (this.getBrokerService().isEnableStatistics()) {
                final long totalTime = endTime - message.getBrokerInTime();
                message.getRegionDestination().getDestinationStatistics().getProcessTime().addTime(totalTime);
            }
        }
    }
    
    @Override
    public void postProcessDispatch(final MessageDispatch messageDispatch) {
    }
    
    @Override
    public void processDispatchNotification(final MessageDispatchNotification messageDispatchNotification) throws Exception {
        final ActiveMQDestination destination = messageDispatchNotification.getDestination();
        switch (destination.getDestinationType()) {
            case 1: {
                this.queueRegion.processDispatchNotification(messageDispatchNotification);
                break;
            }
            case 2: {
                this.topicRegion.processDispatchNotification(messageDispatchNotification);
                break;
            }
            case 5: {
                this.tempQueueRegion.processDispatchNotification(messageDispatchNotification);
                break;
            }
            case 6: {
                this.tempTopicRegion.processDispatchNotification(messageDispatchNotification);
                break;
            }
            default: {
                throw this.createUnknownDestinationTypeException(destination);
            }
        }
    }
    
    public boolean isSlaveBroker() {
        return this.brokerService.isSlave();
    }
    
    @Override
    public boolean isStopped() {
        return !this.started;
    }
    
    @Override
    public Set<ActiveMQDestination> getDurableDestinations() {
        return this.destinationFactory.getDestinations();
    }
    
    protected void doStop(final ServiceStopper ss) {
        ss.stop(this.queueRegion);
        ss.stop(this.topicRegion);
        ss.stop(this.tempQueueRegion);
        ss.stop(this.tempTopicRegion);
    }
    
    public boolean isKeepDurableSubsActive() {
        return this.keepDurableSubsActive;
    }
    
    public void setKeepDurableSubsActive(final boolean keepDurableSubsActive) {
        this.keepDurableSubsActive = keepDurableSubsActive;
    }
    
    public DestinationInterceptor getDestinationInterceptor() {
        return this.destinationInterceptor;
    }
    
    @Override
    public ConnectionContext getAdminConnectionContext() {
        return this.adminConnectionContext;
    }
    
    @Override
    public void setAdminConnectionContext(final ConnectionContext adminConnectionContext) {
        this.adminConnectionContext = adminConnectionContext;
    }
    
    public Map<ConnectionId, ConnectionState> getConnectionStates() {
        return this.connectionStates;
    }
    
    @Override
    public PListStore getTempDataStore() {
        return this.brokerService.getTempDataStore();
    }
    
    @Override
    public URI getVmConnectorURI() {
        return this.brokerService.getVmConnectorURI();
    }
    
    @Override
    public void brokerServiceStarted() {
    }
    
    @Override
    public BrokerService getBrokerService() {
        return this.brokerService;
    }
    
    @Override
    public boolean isExpired(final MessageReference messageReference) {
        boolean expired = false;
        if (messageReference.isExpired()) {
            try {
                final Message message = messageReference.getMessage();
                synchronized (message) {
                    expired = this.stampAsExpired(message);
                }
            }
            catch (IOException e) {
                RegionBroker.LOG.warn("unexpected exception on message expiry determination for: " + messageReference, e);
            }
        }
        return expired;
    }
    
    private boolean stampAsExpired(final Message message) throws IOException {
        boolean stamped = false;
        if (message.getProperty("originalExpiration") == null) {
            final long expiration = message.getExpiration();
            message.setProperty("originalExpiration", new Long(expiration));
            stamped = true;
        }
        return stamped;
    }
    
    @Override
    public void messageExpired(final ConnectionContext context, final MessageReference node, final Subscription subscription) {
        if (RegionBroker.LOG.isDebugEnabled()) {
            RegionBroker.LOG.debug("Message expired " + node);
        }
        this.getRoot().sendToDeadLetterQueue(context, node, subscription);
    }
    
    @Override
    public void sendToDeadLetterQueue(final ConnectionContext context, final MessageReference node, final Subscription subscription) {
        try {
            if (node != null) {
                Message message = node.getMessage();
                if (message != null && node.getRegionDestination() != null) {
                    final DeadLetterStrategy deadLetterStrategy = node.getRegionDestination().getDeadLetterStrategy();
                    if (deadLetterStrategy != null) {
                        if (deadLetterStrategy.isSendToDeadLetterQueue(message)) {
                            message = message.copy();
                            this.stampAsExpired(message);
                            message.setExpiration(0L);
                            if (!message.isPersistent()) {
                                message.setPersistent(true);
                                message.setProperty("originalDeliveryMode", "NON_PERSISTENT");
                            }
                            final ActiveMQDestination deadLetterDestination = deadLetterStrategy.getDeadLetterQueueFor(message, subscription);
                            if (context.getBroker() == null) {
                                context.setBroker(this.getRoot());
                            }
                            BrokerSupport.resendNoCopy(context, message, deadLetterDestination);
                        }
                    }
                    else if (RegionBroker.LOG.isDebugEnabled()) {
                        RegionBroker.LOG.debug("Dead Letter message with no DLQ strategy in place, message id: " + message.getMessageId() + ", destination: " + message.getDestination());
                    }
                }
            }
        }
        catch (Exception e) {
            RegionBroker.LOG.warn("Caught an exception sending to DLQ: " + node, e);
        }
    }
    
    @Override
    public Broker getRoot() {
        try {
            return this.getBrokerService().getBroker();
        }
        catch (Exception e) {
            RegionBroker.LOG.error("Trying to get Root Broker " + e);
            throw new RuntimeException("The broker from the BrokerService should not throw an exception");
        }
    }
    
    @Override
    public long getBrokerSequenceId() {
        synchronized (this.sequenceGenerator) {
            return this.sequenceGenerator.getNextSequenceId();
        }
    }
    
    @Override
    public Scheduler getScheduler() {
        return this.scheduler;
    }
    
    @Override
    public ThreadPoolExecutor getExecutor() {
        return this.executor;
    }
    
    @Override
    public void processConsumerControl(final ConsumerBrokerExchange consumerExchange, final ConsumerControl control) {
        final ActiveMQDestination destination = control.getDestination();
        switch (destination.getDestinationType()) {
            case 1: {
                this.queueRegion.processConsumerControl(consumerExchange, control);
                break;
            }
            case 2: {
                this.topicRegion.processConsumerControl(consumerExchange, control);
                break;
            }
            case 5: {
                this.tempQueueRegion.processConsumerControl(consumerExchange, control);
                break;
            }
            case 6: {
                this.tempTopicRegion.processConsumerControl(consumerExchange, control);
                break;
            }
            default: {
                RegionBroker.LOG.warn("unmatched destination: " + destination + ", in consumerControl: " + control);
                break;
            }
        }
    }
    
    protected void addBrokerInClusterUpdate() {
        final List<TransportConnector> connectors = this.brokerService.getTransportConnectors();
        for (final TransportConnector connector : connectors) {
            if (connector.isUpdateClusterClients()) {
                connector.updateClientClusterInfo();
            }
        }
    }
    
    protected void removeBrokerInClusterUpdate() {
        final List<TransportConnector> connectors = this.brokerService.getTransportConnectors();
        for (final TransportConnector connector : connectors) {
            if (connector.isUpdateClusterClients() && connector.isUpdateClusterClientsOnRemove()) {
                connector.updateClientClusterInfo();
            }
        }
    }
    
    protected void purgeInactiveDestinations() {
        synchronized (this.purgeInactiveDestinationsTask) {
            final List<BaseDestination> list = new ArrayList<BaseDestination>();
            final Map<ActiveMQDestination, Destination> map = this.getDestinationMap();
            final long timeStamp = System.currentTimeMillis();
            for (final Destination d : map.values()) {
                if (d instanceof BaseDestination) {
                    final BaseDestination bd = (BaseDestination)d;
                    bd.markForGC(timeStamp);
                    if (!bd.canGC()) {
                        continue;
                    }
                    list.add(bd);
                }
            }
            if (!list.isEmpty()) {
                final ConnectionContext context = BrokerSupport.getConnectionContext(this);
                context.setBroker(this);
                for (final BaseDestination dest : list) {
                    dest.getLog().info(dest.getName() + " Inactive for longer than " + dest.getInactiveTimoutBeforeGC() + " ms - removing ...");
                    try {
                        this.getRoot().removeDestination(context, dest.getActiveMQDestination(), 0L);
                    }
                    catch (Exception e) {
                        RegionBroker.LOG.error("Failed to remove inactive destination " + dest, e);
                    }
                }
            }
        }
    }
    
    static {
        LOG = LoggerFactory.getLogger(RegionBroker.class);
        BROKER_ID_GENERATOR = new IdGenerator();
    }
}
