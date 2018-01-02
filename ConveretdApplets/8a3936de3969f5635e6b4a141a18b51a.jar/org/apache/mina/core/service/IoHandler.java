// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.service;

import org.apache.mina.core.session.IoSession;

public interface IoHandler
{
    void sessionClosed(final IoSession p0) throws Exception;
    
    void exceptionCaught(final IoSession p0, final Throwable p1) throws Exception;
    
    void messageReceived(final IoSession p0, final Object p1) throws Exception;
}
