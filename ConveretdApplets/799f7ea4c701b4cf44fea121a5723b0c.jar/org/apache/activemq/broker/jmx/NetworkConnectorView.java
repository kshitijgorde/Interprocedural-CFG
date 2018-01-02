// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.jmx;

import org.apache.activemq.network.NetworkConnector;

public class NetworkConnectorView implements NetworkConnectorViewMBean
{
    private final NetworkConnector connector;
    
    public NetworkConnectorView(final NetworkConnector connector) {
        this.connector = connector;
    }
    
    @Override
    public void start() throws Exception {
        this.connector.start();
    }
    
    @Override
    public void stop() throws Exception {
        this.connector.stop();
    }
    
    @Override
    public String getName() {
        return this.connector.getName();
    }
    
    @Override
    public int getNetworkTTL() {
        return this.connector.getNetworkTTL();
    }
    
    @Override
    public int getPrefetchSize() {
        return this.connector.getPrefetchSize();
    }
    
    @Override
    public String getUserName() {
        return this.connector.getUserName();
    }
    
    @Override
    public boolean isBridgeTempDestinations() {
        return this.connector.isBridgeTempDestinations();
    }
    
    @Override
    public boolean isConduitSubscriptions() {
        return this.connector.isConduitSubscriptions();
    }
    
    @Override
    public boolean isDecreaseNetworkConsumerPriority() {
        return this.connector.isDecreaseNetworkConsumerPriority();
    }
    
    @Override
    public boolean isDispatchAsync() {
        return this.connector.isDispatchAsync();
    }
    
    @Override
    public boolean isDynamicOnly() {
        return this.connector.isDynamicOnly();
    }
    
    @Override
    public boolean isDuplex() {
        return this.connector.isDuplex();
    }
    
    @Override
    public void setBridgeTempDestinations(final boolean bridgeTempDestinations) {
        this.connector.setBridgeTempDestinations(bridgeTempDestinations);
    }
    
    @Override
    public void setConduitSubscriptions(final boolean conduitSubscriptions) {
        this.connector.setConduitSubscriptions(conduitSubscriptions);
    }
    
    @Override
    public void setDispatchAsync(final boolean dispatchAsync) {
        this.connector.setDispatchAsync(dispatchAsync);
    }
    
    @Override
    public void setDynamicOnly(final boolean dynamicOnly) {
        this.connector.setDynamicOnly(dynamicOnly);
    }
    
    @Override
    public void setNetworkTTL(final int networkTTL) {
        this.connector.setNetworkTTL(networkTTL);
    }
    
    @Override
    public void setPassword(final String password) {
        this.connector.setPassword(password);
    }
    
    @Override
    public void setPrefetchSize(final int prefetchSize) {
        this.connector.setPrefetchSize(prefetchSize);
    }
    
    @Override
    public void setUserName(final String userName) {
        this.connector.setUserName(userName);
    }
    
    @Override
    public String getPassword() {
        String pw = this.connector.getPassword();
        if (pw != null) {
            pw = pw.replaceAll(".", "*");
        }
        return pw;
    }
    
    @Override
    public void setDecreaseNetworkConsumerPriority(final boolean decreaseNetworkConsumerPriority) {
        this.connector.setDecreaseNetworkConsumerPriority(decreaseNetworkConsumerPriority);
    }
}
