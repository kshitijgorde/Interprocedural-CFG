// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Connection;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.ConnectionFactory;

public class ActiveMQ extends JmsClient
{
    @Override
    protected ConnectionFactory getConnectionFactory() {
        final ActiveMQConnectionFactory result = new ActiveMQConnectionFactory(this.getURL());
        result.setClientID(ActiveMQ.serverType);
        return result;
    }
    
    protected String getURL() {
        final StringBuilder sb = new StringBuilder();
        sb.append("tcp://");
        sb.append(this.host);
        sb.append(':');
        sb.append(this.port);
        return sb.toString();
    }
    
    protected String getSecureURL() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ssl://");
        sb.append(this.host);
        sb.append(':');
        sb.append(this.sslport);
        return sb.toString();
    }
    
    @Override
    protected String getNamingURL() {
        final StringBuilder sb = new StringBuilder();
        sb.append("tcp://");
        sb.append(this.host);
        sb.append(':');
        sb.append(this.port);
        return sb.toString();
    }
    
    @Override
    protected String getDestinationIdentifier(final String name) {
        if (name.startsWith("topic")) {
            return "dynamicTopics/" + name;
        }
        return "dynamicQueues/" + name;
    }
    
    @Override
    protected String getNamingFactoryClass() {
        return "org.apache.activemq.jndi.ActiveMQInitialContextFactory";
    }
}
