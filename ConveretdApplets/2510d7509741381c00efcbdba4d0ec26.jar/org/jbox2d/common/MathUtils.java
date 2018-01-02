// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.common;

public class MathUtils
{
    public static final float clamp(final float a, final float low, final float high) {
        return Math.max(low, Math.min(a, high));
    }
    
    public static final Vec2 clamp(final Vec2 a, final Vec2 low, final Vec2 high) {
        return Vec2.max(low, Vec2.min(a, high));
    }
    
    public static final int nextPowerOfTwo(int x) {
        x |= x >> 1;
        x |= x >> 2;
        x |= x >> 4;
        x |= x >> 8;
        x |= x >> 16;
        return x + 1;
    }
    
    public static final boolean isPowerOfTwo(final int x) {
        return x > 0 && (x & x - 1) == 0x0;
    }
}
