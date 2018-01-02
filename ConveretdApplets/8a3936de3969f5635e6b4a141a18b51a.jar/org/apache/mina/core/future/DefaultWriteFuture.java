// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.future;

import org.apache.mina.core.session.IoSession;

public final class DefaultWriteFuture extends DefaultIoFuture implements WriteFuture
{
    public static WriteFuture newNotWrittenFuture(final IoSession ioSession, final Throwable exception) {
        final DefaultWriteFuture defaultWriteFuture;
        (defaultWriteFuture = new DefaultWriteFuture(ioSession)).setException(exception);
        return defaultWriteFuture;
    }
    
    public DefaultWriteFuture(final IoSession ioSession) {
        super(ioSession);
    }
    
    @Override
    public final void setWritten() {
        this.setValue(Boolean.TRUE);
    }
    
    @Override
    public final void setException(final Throwable value) {
        if (value == null) {
            throw new IllegalArgumentException("exception");
        }
        this.setValue(value);
    }
    
    @Override
    public final WriteFuture addListener(final IoFutureListener<?> ioFutureListener) {
        return (WriteFuture)super.addListener(ioFutureListener);
    }
}
