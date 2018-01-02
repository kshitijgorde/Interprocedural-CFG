// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.network.jms;

import org.slf4j.LoggerFactory;
import javax.jms.Destination;
import javax.jms.Topic;
import javax.jms.TopicSession;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import org.slf4j.Logger;

public class JmsTopicConnector extends JmsConnector
{
    private static final Logger LOG;
    private String outboundTopicConnectionFactoryName;
    private String localConnectionFactoryName;
    private TopicConnectionFactory outboundTopicConnectionFactory;
    private TopicConnectionFactory localTopicConnectionFactory;
    private TopicConnection outboundTopicConnection;
    private TopicConnection localTopicConnection;
    private InboundTopicBridge[] inboundTopicBridges;
    private OutboundTopicBridge[] outboundTopicBridges;
    
    @Override
    public boolean init() {
        final boolean result = super.init();
        if (result) {
            try {
                this.initializeForeignTopicConnection();
                this.initializeLocalTopicConnection();
                this.initializeInboundJmsMessageConvertor();
                this.initializeOutboundJmsMessageConvertor();
                this.initializeInboundTopicBridges();
                this.initializeOutboundTopicBridges();
            }
            catch (Exception e) {
                JmsTopicConnector.LOG.error("Failed to initialize the JMSConnector", e);
            }
        }
        return result;
    }
    
    public InboundTopicBridge[] getInboundTopicBridges() {
        return this.inboundTopicBridges;
    }
    
    public void setInboundTopicBridges(final InboundTopicBridge[] inboundTopicBridges) {
        this.inboundTopicBridges = inboundTopicBridges;
    }
    
    public OutboundTopicBridge[] getOutboundTopicBridges() {
        return this.outboundTopicBridges;
    }
    
    public void setOutboundTopicBridges(final OutboundTopicBridge[] outboundTopicBridges) {
        this.outboundTopicBridges = outboundTopicBridges;
    }
    
    public TopicConnectionFactory getLocalTopicConnectionFactory() {
        return this.localTopicConnectionFactory;
    }
    
    public void setLocalTopicConnectionFactory(final TopicConnectionFactory localConnectionFactory) {
        this.localTopicConnectionFactory = localConnectionFactory;
    }
    
    public TopicConnectionFactory getOutboundTopicConnectionFactory() {
        return this.outboundTopicConnectionFactory;
    }
    
    public String getOutboundTopicConnectionFactoryName() {
        return this.outboundTopicConnectionFactoryName;
    }
    
    public void setOutboundTopicConnectionFactoryName(final String foreignTopicConnectionFactoryName) {
        this.outboundTopicConnectionFactoryName = foreignTopicConnectionFactoryName;
    }
    
    public String getLocalConnectionFactoryName() {
        return this.localConnectionFactoryName;
    }
    
    public void setLocalConnectionFactoryName(final String localConnectionFactoryName) {
        this.localConnectionFactoryName = localConnectionFactoryName;
    }
    
    public TopicConnection getLocalTopicConnection() {
        return this.localTopicConnection;
    }
    
    public void setLocalTopicConnection(final TopicConnection localTopicConnection) {
        this.localTopicConnection = localTopicConnection;
    }
    
    public TopicConnection getOutboundTopicConnection() {
        return this.outboundTopicConnection;
    }
    
    public void setOutboundTopicConnection(final TopicConnection foreignTopicConnection) {
        this.outboundTopicConnection = foreignTopicConnection;
    }
    
    public void setOutboundTopicConnectionFactory(final TopicConnectionFactory foreignTopicConnectionFactory) {
        this.outboundTopicConnectionFactory = foreignTopicConnectionFactory;
    }
    
    @Override
    public void restartProducerConnection() throws NamingException, JMSException {
        this.outboundTopicConnection = null;
        this.initializeForeignTopicConnection();
    }
    
    protected void initializeForeignTopicConnection() throws NamingException, JMSException {
        if (this.outboundTopicConnection == null) {
            if (this.outboundTopicConnectionFactory == null) {
                if (this.outboundTopicConnectionFactoryName == null) {
                    throw new JMSException("Cannot create localConnection - no information");
                }
                this.outboundTopicConnectionFactory = (TopicConnectionFactory)this.jndiOutboundTemplate.lookup(this.outboundTopicConnectionFactoryName, (Class)TopicConnectionFactory.class);
                if (this.outboundUsername != null) {
                    this.outboundTopicConnection = this.outboundTopicConnectionFactory.createTopicConnection(this.outboundUsername, this.outboundPassword);
                }
                else {
                    this.outboundTopicConnection = this.outboundTopicConnectionFactory.createTopicConnection();
                }
            }
            else if (this.outboundUsername != null) {
                this.outboundTopicConnection = this.outboundTopicConnectionFactory.createTopicConnection(this.outboundUsername, this.outboundPassword);
            }
            else {
                this.outboundTopicConnection = this.outboundTopicConnectionFactory.createTopicConnection();
            }
        }
        if (this.localClientId != null && this.localClientId.length() > 0) {
            this.outboundTopicConnection.setClientID(this.getOutboundClientId());
        }
        this.outboundTopicConnection.start();
    }
    
    protected void initializeLocalTopicConnection() throws NamingException, JMSException {
        if (this.localTopicConnection == null) {
            if (this.localTopicConnectionFactory == null) {
                if (this.embeddedConnectionFactory == null) {
                    if (this.localConnectionFactoryName == null) {
                        throw new JMSException("Cannot create localConnection - no information");
                    }
                    this.localTopicConnectionFactory = (TopicConnectionFactory)this.jndiLocalTemplate.lookup(this.localConnectionFactoryName, (Class)TopicConnectionFactory.class);
                    if (this.localUsername != null) {
                        this.localTopicConnection = this.localTopicConnectionFactory.createTopicConnection(this.localUsername, this.localPassword);
                    }
                    else {
                        this.localTopicConnection = this.localTopicConnectionFactory.createTopicConnection();
                    }
                }
                else {
                    this.localTopicConnection = this.embeddedConnectionFactory.createTopicConnection();
                }
            }
            else if (this.localUsername != null) {
                this.localTopicConnection = this.localTopicConnectionFactory.createTopicConnection(this.localUsername, this.localPassword);
            }
            else {
                this.localTopicConnection = this.localTopicConnectionFactory.createTopicConnection();
            }
        }
        if (this.localClientId != null && this.localClientId.length() > 0) {
            this.localTopicConnection.setClientID(this.getLocalClientId());
        }
        this.localTopicConnection.start();
    }
    
    protected void initializeInboundJmsMessageConvertor() {
        this.inboundMessageConvertor.setConnection(this.localTopicConnection);
    }
    
    protected void initializeOutboundJmsMessageConvertor() {
        this.outboundMessageConvertor.setConnection(this.outboundTopicConnection);
    }
    
    protected void initializeInboundTopicBridges() throws JMSException {
        if (this.inboundTopicBridges != null) {
            final TopicSession outboundSession = this.outboundTopicConnection.createTopicSession(false, 1);
            final TopicSession localSession = this.localTopicConnection.createTopicSession(false, 1);
            for (int i = 0; i < this.inboundTopicBridges.length; ++i) {
                final InboundTopicBridge bridge = this.inboundTopicBridges[i];
                final String localTopicName = bridge.getLocalTopicName();
                final Topic activemqTopic = this.createActiveMQTopic(localSession, localTopicName);
                final String topicName = bridge.getInboundTopicName();
                final Topic foreignTopic = this.createForeignTopic(outboundSession, topicName);
                bridge.setConsumerTopic(foreignTopic);
                bridge.setProducerTopic(activemqTopic);
                bridge.setProducerConnection(this.localTopicConnection);
                bridge.setConsumerConnection(this.outboundTopicConnection);
                if (bridge.getJmsMessageConvertor() == null) {
                    bridge.setJmsMessageConvertor(this.getInboundMessageConvertor());
                }
                bridge.setJmsConnector(this);
                this.addInboundBridge(bridge);
            }
            outboundSession.close();
            localSession.close();
        }
    }
    
    protected void initializeOutboundTopicBridges() throws JMSException {
        if (this.outboundTopicBridges != null) {
            final TopicSession outboundSession = this.outboundTopicConnection.createTopicSession(false, 1);
            final TopicSession localSession = this.localTopicConnection.createTopicSession(false, 1);
            for (int i = 0; i < this.outboundTopicBridges.length; ++i) {
                final OutboundTopicBridge bridge = this.outboundTopicBridges[i];
                final String localTopicName = bridge.getLocalTopicName();
                final Topic activemqTopic = this.createActiveMQTopic(localSession, localTopicName);
                final String topicName = bridge.getOutboundTopicName();
                final Topic foreignTopic = this.createForeignTopic(outboundSession, topicName);
                bridge.setConsumerTopic(activemqTopic);
                bridge.setProducerTopic(foreignTopic);
                bridge.setProducerConnection(this.outboundTopicConnection);
                bridge.setConsumerConnection(this.localTopicConnection);
                if (bridge.getJmsMessageConvertor() == null) {
                    bridge.setJmsMessageConvertor(this.getOutboundMessageConvertor());
                }
                bridge.setJmsConnector(this);
                this.addOutboundBridge(bridge);
            }
            outboundSession.close();
            localSession.close();
        }
    }
    
    @Override
    protected Destination createReplyToBridge(final Destination destination, final Connection replyToProducerConnection, final Connection replyToConsumerConnection) {
        final Topic replyToProducerTopic = (Topic)destination;
        final boolean isInbound = replyToProducerConnection.equals(this.localTopicConnection);
        if (isInbound) {
            InboundTopicBridge bridge = (InboundTopicBridge)this.replyToBridges.get(replyToProducerTopic);
            if (bridge == null) {
                bridge = new InboundTopicBridge() {
                    @Override
                    protected Destination processReplyToDestination(final Destination destination) {
                        return null;
                    }
                };
                try {
                    final TopicSession replyToConsumerSession = ((TopicConnection)replyToConsumerConnection).createTopicSession(false, 1);
                    final Topic replyToConsumerTopic = replyToConsumerSession.createTemporaryTopic();
                    replyToConsumerSession.close();
                    bridge.setConsumerTopic(replyToConsumerTopic);
                    bridge.setProducerTopic(replyToProducerTopic);
                    bridge.setProducerConnection((TopicConnection)replyToProducerConnection);
                    bridge.setConsumerConnection((TopicConnection)replyToConsumerConnection);
                    bridge.setDoHandleReplyTo(false);
                    if (bridge.getJmsMessageConvertor() == null) {
                        bridge.setJmsMessageConvertor(this.getInboundMessageConvertor());
                    }
                    bridge.setJmsConnector(this);
                    bridge.start();
                    JmsTopicConnector.LOG.info("Created replyTo bridge for " + replyToProducerTopic);
                }
                catch (Exception e) {
                    JmsTopicConnector.LOG.error("Failed to create replyTo bridge for topic: " + replyToProducerTopic, e);
                    return null;
                }
                this.replyToBridges.put(replyToProducerTopic, bridge);
            }
            return bridge.getConsumerTopic();
        }
        OutboundTopicBridge bridge2 = (OutboundTopicBridge)this.replyToBridges.get(replyToProducerTopic);
        if (bridge2 == null) {
            bridge2 = new OutboundTopicBridge() {
                @Override
                protected Destination processReplyToDestination(final Destination destination) {
                    return null;
                }
            };
            try {
                final TopicSession replyToConsumerSession = ((TopicConnection)replyToConsumerConnection).createTopicSession(false, 1);
                final Topic replyToConsumerTopic = replyToConsumerSession.createTemporaryTopic();
                replyToConsumerSession.close();
                bridge2.setConsumerTopic(replyToConsumerTopic);
                bridge2.setProducerTopic(replyToProducerTopic);
                bridge2.setProducerConnection((TopicConnection)replyToProducerConnection);
                bridge2.setConsumerConnection((TopicConnection)replyToConsumerConnection);
                bridge2.setDoHandleReplyTo(false);
                if (bridge2.getJmsMessageConvertor() == null) {
                    bridge2.setJmsMessageConvertor(this.getOutboundMessageConvertor());
                }
                bridge2.setJmsConnector(this);
                bridge2.start();
                JmsTopicConnector.LOG.info("Created replyTo bridge for " + replyToProducerTopic);
            }
            catch (Exception e) {
                JmsTopicConnector.LOG.error("Failed to create replyTo bridge for topic: " + replyToProducerTopic, e);
                return null;
            }
            this.replyToBridges.put(replyToProducerTopic, bridge2);
        }
        return bridge2.getConsumerTopic();
    }
    
    protected Topic createActiveMQTopic(final TopicSession session, final String topicName) throws JMSException {
        return session.createTopic(topicName);
    }
    
    protected Topic createForeignTopic(final TopicSession session, final String topicName) throws JMSException {
        Topic result = null;
        try {
            result = session.createTopic(topicName);
        }
        catch (JMSException e) {
            try {
                result = (Topic)this.jndiOutboundTemplate.lookup(topicName, (Class)Topic.class);
            }
            catch (NamingException e2) {
                final String errStr = "Failed to look-up Topic for name: " + topicName;
                JmsTopicConnector.LOG.error(errStr, e);
                final JMSException jmsEx = new JMSException(errStr);
                jmsEx.setLinkedException(e2);
                throw jmsEx;
            }
        }
        return result;
    }
    
    static {
        LOG = LoggerFactory.getLogger(JmsTopicConnector.class);
    }
}
