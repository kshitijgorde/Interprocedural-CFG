// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.service;

import java.util.Iterator;
import org.apache.mina.core.RuntimeIoException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Collections;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import org.apache.mina.core.session.IoSessionConfig;
import java.util.Set;
import java.net.SocketAddress;
import java.util.List;

public abstract class AbstractIoAcceptor extends AbstractIoService implements IoAcceptor
{
    private final List<SocketAddress> defaultLocalAddresses;
    private final Set<SocketAddress> boundAddresses;
    private boolean disconnectOnUnbind;
    private Object bindLock;
    
    protected AbstractIoAcceptor(final IoSessionConfig ioSessionConfig, final Executor executor) {
        super(ioSessionConfig, executor);
        Collections.unmodifiableList((List<?>)(this.defaultLocalAddresses = new ArrayList<SocketAddress>()));
        this.boundAddresses = new HashSet<SocketAddress>();
        this.disconnectOnUnbind = true;
        this.bindLock = new Object();
        this.defaultLocalAddresses.add(null);
    }
    
    public final Set<SocketAddress> getLocalAddresses() {
        final HashSet set = new HashSet();
        synchronized (this.boundAddresses) {
            set.addAll(this.boundAddresses);
        }
        return;
    }
    
    @Override
    public final boolean isCloseOnDeactivation() {
        return this.disconnectOnUnbind;
    }
    
    public final void bind(final SocketAddress socketAddress) throws IOException {
        if (socketAddress == null) {
            throw new IllegalArgumentException("localAddress");
        }
        final ArrayList<SocketAddress> list;
        (list = new ArrayList<SocketAddress>(1)).add(socketAddress);
        this.bind(list);
    }
    
    private void bind(final Iterable<? extends SocketAddress> iterable2) throws IOException {
        if (this.isDisposing()) {
            throw new IllegalStateException("Already disposed.");
        }
        if (iterable == null) {
            throw new IllegalArgumentException("localAddresses");
        }
        final ArrayList<SocketAddress> list = new ArrayList<SocketAddress>();
        for (final SocketAddress socketAddress2 : iterable) {
            final SocketAddress socketAddress = socketAddress2;
            if (socketAddress2 != null && !this.getTransportMetadata().getAddressType().isAssignableFrom(socketAddress2.getClass())) {
                throw new IllegalArgumentException("localAddress type: " + socketAddress2.getClass().getSimpleName() + " (expected: " + this.getTransportMetadata().getAddressType().getSimpleName() + ")");
            }
            list.add(socketAddress);
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("localAddresses is empty.");
        }
        synchronized (this.bindLock) {
            synchronized (this.boundAddresses) {
                if (this.boundAddresses.isEmpty()) {}
            }
            if (this.getHandler() == null) {
                throw new IllegalStateException("handler is not set.");
            }
            try {
                final List<? extends SocketAddress> list2;
                final Set<SocketAddress> bindInternal = this.bindInternal(list2);
                synchronized (this.boundAddresses) {
                    this.boundAddresses.addAll(bindInternal);
                }
            }
            catch (IOException ex) {
                throw ex;
            }
            catch (RuntimeException ex2) {
                throw ex2;
            }
            catch (Throwable t) {
                throw new RuntimeIoException("Failed to bind to: " + this.getLocalAddresses(), t);
            }
        }
        if (iterable2 != 0) {
            this.getListeners().fireServiceActivated();
        }
    }
    
    public final void unbind(final Iterable<? extends SocketAddress> iterable) {
        if (iterable == null) {
            throw new IllegalArgumentException("localAddresses");
        }
        boolean b = false;
        synchronized (this.bindLock) {
            synchronized (this.boundAddresses) {
                if (this.boundAddresses.isEmpty()) {
                    return;
                }
                final ArrayList<SocketAddress> list = new ArrayList<SocketAddress>();
                int n = 0;
                for (final SocketAddress socketAddress : iterable) {
                    ++n;
                    if (socketAddress != null && this.boundAddresses.contains(socketAddress)) {
                        list.add(socketAddress);
                    }
                }
                if (n == 0) {
                    throw new IllegalArgumentException("localAddresses is empty.");
                }
                if (!list.isEmpty()) {
                    try {
                        this.unbind0(list);
                    }
                    catch (RuntimeException ex) {
                        throw ex;
                    }
                    catch (Throwable t) {
                        throw new RuntimeIoException("Failed to unbind from: " + this.getLocalAddresses(), t);
                    }
                    this.boundAddresses.removeAll(list);
                    if (this.boundAddresses.isEmpty()) {
                        b = true;
                    }
                }
            }
        }
        if (b) {
            this.getListeners().fireServiceDeactivated();
        }
    }
    
    protected abstract Set<SocketAddress> bindInternal(final List<? extends SocketAddress> p0) throws Exception;
    
    protected abstract void unbind0(final List<? extends SocketAddress> p0) throws Exception;
    
    @Override
    public String toString() {
        final TransportMetadata transportMetadata = this.getTransportMetadata();
        return '(' + transportMetadata.getProviderName() + ' ' + transportMetadata.getName() + " acceptor: " + (this.isActive() ? ("localAddress(es): " + this.getLocalAddresses() + ", managedSessionCount: " + this.getManagedSessionCount()) : "not bound") + ')';
    }
    
    public static final class AcceptorOperationFuture extends ServiceOperationFuture
    {
        private final List<SocketAddress> localAddresses;
        
        public AcceptorOperationFuture(final List<? extends SocketAddress> list) {
            this.localAddresses = new ArrayList<SocketAddress>(list);
        }
        
        public final List<SocketAddress> getLocalAddresses() {
            return Collections.unmodifiableList((List<? extends SocketAddress>)this.localAddresses);
        }
        
        @Override
        public final String toString() {
            final StringBuilder sb;
            (sb = new StringBuilder()).append("Acceptor operation : ");
            if (this.localAddresses != null) {
                int n = 1;
                for (final SocketAddress socketAddress : this.localAddresses) {
                    if (n != 0) {
                        n = 0;
                    }
                    else {
                        sb.append(", ");
                    }
                    sb.append(socketAddress);
                }
            }
            return sb.toString();
        }
    }
}
