import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub24 extends Class98_Sub10
{
    private int anInt5665;
    static Class101[] aClass101Array5666;
    private static short[] aShortArray5667;
    static Class207 aClass207_5668;
    static short[][] aShortArrayArray5669;
    private static short[] aShortArray5670;
    static int anInt5671;
    private int anInt5672;
    private static short[] aShortArray5673;
    private static short[] aShortArray5674;
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (n != 255) {
                Class98_Sub10_Sub24.aClass207_5668 = null;
            }
            if (super.aClass16_3863.aBoolean198) {
                final int n3 = 1 + (this.anInt5665 - -this.anInt5665);
                final int n4 = 65536 / n3;
                final int n5 = 65536 / (1 + this.anInt5672 + this.anInt5672);
                final int[][] array = new int[n3][];
                for (int n6 = n2 + -this.anInt5665; this.anInt5665 + n2 >= n6; ++n6) {
                    final int[] method238 = this.method1000(n6 & za_Sub1.anInt6075, 0, n ^ 0xFF);
                    final int[] array2 = new int[Class25.anInt268];
                    int n7 = 0;
                    for (int n8 = -this.anInt5672; ~this.anInt5672 <= ~n8; ++n8) {
                        n7 += method238[Class329.anInt2761 & n8];
                    }
                    int n10;
                    for (int n9 = 0; ~n9 > ~Class25.anInt268; ++n9, n7 = n10 + method238[Class329.anInt2761 & n9 - -this.anInt5672]) {
                        array2[n9] = n7 * n5 >> 880426384;
                        n10 = n7 - method238[Class329.anInt2761 & -this.anInt5672 + n9];
                    }
                    array[n6 + (this.anInt5665 + -n2)] = array2;
                }
                for (int n11 = 0; ~Class25.anInt268 < ~n11; ++n11) {
                    int n12 = 0;
                    for (int i = 0; i < n3; ++i) {
                        n12 += array[i][n11];
                    }
                    method237[n11] = n4 * n12 >> -153979312;
                }
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "od.G(" + n + ',' + n2 + ')');
        }
    }
    
    static final byte[] method1075(final byte[] array, final boolean b) {
        try {
            if (array == null) {
                return null;
            }
            if (!b) {
                return null;
            }
            final byte[] array2 = new byte[array.length];
            Class236.method2894(array, 0, array2, 0, array.length);
            return array2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "od.F(" + ((array != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final boolean method1076(final int n) {
        try {
            try {
                return PacketParser.method3967(525200579);
            }
            catch (IOException ex3) {
                Canvas_Sub1.method118((byte)104);
                return true;
            }
            catch (Exception ex) {
                String s = "T2 - " + ((Class92.currentIncommingOpcode != null) ? Class92.currentIncommingOpcode.method521((byte)89) : -1) + "," + ((Class260.aClass58_3262 != null) ? Class260.aClass58_3262.method521((byte)64) : -1) + "," + ((Class98_Sub10_Sub21.aClass58_5641 == null) ? -1 : Class98_Sub10_Sub21.aClass58_5641.method521((byte)97)) + " - " + Class65.anInt496 + "," + (Class272.anInt2038 + Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6437[0]) + "," + (aa_Sub2.anInt3562 + Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6438[0]) + " - ";
                for (int n2 = 0; n2 < Class65.anInt496 && ~n2 > -51; ++n2) {
                    s = s + Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992[n2] + ",";
                }
                Class305_Sub1.method3585(ex, -124, s);
                Class98_Sub10_Sub1.method1003(false, false);
                return true;
            }
        }
        catch (RuntimeException ex2) {
            throw Class64_Sub27.method667(ex2, "od.E(" + n + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (b >= -92) {
                method1075(null, true);
            }
            if (n != 0) {
                if (n == 1) {
                    this.anInt5665 = class98_Sub22.readUnsignedByte((byte)(-111));
                    return;
                }
                if (n != 2) {
                    return;
                }
                if (!client.aBoolean3553) {
                    super.aBoolean3861 = (class98_Sub22.readUnsignedByte((byte)(-118)) == 1);
                    return;
                }
            }
            this.anInt5672 = class98_Sub22.readUnsignedByte((byte)126);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "od.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    final int[][] method997(final int n, final int n2) {
        try {
            if (n > -76) {
                Class98_Sub10_Sub24.anInt5671 = -102;
            }
            final int[][] method2828 = super.aClass223_3859.method2828(n2, 0);
            if (super.aClass223_3859.aBoolean1683) {
                final int n3 = this.anInt5665 + this.anInt5665 + 1;
                final int n4 = 65536 / n3;
                final int n5 = 65536 / (this.anInt5672 + this.anInt5672 + 1);
                final int[][][] array = new int[n3][][];
                for (int n6 = -this.anInt5665 + n2; ~n6 >= ~(n2 - -this.anInt5665); ++n6) {
                    final int[][] method2829 = this.method994(n6 & za_Sub1.anInt6075, 24431, 0);
                    final int[][] array2 = new int[3][Class25.anInt268];
                    int n7 = 0;
                    int n8 = 0;
                    int n9 = 0;
                    final int[] array3 = method2829[0];
                    final int[] array4 = method2829[1];
                    final int[] array5 = method2829[2];
                    for (int n10 = -this.anInt5672; ~this.anInt5672 <= ~n10; ++n10) {
                        final int n11 = Class329.anInt2761 & n10;
                        n7 += array3[n11];
                        n8 += array4[n11];
                        n9 += array5[n11];
                    }
                    final int[] array6 = array2[0];
                    final int[] array7 = array2[1];
                    final int[] array8 = array2[2];
                    int n14;
                    int n15;
                    int n16;
                    int n17;
                    for (int n12 = 0; Class25.anInt268 > n12; ++n12, n17 = (Class329.anInt2761 & n12 - -this.anInt5672), n7 = n16 + array3[n17], n9 = n15 + array5[n17], n8 = n14 + array4[n17]) {
                        array6[n12] = n5 * n7 >> -948678224;
                        array7[n12] = n5 * n8 >> -1965134224;
                        array8[n12] = n5 * n9 >> 1110096464;
                        final int n13 = Class329.anInt2761 & -this.anInt5672 + n12;
                        n14 = n8 - array4[n13];
                        n15 = n9 - array5[n13];
                        n16 = n7 - array3[n13];
                    }
                    array[n6 - (-this.anInt5665 - -n2)] = array2;
                }
                final int[] array9 = method2828[0];
                final int[] array10 = method2828[1];
                final int[] array11 = method2828[2];
                for (int i = 0; i < Class25.anInt268; ++i) {
                    int n18 = 0;
                    int n19 = 0;
                    int n20 = 0;
                    for (int n21 = 0; ~n3 < ~n21; ++n21) {
                        final int[][] array12 = array[n21];
                        n20 += array12[2][i];
                        n19 += array12[1][i];
                        n18 += array12[0][i];
                    }
                    array9[i] = n18 * n4 >> 710103472;
                    array10[i] = n19 * n4 >> -1587122512;
                    array11[i] = n4 * n20 >> 807012816;
                }
            }
            return method2828;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "od.C(" + n + ',' + n2 + ')');
        }
    }
    
    public Class98_Sub10_Sub24() {
        super(1, false);
        this.anInt5665 = 1;
        this.anInt5672 = 1;
    }
    
    static final void method1077(final int n, final boolean b, final int anInt463, final int n2, final int n3, final int n4, final Class293[] aClass293Array500, final int n5, final int n6, final boolean b2, final int anInt464) {
        try {
            Class265.aHa1974.KA(n2, n3, n5, n6);
            if (b) {
                for (int n7 = 0; ~n7 > ~aClass293Array500.length; ++n7) {
                    final Class293 class293 = aClass293Array500[n7];
                    if (class293 != null && (n == class293.anInt2234 || (n == -1412584499 && Class255.aClass293_3208 == class293))) {
                        final int n8 = anInt463 + class293.anInt2347;
                        final int n9 = class293.anInt2299 + anInt464;
                        final int n10 = 1 + (class293.anInt2311 + n8);
                        final int n11 = class293.anInt2258 + n9 + 1;
                        int anInt465;
                        if (n4 == -1) {
                            Class98_Sub35.aRectangleArray4144[Class69_Sub2.anInt5335].setBounds(anInt463 + class293.anInt2347, class293.anInt2299 - -anInt464, class293.anInt2311, class293.anInt2258);
                            anInt465 = Class69_Sub2.anInt5335++;
                        }
                        else {
                            anInt465 = n4;
                        }
                        class293.anInt2250 = Class215.anInt1614;
                        class293.anInt2238 = anInt465;
                        if (!client.method111(class293)) {
                            if (~class293.anInt2307 != -1) {
                                Class123.method2206(class293, (byte)19);
                            }
                            int n12 = class293.anInt2347 - -anInt463;
                            int n13 = anInt464 + class293.anInt2299;
                            int method2642 = 0;
                            int method2643 = 0;
                            if (za_Sub2.aBoolean6079) {
                                method2642 = Class189.method2642((byte)42);
                                method2643 = Class335.method3765(false);
                            }
                            int anInt466 = class293.anInt2285;
                            if (Class15.aBoolean169 && (client.method116(class293).anInt4284 != 0 || ~class293.anInt2354 == -1) && anInt466 > 127) {
                                anInt466 = 127;
                            }
                            if (Class255.aClass293_3208 == class293) {
                                if (~n != 0x54325432 && (~Class36.anInt350 == ~class293.anInt2289 || ~Class369.anInt3129 == ~class293.anInt2289)) {
                                    Class65.aClass293Array500 = aClass293Array500;
                                    IncomingOpcode.anInt463 = anInt463;
                                    Class64_Sub5.anInt3654 = anInt464;
                                    continue;
                                }
                                if (Class15.aBoolean186 && Class239.aBoolean1840) {
                                    final int n14 = Class2.aClass299_73.method3514(67) - -method2642;
                                    int anInt467 = Class2.aClass299_73.method3507((byte)116) + method2643 - PlayerUpdateMask.anInt526;
                                    int anInt468 = n14 - Class178.anInt1405;
                                    if (~anInt468 > ~Class246_Sub3_Sub4_Sub2_Sub1.anInt6500) {
                                        anInt468 = Class246_Sub3_Sub4_Sub2_Sub1.anInt6500;
                                    }
                                    if (anInt468 - -class293.anInt2311 > Class189.aClass293_1457.anInt2311 + Class246_Sub3_Sub4_Sub2_Sub1.anInt6500) {
                                        anInt468 = -class293.anInt2311 + (Class189.aClass293_1457.anInt2311 + Class246_Sub3_Sub4_Sub2_Sub1.anInt6500);
                                    }
                                    if (anInt467 < Class222.anInt1670) {
                                        anInt467 = Class222.anInt1670;
                                    }
                                    n12 = anInt468;
                                    if (anInt467 - -class293.anInt2258 > Class222.anInt1670 + Class189.aClass293_1457.anInt2258) {
                                        anInt467 = -class293.anInt2258 + (Class189.aClass293_1457.anInt2258 + Class222.anInt1670);
                                    }
                                    n13 = anInt467;
                                }
                                if (~class293.anInt2289 == ~Class369.anInt3129) {
                                    anInt466 = 128;
                                }
                            }
                            int n17;
                            int n18;
                            int n19;
                            int n20;
                            if (~class293.anInt2354 != 0xFFFFFFFD) {
                                int n15 = class293.anInt2311 + n12;
                                int n16 = n13 - -class293.anInt2258;
                                n17 = ((n13 <= n3) ? n3 : n13);
                                n18 = ((n2 >= n12) ? n2 : n12);
                                if (class293.anInt2354 == 9) {
                                    ++n15;
                                    ++n16;
                                }
                                n19 = ((~n5 < ~n15) ? n15 : n5);
                                n20 = ((n6 <= n16) ? n6 : n16);
                            }
                            else {
                                n17 = n3;
                                n18 = n2;
                                n20 = n6;
                                n19 = n5;
                            }
                            if (~n19 < ~n18 && n17 < n20) {
                                if (~class293.anInt2307 != -1) {
                                    if (Class22.anInt218 == class293.anInt2307 || ~class293.anInt2307 == ~Class64_Sub20.anInt3698) {
                                        Class98_Sub1.method946(n12, -121, n13, class293);
                                        if (!za_Sub2.aBoolean6079) {
                                            Class92.method892(21337, n12, n13, class293.anInt2258, ~class293.anInt2307 == ~Class64_Sub20.anInt3698, class293.anInt2311);
                                            Class265.aHa1974.KA(n2, n3, n5, n6);
                                        }
                                        aa_Sub3.aBooleanArray3574[anInt465] = true;
                                        continue;
                                    }
                                    if (~class293.anInt2307 == ~Class325.anInt2727) {
                                        if (class293.method3469(Class265.aHa1974, 123) == null) {
                                            continue;
                                        }
                                        Class128.method2224(22696);
                                        Class201.method2696(4, class293, Class265.aHa1974, n12, n13);
                                        Class98_Sub10_Sub20.aBooleanArray5639[anInt465] = true;
                                        Class265.aHa1974.KA(n2, n3, n5, n6);
                                        if (!za_Sub2.aBoolean6079) {
                                            continue;
                                        }
                                        if (!b2) {
                                            Class216.method2796(n11, n8, n10, n9, (byte)126);
                                            continue;
                                        }
                                        Class93_Sub1_Sub1.method908(n11, n9, false, n8, n10);
                                        continue;
                                    }
                                    else if (Class96.anInt804 == class293.anInt2307) {
                                        if (class293.method3469(Class265.aHa1974, 103) == null) {
                                            continue;
                                        }
                                        Class304.method3562(4096, n12, n13, class293);
                                        Class98_Sub10_Sub20.aBooleanArray5639[anInt465] = true;
                                        Class265.aHa1974.KA(n2, n3, n5, n6);
                                        if (!za_Sub2.aBoolean6079) {
                                            continue;
                                        }
                                        if (b2) {
                                            Class93_Sub1_Sub1.method908(n11, n9, !b, n8, n10);
                                            continue;
                                        }
                                        Class216.method2796(n11, n8, n10, n9, (byte)113);
                                        continue;
                                    }
                                    else {
                                        if (class293.anInt2307 == Class87.anInt673) {
                                            Class64_Sub29.method673(Class265.aHa1974, Class284_Sub2_Sub2.aD6203, n13, n12, class293.anInt2258, (byte)31, class293.anInt2311);
                                            aa_Sub3.aBooleanArray3574[anInt465] = true;
                                            Class265.aHa1974.KA(n2, n3, n5, n6);
                                            continue;
                                        }
                                        if (Class239.anInt1841 == class293.anInt2307) {
                                            Class202.method2701(n12, class293.anInt2311, Class265.aHa1974, n13, (byte)(-90), class293.anInt2258);
                                            aa_Sub3.aBooleanArray3574[anInt465] = true;
                                            Class265.aHa1974.KA(n2, n3, n5, n6);
                                            continue;
                                        }
                                        if (~class293.anInt2307 == ~Class294.anInt2371) {
                                            if (Class91.aBoolean725 || Class170.aBoolean1313) {
                                                final int n21 = class293.anInt2311 + n12;
                                                int n22 = 15 + n13;
                                                if (za_Sub2.aBoolean6079) {
                                                    if (b2) {
                                                        Class93_Sub1_Sub1.method908(n11, n9, false, n8, n10);
                                                    }
                                                    else {
                                                        Class216.method2796(n11, n8, n10, n9, (byte)118);
                                                    }
                                                }
                                                if (Class91.aBoolean725) {
                                                    int n23 = -256;
                                                    if (Class338.anInt2842 < 20) {
                                                        n23 = -65536;
                                                    }
                                                    Class195.aClass43_1499.method397(n23, 0, n21, -1, "Fps:" + Class338.anInt2842, n22);
                                                    n22 += 15;
                                                    final Runtime runtime = Runtime.getRuntime();
                                                    int n24 = (int)((runtime.totalMemory() - runtime.freeMemory()) / 1024L);
                                                    int n25 = -256;
                                                    if (~n24 < -98305) {
                                                        if (Class23.aBoolean220) {
                                                            Class27.method294(true);
                                                            for (int n26 = 0; ~n26 > -11; ++n26) {
                                                                System.gc();
                                                            }
                                                            n24 = (int)((runtime.totalMemory() + -runtime.freeMemory()) / 1024L);
                                                            if (~n24 < -65537) {
                                                                za_Sub2.method1684("WARNING: Memory usage over 64MB! Please inform whoever is responsible for the content/area you are using/in.", 4, (byte)(-109));
                                                            }
                                                        }
                                                        n25 = -65536;
                                                    }
                                                    Class195.aClass43_1499.method397(n25, 0, n21, -1, "Mem:" + n24 + "k", n22);
                                                    n22 += 15;
                                                    Class195.aClass43_1499.method397(-256, 0, n21, -1, "In:" + Class118.anInt975 + "B/s Out:" + Class98_Sub12.anInt3879 + "B/s", n22);
                                                    n22 += 15;
                                                    final int n27 = Class265.aHa1974.E() / 1024;
                                                    Class195.aClass43_1499.method397((n27 <= 65536) ? -256 : -65536, 0, n21, -1, "Offheap:" + n27 + "k", n22);
                                                    n22 += 15;
                                                    int n28 = 0;
                                                    int n29 = 0;
                                                    int n30 = 0;
                                                    for (int n31 = 0; ~n31 > -38; ++n31) {
                                                        if (Class100.aClass339_Sub1Array844[n31] != null) {
                                                            n28 += Class100.aClass339_Sub1Array844[n31].method3800(0);
                                                            n29 += Class100.aClass339_Sub1Array844[n31].method3798(49);
                                                            n30 += Class100.aClass339_Sub1Array844[n31].method3791((byte)(-125));
                                                        }
                                                    }
                                                    Class69_Sub2.aClass43_5336.method397(-256, 0, n21, -1, "Cache:" + Class39.method349(0, 2, 48, n29 * 10000 / n28, true) + "% (" + n30 * 100 / n28 + "%)", n22);
                                                    n22 += 12;
                                                }
                                                if (Class113.anInt952 > 0) {
                                                    Class69_Sub2.aClass43_5336.method397(-256, 0, n21, -1, "Particles: " + Class98_Sub43_Sub3.anInt5924 + " / " + Class113.anInt952, n22);
                                                }
                                                n22 += 12;
                                                if (Class170.aBoolean1313) {
                                                    Class69_Sub2.aClass43_5336.method397(-256, 0, n21, -1, "Polys: " + Class265.aHa1974.I() + " Models: " + Class265.aHa1974.M(), n22);
                                                    n22 += 12;
                                                    Class69_Sub2.aClass43_5336.method397(-256, 0, n21, -1, "Ls: " + Class191.anInt1480 + " La: " + Class76_Sub5.anInt3748 + " NPC: " + Class181.anInt1432 + " Pl: " + Class98_Sub10_Sub13.anInt5602, n22);
                                                    Class228.method2862(-92);
                                                    n22 += 12;
                                                }
                                                aa_Sub3.aBooleanArray3574[anInt465] = true;
                                            }
                                            continue;
                                        }
                                    }
                                }
                                if (class293.anInt2354 == 0) {
                                    if (class293.anInt2307 == Class64_Sub21.anInt3704 && Class265.aHa1974.method1768()) {
                                        Class265.aHa1974.method1746(n12, n13, class293.anInt2311, class293.anInt2258);
                                    }
                                    method1077(class293.anInt2248, true, -class293.anInt2246 + n12, n18, n17, anInt465, aClass293Array500, n19, n20, b2, n13 + -class293.anInt2213);
                                    if (class293.aClass293Array2339 != null) {
                                        method1077(class293.anInt2248, true, n12 - class293.anInt2246, n18, n17, anInt465, class293.aClass293Array2339, n19, n20, b2, n13 - class293.anInt2213);
                                    }
                                    final Class98_Sub18 class98_Sub18 = (Class98_Sub18)Class116.aClass377_964.method3990(class293.anInt2248, -1);
                                    if (class98_Sub18 != null) {
                                        Class246_Sub3_Sub5_Sub2.method3093(n20, anInt465, n17, class98_Sub18.anInt3945, n13, n12, n19, (byte)(-9), n18);
                                    }
                                    if (~Class64_Sub21.anInt3704 == ~class293.anInt2307 && Class265.aHa1974.method1768()) {
                                        Class265.aHa1974.method1814();
                                    }
                                    Class265.aHa1974.KA(n2, n3, n5, n6);
                                }
                                if (Class232.aBooleanArray1741[anInt465] || ~Class167.anInt1282 < -2) {
                                    if (class293.anInt2354 == 3) {
                                        if (anInt466 != 0) {
                                            if (!class293.aBoolean2263) {
                                                Class265.aHa1974.method1779(n12, n13, class293.anInt2311, class293.anInt2258, 255 + -(0xFF & anInt466) << 1189469208 | (0xFFFFFF & class293.anInt2236), 1);
                                            }
                                            else {
                                                Class265.aHa1974.aa(n12, n13, class293.anInt2311, class293.anInt2258, 255 + -(0xFF & anInt466) << -1750207592 | (0xFFFFFF & class293.anInt2236), 1);
                                            }
                                        }
                                        else if (class293.aBoolean2263) {
                                            Class265.aHa1974.aa(n12, n13, class293.anInt2311, class293.anInt2258, class293.anInt2236, 0);
                                        }
                                        else {
                                            Class265.aHa1974.method1779(n12, n13, class293.anInt2311, class293.anInt2258, class293.anInt2236, 0);
                                        }
                                        if (za_Sub2.aBoolean6079) {
                                            if (b2) {
                                                Class93_Sub1_Sub1.method908(n11, n9, false, n8, n10);
                                            }
                                            else {
                                                Class216.method2796(n11, n8, n10, n9, (byte)91);
                                            }
                                        }
                                    }
                                    else if (class293.anInt2354 == 4) {
                                        final Class43 method2644 = class293.method3460(-69, Class265.aHa1974);
                                        if (method2644 == null) {
                                            if (Class93.aBoolean3503) {
                                                Class341.method3812(1, class293);
                                            }
                                        }
                                        else {
                                            int n32 = class293.anInt2236;
                                            String s = class293.aString2225;
                                            if (class293.anInt2302 != -1) {
                                                final Class297 method2645 = Class98_Sub46_Sub19.aClass205_6068.method2714(class293.anInt2302, (byte)(-121));
                                                s = method2645.aString2450;
                                                if (s == null) {
                                                    s = "null";
                                                }
                                                if ((method2645.anInt2469 == 1 || ~class293.anInt2349 != 0xFFFFFFFE) && ~class293.anInt2349 != 0x0) {
                                                    s = "<col=ff9040>" + s + "</col> x" + Class64_Sub29.method675(class293.anInt2349, 16474);
                                                }
                                            }
                                            if (class293.anInt2211 != -1) {
                                                s = Class48.method454(class293.anInt2211, b);
                                                if (s == null) {
                                                    s = "";
                                                }
                                            }
                                            if (OutputStream_Sub1.aClass293_33 == class293) {
                                                s = Class309.aClass309_2617.method3615(Class374.anInt3159, (byte)25);
                                                n32 = class293.anInt2236;
                                            }
                                            if (Class153.aBoolean1230) {
                                                Class265.aHa1974.T(n12, n13, n12 + class293.anInt2311, n13 + class293.anInt2258);
                                            }
                                            method2644.method405(n12, class293.anInt2350, class293.anInt2258, null, 0, class293.anInt2311, class293.aBoolean2315 ? (-(0xFF & anInt466) + 255 << 1735414296) : -1, class293.anInt2244, class293.anInt2296, n13, (byte)(-80), Class64_Sub18.aClass332Array3689, -(anInt466 & 0xFF) + 255 << 1099433400 | n32, 0, s, class293.anInt2341, null);
                                            if (Class153.aBoolean1230) {
                                                Class265.aHa1974.KA(n2, n3, n5, n6);
                                            }
                                            if (~s.trim().length() < -1) {
                                                if (Class153.aBoolean1230) {
                                                    if (za_Sub2.aBoolean6079) {
                                                        if (b2) {
                                                            Class93_Sub1_Sub1.method908(n11, n9, false, n8, n10);
                                                        }
                                                        else {
                                                            Class216.method2796(n11, n8, n10, n9, (byte)114);
                                                        }
                                                    }
                                                }
                                                else {
                                                    final Class197 method2646 = Class98_Sub46_Sub6.method1550(class293.anInt2264, 18361, Class265.aHa1974);
                                                    final int method2647 = method2646.method2670(class293.anInt2311, s, Class64_Sub18.aClass332Array3689, (byte)79);
                                                    final int method2648 = method2646.method2672(Class64_Sub18.aClass332Array3689, class293.anInt2311, class293.anInt2244, s, true);
                                                    if (za_Sub2.aBoolean6079) {
                                                        if (b2) {
                                                            Class93_Sub1_Sub1.method908(n13 + method2648, n13, false, n12, method2647 + n12);
                                                        }
                                                        else {
                                                            Class216.method2796(method2648 + n13, n12, method2647 + n12, n13, (byte)(-62));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    else if (~class293.anInt2354 == 0xFFFFFFFA) {
                                        if (class293.anInt2267 >= 0) {
                                            class293.method3467(0, Class101.aClass115_857, Class373_Sub2.aClass59_5470).method3831(0, class293.anInt2216 << 1037126371, class293.anInt2261 << 765115811, n13, -24446, n12, Class265.aHa1974, class293.anInt2258, class293.anInt2311, 0);
                                        }
                                        else {
                                            Class332 class294;
                                            if (~class293.anInt2302 != 0x0) {
                                                class294 = Class98_Sub46_Sub19.aClass205_6068.method2710(class293.anInt2304, class293.anInt2302, Class265.aHa1974, class293.aBoolean2262 ? Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass313_6518 : null, class293.anInt2349, class293.anInt2305, 0xFF000000 | class293.anInt2355, 24056);
                                            }
                                            else if (~class293.anInt2211 != 0x0) {
                                                class294 = Class200.method2693(class293.anInt2211, (byte)(-114), Class265.aHa1974);
                                            }
                                            else {
                                                class294 = class293.method3456(Class265.aHa1974, -1234042329);
                                            }
                                            if (class294 != null) {
                                                final int method2649 = class294.method3737();
                                                final int method2650 = class294.method3749();
                                                final int n33 = ((~class293.anInt2236 == -1) ? 16777215 : (class293.anInt2236 & 0xFFFFFF)) | 255 - (anInt466 & 0xFF) << 638192120;
                                                if (class293.aBoolean2288) {
                                                    Class265.aHa1974.T(n12, n13, class293.anInt2311 + n12, n13 + class293.anInt2258);
                                                    if (class293.anInt2255 != 0) {
                                                        final int n34 = (method2649 + (-1 + class293.anInt2311)) / method2649;
                                                        final int n35 = (method2650 - 1 + class293.anInt2258) / method2650;
                                                        for (int n36 = 0; ~n36 > ~n34; ++n36) {
                                                            for (int n37 = 0; ~n35 < ~n37; ++n37) {
                                                                if (class293.anInt2236 == 0) {
                                                                    class294.method3730(n36 * method2649 + n12 + method2649 / 2.0f, method2650 * n37 + n13 + method2650 / 2.0f, 4096, class293.anInt2255);
                                                                }
                                                                else {
                                                                    class294.method3743(n12 - -(n36 * method2649) + method2649 / 2.0f, n13 + n37 * method2650 + method2650 / 2.0f, 4096, class293.anInt2255, 0, n33, 1);
                                                                }
                                                            }
                                                        }
                                                    }
                                                    else if (class293.anInt2236 != 0 || anInt466 != 0) {
                                                        class294.method3728(n12, n13, class293.anInt2311, class293.anInt2258, 0, n33, 1);
                                                    }
                                                    else {
                                                        class294.method3738(n12, n13, class293.anInt2311, class293.anInt2258);
                                                    }
                                                    Class265.aHa1974.KA(n2, n3, n5, n6);
                                                }
                                                else if (class293.anInt2236 == 0 && anInt466 == 0) {
                                                    if (~class293.anInt2255 != -1) {
                                                        class294.method3730(n12 + class293.anInt2311 / 2.0f, n13 + class293.anInt2258 / 2.0f, class293.anInt2311 * 4096 / method2649, class293.anInt2255);
                                                    }
                                                    else if (~method2649 != ~class293.anInt2311 || ~class293.anInt2258 != ~method2650) {
                                                        class294.method3726(n12, n13, class293.anInt2311, class293.anInt2258);
                                                    }
                                                    else {
                                                        class294.method3735(n12, n13);
                                                    }
                                                }
                                                else if (class293.anInt2255 == 0) {
                                                    if (~class293.anInt2311 == ~method2649 && class293.anInt2258 == method2650) {
                                                        class294.method3748(n12, n13, 0, n33, 1);
                                                    }
                                                    else {
                                                        class294.method3727(n12, n13, class293.anInt2311, class293.anInt2258, 0, n33, 1);
                                                    }
                                                }
                                                else {
                                                    class294.method3743(class293.anInt2311 / 2.0f + n12, n13 + class293.anInt2258 / 2.0f, 4096 * class293.anInt2311 / method2649, class293.anInt2255, 0, n33, 1);
                                                }
                                            }
                                            else if (Class93.aBoolean3503) {
                                                Class341.method3812(1, class293);
                                            }
                                        }
                                        if (za_Sub2.aBoolean6079) {
                                            if (!b2) {
                                                Class216.method2796(n11, n8, n10, n9, (byte)102);
                                            }
                                            else {
                                                Class93_Sub1_Sub1.method908(n11, n9, false, n8, n10);
                                            }
                                        }
                                    }
                                    else if (class293.anInt2354 == 6) {
                                        Class98_Sub35.method1452(0);
                                        final Class40 class295 = null;
                                        Class146 class296 = null;
                                        int n38 = 0;
                                        if (~class293.anInt2302 != 0x0) {
                                            final Class297 method2651 = Class98_Sub46_Sub19.aClass205_6068.method2714(class293.anInt2302, (byte)(-121));
                                            if (method2651 != null) {
                                                class296 = method2651.method3493((byte)(-45), class293.anInt2349).method3501(class293.anInt2312, 2048, class293.anInt2303, (class293.anInt2208 == -1) ? null : Class151_Sub7.aClass183_5001.method2623(class293.anInt2208, 16383), class293.anInt2287, Class265.aHa1974, 1, 128, class293.aBoolean2262 ? Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass313_6518 : null);
                                                if (class296 == null) {
                                                    Class341.method3812(1, class293);
                                                }
                                                else {
                                                    n38 = -class296.fa() >> -1224511199;
                                                }
                                            }
                                        }
                                        else if (class293.anInt2233 != 5) {
                                            if (~class293.anInt2233 == 0xFFFFFFF7 || class293.anInt2233 == 9) {
                                                final Class98_Sub3 method2652 = Class64_Sub28.method669(class293.anInt2343, false, 6);
                                                final Class97 class297 = (~class293.anInt2208 == 0x0) ? null : Class151_Sub7.aClass183_5001.method2623(class293.anInt2208, 16383);
                                                if (method2652 != null) {
                                                    class296 = method2652.method951(class297, class293.anInt2312, class293.anInt2303, class293.anInt2233 == 9, class293.aBoolean2262 ? Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass313_6518 : null, class293.anInt2210, 2048, class293.anInt2287, (byte)(-80), Class265.aHa1974);
                                                }
                                            }
                                            else if (~class293.anInt2208 == 0x0) {
                                                class296 = class293.method3461(Class75.aClass140_584, Class151_Sub7.aClass183_5001, Class4.aClass301_85, Class149.aClass83_1205, Class265.aHa1974, Class370.aClass257_3136, class295, (byte)(-40), -1, 2048, -1, 0, null, Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass313_6518, Class98_Sub46_Sub19.aClass205_6068);
                                                if (class296 == null && Class93.aBoolean3503) {
                                                    Class341.method3812(1, class293);
                                                }
                                            }
                                            else {
                                                class296 = class293.method3461(Class75.aClass140_584, Class151_Sub7.aClass183_5001, Class4.aClass301_85, Class149.aClass83_1205, Class265.aHa1974, Class370.aClass257_3136, class295, (byte)(-57), class293.anInt2287, 2048, class293.anInt2303, class293.anInt2312, Class151_Sub7.aClass183_5001.method2623(class293.anInt2208, 16383), Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aClass313_6518, Class98_Sub46_Sub19.aClass205_6068);
                                                if (class296 == null && Class93.aBoolean3503) {
                                                    Class341.method3812(1, class293);
                                                }
                                            }
                                        }
                                        else {
                                            final int anInt469 = class293.anInt2343;
                                            if (~anInt469 <= -1 && ~anInt469 > -2049) {
                                                final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[anInt469];
                                                final Class97 class298 = (class293.anInt2208 != -1) ? Class151_Sub7.aClass183_5001.method2623(class293.anInt2208, 16383) : null;
                                                if (class246_Sub3_Sub4_Sub2_Sub2 != null && (za_Sub2.anInt6080 == anInt469 || Class98_Sub32.method1438(class246_Sub3_Sub4_Sub2_Sub2.aString6537, 6243) == class293.anInt2210)) {
                                                    class296 = class246_Sub3_Sub4_Sub2_Sub2.aClass313_6518.method3628(Class370.aClass257_3136, class298, null, Class151_Sub7.aClass183_5001, class293.anInt2303, Class149.aClass83_1205, null, Class98_Sub46_Sub19.aClass205_6068, true, 0, null, Class4.aClass301_85, Class75.aClass140_584, true, 2048, -1, class293.anInt2312, class293.anInt2287, 0, 0, Class265.aHa1974);
                                                }
                                            }
                                        }
                                        if (class296 != null) {
                                            int n39;
                                            if (class293.anInt2232 > 0) {
                                                n39 = (class293.anInt2311 << -1492214583) / class293.anInt2232;
                                            }
                                            else {
                                                n39 = 512;
                                            }
                                            int n40;
                                            if (class293.anInt2226 > 0) {
                                                n40 = (class293.anInt2258 << -44986999) / class293.anInt2226;
                                            }
                                            else {
                                                n40 = 512;
                                            }
                                            int n41 = class293.anInt2311 / 2 + n12;
                                            int n42 = n13 + class293.anInt2258 / 2;
                                            if (!class293.aBoolean2280) {
                                                n41 += class293.anInt2336 * n39 >> -1439009655;
                                                n42 += n40 * class293.anInt2344 >> -285380663;
                                            }
                                            Class266.aClass111_1986.method2091();
                                            Class265.aHa1974.a(Class266.aClass111_1986);
                                            Class265.aHa1974.DA(n41, n42, n39, n40);
                                            Class265.aHa1974.ya();
                                            if (class293.aBoolean2325) {
                                                Class265.aHa1974.C(false);
                                            }
                                            if (!class293.aBoolean2280) {
                                                final int n43 = (class293.anInt2251 << -1783646334) * Class284_Sub2_Sub2.anIntArray6200[class293.anInt2310 << -1188499165] >> -1207674034;
                                                final int n44 = Class284_Sub2_Sub2.anIntArray6202[class293.anInt2310 << -223590269] * (class293.anInt2251 << -423051582) >> -1224356338;
                                                Class76_Sub5.aClass111_3745.method2104(-class293.anInt2346 << -1988380221);
                                                Class76_Sub5.aClass111_3745.method2097(class293.anInt2218 << 1880483235);
                                                Class76_Sub5.aClass111_3745.method2106(class293.anInt2268 << 1838805666, n38 + (n43 - -(class293.anInt2273 << -1320988254)), (class293.anInt2273 << -290939422) + n44);
                                                Class76_Sub5.aClass111_3745.method2105(class293.anInt2310 << -1796566077);
                                            }
                                            else {
                                                Class76_Sub5.aClass111_3745.method2107(class293.anInt2310);
                                                Class76_Sub5.aClass111_3745.method2097(class293.anInt2218);
                                                Class76_Sub5.aClass111_3745.method2090(class293.anInt2346);
                                                Class76_Sub5.aClass111_3745.method2106(class293.anInt2336, class293.anInt2344, class293.anInt2352);
                                            }
                                            class293.method3464(true, Class265.aHa1974, Class215.anInt1614, class296, Class76_Sub5.aClass111_3745);
                                            if (Class153.aBoolean1230) {
                                                Class265.aHa1974.T(n12, n13, n12 - -class293.anInt2311, class293.anInt2258 + n13);
                                            }
                                            if (!class293.aBoolean2280) {
                                                if (!class293.aBoolean2265) {
                                                    class296.method2325(Class76_Sub5.aClass111_3745, null, 1);
                                                    if (class293.aClass246_Sub5_2301 != null) {
                                                        Class265.aHa1974.method1820(class293.aClass246_Sub5_2301.method3115());
                                                    }
                                                }
                                                else {
                                                    class296.method2329(Class76_Sub5.aClass111_3745, null, class293.anInt2251 << 1326541250, 1);
                                                }
                                            }
                                            else if (class293.aBoolean2265) {
                                                class296.method2329(Class76_Sub5.aClass111_3745, null, class293.anInt2251, 1);
                                            }
                                            else {
                                                class296.method2325(Class76_Sub5.aClass111_3745, null, 1);
                                                if (class293.aClass246_Sub5_2301 != null) {
                                                    Class265.aHa1974.method1820(class293.aClass246_Sub5_2301.method3115());
                                                }
                                            }
                                            if (Class153.aBoolean1230) {
                                                Class265.aHa1974.KA(n2, n3, n5, n6);
                                            }
                                            if (class293.aBoolean2325) {
                                                Class265.aHa1974.C(true);
                                            }
                                        }
                                        if (za_Sub2.aBoolean6079) {
                                            if (b2) {
                                                Class93_Sub1_Sub1.method908(n11, n9, false, n8, n10);
                                            }
                                            else {
                                                Class216.method2796(n11, n8, n10, n9, (byte)95);
                                            }
                                        }
                                    }
                                    else if (~class293.anInt2354 == 0xFFFFFFF6) {
                                        int n45;
                                        int n46;
                                        int n47;
                                        int n48;
                                        if (class293.aBoolean2256) {
                                            n45 = n13;
                                            n46 = n12 + class293.anInt2311;
                                            n47 = n12;
                                            n48 = n13 - -class293.anInt2258;
                                        }
                                        else {
                                            n48 = n13;
                                            n46 = class293.anInt2311 + n12;
                                            n47 = n12;
                                            n45 = class293.anInt2258 + n13;
                                        }
                                        if (class293.anInt2293 == 1) {
                                            Class265.aHa1974.method1795(n47, n48, n46, n45, class293.anInt2236, 0);
                                        }
                                        else {
                                            Class265.aHa1974.method1816(n47, n48, n46, n45, class293.anInt2236, class293.anInt2293, 0);
                                        }
                                        if (za_Sub2.aBoolean6079) {
                                            if (!b2) {
                                                Class216.method2796(n11, n8, n10, n9, (byte)102);
                                            }
                                            else {
                                                Class93_Sub1_Sub1.method908(n11, n9, false, n8, n10);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "od.B(" + n + ',' + b + ',' + anInt463 + ',' + n2 + ',' + n3 + ',' + n4 + ',' + ((aClass293Array500 != null) ? "{...}" : "null") + ',' + n5 + ',' + n6 + ',' + b2 + ',' + anInt464 + ')');
        }
    }
    
    public static void method1078(final byte b) {
        try {
            Class98_Sub10_Sub24.aShortArray5674 = null;
            Class98_Sub10_Sub24.aShortArrayArray5669 = null;
            Class98_Sub10_Sub24.aShortArray5667 = null;
            Class98_Sub10_Sub24.aClass207_5668 = null;
            Class98_Sub10_Sub24.aShortArray5670 = null;
            Class98_Sub10_Sub24.aShortArray5673 = null;
            Class98_Sub10_Sub24.aClass101Array5666 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "od.D(" + b + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub24.aShortArray5667 = new short[] { 967, 20428, -21577, 11219, -10290 };
        Class98_Sub10_Sub24.aShortArray5670 = new short[] { 957, 20418, -21587, 11209, -10300 };
        Class98_Sub10_Sub24.aClass101Array5666 = new Class101[6];
        Class98_Sub10_Sub24.aShortArray5673 = new short[] { 962, 20423, -21582, 11214, -10295 };
        Class98_Sub10_Sub24.aShortArray5674 = new short[] { 952, 20413, -21592, 11204, -10305 };
        Class98_Sub10_Sub24.aShortArrayArray5669 = new short[][] { Class98_Sub10_Sub24.aShortArray5667, Class98_Sub10_Sub24.aShortArray5673, Class98_Sub10_Sub24.aShortArray5670, Class98_Sub10_Sub24.aShortArray5674 };
    }
}
