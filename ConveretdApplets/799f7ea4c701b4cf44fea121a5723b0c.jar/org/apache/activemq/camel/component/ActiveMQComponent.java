// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.camel.component;

import java.util.Iterator;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.CamelContext;
import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.Service;
import org.springframework.jms.connection.SingleConnectionFactory;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.camel.component.jms.JmsComponent;

public class ActiveMQComponent extends JmsComponent
{
    private final CopyOnWriteArrayList<SingleConnectionFactory> singleConnectionFactoryList;
    private final CopyOnWriteArrayList<Service> pooledConnectionFactoryServiceList;
    private boolean exposeAllQueues;
    private CamelEndpointLoader endpointLoader;
    
    public static ActiveMQComponent activeMQComponent() {
        return new ActiveMQComponent();
    }
    
    public static ActiveMQComponent activeMQComponent(final String brokerURL) {
        final ActiveMQComponent answer = new ActiveMQComponent();
        if (answer.getConfiguration() instanceof ActiveMQConfiguration) {
            ((ActiveMQConfiguration)answer.getConfiguration()).setBrokerURL(brokerURL);
        }
        answer.setConnectionFactory((ConnectionFactory)new ActiveMQConnectionFactory(brokerURL));
        return answer;
    }
    
    public ActiveMQComponent() {
        this.singleConnectionFactoryList = new CopyOnWriteArrayList<SingleConnectionFactory>();
        this.pooledConnectionFactoryServiceList = new CopyOnWriteArrayList<Service>();
    }
    
    public ActiveMQComponent(final CamelContext context) {
        super(context);
        this.singleConnectionFactoryList = new CopyOnWriteArrayList<SingleConnectionFactory>();
        this.pooledConnectionFactoryServiceList = new CopyOnWriteArrayList<Service>();
    }
    
    public ActiveMQComponent(final ActiveMQConfiguration configuration) {
        super((JmsConfiguration)configuration);
        this.singleConnectionFactoryList = new CopyOnWriteArrayList<SingleConnectionFactory>();
        this.pooledConnectionFactoryServiceList = new CopyOnWriteArrayList<Service>();
    }
    
    public void setBrokerURL(final String brokerURL) {
        if (this.getConfiguration() instanceof ActiveMQConfiguration) {
            ((ActiveMQConfiguration)this.getConfiguration()).setBrokerURL(brokerURL);
        }
    }
    
    public void setUserName(final String userName) {
        if (this.getConfiguration() instanceof ActiveMQConfiguration) {
            ((ActiveMQConfiguration)this.getConfiguration()).setUserName(userName);
        }
    }
    
    public void setPassword(final String password) {
        if (this.getConfiguration() instanceof ActiveMQConfiguration) {
            ((ActiveMQConfiguration)this.getConfiguration()).setPassword(password);
        }
    }
    
    public boolean isExposeAllQueues() {
        return this.exposeAllQueues;
    }
    
    public void setExposeAllQueues(final boolean exposeAllQueues) {
        this.exposeAllQueues = exposeAllQueues;
    }
    
    public void setUsePooledConnection(final boolean usePooledConnection) {
        if (this.getConfiguration() instanceof ActiveMQConfiguration) {
            ((ActiveMQConfiguration)this.getConfiguration()).setUsePooledConnection(usePooledConnection);
        }
    }
    
    public void setUseSingleConnection(final boolean useSingleConnection) {
        if (this.getConfiguration() instanceof ActiveMQConfiguration) {
            ((ActiveMQConfiguration)this.getConfiguration()).setUseSingleConnection(useSingleConnection);
        }
    }
    
    protected void addPooledConnectionFactoryService(final Service pooledConnectionFactoryService) {
        this.pooledConnectionFactoryServiceList.add(pooledConnectionFactoryService);
    }
    
    protected void addSingleConnectionFactory(final SingleConnectionFactory singleConnectionFactory) {
        this.singleConnectionFactoryList.add(singleConnectionFactory);
    }
    
    protected void doStart() throws Exception {
        super.doStart();
        if (this.isExposeAllQueues()) {
            (this.endpointLoader = new CamelEndpointLoader(this.getCamelContext())).afterPropertiesSet();
        }
    }
    
    protected void doStop() throws Exception {
        if (this.endpointLoader != null) {
            this.endpointLoader.destroy();
            this.endpointLoader = null;
        }
        for (final Service s : this.pooledConnectionFactoryServiceList) {
            s.stop();
        }
        this.pooledConnectionFactoryServiceList.clear();
        for (final SingleConnectionFactory s2 : this.singleConnectionFactoryList) {
            s2.destroy();
        }
        this.singleConnectionFactoryList.clear();
        super.doStop();
    }
    
    public void setConfiguration(final JmsConfiguration configuration) {
        if (configuration instanceof ActiveMQConfiguration) {
            ((ActiveMQConfiguration)configuration).setActiveMQComponent(this);
        }
        super.setConfiguration(configuration);
    }
    
    protected JmsConfiguration createConfiguration() {
        final ActiveMQConfiguration answer = new ActiveMQConfiguration();
        answer.setActiveMQComponent(this);
        return answer;
    }
}
