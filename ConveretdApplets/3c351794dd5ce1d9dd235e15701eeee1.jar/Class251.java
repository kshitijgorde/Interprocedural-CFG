// 
// Decompiled by Procyon v0.5.30
// 

final class Class251
{
    static int anInt1916;
    int anInt1917;
    int anInt1918;
    int anInt1919;
    int anInt1920;
    static IncomingOpcode aClass58_1921;
    
    public static void method3169(final byte b) {
        try {
            Class251.aClass58_1921 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pn.A(" + b + ')');
        }
    }
    
    static final void method3170(final int n, final int anInt2047, final boolean b, final int anInt2048, final int n2) {
        try {
            if (b || anInt2048 != Class160.anInt1258 || ~anInt2047 != ~Class275.anInt2047 || (~Class43.anInt377 != ~Class115.anInt963 && ~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4076.method564((byte)123) != 0xFFFFFFFE)) {
                Class160.anInt1258 = anInt2048;
                Class115.anInt963 = Class43.anInt377;
                Class275.anInt2047 = anInt2047;
                if (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4076.method564((byte)123) == 1) {
                    Class115.anInt963 = 0;
                }
                Class61.method538(n2, false);
                Class246_Sub2.method2972(-77, Class98_Sub46_Sub10.aClass197_6019, Class195.aClass43_1499, true, Class265.aHa1974, Class309.aClass309_2598.method3615(Class374.anInt3159, (byte)25));
                final int anInt2049 = Class272.anInt2038;
                Class272.anInt2038 = (Class160.anInt1258 - (Class165.anInt1276 >> -1323929948)) * 8;
                final int anInt2050 = aa_Sub2.anInt3562;
                aa_Sub2.anInt3562 = (-(Class98_Sub10_Sub7.anInt5572 >> 624271236) + Class275.anInt2047) * 8;
                Class98_Sub40.aClass98_Sub46_Sub10_4195 = Class278.method3303(Class160.anInt1258 * 8, Class275.anInt2047 * 8);
                Class64_Sub23.aClass370_3707 = null;
                final int n3 = -anInt2049 + Class272.anInt2038;
                final int n4 = -anInt2050 + aa_Sub2.anInt3562;
                if (~n2 == 0xFFFFFFF4) {
                    for (int n5 = 0; ~n5 > ~Class98_Sub10_Sub20.anInt5640; ++n5) {
                        final Class98_Sub39 class98_Sub39 = Class163.aClass98_Sub39Array3516[n5];
                        if (class98_Sub39 != null) {
                            final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4187 = class98_Sub39.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                            for (int i = 0; i < 10; ++i) {
                                final int[] anIntArray6437 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6437;
                                final int n6 = i;
                                anIntArray6437[n6] -= n3;
                                final int[] anIntArray6438 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6438;
                                final int n7 = i;
                                anIntArray6438[n7] -= n4;
                            }
                            final Class246_Sub3_Sub4_Sub2_Sub1 class246_Sub3_Sub4_Sub2_Sub1 = aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                            class246_Sub3_Sub4_Sub2_Sub1.anInt5079 -= n4 * 512;
                            final Class246_Sub3_Sub4_Sub2_Sub1 class246_Sub3_Sub4_Sub2_Sub2 = aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                            class246_Sub3_Sub4_Sub2_Sub2.anInt5084 -= n3 * 512;
                        }
                    }
                }
                else {
                    Class150.anInt1211 = 0;
                    boolean b2 = false;
                    final int n8 = (-1 + Class165.anInt1276) * 512;
                    final int n9 = (-1 + Class98_Sub10_Sub7.anInt5572) * 512;
                    for (int n10 = 0; Class98_Sub10_Sub20.anInt5640 > n10; ++n10) {
                        final Class98_Sub39 class98_Sub40 = Class163.aClass98_Sub39Array3516[n10];
                        if (class98_Sub40 != null) {
                            final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4188;
                            final Class246_Sub3_Sub4_Sub2_Sub1 class246_Sub3_Sub4_Sub2_Sub3 = aClass246_Sub3_Sub4_Sub2_Sub1_4188 = class98_Sub40.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                            aClass246_Sub3_Sub4_Sub2_Sub1_4188.anInt5084 -= 512 * n3;
                            final Class246_Sub3_Sub4_Sub2_Sub1 class246_Sub3_Sub4_Sub2_Sub4 = class246_Sub3_Sub4_Sub2_Sub3;
                            class246_Sub3_Sub4_Sub2_Sub4.anInt5079 -= n4 * 512;
                            if (~class246_Sub3_Sub4_Sub2_Sub3.anInt5084 > -1 || ~class246_Sub3_Sub4_Sub2_Sub3.anInt5084 < ~n8 || ~class246_Sub3_Sub4_Sub2_Sub3.anInt5079 > -1 || ~n9 > ~class246_Sub3_Sub4_Sub2_Sub3.anInt5079) {
                                class246_Sub3_Sub4_Sub2_Sub3.method3054(null, 1);
                                class98_Sub40.method942(113);
                                b2 = true;
                            }
                            else {
                                boolean b3 = true;
                                for (int n11 = 0; ~n11 > -11; ++n11) {
                                    final int[] anIntArray6439 = class246_Sub3_Sub4_Sub2_Sub3.anIntArray6437;
                                    final int n12 = n11;
                                    anIntArray6439[n12] -= n3;
                                    final int[] anIntArray6440 = class246_Sub3_Sub4_Sub2_Sub3.anIntArray6438;
                                    final int n13 = n11;
                                    anIntArray6440[n13] -= n4;
                                    if (class246_Sub3_Sub4_Sub2_Sub3.anIntArray6437[n11] < 0 || Class165.anInt1276 <= class246_Sub3_Sub4_Sub2_Sub3.anIntArray6437[n11] || ~class246_Sub3_Sub4_Sub2_Sub3.anIntArray6438[n11] > -1 || ~Class98_Sub10_Sub7.anInt5572 >= ~class246_Sub3_Sub4_Sub2_Sub3.anIntArray6438[n11]) {
                                        b3 = false;
                                    }
                                }
                                if (!b3) {
                                    class246_Sub3_Sub4_Sub2_Sub3.method3054(null, 1);
                                    class98_Sub40.method942(97);
                                    b2 = true;
                                }
                                else {
                                    Class325.anIntArray2726[Class150.anInt1211++] = class246_Sub3_Sub4_Sub2_Sub3.anInt6369;
                                }
                            }
                        }
                    }
                    if (b2) {
                        Class98_Sub10_Sub20.anInt5640 = Class260.aClass377_3254.method3999((byte)(-6));
                        Class260.aClass377_3254.method3992(Class163.aClass98_Sub39Array3516, (byte)74);
                    }
                }
                for (int j = 0; j < 2048; ++j) {
                    final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub5 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[j];
                    if (class246_Sub3_Sub4_Sub2_Sub5 != null) {
                        for (int k = 0; k < 10; ++k) {
                            final int[] anIntArray6441 = class246_Sub3_Sub4_Sub2_Sub5.anIntArray6437;
                            final int n14 = k;
                            anIntArray6441[n14] -= n3;
                            final int[] anIntArray6442 = class246_Sub3_Sub4_Sub2_Sub5.anIntArray6438;
                            final int n15 = k;
                            anIntArray6442[n15] -= n4;
                        }
                        final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub6 = class246_Sub3_Sub4_Sub2_Sub5;
                        class246_Sub3_Sub4_Sub2_Sub6.anInt5079 -= 512 * n4;
                        final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub7 = class246_Sub3_Sub4_Sub2_Sub5;
                        class246_Sub3_Sub4_Sub2_Sub7.anInt5084 -= 512 * n3;
                    }
                }
                if (n == -6547) {
                    final Class36[] aClass36Array903 = Class104.aClass36Array903;
                    for (int n16 = 0; ~n16 > ~aClass36Array903.length; ++n16) {
                        final Class36 class36 = aClass36Array903[n16];
                        if (class36 != null) {
                            final Class36 class37 = class36;
                            class37.anInt338 -= n3 * 512;
                            final Class36 class38 = class36;
                            class38.anInt347 -= 512 * n4;
                        }
                    }
                    for (Class98_Sub33 class98_Sub41 = (Class98_Sub33)Class191.aClass148_1478.method2418(32); class98_Sub41 != null; class98_Sub41 = (Class98_Sub33)Class191.aClass148_1478.method2417(88)) {
                        final Class98_Sub33 class98_Sub42 = class98_Sub41;
                        class98_Sub42.anInt4112 -= n3;
                        final Class98_Sub33 class98_Sub43 = class98_Sub41;
                        class98_Sub43.anInt4113 -= n4;
                        if (Class151_Sub9.anInt5028 != 4 && (~class98_Sub41.anInt4112 > -1 || class98_Sub41.anInt4113 < 0 || ~class98_Sub41.anInt4112 <= ~Class165.anInt1276 || Class98_Sub10_Sub7.anInt5572 <= class98_Sub41.anInt4113)) {
                            class98_Sub41.method942(83);
                        }
                    }
                    for (Class98_Sub33 class98_Sub44 = (Class98_Sub33)Class98_Sub11.aClass148_3866.method2418(32); class98_Sub44 != null; class98_Sub44 = (Class98_Sub33)Class98_Sub11.aClass148_3866.method2417(116)) {
                        final Class98_Sub33 class98_Sub45 = class98_Sub44;
                        class98_Sub45.anInt4113 -= n4;
                        final Class98_Sub33 class98_Sub46 = class98_Sub44;
                        class98_Sub46.anInt4112 -= n3;
                        if (Class151_Sub9.anInt5028 != 4 && (class98_Sub44.anInt4112 < 0 || class98_Sub44.anInt4113 < 0 || ~Class165.anInt1276 >= ~class98_Sub44.anInt4112 || Class98_Sub10_Sub7.anInt5572 <= class98_Sub44.anInt4113)) {
                            class98_Sub44.method942(n + 6603);
                        }
                    }
                    if (Class151_Sub9.anInt5028 != 4) {
                        for (Class98_Sub45 class98_Sub47 = (Class98_Sub45)Class146.aClass377_1180.method3998(101); class98_Sub47 != null; class98_Sub47 = (Class98_Sub45)Class146.aClass377_1180.method3995(n + 6546)) {
                            final int n17 = -Class272.anInt2038 + (int)(class98_Sub47.aLong832 & 0x3FFFL);
                            final int n18 = (int)(class98_Sub47.aLong832 >> -937625842 & 0x3FFFL) + -aa_Sub2.anInt3562;
                            if (~n17 > -1 || n18 < 0 || Class165.anInt1276 <= n17 || Class98_Sub10_Sub7.anInt5572 <= n18) {
                                class98_Sub47.method942(61);
                            }
                        }
                    }
                    if (~Class269.anInt2024 != -1) {
                        Class246_Sub3_Sub1_Sub2.anInt6251 -= n4;
                        Class269.anInt2024 -= n3;
                    }
                    Class220.method2820((byte)126);
                    if (~n2 != 0xFFFFFFF4) {
                        Class98_Sub10_Sub21.anInt5643 -= n4;
                        Exception_Sub1.anInt44 -= n3;
                        Class363.anInt3098 -= n3;
                        Class98_Sub15.anInt3915 -= n4;
                        Class134.anInt3461 -= n4 * 512;
                        Class98_Sub46_Sub10.anInt6020 -= 512 * n3;
                        if (Math.abs(n3) > Class165.anInt1276 || Math.abs(n4) > Class98_Sub10_Sub7.anInt5572) {
                            Class374.method3980((byte)124);
                        }
                    }
                    else if (Class98_Sub46_Sub20_Sub2.anInt6319 == 4) {
                        Class224_Sub3_Sub1.anInt6147 -= n4 * 512;
                        Class135.anInt1051 -= 512 * n4;
                        Class201.anInt1545 -= 512 * n3;
                        Class98_Sub46_Sub2_Sub2.anInt6295 -= n3 * 512;
                    }
                    else {
                        Class116.anInt967 = (Class64_Sub26.anInt3712 = -1);
                        Class98_Sub46_Sub20_Sub2.anInt6319 = 1;
                    }
                    Class64_Sub18.method622((byte)(-38));
                    InputStream_Sub2.method124(n + 6456);
                    Class98_Sub10_Sub11.aClass148_5596.method2422((byte)47);
                    Class280.aClass148_2108.method2422((byte)47);
                    Class64_Sub20.aClass218_3694.method2802(n + 6435);
                    Class98_Sub20.method1171(n + 6547);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pn.B(" + n + ',' + anInt2047 + ',' + b + ',' + anInt2048 + ',' + n2 + ')');
        }
    }
    
    static final void method3171(final String s, final int n, final String s2, final int anInt4855) {
        try {
            if (n != -17877) {
                Class251.anInt1916 = -7;
            }
            Class146_Sub2.anInt4855 = anInt4855;
            Class98_Sub46_Sub20_Sub2.anInt6317 = 2;
            Class342.method3814(false, s2, n + 17782, s);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pn.C(" + ((s != null) ? "{...}" : "null") + ',' + n + ',' + ((s2 != null) ? "{...}" : "null") + ',' + anInt4855 + ')');
        }
    }
    
    static {
        Class251.aClass58_1921 = new IncomingOpcode(4, 6);
    }
}
