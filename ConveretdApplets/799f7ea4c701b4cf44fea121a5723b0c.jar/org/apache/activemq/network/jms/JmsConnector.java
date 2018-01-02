// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.network.jms;

import org.slf4j.LoggerFactory;
import javax.jms.JMSException;
import javax.naming.NamingException;
import org.apache.activemq.broker.BrokerService;
import javax.jms.Connection;
import javax.jms.Destination;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import org.apache.activemq.util.LRUCache;
import org.apache.activemq.ActiveMQConnectionFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import org.springframework.jndi.JndiTemplate;
import org.slf4j.Logger;
import org.apache.activemq.Service;

public abstract class JmsConnector implements Service
{
    private static int nextId;
    private static final Logger LOG;
    protected JndiTemplate jndiLocalTemplate;
    protected JndiTemplate jndiOutboundTemplate;
    protected JmsMesageConvertor inboundMessageConvertor;
    protected JmsMesageConvertor outboundMessageConvertor;
    protected AtomicBoolean initialized;
    protected AtomicBoolean started;
    protected ActiveMQConnectionFactory embeddedConnectionFactory;
    protected int replyToDestinationCacheSize;
    protected String outboundUsername;
    protected String outboundPassword;
    protected String localUsername;
    protected String localPassword;
    protected String outboundClientId;
    protected String localClientId;
    protected LRUCache replyToBridges;
    private List<DestinationBridge> inboundBridges;
    private List<DestinationBridge> outboundBridges;
    private String name;
    
    public JmsConnector() {
        this.initialized = new AtomicBoolean(false);
        this.started = new AtomicBoolean(false);
        this.replyToDestinationCacheSize = 10000;
        this.replyToBridges = createLRUCache();
        this.inboundBridges = new CopyOnWriteArrayList<DestinationBridge>();
        this.outboundBridges = new CopyOnWriteArrayList<DestinationBridge>();
    }
    
    private static LRUCache createLRUCache() {
        return new LRUCache() {
            private static final long serialVersionUID = -7446792754185879286L;
            
            @Override
            protected boolean removeEldestEntry(final Map.Entry enty) {
                if (this.size() > this.maxCacheSize) {
                    final Iterator iter = this.entrySet().iterator();
                    final Map.Entry lru = iter.next();
                    this.remove(lru.getKey());
                    final DestinationBridge bridge = lru.getValue();
                    try {
                        bridge.stop();
                        JmsConnector.LOG.info("Expired bridge: " + bridge);
                    }
                    catch (Exception e) {
                        JmsConnector.LOG.warn("stopping expired bridge" + bridge + " caused an exception", e);
                    }
                }
                return false;
            }
        };
    }
    
    public boolean init() {
        final boolean result = this.initialized.compareAndSet(false, true);
        if (result) {
            if (this.jndiLocalTemplate == null) {
                this.jndiLocalTemplate = new JndiTemplate();
            }
            if (this.jndiOutboundTemplate == null) {
                this.jndiOutboundTemplate = new JndiTemplate();
            }
            if (this.inboundMessageConvertor == null) {
                this.inboundMessageConvertor = new SimpleJmsMessageConvertor();
            }
            if (this.outboundMessageConvertor == null) {
                this.outboundMessageConvertor = new SimpleJmsMessageConvertor();
            }
            this.replyToBridges.setMaxCacheSize(this.getReplyToDestinationCacheSize());
        }
        return result;
    }
    
    @Override
    public void start() throws Exception {
        this.init();
        if (this.started.compareAndSet(false, true)) {
            for (int i = 0; i < this.inboundBridges.size(); ++i) {
                final DestinationBridge bridge = this.inboundBridges.get(i);
                bridge.start();
            }
            for (int i = 0; i < this.outboundBridges.size(); ++i) {
                final DestinationBridge bridge = this.outboundBridges.get(i);
                bridge.start();
            }
            JmsConnector.LOG.info("JMS Connector " + this.getName() + " Started");
        }
    }
    
    @Override
    public void stop() throws Exception {
        if (this.started.compareAndSet(true, false)) {
            for (int i = 0; i < this.inboundBridges.size(); ++i) {
                final DestinationBridge bridge = this.inboundBridges.get(i);
                bridge.stop();
            }
            for (int i = 0; i < this.outboundBridges.size(); ++i) {
                final DestinationBridge bridge = this.outboundBridges.get(i);
                bridge.stop();
            }
            JmsConnector.LOG.info("JMS Connector " + this.getName() + " Stopped");
        }
    }
    
    public void clearBridges() {
        this.inboundBridges.clear();
        this.outboundBridges.clear();
    }
    
    protected abstract Destination createReplyToBridge(final Destination p0, final Connection p1, final Connection p2);
    
    public void setBrokerService(final BrokerService service) {
        this.embeddedConnectionFactory = new ActiveMQConnectionFactory(service.getVmConnectorURI());
    }
    
    public JndiTemplate getJndiLocalTemplate() {
        return this.jndiLocalTemplate;
    }
    
    public void setJndiLocalTemplate(final JndiTemplate jndiTemplate) {
        this.jndiLocalTemplate = jndiTemplate;
    }
    
    public JndiTemplate getJndiOutboundTemplate() {
        return this.jndiOutboundTemplate;
    }
    
    public void setJndiOutboundTemplate(final JndiTemplate jndiOutboundTemplate) {
        this.jndiOutboundTemplate = jndiOutboundTemplate;
    }
    
    public JmsMesageConvertor getInboundMessageConvertor() {
        return this.inboundMessageConvertor;
    }
    
    public void setInboundMessageConvertor(final JmsMesageConvertor jmsMessageConvertor) {
        this.inboundMessageConvertor = jmsMessageConvertor;
    }
    
    public JmsMesageConvertor getOutboundMessageConvertor() {
        return this.outboundMessageConvertor;
    }
    
    public void setOutboundMessageConvertor(final JmsMesageConvertor outboundMessageConvertor) {
        this.outboundMessageConvertor = outboundMessageConvertor;
    }
    
    public int getReplyToDestinationCacheSize() {
        return this.replyToDestinationCacheSize;
    }
    
    public void setReplyToDestinationCacheSize(final int replyToDestinationCacheSize) {
        this.replyToDestinationCacheSize = replyToDestinationCacheSize;
    }
    
    public String getLocalPassword() {
        return this.localPassword;
    }
    
    public void setLocalPassword(final String localPassword) {
        this.localPassword = localPassword;
    }
    
    public String getLocalUsername() {
        return this.localUsername;
    }
    
    public void setLocalUsername(final String localUsername) {
        this.localUsername = localUsername;
    }
    
    public String getOutboundPassword() {
        return this.outboundPassword;
    }
    
    public void setOutboundPassword(final String outboundPassword) {
        this.outboundPassword = outboundPassword;
    }
    
    public String getOutboundUsername() {
        return this.outboundUsername;
    }
    
    public void setOutboundUsername(final String outboundUsername) {
        this.outboundUsername = outboundUsername;
    }
    
    public String getOutboundClientId() {
        return this.outboundClientId;
    }
    
    public void setOutboundClientId(final String outboundClientId) {
        this.outboundClientId = outboundClientId;
    }
    
    public String getLocalClientId() {
        return this.localClientId;
    }
    
    public void setLocalClientId(final String localClientId) {
        this.localClientId = localClientId;
    }
    
    protected void addInboundBridge(final DestinationBridge bridge) {
        this.inboundBridges.add(bridge);
    }
    
    protected void addOutboundBridge(final DestinationBridge bridge) {
        this.outboundBridges.add(bridge);
    }
    
    protected void removeInboundBridge(final DestinationBridge bridge) {
        this.inboundBridges.remove(bridge);
    }
    
    protected void removeOutboundBridge(final DestinationBridge bridge) {
        this.outboundBridges.remove(bridge);
    }
    
    public String getName() {
        if (this.name == null) {
            this.name = "Connector:" + getNextId();
        }
        return this.name;
    }
    
    private static synchronized int getNextId() {
        return JmsConnector.nextId++;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public abstract void restartProducerConnection() throws NamingException, JMSException;
    
    static {
        LOG = LoggerFactory.getLogger(JmsConnector.class);
    }
}
