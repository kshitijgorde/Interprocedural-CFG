// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class246_Sub3_Sub4_Sub2 extends Class246_Sub3_Sub4
{
    int anInt6347;
    boolean aBoolean6348;
    int anInt6349;
    int anInt6350;
    int anInt6351;
    int anInt6352;
    int anInt6353;
    int anInt6354;
    private byte aByte6355;
    int anInt6356;
    static int anInt6357;
    int anInt6358;
    boolean aBoolean6359;
    int anInt6360;
    int anInt6361;
    int anInt6362;
    int anInt6363;
    int anInt6364;
    int anInt6365;
    int anInt6366;
    int anInt6367;
    byte aByte6368;
    int anInt6369;
    int[] anIntArray6370;
    boolean aBoolean6371;
    int anInt6372;
    int[] anIntArray6373;
    String aString6374;
    int[] anIntArray6375;
    int anInt6376;
    int anInt6377;
    int anInt6378;
    int anInt6379;
    int anInt6380;
    byte aByte6381;
    int anInt6382;
    int[] anIntArray6383;
    int anInt6384;
    int anInt6385;
    int[] anIntArray6386;
    Class226[] aClass226Array6387;
    int anInt6388;
    int anInt6389;
    int anInt6390;
    int anInt6391;
    int anInt6392;
    int anInt6393;
    int anInt6394;
    private int anInt6395;
    int anInt6396;
    int[] anIntArray6397;
    int anInt6398;
    Class325 aClass325_6399;
    int anInt6400;
    int anInt6401;
    int anInt6402;
    int anInt6403;
    byte aByte6404;
    int anInt6405;
    int anInt6406;
    int anInt6407;
    int anInt6408;
    int anInt6409;
    int anInt6410;
    int anInt6411;
    int anInt6412;
    int anInt6413;
    int anInt6414;
    int anInt6415;
    int anInt6416;
    int anInt6417;
    int anInt6418;
    int anInt6419;
    int anInt6420;
    int anInt6421;
    byte aByte6422;
    int[] anIntArray6423;
    int anInt6424;
    int[] anIntArray6425;
    int anInt6426;
    int anInt6427;
    int anInt6428;
    int anInt6429;
    int[] anIntArray6430;
    private Class325 aClass325_6431;
    private Class325 aClass325_6432;
    int anInt6433;
    int anInt6434;
    int anInt6435;
    int anInt6436;
    int[] anIntArray6437;
    int[] anIntArray6438;
    Class246_Sub5 aClass246_Sub5_6439;
    boolean aBoolean6440;
    Class146[] aClass146Array6441;
    boolean aBoolean6442;
    byte[] aByteArray6443;
    
    @Override
    final boolean method2987(final int n) {
        try {
            if (n != 6540) {
                this.method3045((byte)(-59), 60);
            }
            return this.aBoolean6442;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.I(" + n + ')');
        }
    }
    
    @Override
    final void method3022(final int n) {
        try {
            if (n != -8675) {
                this.method3037(-16, true, -64, 100, -58, -61, -88, 24);
            }
            final int n2 = (-1 + this.anInt6395 << 1914403080) + 240;
            super.aShort6159 = (short)(n2 + super.anInt5079 >> 1380990889);
            super.aShort6160 = (short)(super.anInt5084 + n2 >> 1794948489);
            super.aShort6157 = (short)(super.anInt5079 + -n2 >> 956113769);
            super.aShort6158 = (short)(-n2 + super.anInt5084 >> 12223625);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.F(" + n + ')');
        }
    }
    
    abstract int method3030(final byte p0);
    
    final void method3031(final int anInt6436) {
        try {
            this.anInt6436 = anInt6436;
            this.anInt6434 = 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.R(" + anInt6436 + ')');
        }
    }
    
    final void method3032(final int n, final boolean b, final int n2, final int n3, final int n4, final int n5) {
        try {
            final int n6 = b ? this.anInt6365 : this.anInt6379;
            if (~n4 != 0x0 && n6 != -1) {
                if (n6 != n4) {
                    final Class107 method3564 = Class196.aClass304_1509.method3564(2, n4);
                    final Class107 method3565 = Class196.aClass304_1509.method3564(2, n6);
                    if (method3564.anInt910 != -1 && ~method3565.anInt910 != 0x0 && Class151_Sub7.aClass183_5001.method2623(method3565.anInt910, 16383).anInt829 > Class151_Sub7.aClass183_5001.method2623(method3564.anInt910, 16383).anInt829) {
                        return;
                    }
                }
                else {
                    final Class107 method3566 = Class196.aClass304_1509.method3564(2, n4);
                    if (method3566.aBoolean909 && method3566.anInt910 != -1) {
                        final int anInt819 = Class151_Sub7.aClass183_5001.method2623(method3566.anInt910, 16383).anInt819;
                        if (~anInt819 == -1) {
                            return;
                        }
                        if (anInt819 == 2) {
                            if (!b) {
                                this.anInt6356 = 0;
                                return;
                            }
                            this.anInt6380 = 0;
                            return;
                        }
                    }
                }
            }
            if (n5 >= -69) {
                this.method3035(-115);
            }
            final int anInt820 = Class215.anInt1614;
            if (b) {
                this.anInt6421 = 1;
                this.anInt6426 = anInt820 - -(n2 & 0xFFFF);
                this.anInt6427 = 0;
                this.anInt6365 = n4;
                this.anInt6363 = n2 >> 813121872;
                this.anInt6428 = 0;
                this.anInt6353 = n;
                this.anInt6360 = n3;
                if (anInt820 < this.anInt6426) {
                    this.anInt6428 = -1;
                }
                if (~this.anInt6365 != 0x0 && anInt820 == this.anInt6426) {
                    final int anInt821 = Class196.aClass304_1509.method3564(2, this.anInt6365).anInt910;
                    if (anInt821 != -1) {
                        final Class97 method3567 = Class151_Sub7.aClass183_5001.method2623(anInt821, 16383);
                        if (method3567 != null && method3567.anIntArray818 != null && !this.aBoolean6371) {
                            Class349.method3840((byte)(-128), this, 0, method3567);
                        }
                    }
                }
            }
            else {
                this.anInt6396 = 0;
                this.anInt6367 = 1;
                this.anInt6379 = n4;
                this.anInt6410 = n;
                this.anInt6389 = n3;
                this.anInt6391 = anInt820 + (n2 & 0xFFFF);
                this.anInt6382 = n2 >> -1127488624;
                this.anInt6376 = 0;
                if (~anInt820 > ~this.anInt6391) {
                    this.anInt6376 = -1;
                }
                if (~this.anInt6379 != 0x0 && this.anInt6391 == anInt820) {
                    final int anInt822 = Class196.aClass304_1509.method3564(2, this.anInt6379).anInt910;
                    if (~anInt822 != 0x0) {
                        final Class97 method3568 = Class151_Sub7.aClass183_5001.method2623(anInt822, 16383);
                        if (method3568 != null && method3568.anIntArray818 != null && !this.aBoolean6371) {
                            Class349.method3840((byte)(-128), this, 0, method3568);
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.S(" + n + ',' + b + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    final int method3033(final byte b) {
        try {
            final Class294 method3039 = this.method3039(1);
            final int anInt2730 = this.aClass325_6399.anInt2730;
            boolean b2;
            if (method3039.anInt2398 == 0) {
                b2 = this.aClass325_6399.method3699(4201, this.anInt6414, this.anInt6415, this.anInt6414);
            }
            else {
                b2 = this.aClass325_6399.method3699(4201, method3039.anInt2383, this.anInt6415, method3039.anInt2398);
            }
            final int n = -anInt2730 + this.aClass325_6399.anInt2730;
            if (n != 0) {
                ++this.anInt6408;
            }
            else {
                this.anInt6408 = 0;
                this.aClass325_6399.method3697(true, this.anInt6415);
            }
            if (b2) {
                if (~method3039.anInt2390 != -1) {
                    if (~n < -1) {
                        this.aClass325_6431.method3699(4201, method3039.anInt2392, method3039.anInt2393, method3039.anInt2390);
                    }
                    else {
                        this.aClass325_6431.method3699(4201, method3039.anInt2392, -method3039.anInt2393, method3039.anInt2390);
                    }
                }
                if (method3039.anInt2375 != 0) {
                    this.aClass325_6432.method3699(4201, method3039.anInt2380, method3039.anInt2363, method3039.anInt2375);
                }
            }
            else {
                if (~method3039.anInt2390 == -1) {
                    this.aClass325_6431.method3697(true, 0);
                }
                else {
                    this.aClass325_6431.method3699(4201, method3039.anInt2392, 0, method3039.anInt2390);
                }
                if (method3039.anInt2375 != 0) {
                    this.aClass325_6432.method3699(4201, method3039.anInt2380, 0, method3039.anInt2375);
                }
                else {
                    this.aClass325_6432.method3697(true, 0);
                }
            }
            return n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.L(" + b + ')');
        }
    }
    
    int method3034(final int n) {
        try {
            if (n != 0) {
                return -77;
            }
            return this.anInt6395;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.A(" + n + ')');
        }
    }
    
    abstract int method3035(final int p0);
    
    final void method3036(final ha ha, final boolean b, final byte b2, final Class111 class111, final Class146[] array) {
        try {
            if (b2 <= -118) {
                Label_0569: {
                    if (b) {
                        if (this.aClass246_Sub5_6439 == null) {
                            break Label_0569;
                        }
                        this.aClass246_Sub5_6439.method3126(Class215.anInt1614);
                        if (!client.aBoolean3553) {
                            break Label_0569;
                        }
                    }
                    final Class146 class112 = array[0];
                    final Class146 class113 = array[1];
                    final Class146 class114 = array[2];
                    if (class112 != null) {
                        class112.method2343(class111);
                        int n = 0;
                        int n2 = 0;
                        int n3 = 0;
                        int n4 = 0;
                        final Class87[] method2320 = class112.method2320();
                        final Class35[] method2321 = class112.method2322();
                        if (method2321 != null) {
                            ++n4;
                            n3 += method2321.length;
                        }
                        if (method2320 != null) {
                            ++n2;
                            n += method2320.length;
                        }
                        Class87[] method2322 = null;
                        Object[] method2323 = null;
                        if (class113 != null) {
                            class113.method2343(class111);
                            method2322 = class113.method2320();
                            method2323 = class113.method2322();
                        }
                        if (method2323 != null) {
                            n3 += ((Class35[])method2323).length;
                            ++n4;
                        }
                        if (method2322 != null) {
                            n += method2322.length;
                            ++n2;
                        }
                        Class87[] method2324 = null;
                        Object[] method2325 = null;
                        if (class114 != null) {
                            class114.method2343(class111);
                            method2324 = class114.method2320();
                            method2325 = class114.method2322();
                        }
                        if (method2325 != null) {
                            n3 += ((Class35[])method2325).length;
                            ++n4;
                        }
                        if (method2324 != null) {
                            ++n2;
                            n += method2324.length;
                        }
                        if ((this.aClass246_Sub5_6439 == null || this.aClass246_Sub5_6439.aBoolean5099) && (~n2 < -1 || ~n4 < -1)) {
                            this.aClass246_Sub5_6439 = Class246_Sub5.method3117(Class215.anInt1614, true);
                        }
                        if (this.aClass246_Sub5_6439 != null) {
                            Class87[] array2;
                            if (n2 == 1) {
                                if (method2322 == null) {
                                    if (method2324 != null) {
                                        array2 = method2324;
                                    }
                                    else {
                                        array2 = method2320;
                                    }
                                }
                                else {
                                    array2 = method2322;
                                }
                            }
                            else {
                                array2 = new Class87[n];
                                int n5 = 0;
                                if (method2320 != null) {
                                    Class236.method2892(method2320, 0, array2, n5, method2320.length);
                                    n5 += method2320.length;
                                }
                                if (method2322 != null) {
                                    Class236.method2892(method2322, 0, array2, n5, method2322.length);
                                    n5 += method2322.length;
                                }
                                if (method2324 != null) {
                                    Class236.method2892(method2324, 0, array2, n5, method2324.length);
                                }
                            }
                            Class35[] array3;
                            if (n4 != 1) {
                                array3 = new Class35[n3];
                                int n6 = 0;
                                if (method2321 != null) {
                                    Class236.method2892(method2321, 0, array3, n6, method2321.length);
                                    n6 += method2321.length;
                                }
                                if (method2323 != null) {
                                    Class236.method2892(method2323, 0, array3, n6, ((Class35[])method2323).length);
                                    n6 += ((Class35[])method2323).length;
                                }
                                if (method2325 != null) {
                                    Class236.method2892(method2325, 0, array3, n6, ((Class35[])method2325).length);
                                }
                            }
                            else if (method2323 != null) {
                                array3 = (Class35[])method2323;
                            }
                            else if (method2325 == null) {
                                array3 = method2321;
                            }
                            else {
                                array3 = (Class35[])method2325;
                            }
                            this.aClass246_Sub5_6439.method3120(ha, Class215.anInt1614, array2, array3, false);
                        }
                        this.aBoolean6440 = true;
                    }
                }
                if (this.aClass246_Sub5_6439 != null) {
                    this.aClass246_Sub5_6439.method3123(super.aByte5088, super.aShort6158, super.aShort6160, super.aShort6157, super.aShort6159);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.N(" + ((ha != null) ? "{...}" : "null") + ',' + b + ',' + b2 + ',' + ((class111 != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method3037(final int n, final boolean b, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            boolean b2 = true;
            boolean b3 = true;
            for (int n8 = 0; ~Class362.anInt3090 < ~n8; ++n8) {
                if (~this.anIntArray6375[n8] < ~n4) {
                    b2 = false;
                }
                else {
                    b3 = false;
                }
            }
            int n9 = -1;
            int anInt642 = -1;
            int anInt643 = 0;
            if (~n3 <= -1) {
                final Class86 method2194 = Class246_Sub3_Sub1.aClass121_6150.method2194(-62, n3);
                anInt642 = method2194.anInt642;
                anInt643 = method2194.anInt651;
            }
            if (!b) {
                if (b3) {
                    if (~anInt642 == 0x0) {
                        return;
                    }
                    n9 = 0;
                    int n10 = 0;
                    if (~anInt642 != -1) {
                        if (~anInt642 == 0xFFFFFFFE) {
                            n10 = this.anIntArray6425[0];
                        }
                    }
                    else {
                        n10 = this.anIntArray6375[0];
                    }
                    for (int n11 = 1; ~Class362.anInt3090 < ~n11; ++n11) {
                        if (~anInt642 == -1) {
                            if (~n10 < ~this.anIntArray6375[n11]) {
                                n9 = n11;
                                n10 = this.anIntArray6375[n11];
                            }
                        }
                        else if (~anInt642 == 0xFFFFFFFE && n10 > this.anIntArray6425[n11]) {
                            n9 = n11;
                            n10 = this.anIntArray6425[n11];
                        }
                    }
                    if (~anInt642 == 0xFFFFFFFE && ~n2 >= ~n10) {
                        return;
                    }
                }
                else {
                    if (b2) {
                        this.aByte6355 = 0;
                    }
                    for (int n12 = 0; ~n12 > ~Class362.anInt3090; ++n12) {
                        final byte aByte6355 = this.aByte6355;
                        this.aByte6355 = (byte)((this.aByte6355 + 1) % Class362.anInt3090);
                        if (~n4 <= ~this.anIntArray6375[aByte6355]) {
                            n9 = aByte6355;
                            break;
                        }
                    }
                }
                if (n9 >= 0) {
                    this.anIntArray6430[n9] = n3;
                    this.anIntArray6425[n9] = n2;
                    this.anIntArray6386[n9] = n7;
                    this.anIntArray6423[n9] = n5;
                    this.anIntArray6375[n9] = anInt643 + (n4 + n6);
                    this.anIntArray6397[n9] = n;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.V(" + n + ',' + b + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    @Override
    final int method2985(final boolean b) {
        try {
            if (b) {
                this.anInt6362 = -77;
            }
            return this.anInt6354;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.O(" + b + ')');
        }
    }
    
    final void method3038(final int[] array, final int[] array2, final boolean b) {
        try {
            if (!b) {
                this.anInt6352 = -44;
            }
            if (this.anIntArray6383 != null || array2 == null) {
                if (array2 == null) {
                    this.anIntArray6383 = null;
                    return;
                }
            }
            else {
                this.anIntArray6383 = new int[12];
            }
            for (int n = 0; this.anIntArray6383.length > n; ++n) {
                this.anIntArray6383[n] = -1;
            }
            for (int n2 = 0; ~n2 > ~array2.length; ++n2) {
                for (int n3 = array[n2], n4 = 0; ~this.anIntArray6383.length < ~n4; ++n4, n3 >>= 1) {
                    if (~(n3 & 0x1) != -1) {
                        this.anIntArray6383[n4] = array2[n2];
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.M(" + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    final int method2990(final int n) {
        try {
            if (n != 0) {
                return 30;
            }
            if (~this.anInt6352 == 0x7FFF) {
                return 0;
            }
            return this.anInt6352;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.J(" + n + ')');
        }
    }
    
    final Class294 method3039(final int n) {
        try {
            if (n != 1) {
                this.method3032(125, false, -5, 97, 58, -63);
            }
            final int method3030 = this.method3030((byte)70);
            if (method3030 != -1) {
                return Class370.aClass257_3136.method3199(false, method3030);
            }
            return Class64_Sub22.aClass294_3706;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.U(" + n + ')');
        }
    }
    
    final void method3040(final boolean b, final int anInt6388, final int n, final int n2, final int n3, final int anInt6389) {
        try {
            final int n4 = super.aShort6158 + super.aShort6160 >> -1012169567;
            final int n5 = super.aShort6157 + super.aShort6159 >> -988754175;
            final int n6 = Class284_Sub2_Sub2.anIntArray6200[n3];
            final int n7 = Class284_Sub2_Sub2.anIntArray6202[n3];
            final int n8 = -n / 2;
            final int n9 = -n2 / 2;
            final int n10 = n8 * n7 + n9 * n6 >> -1025229234;
            final int n11 = n7 * n9 - n6 * n8 >> -184895538;
            if (b) {
                this.method3039(126);
            }
            final int method664 = Class64_Sub27.method664(super.anInt5079 + n11, n5, 109, super.aByte5088, super.anInt5084 + n10, n4);
            final int n12 = n / 2;
            final int n13 = -n2 / 2;
            final int method665 = Class64_Sub27.method664(super.anInt5079 - -(n7 * n13 - n12 * n6 >> -1527044626), n5, 125, super.aByte5088, (n13 * n6 - -(n7 * n12) >> 755643662) + super.anInt5084, n4);
            final int n14 = -n / 2;
            final int n15 = n2 / 2;
            final int method666 = Class64_Sub27.method664((n15 * n7 - n6 * n14 >> -1452830226) + super.anInt5079, n5, 89, super.aByte5088, (n6 * n15 + n14 * n7 >> 726596686) + super.anInt5084, n4);
            final int n16 = n / 2;
            final int n17 = n2 / 2;
            final int method667 = Class64_Sub27.method664((-(n6 * n16) + n7 * n17 >> 352446958) + super.anInt5079, n5, 111, super.aByte5088, (n16 * n7 + n6 * n17 >> -26644050) + super.anInt5084, n4);
            final int n18 = (method665 > method664) ? method664 : method665;
            final int n19 = (~method666 <= ~method667) ? method667 : method666;
            final int n20 = (method667 <= method665) ? method667 : method665;
            final int n21 = (method666 > method664) ? method664 : method666;
            this.anInt6388 = (0x3FFF & (int)(2607.5945876176133 * Math.atan2(-n19 + n18, n2)));
            this.anInt6377 = (0x3FFF & (int)(Math.atan2(n21 + -n20, n) * 2607.5945876176133));
            if (~this.anInt6388 != -1 && ~anInt6388 != -1) {
                final int anInt6390 = 16384 - anInt6388;
                if (this.anInt6388 <= 8192) {
                    if (~this.anInt6388 < ~anInt6388) {
                        this.anInt6388 = anInt6388;
                    }
                }
                else if (this.anInt6388 < anInt6390) {
                    this.anInt6388 = anInt6390;
                }
            }
            if (this.anInt6377 != 0 && anInt6389 != 0) {
                final int anInt6391 = -anInt6389 + 16384;
                if (this.anInt6377 > 8192) {
                    if (anInt6391 > this.anInt6377) {
                        this.anInt6377 = anInt6391;
                    }
                }
                else if (this.anInt6377 > anInt6389) {
                    this.anInt6377 = anInt6389;
                }
            }
            this.anInt6416 = method664 - -method667;
            if (this.anInt6416 > method666 + method665) {
                this.anInt6416 = method665 - -method666;
            }
            this.anInt6416 = -super.anInt5089 + (this.anInt6416 >> 1518450497);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.C(" + b + ',' + anInt6388 + ',' + n + ',' + n2 + ',' + n3 + ',' + anInt6389 + ')');
        }
    }
    
    static final void method3041(final int n, final Class88 class88, final Class98_Sub22 class98_Sub22, final int n2) {
        try {
            final Class98_Sub19 class98_Sub23 = new Class98_Sub19();
            class98_Sub23.anInt3960 = class98_Sub22.readUnsignedByte((byte)5);
            class98_Sub23.anInt3954 = class98_Sub22.readInt(-2);
            class98_Sub23.aByteArrayArrayArray3958 = new byte[class98_Sub23.anInt3960][][];
            class98_Sub23.aClass143Array3962 = new Class143[class98_Sub23.anInt3960];
            class98_Sub23.anIntArray3957 = new int[class98_Sub23.anInt3960];
            class98_Sub23.anIntArray3953 = new int[class98_Sub23.anInt3960];
            class98_Sub23.aClass143Array3961 = new Class143[class98_Sub23.anInt3960];
            class98_Sub23.anIntArray3959 = new int[class98_Sub23.anInt3960];
            for (int n3 = n; ~class98_Sub23.anInt3960 < ~n3; ++n3) {
                try {
                    final int unsignedByte = class98_Sub22.readUnsignedByte((byte)35);
                    if (unsignedByte != 0 && ~unsignedByte != 0xFFFFFFFE && unsignedByte != 2) {
                        if (~unsignedByte == 0xFFFFFFFC || unsignedByte == 4) {
                            final String string = class98_Sub22.readString((byte)84);
                            final String string2 = class98_Sub22.readString((byte)84);
                            final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)(-108));
                            final String[] array = new String[unsignedByte2];
                            for (int n4 = 0; ~n4 > ~unsignedByte2; ++n4) {
                                array[n4] = class98_Sub22.readString((byte)84);
                            }
                            final byte[][] array2 = new byte[unsignedByte2][];
                            if (unsignedByte == 3) {
                                for (int i = 0; i < unsignedByte2; ++i) {
                                    final int int1 = class98_Sub22.readInt(-2);
                                    class98_Sub22.method1190(array2[i] = new byte[int1], true, int1, 0);
                                }
                            }
                            class98_Sub23.anIntArray3953[n3] = unsignedByte;
                            final Class[] array3 = new Class[unsignedByte2];
                            for (int n5 = 0; ~unsignedByte2 < ~n5; ++n5) {
                                array3[n5] = Class98_Sub10_Sub10.method1037(107, array[n5]);
                            }
                            class98_Sub23.aClass143Array3961[n3] = class88.method870(string2, Class98_Sub10_Sub10.method1037(-113, string), Class369.method3953(n, 0), array3);
                            class98_Sub23.aByteArrayArrayArray3958[n3] = array2;
                        }
                    }
                    else {
                        final String string3 = class98_Sub22.readString((byte)84);
                        final String string4 = class98_Sub22.readString((byte)84);
                        int int2 = 0;
                        if (~unsignedByte == 0xFFFFFFFE) {
                            int2 = class98_Sub22.readInt(n - 2);
                        }
                        class98_Sub23.anIntArray3953[n3] = unsignedByte;
                        class98_Sub23.anIntArray3959[n3] = int2;
                        class98_Sub23.aClass143Array3962[n3] = class88.method873(string4, Class98_Sub10_Sub10.method1037(-128, string3), -27303);
                    }
                }
                catch (ClassNotFoundException ex2) {
                    class98_Sub23.anIntArray3957[n3] = -1;
                }
                catch (SecurityException ex3) {
                    class98_Sub23.anIntArray3957[n3] = -2;
                }
                catch (NullPointerException ex4) {
                    class98_Sub23.anIntArray3957[n3] = -3;
                }
                catch (Exception ex5) {
                    class98_Sub23.anIntArray3957[n3] = -4;
                }
                catch (Throwable t) {
                    class98_Sub23.anIntArray3957[n3] = -5;
                }
            }
            Class186.aClass148_3428.method2419(class98_Sub23, -20911);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.D(" + n + ',' + ((class88 != null) ? "{...}" : "null") + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    @Override
    final boolean method2978(final int n) {
        try {
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.H(" + n + ')');
        }
    }
    
    public Class246_Sub3_Sub4_Sub2() {
        super(0, 0, 0, 0, 0, 0, 0, 0, 0, false, (byte)0);
        this.aBoolean6348 = false;
        this.anInt6353 = -1;
        this.anInt6358 = 0;
        this.anInt6361 = -1;
        this.anInt6366 = 0;
        this.aBoolean6359 = false;
        this.anInt6356 = 0;
        this.anInt6351 = 0;
        this.anInt6354 = 0;
        this.anInt6379 = -1;
        this.anInt6352 = -32768;
        this.anInt6385 = -1;
        this.anInt6367 = -1;
        this.aByte6355 = 0;
        this.anInt6380 = 0;
        this.aClass226Array6387 = new Class226[12];
        this.anInt6396 = 0;
        this.anInt6349 = -1;
        this.anIntArray6397 = new int[Class362.anInt3090];
        this.anInt6350 = 0;
        this.aString6374 = null;
        this.anIntArray6375 = new int[Class362.anInt3090];
        this.anInt6364 = -1;
        this.aBoolean6371 = true;
        this.anInt6406 = 0;
        this.anInt6403 = -1;
        this.anInt6405 = 0;
        this.anInt6376 = 0;
        this.anInt6410 = -1;
        this.anInt6414 = 256;
        this.anIntArray6373 = null;
        this.anInt6402 = 0;
        this.anInt6384 = 100;
        this.anIntArray6386 = new int[Class362.anInt3090];
        this.anInt6398 = 0;
        this.anInt6412 = -1000;
        this.anInt6409 = 0;
        this.anInt6419 = -1;
        this.anInt6413 = -1;
        this.anInt6418 = -1000;
        this.anInt6408 = 0;
        this.anIntArray6423 = new int[Class362.anInt3090];
        this.anInt6372 = 0;
        this.anInt6427 = 0;
        this.anInt6365 = -1;
        this.anInt6400 = 0;
        this.anInt6421 = -1;
        this.anIntArray6425 = new int[Class362.anInt3090];
        this.aByte6422 = 0;
        this.anInt6411 = -1;
        this.anInt6393 = 0;
        this.anInt6428 = 0;
        this.anInt6395 = 1;
        this.anIntArray6430 = new int[Class362.anInt3090];
        this.aClass325_6399 = new Class325();
        this.aClass325_6431 = new Class325();
        this.aClass325_6432 = new Class325();
        this.anInt6434 = 0;
        this.anIntArray6437 = new int[10];
        this.anIntArray6438 = new int[10];
        this.anInt6433 = 0;
        this.aClass146Array6441 = new Class146[3];
        this.anInt6435 = 0;
        this.anInt6436 = 0;
        this.aBoolean6442 = false;
        this.aBoolean6440 = false;
        this.aByteArray6443 = new byte[10];
    }
    
    final void method3042(final int n, final int n2) {
        try {
            if (~this.method3039(1).anInt2398 != -1 || ~this.anInt6414 != -1) {
                this.aClass325_6399.method3695(n2 + 452);
                final int n3 = -this.aClass325_6399.anInt2730 + n & 0x3FFF;
                if (~n3 < n2) {
                    this.anInt6415 = -16384 - (-n3 - this.aClass325_6399.anInt2730);
                }
                else {
                    this.anInt6415 = this.aClass325_6399.anInt2730 - -n3;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.P(" + n + ',' + n2 + ')');
        }
    }
    
    final boolean method3043(final int n, final int n2, final int n3) {
        try {
            if (this.anIntArray6370 == null) {
                if (n3 == -1) {
                    return true;
                }
                this.anIntArray6370 = new int[12];
                for (int n4 = 0; ~n4 > -13; ++n4) {
                    this.anIntArray6370[n4] = -1;
                }
            }
            final Class294 method3039 = this.method3039(1);
            if (n != 12) {
                this.method3045((byte)34, 119);
            }
            int n5 = 256;
            if (method3039.anIntArray2373 != null && method3039.anIntArray2373[n2] > 0) {
                n5 = method3039.anIntArray2373[n2];
            }
            if (n3 == -1) {
                if (this.anIntArray6370[n2] == -1) {
                    return true;
                }
                final int method3040 = this.aClass325_6399.method3698((byte)116);
                final int n6 = this.anIntArray6370[n2];
                final int n7 = -n6 + method3040;
                if (-n5 <= n7 && ~n5 <= ~n7) {
                    this.anIntArray6370[n2] = -1;
                    for (int i = 0; i < 12; ++i) {
                        if (~this.anIntArray6370[i] != 0x0) {
                            return true;
                        }
                    }
                    this.anIntArray6370 = null;
                    return true;
                }
                if ((n7 <= 0 || n7 > 8192) && ~n7 < 8191) {
                    this.anIntArray6370[n2] = Class202.method2702(16383, -n5 + n6);
                }
                else {
                    this.anIntArray6370[n2] = Class202.method2702(n5 + n6, 16383);
                }
                return false;
            }
            else {
                if (this.anIntArray6370[n2] == -1) {
                    this.anIntArray6370[n2] = this.aClass325_6399.method3698((byte)116);
                }
                final int n8 = this.anIntArray6370[n2];
                final int n9 = n3 + -n8;
                if (~n9 <= ~(-n5) && n9 <= n5) {
                    this.anIntArray6370[n2] = n3;
                    return true;
                }
                if ((~n9 >= -1 || ~n9 < -8193) && n9 > -8192) {
                    this.anIntArray6370[n2] = Class202.method2702(16383, n8 - n5);
                }
                else {
                    this.anIntArray6370[n2] = Class202.method2702(n5 + n8, 16383);
                }
                return false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.K(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    int method3044(final boolean b) {
        try {
            final Class294 method3039 = this.method3039(1);
            if (method3039.anInt2385 != -1) {
                return method3039.anInt2385;
            }
            if (this.anInt6352 == -32768) {
                return 200;
            }
            if (b) {
                return 122;
            }
            return -this.anInt6352;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.B(" + b + ')');
        }
    }
    
    final void method3045(final byte b, final int anInt6395) {
        try {
            if (b > 66) {
                this.anInt6395 = anInt6395;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.E(" + b + ',' + anInt6395 + ')');
        }
    }
    
    final void method3046(final int n, final Class146 class146) {
        try {
            if (n != 758) {
                this.anInt6384 = -63;
            }
            final int anInt2730 = this.aClass325_6431.anInt2730;
            final int anInt2731 = this.aClass325_6432.anInt2730;
            if (~anInt2730 != -1 || ~anInt2731 != -1) {
                final int n2 = class146.fa() / 2;
                class146.H(0, -n2, 0);
                class146.VA(anInt2730 & 0x3FFF);
                class146.FA(anInt2731 & 0x3FFF);
                class146.H(0, n2, 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.Q(" + n + ',' + ((class146 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method3047(final int n, final boolean b, final int n2) {
        try {
            final Class294 method3039 = this.method3039(1);
            if (b || ~method3039.anInt2398 != -1 || ~this.anInt6414 != -1) {
                if (n2 < 9) {
                    this.anInt6391 = 107;
                }
                this.anInt6415 = (n & 0x3FFF);
                this.aClass325_6399.method3697(true, this.anInt6415);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.W(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    @Override
    protected final void finalize() {
        try {
            if (this.aClass246_Sub5_6439 != null) {
                this.aClass246_Sub5_6439.method3114();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oi.finalize()");
        }
    }
}
