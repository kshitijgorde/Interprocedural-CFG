// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl;

public final class NativeLong extends Number implements Comparable<NativeLong>
{
    public static final int SIZE;
    public static final int SHIFT;
    public static final long MASK;
    private static final NativeLong ZERO;
    private static final NativeLong ONE;
    private static final NativeLong MINUS_ONE;
    private final long value;
    
    public NativeLong(final long value) {
        this.value = value;
    }
    
    public final int intValue() {
        return (int)this.value;
    }
    
    public final long longValue() {
        return this.value;
    }
    
    public final float floatValue() {
        return this.value;
    }
    
    public final double doubleValue() {
        return this.value;
    }
    
    public final int hashCode() {
        return (int)(this.value ^ this.value >>> 32);
    }
    
    public final boolean equals(final Object obj) {
        return obj instanceof NativeLong && this.value == ((NativeLong)obj).value;
    }
    
    public String toString() {
        return String.valueOf(this.value);
    }
    
    public final int compareTo(final NativeLong other) {
        return (this.value < other.value) ? -1 : ((this.value > other.value) ? 1 : 0);
    }
    
    private static final NativeLong _valueOf(final long value) {
        return (value >= -128L && value <= 127L) ? Cache.cache[128 + (int)value] : new NativeLong(value);
    }
    
    public static final NativeLong valueOf(final long value) {
        return (value == 0L) ? NativeLong.ZERO : ((value == 1L) ? NativeLong.ONE : ((value == -1L) ? NativeLong.MINUS_ONE : _valueOf(value)));
    }
    
    public static final NativeLong valueOf(final int value) {
        return valueOf((long)value);
    }
    
    static {
        SIZE = Platform.getPlatform().longSize();
        SHIFT = ((NativeLong.SIZE == 32) ? 2 : 3);
        MASK = ((NativeLong.SIZE == 32) ? 4294967295L : -1L);
        ZERO = new NativeLong(0L);
        ONE = new NativeLong(1L);
        MINUS_ONE = new NativeLong(-1L);
    }
    
    private static final class Cache
    {
        static final NativeLong[] cache;
        
        static {
            cache = new NativeLong[256];
            for (int i = 0; i < Cache.cache.length; ++i) {
                Cache.cache[i] = new NativeLong(i - 128);
            }
            Cache.cache[128] = NativeLong.ZERO;
            Cache.cache[129] = NativeLong.ONE;
            Cache.cache[127] = NativeLong.MINUS_ONE;
        }
    }
}
