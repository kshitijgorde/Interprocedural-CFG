// 
// Decompiled by Procyon v0.5.30
// 

class Class305
{
    private byte[][][] aByteArrayArrayArray2540;
    static int[] anIntArray2541;
    int anInt2542;
    int anInt2543;
    boolean aBoolean2544;
    static float aFloat2545;
    private Class153 aClass153_2546;
    int anInt2547;
    private byte[][][] aByteArrayArrayArray2548;
    int[][][] anIntArrayArrayArray2549;
    byte[][][] aByteArrayArrayArray2550;
    private int[] anIntArray2551;
    private Class32 aClass32_2552;
    private int[] anIntArray2553;
    byte[][][] aByteArrayArrayArray2554;
    private byte[][][] aByteArrayArrayArray2555;
    private byte[][][] aByteArrayArrayArray2556;
    
    final void method3567(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            for (int n7 = n5; n4 + n5 > n7; ++n7) {
                for (int i = n3; i < n3 + n; ++i) {
                    if (~i <= -1 && i < this.anInt2543 && ~n7 <= -1 && ~n7 > ~this.anInt2542) {
                        this.anIntArrayArrayArray2549[n2][i][n7] = ((n2 <= 0) ? 0 : (-960 + this.anIntArrayArrayArray2549[-1 + n2][i][n7]));
                    }
                }
            }
            if (~n3 < -1 && n3 < this.anInt2543) {
                for (int n8 = n5 + 1; ~n8 > ~(n4 + n5); ++n8) {
                    if (n8 >= 0 && n8 < this.anInt2542) {
                        this.anIntArrayArrayArray2549[n2][n3][n8] = this.anIntArrayArrayArray2549[n2][-1 + n3][n8];
                    }
                }
            }
            if (~n5 < -1 && n5 < this.anInt2542) {
                for (int n9 = 1 + n3; ~n9 > ~(n3 - -n); ++n9) {
                    if (n9 >= 0 && n9 < this.anInt2543) {
                        this.anIntArrayArrayArray2549[n2][n9][n5] = this.anIntArrayArrayArray2549[n2][n9][-1 + n5];
                    }
                }
            }
            if (n6 >= ~n3 && ~n5 <= -1 && ~this.anInt2543 < ~n3 && ~this.anInt2542 < ~n5) {
                if (~n2 == -1) {
                    if (~n3 >= -1 || ~this.anIntArrayArrayArray2549[n2][-1 + n3][n5] == -1) {
                        if (n5 > 0 && ~this.anIntArrayArrayArray2549[n2][n3][n5 - 1] != -1) {
                            this.anIntArrayArrayArray2549[n2][n3][n5] = this.anIntArrayArrayArray2549[n2][n3][n5 - 1];
                        }
                        else if (~n3 < -1 && ~n5 < -1 && ~this.anIntArrayArrayArray2549[n2][-1 + n3][n5 - 1] != -1) {
                            this.anIntArrayArrayArray2549[n2][n3][n5] = this.anIntArrayArrayArray2549[n2][-1 + n3][n5 - 1];
                        }
                    }
                    else {
                        this.anIntArrayArrayArray2549[n2][n3][n5] = this.anIntArrayArrayArray2549[n2][n3 - 1][n5];
                    }
                }
                else if (~n3 >= -1 || this.anIntArrayArrayArray2549[-1 + n2][n3 - 1][n5] == this.anIntArrayArrayArray2549[n2][n3 - 1][n5]) {
                    if (~n5 < -1 && this.anIntArrayArrayArray2549[n2][n3][n5 - 1] != this.anIntArrayArrayArray2549[n2 - 1][n3][-1 + n5]) {
                        this.anIntArrayArrayArray2549[n2][n3][n5] = this.anIntArrayArrayArray2549[n2][n3][n5 - 1];
                    }
                    else if (n3 > 0 && n5 > 0 && this.anIntArrayArrayArray2549[n2][n3 - 1][-1 + n5] != this.anIntArrayArrayArray2549[-1 + n2][-1 + n3][n5 - 1]) {
                        this.anIntArrayArrayArray2549[n2][n3][n5] = this.anIntArrayArrayArray2549[n2][n3 - 1][n5 - 1];
                    }
                }
                else {
                    this.anIntArrayArrayArray2549[n2][n3][n5] = this.anIntArrayArrayArray2549[n2][n3 - 1][n5];
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sm.O(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    final void method3568(final s s, final byte b, final s s2, final ha ha) {
        try {
            final int[][] array = new int[this.anInt2543][this.anInt2542];
            if (Class138.anIntArray1083 == null || Class138.anIntArray1083.length != this.anInt2542) {
                OutputStream_Sub2.anIntArray42 = new int[this.anInt2542];
                Class138.anIntArray1083 = new int[this.anInt2542];
                Class294.anIntArray2406 = new int[this.anInt2542];
                Class145.anIntArray1175 = new int[this.anInt2542];
                Class284_Sub1.anIntArray5178 = new int[this.anInt2542];
            }
            for (int n = 0; this.anInt2547 > n; ++n) {
                for (int i = 0; i < this.anInt2542; ++i) {
                    Class138.anIntArray1083[i] = 0;
                    Class294.anIntArray2406[i] = 0;
                    Class145.anIntArray1175[i] = 0;
                    OutputStream_Sub2.anIntArray42[i] = 0;
                    Class284_Sub1.anIntArray5178[i] = 0;
                }
                for (int n2 = -5; this.anInt2543 > n2; ++n2) {
                    for (int n3 = 0; this.anInt2542 > n3; ++n3) {
                        final int n4 = n2 + 5;
                        if (~this.anInt2543 < ~n4) {
                            final int n5 = 0xFF & this.aByteArrayArrayArray2548[n][n4][n3];
                            if (~n5 < -1) {
                                final Class72 method2483 = this.aClass153_2546.method2483(n5 - 1, 123);
                                final int[] anIntArray1083 = Class138.anIntArray1083;
                                final int n6 = n3;
                                anIntArray1083[n6] += method2483.anInt538;
                                final int[] anIntArray1084 = Class294.anIntArray2406;
                                final int n7 = n3;
                                anIntArray1084[n7] += method2483.anInt541;
                                final int[] anIntArray1085 = Class145.anIntArray1175;
                                final int n8 = n3;
                                anIntArray1085[n8] += method2483.anInt542;
                                final int[] anIntArray1086 = OutputStream_Sub2.anIntArray42;
                                final int n9 = n3;
                                anIntArray1086[n9] += method2483.anInt540;
                                final int[] anIntArray1087 = Class284_Sub1.anIntArray5178;
                                final int n10 = n3;
                                ++anIntArray1087[n10];
                            }
                        }
                        final int n11 = n2 - 5;
                        if (n11 >= 0) {
                            final int n12 = this.aByteArrayArrayArray2548[n][n11][n3] & 0xFF;
                            if (n12 > 0) {
                                final Class72 method2484 = this.aClass153_2546.method2483(-1 + n12, 121);
                                final int[] anIntArray1088 = Class138.anIntArray1083;
                                final int n13 = n3;
                                anIntArray1088[n13] -= method2484.anInt538;
                                final int[] anIntArray1089 = Class294.anIntArray2406;
                                final int n14 = n3;
                                anIntArray1089[n14] -= method2484.anInt541;
                                final int[] anIntArray1090 = Class145.anIntArray1175;
                                final int n15 = n3;
                                anIntArray1090[n15] -= method2484.anInt542;
                                final int[] anIntArray1091 = OutputStream_Sub2.anIntArray42;
                                final int n16 = n3;
                                anIntArray1091[n16] -= method2484.anInt540;
                                final int[] anIntArray1092 = Class284_Sub1.anIntArray5178;
                                final int n17 = n3;
                                --anIntArray1092[n17];
                            }
                        }
                    }
                    if (~n2 <= -1) {
                        int n18 = 0;
                        int n19 = 0;
                        int n20 = 0;
                        int n21 = 0;
                        int n22 = 0;
                        for (int j = -5; j < this.anInt2542; ++j) {
                            final int n23 = j + 5;
                            if (n23 < this.anInt2542) {
                                n18 += Class138.anIntArray1083[n23];
                                n21 += OutputStream_Sub2.anIntArray42[n23];
                                n20 += Class145.anIntArray1175[n23];
                                n22 += Class284_Sub1.anIntArray5178[n23];
                                n19 += Class294.anIntArray2406[n23];
                            }
                            final int n24 = j - 5;
                            if (n24 >= 0) {
                                n22 -= Class284_Sub1.anIntArray5178[n24];
                                n19 -= Class294.anIntArray2406[n24];
                                n18 -= Class138.anIntArray1083[n24];
                                n21 -= OutputStream_Sub2.anIntArray42[n24];
                                n20 -= Class145.anIntArray1175[n24];
                            }
                            if (~j <= -1 && n21 > 0 && ~n22 < -1) {
                                array[n2][j] = Class79.method801((byte)(-11), n19 / n22, n18 * 256 / n21, n20 / n22);
                            }
                        }
                    }
                }
                if (Class319.aBoolean2707) {
                    this.method3578((~n != -1) ? null : s2, ha, Class78.aSArray594[n], array, (n != 0) ? null : s, -22657, n);
                }
                else {
                    this.method3576(ha, (~n != -1) ? null : s, array, (~n != -1) ? null : s2, Class78.aSArray594[n], n, (byte)126);
                }
                this.aByteArrayArrayArray2548[n] = null;
                this.aByteArrayArrayArray2540[n] = null;
                this.aByteArrayArrayArray2555[n] = null;
                this.aByteArrayArrayArray2556[n] = null;
            }
            if (!this.aBoolean2544) {
                if (~Class61.anInt479 != -1) {
                    Class364.method3935();
                }
                if (Class97.aBoolean830) {
                    Class146.method2335();
                }
            }
            for (int n25 = 0; this.anInt2547 > n25; ++n25) {
                Class78.aSArray594[n25].YA();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sm.L(" + ((s != null) ? "{...}" : "null") + ',' + b + ',' + ((s2 != null) ? "{...}" : "null") + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method3569(final int n, final boolean b, final int n2, final int n3, final int n4) {
        try {
            if (b) {
                for (int i = 0; i < this.anInt2547; ++i) {
                    this.method3567(n, i, n4, n3, n2, -1);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sm.N(" + n + ',' + b + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    private final void method3570(final int n, final int n2, final ha ha, final byte b, final byte[][] array, final int n3, final Class72 class72, final int n4, final int n5, final boolean[] array2, final byte[][] array3, final byte[][] array4, final Class199 class73, final int n6) {
        try {
            final boolean[] array5 = (class73 != null && class73.aBoolean1526) ? Class161.aBooleanArrayArray1260[n5] : Class98_Sub46_Sub12.aBooleanArrayArray6034[n5];
            if (~n4 < -1) {
                if (~n < -1) {
                    final int n7 = array[n - 1][n4 - 1] & 0xFF;
                    if (n7 > 0) {
                        final Class199 method317 = this.aClass32_2552.method317(4, -1 + n7);
                        if (method317.anInt1537 != -1 && method317.aBoolean1526) {
                            final byte b2 = array3[-1 + n][-1 + n4];
                            final byte b3 = (byte)(4 - -(array4[-1 + n][-1 + n4] * 2) & 0x7);
                            final int method318 = Class98_Sub16.method1149(false, method317, ha);
                            if (Class310.aBooleanArrayArray2653[b2][b3]) {
                                Class98_Sub10_Sub4.anIntArray5547[0] = method317.anInt1537;
                                Class277.anIntArray2049[0] = method318;
                                Class135.anIntArray1056[0] = method317.anInt1542;
                                Class156_Sub1.anIntArray3279[0] = method317.anInt1529;
                                Class95.anIntArray800[0] = method317.anInt1535;
                                Class275.anIntArray2048[0] = 256;
                            }
                        }
                    }
                }
                if (n < -1 + n2) {
                    final int n8 = array[n + 1][n4 - 1] & 0xFF;
                    if (n8 > 0) {
                        final Class199 method319 = this.aClass32_2552.method317(4, n8 - 1);
                        if (~method319.anInt1537 != 0x0 && method319.aBoolean1526) {
                            final byte b4 = array3[1 + n][n4 - 1];
                            final byte b5 = (byte)(0x7 & 2 * array4[1 + n][n4 - 1] + 6);
                            final int method320 = Class98_Sub16.method1149(false, method319, ha);
                            if (Class310.aBooleanArrayArray2653[b4][b5]) {
                                Class98_Sub10_Sub4.anIntArray5547[2] = method319.anInt1537;
                                Class277.anIntArray2049[2] = method320;
                                Class135.anIntArray1056[2] = method319.anInt1542;
                                Class156_Sub1.anIntArray3279[2] = method319.anInt1529;
                                Class95.anIntArray800[2] = method319.anInt1535;
                                Class275.anIntArray2048[2] = 512;
                            }
                        }
                    }
                }
            }
            if (-1 + n6 > n4) {
                if (~n < -1) {
                    final int n9 = 0xFF & array[n - 1][1 + n4];
                    if (~n9 < -1) {
                        final Class199 method321 = this.aClass32_2552.method317(4, n9 - 1);
                        if (~method321.anInt1537 != 0x0 && method321.aBoolean1526) {
                            final byte b6 = array3[n - 1][n4 + 1];
                            final byte b7 = (byte)(2 * array4[-1 + n][1 + n4] + 2 & 0x7);
                            final int method322 = Class98_Sub16.method1149(false, method321, ha);
                            if (Class310.aBooleanArrayArray2653[b6][b7]) {
                                Class98_Sub10_Sub4.anIntArray5547[6] = method321.anInt1537;
                                Class277.anIntArray2049[6] = method322;
                                Class135.anIntArray1056[6] = method321.anInt1542;
                                Class156_Sub1.anIntArray3279[6] = method321.anInt1529;
                                Class95.anIntArray800[6] = method321.anInt1535;
                                Class275.anIntArray2048[6] = 64;
                            }
                        }
                    }
                }
                if (n < -1 + n2) {
                    final int n10 = 0xFF & array[1 + n][1 + n4];
                    if (n10 > 0) {
                        final Class199 method323 = this.aClass32_2552.method317(4, -1 + n10);
                        if (~method323.anInt1537 != 0x0 && method323.aBoolean1526) {
                            final byte b8 = array3[1 + n][1 + n4];
                            final byte b9 = (byte)(2 * array4[1 + n][n4 + 1] & 0x7);
                            final int method324 = Class98_Sub16.method1149(false, method323, ha);
                            if (Class310.aBooleanArrayArray2653[b8][b9]) {
                                Class98_Sub10_Sub4.anIntArray5547[4] = method323.anInt1537;
                                Class277.anIntArray2049[4] = method324;
                                Class135.anIntArray1056[4] = method323.anInt1542;
                                Class156_Sub1.anIntArray3279[4] = method323.anInt1529;
                                Class95.anIntArray800[4] = method323.anInt1535;
                                Class275.anIntArray2048[4] = 128;
                            }
                        }
                    }
                }
            }
            if (n4 > 0) {
                final int n11 = array[n][-1 + n4] & 0xFF;
                if (~n11 < -1) {
                    final Class199 method325 = this.aClass32_2552.method317(4, n11 - 1);
                    if (method325.anInt1537 != -1) {
                        final byte b10 = array3[n][n4 - 1];
                        final byte b11 = array4[n][n4 - 1];
                        if (!method325.aBoolean1526) {
                            if (!array5[n3 & 0x3]) {
                                array2[0] = Class98_Sub46_Sub12.aBooleanArrayArray6034[b10][Class202.method2702(2 + b11, 3)];
                            }
                        }
                        else {
                            int n12 = 2;
                            int n13 = 2 * b11 + 4;
                            final int method326 = Class98_Sub16.method1149(false, method325, ha);
                            for (int i = 0; i < 3; ++i) {
                                n13 &= 0x7;
                                n12 &= 0x7;
                                if (Class310.aBooleanArrayArray2653[b10][n13] && ~Class95.anIntArray800[n12] >= ~method325.anInt1535) {
                                    Class98_Sub10_Sub4.anIntArray5547[n12] = method325.anInt1537;
                                    Class277.anIntArray2049[n12] = method326;
                                    Class135.anIntArray1056[n12] = method325.anInt1542;
                                    Class156_Sub1.anIntArray3279[n12] = method325.anInt1529;
                                    if (method325.anInt1535 != Class95.anIntArray800[n12]) {
                                        Class275.anIntArray2048[n12] = 32;
                                    }
                                    else {
                                        Class275.anIntArray2048[n12] = Class41.method366(Class275.anIntArray2048[n12], 32);
                                    }
                                    Class95.anIntArray800[n12] = method325.anInt1535;
                                }
                                ++n13;
                                --n12;
                            }
                            if (!array5[0x3 & n3]) {
                                array2[0] = Class161.aBooleanArrayArray1260[b10][Class202.method2702(2 - -b11, 3)];
                            }
                        }
                    }
                }
            }
            if (~(-1 + n6) < ~n4) {
                final int n14 = array[n][1 + n4] & 0xFF;
                if (~n14 < -1) {
                    final Class199 method327 = this.aClass32_2552.method317(4, -1 + n14);
                    if (method327.anInt1537 != -1) {
                        final byte b12 = array3[n][n4 + 1];
                        int n15 = array4[n][1 + n4];
                        if (!method327.aBoolean1526) {
                            if (!array5[0x3 & n3 + 2]) {
                                array2[2] = Class98_Sub46_Sub12.aBooleanArrayArray6034[b12][Class202.method2702(3, --n15)];
                            }
                        }
                        else {
                            int n16 = 4;
                            int n17 = n15 * 2 + 2;
                            final int method328 = Class98_Sub16.method1149(false, method327, ha);
                            for (int n18 = 0; ~n18 > -4; ++n18) {
                                n17 &= 0x7;
                                n16 &= 0x7;
                                if (Class310.aBooleanArrayArray2653[b12][n17] && method327.anInt1535 >= Class95.anIntArray800[n16]) {
                                    Class98_Sub10_Sub4.anIntArray5547[n16] = method327.anInt1537;
                                    Class277.anIntArray2049[n16] = method328;
                                    Class135.anIntArray1056[n16] = method327.anInt1542;
                                    Class156_Sub1.anIntArray3279[n16] = method327.anInt1529;
                                    if (Class95.anIntArray800[n16] == method327.anInt1535) {
                                        Class275.anIntArray2048[n16] = Class41.method366(Class275.anIntArray2048[n16], 16);
                                    }
                                    else {
                                        Class275.anIntArray2048[n16] = 16;
                                    }
                                    Class95.anIntArray800[n16] = method327.anInt1535;
                                }
                                --n17;
                                ++n16;
                            }
                            if (!array5[2 - -n3 & 0x3]) {
                                array2[2] = Class161.aBooleanArrayArray1260[b12][Class202.method2702(n15, 3)];
                            }
                        }
                    }
                }
            }
            if (n > 0) {
                final int n19 = array[n - 1][n4] & 0xFF;
                if (n19 > 0) {
                    final Class199 method329 = this.aClass32_2552.method317(4, -1 + n19);
                    if (method329.anInt1537 != -1) {
                        final byte b13 = array3[-1 + n][n4];
                        final byte b14 = array4[-1 + n][n4];
                        if (method329.aBoolean1526) {
                            int n20 = 6;
                            int n21 = 2 * b14 + 4;
                            final int method330 = Class98_Sub16.method1149(false, method329, ha);
                            for (int j = 0; j < 3; ++j) {
                                n20 &= 0x7;
                                n21 &= 0x7;
                                if (Class310.aBooleanArrayArray2653[b13][n21] && method329.anInt1535 >= Class95.anIntArray800[n20]) {
                                    Class98_Sub10_Sub4.anIntArray5547[n20] = method329.anInt1537;
                                    Class277.anIntArray2049[n20] = method330;
                                    Class135.anIntArray1056[n20] = method329.anInt1542;
                                    Class156_Sub1.anIntArray3279[n20] = method329.anInt1529;
                                    if (method329.anInt1535 != Class95.anIntArray800[n20]) {
                                        Class275.anIntArray2048[n20] = 8;
                                    }
                                    else {
                                        Class275.anIntArray2048[n20] = Class41.method366(Class275.anIntArray2048[n20], 8);
                                    }
                                    Class95.anIntArray800[n20] = method329.anInt1535;
                                }
                                --n21;
                                ++n20;
                            }
                            if (!array5[0x3 & 3 - -n3]) {
                                array2[3] = Class161.aBooleanArrayArray1260[b13][Class202.method2702(3, b14 + 1)];
                            }
                        }
                        else if (!array5[n3 + 3 & 0x3]) {
                            array2[3] = Class98_Sub46_Sub12.aBooleanArrayArray6034[b13][Class202.method2702(1 + b14, 3)];
                        }
                    }
                }
            }
            if (-1 + n2 > n) {
                final int n22 = 0xFF & array[1 + n][n4];
                if (~n22 < -1) {
                    final Class199 method331 = this.aClass32_2552.method317(4, n22 - 1);
                    if (~method331.anInt1537 != 0x0) {
                        final byte b15 = array3[n + 1][n4];
                        final byte b16 = array4[1 + n][n4];
                        if (!method331.aBoolean1526) {
                            if (!array5[1 + n3 & 0x3]) {
                                array2[1] = Class98_Sub46_Sub12.aBooleanArrayArray6034[b15][Class202.method2702(3, b16 + 3)];
                            }
                        }
                        else {
                            int n23 = 4;
                            int n24 = 2 * b16 + 6;
                            final int method332 = Class98_Sub16.method1149(false, method331, ha);
                            for (int n25 = 0; ~n25 > -4; ++n25) {
                                n23 &= 0x7;
                                n24 &= 0x7;
                                if (Class310.aBooleanArrayArray2653[b15][n24] && ~method331.anInt1535 <= ~Class95.anIntArray800[n23]) {
                                    Class98_Sub10_Sub4.anIntArray5547[n23] = method331.anInt1537;
                                    Class277.anIntArray2049[n23] = method332;
                                    Class135.anIntArray1056[n23] = method331.anInt1542;
                                    Class156_Sub1.anIntArray3279[n23] = method331.anInt1529;
                                    if (method331.anInt1535 != Class95.anIntArray800[n23]) {
                                        Class275.anIntArray2048[n23] = 4;
                                    }
                                    else {
                                        Class275.anIntArray2048[n23] = Class41.method366(Class275.anIntArray2048[n23], 4);
                                    }
                                    Class95.anIntArray800[n23] = method331.anInt1535;
                                }
                                ++n24;
                                --n23;
                            }
                            if (!array5[0x3 & 1 - -n3]) {
                                array2[1] = Class161.aBooleanArrayArray1260[b15][Class202.method2702(b16 + 3, 3)];
                            }
                        }
                    }
                }
            }
            if (class73 != null) {
                final int method333 = Class98_Sub16.method1149(false, class73, ha);
                if (class73.aBoolean1526) {
                    for (int k = 0; k < 8; ++k) {
                        final int n26 = -(n3 * 2) + k & 0x7;
                        if (Class310.aBooleanArrayArray2653[n5][k] && Class95.anIntArray800[n26] <= class73.anInt1535) {
                            Class98_Sub10_Sub4.anIntArray5547[n26] = class73.anInt1537;
                            Class277.anIntArray2049[n26] = method333;
                            Class135.anIntArray1056[n26] = class73.anInt1542;
                            Class156_Sub1.anIntArray3279[n26] = class73.anInt1529;
                            if (class73.anInt1535 != Class95.anIntArray800[n26]) {
                                Class275.anIntArray2048[n26] = 2;
                            }
                            else {
                                Class275.anIntArray2048[n26] = Class41.method366(Class275.anIntArray2048[n26], 2);
                            }
                            Class95.anIntArray800[n26] = class73.anInt1535;
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sm.D(" + n + ',' + n2 + ',' + ((ha != null) ? "{...}" : "null") + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n3 + ',' + ((class72 != null) ? "{...}" : "null") + ',' + n4 + ',' + n5 + ',' + ((array2 != null) ? "{...}" : "null") + ',' + ((array3 != null) ? "{...}" : "null") + ',' + ((array4 != null) ? "{...}" : "null") + ',' + ((class73 != null) ? "{...}" : "null") + ',' + n6 + ')');
        }
    }
    
    static final void method3571(final int n) {
        try {
            if (Class76_Sub11.anApplet3799 != null) {
                try {
                    Class203.method2706(Class76_Sub11.anApplet3799, "document.cookie=\"" + ("usrdob=" + ((int)(Class343.method3819(-47) / 86400000L) - 11745) + "; version=1; path=/; domain=" + Class76_Sub11.anApplet3799.getParameter("cookiehost")) + "\"", 9202);
                }
                catch (Throwable t) {}
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sm.E(" + n + ')');
        }
    }
    
    static final void method3572(final int n, final Class246_Sub2 class246_Sub2) {
        try {
            class246_Sub2.aClass246_Sub3_Sub4_Sub2_5076 = null;
            if (Class98_Sub50.anInt4294 < n) {
                Class138.aClass218_1084.method2808(true, class246_Sub2);
                ++Class98_Sub50.anInt4294;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sm.B(" + n + ',' + ((class246_Sub2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method3573(final byte b) {
        try {
            Class305.anIntArray2541 = null;
            if (b != -20) {
                Class305.aFloat2545 = -0.16454314f;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sm.C(" + b + ')');
        }
    }
    
    final void method3574(final byte b, final int n, final int n2, final int n3, final Class98_Sub22 class98_Sub22, final int n4, final Class243[] array) {
        try {
            if (!this.aBoolean2544) {
                for (int n5 = 0; ~n5 > -5; ++n5) {
                    final Class243 class243 = array[n5];
                    for (int n6 = 0; ~n6 > -65; ++n6) {
                        for (int n7 = 0; ~n7 > -65; ++n7) {
                            final int n8 = n6 + n4;
                            final int n9 = n + n7;
                            if (n8 >= 0 && n8 < this.anInt2543 && n9 >= 0 && n9 < this.anInt2542) {
                                class243.method2944(n9, 101, n8);
                            }
                        }
                    }
                }
            }
            final int n10 = n4 + n3;
            final int n11 = n2 + n;
            for (int i = 0; i < this.anInt2547; ++i) {
                for (int j = 0; j < 64; ++j) {
                    for (int k = 0; k < 64; ++k) {
                        this.method3581(n + k, false, 0, j + n10, n11 + k, n4 + j, (byte)(-111), 0, 0, i, class98_Sub22);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sm.H(" + b + ',' + n + ',' + n2 + ',' + n3 + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n4 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method3575(final int n, final int n2, final Class98_Sub22 class98_Sub22, final int n3, final int n4, final int n5, final int n6, final int n7, final Class243[] array, final int n8) {
        try {
            final int n9 = (0x7 & n7) * 8;
            final int n10 = (0x7 & n) * 8;
            if (!this.aBoolean2544) {
                final Class243 class243 = array[n8];
                for (int i = 0; i < 8; ++i) {
                    for (int n11 = 0; ~n11 > -9; ++n11) {
                        final int n12 = n5 + Class107.method1720(0x7 & i, n6, n11 & 0x7, n3);
                        final int n13 = Class250.method3166(0x7 & i, 0x7 & n11, n3, (byte)64) + n2;
                        if (~n12 < -1 && n12 < -1 + this.anInt2543 && ~n13 < -1 && n13 < -1 + this.anInt2542) {
                            class243.method2944(n13, n6 ^ 0x31, n12);
                        }
                    }
                }
            }
            final int n14 = (0xFFFFFFF8 & n7) << 633216931;
            final int n15 = (0xFFFFFFF8 & n) << 924843875;
            int n16 = 0;
            int n17 = 0;
            if (~n3 != 0xFFFFFFFE) {
                if (n3 != 2) {
                    if (~n3 == 0xFFFFFFFC) {
                        n16 = 1;
                    }
                }
                else {
                    n17 = 1;
                    n16 = 1;
                }
            }
            else {
                n17 = 1;
            }
            for (int n18 = n6; ~this.anInt2547 < ~n18; ++n18) {
                for (int n19 = 0; ~n19 > -65; ++n19) {
                    for (int j = 0; j < 64; ++j) {
                        if (~n18 == ~n4 && ~n9 >= ~n19 && n19 <= 8 + n9 && j >= n10 && j <= n10 + 8) {
                            int n20;
                            int n21;
                            if (8 + n9 == n19 || n10 + 8 == j) {
                                if (~n3 == -1) {
                                    n20 = -n10 + (j + n2);
                                    n21 = -n9 - (-n19 - n5);
                                }
                                else if (n3 == 1) {
                                    n20 = -n19 + n9 + n2 + 8;
                                    n21 = n5 - -j + -n10;
                                }
                                else if (n3 != 2) {
                                    n21 = n5 + (8 + -j - -n10);
                                    n20 = n2 + n19 - n9;
                                }
                                else {
                                    n20 = -j + n10 + (n2 + 8);
                                    n21 = -n19 + (n9 + (n5 + 8));
                                }
                                this.method3581(n20, true, 0, n19 + n14, j + n15, n21, (byte)(-111), 0, 0, n8, class98_Sub22);
                            }
                            else {
                                n21 = Class107.method1720(0x7 & n19, 0, j & 0x7, n3) + n5;
                                n20 = n2 + Class250.method3166(n19 & 0x7, j & 0x7, n3, (byte)(-125));
                                this.method3581(n20, false, n3, n19 + n14, j + n15, n21, (byte)(-111), n17, n16, n8, class98_Sub22);
                            }
                            if (~n19 == 0xFFFFFFC0 || j == 63) {
                                int n22 = 1;
                                if (n19 == 63 && ~j == 0xFFFFFFC0) {
                                    n22 = 3;
                                }
                                for (int n23 = 0; ~n22 < ~n23; ++n23) {
                                    int n24 = n19;
                                    int n25 = j;
                                    if (n23 == 0) {
                                        n24 = ((n19 != 63) ? n19 : 64);
                                        n25 = ((j != 63) ? j : 64);
                                    }
                                    else if (~n23 == 0xFFFFFFFE) {
                                        n24 = 64;
                                    }
                                    else if (n23 == 2) {
                                        n25 = 64;
                                    }
                                    int n26;
                                    int n27;
                                    if (~n3 == -1) {
                                        n26 = -n9 + (n24 + n5);
                                        n27 = -n10 + (n25 + n2);
                                    }
                                    else if (n3 == 1) {
                                        n26 = -n10 - (-n25 - n5);
                                        n27 = n9 - n24 + (n2 + 8);
                                    }
                                    else if (~n3 != 0xFFFFFFFD) {
                                        n27 = n2 - n9 - -n24;
                                        n26 = n5 - (-8 + -n10) - n25;
                                    }
                                    else {
                                        n26 = -n24 + (n9 + 8 + n5);
                                        n27 = 8 + n2 + (-n25 - -n10);
                                    }
                                    if (n26 >= 0 && ~n26 > ~this.anInt2543 && ~n27 <= -1 && n27 < this.anInt2542) {
                                        this.anIntArrayArrayArray2549[n8][n26][n27] = this.anIntArrayArrayArray2549[n8][n21 - -n16][n17 + n20];
                                    }
                                }
                            }
                        }
                        else {
                            this.method3581(-1, false, 0, 0, 0, -1, (byte)(-111), 0, 0, 0, class98_Sub22);
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sm.A(" + n + ',' + n2 + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + ((array != null) ? "{...}" : "null") + ',' + n8 + ')');
        }
    }
    
    private final void method3576(final ha ha, final s s, final int[][] array, final s s2, final s s3, final int n, final byte b) {
        try {
            if (b > 118) {
                for (int n2 = 0; ~n2 > ~this.anInt2543; ++n2) {
                    for (int i = 0; i < this.anInt2542; ++i) {
                        if (Class113.anInt950 == -1 || Class294.method3477(i, n, n2, Class113.anInt950, -86)) {
                            int n3 = this.aByteArrayArrayArray2555[n][n2][i];
                            final byte b2 = this.aByteArrayArrayArray2556[n][n2][i];
                            final int n4 = this.aByteArrayArrayArray2540[n][n2][i] & 0xFF;
                            final int n5 = 0xFF & this.aByteArrayArrayArray2548[n][n2][i];
                            Class199 class199 = (~n4 == -1) ? null : this.aClass32_2552.method317(4, n4 - 1);
                            if (n3 == 0 && class199 == null) {
                                n3 = 12;
                            }
                            final Class72 class200 = (n5 == 0) ? null : this.aClass153_2546.method2483(n5 - 1, 125);
                            Class199 class201 = class199;
                            if (class199 != null && ~class199.anInt1537 == 0x0 && class199.anInt1540 == -1) {
                                class201 = class199;
                                class199 = null;
                            }
                            if (class199 != null || class200 != null) {
                                final int n6 = Class98_Sub31_Sub4.anIntArray5861[n3];
                                final int n7 = Class98_Sub46_Sub20_Sub2.anIntArray6318[n3];
                                final int n8 = ((class200 == null) ? 0 : n6) + ((class199 == null) ? 0 : n7);
                                int n9 = 0;
                                int n10 = 0;
                                final int n11 = (class199 != null) ? class199.anInt1542 : -1;
                                final int n12 = (class200 == null) ? -1 : class200.anInt537;
                                final int[] array2 = new int[n8];
                                final int[] array3 = new int[n8];
                                final int[] array4 = new int[n8];
                                final int[] array5 = new int[n8];
                                final int[] array6 = new int[n8];
                                final int[] array7 = new int[n8];
                                final int[] array8 = (int[])((class199 == null || ~class199.anInt1540 == 0x0) ? null : new int[n8]);
                                if (class199 != null) {
                                    for (int n13 = 0; ~n7 < ~n13; ++n13) {
                                        array2[n9] = Class64_Sub28.anIntArrayArray3718[n3][n10];
                                        array3[n9] = Class98_Sub45.anIntArrayArray4258[n3][n10];
                                        array4[n9] = Class93_Sub1.anIntArrayArray5487[n3][n10];
                                        array6[n9] = n11;
                                        array7[n9] = class199.anInt1529;
                                        array5[n9] = class199.anInt1537;
                                        if (array8 != null) {
                                            array8[n9] = class199.anInt1540;
                                        }
                                        ++n9;
                                        ++n10;
                                    }
                                    if (!this.aBoolean2544 && n == 0) {
                                        Class286.method3382(n2, i, class199.anInt1532, class199.anInt1530 * 8, class199.anInt1534);
                                    }
                                }
                                else {
                                    n10 += n7;
                                }
                                if (class200 != null) {
                                    for (int n14 = 0; ~n14 > ~n6; ++n14) {
                                        array2[n9] = Class64_Sub28.anIntArrayArray3718[n3][n10];
                                        array3[n9] = Class98_Sub45.anIntArrayArray4258[n3][n10];
                                        array4[n9] = Class93_Sub1.anIntArrayArray5487[n3][n10];
                                        array6[n9] = n12;
                                        array7[n9] = class200.anInt536;
                                        array5[n9] = array[n2][i];
                                        if (array8 != null) {
                                            array8[n9] = array5[n9];
                                        }
                                        ++n10;
                                        ++n9;
                                    }
                                }
                                final int length = this.anIntArray2551.length;
                                final int[] array9 = new int[length];
                                final int[] array10 = new int[length];
                                final int[] array11 = (int[])((s != null) ? new int[length] : null);
                                final int[] array12 = (int[])((s == null && s2 == null) ? null : new int[length]);
                                for (int n15 = 0; ~n15 > ~length; ++n15) {
                                    final int n16 = this.anIntArray2551[n15];
                                    final int n17 = this.anIntArray2553[n15];
                                    if (~b2 != -1) {
                                        if (b2 != 1) {
                                            if (~b2 == 0xFFFFFFFD) {
                                                array9[n15] = -n16 + 512;
                                                array10[n15] = 512 - n17;
                                            }
                                            else if (~b2 == 0xFFFFFFFC) {
                                                final int n18 = n16;
                                                array9[n15] = 512 - n17;
                                                array10[n15] = n18;
                                            }
                                        }
                                        else {
                                            array9[n15] = n17;
                                            array10[n15] = 512 + -n16;
                                        }
                                    }
                                    else {
                                        array9[n15] = n16;
                                        array10[n15] = n17;
                                    }
                                    if (array11 != null && Class310.aBooleanArrayArray2653[n3][n15]) {
                                        final int n19 = (n2 << -1908633335) - -array9[n15];
                                        final int n20 = (i << -463239607) + array10[n15];
                                        array11[n15] = s.method3417(n19, n20, true) + -s3.method3417(n19, n20, true);
                                    }
                                    if (array12 != null) {
                                        if (s != null && !Class310.aBooleanArrayArray2653[n3][n15]) {
                                            final int n21 = (n2 << -540220791) - -array9[n15];
                                            final int n22 = (i << 2038312265) + array10[n15];
                                            array12[n15] = s3.method3417(n21, n22, true) + -s.method3417(n21, n22, true);
                                        }
                                        else if (s2 != null && !Class278_Sub1.aBooleanArrayArray5171[n3][n15]) {
                                            final int n23 = array9[n15] + (n2 << 1895621833);
                                            final int n24 = (i << -678943447) + array10[n15];
                                            array12[n15] = s2.method3417(n23, n24, true) + -s3.method3417(n23, n24, true);
                                        }
                                    }
                                }
                                final int method3420 = s3.method3420(i, -12639, n2);
                                final int method3421 = s3.method3420(i, -12639, n2 + 1);
                                final int method3422 = s3.method3420(i + 1, -12639, n2 + 1);
                                final int method3423 = s3.method3420(i + 1, -12639, n2);
                                final boolean method3424 = Class1.method162(i, (byte)(-115), n2);
                                if ((method3424 && n > 1) || (!method3424 && ~n < -1)) {
                                    boolean b3 = true;
                                    if (class200 != null && !class200.aBoolean543) {
                                        b3 = false;
                                    }
                                    else if (~n5 == -1 && ~n3 != -1) {
                                        b3 = false;
                                    }
                                    else if (n4 > 0 && class201 != null && !class201.aBoolean1527) {
                                        b3 = false;
                                    }
                                    if (b3 && method3420 == method3421 && method3422 == method3420 && ~method3423 == ~method3420) {
                                        this.aByteArrayArrayArray2550[n][n2][i] = (byte)Class41.method366(this.aByteArrayArrayArray2550[n][n2][i], 4);
                                    }
                                }
                                int method3425 = 0;
                                int method3426 = 0;
                                int method3427 = 0;
                                if (this.aBoolean2544) {
                                    method3425 = Class313.method3636(n2, i);
                                    method3426 = IOException_Sub1.method127(n2, i);
                                    method3427 = Class98_Sub46_Sub20.method1639(n2, i);
                                }
                                s3.method3424(n2, i, array9, array11, array10, array12, array2, array3, array4, array5, array8, array6, array7, method3425, method3426, method3427, false);
                                Class224_Sub2_Sub1.method2839(n, n2, i);
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sm.F(" + ((ha != null) ? "{...}" : "null") + ',' + ((s != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ',' + ((s2 != null) ? "{...}" : "null") + ',' + ((s3 != null) ? "{...}" : "null") + ',' + n + ',' + b + ')');
        }
    }
    
    final void method3577(final int n, final int n2, final int[][] array) {
        try {
            final int[][] array2 = this.anIntArrayArrayArray2549[n];
            for (int i = 0; i < this.anInt2543 + 1; ++i) {
                for (int n3 = 0; 1 + this.anInt2542 > n3; ++n3) {
                    final int[] array3 = array2[i];
                    final int n4 = n3;
                    array3[n4] += array[i][n3];
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sm.J(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method3578(final s s, final ha ha, final s s2, final int[][] array, final s s3, final int n, final int n2) {
        try {
            final byte[][] array2 = this.aByteArrayArrayArray2555[n2];
            final byte[][] array3 = this.aByteArrayArrayArray2556[n2];
            final byte[][] array4 = this.aByteArrayArrayArray2548[n2];
            final byte[][] array5 = this.aByteArrayArrayArray2540[n2];
            for (int i = 0; i < this.anInt2543; ++i) {
                final int n3 = (i >= this.anInt2543 - 1) ? i : (1 + i);
                for (int j = 0; j < this.anInt2542; ++j) {
                    final int n4 = (-1 + this.anInt2542 <= j) ? j : (j + 1);
                    if (~Class113.anInt950 == 0x0 || Class294.method3477(j, n2, i, Class113.anInt950, 65)) {
                        boolean b = false;
                        boolean aBoolean1526 = false;
                        final boolean[] array6 = new boolean[4];
                        int n5 = array2[i][j];
                        int n6 = array3[i][j];
                        final int n7 = 0xFF & array5[i][j];
                        final int n8 = 0xFF & array4[i][j];
                        int n9 = 0xFF & array4[i][n4];
                        int n10 = 0xFF & array4[n3][n4];
                        int n11 = 0xFF & array4[n3][j];
                        if (n7 != 0 || n8 != 0) {
                            Class199 class199 = (~n7 == -1) ? null : this.aClass32_2552.method317(n ^ 0xFFFFA77B, -1 + n7);
                            if (n5 == 0 && class199 == null) {
                                n5 = 12;
                            }
                            final Class72 class200 = (n8 == 0) ? null : this.aClass153_2546.method2483(-1 + n8, 120);
                            Class199 class201;
                            if ((class201 = class199) != null) {
                                if (~class199.anInt1537 != 0x0 || ~class199.anInt1540 != 0x0) {
                                    if (class200 != null && n5 != 0) {
                                        aBoolean1526 = class199.aBoolean1526;
                                    }
                                }
                                else {
                                    class201 = class199;
                                    class199 = null;
                                }
                            }
                            if ((~n5 == -1 || n5 == 12) && ~i < -1 && ~j < -1 && ~this.anInt2543 < ~i && this.anInt2542 > j) {
                                final int n12 = 0;
                                final int n13 = 0;
                                final int n14 = 0;
                                final int n15 = 0;
                                int n16 = n14 + ((~array4[n3][n4] != ~n8) ? -1 : 1);
                                int n17 = n12 + ((~array4[i - 1][-1 + j] != ~n8) ? -1 : 1);
                                int n18 = n13 + ((array4[n3][-1 + j] != n8) ? -1 : 1);
                                int n19 = n15 + ((~array4[i - 1][n4] == ~n8) ? 1 : -1);
                                if (~n8 != ~array4[i][-1 + j]) {
                                    --n17;
                                    --n18;
                                }
                                else {
                                    ++n18;
                                    ++n17;
                                }
                                if (array4[n3][j] != n8) {
                                    --n18;
                                    --n16;
                                }
                                else {
                                    ++n16;
                                    ++n18;
                                }
                                if (n8 != array4[i][n4]) {
                                    --n19;
                                    --n16;
                                }
                                else {
                                    ++n19;
                                    ++n16;
                                }
                                if (array4[-1 + i][j] == n8) {
                                    ++n17;
                                    ++n19;
                                }
                                else {
                                    --n17;
                                    --n19;
                                }
                                int n20 = n17 - n16;
                                if (n20 < 0) {
                                    n20 = -n20;
                                }
                                int n21 = n18 + -n19;
                                if (~n21 > -1) {
                                    n21 = -n21;
                                }
                                if (n21 == n20) {
                                    n20 = s2.method3420(j, -12639, i) - s2.method3420(n4, n + 10018, n3);
                                    n21 = s2.method3420(j, n ^ 0x69DE, n3) + -s2.method3420(n4, n + 10018, i);
                                    if (n20 < 0) {
                                        n20 = -n20;
                                    }
                                    if (~n21 > -1) {
                                        n21 = -n21;
                                    }
                                }
                                n6 = ((n20 < n21) ? 1 : 0);
                            }
                            for (int n22 = 0; ~n22 > -14; ++n22) {
                                Class95.anIntArray800[n22] = -1;
                                Class275.anIntArray2048[n22] = 1;
                            }
                            final boolean[] array7 = (class199 == null || !class199.aBoolean1526) ? Class98_Sub46_Sub12.aBooleanArrayArray6034[n5] : Class161.aBooleanArrayArray1260[n5];
                            this.method3570(i, this.anInt2543, ha, (byte)53, array5, n6, class200, j, n5, array6, array2, array3, class199, this.anInt2542);
                            int n23 = (class199 != null && ~class199.anInt1537 != ~class199.anInt1540) ? 1 : 0;
                            if (n23 == 0) {
                                for (int n24 = 0; ~n24 > -9; ++n24) {
                                    if (Class95.anIntArray800[n24] >= 0 && ~Class277.anIntArray2049[n24] != ~Class98_Sub10_Sub4.anIntArray5547[n24]) {
                                        n23 = 1;
                                        break;
                                    }
                                }
                            }
                            if (!array7[1 - -n6 & 0x3]) {
                                array6[1] = Class98_Sub18.method1163(array6[1], ~Class202.method2702(Class275.anIntArray2048[4], Class275.anIntArray2048[2]) == -1);
                            }
                            if (!array7[0x3 & n6 + 3]) {
                                array6[3] = Class98_Sub18.method1163(array6[3], ~Class202.method2702(Class275.anIntArray2048[0], Class275.anIntArray2048[6]) == -1);
                            }
                            if (!array7[n6 & 0x3]) {
                                array6[0] = Class98_Sub18.method1163(array6[0], Class202.method2702(Class275.anIntArray2048[2], Class275.anIntArray2048[0]) == 0);
                            }
                            if (!array7[n6 + 2 & 0x3]) {
                                array6[2] = Class98_Sub18.method1163(array6[2], Class202.method2702(Class275.anIntArray2048[6], Class275.anIntArray2048[4]) == 0);
                            }
                            if (!aBoolean1526 && (~n5 == -1 || ~n5 == 0xFFFFFFF3)) {
                                if (!array6[0] || array6[1] || array6[2] || !array6[3]) {
                                    if (!array6[0] || !array6[1] || array6[2] || array6[3]) {
                                        if (!array6[0] && array6[1] && array6[2] && !array6[3]) {
                                            array6[1] = (array6[2] = false);
                                            n5 = ((~n5 == -1) ? 13 : 14);
                                            n6 = 2;
                                        }
                                        else if (!array6[0] && !array6[1] && array6[2] && array6[3]) {
                                            array6[2] = (array6[3] = false);
                                            n5 = ((~n5 == -1) ? 13 : 14);
                                            n6 = 1;
                                        }
                                    }
                                    else {
                                        n5 = ((~n5 != -1) ? 14 : 13);
                                        array6[0] = (array6[1] = false);
                                        n6 = 3;
                                    }
                                }
                                else {
                                    n6 = 0;
                                    n5 = ((n5 == 0) ? 13 : 14);
                                    array6[0] = (array6[3] = false);
                                }
                            }
                            final boolean b2 = !aBoolean1526 && !array6[0] && !array6[2] && !array6[1] && !array6[3];
                            int[] array8 = null;
                            int n25;
                            int[] array9;
                            int k;
                            int[] array10;
                            int[] array11;
                            if (!b2) {
                                if (aBoolean1526) {
                                    array8 = Class93.anIntArrayArray3510[n5];
                                    n25 = ((class200 == null) ? 0 : Class253.anIntArray1928[n5]);
                                    array9 = Class346.anIntArrayArray2893[n5];
                                    k = ((class199 != null) ? Class6.anIntArray90[n5] : 0);
                                    array10 = Class246_Sub9.anIntArrayArray5141[n5];
                                    array11 = Class98_Sub32.anIntArrayArray4111[n5];
                                }
                                else {
                                    k = ((class199 != null) ? Class75.anIntArray582[n5] : 0);
                                    array10 = Class98_Sub46_Sub10.anIntArrayArray6012[n5];
                                    array9 = Class21_Sub2.anIntArrayArray5386[n5];
                                    array8 = Class64_Sub1.anIntArrayArray3638[n5];
                                    array11 = Class278_Sub1.anIntArrayArray5169[n5];
                                    n25 = ((class200 != null) ? Class129.anIntArray1027[n5] : 0);
                                }
                            }
                            else {
                                array10 = Class64_Sub28.anIntArrayArray3718[n5];
                                k = ((class199 == null) ? 0 : Class98_Sub46_Sub20_Sub2.anIntArray6318[n5]);
                                array11 = Class93_Sub1.anIntArrayArray5487[n5];
                                n25 = ((class200 == null) ? 0 : Class98_Sub31_Sub4.anIntArray5861[n5]);
                                array9 = Class98_Sub45.anIntArrayArray4258[n5];
                            }
                            int n26 = n25 + k;
                            if (~n26 >= -1) {
                                Class224_Sub2_Sub1.method2839(n2, i, j);
                            }
                            else {
                                if (array6[0]) {
                                    ++n26;
                                }
                                if (array6[2]) {
                                    ++n26;
                                }
                                if (array6[1]) {
                                    ++n26;
                                }
                                if (array6[3]) {
                                    ++n26;
                                }
                                int n27 = 0;
                                int n28 = 0;
                                final int n29 = 3 * n26;
                                final int[] array12 = (int[])((n23 == 0) ? null : new int[n29]);
                                final int[] array13 = new int[n29];
                                final int[] array14 = new int[n29];
                                final int[] array15 = new int[n29];
                                final int[] array16 = new int[n29];
                                final int[] array17 = new int[n29];
                                final int[] array18 = (int[])((s3 != null) ? new int[n29] : null);
                                final int[] array19 = (int[])((s3 == null && s == null) ? null : new int[n29]);
                                int anInt1537 = -1;
                                int anInt1538 = -1;
                                int anInt1539 = 256;
                                if (class199 != null) {
                                    anInt1537 = class199.anInt1537;
                                    anInt1539 = class199.anInt1529;
                                    anInt1538 = class199.anInt1542;
                                    final int method1149 = Class98_Sub16.method1149(false, class199, ha);
                                    for (int n30 = 0; k > n30; ++n30) {
                                        int n31;
                                        if (!array6[-n6 & 0x3] || ~array8[0] != ~n27) {
                                            if (!array6[2 + -n6 & 0x3] || ~array8[2] != ~n27) {
                                                if (!array6[-n6 + 1 & 0x3] || ~n27 != ~array8[1]) {
                                                    if (array6[0x3 & 3 - n6] && ~n27 == ~array8[3]) {
                                                        Class337_Sub1.anIntArray5500[0] = array10[n27];
                                                        Class337_Sub1.anIntArray5500[1] = 7;
                                                        Class337_Sub1.anIntArray5500[2] = array11[n27];
                                                        Class337_Sub1.anIntArray5500[3] = 7;
                                                        Class337_Sub1.anIntArray5500[4] = array9[n27];
                                                        Class337_Sub1.anIntArray5500[5] = array11[n27];
                                                        n31 = 6;
                                                    }
                                                    else {
                                                        Class337_Sub1.anIntArray5500[0] = array10[n27];
                                                        Class337_Sub1.anIntArray5500[1] = array9[n27];
                                                        n31 = 3;
                                                        Class337_Sub1.anIntArray5500[2] = array11[n27];
                                                    }
                                                }
                                                else {
                                                    Class337_Sub1.anIntArray5500[0] = array10[n27];
                                                    Class337_Sub1.anIntArray5500[1] = 3;
                                                    Class337_Sub1.anIntArray5500[2] = array11[n27];
                                                    Class337_Sub1.anIntArray5500[3] = 3;
                                                    Class337_Sub1.anIntArray5500[4] = array9[n27];
                                                    Class337_Sub1.anIntArray5500[5] = array11[n27];
                                                    n31 = 6;
                                                }
                                            }
                                            else {
                                                Class337_Sub1.anIntArray5500[0] = array10[n27];
                                                Class337_Sub1.anIntArray5500[1] = 5;
                                                Class337_Sub1.anIntArray5500[2] = array11[n27];
                                                Class337_Sub1.anIntArray5500[3] = 5;
                                                Class337_Sub1.anIntArray5500[4] = array9[n27];
                                                Class337_Sub1.anIntArray5500[5] = array11[n27];
                                                n31 = 6;
                                            }
                                        }
                                        else {
                                            Class337_Sub1.anIntArray5500[0] = array10[n27];
                                            Class337_Sub1.anIntArray5500[1] = 1;
                                            Class337_Sub1.anIntArray5500[2] = array11[n27];
                                            Class337_Sub1.anIntArray5500[3] = 1;
                                            Class337_Sub1.anIntArray5500[4] = array9[n27];
                                            Class337_Sub1.anIntArray5500[5] = array11[n27];
                                            n31 = 6;
                                        }
                                        ++n27;
                                        for (int l = 0; l < n31; ++l) {
                                            final int n32 = Class337_Sub1.anIntArray5500[l];
                                            final int n33 = 0x7 & -(2 * n6) + n32;
                                            final int n34 = this.anIntArray2551[n32];
                                            final int n35 = this.anIntArray2553[n32];
                                            int n36;
                                            int n37;
                                            if (n6 == 1) {
                                                n36 = n35;
                                                n37 = -n34 + 512;
                                            }
                                            else if (~n6 == 0xFFFFFFFD) {
                                                n36 = -n34 + 512;
                                                n37 = 512 + -n35;
                                            }
                                            else if (~n6 != 0xFFFFFFFC) {
                                                n37 = n35;
                                                n36 = n34;
                                            }
                                            else {
                                                n36 = -n35 + 512;
                                                n37 = n34;
                                            }
                                            array13[n28] = n36;
                                            array14[n28] = n37;
                                            if (array18 != null && Class310.aBooleanArrayArray2653[n5][n32]) {
                                                final int n38 = n36 + (i << -139862679);
                                                final int n39 = n37 + (j << -735630231);
                                                array18[n28] = s3.method3417(n38, n39, true) - s2.method3417(n38, n39, true);
                                            }
                                            if (array19 != null) {
                                                if (s3 == null || Class310.aBooleanArrayArray2653[n5][n32]) {
                                                    if (s != null && !Class278_Sub1.aBooleanArrayArray5171[n5][n32]) {
                                                        final int n40 = n36 + (i << -1191204759);
                                                        final int n41 = n37 + (j << -858207319);
                                                        array19[n28] = s.method3417(n40, n41, true) - s2.method3417(n40, n41, true);
                                                    }
                                                }
                                                else {
                                                    final int n42 = (i << -1129452151) + n36;
                                                    final int n43 = (j << 1770605321) + n37;
                                                    array19[n28] = s2.method3417(n42, n43, true) + -s3.method3417(n42, n43, true);
                                                }
                                            }
                                            if (~n32 > -9 && Class95.anIntArray800[n33] > class199.anInt1535) {
                                                if (array12 != null) {
                                                    array12[n28] = Class277.anIntArray2049[n33];
                                                }
                                                array17[n28] = Class156_Sub1.anIntArray3279[n33];
                                                array16[n28] = Class135.anIntArray1056[n33];
                                                array15[n28] = Class98_Sub10_Sub4.anIntArray5547[n33];
                                            }
                                            else {
                                                if (array12 != null) {
                                                    array12[n28] = method1149;
                                                }
                                                array16[n28] = class199.anInt1542;
                                                array17[n28] = class199.anInt1529;
                                                array15[n28] = anInt1537;
                                            }
                                            ++n28;
                                        }
                                    }
                                    if (!this.aBoolean2544 && n2 == 0) {
                                        Class286.method3382(i, j, class199.anInt1532, class199.anInt1530 * 8, class199.anInt1534);
                                    }
                                    if (n5 != 12 && ~class199.anInt1537 != 0x0 && class199.aBoolean1538) {
                                        b = true;
                                    }
                                }
                                else if (b2) {
                                    n27 += Class98_Sub46_Sub20_Sub2.anIntArray6318[n5];
                                }
                                else if (!aBoolean1526) {
                                    n27 += Class75.anIntArray582[n5];
                                }
                                else {
                                    n27 += Class6.anIntArray90[n5];
                                }
                                if (class200 != null) {
                                    if (~n10 == -1) {
                                        n10 = n8;
                                    }
                                    if (n11 == 0) {
                                        n11 = n8;
                                    }
                                    if (n9 == 0) {
                                        n9 = n8;
                                    }
                                    final Class72 method1150 = this.aClass153_2546.method2483(-1 + n8, 126);
                                    final Class72 method1151 = this.aClass153_2546.method2483(-1 + n9, 122);
                                    final Class72 method1152 = this.aClass153_2546.method2483(n10 - 1, n ^ 0xFFFFA704);
                                    final Class72 method1153 = this.aClass153_2546.method2483(n11 - 1, n + 22775);
                                    for (int n44 = 0; ~n44 > ~n25; ++n44) {
                                        int n45;
                                        if (!array6[0x3 & -n6] || array8[0] != n27) {
                                            if (!array6[-n6 + 2 & 0x3] || ~n27 != ~array8[2]) {
                                                if (!array6[1 - n6 & 0x3] || ~n27 != ~array8[1]) {
                                                    if (!array6[0x3 & 3 - n6] || n27 != array8[3]) {
                                                        Class337_Sub1.anIntArray5500[0] = array10[n27];
                                                        Class337_Sub1.anIntArray5500[1] = array9[n27];
                                                        n45 = 3;
                                                        Class337_Sub1.anIntArray5500[2] = array11[n27];
                                                    }
                                                    else {
                                                        Class337_Sub1.anIntArray5500[0] = array10[n27];
                                                        Class337_Sub1.anIntArray5500[1] = 7;
                                                        Class337_Sub1.anIntArray5500[2] = array11[n27];
                                                        Class337_Sub1.anIntArray5500[3] = 7;
                                                        Class337_Sub1.anIntArray5500[4] = array9[n27];
                                                        n45 = 6;
                                                        Class337_Sub1.anIntArray5500[5] = array11[n27];
                                                    }
                                                }
                                                else {
                                                    Class337_Sub1.anIntArray5500[0] = array10[n27];
                                                    Class337_Sub1.anIntArray5500[1] = 3;
                                                    Class337_Sub1.anIntArray5500[2] = array11[n27];
                                                    Class337_Sub1.anIntArray5500[3] = 3;
                                                    Class337_Sub1.anIntArray5500[4] = array9[n27];
                                                    Class337_Sub1.anIntArray5500[5] = array11[n27];
                                                    n45 = 6;
                                                }
                                            }
                                            else {
                                                Class337_Sub1.anIntArray5500[0] = array10[n27];
                                                Class337_Sub1.anIntArray5500[1] = 5;
                                                Class337_Sub1.anIntArray5500[2] = array11[n27];
                                                Class337_Sub1.anIntArray5500[3] = 5;
                                                Class337_Sub1.anIntArray5500[4] = array9[n27];
                                                n45 = 6;
                                                Class337_Sub1.anIntArray5500[5] = array11[n27];
                                            }
                                        }
                                        else {
                                            Class337_Sub1.anIntArray5500[0] = array10[n27];
                                            Class337_Sub1.anIntArray5500[1] = 1;
                                            Class337_Sub1.anIntArray5500[2] = array11[n27];
                                            Class337_Sub1.anIntArray5500[3] = 1;
                                            Class337_Sub1.anIntArray5500[4] = array9[n27];
                                            n45 = 6;
                                            Class337_Sub1.anIntArray5500[5] = array11[n27];
                                        }
                                        ++n27;
                                        for (int n46 = 0; ~n45 < ~n46; ++n46) {
                                            final int n47 = Class337_Sub1.anIntArray5500[n46];
                                            final int n48 = n47 + -(2 * n6) & 0x7;
                                            final int n49 = this.anIntArray2551[n47];
                                            final int n50 = this.anIntArray2553[n47];
                                            int n51;
                                            int n52;
                                            if (n6 != 1) {
                                                if (~n6 != 0xFFFFFFFD) {
                                                    if (n6 != 3) {
                                                        n51 = n50;
                                                        n52 = n49;
                                                    }
                                                    else {
                                                        n51 = n49;
                                                        n52 = -n50 + 512;
                                                    }
                                                }
                                                else {
                                                    n51 = 512 - n50;
                                                    n52 = -n49 + 512;
                                                }
                                            }
                                            else {
                                                n51 = -n49 + 512;
                                                n52 = n50;
                                            }
                                            array13[n28] = n52;
                                            array14[n28] = n51;
                                            if (array18 != null && Class310.aBooleanArrayArray2653[n5][n47]) {
                                                final int n53 = n52 + (i << 1261077129);
                                                final int n54 = (j << 1232038217) - -n51;
                                                array18[n28] = s3.method3417(n53, n54, true) - s2.method3417(n53, n54, true);
                                            }
                                            if (array19 != null) {
                                                if (s3 != null && !Class310.aBooleanArrayArray2653[n5][n47]) {
                                                    final int n55 = (i << 226787497) - -n52;
                                                    final int n56 = n51 + (j << -1842618583);
                                                    array19[n28] = s2.method3417(n55, n56, true) - s3.method3417(n55, n56, true);
                                                }
                                                else if (s != null && !Class278_Sub1.aBooleanArrayArray5171[n5][n47]) {
                                                    final int n57 = n52 + (i << -712659863);
                                                    final int n58 = n51 + (j << -1203749399);
                                                    array19[n28] = s.method3417(n57, n58, true) - s2.method3417(n57, n58, true);
                                                }
                                            }
                                            if (~n47 > -9 && ~Class95.anIntArray800[n48] <= -1) {
                                                if (array12 != null) {
                                                    array12[n28] = Class277.anIntArray2049[n48];
                                                }
                                                array17[n28] = Class156_Sub1.anIntArray3279[n48];
                                                array16[n28] = Class135.anIntArray1056[n48];
                                                array15[n28] = Class98_Sub10_Sub4.anIntArray5547[n48];
                                            }
                                            else {
                                                if (!aBoolean1526 || !Class310.aBooleanArrayArray2653[n5][n47]) {
                                                    if (~n52 == -1 && ~n51 == -1) {
                                                        array15[n28] = array[i][j];
                                                        array16[n28] = method1150.anInt537;
                                                        array17[n28] = method1150.anInt536;
                                                    }
                                                    else if (~n52 == -1 && n51 == 512) {
                                                        array15[n28] = array[i][n4];
                                                        array16[n28] = method1151.anInt537;
                                                        array17[n28] = method1151.anInt536;
                                                    }
                                                    else if (~n52 == 0xFFFFFDFF && n51 == 512) {
                                                        array15[n28] = array[n3][n4];
                                                        array16[n28] = method1152.anInt537;
                                                        array17[n28] = method1152.anInt536;
                                                    }
                                                    else if (~n52 == 0xFFFFFDFF && n51 == 0) {
                                                        array15[n28] = array[n3][j];
                                                        array16[n28] = method1153.anInt537;
                                                        array17[n28] = method1153.anInt536;
                                                    }
                                                    else {
                                                        if (~n52 <= -257) {
                                                            if (n51 < 256) {
                                                                array16[n28] = method1153.anInt537;
                                                                array17[n28] = method1153.anInt536;
                                                            }
                                                            else {
                                                                array16[n28] = method1152.anInt537;
                                                                array17[n28] = method1152.anInt536;
                                                            }
                                                        }
                                                        else if (~n51 > -257) {
                                                            array16[n28] = method1150.anInt537;
                                                            array17[n28] = method1150.anInt536;
                                                        }
                                                        else {
                                                            array16[n28] = method1151.anInt537;
                                                            array17[n28] = method1151.anInt536;
                                                        }
                                                        array15[n28] = Class98_Sub46_Sub20.method1637(n51 << -1396466297 >> -1215779831, -51, Class98_Sub46_Sub20.method1637(n52 << -1870738201 >> 952160265, n + 22533, array[i][j], array[n3][j]), Class98_Sub46_Sub20.method1637(n52 << -1405224473 >> -654341815, -84, array[i][n4], array[n3][n4]));
                                                    }
                                                }
                                                else {
                                                    array16[n28] = anInt1538;
                                                    array17[n28] = anInt1539;
                                                    array15[n28] = anInt1537;
                                                }
                                                if (array12 != null) {
                                                    array12[n28] = array15[n28];
                                                }
                                            }
                                            ++n28;
                                        }
                                    }
                                    if (n5 != 0 && class200.aBoolean544) {
                                        b = true;
                                    }
                                }
                                final int method1154 = s2.method3420(j, n ^ 0x69DE, i);
                                final int method1155 = s2.method3420(j, -12639, n3);
                                final int method1156 = s2.method3420(n4, -12639, n3);
                                final int method1157 = s2.method3420(n4, -12639, i);
                                final boolean method1158 = Class1.method162(j, (byte)(-92), i);
                                if ((method1158 && ~n2 < -2) || (!method1158 && ~n2 < -1)) {
                                    boolean b3 = true;
                                    if (class200 != null && !class200.aBoolean543) {
                                        b3 = false;
                                    }
                                    else if (~n8 == -1 && n5 != 0) {
                                        b3 = false;
                                    }
                                    else if (~n7 < -1 && class201 != null && !class201.aBoolean1527) {
                                        b3 = false;
                                    }
                                    if (b3 && ~method1155 == ~method1154 && method1154 == method1156 && ~method1154 == ~method1157) {
                                        this.aByteArrayArrayArray2550[n2][i][j] = (byte)Class41.method366(this.aByteArrayArrayArray2550[n2][i][j], 4);
                                    }
                                }
                                int method1159 = 0;
                                int method1160 = 0;
                                int method1161 = 0;
                                if (this.aBoolean2544) {
                                    method1159 = Class313.method3636(i, j);
                                    method1160 = IOException_Sub1.method127(i, j);
                                    method1161 = Class98_Sub46_Sub20.method1639(i, j);
                                }
                                s2.U(i, j, array13, array18, array14, array19, array15, array12, array16, array17, method1159, method1160, method1161, b);
                                Class224_Sub2_Sub1.method2839(n2, i, j);
                            }
                        }
                    }
                }
            }
            if (n != -22657) {
                this.aByteArrayArrayArray2554 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sm.K(" + ((s != null) ? "{...}" : "null") + ',' + ((ha != null) ? "{...}" : "null") + ',' + ((s2 != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ',' + ((s3 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    final void method3579(final int n, final Class243[] array, final ha ha, final int[][][] array2) {
        try {
            if (!this.aBoolean2544) {
                for (int i = 0; i < 4; ++i) {
                    for (int j = 0; j < this.anInt2543; ++j) {
                        for (int n2 = 0; ~n2 > ~this.anInt2542; ++n2) {
                            if (~(Class281.aByteArrayArrayArray2117[i][j][n2] & 0x1) != -1) {
                                int n3 = i;
                                if ((0x2 & Class281.aByteArrayArrayArray2117[1][j][n2]) != 0x0) {
                                    --n3;
                                }
                                if (~n3 <= -1) {
                                    array[n3].method2941(n2, 23254, j);
                                }
                            }
                        }
                    }
                }
            }
            for (int n4 = n; ~this.anInt2547 < ~n4; ++n4) {
                int n5 = 0;
                int n6 = 0;
                if (!this.aBoolean2544) {
                    if (Class98_Sub46_Sub9.aBoolean6002) {
                        n6 |= 0x8;
                    }
                    if (Class97.aBoolean830) {
                        n5 |= 0x2;
                    }
                    if (~Class61.anInt479 != -1) {
                        n5 |= 0x1;
                        if (n4 == 0 | Class202.aBoolean1548) {
                            n6 |= 0x10;
                        }
                    }
                }
                if (Class97.aBoolean830) {
                    n6 |= 0x7;
                }
                if (!Class369.aBoolean3130) {
                    n6 |= 0x20;
                }
                Class138.method2278(n4, ha.a(this.anInt2543, this.anInt2542, this.anIntArrayArrayArray2549[n4], (array2 != null && array2.length > n4) ? array2[n4] : this.anIntArrayArrayArray2549[n4], 512, n5, n6));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sm.G(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + ((ha != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final int method3580(final String s, final boolean b) {
        try {
            final int i = s.length();
            int n = 0;
            for (int n2 = 0; i > n2; ++n2) {
                n = (n << 1982319173) - (n - Class349.method3843((byte)88, s.charAt(n2)));
            }
            if (b) {
                method3572(-128, null);
            }
            return n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sm.M(" + ((s != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    private final void method3581(final int n, final boolean b, final int n2, final int n3, final int n4, final int n5, final byte b2, int n6, int n7, final int n8, final Class98_Sub22 class98_Sub22) {
        try {
            if (b2 != -111) {
                this.method3575(88, -38, null, 117, -34, -46, -20, -114, null, -78);
            }
            if (n2 != 1) {
                if (n2 != 2) {
                    if (n2 == 3) {
                        n7 = 1;
                    }
                }
                else {
                    n6 = 1;
                    n7 = 1;
                }
            }
            else {
                n6 = 1;
            }
            if (~n5 <= -1 && ~this.anInt2543 < ~n5 && ~n <= -1 && this.anInt2542 > n) {
                if (!this.aBoolean2544 && !b) {
                    Class281.aByteArrayArrayArray2117[n8][n5][n] = 0;
                }
                while (true) {
                    final int unsignedByte = class98_Sub22.readUnsignedByte((byte)3);
                    if (~unsignedByte == -1) {
                        if (this.aBoolean2544) {
                            this.anIntArrayArrayArray2549[0][n5 - -n7][n6 + n] = 0;
                            break;
                        }
                        if (~n8 != -1) {
                            this.anIntArrayArrayArray2549[n8][n7 + n5][n - -n6] = -960 + this.anIntArrayArrayArray2549[n8 - 1][n7 + n5][n6 + n];
                            break;
                        }
                        this.anIntArrayArrayArray2549[0][n7 + n5][n6 + n] = -Class246_Sub3_Sub4_Sub4.method3082(n4 + 556238, b2 + 115, n3 + 932731) * 8 << -48538654;
                        break;
                    }
                    else if (~unsignedByte == 0xFFFFFFFE) {
                        int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)(-114));
                        if (this.aBoolean2544) {
                            this.anIntArrayArrayArray2549[0][n7 + n5][n6 + n] = 8 * unsignedByte2 << 2030578370;
                            break;
                        }
                        if (unsignedByte2 == 1) {
                            unsignedByte2 = 0;
                        }
                        if (n8 != 0) {
                            this.anIntArrayArrayArray2549[n8][n5 - -n7][n - -n6] = -(8 * unsignedByte2 << 1144983522) + this.anIntArrayArrayArray2549[-1 + n8][n5 + n7][n6 + n];
                            break;
                        }
                        this.anIntArrayArrayArray2549[0][n5 + n7][n + n6] = -unsignedByte2 * 8 << -478503326;
                        break;
                    }
                    else if (unsignedByte <= 49) {
                        if (b) {
                            class98_Sub22.readUnsignedByte((byte)(-105));
                        }
                        else {
                            this.aByteArrayArrayArray2540[n8][n5][n] = class98_Sub22.readSignedByte((byte)(-19));
                            this.aByteArrayArrayArray2555[n8][n5][n] = (byte)((unsignedByte - 2) / 4);
                            this.aByteArrayArrayArray2556[n8][n5][n] = (byte)Class202.method2702(3, n2 - 2 + unsignedByte);
                        }
                    }
                    else if (~unsignedByte >= -82) {
                        if (this.aBoolean2544 || b) {
                            continue;
                        }
                        Class281.aByteArrayArrayArray2117[n8][n5][n] = (byte)(-49 + unsignedByte);
                    }
                    else {
                        if (b) {
                            continue;
                        }
                        this.aByteArrayArrayArray2548[n8][n5][n] = (byte)(unsignedByte - 81);
                    }
                }
            }
            else {
                while (true) {
                    final int unsignedByte3 = class98_Sub22.readUnsignedByte((byte)76);
                    if (~unsignedByte3 == -1) {
                        break;
                    }
                    if (unsignedByte3 == 1) {
                        class98_Sub22.readUnsignedByte((byte)122);
                        break;
                    }
                    if (unsignedByte3 > 49) {
                        continue;
                    }
                    class98_Sub22.readUnsignedByte((byte)(-124));
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sm.I(" + n + ',' + b + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + b2 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class305(final int anInt2547, final int anInt2548, final int anInt2549, final boolean aBoolean2544, final Class32 aClass32_2552, final Class153 aClass153_2546) {
        this.anIntArray2553 = new int[] { 0, 0, 0, 256, 512, 512, 512, 256, 256, 384, 128, 128, 256 };
        this.anIntArray2551 = new int[] { 0, 256, 512, 512, 512, 256, 0, 0, 128, 256, 128, 384, 256 };
        try {
            this.anInt2542 = anInt2549;
            this.aClass32_2552 = aClass32_2552;
            this.anInt2547 = anInt2547;
            this.aBoolean2544 = aBoolean2544;
            this.anInt2543 = anInt2548;
            this.aClass153_2546 = aClass153_2546;
            this.anIntArrayArrayArray2549 = new int[this.anInt2547][1 + this.anInt2543][this.anInt2542 + 1];
            this.aByteArrayArrayArray2555 = new byte[this.anInt2547][this.anInt2543][this.anInt2542];
            this.aByteArrayArrayArray2550 = new byte[this.anInt2547][1 + this.anInt2543][this.anInt2542 + 1];
            this.aByteArrayArrayArray2540 = new byte[this.anInt2547][this.anInt2543][this.anInt2542];
            this.aByteArrayArrayArray2548 = new byte[this.anInt2547][this.anInt2543][this.anInt2542];
            this.aByteArrayArrayArray2556 = new byte[this.anInt2547][this.anInt2543][this.anInt2542];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sm.<init>(" + anInt2547 + ',' + anInt2548 + ',' + anInt2549 + ',' + aBoolean2544 + ',' + ((aClass32_2552 != null) ? "{...}" : "null") + ',' + ((aClass153_2546 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class305.aFloat2545 = 0.0f;
        Class305.anIntArray2541 = new int[8];
    }
}
