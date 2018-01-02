// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.transport.socket.nio;

import java.net.SocketAddress;
import org.apache.mina.core.session.AbstractIoSession;
import org.apache.mina.core.service.IoProcessor;
import java.nio.channels.SelectionKey;
import java.util.Collection;
import java.util.Iterator;
import org.apache.mina.core.service.TransportMetadata;
import org.apache.mina.core.service.IoService;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.transport.socket.DefaultSocketSessionConfig;
import java.nio.channels.Selector;
import org.apache.mina.core.service.IoConnector;
import java.nio.channels.SocketChannel;
import org.apache.mina.core.polling.AbstractPollingIoConnector;

public final class NioSocketConnector extends AbstractPollingIoConnector<NioSession, SocketChannel> implements IoConnector
{
    private volatile Selector selector;
    
    public NioSocketConnector() {
        super(new DefaultSocketSessionConfig(), NioProcessor.class);
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
    
    @Override
    protected final Iterator<SocketChannel> allHandles() {
        return new SocketChannelIterator(this.selector.keys());
    }
    
    @Override
    protected final int select(final int n) throws Exception {
        return this.selector.select(n);
    }
    
    @Override
    protected final Iterator<SocketChannel> selectedHandles() {
        return new SocketChannelIterator(this.selector.selectedKeys());
    }
    
    @Override
    protected final void wakeup() {
        this.selector.wakeup();
    }
    
    static final class SocketChannelIterator implements Iterator<SocketChannel>
    {
        private final Iterator<SelectionKey> i;
        
        private SocketChannelIterator(final Collection<SelectionKey> collection, final byte b) {
            this.i = collection.iterator();
        }
        
        @Override
        public final boolean hasNext() {
            return this.i.hasNext();
        }
        
        @Override
        public final void remove() {
            this.i.remove();
        }
    }
}
