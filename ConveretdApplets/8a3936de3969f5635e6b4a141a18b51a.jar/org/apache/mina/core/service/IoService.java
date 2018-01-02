// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.service;

import org.apache.mina.core.session.IoSessionDataStructureFactory;
import org.apache.mina.core.filterchain.IoFilterChainBuilder;
import org.apache.mina.core.session.IoSessionConfig;

public interface IoService
{
    TransportMetadata getTransportMetadata();
    
    IoHandler getHandler();
    
    IoSessionConfig getSessionConfig();
    
    IoFilterChainBuilder getFilterChainBuilder();
    
    IoSessionDataStructureFactory getSessionDataStructureFactory();
}
