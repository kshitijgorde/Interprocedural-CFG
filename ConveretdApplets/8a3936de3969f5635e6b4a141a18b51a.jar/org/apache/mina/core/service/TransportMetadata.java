// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.service;

import org.apache.mina.core.session.IoSessionConfig;
import java.net.SocketAddress;

public interface TransportMetadata
{
    String getProviderName();
    
    String getName();
    
    boolean isConnectionless();
    
    boolean hasFragmentation();
    
    Class<? extends SocketAddress> getAddressType();
    
    Class<? extends IoSessionConfig> getSessionConfigType();
}
