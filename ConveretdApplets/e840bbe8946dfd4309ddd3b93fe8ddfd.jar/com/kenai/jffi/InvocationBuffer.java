// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

import java.nio.Buffer;

public interface InvocationBuffer
{
    void putByte(final int p0);
    
    void putShort(final int p0);
    
    void putInt(final int p0);
    
    void putLong(final long p0);
    
    void putFloat(final float p0);
    
    void putDouble(final double p0);
    
    void putAddress(final long p0);
    
    void putArray(final byte[] p0, final int p1, final int p2, final int p3);
    
    void putArray(final short[] p0, final int p1, final int p2, final int p3);
    
    void putArray(final int[] p0, final int p1, final int p2, final int p3);
    
    void putArray(final long[] p0, final int p1, final int p2, final int p3);
    
    void putArray(final float[] p0, final int p1, final int p2, final int p3);
    
    void putArray(final double[] p0, final int p1, final int p2, final int p3);
    
    void putDirectBuffer(final Buffer p0, final int p1, final int p2);
    
    void putStruct(final byte[] p0, final int p1);
    
    void putStruct(final long p0);
}
