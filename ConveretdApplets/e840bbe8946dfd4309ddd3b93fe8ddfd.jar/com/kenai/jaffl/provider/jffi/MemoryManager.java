// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import com.kenai.jaffl.Pointer;
import com.kenai.jaffl.MemoryIO;

public class MemoryManager implements com.kenai.jaffl.provider.MemoryManager
{
    public MemoryIO allocate(final int size) {
        return new ArrayMemoryIO(size);
    }
    
    public MemoryIO allocateDirect(final int size) {
        return new BoundedDirectMemoryIO(new AllocatedDirectMemoryIO(size, false), 0L, size);
    }
    
    public MemoryIO allocateDirect(final int size, final boolean clear) {
        return new BoundedDirectMemoryIO(new AllocatedDirectMemoryIO(size, clear), 0L, size);
    }
    
    public MemoryIO wrap(final Pointer ptr) {
        if (ptr instanceof MemoryIO) {
            return (MemoryIO)ptr;
        }
        if (ptr.isDirect()) {
            return MemoryUtil.newMemoryIO(ptr.address());
        }
        throw new UnsupportedOperationException("Unsupported Pointer type: " + ptr.getClass());
    }
    
    public MemoryIO wrap(final Pointer ptr, final int size) {
        if (ptr.isDirect()) {
            return MemoryUtil.newMemoryIO(ptr.address(), size);
        }
        throw new UnsupportedOperationException("Unsupported Pointer type: " + ptr.getClass());
    }
    
    public MemoryIO wrap(final ByteBuffer buffer) {
        return new ByteBufferMemoryIO(buffer);
    }
    
    public Pointer getBufferPointer(final Buffer buffer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
