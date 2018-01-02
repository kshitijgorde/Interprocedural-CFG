// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.util;

import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.List;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class SimpleBufferPool implements BufferPool
{
    private final int bufferSize;
    private final int poolSize;
    private final BufferPool parent;
    private final ArrayList<ByteBuffer> list;
    
    public SimpleBufferPool(final int bufferSize, final int poolSize) {
        this(new DefaultPool(), bufferSize, poolSize);
    }
    
    public SimpleBufferPool(final BufferPool parent, final int bufferSize, final int poolSize) {
        this.parent = parent;
        this.bufferSize = bufferSize;
        this.poolSize = poolSize;
        this.list = new ArrayList<ByteBuffer>(poolSize);
    }
    
    public ByteBuffer get(final int size) {
        if (size <= this.bufferSize && !this.list.isEmpty()) {
            final ByteBuffer buf = this.list.remove(this.list.size() - 1);
            buf.rewind().limit(size);
            return buf;
        }
        final ByteBuffer buf = this.parent.get(Math.max(size, this.bufferSize));
        buf.rewind().limit(size);
        return buf;
    }
    
    public void put(final ByteBuffer buf) {
        if (this.list.size() < this.poolSize && buf.capacity() == this.bufferSize) {
            this.list.add(buf);
        }
        else {
            this.parent.put(buf);
        }
    }
    
    public void putAll(final List<ByteBuffer> list) {
        for (final ByteBuffer buf : list) {
            this.put(buf);
        }
    }
    
    static class DefaultPool implements BufferPool
    {
        public ByteBuffer get(final int size) {
            return ByteBuffer.allocateDirect(size).order(ByteOrder.nativeOrder());
        }
        
        public void put(final ByteBuffer buffer) {
        }
        
        public void putAll(final List<ByteBuffer> list) {
        }
    }
}
