// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.transport.socket.nio;

import java.nio.channels.ByteChannel;
import org.apache.mina.core.filterchain.DefaultIoFilterChain;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.filterchain.IoFilterChain;
import java.nio.channels.SelectionKey;
import java.nio.channels.Channel;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.session.AbstractIoSession;

public abstract class NioSession extends AbstractIoSession
{
    private IoProcessor<NioSession> processor;
    protected final Channel channel;
    private SelectionKey key;
    private final IoFilterChain filterChain;
    
    protected NioSession(final IoProcessor<NioSession> processor, final IoService ioService, final Channel channel) {
        super(ioService);
        this.channel = channel;
        this.processor = processor;
        this.filterChain = new DefaultIoFilterChain(this);
    }
    
    abstract ByteChannel getChannel();
    
    @Override
    public final IoFilterChain getFilterChain() {
        return this.filterChain;
    }
    
    final SelectionKey getSelectionKey() {
        return this.key;
    }
    
    final void setSelectionKey(final SelectionKey key) {
        this.key = key;
    }
    
    @Override
    public final IoProcessor<NioSession> getProcessor() {
        return this.processor;
    }
}
