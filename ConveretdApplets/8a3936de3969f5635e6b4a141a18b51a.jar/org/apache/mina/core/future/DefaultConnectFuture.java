// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.future;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.session.IoSession;

public class DefaultConnectFuture extends DefaultIoFuture implements ConnectFuture
{
    private static final Object CANCELED;
    
    public static ConnectFuture newFailedFuture(final Throwable exception) {
        final DefaultConnectFuture defaultConnectFuture;
        (defaultConnectFuture = new DefaultConnectFuture()).setException(exception);
        return defaultConnectFuture;
    }
    
    public DefaultConnectFuture() {
        super(null);
    }
    
    @Override
    public final IoSession getSession() {
        final Object value;
        if ((value = this.getValue()) instanceof RuntimeException) {
            throw (RuntimeException)value;
        }
        if (value instanceof Error) {
            throw (Error)value;
        }
        if (value instanceof Throwable) {
            throw (RuntimeIoException)new RuntimeIoException("Failed to get the session.").initCause((Throwable)value);
        }
        if (value instanceof IoSession) {
            return (IoSession)value;
        }
        return null;
    }
    
    @Override
    public final boolean isConnected() {
        return this.getValue() instanceof IoSession;
    }
    
    @Override
    public final boolean isCanceled() {
        return this.getValue() == DefaultConnectFuture.CANCELED;
    }
    
    @Override
    public final void setSession(final IoSession value) {
        if (value == null) {
            throw new IllegalArgumentException("session");
        }
        this.setValue(value);
    }
    
    @Override
    public final void setException(final Throwable value) {
        if (value == null) {
            throw new IllegalArgumentException("exception");
        }
        this.setValue(value);
    }
    
    @Override
    public void cancel() {
        this.setValue(DefaultConnectFuture.CANCELED);
    }
    
    @Override
    public final ConnectFuture addListener(final IoFutureListener<?> ioFutureListener) {
        return (ConnectFuture)super.addListener(ioFutureListener);
    }
    
    static {
        CANCELED = new Object();
    }
}
