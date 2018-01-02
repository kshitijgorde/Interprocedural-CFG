// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.future;

public interface CloseFuture extends IoFuture
{
    boolean isClosed();
    
    void setClosed();
    
    CloseFuture awaitUninterruptibly();
    
    CloseFuture addListener(final IoFutureListener<?> p0);
}
