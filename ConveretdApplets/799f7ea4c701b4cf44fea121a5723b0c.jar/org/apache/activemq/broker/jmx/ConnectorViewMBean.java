// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.jmx;

import org.apache.activemq.Service;

public interface ConnectorViewMBean extends Service
{
    @MBeanInfo("Resets the statistics")
    void resetStatistics();
    
    @MBeanInfo("Enables statistics gathering")
    void enableStatistics();
    
    @MBeanInfo("Disables statistics gathering")
    void disableStatistics();
    
    @MBeanInfo("Statistics gathering enabled")
    boolean isStatisticsEnabled();
}
