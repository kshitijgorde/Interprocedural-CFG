// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.service;

import org.apache.mina.core.future.DefaultIoFuture;
import org.slf4j.LoggerFactory;
import org.apache.mina.core.filterchain.DefaultIoFilterChain;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSessionInitializationException;
import org.apache.mina.core.session.AbstractIoSession;
import com.masystem.beergame.debug.Log;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.util.NamePreservingRunnable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.mina.util.ExceptionMonitor;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IoSessionDataStructureFactory;
import org.apache.mina.core.filterchain.IoFilterChainBuilder;
import org.apache.mina.core.session.IoSessionConfig;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;

public abstract class AbstractIoService implements IoService
{
    private static final Logger LOGGER;
    private static final AtomicInteger id;
    private final String threadName;
    private final Executor executor;
    private final boolean createdExecutor;
    private IoHandler handler;
    private final IoSessionConfig sessionConfig;
    private final IoServiceListener serviceActivationListener;
    private IoFilterChainBuilder filterChainBuilder;
    private IoSessionDataStructureFactory sessionDataStructureFactory;
    private final IoServiceListenerSupport listeners;
    protected final Object disposalLock;
    private volatile boolean disposing;
    private volatile boolean disposed;
    private IoServiceStatistics stats;
    
    protected AbstractIoService(final IoSessionConfig sessionConfig, final Executor executor) {
        this.serviceActivationListener = new IoServiceListener(this);
        this.filterChainBuilder = new DefaultIoFilterChainBuilder();
        this.sessionDataStructureFactory = new IoSessionDataStructureFactory();
        this.disposalLock = new Object();
        this.stats = new IoServiceStatistics(this);
        if (sessionConfig == null) {
            throw new IllegalArgumentException("sessionConfig");
        }
        if (this.getTransportMetadata() == null) {
            throw new IllegalArgumentException("TransportMetadata");
        }
        if (!this.getTransportMetadata().getSessionConfigType().isAssignableFrom(sessionConfig.getClass())) {
            throw new IllegalArgumentException("sessionConfig type: " + sessionConfig.getClass() + " (expected: " + this.getTransportMetadata().getSessionConfigType() + ")");
        }
        (this.listeners = new IoServiceListenerSupport(this)).add(this.serviceActivationListener);
        this.sessionConfig = sessionConfig;
        ExceptionMonitor.getInstance();
        if (executor == null) {
            this.executor = Executors.newCachedThreadPool();
            this.createdExecutor = true;
        }
        else {
            this.executor = executor;
            this.createdExecutor = false;
        }
        this.threadName = this.getClass().getSimpleName() + '-' + AbstractIoService.id.incrementAndGet();
    }
    
    @Override
    public final IoFilterChainBuilder getFilterChainBuilder() {
        return this.filterChainBuilder;
    }
    
    public final DefaultIoFilterChainBuilder getFilterChain() {
        if (this.filterChainBuilder instanceof DefaultIoFilterChainBuilder) {
            return (DefaultIoFilterChainBuilder)this.filterChainBuilder;
        }
        throw new IllegalStateException("Current filter chain builder is not a DefaultIoFilterChainBuilder.");
    }
    
    public final boolean isActive() {
        return this.listeners.isActive();
    }
    
    public final boolean isDisposing() {
        return this.disposing;
    }
    
    public final void dispose() {
        if (!this.disposed) {
            synchronized (this.disposalLock) {
                if (!this.disposing) {
                    this.disposing = true;
                    try {
                        this.dispose0();
                    }
                    catch (Exception ex) {
                        ExceptionMonitor.getInstance().exceptionCaught(ex);
                    }
                }
            }
            final AbstractIoService abstractIoService;
            if (abstractIoService.createdExecutor) {
                ((ExecutorService)abstractIoService.executor).shutdownNow();
            }
            abstractIoService.disposed = true;
        }
    }
    
    protected abstract void dispose0() throws Exception;
    
    public final int getManagedSessionCount() {
        return this.listeners.getManagedSessionCount();
    }
    
    @Override
    public final IoHandler getHandler() {
        return this.handler;
    }
    
    public final void setHandler(final IoHandler handler) {
        if (handler == null) {
            throw new IllegalArgumentException("handler cannot be null");
        }
        if (this.listeners.isActive()) {
            throw new IllegalStateException("handler cannot be set while the service is active.");
        }
        this.handler = handler;
    }
    
    @Override
    public IoSessionConfig getSessionConfig() {
        return this.sessionConfig;
    }
    
    @Override
    public final IoSessionDataStructureFactory getSessionDataStructureFactory() {
        return this.sessionDataStructureFactory;
    }
    
    public final IoServiceStatistics getStatistics() {
        return this.stats;
    }
    
    public final long getActivationTime() {
        return this.listeners.getActivationTime();
    }
    
    public final IoServiceListenerSupport getListeners() {
        return this.listeners;
    }
    
    protected final void executeWorker(final Runnable runnable) {
        this.executor.execute(new NamePreservingRunnable(runnable, this.threadName));
    }
    
    protected final void initSession$1746fb3d(final IoSession ioSession, final IoFuture ioFuture, final Log.Listener listener) {
        if (this.stats.getLastReadTime() == 0L) {
            this.stats.setLastReadTime(this.listeners.getActivationTime());
        }
        if (this.stats.getLastWriteTime() == 0L) {
            this.stats.setLastWriteTime(this.listeners.getActivationTime());
        }
        try {
            ((AbstractIoSession)ioSession).setAttributeMap(ioSession.getService().getSessionDataStructureFactory().getAttributeMap$c34fc7f());
        }
        catch (IoSessionInitializationException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new IoSessionInitializationException("Failed to initialize an attributeMap.", ex2);
        }
        try {
            ((AbstractIoSession)ioSession).setWriteRequestQueue(ioSession.getService().getSessionDataStructureFactory().getWriteRequestQueue$709e179());
        }
        catch (IoSessionInitializationException ex3) {
            throw ex3;
        }
        catch (Exception ex4) {
            throw new IoSessionInitializationException("Failed to initialize a writeRequestQueue.", ex4);
        }
        if (ioFuture != null && ioFuture instanceof ConnectFuture) {
            ioSession.setAttribute(DefaultIoFilterChain.SESSION_CREATED_FUTURE, ioFuture);
        }
        this.finishSessionInitialization0(ioSession, ioFuture);
    }
    
    protected void finishSessionInitialization0(final IoSession ioSession, final IoFuture ioFuture) {
    }
    
    static {
        LOGGER = LoggerFactory.getLogger$4ecaad6a();
        id = new AtomicInteger();
    }
    
    public static class ServiceOperationFuture extends DefaultIoFuture
    {
        public ServiceOperationFuture() {
            super(null);
        }
        
        @Override
        public final boolean isDone() {
            return this.getValue() == Boolean.TRUE;
        }
        
        public final void setDone() {
            this.setValue(Boolean.TRUE);
        }
        
        public final Exception getException() {
            if (this.getValue() instanceof Exception) {
                return (Exception)this.getValue();
            }
            return null;
        }
        
        public final void setException(final Exception value) {
            if (value == null) {
                throw new IllegalArgumentException("exception");
            }
            this.setValue(value);
        }
    }
}
