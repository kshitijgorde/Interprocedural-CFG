// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.filterchain;

import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public interface IoFilterChain
{
    IoSession getSession();
    
    boolean contains(final IoFilter p0);
    
    void addLast(final String p0, final IoFilter p1);
    
    void clear() throws Exception;
    
    void fireSessionCreated();
    
    void fireSessionOpened();
    
    void fireSessionClosed();
    
    void fireSessionIdle(final IdleStatus p0);
    
    void fireMessageReceived(final Object p0);
    
    void fireMessageSent(final WriteRequest p0);
    
    void fireExceptionCaught(final Throwable p0);
    
    void fireFilterWrite(final WriteRequest p0);
    
    void fireFilterClose();
    
    public interface Entry
    {
        String getName();
        
        IoFilter getFilter();
        
        IoFilter.NextFilter getNextFilter();
    }
}
