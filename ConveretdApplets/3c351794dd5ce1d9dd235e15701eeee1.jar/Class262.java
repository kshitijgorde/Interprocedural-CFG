// 
// Decompiled by Procyon v0.5.30
// 

final class Class262
{
    static int[] anIntArray1961;
    static int[] anIntArray1962;
    
    static final void method3213() {
        for (int i = Class32.anInt305; i < Class364.anInt3103; ++i) {
            for (int j = 0; j < Class366.anInt3112; ++j) {
                for (int k = 0; k < Class64_Sub9.anInt3662; ++k) {
                    final Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[i][j][k];
                    if (class172 != null) {
                        final Class246_Sub3_Sub3 aClass246_Sub3_Sub3_1324 = class172.aClass246_Sub3_Sub3_1324;
                        final Class246_Sub3_Sub3 aClass246_Sub3_Sub3_1325 = class172.aClass246_Sub3_Sub3_1333;
                        if (aClass246_Sub3_Sub3_1324 != null && aClass246_Sub3_Sub3_1324.method2982((byte)(-105))) {
                            Class64_Sub2.method562(aClass246_Sub3_Sub3_1324, i, j, k, 1, 1);
                            if (aClass246_Sub3_Sub3_1325 != null && aClass246_Sub3_Sub3_1325.method2982((byte)(-90))) {
                                Class64_Sub2.method562(aClass246_Sub3_Sub3_1325, i, j, k, 1, 1);
                                aClass246_Sub3_Sub3_1325.method2981(aClass246_Sub3_Sub3_1324, (byte)(-106), false, 0, Class98_Sub10_Sub30.aHa5709, 0, 0);
                                aClass246_Sub3_Sub3_1325.method2992((byte)(-73));
                            }
                            aClass246_Sub3_Sub3_1324.method2992((byte)(-73));
                        }
                        for (Class154 class173 = class172.aClass154_1325; class173 != null; class173 = class173.aClass154_1233) {
                            final Class246_Sub3_Sub4 aClass246_Sub3_Sub4_1232 = class173.aClass246_Sub3_Sub4_1232;
                            if (aClass246_Sub3_Sub4_1232 != null && aClass246_Sub3_Sub4_1232.method2982((byte)(-128))) {
                                Class64_Sub2.method562(aClass246_Sub3_Sub4_1232, i, j, k, aClass246_Sub3_Sub4_1232.aShort6160 - aClass246_Sub3_Sub4_1232.aShort6158 + 1, aClass246_Sub3_Sub4_1232.aShort6159 - aClass246_Sub3_Sub4_1232.aShort6157 + 1);
                                aClass246_Sub3_Sub4_1232.method2992((byte)(-73));
                            }
                        }
                        final Class246_Sub3_Sub1 aClass246_Sub3_Sub1_1332 = class172.aClass246_Sub3_Sub1_1332;
                        if (aClass246_Sub3_Sub1_1332 != null && aClass246_Sub3_Sub1_1332.method2982((byte)(-93))) {
                            Class98_Sub46_Sub9.method1553(aClass246_Sub3_Sub1_1332, i, j, k);
                            aClass246_Sub3_Sub1_1332.method2992((byte)(-73));
                        }
                    }
                }
            }
        }
    }
    
    public static void method3214(final int n) {
        try {
            Class262.anIntArray1961 = null;
            Class262.anIntArray1962 = null;
            if (n > -118) {
                method3214(-8);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qh.C(" + n + ')');
        }
    }
    
    static final void method3215(final int n, final int[] array, final int[] array2, final Class246_Sub3_Sub4_Sub2_Sub1 class246_Sub3_Sub4_Sub2_Sub1, final int[] array3) {
        try {
            if (n == 26256) {
                for (int n2 = 0; ~n2 > ~array3.length; ++n2) {
                    final int anInt1700 = array3[n2];
                    int n3 = array2[n2];
                    final int n4 = array[n2];
                    for (int n5 = 0; n3 != 0 && ~n5 > ~class246_Sub3_Sub4_Sub2_Sub1.aClass226Array6387.length; n3 >>>= 1, ++n5) {
                        if (~(0x1 & n3) != -1) {
                            if (~anInt1700 != 0x0) {
                                final Class97 method2623 = Class151_Sub7.aClass183_5001.method2623(anInt1700, 16383);
                                final int anInt1701 = method2623.anInt819;
                                Class226 class226 = class246_Sub3_Sub4_Sub2_Sub1.aClass226Array6387[n5];
                                if (class226 != null) {
                                    if (class226.anInt1700 != anInt1700) {
                                        if (~method2623.anInt829 <= ~Class151_Sub7.aClass183_5001.method2623(class226.anInt1700, n ^ 0x596F).anInt829) {
                                            final Class226[] aClass226Array6387 = class246_Sub3_Sub4_Sub2_Sub1.aClass226Array6387;
                                            final int n6 = n5;
                                            final Class226 class227 = null;
                                            aClass226Array6387[n6] = class227;
                                            class226 = class227;
                                        }
                                    }
                                    else if (~anInt1701 != -1) {
                                        if (~anInt1701 == 0xFFFFFFFE) {
                                            class226.anInt1707 = 0;
                                            class226.anInt1702 = 0;
                                            class226.anInt1704 = 0;
                                            class226.anInt1703 = n4;
                                            class226.anInt1701 = 1;
                                            if (!class246_Sub3_Sub4_Sub2_Sub1.aBoolean6371) {
                                                Class349.method3840((byte)(-127), class246_Sub3_Sub4_Sub2_Sub1, 0, method2623);
                                            }
                                        }
                                        else if (~anInt1701 == 0xFFFFFFFD) {
                                            class226.anInt1704 = 0;
                                        }
                                    }
                                    else {
                                        final Class226[] aClass226Array6388 = class246_Sub3_Sub4_Sub2_Sub1.aClass226Array6387;
                                        final int n7 = n5;
                                        final Class226 class228 = null;
                                        aClass226Array6388[n7] = class228;
                                        class226 = class228;
                                    }
                                }
                                if (class226 == null) {
                                    final Class226[] aClass226Array6389 = class246_Sub3_Sub4_Sub2_Sub1.aClass226Array6387;
                                    final int n8 = n5;
                                    final Class226 class229 = new Class226();
                                    aClass226Array6389[n8] = class229;
                                    final Class226 class230 = class229;
                                    class230.anInt1703 = n4;
                                    class230.anInt1704 = 0;
                                    class230.anInt1700 = anInt1700;
                                    class230.anInt1707 = 0;
                                    class230.anInt1702 = 0;
                                    class230.anInt1701 = 1;
                                    if (!class246_Sub3_Sub4_Sub2_Sub1.aBoolean6371) {
                                        Class349.method3840((byte)(-126), class246_Sub3_Sub4_Sub2_Sub1, 0, method2623);
                                    }
                                }
                            }
                            else {
                                class246_Sub3_Sub4_Sub2_Sub1.aClass226Array6387[n5] = null;
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qh.A(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + ((class246_Sub3_Sub4_Sub2_Sub1 != null) ? "{...}" : "null") + ',' + ((array3 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class262.anIntArray1961 = new int[] { 32, 39, 44, 47 };
        Class262.anIntArray1962 = new int[3];
    }
}
