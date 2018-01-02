// 
// Decompiled by Procyon v0.5.30
// 

final class Class340
{
    static Class207 aClass207_2847;
    static Class332[] aClass332Array2848;
    static int anInt2849;
    static Class aClass2850;
    static Class aClass2851;
    
    static final void method3801(final Class246_Sub3_Sub4_Sub2 class246_Sub3_Sub4_Sub2, final int n) {
        try {
            if (n != -28111) {
                Class340.aClass332Array2848 = null;
            }
            Label_0289: {
                if (class246_Sub3_Sub4_Sub2.anInt6385 != -1) {
                    Class97 class97 = Class151_Sub7.aClass183_5001.method2623(class246_Sub3_Sub4_Sub2.anInt6385, 16383);
                    if (class97 != null && class97.anIntArray818 != null) {
                        ++class246_Sub3_Sub4_Sub2.anInt6409;
                        if (~class246_Sub3_Sub4_Sub2.anInt6350 > ~class97.anIntArray818.length && class246_Sub3_Sub4_Sub2.anInt6409 > class97.anIntArray811[class246_Sub3_Sub4_Sub2.anInt6350]) {
                            class246_Sub3_Sub4_Sub2.anInt6409 = 1;
                            ++class246_Sub3_Sub4_Sub2.anInt6350;
                            ++class246_Sub3_Sub4_Sub2.anInt6419;
                            if (!class246_Sub3_Sub4_Sub2.aBoolean6371) {
                                Class349.method3840((byte)(-128), class246_Sub3_Sub4_Sub2, class246_Sub3_Sub4_Sub2.anInt6350, class97);
                            }
                        }
                        if (~class97.anIntArray818.length >= ~class246_Sub3_Sub4_Sub2.anInt6350) {
                            class246_Sub3_Sub4_Sub2.anInt6350 = 0;
                            class246_Sub3_Sub4_Sub2.anInt6409 = 0;
                            if (class246_Sub3_Sub4_Sub2.aBoolean6359) {
                                class246_Sub3_Sub4_Sub2.anInt6385 = class246_Sub3_Sub4_Sub2.method3039(1).method3478(n + 28148);
                                if (~class246_Sub3_Sub4_Sub2.anInt6385 == 0x0) {
                                    class246_Sub3_Sub4_Sub2.aBoolean6359 = false;
                                    break Label_0289;
                                }
                                class97 = Class151_Sub7.aClass183_5001.method2623(class246_Sub3_Sub4_Sub2.anInt6385, n + 44494);
                            }
                            if (!class246_Sub3_Sub4_Sub2.aBoolean6371) {
                                Class349.method3840((byte)(-126), class246_Sub3_Sub4_Sub2, class246_Sub3_Sub4_Sub2.anInt6350, class97);
                            }
                        }
                        class246_Sub3_Sub4_Sub2.anInt6419 = class246_Sub3_Sub4_Sub2.anInt6350 + 1;
                        if (class97.anIntArray818 != null) {
                            if (class246_Sub3_Sub4_Sub2.anInt6419 >= class97.anIntArray818.length) {
                                class246_Sub3_Sub4_Sub2.anInt6419 = 0;
                            }
                        }
                        else {
                            class246_Sub3_Sub4_Sub2.aBoolean6359 = false;
                            class246_Sub3_Sub4_Sub2.anInt6385 = -1;
                        }
                    }
                    else {
                        class246_Sub3_Sub4_Sub2.aBoolean6359 = false;
                        class246_Sub3_Sub4_Sub2.anInt6385 = -1;
                    }
                }
            }
            Label_0822: {
                if (class246_Sub3_Sub4_Sub2.anInt6379 != -1 && class246_Sub3_Sub4_Sub2.anInt6391 <= Class215.anInt1614) {
                    final Class107 method3564 = Class196.aClass304_1509.method3564(2, class246_Sub3_Sub4_Sub2.anInt6379);
                    final int anInt910 = method3564.anInt910;
                    if (~anInt910 != 0x0) {
                        final Class97 method3565 = Class151_Sub7.aClass183_5001.method2623(anInt910, 16383);
                        if (method3564.aBoolean909) {
                            if (method3565.anInt821 == 3) {
                                if (class246_Sub3_Sub4_Sub2.anInt6436 > 0 && ~class246_Sub3_Sub4_Sub2.anInt6390 >= ~Class215.anInt1614 && Class215.anInt1614 > class246_Sub3_Sub4_Sub2.anInt6424) {
                                    class246_Sub3_Sub4_Sub2.anInt6379 = -1;
                                    break Label_0822;
                                }
                            }
                            else if (method3565.anInt821 == 1 && class246_Sub3_Sub4_Sub2.anInt6436 > 0 && ~class246_Sub3_Sub4_Sub2.anInt6390 >= ~Class215.anInt1614 && ~Class215.anInt1614 < ~class246_Sub3_Sub4_Sub2.anInt6424) {
                                class246_Sub3_Sub4_Sub2.anInt6391 = Class215.anInt1614 + 1;
                                break Label_0822;
                            }
                        }
                        if (method3565 != null && method3565.anIntArray818 != null) {
                            if (class246_Sub3_Sub4_Sub2.anInt6376 < 0) {
                                class246_Sub3_Sub4_Sub2.anInt6376 = 0;
                                if (!class246_Sub3_Sub4_Sub2.aBoolean6371) {
                                    Class349.method3840((byte)(-126), class246_Sub3_Sub4_Sub2, 0, method3565);
                                }
                            }
                            ++class246_Sub3_Sub4_Sub2.anInt6396;
                            if (method3565.anIntArray818.length > class246_Sub3_Sub4_Sub2.anInt6376 && ~class246_Sub3_Sub4_Sub2.anInt6396 < ~method3565.anIntArray811[class246_Sub3_Sub4_Sub2.anInt6376]) {
                                ++class246_Sub3_Sub4_Sub2.anInt6376;
                                class246_Sub3_Sub4_Sub2.anInt6396 = 1;
                                if (!class246_Sub3_Sub4_Sub2.aBoolean6371) {
                                    Class349.method3840((byte)(-127), class246_Sub3_Sub4_Sub2, class246_Sub3_Sub4_Sub2.anInt6376, method3565);
                                }
                            }
                            if (~method3565.anIntArray818.length >= ~class246_Sub3_Sub4_Sub2.anInt6376) {
                                if (!method3564.aBoolean909) {
                                    class246_Sub3_Sub4_Sub2.anInt6379 = -1;
                                }
                                else {
                                    ++class246_Sub3_Sub4_Sub2.anInt6356;
                                    class246_Sub3_Sub4_Sub2.anInt6376 -= method3565.anInt828;
                                    if (method3565.anInt807 > class246_Sub3_Sub4_Sub2.anInt6356) {
                                        if (class246_Sub3_Sub4_Sub2.anInt6376 < 0 || method3565.anIntArray818.length <= class246_Sub3_Sub4_Sub2.anInt6376) {
                                            class246_Sub3_Sub4_Sub2.anInt6379 = -1;
                                        }
                                        else if (!class246_Sub3_Sub4_Sub2.aBoolean6371) {
                                            Class349.method3840((byte)(-127), class246_Sub3_Sub4_Sub2, class246_Sub3_Sub4_Sub2.anInt6376, method3565);
                                        }
                                    }
                                    else {
                                        class246_Sub3_Sub4_Sub2.anInt6379 = -1;
                                    }
                                }
                            }
                            class246_Sub3_Sub4_Sub2.anInt6367 = class246_Sub3_Sub4_Sub2.anInt6376 + 1;
                            if (~class246_Sub3_Sub4_Sub2.anInt6367 <= ~method3565.anIntArray818.length) {
                                if (method3564.aBoolean909) {
                                    class246_Sub3_Sub4_Sub2.anInt6367 -= method3565.anInt828;
                                    if (~method3565.anInt807 < ~(class246_Sub3_Sub4_Sub2.anInt6356 + 1)) {
                                        if (class246_Sub3_Sub4_Sub2.anInt6367 < 0 || ~method3565.anIntArray818.length >= ~class246_Sub3_Sub4_Sub2.anInt6367) {
                                            class246_Sub3_Sub4_Sub2.anInt6367 = -1;
                                        }
                                    }
                                    else {
                                        class246_Sub3_Sub4_Sub2.anInt6367 = -1;
                                    }
                                }
                                else {
                                    class246_Sub3_Sub4_Sub2.anInt6367 = -1;
                                }
                            }
                        }
                        else {
                            class246_Sub3_Sub4_Sub2.anInt6379 = -1;
                        }
                    }
                    else {
                        class246_Sub3_Sub4_Sub2.anInt6379 = -1;
                    }
                }
            }
            Label_1359: {
                if (class246_Sub3_Sub4_Sub2.anInt6365 != -1 && Class215.anInt1614 >= class246_Sub3_Sub4_Sub2.anInt6426) {
                    final Class107 method3566 = Class196.aClass304_1509.method3564(2, class246_Sub3_Sub4_Sub2.anInt6365);
                    final int anInt911 = method3566.anInt910;
                    if (anInt911 != -1) {
                        final Class97 method3567 = Class151_Sub7.aClass183_5001.method2623(anInt911, 16383);
                        if (method3566.aBoolean909) {
                            if (~method3567.anInt821 == 0xFFFFFFFC) {
                                if (class246_Sub3_Sub4_Sub2.anInt6436 > 0 && Class215.anInt1614 >= class246_Sub3_Sub4_Sub2.anInt6390 && ~Class215.anInt1614 < ~class246_Sub3_Sub4_Sub2.anInt6424) {
                                    class246_Sub3_Sub4_Sub2.anInt6365 = -1;
                                    break Label_1359;
                                }
                            }
                            else if (method3567.anInt821 == 1 && ~class246_Sub3_Sub4_Sub2.anInt6436 < -1 && Class215.anInt1614 >= class246_Sub3_Sub4_Sub2.anInt6390 && ~Class215.anInt1614 < ~class246_Sub3_Sub4_Sub2.anInt6424) {
                                class246_Sub3_Sub4_Sub2.anInt6426 = Class215.anInt1614 + 1;
                                break Label_1359;
                            }
                        }
                        if (method3567 == null || method3567.anIntArray818 == null) {
                            class246_Sub3_Sub4_Sub2.anInt6365 = -1;
                        }
                        else {
                            if (class246_Sub3_Sub4_Sub2.anInt6428 < 0) {
                                class246_Sub3_Sub4_Sub2.anInt6428 = 0;
                                if (!class246_Sub3_Sub4_Sub2.aBoolean6371) {
                                    Class349.method3840((byte)(-127), class246_Sub3_Sub4_Sub2, 0, method3567);
                                }
                            }
                            ++class246_Sub3_Sub4_Sub2.anInt6427;
                            if (~method3567.anIntArray818.length < ~class246_Sub3_Sub4_Sub2.anInt6428 && method3567.anIntArray811[class246_Sub3_Sub4_Sub2.anInt6428] < class246_Sub3_Sub4_Sub2.anInt6427) {
                                ++class246_Sub3_Sub4_Sub2.anInt6428;
                                class246_Sub3_Sub4_Sub2.anInt6427 = 1;
                                if (!class246_Sub3_Sub4_Sub2.aBoolean6371) {
                                    Class349.method3840((byte)(-126), class246_Sub3_Sub4_Sub2, class246_Sub3_Sub4_Sub2.anInt6428, method3567);
                                }
                            }
                            if (~class246_Sub3_Sub4_Sub2.anInt6428 <= ~method3567.anIntArray818.length) {
                                if (method3566.aBoolean909) {
                                    ++class246_Sub3_Sub4_Sub2.anInt6380;
                                    class246_Sub3_Sub4_Sub2.anInt6428 -= method3567.anInt828;
                                    if (~method3567.anInt807 >= ~class246_Sub3_Sub4_Sub2.anInt6380) {
                                        class246_Sub3_Sub4_Sub2.anInt6365 = -1;
                                    }
                                    else if (~class246_Sub3_Sub4_Sub2.anInt6428 > -1 || method3567.anIntArray818.length <= class246_Sub3_Sub4_Sub2.anInt6428) {
                                        class246_Sub3_Sub4_Sub2.anInt6365 = -1;
                                    }
                                    else if (!class246_Sub3_Sub4_Sub2.aBoolean6371) {
                                        Class349.method3840((byte)(-128), class246_Sub3_Sub4_Sub2, class246_Sub3_Sub4_Sub2.anInt6428, method3567);
                                    }
                                }
                                else {
                                    class246_Sub3_Sub4_Sub2.anInt6365 = -1;
                                }
                            }
                            class246_Sub3_Sub4_Sub2.anInt6421 = class246_Sub3_Sub4_Sub2.anInt6428 + 1;
                            if (class246_Sub3_Sub4_Sub2.anInt6421 >= method3567.anIntArray818.length) {
                                if (method3566.aBoolean909) {
                                    class246_Sub3_Sub4_Sub2.anInt6421 -= method3567.anInt828;
                                    if (~method3567.anInt807 >= ~(1 + class246_Sub3_Sub4_Sub2.anInt6380)) {
                                        class246_Sub3_Sub4_Sub2.anInt6421 = -1;
                                    }
                                    else if (class246_Sub3_Sub4_Sub2.anInt6421 < 0 || ~method3567.anIntArray818.length >= ~class246_Sub3_Sub4_Sub2.anInt6421) {
                                        class246_Sub3_Sub4_Sub2.anInt6421 = -1;
                                    }
                                }
                                else {
                                    class246_Sub3_Sub4_Sub2.anInt6421 = -1;
                                }
                            }
                        }
                    }
                    else {
                        class246_Sub3_Sub4_Sub2.anInt6365 = -1;
                    }
                }
            }
            if (~class246_Sub3_Sub4_Sub2.anInt6413 != 0x0 && class246_Sub3_Sub4_Sub2.anInt6400 <= 1) {
                final Class97 method3568 = Class151_Sub7.aClass183_5001.method2623(class246_Sub3_Sub4_Sub2.anInt6413, 16383);
                if (method3568.anInt821 == 3) {
                    if (class246_Sub3_Sub4_Sub2.anInt6436 > 0 && Class215.anInt1614 >= class246_Sub3_Sub4_Sub2.anInt6390 && class246_Sub3_Sub4_Sub2.anInt6424 < Class215.anInt1614) {
                        class246_Sub3_Sub4_Sub2.anIntArray6373 = null;
                        class246_Sub3_Sub4_Sub2.anInt6413 = -1;
                    }
                }
                else if (method3568.anInt821 == 1 && class246_Sub3_Sub4_Sub2.anInt6436 > 0 && ~class246_Sub3_Sub4_Sub2.anInt6390 >= ~Class215.anInt1614 && ~class246_Sub3_Sub4_Sub2.anInt6424 > ~Class215.anInt1614) {
                    class246_Sub3_Sub4_Sub2.anInt6400 = 2;
                }
            }
            if (class246_Sub3_Sub4_Sub2.anInt6413 != -1 && ~class246_Sub3_Sub4_Sub2.anInt6400 == -1) {
                final Class97 method3569 = Class151_Sub7.aClass183_5001.method2623(class246_Sub3_Sub4_Sub2.anInt6413, 16383);
                if (method3569 != null && method3569.anIntArray818 != null) {
                    ++class246_Sub3_Sub4_Sub2.anInt6366;
                    if (~method3569.anIntArray818.length < ~class246_Sub3_Sub4_Sub2.anInt6393 && method3569.anIntArray811[class246_Sub3_Sub4_Sub2.anInt6393] < class246_Sub3_Sub4_Sub2.anInt6366) {
                        class246_Sub3_Sub4_Sub2.anInt6366 = 1;
                        ++class246_Sub3_Sub4_Sub2.anInt6393;
                        if (!class246_Sub3_Sub4_Sub2.aBoolean6371) {
                            Class349.method3840((byte)(-126), class246_Sub3_Sub4_Sub2, class246_Sub3_Sub4_Sub2.anInt6393, method3569);
                        }
                    }
                    if (class246_Sub3_Sub4_Sub2.anInt6393 >= method3569.anIntArray818.length) {
                        ++class246_Sub3_Sub4_Sub2.anInt6405;
                        class246_Sub3_Sub4_Sub2.anInt6393 -= method3569.anInt828;
                        if (~method3569.anInt807 < ~class246_Sub3_Sub4_Sub2.anInt6405) {
                            if (~class246_Sub3_Sub4_Sub2.anInt6393 > -1 || ~method3569.anIntArray818.length >= ~class246_Sub3_Sub4_Sub2.anInt6393) {
                                class246_Sub3_Sub4_Sub2.anInt6413 = -1;
                                class246_Sub3_Sub4_Sub2.anIntArray6373 = null;
                            }
                            else if (!class246_Sub3_Sub4_Sub2.aBoolean6371) {
                                Class349.method3840((byte)(-128), class246_Sub3_Sub4_Sub2, class246_Sub3_Sub4_Sub2.anInt6393, method3569);
                            }
                        }
                        else {
                            class246_Sub3_Sub4_Sub2.anIntArray6373 = null;
                            class246_Sub3_Sub4_Sub2.anInt6413 = -1;
                        }
                    }
                    class246_Sub3_Sub4_Sub2.anInt6361 = 1 + class246_Sub3_Sub4_Sub2.anInt6393;
                    if (class246_Sub3_Sub4_Sub2.anInt6361 >= method3569.anIntArray818.length) {
                        class246_Sub3_Sub4_Sub2.anInt6361 -= method3569.anInt828;
                        if (~method3569.anInt807 < ~(1 + class246_Sub3_Sub4_Sub2.anInt6405)) {
                            if (~class246_Sub3_Sub4_Sub2.anInt6361 > -1 || ~class246_Sub3_Sub4_Sub2.anInt6361 <= ~method3569.anIntArray818.length) {
                                class246_Sub3_Sub4_Sub2.anInt6361 = -1;
                            }
                        }
                        else {
                            class246_Sub3_Sub4_Sub2.anInt6361 = -1;
                        }
                    }
                }
                else {
                    class246_Sub3_Sub4_Sub2.anIntArray6373 = null;
                    class246_Sub3_Sub4_Sub2.anInt6413 = -1;
                }
            }
            if (~class246_Sub3_Sub4_Sub2.anInt6400 < -1) {
                --class246_Sub3_Sub4_Sub2.anInt6400;
            }
            for (int i = 0; i < class246_Sub3_Sub4_Sub2.aClass226Array6387.length; ++i) {
                final Class226 class98 = class246_Sub3_Sub4_Sub2.aClass226Array6387[i];
                if (class98 != null) {
                    if (class98.anInt1703 > 0) {
                        final Class226 class99 = class98;
                        --class99.anInt1703;
                    }
                    else {
                        final Class97 method3570 = Class151_Sub7.aClass183_5001.method2623(class98.anInt1700, n + 44494);
                        if (method3570 != null && method3570.anIntArray818 != null) {
                            final Class226 class100 = class98;
                            ++class100.anInt1707;
                            if (class98.anInt1702 < method3570.anIntArray818.length && method3570.anIntArray811[class98.anInt1702] < class98.anInt1707) {
                                class98.anInt1707 = 1;
                                final Class226 class101 = class98;
                                ++class101.anInt1702;
                                if (!class246_Sub3_Sub4_Sub2.aBoolean6371) {
                                    Class349.method3840((byte)(-128), class246_Sub3_Sub4_Sub2, class98.anInt1702, method3570);
                                }
                            }
                            if (method3570.anIntArray818.length <= class98.anInt1702) {
                                final Class226 class102 = class98;
                                ++class102.anInt1704;
                                final Class226 class103 = class98;
                                class103.anInt1702 -= method3570.anInt828;
                                if (method3570.anInt807 <= class98.anInt1704) {
                                    class246_Sub3_Sub4_Sub2.aClass226Array6387[i] = null;
                                }
                                else if (class98.anInt1702 >= 0 && method3570.anIntArray818.length > class98.anInt1702) {
                                    if (!class246_Sub3_Sub4_Sub2.aBoolean6371) {
                                        Class349.method3840((byte)(-127), class246_Sub3_Sub4_Sub2, class98.anInt1702, method3570);
                                    }
                                }
                                else {
                                    class246_Sub3_Sub4_Sub2.aClass226Array6387[i] = null;
                                }
                            }
                            class98.anInt1701 = class98.anInt1702 + 1;
                            if (~method3570.anIntArray818.length >= ~class98.anInt1701) {
                                final Class226 class104 = class98;
                                class104.anInt1701 -= method3570.anInt828;
                                if (~(1 + class98.anInt1704) <= ~method3570.anInt807) {
                                    class98.anInt1701 = -1;
                                }
                                else if (~class98.anInt1701 > -1 || method3570.anIntArray818.length <= class98.anInt1701) {
                                    class98.anInt1701 = -1;
                                }
                            }
                        }
                        else {
                            class246_Sub3_Sub4_Sub2.aClass226Array6387[i] = null;
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uo.D(" + ((class246_Sub3_Sub4_Sub2 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final void method3802(final ha ha, final int n, final byte b) {
        try {
            if (b != -59) {
                Class340.aClass332Array2848 = null;
            }
            if (!Class98_Sub17.aBoolean3942 || !Class135.aBoolean1052) {
                Class59.anInt464 = 0;
            }
            else {
                if (Class93_Sub1.aBoolean5479) {
                    Class98_Sub42.aLong4212 = Class376.aClass142_3174.method2308((byte)69);
                }
                Class151_Sub7.anInt5006 = 0;
                Class302.anInt2518 = 0;
                Class98_Sub16.anInt3927 = 0;
                final int[] y = ha.Y();
                Class38.anInt358 = (int)(y[2] / 3.0f);
                Class331.anInt2800 = (int)(y[3] / 3.0f);
                ha.method1801(Class98_Sub46_Sub4.anIntArray5960);
                if (Class64_Sub3.anInt3646 != (int)(Class98_Sub46_Sub4.anIntArray5960[0] / 3.0f) || ~IncomingOpcode.anInt461 != ~(int)(Class98_Sub46_Sub4.anIntArray5960[1] / 3.0f)) {
                    IncomingOpcode.anInt461 = (int)(Class98_Sub46_Sub4.anIntArray5960[1] / 3.0f);
                    Class64_Sub3.anInt3646 = (int)(Class98_Sub46_Sub4.anIntArray5960[0] / 3.0f);
                    Class111_Sub3.anIntArray4707 = new int[IncomingOpcode.anInt461 * Class64_Sub3.anInt3646];
                    Class2.anInt69 = Class64_Sub3.anInt3646 >> 427328737;
                    Class98_Sub10_Sub23.anInt5659 = IncomingOpcode.anInt461 >> 942536705;
                }
                RuntimeException_Sub1.aClass111_3203 = ha.method1752();
                Class59.anInt464 = 0;
                for (int n2 = 0; Class21_Sub3.anInt5389 > n2; ++n2) {
                    Class271.method3278(3, n, ha, Class258.aClass155Array1951[n2]);
                }
                for (int i = 0; i < Class336.anInt2820; ++i) {
                    Class271.method3278(3, n, ha, Class98_Sub30.aClass155Array4099[i]);
                }
                for (int n3 = 0; ~ha.anInt936 < ~n3; ++n3) {
                    Class271.method3278(3, n, ha, Class98_Sub32_Sub1.aClass155Array5889[n3]);
                }
                Class4.anInt81 = 0;
                if (Class59.anInt464 > 0) {
                    final int j = Class111_Sub3.anIntArray4707.length;
                    int n4;
                    int n5;
                    for (n4 = (-j + j & 0x7), n5 = 0; ~n5 > ~n4; Class111_Sub3.anIntArray4707[n5++] = Integer.MAX_VALUE, Class111_Sub3.anIntArray4707[n5++] = Integer.MAX_VALUE, Class111_Sub3.anIntArray4707[n5++] = Integer.MAX_VALUE, Class111_Sub3.anIntArray4707[n5++] = Integer.MAX_VALUE, Class111_Sub3.anIntArray4707[n5++] = Integer.MAX_VALUE, Class111_Sub3.anIntArray4707[n5++] = Integer.MAX_VALUE, Class111_Sub3.anIntArray4707[n5++] = Integer.MAX_VALUE, Class111_Sub3.anIntArray4707[n5++] = Integer.MAX_VALUE) {}
                    while (j > n5) {
                        Class111_Sub3.anIntArray4707[n5++] = Integer.MAX_VALUE;
                    }
                    Class287.anInt2190 = 1;
                    for (int n6 = 0; ~Class59.anInt464 < ~n6; ++n6) {
                        final Class155 class155 = Class213.aClass155Array1611[n6];
                        Class267.method3243(class155.aShortArray1244[1], class155.aShortArray1244[0], (byte)85, class155.aShortArray1234[1], class155.aShortArray1235[3], class155.aShortArray1235[0], class155.aShortArray1244[3], class155.aShortArray1235[1], class155.aShortArray1234[0], class155.aShortArray1234[3]);
                        Class267.method3243(class155.aShortArray1244[2], class155.aShortArray1244[1], (byte)81, class155.aShortArray1234[2], class155.aShortArray1235[3], class155.aShortArray1235[1], class155.aShortArray1244[3], class155.aShortArray1235[2], class155.aShortArray1234[1], class155.aShortArray1234[3]);
                    }
                    Class287.anInt2190 = 2;
                }
                if (Class93_Sub1.aBoolean5479) {
                    Class249.aLong1898 = Class376.aClass142_3174.method2308((byte)69) - Class98_Sub42.aLong4212;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uo.B(" + ((ha != null) ? "{...}" : "null") + ',' + n + ',' + b + ')');
        }
    }
    
    static final synchronized void method3803(final boolean b) {
        try {
            if (!b && Class98_Sub10_Sub22.anObject5653 == null) {
                try {
                    final Class<?> forName = Class.forName("java.lang.management.ManagementFactory");
                    Class98_Sub10_Sub22.anObject5653 = forName.getMethod("newPlatformMXBeanProxy", Class.forName("javax.management.MBeanServerConnection"), (Class340.aClass2850 != null) ? Class340.aClass2850 : (Class340.aClass2850 = method3805("java.lang.String")), (Class340.aClass2851 != null) ? Class340.aClass2851 : (Class340.aClass2851 = method3805("java.lang.Class"))).invoke(null, forName.getDeclaredMethod("getPlatformMBeanServer", (Class<?>[])null).invoke(null, (Object[])null), "com.sun.management:type=HotSpotDiagnostic", Class.forName("com.sun.management.HotSpotDiagnosticMXBean"));
                }
                catch (Exception ex) {
                    System.out.println("HeapDump setup error:");
                    ex.printStackTrace();
                }
            }
        }
        catch (RuntimeException ex2) {
            throw Class64_Sub27.method667(ex2, "uo.C(" + b + ')');
        }
    }
    
    public static void method3804(final byte b) {
        try {
            Class340.aClass332Array2848 = null;
            if (b == -71) {
                Class340.aClass207_2847 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uo.A(" + b + ')');
        }
    }
    
    static Class method3805(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Class340.anInt2849 = 0;
    }
}
