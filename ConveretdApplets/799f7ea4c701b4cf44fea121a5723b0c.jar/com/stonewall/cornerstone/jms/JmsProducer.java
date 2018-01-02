// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms;

import javax.jms.Message;
import javax.jms.JMSException;
import org.xmodel.log.Log;
import java.util.concurrent.locks.ReentrantLock;
import javax.jms.Destination;
import javax.jms.MessageProducer;

public class JmsProducer implements MessageProducer
{
    JmsSession session;
    MessageProducer producer;
    Destination destination;
    int priority;
    int deliveryMode;
    long ttl;
    boolean disableMessageID;
    boolean disableMessageTS;
    private final ReentrantLock lock;
    static final Log log;
    
    static {
        log = Log.getLog(JmsProducer.class);
    }
    
    public JmsProducer(final JmsSession session, final MessageProducer producer) throws JMSException {
        this.lock = new ReentrantLock();
        this.session = session;
        this.producer = producer;
        this.destination = producer.getDestination();
        this.priority = producer.getPriority();
        this.deliveryMode = producer.getDeliveryMode();
        this.ttl = producer.getTimeToLive();
        this.disableMessageID = this.getDisableMessageID();
        this.disableMessageTS = this.getDisableMessageTimestamp();
    }
    
    public void finalize() {
        try {
            this.close();
        }
        catch (Exception ex) {}
    }
    
    @Override
    public void setDisableMessageID(final boolean arg0) throws JMSException {
        this.lock.lock();
        try {
            this.producer.setDisableMessageID(arg0);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public boolean getDisableMessageID() throws JMSException {
        this.lock.lock();
        try {
            return this.producer.getDisableMessageID();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void setDisableMessageTimestamp(final boolean arg0) throws JMSException {
        this.lock.lock();
        try {
            this.producer.setDisableMessageTimestamp(arg0);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public boolean getDisableMessageTimestamp() throws JMSException {
        this.lock.lock();
        try {
            return this.producer.getDisableMessageTimestamp();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void setDeliveryMode(final int arg0) throws JMSException {
        this.lock.lock();
        try {
            this.producer.setDeliveryMode(arg0);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public int getDeliveryMode() throws JMSException {
        this.lock.lock();
        try {
            return this.producer.getDeliveryMode();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void setPriority(final int arg0) throws JMSException {
        this.lock.lock();
        try {
            this.producer.setPriority(arg0);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public int getPriority() throws JMSException {
        this.lock.lock();
        try {
            return this.producer.getPriority();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void setTimeToLive(final long arg0) throws JMSException {
        this.lock.lock();
        try {
            this.producer.setTimeToLive(arg0);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public long getTimeToLive() throws JMSException {
        this.lock.lock();
        try {
            return this.producer.getTimeToLive();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public Destination getDestination() throws JMSException {
        this.lock.lock();
        try {
            return this.producer.getDestination();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void close() throws JMSException {
        this.lock.lock();
        try {
            this.producer.close();
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        this.session.closed(this);
    }
    
    @Override
    public void send(final Message arg0) throws JMSException {
        this.lock.lock();
        try {
            this.adjustTTL();
            this.producer.send(arg0);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public void send(final Message arg0, final int arg1, final int arg2, final long arg3) throws JMSException {
        this.lock.lock();
        try {
            this.adjustTTL();
            this.producer.send(arg0, arg1, arg2, arg3);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public void send(final Destination arg0, final Message arg1) throws JMSException {
        this.lock.lock();
        try {
            this.adjustTTL();
            this.producer.send(arg0, arg1);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public void send(final Destination arg0, final Message arg1, final int arg2, final int arg3, long arg4) throws JMSException {
        arg4 = Timing.getInstance().adjust(arg4);
        this.lock.lock();
        try {
            this.producer.send(arg0, arg1, arg2, arg3, arg4);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    void reinit() throws JMSException {
        JmsProducer.log.info(this + ": Reinit()");
        this.lock.lock();
        try {
            final MessageProducer p = this.session.session().createProducer(this.destination);
            p.setPriority(this.priority);
            p.setDeliveryMode(this.deliveryMode);
            p.setTimeToLive(this.ttl);
            p.setDisableMessageID(this.disableMessageID);
            p.setDisableMessageTimestamp(this.disableMessageTS);
            this.producer = p;
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    private void adjustTTL() throws JMSException {
        long ttl = this.producer.getTimeToLive();
        if (ttl > 0L) {
            ttl = Timing.getInstance().adjust(ttl);
            this.producer.setTimeToLive(ttl);
        }
    }
}
