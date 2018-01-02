// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.jmx;

import org.apache.activemq.Service;

public interface ConnectionViewMBean extends Service
{
    @MBeanInfo("Connection is slow.")
    boolean isSlow();
    
    @MBeanInfo("Connection is blocked.")
    boolean isBlocked();
    
    @MBeanInfo("Connection is connected to the broker.")
    boolean isConnected();
    
    @MBeanInfo("Connection is active (both connected and receiving messages).")
    boolean isActive();
    
    @MBeanInfo("Resets the statistics")
    void resetStatistics();
    
    @MBeanInfo("Source address for this connection")
    String getRemoteAddress();
}
