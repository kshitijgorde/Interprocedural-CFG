// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class373 implements Interface18
{
    private int anInt3472;
    Class207 aClass207_3473;
    private Class43 aClass43_3474;
    private long aLong3475;
    private Class207 aClass207_3476;
    static int anInt3477;
    Class93 aClass93_3478;
    static int[] anIntArray3479;
    
    @Override
    public boolean method59(final int n) {
        try {
            if (n != 14017) {
                return false;
            }
            boolean b = true;
            if (!this.aClass207_3473.method2742(n - 14068, this.aClass93_3478.anInt3506)) {
                b = false;
            }
            if (!this.aClass207_3476.method2742(-112, this.aClass93_3478.anInt3506)) {
                b = false;
            }
            return b;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wl.A(" + n + ')');
        }
    }
    
    abstract void method3960(final int p0, final int p1, final byte p2, final boolean p3);
    
    public static void method3961(final int n) {
        try {
            Class373.anIntArray3479 = null;
            if (n != -3771) {
                Class373.anIntArray3479 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wl.K(" + n + ')');
        }
    }
    
    static final void method3962(final byte b) {
        try {
            if (b != 0) {
                Class373.anIntArray3479 = null;
            }
            Class123.aClass79_1010.method794(125);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wl.M(" + b + ')');
        }
    }
    
    @Override
    public final void method60(final boolean b, final byte b2) {
        try {
            final int n = this.aClass93_3478.aClass63_3509.method545(Class98_Sub17_Sub1.anInt5782, this.aClass93_3478.anInt3514, (byte)124) - -this.aClass93_3478.anInt3505;
            final int n2 = this.aClass93_3478.aClass110_3511.method2088(this.aClass93_3478.anInt3504, Class246_Sub2.anInt5072, (byte)(-56)) - -this.aClass93_3478.anInt3507;
            this.method3960(n2, n, (byte)(-36), b);
            if (b2 >= -81) {
                this.method3960(8, 30, (byte)(-95), true);
            }
            this.method3965(2, n2, n, b);
            String s = Class140.aClass47_3241.method443((byte)(-46));
            if (Class343.method3819(-47) - this.aLong3475 > 10000L) {
                s = s + " (" + Class140.aClass47_3241.method442((byte)54).method736((byte)(-10)) + ")";
            }
            this.aClass43_3474.method415(this.aClass93_3478.anInt3513, s, n + this.aClass93_3478.anInt3514 / 2, -1, (byte)(-98), 4 + (n2 - -(this.aClass93_3478.anInt3504 / 2) + this.aClass93_3478.anInt3508));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wl.B(" + b + ',' + b2 + ')');
        }
    }
    
    final int method3963(final int n) {
        try {
            final int method440 = Class140.aClass47_3241.method440(-125);
            int n2 = method440 * n;
            if (~method440 != ~this.anInt3472 || ~method440 == -1) {
                this.anInt3472 = method440;
                this.aLong3475 = Class343.method3819(n ^ 0xFFFFFFB5);
            }
            else {
                final int method441 = Class140.aClass47_3241.method447(6119);
                if (~method441 < ~method440) {
                    final long n3 = this.aLong3475 + -Class140.aClass47_3241.method445((byte)(-5));
                    if (n3 > 0L) {
                        final long n4 = (method441 - method440) * (10000L * n3 / method440);
                        final long n5 = 10000L * (-this.aLong3475 + Class343.method3819(n - 147));
                        if (~n5 > ~n4) {
                            n2 = (int)(100L * (method441 + -method440) * n5 / n4 - -(100 * method440));
                        }
                        else {
                            n2 = method441 * 100;
                        }
                    }
                }
            }
            return n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wl.L(" + n + ')');
        }
    }
    
    @Override
    public void method58(final byte b) {
        try {
            if (b != -43) {
                this.method3960(-19, 49, (byte)123, false);
            }
            this.aClass43_3474 = Class265.aHa1974.method1804(Class119_Sub1.method2182(this.aClass207_3476, true, this.aClass93_3478.anInt3506), Class324.method3684(this.aClass207_3473, this.aClass93_3478.anInt3506), true);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wl.C(" + b + ')');
        }
    }
    
    static final void method3964(final Class98_Sub22_Sub1 class98_Sub22_Sub1, final int n) {
        try {
            class98_Sub22_Sub1.method1256(0);
            final int anInt6080 = za_Sub2.anInt6080;
            final Class246_Sub3_Sub4_Sub2_Sub2[] aClass246_Sub3_Sub4_Sub2_Sub2Array5030 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030;
            final int n2 = anInt6080;
            final Class246_Sub3_Sub4_Sub2_Sub2 aClass246_Sub3_Sub4_Sub2_Sub2_660 = new Class246_Sub3_Sub4_Sub2_Sub2();
            aClass246_Sub3_Sub4_Sub2_Sub2Array5030[n2] = aClass246_Sub3_Sub4_Sub2_Sub2_660;
            Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660 = aClass246_Sub3_Sub4_Sub2_Sub2_660;
            final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2 = aClass246_Sub3_Sub4_Sub2_Sub2_660;
            class246_Sub3_Sub4_Sub2_Sub2.anInt6369 = anInt6080;
            final int bits = class98_Sub22_Sub1.readBits((byte)(-108), 30);
            final byte b = (byte)(bits >> -638775876);
            class246_Sub3_Sub4_Sub2_Sub2.anIntArray6437[0] = ((0xFFFF6B1 & bits) >> -190559890) - Class272.anInt2038;
            final int n3 = 0x3FFF & bits;
            class246_Sub3_Sub4_Sub2_Sub2.anInt5084 = (class246_Sub3_Sub4_Sub2_Sub2.anIntArray6437[0] << 152480009) - -(class246_Sub3_Sub4_Sub2_Sub2.method3034(n ^ n) << 15811816);
            class246_Sub3_Sub4_Sub2_Sub2.anIntArray6438[0] = -aa_Sub2.anInt3562 + n3;
            class246_Sub3_Sub4_Sub2_Sub2.anInt5079 = (class246_Sub3_Sub4_Sub2_Sub2.anIntArray6438[0] << 652406601) + (class246_Sub3_Sub4_Sub2_Sub2.method3034(0) << 2125038824);
            final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub3 = class246_Sub3_Sub4_Sub2_Sub2;
            final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub4 = class246_Sub3_Sub4_Sub2_Sub2;
            final byte anInt6081 = b;
            class246_Sub3_Sub4_Sub2_Sub4.aByte5081 = anInt6081;
            class246_Sub3_Sub4_Sub2_Sub3.aByte5088 = anInt6081;
            Class43.anInt377 = anInt6081;
            if (Class1.method162(class246_Sub3_Sub4_Sub2_Sub2.anIntArray6438[0], (byte)(-117), class246_Sub3_Sub4_Sub2_Sub2.anIntArray6437[0])) {
                final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub5 = class246_Sub3_Sub4_Sub2_Sub2;
                ++class246_Sub3_Sub4_Sub2_Sub5.aByte5081;
            }
            if (Class224_Sub3_Sub1.aClass98_Sub22Array6146[anInt6080] != null) {
                class246_Sub3_Sub4_Sub2_Sub2.method3062(Class224_Sub3_Sub1.aClass98_Sub22Array6146[anInt6080], (byte)73);
            }
            Class2.anInt71 = 0;
            Class319.anIntArray2705[Class2.anInt71++] = anInt6080;
            Class98_Sub27.aByteArray4075[anInt6080] = 0;
            Class373_Sub2.anInt5473 = 0;
            for (int n4 = 1; ~n4 > -2049; ++n4) {
                if (~n4 != ~anInt6080) {
                    final int bits2 = class98_Sub22_Sub1.readBits((byte)(-20), 18);
                    final int n5 = bits2 >> 1414608112;
                    final int n6 = (bits2 & 0xFFE4) >> 251705128;
                    final int n7 = bits2 & 0xFF;
                    final Class376[] aClass376Array2562 = Class306.aClass376Array2562;
                    final int n8 = n4;
                    final Class376 class376 = new Class376();
                    aClass376Array2562[n8] = class376;
                    final Class376 class377 = class376;
                    class377.anInt3176 = (n5 << -148957092) - (-(n6 << -430930482) - n7);
                    class377.aBoolean3175 = false;
                    class377.anInt3177 = -1;
                    class377.anInt3172 = 0;
                    Class76_Sub9.anIntArray3791[Class373_Sub2.anInt5473++] = n4;
                    Class98_Sub27.aByteArray4075[n4] = 0;
                }
            }
            class98_Sub22_Sub1.method1254((byte)120);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wl.J(" + ((class98_Sub22_Sub1 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    abstract void method3965(final int p0, final int p1, final int p2, final boolean p3);
    
    Class373(final Class207 aClass207_3473, final Class207 aClass207_3474, final Class93 aClass93_3478) {
        try {
            this.aClass207_3473 = aClass207_3473;
            this.aClass207_3476 = aClass207_3474;
            this.aClass93_3478 = aClass93_3478;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wl.<init>(" + ((aClass207_3473 != null) ? "{...}" : "null") + ',' + ((aClass207_3474 != null) ? "{...}" : "null") + ',' + ((aClass93_3478 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class373.anInt3477 = Class48_Sub2_Sub1.method474(1600, (byte)31);
        Class373.anIntArray3479 = new int[] { 16, 32, 64, 128 };
    }
}
