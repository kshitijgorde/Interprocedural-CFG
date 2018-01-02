// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub21 extends Class98
{
    int anInt3976;
    int anInt3977;
    String aString3978;
    int anInt3979;
    boolean aBoolean3980;
    Object[] anObjectArray3981;
    Class293 aClass293_3982;
    private static short[] aShortArray3983;
    int anInt3984;
    int anInt3985;
    Class293 aClass293_3986;
    static short[][] aShortArrayArray3987;
    private static short[] aShortArray3988;
    private static short[] aShortArray3989;
    int anInt3990;
    
    static final int method1176(final boolean b) {
        try {
            if (Class98_Sub46.anInt4261 == 1) {
                return Class48_Sub1_Sub2.anInt5519;
            }
            if (b) {
                Class98_Sub21.aShortArray3989 = null;
            }
            return Class149.anInt1208;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "je.E(" + b + ')');
        }
    }
    
    public static void method1177(final int n) {
        try {
            Class98_Sub21.aShortArray3989 = null;
            Class98_Sub21.aShortArrayArray3987 = null;
            Class98_Sub21.aShortArray3983 = null;
            if (n != 24301) {
                method1177(-117);
            }
            Class98_Sub21.aShortArray3988 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "je.A(" + n + ')');
        }
    }
    
    static final boolean method1178(final int n) {
        try {
            if (n != 13299) {
                Class98_Sub21.aShortArrayArray3987 = null;
            }
            try {
                if (~Class257.anInt1948 == 0xFFFFFFFD) {
                    if (Class81.aClass98_Sub7_620 == null) {
                        Class81.aClass98_Sub7_620 = Class98_Sub7.method985(Class269.aClass207_2025, Class98_Sub18.anInt3951, Class76_Sub8.anInt3770);
                        if (Class81.aClass98_Sub7_620 == null) {
                            return false;
                        }
                    }
                    if (Class202.aClass308_1550 == null) {
                        Class202.aClass308_1550 = new Class308(Class94.aClass207_793, Class98_Sub34.aClass207_4127);
                    }
                    Class98_Sub31_Sub2 aClass98_Sub31_Sub2_3122 = Class366.aClass98_Sub31_Sub2_3122;
                    if (Class116.aClass98_Sub31_Sub2_965 != null) {
                        aClass98_Sub31_Sub2_3122 = Class116.aClass98_Sub31_Sub2_965;
                    }
                    if (aClass98_Sub31_Sub2_3122.method1352(Class81.aClass98_Sub7_620, 22050, Class202.aClass308_1550, Class64_Sub1.aClass207_3641, false)) {
                        (Class366.aClass98_Sub31_Sub2_3122 = aClass98_Sub31_Sub2_3122).method1358((byte)23);
                        if (Class22.anInt219 <= 0) {
                            Class257.anInt1948 = 0;
                            Class366.aClass98_Sub31_Sub2_3122.method1366(Class224_Sub3.anInt5037, (byte)124);
                            for (int n2 = 0; ~Class77_Sub1.anIntArray3804.length < ~n2; ++n2) {
                                Class366.aClass98_Sub31_Sub2_3122.method1363(-17, n2, Class77_Sub1.anIntArray3804[n2]);
                                Class77_Sub1.anIntArray3804[n2] = 255;
                            }
                        }
                        else {
                            Class257.anInt1948 = 3;
                            Class366.aClass98_Sub31_Sub2_3122.method1366((Class224_Sub3.anInt5037 < Class22.anInt219) ? Class224_Sub3.anInt5037 : Class22.anInt219, (byte)33);
                            for (int i = 0; i < Class77_Sub1.anIntArray3804.length; ++i) {
                                Class366.aClass98_Sub31_Sub2_3122.method1363(-17, i, Class77_Sub1.anIntArray3804[i]);
                                Class77_Sub1.anIntArray3804[i] = 255;
                            }
                        }
                        if (Class116.aClass98_Sub31_Sub2_965 == null) {
                            if (~Class133.aLong3455 < -1L) {
                                Class366.aClass98_Sub31_Sub2_3122.method1341(true, Class1.aBoolean66, Class81.aClass98_Sub7_620, Class133.aLong3455, -3);
                            }
                            else {
                                Class366.aClass98_Sub31_Sub2_3122.method1332(Class1.aBoolean66, Class81.aClass98_Sub7_620, (byte)(-4));
                            }
                        }
                        if (Class270.aClass268_2032 != null) {
                            Class270.aClass268_2032.method3252(n - 13299, Class366.aClass98_Sub31_Sub2_3122);
                        }
                        Class269.aClass207_2025 = null;
                        Class202.aClass308_1550 = null;
                        Class81.aClass98_Sub7_620 = null;
                        Class133.aLong3455 = 0L;
                        Class116.aClass98_Sub31_Sub2_965 = null;
                        return true;
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                Class366.aClass98_Sub31_Sub2_3122.method1364(98);
                Class269.aClass207_2025 = null;
                Class202.aClass308_1550 = null;
                Class257.anInt1948 = 0;
                Class81.aClass98_Sub7_620 = null;
                Class116.aClass98_Sub31_Sub2_965 = null;
            }
            return false;
        }
        catch (RuntimeException ex2) {
            throw Class64_Sub27.method667(ex2, "je.C(" + n + ')');
        }
    }
    
    static final boolean method1179(final int n, final int n2) {
        try {
            if (n2 != 255) {
                Class98_Sub21.aShortArray3983 = null;
            }
            return n == 49 || n == 59 || n == 1006 || n == 21 || n == 9;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "je.B(" + n + ',' + n2 + ')');
        }
    }
    
    static final int method1180(final int n, final byte b, final int n2, final int n3, final int n4) {
        try {
            final int n5 = 65536 - Class284_Sub2_Sub2.anIntArray6202[8192 * n3 / n] >> -219666591;
            return (n5 * n4 >> -23038832) + (n2 * (65536 - n5) >> 1646652976);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "je.D(" + n + ',' + b + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    static {
        Class98_Sub21.aShortArray3983 = new short[] { -10304, 9104, 25485, 4620, 4540 };
        Class98_Sub21.aShortArray3988 = new short[] { -1, -1, -1, -1, -1 };
        Class98_Sub21.aShortArray3989 = new short[] { 6798, 8741, 25238, 4626, 4550 };
        Class98_Sub21.aShortArrayArray3987 = new short[][] { Class98_Sub21.aShortArray3989, Class98_Sub21.aShortArray3983, Class98_Sub21.aShortArray3988 };
    }
}
