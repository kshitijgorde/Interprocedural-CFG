// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.session;

import org.apache.mina.core.write.WriteTimeoutException;
import java.util.Iterator;
import org.apache.mina.core.service.TransportMetadata;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.AbstractIoService;
import org.apache.mina.core.future.IoFuture;
import java.io.IOException;
import org.apache.mina.util.ExceptionMonitor;
import org.apache.mina.core.file.FilenameFileRegion;
import java.io.FileInputStream;
import java.io.File;
import org.apache.mina.core.file.DefaultFileRegion;
import java.nio.channels.FileChannel;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.write.WriteToClosedSessionException;
import org.apache.mina.core.write.DefaultWriteRequest;
import org.apache.mina.core.future.DefaultWriteFuture;
import java.net.SocketAddress;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.future.DefaultReadFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.mina.core.future.ReadFuture;
import java.util.Queue;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.future.DefaultCloseFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.mina.core.write.WriteRequestQueue;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.IoHandler;

public abstract class AbstractIoSession implements IoSession
{
    private final IoHandler handler;
    protected IoSessionConfig config;
    private final IoService service;
    private static final AttributeKey READY_READ_FUTURES_KEY;
    private static final AttributeKey WAITING_READ_FUTURES_KEY;
    private static final IoFutureListener<CloseFuture> SCHEDULED_COUNTER_RESETTER;
    private static final WriteRequest CLOSE_REQUEST;
    private final Object lock;
    private IoSessionAttributeMap attributes;
    private WriteRequestQueue writeRequestQueue;
    private WriteRequest currentWriteRequest;
    private static AtomicLong idGenerator;
    private long sessionId;
    private final CloseFuture closeFuture;
    private volatile boolean closing;
    private boolean readSuspended;
    private boolean writeSuspended;
    private final AtomicBoolean scheduledForFlush;
    private final AtomicInteger scheduledWriteBytes;
    private final AtomicInteger scheduledWriteMessages;
    private long readBytes;
    private long writtenBytes;
    private long readMessages;
    private long writtenMessages;
    private long lastReadTime;
    private long lastWriteTime;
    private AtomicInteger idleCountForBoth;
    private AtomicInteger idleCountForRead;
    private AtomicInteger idleCountForWrite;
    private long lastIdleTimeForBoth;
    private long lastIdleTimeForRead;
    private long lastIdleTimeForWrite;
    private boolean deferDecreaseReadBuffer;
    
    protected AbstractIoSession(final IoService service) {
        this.lock = new Object();
        this.closeFuture = new DefaultCloseFuture(this);
        this.readSuspended = false;
        this.writeSuspended = false;
        this.scheduledForFlush = new AtomicBoolean();
        this.scheduledWriteBytes = new AtomicInteger();
        this.scheduledWriteMessages = new AtomicInteger();
        this.idleCountForBoth = new AtomicInteger();
        this.idleCountForRead = new AtomicInteger();
        this.idleCountForWrite = new AtomicInteger();
        this.deferDecreaseReadBuffer = true;
        this.service = service;
        this.handler = service.getHandler();
        final long currentTimeMillis = System.currentTimeMillis();
        this.lastReadTime = currentTimeMillis;
        this.lastWriteTime = currentTimeMillis;
        this.lastIdleTimeForBoth = currentTimeMillis;
        this.lastIdleTimeForRead = currentTimeMillis;
        this.lastIdleTimeForWrite = currentTimeMillis;
        this.closeFuture.addListener((IoFutureListener<?>)AbstractIoSession.SCHEDULED_COUNTER_RESETTER);
        this.sessionId = AbstractIoSession.idGenerator.incrementAndGet();
    }
    
    @Override
    public final long getId() {
        return this.sessionId;
    }
    
    public abstract IoProcessor getProcessor();
    
    @Override
    public final boolean isConnected() {
        return !this.closeFuture.isClosed();
    }
    
    private boolean isClosing() {
        return this.closing || this.closeFuture.isClosed();
    }
    
    @Override
    public final CloseFuture getCloseFuture() {
        return this.closeFuture;
    }
    
    public final boolean isScheduledForFlush() {
        return this.scheduledForFlush.get();
    }
    
    public final void unscheduledForFlush() {
        this.scheduledForFlush.set(false);
    }
    
    public final boolean setScheduledForFlush(final boolean b) {
        return this.scheduledForFlush.compareAndSet(false, true);
    }
    
    @Override
    public final CloseFuture close(final boolean b) {
        if (b) {
            return this.close();
        }
        this.getWriteRequestQueue().offer(this, AbstractIoSession.CLOSE_REQUEST);
        this.getProcessor().flush(this);
        return this.closeFuture;
    }
    
    public final CloseFuture close() {
        synchronized (this.lock) {
            if (this.isClosing()) {
                return this.closeFuture;
            }
            this.closing = true;
        }
        this.getFilterChain().fireFilterClose();
        return this.closeFuture;
    }
    
    @Override
    public final IoHandler getHandler() {
        return this.handler;
    }
    
    @Override
    public IoSessionConfig getConfig() {
        return this.config;
    }
    
    public final void offerReadFuture(final Object read) {
        this.newReadFuture().setRead(read);
    }
    
    public final void offerFailedReadFuture(final Throwable exception) {
        this.newReadFuture().setException(exception);
    }
    
    public final void offerClosedReadFuture() {
        final Queue<ReadFuture> readyReadFutures = this.getReadyReadFutures();
        synchronized (readyReadFutures) {
            this.newReadFuture().setClosed();
        }
    }
    
    private ReadFuture newReadFuture() {
        final Queue<ReadFuture> readyReadFutures = this.getReadyReadFutures();
        Object o;
        if ((o = this.getAttribute(AbstractIoSession.WAITING_READ_FUTURES_KEY, null)) == null) {
            o = new ConcurrentLinkedQueue<ReadFuture>();
            final Queue queue;
            if ((queue = (Queue)this.setAttributeIfAbsent(AbstractIoSession.WAITING_READ_FUTURES_KEY, o)) != null) {
                o = queue;
            }
        }
        Object o2 = o;
        synchronized (readyReadFutures) {
            if ((o2 = ((Queue<ReadFuture>)o2).poll()) == null) {
                o2 = new DefaultReadFuture(this);
                readyReadFutures.offer((ReadFuture)o2);
            }
        }
        return (ReadFuture)o2;
    }
    
    private Queue<ReadFuture> getReadyReadFutures() {
        Queue<ReadFuture> queue;
        if ((queue = (Queue<ReadFuture>)this.getAttribute(AbstractIoSession.READY_READ_FUTURES_KEY, null)) == null) {
            queue = new ConcurrentLinkedQueue<ReadFuture>();
            final Queue queue2;
            if ((queue2 = (Queue)this.setAttributeIfAbsent(AbstractIoSession.READY_READ_FUTURES_KEY, queue)) != null) {
                queue = (Queue<ReadFuture>)queue2;
            }
        }
        return queue;
    }
    
    @Override
    public final WriteFuture write(final Object o) {
        return this.write(o, null);
    }
    
    private WriteFuture write(Object o, final SocketAddress socketAddress) {
        if (o == null) {
            throw new IllegalArgumentException("message");
        }
        this.getTransportMetadata().isConnectionless();
        if (this.isClosing() || !this.isConnected()) {
            final DefaultWriteFuture defaultWriteFuture = new DefaultWriteFuture(this);
            defaultWriteFuture.setException(new WriteToClosedSessionException(new DefaultWriteRequest(o, defaultWriteFuture, null)));
            return defaultWriteFuture;
        }
        FileChannel channel = null;
        try {
            if (o instanceof IoBuffer && !((IoBuffer)o).hasRemaining()) {
                throw new IllegalArgumentException("message is empty. Forgot to call flip()?");
            }
            if (o instanceof FileChannel) {
                final FileChannel fileChannel = (FileChannel)o;
                o = new DefaultFileRegion(fileChannel, 0L, fileChannel.size());
            }
            else if (o instanceof File) {
                final File file = (File)o;
                channel = new FileInputStream(file).getChannel();
                o = new FilenameFileRegion(file, channel, 0L, channel.size());
            }
        }
        catch (IOException ex) {
            ExceptionMonitor.getInstance().exceptionCaught(ex);
            return DefaultWriteFuture.newNotWrittenFuture(this, ex);
        }
        final DefaultWriteFuture defaultWriteFuture2 = new DefaultWriteFuture(this);
        o = new DefaultWriteRequest(o, defaultWriteFuture2, null);
        this.getFilterChain().fireFilterWrite((WriteRequest)o);
        if (channel != null) {
            defaultWriteFuture2.addListener((IoFutureListener<?>)new IoFutureListener<WriteFuture>(this) {});
        }
        return defaultWriteFuture2;
    }
    
    @Override
    public final Object getAttribute(final Object o) {
        return this.getAttribute(o, null);
    }
    
    @Override
    public final Object getAttribute(final Object o, final Object o2) {
        return this.attributes.getAttribute$1ce32dd9(o, o2);
    }
    
    @Override
    public final Object setAttribute(final Object o, final Object o2) {
        return this.attributes.setAttribute$1ce32dd9(o, o2);
    }
    
    @Override
    public final Object setAttribute(final Object o) {
        return this.setAttribute(o, Boolean.TRUE);
    }
    
    public final Object setAttributeIfAbsent(final Object o, final Object o2) {
        return this.attributes.setAttributeIfAbsent$1ce32dd9(o, o2);
    }
    
    @Override
    public final Object removeAttribute(final Object o) {
        return this.attributes.removeAttribute$518970c3(o);
    }
    
    @Override
    public final boolean containsAttribute(final Object o) {
        return this.attributes.containsAttribute$6f142e53(o);
    }
    
    public final IoSessionAttributeMap getAttributeMap() {
        return this.attributes;
    }
    
    public final void setAttributeMap(final IoSessionAttributeMap attributes) {
        this.attributes = attributes;
    }
    
    public final void setWriteRequestQueue(final WriteRequestQueue writeRequestQueue) {
        this.writeRequestQueue = new CloseAwareWriteQueue(writeRequestQueue);
    }
    
    public final boolean isReadSuspended() {
        return false;
    }
    
    public final boolean isWriteSuspended() {
        return false;
    }
    
    public final void increaseReadBytes(final long n, final long lastReadTime) {
        if (n <= 0L) {
            return;
        }
        this.readBytes += n;
        this.lastReadTime = lastReadTime;
        this.idleCountForBoth.set(0);
        this.idleCountForRead.set(0);
        if (this.service instanceof AbstractIoService) {
            ((AbstractIoService)this.service).getStatistics().increaseReadBytes(n, lastReadTime);
        }
    }
    
    public final void increaseReadMessages(final long lastReadTime) {
        ++this.readMessages;
        this.lastReadTime = lastReadTime;
        this.idleCountForBoth.set(0);
        this.idleCountForRead.set(0);
        if (this.service instanceof AbstractIoService) {
            ((AbstractIoService)this.service).getStatistics().increaseReadMessages(lastReadTime);
        }
    }
    
    public final void increaseWrittenBytes(final int n, final long lastWriteTime) {
        if (n <= 0) {
            return;
        }
        this.writtenBytes += n;
        this.lastWriteTime = lastWriteTime;
        this.idleCountForBoth.set(0);
        this.idleCountForWrite.set(0);
        if (this.service instanceof AbstractIoService) {
            ((AbstractIoService)this.service).getStatistics().increaseWrittenBytes(n, lastWriteTime);
        }
        this.increaseScheduledWriteBytes(-n);
    }
    
    public final void increaseWrittenMessages(final WriteRequest writeRequest, final long lastWriteTime) {
        final Object message;
        if ((message = writeRequest.getMessage()) instanceof IoBuffer && ((IoBuffer)message).hasRemaining()) {
            return;
        }
        ++this.writtenMessages;
        this.lastWriteTime = lastWriteTime;
        if (this.service instanceof AbstractIoService) {
            ((AbstractIoService)this.service).getStatistics().increaseWrittenMessages(lastWriteTime);
        }
        this.decreaseScheduledWriteMessages();
    }
    
    public final void increaseScheduledWriteBytes(final int n) {
        this.scheduledWriteBytes.addAndGet(n);
        if (this.service instanceof AbstractIoService) {
            ((AbstractIoService)this.service).getStatistics().increaseScheduledWriteBytes(n);
        }
    }
    
    public final void increaseScheduledWriteMessages() {
        this.scheduledWriteMessages.incrementAndGet();
        if (this.service instanceof AbstractIoService) {
            ((AbstractIoService)this.service).getStatistics().increaseScheduledWriteMessages();
        }
    }
    
    private void decreaseScheduledWriteMessages() {
        this.scheduledWriteMessages.decrementAndGet();
        if (this.service instanceof AbstractIoService) {
            ((AbstractIoService)this.service).getStatistics().decreaseScheduledWriteMessages();
        }
    }
    
    public final void decreaseScheduledBytesAndMessages(final WriteRequest writeRequest) {
        final Object message;
        if ((message = writeRequest.getMessage()) instanceof IoBuffer) {
            if (!((IoBuffer)message).hasRemaining()) {
                this.decreaseScheduledWriteMessages();
                return;
            }
            this.increaseScheduledWriteBytes(-((IoBuffer)message).remaining());
        }
        else {
            this.decreaseScheduledWriteMessages();
        }
    }
    
    @Override
    public final WriteRequestQueue getWriteRequestQueue() {
        if (this.writeRequestQueue == null) {
            throw new IllegalStateException();
        }
        return this.writeRequestQueue;
    }
    
    @Override
    public final WriteRequest getCurrentWriteRequest() {
        return this.currentWriteRequest;
    }
    
    @Override
    public final void setCurrentWriteRequest(final WriteRequest currentWriteRequest) {
        this.currentWriteRequest = currentWriteRequest;
    }
    
    public final void increaseReadBufferSize() {
        final int readBufferSize;
        if ((readBufferSize = this.getConfig().getReadBufferSize() << 1) <= this.getConfig().getMaxReadBufferSize()) {
            this.getConfig().setReadBufferSize(readBufferSize);
        }
        else {
            this.getConfig().setReadBufferSize(this.getConfig().getMaxReadBufferSize());
        }
        this.deferDecreaseReadBuffer = true;
    }
    
    public final void decreaseReadBufferSize() {
        if (this.deferDecreaseReadBuffer) {
            this.deferDecreaseReadBuffer = false;
            return;
        }
        if (this.getConfig().getReadBufferSize() > this.getConfig().getMinReadBufferSize()) {
            this.getConfig().setReadBufferSize(this.getConfig().getReadBufferSize() >>> 1);
        }
        this.deferDecreaseReadBuffer = true;
    }
    
    @Override
    public final long getLastIoTime() {
        return Math.max(this.lastReadTime, this.lastWriteTime);
    }
    
    @Override
    public final long getLastReadTime() {
        return this.lastReadTime;
    }
    
    @Override
    public final long getLastWriteTime() {
        return this.lastWriteTime;
    }
    
    @Override
    public final long getLastIdleTime(final IdleStatus idleStatus) {
        if (idleStatus == IdleStatus.BOTH_IDLE) {
            return this.lastIdleTimeForBoth;
        }
        if (idleStatus == IdleStatus.READER_IDLE) {
            return this.lastIdleTimeForRead;
        }
        if (idleStatus == IdleStatus.WRITER_IDLE) {
            return this.lastIdleTimeForWrite;
        }
        throw new IllegalArgumentException("Unknown idle status: " + idleStatus);
    }
    
    public final void increaseIdleCount(final IdleStatus idleStatus, final long lastIdleTimeForWrite) {
        if (idleStatus == IdleStatus.BOTH_IDLE) {
            this.idleCountForBoth.incrementAndGet();
            this.lastIdleTimeForBoth = lastIdleTimeForWrite;
            return;
        }
        if (idleStatus == IdleStatus.READER_IDLE) {
            this.idleCountForRead.incrementAndGet();
            this.lastIdleTimeForRead = lastIdleTimeForWrite;
            return;
        }
        if (idleStatus == IdleStatus.WRITER_IDLE) {
            this.idleCountForWrite.incrementAndGet();
            this.lastIdleTimeForWrite = lastIdleTimeForWrite;
            return;
        }
        throw new IllegalArgumentException("Unknown idle status: " + idleStatus);
    }
    
    @Override
    public final int hashCode() {
        return super.hashCode();
    }
    
    @Override
    public final boolean equals(final Object o) {
        return super.equals(o);
    }
    
    @Override
    public String toString() {
        if (!this.isConnected()) {
            if (!this.isClosing()) {
                return "Session disconnected ...";
            }
        }
        try {
            final SocketAddress remoteAddress = this.getRemoteAddress();
            final SocketAddress localAddress = this.getLocalAddress();
            if (this.service instanceof IoAcceptor) {
                return "(" + this.getIdAsString() + ": " + this.getServiceName() + ", server, " + remoteAddress + " => " + localAddress + ')';
            }
            return "(" + this.getIdAsString() + ": " + this.getServiceName() + ", client, " + localAddress + " => " + remoteAddress + ')';
        }
        catch (Exception ex) {
            return "Session is disconnecting ...";
        }
        return "Session disconnected ...";
    }
    
    private String getIdAsString() {
        String s;
        for (s = Long.toHexString(this.sessionId).toUpperCase(); s.length() < 8; s = '0' + s) {}
        return "0x" + s;
    }
    
    private String getServiceName() {
        final TransportMetadata transportMetadata;
        if ((transportMetadata = this.getTransportMetadata()) == null) {
            return "null";
        }
        return transportMetadata.getProviderName() + ' ' + transportMetadata.getName();
    }
    
    @Override
    public final IoService getService() {
        return this.service;
    }
    
    public static void notifyIdleness(final Iterator<? extends IoSession> iterator, final long n) {
        while (iterator.hasNext()) {
            final IoSession ioSession;
            notifyIdleSession0(ioSession = (IoSession)iterator.next(), n, ioSession.getConfig().getIdleTimeInMillis(IdleStatus.BOTH_IDLE), IdleStatus.BOTH_IDLE, Math.max(ioSession.getLastIoTime(), ioSession.getLastIdleTime(IdleStatus.BOTH_IDLE)));
            notifyIdleSession0(ioSession, n, ioSession.getConfig().getIdleTimeInMillis(IdleStatus.READER_IDLE), IdleStatus.READER_IDLE, Math.max(ioSession.getLastReadTime(), ioSession.getLastIdleTime(IdleStatus.READER_IDLE)));
            notifyIdleSession0(ioSession, n, ioSession.getConfig().getIdleTimeInMillis(IdleStatus.WRITER_IDLE), IdleStatus.WRITER_IDLE, Math.max(ioSession.getLastWriteTime(), ioSession.getLastIdleTime(IdleStatus.WRITER_IDLE)));
            final IoSession ioSession2;
            final long writeTimeoutInMillis;
            final WriteRequest currentWriteRequest;
            if ((writeTimeoutInMillis = (ioSession2 = ioSession).getConfig().getWriteTimeoutInMillis()) > 0L && n - ioSession2.getLastWriteTime() >= writeTimeoutInMillis && !ioSession2.getWriteRequestQueue().isEmpty(ioSession2) && (currentWriteRequest = ioSession2.getCurrentWriteRequest()) != null) {
                ioSession2.setCurrentWriteRequest(null);
                final WriteTimeoutException exception = new WriteTimeoutException(currentWriteRequest);
                currentWriteRequest.getFuture().setException(exception);
                ioSession2.getFilterChain().fireExceptionCaught(exception);
                ioSession2.close(true);
            }
        }
    }
    
    private static void notifyIdleSession0(final IoSession ioSession, final long n, final long n2, final IdleStatus idleStatus, final long n3) {
        if (n2 > 0L && n3 != 0L && n - n3 >= n2) {
            ioSession.getFilterChain().fireSessionIdle(idleStatus);
        }
    }
    
    static {
        READY_READ_FUTURES_KEY = new AttributeKey(AbstractIoSession.class, "readyReadFutures");
        WAITING_READ_FUTURES_KEY = new AttributeKey(AbstractIoSession.class, "waitingReadFutures");
        SCHEDULED_COUNTER_RESETTER = new IoFutureListener<CloseFuture>() {};
        CLOSE_REQUEST = new DefaultWriteRequest(new Object());
        AbstractIoSession.idGenerator = new AtomicLong(0L);
    }
    
    final class CloseAwareWriteQueue implements WriteRequestQueue
    {
        private final WriteRequestQueue queue;
        
        public CloseAwareWriteQueue(final WriteRequestQueue queue) {
            this.queue = queue;
        }
        
        @Override
        public final synchronized WriteRequest poll(final IoSession ioSession) {
            WriteRequest poll;
            if ((poll = this.queue.poll(ioSession)) == AbstractIoSession.CLOSE_REQUEST) {
                AbstractIoSession.this.close();
                this.dispose(ioSession);
                poll = null;
            }
            return poll;
        }
        
        @Override
        public final void offer(final IoSession ioSession, final WriteRequest writeRequest) {
            this.queue.offer(ioSession, writeRequest);
        }
        
        @Override
        public final boolean isEmpty(final IoSession ioSession) {
            return this.queue.isEmpty(ioSession);
        }
        
        @Override
        public final void dispose(final IoSession ioSession) {
            this.queue.dispose(ioSession);
        }
    }
}
