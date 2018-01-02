// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.advisory;

import org.slf4j.LoggerFactory;
import java.util.Map;
import org.apache.activemq.state.ProducerState;
import org.apache.activemq.broker.ProducerBrokerExchange;
import org.apache.activemq.command.MessageId;
import org.apache.activemq.command.DataStructure;
import java.util.Set;
import org.apache.activemq.command.BrokerInfo;
import org.apache.activemq.security.SecurityContext;
import org.apache.activemq.usage.Usage;
import org.apache.activemq.broker.region.TopicSubscription;
import org.apache.activemq.command.Message;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.broker.region.MessageReference;
import org.apache.activemq.broker.region.Destination;
import java.util.Iterator;
import org.apache.activemq.broker.region.Subscription;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.command.Command;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.util.LongSequenceGenerator;
import org.apache.activemq.command.DestinationInfo;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ProducerInfo;
import org.apache.activemq.command.ProducerId;
import org.apache.activemq.command.ConsumerInfo;
import org.apache.activemq.command.ConsumerId;
import org.apache.activemq.command.ConnectionInfo;
import org.apache.activemq.command.ConnectionId;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.activemq.util.IdGenerator;
import org.slf4j.Logger;
import org.apache.activemq.broker.BrokerFilter;

public class AdvisoryBroker extends BrokerFilter
{
    private static final Logger LOG;
    private static final IdGenerator ID_GENERATOR;
    protected final ConcurrentHashMap<ConnectionId, ConnectionInfo> connections;
    protected final ConcurrentHashMap<ConsumerId, ConsumerInfo> consumers;
    protected final ConcurrentHashMap<ProducerId, ProducerInfo> producers;
    protected final ConcurrentHashMap<ActiveMQDestination, DestinationInfo> destinations;
    protected final ProducerId advisoryProducerId;
    private final LongSequenceGenerator messageIdGenerator;
    
    public AdvisoryBroker(final Broker next) {
        super(next);
        this.connections = new ConcurrentHashMap<ConnectionId, ConnectionInfo>();
        this.consumers = new ConcurrentHashMap<ConsumerId, ConsumerInfo>();
        this.producers = new ConcurrentHashMap<ProducerId, ProducerInfo>();
        this.destinations = new ConcurrentHashMap<ActiveMQDestination, DestinationInfo>();
        this.advisoryProducerId = new ProducerId();
        this.messageIdGenerator = new LongSequenceGenerator();
        this.advisoryProducerId.setConnectionId(AdvisoryBroker.ID_GENERATOR.generateId());
    }
    
    @Override
    public void addConnection(final ConnectionContext context, final ConnectionInfo info) throws Exception {
        super.addConnection(context, info);
        final ActiveMQTopic topic = AdvisorySupport.getConnectionAdvisoryTopic();
        final ConnectionInfo copy = info.copy();
        copy.setUserName("");
        copy.setPassword("");
        this.fireAdvisory(context, topic, copy);
        this.connections.put(copy.getConnectionId(), copy);
    }
    
    @Override
    public Subscription addConsumer(final ConnectionContext context, final ConsumerInfo info) throws Exception {
        final Subscription answer = super.addConsumer(context, info);
        if (!AdvisorySupport.isAdvisoryTopic(info.getDestination())) {
            final ActiveMQTopic topic = AdvisorySupport.getConsumerAdvisoryTopic(info.getDestination());
            this.consumers.put(info.getConsumerId(), info);
            this.fireConsumerAdvisory(context, info.getDestination(), topic, info);
        }
        else {
            if (AdvisorySupport.isConnectionAdvisoryTopic(info.getDestination())) {
                for (final ConnectionInfo value : this.connections.values()) {
                    final ActiveMQTopic topic2 = AdvisorySupport.getConnectionAdvisoryTopic();
                    this.fireAdvisory(context, topic2, value, info.getConsumerId());
                }
            }
            if (AdvisorySupport.isDestinationAdvisoryTopic(info.getDestination())) {
                for (final DestinationInfo value2 : this.destinations.values()) {
                    final ActiveMQTopic topic2 = AdvisorySupport.getDestinationAdvisoryTopic(value2.getDestination());
                    this.fireAdvisory(context, topic2, value2, info.getConsumerId());
                }
            }
            if (AdvisorySupport.isProducerAdvisoryTopic(info.getDestination())) {
                for (final ProducerInfo value3 : this.producers.values()) {
                    final ActiveMQTopic topic2 = AdvisorySupport.getProducerAdvisoryTopic(value3.getDestination());
                    this.fireProducerAdvisory(context, value3.getDestination(), topic2, value3, info.getConsumerId());
                }
            }
            if (AdvisorySupport.isConsumerAdvisoryTopic(info.getDestination())) {
                for (final ConsumerInfo value4 : this.consumers.values()) {
                    final ActiveMQTopic topic2 = AdvisorySupport.getConsumerAdvisoryTopic(value4.getDestination());
                    this.fireConsumerAdvisory(context, value4.getDestination(), topic2, value4, info.getConsumerId());
                }
            }
        }
        return answer;
    }
    
    @Override
    public void addProducer(final ConnectionContext context, final ProducerInfo info) throws Exception {
        super.addProducer(context, info);
        if (info.getDestination() != null && !AdvisorySupport.isAdvisoryTopic(info.getDestination())) {
            final ActiveMQTopic topic = AdvisorySupport.getProducerAdvisoryTopic(info.getDestination());
            this.fireProducerAdvisory(context, info.getDestination(), topic, info);
            this.producers.put(info.getProducerId(), info);
        }
    }
    
    @Override
    public Destination addDestination(final ConnectionContext context, final ActiveMQDestination destination, final boolean create) throws Exception {
        final Destination answer = super.addDestination(context, destination, create);
        if (!AdvisorySupport.isAdvisoryTopic(destination)) {
            final DestinationInfo info = new DestinationInfo(context.getConnectionId(), (byte)0, destination);
            final DestinationInfo previous = this.destinations.putIfAbsent(destination, info);
            if (previous == null) {
                final ActiveMQTopic topic = AdvisorySupport.getDestinationAdvisoryTopic(destination);
                this.fireAdvisory(context, topic, info);
            }
        }
        return answer;
    }
    
    @Override
    public void addDestinationInfo(final ConnectionContext context, final DestinationInfo info) throws Exception {
        final ActiveMQDestination destination = info.getDestination();
        this.next.addDestinationInfo(context, info);
        if (!AdvisorySupport.isAdvisoryTopic(destination)) {
            final DestinationInfo previous = this.destinations.putIfAbsent(destination, info);
            if (previous == null) {
                final ActiveMQTopic topic = AdvisorySupport.getDestinationAdvisoryTopic(destination);
                this.fireAdvisory(context, topic, info);
            }
        }
    }
    
    @Override
    public void removeDestination(final ConnectionContext context, final ActiveMQDestination destination, final long timeout) throws Exception {
        super.removeDestination(context, destination, timeout);
        final DestinationInfo info = this.destinations.remove(destination);
        if (info != null) {
            info.setDestination(destination);
            info.setOperationType((byte)1);
            final ActiveMQTopic topic = AdvisorySupport.getDestinationAdvisoryTopic(destination);
            this.fireAdvisory(context, topic, info);
            try {
                this.next.removeDestination(context, AdvisorySupport.getConsumerAdvisoryTopic(info.getDestination()), -1L);
            }
            catch (Exception ex) {}
            try {
                this.next.removeDestination(context, AdvisorySupport.getProducerAdvisoryTopic(info.getDestination()), -1L);
            }
            catch (Exception ex2) {}
        }
    }
    
    @Override
    public void removeDestinationInfo(final ConnectionContext context, final DestinationInfo destInfo) throws Exception {
        super.removeDestinationInfo(context, destInfo);
        final DestinationInfo info = this.destinations.remove(destInfo.getDestination());
        if (info != null) {
            info.setDestination(destInfo.getDestination());
            info.setOperationType((byte)1);
            final ActiveMQTopic topic = AdvisorySupport.getDestinationAdvisoryTopic(destInfo.getDestination());
            this.fireAdvisory(context, topic, info);
            try {
                this.next.removeDestination(context, AdvisorySupport.getConsumerAdvisoryTopic(info.getDestination()), -1L);
            }
            catch (Exception ex) {}
            try {
                this.next.removeDestination(context, AdvisorySupport.getProducerAdvisoryTopic(info.getDestination()), -1L);
            }
            catch (Exception ex2) {}
        }
    }
    
    @Override
    public void removeConnection(final ConnectionContext context, final ConnectionInfo info, final Throwable error) throws Exception {
        super.removeConnection(context, info, error);
        final ActiveMQTopic topic = AdvisorySupport.getConnectionAdvisoryTopic();
        this.fireAdvisory(context, topic, info.createRemoveCommand());
        this.connections.remove(info.getConnectionId());
    }
    
    @Override
    public void removeConsumer(final ConnectionContext context, final ConsumerInfo info) throws Exception {
        super.removeConsumer(context, info);
        final ActiveMQDestination dest = info.getDestination();
        if (!AdvisorySupport.isAdvisoryTopic(dest)) {
            final ActiveMQTopic topic = AdvisorySupport.getConsumerAdvisoryTopic(dest);
            this.consumers.remove(info.getConsumerId());
            if (!dest.isTemporary() || this.destinations.containsKey(dest)) {
                this.fireConsumerAdvisory(context, dest, topic, info.createRemoveCommand());
            }
        }
    }
    
    @Override
    public void removeProducer(final ConnectionContext context, final ProducerInfo info) throws Exception {
        super.removeProducer(context, info);
        final ActiveMQDestination dest = info.getDestination();
        if (info.getDestination() != null && !AdvisorySupport.isAdvisoryTopic(dest)) {
            final ActiveMQTopic topic = AdvisorySupport.getProducerAdvisoryTopic(dest);
            this.producers.remove(info.getProducerId());
            if (!dest.isTemporary() || this.destinations.contains(dest)) {
                this.fireProducerAdvisory(context, dest, topic, info.createRemoveCommand());
            }
        }
    }
    
    @Override
    public void messageExpired(final ConnectionContext context, final MessageReference messageReference, final Subscription subscription) {
        super.messageExpired(context, messageReference, subscription);
        try {
            if (!messageReference.isAdvisory()) {
                final ActiveMQTopic topic = AdvisorySupport.getExpiredMessageTopic(messageReference.getMessage().getDestination());
                final Message payload = messageReference.getMessage().copy();
                payload.clearBody();
                final ActiveMQMessage advisoryMessage = new ActiveMQMessage();
                advisoryMessage.setStringProperty("orignalMessageId", payload.getMessageId().toString());
                this.fireAdvisory(context, topic, payload, null, advisoryMessage);
            }
        }
        catch (Exception e) {
            this.handleFireFailure("expired", e);
        }
    }
    
    @Override
    public void messageConsumed(final ConnectionContext context, final MessageReference messageReference) {
        super.messageConsumed(context, messageReference);
        try {
            if (!messageReference.isAdvisory()) {
                final ActiveMQTopic topic = AdvisorySupport.getMessageConsumedAdvisoryTopic(messageReference.getMessage().getDestination());
                final Message payload = messageReference.getMessage().copy();
                payload.clearBody();
                this.fireAdvisory(context, topic, payload);
            }
        }
        catch (Exception e) {
            this.handleFireFailure("consumed", e);
        }
    }
    
    @Override
    public void messageDelivered(final ConnectionContext context, final MessageReference messageReference) {
        super.messageDelivered(context, messageReference);
        try {
            if (!messageReference.isAdvisory()) {
                final ActiveMQTopic topic = AdvisorySupport.getMessageDeliveredAdvisoryTopic(messageReference.getMessage().getDestination());
                final Message payload = messageReference.getMessage().copy();
                payload.clearBody();
                this.fireAdvisory(context, topic, payload);
            }
        }
        catch (Exception e) {
            this.handleFireFailure("delivered", e);
        }
    }
    
    @Override
    public void messageDiscarded(final ConnectionContext context, final Subscription sub, final MessageReference messageReference) {
        super.messageDiscarded(context, sub, messageReference);
        try {
            if (!messageReference.isAdvisory()) {
                final ActiveMQTopic topic = AdvisorySupport.getMessageDiscardedAdvisoryTopic(messageReference.getMessage().getDestination());
                final Message payload = messageReference.getMessage().copy();
                payload.clearBody();
                final ActiveMQMessage advisoryMessage = new ActiveMQMessage();
                if (sub instanceof TopicSubscription) {
                    advisoryMessage.setIntProperty("discardedCount", ((TopicSubscription)sub).discarded());
                }
                advisoryMessage.setStringProperty("consumerId", sub.getConsumerInfo().getConsumerId().toString());
                this.fireAdvisory(context, topic, payload, null, advisoryMessage);
            }
        }
        catch (Exception e) {
            this.handleFireFailure("discarded", e);
        }
    }
    
    @Override
    public void slowConsumer(final ConnectionContext context, final Destination destination, final Subscription subs) {
        super.slowConsumer(context, destination, subs);
        try {
            final ActiveMQTopic topic = AdvisorySupport.getSlowConsumerAdvisoryTopic(destination.getActiveMQDestination());
            final ActiveMQMessage advisoryMessage = new ActiveMQMessage();
            advisoryMessage.setStringProperty("consumerId", subs.getConsumerInfo().getConsumerId().toString());
            this.fireAdvisory(context, topic, subs.getConsumerInfo(), null, advisoryMessage);
        }
        catch (Exception e) {
            this.handleFireFailure("slow consumer", e);
        }
    }
    
    @Override
    public void fastProducer(final ConnectionContext context, final ProducerInfo producerInfo) {
        super.fastProducer(context, producerInfo);
        try {
            final ActiveMQTopic topic = AdvisorySupport.getFastProducerAdvisoryTopic(producerInfo.getDestination());
            final ActiveMQMessage advisoryMessage = new ActiveMQMessage();
            advisoryMessage.setStringProperty("producerId", producerInfo.getProducerId().toString());
            this.fireAdvisory(context, topic, producerInfo, null, advisoryMessage);
        }
        catch (Exception e) {
            this.handleFireFailure("fast producer", e);
        }
    }
    
    @Override
    public void isFull(final ConnectionContext context, final Destination destination, final Usage usage) {
        super.isFull(context, destination, usage);
        if (!AdvisorySupport.isAdvisoryTopic(destination.getActiveMQDestination())) {
            try {
                final ActiveMQTopic topic = AdvisorySupport.getFullAdvisoryTopic(destination.getActiveMQDestination());
                final ActiveMQMessage advisoryMessage = new ActiveMQMessage();
                advisoryMessage.setStringProperty("usageName", usage.getName());
                this.fireAdvisory(context, topic, null, null, advisoryMessage);
            }
            catch (Exception e) {
                this.handleFireFailure("is full", e);
            }
        }
    }
    
    @Override
    public void nowMasterBroker() {
        super.nowMasterBroker();
        try {
            final ActiveMQTopic topic = AdvisorySupport.getMasterBrokerAdvisoryTopic();
            final ActiveMQMessage advisoryMessage = new ActiveMQMessage();
            final ConnectionContext context = new ConnectionContext();
            context.setSecurityContext(SecurityContext.BROKER_SECURITY_CONTEXT);
            context.setBroker(this.getBrokerService().getBroker());
            this.fireAdvisory(context, topic, null, null, advisoryMessage);
        }
        catch (Exception e) {
            this.handleFireFailure("now master broker", e);
        }
    }
    
    @Override
    public void sendToDeadLetterQueue(final ConnectionContext context, final MessageReference messageReference, final Subscription subscription) {
        super.sendToDeadLetterQueue(context, messageReference, subscription);
        try {
            if (!messageReference.isAdvisory()) {
                final ActiveMQTopic topic = AdvisorySupport.getMessageDLQdAdvisoryTopic(messageReference.getMessage().getDestination());
                final Message payload = messageReference.getMessage().copy();
                payload.clearBody();
                this.fireAdvisory(context, topic, payload);
            }
        }
        catch (Exception e) {
            this.handleFireFailure("add to DLQ", e);
        }
    }
    
    @Override
    public void networkBridgeStarted(final BrokerInfo brokerInfo, final boolean createdByDuplex) {
        try {
            if (brokerInfo != null) {
                final ActiveMQMessage advisoryMessage = new ActiveMQMessage();
                advisoryMessage.setBooleanProperty("started", true);
                advisoryMessage.setBooleanProperty("createdByDuplex", createdByDuplex);
                final ActiveMQTopic topic = AdvisorySupport.getNetworkBridgeAdvisoryTopic();
                final ConnectionContext context = new ConnectionContext();
                context.setSecurityContext(SecurityContext.BROKER_SECURITY_CONTEXT);
                context.setBroker(this.getBrokerService().getBroker());
                this.fireAdvisory(context, topic, brokerInfo, null, advisoryMessage);
            }
        }
        catch (Exception e) {
            this.handleFireFailure("network bridge started", e);
        }
    }
    
    @Override
    public void networkBridgeStopped(final BrokerInfo brokerInfo) {
        try {
            if (brokerInfo != null) {
                final ActiveMQMessage advisoryMessage = new ActiveMQMessage();
                advisoryMessage.setBooleanProperty("started", false);
                final ActiveMQTopic topic = AdvisorySupport.getNetworkBridgeAdvisoryTopic();
                final ConnectionContext context = new ConnectionContext();
                context.setSecurityContext(SecurityContext.BROKER_SECURITY_CONTEXT);
                context.setBroker(this.getBrokerService().getBroker());
                this.fireAdvisory(context, topic, brokerInfo, null, advisoryMessage);
            }
        }
        catch (Exception e) {
            this.handleFireFailure("network bridge stopped", e);
        }
    }
    
    private void handleFireFailure(final String message, final Throwable cause) {
        AdvisoryBroker.LOG.warn("Failed to fire " + message + " advisory, reason: " + cause);
        if (AdvisoryBroker.LOG.isDebugEnabled()) {
            AdvisoryBroker.LOG.debug(message + " detail", cause);
        }
    }
    
    protected void fireAdvisory(final ConnectionContext context, final ActiveMQTopic topic, final Command command) throws Exception {
        this.fireAdvisory(context, topic, command, null);
    }
    
    protected void fireAdvisory(final ConnectionContext context, final ActiveMQTopic topic, final Command command, final ConsumerId targetConsumerId) throws Exception {
        final ActiveMQMessage advisoryMessage = new ActiveMQMessage();
        this.fireAdvisory(context, topic, command, targetConsumerId, advisoryMessage);
    }
    
    protected void fireConsumerAdvisory(final ConnectionContext context, final ActiveMQDestination consumerDestination, final ActiveMQTopic topic, final Command command) throws Exception {
        this.fireConsumerAdvisory(context, consumerDestination, topic, command, null);
    }
    
    protected void fireConsumerAdvisory(final ConnectionContext context, final ActiveMQDestination consumerDestination, final ActiveMQTopic topic, final Command command, final ConsumerId targetConsumerId) throws Exception {
        final ActiveMQMessage advisoryMessage = new ActiveMQMessage();
        int count = 0;
        final Set<Destination> set = this.getDestinations(consumerDestination);
        if (set != null) {
            for (final Destination dest : set) {
                count += (int)dest.getDestinationStatistics().getConsumers().getCount();
            }
        }
        advisoryMessage.setIntProperty("consumerCount", count);
        this.fireAdvisory(context, topic, command, targetConsumerId, advisoryMessage);
    }
    
    protected void fireProducerAdvisory(final ConnectionContext context, final ActiveMQDestination producerDestination, final ActiveMQTopic topic, final Command command) throws Exception {
        this.fireProducerAdvisory(context, producerDestination, topic, command, null);
    }
    
    protected void fireProducerAdvisory(final ConnectionContext context, final ActiveMQDestination producerDestination, final ActiveMQTopic topic, final Command command, final ConsumerId targetConsumerId) throws Exception {
        final ActiveMQMessage advisoryMessage = new ActiveMQMessage();
        int count = 0;
        if (producerDestination != null) {
            final Set<Destination> set = this.getDestinations(producerDestination);
            if (set != null) {
                for (final Destination dest : set) {
                    count += (int)dest.getDestinationStatistics().getProducers().getCount();
                }
            }
        }
        advisoryMessage.setIntProperty("producerCount", count);
        this.fireAdvisory(context, topic, command, targetConsumerId, advisoryMessage);
    }
    
    protected void fireAdvisory(final ConnectionContext context, final ActiveMQTopic topic, final Command command, final ConsumerId targetConsumerId, final ActiveMQMessage advisoryMessage) throws Exception {
        if (this.getBrokerService().isStarted()) {
            advisoryMessage.setStringProperty("originBrokerName", this.getBrokerName());
            final String id = (this.getBrokerId() != null) ? this.getBrokerId().getValue() : "NOT_SET";
            advisoryMessage.setStringProperty("originBrokerId", id);
            String url = this.getBrokerService().getVmConnectorURI().toString();
            if (this.getBrokerService().getDefaultSocketURIString() != null) {
                url = this.getBrokerService().getDefaultSocketURIString();
            }
            advisoryMessage.setStringProperty("originBrokerURL", url);
            advisoryMessage.setDataStructure(command);
            advisoryMessage.setPersistent(false);
            advisoryMessage.setType("Advisory");
            advisoryMessage.setMessageId(new MessageId(this.advisoryProducerId, this.messageIdGenerator.getNextSequenceId()));
            advisoryMessage.setTargetConsumerId(targetConsumerId);
            advisoryMessage.setDestination(topic);
            advisoryMessage.setResponseRequired(false);
            advisoryMessage.setProducerId(this.advisoryProducerId);
            final boolean originalFlowControl = context.isProducerFlowControl();
            final ProducerBrokerExchange producerExchange = new ProducerBrokerExchange();
            producerExchange.setConnectionContext(context);
            producerExchange.setMutable(true);
            producerExchange.setProducerState(new ProducerState(new ProducerInfo()));
            try {
                context.setProducerFlowControl(false);
                this.next.send(producerExchange, advisoryMessage);
            }
            finally {
                context.setProducerFlowControl(originalFlowControl);
            }
        }
    }
    
    public Map<ConnectionId, ConnectionInfo> getAdvisoryConnections() {
        return this.connections;
    }
    
    public Map<ConsumerId, ConsumerInfo> getAdvisoryConsumers() {
        return this.consumers;
    }
    
    public Map<ProducerId, ProducerInfo> getAdvisoryProducers() {
        return this.producers;
    }
    
    public Map<ActiveMQDestination, DestinationInfo> getAdvisoryDestinations() {
        return this.destinations;
    }
    
    static {
        LOG = LoggerFactory.getLogger(AdvisoryBroker.class);
        ID_GENERATOR = new IdGenerator();
    }
}
