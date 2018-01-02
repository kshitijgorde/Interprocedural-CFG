// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.transport.socket.nio;

import org.apache.mina.core.session.SessionState;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.ByteChannel;
import org.apache.mina.core.session.AbstractIoSession;
import java.nio.channels.WritableByteChannel;
import org.apache.mina.core.file.FileRegion;
import org.apache.mina.core.buffer.IoBuffer;
import java.util.Set;
import java.util.Iterator;
import java.io.IOException;
import org.apache.mina.core.RuntimeIoException;
import java.util.concurrent.Executor;
import java.nio.channels.Selector;
import org.apache.mina.core.polling.AbstractPollingIoProcessor;

public final class NioProcessor extends AbstractPollingIoProcessor<NioSession>
{
    private Selector selector;
    
    public NioProcessor(final Executor executor) {
        super(executor);
        try {
            this.selector = Selector.open();
        }
        catch (IOException ex) {
            throw new RuntimeIoException("Failed to open a selector.", ex);
        }
    }
    
    @Override
    protected final void doDispose() throws Exception {
        this.selector.close();
    }
    
    @Override
    protected final int select(final long n) throws Exception {
        return this.selector.select(1000L);
    }
    
    @Override
    protected final boolean isSelectorEmpty() {
        return this.selector.keys().isEmpty();
    }
    
    @Override
    protected final void wakeup() {
        this.wakeupCalled.getAndSet(true);
        this.selector.wakeup();
    }
    
    @Override
    protected final Iterator<NioSession> allSessions() {
        return new IoSessionIterator<NioSession>(this.selector.keys());
    }
    
    @Override
    protected final Iterator<NioSession> selectedSessions() {
        return new IoSessionIterator<NioSession>(this.selector.selectedKeys());
    }
    
    private static int write(final NioSession nioSession, final IoBuffer ioBuffer, final int n) throws Exception {
        if (ioBuffer.remaining() <= n) {
            return nioSession.getChannel().write(ioBuffer.buf());
        }
        final int limit = ioBuffer.limit();
        ioBuffer.limit(ioBuffer.position() + n);
        try {
            return nioSession.getChannel().write(ioBuffer.buf());
        }
        finally {
            ioBuffer.limit(limit);
        }
    }
    
    private static int transferFile(final NioSession nioSession, final FileRegion fileRegion, final int n) throws Exception {
        try {
            return (int)fileRegion.getFileChannel().transferTo(fileRegion.getPosition(), n, nioSession.getChannel());
        }
        catch (IOException ex2) {
            final IOException ex = ex2;
            final String message;
            if ((message = ex2.getMessage()) != null && message.contains("temporarily unavailable")) {
                return 0;
            }
            throw ex;
        }
    }
    
    public static final class IoSessionIterator<NioSession> implements Iterator<NioSession>
    {
        private final Iterator<SelectionKey> iterator;
        
        private IoSessionIterator(final Set<SelectionKey> set, final byte b) {
            this.iterator = set.iterator();
        }
        
        @Override
        public final boolean hasNext() {
            return this.iterator.hasNext();
        }
        
        @Override
        public final NioSession next() {
            return (NioSession)this.iterator.next().attachment();
        }
        
        @Override
        public final void remove() {
            this.iterator.remove();
        }
    }
}
