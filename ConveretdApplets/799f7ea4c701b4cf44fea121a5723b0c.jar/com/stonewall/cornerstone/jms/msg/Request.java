// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg;

import javax.jms.TemporaryQueue;
import javax.jms.MessageProducer;
import com.stonewall.cornerstone.jms.JmsProvider;
import org.xmodel.IModelObject;
import javax.jms.Session;
import javax.jms.Connection;
import javax.jms.JMSException;
import com.stonewall.cornerstone.jms.ProviderFactory;
import javax.jms.MessageListener;
import java.util.concurrent.ConcurrentHashMap;
import org.xmodel.log.Log;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import java.util.Map;

public abstract class Request extends Message
{
    private static final Map<String, MessageConsumer> jmsConsumers;
    private String jmsMessageId;
    private Destination replyTo;
    protected Destination jmsReplyTo;
    static final Log log;
    
    static {
        jmsConsumers = new ConcurrentHashMap<String, MessageConsumer>();
        log = Log.getLog(Request.class);
    }
    
    protected static final void addListener(final String destId, final MessageListener listener, final boolean reset) throws JMSException {
        final Connection connection = ProviderFactory.getJmsProvider().getConnection();
        if (Request.jmsConsumers.get(destId) != null) {
            throw new JMSException("Listener already registered for " + destId);
        }
        final Session session = connection.createSession(false, 1);
        final Destination destination = Message.getDestination(destId);
        final MessageConsumer jmsConsumer = session.createConsumer(destination);
        if (reset) {
            javax.jms.Message m = null;
            do {
                Request.log.info("Removing message" + m);
                m = jmsConsumer.receiveNoWait();
            } while (m != null);
        }
        jmsConsumer.setMessageListener(listener);
        Request.jmsConsumers.put(destId, jmsConsumer);
    }
    
    public static void removeListener(final String destId) throws JMSException {
        final MessageConsumer jmsConsumer = Request.jmsConsumers.remove(destId);
        jmsConsumer.close();
    }
    
    public Request(final String rootTag) {
        super(rootTag);
    }
    
    public Request() {
    }
    
    public Request(final JmsMessage message, final IModelObject root) throws Exception {
        super(message, root);
    }
    
    public Request(final IModelObject root) {
        super(root);
    }
    
    public void processJMSMessageInfo(final JmsMessage jmsMessage) throws Exception {
        super.processJMSMessageInfo(jmsMessage);
        this.jmsReplyTo = jmsMessage.getReplyTo();
        this.jmsMessageId = jmsMessage.getMessageId();
    }
    
    @Override
    public JmsMessage getJMSMessage(final Session session) throws JMSException {
        final JmsMessage message = super.getJMSMessage(session);
        message.setReplyTo(this.replyTo);
        return message;
    }
    
    protected String getJmsMessageId() {
        return this.jmsMessageId;
    }
    
    public void send(final Destination replyTo) {
        this.replyTo = replyTo;
        try {
            Request.log.debug("Request sent: " + this);
            super._send(Message.getDestination(this.getDestinationId()));
        }
        catch (JMSException e) {
            Request.log.error(e);
        }
    }
    
    protected abstract String getDestinationId();
    
    public abstract Reply createReply();
    
    public JmsMessage sendAndWait(long timeout) throws JMSException {
        final JmsProvider provider = ProviderFactory.getJmsProvider();
        MessageConsumer tempConsumer = null;
        Session session = null;
        MessageProducer producer = null;
        TemporaryQueue tempQueue = null;
        while (true) {
            try {
                final Connection connection = provider.getConnection();
                session = connection.createSession(false, 1);
                producer = session.createProducer(Message.getDestination(this.getDestinationId()));
                producer.setTimeToLive(this.timeToLive);
                tempQueue = (TemporaryQueue)(this.replyTo = session.createTemporaryQueue());
                tempConsumer = session.createConsumer(tempQueue);
                Request.log.debug("Request sent: " + this);
                final JmsMessage msg = this.getJMSMessage(session);
                producer.send(msg.getMessage());
            }
            catch (JMSException e) {
                if (timeout <= 0L) {
                    if (producer != null) {
                        producer.close();
                    }
                    if (tempConsumer != null) {
                        tempConsumer.close();
                    }
                    this.replyTo = null;
                    if (tempQueue != null) {
                        tempQueue.delete();
                    }
                    if (session != null) {
                        session.close();
                    }
                    Request.log.error(e);
                    throw new JMSException("Request send timed out");
                }
                try {
                    Thread.sleep(this.sleepDuration());
                }
                catch (InterruptedException ex) {}
                timeout -= this.sleepDuration();
                continue;
            }
            break;
        }
        try {
            final javax.jms.Message message = tempConsumer.receive(timeout);
            if (message != null) {
                return new JmsMessage(message);
            }
            return null;
        }
        catch (JMSException e) {
            Request.log.error("Timeout=" + timeout, e);
            return null;
        }
        finally {
            if (producer != null) {
                producer.close();
            }
            if (tempConsumer != null) {
                tempConsumer.close();
            }
            this.replyTo = null;
            if (tempQueue != null) {
                tempQueue.delete();
            }
            if (session != null) {
                session.close();
            }
        }
    }
}
