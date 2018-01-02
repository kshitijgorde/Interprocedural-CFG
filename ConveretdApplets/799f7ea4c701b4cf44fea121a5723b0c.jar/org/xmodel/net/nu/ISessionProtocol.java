// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net.nu;

public interface ISessionProtocol
{
    void sendSessionBeginRequest(final short p0);
    
    void sendSessionBeginResponse(final int p0);
    
    void sendSessionEndRequest(final int p0);
}
