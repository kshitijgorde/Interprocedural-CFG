// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.network.jms;

import org.slf4j.LoggerFactory;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.QueueSession;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import org.slf4j.Logger;

public class JmsQueueConnector extends JmsConnector
{
    private static final Logger LOG;
    private String outboundQueueConnectionFactoryName;
    private String localConnectionFactoryName;
    private QueueConnectionFactory outboundQueueConnectionFactory;
    private QueueConnectionFactory localQueueConnectionFactory;
    private QueueConnection outboundQueueConnection;
    private QueueConnection localQueueConnection;
    private InboundQueueBridge[] inboundQueueBridges;
    private OutboundQueueBridge[] outboundQueueBridges;
    
    @Override
    public boolean init() {
        final boolean result = super.init();
        if (result) {
            try {
                this.initializeForeignQueueConnection();
                this.initializeLocalQueueConnection();
                this.initializeInboundJmsMessageConvertor();
                this.initializeOutboundJmsMessageConvertor();
                this.initializeInboundQueueBridges();
                this.initializeOutboundQueueBridges();
            }
            catch (Exception e) {
                JmsQueueConnector.LOG.error("Failed to initialize the JMSConnector", e);
            }
        }
        return result;
    }
    
    public InboundQueueBridge[] getInboundQueueBridges() {
        return this.inboundQueueBridges;
    }
    
    public void setInboundQueueBridges(final InboundQueueBridge[] inboundQueueBridges) {
        this.inboundQueueBridges = inboundQueueBridges;
    }
    
    public OutboundQueueBridge[] getOutboundQueueBridges() {
        return this.outboundQueueBridges;
    }
    
    public void setOutboundQueueBridges(final OutboundQueueBridge[] outboundQueueBridges) {
        this.outboundQueueBridges = outboundQueueBridges;
    }
    
    public QueueConnectionFactory getLocalQueueConnectionFactory() {
        return this.localQueueConnectionFactory;
    }
    
    public void setLocalQueueConnectionFactory(final QueueConnectionFactory localConnectionFactory) {
        this.localQueueConnectionFactory = localConnectionFactory;
    }
    
    public QueueConnectionFactory getOutboundQueueConnectionFactory() {
        return this.outboundQueueConnectionFactory;
    }
    
    public String getOutboundQueueConnectionFactoryName() {
        return this.outboundQueueConnectionFactoryName;
    }
    
    public void setOutboundQueueConnectionFactoryName(final String foreignQueueConnectionFactoryName) {
        this.outboundQueueConnectionFactoryName = foreignQueueConnectionFactoryName;
    }
    
    public String getLocalConnectionFactoryName() {
        return this.localConnectionFactoryName;
    }
    
    public void setLocalConnectionFactoryName(final String localConnectionFactoryName) {
        this.localConnectionFactoryName = localConnectionFactoryName;
    }
    
    public QueueConnection getLocalQueueConnection() {
        return this.localQueueConnection;
    }
    
    public void setLocalQueueConnection(final QueueConnection localQueueConnection) {
        this.localQueueConnection = localQueueConnection;
    }
    
    public QueueConnection getOutboundQueueConnection() {
        return this.outboundQueueConnection;
    }
    
    public void setOutboundQueueConnection(final QueueConnection foreignQueueConnection) {
        this.outboundQueueConnection = foreignQueueConnection;
    }
    
    public void setOutboundQueueConnectionFactory(final QueueConnectionFactory foreignQueueConnectionFactory) {
        this.outboundQueueConnectionFactory = foreignQueueConnectionFactory;
    }
    
    @Override
    public void restartProducerConnection() throws NamingException, JMSException {
        this.outboundQueueConnection = null;
        this.initializeForeignQueueConnection();
        if (this.inboundQueueBridges != null) {
            for (int i = 0; i < this.inboundQueueBridges.length; ++i) {
                final InboundQueueBridge bridge = this.inboundQueueBridges[i];
                bridge.setConsumerConnection(this.outboundQueueConnection);
            }
        }
        if (this.outboundQueueBridges != null) {
            for (int i = 0; i < this.outboundQueueBridges.length; ++i) {
                final OutboundQueueBridge bridge2 = this.outboundQueueBridges[i];
                bridge2.setProducerConnection(this.outboundQueueConnection);
            }
        }
    }
    
    protected void initializeForeignQueueConnection() throws NamingException, JMSException {
        if (this.outboundQueueConnection == null) {
            if (this.outboundQueueConnectionFactory == null) {
                if (this.outboundQueueConnectionFactoryName == null) {
                    throw new JMSException("Cannot create foreignConnection - no information");
                }
                this.outboundQueueConnectionFactory = (QueueConnectionFactory)this.jndiOutboundTemplate.lookup(this.outboundQueueConnectionFactoryName, (Class)QueueConnectionFactory.class);
                if (this.outboundUsername != null) {
                    this.outboundQueueConnection = this.outboundQueueConnectionFactory.createQueueConnection(this.outboundUsername, this.outboundPassword);
                }
                else {
                    this.outboundQueueConnection = this.outboundQueueConnectionFactory.createQueueConnection();
                }
            }
            else if (this.outboundUsername != null) {
                this.outboundQueueConnection = this.outboundQueueConnectionFactory.createQueueConnection(this.outboundUsername, this.outboundPassword);
            }
            else {
                this.outboundQueueConnection = this.outboundQueueConnectionFactory.createQueueConnection();
            }
        }
        if (this.localClientId != null && this.localClientId.length() > 0) {
            this.outboundQueueConnection.setClientID(this.getOutboundClientId());
        }
        this.outboundQueueConnection.start();
    }
    
    protected void initializeLocalQueueConnection() throws NamingException, JMSException {
        if (this.localQueueConnection == null) {
            if (this.localQueueConnectionFactory == null) {
                if (this.embeddedConnectionFactory == null) {
                    if (this.localConnectionFactoryName == null) {
                        throw new JMSException("Cannot create localConnection - no information");
                    }
                    this.localQueueConnectionFactory = (QueueConnectionFactory)this.jndiLocalTemplate.lookup(this.localConnectionFactoryName, (Class)QueueConnectionFactory.class);
                    if (this.localUsername != null) {
                        this.localQueueConnection = this.localQueueConnectionFactory.createQueueConnection(this.localUsername, this.localPassword);
                    }
                    else {
                        this.localQueueConnection = this.localQueueConnectionFactory.createQueueConnection();
                    }
                }
                else {
                    this.localQueueConnection = this.embeddedConnectionFactory.createQueueConnection();
                }
            }
            else if (this.localUsername != null) {
                this.localQueueConnection = this.localQueueConnectionFactory.createQueueConnection(this.localUsername, this.localPassword);
            }
            else {
                this.localQueueConnection = this.localQueueConnectionFactory.createQueueConnection();
            }
        }
        if (this.localClientId != null && this.localClientId.length() > 0) {
            this.localQueueConnection.setClientID(this.getLocalClientId());
        }
        this.localQueueConnection.start();
    }
    
    protected void initializeInboundJmsMessageConvertor() {
        this.inboundMessageConvertor.setConnection(this.localQueueConnection);
    }
    
    protected void initializeOutboundJmsMessageConvertor() {
        this.outboundMessageConvertor.setConnection(this.outboundQueueConnection);
    }
    
    protected void initializeInboundQueueBridges() throws JMSException {
        if (this.inboundQueueBridges != null) {
            final QueueSession outboundSession = this.outboundQueueConnection.createQueueSession(false, 1);
            final QueueSession localSession = this.localQueueConnection.createQueueSession(false, 1);
            for (int i = 0; i < this.inboundQueueBridges.length; ++i) {
                final InboundQueueBridge bridge = this.inboundQueueBridges[i];
                final String localQueueName = bridge.getLocalQueueName();
                final Queue activemqQueue = this.createActiveMQQueue(localSession, localQueueName);
                final String queueName = bridge.getInboundQueueName();
                final Queue foreignQueue = this.createForeignQueue(outboundSession, queueName);
                bridge.setConsumerQueue(foreignQueue);
                bridge.setProducerQueue(activemqQueue);
                bridge.setProducerConnection(this.localQueueConnection);
                bridge.setConsumerConnection(this.outboundQueueConnection);
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
    
    protected void initializeOutboundQueueBridges() throws JMSException {
        if (this.outboundQueueBridges != null) {
            final QueueSession outboundSession = this.outboundQueueConnection.createQueueSession(false, 1);
            final QueueSession localSession = this.localQueueConnection.createQueueSession(false, 1);
            for (int i = 0; i < this.outboundQueueBridges.length; ++i) {
                final OutboundQueueBridge bridge = this.outboundQueueBridges[i];
                final String localQueueName = bridge.getLocalQueueName();
                final Queue activemqQueue = this.createActiveMQQueue(localSession, localQueueName);
                final String queueName = bridge.getOutboundQueueName();
                final Queue foreignQueue = this.createForeignQueue(outboundSession, queueName);
                bridge.setConsumerQueue(activemqQueue);
                bridge.setProducerQueue(foreignQueue);
                bridge.setProducerConnection(this.outboundQueueConnection);
                bridge.setConsumerConnection(this.localQueueConnection);
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
        final Queue replyToProducerQueue = (Queue)destination;
        final boolean isInbound = replyToProducerConnection.equals(this.localQueueConnection);
        if (isInbound) {
            InboundQueueBridge bridge = (InboundQueueBridge)this.replyToBridges.get(replyToProducerQueue);
            if (bridge == null) {
                bridge = new InboundQueueBridge() {
                    @Override
                    protected Destination processReplyToDestination(final Destination destination) {
                        return null;
                    }
                };
                try {
                    final QueueSession replyToConsumerSession = ((QueueConnection)replyToConsumerConnection).createQueueSession(false, 1);
                    final Queue replyToConsumerQueue = replyToConsumerSession.createTemporaryQueue();
                    replyToConsumerSession.close();
                    bridge.setConsumerQueue(replyToConsumerQueue);
                    bridge.setProducerQueue(replyToProducerQueue);
                    bridge.setProducerConnection((QueueConnection)replyToProducerConnection);
                    bridge.setConsumerConnection((QueueConnection)replyToConsumerConnection);
                    bridge.setDoHandleReplyTo(false);
                    if (bridge.getJmsMessageConvertor() == null) {
                        bridge.setJmsMessageConvertor(this.getInboundMessageConvertor());
                    }
                    bridge.setJmsConnector(this);
                    bridge.start();
                    JmsQueueConnector.LOG.info("Created replyTo bridge for " + replyToProducerQueue);
                }
                catch (Exception e) {
                    JmsQueueConnector.LOG.error("Failed to create replyTo bridge for queue: " + replyToProducerQueue, e);
                    return null;
                }
                this.replyToBridges.put(replyToProducerQueue, bridge);
            }
            return bridge.getConsumerQueue();
        }
        OutboundQueueBridge bridge2 = (OutboundQueueBridge)this.replyToBridges.get(replyToProducerQueue);
        if (bridge2 == null) {
            bridge2 = new OutboundQueueBridge() {
                @Override
                protected Destination processReplyToDestination(final Destination destination) {
                    return null;
                }
            };
            try {
                final QueueSession replyToConsumerSession = ((QueueConnection)replyToConsumerConnection).createQueueSession(false, 1);
                final Queue replyToConsumerQueue = replyToConsumerSession.createTemporaryQueue();
                replyToConsumerSession.close();
                bridge2.setConsumerQueue(replyToConsumerQueue);
                bridge2.setProducerQueue(replyToProducerQueue);
                bridge2.setProducerConnection((QueueConnection)replyToProducerConnection);
                bridge2.setConsumerConnection((QueueConnection)replyToConsumerConnection);
                bridge2.setDoHandleReplyTo(false);
                if (bridge2.getJmsMessageConvertor() == null) {
                    bridge2.setJmsMessageConvertor(this.getOutboundMessageConvertor());
                }
                bridge2.setJmsConnector(this);
                bridge2.start();
                JmsQueueConnector.LOG.info("Created replyTo bridge for " + replyToProducerQueue);
            }
            catch (Exception e) {
                JmsQueueConnector.LOG.error("Failed to create replyTo bridge for queue: " + replyToProducerQueue, e);
                return null;
            }
            this.replyToBridges.put(replyToProducerQueue, bridge2);
        }
        return bridge2.getConsumerQueue();
    }
    
    protected Queue createActiveMQQueue(final QueueSession session, final String queueName) throws JMSException {
        return session.createQueue(queueName);
    }
    
    protected Queue createForeignQueue(final QueueSession session, final String queueName) throws JMSException {
        Queue result = null;
        try {
            result = session.createQueue(queueName);
        }
        catch (JMSException e) {
            try {
                result = (Queue)this.jndiOutboundTemplate.lookup(queueName, (Class)Queue.class);
            }
            catch (NamingException e2) {
                final String errStr = "Failed to look-up Queue for name: " + queueName;
                JmsQueueConnector.LOG.error(errStr, e);
                final JMSException jmsEx = new JMSException(errStr);
                jmsEx.setLinkedException(e2);
                throw jmsEx;
            }
        }
        return result;
    }
    
    static {
        LOG = LoggerFactory.getLogger(JmsQueueConnector.class);
    }
}
