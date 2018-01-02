// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.network.jms;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Queue;

class QueueBridge extends DestinationBridge
{
    protected Queue consumerQueue;
    protected Queue producerQueue;
    protected QueueSession consumerSession;
    protected QueueSession producerSession;
    protected String selector;
    protected QueueSender producer;
    protected QueueConnection consumerConnection;
    protected QueueConnection producerConnection;
    
    @Override
    public void stop() throws Exception {
        super.stop();
        if (this.consumerSession != null) {
            this.consumerSession.close();
        }
        if (this.producerSession != null) {
            this.producerSession.close();
        }
    }
    
    @Override
    protected MessageConsumer createConsumer() throws JMSException {
        this.consumerSession = this.consumerConnection.createQueueSession(false, 2);
        MessageConsumer consumer = null;
        if (this.selector != null && this.selector.length() > 0) {
            consumer = this.consumerSession.createReceiver(this.consumerQueue, this.selector);
        }
        else {
            consumer = this.consumerSession.createReceiver(this.consumerQueue);
        }
        return consumer;
    }
    
    @Override
    protected synchronized MessageProducer createProducer() throws JMSException {
        this.producerSession = this.producerConnection.createQueueSession(false, 1);
        return this.producer = this.producerSession.createSender(null);
    }
    
    @Override
    protected synchronized void sendMessage(final Message message) throws JMSException {
        if (this.producer == null) {
            this.createProducer();
        }
        this.producer.send(this.producerQueue, message);
    }
    
    public QueueConnection getConsumerConnection() {
        return this.consumerConnection;
    }
    
    public void setConsumerConnection(final QueueConnection consumerConnection) {
        this.consumerConnection = consumerConnection;
    }
    
    public Queue getConsumerQueue() {
        return this.consumerQueue;
    }
    
    public void setConsumerQueue(final Queue consumerQueue) {
        this.consumerQueue = consumerQueue;
    }
    
    public QueueConnection getProducerConnection() {
        return this.producerConnection;
    }
    
    public void setProducerConnection(final QueueConnection producerConnection) {
        this.producerConnection = producerConnection;
    }
    
    public Queue getProducerQueue() {
        return this.producerQueue;
    }
    
    public void setProducerQueue(final Queue producerQueue) {
        this.producerQueue = producerQueue;
    }
    
    public String getSelector() {
        return this.selector;
    }
    
    public void setSelector(final String selector) {
        this.selector = selector;
    }
    
    @Override
    protected Connection getConnnectionForConsumer() {
        return this.getConsumerConnection();
    }
    
    @Override
    protected Connection getConnectionForProducer() {
        return this.getProducerConnection();
    }
}
