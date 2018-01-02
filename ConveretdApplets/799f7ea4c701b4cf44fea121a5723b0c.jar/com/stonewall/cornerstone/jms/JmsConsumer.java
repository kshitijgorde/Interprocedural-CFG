// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms;

import javax.jms.InvalidDestinationException;
import javax.jms.Message;
import javax.jms.Topic;
import javax.jms.JMSException;
import org.xmodel.log.Log;
import java.util.concurrent.locks.ReentrantLock;
import javax.jms.Destination;
import javax.jms.MessageListener;
import javax.jms.TopicSubscriber;
import javax.jms.MessageConsumer;

public class JmsConsumer implements MessageConsumer, TopicSubscriber
{
    private final JmsSession session;
    MessageConsumer consumer;
    private MessageListener listener;
    private Destination destination;
    private String selector;
    private boolean noLocal;
    private String subscription;
    private final ReentrantLock lock;
    static final Log log;
    
    static {
        log = Log.getLog(JmsConsumer.class);
    }
    
    JmsConsumer(final JmsSession session, final MessageConsumer consumer, final Destination destination) throws JMSException {
        this.lock = new ReentrantLock();
        this.session = session;
        this.consumer = consumer;
        this.destination = destination;
        this.selector = null;
        this.noLocal = false;
        this.subscription = null;
    }
    
    public void finalize() {
        try {
            this.close();
        }
        catch (Exception ex) {}
    }
    
    @Override
    public boolean getNoLocal() {
        this.lock.lock();
        try {
            return this.noLocal;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public Topic getTopic() {
        this.lock.lock();
        try {
            return (Topic)this.destination;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public String getMessageSelector() throws JMSException {
        this.lock.lock();
        try {
            return this.consumer.getMessageSelector();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public MessageListener getMessageListener() throws JMSException {
        this.lock.lock();
        try {
            return this.consumer.getMessageListener();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void setMessageListener(final MessageListener arg0) throws JMSException {
        this.lock.lock();
        try {
            this.consumer.setMessageListener(arg0);
            this.listener = arg0;
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public Message receive() throws JMSException {
        this.lock.lock();
        try {
            return this.consumer.receive();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public Message receive(final long arg0) throws JMSException {
        this.lock.lock();
        try {
            return this.consumer.receive(arg0);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public Message receiveNoWait() throws JMSException {
        this.lock.lock();
        try {
            return this.consumer.receiveNoWait();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void close() throws JMSException {
        this.lock.lock();
        try {
            this.consumer.close();
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        this.session.closed(this);
    }
    
    void selector(final String s) {
        this.selector = s;
    }
    
    void noLocal(final boolean flag) {
        this.noLocal = flag;
    }
    
    void subscription(final String s) {
        this.subscription = s;
    }
    
    void reinit() throws JMSException {
        JmsConsumer.log.info(this + ": Reinit()");
        if (this.subscription == null) {
            this.reinitConsumer();
        }
        else {
            this.reinitDurableSubscriber();
        }
    }
    
    void reinitConsumer() throws JMSException {
        this.lock.lock();
        try {
            try {
                this.consumer = this.session.session().createConsumer(this.destination, this.selector, this.noLocal);
            }
            catch (InvalidDestinationException e) {
                JmsConsumer.log.error(this, e);
                return;
            }
            if (this.listener != null) {
                this.consumer.setMessageListener(this.listener);
            }
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    void reinitDurableSubscriber() throws JMSException {
        this.lock.lock();
        try {
            final MessageConsumer c = this.session.session().createDurableSubscriber(this.getTopic(), this.subscription, this.selector, this.noLocal);
            if (this.listener != null) {
                c.setMessageListener(this.listener);
            }
            this.consumer = c;
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
}
