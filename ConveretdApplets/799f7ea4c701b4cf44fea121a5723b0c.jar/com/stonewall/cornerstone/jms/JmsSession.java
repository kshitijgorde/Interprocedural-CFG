// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms;

import java.util.Iterator;
import javax.jms.TemporaryTopic;
import javax.jms.TemporaryQueue;
import javax.jms.QueueBrowser;
import javax.jms.TopicSubscriber;
import javax.jms.Topic;
import javax.jms.Queue;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Destination;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.jms.StreamMessage;
import java.io.Serializable;
import javax.jms.ObjectMessage;
import javax.jms.Message;
import javax.jms.MapMessage;
import javax.jms.BytesMessage;
import javax.jms.JMSException;
import java.util.ArrayList;
import org.xmodel.log.Log;
import java.util.concurrent.locks.ReentrantLock;
import java.util.List;
import javax.jms.Session;

public class JmsSession implements Session
{
    private Session session;
    private final boolean transacted;
    private final int ackMode;
    private final JmsConnection connection;
    private final List<JmsProducer> producerList;
    private final List<JmsConsumer> consumerList;
    private final ReentrantLock lock;
    static final Log log;
    
    static {
        log = Log.getLog(JmsSession.class);
    }
    
    JmsSession(final JmsConnection connection, final Session session) throws JMSException {
        this.lock = new ReentrantLock();
        this.connection = connection;
        this.session = session;
        this.transacted = session.getTransacted();
        this.ackMode = session.getAcknowledgeMode();
        this.producerList = new ArrayList<JmsProducer>();
        this.consumerList = new ArrayList<JmsConsumer>();
    }
    
    public void finalize() {
        try {
            this.close();
        }
        catch (Exception ex) {}
    }
    
    @Override
    public BytesMessage createBytesMessage() throws JMSException {
        this.lock.lock();
        try {
            return this.session.createBytesMessage();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public MapMessage createMapMessage() throws JMSException {
        this.lock.lock();
        try {
            return this.session.createMapMessage();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public Message createMessage() throws JMSException {
        this.lock.lock();
        try {
            return this.session.createMessage();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public ObjectMessage createObjectMessage() throws JMSException {
        this.lock.lock();
        try {
            return this.session.createObjectMessage();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public ObjectMessage createObjectMessage(final Serializable arg0) throws JMSException {
        this.lock.lock();
        try {
            return this.session.createObjectMessage(arg0);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public StreamMessage createStreamMessage() throws JMSException {
        this.lock.lock();
        try {
            return this.session.createStreamMessage();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public TextMessage createTextMessage() throws JMSException {
        this.lock.lock();
        try {
            return this.session.createTextMessage();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public TextMessage createTextMessage(final String arg0) throws JMSException {
        this.lock.lock();
        try {
            return this.session.createTextMessage(arg0);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public boolean getTransacted() throws JMSException {
        this.lock.lock();
        try {
            return this.session.getTransacted();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public int getAcknowledgeMode() throws JMSException {
        this.lock.lock();
        try {
            return this.session.getAcknowledgeMode();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void commit() throws JMSException {
        this.lock.lock();
        try {
            this.session.commit();
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public void rollback() throws JMSException {
        this.lock.lock();
        try {
            this.session.rollback();
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public void close() throws JMSException {
        this.lock.lock();
        try {
            this.producerList.clear();
            this.consumerList.clear();
            this.session.close();
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        this.connection.closed(this);
    }
    
    @Override
    public void recover() throws JMSException {
        this.lock.lock();
        try {
            this.session.recover();
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public MessageListener getMessageListener() throws JMSException {
        this.lock.lock();
        try {
            return this.session.getMessageListener();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void setMessageListener(final MessageListener arg0) throws JMSException {
        this.lock.lock();
        try {
            this.session.setMessageListener(arg0);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public void run() {
        this.session.run();
    }
    
    @Override
    public MessageProducer createProducer(final Destination arg0) throws JMSException {
        this.lock.lock();
        try {
            final MessageProducer mp = this.session.createProducer(arg0);
            final JmsProducer p = new JmsProducer(this, mp);
            this.producerList.add(p);
            return p;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public MessageConsumer createConsumer(final Destination arg0) throws JMSException {
        this.lock.lock();
        try {
            final MessageConsumer mc = this.session.createConsumer(arg0);
            final JmsConsumer c = new JmsConsumer(this, mc, arg0);
            this.consumerList.add(c);
            return c;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public MessageConsumer createConsumer(final Destination arg0, final String arg1) throws JMSException {
        this.lock.lock();
        try {
            final MessageConsumer mc = this.session.createConsumer(arg0, arg1);
            final JmsConsumer c = new JmsConsumer(this, mc, arg0);
            c.selector(arg1);
            this.consumerList.add(c);
            return c;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public MessageConsumer createConsumer(final Destination arg0, final String arg1, final boolean arg2) throws JMSException {
        this.lock.lock();
        try {
            final MessageConsumer mc = this.session.createConsumer(arg0, arg1, arg2);
            final JmsConsumer c = new JmsConsumer(this, mc, arg0);
            c.selector(arg1);
            c.noLocal(arg2);
            this.consumerList.add(c);
            return c;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public Queue createQueue(final String arg0) throws JMSException {
        this.lock.lock();
        try {
            return this.session.createQueue(arg0);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public Topic createTopic(final String arg0) throws JMSException {
        this.lock.lock();
        try {
            return this.session.createTopic(arg0);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public TopicSubscriber createDurableSubscriber(final Topic arg0, final String arg1) throws JMSException {
        this.lock.lock();
        try {
            final MessageConsumer mc = this.session.createDurableSubscriber(arg0, arg1);
            final JmsConsumer c = new JmsConsumer(this, mc, arg0);
            c.subscription(arg1);
            this.consumerList.add(c);
            return c;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public TopicSubscriber createDurableSubscriber(final Topic arg0, final String arg1, final String arg2, final boolean arg3) throws JMSException {
        this.lock.lock();
        try {
            final MessageConsumer mc = this.session.createDurableSubscriber(arg0, arg1, arg2, arg3);
            final JmsConsumer c = new JmsConsumer(this, mc, arg0);
            c.subscription(arg1);
            c.selector(arg2);
            c.noLocal(arg3);
            this.consumerList.add(c);
            return c;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public QueueBrowser createBrowser(final Queue arg0) throws JMSException {
        this.lock.lock();
        try {
            return this.session.createBrowser(arg0);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public QueueBrowser createBrowser(final Queue arg0, final String arg1) throws JMSException {
        this.lock.lock();
        try {
            return this.session.createBrowser(arg0, arg1);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public TemporaryQueue createTemporaryQueue() throws JMSException {
        this.lock.lock();
        try {
            return this.session.createTemporaryQueue();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public TemporaryTopic createTemporaryTopic() throws JMSException {
        this.lock.lock();
        try {
            return this.session.createTemporaryTopic();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    @Override
    public void unsubscribe(final String arg0) throws JMSException {
        this.lock.lock();
        try {
            this.session.unsubscribe(arg0);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    void reinit() throws JMSException {
        JmsSession.log.info(this + ": Reinit()");
        this.lock.lock();
        try {
            this.session = this.connection.connection().createSession(this.transacted, this.ackMode);
            for (final JmsProducer p : this.producerList) {
                p.reinit();
            }
            for (final JmsConsumer c : this.consumerList) {
                c.reinit();
            }
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    void closed(final JmsProducer p) {
        this.lock.lock();
        try {
            this.producerList.remove(p);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    void closed(final JmsConsumer c) {
        this.lock.lock();
        try {
            this.consumerList.remove(c);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    Session session() {
        return this.session;
    }
}
