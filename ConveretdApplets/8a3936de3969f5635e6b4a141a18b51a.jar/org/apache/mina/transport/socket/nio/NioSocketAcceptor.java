// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.transport.socket.nio;

import java.nio.channels.SocketChannel;
import org.apache.mina.core.session.AbstractIoSession;
import org.apache.mina.core.service.IoProcessor;
import java.nio.channels.SelectionKey;
import java.util.Collection;
import java.util.Iterator;
import java.net.ServerSocket;
import java.net.SocketAddress;
import org.apache.mina.core.service.TransportMetadata;
import org.apache.mina.core.service.IoService;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.transport.socket.DefaultSocketSessionConfig;
import java.nio.channels.Selector;
import org.apache.mina.transport.socket.SocketAcceptor;
import java.nio.channels.ServerSocketChannel;
import org.apache.mina.core.polling.AbstractPollingIoAcceptor;

public final class NioSocketAcceptor extends AbstractPollingIoAcceptor<NioSession, ServerSocketChannel> implements SocketAcceptor
{
    private int backlog;
    private boolean reuseAddress;
    private volatile Selector selector;
    
    public NioSocketAcceptor() {
        super(new DefaultSocketSessionConfig(), NioProcessor.class);
        this.backlog = 50;
        this.reuseAddress = false;
        ((DefaultSocketSessionConfig)super.getSessionConfig()).init(this);
    }
    
    @Override
    protected final void init() throws Exception {
        this.selector = Selector.open();
    }
    
    @Override
    protected final void destroy() throws Exception {
        if (this.selector != null) {
            this.selector.close();
        }
    }
    
    @Override
    public final TransportMetadata getTransportMetadata() {
        return NioSocketSession.METADATA;
    }
    
    private ServerSocketChannel open(final SocketAddress socketAddress) throws Exception {
        final ServerSocketChannel open = ServerSocketChannel.open();
        try {
            open.configureBlocking(false);
            final ServerSocket socket;
            (socket = open.socket()).setReuseAddress(false);
            socket.bind(socketAddress, this.backlog);
            open.register(this.selector, 16);
        }
        finally {
            this.close(open);
        }
        return open;
    }
    
    @Override
    protected final int select() throws Exception {
        return this.selector.select();
    }
    
    @Override
    protected final Iterator<ServerSocketChannel> selectedHandles() {
        return new ServerSocketChannelIterator(this.selector.selectedKeys());
    }
    
    private void close(final ServerSocketChannel serverSocketChannel) throws Exception {
        final SelectionKey key;
        if ((key = serverSocketChannel.keyFor(this.selector)) != null) {
            key.cancel();
        }
        serverSocketChannel.close();
    }
    
    @Override
    protected final void wakeup() {
        this.selector.wakeup();
    }
    
    static final class ServerSocketChannelIterator implements Iterator<ServerSocketChannel>
    {
        private final Iterator<SelectionKey> iterator;
        
        private ServerSocketChannelIterator(final Collection<SelectionKey> collection, final byte b) {
            this.iterator = collection.iterator();
        }
        
        @Override
        public final boolean hasNext() {
            return this.iterator.hasNext();
        }
        
        @Override
        public final void remove() {
            this.iterator.remove();
        }
    }
}
