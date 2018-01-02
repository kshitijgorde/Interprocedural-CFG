// 
// Decompiled by Procyon v0.5.30
// 

final class Class319
{
    static int anInt2699;
    static boolean aBoolean2700;
    private d aD2701;
    static boolean[][] aBooleanArrayArray2702;
    private Class79 aClass79_2703;
    private ha_Sub3 aHa_Sub3_2704;
    static int[] anIntArray2705;
    static int anInt2706;
    static boolean aBoolean2707;
    
    public static void method3658(final byte b) {
        try {
            Class319.anIntArray2705 = null;
            Class319.aBooleanArrayArray2702 = null;
            if (b < 79) {
                method3658((byte)(-66));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tl.C(" + b + ')');
        }
    }
    
    final void method3659(final int n) {
        try {
            if (n != -3) {
                method3658((byte)47);
            }
            this.aClass79_2703.method794(n + 80);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tl.B(" + n + ')');
        }
    }
    
    Class319(final ha_Sub3 aHa_Sub3_2704, final d ad2701) {
        this.aClass79_2703 = new Class79(256);
        try {
            this.aHa_Sub3_2704 = aHa_Sub3_2704;
            this.aD2701 = ad2701;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tl.<init>(" + ((aHa_Sub3_2704 != null) ? "{...}" : "null") + ',' + ((ad2701 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final boolean method3660(final boolean b) {
        try {
            if (!Class134_Sub1.method2246("jaclib", (byte)(-36))) {
                return false;
            }
            if (!b) {
                method3658((byte)113);
            }
            return Class134_Sub1.method2246("hw3d", (byte)(-36));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tl.A(" + b + ')');
        }
    }
    
    final Interface4_Impl2 method3661(final int n, final int n2) {
        try {
            final Object method802 = this.aClass79_2703.method802(n - 124, n2);
            if (method802 != null) {
                return (Interface4_Impl2)method802;
            }
            if (!this.aD2701.method8(-5, n2)) {
                return null;
            }
            if (n != 0) {
                return null;
            }
            final Class238 method803 = this.aD2701.method11(n2, n - 28755);
            final int n3 = method803.aBoolean1822 ? 64 : this.aHa_Sub3_2704.anInt4607;
            Interface4_Impl2 interface4_Impl2;
            if (method803.aBoolean1817 && this.aHa_Sub3_2704.method1768()) {
                interface4_Impl2 = this.aHa_Sub3_2704.method2066(Class62.aClass164_486, ~method803.aByte1832 != -1, this.aD2701.method10((byte)(-117), false, n2, n3, 0.7f, n3), false, n3, n3);
            }
            else {
                int[] array;
                if (~method803.anInt1818 == 0xFFFFFFFD || !Class98_Sub10_Sub7.method1023(n ^ 0x1, method803.aByte1820)) {
                    array = this.aD2701.method13(n + 115, n3, n2, 0.7f, false, n3);
                }
                else {
                    array = this.aD2701.method9(n2, (byte)(-116), n3, 0.7f, true, n3);
                }
                interface4_Impl2 = this.aHa_Sub3_2704.method2012(n3, n3, (byte)31, array, method803.aByte1832 != 0);
            }
            interface4_Impl2.method46(method803.aBoolean1826, method803.aBoolean1819, -97);
            this.aClass79_2703.method805(n2, interface4_Impl2, (byte)(-80));
            return interface4_Impl2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tl.D(" + n + ',' + n2 + ')');
        }
    }
    
    final void method3662(final int n) {
        try {
            this.aClass79_2703.method800((byte)62, 5);
            if (n >= -112) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tl.E(" + n + ')');
        }
    }
    
    static {
        Class319.aBoolean2700 = false;
        Class319.anInt2699 = 0;
        Class319.anIntArray2705 = new int[2048];
        Class319.aBoolean2707 = false;
    }
}
