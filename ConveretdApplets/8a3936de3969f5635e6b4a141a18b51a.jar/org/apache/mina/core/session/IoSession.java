// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.session;

import org.apache.mina.core.write.WriteRequest;
import java.net.SocketAddress;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.TransportMetadata;
import org.apache.mina.core.write.WriteRequestQueue;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoService;

public interface IoSession
{
    long getId();
    
    IoService getService();
    
    IoHandler getHandler();
    
    IoSessionConfig getConfig();
    
    IoFilterChain getFilterChain();
    
    WriteRequestQueue getWriteRequestQueue();
    
    TransportMetadata getTransportMetadata();
    
    WriteFuture write(final Object p0);
    
    CloseFuture close(final boolean p0);
    
    Object getAttribute(final Object p0);
    
    Object getAttribute(final Object p0, final Object p1);
    
    Object setAttribute(final Object p0, final Object p1);
    
    Object setAttribute(final Object p0);
    
    Object removeAttribute(final Object p0);
    
    boolean containsAttribute(final Object p0);
    
    boolean isConnected();
    
    CloseFuture getCloseFuture();
    
    SocketAddress getRemoteAddress();
    
    SocketAddress getLocalAddress();
    
    void setCurrentWriteRequest(final WriteRequest p0);
    
    WriteRequest getCurrentWriteRequest();
    
    long getLastIoTime();
    
    long getLastReadTime();
    
    long getLastWriteTime();
    
    long getLastIdleTime(final IdleStatus p0);
}
