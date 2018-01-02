// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.service;

import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.future.IoFuture;
import com.masystem.beergame.debug.Log;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.future.ConnectFuture;
import java.net.SocketAddress;
import java.util.concurrent.Executor;
import org.apache.mina.core.session.IoSessionConfig;

public abstract class AbstractIoConnector extends AbstractIoService implements IoConnector
{
    private long connectTimeoutInMillis;
    
    protected AbstractIoConnector(final IoSessionConfig ioSessionConfig, final Executor executor) {
        super(ioSessionConfig, executor);
        this.connectTimeoutInMillis = 60000L;
    }
    
    public final long getConnectTimeoutMillis() {
        return this.connectTimeoutInMillis;
    }
    
    public final ConnectFuture connect(final SocketAddress socketAddress) {
        if (this.isDisposing()) {
            throw new IllegalStateException("The connector has been disposed.");
        }
        if (socketAddress == null) {
            throw new IllegalArgumentException("remoteAddress");
        }
        if (!this.getTransportMetadata().getAddressType().isAssignableFrom(socketAddress.getClass())) {
            throw new IllegalArgumentException("remoteAddress type: " + socketAddress.getClass() + " (expected: " + this.getTransportMetadata().getAddressType() + ")");
        }
        if (this.getHandler() == null) {
            if (!this.getSessionConfig().isUseReadOperation()) {
                throw new IllegalStateException("handler is not set.");
            }
            this.setHandler(new IoHandler() {
                @Override
                public final void exceptionCaught(final IoSession ioSession, final Throwable t) throws Exception {
                }
                
                @Override
                public final void messageReceived(final IoSession ioSession, final Object o) throws Exception {
                }
                
                @Override
                public final void sessionClosed(final IoSession ioSession) throws Exception {
                }
            });
        }
        return this.connect0$181451ab(socketAddress, null, null);
    }
    
    protected abstract ConnectFuture connect0$181451ab(final SocketAddress p0, final SocketAddress p1, final Log.Listener<? extends ConnectFuture> p2);
    
    @Override
    protected final void finishSessionInitialization0(final IoSession ioSession, final IoFuture ioFuture) {
        ioFuture.addListener(new IoFutureListener<ConnectFuture>(this) {});
    }
    
    @Override
    public String toString() {
        final TransportMetadata transportMetadata = this.getTransportMetadata();
        return '(' + transportMetadata.getProviderName() + ' ' + transportMetadata.getName() + " connector: " + "managedSessionCount: " + this.getManagedSessionCount() + ')';
    }
}
