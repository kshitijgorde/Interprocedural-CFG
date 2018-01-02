// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.future;

public interface ReadFuture extends IoFuture
{
    void setRead(final Object p0);
    
    void setClosed();
    
    void setException(final Throwable p0);
}
