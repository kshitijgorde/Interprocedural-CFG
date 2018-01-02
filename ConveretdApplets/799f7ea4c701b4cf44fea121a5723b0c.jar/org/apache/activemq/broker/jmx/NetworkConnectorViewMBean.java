// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.jmx;

import org.apache.activemq.Service;

public interface NetworkConnectorViewMBean extends Service
{
    String getName();
    
    int getNetworkTTL();
    
    int getPrefetchSize();
    
    String getUserName();
    
    boolean isBridgeTempDestinations();
    
    boolean isConduitSubscriptions();
    
    boolean isDecreaseNetworkConsumerPriority();
    
    boolean isDispatchAsync();
    
    boolean isDynamicOnly();
    
    boolean isDuplex();
    
    void setBridgeTempDestinations(final boolean p0);
    
    void setConduitSubscriptions(final boolean p0);
    
    void setDispatchAsync(final boolean p0);
    
    void setDynamicOnly(final boolean p0);
    
    void setNetworkTTL(final int p0);
    
    void setPassword(final String p0);
    
    void setPrefetchSize(final int p0);
    
    void setUserName(final String p0);
    
    String getPassword();
    
    void setDecreaseNetworkConsumerPriority(final boolean p0);
}
