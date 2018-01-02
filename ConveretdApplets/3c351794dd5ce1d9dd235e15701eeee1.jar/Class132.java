// 
// Decompiled by Procyon v0.5.30
// 

final class Class132
{
    static int anInt1043;
    static int[] anIntArray1044;
    private Class207 aClass207_1045;
    static int anInt1046;
    int anInt1047;
    static Class98_Sub48 aClass98_Sub48_1048;
    private Class79 aClass79_1049;
    static int anInt1050;
    
    public static void method2234(final int n) {
        try {
            Class132.aClass98_Sub48_1048 = null;
            if (n == 0) {
                Class132.anIntArray1044 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iu.B(" + n + ')');
        }
    }
    
    static final int method2235(final int n, final int n2, final byte b) {
        try {
            if (b != 98) {
                Class132.anIntArray1044 = null;
            }
            int n3;
            if (~n >= -20001) {
                if (n > 10000) {
                    n3 = 3;
                    Class98_Sub50.method1672((byte)19);
                }
                else if (~n < -5001) {
                    Class287.method3385((byte)66);
                    n3 = 2;
                }
                else {
                    Class98_Sub32.method1436(b - 216, true);
                    n3 = 1;
                }
            }
            else {
                Class98_Sub27.method1284(1);
                n3 = 4;
            }
            if (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)125) != ~n2) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), n2, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4062);
                Class76_Sub4.method754(n2, false, b - 215);
            }
            Class310.method3618(-5964);
            return n3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iu.C(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    static final void method2236(final boolean b, final int anInt3150, final String s, final String s2, final int n) {
        try {
            final Class98_Sub11 method1556 = Class98_Sub46_Sub9.method1556(false);
            method1556.aClass98_Sub22_Sub1_3865.method1194(Class298.aClass222_2485.anInt1668, -86);
            method1556.aClass98_Sub22_Sub1_3865.writeShort(0, 1571862888);
            final int anInt3151 = method1556.aClass98_Sub22_Sub1_3865.anInt3991;
            method1556.aClass98_Sub22_Sub1_3865.writeShort(637, anInt3150 ^ 0x5DB0B968);
            final int[] method1557 = Class42_Sub2.method389(12206, method1556);
            final int anInt3152 = method1556.aClass98_Sub22_Sub1_3865.anInt3991;
            method1556.aClass98_Sub22_Sub1_3865.method1188(s2, (byte)113);
            method1556.aClass98_Sub22_Sub1_3865.writeShort(Class98_Sub10_Sub15.anInt5619, 1571862888);
            method1556.aClass98_Sub22_Sub1_3865.method1188(s, (byte)113);
            method1556.aClass98_Sub22_Sub1_3865.method1221(-75, Class197.aLong1515);
            method1556.aClass98_Sub22_Sub1_3865.method1194(Class374.anInt3159, 62);
            method1556.aClass98_Sub22_Sub1_3865.method1194(Class4.aClass279_86.anInt2095, 126);
            Class149.method2430(method1556.aClass98_Sub22_Sub1_3865, (byte)0);
            final String aString5573 = Class98_Sub10_Sub7.aString5573;
            method1556.aClass98_Sub22_Sub1_3865.method1194((aString5573 != null) ? 1 : 0, anInt3150 - 61);
            if (aString5573 != null) {
                method1556.aClass98_Sub22_Sub1_3865.method1188(aString5573, (byte)113);
            }
            method1556.aClass98_Sub22_Sub1_3865.method1194(n, -114);
            method1556.aClass98_Sub22_Sub1_3865.method1194(b ? 1 : 0, 93);
            final Class98_Sub22_Sub1 aClass98_Sub22_Sub1_3865 = method1556.aClass98_Sub22_Sub1_3865;
            aClass98_Sub22_Sub1_3865.anInt3991 += 7;
            method1556.aClass98_Sub22_Sub1_3865.method1235(true, method1557, anInt3152, method1556.aClass98_Sub22_Sub1_3865.anInt3991);
            method1556.aClass98_Sub22_Sub1_3865.method1207((byte)90, -anInt3151 + method1556.aClass98_Sub22_Sub1_3865.anInt3991);
            Class98_Sub10_Sub29.sendPacket(false, method1556);
            Class21_Sub4.anInt5394 = 1;
            Class372.anInt3150 = anInt3150;
            Class98_Sub26.anInt4028 = 0;
            Class55.anInt442 = -3;
            if (n < 13) {
                ha.aBoolean940 = true;
                Class305.method3571(-97);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iu.D(" + b + ',' + anInt3150 + ',' + ((s != null) ? "{...}" : "null") + ',' + ((s2 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final Class90 method2237(final int n, final int n2) {
        try {
            final Class90 class90;
            synchronized (this.aClass79_1049) {
                class90 = (Class90)this.aClass79_1049.method802(-121, n);
            }
            if (class90 != null) {
                return class90;
            }
            if (n2 <= 39) {
                method2235(-23, -51, (byte)7);
            }
            final byte[] method2745;
            synchronized (this.aClass207_1045) {
                method2745 = this.aClass207_1045.method2745(n, 19, false);
            }
            final Class90 class91 = new Class90();
            if (method2745 != null) {
                class91.method885(new Class98_Sub22(method2745), -23453);
            }
            synchronized (this.aClass79_1049) {
                this.aClass79_1049.method805(n, class91, (byte)(-80));
            }
            return class91;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iu.A(" + n + ',' + n2 + ')');
        }
    }
    
    Class132(final Class279 class279, final int n, final Class207 aClass207_1045) {
        this.aClass79_1049 = new Class79(64);
        try {
            this.aClass207_1045 = aClass207_1045;
            this.anInt1047 = this.aClass207_1045.method2761(0, 19);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iu.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_1045 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class132.anInt1043 = 0;
        Class132.aClass98_Sub48_1048 = new Class98_Sub48(0, 0);
        Class132.anInt1050 = -1;
    }
}
