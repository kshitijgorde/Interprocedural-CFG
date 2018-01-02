import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class110
{
    static int anInt944;
    static String[] aStringArray945;
    static int anInt946;
    
    @Override
    public final String toString() {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "haa.toString()");
        }
    }
    
    public static void method2086(final byte b) {
        try {
            if (b == 54) {
                Class110.aStringArray945 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "haa.B(" + b + ')');
        }
    }
    
    static final ha method2087(final Canvas canvas, final int n, final int n2, final d d) {
        try {
            if (n != 2) {
                method2087(null, -100, 122, null);
            }
            return new ha_Sub1(canvas, d, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "haa.C(" + ((canvas != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + ((d != null) ? "{...}" : "null") + ')');
        }
    }
    
    final int method2088(final int n, final int n2, final byte b) {
        try {
            final int n3 = (Class98_Sub25.anInt4024 <= n2) ? n2 : Class98_Sub25.anInt4024;
            if (Class358.aClass110_3030 == this) {
                return 0;
            }
            if (b != -56) {
                Class110.anInt944 = -104;
            }
            if (ha.aClass110_941 == this) {
                return -n + n3;
            }
            if (this == Class314.aClass110_2688) {
                return (n3 - n) / 2;
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "haa.A(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    static {
        Class110.anInt944 = 0;
        Class110.aStringArray945 = new String[100];
    }
}
