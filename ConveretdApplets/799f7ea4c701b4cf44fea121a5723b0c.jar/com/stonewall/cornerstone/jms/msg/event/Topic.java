// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg.event;

import javax.jms.Session;
import javax.jms.Destination;
import javax.jms.Connection;
import com.stonewall.cornerstone.jms.JmsProvider;
import com.stonewall.cornerstone.jms.ProviderFactory;
import javax.jms.JMSException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.xmodel.log.Log;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import java.util.Map;

public class Topic
{
    private static final Map<MessageListener, MessageConsumer> jmsConsumers;
    private static Map<Event.Type, String> topics;
    private String id;
    static final Log log;
    
    static {
        jmsConsumers = new ConcurrentHashMap<MessageListener, MessageConsumer>();
        (Topic.topics = new HashMap<Event.Type, String>()).put(Event.Type.alarm, "topic.event.alarm");
        Topic.topics.put(Event.Type.db, "topic.event.db");
        Topic.topics.put(Event.Type.heartbeat, "topic.heartbeat");
        Topic.topics.put(Event.Type.mail, "topic.event.mail");
        Topic.topics.put(Event.Type.process, "topic.event.process");
        Topic.topics.put(Event.Type.security, "topic.event.security");
        Topic.topics.put(Event.Type.service, "topic.event.service");
        Topic.topics.put(Event.Type.timesync, "topic.time");
        log = Log.getLog(Topic.class);
    }
    
    public Topic(final Event.Type type) {
        this.id = Topic.topics.get(type);
    }
    
    public Topic(final Event.Type type, final String qualifier) {
        this.id = Topic.topics.get(type);
        this.id = String.valueOf(this.id) + "." + qualifier;
    }
    
    public void register(final MessageListener listener) throws JMSException {
        if (Topic.jmsConsumers.get(listener) != null) {
            throw new JMSException("Listener: " + listener + " already added.");
        }
        final JmsProvider jms = ProviderFactory.getJmsProvider();
        final Connection connection = jms.getConnection();
        final Destination destination = this.getDestination();
        final Session session = connection.createSession(false, 1);
        final MessageConsumer jmsConsumer = session.createConsumer(destination);
        jmsConsumer.setMessageListener(listener);
        Topic.jmsConsumers.put(listener, jmsConsumer);
        Topic.log.debug("Adding listener : " + listener + " for destination : " + destination);
    }
    
    public final void unregister(final MessageListener listener) throws JMSException {
        final MessageConsumer jmsConsumer = Topic.jmsConsumers.remove(listener);
        if (jmsConsumer != null) {
            jmsConsumer.close();
        }
    }
    
    public MessageConsumer getConsumer() throws JMSException {
        final JmsProvider jms = ProviderFactory.getJmsProvider();
        final Connection connection = jms.getConnection();
        final Destination destination = this.getDestination();
        final Session session = connection.createSession(false, 1);
        return session.createConsumer(destination);
    }
    
    public Destination getDestination() {
        try {
            return ProviderFactory.getJmsProvider().getDestination(this.id);
        }
        catch (JMSException e) {
            Topic.log.error("Cannot find destination:" + this.id);
            return null;
        }
    }
}
