// 
// Decompiled by Procyon v0.5.30
// 

final class Class364
{
    private d aD3101;
    static int[] anIntArray3102;
    static int anInt3103;
    static Class88 aClass88_3104;
    private ha_Sub1 aHa_Sub1_3105;
    private Class79 aClass79_3106;
    
    final Class42_Sub1 method3931(final int n, final int n2) {
        try {
            final Object method802 = this.aClass79_3106.method802(-121, n2);
            if (method802 != null) {
                return (Class42_Sub1)method802;
            }
            if (!this.aD3101.method8(-117, n2)) {
                return null;
            }
            if (n <= 122) {
                Class364.anIntArray3102 = null;
            }
            final Class238 method803 = this.aD3101.method11(n2, -28755);
            final int n3 = method803.aBoolean1822 ? 64 : this.aHa_Sub1_3105.anInt4309;
            Class42_Sub1 class42_Sub1;
            if (method803.aBoolean1817 && this.aHa_Sub1_3105.method1768()) {
                class42_Sub1 = new Class42_Sub1(this.aHa_Sub1_3105, 3553, 34842, n3, n3, ~method803.aByte1832 != -1, this.aD3101.method10((byte)(-126), false, n2, n3, 0.7f, n3), 6408);
            }
            else {
                int[] array;
                if (~method803.anInt1818 == 0xFFFFFFFD || !Class98_Sub10_Sub7.method1023(1, method803.aByte1820)) {
                    array = this.aD3101.method13(127, n3, n2, 0.7f, false, n3);
                }
                else {
                    array = this.aD3101.method9(n2, (byte)(-120), n3, 0.7f, true, n3);
                }
                class42_Sub1 = new Class42_Sub1(this.aHa_Sub1_3105, 3553, 6408, n3, n3, method803.aByte1832 != 0, array, 0, 0, false);
            }
            class42_Sub1.method383(method803.aBoolean1819, 10242, method803.aBoolean1826);
            this.aClass79_3106.method805(n2, class42_Sub1, (byte)(-80));
            return class42_Sub1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vw.C(" + n + ',' + n2 + ')');
        }
    }
    
    static final int method3932(final boolean b, final byte b2) {
        try {
            if (b2 != -67) {
                Class364.aClass88_3104 = null;
            }
            final int anInt4261 = Class98_Sub46.anInt4261;
            if (anInt4261 != 0) {
                if (anInt4261 == 1) {
                    return Class272.anInt2037;
                }
                if (anInt4261 != 2) {
                    return 0;
                }
                if (!client.aBoolean3553) {
                    return 0;
                }
            }
            if (b) {
                return 0;
            }
            return Class272.anInt2037;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vw.E(" + b + ',' + b2 + ')');
        }
    }
    
    final void method3933(final int n) {
        try {
            if (n != 0) {
                Class364.aClass88_3104 = null;
            }
            this.aClass79_3106.method800((byte)62, 5);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vw.B(" + n + ')');
        }
    }
    
    final void method3934(final byte b) {
        try {
            if (b != 100) {
                this.aHa_Sub1_3105 = null;
            }
            this.aClass79_3106.method794(b + 13);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vw.F(" + b + ')');
        }
    }
    
    static final void method3935() {
        Class314.method3644(1, Class364.anInt3103);
    }
    
    public static void method3936(final byte b) {
        try {
            Class364.anIntArray3102 = null;
            Class364.aClass88_3104 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vw.D(" + b + ')');
        }
    }
    
    Class364(final ha_Sub1 aHa_Sub1_3105, final d ad3101) {
        this.aClass79_3106 = new Class79(256);
        try {
            this.aD3101 = ad3101;
            this.aHa_Sub1_3105 = aHa_Sub1_3105;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vw.<init>(" + ((aHa_Sub1_3105 != null) ? "{...}" : "null") + ',' + ((ad3101 != null) ? "{...}" : "null") + ')');
        }
    }
}
