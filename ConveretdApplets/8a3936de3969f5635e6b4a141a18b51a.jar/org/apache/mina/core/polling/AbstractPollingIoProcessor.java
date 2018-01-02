// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.polling;

import java.nio.channels.ClosedSelectorException;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.LoggerFactory;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.transport.socket.AbstractDatagramSessionConfig;
import java.net.PortUnreachableException;
import java.io.IOException;
import org.apache.mina.core.write.WriteRequestQueue;
import java.util.Collection;
import org.apache.mina.core.write.WriteToClosedSessionException;
import org.apache.mina.core.write.WriteRequest;
import java.util.ArrayList;
import org.apache.mina.util.ExceptionMonitor;
import org.apache.mina.core.service.AbstractIoService;
import org.apache.mina.util.NamePreservingRunnable;
import org.apache.mina.core.file.FileRegion;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.SessionState;
import java.util.Iterator;
import org.apache.mina.core.session.IoSession;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.mina.core.future.DefaultIoFuture;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.session.AbstractIoSession;

public abstract class AbstractPollingIoProcessor<S extends AbstractIoSession> implements IoProcessor<S>
{
    private static final Map<Class<?>, AtomicInteger> threadIds;
    private final Object lock;
    private final String threadName;
    private final Executor executor;
    private final Queue<S> newSessions;
    private final Queue<S> removingSessions;
    private final Queue<S> flushingSessions;
    private final Queue<S> trafficControllingSessions;
    private Processor processor;
    private long lastIdleCheckTime;
    private final Object disposalLock;
    private volatile boolean disposing;
    private volatile boolean disposed;
    private final DefaultIoFuture disposalFuture;
    protected AtomicBoolean wakeupCalled;
    
    protected AbstractPollingIoProcessor(final Executor executor) {
        this.lock = new Object();
        this.newSessions = new ConcurrentLinkedQueue<S>();
        this.removingSessions = new ConcurrentLinkedQueue<S>();
        this.flushingSessions = new ConcurrentLinkedQueue<S>();
        this.trafficControllingSessions = new ConcurrentLinkedQueue<S>();
        this.disposalLock = new Object();
        this.disposalFuture = new DefaultIoFuture(null);
        this.wakeupCalled = new AtomicBoolean(false);
        if (executor == null) {
            throw new IllegalArgumentException("executor");
        }
        this.threadName = this.nextThreadName();
        this.executor = executor;
    }
    
    private String nextThreadName() {
        final Class<? extends AbstractPollingIoProcessor> class1 = this.getClass();
        int incrementAndGet;
        synchronized (AbstractPollingIoProcessor.threadIds) {
            final AtomicInteger atomicInteger;
            if ((atomicInteger = AbstractPollingIoProcessor.threadIds.get(class1)) == null) {
                incrementAndGet = 1;
                AbstractPollingIoProcessor.threadIds.put(class1, new AtomicInteger(1));
            }
            else {
                incrementAndGet = atomicInteger.incrementAndGet();
            }
        }
        final Class clazz;
        return clazz.getSimpleName() + '-' + incrementAndGet;
    }
    
    @Override
    public final boolean isDisposing() {
        return this.disposing;
    }
    
    public final boolean isDisposed() {
        return this.disposed;
    }
    
    @Override
    public final void dispose() {
        if (this.disposed || this.disposing) {
            return;
        }
        synchronized (this.disposalLock) {
            this.disposing = true;
            this.startupProcessor();
        }
        this.disposalFuture.awaitUninterruptibly();
        this.disposed = true;
    }
    
    protected abstract void doDispose() throws Exception;
    
    protected abstract int select(final long p0) throws Exception;
    
    protected abstract boolean isSelectorEmpty();
    
    protected abstract void wakeup();
    
    protected abstract Iterator<S> allSessions();
    
    protected abstract Iterator<S> selectedSessions();
    
    protected abstract SessionState getState(final S p0);
    
    protected abstract boolean isWritable(final S p0);
    
    protected abstract boolean isReadable(final S p0);
    
    protected abstract void setInterestedInWrite(final S p0, final boolean p1) throws Exception;
    
    protected abstract void setInterestedInRead(final S p0, final boolean p1) throws Exception;
    
    protected abstract void init(final S p0) throws Exception;
    
    protected abstract void destroy(final S p0) throws Exception;
    
    protected abstract int read(final S p0, final IoBuffer p1) throws Exception;
    
    protected abstract int write(final S p0, final IoBuffer p1, final int p2) throws Exception;
    
    protected abstract int transferFile(final S p0, final FileRegion p1, final int p2) throws Exception;
    
    @Override
    public final void add(final S n) {
        if (this.disposed || this.disposing) {
            throw new IllegalStateException("Already disposed.");
        }
        this.newSessions.add(n);
        this.startupProcessor();
    }
    
    @Override
    public final void remove(final S n) {
        this.scheduleRemove(n);
        this.startupProcessor();
    }
    
    private void scheduleRemove(final S n) {
        this.removingSessions.add(n);
    }
    
    @Override
    public final void flush(final S n) {
        if (n.setScheduledForFlush(true)) {
            this.flushingSessions.add(n);
            this.wakeup();
        }
    }
    
    private void scheduleFlush(final S n) {
        if (n.setScheduledForFlush(true)) {
            this.flushingSessions.add(n);
        }
    }
    
    public final void updateTrafficMask(final S n) {
        this.trafficControllingSessions.add(n);
        this.wakeup();
    }
    
    private void startupProcessor() {
        synchronized (this.lock) {
            if (this.processor == null) {
                this.processor = new Processor();
                this.executor.execute(new NamePreservingRunnable(this.processor, this.threadName));
            }
        }
        this.wakeup();
    }
    
    private boolean addNow(final S n) {
        boolean b = false;
        try {
            this.init(n);
            b = true;
            n.getService().getFilterChainBuilder().buildFilterChain(n.getFilterChain());
            ((AbstractIoService)n.getService()).getListeners().fireSessionCreated(n);
        }
        catch (Throwable t) {
            ExceptionMonitor.getInstance().exceptionCaught(t);
            try {
                this.destroy(n);
                b = false;
            }
            catch (Exception ex) {
                ExceptionMonitor.getInstance().exceptionCaught(ex);
                b = false;
            }
        }
        return b;
    }
    
    private boolean removeNow(final S n) {
        clearWriteRequestQueue(n);
        try {
            this.destroy(n);
            return true;
        }
        catch (Exception ex) {
            n.getFilterChain().fireExceptionCaught(ex);
        }
        finally {
            clearWriteRequestQueue(n);
            ((AbstractIoService)n.getService()).getListeners().fireSessionDestroyed(n);
        }
        return false;
    }
    
    private static void clearWriteRequestQueue(final S n) {
        final WriteRequestQueue writeRequestQueue = n.getWriteRequestQueue();
        final ArrayList<WriteRequest> list = new ArrayList<WriteRequest>();
        final WriteRequest poll;
        if ((poll = writeRequestQueue.poll(n)) != null) {
            final Object message;
            if ((message = poll.getMessage()) instanceof IoBuffer) {
                final IoBuffer ioBuffer;
                if ((ioBuffer = (IoBuffer)message).hasRemaining()) {
                    ioBuffer.reset();
                    list.add(poll);
                }
                else {
                    n.getFilterChain().fireMessageSent(poll);
                }
            }
            else {
                list.add(poll);
            }
            WriteRequest poll2;
            while ((poll2 = writeRequestQueue.poll(n)) != null) {
                list.add(poll2);
            }
        }
        if (!list.isEmpty()) {
            final WriteToClosedSessionException exception = new WriteToClosedSessionException(list);
            for (final WriteRequest writeRequest : list) {
                n.decreaseScheduledBytesAndMessages(writeRequest);
                writeRequest.getFuture().setException(exception);
            }
            n.getFilterChain().fireExceptionCaught(exception);
        }
    }
    
    private void read(final S n) {
        final IoSessionConfig config;
        final IoBuffer allocate = IoBuffer.allocate((config = n.getConfig()).getReadBufferSize());
        final boolean hasFragmentation = n.getTransportMetadata().hasFragmentation();
        try {
            int n2 = 0;
            int n3;
            try {
                if (hasFragmentation) {
                    while ((n3 = this.read(n, allocate)) > 0) {
                        n2 += n3;
                        if (!allocate.hasRemaining()) {
                            break;
                        }
                    }
                }
                else if ((n3 = this.read(n, allocate)) > 0) {
                    n2 = n3;
                }
            }
            finally {
                allocate.flip();
            }
            if (n2 > 0) {
                n.getFilterChain().fireMessageReceived(allocate);
                if (hasFragmentation) {
                    if (n2 << 1 < config.getReadBufferSize()) {
                        n.decreaseReadBufferSize();
                    }
                    else if (n2 == config.getReadBufferSize()) {
                        n.increaseReadBufferSize();
                    }
                }
            }
            if (n3 < 0) {
                this.scheduleRemove(n);
            }
        }
        catch (Throwable t2) {
            final Throwable t = t2;
            if (t2 instanceof IOException && (!(t instanceof PortUnreachableException) || !AbstractDatagramSessionConfig.class.isAssignableFrom(((AbstractDatagramSessionConfig)config).getClass()) || ((AbstractDatagramSessionConfig)config).isCloseOnPortUnreachable())) {
                this.scheduleRemove(n);
            }
            n.getFilterChain().fireExceptionCaught(t);
        }
    }
    
    private boolean flushNow(final S n, final long n2) {
        if (!n.isConnected()) {
            this.scheduleRemove(n);
            return false;
        }
        final boolean hasFragmentation = n.getTransportMetadata().hasFragmentation();
        final WriteRequestQueue writeRequestQueue = n.getWriteRequestQueue();
        final int n3 = n.getConfig().getMaxReadBufferSize() + (n.getConfig().getMaxReadBufferSize() >>> 1);
        int i = 0;
        WriteRequest currentWriteRequest = null;
        try {
            this.setInterestedInWrite(n, false);
            do {
                if ((currentWriteRequest = n.getCurrentWriteRequest()) == null) {
                    if ((currentWriteRequest = writeRequestQueue.poll(n)) == null) {
                        break;
                    }
                    n.setCurrentWriteRequest(currentWriteRequest);
                }
                final Object message;
                int n6;
                if ((message = currentWriteRequest.getMessage()) instanceof IoBuffer) {
                    final WriteRequest writeRequest = currentWriteRequest;
                    final boolean b = hasFragmentation;
                    final int n4 = n3 - i;
                    final boolean b2 = b;
                    final WriteRequest writeRequest2 = writeRequest;
                    final IoBuffer ioBuffer = (IoBuffer)writeRequest2.getMessage();
                    int write = 0;
                    if (ioBuffer.hasRemaining()) {
                        int n5;
                        if (b2) {
                            n5 = Math.min(ioBuffer.remaining(), n4);
                        }
                        else {
                            n5 = ioBuffer.remaining();
                        }
                        write = this.write(n, ioBuffer, n5);
                    }
                    n.increaseWrittenBytes(write, n2);
                    if (!ioBuffer.hasRemaining() || (!b2 && write != 0)) {
                        final int position = ioBuffer.position();
                        ioBuffer.reset();
                        fireMessageSent(n, writeRequest2);
                        ioBuffer.position(position);
                    }
                    if ((n6 = write) > 0 && ((IoBuffer)message).hasRemaining()) {
                        this.setInterestedInWrite(n, true);
                        return false;
                    }
                }
                else {
                    if (!(message instanceof FileRegion)) {
                        throw new IllegalStateException("Don't know how to handle message of type '" + ((FileRegion)message).getClass().getName() + "'.  Are you missing a protocol encoder?");
                    }
                    final WriteRequest writeRequest3 = currentWriteRequest;
                    final boolean b3 = hasFragmentation;
                    final int n7 = n3 - i;
                    final boolean b4 = b3;
                    final WriteRequest writeRequest4 = writeRequest3;
                    final FileRegion fileRegion;
                    int transferFile;
                    if ((fileRegion = (FileRegion)writeRequest4.getMessage()).getRemainingBytes() > 0L) {
                        int n8;
                        if (b4) {
                            n8 = (int)Math.min(fileRegion.getRemainingBytes(), n7);
                        }
                        else {
                            n8 = (int)Math.min(2147483647L, fileRegion.getRemainingBytes());
                        }
                        transferFile = this.transferFile(n, fileRegion, n8);
                        fileRegion.update(transferFile);
                    }
                    else {
                        transferFile = 0;
                    }
                    n.increaseWrittenBytes(transferFile, n2);
                    if (fileRegion.getRemainingBytes() <= 0L || (!b4 && transferFile != 0)) {
                        fireMessageSent(n, writeRequest4);
                    }
                    if ((n6 = transferFile) > 0 && ((FileRegion)message).getRemainingBytes() > 0L) {
                        this.setInterestedInWrite(n, true);
                        return false;
                    }
                }
                if (n6 == 0) {
                    this.setInterestedInWrite(n, true);
                    return false;
                }
                if ((i += n6) >= n3) {
                    this.scheduleFlush(n);
                    return false;
                }
            } while (i < n3);
        }
        catch (Exception exception) {
            if (currentWriteRequest != null) {
                currentWriteRequest.getFuture().setException(exception);
            }
            n.getFilterChain().fireExceptionCaught(exception);
            return false;
        }
        return true;
    }
    
    private static void fireMessageSent(final S n, final WriteRequest writeRequest) {
        n.setCurrentWriteRequest(null);
        n.getFilterChain().fireMessageSent(writeRequest);
    }
    
    @Override
    public void updateTrafficControl(final S n) {
        try {
            this.setInterestedInRead(n, !n.isReadSuspended());
        }
        catch (Exception ex) {
            n.getFilterChain().fireExceptionCaught(ex);
        }
        try {
            this.setInterestedInWrite(n, !n.getWriteRequestQueue().isEmpty(n) && !n.isWriteSuspended());
        }
        catch (Exception ex2) {
            n.getFilterChain().fireExceptionCaught(ex2);
        }
    }
    
    static /* synthetic */ int access$200(AbstractPollingIoProcessor abstractPollingIoProcessor) {
        abstractPollingIoProcessor = abstractPollingIoProcessor;
        int n = 0;
        for (AbstractIoSession abstractIoSession = abstractPollingIoProcessor.newSessions.poll(); abstractIoSession != null; abstractIoSession = abstractPollingIoProcessor.newSessions.poll()) {
            if (abstractPollingIoProcessor.addNow(abstractIoSession)) {
                ++n;
            }
        }
        return n;
    }
    
    static /* synthetic */ void access$300(AbstractPollingIoProcessor abstractPollingIoProcessor) {
        AbstractIoSession abstractIoSession;
        for (int size = (abstractPollingIoProcessor = abstractPollingIoProcessor).trafficControllingSessions.size(); size > 0 && (abstractIoSession = abstractPollingIoProcessor.trafficControllingSessions.poll()) != null; --size) {
            final SessionState state = abstractPollingIoProcessor.getState(abstractIoSession);
            switch (state) {
                case OPENED: {
                    abstractPollingIoProcessor.updateTrafficControl(abstractIoSession);
                    break;
                }
                case CLOSING: {
                    break;
                }
                case OPENING: {
                    abstractPollingIoProcessor.trafficControllingSessions.add(abstractIoSession);
                    break;
                }
                default: {
                    throw new IllegalStateException(String.valueOf(state));
                }
            }
        }
    }
    
    static /* synthetic */ void access$400(AbstractPollingIoProcessor abstractPollingIoProcessor) throws Exception {
        final Iterator<AbstractIoSession> selectedSessions = (abstractPollingIoProcessor = abstractPollingIoProcessor).selectedSessions();
        while (selectedSessions.hasNext()) {
            final AbstractIoSession abstractIoSession = selectedSessions.next();
            final AbstractPollingIoProcessor<AbstractIoSession> abstractPollingIoProcessor2 = abstractPollingIoProcessor;
            final AbstractIoSession abstractIoSession2 = abstractIoSession;
            final AbstractPollingIoProcessor<AbstractIoSession> abstractPollingIoProcessor3 = abstractPollingIoProcessor2;
            if (abstractPollingIoProcessor2.isReadable(abstractIoSession2) && !abstractIoSession2.isReadSuspended()) {
                abstractPollingIoProcessor3.read(abstractIoSession2);
            }
            if (abstractPollingIoProcessor3.isWritable(abstractIoSession2) && !abstractIoSession2.isWriteSuspended() && abstractIoSession2.setScheduledForFlush(true)) {
                abstractPollingIoProcessor3.flushingSessions.add(abstractIoSession2);
            }
            selectedSessions.remove();
        }
    }
    
    static /* synthetic */ void access$500(AbstractPollingIoProcessor abstractPollingIoProcessor, long n2) {
        if (!(abstractPollingIoProcessor = abstractPollingIoProcessor).flushingSessions.isEmpty()) {
            while ((n = (long)abstractPollingIoProcessor.flushingSessions.poll()) != null) {
                ((AbstractIoSession)n).unscheduledForFlush();
                final SessionState state = abstractPollingIoProcessor.getState((AbstractIoSession)n);
                switch (state) {
                    case OPENED: {
                        try {
                            if (abstractPollingIoProcessor.flushNow((AbstractIoSession)n, n2) && !((AbstractIoSession)n).getWriteRequestQueue().isEmpty((IoSession)n) && !((AbstractIoSession)n).isScheduledForFlush()) {
                                abstractPollingIoProcessor.scheduleFlush((AbstractIoSession)n);
                            }
                        }
                        catch (Exception ex) {
                            abstractPollingIoProcessor.scheduleRemove((AbstractIoSession)n);
                            ((IoSession)n).getFilterChain().fireExceptionCaught(ex);
                        }
                        break;
                    }
                    case CLOSING: {
                        break;
                    }
                    case OPENING: {
                        abstractPollingIoProcessor.scheduleFlush((AbstractIoSession)n);
                        return;
                    }
                    default: {
                        throw new IllegalStateException(String.valueOf(state));
                    }
                }
                if (abstractPollingIoProcessor.flushingSessions.isEmpty()) {
                    break;
                }
            }
        }
    }
    
    static /* synthetic */ int access$600(AbstractPollingIoProcessor abstractPollingIoProcessor) {
        abstractPollingIoProcessor = abstractPollingIoProcessor;
        int n = 0;
        for (AbstractIoSession abstractIoSession = abstractPollingIoProcessor.removingSessions.poll(); abstractIoSession != null; abstractIoSession = abstractPollingIoProcessor.removingSessions.poll()) {
            final SessionState state = abstractPollingIoProcessor.getState(abstractIoSession);
            switch (state) {
                case OPENED: {
                    if (abstractPollingIoProcessor.removeNow(abstractIoSession)) {
                        ++n;
                        break;
                    }
                    break;
                }
                case CLOSING: {
                    break;
                }
                case OPENING: {
                    abstractPollingIoProcessor.newSessions.remove(abstractIoSession);
                    if (abstractPollingIoProcessor.removeNow(abstractIoSession)) {
                        ++n;
                        break;
                    }
                    break;
                }
                default: {
                    throw new IllegalStateException(String.valueOf(state));
                }
            }
        }
        return n;
    }
    
    static /* synthetic */ void access$700(AbstractPollingIoProcessor abstractPollingIoProcessor, final long lastIdleCheckTime) throws Exception {
        abstractPollingIoProcessor = abstractPollingIoProcessor;
        if (lastIdleCheckTime - abstractPollingIoProcessor.lastIdleCheckTime >= 1000L) {
            abstractPollingIoProcessor.lastIdleCheckTime = lastIdleCheckTime;
            AbstractIoSession.notifyIdleness(abstractPollingIoProcessor.allSessions(), lastIdleCheckTime);
        }
    }
    
    static /* synthetic */ Processor access$1002(final AbstractPollingIoProcessor abstractPollingIoProcessor, final Processor processor) {
        return abstractPollingIoProcessor.processor = null;
    }
    
    static {
        LoggerFactory.getLogger$4ecaad6a();
        threadIds = new ConcurrentHashMap<Class<?>, AtomicInteger>();
    }
    
    final class Processor implements Runnable
    {
        private Processor(final byte b) {
        }
        
        @Override
        public final void run() {
            int n = 0;
            AbstractPollingIoProcessor.this.lastIdleCheckTime = System.currentTimeMillis();
            while (true) {
                try {
                    while (true) {
                        final int select = AbstractPollingIoProcessor.this.select(1000L);
                        n += AbstractPollingIoProcessor.access$200(AbstractPollingIoProcessor.this);
                        AbstractPollingIoProcessor.access$300(AbstractPollingIoProcessor.this);
                        if (select > 0) {
                            AbstractPollingIoProcessor.access$400(AbstractPollingIoProcessor.this);
                        }
                        final long currentTimeMillis = System.currentTimeMillis();
                        AbstractPollingIoProcessor.access$500(AbstractPollingIoProcessor.this, currentTimeMillis);
                        n -= AbstractPollingIoProcessor.access$600(AbstractPollingIoProcessor.this);
                        AbstractPollingIoProcessor.access$700(AbstractPollingIoProcessor.this, currentTimeMillis);
                        if (n == 0) {
                            synchronized (AbstractPollingIoProcessor.this.lock) {
                                if (AbstractPollingIoProcessor.this.newSessions.isEmpty() && AbstractPollingIoProcessor.this.isSelectorEmpty()) {
                                    AbstractPollingIoProcessor.access$1002(AbstractPollingIoProcessor.this, null);
                                    break;
                                }
                            }
                        }
                        if (AbstractPollingIoProcessor.this.isDisposing()) {
                            final Iterator<AbstractIoSession> allSessions = (Iterator<AbstractIoSession>)AbstractPollingIoProcessor.this.allSessions();
                            while (allSessions.hasNext()) {
                                AbstractPollingIoProcessor.this.scheduleRemove(allSessions.next());
                            }
                            AbstractPollingIoProcessor.this.wakeup();
                        }
                    }
                }
                catch (ClosedSelectorException ex2) {}
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
            try {
                synchronized (AbstractPollingIoProcessor.this.disposalLock) {
                    if (AbstractPollingIoProcessor.this.disposing) {
                        AbstractPollingIoProcessor.this.doDispose();
                    }
                }
            }
            catch (Throwable t2) {
                ExceptionMonitor.getInstance().exceptionCaught(t2);
            }
            finally {
                AbstractPollingIoProcessor.this.disposalFuture.setValue(true);
            }
        }
    }
}
