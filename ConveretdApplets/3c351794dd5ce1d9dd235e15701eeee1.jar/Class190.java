import jaclib.memory.Buffer;
import jaclib.memory.Stream;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class190
{
    int anInt1460;
    private Interface4_Impl2 anInterface4_Impl2_1461;
    private int anInt1462;
    static int[] anIntArray1463;
    private Class15 aClass15_1464;
    private ha_Sub3 aHa_Sub3_1465;
    private int anInt1466;
    private int anInt1467;
    static byte[][][] aByteArrayArrayArray1468;
    private int anInt1469;
    boolean aBoolean1470;
    private Interface2_Impl2 anInterface2_Impl2_1471;
    private int anInt1472;
    
    final void method2643(final int n) {
        try {
            if (n != 30925) {
                method2648(-107L, -52);
            }
            this.method2645(-18732, this.anInterface2_Impl2_1471, this.anInt1460);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mj.C(" + n + ')');
        }
    }
    
    static final byte method2644(final int n, final int n2, final int n3) {
        try {
            if (n2 <= 54) {
                method2644(8, 49, -34);
            }
            if (~n != 0xFFFFFFF6) {
                return 0;
            }
            if ((n3 & 0x1) != 0x0) {
                return 2;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mj.E(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final void method2645(final int n, final Interface2_Impl2 interface2_Impl2, final int n2) {
        try {
            if (~n2 < -1) {
                this.method2647(128);
                this.aHa_Sub3_1465.method2005(this.anInterface4_Impl2_1461, 125);
                this.aHa_Sub3_1465.method1973(Class336.aClass232_2822, 1 + -this.anInt1472 + this.anInt1462, 0, 26810, interface2_Impl2, this.anInt1472, n2);
            }
            if (n != -18732) {
                method2646(41);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mj.B(" + n + ',' + ((interface2_Impl2 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    public static void method2646(final int n) {
        try {
            Class190.anIntArray1463 = null;
            Class190.aByteArrayArrayArray1468 = null;
            if (n != 27387) {
                method2646(102);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mj.D(" + n + ')');
        }
    }
    
    private final void method2647(final int n) {
        try {
            if (n != 128) {
                this.anInterface4_Impl2_1461 = null;
            }
            if (this.aBoolean1470) {
                this.aBoolean1470 = false;
                final byte[] aByteArray176 = this.aClass15_1464.aByteArray176;
                int anInt1469 = 0;
                final int anInt1470 = this.aClass15_1464.anInt180;
                int n2 = this.aClass15_1464.anInt180 * this.anInt1466 + this.anInt1467;
                for (int n3 = -128; ~n3 > -1; ++n3) {
                    anInt1469 = -anInt1469 + (anInt1469 << -266560856);
                    for (int i = -128; i < 0; ++i) {
                        if (aByteArray176[n2++] != 0) {
                            ++anInt1469;
                        }
                    }
                    n2 += anInt1470 - 128;
                }
                if (this.anInterface4_Impl2_1461 != null && ~anInt1469 == ~this.anInt1469) {
                    this.aBoolean1470 = false;
                }
                else {
                    this.anInt1469 = anInt1469;
                    int n4 = this.anInt1467 - -(this.anInt1466 * anInt1470);
                    int n5 = 0;
                    if (!this.aHa_Sub3_1465.method1942(n - 128, Class53_Sub1.aClass164_3633, Class162.aClass162_1266)) {
                        if (Class64_Sub1.anIntArray3639 == null) {
                            Class64_Sub1.anIntArray3639 = new int[16384];
                        }
                        final int[] anIntArray3639 = Class64_Sub1.anIntArray3639;
                        for (int n6 = -128; ~n6 > -1; ++n6) {
                            for (int n7 = -128; ~n7 > -1; ++n7) {
                                if (~aByteArray176[n4] != -1) {
                                    anIntArray3639[n5++] = 1140850688;
                                }
                                else {
                                    int n8 = 0;
                                    if (aByteArray176[n4 - 1] != 0) {
                                        ++n8;
                                    }
                                    if (aByteArray176[n4 + 1] != 0) {
                                        ++n8;
                                    }
                                    if (aByteArray176[n4 - anInt1470] != 0) {
                                        ++n8;
                                    }
                                    if (~aByteArray176[anInt1470 + n4] != -1) {
                                        ++n8;
                                    }
                                    anIntArray3639[n5++] = n8 * 17 << -1644342728;
                                }
                                ++n4;
                            }
                            n4 += this.aClass15_1464.anInt180 - 128;
                        }
                        if (this.anInterface4_Impl2_1461 != null) {
                            this.anInterface4_Impl2_1461.method49(17779, 0, 128, Class64_Sub1.anIntArray3639, 0, 0, 128, 128);
                        }
                        else {
                            (this.anInterface4_Impl2_1461 = this.aHa_Sub3_1465.method2012(128, 128, (byte)31, Class64_Sub1.anIntArray3639, false)).method46(false, false, n - 61);
                        }
                    }
                    else {
                        if (Class298.aByteArray2492 == null) {
                            Class298.aByteArray2492 = new byte[16384];
                        }
                        final byte[] aByteArray177 = Class298.aByteArray2492;
                        for (int n9 = -128; ~n9 > -1; ++n9) {
                            for (int n10 = -128; ~n10 > -1; ++n10) {
                                if (~aByteArray176[n4] != -1) {
                                    aByteArray177[n5++] = 68;
                                }
                                else {
                                    int n11 = 0;
                                    if (~aByteArray176[-1 + n4] != -1) {
                                        ++n11;
                                    }
                                    if (aByteArray176[n4 + 1] != 0) {
                                        ++n11;
                                    }
                                    if (~aByteArray176[n4 - anInt1470] != -1) {
                                        ++n11;
                                    }
                                    if (aByteArray176[anInt1470 + n4] != 0) {
                                        ++n11;
                                    }
                                    aByteArray177[n5++] = (byte)(17 * n11);
                                }
                                ++n4;
                            }
                            n4 += this.aClass15_1464.anInt180 - 128;
                        }
                        if (this.anInterface4_Impl2_1461 == null) {
                            (this.anInterface4_Impl2_1461 = this.aHa_Sub3_1465.method2053(128, Class53_Sub1.aClass164_3633, (byte)87, Class298.aByteArray2492, false, 128)).method46(false, false, 124);
                        }
                        else {
                            this.anInterface4_Impl2_1461.method41(128, 0, 128, 0, 128, -26946, Class298.aByteArray2492, Class53_Sub1.aClass164_3633, 0);
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mj.F(" + n + ')');
        }
    }
    
    Class190(final ha_Sub3 aHa_Sub3_1465, final Class15 aClass15_1464, final s_Sub2 s_Sub2, final int n, final int n2, final int n3, final int anInt1467, final int anInt1468) {
        this.anInt1469 = -1;
        this.aBoolean1470 = true;
        try {
            this.aClass15_1464 = aClass15_1464;
            this.anInt1466 = anInt1468;
            this.anInt1467 = anInt1467;
            this.aHa_Sub3_1465 = aHa_Sub3_1465;
            final int i = 1 << n3;
            int n4 = 0;
            final int n5 = n << n3;
            final int n6 = n2 << n3;
            for (int n7 = 0; i > n7; ++n7) {
                int n8 = s_Sub2.anInt2203 * (n6 - -n7) + n5;
                for (int n9 = 0; ~i < ~n9; ++n9) {
                    final short[] array = s_Sub2.aShortArrayArray5230[n8++];
                    if (array != null) {
                        n4 += array.length;
                    }
                }
            }
            if (n4 > 0) {
                this.anInt1472 = Integer.MAX_VALUE;
                this.anInt1462 = Integer.MIN_VALUE;
                (this.anInterface2_Impl2_1471 = this.aHa_Sub3_1465.method1990((byte)84, false)).method76(n4, 20779);
                for (int j = 0; j < 4; ++j) {
                    final Buffer method78 = this.anInterface2_Impl2_1471.method78(true, -102);
                    if (method78 != null) {
                        final Stream method79 = this.aHa_Sub3_1465.method2043(24022, method78);
                        if (Stream.a()) {
                            for (int n10 = 0; ~n10 > ~i; ++n10) {
                                int n11 = (n6 - -n10) * s_Sub2.anInt2203 + n5;
                                for (int n12 = 0; ~i < ~n12; ++n12) {
                                    final short[] array2 = s_Sub2.aShortArrayArray5230[n11++];
                                    if (array2 != null) {
                                        for (int n13 = 0; ~array2.length < ~n13; ++n13) {
                                            final int n14 = 0xFFFF & array2[n13];
                                            if (n14 > this.anInt1462) {
                                                this.anInt1462 = n14;
                                            }
                                            if (this.anInt1472 > n14) {
                                                this.anInt1472 = n14;
                                            }
                                            method79.c(n14);
                                        }
                                    }
                                }
                            }
                        }
                        else {
                            for (int k = 0; k < i; ++k) {
                                int n15 = n5 + (n6 - -k) * s_Sub2.anInt2203;
                                for (int l = 0; l < i; ++l) {
                                    final short[] array3 = s_Sub2.aShortArrayArray5230[n15++];
                                    if (array3 != null) {
                                        for (int n16 = 0; array3.length > n16; ++n16) {
                                            final int n17 = array3[n16] & 0xFFFF;
                                            if (~n17 > ~this.anInt1472) {
                                                this.anInt1472 = n17;
                                            }
                                            if (~n17 < ~this.anInt1462) {
                                                this.anInt1462 = n17;
                                            }
                                            method79.d(n17);
                                        }
                                    }
                                }
                            }
                        }
                        method79.c();
                        if (this.anInterface2_Impl2_1471.method79((byte)(-120))) {
                            break;
                        }
                    }
                }
                this.anInt1460 = n4 / 3;
            }
            else {
                this.anInt1460 = 0;
                this.anInterface4_Impl2_1461 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mj.<init>(" + ((aHa_Sub3_1465 != null) ? "{...}" : "null") + ',' + ((aClass15_1464 != null) ? "{...}" : "null") + ',' + ((s_Sub2 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + anInt1467 + ',' + anInt1468 + ')');
        }
    }
    
    static final void method2648(final long n, final int n2) {
        try {
            if (Class98_Sub46_Sub1.aClass172ArrayArrayArray5948 != null) {
                if (Class98_Sub46_Sub20_Sub2.anInt6319 == 1 || Class98_Sub46_Sub20_Sub2.anInt6319 == 5) {
                    Class153.method2489(n, (byte)(-102));
                }
                else if (~Class98_Sub46_Sub20_Sub2.anInt6319 == 0xFFFFFFFB) {
                    Class64_Sub6.method573(-1, n);
                }
            }
            Class45.method431(Class215.anInt1614, true, Class265.aHa1974);
            if (Class15.anInt185 != -1) {
                Class350.method3844(Class15.anInt185, -47);
            }
            for (int n3 = 0; ~Class69_Sub2.anInt5335 < ~n3; ++n3) {
                if (aa_Sub3.aBooleanArray3574[n3]) {
                    Class98_Sub10_Sub20.aBooleanArray5639[n3] = true;
                }
                Class232.aBooleanArray1741[n3] = aa_Sub3.aBooleanArray3574[n3];
                aa_Sub3.aBooleanArray3574[n3] = false;
            }
            Class77_Sub1.anInt3803 = Class215.anInt1614;
            Class98_Sub1.method946(-1, -125, -1, null);
            Class304.method3563(-1, null, -1, 60);
            if (~Class15.anInt185 != 0x0) {
                Class69_Sub2.anInt5335 = 0;
                Class215.method2791((byte)118);
            }
            Class265.aHa1974.la();
            Class98_Sub10_Sub22.method1069(256, Class265.aHa1974);
            int n4 = Class83.method824((byte)(-72));
            if (n4 == -1) {
                n4 = Class21_Sub2.anInt5387;
            }
            if (~n4 == 0x0) {
                n4 = OutputStream_Sub2.anInt39;
            }
            Class43.method401(n4, true);
            final int n5 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.method3034(0) << 2087692776;
            Class169.method2535(Class279.anInt2099, Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088, Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079 - -n5, Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084 + n5, (byte)112);
            Class279.anInt2099 = 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mj.A(" + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class190.anIntArray1463 = new int[1];
    }
}
