// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl;

import java.nio.charset.Charset;

public interface Pointer
{
    public static final int SIZE = Platform.getPlatform().addressSize();
    
    byte getByte(final long p0);
    
    short getShort(final long p0);
    
    int getInt(final long p0);
    
    long getLong(final long p0);
    
    float getFloat(final long p0);
    
    double getDouble(final long p0);
    
    void putByte(final long p0, final byte p1);
    
    void putShort(final long p0, final short p1);
    
    void putInt(final long p0, final int p1);
    
    void putLong(final long p0, final long p1);
    
    void putFloat(final long p0, final float p1);
    
    void putDouble(final long p0, final double p1);
    
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
    
    Pointer getPointer(final long p0);
    
    void putPointer(final long p0, final Pointer p1);
    
    String getString(final long p0);
    
    String getString(final long p0, final int p1, final Charset p2);
    
    void putString(final long p0, final String p1, final int p2, final Charset p3);
    
    long address();
    
    boolean isDirect();
}
