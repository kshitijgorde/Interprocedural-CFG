// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.jmx;

import org.apache.activemq.Service;

public interface NetworkBridgeViewMBean extends Service
{
    String getLocalAddress();
    
    String getRemoteAddress();
    
    String getRemoteBrokerName();
    
    String getLocalBrokerName();
    
    long getEnqueueCounter();
    
    long getDequeueCounter();
    
    boolean isCreatedByDuplex();
}
