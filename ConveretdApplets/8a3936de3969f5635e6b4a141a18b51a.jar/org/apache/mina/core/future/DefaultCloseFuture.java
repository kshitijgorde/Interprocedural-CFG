// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.future;

import org.apache.mina.core.session.IoSession;

public final class DefaultCloseFuture extends DefaultIoFuture implements CloseFuture
{
    public DefaultCloseFuture(final IoSession ioSession) {
        super(ioSession);
    }
    
    @Override
    public final boolean isClosed() {
        return this.isDone() && (boolean)this.getValue();
    }
    
    @Override
    public final void setClosed() {
        this.setValue(Boolean.TRUE);
    }
    
    @Override
    public final CloseFuture awaitUninterruptibly() {
        return (CloseFuture)super.awaitUninterruptibly();
    }
    
    @Override
    public final CloseFuture addListener(final IoFutureListener<?> ioFutureListener) {
        return (CloseFuture)super.addListener(ioFutureListener);
    }
}
