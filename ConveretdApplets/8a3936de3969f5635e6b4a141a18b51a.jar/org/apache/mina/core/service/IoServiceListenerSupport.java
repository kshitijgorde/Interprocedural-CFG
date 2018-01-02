// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.service;

import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.filterchain.IoFilterChain;
import java.util.Iterator;
import org.apache.mina.util.ExceptionMonitor;
import java.util.Map;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.mina.core.session.IoSession;
import java.util.concurrent.ConcurrentMap;
import java.util.List;

public final class IoServiceListenerSupport
{
    private final IoService service;
    private final List<IoServiceListener> listeners;
    private final ConcurrentMap<Long, IoSession> managedSessions;
    private final AtomicBoolean activated;
    private volatile long activationTime;
    private volatile int largestManagedSessionCount;
    private volatile long cumulativeManagedSessionCount;
    
    public IoServiceListenerSupport(final IoService service) {
        this.listeners = new CopyOnWriteArrayList<IoServiceListener>();
        Collections.unmodifiableMap((Map<?, ?>)(this.managedSessions = new ConcurrentHashMap<Long, IoSession>()));
        this.activated = new AtomicBoolean();
        this.largestManagedSessionCount = 0;
        this.cumulativeManagedSessionCount = 0L;
        if (service == null) {
            throw new IllegalArgumentException("service");
        }
        this.service = service;
    }
    
    public final void add(final IoServiceListener ioServiceListener) {
        if (ioServiceListener != null) {
            this.listeners.add(ioServiceListener);
        }
    }
    
    public final long getActivationTime() {
        return this.activationTime;
    }
    
    public final int getManagedSessionCount() {
        return this.managedSessions.size();
    }
    
    public final boolean isActive() {
        return this.activated.get();
    }
    
    public final void fireServiceActivated() {
        if (!this.activated.compareAndSet(false, true)) {
            return;
        }
        this.activationTime = System.currentTimeMillis();
        for (final IoServiceListener ioServiceListener : this.listeners) {
            try {
                ioServiceListener.serviceActivated(this.service);
            }
            catch (Throwable t) {
                ExceptionMonitor.getInstance().exceptionCaught(t);
            }
        }
    }
    
    public final void fireServiceDeactivated() {
        if (!this.activated.compareAndSet(true, false)) {
            return;
        }
        try {
            for (final IoServiceListener ioServiceListener : this.listeners) {
                try {
                    final IoServiceListener ioServiceListener2 = ioServiceListener;
                    final IoService service = this.service;
                    ioServiceListener2.serviceDeactivated$650f010b();
                }
                catch (Throwable t) {
                    ExceptionMonitor.getInstance().exceptionCaught(t);
                }
            }
        }
        finally {
            this.disconnectSessions();
        }
    }
    
    public final void fireSessionCreated(IoSession iterator) {
        boolean empty = false;
        if (iterator.getService() instanceof IoConnector) {
            synchronized (this.managedSessions) {
                empty = this.managedSessions.isEmpty();
            }
        }
        if (this.managedSessions.putIfAbsent(iterator.getId(), iterator) != null) {
            return;
        }
        if (empty) {
            this.fireServiceActivated();
        }
        final IoFilterChain filterChain;
        (filterChain = iterator.getFilterChain()).fireSessionCreated();
        filterChain.fireSessionOpened();
        final int size;
        if ((size = this.managedSessions.size()) > this.largestManagedSessionCount) {
            this.largestManagedSessionCount = size;
        }
        ++this.cumulativeManagedSessionCount;
        iterator = (IoSession)this.listeners.iterator();
        while (((Iterator)iterator).hasNext()) {
            final IoServiceListener ioServiceListener = ((Iterator<IoServiceListener>)iterator).next();
            try {
                ioServiceListener.sessionCreated$5e760533();
            }
            catch (Throwable t) {
                ExceptionMonitor.getInstance().exceptionCaught(t);
            }
        }
    }
    
    public final void fireSessionDestroyed(final IoSession ioSession2) {
        if (this.managedSessions.remove(ioSession.getId()) == null) {
            return;
        }
        ioSession.getFilterChain().fireSessionClosed();
        try {
            for (final IoServiceListener ioServiceListener : this.listeners) {
                try {
                    ioServiceListener.sessionDestroyed$5e760533();
                }
                catch (Throwable t) {
                    ExceptionMonitor.getInstance().exceptionCaught(t);
                }
            }
        }
        finally {
            if (ioSession.getService() instanceof IoConnector) {
                synchronized (this.managedSessions) {
                    this.managedSessions.isEmpty();
                }
                if (ioSession2 != 0) {
                    this.fireServiceDeactivated();
                }
            }
        }
    }
    
    private void disconnectSessions() {
        if (!(this.service instanceof IoAcceptor)) {
            return;
        }
        if (!((IoAcceptor)this.service).isCloseOnDeactivation()) {
            return;
        }
        final Object o = new Object();
        final LockNotifyingListener lockNotifyingListener = new LockNotifyingListener(o);
        final Iterator<IoSession> iterator = this.managedSessions.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().close(true).addListener((IoFutureListener<?>)lockNotifyingListener);
        }
        try {
            synchronized (o) {
                while (!this.managedSessions.isEmpty()) {
                    o.wait(500L);
                }
            }
        }
        catch (InterruptedException ex) {}
    }
    
    static final class LockNotifyingListener implements IoFutureListener<IoFuture>
    {
        private final Object lock;
        
        public LockNotifyingListener(final Object lock) {
            this.lock = lock;
        }
        
        @Override
        public final void operationComplete(final IoFuture ioFuture) {
            synchronized (this.lock) {
                this.lock.notifyAll();
            }
        }
    }
}
