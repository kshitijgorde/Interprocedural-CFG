// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.network;

import org.slf4j.LoggerFactory;
import java.util.Collection;
import org.apache.activemq.command.ConsumerId;
import javax.management.MalformedObjectNameException;
import org.apache.activemq.util.JMXSupport;
import java.util.Map;
import java.util.HashMap;
import org.apache.activemq.broker.jmx.NetworkBridgeViewMBean;
import org.apache.activemq.broker.jmx.AnnotatedMBean;
import org.apache.activemq.broker.jmx.NetworkBridgeView;
import org.apache.activemq.transport.TransportFactory;
import org.apache.activemq.transport.Transport;
import java.util.Iterator;
import java.util.HashSet;
import java.net.URISyntaxException;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.activemq.util.ServiceStopper;
import javax.management.ObjectName;
import org.apache.activemq.broker.BrokerService;
import java.util.List;
import org.apache.activemq.command.ActiveMQDestination;
import java.util.Set;
import org.apache.activemq.util.ServiceSupport;
import java.util.concurrent.ConcurrentHashMap;
import java.net.URI;
import org.slf4j.Logger;
import org.apache.activemq.Service;

public abstract class NetworkConnector extends NetworkBridgeConfiguration implements Service
{
    private static final Logger LOG;
    protected URI localURI;
    protected ConnectionFilter connectionFilter;
    protected ConcurrentHashMap<URI, NetworkBridge> bridges;
    protected ServiceSupport serviceSupport;
    private Set<ActiveMQDestination> durableDestinations;
    private List<ActiveMQDestination> excludedDestinations;
    private List<ActiveMQDestination> dynamicallyIncludedDestinations;
    private List<ActiveMQDestination> staticallyIncludedDestinations;
    private BrokerService brokerService;
    private ObjectName objectName;
    
    public NetworkConnector() {
        this.bridges = new ConcurrentHashMap<URI, NetworkBridge>();
        this.serviceSupport = new ServiceSupport() {
            @Override
            protected void doStart() throws Exception {
                NetworkConnector.this.handleStart();
            }
            
            @Override
            protected void doStop(final ServiceStopper stopper) throws Exception {
                NetworkConnector.this.handleStop(stopper);
            }
        };
        this.excludedDestinations = new CopyOnWriteArrayList<ActiveMQDestination>();
        this.dynamicallyIncludedDestinations = new CopyOnWriteArrayList<ActiveMQDestination>();
        this.staticallyIncludedDestinations = new CopyOnWriteArrayList<ActiveMQDestination>();
    }
    
    public NetworkConnector(final URI localURI) {
        this.bridges = new ConcurrentHashMap<URI, NetworkBridge>();
        this.serviceSupport = new ServiceSupport() {
            @Override
            protected void doStart() throws Exception {
                NetworkConnector.this.handleStart();
            }
            
            @Override
            protected void doStop(final ServiceStopper stopper) throws Exception {
                NetworkConnector.this.handleStop(stopper);
            }
        };
        this.excludedDestinations = new CopyOnWriteArrayList<ActiveMQDestination>();
        this.dynamicallyIncludedDestinations = new CopyOnWriteArrayList<ActiveMQDestination>();
        this.staticallyIncludedDestinations = new CopyOnWriteArrayList<ActiveMQDestination>();
        this.localURI = localURI;
    }
    
    public URI getLocalUri() throws URISyntaxException {
        return this.localURI;
    }
    
    public void setLocalUri(final URI localURI) {
        this.localURI = localURI;
    }
    
    public Set getDurableDestinations() {
        return this.durableDestinations;
    }
    
    public void setDurableDestinations(final Set<ActiveMQDestination> durableDestinations) {
        this.durableDestinations = durableDestinations;
    }
    
    @Override
    public List<ActiveMQDestination> getExcludedDestinations() {
        return this.excludedDestinations;
    }
    
    @Override
    public void setExcludedDestinations(final List<ActiveMQDestination> excludedDestinations) {
        this.excludedDestinations = excludedDestinations;
    }
    
    public void addExcludedDestination(final ActiveMQDestination destiantion) {
        this.excludedDestinations.add(destiantion);
    }
    
    @Override
    public List<ActiveMQDestination> getStaticallyIncludedDestinations() {
        return this.staticallyIncludedDestinations;
    }
    
    @Override
    public void setStaticallyIncludedDestinations(final List<ActiveMQDestination> staticallyIncludedDestinations) {
        this.staticallyIncludedDestinations = staticallyIncludedDestinations;
    }
    
    public void addStaticallyIncludedDestination(final ActiveMQDestination destiantion) {
        this.staticallyIncludedDestinations.add(destiantion);
    }
    
    @Override
    public List<ActiveMQDestination> getDynamicallyIncludedDestinations() {
        return this.dynamicallyIncludedDestinations;
    }
    
    @Override
    public void setDynamicallyIncludedDestinations(final List<ActiveMQDestination> dynamicallyIncludedDestinations) {
        this.dynamicallyIncludedDestinations = dynamicallyIncludedDestinations;
    }
    
    public void addDynamicallyIncludedDestination(final ActiveMQDestination destiantion) {
        this.dynamicallyIncludedDestinations.add(destiantion);
    }
    
    public ConnectionFilter getConnectionFilter() {
        return this.connectionFilter;
    }
    
    public void setConnectionFilter(final ConnectionFilter connectionFilter) {
        this.connectionFilter = connectionFilter;
    }
    
    protected NetworkBridge configureBridge(final DemandForwardingBridgeSupport result) {
        List<ActiveMQDestination> destsList = this.getDynamicallyIncludedDestinations();
        ActiveMQDestination[] dests = destsList.toArray(new ActiveMQDestination[destsList.size()]);
        result.setDynamicallyIncludedDestinations(dests);
        destsList = this.getExcludedDestinations();
        dests = destsList.toArray(new ActiveMQDestination[destsList.size()]);
        result.setExcludedDestinations(dests);
        destsList = this.getStaticallyIncludedDestinations();
        dests = destsList.toArray(new ActiveMQDestination[destsList.size()]);
        result.setStaticallyIncludedDestinations(dests);
        if (this.durableDestinations != null) {
            final HashSet<ActiveMQDestination> topics = new HashSet<ActiveMQDestination>();
            for (final ActiveMQDestination d : this.durableDestinations) {
                if (d.isTopic()) {
                    topics.add(d);
                }
            }
            ActiveMQDestination[] dest = new ActiveMQDestination[topics.size()];
            dest = topics.toArray(dest);
            result.setDurableDestinations(dest);
        }
        return result;
    }
    
    protected Transport createLocalTransport() throws Exception {
        return TransportFactory.connect(this.localURI);
    }
    
    @Override
    public void start() throws Exception {
        this.serviceSupport.start();
    }
    
    @Override
    public void stop() throws Exception {
        this.serviceSupport.stop();
    }
    
    protected void handleStart() throws Exception {
        if (this.localURI == null) {
            throw new IllegalStateException("You must configure the 'localURI' property");
        }
        NetworkConnector.LOG.info("Network Connector " + this + " Started");
    }
    
    protected void handleStop(final ServiceStopper stopper) throws Exception {
        NetworkConnector.LOG.info("Network Connector " + this + " Stopped");
    }
    
    public ObjectName getObjectName() {
        return this.objectName;
    }
    
    public void setObjectName(final ObjectName objectName) {
        this.objectName = objectName;
    }
    
    public BrokerService getBrokerService() {
        return this.brokerService;
    }
    
    public void setBrokerService(final BrokerService brokerService) {
        this.brokerService = brokerService;
    }
    
    protected void registerNetworkBridgeMBean(final NetworkBridge bridge) {
        if (!this.getBrokerService().isUseJmx()) {
            return;
        }
        final NetworkBridgeViewMBean view = new NetworkBridgeView(bridge);
        try {
            final ObjectName objectName = this.createNetworkBridgeObjectName(bridge);
            AnnotatedMBean.registerMBean(this.getBrokerService().getManagementContext(), view, objectName);
        }
        catch (Throwable e) {
            NetworkConnector.LOG.debug("Network bridge could not be registered in JMX: " + e.getMessage(), e);
        }
    }
    
    protected void unregisterNetworkBridgeMBean(final NetworkBridge bridge) {
        if (!this.getBrokerService().isUseJmx()) {
            return;
        }
        try {
            final ObjectName objectName = this.createNetworkBridgeObjectName(bridge);
            this.getBrokerService().getManagementContext().unregisterMBean(objectName);
        }
        catch (Throwable e) {
            NetworkConnector.LOG.debug("Network bridge could not be unregistered in JMX: " + e.getMessage(), e);
        }
    }
    
    protected ObjectName createNetworkBridgeObjectName(final NetworkBridge bridge) throws MalformedObjectNameException {
        final ObjectName connectorName = this.getObjectName();
        final Map<String, String> map = new HashMap<String, String>(connectorName.getKeyPropertyList());
        return new ObjectName(connectorName.getDomain() + ":" + "BrokerName=" + JMXSupport.encodeObjectNamePart(map.get("BrokerName")) + "," + "Type=NetworkBridge," + "NetworkConnectorName=" + JMXSupport.encodeObjectNamePart(map.get("NetworkConnectorName")) + "," + "Name=" + JMXSupport.encodeObjectNamePart(JMXSupport.encodeObjectNamePart(bridge.getRemoteAddress())));
    }
    
    public boolean removeDemandSubscription(final ConsumerId consumerId) {
        boolean removeSucceeded = false;
        for (final NetworkBridge bridge : this.bridges.values()) {
            if (bridge instanceof DemandForwardingBridgeSupport) {
                final DemandForwardingBridgeSupport demandBridge = (DemandForwardingBridgeSupport)bridge;
                if (demandBridge.removeDemandSubscriptionByLocalId(consumerId)) {
                    removeSucceeded = true;
                    break;
                }
                continue;
            }
        }
        return removeSucceeded;
    }
    
    public Collection<NetworkBridge> activeBridges() {
        return this.bridges.values();
    }
    
    static {
        LOG = LoggerFactory.getLogger(NetworkConnector.class);
    }
}
