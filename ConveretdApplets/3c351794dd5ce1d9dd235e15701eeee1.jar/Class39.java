// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class39
{
    static Class126 aClass126_361;
    static Class85 aClass85_362;
    static int anInt363;
    static IncomingOpcode aClass58_364;
    
    static final String method349(final int n, final int n2, final int n3, long n4, final boolean b) {
        try {
            char c = ',';
            char c2 = '.';
            if (~n == -1) {
                c2 = ',';
                c = '.';
            }
            if (~n == 0xFFFFFFFD) {
                c2 = ' ';
            }
            boolean b2 = false;
            if (n3 != 48) {
                return null;
            }
            if (n4 < 0L) {
                b2 = true;
                n4 = -n4;
            }
            final StringBuffer sb = new StringBuffer(26);
            if (~n2 < -1) {
                for (int i = 0; i < n2; ++i) {
                    final int n5 = (int)n4;
                    n4 /= 10L;
                    sb.append((char)(-((int)n4 * 10) + 48 - -n5));
                }
                sb.append(c);
            }
            int n6 = 0;
            while (true) {
                final int n7 = (int)n4;
                n4 /= 10L;
                sb.append((char)(n7 + (48 - 10 * (int)n4)));
                if (~n4 == -1L) {
                    break;
                }
                if (!b || ~(++n6 % 3) != -1) {
                    continue;
                }
                sb.append(c2);
            }
            if (b2) {
                sb.append('-');
            }
            return sb.reverse().toString();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cn.E(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ')');
        }
    }
    
    static final void method350(final int n, final int n2, final int n3, final ha ha) {
        try {
            if (~n3 <= -1 && ~n2 <= -1 && ~Class98_Sub46_Sub2.anInt5952 != -1 && ~Class282.anInt2132 != -1) {
                Class111 class111;
                int anInt2132;
                int anInt2133;
                int anInt2134;
                int anInt2135;
                int n4;
                int n5;
                if (za_Sub2.aBoolean6079) {
                    Class98_Sub46_Sub14.method1604(false, (byte)88);
                    class111 = ha.method1752();
                    final int[] y = ha.Y();
                    anInt2132 = y[3];
                    anInt2133 = y[0];
                    anInt2134 = y[2];
                    anInt2135 = y[1];
                    n4 = n3 - -Class98_Sub22_Sub1.method1253(false, n ^ 0xFFFF84BF);
                    n5 = Class364.method3932(false, (byte)(-67)) + n2;
                }
                else {
                    ha.DA(Class98_Sub46_Sub11.anInt6025, Class98_Sub46_Sub20.anInt6074, Class98_Sub46_Sub2.anInt5952, Class282.anInt2132);
                    anInt2132 = Class282.anInt2132;
                    anInt2135 = Class98_Sub46_Sub20.anInt6074;
                    anInt2133 = Class98_Sub46_Sub11.anInt6025;
                    anInt2134 = Class98_Sub46_Sub2.anInt5952;
                    ha.KA(Class98_Sub10_Sub17.anInt5623, Class64_Sub10.anInt3664, Class98_Sub46_Sub2.anInt5952, Class282.anInt2132);
                    class111 = ha.method1821();
                    class111.method2093(Class64_Sub28.anInt3717, Class98_Sub42.anInt4239, Class137.anInt1079, Class356.anInt3025, Class287_Sub2.anInt3274, Class64_Sub6.anInt3655);
                    n4 = n3;
                    ha.a(class111);
                    n5 = n2;
                }
                if (anInt2134 == 0) {
                    anInt2134 = 1;
                }
                Class201.method2697(-546, true);
                if (anInt2132 == 0) {
                    anInt2132 = 1;
                }
                if (Class98_Sub46_Sub2_Sub2.aSArray6298 != null && (!Class98_Sub10_Sub9.aBoolean5585 || (Class98_Sub4.anInt3826 & 0x40) != 0x0)) {
                    int n6 = -1;
                    int n7 = -1;
                    final int i = ha.i();
                    final int xa = ha.XA();
                    int n9;
                    int n8;
                    int n11;
                    int n10;
                    if (Class239.aBoolean1839) {
                        n8 = (n9 = (-anInt2133 + n4) * Class16.anInt197 / anInt2134);
                        n10 = (n11 = Class16.anInt197 * (n5 - anInt2135) / anInt2132);
                    }
                    else {
                        n9 = (-anInt2133 + n4) * i / anInt2134;
                        n10 = xa * (n5 - anInt2135) / anInt2132;
                        n8 = xa * (n4 - anInt2133) / anInt2134;
                        n11 = (n5 + -anInt2135) * i / anInt2132;
                    }
                    final int[] array = { n9, n11, i };
                    final int[] array2 = { n8, n10, xa };
                    class111.method2108(array);
                    class111.method2108(array2);
                    final float method2753 = Class207.method2753(4, 10665, array[2], array2[0], array2[1], array[1], array2[2], array[0]);
                    if (method2753 > 0.0f) {
                        final int n12 = array2[0] + -array[0];
                        final int n13 = -array[2] + array2[2];
                        final int n14 = (int)(n12 * method2753 + array[0]);
                        final int n15 = (int)(array[2] + n13 * method2753);
                        n6 = n14 + (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.method3034(0) - 1 << 602308552) >> 503614921;
                        n7 = (-1 + Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.method3034(n) << 24452936) + n15 >> -970214583;
                        int aByte5088 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088;
                        if (aByte5088 < 3 && ~(0x2 & Class281.aByteArrayArrayArray2117[1][n14 >> 216058729][n15 >> 636707433]) != -1) {
                            ++aByte5088;
                        }
                    }
                    if (~n6 != 0x0 && ~n7 != 0x0) {
                        if (Class98_Sub10_Sub9.aBoolean5585 && (Class98_Sub4.anInt3826 & 0x40) != 0x0) {
                            if (Class246_Sub9.method3139((byte)72, Class187.anInt1450, Class310.anInt2652) != null) {
                                Class293.method3470(false, true, 0L, Class336.anInt2823, n6, " ->", true, n7, 46, n6 << 937987840 | n7, -1, false, Class287_Sub2.aString3272);
                            }
                            else {
                                Class98_Sub10_Sub32.method1098((byte)100);
                            }
                        }
                        else {
                            if (Class98_Sub46_Sub1.aBoolean5943) {
                                Class293.method3470(false, true, 0L, -1, n6, "", true, n7, 60, n6 << -896229152 | n7, -1, false, Class309.aClass309_2613.method3615(Class374.anInt3159, (byte)25));
                            }
                            Class293.method3470(false, true, 0L, Class199.anInt1541, n6, "", true, n7, 6, n6 << -1708316192 | n7, -1, false, Class218.aString1636);
                        }
                    }
                }
                if (za_Sub2.aBoolean6079) {
                    Class207.method2765((byte)119);
                }
                for (int j = n; j < (za_Sub2.aBoolean6079 ? 2 : 1); ++j) {
                    final boolean b = ~j == -1;
                    final Class84 class112 = b ? Class98_Sub10_Sub27.aClass84_5692 : Class266.aClass84_1988;
                    int n16 = n3;
                    int n17 = n2;
                    if (za_Sub2.aBoolean6079) {
                        Class98_Sub46_Sub14.method1604(b, (byte)88);
                        n16 += Class98_Sub22_Sub1.method1253(b, -31553);
                        n17 += Class364.method3932(b, (byte)(-67));
                    }
                    final Class218 aClass218_635 = class112.aClass218_635;
                    for (Class246_Sub1 class246_Sub1 = (Class246_Sub1)aClass218_635.method2803((byte)15); class246_Sub1 != null; class246_Sub1 = (Class246_Sub1)aClass218_635.method2809(false)) {
                        if ((Class246_Sub3_Sub5_Sub2.aBoolean6272 || ~class246_Sub1.aClass246_Sub3_5069.aByte5088 == ~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088) && class246_Sub1.method2969(ha, n16, n17, -66)) {
                            int aShort6158;
                            int aShort6159;
                            if (class246_Sub1.aClass246_Sub3_5069 instanceof Class246_Sub3_Sub4) {
                                aShort6158 = ((Class246_Sub3_Sub4)class246_Sub1.aClass246_Sub3_5069).aShort6158;
                                aShort6159 = ((Class246_Sub3_Sub4)class246_Sub1.aClass246_Sub3_5069).aShort6157;
                            }
                            else {
                                aShort6158 = class246_Sub1.aClass246_Sub3_5069.anInt5084 >> -1829008855;
                                aShort6159 = class246_Sub1.aClass246_Sub3_5069.anInt5079 >> -277104311;
                            }
                            if (class246_Sub1.aClass246_Sub3_5069 instanceof Class246_Sub3_Sub4_Sub2_Sub2) {
                                final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2 = (Class246_Sub3_Sub4_Sub2_Sub2)class246_Sub1.aClass246_Sub3_5069;
                                final int method2754 = class246_Sub3_Sub4_Sub2_Sub2.method3034(n);
                                if (((0x1 & method2754) == 0x0 && ~(0x1FF & class246_Sub3_Sub4_Sub2_Sub2.anInt5084) == -1 && ~(0x1FF & class246_Sub3_Sub4_Sub2_Sub2.anInt5079) == -1) || ((0x1 & method2754) && ~(0x1FF & class246_Sub3_Sub4_Sub2_Sub2.anInt5084) == 0xFFFFFEFF && (0x1FF & class246_Sub3_Sub4_Sub2_Sub2.anInt5079) == 0x100)) {
                                    final int n18 = class246_Sub3_Sub4_Sub2_Sub2.anInt5084 + -(-1 + class246_Sub3_Sub4_Sub2_Sub2.method3034(0) << -982663128);
                                    final int n19 = class246_Sub3_Sub4_Sub2_Sub2.anInt5079 + -(class246_Sub3_Sub4_Sub2_Sub2.method3034(n) - 1 << -515460824);
                                    for (int n20 = 0; ~n20 > ~Class150.anInt1211; ++n20) {
                                        final Class98_Sub39 class98_Sub39 = (Class98_Sub39)Class260.aClass377_3254.method3990(Class325.anIntArray2726[n20], n - 1);
                                        if (class98_Sub39 != null) {
                                            final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4187 = class98_Sub39.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                                            if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6411 != Class215.anInt1614 && aClass246_Sub3_Sub4_Sub2_Sub1_4187.aBoolean6371) {
                                                final int n21 = -(-1 + aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.anInt1112 << -1071692376) + aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084;
                                                final int n22 = -(aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.anInt1112 - 1 << -1646493944) + aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079;
                                                if (~n21 <= ~n18 && ~aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.anInt1112 >= ~(-(n21 - n18 >> 1292283049) + class246_Sub3_Sub4_Sub2_Sub2.method3034(n)) && ~n19 >= ~n22 && ~aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.anInt1112 >= ~(class246_Sub3_Sub4_Sub2_Sub2.method3034(0) + -(-n19 + n22 >> -1124961751))) {
                                                    Class98_Sub10.method995(aClass246_Sub3_Sub4_Sub2_Sub1_4187, (byte)55, class246_Sub1.aClass246_Sub3_5069.aByte5088 != Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088);
                                                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6411 = Class215.anInt1614;
                                                }
                                            }
                                        }
                                    }
                                    final int anInt2136 = Class2.anInt71;
                                    final int[] anIntArray2705 = Class319.anIntArray2705;
                                    for (int n23 = 0; ~anInt2136 < ~n23; ++n23) {
                                        final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub3 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[anIntArray2705[n23]];
                                        if (class246_Sub3_Sub4_Sub2_Sub3 != null && class246_Sub3_Sub4_Sub2_Sub3.anInt6411 != Class215.anInt1614 && class246_Sub3_Sub4_Sub2_Sub3 != class246_Sub3_Sub4_Sub2_Sub2 && class246_Sub3_Sub4_Sub2_Sub3.aBoolean6371) {
                                            final int n24 = class246_Sub3_Sub4_Sub2_Sub3.anInt5084 + -(-1 + class246_Sub3_Sub4_Sub2_Sub3.method3034(0) << 1331379528);
                                            final int n25 = class246_Sub3_Sub4_Sub2_Sub3.anInt5079 + -(-1 + class246_Sub3_Sub4_Sub2_Sub3.method3034(0) << -453541336);
                                            if (n24 >= n18 && class246_Sub3_Sub4_Sub2_Sub3.method3034(n) <= -(n24 + -n18 >> -688451639) + class246_Sub3_Sub4_Sub2_Sub2.method3034(0) && ~n19 >= ~n25 && class246_Sub3_Sub4_Sub2_Sub3.method3034(n) <= -(-n19 + n25 >> 1317624233) + class246_Sub3_Sub4_Sub2_Sub2.method3034(0)) {
                                                Class98_Sub30.method1311(~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088 != ~class246_Sub1.aClass246_Sub3_5069.aByte5088, true, class246_Sub3_Sub4_Sub2_Sub3);
                                                class246_Sub3_Sub4_Sub2_Sub3.anInt6411 = Class215.anInt1614;
                                            }
                                        }
                                    }
                                }
                                if (~class246_Sub3_Sub4_Sub2_Sub2.anInt6411 == ~Class215.anInt1614) {
                                    continue;
                                }
                                Class98_Sub30.method1311(~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088 != ~class246_Sub1.aClass246_Sub3_5069.aByte5088, true, class246_Sub3_Sub4_Sub2_Sub2);
                                class246_Sub3_Sub4_Sub2_Sub2.anInt6411 = Class215.anInt1614;
                            }
                            if (class246_Sub1.aClass246_Sub3_5069 instanceof Class246_Sub3_Sub4_Sub2_Sub1) {
                                final Class246_Sub3_Sub4_Sub2_Sub1 class246_Sub3_Sub4_Sub2_Sub4 = (Class246_Sub3_Sub4_Sub2_Sub1)class246_Sub1.aClass246_Sub3_5069;
                                if (class246_Sub3_Sub4_Sub2_Sub4.aClass141_6504 != null) {
                                    if ((~(0x1 & class246_Sub3_Sub4_Sub2_Sub4.aClass141_6504.anInt1112) == -1 && ~(0x1FF & class246_Sub3_Sub4_Sub2_Sub4.anInt5084) == -1 && (class246_Sub3_Sub4_Sub2_Sub4.anInt5079 & 0x1FF) == 0x0) || ((0x1 & class246_Sub3_Sub4_Sub2_Sub4.aClass141_6504.anInt1112) == 0x1 && ~(class246_Sub3_Sub4_Sub2_Sub4.anInt5084 & 0x1FF) == 0xFFFFFEFF && (class246_Sub3_Sub4_Sub2_Sub4.anInt5079 & 0x1FF) == 0x100)) {
                                        final int n26 = -(-1 + class246_Sub3_Sub4_Sub2_Sub4.aClass141_6504.anInt1112 << -685347000) + class246_Sub3_Sub4_Sub2_Sub4.anInt5084;
                                        final int n27 = class246_Sub3_Sub4_Sub2_Sub4.anInt5079 + -(class246_Sub3_Sub4_Sub2_Sub4.aClass141_6504.anInt1112 - 1 << 986207144);
                                        for (int n28 = 0; ~Class150.anInt1211 < ~n28; ++n28) {
                                            final Class98_Sub39 class98_Sub40 = (Class98_Sub39)Class260.aClass377_3254.method3990(Class325.anIntArray2726[n28], -1);
                                            if (class98_Sub40 != null) {
                                                final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4188 = class98_Sub40.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                                                if (Class215.anInt1614 != aClass246_Sub3_Sub4_Sub2_Sub1_4188.anInt6411 && class246_Sub3_Sub4_Sub2_Sub4 != aClass246_Sub3_Sub4_Sub2_Sub1_4188 && aClass246_Sub3_Sub4_Sub2_Sub1_4188.aBoolean6371) {
                                                    final int n29 = aClass246_Sub3_Sub4_Sub2_Sub1_4188.anInt5084 - (aClass246_Sub3_Sub4_Sub2_Sub1_4188.aClass141_6504.anInt1112 - 1 << -1718624696);
                                                    final int n30 = aClass246_Sub3_Sub4_Sub2_Sub1_4188.anInt5079 + -(-1 + aClass246_Sub3_Sub4_Sub2_Sub1_4188.aClass141_6504.anInt1112 << -559324184);
                                                    if (~n26 >= ~n29 && class246_Sub3_Sub4_Sub2_Sub4.aClass141_6504.anInt1112 - (-n26 + n29 >> -1827274359) >= aClass246_Sub3_Sub4_Sub2_Sub1_4188.aClass141_6504.anInt1112 && n30 >= n27 && ~(-(n30 - n27 >> 1989017673) + class246_Sub3_Sub4_Sub2_Sub4.aClass141_6504.anInt1112) <= ~aClass246_Sub3_Sub4_Sub2_Sub1_4188.aClass141_6504.anInt1112) {
                                                        Class98_Sub10.method995(aClass246_Sub3_Sub4_Sub2_Sub1_4188, (byte)55, Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088 != class246_Sub1.aClass246_Sub3_5069.aByte5088);
                                                        aClass246_Sub3_Sub4_Sub2_Sub1_4188.anInt6411 = Class215.anInt1614;
                                                    }
                                                }
                                            }
                                        }
                                        final int anInt2137 = Class2.anInt71;
                                        final int[] anIntArray2706 = Class319.anIntArray2705;
                                        for (int n31 = 0; ~anInt2137 < ~n31; ++n31) {
                                            final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub5 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[anIntArray2706[n31]];
                                            if (class246_Sub3_Sub4_Sub2_Sub5 != null && ~class246_Sub3_Sub4_Sub2_Sub5.anInt6411 != ~Class215.anInt1614 && class246_Sub3_Sub4_Sub2_Sub5.aBoolean6371) {
                                                final int n32 = class246_Sub3_Sub4_Sub2_Sub5.anInt5084 - (class246_Sub3_Sub4_Sub2_Sub5.method3034(0) - 1 << 714231912);
                                                final int n33 = class246_Sub3_Sub4_Sub2_Sub5.anInt5079 - (-1 + class246_Sub3_Sub4_Sub2_Sub5.method3034(0) << 1201196744);
                                                if (n32 >= n26 && ~class246_Sub3_Sub4_Sub2_Sub5.method3034(0) >= ~(-(-n26 + n32 >> -857371959) + class246_Sub3_Sub4_Sub2_Sub4.aClass141_6504.anInt1112) && n33 >= n27 && ~class246_Sub3_Sub4_Sub2_Sub5.method3034(0) >= ~(class246_Sub3_Sub4_Sub2_Sub4.aClass141_6504.anInt1112 + -(n33 - n27 >> 317921065))) {
                                                    Class98_Sub30.method1311(Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088 != class246_Sub1.aClass246_Sub3_5069.aByte5088, true, class246_Sub3_Sub4_Sub2_Sub5);
                                                    class246_Sub3_Sub4_Sub2_Sub5.anInt6411 = Class215.anInt1614;
                                                }
                                            }
                                        }
                                    }
                                    if (~class246_Sub3_Sub4_Sub2_Sub4.anInt6411 == ~Class215.anInt1614) {
                                        continue;
                                    }
                                    Class98_Sub10.method995(class246_Sub3_Sub4_Sub2_Sub4, (byte)55, class246_Sub1.aClass246_Sub3_5069.aByte5088 != Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088);
                                    class246_Sub3_Sub4_Sub2_Sub4.anInt6411 = Class215.anInt1614;
                                }
                            }
                            if (class246_Sub1.aClass246_Sub3_5069 instanceof Class246_Sub3_Sub2_Sub1) {
                                final Class98_Sub45 class98_Sub41 = (Class98_Sub45)Class146.aClass377_1180.method3990(Class272.anInt2038 + aShort6158 | (aShort6159 - -aa_Sub2.anInt3562 << 229022126 | class246_Sub1.aClass246_Sub3_5069.aByte5088 << 808463964), -1);
                                if (class98_Sub41 != null) {
                                    int n34 = 0;
                                    for (Class98_Sub26 class98_Sub42 = (Class98_Sub26)class98_Sub41.aClass148_4254.method2427(n ^ 0xFFFFFF93); class98_Sub42 != null; class98_Sub42 = (Class98_Sub26)class98_Sub41.aClass148_4254.method2416((byte)(-121)), ++n34) {
                                        final Class297 method2755 = Class98_Sub46_Sub19.aClass205_6068.method2714(class98_Sub42.anInt4031, (byte)(-126));
                                        if (Class98_Sub10_Sub9.aBoolean5585 && ~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088 == ~class246_Sub1.aClass246_Sub3_5069.aByte5088) {
                                            final Class149 class113 = (~Class98_Sub46_Sub1.anInt5945 != 0x0) ? Class98_Sub43_Sub1.aClass365_5897.method3940((byte)31, Class98_Sub46_Sub1.anInt5945) : null;
                                            if ((Class98_Sub4.anInt3826 & 0x1) != 0x0 && (class113 == null || method2755.method3494(Class98_Sub46_Sub1.anInt5945, (byte)(-89), class113.anInt1202) != class113.anInt1202)) {
                                                Class293.method3470(false, true, class98_Sub42.anInt4031, Class336.anInt2823, aShort6158, Class246_Sub3_Sub3.aString6156 + " -> <col=ff9040>" + method2755.aString2450, false, aShort6159, 58, n34, -1, false, Class287_Sub2.aString3272);
                                            }
                                        }
                                        if (~class246_Sub1.aClass246_Sub3_5069.aByte5088 == ~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088) {
                                            final String[] aStringArray2446 = method2755.aStringArray2446;
                                            for (int n35 = 4; ~n35 <= -1; --n35) {
                                                if (aStringArray2446 != null && aStringArray2446[n35] != null) {
                                                    int n36 = 0;
                                                    if (~n35 == -1) {
                                                        n36 = 13;
                                                    }
                                                    int n37 = Class284_Sub2.anInt5186;
                                                    if (n35 == 1) {
                                                        n36 = 23;
                                                    }
                                                    if (n35 == 2) {
                                                        n36 = 2;
                                                    }
                                                    if (~n35 == 0xFFFFFFFC) {
                                                        n36 = 30;
                                                    }
                                                    if (~n35 == ~method2755.anInt2438) {
                                                        n37 = method2755.anInt2439;
                                                    }
                                                    if (~n35 == 0xFFFFFFFB) {
                                                        n36 = 18;
                                                    }
                                                    if (~method2755.anInt2421 == ~n35) {
                                                        n37 = method2755.anInt2471;
                                                    }
                                                    Class293.method3470(false, true, class98_Sub42.anInt4031, n37, aShort6158, "<col=ff9040>" + method2755.aString2450, false, aShort6159, n36, n34, -1, false, aStringArray2446[n35]);
                                                }
                                            }
                                        }
                                        Class293.method3470(false, true, class98_Sub42.anInt4031, Class16.anInt190, aShort6158, "<col=ff9040>" + method2755.aString2450, false, aShort6159, 1008, n34, -1, class246_Sub1.aClass246_Sub3_5069.aByte5088 != Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088, Class309.aClass309_2608.method3615(Class374.anInt3159, (byte)25));
                                    }
                                }
                            }
                            if (class246_Sub1.aClass246_Sub3_5069 instanceof Interface19) {
                                final Interface19 interface19 = (Interface19)class246_Sub1.aClass246_Sub3_5069;
                                Class352 class114 = Class130.aClass302_1028.method3546(interface19.method64(n ^ 0x7708), (byte)119);
                                if (class114.anIntArray2928 != null) {
                                    class114 = class114.method3852(Class75.aClass140_584, (byte)(-55));
                                }
                                if (class114 != null) {
                                    if (Class98_Sub10_Sub9.aBoolean5585 && ~class246_Sub1.aClass246_Sub3_5069.aByte5088 == ~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088) {
                                        final Class149 class115 = (~Class98_Sub46_Sub1.anInt5945 == 0x0) ? null : Class98_Sub43_Sub1.aClass365_5897.method3940((byte)31, Class98_Sub46_Sub1.anInt5945);
                                        if (~(0x4 & Class98_Sub4.anInt3826) != -1 && (class115 == null || class114.method3866(class115.anInt1202, Class98_Sub46_Sub1.anInt5945, n ^ 0x1) != class115.anInt1202)) {
                                            Class293.method3470(false, true, Class98_Sub10_Sub39.method1120(interface19, (byte)113, aShort6158, aShort6159), Class336.anInt2823, aShort6158, Class246_Sub3_Sub3.aString6156 + " -> <col=00ffff>" + class114.name, false, aShort6159, 50, interface19.hashCode(), -1, false, Class287_Sub2.aString3272);
                                        }
                                    }
                                    if (~class246_Sub1.aClass246_Sub3_5069.aByte5088 == ~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088) {
                                        final String[] aStringArray2447 = class114.aStringArray2939;
                                        if (aStringArray2447 != null) {
                                            for (int k = 4; k >= 0; --k) {
                                                if (aStringArray2447[k] != null) {
                                                    int n38 = 0;
                                                    if (~k == -1) {
                                                        n38 = 15;
                                                    }
                                                    int n39 = Class284_Sub2.anInt5186;
                                                    if (k == 1) {
                                                        n38 = 4;
                                                    }
                                                    if (k == 2) {
                                                        n38 = 8;
                                                    }
                                                    if (~k == 0xFFFFFFFC) {
                                                        n38 = 16;
                                                    }
                                                    if (k == class114.anInt3002) {
                                                        n39 = class114.anInt3008;
                                                    }
                                                    if (k == 4) {
                                                        n38 = 1007;
                                                    }
                                                    if (~class114.anInt2933 == ~k) {
                                                        n39 = class114.anInt2977;
                                                    }
                                                    Class293.method3470(false, true, Class98_Sub10_Sub39.method1120(interface19, (byte)113, aShort6158, aShort6159), n39, aShort6158, "<col=00ffff>" + class114.name, false, aShort6159, n38, interface19.hashCode(), -1, false, aStringArray2447[k]);
                                                }
                                            }
                                        }
                                        Class293.method3470(false, true, class114.id, Class16.anInt190, aShort6158, "<col=00ffff>" + class114.name, false, aShort6159, 1009, interface19.hashCode(), -1, ~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088 != ~class246_Sub1.aClass246_Sub3_5069.aByte5088, Class309.aClass309_2608.method3615(Class374.anInt3159, (byte)25));
                                    }
                                }
                            }
                        }
                    }
                    if (za_Sub2.aBoolean6079) {
                        Class207.method2765((byte)53);
                    }
                }
                Class201.method2697(-546, false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cn.F(" + n + ',' + n2 + ',' + n3 + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method351(final boolean b) {
        try {
            Class39.aClass85_362 = null;
            if (!b) {
                Class39.anInt363 = -29;
            }
            Class39.aClass126_361 = null;
            Class39.aClass58_364 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cn.D(" + b + ')');
        }
    }
    
    abstract void method352(final int p0, final int p1, final float p2, final int p3, final float p4, final int p5, final float p6, final float p7, final float[] p8, final int p9, final int p10);
    
    static final void method353(final byte b) {
        try {
            if (Class153.aHa1225 != null) {
                Class153.aHa1225.method1743(-1);
                Class238.aClass43_1828 = null;
                Class153.aHa1225 = null;
            }
            if (b != -79) {
                Class39.aClass85_362 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cn.G(" + b + ')');
        }
    }
    
    static {
        Class39.aClass126_361 = new Class126();
        Class39.aClass85_362 = new Class85(14, 8);
        Class39.aClass58_364 = new IncomingOpcode(25, 0);
    }
}
