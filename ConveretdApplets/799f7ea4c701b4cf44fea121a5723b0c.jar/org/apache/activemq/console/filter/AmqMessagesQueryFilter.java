// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.console.filter;

import javax.jms.JMSException;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.Connection;
import java.util.Enumeration;
import java.util.Collections;
import javax.jms.Queue;
import java.util.Iterator;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.command.ActiveMQQueue;
import java.util.List;
import javax.jms.Destination;
import java.net.URI;

public class AmqMessagesQueryFilter extends AbstractQueryFilter
{
    private URI brokerUrl;
    private Destination destination;
    
    public AmqMessagesQueryFilter(final URI brokerUrl, final Destination destination) {
        super(null);
        this.brokerUrl = brokerUrl;
        this.destination = destination;
    }
    
    @Override
    public List query(final List queries) throws Exception {
        String selector = "";
        final Iterator i = queries.iterator();
        while (i.hasNext()) {
            selector = selector + "(" + i.next().toString() + ") AND ";
        }
        if (!selector.equals("")) {
            selector = selector.substring(0, selector.length() - 5);
        }
        if (this.destination instanceof ActiveMQQueue) {
            return this.queryMessages((ActiveMQQueue)this.destination, selector);
        }
        return this.queryMessages((ActiveMQTopic)this.destination, selector);
    }
    
    protected List queryMessages(final ActiveMQQueue queue, final String selector) throws Exception {
        final Connection conn = this.createConnection(this.getBrokerUrl());
        final Session sess = conn.createSession(false, 1);
        final QueueBrowser browser = sess.createBrowser(queue, selector);
        final List messages = Collections.list((Enumeration<Object>)browser.getEnumeration());
        conn.close();
        return messages;
    }
    
    protected List queryMessages(final ActiveMQTopic topic, final String selector) throws Exception {
        return null;
    }
    
    protected Connection createConnection(final URI brokerUrl) throws JMSException {
        final Connection conn = new ActiveMQConnectionFactory(brokerUrl).createConnection();
        conn.start();
        return conn;
    }
    
    public URI getBrokerUrl() {
        return this.brokerUrl;
    }
    
    public void setBrokerUrl(final URI brokerUrl) {
        this.brokerUrl = brokerUrl;
    }
    
    public Destination getDestination() {
        return this.destination;
    }
    
    public void setDestination(final Destination destination) {
        this.destination = destination;
    }
}
