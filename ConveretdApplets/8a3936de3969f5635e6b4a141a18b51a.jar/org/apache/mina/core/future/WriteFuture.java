// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.future;

public interface WriteFuture extends IoFuture
{
    void setWritten();
    
    void setException(final Throwable p0);
    
    WriteFuture addListener(final IoFutureListener<?> p0);
}
