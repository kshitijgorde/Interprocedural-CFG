// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.polling;

import java.nio.channels.ClosedSelectorException;
import com.masystem.beergame.debug.Log;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.session.IoSession;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Iterator;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.util.ExceptionMonitor;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import org.apache.mina.core.service.SimpleIoProcessorPool;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.core.service.AbstractIoService;
import java.net.SocketAddress;
import java.util.Map;
import java.util.Queue;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.service.AbstractIoAcceptor;
import org.apache.mina.core.session.AbstractIoSession;

public abstract class AbstractPollingIoAcceptor<T extends AbstractIoSession, H> extends AbstractIoAcceptor
{
    private final IoProcessor<T> processor;
    private final boolean createdProcessor;
    private final Object lock;
    private final Queue<AcceptorOperationFuture> registerQueue;
    private final Queue<AcceptorOperationFuture> cancelQueue;
    private final Map<SocketAddress, H> boundHandles;
    private final ServiceOperationFuture disposalFuture;
    private volatile boolean selectable;
    private Acceptor acceptor;
    
    protected AbstractPollingIoAcceptor(final IoSessionConfig ioSessionConfig, final Class<? extends IoProcessor<T>> clazz) {
        this(ioSessionConfig, null, (IoProcessor)new SimpleIoProcessorPool(clazz), true);
    }
    
    private AbstractPollingIoAcceptor(final IoSessionConfig ioSessionConfig, final Executor executor, final IoProcessor<T> processor, final boolean b) {
        super(ioSessionConfig, null);
        this.lock = new Object();
        this.registerQueue = new ConcurrentLinkedQueue<AcceptorOperationFuture>();
        this.cancelQueue = new ConcurrentLinkedQueue<AcceptorOperationFuture>();
        this.boundHandles = Collections.synchronizedMap(new HashMap<SocketAddress, H>());
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
    
    protected abstract int select() throws Exception;
    
    protected abstract void wakeup();
    
    protected abstract Iterator<H> selectedHandles();
    
    protected abstract H open(final SocketAddress p0) throws Exception;
    
    protected abstract SocketAddress localAddress(final H p0) throws Exception;
    
    protected abstract T accept(final IoProcessor<T> p0, final H p1) throws Exception;
    
    protected abstract void close(final H p0) throws Exception;
    
    @Override
    protected final void dispose0() throws Exception {
        this.unbind(this.getLocalAddresses());
        this.startupAcceptor();
        this.wakeup();
    }
    
    @Override
    protected final Set<SocketAddress> bindInternal(final List<? extends SocketAddress> list) throws Exception {
        final AcceptorOperationFuture acceptorOperationFuture = new AcceptorOperationFuture(list);
        this.registerQueue.add(acceptorOperationFuture);
        this.startupAcceptor();
        this.wakeup();
        acceptorOperationFuture.awaitUninterruptibly();
        if (acceptorOperationFuture.getException() != null) {
            throw acceptorOperationFuture.getException();
        }
        final HashSet<SocketAddress> set = new HashSet<SocketAddress>();
        final Iterator<H> iterator = this.boundHandles.values().iterator();
        while (iterator.hasNext()) {
            set.add(this.localAddress(iterator.next()));
        }
        return set;
    }
    
    private void startupAcceptor() {
        if (!this.selectable) {
            this.registerQueue.clear();
            this.cancelQueue.clear();
        }
        synchronized (this.lock) {
            if (this.acceptor == null) {
                this.executeWorker(this.acceptor = new Acceptor());
            }
        }
    }
    
    @Override
    protected final void unbind0(final List<? extends SocketAddress> list) throws Exception {
        final AcceptorOperationFuture acceptorOperationFuture = new AcceptorOperationFuture(list);
        this.cancelQueue.add(acceptorOperationFuture);
        this.startupAcceptor();
        this.wakeup();
        acceptorOperationFuture.awaitUninterruptibly();
        if (acceptorOperationFuture.getException() != null) {
            throw acceptorOperationFuture.getException();
        }
    }
    
    private int registerHandles() {
        AcceptorOperationFuture acceptorOperationFuture;
        while ((acceptorOperationFuture = this.registerQueue.poll()) != null) {
            final ConcurrentHashMap<SocketAddress, H> concurrentHashMap = new ConcurrentHashMap<SocketAddress, H>();
            final List<SocketAddress> localAddresses = acceptorOperationFuture.getLocalAddresses();
            try {
                final Iterator<SocketAddress> iterator = localAddresses.iterator();
                while (iterator.hasNext()) {
                    final H open = this.open(iterator.next());
                    concurrentHashMap.put(this.localAddress(open), open);
                }
                this.boundHandles.putAll((Map<? extends SocketAddress, ? extends H>)concurrentHashMap);
                acceptorOperationFuture.setDone();
                return concurrentHashMap.size();
            }
            catch (Exception exception) {
                acceptorOperationFuture.setException(exception);
            }
            finally {
                if (acceptorOperationFuture.getException() != null) {
                    for (final H next : concurrentHashMap.values()) {
                        try {
                            this.close(next);
                        }
                        catch (Exception ex) {
                            ExceptionMonitor.getInstance().exceptionCaught(ex);
                        }
                    }
                    this.wakeup();
                }
            }
        }
        return 0;
    }
    
    private int unregisterHandles() {
        int n = 0;
        AcceptorOperationFuture acceptorOperationFuture;
        while ((acceptorOperationFuture = this.cancelQueue.poll()) != null) {
            final Iterator<SocketAddress> iterator = acceptorOperationFuture.getLocalAddresses().iterator();
            while (iterator.hasNext()) {
                final H remove;
                if ((remove = this.boundHandles.remove(iterator.next())) != null) {
                    try {
                        this.close(remove);
                        this.wakeup();
                        ++n;
                        continue;
                    }
                    catch (Throwable t) {
                        ExceptionMonitor.getInstance().exceptionCaught(t);
                        ++n;
                        continue;
                    }
                    break;
                }
            }
            acceptorOperationFuture.setDone();
        }
        return n;
    }
    
    static /* synthetic */ Acceptor access$702(final AbstractPollingIoAcceptor abstractPollingIoAcceptor, final Acceptor acceptor) {
        return abstractPollingIoAcceptor.acceptor = null;
    }
    
    static /* synthetic */ boolean access$102(final AbstractPollingIoAcceptor abstractPollingIoAcceptor, final boolean b) {
        return abstractPollingIoAcceptor.selectable = false;
    }
    
    static /* synthetic */ void access$1200$11e37792(final AbstractPollingIoAcceptor abstractPollingIoAcceptor, final IoSession ioSession, final IoFuture ioFuture, final Log.Listener listener) {
        abstractPollingIoAcceptor.initSession$1746fb3d(ioSession, null, null);
    }
    
    final class Acceptor implements Runnable
    {
        private Acceptor(final byte b) {
        }
        
        @Override
        public final void run() {
            int n = 0;
            while (AbstractPollingIoAcceptor.this.selectable) {
                try {
                    final int select = AbstractPollingIoAcceptor.this.select();
                    n += AbstractPollingIoAcceptor.this.registerHandles();
                    if (select > 0) {
                        final Iterator<H> selectedHandles = AbstractPollingIoAcceptor.this.selectedHandles();
                        while (selectedHandles.hasNext()) {
                            final H next = selectedHandles.next();
                            selectedHandles.remove();
                            final AbstractIoSession accept;
                            if ((accept = AbstractPollingIoAcceptor.this.accept(AbstractPollingIoAcceptor.this.processor, next)) == null) {
                                break;
                            }
                            AbstractPollingIoAcceptor.access$1200$11e37792(AbstractPollingIoAcceptor.this, accept, null, null);
                            accept.getProcessor().add(accept);
                        }
                    }
                    if ((n -= AbstractPollingIoAcceptor.this.unregisterHandles()) != 0) {
                        continue;
                    }
                    synchronized (AbstractPollingIoAcceptor.this.lock) {
                        if (AbstractPollingIoAcceptor.this.registerQueue.isEmpty() && AbstractPollingIoAcceptor.this.cancelQueue.isEmpty()) {
                            AbstractPollingIoAcceptor.access$702(AbstractPollingIoAcceptor.this, null);
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
            if (AbstractPollingIoAcceptor.this.selectable && AbstractPollingIoAcceptor.this.isDisposing()) {
                AbstractPollingIoAcceptor.access$102(AbstractPollingIoAcceptor.this, false);
                try {
                    if (AbstractPollingIoAcceptor.this.createdProcessor) {
                        AbstractPollingIoAcceptor.this.processor.dispose();
                    }
                    try {
                        synchronized (AbstractPollingIoAcceptor.this.disposalLock) {
                            if (AbstractPollingIoAcceptor.this.isDisposing()) {
                                AbstractPollingIoAcceptor.this.destroy();
                            }
                        }
                    }
                    catch (Exception ex2) {
                        ExceptionMonitor.getInstance().exceptionCaught(ex2);
                    }
                    finally {
                        AbstractPollingIoAcceptor.this.disposalFuture.setDone();
                    }
                }
                finally {
                    try {
                        synchronized (AbstractPollingIoAcceptor.this.disposalLock) {
                            if (AbstractPollingIoAcceptor.this.isDisposing()) {
                                AbstractPollingIoAcceptor.this.destroy();
                            }
                        }
                        AbstractPollingIoAcceptor.this.disposalFuture.setDone();
                    }
                    catch (Exception ex3) {
                        ExceptionMonitor.getInstance().exceptionCaught(ex3);
                        AbstractPollingIoAcceptor.this.disposalFuture.setDone();
                    }
                    finally {
                        AbstractPollingIoAcceptor.this.disposalFuture.setDone();
                    }
                }
            }
        }
    }
}
