// 
// Decompiled by Procyon v0.5.30
// 

class Class278
{
    static Class207 aClass207_2054;
    private static Class153 aClass153_2055;
    static Class98_Sub46_Sub10 aClass98_Sub46_Sub10_2056;
    static Class341 aClass341_2057;
    private static Class32 aClass32_2058;
    static Class335 aClass335_2059;
    static Interface6 anInterface6_2060;
    private static Class377 aClass377_2061;
    static Class302 aClass302_2062;
    static int anInt2063;
    static float aFloat2064;
    static Class148 aClass148_2065;
    static Class370 aClass370_2066;
    private static byte[] aByteArray2067;
    static float aFloat2068;
    static int anInt2069;
    private static short[] aShortArray2070;
    static int anInt2071;
    static byte[][][] aByteArrayArrayArray2072;
    private static byte[] aByteArray2073;
    static int anInt2074;
    static int anInt2075;
    private static short[] aShortArray2076;
    static int anInt2077;
    static int anInt2078;
    private static int[] anIntArray2079;
    private static byte[] aByteArray2080;
    private static byte[] aByteArray2081;
    private static Class218[][][] aClass218ArrayArrayArray2082;
    static int anInt2083;
    static int anInt2084;
    private static short[] aShortArray2085;
    static int anInt2086;
    private static byte[] aByteArray2087;
    private static Class377 aClass377_2088;
    static int anInt2089;
    static int anInt2090;
    static int anInt2091;
    private static byte[] aByteArray2092;
    static int anInt2093;
    static int anInt2094;
    
    private static final void method3295() {
        for (int i = 0; i < Class278.anInt2089; ++i) {
            for (int j = 0; j < Class278.anInt2084; ++j) {
                final int n = Class278.aShortArray2076[i + j * Class278.anInt2089] & 0xFFFF;
                if (n != 0) {
                    if (n == 65535) {
                        final Class98_Sub29 class98_Sub29 = (Class98_Sub29)Class278.aClass377_2088.method3990(i << 16 | j, -1);
                        if (class98_Sub29 != null) {
                            for (int k = 0; k < class98_Sub29.aShortArray4082.length; ++k) {
                                final Class352 method3546 = Class278.aClass302_2062.method3546(class98_Sub29.aShortArray4082[k] & 0xFFFF, (byte)119);
                                int n2 = method3546.anInt2958;
                                if (method3546.anIntArray2928 != null) {
                                    final Class352 method3547 = method3546.method3852(Class278.anInterface6_2060, (byte)(-72));
                                    if (method3547 != null) {
                                        n2 = method3547.anInt2958;
                                    }
                                }
                                if (n2 != -1) {
                                    final Class98_Sub47 class98_Sub30 = new Class98_Sub47(n2);
                                    class98_Sub30.anInt4272 = i;
                                    class98_Sub30.anInt4267 = j;
                                    Class278.aClass148_2065.method2419(class98_Sub30, -20911);
                                }
                            }
                        }
                    }
                    else {
                        final Class352 method3548 = Class278.aClass302_2062.method3546(n - 1, (byte)119);
                        int n3 = method3548.anInt2958;
                        if (method3548.anIntArray2928 != null) {
                            final Class352 method3549 = method3548.method3852(Class278.anInterface6_2060, (byte)(-123));
                            if (method3549 != null) {
                                n3 = method3549.anInt2958;
                            }
                        }
                        if (n3 != -1) {
                            final Class98_Sub47 class98_Sub31 = new Class98_Sub47(n3);
                            class98_Sub31.anInt4272 = i;
                            class98_Sub31.anInt4267 = j;
                            Class278.aClass148_2065.method2419(class98_Sub31, -20911);
                        }
                    }
                }
            }
        }
        for (int l = 0; l < 3; ++l) {
            for (int n4 = 0; n4 < Class278.aClass218ArrayArrayArray2082[0].length; ++n4) {
                for (int n5 = 0; n5 < Class278.aClass218ArrayArrayArray2082[0][0].length; ++n5) {
                    final Class218 class218 = Class278.aClass218ArrayArrayArray2082[l][n4][n5];
                    if (class218 != null) {
                        for (Class246_Sub8 class246_Sub8 = (Class246_Sub8)class218.method2803((byte)15); class246_Sub8 != null; class246_Sub8 = (Class246_Sub8)class218.method2809(false)) {
                            if (class246_Sub8.aShortArray5129 != null) {
                                for (int n6 = 0; n6 < class246_Sub8.aShortArray5129.length; ++n6) {
                                    final Class352 method3550 = Class278.aClass302_2062.method3546(class246_Sub8.aShortArray5129[n6] & 0xFFFF, (byte)119);
                                    int n7 = method3550.anInt2958;
                                    if (method3550.anIntArray2928 != null) {
                                        final Class352 method3551 = method3550.method3852(Class278.anInterface6_2060, (byte)(-107));
                                        if (method3551 != null) {
                                            n7 = method3551.anInt2958;
                                        }
                                    }
                                    if (n7 != -1) {
                                        final Class98_Sub47 class98_Sub32 = new Class98_Sub47(n7);
                                        class98_Sub32.anInt4272 = (n4 + (Class278.anInt2075 >> 6)) * 64 + class246_Sub8.aByte5128 - Class278.anInt2075;
                                        class98_Sub32.anInt4267 = (n5 + (Class278.anInt2078 >> 6)) * 64 + class246_Sub8.aByte5125 - Class278.anInt2078;
                                        Class278.aClass148_2065.method2419(class98_Sub32, -20911);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    static final Class215 method3296(final int n, final int n2) {
        final Class215 class215 = new Class215();
        for (Class98_Sub46_Sub10 class98_Sub46_Sub10 = (Class98_Sub46_Sub10)Class278.aClass377_2061.method3998(107); class98_Sub46_Sub10 != null; class98_Sub46_Sub10 = (Class98_Sub46_Sub10)Class278.aClass377_2061.method3995(-1)) {
            if (class98_Sub46_Sub10.aBoolean6021 && class98_Sub46_Sub10.method1564(6, n, n2)) {
                class215.method2785(class98_Sub46_Sub10, -76);
            }
        }
        return class215;
    }
    
    static final void method3297() {
        Class278.aByteArray2081 = new byte[Class278.anInt2089 * Class278.anInt2084];
        Class278.aByteArray2087 = new byte[Class278.anInt2089 * Class278.anInt2084];
        Class278.aByteArray2073 = new byte[Class278.anInt2089 * Class278.anInt2084];
        Class278.aShortArray2076 = new short[Class278.anInt2089 * Class278.anInt2084];
        Class278.aByteArray2092 = new byte[Class278.anInt2089 * Class278.anInt2084];
        Class278.aClass377_2088 = new Class377(1024);
        Class278.aClass218ArrayArrayArray2082 = new Class218[3][Class278.anInt2089 >> 6][Class278.anInt2084 >> 6];
        Class278.anIntArray2079 = new int[Class278.aClass32_2058.anInt309 + 1];
    }
    
    static final void method3298(final Class207 aClass207_2054, final Class32 aClass32_2058, final Class153 aClass153_2055, final Class302 aClass302_2062, final Class341 aClass341_2057, final Class335 aClass335_2059, final Interface6 anInterface6_2060) {
        Class278.aClass207_2054 = aClass207_2054;
        Class278.aClass32_2058 = aClass32_2058;
        Class278.aClass153_2055 = aClass153_2055;
        Class278.aClass302_2062 = aClass302_2062;
        Class278.aClass341_2057 = aClass341_2057;
        Class278.aClass335_2059 = aClass335_2059;
        Class278.anInterface6_2060 = anInterface6_2060;
        Class278.aClass377_2061.method3994(-107);
        final int method2750 = Class278.aClass207_2054.method2750((byte)(-76), "details");
        final int[] method2751 = Class278.aClass207_2054.method2743(method2750, 6341);
        if (method2751 != null) {
            for (int i = 0; i < method2751.length; ++i) {
                final Class98_Sub46_Sub10 method2752 = Class48_Sub1.method457(method2751[i], method2750, 10443, Class278.aClass207_2054);
                Class278.aClass377_2061.method3996(method2752, method2752.anInt6014, -1);
            }
        }
        Class101.method1702(8, true, false);
    }
    
    static final void method3299() {
        Class278.aByteArray2081 = null;
        Class278.aByteArray2080 = null;
        Class278.aShortArray2085 = null;
        Class278.aByteArray2087 = null;
        Class278.aByteArray2073 = null;
        Class278.aShortArray2076 = null;
        Class278.aByteArray2092 = null;
        Class278.aClass377_2088 = null;
        Class278.aClass218ArrayArrayArray2082 = null;
        Class278.anIntArray2079 = null;
    }
    
    private static final void method3300(final ha ha, final Class98_Sub22 class98_Sub22, final int n, final int n2, final int n3, final int n4, final int[] array, final int[] array2) {
        final int unsignedByte = class98_Sub22.readUnsignedByte((byte)95);
        if ((unsignedByte & 0x1) == 0x0) {
            final boolean b = (unsignedByte & 0x2) == 0x0;
            final int n5 = unsignedByte >> 2 & 0x3F;
            if (n5 != 62) {
                int unsignedByte2;
                if (n5 == 63) {
                    unsignedByte2 = class98_Sub22.readUnsignedByte((byte)124);
                }
                else if (b) {
                    unsignedByte2 = array[n5];
                }
                else {
                    unsignedByte2 = array2[n5];
                }
                if (b) {
                    Class278.aByteArray2081[n3 + n4 * Class278.anInt2089] = (byte)unsignedByte2;
                    Class278.aByteArray2087[n3 + n4 * Class278.anInt2089] = 0;
                }
                else {
                    Class278.aByteArray2087[n3 + n4 * Class278.anInt2089] = (byte)unsignedByte2;
                    Class278.aByteArray2073[n3 + n4 * Class278.anInt2089] = 0;
                    Class278.aByteArray2081[n3 + n4 * Class278.anInt2089] = class98_Sub22.readSignedByte((byte)(-19));
                }
            }
        }
        else {
            final int n6 = (unsignedByte >> 1 & 0x3) + 1;
            final boolean b2 = (unsignedByte & 0x8) != 0x0;
            final boolean b3 = (unsignedByte & 0x10) != 0x0;
            for (int i = 0; i < n6; ++i) {
                final int unsignedByte3 = class98_Sub22.readUnsignedByte((byte)(-110));
                int unsignedByte4 = 0;
                int unsignedByte5 = 0;
                if (b2) {
                    unsignedByte4 = class98_Sub22.readUnsignedByte((byte)(-123));
                    unsignedByte5 = class98_Sub22.readUnsignedByte((byte)(-127));
                }
                int unsignedByte6 = 0;
                if (b3) {
                    unsignedByte6 = class98_Sub22.readUnsignedByte((byte)15);
                }
                if (i == 0) {
                    Class278.aByteArray2081[n3 + n4 * Class278.anInt2089] = (byte)unsignedByte3;
                    Class278.aByteArray2087[n3 + n4 * Class278.anInt2089] = (byte)unsignedByte4;
                    Class278.aByteArray2073[n3 + n4 * Class278.anInt2089] = (byte)unsignedByte5;
                    if (unsignedByte6 == 1) {
                        Class278.aShortArray2076[n3 + n4 * Class278.anInt2089] = (short)(class98_Sub22.readShort((byte)127) + 1);
                        Class278.aByteArray2092[n3 + n4 * Class278.anInt2089] = class98_Sub22.readSignedByte((byte)(-19));
                    }
                    else if (unsignedByte6 > 1) {
                        Class278.aShortArray2076[n3 + n4 * Class278.anInt2089] = -1;
                        final short[] array3 = new short[unsignedByte6];
                        final byte[] array4 = new byte[unsignedByte6];
                        for (int j = 0; j < unsignedByte6; ++j) {
                            array3[j] = (short)class98_Sub22.readShort((byte)127);
                            array4[j] = class98_Sub22.readSignedByte((byte)(-19));
                        }
                        Class278.aClass377_2088.method3996(new Class98_Sub29(array3, array4), n3 << 16 | n4, -1);
                    }
                }
                else {
                    short[] array5 = null;
                    byte[] array6 = null;
                    if (unsignedByte6 > 0) {
                        array5 = new short[unsignedByte6];
                        array6 = new byte[unsignedByte6];
                        for (int k = 0; k < unsignedByte6; ++k) {
                            array5[k] = (short)class98_Sub22.readShort((byte)127);
                            array6[k] = class98_Sub22.readSignedByte((byte)(-19));
                        }
                    }
                    if (Class278.aClass218ArrayArrayArray2082[i - 1][n - (Class278.anInt2075 >> 6)][n2 - (Class278.anInt2078 >> 6)] == null) {
                        Class278.aClass218ArrayArrayArray2082[i - 1][n - (Class278.anInt2075 >> 6)][n2 - (Class278.anInt2078 >> 6)] = new Class218();
                    }
                    Class278.aClass218ArrayArrayArray2082[i - 1][n - (Class278.anInt2075 >> 6)][n2 - (Class278.anInt2078 >> 6)].method2808(true, new Class246_Sub8(n3 & 0x3F, n4 & 0x3F, unsignedByte3, unsignedByte4, unsignedByte5, array5, array6));
                }
            }
        }
    }
    
    private static final void method3301(final ha ha, final int n, final int n2, final int n3, final int n4) {
        int n5 = Class278.anInt2074 - Class278.anInt2091;
        int n6 = Class278.anInt2083 - Class278.anInt2090;
        if (Class278.anInt2074 < Class278.anInt2089) {
            ++n5;
        }
        if (Class278.anInt2083 < Class278.anInt2084) {
            ++n6;
        }
        for (int i = 0; i < n5; ++i) {
            final int n7 = (n3 + n * i >> 16) + Class278.anInt2086;
            final int n8 = (n3 + n * (i + 1) >> 16) + Class278.anInt2086 - n7;
            if (n8 > 0) {
                final int n9 = Class278.anInt2091 + i;
                if (n9 < 0 || n9 >= Class278.anInt2089) {
                    for (int j = 0; j < n6; ++j) {
                        final int n10 = Class278.anInt2094 - (n4 + n2 * (j + 1) >> 16);
                        final int n11 = Class278.anInt2094 - (n4 + n2 * j >> 16) - n10;
                        int n12;
                        if (Class278.aClass98_Sub46_Sub10_2056.anInt6013 != -1) {
                            n12 = (0xFF000000 | Class278.aClass98_Sub46_Sub10_2056.anInt6013);
                        }
                        else if ((i + Class278.anInt2091 & 0x4) != (j + Class278.anInt2083 & 0x4)) {
                            n12 = -11840664;
                        }
                        else {
                            n12 = Class278.anIntArray2079[Class278.aClass32_2058.anInt312 + 1];
                        }
                        if (n12 == 0) {
                            n12 = -16777216;
                        }
                        ha.aa(n7, n10, n8, n11, n12, 0);
                    }
                }
                else {
                    for (int k = 0; k < n6; ++k) {
                        final int n13 = Class278.anInt2094 - (n4 + n2 * (k + 1) >> 16);
                        final int n14 = Class278.anInt2094 - (n4 + n2 * k >> 16) - n13;
                        if (n14 > 0) {
                            final int n15 = k + Class278.anInt2090;
                            final int n16 = n9 + n15 * Class278.anInt2089;
                            int n17 = 0;
                            int n18 = 0;
                            int n19 = 0;
                            if (n15 >= 0 && n15 < Class278.anInt2084) {
                                n17 = ((Class278.aByteArray2080[n16] & 0xFF) << 16 | (Class278.aShortArray2085[n16] & 0xFFFF));
                                if (n17 != 0) {
                                    n17 |= 0xFF000000;
                                }
                                n18 = (Class278.aByteArray2087[n16] & 0xFF);
                                n19 = (Class278.aShortArray2076[n16] & 0xFFFF);
                            }
                            if (n17 == 0 && n18 == 0 && n19 == 0) {
                                int n20;
                                if (Class278.aClass98_Sub46_Sub10_2056.anInt6013 != -1) {
                                    n20 = (0xFF000000 | Class278.aClass98_Sub46_Sub10_2056.anInt6013);
                                }
                                else if ((i + Class278.anInt2091 & 0x4) != (k + Class278.anInt2083 & 0x4)) {
                                    n20 = -11840664;
                                }
                                else {
                                    n20 = Class278.anIntArray2079[Class278.aClass32_2058.anInt312 + 1];
                                }
                                if (n20 == 0) {
                                    n20 = -16777216;
                                }
                                ha.aa(n7, n13, n8, n14, n20, 0);
                            }
                            else if (n19 > 0) {
                                if (n19 == 65535) {
                                    final Class98_Sub29 class98_Sub29 = (Class98_Sub29)Class278.aClass377_2088.method3990(n9 << 16 | n15, -1);
                                    if (class98_Sub29 != null) {
                                        method3318(ha, n7, n13, n8, n14, n17, n18, Class278.aByteArray2073[n16], class98_Sub29.aShortArray4082, class98_Sub29.aByteArray4083, true);
                                    }
                                }
                                else {
                                    Class278.aShortArray2070[0] = (short)(n19 - 1);
                                    Class278.aByteArray2067[0] = Class278.aByteArray2092[n16];
                                    method3318(ha, n7, n13, n8, n14, n17, n18, Class278.aByteArray2073[n16], Class278.aShortArray2070, Class278.aByteArray2067, true);
                                }
                            }
                            else {
                                method3318(ha, n7, n13, n8, n14, n17, n18, Class278.aByteArray2073[n16], null, null, true);
                            }
                        }
                    }
                }
            }
        }
        for (int l = -16; l < n5 + 16; ++l) {
            final int n21 = (n3 + n * l >> 16) + Class278.anInt2086;
            final int n22 = (n3 + n * (l + 1) >> 16) + Class278.anInt2086 - n21;
            if (n22 > 0) {
                final int n23 = l + Class278.anInt2091;
                if (n23 >= 0 && n23 < Class278.anInt2089) {
                    for (int n24 = -16; n24 < n6 + 16; ++n24) {
                        final int n25 = Class278.anInt2094 - (n4 + n2 * (n24 + 1) >> 16);
                        final int n26 = Class278.anInt2094 - (n4 + n2 * n24 >> 16) - n25;
                        if (n26 > 0) {
                            final int n27 = n24 + Class278.anInt2090;
                            if (n27 >= 0 && n27 < Class278.anInt2084) {
                                final int n28 = Class278.aShortArray2076[n23 + n27 * Class278.anInt2089] & 0xFFFF;
                                if (n28 > 0) {
                                    if (n28 == 65535) {
                                        final Class98_Sub29 class98_Sub30 = (Class98_Sub29)Class278.aClass377_2088.method3990(n23 << 16 | n27, -1);
                                        if (class98_Sub30 != null) {
                                            method3317(ha, n21, n25, n22, n26, class98_Sub30.aShortArray4082, class98_Sub30.aByteArray4083);
                                        }
                                    }
                                    else {
                                        Class278.aShortArray2070[0] = (short)(n28 - 1);
                                        Class278.aByteArray2067[0] = Class278.aByteArray2092[n23 + n27 * Class278.anInt2089];
                                        method3317(ha, n21, n25, n22, n26, Class278.aShortArray2070, Class278.aByteArray2067);
                                    }
                                }
                                else {
                                    method3317(ha, n21, n25, n22, n26, null, null);
                                }
                            }
                        }
                    }
                }
            }
        }
        int n29 = Class278.anInt2091 >> 6;
        int n30 = Class278.anInt2090 >> 6;
        if (n29 < 0) {
            n29 = 0;
        }
        if (n30 < 0) {
            n30 = 0;
        }
        int n31 = Class278.anInt2074 >> 6;
        int n32 = Class278.anInt2083 >> 6;
        if (n31 >= Class278.aClass218ArrayArrayArray2082[0].length) {
            n31 = Class278.aClass218ArrayArrayArray2082[0].length - 1;
        }
        if (n32 >= Class278.aClass218ArrayArrayArray2082[0][0].length) {
            n32 = Class278.aClass218ArrayArrayArray2082[0][0].length - 1;
        }
        for (int n33 = 0; n33 < 3; ++n33) {
            for (int n34 = n29; n34 <= n31; ++n34) {
                for (int n35 = n30; n35 <= n32; ++n35) {
                    final Class218 class218 = Class278.aClass218ArrayArrayArray2082[n33][n34][n35];
                    if (class218 != null) {
                        final int n36 = (n34 + (Class278.anInt2075 >> 6)) * 64;
                        final int n37 = (n35 + (Class278.anInt2078 >> 6)) * 64;
                        for (Class246_Sub8 class246_Sub8 = (Class246_Sub8)class218.method2803((byte)15); class246_Sub8 != null; class246_Sub8 = (Class246_Sub8)class218.method2809(false)) {
                            final int n38 = n36 + class246_Sub8.aByte5128 - Class278.anInt2075 - Class278.anInt2091;
                            final int n39 = n37 + class246_Sub8.aByte5125 - Class278.anInt2078 - Class278.anInt2090;
                            final int n40 = (n3 + n * n38 >> 16) + Class278.anInt2086;
                            final int n41 = (n3 + n * (n38 + 1) >> 16) + Class278.anInt2086;
                            final int n42 = Class278.anInt2094 - (n4 + n2 * (n39 + 1) >> 16);
                            method3318(ha, n40, n42, n41 - n40, Class278.anInt2094 - (n4 + n2 * n39 >> 16) - n42, class246_Sub8.anInt5124, class246_Sub8.aByte5127 & 0xFF, class246_Sub8.aByte5126, class246_Sub8.aShortArray5129, class246_Sub8.aByteArray5130, false);
                        }
                    }
                }
            }
            for (int n43 = n29; n43 <= n31; ++n43) {
                for (int n44 = n30; n44 <= n32; ++n44) {
                    final Class218 class219 = Class278.aClass218ArrayArrayArray2082[n33][n43][n44];
                    if (class219 != null) {
                        final int n45 = (n43 + (Class278.anInt2075 >> 6)) * 64;
                        final int n46 = (n44 + (Class278.anInt2078 >> 6)) * 64;
                        for (Class246_Sub8 class246_Sub9 = (Class246_Sub8)class219.method2803((byte)15); class246_Sub9 != null; class246_Sub9 = (Class246_Sub8)class219.method2809(false)) {
                            final int n47 = n45 + class246_Sub9.aByte5128 - Class278.anInt2075 - Class278.anInt2091;
                            final int n48 = n46 + class246_Sub9.aByte5125 - Class278.anInt2078 - Class278.anInt2090;
                            final int n49 = (n3 + n * n47 >> 16) + Class278.anInt2086;
                            final int n50 = (n3 + n * (n47 + 1) >> 16) + Class278.anInt2086;
                            final int n51 = Class278.anInt2094 - (n4 + n2 * (n48 + 1) >> 16);
                            method3317(ha, n49, n51, n50 - n49, Class278.anInt2094 - (n4 + n2 * n48 >> 16) - n51, class246_Sub9.aShortArray5129, class246_Sub9.aByteArray5130);
                        }
                    }
                }
            }
        }
    }
    
    static final void method3302() {
        final int[] array = new int[3];
        for (int i = 0; i < Class278.aClass370_2066.anInt3137; ++i) {
            if (Class278.aClass98_Sub46_Sub10_2056.method1573(Class278.aClass370_2066.anIntArray3133[i] >> 28 & 0x3, array, -122, Class278.aClass370_2066.anIntArray3133[i] & 0x3FFF, Class278.aClass370_2066.anIntArray3133[i] >> 14 & 0x3FFF)) {
                final Class98_Sub47 class98_Sub47 = new Class98_Sub47(Class278.aClass370_2066.anIntArray3138[i]);
                class98_Sub47.anInt4272 = array[1] - Class278.anInt2075;
                class98_Sub47.anInt4267 = array[2] - Class278.anInt2078;
                Class278.aClass148_2065.method2419(class98_Sub47, -20911);
            }
        }
    }
    
    static final Class98_Sub46_Sub10 method3303(final int n, final int n2) {
        for (Class98_Sub46_Sub10 class98_Sub46_Sub10 = (Class98_Sub46_Sub10)Class278.aClass377_2061.method3998(101); class98_Sub46_Sub10 != null; class98_Sub46_Sub10 = (Class98_Sub46_Sub10)Class278.aClass377_2061.method3995(-1)) {
            if (class98_Sub46_Sub10.aBoolean6021 && class98_Sub46_Sub10.method1564(6, n, n2)) {
                return class98_Sub46_Sub10;
            }
        }
        return null;
    }
    
    static final void method3304(final d d, final int n, final int n2) {
        for (int i = 0; i < Class278.aClass32_2058.anInt309; ++i) {
            Class278.anIntArray2079[i + 1] = method3311(d, i, n, n2);
        }
    }
    
    static final void method3305(final ha ha, final int n, final int n2) {
        final Class98_Sub22 class98_Sub22 = new Class98_Sub22(Class278.aClass207_2054.method2739(Class278.aClass98_Sub46_Sub10_2056.aString6017, "area", -32734));
        final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-109));
        final int[] array = new int[unsignedByte];
        for (int i = 0; i < unsignedByte; ++i) {
            array[i] = class98_Sub22.readUnsignedByte((byte)92);
        }
        final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)(-113));
        final int[] array2 = new int[unsignedByte2];
        for (int j = 0; j < unsignedByte2; ++j) {
            array2[j] = class98_Sub22.readUnsignedByte((byte)(-8));
        }
        while (class98_Sub22.anInt3991 < class98_Sub22.aByteArray3992.length) {
            if (class98_Sub22.readUnsignedByte((byte)(-111)) == 0) {
                final int unsignedByte3 = class98_Sub22.readUnsignedByte((byte)(-115));
                final int unsignedByte4 = class98_Sub22.readUnsignedByte((byte)56);
                for (int k = 0; k < 64; ++k) {
                    for (int l = 0; l < 64; ++l) {
                        method3300(ha, class98_Sub22, unsignedByte3, unsignedByte4, unsignedByte3 * 64 + k - Class278.anInt2075, unsignedByte4 * 64 + l - Class278.anInt2078, array, array2);
                    }
                }
            }
            else {
                final int unsignedByte5 = class98_Sub22.readUnsignedByte((byte)126);
                final int unsignedByte6 = class98_Sub22.readUnsignedByte((byte)(-108));
                final int unsignedByte7 = class98_Sub22.readUnsignedByte((byte)(-4));
                final int unsignedByte8 = class98_Sub22.readUnsignedByte((byte)(-113));
                for (int n3 = 0; n3 < 8; ++n3) {
                    for (int n4 = 0; n4 < 8; ++n4) {
                        method3300(ha, class98_Sub22, unsignedByte5, unsignedByte6, unsignedByte5 * 64 + unsignedByte7 * 8 + n3 - Class278.anInt2075, unsignedByte6 * 64 + unsignedByte8 * 8 + n4 - Class278.anInt2078, array, array2);
                    }
                }
            }
        }
        Class278.aByteArray2080 = new byte[Class278.anInt2089 * Class278.anInt2084];
        Class278.aShortArray2085 = new short[Class278.anInt2089 * Class278.anInt2084];
        for (int n5 = 0; n5 < 3; ++n5) {
            final byte[] array3 = new byte[Class278.anInt2089 * Class278.anInt2084];
            for (int n6 = 0; n6 < Class278.aClass218ArrayArrayArray2082[n5].length; ++n6) {
                for (int n7 = 0; n7 < Class278.aClass218ArrayArrayArray2082[n5][0].length; ++n7) {
                    final Class218 class218 = Class278.aClass218ArrayArrayArray2082[n5][n6][n7];
                    if (class218 != null) {
                        for (Class246_Sub8 class246_Sub8 = (Class246_Sub8)class218.method2803((byte)15); class246_Sub8 != null; class246_Sub8 = (Class246_Sub8)class218.method2809(false)) {
                            array3[n6 * 64 + class246_Sub8.aByte5128 + (n7 * 64 + class246_Sub8.aByte5125) * Class278.anInt2089] = (byte)class246_Sub8.anInt5124;
                        }
                    }
                }
            }
            method3310(array3, Class278.aByteArray2080, Class278.aShortArray2085, n, n2);
            for (int n8 = 0; n8 < Class278.aClass218ArrayArrayArray2082[n5].length; ++n8) {
                for (int n9 = 0; n9 < Class278.aClass218ArrayArrayArray2082[n5][0].length; ++n9) {
                    final Class218 class219 = Class278.aClass218ArrayArrayArray2082[n5][n8][n9];
                    if (class219 != null) {
                        for (Class246_Sub8 class246_Sub9 = (Class246_Sub8)class219.method2803((byte)15); class246_Sub9 != null; class246_Sub9 = (Class246_Sub8)class219.method2809(false)) {
                            final int n10 = n8 * 64 + class246_Sub9.aByte5128 + (n9 * 64 + class246_Sub9.aByte5125) * Class278.anInt2089;
                            class246_Sub9.anInt5124 = ((Class278.aByteArray2080[n10] & 0xFF) << 16 | (Class278.aShortArray2085[n10] & 0xFFFF));
                            if (class246_Sub9.anInt5124 != 0) {
                                final Class246_Sub8 class246_Sub10 = class246_Sub9;
                                class246_Sub10.anInt5124 |= 0xFF000000;
                            }
                        }
                    }
                }
            }
        }
        method3310(Class278.aByteArray2081, Class278.aByteArray2080, Class278.aShortArray2085, n, n2);
        Class278.aByteArray2081 = null;
        method3295();
    }
    
    static final Class98_Sub46_Sub10 method3306(final int n) {
        return (Class98_Sub46_Sub10)Class278.aClass377_2061.method3990(n, -1);
    }
    
    private static final void method3307(final ha ha, final Class98_Sub47 class98_Sub47, final int n, final int n2, final int n3, final int n4) {
        class98_Sub47.anInt4266 = Class278.anInt2086 + (n3 + n * (class98_Sub47.anInt4272 - Class278.anInt2091) >> 16);
        class98_Sub47.anInt4271 = Class278.anInt2094 - (n4 + n2 * (class98_Sub47.anInt4267 - Class278.anInt2090) >> 16);
    }
    
    static final void method3308(final int n, final int n2, final int n3, final int n4, final int anInt2086, final int anInt2087, final int anInt2088, final int anInt2089) {
        Class278.anInt2091 = n - Class278.anInt2075;
        Class278.anInt2083 = n2 - Class278.anInt2078;
        Class278.anInt2074 = n3 - Class278.anInt2075;
        Class278.anInt2090 = n4 - Class278.anInt2078;
        Class278.anInt2086 = anInt2086;
        Class278.anInt2077 = anInt2087;
        Class278.anInt2093 = anInt2088;
        Class278.anInt2094 = anInt2089;
    }
    
    static final void method3309(final ha ha) {
        method3301(ha, (Class278.anInt2093 - Class278.anInt2086 << 16) / (Class278.anInt2074 - Class278.anInt2091), (Class278.anInt2094 - Class278.anInt2077 << 16) / (Class278.anInt2083 - Class278.anInt2090), 0, 0);
    }
    
    private static final void method3310(final byte[] array, final byte[] array2, final short[] array3, final int n, final int n2) {
        final int[] array4 = new int[Class278.anInt2084];
        final int[] array5 = new int[Class278.anInt2084];
        final int[] array6 = new int[Class278.anInt2084];
        final int[] array7 = new int[Class278.anInt2084];
        final int[] array8 = new int[Class278.anInt2084];
        for (int i = -5; i < Class278.anInt2089; ++i) {
            final int n3 = i + 5;
            final int n4 = i - 5;
            for (int j = 0; j < Class278.anInt2084; ++j) {
                if (n3 < Class278.anInt2089) {
                    final int n5 = array[n3 + j * Class278.anInt2089] & 0xFF;
                    if (n5 > 0) {
                        final Class72 method2483 = Class278.aClass153_2055.method2483(n5 - 1, 125);
                        final int[] array9 = array4;
                        final int n6 = j;
                        array9[n6] += method2483.anInt538;
                        final int[] array10 = array5;
                        final int n7 = j;
                        array10[n7] += method2483.anInt541;
                        final int[] array11 = array6;
                        final int n8 = j;
                        array11[n8] += method2483.anInt542;
                        final int[] array12 = array7;
                        final int n9 = j;
                        array12[n9] += method2483.anInt540;
                        final int[] array13 = array8;
                        final int n10 = j;
                        ++array13[n10];
                    }
                }
                if (n4 >= 0) {
                    final int n11 = array[n4 + j * Class278.anInt2089] & 0xFF;
                    if (n11 > 0) {
                        final Class72 method2484 = Class278.aClass153_2055.method2483(n11 - 1, 120);
                        final int[] array14 = array4;
                        final int n12 = j;
                        array14[n12] -= method2484.anInt538;
                        final int[] array15 = array5;
                        final int n13 = j;
                        array15[n13] -= method2484.anInt541;
                        final int[] array16 = array6;
                        final int n14 = j;
                        array16[n14] -= method2484.anInt542;
                        final int[] array17 = array7;
                        final int n15 = j;
                        array17[n15] -= method2484.anInt540;
                        final int[] array18 = array8;
                        final int n16 = j;
                        --array18[n16];
                    }
                }
            }
            if (i >= 0) {
                int n17 = 0;
                int n18 = 0;
                int n19 = 0;
                int n20 = 0;
                int n21 = 0;
                for (int k = -5; k < Class278.anInt2084; ++k) {
                    final int n22 = k + 5;
                    if (n22 < Class278.anInt2084) {
                        n17 += array4[n22];
                        n18 += array5[n22];
                        n19 += array6[n22];
                        n20 += array7[n22];
                        n21 += array8[n22];
                    }
                    final int n23 = k - 5;
                    if (n23 >= 0) {
                        n17 -= array4[n23];
                        n18 -= array5[n23];
                        n19 -= array6[n23];
                        n20 -= array7[n23];
                        n21 -= array8[n23];
                    }
                    if (k >= 0 && n21 > 0) {
                        if ((array[i + k * Class278.anInt2089] & 0xFF) == 0x0) {
                            final int n24 = i + k * Class278.anInt2089;
                            array2[n24] = 0;
                            array3[n24] = 0;
                        }
                        else {
                            final int n25 = (n20 == 0) ? 0 : Class79.method801((byte)(-11), n18 / n21, n17 * 256 / n20, n19 / n21);
                            int n26 = (n25 & 0x7F) + n2;
                            if (n26 < 0) {
                                n26 = 0;
                            }
                            else if (n26 > 127) {
                                n26 = 127;
                            }
                            final int n27 = (n25 + n & 0xFC00) + (n25 & 0x380) + n26;
                            final int n28 = i + k * Class278.anInt2089;
                            final int n29 = Class221.anIntArray1665[Class111_Sub2.method2117(Class98_Sub10_Sub8.method1027(96, n27, -126), -118) & 0xFFFF];
                            array2[n28] = (byte)(n29 >> 16 & 0xFF);
                            array3[n28] = (short)(n29 & 0xFFFF);
                        }
                    }
                }
            }
        }
    }
    
    private static final int method3311(final d d, final int n, final int n2, final int n3) {
        final Class199 method317 = Class278.aClass32_2058.method317(4, n);
        if (method317 == null) {
            return 0;
        }
        int anInt1542 = method317.anInt1542;
        if (anInt1542 >= 0 && d.method11(anInt1542, -28755).aBoolean1825) {
            anInt1542 = -1;
        }
        int n5;
        if (method317.anInt1540 >= 0) {
            final int anInt1543 = method317.anInt1540;
            int n4 = (anInt1543 & 0x7F) + n3;
            if (n4 < 0) {
                n4 = 0;
            }
            else if (n4 > 127) {
                n4 = 127;
            }
            n5 = (0xFF000000 | Class221.anIntArray1665[Class111_Sub2.method2117(Class345.method3825(96, (anInt1543 + n2 & 0xFC00) + (anInt1543 & 0x380) + n4, (byte)(-21)), 51) & 0xFFFF]);
        }
        else if (anInt1542 >= 0) {
            n5 = (0xFF000000 | Class221.anIntArray1665[Class111_Sub2.method2117(Class345.method3825(96, d.method11(anInt1542, -28755).aShort1831, (byte)(-21)), 92) & 0xFFFF]);
        }
        else if (method317.anInt1537 == -1) {
            n5 = 0;
        }
        else {
            final int anInt1544 = method317.anInt1537;
            int n6 = (anInt1544 & 0x7F) + n3;
            if (n6 < 0) {
                n6 = 0;
            }
            else if (n6 > 127) {
                n6 = 127;
            }
            n5 = (0xFF000000 | Class221.anIntArray1665[Class111_Sub2.method2117(Class345.method3825(96, (anInt1544 + n2 & 0xFC00) + (anInt1544 & 0x380) + n6, (byte)(-21)), -41) & 0xFFFF]);
        }
        return n5;
    }
    
    private static final Class148 method3312(final ha ha, final int n, final int n2, final int n3, final int n4) {
        for (Class98_Sub47 class98_Sub47 = (Class98_Sub47)Class278.aClass148_2065.method2418(32); class98_Sub47 != null; class98_Sub47 = (Class98_Sub47)Class278.aClass148_2065.method2417(88)) {
            method3307(ha, class98_Sub47, n, n2, n3, n4);
        }
        return Class278.aClass148_2065;
    }
    
    public static void method3313() {
        Class278.aClass32_2058 = null;
        Class278.aClass153_2055 = null;
        Class278.aClass302_2062 = null;
        Class278.aClass341_2057 = null;
        Class278.aClass335_2059 = null;
        Class278.anInterface6_2060 = null;
        Class278.aClass98_Sub46_Sub10_2056 = null;
        Class278.aClass207_2054 = null;
        Class278.aClass377_2061 = null;
        Class278.aByteArrayArrayArray2072 = null;
        Class278.aShortArray2070 = null;
        Class278.aByteArray2067 = null;
        Class278.aClass370_2066 = null;
        Class278.aClass148_2065 = null;
        Class278.anIntArray2079 = null;
        Class278.aByteArray2081 = null;
        Class278.aByteArray2080 = null;
        Class278.aShortArray2085 = null;
        Class278.aByteArray2087 = null;
        Class278.aByteArray2073 = null;
        Class278.aShortArray2076 = null;
        Class278.aByteArray2092 = null;
        Class278.aClass377_2088 = null;
        Class278.aClass218ArrayArrayArray2082 = null;
    }
    
    static final void method3314(final ha ha, final Class98_Sub47 class98_Sub47, final Class24 class24) {
        if (class24.anIntArray265 != null) {
            final int[] array = new int[class24.anIntArray265.length];
            for (int i = 0; i < array.length / 2; ++i) {
                final int n = class24.anIntArray265[i * 2] + class98_Sub47.anInt4272;
                final int n2 = class24.anIntArray265[i * 2 + 1] + class98_Sub47.anInt4267;
                array[i * 2] = Class278.anInt2086 + (Class278.anInt2093 - Class278.anInt2086) * (n - Class278.anInt2091) / (Class278.anInt2074 - Class278.anInt2091);
                array[i * 2 + 1] = Class278.anInt2094 - (Class278.anInt2094 - Class278.anInt2077) * (n2 - Class278.anInt2090) / (Class278.anInt2083 - Class278.anInt2090);
            }
            Class136.method2270(ha, array, class24.anInt249);
            if (class24.anInt250 > 0) {
                for (int j = 0; j < array.length / 2 - 1; ++j) {
                    int n3 = array[j * 2];
                    int n4 = array[j * 2 + 1];
                    int n5 = array[(j + 1) * 2];
                    int n6 = array[(j + 1) * 2 + 1];
                    if (n5 < n3) {
                        final int n7 = n3;
                        final int n8 = n4;
                        n3 = n5;
                        n4 = n6;
                        n5 = n7;
                        n6 = n8;
                    }
                    else if (n5 == n3 && n6 < n4) {
                        final int n9 = n4;
                        n4 = n6;
                        n6 = n9;
                    }
                    ha.method1811(n3, n4, n5, n6, class24.anIntArray234[class24.aByteArray229[j] & 0xFF], 1, class24.anInt250, class24.anInt253, class24.anInt224);
                }
                int n10 = array[array.length - 2];
                int n11 = array[array.length - 1];
                int n12 = array[0];
                int n13 = array[1];
                if (n12 < n10) {
                    final int n14 = n10;
                    final int n15 = n11;
                    n10 = n12;
                    n11 = n13;
                    n12 = n14;
                    n13 = n15;
                }
                else if (n12 == n10 && n13 < n11) {
                    final int n16 = n11;
                    n11 = n13;
                    n13 = n16;
                }
                ha.method1811(n10, n11, n12, n13, class24.anIntArray234[class24.aByteArray229[class24.aByteArray229.length - 1] & 0xFF], 1, class24.anInt250, class24.anInt253, class24.anInt224);
            }
            else {
                for (int k = 0; k < array.length / 2 - 1; ++k) {
                    ha.method1789(array[k * 2 + 1], class24.anIntArray234[class24.aByteArray229[k] & 0xFF], array[(k + 1) * 2 + 1], array[(k + 1) * 2], -10550, array[k * 2]);
                }
                ha.method1789(array[array.length - 1], class24.anIntArray234[class24.aByteArray229[class24.aByteArray229.length - 1] & 0xFF], array[1], array[0], -10550, array[array.length - 2]);
            }
        }
    }
    
    static final Class148 method3315(final ha ha) {
        return method3312(ha, (Class278.anInt2093 - Class278.anInt2086 << 16) / (Class278.anInt2074 - Class278.anInt2091), (Class278.anInt2094 - Class278.anInt2077 << 16) / (Class278.anInt2083 - Class278.anInt2090), 0, 0);
    }
    
    static final void method3316(final int n) {
        Class278.aClass98_Sub46_Sub10_2056 = (Class98_Sub46_Sub10)Class278.aClass377_2061.method3990(n, -1);
    }
    
    private static final void method3317(final ha ha, final int n, final int n2, final int n3, final int n4, final short[] array, final byte[] array2) {
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                final Class352 method3546 = Class278.aClass302_2062.method3546(array[i] & 0xFFFF, (byte)119);
                final int anInt2990 = method3546.anInt2990;
                if (anInt2990 != -1) {
                    final Class9 method3547 = Class278.aClass335_2059.method3766(anInt2990, (byte)(-28));
                    final Class332 method3548 = method3547.method190(method3546.aBoolean2976 && method3546.aBoolean2961, 0, method3546.aBoolean3004 ? (array2[i] >> 6 & 0x3) : 0, ha);
                    if (method3548 != null) {
                        int n5 = n3 * method3548.method3737() >> 2;
                        int n6 = n4 * method3548.method3749() >> 2;
                        if (method3547.aBoolean116) {
                            int sizeY = method3546.sizeY;
                            int sizeX = method3546.sizeX;
                            if ((array2[i] >> 6 & 0x1) == 0x1) {
                                final int n7 = sizeY;
                                sizeY = sizeX;
                                sizeX = n7;
                            }
                            n5 = sizeY * n3;
                            n6 = sizeX * n4;
                        }
                        if (n5 != 0 && n6 != 0) {
                            if (method3547.anInt115 != 0) {
                                method3548.method3727(n, n2 - n6 + n4, n5, n6, 0, 0xFF000000 | method3547.anInt115, 1);
                            }
                            else {
                                method3548.method3726(n, n2 - n6 + n4, n5, n6);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private static final void method3318(final ha ha, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final short[] array, final byte[] array2, final boolean b) {
        if (b || n5 != 0 || n6 > 0) {
            if (n6 == 0) {
                ha.aa(n, n2, n3, n4, n5, 0);
            }
            else {
                final int n8 = n7 & 0x3F;
                if (n8 == 0 || n3 <= 1 || n4 <= 1) {
                    final int n9 = Class278.anIntArray2079[n6];
                    if (b || n9 != 0) {
                        ha.aa(n, n2, n3, n4, n9, 0);
                    }
                }
                else {
                    Class276.method3287(n7 >> 6 & 0x3, n5, Class278.anInt2069, n, n4, n8, Class278.anIntArray2079[n6], ha, n2, n3, Class278.aByteArrayArrayArray2072, b ? 0 : 1, 10);
                }
            }
        }
        if (array != null) {
            int n10;
            if (n3 == 1) {
                n10 = n;
            }
            else {
                n10 = n + n3 - 1;
            }
            int n11;
            if (n4 == 1) {
                n11 = n2;
            }
            else {
                n11 = n2 + n4 - 1;
            }
            for (int i = 0; i < array.length; ++i) {
                final byte b2 = (byte)(array2[i] & 0x3F);
                if (b2 == 0 || b2 == 2 || b2 == 3 || b2 == 9) {
                    final Class352 method3546 = Class278.aClass302_2062.method3546(array[i] & 0xFFFF, (byte)119);
                    if (method3546.anInt2990 == -1) {
                        int n12 = -3355444;
                        if (method3546.anInt2998 == 1) {
                            n12 = -3407872;
                        }
                        final int n13 = array2[i] >> 6 & 0x3;
                        if (b2 == 0) {
                            if (n13 == 0) {
                                ha.P(n, n2, n4, n12, 0);
                            }
                            else if (n13 == 1) {
                                ha.U(n, n2, n3, n12, 0);
                            }
                            else if (n13 == 2) {
                                ha.P(n10, n2, n4, n12, 0);
                            }
                            else {
                                ha.U(n, n11, n3, n12, 0);
                            }
                        }
                        else if (b2 == 2) {
                            if (n13 == 0) {
                                ha.P(n, n2, n4, -1, 0);
                                ha.U(n, n2, n3, n12, 0);
                            }
                            else if (n13 == 1) {
                                ha.P(n10, n2, n4, -1, 0);
                                ha.U(n, n2, n3, n12, 0);
                            }
                            else if (n13 == 2) {
                                ha.P(n10, n2, n4, -1, 0);
                                ha.U(n, n11, n3, n12, 0);
                            }
                            else {
                                ha.P(n, n2, n4, -1, 0);
                                ha.U(n, n11, n3, n12, 0);
                            }
                        }
                        else if (b2 == 3) {
                            if (n13 == 0) {
                                ha.U(n, n2, 1, n12, 0);
                            }
                            else if (n13 == 1) {
                                ha.U(n10, n2, 1, n12, 0);
                            }
                            else if (n13 == 2) {
                                ha.U(n10, n11, 1, n12, 0);
                            }
                            else {
                                ha.U(n, n11, 1, n12, 0);
                            }
                        }
                        else if (b2 == 9) {
                            if (n13 == 0 || n13 == 2) {
                                for (int j = 0; j < n4; ++j) {
                                    ha.U(n + j, n11 - j, 1, n12, 0);
                                }
                            }
                            else {
                                for (int k = 0; k < n4; ++k) {
                                    ha.U(n + k, n2 + k, 1, n12, 0);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    static {
        Class278.aClass377_2061 = new Class377(16);
        Class278.anInt2063 = (int)(Math.random() * 11.0) - 5;
        Class278.anInt2071 = (int)(Math.random() * 17.0) - 8;
        Class278.aShortArray2070 = new short[1];
        Class278.aByteArray2067 = new byte[1];
        Class278.aClass148_2065 = new Class148();
    }
}
