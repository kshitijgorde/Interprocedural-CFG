// 
// Decompiled by Procyon v0.5.30
// 

final class Class166
{
    static boolean aBoolean1278;
    static int anInt1279;
    static Class207 aClass207_1280;
    
    static final void method2525(final int n) {
        try {
            Class259.anInt1954 = 0;
            ++Class201.anInt1544;
            Class65.anInt502 = 0;
            Class98_Sub39.method1468(-4942);
            Class341.method3810((byte)121);
            Class21_Sub2.method273((byte)(-61));
            boolean b = false;
            for (int n2 = 0; ~Class259.anInt1954 < ~n2; ++n2) {
                final int n3 = Class246_Sub3_Sub4_Sub3.anIntArray6451[n2];
                final Class98_Sub39 class98_Sub39 = (Class98_Sub39)Class260.aClass377_3254.method3990(n3, -1);
                final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4187 = class98_Sub39.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                if (Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540 && Class98_Sub44.method1514(-128, n3)) {
                    Class317.method3651((byte)(-53));
                }
                if (~Class201.anInt1544 != ~aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6406) {
                    if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.method2302((byte)117)) {
                        Class98_Sub43_Sub4.method1504(aClass246_Sub3_Sub4_Sub2_Sub1_4187, -16255);
                    }
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3054(null, 1);
                    class98_Sub39.method942(n + 98);
                    b = true;
                }
            }
            if (b) {
                Class98_Sub10_Sub20.anInt5640 = Class260.aClass377_3254.method3999((byte)(-6));
                Class260.aClass377_3254.method3992(Class163.aClass98_Sub39Array3516, (byte)74);
            }
            if (Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.anInt3991 != Class65.anInt496) {
                throw new RuntimeException("gnp1 pos:" + Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.anInt3991 + " psize:" + Class65.anInt496);
            }
            for (int n4 = 0; ~Class150.anInt1211 < ~n4; ++n4) {
                if (Class260.aClass377_3254.method3990(Class325.anIntArray2726[n4], -1) == null) {
                    throw new RuntimeException("gnp2 pos:" + n4 + " size:" + Class150.anInt1211);
                }
            }
            if (-Class150.anInt1211 + Class98_Sub10_Sub20.anInt5640 != n) {
                throw new RuntimeException("gnp3 mis:" + (Class98_Sub10_Sub20.anInt5640 + -Class150.anInt1211));
            }
            for (int i = 0; i < Class98_Sub10_Sub20.anInt5640; ++i) {
                if (~Class201.anInt1544 != ~Class163.aClass98_Sub39Array3516[i].aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6406) {
                    throw new RuntimeException("gnp4 uk:" + Class163.aClass98_Sub39Array3516[i].aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6369);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void method2526(final int n) {
        try {
            Class166.aClass207_1280 = null;
            if (n != -19351) {
                method2526(-23);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "l.A(" + n + ')');
        }
    }
    
    static {
        Class166.aBoolean1278 = false;
    }
}
