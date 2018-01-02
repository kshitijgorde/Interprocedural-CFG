// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub34 extends Class98
{
    int anInt4126;
    static Class207 aClass207_4127;
    static IncomingOpcode aClass58_4128;
    
    static final void method1450(final int n, final int n2, final int n3, final int n4, final byte b, final int n5, final int n6) {
        try {
            final int anInt71 = Class2.anInt71;
            final int[] anIntArray2705 = Class319.anIntArray2705;
            Class48_Sub2_Sub1.anInt5532 = 0;
            for (int n7 = 0; ~n7 > ~(Class150.anInt1211 + anInt71); ++n7) {
                Class141 class141 = null;
                Class246_Sub3_Sub4_Sub2 aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                if (~n7 <= ~anInt71) {
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187 = ((Class98_Sub39)Class260.aClass377_3254.method3990(Class325.anIntArray2726[-anInt71 + n7], -1)).aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                    class141 = ((Class246_Sub3_Sub4_Sub2_Sub1)aClass246_Sub3_Sub4_Sub2_Sub1_4187).aClass141_6504;
                    if (class141.anIntArray1109 != null) {
                        class141 = class141.method2300(Class75.aClass140_584, (byte)127);
                        if (class141 == null) {
                            continue;
                        }
                    }
                }
                else {
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[anIntArray2705[n7]];
                }
                if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6372 >= 0 && (~Class64_Sub15.anInt3676 == ~aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6417 || aClass246_Sub3_Sub4_Sub2_Sub1_4187.aByte5088 == Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088)) {
                    Class17.method243(aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3044(false), n2 >> 620118273, -100, n4, aClass246_Sub3_Sub4_Sub2_Sub1_4187, n3 >> -120706623, n);
                    if (~Class259.anIntArray1957[0] <= -1) {
                        if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.aString6374 != null && (~anInt71 >= ~n7 || ~Class265.anInt1983 == -1 || Class265.anInt1983 == 3 || (Class265.anInt1983 == 1 && Class256_Sub1.method3195(0, ((Class246_Sub3_Sub4_Sub2_Sub2)aClass246_Sub3_Sub4_Sub2_Sub1_4187).aString6537))) && ~Class230.anInt1725 < ~Class48_Sub2_Sub1.anInt5532) {
                            Class230.anIntArray1729[Class48_Sub2_Sub1.anInt5532] = Class42_Sub1.aClass197_5354.method2674(aClass246_Sub3_Sub4_Sub2_Sub1_4187.aString6374, 124) / 2;
                            Class230.anIntArray1724[Class48_Sub2_Sub1.anInt5532] = Class259.anIntArray1957[0];
                            Class230.anIntArray1730[Class48_Sub2_Sub1.anInt5532] = Class259.anIntArray1957[1];
                            Class230.anIntArray1726[Class48_Sub2_Sub1.anInt5532] = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6398;
                            Class230.anIntArray1728[Class48_Sub2_Sub1.anInt5532] = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6402;
                            Class230.anIntArray1722[Class48_Sub2_Sub1.anInt5532] = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6384;
                            Class230.aStringArray1721[Class48_Sub2_Sub1.anInt5532] = aClass246_Sub3_Sub4_Sub2_Sub1_4187.aString6374;
                            ++Class48_Sub2_Sub1.anInt5532;
                        }
                        final int n8 = n6 + Class259.anIntArray1957[1];
                        int n12;
                        if (!aClass246_Sub3_Sub4_Sub2_Sub1_4187.aBoolean6371 && ~Class215.anInt1614 > ~aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6418) {
                            int n9 = 1;
                            int n10;
                            if (anInt71 <= n7) {
                                n10 = class141.anInt1127;
                                if (~n10 == 0x0) {
                                    n10 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3039(b ^ 0xFFFFFF85).anInt2381;
                                }
                            }
                            else {
                                final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[anIntArray2705[n7]];
                                n10 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3039(1).anInt2381;
                                if (class246_Sub3_Sub4_Sub2_Sub2.aBoolean6534) {
                                    n9 = 2;
                                }
                            }
                            Class332[] aClass332Array1943 = Class254.aClass332Array1943;
                            if (n10 != -1) {
                                Class332[] array = (Class332[])Class275.aClass79_2046.method802(-128, n10);
                                if (array == null) {
                                    final Class324[] method3680 = Class324.method3680(Class332_Sub2.aClass207_5423, n10, 0);
                                    if (method3680 != null) {
                                        array = new Class332[method3680.length];
                                        for (int n11 = 0; ~method3680.length < ~n11; ++n11) {
                                            array[n11] = Class265.aHa1974.method1758(method3680[n11], true);
                                        }
                                        Class275.aClass79_2046.method805(n10, array, (byte)(-80));
                                    }
                                }
                                if (array != null && array.length >= 2) {
                                    aClass332Array1943 = array;
                                }
                            }
                            if (aClass332Array1943.length <= n9) {
                                n9 = 1;
                            }
                            final Class332 class142 = aClass332Array1943[0];
                            final Class332 class143 = aClass332Array1943[n9];
                            n12 = n8 - Math.max(Class42_Sub1.aClass197_5354.anInt1517, class142.method3731());
                            final int n13 = n5 + (Class259.anIntArray1957[0] - (class142.method3734() >> -1452536927));
                            int n14 = class142.method3734() * aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6429 / 255;
                            final int method3681 = class142.method3731();
                            if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6429 > 0 && n14 < 2) {
                                n14 = 2;
                            }
                            class142.method3735(n13, n12);
                            Class265.aHa1974.T(n13, n12, n13 + n14, method3681 + n12);
                            class143.method3735(n13, n12);
                            Class265.aHa1974.KA(n5, n6, n2 + n5, n6 - -n3);
                            Class93_Sub1_Sub1.method908(n12 + method3681, n12, false, n13, class142.method3737() + n13);
                        }
                        else {
                            n12 = n8 - Math.max(Class42_Sub1.aClass197_5354.anInt1517, Class254.aClass332Array1943[0].method3731());
                        }
                        n12 -= 2;
                        if (!aClass246_Sub3_Sub4_Sub2_Sub1_4187.aBoolean6371) {
                            if (~aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6412 < ~Class215.anInt1614) {
                                Class332 class144 = Class177.aClass332Array1382[aClass246_Sub3_Sub4_Sub2_Sub1_4187.aBoolean6348 ? 2 : 0];
                                Class332 class145 = Class177.aClass332Array1382[aClass246_Sub3_Sub4_Sub2_Sub1_4187.aBoolean6348 ? 3 : 1];
                                int n15;
                                if (!(aClass246_Sub3_Sub4_Sub2_Sub1_4187 instanceof Class246_Sub3_Sub4_Sub2_Sub1)) {
                                    n15 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3039(1).anInt2374;
                                }
                                else {
                                    n15 = class141.anInt1100;
                                    if (n15 == -1) {
                                        n15 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3039(1).anInt2374;
                                    }
                                }
                                if (n15 != -1) {
                                    Class332[] array2 = (Class332[])Class224_Sub3.aClass79_5039.method802(-126, n15);
                                    if (array2 == null) {
                                        final Class324[] method3682 = Class324.method3680(Class332_Sub2.aClass207_5423, n15, 0);
                                        if (method3682 != null) {
                                            array2 = new Class332[method3682.length];
                                            for (int n16 = 0; ~n16 > ~method3682.length; ++n16) {
                                                array2[n16] = Class265.aHa1974.method1758(method3682[n16], true);
                                            }
                                            Class224_Sub3.aClass79_5039.method805(n15, array2, (byte)(-80));
                                        }
                                    }
                                    if (array2 != null && array2.length == 4) {
                                        class144 = array2[aClass246_Sub3_Sub4_Sub2_Sub1_4187.aBoolean6348 ? 2 : 0];
                                        class145 = array2[aClass246_Sub3_Sub4_Sub2_Sub1_4187.aBoolean6348 ? 3 : 1];
                                    }
                                }
                                final int n17 = -Class215.anInt1614 + aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6412;
                                int method3683;
                                if (~aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6394 <= ~n17) {
                                    method3683 = class144.method3734();
                                }
                                else {
                                    final int n18 = n17 - aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6394;
                                    method3683 = class144.method3734() * ((~aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6401 != -1) ? ((-n18 + aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6420) / aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6401 * aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6401) : 0) / aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6420;
                                }
                                final int method3684 = class144.method3731();
                                n12 -= method3684;
                                final int n19 = Class259.anIntArray1957[0] + (n5 + -(class144.method3734() >> 1704497793));
                                class144.method3735(n19, n12);
                                Class265.aHa1974.T(n19, n12, method3683 + n19, n12 - -method3684);
                                class145.method3735(n19, n12);
                                Class265.aHa1974.KA(n5, n6, n2 + n5, n6 + n3);
                                Class93_Sub1_Sub1.method908(method3684 + n12, n12, false, n19, class144.method3737() + n19);
                                n12 -= 2;
                            }
                            if (n7 >= anInt71) {
                                if (~class141.anInt1113 <= -1 && ~class141.anInt1113 > ~Class2.aClass332Array72.length) {
                                    final Class332 class146 = Class2.aClass332Array72[class141.anInt1113];
                                    n12 -= 25;
                                    class146.method3735(n5 - -Class259.anIntArray1957[0] - (class146.method3734() >> -1760821695), n12);
                                    Class93_Sub1_Sub1.method908(class146.method3749() + n12, n12, false, n5 - (-Class259.anIntArray1957[0] + (class146.method3734() >> 845665)), Class259.anIntArray1957[0] + n5 + (-(class146.method3734() >> 1759618049) + class146.method3737()));
                                    n12 -= 2;
                                }
                            }
                            else {
                                final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub3 = (Class246_Sub3_Sub4_Sub2_Sub2)aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                                if (class246_Sub3_Sub4_Sub2_Sub3.anInt6515 != -1) {
                                    n12 -= 25;
                                    final Class332 class147 = Class119_Sub4.aClass332Array4739[class246_Sub3_Sub4_Sub2_Sub3.anInt6515];
                                    class147.method3735(n5 + (Class259.anIntArray1957[0] - 12), n12);
                                    Class93_Sub1_Sub1.method908(class147.method3749() + n12, n12, false, n5 - (-Class259.anIntArray1957[0] + 12), Class259.anIntArray1957[0] + (n5 - 12 + class147.method3737()));
                                    n12 -= 2;
                                }
                                if (~class246_Sub3_Sub4_Sub2_Sub3.anInt6530 != 0x0) {
                                    n12 -= 25;
                                    final Class332 class148 = Class2.aClass332Array72[class246_Sub3_Sub4_Sub2_Sub3.anInt6530];
                                    class148.method3735(-12 + (n5 - -Class259.anIntArray1957[0]), n12);
                                    Class93_Sub1_Sub1.method908(n12 - -class148.method3749(), n12, false, Class259.anIntArray1957[0] + n5 - 12, n5 + (Class259.anIntArray1957[0] - 12 - -class148.method3737()));
                                    n12 -= 2;
                                }
                            }
                        }
                        if (aClass246_Sub3_Sub4_Sub2_Sub1_4187 instanceof Class246_Sub3_Sub4_Sub2_Sub2) {
                            if (~n7 <= -1) {
                                int method3685 = 0;
                                final Class36[] aClass36Array903 = Class104.aClass36Array903;
                                for (int i = 0; i < aClass36Array903.length; ++i) {
                                    final Class36 class149 = aClass36Array903[i];
                                    if (class149 != null && ~class149.anInt346 == 0xFFFFFFF5 && class149.anInt345 == anIntArray2705[n7]) {
                                        final Class332 class150 = Class306.aClass332Array2557[class149.anInt341];
                                        if (~method3685 > ~class150.method3731()) {
                                            method3685 = class150.method3731();
                                        }
                                        class150.method3735(Class259.anIntArray1957[0] + (n5 - 12), -class150.method3731() + n12);
                                        Class93_Sub1_Sub1.method908(-class150.method3731() + n12 - -class150.method3749(), n12 - class150.method3731(), false, Class259.anIntArray1957[0] + (n5 - 12), n5 - (-Class259.anIntArray1957[0] + 12) - -class150.method3737());
                                    }
                                }
                                if (method3685 > 0) {}
                            }
                        }
                        else {
                            int method3686 = 0;
                            final Class36[] aClass36Array904 = Class104.aClass36Array903;
                            for (int n20 = 0; aClass36Array904.length > n20; ++n20) {
                                final Class36 class151 = aClass36Array904[n20];
                                if (class151 != null && class151.anInt346 == 1 && ~class151.anInt345 == ~Class325.anIntArray2726[-anInt71 + n7]) {
                                    final Class332 class152 = Class306.aClass332Array2557[class151.anInt341];
                                    if (class152.method3731() > method3686) {
                                        method3686 = class152.method3731();
                                    }
                                    if (~(Class215.anInt1614 % 20) > -11) {
                                        class152.method3735(-12 + (Class259.anIntArray1957[0] + n5), n12 + -class152.method3731());
                                        Class93_Sub1_Sub1.method908(n12 - class152.method3731() - -class152.method3749(), n12 + -class152.method3731(), false, -12 + (Class259.anIntArray1957[0] + n5), -12 + Class259.anIntArray1957[0] + (n5 + class152.method3737()));
                                    }
                                }
                            }
                            if (~method3686 < -1) {}
                        }
                        for (int j = 0; j < Class362.anInt3090; ++j) {
                            final int n21 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6375[j];
                            final int n22 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6430[j];
                            Class86 method3687 = null;
                            int anInt72 = 0;
                            if (~n22 <= -1) {
                                if (~Class215.anInt1614 <= ~n21) {
                                    continue;
                                }
                                method3687 = Class246_Sub3_Sub1.aClass121_6150.method2194(b + 87, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6430[j]);
                                anInt72 = method3687.anInt651;
                            }
                            else if (n21 < 0) {
                                continue;
                            }
                            final int n23 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6386[j];
                            Class86 method3688 = null;
                            if (n23 >= 0) {
                                method3688 = Class246_Sub3_Sub1.aClass121_6150.method2194(94, n23);
                            }
                            if (~Class215.anInt1614 <= ~(n21 - anInt72)) {
                                final int anInt73 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6397[j];
                                if (anInt73 >= 0) {
                                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6418 = 300 + Class215.anInt1614;
                                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6429 = anInt73;
                                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6397[j] = -1;
                                }
                                if (method3687 == null) {
                                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6375[j] = -1;
                                }
                                else {
                                    Class17.method243(aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3044(false) / 2, n2 >> 1162505793, 92, n4, aClass246_Sub3_Sub4_Sub2_Sub1_4187, n3 >> 649783713, n);
                                    if (~Class259.anIntArray1957[0] < 0) {
                                        final int[] anIntArray2706 = Class259.anIntArray1957;
                                        final int n24 = 0;
                                        anIntArray2706[n24] += Class57.anIntArray457[j];
                                        final int[] anIntArray2707 = Class259.anIntArray1957;
                                        final int n25 = 1;
                                        anIntArray2707[n25] += Class235.anIntArray1764[j];
                                        int method3689 = 0;
                                        int method3690 = 0;
                                        int method3691 = 0;
                                        int method3692 = 0;
                                        int n26 = 0;
                                        int n27 = 0;
                                        int n28 = 0;
                                        int n29 = 0;
                                        Class332 method3693 = null;
                                        Class332 method3694 = null;
                                        Class332 method3695 = null;
                                        Class332 method3696 = null;
                                        int method3697 = 0;
                                        int method3698 = 0;
                                        int method3699 = 0;
                                        int method3700 = 0;
                                        int n30 = 0;
                                        int n31 = 0;
                                        int n32 = 0;
                                        int n33 = 0;
                                        final Class332 method3701 = method3687.method846(false, Class265.aHa1974);
                                        int n34 = 0;
                                        if (method3701 != null) {
                                            method3689 = method3701.method3734();
                                            final int method3702 = method3701.method3731();
                                            method3701.method3741(Class284_Sub1_Sub1.anIntArray6190);
                                            if (~method3702 < ~n34) {
                                                n34 = method3702;
                                            }
                                            n26 = Class284_Sub1_Sub1.anIntArray6190[0];
                                        }
                                        final Class332 method3703 = method3687.method852(40, Class265.aHa1974);
                                        if (method3703 != null) {
                                            method3690 = method3703.method3734();
                                            final int method3704 = method3703.method3731();
                                            method3703.method3741(Class284_Sub1_Sub1.anIntArray6190);
                                            if (method3704 > n34) {
                                                n34 = method3704;
                                            }
                                            n27 = Class284_Sub1_Sub1.anIntArray6190[0];
                                        }
                                        final Class332 method3705 = method3687.method849(Class265.aHa1974, b ^ 0x17);
                                        if (method3705 != null) {
                                            method3691 = method3705.method3734();
                                            final int method3706 = method3705.method3731();
                                            method3705.method3741(Class284_Sub1_Sub1.anIntArray6190);
                                            if (n34 < method3706) {
                                                n34 = method3706;
                                            }
                                            n28 = Class284_Sub1_Sub1.anIntArray6190[0];
                                        }
                                        final Class332 method3707 = method3687.method850(-1, Class265.aHa1974);
                                        if (method3707 != null) {
                                            method3692 = method3707.method3734();
                                            final int method3708 = method3707.method3731();
                                            if (~method3708 < ~n34) {
                                                n34 = method3708;
                                            }
                                            method3707.method3741(Class284_Sub1_Sub1.anIntArray6190);
                                            n29 = Class284_Sub1_Sub1.anIntArray6190[0];
                                        }
                                        if (method3688 != null) {
                                            method3693 = method3688.method846(false, Class265.aHa1974);
                                            if (method3693 != null) {
                                                method3697 = method3693.method3734();
                                                final int method3709 = method3693.method3731();
                                                if (~n34 > ~method3709) {
                                                    n34 = method3709;
                                                }
                                                method3693.method3741(Class284_Sub1_Sub1.anIntArray6190);
                                                n30 = Class284_Sub1_Sub1.anIntArray6190[0];
                                            }
                                            method3694 = method3688.method852(63, Class265.aHa1974);
                                            if (method3694 != null) {
                                                method3698 = method3694.method3734();
                                                final int method3710 = method3694.method3731();
                                                method3694.method3741(Class284_Sub1_Sub1.anIntArray6190);
                                                if (~n34 > ~method3710) {
                                                    n34 = method3710;
                                                }
                                                n31 = Class284_Sub1_Sub1.anIntArray6190[0];
                                            }
                                            method3695 = method3688.method849(Class265.aHa1974, b ^ 0x7D);
                                            if (method3695 != null) {
                                                method3699 = method3695.method3734();
                                                final int method3711 = method3695.method3731();
                                                if (method3711 > n34) {
                                                    n34 = method3711;
                                                }
                                                method3695.method3741(Class284_Sub1_Sub1.anIntArray6190);
                                                n32 = Class284_Sub1_Sub1.anIntArray6190[0];
                                            }
                                            method3696 = method3688.method850(b ^ 0x7B, Class265.aHa1974);
                                            if (method3696 != null) {
                                                method3700 = method3696.method3734();
                                                final int method3712 = method3696.method3731();
                                                method3696.method3741(Class284_Sub1_Sub1.anIntArray6190);
                                                if (~n34 > ~method3712) {
                                                    n34 = method3712;
                                                }
                                                n33 = Class284_Sub1_Sub1.anIntArray6190[0];
                                            }
                                        }
                                        Class43 aClass43_5336 = Class69_Sub2.aClass43_5336;
                                        Class43 aClass43_5337 = Class69_Sub2.aClass43_5336;
                                        Class197 aClass197_6296 = Class98_Sub46_Sub2_Sub2.aClass197_6296;
                                        final int anInt74 = method3687.anInt655;
                                        Class197 aClass197_6297 = Class98_Sub46_Sub2_Sub2.aClass197_6296;
                                        if (anInt74 >= 0) {
                                            final Class43 method3713 = Class98_Sub1.method945(anInt74, Class265.aHa1974, (byte)124, true);
                                            final Class197 method3714 = Class98_Sub46_Sub6.method1550(anInt74, 18361, Class265.aHa1974);
                                            if (method3713 != null && method3714 != null) {
                                                aClass197_6296 = method3714;
                                                aClass43_5336 = method3713;
                                            }
                                        }
                                        if (method3688 != null) {
                                            final int anInt75 = method3688.anInt655;
                                            if (~anInt75 <= -1) {
                                                final Class43 method3715 = Class98_Sub1.method945(anInt75, Class265.aHa1974, (byte)124, true);
                                                final Class197 method3716 = Class98_Sub46_Sub6.method1550(anInt75, 18361, Class265.aHa1974);
                                                if (method3715 != null && method3716 != null) {
                                                    aClass197_6297 = method3716;
                                                    aClass43_5337 = method3715;
                                                }
                                            }
                                        }
                                        String method3717 = null;
                                        final String method3718 = method3687.method848(22414, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6425[j]);
                                        int method3719 = 0;
                                        final int method3720 = aClass197_6296.method2674(method3718, b + 242);
                                        if (method3688 != null) {
                                            method3717 = method3688.method848(22414, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6423[j]);
                                            method3719 = aClass197_6297.method2674(method3717, 107);
                                        }
                                        int k = 0;
                                        int n35 = 0;
                                        if (method3690 > 0) {
                                            k = method3720 / method3690 + 1;
                                        }
                                        if (method3688 != null && method3698 > 0) {
                                            n35 = 1 + method3719 / method3698;
                                        }
                                        final int n37;
                                        int n36 = n37 = 0;
                                        if (method3689 > 0) {
                                            n36 += method3689;
                                        }
                                        n36 += 2;
                                        final int n38 = n36;
                                        if (~method3691 < -1) {
                                            n36 += method3691;
                                        }
                                        final int n39 = n36;
                                        int n40 = n36;
                                        int n42;
                                        if (~method3690 < -1) {
                                            final int n41 = k * method3690;
                                            n42 = n36 + n41;
                                            n40 += (-method3720 + n41) / 2;
                                        }
                                        else {
                                            n42 = n36 + method3720;
                                        }
                                        final int n43 = n42;
                                        if (method3692 > 0) {
                                            n42 += method3692;
                                        }
                                        int n44 = 0;
                                        int n45 = 0;
                                        int n46 = 0;
                                        int n47 = 0;
                                        int n48 = 0;
                                        if (method3688 != null) {
                                            n42 += 2;
                                            n44 = n42;
                                            if (~method3697 < -1) {
                                                n42 += method3697;
                                            }
                                            n42 += 2;
                                            n45 = n42;
                                            if (~method3699 < -1) {
                                                n42 += method3699;
                                            }
                                            n46 = n42;
                                            n48 = n42;
                                            if (method3698 > 0) {
                                                final int n49 = n35 * method3698;
                                                n48 += (n49 - method3719) / 2;
                                                n42 += n49;
                                            }
                                            else {
                                                n42 += method3719;
                                            }
                                            n47 = n42;
                                            if (~method3700 < -1) {
                                                n42 += method3700;
                                            }
                                        }
                                        final int n50 = -Class215.anInt1614 + aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6375[j];
                                        final int n51 = -(method3687.anInt653 * n50 / method3687.anInt651) + method3687.anInt653;
                                        final int n52 = method3687.anInt650 * n50 / method3687.anInt651 + -method3687.anInt650;
                                        final int n53 = n51 + (-(n42 >> -291615551) + (Class259.anIntArray1957[0] + n5));
                                        int n55;
                                        final int n54 = n55 = n52 - 12 + (n6 + Class259.anIntArray1957[1]);
                                        int n56 = n34 + n54;
                                        final int n57 = method3687.anInt646 + n54 + 15;
                                        final int n58 = -aClass197_6296.anInt1517 + n57;
                                        if (n58 < n55) {
                                            n55 = n58;
                                        }
                                        final int n59 = aClass197_6296.anInt1514 + n57;
                                        if (~n59 < ~n56) {
                                            n56 = n59;
                                        }
                                        int n60 = 0;
                                        if (method3688 != null) {
                                            n60 = 15 + n54 + method3688.anInt646;
                                            final int n61 = n60 + -aClass197_6297.anInt1517;
                                            final int n62 = aClass197_6297.anInt1514 + n60;
                                            if (n61 < n55) {
                                                n55 = n61;
                                            }
                                            if (n56 < n62) {
                                                n56 = n62;
                                            }
                                        }
                                        int n63 = 255;
                                        if (~method3687.anInt645 <= -1) {
                                            n63 = (n50 << -847692632) / (method3687.anInt651 - method3687.anInt645);
                                        }
                                        if (~n63 <= -1 && ~n63 > -256) {
                                            final int n64 = n63 << 877653624;
                                            final int n65 = 0xFFFFFF | n64;
                                            if (method3701 != null) {
                                                method3701.method3748(-n26 + n37 + n53, n54, 0, n65, 1);
                                            }
                                            if (method3705 != null) {
                                                method3705.method3748(-n28 + n38 + n53, n54, 0, n65, 1);
                                            }
                                            if (method3703 != null) {
                                                for (int n66 = 0; k > n66; ++n66) {
                                                    method3703.method3748(n53 - -n39 - (n27 - n66 * method3690), n54, 0, n65, 1);
                                                }
                                            }
                                            if (method3707 != null) {
                                                method3707.method3748(-n29 + (n53 - -n43), n54, 0, n65, 1);
                                            }
                                            aClass43_5336.method411((byte)(-102), n57, method3718, method3687.anInt648 | n64, 0, n40 + n53);
                                            if (method3688 != null) {
                                                if (method3693 != null) {
                                                    method3693.method3748(n53 + n44 - n30, n54, 0, n65, 1);
                                                }
                                                if (method3695 != null) {
                                                    method3695.method3748(n53 - (-n45 - -n32), n54, 0, n65, 1);
                                                }
                                                if (method3694 != null) {
                                                    for (int l = 0; l < n35; ++l) {
                                                        method3694.method3748(method3698 * l + -n31 + n53 + n46, n54, 0, n65, 1);
                                                    }
                                                }
                                                if (method3696 != null) {
                                                    method3696.method3748(n47 + n53 + -n33, n54, 0, n65, 1);
                                                }
                                                aClass43_5337.method411((byte)(-105), n60, method3717, n64 | method3688.anInt648, 0, n48 + n53);
                                            }
                                        }
                                        else {
                                            if (method3701 != null) {
                                                method3701.method3735(n53 - -n37 - n26, n54);
                                            }
                                            if (method3705 != null) {
                                                method3705.method3735(-n28 + (n53 + n38), n54);
                                            }
                                            if (method3703 != null) {
                                                for (int n67 = 0; k > n67; ++n67) {
                                                    method3703.method3735(n53 - -n39 + (-n27 - -(method3690 * n67)), n54);
                                                }
                                            }
                                            if (method3707 != null) {
                                                method3707.method3735(-n29 + (n53 + n43), n54);
                                            }
                                            aClass43_5336.method411((byte)79, n57, method3718, method3687.anInt648 | 0xFF000000, 0, n53 - -n40);
                                            if (method3688 != null) {
                                                if (method3693 != null) {
                                                    method3693.method3735(n53 - (-n44 - -n30), n54);
                                                }
                                                if (method3695 != null) {
                                                    method3695.method3735(-n32 + n45 + n53, n54);
                                                }
                                                if (method3694 != null) {
                                                    for (int n68 = 0; n68 < n35; ++n68) {
                                                        method3694.method3735(n53 + (n46 + (-n31 - -(n68 * method3698))), n54);
                                                    }
                                                }
                                                if (method3696 != null) {
                                                    method3696.method3735(-n33 + (n53 + n47), n54);
                                                }
                                                aClass43_5337.method411((byte)81, n60, method3717, 0xFF000000 | method3688.anInt648, 0, n53 - -n48);
                                            }
                                        }
                                        Class93_Sub1_Sub1.method908(1 + n56, n55, false, n53, n42 + n53);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for (int n69 = 0; ~n69 > ~Class98_Sub19.anInt3955; ++n69) {
                final int n70 = Class151_Sub1.anIntArray4969[n69];
                Class246_Sub3_Sub4_Sub2 aClass246_Sub3_Sub4_Sub2_Sub1_4188;
                if (n70 < 2048) {
                    aClass246_Sub3_Sub4_Sub2_Sub1_4188 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[n70];
                }
                else {
                    aClass246_Sub3_Sub4_Sub2_Sub1_4188 = ((Class98_Sub39)Class260.aClass377_3254.method3990(-2048 + n70, -1)).aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                }
                final int n71 = Class119_Sub2.anIntArray4727[n69];
                Class246_Sub3_Sub4_Sub2 aClass246_Sub3_Sub4_Sub2_Sub1_4189;
                if (n71 >= 2048) {
                    aClass246_Sub3_Sub4_Sub2_Sub1_4189 = ((Class98_Sub39)Class260.aClass377_3254.method3990(-2048 + n71, b + 123)).aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                }
                else {
                    aClass246_Sub3_Sub4_Sub2_Sub1_4189 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[n71];
                }
                final int n72 = 123;
                final Class246_Sub3_Sub4_Sub2 class246_Sub3_Sub4_Sub2 = aClass246_Sub3_Sub4_Sub2_Sub1_4188;
                final Class246_Sub3_Sub4_Sub2 class246_Sub3_Sub4_Sub3 = aClass246_Sub3_Sub4_Sub2_Sub1_4189;
                final Class246_Sub3_Sub4_Sub2 class246_Sub3_Sub4_Sub4 = aClass246_Sub3_Sub4_Sub2_Sub1_4188;
                Class286.method3381(n, n5, n4, n72, class246_Sub3_Sub4_Sub2, n6, class246_Sub3_Sub4_Sub3, --class246_Sub3_Sub4_Sub4.anInt6358, n2, n3);
            }
            final int n73 = 2 + (Class42_Sub1.aClass197_5354.anInt1514 + Class42_Sub1.aClass197_5354.anInt1517);
            if (b == -124) {
                for (int n74 = 0; ~Class48_Sub2_Sub1.anInt5532 < ~n74; ++n74) {
                    final int n75 = Class230.anIntArray1724[n74];
                    int n76 = Class230.anIntArray1730[n74];
                    final int n77 = Class230.anIntArray1729[n74];
                    int n78 = 1;
                    while (n78 != 0) {
                        n78 = 0;
                        for (int n79 = 0; n74 > n79; ++n79) {
                            if (-n73 + Class230.anIntArray1730[n79] < n76 + 2 && ~(2 + Class230.anIntArray1730[n79]) < ~(-n73 + n76) && -n77 + n75 < Class230.anIntArray1729[n79] + Class230.anIntArray1724[n79] && ~(-Class230.anIntArray1729[n79] + Class230.anIntArray1724[n79]) > ~(n75 - -n77) && ~n76 < ~(-n73 + Class230.anIntArray1730[n79])) {
                                n76 = Class230.anIntArray1730[n79] + -n73;
                                n78 = 1;
                            }
                        }
                    }
                    Class230.anIntArray1730[n74] = n76;
                    final String s = Class230.aStringArray1721[n74];
                    final int method3721 = Class42_Sub1.aClass197_5354.method2674(s, 104);
                    int n80 = n5 - -n75;
                    int n81 = -Class42_Sub1.aClass197_5354.anInt1517 + (n76 + n6);
                    int n82 = method3721 + n80;
                    int n83 = n76 + n6 + Class42_Sub1.aClass197_5354.anInt1514;
                    if (Class129.anInt1026 == 0) {
                        int n84 = 16776960;
                        if (Class230.anIntArray1726[n74] < 6) {
                            n84 = OutputStream_Sub1.anIntArray38[Class230.anIntArray1726[n74]];
                        }
                        if (~Class230.anIntArray1726[n74] == 0xFFFFFFF9) {
                            n84 = ((~(Class64_Sub15.anInt3676 % 20) > -11) ? 16711680 : 16776960);
                        }
                        if (~Class230.anIntArray1726[n74] == 0xFFFFFFF8) {
                            n84 = ((Class64_Sub15.anInt3676 % 20 >= 10) ? 65535 : 255);
                        }
                        if (~Class230.anIntArray1726[n74] == 0xFFFFFFF7) {
                            n84 = ((Class64_Sub15.anInt3676 % 20 < 10) ? 45056 : 8454016);
                        }
                        if (~Class230.anIntArray1726[n74] == 0xFFFFFFF6) {
                            final int n85 = -Class230.anIntArray1722[n74] + 150;
                            if (~n85 <= -51) {
                                if (n85 < 100) {
                                    n84 = 16776960 + -(n85 * 327680) + 16384000;
                                }
                                else if (n85 < 150) {
                                    n84 = (n85 - 100) * 5 + 65280;
                                }
                            }
                            else {
                                n84 = n85 * 1280 + 16711680;
                            }
                        }
                        if (~Class230.anIntArray1726[n74] == 0xFFFFFFF5) {
                            final int n86 = 150 + -Class230.anIntArray1722[n74];
                            if (n86 < 50) {
                                n84 = n86 * 5 + 16711680;
                            }
                            else if (~n86 <= -101) {
                                if (n86 < 150) {
                                    n84 = 255 - -(327680 * n86) - (32768000 - -((n86 - 100) * 5));
                                }
                            }
                            else {
                                n84 = 16384000 - 327680 * n86 + 16711935;
                            }
                        }
                        if (~Class230.anIntArray1726[n74] == 0xFFFFFFF4) {
                            final int n87 = 150 - Class230.anIntArray1722[n74];
                            if (~n87 <= -51) {
                                if (~n87 <= -101) {
                                    if (n87 < 150) {
                                        n84 = -(327680 * (-100 + n87)) + 16777215;
                                    }
                                }
                                else {
                                    n84 = -16384250 + (327685 * n87 + 65280);
                                }
                            }
                            else {
                                n84 = -(327685 * n87) + 16777215;
                            }
                        }
                        final int n88 = 0xFF000000 | n84;
                        if (~Class230.anIntArray1728[n74] == -1) {
                            n82 -= method3721 >> 315503681;
                            Class98_Sub10_Sub34.aClass43_5730.method415(n88, s, n5 + n75, -16777216, (byte)60, n6 + n76);
                            n80 -= method3721 >> 887140673;
                        }
                        if (~Class230.anIntArray1728[n74] == 0xFFFFFFFE) {
                            n81 -= 5;
                            n80 -= method3721 >> -1595838975;
                            Class98_Sub10_Sub34.aClass43_5730.method403(-16777216, n75 + n5, n6 + n76, Class64_Sub15.anInt3676, 62, s, n88);
                            n83 += 5;
                            n82 -= method3721 >> -50142815;
                        }
                        if (~Class230.anIntArray1728[n74] == 0xFFFFFFFD) {
                            n82 -= (method3721 >> 90209633) - 5;
                            n83 += 5;
                            n81 -= 5;
                            Class98_Sub10_Sub34.aClass43_5730.method412(-16777216, -127, n88, n75 + n5, n76 + n6, s, Class64_Sub15.anInt3676);
                            n80 -= 5 + (method3721 >> -574220287);
                        }
                        if (~Class230.anIntArray1728[n74] == 0xFFFFFFFC) {
                            n82 -= method3721 >> 988959905;
                            n83 += 7;
                            Class98_Sub10_Sub34.aClass43_5730.method406(0, -16777216, n6 - -n76, n88, -Class230.anIntArray1722[n74] + 150, Class64_Sub15.anInt3676, n75 + n5, s);
                            n81 -= 7;
                            n80 -= method3721 >> -798403551;
                        }
                        if (Class230.anIntArray1728[n74] == 4) {
                            final int n89 = (-Class230.anIntArray1722[n74] + 150) * (100 + Class42_Sub1.aClass197_5354.method2674(s, 112)) / 150;
                            Class265.aHa1974.T(-50 + n75 + n5, n6, 50 + (n5 + n75), n6 - -n3);
                            n82 += 50 + -n89;
                            n80 += -n89 + 50;
                            Class98_Sub10_Sub34.aClass43_5730.method411((byte)66, n76 + n6, s, n88, -16777216, -n89 + n75 + n5 + 50);
                            Class265.aHa1974.KA(n5, n6, n5 + n2, n3 + n6);
                        }
                        if (Class230.anIntArray1728[n74] == 5) {
                            final int n90 = 150 + -Class230.anIntArray1722[n74];
                            int n91 = 0;
                            if (n90 < 25) {
                                n91 = n90 - 25;
                            }
                            else if (~n90 < -126) {
                                n91 = -125 + n90;
                            }
                            Class265.aHa1974.T(n5, -(Class42_Sub1.aClass197_5354.anInt1514 + Class42_Sub1.aClass197_5354.anInt1517) + (n76 + n6 - 1), n2 + n5, n6 - (-n76 - 5));
                            n81 += n91;
                            n83 += n91;
                            n82 -= method3721 >> -305213023;
                            Class98_Sub10_Sub34.aClass43_5730.method415(n88, s, n5 - -n75, -16777216, (byte)124, n91 + (n6 - -n76));
                            n80 -= method3721 >> 1565844833;
                            Class265.aHa1974.KA(n5, n6, n2 + n5, n3 + n6);
                        }
                    }
                    else {
                        Class98_Sub10_Sub34.aClass43_5730.method415(-256, s, n75 + n5, -16777216, (byte)(-87), n76 + n6);
                        n82 -= method3721 >> 1383577281;
                        n80 -= method3721 >> -37573471;
                    }
                    Class93_Sub1_Sub1.method908(1 + n83, n81, false, n80, n82 + 1);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pda.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    public static void method1451(final int n) {
        try {
            Class98_Sub34.aClass58_4128 = null;
            if (n > -106) {
                Class98_Sub34.aClass207_4127 = null;
            }
            Class98_Sub34.aClass207_4127 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pda.B(" + n + ')');
        }
    }
    
    public Class98_Sub34() {
    }
    
    Class98_Sub34(final int anInt4126) {
        try {
            this.anInt4126 = anInt4126;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pda.<init>(" + anInt4126 + ')');
        }
    }
    
    static {
        Class98_Sub34.aClass58_4128 = new IncomingOpcode(86, 6);
    }
}
