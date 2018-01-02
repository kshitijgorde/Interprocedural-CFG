// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.camel.component;

import org.slf4j.LoggerFactory;
import org.apache.camel.component.jms.JmsEndpoint;
import org.apache.camel.Endpoint;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.jms.JmsQueueEndpoint;
import javax.annotation.PreDestroy;
import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.Set;
import org.apache.activemq.advisory.DestinationSource;
import javax.jms.Connection;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.advisory.DestinationEvent;
import org.apache.activemq.advisory.DestinationListener;
import org.apache.camel.util.ObjectHelper;
import javax.jms.ConnectionFactory;
import org.apache.activemq.EnhancedConnection;
import org.apache.camel.CamelContext;
import org.slf4j.Logger;
import org.apache.camel.CamelContextAware;

public class CamelEndpointLoader implements CamelContextAware
{
    private static final transient Logger LOG;
    private CamelContext camelContext;
    private EnhancedConnection connection;
    private ConnectionFactory connectionFactory;
    private ActiveMQComponent component;
    
    public CamelEndpointLoader() {
    }
    
    public CamelEndpointLoader(final CamelContext camelContext) {
        this.camelContext = camelContext;
    }
    
    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        ObjectHelper.notNull((Object)this.camelContext, "camelContext");
        if (this.connection == null) {
            final Connection value = this.getConnectionFactory().createConnection();
            if (!(value instanceof EnhancedConnection)) {
                throw new IllegalArgumentException("Created JMS Connection is not an EnhancedConnection: " + value);
            }
            this.connection = (EnhancedConnection)value;
        }
        this.connection.start();
        final DestinationSource source = this.connection.getDestinationSource();
        source.setDestinationListener(new DestinationListener() {
            @Override
            public void onDestinationEvent(final DestinationEvent event) {
                try {
                    final ActiveMQDestination destination = event.getDestination();
                    if (destination instanceof ActiveMQQueue) {
                        final ActiveMQQueue queue = (ActiveMQQueue)destination;
                        if (event.isAddOperation()) {
                            CamelEndpointLoader.this.addQueue(queue);
                        }
                        else {
                            CamelEndpointLoader.this.removeQueue(queue);
                        }
                    }
                    else if (destination instanceof ActiveMQTopic) {
                        final ActiveMQTopic topic = (ActiveMQTopic)destination;
                        if (event.isAddOperation()) {
                            CamelEndpointLoader.this.addTopic(topic);
                        }
                        else {
                            CamelEndpointLoader.this.removeTopic(topic);
                        }
                    }
                }
                catch (Exception e) {
                    CamelEndpointLoader.LOG.warn("Caught: " + e, e);
                }
            }
        });
        final Set<ActiveMQQueue> queues = source.getQueues();
        for (final ActiveMQQueue queue : queues) {
            this.addQueue(queue);
        }
        final Set<ActiveMQTopic> topics = source.getTopics();
        for (final ActiveMQTopic topic : topics) {
            this.addTopic(topic);
        }
    }
    
    @PreDestroy
    public void destroy() throws Exception {
        if (this.connection != null) {
            this.connection.close();
            this.connection = null;
        }
    }
    
    public CamelContext getCamelContext() {
        return this.camelContext;
    }
    
    public void setCamelContext(final CamelContext camelContext) {
        this.camelContext = camelContext;
    }
    
    public EnhancedConnection getConnection() {
        return this.connection;
    }
    
    public ConnectionFactory getConnectionFactory() {
        if (this.connectionFactory == null && this.getComponent().getConfiguration() instanceof ActiveMQConfiguration) {
            this.connectionFactory = ((ActiveMQConfiguration)this.getComponent().getConfiguration()).createConnectionFactory();
        }
        return this.connectionFactory;
    }
    
    public void setConnectionFactory(final ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
    
    public ActiveMQComponent getComponent() {
        if (this.component == null) {
            this.component = (ActiveMQComponent)this.camelContext.getComponent("activemq", (Class)ActiveMQComponent.class);
        }
        return this.component;
    }
    
    public void setComponent(final ActiveMQComponent component) {
        this.component = component;
    }
    
    protected void addQueue(final ActiveMQQueue queue) throws Exception {
        final String queueUri = this.getQueueUri(queue);
        final ActiveMQComponent jmsComponent = this.getComponent();
        final Endpoint endpoint = (Endpoint)new JmsQueueEndpoint(queueUri, (JmsComponent)jmsComponent, queue.getPhysicalName(), jmsComponent.getConfiguration());
        this.camelContext.addEndpoint(queueUri, endpoint);
    }
    
    protected String getQueueUri(final ActiveMQQueue queue) {
        return "activemq:" + queue.getPhysicalName();
    }
    
    protected void removeQueue(final ActiveMQQueue queue) throws Exception {
        final String queueUri = this.getQueueUri(queue);
        this.camelContext.removeEndpoints(queueUri);
    }
    
    protected void addTopic(final ActiveMQTopic topic) throws Exception {
        final String topicUri = this.getTopicUri(topic);
        final ActiveMQComponent jmsComponent = this.getComponent();
        final Endpoint endpoint = (Endpoint)new JmsEndpoint(topicUri, (JmsComponent)jmsComponent, topic.getPhysicalName(), true, jmsComponent.getConfiguration());
        this.camelContext.addEndpoint(topicUri, endpoint);
    }
    
    protected String getTopicUri(final ActiveMQTopic topic) {
        return "activemq:topic:" + topic.getPhysicalName();
    }
    
    protected void removeTopic(final ActiveMQTopic topic) throws Exception {
        final String topicUri = this.getTopicUri(topic);
        this.camelContext.removeEndpoints(topicUri);
    }
    
    static {
        LOG = LoggerFactory.getLogger(CamelEndpointLoader.class);
    }
}
