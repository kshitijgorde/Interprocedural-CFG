// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.jmx;

import org.apache.activemq.network.NetworkBridge;

public class NetworkBridgeView implements NetworkBridgeViewMBean
{
    private final NetworkBridge bridge;
    private boolean createByDuplex;
    
    public NetworkBridgeView(final NetworkBridge bridge) {
        this.createByDuplex = false;
        this.bridge = bridge;
    }
    
    @Override
    public void start() throws Exception {
        this.bridge.start();
    }
    
    @Override
    public void stop() throws Exception {
        this.bridge.stop();
    }
    
    @Override
    public String getLocalAddress() {
        return this.bridge.getLocalAddress();
    }
    
    @Override
    public String getRemoteAddress() {
        return this.bridge.getRemoteAddress();
    }
    
    @Override
    public String getRemoteBrokerName() {
        return this.bridge.getRemoteBrokerName();
    }
    
    @Override
    public String getLocalBrokerName() {
        return this.bridge.getLocalBrokerName();
    }
    
    @Override
    public long getEnqueueCounter() {
        return this.bridge.getEnqueueCounter();
    }
    
    @Override
    public long getDequeueCounter() {
        return this.bridge.getDequeueCounter();
    }
    
    @Override
    public boolean isCreatedByDuplex() {
        return this.createByDuplex;
    }
    
    public void setCreateByDuplex(final boolean createByDuplex) {
        this.createByDuplex = createByDuplex;
    }
}
