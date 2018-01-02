// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import com.kenai.jaffl.provider.NullMemoryIO;
import com.kenai.jaffl.MemoryIO;
import com.kenai.jaffl.Pointer;

public final class MemoryUtil
{
    static final Pointer newPointer(final long ptr) {
        return (ptr != 0L) ? new DirectMemoryIO(ptr) : null;
    }
    
    static final MemoryIO newMemoryIO(final long ptr) {
        return (ptr != 0L) ? new DirectMemoryIO(ptr) : NullMemoryIO.INSTANCE;
    }
    
    static final MemoryIO newMemoryIO(final long ptr, final long size) {
        return (ptr != 0L) ? new BoundedDirectMemoryIO(new DirectMemoryIO(ptr), 0L, size) : NullMemoryIO.INSTANCE;
    }
    
    static final long getAddress(final MemoryIO ptr) {
        if (ptr == null) {
            return 0L;
        }
        if (ptr instanceof JFFIPointer) {
            return ((JFFIPointer)ptr).address;
        }
        if (ptr instanceof DirectMemoryIO) {
            return ((DirectMemoryIO)ptr).address;
        }
        throw new IllegalArgumentException("attempted to get address of non-direct memory. " + ptr.getClass());
    }
}
