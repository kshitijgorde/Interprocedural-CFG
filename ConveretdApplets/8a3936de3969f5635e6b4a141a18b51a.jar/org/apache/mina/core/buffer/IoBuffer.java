// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.buffer;

import java.nio.ByteOrder;
import java.nio.ByteBuffer;

public abstract class IoBuffer implements Comparable<IoBuffer>
{
    private static IoBufferAllocator allocator;
    private static boolean useDirectBuffer;
    
    public static IoBufferAllocator getAllocator() {
        return IoBuffer.allocator;
    }
    
    public static void setAllocator(final IoBufferAllocator allocator) {
        if (allocator == null) {
            throw new IllegalArgumentException("allocator");
        }
        final IoBufferAllocator allocator2 = IoBuffer.allocator;
        IoBuffer.allocator = allocator;
    }
    
    public static IoBuffer allocate(int n) {
        final int n2 = n;
        final boolean b = false;
        n = n2;
        if (n2 < 0) {
            throw new IllegalArgumentException("capacity: " + n);
        }
        return IoBuffer.allocator.allocate(n, b);
    }
    
    public static IoBuffer wrap(final byte[] array) {
        return IoBuffer.allocator.wrap(ByteBuffer.wrap(array));
    }
    
    public abstract ByteBuffer buf();
    
    public abstract int capacity();
    
    public abstract boolean isAutoExpand();
    
    public abstract IoBuffer setAutoExpand(final boolean p0);
    
    public abstract int position();
    
    public abstract IoBuffer position(final int p0);
    
    public abstract int limit();
    
    public abstract IoBuffer limit(final int p0);
    
    public abstract IoBuffer mark();
    
    public abstract IoBuffer reset();
    
    public abstract IoBuffer flip();
    
    public abstract int remaining();
    
    public abstract boolean hasRemaining();
    
    public abstract byte get();
    
    public abstract byte get(final int p0);
    
    public abstract IoBuffer put(final IoBuffer p0);
    
    public abstract IoBuffer compact();
    
    public abstract ByteOrder order();
    
    public abstract IoBuffer order(final ByteOrder p0);
    
    public abstract String getHexDump();
    
    public abstract Object getObject(final ClassLoader p0) throws ClassNotFoundException;
    
    public abstract IoBuffer putObject(final Object p0);
    
    public abstract boolean prefixedDataAvailable(final int p0, final int p1);
    
    static {
        IoBuffer.allocator = new IoBufferAllocator();
        IoBuffer.useDirectBuffer = false;
    }
}
