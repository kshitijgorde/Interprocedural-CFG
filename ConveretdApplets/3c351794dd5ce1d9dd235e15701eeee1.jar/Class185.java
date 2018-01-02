import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class185
{
    short[] aShortArray1444;
    static Class246_Sub4_Sub2_Sub1[] aClass246_Sub4_Sub2_Sub1Array1445;
    int[] anIntArray1446;
    short[] aShortArray1447;
    long aLong1448;
    
    static final Class98_Sub46_Sub17 method2628(final int n, final int n2, final int n3) {
        try {
            Class98_Sub46_Sub17 class98_Sub46_Sub17 = (Class98_Sub46_Sub17)Class76_Sub4.aClass377_3738.method3990(n3 << 1046340512 | n, -1);
            if (n2 >= -17) {
                Class185.aClass246_Sub4_Sub2_Sub1Array1445 = null;
            }
            if (class98_Sub46_Sub17 == null) {
                class98_Sub46_Sub17 = new Class98_Sub46_Sub17(n3, n);
                Class76_Sub4.aClass377_3738.method3996(class98_Sub46_Sub17, class98_Sub46_Sub17.aLong832, -1);
            }
            return class98_Sub46_Sub17;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mg.B(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method2629(final byte b) {
        try {
            if (~Class98_Sub10_Sub6.anInt5569 < -2) {
                Class98_Sub36.anInt4161 = Class24.anInt242;
                --Class98_Sub10_Sub6.anInt5569;
            }
            if (Class76_Sub9.aBoolean3788) {
                Class76_Sub9.aBoolean3788 = false;
                Canvas_Sub1.method118((byte)104);
            }
            else {
                if (!Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540) {
                    Class46.method435((byte)85);
                }
                for (int n = 0; ~n > -101 && Class98_Sub10_Sub24.method1076(b - 58); ++n) {}
                if (Class177.anInt1376 == 10) {
                    while (Class92.method893(118)) {
                        final Class98_Sub11 method3023 = Class246_Sub3_Sub4.method3023(b + 313, Class98_Sub10_Sub7.aClass171_5571, Class331.aClass117_2811);
                        method3023.aClass98_Sub22_Sub1_3865.method1194(0, b + 138);
                        final int anInt3991 = method3023.aClass98_Sub22_Sub1_3865.anInt3991;
                        Class361.method3919((byte)(-61), method3023.aClass98_Sub22_Sub1_3865);
                        method3023.aClass98_Sub22_Sub1_3865.method1211((byte)91, -anInt3991 + method3023.aClass98_Sub22_Sub1_3865.anInt3991);
                        Class98_Sub10_Sub29.sendPacket(false, method3023);
                    }
                    if (Class284.aClass98_Sub4_2167 != null) {
                        if (Class284.aClass98_Sub4_2167.anInt3827 != -1) {
                            final Class98_Sub11 method3024 = Class246_Sub3_Sub4.method3023(260, Class98_Sub10_Sub22.aClass171_5652, Class331.aClass117_2811);
                            method3024.aClass98_Sub22_Sub1_3865.writeShort(Class284.aClass98_Sub4_2167.anInt3827, 1571862888);
                            Class98_Sub10_Sub29.sendPacket(false, method3024);
                            Class284.aClass98_Sub4_2167 = null;
                            Class11.aLong121 = 30000L + Class343.method3819(-47);
                        }
                    }
                    else if (~Class343.method3819(b ^ 0x1A) <= ~Class11.aLong121) {
                        Class284.aClass98_Sub4_2167 = Class48_Sub2_Sub1.aClass265_5531.method3229(b ^ 0xFFFFFFF4, Class98_Sub46_Sub10.aClass354_6011.aString3016);
                    }
                    final Class98_Sub17 class98_Sub17 = (Class98_Sub17)Class167.aClass148_1284.method2418(32);
                    if (class98_Sub17 != null || -2000L + Class343.method3819(-47) > Class113.aLong954) {}
                    if (class98_Sub17 != null) {
                        long n2 = (class98_Sub17.method1154(true) - Class98_Sub36.aLong4159) / 50L;
                        Class98_Sub36.aLong4159 = class98_Sub17.method1154(true);
                        if (n2 > 32767L) {
                            n2 = 32767L;
                        }
                        int method3025 = class98_Sub17.method1151(89);
                        if (~method3025 > -1) {
                            method3025 = 0;
                        }
                        else if (~method3025 < -65536) {
                            method3025 = 65535;
                        }
                        int method3026 = class98_Sub17.method1155(122);
                        if (method3026 >= 0) {
                            if (method3026 > 65535) {
                                method3026 = 65535;
                            }
                        }
                        else {
                            method3026 = 0;
                        }
                        int n3 = 0;
                        if (class98_Sub17.method1156(-5) == 2) {
                            n3 = 1;
                        }
                        final int n4 = (int)n2;
                        final Class98_Sub11 method3027 = Class246_Sub3_Sub4.method3023(260, Class287_Sub2.aClass171_3270, Class331.aClass117_2811);
                        method3027.aClass98_Sub22_Sub1_3865.writeShort(n3 << 391207055 | n4, b + 1571862941);
                        method3027.aClass98_Sub22_Sub1_3865.writeInt(b + 1571862941, method3026 | method3025 << -1879594384);
                        Class98_Sub10_Sub29.sendPacket(false, method3027);
                    }
                    if (~Class98_Sub46_Sub1.anInt5949 < -1) {
                        final Class98_Sub11 method3028 = Class246_Sub3_Sub4.method3023(260, Class98_Sub32.aClass171_4106, Class331.aClass117_2811);
                        method3028.aClass98_Sub22_Sub1_3865.method1194(3 * Class98_Sub46_Sub1.anInt5949, 78);
                        for (int n5 = 0; ~Class98_Sub46_Sub1.anInt5949 < ~n5; ++n5) {
                            final Interface7 interface7 = Class339.anInterface7Array2845[n5];
                            long n6 = (interface7.method18(-121) - Class292.aLong3356) / 50L;
                            Class292.aLong3356 = interface7.method18(-84);
                            if (~n6 < -65536L) {
                                n6 = 65535L;
                            }
                            method3028.aClass98_Sub22_Sub1_3865.method1194(interface7.method17(true), 89);
                            method3028.aClass98_Sub22_Sub1_3865.writeShort((int)n6, b + 1571862941);
                        }
                        Class98_Sub10_Sub29.sendPacket(false, method3028);
                    }
                    if (~Class42_Sub1_Sub1.anInt6210 < -1) {
                        --Class42_Sub1_Sub1.anInt6210;
                    }
                    if (Class64_Sub6.aBoolean3656 && ~Class42_Sub1_Sub1.anInt6210 >= -1) {
                        Class64_Sub6.aBoolean3656 = false;
                        Class42_Sub1_Sub1.anInt6210 = 20;
                        final Class98_Sub11 method3029 = Class246_Sub3_Sub4.method3023(b + 313, Class246_Sub7.aClass171_5115, Class331.aClass117_2811);
                        method3029.aClass98_Sub22_Sub1_3865.writeLEShort((int)Class119_Sub4.aFloat4740 >> -52235901, 17624);
                        method3029.aClass98_Sub22_Sub1_3865.writeShort((int)Class98_Sub22_Sub2.aFloat5794 >> -449746077, b + 1571862941);
                        Class98_Sub10_Sub29.sendPacket(false, method3029);
                    }
                    if (Class4.aBoolean84 == !Class101.aBoolean856) {
                        Class101.aBoolean856 = Class4.aBoolean84;
                        final Class98_Sub11 method3030 = Class246_Sub3_Sub4.method3023(260, Class230.aClass171_1727, Class331.aClass117_2811);
                        method3030.aClass98_Sub22_Sub1_3865.method1194(Class4.aBoolean84 ? 1 : 0, -93);
                        Class98_Sub10_Sub29.sendPacket(false, method3030);
                    }
                    if (!s_Sub1.aBoolean5207) {
                        final Class98_Sub11 method3031 = Class246_Sub3_Sub4.method3023(b + 313, Class246_Sub1.aClass171_5068, Class331.aClass117_2811);
                        method3031.aClass98_Sub22_Sub1_3865.method1194(0, b - 27);
                        final int anInt3992 = method3031.aClass98_Sub22_Sub1_3865.anInt3991;
                        final Class98_Sub22 method3032 = Class98_Sub9.aClass98_Sub27_3856.method1288(true);
                        method3031.aClass98_Sub22_Sub1_3865.method1217(method3032.aByteArray3992, method3032.anInt3991, -1, 0);
                        method3031.aClass98_Sub22_Sub1_3865.method1211((byte)107, method3031.aClass98_Sub22_Sub1_3865.anInt3991 - anInt3992);
                        Class98_Sub10_Sub29.sendPacket(false, method3031);
                        s_Sub1.aBoolean5207 = true;
                    }
                    if (Class98_Sub46_Sub1.aClass172ArrayArrayArray5948 != null) {
                        if (~Class98_Sub46_Sub20_Sub2.anInt6319 == 0xFFFFFFFD) {
                            Class98_Sub46_Sub3.method1541(73);
                        }
                        else if (~Class98_Sub46_Sub20_Sub2.anInt6319 == 0xFFFFFFFC) {
                            Class183.method2620(0);
                        }
                    }
                    if (Class4.aBoolean79) {
                        Class4.aBoolean79 = false;
                    }
                    else {
                        Class180.aFloat3405 /= 2.0f;
                    }
                    if (b != -53) {
                        Class185.aClass246_Sub4_Sub2_Sub1Array1445 = null;
                    }
                    if (PacketSender.aBoolean2575) {
                        PacketSender.aBoolean2575 = false;
                    }
                    else {
                        Class305.aFloat2545 /= 2.0f;
                    }
                    Class141.method2294(0);
                    if (~Class177.anInt1376 == 0xFFFFFFF5) {
                        Class329.method3708(-1);
                        Class269.method3273(true);
                        Class246_Sub3_Sub2.method3003(b ^ 0x54BC);
                        ++Class224_Sub1.anInt5031;
                        if (Class224_Sub1.anInt5031 > 750) {
                            Canvas_Sub1.method118((byte)104);
                        }
                        else {
                            Class50.method485(37);
                            Class216.method2794((byte)(-85));
                            Class76_Sub10.method770(-256);
                            for (int i = Class75.aClass140_584.method2290(-115, true); i != -1; i = Class75.aClass140_584.method2290(-87, false)) {
                                Class349.method3838(i, 1864);
                                Class111_Sub1.anIntArray4682[Class202.method2702(31, Class239.anInt1844++)] = i;
                            }
                            for (Class98_Sub46_Sub17 class98_Sub46_Sub17 = Class98_Sub31_Sub4.method1384(b ^ 0x4B); class98_Sub46_Sub17 != null; class98_Sub46_Sub17 = Class98_Sub31_Sub4.method1384(-46)) {
                                final int method3033 = class98_Sub46_Sub17.method1625((byte)(-108));
                                final int method3034 = class98_Sub46_Sub17.method1623(-101);
                                if (method3033 == 1) {
                                    Class76_Sub5.anIntArray3744[method3034] = class98_Sub46_Sub17.anInt6054;
                                    Class66.aBoolean507 |= Class140.aBooleanArray3246[method3034];
                                    Class98_Sub45.anIntArray4257[Class202.method2702(Class246_Sub4_Sub2.anInt6181++, 31)] = method3034;
                                }
                                else if (method3033 != 2) {
                                    if (~method3033 != 0xFFFFFFFC) {
                                        if (method3033 == 4) {
                                            final Class293 method3035 = Class159.method2509(method3034, -9820);
                                            final int anInt3993 = class98_Sub46_Sub17.anInt6054;
                                            final int anInt3994 = class98_Sub46_Sub17.anInt6051;
                                            final int anInt3995 = class98_Sub46_Sub17.anInt6053;
                                            if (method3035.anInt2233 != anInt3993 || ~anInt3994 != ~method3035.anInt2343 || anInt3995 != method3035.anInt2210) {
                                                method3035.anInt2233 = anInt3993;
                                                method3035.anInt2343 = anInt3994;
                                                method3035.anInt2210 = anInt3995;
                                                Class341.method3812(1, method3035);
                                            }
                                        }
                                        else if (~method3033 != 0xFFFFFFFA) {
                                            if (~method3033 != 0xFFFFFFF9) {
                                                if (~method3033 != 0xFFFFFFF8) {
                                                    if (method3033 == 8) {
                                                        final Class293 method3036 = Class159.method2509(method3034, b - 9767);
                                                        if (class98_Sub46_Sub17.anInt6054 != method3036.anInt2310 || ~method3036.anInt2218 != ~class98_Sub46_Sub17.anInt6051 || class98_Sub46_Sub17.anInt6053 != method3036.anInt2251) {
                                                            method3036.anInt2310 = class98_Sub46_Sub17.anInt6054;
                                                            method3036.anInt2251 = class98_Sub46_Sub17.anInt6053;
                                                            method3036.anInt2218 = class98_Sub46_Sub17.anInt6051;
                                                            if (method3036.anInt2302 != -1) {
                                                                if (method3036.anInt2232 <= 0) {
                                                                    if (method3036.anInt2235 > 0) {
                                                                        method3036.anInt2251 = 32 * method3036.anInt2251 / method3036.anInt2235;
                                                                    }
                                                                }
                                                                else {
                                                                    method3036.anInt2251 = 32 * method3036.anInt2251 / method3036.anInt2232;
                                                                }
                                                            }
                                                            Class341.method3812(1, method3036);
                                                        }
                                                    }
                                                    else if (~method3033 != 0xFFFFFFF6) {
                                                        if (~method3033 != 0xFFFFFFF5) {
                                                            if (method3033 == 11) {
                                                                final Class293 method3037 = Class159.method2509(method3034, -9820);
                                                                method3037.aByte2245 = 0;
                                                                method3037.aByte2240 = 0;
                                                                final Class293 class293 = method3037;
                                                                final Class293 class294 = method3037;
                                                                final int anInt3996 = class98_Sub46_Sub17.anInt6054;
                                                                class294.anInt2283 = anInt3996;
                                                                class293.anInt2347 = anInt3996;
                                                                final Class293 class295 = method3037;
                                                                final Class293 class296 = method3037;
                                                                final int anInt3997 = class98_Sub46_Sub17.anInt6051;
                                                                class296.anInt2229 = anInt3997;
                                                                class295.anInt2299 = anInt3997;
                                                                Class341.method3812(1, method3037);
                                                            }
                                                            else if (method3033 != 12) {
                                                                if (~method3033 == 0xFFFFFFF1) {
                                                                    Class159.method2509(method3034, b ^ 0x266F).anInt2237 = class98_Sub46_Sub17.anInt6054;
                                                                }
                                                                else if (~method3033 != 0xFFFFFFF0) {
                                                                    if (method3033 != 16) {
                                                                        if (method3033 == 17) {
                                                                            Class159.method2509(method3034, -9820).anInt2211 = class98_Sub46_Sub17.anInt6054;
                                                                        }
                                                                    }
                                                                    else {
                                                                        Class159.method2509(method3034, -9820).anInt2264 = class98_Sub46_Sub17.anInt6054;
                                                                    }
                                                                }
                                                                else {
                                                                    Class269.anInt2024 = class98_Sub46_Sub17.anInt6054;
                                                                    Class246_Sub3_Sub1_Sub2.anInt6251 = class98_Sub46_Sub17.anInt6051;
                                                                    Class365.aBoolean3110 = true;
                                                                }
                                                            }
                                                            else {
                                                                final Class293 method3038 = Class159.method2509(method3034, -9820);
                                                                int anInt3998 = class98_Sub46_Sub17.anInt6054;
                                                                if (method3038 != null && ~method3038.anInt2354 == -1) {
                                                                    if (-method3038.anInt2258 + method3038.anInt2228 < anInt3998) {
                                                                        anInt3998 = method3038.anInt2228 + -method3038.anInt2258;
                                                                    }
                                                                    if (~anInt3998 > -1) {
                                                                        anInt3998 = 0;
                                                                    }
                                                                    if (~anInt3998 != ~method3038.anInt2213) {
                                                                        method3038.anInt2213 = anInt3998;
                                                                        Class341.method3812(1, method3038);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        else {
                                                            final Class293 method3039 = Class159.method2509(method3034, -9820);
                                                            if (~class98_Sub46_Sub17.anInt6054 != ~method3039.anInt2268 || method3039.anInt2273 != class98_Sub46_Sub17.anInt6051 || ~method3039.anInt2346 != ~class98_Sub46_Sub17.anInt6053) {
                                                                method3039.anInt2346 = class98_Sub46_Sub17.anInt6053;
                                                                method3039.anInt2268 = class98_Sub46_Sub17.anInt6054;
                                                                method3039.anInt2273 = class98_Sub46_Sub17.anInt6051;
                                                                Class341.method3812(1, method3039);
                                                            }
                                                        }
                                                    }
                                                    else {
                                                        final Class293 method3040 = Class159.method2509(method3034, -9820);
                                                        if (~class98_Sub46_Sub17.anInt6054 != ~method3040.anInt2302 || ~method3040.anInt2349 != ~class98_Sub46_Sub17.anInt6051) {
                                                            method3040.anInt2349 = class98_Sub46_Sub17.anInt6051;
                                                            method3040.anInt2302 = class98_Sub46_Sub17.anInt6054;
                                                            Class341.method3812(1, method3040);
                                                        }
                                                    }
                                                }
                                                else {
                                                    final Class293 method3041 = Class159.method2509(method3034, b ^ 0x266F);
                                                    final boolean aBoolean2295 = ~class98_Sub46_Sub17.anInt6054 == 0xFFFFFFFE;
                                                    if (!method3041.aBoolean2295 != !aBoolean2295) {
                                                        method3041.aBoolean2295 = aBoolean2295;
                                                        Class341.method3812(b + 54, method3041);
                                                    }
                                                }
                                            }
                                            else {
                                                final int anInt3999 = class98_Sub46_Sub17.anInt6054;
                                                final int anInt4000 = ((anInt3999 >> 504954597 & 0x1F) << -116011253) + ((0x7D21 & anInt3999) >> -1879977206 << 1244792819) + ((0x1F & anInt3999) << -1908460573);
                                                final Class293 method3042 = Class159.method2509(method3034, -9820);
                                                if (~anInt4000 != ~method3042.anInt2236) {
                                                    method3042.anInt2236 = anInt4000;
                                                    Class341.method3812(1, method3042);
                                                }
                                            }
                                        }
                                        else {
                                            final Class293 method3043 = Class159.method2509(method3034, -9820);
                                            if (method3043.anInt2208 != class98_Sub46_Sub17.anInt6054 || ~class98_Sub46_Sub17.anInt6054 == 0x0) {
                                                method3043.anInt2208 = class98_Sub46_Sub17.anInt6054;
                                                method3043.anInt2303 = 0;
                                                method3043.anInt2312 = 0;
                                                method3043.anInt2287 = 1;
                                                final Class97 class297 = (~method3043.anInt2208 != 0x0) ? Class151_Sub7.aClass183_5001.method2623(method3043.anInt2208, b + 16436) : null;
                                                if (class297 != null) {
                                                    Class280.method3327(method3043.anInt2303, class297, (byte)118);
                                                }
                                                Class341.method3812(b + 54, method3043);
                                            }
                                        }
                                    }
                                    else {
                                        final Class293 method3044 = Class159.method2509(method3034, b - 9767);
                                        if (!class98_Sub46_Sub17.aString6052.equals(method3044.aString2225)) {
                                            method3044.aString2225 = class98_Sub46_Sub17.aString6052;
                                            Class341.method3812(b + 54, method3044);
                                        }
                                    }
                                }
                                else {
                                    Class151_Sub1.aStringArray4967[method3034] = class98_Sub46_Sub17.aString6052;
                                    Class148.anIntArray1196[Class202.method2702(31, Class96.anInt803++)] = method3034;
                                }
                            }
                            ++Class279.anInt2099;
                            if (~Class55.anInt440 != -1) {
                                Class98_Sub10_Sub32.anInt5720 += 20;
                                if (~Class98_Sub10_Sub32.anInt5720 <= -401) {
                                    Class55.anInt440 = 0;
                                }
                            }
                            if (Class77.aClass293_593 != null) {
                                ++Class42_Sub3.anInt5365;
                                if (~Class42_Sub3.anInt5365 <= -16) {
                                    Class341.method3812(1, Class77.aClass293_593);
                                    Class77.aClass293_593 = null;
                                }
                            }
                            Class162.aClass293_1272 = null;
                            Class239.aBoolean1840 = false;
                            Class11.aClass293_120 = null;
                            Class166.aBoolean1278 = false;
                            Class98_Sub1.method946(-1, -123, -1, null);
                            Class304.method3563(-1, null, -1, b ^ 0xFFFFFFF7);
                            if (!Class98_Sub10_Sub9.aBoolean5585) {
                                Class21_Sub2.anInt5387 = -1;
                            }
                            Class3.method172(98);
                            ++Class24.anInt242;
                            if (Class102.aBoolean889) {
                                final Class98_Sub11 method3045 = Class246_Sub3_Sub4.method3023(260, Class98_Sub43_Sub2.aClass171_5906, Class331.aClass117_2811);
                                method3045.aClass98_Sub22_Sub1_3865.method1232(Class375.anInt3169 | (Class122.anInt1009 << 514171598 | Class333.anInt3385 << -509241764), (byte)106);
                                Class98_Sub10_Sub29.sendPacket(false, method3045);
                                Class102.aBoolean889 = false;
                            }
                            while (true) {
                                final Class98_Sub21 class98_Sub18 = (Class98_Sub21)Class181.aClass148_1430.method2421(b ^ 0xFFFFE695);
                                if (class98_Sub18 == null) {
                                    break;
                                }
                                final Class293 aClass293_3986 = class98_Sub18.aClass293_3986;
                                if (~aClass293_3986.anInt2300 <= -1) {
                                    final Class293 method3046 = Class159.method2509(aClass293_3986.anInt2234, b ^ 0x266F);
                                    if (method3046 == null || method3046.aClass293Array2339 == null || ~aClass293_3986.anInt2300 <= ~method3046.aClass293Array2339.length) {
                                        continue;
                                    }
                                    if (method3046.aClass293Array2339[aClass293_3986.anInt2300] != aClass293_3986) {
                                        continue;
                                    }
                                }
                                Class247.method3144(class98_Sub18);
                            }
                            while (true) {
                                final Class98_Sub21 class98_Sub19 = (Class98_Sub21)Class98_Sub46_Sub10.aClass148_6018.method2421(b ^ 0xFFFFE695);
                                if (class98_Sub19 == null) {
                                    break;
                                }
                                final Class293 aClass293_3987 = class98_Sub19.aClass293_3986;
                                if (~aClass293_3987.anInt2300 <= -1) {
                                    final Class293 method3047 = Class159.method2509(aClass293_3987.anInt2234, -9820);
                                    if (method3047 == null || method3047.aClass293Array2339 == null || aClass293_3987.anInt2300 >= method3047.aClass293Array2339.length) {
                                        continue;
                                    }
                                    if (method3047.aClass293Array2339[aClass293_3987.anInt2300] != aClass293_3987) {
                                        continue;
                                    }
                                }
                                Class247.method3144(class98_Sub19);
                            }
                            while (true) {
                                final Class98_Sub21 class98_Sub20 = (Class98_Sub21)Class151_Sub3.aClass148_4977.method2421(6494);
                                if (class98_Sub20 == null) {
                                    break;
                                }
                                final Class293 aClass293_3988 = class98_Sub20.aClass293_3986;
                                if (aClass293_3988.anInt2300 >= 0) {
                                    final Class293 method3048 = Class159.method2509(aClass293_3988.anInt2234, -9820);
                                    if (method3048 == null || method3048.aClass293Array2339 == null || ~method3048.aClass293Array2339.length >= ~aClass293_3988.anInt2300) {
                                        continue;
                                    }
                                    if (aClass293_3988 != method3048.aClass293Array2339[aClass293_3988.anInt2300]) {
                                        continue;
                                    }
                                }
                                Class247.method3144(class98_Sub20);
                            }
                            if (Class11.aClass293_120 == null) {
                                Class156_Sub2.anInt3423 = 0;
                            }
                            if (Class255.aClass293_3208 != null) {
                                Class111_Sub3.method2118(19653);
                            }
                            if (Class282.anInt2125 > 0 && Class219.aClass77_1641.method779(82, 5503) && Class219.aClass77_1641.method779(81, 5503) && ~Class319.anInt2699 != -1) {
                                int n7 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088 - Class319.anInt2699;
                                if (n7 >= 0) {
                                    if (n7 > 3) {
                                        n7 = 3;
                                    }
                                }
                                else {
                                    n7 = 0;
                                }
                                Class351.method3846(Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6438[0] + aa_Sub2.anInt3562, Class272.anInt2038 + Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6437[0], n7, 52);
                            }
                            Class204.method2709((byte)49);
                            for (int j = 0; j < 5; ++j) {
                                final int[] anIntArray1597 = Class212.anIntArray1597;
                                final int n8 = j;
                                ++anIntArray1597[n8];
                            }
                            if (Class66.aBoolean507 && ~r_Sub1.aLong6322 > ~(-60000L + Class343.method3819(-47))) {
                                Class23.method283((byte)118);
                            }
                            for (Class246_Sub4_Sub1 class246_Sub4_Sub1 = (Class246_Sub4_Sub1)Class119.aClass218_986.method2803((byte)15); class246_Sub4_Sub1 != null; class246_Sub4_Sub1 = (Class246_Sub4_Sub1)Class119.aClass218_986.method2809(false)) {
                                if (~(-5L + Class343.method3819(b ^ 0x1A) / 1000L) < ~class246_Sub4_Sub1.anInt6172) {
                                    if (class246_Sub4_Sub1.aShort6167 > 0) {
                                        Class98_Sub45.method1521((byte)125, 5, class246_Sub4_Sub1.aString6168 + Class309.aClass309_2605.method3615(Class374.anInt3159, (byte)25), 0, "", "", "");
                                    }
                                    if (~class246_Sub4_Sub1.aShort6167 == -1) {
                                        Class98_Sub45.method1521((byte)(-120), 5, class246_Sub4_Sub1.aString6168 + Class309.aClass309_2606.method3615(Class374.anInt3159, (byte)25), 0, "", "", "");
                                    }
                                    class246_Sub4_Sub1.method2965((byte)126);
                                }
                            }
                            ++Class134_Sub1.anInt5459;
                            if (Class134_Sub1.anInt5459 > 500) {
                                Class134_Sub1.anInt5459 = 0;
                                final int n9 = (int)(Math.random() * 8.0);
                                if ((n9 & 0x1) == 0x1) {
                                    Class304.anInt2533 += Class197.anInt1520;
                                }
                                if ((n9 & 0x2) == 0x2) {
                                    Class98_Sub17.anInt3943 += Class93_Sub1.anInt5488;
                                }
                                if ((n9 & 0x4) == 0x4) {
                                    Class98_Sub10_Sub9.anInt5581 += Class98_Sub46_Sub4.anInt5961;
                                }
                            }
                            if (Class304.anInt2533 < -50) {
                                Class197.anInt1520 = 2;
                            }
                            if (Class304.anInt2533 > 50) {
                                Class197.anInt1520 = -2;
                            }
                            if (~Class98_Sub17.anInt3943 > 54) {
                                Class93_Sub1.anInt5488 = 2;
                            }
                            if (Class98_Sub10_Sub9.anInt5581 < -40) {
                                Class98_Sub46_Sub4.anInt5961 = 1;
                            }
                            if (Class98_Sub17.anInt3943 > 55) {
                                Class93_Sub1.anInt5488 = -2;
                            }
                            ++Class278_Sub1.anInt5170;
                            if (~Class98_Sub10_Sub9.anInt5581 < -41) {
                                Class98_Sub46_Sub4.anInt5961 = -1;
                            }
                            if (Class278_Sub1.anInt5170 > 500) {
                                Class278_Sub1.anInt5170 = 0;
                                final int n10 = (int)(8.0 * Math.random());
                                if (~(0x2 & n10) == 0xFFFFFFFD) {
                                    Class151.anInt1213 += Class76_Sub9.anInt3786;
                                }
                                if ((0x1 & n10) != 0x0) {
                                    Class204.anInt1553 += Class169.anInt1305;
                                }
                            }
                            if (~Class204.anInt1553 > 59) {
                                Class169.anInt1305 = 2;
                            }
                            if (Class151.anInt1213 < -20) {
                                Class76_Sub9.anInt3786 = 1;
                            }
                            if (Class204.anInt1553 > 60) {
                                Class169.anInt1305 = -2;
                            }
                            ++Class196.anInt1511;
                            if (Class151.anInt1213 > 10) {
                                Class76_Sub9.anInt3786 = -1;
                            }
                            if (Class196.anInt1511 > 50) {
                                ++Class76_Sub5.anInt3746;
                                Class98_Sub10_Sub29.sendPacket(false, Class246_Sub3_Sub4.method3023(260, Class98_Sub40.aClass171_4193, Class331.aClass117_2811));
                            }
                            if (Class76_Sub8.aBoolean3771) {
                                Class213.method2781(b ^ 0xFFFFFFC3);
                                Class76_Sub8.aBoolean3771 = false;
                            }
                            try {
                                Class95.method920((byte)115);
                            }
                            catch (IOException ex2) {
                                Canvas_Sub1.method118((byte)104);
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mg.D(" + b + ')');
        }
    }
    
    static final void method2630(final int n, final int n2, final int n3, final int n4) {
        try {
            if (n2 <= -102) {
                final Class28 class28 = Class76.aClass28ArrayArray586[n3][n];
                Class21_Sub3.method275(false, n4, (class28 == null) ? Class91.aClass28_722 : class28);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mg.C(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    static final void method2631(final int n, final int n2) {
        try {
            method2628(n2, -29, n).method1621(0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mg.E(" + n + ',' + n2 + ')');
        }
    }
    
    Class185(final long aLong1448, final int[] anIntArray1446, final short[] aShortArray1447, final short[] aShortArray1448) {
        try {
            this.aShortArray1444 = aShortArray1448;
            this.aLong1448 = aLong1448;
            this.anIntArray1446 = anIntArray1446;
            this.aShortArray1447 = aShortArray1447;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mg.<init>(" + aLong1448 + ',' + ((anIntArray1446 != null) ? "{...}" : "null") + ',' + ((aShortArray1447 != null) ? "{...}" : "null") + ',' + ((aShortArray1448 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method2632(final byte b) {
        try {
            Class185.aClass246_Sub4_Sub2_Sub1Array1445 = null;
            if (b > -6) {
                method2630(14, 22, 64, 106);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mg.A(" + b + ')');
        }
    }
    
    protected Class185() {
    }
}
