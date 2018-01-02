// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub46_Sub15 extends Class98_Sub46
{
    static int anInt6039;
    short[][] aShortArrayArray6040;
    static IncomingOpcode aClass58_6041;
    double aDouble6042;
    static Class232 aClass232_6043;
    
    final long method1608(final int n) {
        try {
            if (n != 2) {
                return -37L;
            }
            return this.aShortArrayArray6040[0].length | this.aShortArrayArray6040.length << -2104658688;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jba.A(" + n + ')');
        }
    }
    
    static final void method1609(final int n, final int n2) {
        try {
            if (Class85.method837(n, 11) && n2 == -12889) {
                final Class293[] array = Class159.aClass293ArrayArray1252[n];
                for (int n3 = 0; ~array.length < ~n3; ++n3) {
                    final Class293 class293 = array[n3];
                    if (class293 != null) {
                        class293.anInt2303 = 0;
                        class293.anInt2312 = 0;
                        class293.anInt2287 = 1;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jba.C(" + n + ',' + n2 + ')');
        }
    }
    
    static final int method1610(final boolean b, final byte b2) {
        try {
            if (Class2.anIntArray70 == null) {
                return 0;
            }
            if (!b && Class242.aClass244Array1851 != null) {
                return Class2.anIntArray70.length * 2;
            }
            int n = 0;
            for (int n2 = 0; ~Class2.anIntArray70.length < ~n2; ++n2) {
                final int n3 = Class2.anIntArray70[n2];
                if (Class64_Sub16.aClass207_3683.method2742(b2 - 161, n3)) {
                    ++n;
                }
                if (Class64_Sub17.aClass207_3687.method2742(b2 ^ 0xFFFFFFB2, n3)) {
                    ++n;
                }
            }
            if (b2 != 90) {
                Class98_Sub46_Sub15.anInt6039 = 34;
            }
            return n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jba.E(" + b + ',' + b2 + ')');
        }
    }
    
    static final boolean method1611(final byte b, final char c) {
        try {
            if (b < 117) {
                method1612(false);
            }
            return (~c <= -66 && ~c >= -91) || (~c <= -98 && ~c >= -123);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jba.D(" + b + ',' + c + ')');
        }
    }
    
    Class98_Sub46_Sub15(final short[][] aShortArrayArray6040, final double aDouble6042) {
        try {
            this.aShortArrayArray6040 = aShortArrayArray6040;
            this.aDouble6042 = aDouble6042;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jba.<init>(" + ((aShortArrayArray6040 != null) ? "{...}" : "null") + ',' + aDouble6042 + ')');
        }
    }
    
    public static void method1612(final boolean b) {
        try {
            if (!b) {
                Class98_Sub46_Sub15.anInt6039 = 72;
            }
            Class98_Sub46_Sub15.aClass232_6043 = null;
            Class98_Sub46_Sub15.aClass58_6041 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jba.B(" + b + ')');
        }
    }
    
    static {
        Class98_Sub46_Sub15.anInt6039 = -1;
        Class98_Sub46_Sub15.aClass58_6041 = new IncomingOpcode(36, 3);
        Class98_Sub46_Sub15.aClass232_6043 = new Class232();
    }
}
