// 
// Decompiled by Procyon v0.5.30
// 

final class Class246_Sub3_Sub4_Sub4 extends Class246_Sub3_Sub4
{
    private Class97 aClass97_6459;
    private int anInt6460;
    private int anInt6461;
    private double aDouble6462;
    int anInt6463;
    private double aDouble6464;
    private int anInt6465;
    int anInt6466;
    private int anInt6467;
    private int anInt6468;
    private int anInt6469;
    private int anInt6470;
    private boolean aBoolean6471;
    private double aDouble6472;
    private boolean aBoolean6473;
    private int anInt6474;
    private int anInt6475;
    private double aDouble6476;
    private int anInt6477;
    private double aDouble6478;
    int anInt6479;
    private double aDouble6480;
    private boolean aBoolean6481;
    int anInt6482;
    private double aDouble6483;
    private int anInt6484;
    private int anInt6485;
    private int anInt6486;
    static IncomingOpcode aClass58_6487;
    static int anInt6488;
    private Class246_Sub5 aClass246_Sub5_6489;
    private double aDouble6490;
    
    @Override
    final void method2988(final ha ha, final int n) {
        try {
            final Class146 method3081 = this.method3081((byte)(-96), 0, ha);
            if (method3081 != null) {
                final Class111 method3082 = ha.method1793();
                method3082.method2107(this.anInt6474);
                method3082.method2097(this.anInt6468);
                method3082.method2106((int)this.aDouble6472, (int)this.aDouble6490, (int)this.aDouble6462);
                this.anInt6484 = method3081.fa();
                this.anInt6460 = method3081.ma();
                this.method3076(method3082, ha, method3081, (byte)74);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.MA(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final Class228 method2974(final byte b, final ha ha) {
        try {
            if (b != -53) {
                this.method3074(-62, -24, -14, (byte)(-98), -63);
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.KA(" + b + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method2992(final byte b) {
        try {
            if (b != -73) {
                this.anInt6463 = -41;
            }
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.DA(" + b + ')');
        }
    }
    
    @Override
    final int method2990(final int n) {
        try {
            if (n != 0) {
                this.method2988(null, 60);
            }
            return this.anInt6484;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.J(" + n + ')');
        }
    }
    
    final void method3074(final int n, final int n2, final int n3, final byte b, final int n4) {
        try {
            if (!this.aBoolean6481) {
                final double n5 = -super.anInt5084 + n;
                final double n6 = n4 + -super.anInt5079;
                final double sqrt = Math.sqrt(n5 * n5 + n6 * n6);
                this.aDouble6462 = super.anInt5079 + this.anInt6467 * n6 / sqrt;
                this.aDouble6472 = super.anInt5084 + n5 * this.anInt6467 / sqrt;
                if (this.aBoolean6473) {
                    this.aDouble6490 = Class98_Sub46_Sub2_Sub2.method1538(super.aByte5088, (int)this.aDouble6462, (int)this.aDouble6472, 24111) - this.anInt6461;
                }
                else {
                    this.aDouble6490 = super.anInt5089;
                }
            }
            final double n7 = -n3 + 1 + this.anInt6466;
            this.aDouble6483 = (n4 - this.aDouble6462) / n7;
            this.aDouble6464 = (-this.aDouble6472 + n) / n7;
            this.aDouble6478 = Math.sqrt(this.aDouble6483 * this.aDouble6483 + this.aDouble6464 * this.aDouble6464);
            if (this.anInt6475 == -1) {
                this.aDouble6480 = (n2 - this.aDouble6490) / n7;
            }
            else {
                if (!this.aBoolean6481) {
                    this.aDouble6480 = -this.aDouble6478 * Math.tan(this.anInt6475 * 0.02454369);
                }
                this.aDouble6476 = 2.0 * (n2 - this.aDouble6490 - this.aDouble6480 * n7) / (n7 * n7);
            }
            if (b != 108) {
                this.anInt6475 = 74;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.NA(" + n + ',' + n2 + ',' + n3 + ',' + b + ',' + n4 + ')');
        }
    }
    
    @Override
    final int method2985(final boolean b) {
        try {
            if (b) {
                this.method2976(115, null, (byte)(-18), -25);
            }
            return this.anInt6460;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.O(" + b + ')');
        }
    }
    
    final void method3075(final int n, final int n2) {
        try {
            this.aDouble6462 += n2 * this.aDouble6483;
            this.aDouble6472 += this.aDouble6464 * n2;
            this.aBoolean6481 = true;
            if (!this.aBoolean6473) {
                if (~this.anInt6475 != 0x0) {
                    this.aDouble6490 += n2 * (n2 * (this.aDouble6476 * 0.5)) + n2 * this.aDouble6480;
                    this.aDouble6480 += n2 * this.aDouble6476;
                }
                else {
                    this.aDouble6490 += this.aDouble6480 * n2;
                }
            }
            else {
                this.aDouble6490 = Class98_Sub46_Sub2_Sub2.method1538(super.aByte5088, (int)this.aDouble6462, (int)this.aDouble6472, n + 34573) + -this.anInt6461;
            }
            if (n == -10462) {
                this.anInt6468 = (8192 + (int)(2607.5945876176133 * Math.atan2(this.aDouble6464, this.aDouble6483)) & 0x3FFF);
                this.anInt6474 = ((int)(2607.5945876176133 * Math.atan2(this.aDouble6480, this.aDouble6478)) & 0x3FFF);
                if (this.aClass97_6459 != null) {
                    this.anInt6477 += n2;
                    while (~this.aClass97_6459.anIntArray811[this.anInt6465] > ~this.anInt6477) {
                        this.anInt6477 -= this.aClass97_6459.anIntArray811[this.anInt6465];
                        ++this.anInt6465;
                        if (this.anInt6465 >= this.aClass97_6459.anIntArray818.length) {
                            this.anInt6465 -= this.aClass97_6459.anInt828;
                            if (this.anInt6465 < 0 || this.anInt6465 >= this.aClass97_6459.anIntArray818.length) {
                                this.anInt6465 = 0;
                            }
                        }
                        this.anInt6469 = this.anInt6465 + 1;
                        if (this.aClass97_6459.anIntArray818.length <= this.anInt6469) {
                            this.anInt6469 -= this.aClass97_6459.anInt828;
                            if (this.anInt6469 >= 0 && this.anInt6469 < this.aClass97_6459.anIntArray818.length) {
                                continue;
                            }
                            this.anInt6469 = -1;
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.VA(" + n + ',' + n2 + ')');
        }
    }
    
    private final void method3076(final Class111 class111, final ha ha, final Class146 class112, final byte b) {
        try {
            class112.method2343(class111);
            final Class87[] method2320 = class112.method2320();
            final Class35[] method2321 = class112.method2322();
            if ((this.aClass246_Sub5_6489 == null || this.aClass246_Sub5_6489.aBoolean5099) && (method2320 != null || method2321 != null)) {
                this.aClass246_Sub5_6489 = Class246_Sub5.method3117(Class215.anInt1614, true);
            }
            if (this.aClass246_Sub5_6489 != null) {
                this.aClass246_Sub5_6489.method3120(ha, Class215.anInt1614, method2320, method2321, false);
                this.aClass246_Sub5_6489.method3123(super.aByte5088, super.aShort6158, super.aShort6160, super.aShort6157, super.aShort6159);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.RA(" + ((class111 != null) ? "{...}" : "null") + ',' + ((ha != null) ? "{...}" : "null") + ',' + ((class112 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public static void method3077(final byte b) {
        try {
            Class246_Sub3_Sub4_Sub4.aClass58_6487 = null;
            if (b != -29) {
                method3077((byte)73);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.SA(" + b + ')');
        }
    }
    
    final void method3078(final int n) {
        try {
            if (n != -6095) {
                method3077((byte)(-55));
            }
            if (this.aClass246_Sub5_6489 != null) {
                this.aClass246_Sub5_6489.method3114();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.PA(" + n + ')');
        }
    }
    
    static final void method3079(final byte b, final Class207 aClass207_3644, final int n, final Class207 aClass207_3645) {
        try {
            Class64_Sub15.aClass207_3679 = aClass207_3645;
            Class64_Sub2.aClass207_3644 = aClass207_3644;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.JA(" + b + ',' + ((aClass207_3644 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_3645 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final boolean method2982(final byte b) {
        try {
            if (b > -70) {
                this.method3078(-85);
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.HA(" + b + ')');
        }
    }
    
    @Override
    final boolean method2978(final int n) {
        try {
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.H(" + n + ')');
        }
    }
    
    Class246_Sub3_Sub4_Sub4(final int anInt6485, final int n, final int n2, final int n3, final int n4, final int anInt6486, final int anInt6487, final int anInt6488, final int anInt6489, final int anInt6490, final int anInt6491, final int anInt6492, final int anInt6493, final boolean aBoolean6473, final int anInt6494) {
        super(n, n2, n3, -anInt6486 + Class98_Sub46_Sub2_Sub2.method1538(n, n4, n3, 24111), n4, n3 >> 1293743369, n3 >> -108544983, n4 >> -860505271, n4 >> 1727487849, false, (byte)0);
        this.anInt6465 = 0;
        this.aBoolean6471 = false;
        this.anInt6460 = 0;
        this.anInt6469 = -1;
        this.aBoolean6481 = false;
        this.anInt6477 = 0;
        this.anInt6484 = 0;
        try {
            this.anInt6461 = anInt6486;
            this.anInt6475 = anInt6489;
            this.anInt6467 = anInt6490;
            this.anInt6485 = anInt6485;
            this.aBoolean6481 = false;
            this.anInt6463 = anInt6493;
            this.anInt6479 = anInt6487;
            this.anInt6482 = anInt6492;
            this.anInt6466 = anInt6488;
            this.anInt6470 = anInt6491;
            this.aBoolean6473 = aBoolean6473;
            this.anInt6486 = anInt6494;
            final int anInt6495 = Class196.aClass304_1509.method3564(2, this.anInt6485).anInt910;
            if (anInt6495 != -1) {
                this.aClass97_6459 = Class151_Sub7.aClass183_5001.method2623(anInt6495, 16383);
            }
            else {
                this.aClass97_6459 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.<init>(" + anInt6485 + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + anInt6486 + ',' + anInt6487 + ',' + anInt6488 + ',' + anInt6489 + ',' + anInt6490 + ',' + anInt6491 + ',' + anInt6492 + ',' + anInt6493 + ',' + aBoolean6473 + ',' + anInt6494 + ')');
        }
    }
    
    @Override
    final boolean method2976(final int n, final ha ha, final byte b, final int n2) {
        try {
            if (b < 59) {
                this.aBoolean6471 = false;
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.TA(" + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + b + ',' + n2 + ')');
        }
    }
    
    final void method3080(final byte b) {
        try {
            if (!this.aBoolean6481) {
                if (b < 72) {
                    this.anInt6485 = -118;
                }
                if (~this.anInt6470 != -1) {
                    Class246_Sub3_Sub4_Sub2 class246_Sub3_Sub4_Sub2 = null;
                    if (this.anInt6470 < 0) {
                        final int n = -this.anInt6470 - 1;
                        if (~za_Sub2.anInt6080 == ~n) {
                            class246_Sub3_Sub4_Sub2 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660;
                        }
                        else {
                            class246_Sub3_Sub4_Sub2 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[n];
                        }
                    }
                    else {
                        final Class98_Sub39 class98_Sub39 = (Class98_Sub39)Class260.aClass377_3254.method3990(-1 + this.anInt6470, -1);
                        if (class98_Sub39 != null) {
                            class246_Sub3_Sub4_Sub2 = class98_Sub39.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                        }
                    }
                    if (class246_Sub3_Sub4_Sub2 != null) {
                        super.anInt5084 = class246_Sub3_Sub4_Sub2.anInt5084;
                        super.anInt5079 = class246_Sub3_Sub4_Sub2.anInt5079;
                        super.anInt5089 = Class98_Sub46_Sub2_Sub2.method1538(super.aByte5088, class246_Sub3_Sub4_Sub2.anInt5079, class246_Sub3_Sub4_Sub2.anInt5084, 24111) - this.anInt6461;
                        if (this.anInt6486 >= 0) {
                            final Class294 method3039 = class246_Sub3_Sub4_Sub2.method3039(1);
                            int n2 = 0;
                            int n3 = 0;
                            if (method3039.anIntArrayArray2366 != null && method3039.anIntArrayArray2366[this.anInt6486] != null) {
                                n2 += method3039.anIntArrayArray2366[this.anInt6486][0];
                                n3 += method3039.anIntArrayArray2366[this.anInt6486][2];
                            }
                            if (method3039.anIntArrayArray2364 != null && method3039.anIntArrayArray2364[this.anInt6486] != null) {
                                n3 += method3039.anIntArrayArray2364[this.anInt6486][2];
                                n2 += method3039.anIntArrayArray2364[this.anInt6486][0];
                            }
                            if (~n2 != -1 || n3 != 0) {
                                int method3040 = class246_Sub3_Sub4_Sub2.aClass325_6399.method3698((byte)116);
                                if (class246_Sub3_Sub4_Sub2.anIntArray6370 != null && class246_Sub3_Sub4_Sub2.anIntArray6370[this.anInt6486] != -1) {
                                    method3040 = class246_Sub3_Sub4_Sub2.anIntArray6370[this.anInt6486];
                                }
                                final int n4 = 0x3FFF & -method3040 + method3040;
                                final int n5 = Class284_Sub2_Sub2.anIntArray6200[n4];
                                final int n6 = Class284_Sub2_Sub2.anIntArray6202[n4];
                                final int n7 = n3 * n5 + n6 * n2 >> 1205179214;
                                final int n8 = n6 * n3 + -(n5 * n2) >> -1374485746;
                                super.anInt5084 += n7;
                                super.anInt5079 += n8;
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.OA(" + b + ')');
        }
    }
    
    private final Class146 method3081(final byte b, final int n, final ha ha) {
        try {
            if (b != -96) {
                this.method2981(null, (byte)(-125), false, -13, null, 78, -34);
            }
            return Class196.aClass304_1509.method3564(2, this.anInt6485).method1728(this.anInt6465, Class151_Sub7.aClass183_5001, n, this.anInt6477, (byte)(-94), this.anInt6469, ha);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.LA(" + b + ',' + n + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final boolean method2987(final int n) {
        try {
            if (n != 6540) {
                this.anInt6470 = 62;
            }
            return this.aBoolean6471;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.I(" + n + ')');
        }
    }
    
    static final int method3082(final int n, final int n2, final int n3) {
        try {
            int n4 = (int)((-128 + Class98_Sub5_Sub3.method971(45365 + n3, 91923 + n, n2, n2 - 91) - (-(Class98_Sub5_Sub3.method971(n3 + 10294, 37821 + n, 2, n2 - 98) - 128 >> 1334886049) - (Class98_Sub5_Sub3.method971(n3, n, 1, -48) - 128 >> -872292894))) * 0.3) + 35;
            if (~n4 <= -11) {
                if (~n4 >= -61) {
                    return n4;
                }
                n4 = 60;
                if (!client.aBoolean3553) {
                    return n4;
                }
            }
            n4 = 10;
            return n4;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.UA(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method3022(final int n) {
        try {
            final short n2 = (short)(this.aDouble6462 / 512.0);
            super.aShort6159 = n2;
            super.aShort6157 = n2;
            if (n != -8675) {
                this.aDouble6480 = 0.42551889851769525;
            }
            final short n3 = (short)(this.aDouble6472 / 512.0);
            super.aShort6160 = n3;
            super.aShort6158 = n3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.F(" + n + ')');
        }
    }
    
    @Override
    final void method2981(final Class246_Sub3 class246_Sub3, final byte b, final boolean b2, final int n, final ha ha, final int n2, final int n3) {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.CA(" + ((class246_Sub3 != null) ? "{...}" : "null") + ',' + b + ',' + b2 + ',' + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    protected final void finalize() {
        try {
            if (this.aClass246_Sub5_6489 != null) {
                this.aClass246_Sub5_6489.method3114();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.finalize()");
        }
    }
    
    @Override
    final Class246_Sub1 method2975(final ha ha, final int n) {
        try {
            final Class146 method3081 = this.method3081((byte)(-96), 2048, ha);
            if (method3081 == null) {
                return null;
            }
            final Class111 method3082 = ha.method1793();
            method3082.method2107(this.anInt6474);
            method3082.method2097(this.anInt6468);
            method3082.method2106((int)this.aDouble6472, (int)this.aDouble6490, (int)this.aDouble6462);
            this.method3076(method3082, ha, method3081, (byte)(-74));
            final Class246_Sub1 method3083 = Class94.method915(1, (byte)(-47), false);
            if (!Class239.aBoolean1839) {
                method3081.method2325(method3082, method3083.aClass246_Sub6Array5067[0], 0);
            }
            else {
                method3081.method2329(method3082, method3083.aClass246_Sub6Array5067[0], Class16.anInt197, 0);
            }
            if (this.aClass246_Sub5_6489 != null) {
                final Class242 method3084 = this.aClass246_Sub5_6489.method3116();
                if (!Class239.aBoolean1839) {
                    ha.method1820(method3084);
                }
                else {
                    ha.method1785(method3084, Class16.anInt197);
                }
            }
            if (n > -12) {
                this.aDouble6476 = 0.3831954713971479;
            }
            this.aBoolean6471 = method3081.F();
            this.anInt6484 = method3081.fa();
            this.anInt6460 = method3081.ma();
            return method3083;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rk.QA(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static {
        Class246_Sub3_Sub4_Sub4.aClass58_6487 = new IncomingOpcode(112, 6);
    }
}
