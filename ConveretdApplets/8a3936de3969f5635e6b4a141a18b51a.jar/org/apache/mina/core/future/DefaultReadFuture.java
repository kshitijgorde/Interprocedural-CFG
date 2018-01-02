// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.future;

import org.apache.mina.core.session.IoSession;

public final class DefaultReadFuture extends DefaultIoFuture implements ReadFuture
{
    private static final Object CLOSED;
    
    public DefaultReadFuture(final IoSession ioSession) {
        super(ioSession);
    }
    
    @Override
    public final void setClosed() {
        this.setValue(DefaultReadFuture.CLOSED);
    }
    
    @Override
    public final void setRead(final Object value) {
        if (value == null) {
            throw new IllegalArgumentException("message");
        }
        this.setValue(value);
    }
    
    @Override
    public final void setException(final Throwable t) {
        if (t == null) {
            throw new IllegalArgumentException("exception");
        }
        this.setValue(new Object(t));
    }
    
    static {
        CLOSED = new Object();
    }
}
