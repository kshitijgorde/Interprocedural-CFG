// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util;

import java.io.Serializable;
import javax.jms.Message;
import org.apache.log4j.spi.LoggingEvent;
import javax.jms.Destination;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Connection;
import org.apache.log4j.AppenderSkeleton;

public abstract class JmsLogAppenderSupport extends AppenderSkeleton
{
    public static final int JMS_PUBLISH_ERROR_CODE = 61616;
    private Connection connection;
    private Session session;
    private MessageProducer producer;
    private boolean allowTextMessages;
    private String subjectPrefix;
    
    public JmsLogAppenderSupport() {
        this.allowTextMessages = true;
        this.subjectPrefix = "log4j.";
    }
    
    public Connection getConnection() throws JMSException, NamingException {
        if (this.connection == null) {
            this.connection = this.createConnection();
        }
        return this.connection;
    }
    
    public void setConnection(final Connection connection) {
        this.connection = connection;
    }
    
    public Session getSession() throws JMSException, NamingException {
        if (this.session == null) {
            this.session = this.createSession();
        }
        return this.session;
    }
    
    public void setSession(final Session session) {
        this.session = session;
    }
    
    public MessageProducer getProducer() throws JMSException, NamingException {
        if (this.producer == null) {
            this.producer = this.createProducer();
        }
        return this.producer;
    }
    
    public void setProducer(final MessageProducer producer) {
        this.producer = producer;
    }
    
    @Override
    public void close() {
        final List<JMSException> errors = new ArrayList<JMSException>();
        if (this.producer != null) {
            try {
                this.producer.close();
            }
            catch (JMSException e) {
                errors.add(e);
            }
        }
        if (this.session != null) {
            try {
                this.session.close();
            }
            catch (JMSException e) {
                errors.add(e);
            }
        }
        if (this.connection != null) {
            try {
                this.connection.close();
            }
            catch (JMSException e) {
                errors.add(e);
            }
        }
        for (final JMSException e2 : errors) {
            this.getErrorHandler().error("Error closing JMS resources: " + e2, e2, 61616);
        }
    }
    
    @Override
    public boolean requiresLayout() {
        return false;
    }
    
    @Override
    public void activateOptions() {
        try {
            this.getProducer();
        }
        catch (Exception e) {
            this.getErrorHandler().error("Could not create JMS resources: " + e, e, 61616);
        }
    }
    
    protected abstract Connection createConnection() throws JMSException, NamingException;
    
    protected Session createSession() throws JMSException, NamingException {
        return this.getConnection().createSession(false, 1);
    }
    
    protected MessageProducer createProducer() throws JMSException, NamingException {
        return this.getSession().createProducer(null);
    }
    
    @Override
    protected void append(final LoggingEvent event) {
        try {
            final Message message = this.createMessage(event);
            final Destination destination = this.getDestination(event);
            this.getProducer().send(destination, message);
        }
        catch (Exception e) {
            this.getErrorHandler().error("Could not send message due to: " + e, e, 61616, event);
        }
    }
    
    protected Message createMessage(final LoggingEvent event) throws JMSException, NamingException {
        Message answer = null;
        final Object value = event.getMessage();
        if (this.allowTextMessages && value instanceof String) {
            answer = this.getSession().createTextMessage((String)value);
        }
        else {
            answer = this.getSession().createObjectMessage((Serializable)value);
        }
        answer.setStringProperty("level", event.getLevel().toString());
        answer.setIntProperty("levelInt", event.getLevel().toInt());
        answer.setStringProperty("threadName", event.getThreadName());
        return answer;
    }
    
    protected Destination getDestination(final LoggingEvent event) throws JMSException, NamingException {
        final String name = this.subjectPrefix + event.getLoggerName();
        return this.getSession().createTopic(name);
    }
}
