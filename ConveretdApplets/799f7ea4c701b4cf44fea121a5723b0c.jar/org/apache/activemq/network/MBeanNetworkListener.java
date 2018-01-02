// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.network;

import org.slf4j.LoggerFactory;
import javax.management.MalformedObjectNameException;
import org.apache.activemq.util.JMXSupport;
import java.util.Map;
import java.util.HashMap;
import org.apache.activemq.broker.jmx.NetworkBridgeViewMBean;
import org.apache.activemq.broker.jmx.AnnotatedMBean;
import org.apache.activemq.broker.jmx.NetworkBridgeView;
import javax.management.ObjectName;
import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;

public class MBeanNetworkListener implements NetworkBridgeListener
{
    private static final Logger LOG;
    BrokerService brokerService;
    ObjectName connectorName;
    boolean createdByDuplex;
    
    public MBeanNetworkListener(final BrokerService brokerService, final ObjectName connectorName) {
        this.createdByDuplex = false;
        this.brokerService = brokerService;
        this.connectorName = connectorName;
    }
    
    @Override
    public void bridgeFailed() {
    }
    
    @Override
    public void onStart(final NetworkBridge bridge) {
        if (!this.brokerService.isUseJmx()) {
            return;
        }
        final NetworkBridgeViewMBean view = new NetworkBridgeView(bridge);
        ((NetworkBridgeView)view).setCreateByDuplex(this.createdByDuplex);
        try {
            final ObjectName objectName = this.createNetworkBridgeObjectName(bridge);
            AnnotatedMBean.registerMBean(this.brokerService.getManagementContext(), view, objectName);
        }
        catch (Throwable e) {
            MBeanNetworkListener.LOG.debug("Network bridge could not be registered in JMX: " + e.getMessage(), e);
        }
    }
    
    @Override
    public void onStop(final NetworkBridge bridge) {
        if (!this.brokerService.isUseJmx()) {
            return;
        }
        try {
            final ObjectName objectName = this.createNetworkBridgeObjectName(bridge);
            this.brokerService.getManagementContext().unregisterMBean(objectName);
        }
        catch (Throwable e) {
            MBeanNetworkListener.LOG.debug("Network bridge could not be unregistered in JMX: " + e.getMessage(), e);
        }
    }
    
    protected ObjectName createNetworkBridgeObjectName(final NetworkBridge bridge) throws MalformedObjectNameException {
        final Map<String, String> map = new HashMap<String, String>(this.connectorName.getKeyPropertyList());
        return new ObjectName(this.connectorName.getDomain() + ":" + "BrokerName=" + JMXSupport.encodeObjectNamePart(map.get("BrokerName")) + "," + "Type=NetworkBridge," + "NetworkConnectorName=" + JMXSupport.encodeObjectNamePart(map.get("NetworkConnectorName")) + "," + "Name=" + JMXSupport.encodeObjectNamePart(JMXSupport.encodeObjectNamePart(bridge.getRemoteAddress())));
    }
    
    public void setCreatedByDuplex(final boolean createdByDuplex) {
        this.createdByDuplex = createdByDuplex;
    }
    
    static {
        LOG = LoggerFactory.getLogger(MBeanNetworkListener.class);
    }
}
