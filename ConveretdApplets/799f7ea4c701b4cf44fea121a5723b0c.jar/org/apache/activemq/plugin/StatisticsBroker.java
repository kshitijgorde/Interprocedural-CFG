// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.plugin;

import org.slf4j.LoggerFactory;
import org.apache.activemq.state.ProducerState;
import org.apache.activemq.command.ProducerInfo;
import org.apache.activemq.command.MessageId;
import org.apache.activemq.broker.ConnectionContext;
import java.io.File;
import java.net.URI;
import org.apache.activemq.usage.SystemUsage;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.region.DestinationStatistics;
import java.util.Iterator;
import java.util.Set;
import org.apache.activemq.broker.region.RegionBroker;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.broker.region.Destination;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.Message;
import org.apache.activemq.broker.ProducerBrokerExchange;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.command.ProducerId;
import org.apache.activemq.util.LongSequenceGenerator;
import org.apache.activemq.util.IdGenerator;
import org.slf4j.Logger;
import org.apache.activemq.broker.BrokerFilter;

public class StatisticsBroker extends BrokerFilter
{
    private static Logger LOG;
    static final String STATS_DESTINATION_PREFIX = "ActiveMQ.Statistics.Destination";
    static final String STATS_BROKER_PREFIX = "ActiveMQ.Statistics.Broker";
    private static final IdGenerator ID_GENERATOR;
    private final LongSequenceGenerator messageIdGenerator;
    protected final ProducerId advisoryProducerId;
    
    public StatisticsBroker(final Broker next) {
        super(next);
        this.messageIdGenerator = new LongSequenceGenerator();
        (this.advisoryProducerId = new ProducerId()).setConnectionId(StatisticsBroker.ID_GENERATOR.generateId());
    }
    
    @Override
    public void send(final ProducerBrokerExchange producerExchange, final Message messageSend) throws Exception {
        final ActiveMQDestination msgDest = messageSend.getDestination();
        final ActiveMQDestination replyTo = messageSend.getReplyTo();
        if (replyTo != null) {
            final String physicalName = msgDest.getPhysicalName();
            final boolean destStats = physicalName.regionMatches(true, 0, "ActiveMQ.Statistics.Destination", 0, "ActiveMQ.Statistics.Destination".length());
            final boolean brokerStats = physicalName.regionMatches(true, 0, "ActiveMQ.Statistics.Broker", 0, "ActiveMQ.Statistics.Broker".length());
            if (destStats) {
                final String queueryName = physicalName.substring("ActiveMQ.Statistics.Destination".length(), physicalName.length());
                final ActiveMQDestination queryDest = ActiveMQDestination.createDestination(queueryName, msgDest.getDestinationType());
                final Set<Destination> set = this.getDestinations(queryDest);
                for (final Destination dest : set) {
                    final DestinationStatistics stats = dest.getDestinationStatistics();
                    if (stats != null) {
                        final ActiveMQMapMessage statsMessage = new ActiveMQMapMessage();
                        statsMessage.setString("destinationName", dest.getActiveMQDestination().toString());
                        statsMessage.setLong("size", stats.getMessages().getCount());
                        statsMessage.setLong("enqueueCount", stats.getEnqueues().getCount());
                        statsMessage.setLong("dequeueCount", stats.getDequeues().getCount());
                        statsMessage.setLong("dispatchCount", stats.getDispatched().getCount());
                        statsMessage.setLong("expiredCount", stats.getExpired().getCount());
                        statsMessage.setLong("inflightCount", stats.getInflight().getCount());
                        statsMessage.setLong("messagesCached", stats.getMessagesCached().getCount());
                        statsMessage.setInt("memoryPercentUsage", dest.getMemoryUsage().getPercentUsage());
                        statsMessage.setLong("memoryUsage", dest.getMemoryUsage().getUsage());
                        statsMessage.setLong("memoryLimit", dest.getMemoryUsage().getLimit());
                        statsMessage.setDouble("averageEnqueueTime", stats.getProcessTime().getAverageTime());
                        statsMessage.setDouble("maxEnqueueTime", stats.getProcessTime().getMaxTime());
                        statsMessage.setDouble("minEnqueueTime", stats.getProcessTime().getMinTime());
                        statsMessage.setLong("consumerCount", stats.getConsumers().getCount());
                        statsMessage.setLong("producerCount", stats.getProducers().getCount());
                        statsMessage.setJMSCorrelationID(messageSend.getCorrelationId());
                        this.sendStats(producerExchange.getConnectionContext(), statsMessage, replyTo);
                    }
                }
            }
            else if (brokerStats) {
                final ActiveMQMapMessage statsMessage2 = new ActiveMQMapMessage();
                final BrokerService brokerService = this.getBrokerService();
                final RegionBroker regionBroker = (RegionBroker)brokerService.getRegionBroker();
                final SystemUsage systemUsage = brokerService.getSystemUsage();
                final DestinationStatistics stats2 = regionBroker.getDestinationStatistics();
                statsMessage2.setString("brokerName", regionBroker.getBrokerName());
                statsMessage2.setString("brokerId", regionBroker.getBrokerId().toString());
                statsMessage2.setLong("size", stats2.getMessages().getCount());
                statsMessage2.setLong("enqueueCount", stats2.getEnqueues().getCount());
                statsMessage2.setLong("dequeueCount", stats2.getDequeues().getCount());
                statsMessage2.setLong("dispatchCount", stats2.getDispatched().getCount());
                statsMessage2.setLong("expiredCount", stats2.getExpired().getCount());
                statsMessage2.setLong("inflightCount", stats2.getInflight().getCount());
                statsMessage2.setLong("messagesCached", stats2.getMessagesCached().getCount());
                statsMessage2.setInt("memoryPercentUsage", systemUsage.getMemoryUsage().getPercentUsage());
                statsMessage2.setLong("memoryUsage", systemUsage.getMemoryUsage().getUsage());
                statsMessage2.setLong("memoryLimit", systemUsage.getMemoryUsage().getLimit());
                statsMessage2.setInt("storePercentUsage", systemUsage.getStoreUsage().getPercentUsage());
                statsMessage2.setLong("storeUsage", systemUsage.getStoreUsage().getUsage());
                statsMessage2.setLong("storeLimit", systemUsage.getStoreUsage().getLimit());
                statsMessage2.setInt("tempPercentUsage", systemUsage.getTempUsage().getPercentUsage());
                statsMessage2.setLong("tempUsage", systemUsage.getTempUsage().getUsage());
                statsMessage2.setLong("tempLimit", systemUsage.getTempUsage().getLimit());
                statsMessage2.setDouble("averageEnqueueTime", stats2.getProcessTime().getAverageTime());
                statsMessage2.setDouble("maxEnqueueTime", stats2.getProcessTime().getMaxTime());
                statsMessage2.setDouble("minEnqueueTime", stats2.getProcessTime().getMinTime());
                statsMessage2.setLong("consumerCount", stats2.getConsumers().getCount());
                statsMessage2.setLong("producerCount", stats2.getProducers().getCount());
                String answer = brokerService.getTransportConnectorURIsAsMap().get("tcp");
                answer = ((answer != null) ? answer : "");
                statsMessage2.setString("openwire", answer);
                answer = brokerService.getTransportConnectorURIsAsMap().get("stomp");
                answer = ((answer != null) ? answer : "");
                statsMessage2.setString("stomp", answer);
                answer = brokerService.getTransportConnectorURIsAsMap().get("ssl");
                answer = ((answer != null) ? answer : "");
                statsMessage2.setString("ssl", answer);
                answer = brokerService.getTransportConnectorURIsAsMap().get("stomp+ssl");
                answer = ((answer != null) ? answer : "");
                statsMessage2.setString("stomp+ssl", answer);
                final URI uri = brokerService.getVmConnectorURI();
                answer = ((uri != null) ? uri.toString() : "");
                statsMessage2.setString("vm", answer);
                final File file = brokerService.getDataDirectoryFile();
                answer = ((file != null) ? file.getCanonicalPath() : "");
                statsMessage2.setString("dataDirectory", answer);
                statsMessage2.setJMSCorrelationID(messageSend.getCorrelationId());
                this.sendStats(producerExchange.getConnectionContext(), statsMessage2, replyTo);
            }
            else {
                super.send(producerExchange, messageSend);
            }
        }
        else {
            super.send(producerExchange, messageSend);
        }
    }
    
    @Override
    public void start() throws Exception {
        super.start();
        StatisticsBroker.LOG.info("Starting StatisticsBroker");
    }
    
    @Override
    public void stop() throws Exception {
        super.stop();
    }
    
    protected void sendStats(final ConnectionContext context, final ActiveMQMapMessage msg, final ActiveMQDestination replyTo) throws Exception {
        msg.setPersistent(false);
        msg.setType("Advisory");
        msg.setMessageId(new MessageId(this.advisoryProducerId, this.messageIdGenerator.getNextSequenceId()));
        msg.setDestination(replyTo);
        msg.setResponseRequired(false);
        msg.setProducerId(this.advisoryProducerId);
        final boolean originalFlowControl = context.isProducerFlowControl();
        final ProducerBrokerExchange producerExchange = new ProducerBrokerExchange();
        producerExchange.setConnectionContext(context);
        producerExchange.setMutable(true);
        producerExchange.setProducerState(new ProducerState(new ProducerInfo()));
        try {
            context.setProducerFlowControl(false);
            this.next.send(producerExchange, msg);
        }
        finally {
            context.setProducerFlowControl(originalFlowControl);
        }
    }
    
    static {
        StatisticsBroker.LOG = LoggerFactory.getLogger(StatisticsBroker.class);
        ID_GENERATOR = new IdGenerator();
    }
}
