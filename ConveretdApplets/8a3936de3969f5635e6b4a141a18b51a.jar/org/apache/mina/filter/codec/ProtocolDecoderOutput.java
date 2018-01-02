// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.filter.codec;

import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.filterchain.IoFilter;
import java.util.Queue;

public abstract class ProtocolDecoderOutput
{
    private final Queue<Object> messageQueue;
    
    public void write(final Object o) {
        if (o == null) {
            throw new IllegalArgumentException("message");
        }
        this.messageQueue.add(o);
    }
    
    public abstract void flush(final IoFilter.NextFilter p0, final IoSession p1);
    
    public ProtocolDecoderOutput() {
        this.messageQueue = new ConcurrentLinkedQueue<Object>();
    }
    
    public Queue<Object> getMessageQueue() {
        return this.messageQueue;
    }
}
