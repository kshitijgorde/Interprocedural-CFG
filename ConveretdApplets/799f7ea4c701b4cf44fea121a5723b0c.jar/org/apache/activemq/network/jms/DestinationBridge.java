// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.network.jms;

import org.slf4j.LoggerFactory;
import javax.naming.NamingException;
import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Destination;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.jms.MessageConsumer;
import org.slf4j.Logger;
import javax.jms.MessageListener;
import org.apache.activemq.Service;

public abstract class DestinationBridge implements Service, MessageListener
{
    private static final Logger LOG;
    protected MessageConsumer consumer;
    protected AtomicBoolean started;
    protected JmsMesageConvertor jmsMessageConvertor;
    protected boolean doHandleReplyTo;
    protected JmsConnector jmsConnector;
    private int maximumRetries;
    
    public DestinationBridge() {
        this.started = new AtomicBoolean(false);
        this.doHandleReplyTo = true;
        this.maximumRetries = 10;
    }
    
    public MessageConsumer getConsumer() {
        return this.consumer;
    }
    
    public void setConsumer(final MessageConsumer consumer) {
        this.consumer = consumer;
    }
    
    public void setJmsConnector(final JmsConnector connector) {
        this.jmsConnector = connector;
    }
    
    public JmsMesageConvertor getJmsMessageConvertor() {
        return this.jmsMessageConvertor;
    }
    
    public void setJmsMessageConvertor(final JmsMesageConvertor jmsMessageConvertor) {
        this.jmsMessageConvertor = jmsMessageConvertor;
    }
    
    public int getMaximumRetries() {
        return this.maximumRetries;
    }
    
    public void setMaximumRetries(final int maximumRetries) {
        this.maximumRetries = maximumRetries;
    }
    
    protected Destination processReplyToDestination(final Destination destination) {
        return this.jmsConnector.createReplyToBridge(destination, this.getConnnectionForConsumer(), this.getConnectionForProducer());
    }
    
    @Override
    public void start() throws Exception {
        if (this.started.compareAndSet(false, true)) {
            final MessageConsumer consumer = this.createConsumer();
            consumer.setMessageListener(this);
            this.createProducer();
        }
    }
    
    @Override
    public void stop() throws Exception {
        this.started.set(false);
    }
    
    @Override
    public void onMessage(final Message message) {
        int attempt = 0;
        while (this.started.get() && message != null) {
            try {
                if (attempt > 0) {
                    this.restartProducer();
                }
                Message converted;
                if (this.doHandleReplyTo) {
                    final Destination replyTo = message.getJMSReplyTo();
                    if (replyTo != null) {
                        converted = this.jmsMessageConvertor.convert(message, this.processReplyToDestination(replyTo));
                    }
                    else {
                        converted = this.jmsMessageConvertor.convert(message);
                    }
                }
                else {
                    message.setJMSReplyTo(null);
                    converted = this.jmsMessageConvertor.convert(message);
                }
                this.sendMessage(converted);
                message.acknowledge();
                return;
            }
            catch (Exception e) {
                DestinationBridge.LOG.error("failed to forward message on attempt: " + ++attempt + " reason: " + e + " message: " + message, e);
                if (this.maximumRetries <= 0 || attempt < this.maximumRetries) {
                    continue;
                }
                try {
                    this.stop();
                }
                catch (Exception e2) {
                    DestinationBridge.LOG.warn("Failed to stop cleanly", e2);
                }
                continue;
            }
            break;
        }
    }
    
    protected boolean isDoHandleReplyTo() {
        return this.doHandleReplyTo;
    }
    
    protected void setDoHandleReplyTo(final boolean doHandleReplyTo) {
        this.doHandleReplyTo = doHandleReplyTo;
    }
    
    protected abstract MessageConsumer createConsumer() throws JMSException;
    
    protected abstract MessageProducer createProducer() throws JMSException;
    
    protected abstract void sendMessage(final Message p0) throws JMSException;
    
    protected abstract Connection getConnnectionForConsumer();
    
    protected abstract Connection getConnectionForProducer();
    
    protected void restartProducer() throws JMSException, NamingException {
        try {
            Thread.sleep(1000L);
            this.getConnectionForProducer().close();
        }
        catch (Exception e) {
            DestinationBridge.LOG.debug("Ignoring failure to close producer connection: " + e, e);
        }
        this.jmsConnector.restartProducerConnection();
        this.createProducer();
    }
    
    static {
        LOG = LoggerFactory.getLogger(DestinationBridge.class);
    }
}
