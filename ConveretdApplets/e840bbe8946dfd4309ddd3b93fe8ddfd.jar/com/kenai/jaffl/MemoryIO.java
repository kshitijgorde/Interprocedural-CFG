// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl;

import java.nio.charset.Charset;
import java.nio.ByteBuffer;

public abstract class MemoryIO implements Pointer
{
    public static final MemoryIO allocate(final int size) {
        return FFIProvider.getProvider().getMemoryManager().allocate(size);
    }
    
    public static final MemoryIO allocateDirect(final int size) {
        return FFIProvider.getProvider().getMemoryManager().allocateDirect(size);
    }
    
    public static final MemoryIO allocateDirect(final int size, final boolean clear) {
        return FFIProvider.getProvider().getMemoryManager().allocateDirect(size, clear);
    }
    
    public static final MemoryIO wrap(final Pointer ptr) {
        return FFIProvider.getProvider().getMemoryManager().wrap(ptr);
    }
    
    public static final MemoryIO wrap(final Pointer ptr, final int size) {
        return FFIProvider.getProvider().getMemoryManager().wrap(ptr, size);
    }
    
    public static final MemoryIO wrap(final ByteBuffer buffer) {
        return FFIProvider.getProvider().getMemoryManager().wrap(buffer);
    }
    
    public abstract byte getByte(final long p0);
    
    public abstract short getShort(final long p0);
    
    public abstract int getInt(final long p0);
    
    public abstract long getLong(final long p0);
    
    public abstract float getFloat(final long p0);
    
    public abstract double getDouble(final long p0);
    
    public abstract void putByte(final long p0, final byte p1);
    
    public abstract void putShort(final long p0, final short p1);
    
    public abstract void putInt(final long p0, final int p1);
    
    public abstract void putLong(final long p0, final long p1);
    
    public abstract void putFloat(final long p0, final float p1);
    
    public abstract void putDouble(final long p0, final double p1);
    
    public abstract void get(final long p0, final byte[] p1, final int p2, final int p3);
    
    public abstract void put(final long p0, final byte[] p1, final int p2, final int p3);
    
    public abstract void get(final long p0, final short[] p1, final int p2, final int p3);
    
    public abstract void put(final long p0, final short[] p1, final int p2, final int p3);
    
    public abstract void get(final long p0, final int[] p1, final int p2, final int p3);
    
    public abstract void put(final long p0, final int[] p1, final int p2, final int p3);
    
    public abstract void get(final long p0, final long[] p1, final int p2, final int p3);
    
    public abstract void put(final long p0, final long[] p1, final int p2, final int p3);
    
    public abstract void get(final long p0, final float[] p1, final int p2, final int p3);
    
    public abstract void put(final long p0, final float[] p1, final int p2, final int p3);
    
    public abstract void get(final long p0, final double[] p1, final int p2, final int p3);
    
    public abstract void put(final long p0, final double[] p1, final int p2, final int p3);
    
    public abstract MemoryIO getMemoryIO(final long p0);
    
    public abstract MemoryIO getMemoryIO(final long p0, final long p1);
    
    public abstract Pointer getPointer(final long p0);
    
    public abstract void putPointer(final long p0, final Pointer p1);
    
    public abstract int indexOf(final long p0, final byte p1);
    
    public abstract int indexOf(final long p0, final byte p1, final int p2);
    
    public abstract long getAddress(final long p0);
    
    public abstract void putAddress(final long p0, final long p1);
    
    public abstract void putAddress(final long p0, final Address p1);
    
    public abstract long getNativeLong(final long p0);
    
    public abstract void putNativeLong(final long p0, final long p1);
    
    public abstract String getString(final long p0, final int p1, final Charset p2);
    
    public abstract String getString(final long p0);
    
    public abstract void putString(final long p0, final String p1, final int p2, final Charset p3);
    
    public abstract MemoryIO slice(final long p0);
    
    public abstract MemoryIO slice(final long p0, final long p1);
    
    public abstract void transferTo(final long p0, final MemoryIO p1, final long p2, final long p3);
    
    public abstract void setMemory(final long p0, final long p1, final byte p2);
    
    public abstract boolean isDirect();
    
    public abstract long address();
}
