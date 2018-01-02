// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.network;

import org.apache.activemq.transport.Transport;

public final class NetworkBridgeFactory
{
    public static DemandForwardingBridge createBridge(final NetworkBridgeConfiguration config, final Transport localTransport, final Transport remoteTransport) {
        return createBridge(config, localTransport, remoteTransport, null);
    }
    
    public static DemandForwardingBridge createBridge(final NetworkBridgeConfiguration configuration, final Transport localTransport, final Transport remoteTransport, final NetworkBridgeListener listener) {
        DemandForwardingBridge result = null;
        if (configuration.isConduitSubscriptions()) {
            result = new DurableConduitBridge(configuration, localTransport, remoteTransport);
        }
        else {
            result = new DemandForwardingBridge(configuration, localTransport, remoteTransport);
        }
        if (listener != null) {
            result.setNetworkBridgeListener(listener);
        }
        return result;
    }
}
