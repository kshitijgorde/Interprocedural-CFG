// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.jmx;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import org.apache.activemq.command.RemoveSubscriptionInfo;
import org.apache.activemq.broker.region.Subscription;
import org.apache.activemq.command.ConsumerId;
import org.apache.activemq.command.ConsumerInfo;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.util.BrokerSupport;
import org.apache.activemq.network.NetworkConnector;
import org.apache.activemq.broker.TransportConnector;
import org.apache.activemq.ActiveMQConnectionMetaData;
import javax.management.ObjectName;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.activemq.broker.BrokerService;

public class BrokerView implements BrokerViewMBean
{
    ManagedRegionBroker broker;
    private final BrokerService brokerService;
    private final AtomicInteger sessionIdCounter;
    private ObjectName jmsJobScheduler;
    
    public BrokerView(final BrokerService brokerService, final ManagedRegionBroker managedBroker) throws Exception {
        this.sessionIdCounter = new AtomicInteger(0);
        this.brokerService = brokerService;
        this.broker = managedBroker;
    }
    
    public ManagedRegionBroker getBroker() {
        return this.broker;
    }
    
    public void setBroker(final ManagedRegionBroker broker) {
        this.broker = broker;
    }
    
    @Override
    public String getBrokerId() {
        return this.broker.getBrokerId().toString();
    }
    
    @Override
    public String getBrokerName() {
        return this.broker.getBrokerName();
    }
    
    @Override
    public String getBrokerVersion() {
        return ActiveMQConnectionMetaData.PROVIDER_VERSION;
    }
    
    @Override
    public void gc() throws Exception {
        this.brokerService.getBroker().gc();
    }
    
    @Override
    public void start() throws Exception {
        this.brokerService.start();
    }
    
    @Override
    public void stop() throws Exception {
        this.brokerService.stop();
    }
    
    @Override
    public void stopGracefully(final String connectorName, final String queueName, final long timeout, final long pollInterval) throws Exception {
        this.brokerService.stopGracefully(connectorName, queueName, timeout, pollInterval);
    }
    
    @Override
    public long getTotalEnqueueCount() {
        return this.broker.getDestinationStatistics().getEnqueues().getCount();
    }
    
    @Override
    public long getTotalDequeueCount() {
        return this.broker.getDestinationStatistics().getDequeues().getCount();
    }
    
    @Override
    public long getTotalConsumerCount() {
        return this.broker.getDestinationStatistics().getConsumers().getCount();
    }
    
    @Override
    public long getTotalMessageCount() {
        return this.broker.getDestinationStatistics().getMessages().getCount();
    }
    
    public long getTotalMessagesCached() {
        return this.broker.getDestinationStatistics().getMessagesCached().getCount();
    }
    
    @Override
    public int getMemoryPercentUsage() {
        return this.brokerService.getSystemUsage().getMemoryUsage().getPercentUsage();
    }
    
    @Override
    public long getMemoryLimit() {
        return this.brokerService.getSystemUsage().getMemoryUsage().getLimit();
    }
    
    @Override
    public void setMemoryLimit(final long limit) {
        this.brokerService.getSystemUsage().getMemoryUsage().setLimit(limit);
    }
    
    @Override
    public long getStoreLimit() {
        return this.brokerService.getSystemUsage().getStoreUsage().getLimit();
    }
    
    @Override
    public int getStorePercentUsage() {
        return this.brokerService.getSystemUsage().getStoreUsage().getPercentUsage();
    }
    
    @Override
    public long getTempLimit() {
        return this.brokerService.getSystemUsage().getTempUsage().getLimit();
    }
    
    @Override
    public int getTempPercentUsage() {
        return this.brokerService.getSystemUsage().getTempUsage().getPercentUsage();
    }
    
    @Override
    public void setStoreLimit(final long limit) {
        this.brokerService.getSystemUsage().getStoreUsage().setLimit(limit);
    }
    
    @Override
    public void setTempLimit(final long limit) {
        this.brokerService.getSystemUsage().getTempUsage().setLimit(limit);
    }
    
    @Override
    public void resetStatistics() {
        this.broker.getDestinationStatistics().reset();
    }
    
    @Override
    public void enableStatistics() {
        this.broker.getDestinationStatistics().setEnabled(true);
    }
    
    @Override
    public void disableStatistics() {
        this.broker.getDestinationStatistics().setEnabled(false);
    }
    
    @Override
    public boolean isStatisticsEnabled() {
        return this.broker.getDestinationStatistics().isEnabled();
    }
    
    @Override
    public boolean isPersistent() {
        return this.brokerService.isPersistent();
    }
    
    @Override
    public boolean isSlave() {
        return this.brokerService.isSlave();
    }
    
    @Override
    public void terminateJVM(final int exitCode) {
        System.exit(exitCode);
    }
    
    @Override
    public ObjectName[] getTopics() {
        return this.broker.getTopics();
    }
    
    @Override
    public ObjectName[] getQueues() {
        return this.broker.getQueues();
    }
    
    @Override
    public ObjectName[] getTemporaryTopics() {
        return this.broker.getTemporaryTopics();
    }
    
    @Override
    public ObjectName[] getTemporaryQueues() {
        return this.broker.getTemporaryQueues();
    }
    
    @Override
    public ObjectName[] getTopicSubscribers() {
        return this.broker.getTopicSubscribers();
    }
    
    @Override
    public ObjectName[] getDurableTopicSubscribers() {
        return this.broker.getDurableTopicSubscribers();
    }
    
    @Override
    public ObjectName[] getQueueSubscribers() {
        return this.broker.getQueueSubscribers();
    }
    
    @Override
    public ObjectName[] getTemporaryTopicSubscribers() {
        return this.broker.getTemporaryTopicSubscribers();
    }
    
    @Override
    public ObjectName[] getTemporaryQueueSubscribers() {
        return this.broker.getTemporaryQueueSubscribers();
    }
    
    @Override
    public ObjectName[] getInactiveDurableTopicSubscribers() {
        return this.broker.getInactiveDurableTopicSubscribers();
    }
    
    @Override
    public String addConnector(final String discoveryAddress) throws Exception {
        final TransportConnector connector = this.brokerService.addConnector(discoveryAddress);
        connector.start();
        return connector.getName();
    }
    
    @Override
    public String addNetworkConnector(final String discoveryAddress) throws Exception {
        final NetworkConnector connector = this.brokerService.addNetworkConnector(discoveryAddress);
        connector.start();
        return connector.getName();
    }
    
    @Override
    public boolean removeConnector(final String connectorName) throws Exception {
        final TransportConnector connector = this.brokerService.getConnectorByName(connectorName);
        connector.stop();
        return this.brokerService.removeConnector(connector);
    }
    
    @Override
    public boolean removeNetworkConnector(final String connectorName) throws Exception {
        final NetworkConnector connector = this.brokerService.getNetworkConnectorByName(connectorName);
        connector.stop();
        return this.brokerService.removeNetworkConnector(connector);
    }
    
    @Override
    public void addTopic(final String name) throws Exception {
        this.broker.getContextBroker().addDestination(BrokerSupport.getConnectionContext(this.broker.getContextBroker()), new ActiveMQTopic(name), true);
    }
    
    @Override
    public void addQueue(final String name) throws Exception {
        this.broker.getContextBroker().addDestination(BrokerSupport.getConnectionContext(this.broker.getContextBroker()), new ActiveMQQueue(name), true);
    }
    
    @Override
    public void removeTopic(final String name) throws Exception {
        this.broker.getContextBroker().removeDestination(BrokerSupport.getConnectionContext(this.broker.getContextBroker()), new ActiveMQTopic(name), 1000L);
    }
    
    @Override
    public void removeQueue(final String name) throws Exception {
        this.broker.getContextBroker().removeDestination(BrokerSupport.getConnectionContext(this.broker.getContextBroker()), new ActiveMQQueue(name), 1000L);
    }
    
    @Override
    public ObjectName createDurableSubscriber(final String clientId, final String subscriberName, final String topicName, final String selector) throws Exception {
        final ConnectionContext context = new ConnectionContext();
        context.setBroker(this.broker);
        context.setClientId(clientId);
        final ConsumerInfo info = new ConsumerInfo();
        final ConsumerId consumerId = new ConsumerId();
        consumerId.setConnectionId(clientId);
        consumerId.setSessionId(this.sessionIdCounter.incrementAndGet());
        consumerId.setValue(0L);
        info.setConsumerId(consumerId);
        info.setDestination(new ActiveMQTopic(topicName));
        info.setSubscriptionName(subscriberName);
        info.setSelector(selector);
        final Subscription subscription = this.broker.addConsumer(context, info);
        this.broker.removeConsumer(context, info);
        if (subscription != null) {
            return subscription.getObjectName();
        }
        return null;
    }
    
    @Override
    public void destroyDurableSubscriber(final String clientId, final String subscriberName) throws Exception {
        final RemoveSubscriptionInfo info = new RemoveSubscriptionInfo();
        info.setClientId(clientId);
        info.setSubscriptionName(subscriberName);
        final ConnectionContext context = new ConnectionContext();
        context.setBroker(this.broker);
        context.setClientId(clientId);
        this.broker.removeSubscription(context, info);
    }
    
    @Override
    public void reloadLog4jProperties() throws Throwable {
        try {
            final ClassLoader cl = this.getClass().getClassLoader();
            final Class logManagerClass = cl.loadClass("org.apache.log4j.LogManager");
            final Method resetConfiguration = logManagerClass.getMethod("resetConfiguration", (Class[])new Class[0]);
            resetConfiguration.invoke(null, new Object[0]);
            final URL log4jprops = cl.getResource("log4j.properties");
            if (log4jprops != null) {
                final Class propertyConfiguratorClass = cl.loadClass("org.apache.log4j.PropertyConfigurator");
                final Method configure = propertyConfiguratorClass.getMethod("configure", URL.class);
                configure.invoke(null, log4jprops);
            }
        }
        catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }
    
    @Override
    public String getOpenWireURL() {
        final String answer = this.brokerService.getTransportConnectorURIsAsMap().get("tcp");
        return (answer != null) ? answer : "";
    }
    
    @Override
    public String getStompURL() {
        final String answer = this.brokerService.getTransportConnectorURIsAsMap().get("stomp");
        return (answer != null) ? answer : "";
    }
    
    @Override
    public String getSslURL() {
        final String answer = this.brokerService.getTransportConnectorURIsAsMap().get("ssl");
        return (answer != null) ? answer : "";
    }
    
    @Override
    public String getStompSslURL() {
        final String answer = this.brokerService.getTransportConnectorURIsAsMap().get("stomp+ssl");
        return (answer != null) ? answer : "";
    }
    
    @Override
    public String getVMURL() {
        final URI answer = this.brokerService.getVmConnectorURI();
        return (answer != null) ? answer.toString() : "";
    }
    
    @Override
    public String getDataDirectory() {
        final File file = this.brokerService.getDataDirectoryFile();
        try {
            return (file != null) ? file.getCanonicalPath() : "";
        }
        catch (IOException e) {
            return "";
        }
    }
    
    @Override
    public ObjectName getJMSJobScheduler() {
        return this.jmsJobScheduler;
    }
    
    public void setJMSJobScheduler(final ObjectName name) {
        this.jmsJobScheduler = name;
    }
}
