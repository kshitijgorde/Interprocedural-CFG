// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.write;

import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.IoSession;
import java.net.SocketAddress;
import org.apache.mina.core.future.WriteFuture;

public class DefaultWriteRequest implements WriteRequest
{
    private static final WriteFuture UNUSED_FUTURE;
    private final Object message;
    private final WriteFuture future;
    private final SocketAddress destination;
    
    public DefaultWriteRequest(final Object o) {
        this(o, null, null);
    }
    
    public DefaultWriteRequest(final Object message, WriteFuture unused_FUTURE, final SocketAddress destination) {
        if (message == null) {
            throw new IllegalArgumentException("message");
        }
        if (unused_FUTURE == null) {
            unused_FUTURE = DefaultWriteRequest.UNUSED_FUTURE;
        }
        this.message = message;
        this.future = unused_FUTURE;
        this.destination = destination;
    }
    
    @Override
    public final WriteFuture getFuture() {
        return this.future;
    }
    
    @Override
    public final Object getMessage() {
        return this.message;
    }
    
    @Override
    public final WriteRequest getOriginalRequest() {
        return this;
    }
    
    @Override
    public final SocketAddress getDestination() {
        return this.destination;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb;
        (sb = new StringBuilder()).append("WriteRequest: ");
        if (this.message.getClass().getName().equals(Object.class.getName())) {
            sb.append("CLOSE_REQUEST");
        }
        else if (this.destination == null) {
            sb.append(this.message);
        }
        else {
            sb.append(this.message);
            sb.append(" => ");
            sb.append(this.destination);
        }
        return sb.toString();
    }
    
    @Override
    public boolean isEncoded() {
        return false;
    }
    
    static {
        UNUSED_FUTURE = new WriteFuture() {
            @Override
            public final void setWritten() {
            }
            
            @Override
            public final IoSession getSession() {
                return null;
            }
            
            @Override
            public final WriteFuture addListener(final IoFutureListener<?> ioFutureListener) {
                throw new IllegalStateException("You can't add a listener to a dummy future.");
            }
            
            @Override
            public final void setException(final Throwable t) {
            }
        };
    }
}
