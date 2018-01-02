// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.buffer;

import java.nio.ByteOrder;
import java.nio.ByteBuffer;

final class SimpleBufferAllocator$SimpleBuffer extends AbstractIoBuffer
{
    private ByteBuffer buf;
    
    protected SimpleBufferAllocator$SimpleBuffer(final IoBufferAllocator ioBufferAllocator, final ByteBuffer buf) {
        super(ioBufferAllocator, buf.capacity());
        (this.buf = buf).order(ByteOrder.BIG_ENDIAN);
    }
    
    @Override
    public final ByteBuffer buf() {
        return this.buf;
    }
    
    @Override
    protected final void buf(final ByteBuffer buf) {
        this.buf = buf;
    }
}
