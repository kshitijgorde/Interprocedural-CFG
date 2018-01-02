// 
// Decompiled by Procyon v0.5.30
// 

final class Class201
{
    static int anInt1544;
    static int anInt1545;
    
    static final void method2696(final int n, final Class293 class293, final ha ha, final int n2, final int n3) {
        try {
            final aa method3469 = class293.method3469(ha, n + 117);
            if (method3469 != null) {
                ha.KA(n2, n3, class293.anInt2311 + n2, n3 + class293.anInt2258);
                if (~Class333.anInt3386 == 0xFFFFFFFD || Class333.anInt3386 == 5 || Class64_Sub9.aClass332_3663 == null) {
                    ha.A(-16777216, method3469, n2, n3);
                }
                else {
                    int n4;
                    int n5;
                    int n6;
                    int n7;
                    if (~Class98_Sub46_Sub20_Sub2.anInt6319 == 0xFFFFFFFB) {
                        n4 = Class98_Sub46_Sub2_Sub2.anInt6295;
                        n5 = Class135.anInt1051;
                        n6 = 4096;
                        n7 = (0x3FFF & (int)(-Class98_Sub22_Sub2.aFloat5794));
                    }
                    else {
                        n6 = -(16 * Class151.anInt1213) + 4096;
                        n4 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084;
                        n7 = (0x3FFF & Class204.anInt1553 + (int)(-Class98_Sub22_Sub2.aFloat5794));
                        n5 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079;
                    }
                    Class64_Sub9.aClass332_3663.method3746(n2 + class293.anInt2311 / 2.0f, n3 + class293.anInt2258 / 2.0f, -(2 * (-104 + Class165.anInt1276)) + n4 / 128 + 48, -(n5 / 128) + 48 - (-(Class98_Sub10_Sub7.anInt5572 * 4) - -(2 * Class98_Sub10_Sub7.anInt5572) - 208), n6, n7 << 1401446114, method3469, n2, n3);
                    for (Class98_Sub34 class98_Sub34 = (Class98_Sub34)Class64_Sub8.aClass148_3659.method2418(32); class98_Sub34 != null; class98_Sub34 = (Class98_Sub34)Class64_Sub8.aClass148_3659.method2417(n ^ 0x64)) {
                        final int anInt4126 = class98_Sub34.anInt4126;
                        Class164.method2522(-(n4 / 128) + 2 + 4 * ((0x3FFF & Class64_Sub23.aClass370_3707.anIntArray3133[anInt4126] >> -86148882) + -Class272.anInt2038), Class64_Sub23.aClass370_3707.anIntArray3138[anInt4126], n3, method3469, ha, n ^ 0x8668008A, class293, n2, 2 + (-aa_Sub2.anInt3562 + (Class64_Sub23.aClass370_3707.anIntArray3133[anInt4126] & 0x3FFF)) * 4 - n5 / 128);
                    }
                    for (int n8 = 0; ~n8 > ~Class98_Sub10_Sub7.anInt5577; ++n8) {
                        final int n9 = -(n4 / 128) + 4 * Class19.anIntArray3451[n8] + 2;
                        final int n10 = 4 * Class6.anIntArray91[n8] + (2 + -(n5 / 128));
                        Class352 class294 = Class130.aClass302_1028.method3546(Class79.anIntArray603[n8], (byte)119);
                        if (class294.anIntArray2928 != null) {
                            class294 = class294.method3852(Class75.aClass140_584, (byte)(-95));
                            if (class294 == null) {
                                continue;
                            }
                            if (class294.anInt2958 == -1) {
                                continue;
                            }
                        }
                        Class164.method2522(n9, class294.anInt2958, n3, method3469, ha, -2040004466, class293, n2, n10);
                    }
                    for (Class98_Sub45 class98_Sub35 = (Class98_Sub45)Class146.aClass377_1180.method3998(97); class98_Sub35 != null; class98_Sub35 = (Class98_Sub45)Class146.aClass377_1180.method3995(-1)) {
                        if (~(int)(0x3L & class98_Sub35.aLong832 >> 1749334492) == ~Canvas_Sub1.anInt26) {
                            Class4.method173(n3, -(n5 / 128) + 2 + 4 * ((int)(0x3FFFL & class98_Sub35.aLong832 >> 65890382) + -aa_Sub2.anInt3562), n2, class293, method3469, -(n4 / 128) + (2 + 4 * ((int)(0x3FFFL & class98_Sub35.aLong832) + -Class272.anInt2038)), (byte)(-24), Class93.aClass332Array3512[0]);
                        }
                    }
                    for (int n11 = 0; Class150.anInt1211 > n11; ++n11) {
                        final Class98_Sub39 class98_Sub36 = (Class98_Sub39)Class260.aClass377_3254.method3990(Class325.anIntArray2726[n11], n - 5);
                        if (class98_Sub36 != null) {
                            final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4187 = class98_Sub36.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                            if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3052((byte)106) && ~aClass246_Sub3_Sub4_Sub2_Sub1_4187.aByte5088 == ~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088) {
                                Class141 class295 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504;
                                if (class295 != null && class295.anIntArray1109 != null) {
                                    class295 = class295.method2300(Class75.aClass140_584, (byte)78);
                                }
                                if (class295 != null && class295.aBoolean1140 && class295.aBoolean1116) {
                                    final int n12 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084 / 128 - n4 / 128;
                                    final int n13 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079 / 128 + -(n5 / 128);
                                    if (class295.anInt1118 == -1) {
                                        Class4.method173(n3, n13, n2, class293, method3469, n12, (byte)(-24), Class93.aClass332Array3512[1]);
                                    }
                                    else {
                                        Class164.method2522(n12, class295.anInt1118, n3, method3469, ha, -2040004466, class293, n2, n13);
                                    }
                                }
                            }
                        }
                    }
                    final int anInt4127 = Class2.anInt71;
                    final int[] anIntArray2705 = Class319.anIntArray2705;
                    for (int n14 = 0; ~n14 > ~anInt4127; ++n14) {
                        final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[anIntArray2705[n14]];
                        if (class246_Sub3_Sub4_Sub2_Sub2 != null && class246_Sub3_Sub4_Sub2_Sub2.method3055((byte)106) && !class246_Sub3_Sub4_Sub2_Sub2.aBoolean6523 && class246_Sub3_Sub4_Sub2_Sub2 != Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660 && ~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088 == ~class246_Sub3_Sub4_Sub2_Sub2.aByte5088) {
                            final int n15 = -(n4 / 128) + class246_Sub3_Sub4_Sub2_Sub2.anInt5084 / 128;
                            final int n16 = -(n5 / 128) + class246_Sub3_Sub4_Sub2_Sub2.anInt5079 / 128;
                            boolean b = false;
                            for (int n17 = 0; ~Class314.anInt2692 < ~n17; ++n17) {
                                if (class246_Sub3_Sub4_Sub2_Sub2.aString6537.equals(Class98_Sub25.aStringArray4026[n17]) && ~Class98_Sub26.anIntArray4030[n17] != -1) {
                                    b = true;
                                    break;
                                }
                            }
                            boolean b2 = false;
                            for (int n18 = 0; Class32.anInt308 > n18; ++n18) {
                                if (class246_Sub3_Sub4_Sub2_Sub2.aString6537.equals(Class374.aClass147Array3157[n18].aString1186)) {
                                    b2 = true;
                                    break;
                                }
                            }
                            boolean b3 = false;
                            if (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6528 != 0 && ~class246_Sub3_Sub4_Sub2_Sub2.anInt6528 != -1 && ~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6528 == ~class246_Sub3_Sub4_Sub2_Sub2.anInt6528) {
                                b3 = true;
                            }
                            if (class246_Sub3_Sub4_Sub2_Sub2.aBoolean6534) {
                                Class4.method173(n3, n16, n2, class293, method3469, n15, (byte)(-24), Class93.aClass332Array3512[6]);
                            }
                            else if (b) {
                                Class4.method173(n3, n16, n2, class293, method3469, n15, (byte)(-24), Class93.aClass332Array3512[3]);
                            }
                            else if (b2) {
                                Class4.method173(n3, n16, n2, class293, method3469, n15, (byte)(-24), Class93.aClass332Array3512[5]);
                            }
                            else if (b3) {
                                Class4.method173(n3, n16, n2, class293, method3469, n15, (byte)(-24), Class93.aClass332Array3512[4]);
                            }
                            else {
                                Class4.method173(n3, n16, n2, class293, method3469, n15, (byte)(-24), Class93.aClass332Array3512[2]);
                            }
                        }
                    }
                    final Class36[] aClass36Array903 = Class104.aClass36Array903;
                    for (int n19 = 0; ~n19 > ~aClass36Array903.length; ++n19) {
                        final Class36 class296 = aClass36Array903[n19];
                        if (class296 != null && class296.anInt346 != 0 && ~(Class215.anInt1614 % 20) > -11) {
                            if (~class296.anInt346 == 0xFFFFFFFE) {
                                final Class98_Sub39 class98_Sub37 = (Class98_Sub39)Class260.aClass377_3254.method3990(class296.anInt345, -1);
                                if (class98_Sub37 != null) {
                                    final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4188 = class98_Sub37.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                                    Class98_Sub10_Sub29.method1090(class296.anInt341, method3469, aClass246_Sub3_Sub4_Sub2_Sub1_4188.anInt5079 / 128 - n5 / 128, aClass246_Sub3_Sub4_Sub2_Sub1_4188.anInt5084 / 128 - n4 / 128, class293, n3, 360000L, n2, 4);
                                }
                            }
                            if (class296.anInt346 == 2) {
                                final int n20 = -(n4 / 128) + class296.anInt338 / 128;
                                final int n21 = -(n5 / 128) + class296.anInt347 / 128;
                                final long n22 = class296.anInt340 << 546406215;
                                Class98_Sub10_Sub29.method1090(class296.anInt341, method3469, n21, n20, class293, n3, n22 * n22, n2, 4);
                            }
                            if (class296.anInt346 == 10 && ~class296.anInt345 <= -1 && class296.anInt345 < Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030.length) {
                                final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub3 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[class296.anInt345];
                                if (class246_Sub3_Sub4_Sub2_Sub3 != null) {
                                    Class98_Sub10_Sub29.method1090(class296.anInt341, method3469, -(n5 / 128) + class246_Sub3_Sub4_Sub2_Sub3.anInt5079 / 128, class246_Sub3_Sub4_Sub2_Sub3.anInt5084 / 128 - n4 / 128, class293, n3, 360000L, n2, 4);
                                }
                            }
                        }
                    }
                    if (~Class98_Sub46_Sub20_Sub2.anInt6319 != 0xFFFFFFFB) {
                        if (~Class269.anInt2024 != -1) {
                            Class4.method173(n3, Class246_Sub3_Sub1_Sub2.anInt6251 * 4 + (2 - n5 / 128 - -(2 * (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.method3034(n ^ 0x4) - 1))), n2, class293, method3469, -(n4 / 128) + 2 + (4 * Class269.anInt2024 + (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.method3034(0) - 1) * 2), (byte)(-24), Class340.aClass332Array2848[Class365.aBoolean3110]);
                        }
                        if (!Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aBoolean6523) {
                            ha.method1760(3, 3, -1 + class293.anInt2258 / 2 + n3, -1, (byte)(-66), n2 - (-(class293.anInt2311 / 2) + 1));
                        }
                    }
                }
                if (n != 4) {
                    Class201.anInt1545 = 36;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nda.B(" + n + ',' + ((class293 != null) ? "{...}" : "null") + ',' + ((ha != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method2697(final int n, final boolean b) {
        try {
            if (n == -546) {
                int anInt6184 = Class246_Sub4_Sub2.anInt6184;
                int anInt6185 = Class64_Sub20.anInt3696;
                if (b && Class239.aBoolean1839) {
                    anInt6184 <<= 1;
                    anInt6185 = -anInt6184;
                }
                Class265.aHa1974.f(anInt6185, anInt6184);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nda.A(" + n + ',' + b + ')');
        }
    }
    
    static {
        Class201.anInt1544 = 0;
    }
}
