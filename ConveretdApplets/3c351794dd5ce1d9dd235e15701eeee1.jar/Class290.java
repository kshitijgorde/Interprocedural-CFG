// 
// Decompiled by Procyon v0.5.30
// 

final class Class290
{
    static int[] anIntArray2198;
    
    static final void method3411(final int n, final byte b, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            if (b != 7) {
                Class290.anIntArray2198 = null;
            }
            for (Class246_Sub7 class246_Sub7 = (Class246_Sub7)Class64_Sub20.aClass218_3694.method2803((byte)15); class246_Sub7 != null; class246_Sub7 = (Class246_Sub7)Class64_Sub20.aClass218_3694.method2809(false)) {
                if (class246_Sub7.anInt5118 <= Class215.anInt1614) {
                    class246_Sub7.method2965((byte)5);
                }
                else {
                    Class42_Sub1.method385(n4, class246_Sub7.anInt5120, 256 + (class246_Sub7.anInt5116 << 1991702313), b ^ 0xFFFFFFF8, n5, class246_Sub7.anInt5122 * 2, 256 + (class246_Sub7.anInt5123 << 999372329), n3 >> -1188035487, n6 >> 717151425);
                    Class98_Sub10_Sub34.aClass43_5730.method415(0xFF000000 | class246_Sub7.anInt5117, class246_Sub7.aString5121, n - -Class259.anIntArray1957[0], 0, (byte)(-67), n2 - -Class259.anIntArray1957[1]);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rt.C(" + n + ',' + b + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    public static void method3412(final int n) {
        try {
            if (n != -1) {
                method3411(110, (byte)(-22), 5, 54, 26, 54, 115);
            }
            Class290.anIntArray2198 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rt.A(" + n + ')');
        }
    }
    
    static final void method3413(final int n, final int n2) {
        try {
            Class185.method2628(n2, n - 72, 8).method1621(n + n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rt.B(" + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class290.anIntArray2198 = new int[14];
    }
}
