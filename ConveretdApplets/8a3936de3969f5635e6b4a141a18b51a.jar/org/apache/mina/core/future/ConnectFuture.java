// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.future;

import org.apache.mina.core.session.IoSession;

public interface ConnectFuture extends IoFuture
{
    boolean isConnected();
    
    boolean isCanceled();
    
    void setSession(final IoSession p0);
    
    void setException(final Throwable p0);
    
    void cancel();
    
    ConnectFuture addListener(final IoFutureListener<?> p0);
}
