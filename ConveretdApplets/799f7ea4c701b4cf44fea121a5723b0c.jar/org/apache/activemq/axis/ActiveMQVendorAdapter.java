// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.axis;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.ConnectionFactory;
import org.apache.axis.transport.jms.JMSURLHelper;
import javax.jms.TopicConnectionFactory;
import javax.jms.QueueConnectionFactory;
import java.util.HashMap;
import org.apache.axis.components.jms.BeanVendorAdapter;

public class ActiveMQVendorAdapter extends BeanVendorAdapter
{
    public static final String BROKER_URL = "brokerURL";
    public static final String DEFAULT_USERNAME = "defaultUser";
    public static final String DEFAULT_PASSWORD = "defaultPassword";
    protected static final String QCF_CLASS;
    protected static final String TCF_CLASS;
    
    public QueueConnectionFactory getQueueConnectionFactory(HashMap properties) throws Exception {
        properties = (HashMap)properties.clone();
        properties.put("transport.jms.ConnectionFactoryClass", ActiveMQVendorAdapter.QCF_CLASS);
        return super.getQueueConnectionFactory(properties);
    }
    
    public TopicConnectionFactory getTopicConnectionFactory(HashMap properties) throws Exception {
        properties = (HashMap)properties.clone();
        properties.put("transport.jms.ConnectionFactoryClass", ActiveMQVendorAdapter.TCF_CLASS);
        return super.getTopicConnectionFactory(properties);
    }
    
    public void addVendorConnectionFactoryProperties(final JMSURLHelper jmsUrl, final HashMap properties) {
        if (jmsUrl.getPropertyValue("brokerURL") != null) {
            properties.put("brokerURL", jmsUrl.getPropertyValue("brokerURL"));
        }
        if (jmsUrl.getPropertyValue("defaultUser") != null) {
            properties.put("defaultUser", jmsUrl.getPropertyValue("defaultUser"));
        }
        if (jmsUrl.getPropertyValue("defaultPassword") != null) {
            properties.put("defaultPassword", jmsUrl.getPropertyValue("defaultPassword"));
        }
    }
    
    public boolean isMatchingConnectionFactory(final ConnectionFactory connectionFactory, final JMSURLHelper jmsURL, final HashMap properties) {
        String brokerURL = null;
        if (connectionFactory instanceof ActiveMQConnectionFactory) {
            final ActiveMQConnectionFactory amqConnectionFactory = (ActiveMQConnectionFactory)connectionFactory;
            brokerURL = amqConnectionFactory.getBrokerURL();
        }
        final String propertyBrokerURL = properties.get("brokerURL");
        return brokerURL != null && brokerURL.equals(propertyBrokerURL);
    }
    
    static {
        QCF_CLASS = ActiveMQConnectionFactory.class.getName();
        TCF_CLASS = ActiveMQVendorAdapter.QCF_CLASS;
    }
}
