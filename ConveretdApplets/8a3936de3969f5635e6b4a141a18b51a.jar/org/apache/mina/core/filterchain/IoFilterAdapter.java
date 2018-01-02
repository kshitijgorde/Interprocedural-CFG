// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.filterchain;

import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class IoFilterAdapter implements IoFilter
{
    @Override
    public void onPreAdd$64777341(final IoFilterChain ioFilterChain) throws Exception {
    }
    
    @Override
    public void onPostRemove$64777341(final IoFilterChain ioFilterChain) throws Exception {
    }
    
    @Override
    public void sessionCreated(final NextFilter nextFilter, final IoSession ioSession) throws Exception {
        nextFilter.sessionCreated(ioSession);
    }
    
    @Override
    public void sessionOpened(final NextFilter nextFilter, final IoSession ioSession) throws Exception {
        nextFilter.sessionOpened(ioSession);
    }
    
    @Override
    public void sessionClosed(final NextFilter nextFilter, final IoSession ioSession) throws Exception {
        nextFilter.sessionClosed(ioSession);
    }
    
    @Override
    public void sessionIdle(final NextFilter nextFilter, final IoSession ioSession, final IdleStatus idleStatus) throws Exception {
        nextFilter.sessionIdle(ioSession, idleStatus);
    }
    
    @Override
    public void exceptionCaught(final NextFilter nextFilter, final IoSession ioSession, final Throwable t) throws Exception {
        nextFilter.exceptionCaught(ioSession, t);
    }
    
    @Override
    public void messageReceived(final NextFilter nextFilter, final IoSession ioSession, final Object o) throws Exception {
        nextFilter.messageReceived(ioSession, o);
    }
    
    @Override
    public void messageSent(final NextFilter nextFilter, final IoSession ioSession, final WriteRequest writeRequest) throws Exception {
        nextFilter.messageSent(ioSession, writeRequest);
    }
    
    @Override
    public void filterWrite(final NextFilter nextFilter, final IoSession ioSession, final WriteRequest writeRequest) throws Exception {
        nextFilter.filterWrite(ioSession, writeRequest);
    }
    
    @Override
    public void filterClose(final NextFilter nextFilter, final IoSession ioSession) throws Exception {
        nextFilter.filterClose(ioSession);
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
