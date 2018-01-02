// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.network;

import org.apache.activemq.Service;

public interface NetworkBridge extends Service
{
    void serviceRemoteException(final Throwable p0);
    
    void serviceLocalException(final Throwable p0);
    
    void setNetworkBridgeListener(final NetworkBridgeListener p0);
    
    String getRemoteAddress();
    
    String getRemoteBrokerName();
    
    String getLocalAddress();
    
    String getLocalBrokerName();
    
    long getEnqueueCounter();
    
    long getDequeueCounter();
}
