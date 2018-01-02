import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class277
{
    static int[] anIntArray2049;
    static int anInt2050;
    static OutgoingOpcode aClass171_2051;
    static IncomingOpcode aClass58_2052;
    static Class aClass2053;
    
    static final void method3288(final boolean b, final int n, String lowerCase, final int anInt1575, final String s, final boolean b2, final int n2) {
        try {
            Class208.aClass207_1581.anInt1575 = 1;
            lowerCase = lowerCase.toLowerCase();
            short[] aShortArray3682 = new short[16];
            int anInt1576 = -1;
            String aString1203 = null;
            if (~n != 0x0) {
                final Class149 method3940 = Class98_Sub43_Sub1.aClass365_5897.method3940((byte)31, n);
                if (method3940 == null || !b2 == method3940.method2433(false)) {
                    return;
                }
                if (method3940.method2433(false)) {
                    aString1203 = method3940.aString1203;
                }
                else {
                    anInt1576 = method3940.anInt1202;
                }
            }
            int anInt1577 = 0;
            for (int i = 0; i < Class98_Sub46_Sub19.aClass205_6068.anInt1554; ++i) {
                final Class297 method3941 = Class98_Sub46_Sub19.aClass205_6068.method2714(i, (byte)(-119));
                if ((!b || method3941.aBoolean2461) && ~method3941.anInt2414 == 0x0 && method3941.anInt2459 == -1 && ~method3941.anInt2464 == -1 && ~method3941.aString2450.toLowerCase().indexOf(lowerCase) != 0x0) {
                    if (~n != 0x0) {
                        if (b2) {
                            if (!s.equals(method3941.method3495(aString1203, -1, n))) {
                                continue;
                            }
                        }
                        else if (method3941.method3494(n, (byte)(-90), anInt1576) != n2) {
                            continue;
                        }
                    }
                    if (~anInt1577 <= -251) {
                        Class64_Sub16.aShortArray3682 = null;
                        Class18.anInt214 = -1;
                        return;
                    }
                    if (aShortArray3682.length <= anInt1577) {
                        final short[] array = new short[aShortArray3682.length * 2];
                        for (int n3 = 0; ~n3 > ~anInt1577; ++n3) {
                            array[n3] = aShortArray3682[n3];
                        }
                        aShortArray3682 = array;
                    }
                    aShortArray3682[anInt1577++] = (short)i;
                }
            }
            Class64_Sub16.aShortArray3682 = aShortArray3682;
            Class18.anInt214 = anInt1577;
            Class85.anInt638 = 0;
            final String[] array2 = new String[Class18.anInt214];
            for (int n4 = 0; ~Class18.anInt214 < ~n4; ++n4) {
                array2[n4] = Class98_Sub46_Sub19.aClass205_6068.method2714(aShortArray3682[n4], (byte)(-127)).aString2450;
            }
            Class98_Sub11.method1126(true, Class64_Sub16.aShortArray3682, array2);
            Class208.aClass207_1581.method2760((byte)(-116));
            Class208.aClass207_1581.anInt1575 = anInt1575;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rfa.E(" + b + ',' + n + ',' + ((lowerCase != null) ? "{...}" : "null") + ',' + anInt1575 + ',' + ((s != null) ? "{...}" : "null") + ',' + b2 + ',' + n2 + ')');
        }
    }
    
    static final void method3289(final int n, final int n2, final int n3, final int n4, final ha ha, final int n5, final boolean b, final int n6, final int n7) {
        try {
            final Interface19 interface19 = (Interface19)Class21_Sub1.method268(n, n6, n3);
            if (interface19 != null) {
                final Class352 method3546 = Class130.aClass302_1028.method3546(interface19.method64(30472), (byte)119);
                final int n8 = 0x3 & interface19.method66(4657);
                final int method3547 = interface19.method63((byte)20);
                if (method3546.anInt2990 != -1) {
                    Class122.method2201(n8, method3546, n2, ha, n5, (byte)70);
                }
                else {
                    int n9 = n7;
                    if (~method3546.anInt2998 < -1) {
                        n9 = n4;
                    }
                    if (~method3547 == -1 || method3547 == 2) {
                        if (n8 != 0) {
                            if (n8 != 1) {
                                if (n8 == 2) {
                                    ha.method1755(8479, n5, 3 + n2, n9, 4);
                                }
                                else if (~n8 == 0xFFFFFFFC) {
                                    ha.method1753(22294, 4, n9, n5 + 3, n2);
                                }
                            }
                            else {
                                ha.method1753(22294, 4, n9, n5, n2);
                            }
                        }
                        else {
                            ha.method1755(8479, n5, n2, n9, 4);
                        }
                    }
                    if (method3547 == 3) {
                        if (n8 == 0) {
                            ha.method1760(1, 1, n5, n9, (byte)(-66), n2);
                        }
                        else if (n8 == 1) {
                            ha.method1760(1, 1, n5, n9, (byte)(-66), 3 + n2);
                        }
                        else if (n8 != 2) {
                            if (~n8 == 0xFFFFFFFC) {
                                ha.method1760(1, 1, n5 + 3, n9, (byte)(-66), n2);
                            }
                        }
                        else {
                            ha.method1760(1, 1, n5 + 3, n9, (byte)(-66), n2 + 3);
                        }
                    }
                    if (method3547 == 2) {
                        if (n8 != 0) {
                            if (n8 == 1) {
                                ha.method1755(8479, n5, n2 + 3, n9, 4);
                            }
                            else if (~n8 == 0xFFFFFFFD) {
                                ha.method1753(22294, 4, n9, 3 + n5, n2);
                            }
                            else if (~n8 == 0xFFFFFFFC) {
                                ha.method1755(8479, n5, n2, n9, 4);
                            }
                        }
                        else {
                            ha.method1753(22294, 4, n9, n5, n2);
                        }
                    }
                }
            }
            final Interface19 interface20 = (Interface19)Class97.method931(n, n6, n3, (Class277.aClass2053 != null) ? Class277.aClass2053 : (Class277.aClass2053 = method3294("Interface19")));
            if (interface20 != null) {
                final Class352 method3548 = Class130.aClass302_1028.method3546(interface20.method64(30472), (byte)119);
                final int n10 = 0x3 & interface20.method66(4657);
                final int method3549 = interface20.method63((byte)20);
                if (method3548.anInt2990 != -1) {
                    Class122.method2201(n10, method3548, n2, ha, n5, (byte)70);
                }
                else if (~method3549 == 0xFFFFFFF6) {
                    int n11 = -1118482;
                    if (~method3548.anInt2998 < -1) {
                        n11 = -1179648;
                    }
                    if (~n10 == -1 || n10 == 2) {
                        ha.method1789(3 + n5, n11, n5, 3 + n2, -10550, n2);
                    }
                    else {
                        ha.method1789(n5, n11, 3 + n5, n2 + 3, -10550, n2);
                    }
                }
            }
            final Interface19 interface21 = (Interface19)Class253.method3177(n, n6, n3);
            if (!b) {
                Class277.anInt2050 = -91;
            }
            if (interface21 != null) {
                final Class352 method3550 = Class130.aClass302_1028.method3546(interface21.method64(30472), (byte)119);
                final int n12 = interface21.method66(4657) & 0x3;
                if (method3550.anInt2990 != -1) {
                    Class122.method2201(n12, method3550, n2, ha, n5, (byte)70);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rfa.F(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + ((ha != null) ? "{...}" : "null") + ',' + n5 + ',' + b + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    abstract Class98_Sub46_Sub20 method3290(final Class98_Sub46_Sub20 p0, final byte p1);
    
    public static void method3291(final int n) {
        try {
            Class277.aClass58_2052 = null;
            Class277.aClass171_2051 = null;
            Class277.anIntArray2049 = null;
            if (n < 113) {
                Class277.aClass171_2051 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rfa.D(" + n + ')');
        }
    }
    
    static final void method3292(final byte b) {
        try {
            if (!Class265.aHa1974.method1766()) {
                Class76_Sub4.method754(Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)123), false, 65);
            }
            else {
                Class265.aHa1974.method1786(Class42_Sub3.aCanvas5361);
                Class98_Sub43_Sub4.method1510(28837);
                if (!za_Sub2.aBoolean6079) {
                    final Dimension size = Class42_Sub3.aCanvas5361.getSize();
                    Class265.aHa1974.method1741(Class42_Sub3.aCanvas5361, size.width, size.height);
                }
                else {
                    Class101.method1699(Class42_Sub3.aCanvas5361, 7);
                }
                Class265.aHa1974.method1750(Class42_Sub3.aCanvas5361);
            }
            Class98_Sub43.method1481(2);
            Class358.aBoolean3033 = true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rfa.B(" + b + ')');
        }
    }
    
    static final int method3293(final int n, final Class246_Sub3_Sub4_Sub2_Sub1 class246_Sub3_Sub4_Sub2_Sub1) {
        try {
            Class141 class141 = class246_Sub3_Sub4_Sub2_Sub1.aClass141_6504;
            if (n <= 119) {
                Class277.anIntArray2049 = null;
            }
            if (class141.anIntArray1109 != null) {
                class141 = class141.method2300(Class75.aClass140_584, (byte)64);
                if (class141 == null) {
                    return -1;
                }
            }
            int n2 = class141.anInt1102;
            final Class294 method3039 = class246_Sub3_Sub4_Sub2_Sub1.method3039(1);
            if (class246_Sub3_Sub4_Sub2_Sub1.anInt6385 == -1 || class246_Sub3_Sub4_Sub2_Sub1.aBoolean6359) {
                n2 = class141.anInt1120;
            }
            else if (~method3039.anInt2389 != ~class246_Sub3_Sub4_Sub2_Sub1.anInt6385 && method3039.anInt2361 != class246_Sub3_Sub4_Sub2_Sub1.anInt6385 && ~method3039.anInt2402 != ~class246_Sub3_Sub4_Sub2_Sub1.anInt6385 && class246_Sub3_Sub4_Sub2_Sub1.anInt6385 != method3039.anInt2357) {
                if (~method3039.anInt2368 == ~class246_Sub3_Sub4_Sub2_Sub1.anInt6385 || ~class246_Sub3_Sub4_Sub2_Sub1.anInt6385 == ~method3039.anInt2394 || class246_Sub3_Sub4_Sub2_Sub1.anInt6385 == method3039.anInt2403 || class246_Sub3_Sub4_Sub2_Sub1.anInt6385 == method3039.anInt2377) {
                    n2 = class141.anInt1132;
                }
            }
            else {
                n2 = class141.anInt1147;
            }
            return n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rfa.C(" + n + ',' + ((class246_Sub3_Sub4_Sub2_Sub1 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static Class method3294(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Class277.anIntArray2049 = new int[13];
        Class277.aClass171_2051 = new OutgoingOpcode(68, 3);
        Class277.aClass58_2052 = new IncomingOpcode(116, 8);
    }
}
