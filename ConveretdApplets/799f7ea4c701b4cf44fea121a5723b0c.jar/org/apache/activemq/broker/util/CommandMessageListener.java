// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.util;

import org.slf4j.LoggerFactory;
import org.apache.activemq.util.FactoryFinder;
import java.io.IOException;
import org.apache.activemq.command.ActiveMQTextMessage;
import javax.jms.JMSException;
import javax.jms.Destination;
import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import org.slf4j.Logger;
import javax.jms.MessageListener;

public class CommandMessageListener implements MessageListener
{
    private static final Logger LOG;
    private Session session;
    private MessageProducer producer;
    private CommandHandler handler;
    
    public CommandMessageListener(final Session session) {
        this.session = session;
    }
    
    @Override
    public void onMessage(final Message message) {
        if (CommandMessageListener.LOG.isDebugEnabled()) {
            CommandMessageListener.LOG.debug("Received command: " + message);
        }
        if (message instanceof TextMessage) {
            final TextMessage request = (TextMessage)message;
            try {
                final Destination replyTo = message.getJMSReplyTo();
                if (replyTo == null) {
                    CommandMessageListener.LOG.warn("Ignored message as no JMSReplyTo set: " + message);
                    return;
                }
                final Message response = this.processCommand(request);
                this.addReplyHeaders(request, response);
                this.getProducer().send(replyTo, response);
            }
            catch (Exception e) {
                CommandMessageListener.LOG.error("Failed to process message due to: " + e + ". Message: " + message, e);
            }
        }
        else {
            CommandMessageListener.LOG.warn("Ignoring invalid message: " + message);
        }
    }
    
    protected void addReplyHeaders(final TextMessage request, final Message response) throws JMSException {
        final String correlationID = request.getJMSCorrelationID();
        if (correlationID != null) {
            response.setJMSCorrelationID(correlationID);
        }
    }
    
    public Message processCommand(final TextMessage request) throws Exception {
        final TextMessage response = this.session.createTextMessage();
        this.getHandler().processCommand(request, response);
        return response;
    }
    
    public String processCommandText(final String line) throws Exception {
        final TextMessage request = new ActiveMQTextMessage();
        request.setText(line);
        final TextMessage response = new ActiveMQTextMessage();
        this.getHandler().processCommand(request, response);
        return response.getText();
    }
    
    public Session getSession() {
        return this.session;
    }
    
    public MessageProducer getProducer() throws JMSException {
        if (this.producer == null) {
            this.producer = this.getSession().createProducer(null);
        }
        return this.producer;
    }
    
    public CommandHandler getHandler() throws IllegalAccessException, IOException, InstantiationException, ClassNotFoundException {
        if (this.handler == null) {
            this.handler = this.createHandler();
        }
        return this.handler;
    }
    
    private CommandHandler createHandler() throws IllegalAccessException, IOException, ClassNotFoundException, InstantiationException {
        final FactoryFinder factoryFinder = new FactoryFinder("META-INF/services/org/apache/activemq/broker/");
        return (CommandHandler)factoryFinder.newInstance("agent");
    }
    
    static {
        LOG = LoggerFactory.getLogger(CommandMessageListener.class);
    }
}
