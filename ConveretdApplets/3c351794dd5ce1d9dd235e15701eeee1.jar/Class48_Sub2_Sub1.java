// 
// Decompiled by Procyon v0.5.30
// 

final class Class48_Sub2_Sub1 extends Class48_Sub2
{
    private ha_Sub3 aHa_Sub3_5521;
    static float[][] aFloatArrayArray5522;
    private int anInt5523;
    private int anInt5524;
    static int anInt5525;
    private int anInt5526;
    private int anInt5527;
    private int anInt5528;
    private Interface4_Impl3 anInterface4_Impl3_5529;
    private int anInt5530;
    static Class265 aClass265_5531;
    static int anInt5532;
    
    static final void method471(final int n, final byte b, final int n2, final int n3, final int n4) {
        try {
            if (n4 < n3) {
                for (int n5 = n4; ~n3 < ~n5; ++n5) {
                    Class97.anIntArrayArray814[n5][n] = n2;
                }
            }
            else {
                for (int n6 = n3; ~n4 < ~n6; ++n6) {
                    Class97.anIntArrayArray814[n6][n] = n2;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "efa.D(" + n + ',' + b + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    static final Class228 method472(final int n, final int n2, final Class146 class146, final int n3, final int n4) {
        try {
            if (class146 == null) {
                return null;
            }
            if (n4 != 4) {
                Class48_Sub2_Sub1.anInt5525 = -48;
            }
            return new Class228(n2, n, n3, class146.na(), class146.V(), class146.RA(), class146.fa(), class146.EA(), class146.HA(), class146.G());
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "efa.F(" + n + ',' + n2 + ',' + ((class146 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    static final void method473(final int n, final int n2, final int n3, int n4, final int n5, final int n6, final int n7, final int n8) {
        try {
            int n9 = 0;
            int n10 = 0;
            final int n11 = n6 + -n2;
            final int n12 = n4 + -n2;
            final int n13 = n6 * n6;
            final int n14 = n4 * n4;
            final int n15 = n11 * n11;
            final int n16 = n12 * n12;
            final int n17 = n14 << 1121913825;
            final int n18 = n13 << 2023203873;
            final int n19 = n16 << -1633987743;
            final int n20 = n15 << -1996017919;
            final int n21 = n4 << 2038291265;
            final int n22 = n12 << -951242911;
            int i = n13 * (-n21 + 1) - -n17;
            int n23 = n14 - n18 * (-1 + n21);
            int n24 = n19 + n15 * (-n22 + 1);
            int n25 = n16 - (-1 + n22) * n20;
            if (n7 != -25682) {
                Class48_Sub2_Sub1.anInt5525 = -99;
            }
            final int n26 = n13 << 1187177570;
            final int n27 = n14 << 1493540962;
            final int n28 = n15 << -1623543038;
            final int n29 = n16 << -1092116446;
            int n30 = n17 * 3;
            int n31 = (-3 + n21) * n18;
            int n32 = 3 * n19;
            int n33 = n20 * (n22 - 3);
            int n34 = n27;
            int n35 = (-1 + n4) * n26;
            int n36 = n29;
            if (~Class98_Sub10_Sub38.anInt5753 >= ~n && ~Class218.anInt1635 <= ~n) {
                final int[] array = Class97.anIntArrayArray814[n];
                final int method3219 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, -n6 + n3);
                final int method3220 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n3 + n6);
                final int method3221 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n3 - n11);
                final int method3222 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n3 + n11);
                Class333.method3761(n5, array, method3219, method3221, (byte)24);
                Class333.method3761(n8, array, method3221, method3222, (byte)(-128));
                Class333.method3761(n5, array, method3222, method3220, (byte)(-128));
            }
            int n37 = n28 * (-1 + n12);
            while (~n4 < -1) {
                final boolean b = n12 >= n4;
                if (i < 0) {
                    while (i < 0) {
                        i += n30;
                        n23 += n34;
                        n34 += n27;
                        n30 += n27;
                        ++n9;
                    }
                }
                if (b) {
                    if (n24 < 0) {
                        while (~n24 > -1) {
                            n25 += n36;
                            n24 += n32;
                            ++n10;
                            n32 += n29;
                            n36 += n29;
                        }
                    }
                    if (~n25 > -1) {
                        n24 += n32;
                        n25 += n36;
                        n32 += n29;
                        n36 += n29;
                        ++n10;
                    }
                    n24 += -n37;
                    n25 += -n33;
                    n37 -= n28;
                    n33 -= n28;
                }
                if (~n23 > -1) {
                    i += n30;
                    n23 += n34;
                    n34 += n27;
                    ++n9;
                    n30 += n27;
                }
                i += -n35;
                n23 += -n31;
                n35 -= n26;
                n31 -= n26;
                final int n38 = --n4 + n;
                final int n39 = n4 + n;
                if (~n39 <= ~Class98_Sub10_Sub38.anInt5753 && ~Class218.anInt1635 <= ~n38) {
                    final int method3223 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n3 + n9);
                    final int method3224 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, -n9 + n3);
                    if (b) {
                        final int method3225 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n3 + n10);
                        final int method3226 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n3 + -n10);
                        if (~Class98_Sub10_Sub38.anInt5753 >= ~n38) {
                            final int[] array2 = Class97.anIntArrayArray814[n38];
                            Class333.method3761(n5, array2, method3224, method3226, (byte)(-125));
                            Class333.method3761(n8, array2, method3226, method3225, (byte)(-123));
                            Class333.method3761(n5, array2, method3225, method3223, (byte)47);
                        }
                        if (~Class218.anInt1635 > ~n39) {
                            continue;
                        }
                        final int[] array3 = Class97.anIntArrayArray814[n39];
                        Class333.method3761(n5, array3, method3224, method3226, (byte)40);
                        Class333.method3761(n8, array3, method3226, method3225, (byte)(-127));
                        Class333.method3761(n5, array3, method3225, method3223, (byte)(-123));
                    }
                    else {
                        if (n38 >= Class98_Sub10_Sub38.anInt5753) {
                            Class333.method3761(n5, Class97.anIntArrayArray814[n38], method3224, method3223, (byte)14);
                        }
                        if (~Class218.anInt1635 > ~n39) {
                            continue;
                        }
                        Class333.method3761(n5, Class97.anIntArrayArray814[n39], method3224, method3223, (byte)(-125));
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "efa.C(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ')');
        }
    }
    
    static final int method474(int n, final byte b) {
        try {
            int n2 = 0;
            if (b != 31) {
                Class48_Sub2_Sub1.anInt5525 = 28;
            }
            if (n < 0 || ~n <= -65537) {
                n2 += 16;
                n >>>= 16;
            }
            if (n >= 256) {
                n >>>= 8;
                n2 += 8;
            }
            if (n >= 16) {
                n >>>= 4;
                n2 += 4;
            }
            if (n >= 4) {
                n >>>= 2;
                n2 += 2;
            }
            if (n >= 1) {
                n >>>= 1;
                ++n2;
            }
            return n + n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "efa.A(" + n + ',' + b + ')');
        }
    }
    
    public static void method475(final int n) {
        try {
            if (n == 1187177570) {
                Class48_Sub2_Sub1.aFloatArrayArray5522 = null;
                Class48_Sub2_Sub1.aClass265_5531 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "efa.E(" + n + ')');
        }
    }
    
    @Override
    final Interface4_Impl3 method469(final int n) {
        try {
            if (this.anInterface4_Impl3_5529 == null) {
                Class246_Sub3_Sub1_Sub1.anIntArray6223[4] = this.anInt5526;
                Class246_Sub3_Sub1_Sub1.anIntArray6223[3] = this.anInt5523;
                Class246_Sub3_Sub1_Sub1.anIntArray6223[1] = this.anInt5527;
                Class246_Sub3_Sub1_Sub1.anIntArray6223[2] = this.anInt5524;
                Class246_Sub3_Sub1_Sub1.anIntArray6223[0] = this.anInt5528;
                final d ad938 = this.aHa_Sub3_5521.aD938;
                Class246_Sub3_Sub1_Sub1.anIntArray6223[5] = this.anInt5530;
                boolean b = false;
                int n2 = 0;
                for (int n3 = 0; ~n3 > -7; ++n3) {
                    if (!ad938.method8(97, Class246_Sub3_Sub1_Sub1.anIntArray6223[n3])) {
                        return null;
                    }
                    final Class238 method11 = ad938.method11(Class246_Sub3_Sub1_Sub1.anIntArray6223[n3], -28755);
                    final int n4 = method11.aBoolean1822 ? 64 : 128;
                    if (~n2 > ~n4) {
                        n2 = n4;
                    }
                    if (~method11.aByte1832 < -1) {
                        b = true;
                    }
                }
                for (int n5 = 0; ~n5 > -7; ++n5) {
                    Class19.anIntArrayArray3443[n5] = ad938.method9(Class246_Sub3_Sub1_Sub1.anIntArray6223[n5], (byte)(-114), n2, 1.0f, false, n2);
                }
                this.anInterface4_Impl3_5529 = this.aHa_Sub3_5521.method1934(8, b, Class19.anIntArrayArray3443, n2);
            }
            return this.anInterface4_Impl3_5529;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "efa.B(" + n + ')');
        }
    }
    
    static final Class113[] method476(final boolean b) {
        try {
            if (b) {
                Class48_Sub2_Sub1.aFloatArrayArray5522 = null;
            }
            return new Class113[] { Class100.aClass113_840, Class47.aClass113_399, Class137.aClass113_1078, Class98_Sub44.aClass113_4245, Class365.aClass113_3109, Class280.aClass113_2111, Class98_Sub10_Sub3.aClass113_5546, Class308.aClass113_2582, Class4.aClass113_80, Class18.aClass113_210 };
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "efa.G(" + b + ')');
        }
    }
    
    Class48_Sub2_Sub1(final ha_Sub3 aHa_Sub3_5521, final int anInt5528, final int anInt5529, final int anInt5530, final int anInt5531, final int anInt5532, final int anInt5533) {
        try {
            this.anInt5527 = anInt5529;
            this.anInt5526 = anInt5532;
            this.anInt5528 = anInt5528;
            this.anInt5524 = anInt5530;
            this.anInt5523 = anInt5531;
            this.anInt5530 = anInt5533;
            this.aHa_Sub3_5521 = aHa_Sub3_5521;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "efa.<init>(" + ((aHa_Sub3_5521 != null) ? "{...}" : "null") + ',' + anInt5528 + ',' + anInt5529 + ',' + anInt5530 + ',' + anInt5531 + ',' + anInt5532 + ',' + anInt5533 + ')');
        }
    }
    
    static {
        Class48_Sub2_Sub1.aFloatArrayArray5522 = new float[][] { { -0.333333f, -0.333333f, -0.333333f }, { 0.333333f, -0.333333f, -0.333333f }, { -0.333333f, 0.333333f, -0.333333f }, { 0.333333f, 0.333333f, -0.333333f }, { -0.333333f, -0.333333f, 0.333333f }, { 0.333333f, -0.333333f, 0.333333f }, { -0.333333f, 0.333333f, 0.333333f }, { 0.333333f, 0.333333f, 0.333333f } };
        Class48_Sub2_Sub1.anInt5525 = -1;
        Class48_Sub2_Sub1.aClass265_5531 = new Class265();
        Class48_Sub2_Sub1.anInt5532 = 0;
    }
}
