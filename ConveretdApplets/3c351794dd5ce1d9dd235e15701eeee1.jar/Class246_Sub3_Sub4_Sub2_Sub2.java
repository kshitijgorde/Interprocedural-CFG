// 
// Decompiled by Procyon v0.5.30
// 

final class Class246_Sub3_Sub4_Sub2_Sub2 extends Class246_Sub3_Sub4_Sub2
{
    int anInt6512;
    private byte aByte6513;
    int anInt6514;
    int anInt6515;
    static IncomingOpcode aClass58_6516;
    int anInt6517;
    Class313 aClass313_6518;
    int anInt6519;
    boolean aBoolean6520;
    int anInt6521;
    int anInt6522;
    boolean aBoolean6523;
    int anInt6524;
    int anInt6525;
    boolean aBoolean6526;
    int anInt6527;
    int anInt6528;
    private int anInt6529;
    int anInt6530;
    private byte aByte6531;
    boolean aBoolean6532;
    static byte[][] aByteArrayArray6533;
    boolean aBoolean6534;
    int anInt6535;
    String aString6536;
    String aString6537;
    private byte aByte6538;
    int anInt6539;
    static boolean aBoolean6540;
    int anInt6541;
    int anInt6542;
    static Class196 aClass196_6543;
    
    final boolean method3055(final byte b) {
        try {
            if (this.aClass313_6518 == null) {
                return false;
            }
            if (b != 106) {
                this.aBoolean6534 = true;
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gv.WA(" + b + ')');
        }
    }
    
    final void method3056(final byte b, final int n, final int n2, final int n3) {
        try {
            if (super.anInt6434 < 9) {
                ++super.anInt6434;
            }
            for (int anInt6434 = super.anInt6434; ~anInt6434 < -1; --anInt6434) {
                super.anIntArray6437[anInt6434] = super.anIntArray6437[-1 + anInt6434];
                super.anIntArray6438[anInt6434] = super.anIntArray6438[anInt6434 - 1];
                super.aByteArray6443[anInt6434] = super.aByteArray6443[anInt6434 - 1];
            }
            super.anIntArray6437[0] = n3;
            super.anIntArray6438[0] = n2;
            if (n != 1) {
                method3064(-126);
            }
            super.aByteArray6443[0] = b;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gv.NA(" + b + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final void method3057(final int n, final int n2, final byte b, final int n3) {
        try {
            if (~super.anInt6413 != 0x0 && Class151_Sub7.aClass183_5001.method2623(super.anInt6413, 16383).anInt816 == 1) {
                super.anInt6413 = -1;
                super.anIntArray6373 = null;
            }
            if (super.anInt6379 != n3) {
                final Class107 method3564 = Class196.aClass304_1509.method3564(n3 + 3, super.anInt6379);
                if (method3564.aBoolean909 && method3564.anInt910 != -1 && ~Class151_Sub7.aClass183_5001.method2623(method3564.anInt910, 16383).anInt816 == 0xFFFFFFFE) {
                    super.anInt6379 = -1;
                }
            }
            if (~super.anInt6365 != 0x0) {
                final Class107 method3565 = Class196.aClass304_1509.method3564(2, super.anInt6365);
                if (method3565.aBoolean909 && method3565.anInt910 != -1 && Class151_Sub7.aClass183_5001.method2623(method3565.anInt910, 16383).anInt816 == 1) {
                    super.anInt6365 = -1;
                }
            }
            this.anInt6512 = -1;
            if (n < 0 || ~Class165.anInt1276 >= ~n || n2 < 0 || n2 >= Class98_Sub10_Sub7.anInt5572) {
                this.method3060(n2, n, 1470);
            }
            else if (~super.anIntArray6437[0] <= -1 && ~super.anIntArray6437[0] > ~Class165.anInt1276 && super.anIntArray6438[0] >= 0 && ~super.anIntArray6438[0] > ~Class98_Sub10_Sub7.anInt5572) {
                if (~b == 0xFFFFFFFD) {
                    Class363.method3930(false, (byte)2, this, n2, n);
                }
                this.method3056(b, 1, n2, n);
            }
            else {
                this.method3060(n2, n, 1470);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gv.LA(" + n + ',' + n2 + ',' + b + ',' + n3 + ')');
        }
    }
    
    private final boolean method3058(final byte b, final ha ha, int n) {
        try {
            final int n2 = n;
            final Class294 method3039 = this.method3039(1);
            final Class97 class97 = (super.anInt6413 == -1 || super.anInt6400 != 0) ? null : Class151_Sub7.aClass183_5001.method2623(super.anInt6413, 16383);
            final Class97 class98 = (super.anInt6385 == -1 || this.aBoolean6520 || (super.aBoolean6359 && class97 != null)) ? null : Class151_Sub7.aClass183_5001.method2623(super.anInt6385, 16383);
            final int anInt2362 = method3039.anInt2362;
            final int anInt2363 = method3039.anInt2382;
            if (~anInt2362 != -1 || ~anInt2363 != -1 || ~method3039.anInt2393 != -1 || ~method3039.anInt2363 != -1) {
                n |= 0x7;
            }
            final int method3040 = super.aClass325_6399.method3698((byte)116);
            final boolean b2 = ~super.aByte6422 != -1 && Class215.anInt1614 >= super.anInt6403 && Class215.anInt1614 < super.anInt6349;
            if (b2) {
                n |= 0x80000;
            }
            final Class146[] aClass146Array6441 = super.aClass146Array6441;
            final int n3 = 0;
            final Class146 method3041 = this.aClass313_6518.method3628(Class370.aClass257_3136, class97, class98, Class151_Sub7.aClass183_5001, super.anInt6393, Class149.aClass83_1205, super.anIntArray6370, Class98_Sub46_Sub19.aClass205_6068, true, super.anInt6409, super.aClass226Array6387, Class4.aClass301_85, Class75.aClass140_584, true, n, super.anInt6419, super.anInt6366, super.anInt6361, super.anInt6350, method3040, ha);
            aClass146Array6441[n3] = method3041;
            final Class146 class99 = method3041;
            final int method3042 = Class52.method488(true);
            if (Class292.anInt3359 < 96 && ~method3042 < -51) {
                Class206.method2727(89);
            }
            if (Class43.aClass196_375 == Class64_Sub29.aClass196_3720 || ~method3042 <= -51) {
                if (Class64_Sub29.aClass196_3720 != Class43.aClass196_375) {
                    Class98_Sub48.anInt4281 = 0;
                    Class76.aByteArrayArray590 = new byte[50][];
                }
            }
            else {
                final int n4 = -method3042 + 50;
                while (~n4 < ~Class98_Sub48.anInt4281) {
                    Class76.aByteArrayArray590[Class98_Sub48.anInt4281] = new byte[102400];
                    ++Class98_Sub48.anInt4281;
                }
                while (~Class98_Sub48.anInt4281 < ~n4) {
                    --Class98_Sub48.anInt4281;
                    Class76.aByteArrayArray590[Class98_Sub48.anInt4281] = null;
                }
            }
            if (class99 == null) {
                return false;
            }
            super.anInt6352 = class99.fa();
            super.anInt6354 = class99.ma();
            this.method3046(758, class99);
            if (~anInt2362 != -1 || anInt2363 != 0) {
                this.method3040(false, method3039.anInt2360, anInt2362, anInt2363, method3040, method3039.anInt2391);
                if (~super.anInt6388 != -1) {
                    class99.FA(super.anInt6388);
                }
                if (super.anInt6377 != 0) {
                    class99.VA(super.anInt6377);
                }
                if (~super.anInt6416 != -1) {
                    class99.H(0, super.anInt6416, 0);
                }
            }
            else {
                this.method3040(false, 0, this.method3034(0) << -1003342711, this.method3034(0) << -31149111, method3040, 0);
            }
            if (b2) {
                class99.method2337(super.aByte6404, super.aByte6381, super.aByte6368, 0xFF & super.aByte6422);
            }
            if (!this.aBoolean6520 && super.anInt6379 != -1 && ~super.anInt6376 != 0x0) {
                final Class107 method3043 = Class196.aClass304_1509.method3564(2, super.anInt6379);
                final boolean b3 = method3043.aByte923 == 3 && (~anInt2362 != -1 || anInt2363 != 0);
                int n5 = n2;
                if (!b3) {
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
                final Class146 method3044 = method3043.method1728(super.anInt6376, Class151_Sub7.aClass183_5001, n5, super.anInt6396, (byte)(-95), super.anInt6367, ha);
                aClass146Array6442[n6] = method3044;
                final Class146 class100 = method3044;
                if (class100 != null) {
                    if (~super.anInt6410 <= -1 && method3039.anIntArrayArray2366 != null && method3039.anIntArrayArray2366[super.anInt6410] != null) {
                        int n7 = 0;
                        int n8 = 0;
                        int n9 = 0;
                        if (method3039.anIntArrayArray2366 != null && method3039.anIntArrayArray2366[super.anInt6410] != null) {
                            n9 += method3039.anIntArrayArray2366[super.anInt6410][2];
                            n7 += method3039.anIntArrayArray2366[super.anInt6410][0];
                            n8 += method3039.anIntArrayArray2366[super.anInt6410][1];
                        }
                        if (method3039.anIntArrayArray2364 != null && method3039.anIntArrayArray2364[super.anInt6410] != null) {
                            n9 += method3039.anIntArrayArray2364[super.anInt6410][2];
                            n7 += method3039.anIntArrayArray2364[super.anInt6410][0];
                            n8 += method3039.anIntArrayArray2364[super.anInt6410][1];
                        }
                        if (n9 != 0 || ~n7 != -1) {
                            int n10 = method3040;
                            if (super.anIntArray6370 != null && ~super.anIntArray6370[super.anInt6410] != 0x0) {
                                n10 = super.anIntArray6370[super.anInt6410];
                            }
                            final int n11 = 0x3FFF & n10 + (super.anInt6389 * 2048 + -method3040);
                            if (n11 != 0) {
                                class100.a(n11);
                            }
                            final int n12 = Class284_Sub2_Sub2.anIntArray6200[n11];
                            final int n13 = Class284_Sub2_Sub2.anIntArray6202[n11];
                            final int n14 = n13 * n7 + n12 * n9 >> 1204025262;
                            n9 = n9 * n13 + -(n7 * n12) >> -1923367154;
                            n7 = n14;
                        }
                        class100.H(n7, n8, n9);
                    }
                    else if (super.anInt6389 != 0) {
                        class100.a(2048 * super.anInt6389);
                    }
                    if (~super.anInt6382 != -1) {
                        class100.H(0, -super.anInt6382 << -849373150, 0);
                    }
                    if (b3) {
                        if (super.anInt6388 != 0) {
                            class100.FA(super.anInt6388);
                        }
                        if (~super.anInt6377 != -1) {
                            class100.VA(super.anInt6377);
                        }
                        if (super.anInt6416 != 0) {
                            class100.H(0, super.anInt6416, 0);
                        }
                    }
                }
            }
            else {
                super.aClass146Array6441[1] = null;
            }
            if (this.aBoolean6520 || super.anInt6365 == -1 || ~super.anInt6428 == 0x0) {
                super.aClass146Array6441[2] = null;
            }
            else {
                final Class107 method3045 = Class196.aClass304_1509.method3564(2, super.anInt6365);
                final boolean b4 = ~method3045.aByte923 == 0xFFFFFFFC && (anInt2362 != 0 || ~anInt2363 != -1);
                int n15 = n2;
                if (!b4) {
                    if (~super.anInt6360 != -1) {
                        n15 |= 0x5;
                    }
                    if (super.anInt6363 != 0) {
                        n15 |= 0x2;
                    }
                    if (super.anInt6353 >= 0) {
                        n15 |= 0x7;
                    }
                }
                else {
                    n15 |= 0x7;
                }
                final Class146[] aClass146Array6443 = super.aClass146Array6441;
                final int n16 = 2;
                final Class146 method3046 = method3045.method1721(ha, super.anInt6421, 21945, n15, Class151_Sub7.aClass183_5001, super.anInt6427, super.anInt6428);
                aClass146Array6443[n16] = method3046;
                final Class146 class101 = method3046;
                if (class101 != null) {
                    if (~super.anInt6353 > -1 || method3039.anIntArrayArray2366 == null || method3039.anIntArrayArray2366[super.anInt6353] == null) {
                        if (~super.anInt6360 != -1) {
                            class101.a(2048 * super.anInt6360);
                        }
                    }
                    else {
                        int n17 = 0;
                        int n18 = 0;
                        int n19 = 0;
                        if (method3039.anIntArrayArray2366 != null && method3039.anIntArrayArray2366[super.anInt6353] != null) {
                            n19 += method3039.anIntArrayArray2366[super.anInt6353][2];
                            n18 += method3039.anIntArrayArray2366[super.anInt6353][1];
                            n17 += method3039.anIntArrayArray2366[super.anInt6353][0];
                        }
                        if (method3039.anIntArrayArray2364 != null && method3039.anIntArrayArray2364[super.anInt6353] != null) {
                            n19 += method3039.anIntArrayArray2364[super.anInt6353][2];
                            n18 += method3039.anIntArrayArray2364[super.anInt6353][1];
                            n17 += method3039.anIntArrayArray2364[super.anInt6353][0];
                        }
                        if (~n19 != -1 || n17 != 0) {
                            int n20 = method3040;
                            if (super.anIntArray6370 != null && ~super.anIntArray6370[super.anInt6353] != 0x0) {
                                n20 = super.anIntArray6370[super.anInt6353];
                            }
                            final int n21 = 0x3FFF & -method3040 + (super.anInt6360 * 2048 - -n20);
                            if (~n21 != -1) {
                                class101.a(n21);
                            }
                            final int n22 = Class284_Sub2_Sub2.anIntArray6200[n21];
                            final int n23 = Class284_Sub2_Sub2.anIntArray6202[n21];
                            final int n24 = n17 * n23 + n22 * n19 >> 1334277742;
                            n19 = n23 * n19 - n22 * n17 >> 1208318926;
                            n17 = n24;
                        }
                        class101.H(n17, n18, n19);
                    }
                    if (~super.anInt6363 != -1) {
                        class101.H(0, -super.anInt6363 << -623157630, 0);
                    }
                    if (b4) {
                        if (~super.anInt6388 != -1) {
                            class101.FA(super.anInt6388);
                        }
                        if (super.anInt6377 != 0) {
                            class101.VA(super.anInt6377);
                        }
                        if (~super.anInt6416 != -1) {
                            class101.H(0, super.anInt6416, 0);
                        }
                    }
                }
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gv.IA(" + b + ',' + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final boolean method2982(final byte b) {
        try {
            if (b >= -70) {
                Class246_Sub3_Sub4_Sub2_Sub2.aClass196_6543 = null;
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gv.HA(" + b + ')');
        }
    }
    
    final String method3059(final int n, final boolean b) {
        try {
            if (n != -1) {
                this.method2992((byte)110);
            }
            if (b) {
                return this.aString6536;
            }
            return this.aString6537;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gv.JA(" + n + ',' + b + ')');
        }
    }
    
    final void method3060(final int n, final int n2, final int n3) {
        try {
            super.anIntArray6437[0] = n2;
            super.anInt6433 = 0;
            super.anInt6436 = 0;
            super.anInt6434 = 0;
            if (n3 != 1470) {
                this.aByte6538 = -42;
            }
            super.anIntArray6438[0] = n;
            final int method3034 = this.method3034(0);
            super.anInt5084 = 512 * super.anIntArray6437[0] + 256 * method3034;
            super.anInt5079 = method3034 * 256 + super.anIntArray6438[0] * 512;
            if (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660 == this) {
                Class374.method3980((byte)126);
            }
            if (super.aClass246_Sub5_6439 != null) {
                super.aClass246_Sub5_6439.method3127();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gv.VA(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final Class228 method2974(final byte b, final ha ha) {
        try {
            if (b != -53) {
                return null;
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gv.KA(" + b + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method3061(final ha ha, final int n, final int n2, final int n3, final boolean b, final Class111 class111, final int n4, final int n5, final Class146 class112) {
        try {
            final int n6 = n * n + n3 * n3;
            if (~n6 <= -262145 && ~n4 <= ~n6) {
                if (!b) {
                    this.method3030((byte)44);
                }
                final Class146 method1052 = Class98_Sub10_Sub16.method1052(super.anInt6377, 0x3FFF & (int)(2607.5945876176133 * Math.atan2(n3, n)), super.anInt6388, ha, n5, 106, super.anInt6416);
                if (method1052 != null) {
                    ha.C(false);
                    method1052.method2329(class111, null, n2, 0);
                    ha.C(true);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gv.AB(" + ((ha != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + b + ',' + ((class111 != null) ? "{...}" : "null") + ',' + n4 + ',' + n5 + ',' + ((class112 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method3062(final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            class98_Sub22.anInt3991 = 0;
            final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-101));
            this.aByte6531 = (byte)(0x1 & unsignedByte);
            final boolean aBoolean6526 = this.aBoolean6526;
            this.aBoolean6526 = (~(unsignedByte & 0x2) != -1);
            final boolean b2 = (0x4 & unsignedByte) != 0x0;
            final int method3034 = super.method3034(0);
            this.method3045((byte)68, 1 + (0x7 & unsignedByte >> 846259619));
            this.aByte6513 = (byte)(unsignedByte >> -218019610 & 0x3);
            super.anInt5084 += -method3034 + this.method3034(b - 73) << 1992716520;
            super.anInt5079 += this.method3034(0) - method3034 << 1346950216;
            this.aByte6538 = class98_Sub22.readSignedByte((byte)(-19));
            this.anInt6515 = class98_Sub22.readSignedByte((byte)(-19));
            this.anInt6530 = class98_Sub22.readSignedByte((byte)(-19));
            this.aBoolean6523 = (class98_Sub22.readSignedByte((byte)(-19)) == 1);
            if (Class64_Sub29.aClass196_3720 == Class43.aClass196_375 && Class282.anInt2125 >= 2) {
                this.aBoolean6523 = false;
            }
            int short1 = -1;
            this.anInt6528 = 0;
            final int[] array = new int[12];
            for (int i = 0; i < 12; ++i) {
                final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)(-123));
                if (unsignedByte2 == 0) {
                    array[i] = 0;
                }
                else {
                    final int n = (unsignedByte2 << -30617816) + class98_Sub22.readUnsignedByte((byte)(-111));
                    if (i == 0 && ~n == 0xFFFF0000) {
                        short1 = class98_Sub22.readShort((byte)127);
                        this.anInt6528 = class98_Sub22.readUnsignedByte((byte)(-114));
                        break;
                    }
                    if (n >= 32768) {
                        final int n2 = Class255.anIntArray3207[-32768 + n];
                        array[i] = Class41.method366(1073741824, n2);
                        final int anInt2435 = Class98_Sub46_Sub19.aClass205_6068.method2714(n2, (byte)(-119)).anInt2435;
                        if (anInt2435 != 0) {
                            this.anInt6528 = anInt2435;
                        }
                    }
                    else {
                        array[i] = Class41.method366(Integer.MIN_VALUE, n - 256);
                    }
                }
            }
            final int[] array2 = new int[5];
            for (int j = 0; j < 5; ++j) {
                int unsignedByte3 = class98_Sub22.readUnsignedByte((byte)(-108));
                if (~Class61.aShortArrayArrayArray478.length > -2 || unsignedByte3 < 0 || Class61.aShortArrayArrayArray478[0][j].length <= unsignedByte3) {
                    unsignedByte3 = 0;
                }
                array2[j] = unsignedByte3;
            }
            this.anInt6529 = class98_Sub22.readShort((byte)127);
            this.aString6536 = class98_Sub22.readString((byte)84);
            this.aString6537 = this.aString6536;
            if (this == Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660) {
                Class256_Sub1.aString5157 = this.aString6536;
            }
            this.anInt6519 = class98_Sub22.readUnsignedByte((byte)80);
            if (!b2) {
                this.anInt6539 = 0;
                this.anInt6542 = class98_Sub22.readUnsignedByte((byte)(-125));
                this.anInt6535 = class98_Sub22.readUnsignedByte((byte)31);
                if (~this.anInt6535 == 0xFFFFFF00) {
                    this.anInt6535 = -1;
                }
            }
            else {
                this.anInt6539 = class98_Sub22.readShort((byte)127);
                this.anInt6535 = -1;
                this.anInt6542 = this.anInt6519;
                if (~this.anInt6539 == 0xFFFF0000) {
                    this.anInt6539 = -1;
                }
            }
            final int anInt2436 = this.anInt6525;
            this.anInt6525 = class98_Sub22.readUnsignedByte((byte)(-122));
            if (~this.anInt6525 == -1) {
                Class213.method2778(true, this);
            }
            else {
                final int anInt2437 = this.anInt6527;
                final int anInt2438 = this.anInt6524;
                final int anInt2439 = this.anInt6522;
                final int anInt2440 = this.anInt6517;
                final int anInt2441 = this.anInt6514;
                this.anInt6527 = class98_Sub22.readShort((byte)127);
                this.anInt6524 = class98_Sub22.readShort((byte)127);
                this.anInt6522 = class98_Sub22.readShort((byte)127);
                this.anInt6517 = class98_Sub22.readShort((byte)127);
                this.anInt6514 = class98_Sub22.readUnsignedByte((byte)(-115));
                if (!aBoolean6526 != !this.aBoolean6526 || anInt2436 != this.anInt6525 || anInt2437 != this.anInt6527 || anInt2438 != this.anInt6524 || anInt2439 != this.anInt6522 || anInt2440 != this.anInt6517 || this.anInt6514 != anInt2441) {
                    Class322.method3674(0, this);
                }
            }
            if (this.aClass313_6518 == null) {
                this.aClass313_6518 = new Class313();
            }
            final int anInt2442 = this.aClass313_6518.anInt2684;
            final int[] anIntArray2683 = this.aClass313_6518.anIntArray2683;
            this.aClass313_6518.method3627(array, -59, ~this.aByte6531 == 0xFFFFFFFE, array2, this.method3030((byte)70), short1);
            if (b == 73) {
                if (~short1 != ~anInt2442) {
                    super.anInt5084 = (super.anIntArray6437[0] << -1791062743) - -(this.method3034(0) << 2009592264);
                    super.anInt5079 = (super.anIntArray6438[0] << 845342377) - -(this.method3034(b ^ 0x49) << 1847977800);
                }
                if (~super.anInt6369 == ~za_Sub2.anInt6080 && anIntArray2683 != null) {
                    for (int n3 = 0; array2.length > n3; ++n3) {
                        if (~anIntArray2683[n3] != ~array2[n3]) {
                            Class98_Sub46_Sub19.aClass205_6068.method2717(b - 9);
                            break;
                        }
                    }
                }
                if (super.aClass246_Sub5_6439 != null) {
                    super.aClass246_Sub5_6439.method3127();
                }
                if (super.anInt6385 != -1 && super.aBoolean6359) {
                    if (!this.method3039(1).method3480((byte)119, super.anInt6385)) {
                        super.aBoolean6359 = false;
                        super.anInt6385 = -1;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gv.SA(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    final void method2981(final Class246_Sub3 class246_Sub3, final byte b, final boolean b2, final int n, final ha ha, final int n2, final int n3) {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gv.CA(" + ((class246_Sub3 != null) ? "{...}" : "null") + ',' + b + ',' + b2 + ',' + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final boolean method2976(final int n, final ha ha, final byte b, final int n2) {
        try {
            if (this.aClass313_6518 == null || !this.method3058((byte)(-105), ha, 131072)) {
                return false;
            }
            final Class111 method1793 = ha.method1793();
            method1793.method2101(super.aClass325_6399.method3698((byte)116));
            method1793.method2106(super.anInt5084, super.anInt5089, super.anInt5079);
            boolean b2 = false;
            for (int n3 = 0; super.aClass146Array6441.length > n3; ++n3) {
                if (super.aClass146Array6441[n3] != null) {
                    if (Class239.aBoolean1839) {
                        if (!super.aClass146Array6441[n3].method2333(n, n2, method1793, true, 0, Class16.anInt197)) {
                            continue;
                        }
                    }
                    else if (!super.aClass146Array6441[n3].method2339(n, n2, method1793, true, 0)) {
                        continue;
                    }
                    b2 = true;
                    break;
                }
            }
            if (b < 59) {
                this.aBoolean6534 = true;
            }
            final Class146[] aClass146Array6441 = super.aClass146Array6441;
            final int n4 = 0;
            final Class146[] aClass146Array6442 = super.aClass146Array6441;
            final int n5 = 1;
            final Class146[] aClass146Array6443 = super.aClass146Array6441;
            final int n6 = 2;
            final Class146 class146 = null;
            aClass146Array6443[n6] = class146;
            aClass146Array6441[n4] = (aClass146Array6442[n5] = class146);
            return b2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gv.TA(" + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + b + ',' + n2 + ')');
        }
    }
    
    final String method3063(final int n, final boolean b) {
        try {
            String s = "";
            if (n != 0) {
                return null;
            }
            if (Class116.aStringArray966 != null) {
                s += Class116.aStringArray966[this.aByte6513];
            }
            int[] array;
            if (~this.aByte6531 == 0xFFFFFFFE && Class35.anIntArray333 != null) {
                array = Class35.anIntArray333;
            }
            else {
                array = Class272.anIntArray2036;
            }
            if (array != null && ~array[this.aByte6513] != 0x0) {
                final Class306 method302 = Class98_Sub10_Sub16.aClass29_5620.method302(array[this.aByte6513], 1028602529);
                if (~method302.aChar2567 == 0xFFFFFF8C) {
                    s += method302.method3594(this.aByte6538 & 0xFF, (byte)37);
                }
                else {
                    Class305_Sub1.method3585(new Throwable(), n - 126, "gdn1");
                    array[this.aByte6513] = -1;
                }
            }
            String s2;
            if (!b) {
                s2 = s + this.aString6537;
            }
            else {
                s2 = s + this.aString6536;
            }
            if (Class84.aStringArray636 != null) {
                s2 += Class84.aStringArray636[this.aByte6513];
            }
            return s2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gv.PA(" + n + ',' + b + ')');
        }
    }
    
    @Override
    final int method3035(final int n) {
        try {
            if (n != 28213) {
                this.method3062(null, (byte)(-78));
            }
            return -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gv.T(" + n + ')');
        }
    }
    
    @Override
    final void method2988(final ha ha, final int n) {
        try {
            if (this.aClass313_6518 != null && (super.aBoolean6440 || this.method3058((byte)35, ha, 0))) {
                final Class111 method1793 = ha.method1793();
                method1793.method2101(super.aClass325_6399.method3698((byte)116));
                method1793.method2106(super.anInt5084, super.anInt5089 - 5, super.anInt5079);
                this.method3036(ha, super.aBoolean6440, (byte)(-126), method1793, super.aClass146Array6441);
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
            throw Class64_Sub27.method667(ex, "gv.MA(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final int method3034(final int n) {
        try {
            if (this.aClass313_6518 != null && ~this.aClass313_6518.anInt2684 != 0x0) {
                return Class4.aClass301_85.method3538(n + 5, this.aClass313_6518.anInt2684).anInt1112;
            }
            if (n != 0) {
                return 57;
            }
            return super.method3034(0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gv.A(" + n + ')');
        }
    }
    
    public static void method3064(final int n) {
        try {
            if (n != -2485) {
                method3064(-115);
            }
            Class246_Sub3_Sub4_Sub2_Sub2.aClass196_6543 = null;
            Class246_Sub3_Sub4_Sub2_Sub2.aByteArrayArray6533 = null;
            Class246_Sub3_Sub4_Sub2_Sub2.aClass58_6516 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gv.OA(" + n + ')');
        }
    }
    
    @Override
    final void method2992(final byte b) {
        try {
            if (b != -73) {
                this.method3035(16);
            }
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gv.DA(" + b + ')');
        }
    }
    
    static final void method3065(final int n, final int n2, final boolean b) {
        try {
            final Class28 class28 = Class76.aClass28ArrayArray586[n][n2];
            if (b) {
                if (class28 != null) {
                    Class48.anInt410 = class28.anInt284;
                    InputStream_Sub2.anInt29 = class28.anInt282;
                    OutputStream_Sub2.anInt40 = class28.anInt292;
                }
                Class230.method2870((byte)(-74));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gv.UA(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    @Override
    final Class246_Sub1 method2975(final ha ha, final int n) {
        try {
            if (this.aClass313_6518 == null || !this.method3058((byte)(-122), ha, 2048)) {
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
                super.anInt6351 -= (int)((super.anInt6351 - class172.aClass246_Sub3_Sub1_1332.aShort6149) / 10.0f);
            }
            method1793.method2106(super.anInt5084, -super.anInt6351 + super.anInt5089 - 20, super.anInt5079);
            Class246_Sub1 class246_Sub1 = null;
            if (n > -12) {
                return null;
            }
            super.aBoolean6442 = false;
            if (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub26_4035.method662((byte)120) == 0xFFFFFFFE && this.method3039(1).aBoolean2400 && (this.aClass313_6518.anInt2684 == -1 || Class4.aClass301_85.method3538(5, this.aClass313_6518.anInt2684).aBoolean1130)) {
                final Class97 class173 = (~super.anInt6413 != 0x0 && super.anInt6400 == 0) ? Class151_Sub7.aClass183_5001.method2623(super.anInt6413, 16383) : null;
                final Class97 class174 = (~super.anInt6385 == 0x0 || this.aBoolean6520 || (super.aBoolean6359 && class173 != null)) ? null : Class151_Sub7.aClass183_5001.method2623(super.anInt6385, 16383);
                final Class146 method1795 = Class102.method1703((class174 == null) ? class173 : class174, super.anInt6377, 1, 240, super.aClass146Array6441[0], super.anInt6388, method1794, 124, 160, 0, (class174 == null) ? super.anInt6393 : super.anInt6350, ha, 0, super.anInt6416);
                if (method1795 != null) {
                    class246_Sub1 = Class94.method915(1 + super.aClass146Array6441.length, (byte)(-47), true);
                    super.aBoolean6442 = true;
                    ha.C(false);
                    if (Class239.aBoolean1839) {
                        method1795.method2329(method1793, class246_Sub1.aClass246_Sub6Array5067[super.aClass146Array6441.length], Class16.anInt197, 0);
                    }
                    else {
                        method1795.method2325(method1793, class246_Sub1.aClass246_Sub6Array5067[super.aClass146Array6441.length], 0);
                    }
                    ha.C(true);
                }
            }
            if (this == Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660) {
                method1793.method2100(super.anInt5084, super.anInt5089, super.anInt5079);
                for (int n2 = -1 + Class104.aClass36Array903.length; ~n2 <= -1; --n2) {
                    final Class36 class175 = Class104.aClass36Array903[n2];
                    if (class175 != null && class175.anInt339 != -1) {
                        if (~class175.anInt346 == 0xFFFFFFFE) {
                            final Class98_Sub39 class98_Sub39 = (Class98_Sub39)Class260.aClass377_3254.method3990(class175.anInt345, -1);
                            if (class98_Sub39 != null) {
                                final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4187 = class98_Sub39.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                                final int n3 = -Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084 + aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084;
                                final int n4 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079 - Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079;
                                if (!Class239.aBoolean1839) {
                                    this.method3066(n4, method1793, n3, false, super.aClass146Array6441[0], 92160000, ha, class175.anInt339);
                                }
                                else {
                                    this.method3061(ha, n4, Class16.anInt197, n3, true, method1793, 92160000, class175.anInt339, super.aClass146Array6441[0]);
                                }
                            }
                        }
                        if (class175.anInt346 == 2) {
                            final int n5 = -Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084 + class175.anInt338 + 256;
                            final int n6 = -Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079 + 256 + class175.anInt347;
                            final int n7 = class175.anInt340 << 556190985;
                            final int n8 = n7 * n7;
                            if (Class239.aBoolean1839) {
                                this.method3061(ha, n6, Class16.anInt197, n5, true, method1793, n8, class175.anInt339, super.aClass146Array6441[0]);
                            }
                            else {
                                this.method3066(n6, method1793, n5, false, super.aClass146Array6441[0], n8, ha, class175.anInt339);
                            }
                        }
                        if (~class175.anInt346 == 0xFFFFFFF5 && ~class175.anInt345 <= -1 && class175.anInt345 < Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030.length) {
                            final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[class175.anInt345];
                            if (class246_Sub3_Sub4_Sub2_Sub2 != null) {
                                final int n9 = class246_Sub3_Sub4_Sub2_Sub2.anInt5084 - Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084;
                                final int n10 = -Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079 + class246_Sub3_Sub4_Sub2_Sub2.anInt5079;
                                if (!Class239.aBoolean1839) {
                                    this.method3066(n10, method1793, n9, false, super.aClass146Array6441[0], 92160000, ha, class175.anInt339);
                                }
                                else {
                                    this.method3061(ha, n10, Class16.anInt197, n9, true, method1793, 92160000, class175.anInt339, super.aClass146Array6441[0]);
                                }
                            }
                        }
                    }
                }
                method1793.method2101(method1794);
                method1793.method2106(super.anInt5084, super.anInt5089, super.anInt5079);
            }
            method1793.method2101(method1794);
            method1793.method2106(super.anInt5084, -super.anInt6351 - 5 + super.anInt5089, super.anInt5079);
            if (class246_Sub1 == null) {
                class246_Sub1 = Class94.method915(super.aClass146Array6441.length, (byte)(-47), true);
            }
            this.method3036(ha, false, (byte)(-124), method1793, super.aClass146Array6441);
            if (!Class239.aBoolean1839) {
                for (int n11 = 0; ~super.aClass146Array6441.length < ~n11; ++n11) {
                    if (super.aClass146Array6441[n11] != null) {
                        super.aClass146Array6441[n11].method2325(method1793, class246_Sub1.aClass246_Sub6Array5067[n11], (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660 == this) ? 1 : 0);
                    }
                }
            }
            else {
                for (int n12 = 0; ~n12 > ~super.aClass146Array6441.length; ++n12) {
                    if (super.aClass146Array6441[n12] != null) {
                        super.aClass146Array6441[n12].method2329(method1793, class246_Sub1.aClass246_Sub6Array5067[n12], Class16.anInt197, (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660 == this) ? 1 : 0);
                    }
                }
            }
            if (super.aClass246_Sub5_6439 != null) {
                final Class242 method1796 = super.aClass246_Sub5_6439.method3116();
                if (Class239.aBoolean1839) {
                    ha.method1785(method1796, Class16.anInt197);
                }
                else {
                    ha.method1820(method1796);
                }
            }
            for (int n13 = 0; super.aClass146Array6441.length > n13; ++n13) {
                if (super.aClass146Array6441[n13] != null) {
                    super.aBoolean6442 |= super.aClass146Array6441[n13].F();
                }
            }
            super.anInt6417 = Class64_Sub15.anInt3676;
            final Class146[] aClass146Array6441 = super.aClass146Array6441;
            final int n14 = 0;
            final Class146[] aClass146Array6442 = super.aClass146Array6441;
            final int n15 = 1;
            final Class146[] aClass146Array6443 = super.aClass146Array6441;
            final int n16 = 2;
            final Class146 class176 = null;
            aClass146Array6443[n16] = class176;
            aClass146Array6441[n14] = (aClass146Array6442[n15] = class176);
            return class246_Sub1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gv.QA(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final int method3030(final byte b) {
        try {
            if (b != 70) {
                this.method3030((byte)12);
            }
            return this.anInt6529;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gv.G(" + b + ')');
        }
    }
    
    private final void method3066(final int n, final Class111 class111, final int n2, final boolean b, final Class146 class112, final int n3, final ha ha, final int n4) {
        try {
            final int n5 = n * n + n2 * n2;
            if (n5 >= 262144 && ~n5 >= ~n3 && !b) {
                final Class146 method1052 = Class98_Sub10_Sub16.method1052(super.anInt6377, (int)(Math.atan2(n2, n) * 2607.5945876176133) & 0x3FFF, super.anInt6388, ha, n4, 94, super.anInt6416);
                if (method1052 != null) {
                    ha.C(false);
                    method1052.method2325(class111, null, 0);
                    ha.C(true);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gv.RA(" + n + ',' + ((class111 != null) ? "{...}" : "null") + ',' + n2 + ',' + b + ',' + ((class112 != null) ? "{...}" : "null") + ',' + n3 + ',' + ((ha != null) ? "{...}" : "null") + ',' + n4 + ')');
        }
    }
    
    public Class246_Sub3_Sub4_Sub2_Sub2() {
        this.anInt6512 = -1;
        this.anInt6515 = -1;
        this.anInt6517 = -1;
        this.aByte6513 = 0;
        this.anInt6519 = 0;
        this.anInt6524 = -1;
        this.aBoolean6532 = false;
        this.anInt6535 = -1;
        this.aBoolean6523 = false;
        this.anInt6530 = -1;
        this.anInt6539 = 0;
        this.aByte6538 = 0;
        this.anInt6514 = 255;
        this.anInt6525 = 0;
        this.aBoolean6534 = false;
        this.aBoolean6526 = false;
        this.anInt6528 = 0;
        this.anInt6522 = -1;
        this.anInt6527 = -1;
        this.aByte6531 = 0;
        this.aBoolean6520 = false;
        this.anInt6542 = 0;
    }
    
    static {
        Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540 = false;
        Class246_Sub3_Sub4_Sub2_Sub2.aClass58_6516 = new IncomingOpcode(28, -2);
        Class246_Sub3_Sub4_Sub2_Sub2.aClass196_6543 = new Class196("WTQA", "office", "_qa", 2);
    }
}
