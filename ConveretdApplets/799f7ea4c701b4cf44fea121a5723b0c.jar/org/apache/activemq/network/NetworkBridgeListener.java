// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.network;

public interface NetworkBridgeListener
{
    void bridgeFailed();
    
    void onStart(final NetworkBridge p0);
    
    void onStop(final NetworkBridge p0);
}
