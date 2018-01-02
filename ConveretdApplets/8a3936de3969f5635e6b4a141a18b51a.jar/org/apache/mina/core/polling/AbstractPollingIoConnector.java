// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.polling;

import java.nio.channels.ClosedSelectorException;
import java.net.ConnectException;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.future.DefaultConnectFuture;
import org.apache.mina.core.future.ConnectFuture;
import com.masystem.beergame.debug.Log;
import java.util.Iterator;
import java.net.SocketAddress;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.util.ExceptionMonitor;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import org.apache.mina.core.service.SimpleIoProcessorPool;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.core.service.AbstractIoService;
import org.apache.mina.core.service.IoProcessor;
import java.util.Queue;
import org.apache.mina.core.service.AbstractIoConnector;
import org.apache.mina.core.session.AbstractIoSession;

public abstract class AbstractPollingIoConnector<T extends AbstractIoSession, H> extends AbstractIoConnector
{
    private final Object lock;
    private final Queue<ConnectionRequest> connectQueue;
    private final Queue<ConnectionRequest> cancelQueue;
    private final IoProcessor<T> processor;
    private final boolean createdProcessor;
    private final ServiceOperationFuture disposalFuture;
    private volatile boolean selectable;
    private Connector connector;
    
    protected AbstractPollingIoConnector(final IoSessionConfig ioSessionConfig, final Class<? extends IoProcessor<T>> clazz) {
        this(ioSessionConfig, null, (IoProcessor)new SimpleIoProcessorPool(clazz), true);
    }
    
    private AbstractPollingIoConnector(final IoSessionConfig ioSessionConfig, final Executor executor, final IoProcessor<T> processor, final boolean b) {
        super(ioSessionConfig, null);
        this.lock = new Object();
        this.connectQueue = new ConcurrentLinkedQueue<ConnectionRequest>();
        this.cancelQueue = new ConcurrentLinkedQueue<ConnectionRequest>();
        this.disposalFuture = new ServiceOperationFuture();
        if (processor == null) {
            throw new IllegalArgumentException("processor");
        }
        this.processor = processor;
        this.createdProcessor = true;
        try {
            this.init();
            if (this.selectable = true) {
                return;
            }
            try {
                this.destroy();
            }
            catch (Exception ex) {
                ExceptionMonitor.getInstance().exceptionCaught(ex);
            }
        }
        catch (RuntimeException ex2) {
            throw ex2;
        }
        catch (Exception ex3) {
            throw new RuntimeIoException("Failed to initialize.", ex3);
        }
        finally {
            if (!this.selectable) {
                try {
                    this.destroy();
                }
                catch (Exception ex4) {
                    ExceptionMonitor.getInstance().exceptionCaught(ex4);
                }
            }
        }
    }
    
    protected abstract void init() throws Exception;
    
    protected abstract void destroy() throws Exception;
    
    protected abstract H newHandle(final SocketAddress p0) throws Exception;
    
    protected abstract boolean connect(final H p0, final SocketAddress p1) throws Exception;
    
    protected abstract boolean finishConnect(final H p0) throws Exception;
    
    protected abstract T newSession(final IoProcessor<T> p0, final H p1) throws Exception;
    
    protected abstract void close(final H p0) throws Exception;
    
    protected abstract void wakeup();
    
    protected abstract int select(final int p0) throws Exception;
    
    protected abstract Iterator<H> selectedHandles();
    
    protected abstract Iterator<H> allHandles();
    
    protected abstract void register(final H p0, final ConnectionRequest p1) throws Exception;
    
    protected abstract ConnectionRequest getConnectionRequest(final H p0);
    
    @Override
    protected final void dispose0() throws Exception {
        this.startupWorker();
        this.wakeup();
    }
    
    @Override
    protected final ConnectFuture connect0$181451ab(final SocketAddress socketAddress, SocketAddress failedFuture, final Log.Listener<? extends ConnectFuture> listener) {
        H handle = null;
        boolean b = false;
        try {
            handle = this.newHandle(failedFuture);
            if (this.connect(handle, socketAddress)) {
                final DefaultConnectFuture defaultConnectFuture = new DefaultConnectFuture();
                final AbstractIoSession session = this.newSession((IoProcessor<AbstractIoSession>)this.processor, handle);
                this.initSession$1746fb3d(session, defaultConnectFuture, listener);
                session.getProcessor().add(session);
                b = true;
                return defaultConnectFuture;
            }
        }
        catch (Exception ex) {
            failedFuture = (SocketAddress)DefaultConnectFuture.newFailedFuture(ex);
            return (ConnectFuture)failedFuture;
        }
        finally {
            if (!b && handle != null) {
                try {
                    this.close(handle);
                }
                catch (Exception ex2) {
                    ExceptionMonitor.getInstance().exceptionCaught(ex2);
                }
            }
        }
        final ConnectionRequest connectionRequest = new ConnectionRequest(handle, listener);
        this.connectQueue.add(connectionRequest);
        this.startupWorker();
        this.wakeup();
        return connectionRequest;
    }
    
    private void startupWorker() {
        if (!this.selectable) {
            this.connectQueue.clear();
            this.cancelQueue.clear();
        }
        synchronized (this.lock) {
            if (this.connector == null) {
                this.executeWorker(this.connector = new Connector());
            }
        }
    }
    
    private int registerNew() {
        int n = 0;
        ConnectionRequest connectionRequest;
        while ((connectionRequest = this.connectQueue.poll()) != null) {
            final Object access$100 = connectionRequest.handle;
            try {
                this.register((H)access$100, connectionRequest);
                ++n;
                continue;
            }
            catch (Exception exception) {
                connectionRequest.setException(exception);
                try {
                    this.close((H)access$100);
                }
                catch (Exception ex) {
                    ExceptionMonitor.getInstance().exceptionCaught(ex);
                }
                continue;
            }
            break;
        }
        return n;
    }
    
    private int cancelKeys() {
        int n = 0;
        ConnectionRequest connectionRequest;
        while ((connectionRequest = this.cancelQueue.poll()) != null) {
            final Object access$100 = connectionRequest.handle;
            try {
                this.close((H)access$100);
                ++n;
                continue;
            }
            catch (Exception ex) {
                ExceptionMonitor.getInstance().exceptionCaught(ex);
                ++n;
                continue;
            }
            break;
        }
        return n;
    }
    
    private int processConnections(final Iterator<H> iterator) {
        int n = 0;
        while (iterator.hasNext()) {
            final H next = iterator.next();
            iterator.remove();
            final ConnectionRequest connectionRequest;
            if ((connectionRequest = this.getConnectionRequest(next)) != null) {
                try {
                    if (!this.finishConnect(next)) {
                        continue;
                    }
                    final AbstractIoSession session = this.newSession((IoProcessor<AbstractIoSession>)this.processor, next);
                    this.initSession$1746fb3d(session, connectionRequest, connectionRequest.getSessionInitializer$21da4635());
                    session.getProcessor().add(session);
                    ++n;
                    continue;
                }
                catch (Throwable exception) {
                    connectionRequest.setException(exception);
                    continue;
                }
                finally {
                    this.cancelQueue.offer(connectionRequest);
                }
                break;
            }
        }
        return n;
    }
    
    static /* synthetic */ void access$600(AbstractPollingIoConnector abstractPollingIoConnector, Iterator iterator) {
        final AbstractPollingIoConnector<T, Object> abstractPollingIoConnector2 = abstractPollingIoConnector;
        iterator = iterator;
        abstractPollingIoConnector = abstractPollingIoConnector2;
        final long currentTimeMillis = System.currentTimeMillis();
        while (iterator.hasNext()) {
            final ConnectionRequest connectionRequest;
            if ((connectionRequest = abstractPollingIoConnector.getConnectionRequest(iterator.next())) != null && currentTimeMillis >= connectionRequest.deadline) {
                connectionRequest.setException(new ConnectException("Connection timed out."));
                abstractPollingIoConnector.cancelQueue.offer(connectionRequest);
            }
        }
    }
    
    static /* synthetic */ Connector access$1002(final AbstractPollingIoConnector abstractPollingIoConnector, final Connector connector) {
        return abstractPollingIoConnector.connector = null;
    }
    
    static /* synthetic */ boolean access$302(final AbstractPollingIoConnector abstractPollingIoConnector, final boolean b) {
        return abstractPollingIoConnector.selectable = false;
    }
    
    public final class ConnectionRequest extends DefaultConnectFuture
    {
        private final H handle;
        private final long deadline;
        private final Log.Listener<? extends ConnectFuture> sessionInitializer$47022f2a;
        
        public ConnectionRequest(final H handle, final Log.Listener<? extends ConnectFuture> sessionInitializer$47022f2a) {
            this.handle = handle;
            final long connectTimeoutMillis;
            if ((connectTimeoutMillis = AbstractPollingIoConnector.this.getConnectTimeoutMillis()) <= 0L) {
                this.deadline = Long.MAX_VALUE;
            }
            else {
                this.deadline = System.currentTimeMillis() + connectTimeoutMillis;
            }
            this.sessionInitializer$47022f2a = sessionInitializer$47022f2a;
        }
        
        public final Log.Listener<? extends ConnectFuture> getSessionInitializer$21da4635() {
            return this.sessionInitializer$47022f2a;
        }
        
        @Override
        public final void cancel() {
            if (!this.isDone()) {
                super.cancel();
                AbstractPollingIoConnector.this.cancelQueue.add(this);
                AbstractPollingIoConnector.this.startupWorker();
                AbstractPollingIoConnector.this.wakeup();
            }
        }
    }
    
    final class Connector implements Runnable
    {
        private Connector(final byte b) {
        }
        
        @Override
        public final void run() {
            int n = 0;
            while (AbstractPollingIoConnector.this.selectable) {
                try {
                    final int select = AbstractPollingIoConnector.this.select((int)Math.min(AbstractPollingIoConnector.this.getConnectTimeoutMillis(), 1000L));
                    n += AbstractPollingIoConnector.this.registerNew();
                    if (select > 0) {
                        n -= AbstractPollingIoConnector.this.processConnections(AbstractPollingIoConnector.this.selectedHandles());
                    }
                    AbstractPollingIoConnector.access$600(AbstractPollingIoConnector.this, AbstractPollingIoConnector.this.allHandles());
                    if ((n -= AbstractPollingIoConnector.this.cancelKeys()) != 0) {
                        continue;
                    }
                    synchronized (AbstractPollingIoConnector.this.lock) {
                        if (AbstractPollingIoConnector.this.connectQueue.isEmpty()) {
                            AbstractPollingIoConnector.access$1002(AbstractPollingIoConnector.this, null);
                            break;
                        }
                        continue;
                    }
                    continue;
                }
                catch (ClosedSelectorException ex4) {}
                catch (Throwable t) {
                    ExceptionMonitor.getInstance().exceptionCaught(t);
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (InterruptedException ex) {
                        ExceptionMonitor.getInstance().exceptionCaught(ex);
                    }
                    continue;
                }
                break;
            }
            if (AbstractPollingIoConnector.this.selectable && AbstractPollingIoConnector.this.isDisposing()) {
                AbstractPollingIoConnector.access$302(AbstractPollingIoConnector.this, false);
                try {
                    if (AbstractPollingIoConnector.this.createdProcessor) {
                        AbstractPollingIoConnector.this.processor.dispose();
                    }
                    try {
                        synchronized (AbstractPollingIoConnector.this.disposalLock) {
                            if (AbstractPollingIoConnector.this.isDisposing()) {
                                AbstractPollingIoConnector.this.destroy();
                            }
                        }
                    }
                    catch (Exception ex2) {
                        ExceptionMonitor.getInstance().exceptionCaught(ex2);
                    }
                    finally {
                        AbstractPollingIoConnector.this.disposalFuture.setDone();
                    }
                }
                finally {
                    try {
                        synchronized (AbstractPollingIoConnector.this.disposalLock) {
                            if (AbstractPollingIoConnector.this.isDisposing()) {
                                AbstractPollingIoConnector.this.destroy();
                            }
                        }
                        AbstractPollingIoConnector.this.disposalFuture.setDone();
                    }
                    catch (Exception ex3) {
                        ExceptionMonitor.getInstance().exceptionCaught(ex3);
                        AbstractPollingIoConnector.this.disposalFuture.setDone();
                    }
                    finally {
                        AbstractPollingIoConnector.this.disposalFuture.setDone();
                    }
                }
            }
        }
    }
}
