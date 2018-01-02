import java.lang.reflect.Method;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class96
{
    static int anInt801;
    static int anInt802;
    static int anInt803;
    static int anInt804;
    static Class aClass805;
    
    static final void method922(final boolean b, final int n) {
        try {
            if (b) {
                if (~Class15.anInt185 != 0x0) {
                    Class246.method2964(false, Class15.anInt185);
                }
                for (Class98_Sub18 class98_Sub18 = (Class98_Sub18)Class116.aClass377_964.method3998(122); class98_Sub18 != null; class98_Sub18 = (Class98_Sub18)Class116.aClass377_964.method3995(-1)) {
                    if (!class98_Sub18.method941((byte)78)) {
                        class98_Sub18 = (Class98_Sub18)Class116.aClass377_964.method3998(104);
                        if (class98_Sub18 == null) {
                            break;
                        }
                    }
                    Class196.method2666(n + 16399, false, class98_Sub18, true);
                }
                Class15.anInt185 = -1;
                Class116.aClass377_964 = new Class377(8);
                Class76_Sub9.method768(118);
                Class15.anInt185 = Class19.anInt3450;
                Class40.method359(39, false);
                Class98_Sub43.method1481(2);
                Class247.method3155(Class15.anInt185);
            }
            aa_Sub3.aBoolean3569 = true;
            if (n != -1) {
                Class96.anInt804 = 122;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fv.B(" + b + ',' + n + ')');
        }
    }
    
    static final void method923(final int n, final int anInt2911) {
        try {
            Class98_Sub18.anInt3951 = -1;
            Class257.anInt1948 = 1;
            Class1.aBoolean66 = false;
            Class76_Sub8.anInt3770 = -1;
            Class348.anInt2911 = anInt2911;
            Class224_Sub3.anInt5037 = 0;
            Class269.aClass207_2025 = null;
            Class116.aClass98_Sub31_Sub2_965 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fv.D(" + n + ',' + anInt2911 + ')');
        }
    }
    
    static final void method924(final byte b, final int n, final int n2, final int n3, final int n4) {
        try {
            int n5 = 0;
            int i = n2;
            if (b == -109) {
                int n6 = -n2;
                int n7 = -1;
                Class333.method3761(n4, Class97.anIntArrayArray814[n], Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n3 - n2), Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n3 + n2), (byte)(-125));
                while (i > n5) {
                    n7 += 2;
                    n6 += n7;
                    if (n6 > 0) {
                        --i;
                        n6 -= i << 1928097217;
                        final int n8 = -i + n;
                        final int n9 = n + i;
                        if (n9 >= Class98_Sub10_Sub38.anInt5753 && ~Class218.anInt1635 <= ~n8) {
                            final int method3219 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n3 - -n5);
                            final int method3220 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n3 + -n5);
                            if (~n9 >= ~Class218.anInt1635) {
                                Class333.method3761(n4, Class97.anIntArrayArray814[n9], method3220, method3219, (byte)(-127));
                            }
                            if (Class98_Sub10_Sub38.anInt5753 <= n8) {
                                Class333.method3761(n4, Class97.anIntArrayArray814[n8], method3220, method3219, (byte)(-125));
                            }
                        }
                    }
                    final int n10 = -(++n5) + n;
                    final int n11 = n5 + n;
                    if (~Class98_Sub10_Sub38.anInt5753 >= ~n11 && Class218.anInt1635 >= n10) {
                        final int method3221 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, i + n3);
                        final int method3222 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n3 - i);
                        if (n11 <= Class218.anInt1635) {
                            Class333.method3761(n4, Class97.anIntArrayArray814[n11], method3222, method3221, (byte)(-124));
                        }
                        if (Class98_Sub10_Sub38.anInt5753 > n10) {
                            continue;
                        }
                        Class333.method3761(n4, Class97.anIntArrayArray814[n10], method3222, method3221, (byte)(-125));
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fv.F(" + b + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    static final int method925(final int n, final int n2, final int n3, final boolean b) {
        try {
            final Class98_Sub3 method669 = Class64_Sub28.method669(n, b, 6);
            if (method669 == null) {
                return -1;
            }
            if (n3 < 0 || ~method669.anIntArray3824.length >= ~n3) {
                return -1;
            }
            return method669.anIntArray3824[n3];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fv.A(" + n + ',' + n2 + ',' + n3 + ',' + b + ')');
        }
    }
    
    static final void method926(final int n, final ha ha) {
        try {
            if ((n <= Class359.anInt3058 || Class98_Sub10_Sub9.aBoolean5585) && Class255.aClass293_3208 == null) {
                String s;
                if (!Class98_Sub10_Sub9.aBoolean5585 || ~Class359.anInt3058 <= -3) {
                    if (Class109.aBoolean934 && Class219.aClass77_1641.method779(81, 5503) && Class359.anInt3058 > 2) {
                        s = Class86.method845((byte)(-124), Class266.aClass98_Sub46_Sub8_1994);
                    }
                    else {
                        final Class98_Sub46_Sub8 aClass98_Sub46_Sub8_1994 = Class266.aClass98_Sub46_Sub8_1994;
                        if (aClass98_Sub46_Sub8_1994 == null) {
                            return;
                        }
                        s = Class86.method845((byte)(-124), aClass98_Sub46_Sub8_1994);
                        int[] array = null;
                        if (Class299_Sub2.method3526(127, aClass98_Sub46_Sub8_1994.anInt5990)) {
                            array = Class98_Sub46_Sub19.aClass205_6068.method2714((int)aClass98_Sub46_Sub8_1994.aLong5987, (byte)(-120)).anIntArray2436;
                        }
                        else if (~aClass98_Sub46_Sub8_1994.anInt5988 != 0x0) {
                            array = Class98_Sub46_Sub19.aClass205_6068.method2714(aClass98_Sub46_Sub8_1994.anInt5988, (byte)(-128)).anIntArray2436;
                        }
                        else if (!Class36.method340(aClass98_Sub46_Sub8_1994.anInt5990, (byte)(-61))) {
                            if (Class98_Sub10_Sub21.method1064(aClass98_Sub46_Sub8_1994.anInt5990, false)) {
                                Class352 class352;
                                if (aClass98_Sub46_Sub8_1994.anInt5990 == 1009) {
                                    class352 = Class130.aClass302_1028.method3546((int)aClass98_Sub46_Sub8_1994.aLong5987, (byte)119);
                                }
                                else {
                                    class352 = Class130.aClass302_1028.method3546((int)(aClass98_Sub46_Sub8_1994.aLong5987 >>> -584445664 & 0x7FFFFFFFL), (byte)119);
                                }
                                if (class352.anIntArray2928 != null) {
                                    class352 = class352.method3852(Class75.aClass140_584, (byte)(-48));
                                }
                                if (class352 != null) {
                                    array = class352.anIntArray2934;
                                }
                            }
                        }
                        else {
                            final Class98_Sub39 class98_Sub39 = (Class98_Sub39)Class260.aClass377_3254.method3990((int)aClass98_Sub46_Sub8_1994.aLong5987, n - 3);
                            if (class98_Sub39 != null) {
                                Class141 class353 = class98_Sub39.aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504;
                                if (class353.anIntArray1109 != null) {
                                    class353 = class353.method2300(Class75.aClass140_584, (byte)72);
                                }
                                if (class353 != null) {
                                    array = class353.anIntArray1152;
                                }
                            }
                        }
                        if (array != null) {
                            s += Class64_Sub25.method653(0, array);
                        }
                    }
                }
                else {
                    s = Class287_Sub2.aString3272 + Class309.aClass309_2618.method3615(Class374.anInt3159, (byte)25) + Class246_Sub3_Sub3.aString6156 + " ->";
                }
                if (Class359.anInt3058 > 2) {
                    s = s + "<col=ffffff> / " + (Class359.anInt3058 - 2) + Class309.aClass309_2611.method3615(Class374.anInt3159, (byte)25);
                }
                if (Class282.aClass293_2129 != null) {
                    Class43 class354 = Class282.aClass293_2129.method3460(-47, ha);
                    if (class354 == null) {
                        class354 = Class98_Sub10_Sub34.aClass43_5730;
                    }
                    class354.method416(Class341.anInt2858, Class282.aClass293_2129.anInt2236, Class39_Sub1.anInt3591, Class282.aClass293_2129.anInt2355, Class282.aClass293_2129.anInt2258, Class76_Sub8.aRandom3767, Class282.aClass293_2129.anInt2341, Class284_Sub1_Sub2.anIntArray6193, Class282.aClass293_2129.anInt2311, s, Class282.aClass293_2129.anInt2296, Class64_Sub5.anIntArray3652, -121, Class217.aClass332Array3408, Class314.anInt2690);
                    Class246_Sub3_Sub4_Sub3.method3071(Class284_Sub1_Sub2.anIntArray6193[3], -1, Class284_Sub1_Sub2.anIntArray6193[2], Class284_Sub1_Sub2.anIntArray6193[0], Class284_Sub1_Sub2.anIntArray6193[1]);
                }
                else if (Class265.aClass293_1979 != null && s_Sub1.aClass279_5197 == Class4.aClass279_86) {
                    Class246_Sub3_Sub4_Sub3.method3071(16, -1, Class42_Sub1.aClass197_5354.method2674(s, n + 106) - -Class98_Sub10_Sub34.aClass43_5730.method407(0, 16777215, 16 + Class64_Sub10.anInt3664, s, Class76_Sub8.aRandom3767, Class64_Sub5.anIntArray3652, Class39_Sub1.anInt3591, Class98_Sub10_Sub17.anInt5623 + 4, Class217.aClass332Array3408, -70), Class98_Sub10_Sub17.anInt5623 + 4, Class64_Sub10.anInt3664);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fv.E(" + n + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method927(final int n) {
        try {
            if (!Class98_Sub43_Sub2.aClass88_5907.aBoolean675) {
                try {
                    final Method method = ((Class96.aClass805 != null) ? Class96.aClass805 : (Class96.aClass805 = method929("java.lang.Runtime"))).getMethod("maxMemory", (Class[])new Class[0]);
                    if (method != null) {
                        try {
                            Class292.anInt3359 = 1 + (int)((long)method.invoke(Runtime.getRuntime(), (Object[])null) / 1048576L);
                        }
                        catch (Throwable t) {}
                    }
                }
                catch (Exception ex2) {}
            }
            else {
                Class292.anInt3359 = 96;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fv.C(" + n + ')');
        }
    }
    
    static final void method928(final byte b) {
        try {
            Class98_Sub37.aHa4185.L(Class284_Sub1_Sub2.anInt6192, (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub14_4049.method609((byte)122) == 0xFFFFFFFE) ? (Class263.anInt1965 + 256 << 1972322498) : -1, 0);
            if (b != -42) {
                Class96.anInt803 = 54;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fv.G(" + b + ')');
        }
    }
    
    static Class method929(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Class96.anInt803 = 0;
        Class96.anInt804 = 1339;
    }
}
