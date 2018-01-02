// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.buffer;

import java.nio.ByteBuffer;

public class IoBufferAllocator
{
    public IoBuffer allocate(final int n, final boolean b) {
        return this.wrap(this.allocateNioBuffer(n, b));
    }
    
    public ByteBuffer allocateNioBuffer(final int n, final boolean b) {
        ByteBuffer byteBuffer;
        if (b) {
            byteBuffer = ByteBuffer.allocateDirect(n);
        }
        else {
            byteBuffer = ByteBuffer.allocate(n);
        }
        return byteBuffer;
    }
    
    public IoBuffer wrap(final ByteBuffer byteBuffer) {
        return new SimpleBufferAllocator$SimpleBuffer(this, byteBuffer);
    }
}
