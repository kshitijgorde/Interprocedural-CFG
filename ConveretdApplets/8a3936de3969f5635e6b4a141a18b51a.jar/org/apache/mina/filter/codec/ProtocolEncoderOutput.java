// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.filter.codec;

import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.mina.core.buffer.IoBuffer;
import java.util.Queue;

public abstract class ProtocolEncoderOutput
{
    private final Queue<Object> messageQueue;
    
    public void write(final Object o) {
        if (o instanceof IoBuffer) {
            final IoBuffer ioBuffer;
            if (!(ioBuffer = (IoBuffer)o).hasRemaining()) {
                throw new IllegalArgumentException("buf is empty. Forgot to call flip()?");
            }
            this.messageQueue.offer(ioBuffer);
        }
        else {
            this.messageQueue.offer(o);
        }
    }
    
    public ProtocolEncoderOutput() {
        this.messageQueue = new ConcurrentLinkedQueue<Object>();
    }
    
    public Queue<Object> getMessageQueue() {
        return this.messageQueue;
    }
}
