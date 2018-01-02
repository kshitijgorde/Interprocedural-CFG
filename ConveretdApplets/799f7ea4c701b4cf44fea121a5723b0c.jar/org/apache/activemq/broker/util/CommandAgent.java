// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.util;

import org.slf4j.LoggerFactory;
import org.apache.activemq.advisory.AdvisorySupport;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.annotation.PreDestroy;
import javax.jms.JMSException;
import org.apache.activemq.util.ServiceStopper;
import javax.annotation.PostConstruct;
import javax.jms.MessageListener;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Destination;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import org.slf4j.Logger;
import javax.jms.ExceptionListener;
import org.apache.activemq.Service;

public class CommandAgent implements Service, ExceptionListener
{
    private static final Logger LOG;
    private String brokerUrl;
    private String username;
    private String password;
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Destination commandDestination;
    private CommandMessageListener listener;
    private Session session;
    private MessageConsumer consumer;
    
    public CommandAgent() {
        this.brokerUrl = "vm://localhost";
    }
    
    @PostConstruct
    @Override
    public void start() throws Exception {
        this.session = this.getConnection().createSession(false, 1);
        this.listener = new CommandMessageListener(this.session);
        final Destination destination = this.getCommandDestination();
        if (CommandAgent.LOG.isDebugEnabled()) {
            CommandAgent.LOG.debug("Agent subscribing to control destination: " + destination);
        }
        (this.consumer = this.session.createConsumer(destination)).setMessageListener(this.listener);
    }
    
    @PreDestroy
    @Override
    public void stop() throws Exception {
        final ServiceStopper stopper = new ServiceStopper();
        if (this.consumer != null) {
            try {
                this.consumer.close();
                this.consumer = null;
            }
            catch (JMSException e) {
                stopper.onException(this, e);
            }
        }
        if (this.session != null) {
            try {
                this.session.close();
                this.session = null;
            }
            catch (JMSException e) {
                stopper.onException(this, e);
            }
        }
        if (this.connection != null) {
            try {
                this.connection.close();
                this.connection = null;
            }
            catch (JMSException e) {
                stopper.onException(this, e);
            }
        }
        stopper.throwFirstException();
    }
    
    public String getBrokerUrl() {
        return this.brokerUrl;
    }
    
    public void setBrokerUrl(final String brokerUrl) {
        this.brokerUrl = brokerUrl;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public ConnectionFactory getConnectionFactory() {
        if (this.connectionFactory == null) {
            this.connectionFactory = new ActiveMQConnectionFactory(this.brokerUrl);
        }
        return this.connectionFactory;
    }
    
    public void setConnectionFactory(final ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
    
    public Connection getConnection() throws JMSException {
        if (this.connection == null) {
            (this.connection = this.createConnection()).setExceptionListener(this);
            this.connection.start();
        }
        return this.connection;
    }
    
    public void setConnection(final Connection connection) {
        this.connection = connection;
    }
    
    public Destination getCommandDestination() {
        if (this.commandDestination == null) {
            this.commandDestination = this.createCommandDestination();
        }
        return this.commandDestination;
    }
    
    public void setCommandDestination(final Destination commandDestination) {
        this.commandDestination = commandDestination;
    }
    
    protected Connection createConnection() throws JMSException {
        return this.getConnectionFactory().createConnection(this.username, this.password);
    }
    
    protected Destination createCommandDestination() {
        return AdvisorySupport.getAgentDestination();
    }
    
    @Override
    public void onException(final JMSException exception) {
        try {
            this.stop();
        }
        catch (Exception ex) {}
    }
    
    static {
        LOG = LoggerFactory.getLogger(CommandAgent.class);
    }
}
