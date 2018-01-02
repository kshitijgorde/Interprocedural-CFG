// 
// Decompiled by Procyon v0.5.30
// 

final class Class329
{
    int anInt2760;
    static int anInt2761;
    int anInt2762;
    int anInt2763;
    int anInt2764;
    static int anInt2765;
    int anInt2766;
    int anInt2767;
    int anInt2768;
    int anInt2769;
    int anInt2770;
    static int[] anIntArray2771;
    int anInt2772;
    int anInt2773;
    
    static final void method3708(final int n) {
        try {
            if (Class53_Sub1.method499(n ^ 0xFFFFF7FF, Class177.anInt1376) || Class246_Sub3_Sub3.method3011(-6410, Class177.anInt1376)) {
                Class185.method2630(Class134.anInt3461 >> -802670004, -108, Class98_Sub46_Sub10.anInt6020 >> 1156493548, 5000);
            }
            else {
                final int n2 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6437[0] >> -1272391965;
                final int n3 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6438[0] >> -1164602557;
                if (~n2 > -1 || Class165.anInt1276 >> -310895453 <= n2 || ~n3 > -1 || ~n3 <= ~(Class98_Sub10_Sub7.anInt5572 >> 1384895075)) {
                    Class185.method2630(Class98_Sub10_Sub7.anInt5572 >> -980626748, n - 118, Class165.anInt1276 >> -3353948, 0);
                }
                else {
                    Class185.method2630(n3, n - 111, n2, 5000);
                }
            }
            RuntimeException_Sub1.method4011(n - 113);
            if (n != -1) {
                Class329.anInt2765 = 71;
            }
            Class230.method2870((byte)(-74));
            PlayerUpdate.method2855(-19004);
            Class96.method928((byte)(-42));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uc.A(" + n + ')');
        }
    }
    
    final void method3709(final Class329 class329, final int n) {
        try {
            this.anInt2770 = class329.anInt2770;
            this.anInt2768 = class329.anInt2768;
            this.anInt2769 = class329.anInt2769;
            this.anInt2763 = class329.anInt2763;
            this.anInt2762 = class329.anInt2762;
            this.anInt2772 = class329.anInt2772;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uc.C(" + ((class329 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final Class329 method3710(final int n) {
        try {
            if (n != 28889) {
                this.anInt2762 = -34;
            }
            return new Class329(this.anInt2770, this.anInt2763, this.anInt2768, this.anInt2762, this.anInt2772, this.anInt2769);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uc.B(" + n + ')');
        }
    }
    
    static final int method3711(final byte b, final int n) {
        try {
            if (b <= 113) {
                method3708(51);
            }
            return n >>> 367185160;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uc.E(" + b + ',' + n + ')');
        }
    }
    
    public static void method3712(final byte b) {
        try {
            Class329.anIntArray2771 = null;
            if (b != 21) {
                method3712((byte)51);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uc.D(" + b + ')');
        }
    }
    
    static final void method3713(final byte b, final String s) {
        try {
            final Class98_Sub11 method1556 = Class98_Sub46_Sub9.method1556(false);
            method1556.aClass98_Sub22_Sub1_3865.method1194(Class298.aClass222_2490.anInt1668, -110);
            method1556.aClass98_Sub22_Sub1_3865.writeShort(0, 1571862888);
            final int anInt3991 = method1556.aClass98_Sub22_Sub1_3865.anInt3991;
            method1556.aClass98_Sub22_Sub1_3865.writeShort(637, 1571862888);
            final int[] method1557 = Class42_Sub2.method389(12206, method1556);
            final int anInt3992 = method1556.aClass98_Sub22_Sub1_3865.anInt3991;
            method1556.aClass98_Sub22_Sub1_3865.method1188(s, (byte)113);
            method1556.aClass98_Sub22_Sub1_3865.method1194(Class374.anInt3159, 84);
            final Class98_Sub22_Sub1 aClass98_Sub22_Sub1_3865 = method1556.aClass98_Sub22_Sub1_3865;
            aClass98_Sub22_Sub1_3865.anInt3991 += 7;
            method1556.aClass98_Sub22_Sub1_3865.method1235(true, method1557, anInt3992, method1556.aClass98_Sub22_Sub1_3865.anInt3991);
            method1556.aClass98_Sub22_Sub1_3865.method1207((byte)90, method1556.aClass98_Sub22_Sub1_3865.anInt3991 - anInt3991);
            Class98_Sub10_Sub29.sendPacket(false, method1556);
            Class98_Sub26.anInt4028 = 0;
            Class55.anInt442 = -3;
            Class21_Sub4.anInt5394 = 1;
            Class372.anInt3150 = 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uc.F(" + b + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class329(final int anInt2770) {
        this.anInt2768 = 128;
        this.anInt2763 = 128;
        try {
            this.anInt2770 = anInt2770;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uc.<init>(" + anInt2770 + ')');
        }
    }
    
    private Class329(final int anInt2770, final int anInt2771, final int anInt2772, final int anInt2773, final int anInt2774, final int anInt2775) {
        this.anInt2768 = 128;
        this.anInt2763 = 128;
        try {
            this.anInt2768 = anInt2772;
            this.anInt2770 = anInt2770;
            this.anInt2769 = anInt2775;
            this.anInt2762 = anInt2773;
            this.anInt2763 = anInt2771;
            this.anInt2772 = anInt2774;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uc.<init>(" + anInt2770 + ',' + anInt2771 + ',' + anInt2772 + ',' + anInt2773 + ',' + anInt2774 + ',' + anInt2775 + ')');
        }
    }
    
    static {
        Class329.anInt2765 = 0;
    }
}
