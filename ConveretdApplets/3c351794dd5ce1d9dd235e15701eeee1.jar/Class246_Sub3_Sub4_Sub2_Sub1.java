// 
// Decompiled by Procyon v0.5.30
// 

final class Class246_Sub3_Sub4_Sub2_Sub1 extends Class246_Sub3_Sub4_Sub2
{
    static int anInt6500;
    int anInt6501;
    Class40 aClass40_6502;
    int anInt6503;
    Class141 aClass141_6504;
    int anInt6505;
    static OutgoingOpcode aClass171_6506;
    static String aString6507;
    static Class332 aClass332_6508;
    static int anInt6509;
    int anInt6510;
    static int anInt6511;
    
    @Override
    final void method2988(final ha ha, final int n) {
        try {
            if (this.aClass141_6504 != null && (super.aBoolean6440 || this.method3048(ha, 255, 0))) {
                final Class111 method1793 = ha.method1793();
                method1793.method2101(super.aClass325_6399.method3698((byte)116));
                method1793.method2106(super.anInt5084, super.anInt5089 - 20, super.anInt5079);
                this.method3036(ha, super.aBoolean6440, (byte)(-119), method1793, super.aClass146Array6441);
                final Class146[] aClass146Array6441 = super.aClass146Array6441;
                final int n2 = 0;
                final Class146[] aClass146Array6442 = super.aClass146Array6441;
                final int n3 = 1;
                final Class146[] aClass146Array6443 = super.aClass146Array6441;
                final int n4 = 2;
                final Class146 class146 = null;
                aClass146Array6443[n4] = class146;
                aClass146Array6441[n2] = (aClass146Array6442[n3] = class146);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cea.MA(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    private final boolean method3048(final ha ha, final int n, int n2) {
        try {
            final int n3 = n2;
            final Class294 method3039 = this.method3039(1);
            if (n != 255) {
                Class246_Sub3_Sub4_Sub2_Sub1.aString6507 = null;
            }
            final Class97 class97 = (super.anInt6413 != -1 && ~super.anInt6400 == -1) ? Class151_Sub7.aClass183_5001.method2623(super.anInt6413, n ^ 0x3F00) : null;
            final Class97 class98 = (~super.anInt6385 == 0x0 || (super.aBoolean6359 && class97 != null)) ? null : Class151_Sub7.aClass183_5001.method2623(super.anInt6385, 16383);
            final int anInt2362 = method3039.anInt2362;
            final int anInt2363 = method3039.anInt2382;
            if (~anInt2362 != -1 || ~anInt2363 != -1 || ~method3039.anInt2393 != -1 || method3039.anInt2363 != 0) {
                n2 |= 0x7;
            }
            final boolean b = ~super.aByte6422 != -1 && Class215.anInt1614 >= super.anInt6403 && ~Class215.anInt1614 > ~super.anInt6349;
            if (b) {
                n2 |= 0x80000;
            }
            final int method3040 = super.aClass325_6399.method3698((byte)116);
            final Class146[] aClass146Array6441 = super.aClass146Array6441;
            final int n4 = 0;
            final Class146 method3041 = this.aClass141_6504.method2301(super.anInt6393, method3040, super.anIntArray6370, super.anInt6350, (byte)100, class97, super.aClass226Array6387, ha, super.anInt6409, super.anInt6361, Class75.aClass140_584, this.aClass40_6502, super.anInt6366, Class370.aClass257_3136, n2, super.anInt6419, Class151_Sub7.aClass183_5001, class98);
            aClass146Array6441[n4] = method3041;
            final Class146 class99 = method3041;
            if (class99 == null) {
                return false;
            }
            super.anInt6352 = class99.fa();
            super.anInt6354 = class99.ma();
            this.method3046(758, class99);
            if (~anInt2362 != -1 || anInt2363 != 0) {
                this.method3040(false, method3039.anInt2360, anInt2362, anInt2363, method3040, method3039.anInt2391);
                if (~super.anInt6388 != -1) {
                    super.aClass146Array6441[0].FA(super.anInt6388);
                }
                if (super.anInt6377 != 0) {
                    super.aClass146Array6441[0].VA(super.anInt6377);
                }
                if (super.anInt6416 != 0) {
                    super.aClass146Array6441[0].H(0, super.anInt6416, 0);
                }
            }
            else {
                this.method3040(false, 0, this.method3034(n - 255) << 188560585, this.method3034(0) << -1964945015, method3040, 0);
            }
            if (b) {
                class99.method2337(super.aByte6404, super.aByte6381, super.aByte6368, super.aByte6422 & 0xFF);
            }
            if (super.anInt6379 == -1 || ~super.anInt6376 == 0x0) {
                super.aClass146Array6441[1] = null;
            }
            else {
                final Class107 method3042 = Class196.aClass304_1509.method3564(n - 253, super.anInt6379);
                final boolean b2 = method3042.aByte923 == 3 && (anInt2362 != 0 || anInt2363 != 0);
                int n5 = n3;
                if (!b2) {
                    if (super.anInt6389 != 0) {
                        n5 |= 0x5;
                    }
                    if (~super.anInt6382 != -1) {
                        n5 |= 0x2;
                    }
                    if (super.anInt6410 >= 0) {
                        n5 |= 0x7;
                    }
                }
                else {
                    n5 |= 0x7;
                }
                final Class146[] aClass146Array6442 = super.aClass146Array6441;
                final int n6 = 1;
                final Class146 method3043 = method3042.method1728(super.anInt6376, Class151_Sub7.aClass183_5001, n5, super.anInt6396, (byte)66, super.anInt6367, ha);
                aClass146Array6442[n6] = method3043;
                final Class146 class100 = method3043;
                if (class100 != null) {
                    if (~super.anInt6410 > -1) {
                        if (super.anInt6389 != 0) {
                            class100.a(2048 * super.anInt6389);
                        }
                    }
                    else {
                        int n7 = 0;
                        int n8 = 0;
                        int n9 = 0;
                        if (method3039.anIntArrayArray2366 != null && method3039.anIntArrayArray2366[super.anInt6410] != null) {
                            n8 += method3039.anIntArrayArray2366[super.anInt6410][1];
                            n9 += method3039.anIntArrayArray2366[super.anInt6410][2];
                            n7 += method3039.anIntArrayArray2366[super.anInt6410][0];
                        }
                        if (method3039.anIntArrayArray2364 != null && method3039.anIntArrayArray2364[super.anInt6410] != null) {
                            n7 += method3039.anIntArrayArray2364[super.anInt6410][0];
                            n8 += method3039.anIntArrayArray2364[super.anInt6410][1];
                            n9 += method3039.anIntArrayArray2364[super.anInt6410][2];
                        }
                        if (~n9 != -1 || n7 != 0) {
                            int n10 = method3040;
                            if (super.anIntArray6370 != null && ~super.anIntArray6370[super.anInt6410] != 0x0) {
                                n10 = super.anIntArray6370[super.anInt6410];
                            }
                            final int n11 = n10 + (2048 * super.anInt6389 - method3040) & 0x3FFF;
                            if (n11 != 0) {
                                class100.a(n11);
                            }
                            final int n12 = Class284_Sub2_Sub2.anIntArray6200[n11];
                            final int n13 = Class284_Sub2_Sub2.anIntArray6202[n11];
                            final int n14 = n7 * n13 + n12 * n9 >> -874650578;
                            n9 = -(n7 * n12) + n9 * n13 >> 562276558;
                            n7 = n14;
                        }
                        class100.H(n7, n8, n9);
                    }
                    if (super.anInt6382 != 0) {
                        class100.H(0, -super.anInt6382 << -1220664798, 0);
                    }
                    if (b2) {
                        if (~super.anInt6388 != -1) {
                            class100.FA(super.anInt6388);
                        }
                        if (~super.anInt6377 != -1) {
                            class100.VA(super.anInt6377);
                        }
                        if (~super.anInt6416 != -1) {
                            class100.H(0, super.anInt6416, 0);
                        }
                    }
                }
            }
            if (~super.anInt6365 == 0x0 || super.anInt6428 == -1) {
                super.aClass146Array6441[2] = null;
            }
            else {
                final Class107 method3044 = Class196.aClass304_1509.method3564(2, super.anInt6365);
                final boolean b3 = ~method3044.aByte923 == 0xFFFFFFFC && (anInt2362 != 0 || anInt2363 != 0);
                int n15 = n3;
                if (!b3) {
                    if (super.anInt6360 != 0) {
                        n15 |= 0x5;
                    }
                    if (~super.anInt6363 != -1) {
                        n15 |= 0x2;
                    }
                    if (~super.anInt6353 <= -1) {
                        n15 |= 0x7;
                    }
                }
                else {
                    n15 |= 0x7;
                }
                final Class146[] aClass146Array6443 = super.aClass146Array6441;
                final int n16 = 2;
                final Class146 method3045 = method3044.method1721(ha, super.anInt6421, 21945, n15, Class151_Sub7.aClass183_5001, super.anInt6427, super.anInt6428);
                aClass146Array6443[n16] = method3045;
                final Class146 class101 = method3045;
                if (class101 != null) {
                    if (~super.anInt6353 > -1 || method3039.anIntArrayArray2366 == null || method3039.anIntArrayArray2366[super.anInt6353] == null) {
                        if (super.anInt6360 != 0) {
                            class101.a(2048 * super.anInt6360);
                        }
                    }
                    else {
                        int n17 = 0;
                        int n18 = 0;
                        int n19 = 0;
                        if (method3039.anIntArrayArray2366 != null && method3039.anIntArrayArray2366[super.anInt6353] != null) {
                            n17 += method3039.anIntArrayArray2366[super.anInt6353][0];
                            n18 += method3039.anIntArrayArray2366[super.anInt6353][1];
                            n19 += method3039.anIntArrayArray2366[super.anInt6353][2];
                        }
                        if (method3039.anIntArrayArray2364 != null && method3039.anIntArrayArray2364[super.anInt6353] != null) {
                            n18 += method3039.anIntArrayArray2364[super.anInt6353][1];
                            n17 += method3039.anIntArrayArray2364[super.anInt6353][0];
                            n19 += method3039.anIntArrayArray2364[super.anInt6353][2];
                        }
                        if (n19 != 0 || n17 != 0) {
                            int n20 = method3040;
                            if (super.anIntArray6370 != null && super.anIntArray6370[super.anInt6353] != -1) {
                                n20 = super.anIntArray6370[super.anInt6353];
                            }
                            final int n21 = 0x3FFF & n20 + super.anInt6360 * 2048 - method3040;
                            if (~n21 != -1) {
                                class101.a(n21);
                            }
                            final int n22 = Class284_Sub2_Sub2.anIntArray6200[n21];
                            final int n23 = Class284_Sub2_Sub2.anIntArray6202[n21];
                            final int n24 = n19 * n22 + n17 * n23 >> 349754830;
                            n19 = n23 * n19 - n17 * n22 >> 1020454830;
                            n17 = n24;
                        }
                        class101.H(n17, n18, n19);
                    }
                    if (super.anInt6363 != 0) {
                        class101.H(0, -super.anInt6363 << 1062444738, 0);
                    }
                    if (b3) {
                        if (super.anInt6388 != 0) {
                            class101.FA(super.anInt6388);
                        }
                        if (super.anInt6377 != 0) {
                            class101.VA(super.anInt6377);
                        }
                        if (super.anInt6416 != 0) {
                            class101.H(0, super.anInt6416, 0);
                        }
                    }
                }
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cea.CB(" + ((ha != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    final void method3049(final int n, final int n2, final boolean b, final byte b2, final int n3, final int n4) {
        try {
            final byte b3 = (byte)n4;
            super.aByte5081 = b3;
            super.aByte5088 = b3;
            if (Class1.method162(n3, (byte)(-99), n2)) {
                ++super.aByte5081;
            }
            if (~super.anInt6413 != 0x0 && ~Class151_Sub7.aClass183_5001.method2623(super.anInt6413, 16383).anInt816 == 0xFFFFFFFE) {
                super.anIntArray6373 = null;
                super.anInt6413 = -1;
            }
            if (super.anInt6379 != -1) {
                final Class107 method3564 = Class196.aClass304_1509.method3564(2, super.anInt6379);
                if (method3564.aBoolean909 && ~method3564.anInt910 != 0x0 && ~Class151_Sub7.aClass183_5001.method2623(method3564.anInt910, 16383).anInt816 == 0xFFFFFFFE) {
                    super.anInt6379 = -1;
                }
            }
            if (~super.anInt6365 != 0x0) {
                final Class107 method3565 = Class196.aClass304_1509.method3564(2, super.anInt6365);
                if (method3565.aBoolean909 && ~method3565.anInt910 != 0x0 && Class151_Sub7.aClass183_5001.method2623(method3565.anInt910, 16383).anInt816 == 1) {
                    super.anInt6365 = -1;
                }
            }
            if (!b) {
                final int n5 = -super.anIntArray6437[0] + n2;
                final int n6 = n3 + -super.anIntArray6438[0];
                if (~n5 <= 7 && ~n5 >= -9 && ~n6 <= 7 && n6 <= 8) {
                    if (~super.anInt6434 > -10) {
                        ++super.anInt6434;
                    }
                    for (int anInt6434 = super.anInt6434; ~anInt6434 < -1; --anInt6434) {
                        super.anIntArray6437[anInt6434] = super.anIntArray6437[-1 + anInt6434];
                        super.anIntArray6438[anInt6434] = super.anIntArray6438[anInt6434 - 1];
                        super.aByteArray6443[anInt6434] = super.aByteArray6443[-1 + anInt6434];
                    }
                    super.anIntArray6437[0] = n2;
                    super.aByteArray6443[0] = 1;
                    super.anIntArray6438[0] = n3;
                    return;
                }
            }
            super.anInt6434 = 0;
            super.anIntArray6437[0] = n2;
            super.anInt6436 = 0;
            super.anInt6433 = 0;
            super.anIntArray6438[0] = n3;
            super.anInt5079 = (n << -869632920) + (super.anIntArray6438[0] << 1403178185);
            super.anInt5084 = (super.anIntArray6437[0] << 348528265) + (n << 1193276360);
            if (b2 >= -105) {
                this.anInt6501 = -54;
            }
            if (super.aClass246_Sub5_6439 != null) {
                super.aClass246_Sub5_6439.method3127();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cea.GB(" + n + ',' + n2 + ',' + b + ',' + b2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    final void method3050(final int n, final int n2, final int n3) {
        try {
            int n4 = super.anIntArray6437[0];
            int n5 = super.anIntArray6438[0];
            if (n3 == 0) {
                ++n5;
            }
            if (n3 == 1) {
                ++n4;
                ++n5;
            }
            if (n3 == 2) {
                ++n4;
            }
            if (n3 == 3) {
                ++n4;
                --n5;
            }
            if (n3 == 4) {
                --n5;
            }
            if (n3 == 5) {
                --n4;
                --n5;
            }
            if (n3 == 6) {
                --n4;
            }
            if (n3 == 7) {
                ++n5;
                --n4;
            }
            if (super.anInt6413 != -1 && Class151_Sub7.aClass183_5001.method2623(super.anInt6413, 16383).anInt816 == 1) {
                super.anInt6413 = -1;
                super.anIntArray6373 = null;
            }
            if (~super.anInt6379 != n) {
                final Class107 method3564 = Class196.aClass304_1509.method3564(n + 2, super.anInt6379);
                if (method3564.aBoolean909 && method3564.anInt910 != -1 && Class151_Sub7.aClass183_5001.method2623(method3564.anInt910, 16383).anInt816 == 1) {
                    super.anInt6379 = -1;
                }
            }
            if (super.anInt6365 != -1) {
                final Class107 method3565 = Class196.aClass304_1509.method3564(n + 2, super.anInt6365);
                if (method3565.aBoolean909 && ~method3565.anInt910 != 0x0 && Class151_Sub7.aClass183_5001.method2623(method3565.anInt910, 16383).anInt816 == 1) {
                    super.anInt6365 = -1;
                }
            }
            if (super.anInt6434 < 9) {
                ++super.anInt6434;
            }
            for (int anInt6434 = super.anInt6434; ~anInt6434 < -1; --anInt6434) {
                super.anIntArray6437[anInt6434] = super.anIntArray6437[-1 + anInt6434];
                super.anIntArray6438[anInt6434] = super.anIntArray6438[-1 + anInt6434];
                super.aByteArray6443[anInt6434] = super.aByteArray6443[-1 + anInt6434];
            }
            super.anIntArray6437[0] = n4;
            super.anIntArray6438[0] = n5;
            super.aByteArray6443[0] = (byte)n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cea.EB(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final int method3044(final boolean b) {
        try {
            if (b) {
                this.aClass141_6504 = null;
            }
            if (this.aClass141_6504.anIntArray1109 != null) {
                final Class141 method2300 = this.aClass141_6504.method2300(Class75.aClass140_584, (byte)70);
                if (method2300 != null && ~method2300.anInt1092 != 0x0) {
                    return method2300.anInt1092;
                }
            }
            if (this.aClass141_6504.anInt1092 == -1) {
                return super.method3044(false);
            }
            return this.aClass141_6504.anInt1092;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cea.B(" + b + ')');
        }
    }
    
    private final boolean method3051(final int n) {
        try {
            if (n != 2) {
                this.anInt6503 = -16;
            }
            return this.aClass141_6504.aBoolean1116;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cea.DB(" + n + ')');
        }
    }
    
    @Override
    final void method2981(final Class246_Sub3 class246_Sub3, final byte b, final boolean b2, final int n, final ha ha, final int n2, final int n3) {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cea.CA(" + ((class246_Sub3 != null) ? "{...}" : "null") + ',' + b + ',' + b2 + ',' + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final boolean method2976(final int n, final ha ha, final byte b, final int n2) {
        try {
            if (this.aClass141_6504 == null || !this.method3048(ha, 255, 131072)) {
                return false;
            }
            final Class111 method1793 = ha.method1793();
            method1793.method2101(super.aClass325_6399.method3698((byte)116));
            if (b <= 59) {
                this.method2975(null, -17);
            }
            method1793.method2106(super.anInt5084, super.anInt5089, super.anInt5079);
            boolean b2 = false;
            for (int i = 0; i < super.aClass146Array6441.length; ++i) {
                if (super.aClass146Array6441[i] != null) {
                    final boolean b3 = ~this.aClass141_6504.anInt1123 < -1 || ((~this.aClass141_6504.anInt1096 != 0x0) ? (~this.aClass141_6504.anInt1096 == 0xFFFFFFFE) : (this.aClass141_6504.anInt1112 == 1));
                    boolean b4;
                    if (!Class239.aBoolean1839) {
                        b4 = super.aClass146Array6441[i].method2339(n, n2, method1793, b3, this.aClass141_6504.anInt1123);
                    }
                    else {
                        b4 = super.aClass146Array6441[i].method2333(n, n2, method1793, b3, this.aClass141_6504.anInt1123, Class16.anInt197);
                    }
                    if (b4) {
                        b2 = true;
                        break;
                    }
                }
            }
            final Class146[] aClass146Array6441 = super.aClass146Array6441;
            final int n3 = 0;
            final Class146[] aClass146Array6442 = super.aClass146Array6441;
            final int n4 = 1;
            final Class146[] aClass146Array6443 = super.aClass146Array6441;
            final int n5 = 2;
            final Class146 class146 = null;
            aClass146Array6443[n5] = class146;
            aClass146Array6441[n3] = (aClass146Array6442[n4] = class146);
            return b2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cea.TA(" + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + b + ',' + n2 + ')');
        }
    }
    
    @Override
    final int method3035(final int n) {
        try {
            if (this.aClass141_6504.anIntArray1109 != null) {
                final Class141 method2300 = this.aClass141_6504.method2300(Class75.aClass140_584, (byte)82);
                if (method2300 != null && ~method2300.anInt1095 != 0x0) {
                    return method2300.anInt1095;
                }
            }
            if (n != 28213) {
                Class246_Sub3_Sub4_Sub2_Sub1.aClass171_6506 = null;
            }
            return this.aClass141_6504.anInt1095;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cea.T(" + n + ')');
        }
    }
    
    @Override
    final Class228 method2974(final byte b, final ha ha) {
        try {
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cea.KA(" + b + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    final boolean method3052(final byte b) {
        try {
            return this.aClass141_6504 != null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cea.WA(" + b + ')');
        }
    }
    
    public static void method3053(final boolean b) {
        try {
            Class246_Sub3_Sub4_Sub2_Sub1.aClass171_6506 = null;
            Class246_Sub3_Sub4_Sub2_Sub1.aClass332_6508 = null;
            Class246_Sub3_Sub4_Sub2_Sub1.aString6507 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cea.BB(" + b + ')');
        }
    }
    
    final void method3054(final Class141 aClass141_6504, final int n) {
        try {
            this.aClass141_6504 = aClass141_6504;
            if (n != 1) {
                this.anInt6510 = -51;
            }
            if (super.aClass246_Sub5_6439 != null) {
                super.aClass246_Sub5_6439.method3127();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cea.AB(" + ((aClass141_6504 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final int method3030(final byte b) {
        try {
            if (this.aClass141_6504.anIntArray1109 != null) {
                final Class141 method2300 = this.aClass141_6504.method2300(Class75.aClass140_584, (byte)121);
                if (method2300 != null && method2300.anInt1145 != -1) {
                    return method2300.anInt1145;
                }
            }
            return this.aClass141_6504.anInt1145;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cea.G(" + b + ')');
        }
    }
    
    @Override
    final boolean method2982(final byte b) {
        try {
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cea.HA(" + b + ')');
        }
    }
    
    @Override
    final int method2986(final int n) {
        try {
            if (this.aClass141_6504 == null) {
                return 0;
            }
            return this.aClass141_6504.anInt1123;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cea.FB(" + n + ')');
        }
    }
    
    @Override
    final Class246_Sub1 method2975(final ha ha, final int n) {
        try {
            if (this.aClass141_6504 == null || !this.method3048(ha, 255, 2048)) {
                return null;
            }
            final Class111 method1793 = ha.method1793();
            final int method1794 = super.aClass325_6399.method3698((byte)116);
            method1793.method2101(method1794);
            final Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[super.aByte5088][super.anInt5084 >> Class151_Sub8.anInt5015][super.anInt5079 >> Class151_Sub8.anInt5015];
            if (class172 == null || class172.aClass246_Sub3_Sub1_1332 == null) {
                super.anInt6351 -= (int)(super.anInt6351 / 10.0f);
            }
            else {
                super.anInt6351 -= (int)((-class172.aClass246_Sub3_Sub1_1332.aShort6149 + super.anInt6351) / 10.0f);
            }
            method1793.method2106(super.anInt5084, -super.anInt6351 + super.anInt5089 - 20, super.anInt5079);
            final Class294 method1795 = this.method3039(1);
            final Class141 class173 = (this.aClass141_6504.anIntArray1109 != null) ? this.aClass141_6504.method2300(Class75.aClass140_584, (byte)70) : this.aClass141_6504;
            super.aBoolean6442 = false;
            Class246_Sub1 class246_Sub1 = null;
            if (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub26_4035.method662((byte)123) == 0xFFFFFFFE && class173.aBoolean1130 && method1795.aBoolean2400) {
                final Class97 class174 = (super.anInt6413 == -1 || ~super.anInt6400 != -1) ? null : Class151_Sub7.aClass183_5001.method2623(super.anInt6413, 16383);
                final Class97 class175 = (super.anInt6385 == -1 || (super.aBoolean6359 && class174 != null)) ? null : Class151_Sub7.aClass183_5001.method2623(super.anInt6385, 16383);
                final Class146 method1796 = Class102.method1703((class175 == null) ? class174 : class175, super.anInt6377, this.aClass141_6504.anInt1112, this.aClass141_6504.aByte1134 & 0xFF, super.aClass146Array6441[0], super.anInt6388, method1794, 121, 0xFF & this.aClass141_6504.aByte1122, this.aClass141_6504.aShort1135 & 0xFFFF, (class175 != null) ? super.anInt6350 : super.anInt6393, ha, this.aClass141_6504.aShort1094 & 0xFFFF, super.anInt6416);
                if (method1796 != null) {
                    class246_Sub1 = Class94.method915(1 + super.aClass146Array6441.length, (byte)(-47), this.method3051(2));
                    super.aBoolean6442 = true;
                    ha.C(false);
                    if (Class239.aBoolean1839) {
                        method1796.method2329(method1793, class246_Sub1.aClass246_Sub6Array5067[super.aClass146Array6441.length], Class16.anInt197, 0);
                    }
                    else {
                        method1796.method2325(method1793, class246_Sub1.aClass246_Sub6Array5067[super.aClass146Array6441.length], 0);
                    }
                    ha.C(true);
                }
            }
            method1793.method2101(method1794);
            method1793.method2106(super.anInt5084, super.anInt5089 - 5 + -super.anInt6351, super.anInt5079);
            if (class246_Sub1 == null) {
                class246_Sub1 = Class94.method915(super.aClass146Array6441.length, (byte)(-47), this.method3051(2));
            }
            this.method3036(ha, false, (byte)(-124), method1793, super.aClass146Array6441);
            if (Class239.aBoolean1839) {
                for (int i = 0; i < super.aClass146Array6441.length; ++i) {
                    if (super.aClass146Array6441[i] != null) {
                        super.aClass146Array6441[i].method2329(method1793, class246_Sub1.aClass246_Sub6Array5067[i], Class16.anInt197, 0);
                    }
                }
            }
            else {
                for (int n2 = 0; ~n2 > ~super.aClass146Array6441.length; ++n2) {
                    if (super.aClass146Array6441[n2] != null) {
                        super.aClass146Array6441[n2].method2325(method1793, class246_Sub1.aClass246_Sub6Array5067[n2], 0);
                    }
                }
            }
            if (super.aClass246_Sub5_6439 != null) {
                final Class242 method1797 = super.aClass246_Sub5_6439.method3116();
                if (!Class239.aBoolean1839) {
                    ha.method1820(method1797);
                }
                else {
                    ha.method1785(method1797, Class16.anInt197);
                }
            }
            for (int n3 = 0; super.aClass146Array6441.length > n3; ++n3) {
                if (super.aClass146Array6441[n3] != null) {
                    super.aBoolean6442 |= super.aClass146Array6441[n3].F();
                }
            }
            final Class146[] aClass146Array6441 = super.aClass146Array6441;
            final int n4 = 0;
            final Class146[] aClass146Array6442 = super.aClass146Array6441;
            final int n5 = 1;
            final Class146[] aClass146Array6443 = super.aClass146Array6441;
            final int n6 = 2;
            final Class146 class176 = null;
            aClass146Array6443[n6] = class176;
            aClass146Array6441[n4] = (aClass146Array6442[n5] = class176);
            super.anInt6417 = Class64_Sub15.anInt3676;
            if (n > -12) {
                this.method2982((byte)(-72));
            }
            return class246_Sub1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cea.QA(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public Class246_Sub3_Sub4_Sub2_Sub1() {
        this.anInt6501 = 1;
        this.anInt6503 = 1;
        this.anInt6505 = -1;
        this.anInt6510 = -1;
    }
    
    @Override
    final void method2992(final byte b) {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cea.DA(" + b + ')');
        }
    }
    
    static {
        Class246_Sub3_Sub4_Sub2_Sub1.anInt6500 = -1;
        Class246_Sub3_Sub4_Sub2_Sub1.aClass171_6506 = new OutgoingOpcode(5, 2);
    }
}
