// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.ffi;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public interface MemoryIO
{
    boolean isNull();
    
    boolean isDirect();
    
    ByteOrder order();
    
    MemoryIO slice(final long p0);
    
    MemoryIO slice(final long p0, final long p1);
    
    MemoryIO dup();
    
    ByteBuffer asByteBuffer();
    
    byte getByte(final long p0);
    
    short getShort(final long p0);
    
    int getInt(final long p0);
    
    long getLong(final long p0);
    
    long getNativeLong(final long p0);
    
    float getFloat(final long p0);
    
    double getDouble(final long p0);
    
    long getAddress(final long p0);
    
    DirectMemoryIO getMemoryIO(final long p0);
    
    void putByte(final long p0, final byte p1);
    
    void putShort(final long p0, final short p1);
    
    void putInt(final long p0, final int p1);
    
    void putLong(final long p0, final long p1);
    
    void putNativeLong(final long p0, final long p1);
    
    void putFloat(final long p0, final float p1);
    
    void putDouble(final long p0, final double p1);
    
    void putMemoryIO(final long p0, final MemoryIO p1);
    
    void putAddress(final long p0, final long p1);
    
    void get(final long p0, final byte[] p1, final int p2, final int p3);
    
    void put(final long p0, final byte[] p1, final int p2, final int p3);
    
    void get(final long p0, final short[] p1, final int p2, final int p3);
    
    void put(final long p0, final short[] p1, final int p2, final int p3);
    
    void get(final long p0, final int[] p1, final int p2, final int p3);
    
    void put(final long p0, final int[] p1, final int p2, final int p3);
    
    void get(final long p0, final long[] p1, final int p2, final int p3);
    
    void put(final long p0, final long[] p1, final int p2, final int p3);
    
    void get(final long p0, final float[] p1, final int p2, final int p3);
    
    void put(final long p0, final float[] p1, final int p2, final int p3);
    
    void get(final long p0, final double[] p1, final int p2, final int p3);
    
    void put(final long p0, final double[] p1, final int p2, final int p3);
    
    int indexOf(final long p0, final byte p1);
    
    int indexOf(final long p0, final byte p1, final int p2);
    
    void setMemory(final long p0, final long p1, final byte p2);
    
    byte[] getZeroTerminatedByteArray(final long p0);
    
    byte[] getZeroTerminatedByteArray(final long p0, final int p1);
    
    void putZeroTerminatedByteArray(final long p0, final byte[] p1, final int p2, final int p3);
}
