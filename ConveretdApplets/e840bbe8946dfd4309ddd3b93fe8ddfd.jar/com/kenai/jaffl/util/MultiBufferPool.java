// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.util;

import java.util.Iterator;
import java.util.List;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;

public class MultiBufferPool implements BufferPool
{
    private final int maxBufferSize;
    private final int maxItemsPerSize;
    private final int maxPoolIndex;
    private SimpleBufferPool[] pools;
    
    public MultiBufferPool(final int maxBufferSize, final int maxItemsPerSize, final boolean threadSafe) {
        this.maxBufferSize = maxBufferSize;
        this.maxItemsPerSize = maxItemsPerSize;
        this.maxPoolIndex = getSizeIndex(maxBufferSize);
        this.pools = new SimpleBufferPool[this.maxPoolIndex + 1];
        for (int i = 0; i <= this.maxPoolIndex; ++i) {
            if (threadSafe) {
                this.pools[i] = new SynchronizedPool(1 << i, maxItemsPerSize);
            }
            else {
                this.pools[i] = new SimpleBufferPool(1 << i, maxItemsPerSize);
            }
        }
    }
    
    public MultiBufferPool(final int maxBufferSize, final int maxItemsPerSize) {
        this(maxBufferSize, maxItemsPerSize, false);
    }
    
    private static final int getSizeIndex(final int size) {
        int start = 0;
        int ssize = size;
        if (ssize > 65535) {
            start += 16;
            ssize >>= 16;
        }
        if (ssize > 255) {
            start += 8;
            ssize >>= 8;
        }
        if (ssize > 15) {
            start += 4;
        }
        for (int i = start; i < 32; ++i) {
            if (1 << i >= size) {
                return i;
            }
        }
        return 32;
    }
    
    public ByteBuffer get(final int size) {
        final int index = getSizeIndex(size);
        if (index <= this.maxPoolIndex) {
            return this.pools[index].get(size);
        }
        return ByteBuffer.allocateDirect(size).order(ByteOrder.nativeOrder());
    }
    
    public void put(final ByteBuffer buf) {
        final int index = getSizeIndex(buf.capacity());
        if (index <= this.maxPoolIndex) {
            this.pools[index].put(buf);
        }
    }
    
    public void putAll(final List<ByteBuffer> list) {
        for (final ByteBuffer buf : list) {
            this.put(buf);
        }
    }
    
    static class SynchronizedPool extends SimpleBufferPool
    {
        public SynchronizedPool(final int bufferSize, final int poolSize) {
            super(bufferSize, poolSize);
        }
        
        public synchronized ByteBuffer get(final int size) {
            return super.get(size);
        }
        
        public synchronized void put(final ByteBuffer buf) {
            super.put(buf);
        }
    }
}
