// 
// Decompiled by Procyon v0.5.30
// 

final class Class243
{
    static int anInt1852;
    int[][] anIntArrayArray1853;
    int anInt1854;
    int anInt1855;
    int anInt1856;
    int anInt1857;
    
    final boolean method2936(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        try {
            if (n4 != -1) {
                this.method2949(33, false, 107, -45, 14, true, -5);
            }
            final int n11 = n7 + n8;
            final int n12 = n5 - -n9;
            final int n13 = n6 + n;
            final int n14 = n3 - -n2;
            if (n13 != n8 || (n10 & 0x2) != 0x0) {
                if (n11 != n || ~(n10 & 0x8) != -1) {
                    if (n5 == n14 && ~(0x1 & n10) == -1) {
                        for (int i = (~n8 < ~n) ? n8 : n; i < ((n13 > n11) ? n11 : n13); ++i) {
                            if ((this.anIntArrayArray1853[-this.anInt1854 + i][-this.anInt1855 + n14 - 1] & 0x2) == 0x0) {
                                return true;
                            }
                        }
                    }
                    else if (~n3 == ~n12 && ~(0x4 & n10) == -1) {
                        for (int n15 = (~n <= ~n8) ? n : n8; ~n15 > ~((~n13 >= ~n11) ? n13 : n11); ++n15) {
                            if (~(0x20 & this.anIntArrayArray1853[n15 + -this.anInt1854][-this.anInt1855 + n3]) == -1) {
                                return true;
                            }
                        }
                    }
                }
                else {
                    for (int n16 = (n5 <= n3) ? n3 : n5; ~((n12 < n14) ? n12 : n14) < ~n16; ++n16) {
                        if (~(0x80 & this.anIntArrayArray1853[n + -this.anInt1854][n16 + -this.anInt1855]) == -1) {
                            return true;
                        }
                    }
                }
            }
            else {
                for (int j = (n5 > n3) ? n5 : n3; j < ((~n14 < ~n12) ? n12 : n14); ++j) {
                    if (~(0x8 & this.anIntArrayArray1853[-this.anInt1854 - 1 + n13][j + -this.anInt1855]) == -1) {
                        return true;
                    }
                }
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pfa.C(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ',' + n10 + ')');
        }
    }
    
    final void method2937(final int n, final boolean b, final byte b2, final boolean b3, int n2, final int n3, int n4) {
        try {
            n4 -= this.anInt1855;
            n2 -= this.anInt1854;
            if (n == 0) {
                if (~n3 == -1) {
                    this.method2940(n2, n4, 128, 33);
                    this.method2940(-1 + n2, n4, 8, 91);
                }
                if (~n3 == 0xFFFFFFFE) {
                    this.method2940(n2, n4, 2, 52);
                    this.method2940(n2, 1 + n4, 32, 60);
                }
                if (~n3 == 0xFFFFFFFD) {
                    this.method2940(n2, n4, 8, 104);
                    this.method2940(n2 + 1, n4, 128, 39);
                }
                if (n3 == 3) {
                    this.method2940(n2, n4, 32, 83);
                    this.method2940(n2, -1 + n4, 2, 126);
                }
            }
            if (n == 1 || n == 3) {
                if (n3 == 0) {
                    this.method2940(n2, n4, 1, 84);
                    this.method2940(n2 - 1, 1 + n4, 16, 80);
                }
                if (~n3 == 0xFFFFFFFE) {
                    this.method2940(n2, n4, 4, 118);
                    this.method2940(1 + n2, 1 + n4, 64, 75);
                }
                if (~n3 == 0xFFFFFFFD) {
                    this.method2940(n2, n4, 16, 60);
                    this.method2940(n2 + 1, -1 + n4, 1, 125);
                }
                if (~n3 == 0xFFFFFFFC) {
                    this.method2940(n2, n4, 64, 86);
                    this.method2940(n2 - 1, n4 - 1, 4, 67);
                }
            }
            if (~n == 0xFFFFFFFD) {
                if (n3 == 0) {
                    this.method2940(n2, n4, 130, 109);
                    this.method2940(n2 - 1, n4, 8, 105);
                    this.method2940(n2, n4 + 1, 32, 83);
                }
                if (~n3 == 0xFFFFFFFE) {
                    this.method2940(n2, n4, 10, 42);
                    this.method2940(n2, n4 + 1, 32, 112);
                    this.method2940(1 + n2, n4, 128, 34);
                }
                if (n3 == 2) {
                    this.method2940(n2, n4, 40, 78);
                    this.method2940(n2 + 1, n4, 128, 99);
                    this.method2940(n2, -1 + n4, 2, 30);
                }
                if (~n3 == 0xFFFFFFFC) {
                    this.method2940(n2, n4, 160, 66);
                    this.method2940(n2, -1 + n4, 2, 30);
                    this.method2940(-1 + n2, n4, 8, 86);
                }
            }
            if (b) {
                if (n == 0) {
                    if (n3 == 0) {
                        this.method2940(n2, n4, 65536, 76);
                        this.method2940(-1 + n2, n4, 4096, 41);
                    }
                    if (~n3 == 0xFFFFFFFE) {
                        this.method2940(n2, n4, 1024, 94);
                        this.method2940(n2, n4 + 1, 16384, 29);
                    }
                    if (n3 == 2) {
                        this.method2940(n2, n4, 4096, 50);
                        this.method2940(1 + n2, n4, 65536, 118);
                    }
                    if (~n3 == 0xFFFFFFFC) {
                        this.method2940(n2, n4, 16384, 41);
                        this.method2940(n2, -1 + n4, 1024, 69);
                    }
                }
                if (~n == 0xFFFFFFFE || n == 3) {
                    if (~n3 == -1) {
                        this.method2940(n2, n4, 512, 34);
                        this.method2940(n2 - 1, n4 + 1, 8192, 98);
                    }
                    if (~n3 == 0xFFFFFFFE) {
                        this.method2940(n2, n4, 2048, 76);
                        this.method2940(n2 + 1, n4 + 1, 32768, 60);
                    }
                    if (~n3 == 0xFFFFFFFD) {
                        this.method2940(n2, n4, 8192, 100);
                        this.method2940(1 + n2, -1 + n4, 512, 31);
                    }
                    if (~n3 == 0xFFFFFFFC) {
                        this.method2940(n2, n4, 32768, 92);
                        this.method2940(n2 - 1, -1 + n4, 2048, 111);
                    }
                }
                if (n == 2) {
                    if (n3 == 0) {
                        this.method2940(n2, n4, 66560, 88);
                        this.method2940(-1 + n2, n4, 4096, 99);
                        this.method2940(n2, 1 + n4, 16384, 101);
                    }
                    if (~n3 == 0xFFFFFFFE) {
                        this.method2940(n2, n4, 5120, 92);
                        this.method2940(n2, n4 + 1, 16384, 54);
                        this.method2940(n2 + 1, n4, 65536, 113);
                    }
                    if (n3 == 2) {
                        this.method2940(n2, n4, 20480, 93);
                        this.method2940(1 + n2, n4, 65536, 115);
                        this.method2940(n2, -1 + n4, 1024, 34);
                    }
                    if (~n3 == 0xFFFFFFFC) {
                        this.method2940(n2, n4, 81920, 37);
                        this.method2940(n2, -1 + n4, 1024, 112);
                        this.method2940(n2 - 1, n4, 4096, 34);
                    }
                }
            }
            if (b3) {
                if (~n == -1) {
                    if (n3 == 0) {
                        this.method2940(n2, n4, 536870912, 71);
                        this.method2940(-1 + n2, n4, 33554432, 86);
                    }
                    if (~n3 == 0xFFFFFFFE) {
                        this.method2940(n2, n4, 8388608, 99);
                        this.method2940(n2, 1 + n4, 134217728, 89);
                    }
                    if (n3 == 2) {
                        this.method2940(n2, n4, 33554432, 91);
                        this.method2940(1 + n2, n4, 536870912, 55);
                    }
                    if (~n3 == 0xFFFFFFFC) {
                        this.method2940(n2, n4, 134217728, 82);
                        this.method2940(n2, -1 + n4, 8388608, 117);
                    }
                }
                if (~n == 0xFFFFFFFE || n == 3) {
                    if (n3 == 0) {
                        this.method2940(n2, n4, 4194304, 78);
                        this.method2940(n2 - 1, 1 + n4, 67108864, 106);
                    }
                    if (~n3 == 0xFFFFFFFE) {
                        this.method2940(n2, n4, 16777216, 39);
                        this.method2940(n2 + 1, n4 + 1, 268435456, 34);
                    }
                    if (n3 == 2) {
                        this.method2940(n2, n4, 67108864, 31);
                        this.method2940(n2 + 1, n4 - 1, 4194304, 65);
                    }
                    if (n3 == 3) {
                        this.method2940(n2, n4, 268435456, 79);
                        this.method2940(-1 + n2, -1 + n4, 16777216, 58);
                    }
                }
                if (~n == 0xFFFFFFFD) {
                    if (n3 == 0) {
                        this.method2940(n2, n4, 545259520, 79);
                        this.method2940(n2 - 1, n4, 33554432, 51);
                        this.method2940(n2, n4 + 1, 134217728, 53);
                    }
                    if (n3 == 1) {
                        this.method2940(n2, n4, 41943040, 83);
                        this.method2940(n2, n4 + 1, 134217728, 42);
                        this.method2940(1 + n2, n4, 536870912, 39);
                    }
                    if (~n3 == 0xFFFFFFFD) {
                        this.method2940(n2, n4, 167772160, 105);
                        this.method2940(n2 + 1, n4, 536870912, 118);
                        this.method2940(n2, -1 + n4, 8388608, 60);
                    }
                    if (n3 == 3) {
                        this.method2940(n2, n4, 671088640, 77);
                        this.method2940(n2, -1 + n4, 8388608, 106);
                        this.method2940(n2 - 1, n4, 33554432, 111);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pfa.O(" + n + ',' + b + ',' + b2 + ',' + b3 + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    final boolean method2938(int n, int n2, int n3, int n4, final int n5, final int n6, final int n7, int n8) {
        try {
            if (n7 != 1) {
                if (n >= n8 && n <= n8 - -n7 - 1 && n4 <= n4 && ~n4 >= ~(n7 + n4 - 1)) {
                    return true;
                }
            }
            else if (n8 == n && ~n3 == ~n4) {
                return true;
            }
            n8 -= this.anInt1854;
            n3 -= this.anInt1855;
            n4 -= this.anInt1855;
            if (n6 != 17761) {
                this.method2946(39, (byte)(-56), -52);
            }
            n -= this.anInt1854;
            if (n7 == 1) {
                if (~n5 == 0xFFFFFFF9 || n5 == 7) {
                    if (~n5 == 0xFFFFFFF8) {
                        n2 = (2 + n2 & 0x3);
                    }
                    if (n2 != 0) {
                        if (~n2 == 0xFFFFFFFE) {
                            if (-1 + n == n8 && n3 == n4 && (this.anIntArrayArray1853[n8][n3] & 0x8) == 0x0) {
                                return true;
                            }
                            if (n == n8 && ~(n4 - 1) == ~n3 && (0x2 & this.anIntArrayArray1853[n8][n3]) == 0x0) {
                                return true;
                            }
                        }
                        else if (~n2 == 0xFFFFFFFD) {
                            if (n8 == -1 + n && ~n3 == ~n4 && (0x8 & this.anIntArrayArray1853[n8][n3]) == 0x0) {
                                return true;
                            }
                            if (n8 == n && ~n3 == ~(1 + n4) && ~(0x20 & this.anIntArrayArray1853[n8][n3]) == -1) {
                                return true;
                            }
                        }
                        else if (n2 == 3) {
                            if (~n8 == ~(n + 1) && n4 == n3 && ~(0x80 & this.anIntArrayArray1853[n8][n3]) == -1) {
                                return true;
                            }
                            if (~n == ~n8 && ~(1 + n4) == ~n3 && ~(0x20 & this.anIntArrayArray1853[n8][n3]) == -1) {
                                return true;
                            }
                        }
                    }
                    else {
                        if (~n8 == ~(1 + n) && ~n4 == ~n3 && (0x80 & this.anIntArrayArray1853[n8][n3]) == 0x0) {
                            return true;
                        }
                        if (n == n8 && -1 + n4 == n3 && (0x2 & this.anIntArrayArray1853[n8][n3]) == 0x0) {
                            return true;
                        }
                    }
                }
                if (n5 == 8) {
                    if (n8 == n && 1 + n4 == n3 && (this.anIntArrayArray1853[n8][n3] & 0x20) == 0x0) {
                        return true;
                    }
                    if (n == n8 && n3 == n4 - 1 && (this.anIntArrayArray1853[n8][n3] & 0x2) == 0x0) {
                        return true;
                    }
                    if (n8 == -1 + n && n3 == n4 && (0x8 & this.anIntArrayArray1853[n8][n3]) == 0x0) {
                        return true;
                    }
                    if (~n8 == ~(1 + n) && n3 == n4 && ~(0x80 & this.anIntArrayArray1853[n8][n3]) == -1) {
                        return true;
                    }
                }
            }
            else {
                final int n9 = -1 + n7 + n8;
                final int n10 = n7 + (n3 - 1);
                if (n5 == 6 || ~n5 == 0xFFFFFFF8) {
                    if (~n5 == 0xFFFFFFF8) {
                        n2 = (n2 + 2 & 0x3);
                    }
                    if (n2 == 0) {
                        if (n8 == 1 + n && n4 >= n3 && n10 >= n4 && (0x80 & this.anIntArrayArray1853[n8][n4]) == 0x0) {
                            return true;
                        }
                        if (~n <= ~n8 && ~n9 <= ~n && n4 + -n7 == n3 && (this.anIntArrayArray1853[n][n10] & 0x2) == 0x0) {
                            return true;
                        }
                    }
                    else if (~n2 == 0xFFFFFFFE) {
                        if (~n8 == ~(-n7 + n) && n4 >= n3 && n4 <= n10 && (0x8 & this.anIntArrayArray1853[n9][n4]) == 0x0) {
                            return true;
                        }
                        if (~n <= ~n8 && ~n9 <= ~n && n3 == n4 - n7 && ~(0x2 & this.anIntArrayArray1853[n][n10]) == -1) {
                            return true;
                        }
                    }
                    else if (~n2 != 0xFFFFFFFD) {
                        if (n2 == 3) {
                            if (~n8 == ~(1 + n) && ~n4 <= ~n3 && ~n4 >= ~n10 && ~(0x80 & this.anIntArrayArray1853[n8][n4]) == -1) {
                                return true;
                            }
                            if (~n8 >= ~n && ~n9 <= ~n && 1 + n4 == n3 && (0x20 & this.anIntArrayArray1853[n][n3]) == 0x0) {
                                return true;
                            }
                        }
                    }
                    else {
                        if (n8 == -n7 + n && ~n3 >= ~n4 && n10 >= n4 && ~(this.anIntArrayArray1853[n9][n4] & 0x8) == -1) {
                            return true;
                        }
                        if (n8 <= n && ~n >= ~n9 && ~n3 == ~(n4 + 1) && (0x20 & this.anIntArrayArray1853[n][n3]) == 0x0) {
                            return true;
                        }
                    }
                }
                if (n5 == 8) {
                    if (n8 <= n && ~n >= ~n9 && n3 == 1 + n4 && ~(this.anIntArrayArray1853[n][n3] & 0x20) == -1) {
                        return true;
                    }
                    if (n >= n8 && ~n >= ~n9 && ~n3 == ~(n4 - n7) && ~(this.anIntArrayArray1853[n][n10] & 0x2) == -1) {
                        return true;
                    }
                    if (n8 == -n7 + n && ~n4 <= ~n3 && ~n10 <= ~n4 && (this.anIntArrayArray1853[n9][n4] & 0x8) == 0x0) {
                        return true;
                    }
                    if (n + 1 == n8 && ~n3 >= ~n4 && n10 >= n4 && (this.anIntArrayArray1853[n8][n4] & 0x80) == 0x0) {
                        return true;
                    }
                }
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pfa.B(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ')');
        }
    }
    
    final boolean method2939(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        try {
            if (n5 > 1) {
                return Class98_Sub5.method960(n2, n, -126, n3, n9, n8, n6, n5, n5) || this.method2936(n2, n9, n6, -1, n3, n, n5, n8, n5, n7);
            }
            final int n10 = n2 - -n - 1;
            final int n11 = -1 + n6 - -n9;
            return (~n2 >= ~n8 && n10 >= n8 && n3 >= n6 && ~n3 >= ~n11) || (~n8 == ~(n2 - 1) && ~n3 <= ~n6 && ~n11 <= ~n3 && (0x8 & this.anIntArrayArray1853[-this.anInt1854 + n8][n3 - this.anInt1855]) == 0x0 && ~(n7 & 0x8) == -1) || (~(1 + n10) == ~n8 && ~n6 >= ~n3 && ~n11 <= ~n3 && ~(this.anIntArrayArray1853[-this.anInt1854 + n8][-this.anInt1855 + n3] & 0x80) == -1 && ~(0x2 & n7) == -1) || (n6 - 1 == n3 && n2 <= n8 && n10 >= n8 && ~(0x2 & this.anIntArrayArray1853[n8 - this.anInt1854][n3 + -this.anInt1855]) == -1 && (n7 & 0x4) == 0x0) || (n4 == 14672 && (~n3 == ~(1 + n11) && ~n2 >= ~n8 && ~n8 >= ~n10 && ~(0x20 & this.anIntArrayArray1853[-this.anInt1854 + n8][-this.anInt1855 + n3]) == -1 && ~(0x1 & n7) == -1));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pfa.P(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ')');
        }
    }
    
    private final void method2940(final int n, final int n2, final int n3, final int n4) {
        try {
            if (n4 <= 28) {
                this.method2938(30, 97, -75, -102, 18, -47, -86, 19);
            }
            this.anIntArrayArray1853[n][n2] = Class202.method2702(this.anIntArrayArray1853[n][n2], ~n3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pfa.N(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    final void method2941(int n, final int n2, int n3) {
        try {
            n3 -= this.anInt1854;
            n -= this.anInt1855;
            if (n2 != 23254) {
                this.method2945(-47, true, false, 44, -19, -64, (byte)(-46));
            }
            this.anIntArrayArray1853[n3][n] = Class41.method366(this.anIntArrayArray1853[n3][n], 2097152);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pfa.D(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method2942(final int n) {
        try {
            synchronized (Class211.aClass79_1594) {
                Class211.aClass79_1594.method806((byte)29);
                if (n != 1) {
                    Class243.anInt1852 = 11;
                }
            }
            synchronized (PlayerUpdate.aClass79_3411) {
                PlayerUpdate.aClass79_3411.method806((byte)80);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pfa.A(" + n + ')');
        }
    }
    
    static final void method2943(final float[] array, int n, final int n2, final int n3, final float[] array2, int n4, final int n5, final float n6, int n7, final int n8, final int n9) {
        try {
            if (n9 < 101) {
                Class243.anInt1852 = 104;
            }
            n4 -= n3;
            n -= n5;
            n7 -= n8;
            final float n10 = n * array2[1] + array2[0] * n7 + n4 * array2[2];
            final float n11 = n4 * array2[5] + (array2[3] * n7 + array2[4] * n);
            final float n12 = n7 * array2[6] + array2[7] * n + array2[8] * n4;
            final float n13 = (float)Math.sqrt(n11 * n11 + n10 * n10 + n12 * n12);
            float n14 = (float)Math.atan2(n10, n12) / 6.2831855f + 0.5f;
            float n15 = 0.5f + (float)Math.asin(n11 / n13) / 3.1415927f + n6;
            if (n2 == 1) {
                final float n16 = n14;
                n14 = -n15;
                n15 = n16;
            }
            else if (n2 == 2) {
                n14 = -n14;
                n15 = -n15;
            }
            else if (~n2 == 0xFFFFFFFC) {
                final float n17 = n14;
                n14 = n15;
                n15 = -n17;
            }
            array[0] = n14;
            array[1] = n15;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pfa.E(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ')');
        }
    }
    
    final void method2944(int n, final int n2, int n3) {
        try {
            n -= this.anInt1855;
            if (n2 >= 28) {
                n3 -= this.anInt1854;
                this.anIntArrayArray1853[n3][n] = Class202.method2702(this.anIntArrayArray1853[n3][n], -2097153);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pfa.H(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final void method2945(int n, final boolean b, final boolean b2, final int n2, int n3, final int n4, final byte b3) {
        try {
            n -= this.anInt1855;
            if (b3 < 63) {
                this.anInt1857 = 59;
            }
            n3 -= this.anInt1854;
            if (~n4 == -1) {
                if (~n2 == -1) {
                    this.method2947(n3, 128, (byte)(-25), n);
                    this.method2947(-1 + n3, 8, (byte)(-23), n);
                }
                if (~n2 == 0xFFFFFFFE) {
                    this.method2947(n3, 2, (byte)(-96), n);
                    this.method2947(n3, 32, (byte)(-89), 1 + n);
                }
                if (n2 == 2) {
                    this.method2947(n3, 8, (byte)(-54), n);
                    this.method2947(1 + n3, 128, (byte)(-114), n);
                }
                if (~n2 == 0xFFFFFFFC) {
                    this.method2947(n3, 32, (byte)(-104), n);
                    this.method2947(n3, 2, (byte)(-20), n - 1);
                }
            }
            if (n4 == 1 || ~n4 == 0xFFFFFFFC) {
                if (n2 == 0) {
                    this.method2947(n3, 1, (byte)(-119), n);
                    this.method2947(n3 - 1, 16, (byte)(-68), 1 + n);
                }
                if (~n2 == 0xFFFFFFFE) {
                    this.method2947(n3, 4, (byte)(-99), n);
                    this.method2947(n3 + 1, 64, (byte)(-45), n + 1);
                }
                if (~n2 == 0xFFFFFFFD) {
                    this.method2947(n3, 16, (byte)(-49), n);
                    this.method2947(n3 + 1, 1, (byte)(-73), n - 1);
                }
                if (n2 == 3) {
                    this.method2947(n3, 64, (byte)(-53), n);
                    this.method2947(-1 + n3, 4, (byte)(-82), -1 + n);
                }
            }
            if (n4 == 2) {
                if (n2 == 0) {
                    this.method2947(n3, 130, (byte)(-52), n);
                    this.method2947(-1 + n3, 8, (byte)(-33), n);
                    this.method2947(n3, 32, (byte)(-26), n + 1);
                }
                if (n2 == 1) {
                    this.method2947(n3, 10, (byte)(-53), n);
                    this.method2947(n3, 32, (byte)(-112), n + 1);
                    this.method2947(1 + n3, 128, (byte)(-71), n);
                }
                if (n2 == 2) {
                    this.method2947(n3, 40, (byte)(-72), n);
                    this.method2947(1 + n3, 128, (byte)(-113), n);
                    this.method2947(n3, 2, (byte)(-111), -1 + n);
                }
                if (n2 == 3) {
                    this.method2947(n3, 160, (byte)(-90), n);
                    this.method2947(n3, 2, (byte)(-45), -1 + n);
                    this.method2947(-1 + n3, 8, (byte)(-95), n);
                }
            }
            if (b) {
                if (n4 == 0) {
                    if (~n2 == -1) {
                        this.method2947(n3, 65536, (byte)(-30), n);
                        this.method2947(n3 - 1, 4096, (byte)(-126), n);
                    }
                    if (~n2 == 0xFFFFFFFE) {
                        this.method2947(n3, 1024, (byte)(-102), n);
                        this.method2947(n3, 16384, (byte)(-92), 1 + n);
                    }
                    if (~n2 == 0xFFFFFFFD) {
                        this.method2947(n3, 4096, (byte)(-105), n);
                        this.method2947(n3 + 1, 65536, (byte)(-39), n);
                    }
                    if (n2 == 3) {
                        this.method2947(n3, 16384, (byte)(-111), n);
                        this.method2947(n3, 1024, (byte)(-106), n - 1);
                    }
                }
                if (n4 == 1 || ~n4 == 0xFFFFFFFC) {
                    if (n2 == 0) {
                        this.method2947(n3, 512, (byte)(-128), n);
                        this.method2947(n3 - 1, 8192, (byte)(-60), 1 + n);
                    }
                    if (~n2 == 0xFFFFFFFE) {
                        this.method2947(n3, 2048, (byte)(-78), n);
                        this.method2947(n3 + 1, 32768, (byte)(-81), n + 1);
                    }
                    if (n2 == 2) {
                        this.method2947(n3, 8192, (byte)(-50), n);
                        this.method2947(1 + n3, 512, (byte)(-92), -1 + n);
                    }
                    if (~n2 == 0xFFFFFFFC) {
                        this.method2947(n3, 32768, (byte)(-85), n);
                        this.method2947(-1 + n3, 2048, (byte)(-27), n - 1);
                    }
                }
                if (n4 == 2) {
                    if (~n2 == -1) {
                        this.method2947(n3, 66560, (byte)(-49), n);
                        this.method2947(-1 + n3, 4096, (byte)(-27), n);
                        this.method2947(n3, 16384, (byte)(-72), 1 + n);
                    }
                    if (n2 == 1) {
                        this.method2947(n3, 5120, (byte)(-83), n);
                        this.method2947(n3, 16384, (byte)(-95), 1 + n);
                        this.method2947(n3 + 1, 65536, (byte)(-110), n);
                    }
                    if (n2 == 2) {
                        this.method2947(n3, 20480, (byte)(-72), n);
                        this.method2947(n3 + 1, 65536, (byte)(-36), n);
                        this.method2947(n3, 1024, (byte)(-27), n - 1);
                    }
                    if (~n2 == 0xFFFFFFFC) {
                        this.method2947(n3, 81920, (byte)(-26), n);
                        this.method2947(n3, 1024, (byte)(-112), n - 1);
                        this.method2947(n3 - 1, 4096, (byte)(-108), n);
                    }
                }
            }
            if (b2) {
                if (~n4 == -1) {
                    if (~n2 == -1) {
                        this.method2947(n3, 536870912, (byte)(-67), n);
                        this.method2947(n3 - 1, 33554432, (byte)(-84), n);
                    }
                    if (n2 == 1) {
                        this.method2947(n3, 8388608, (byte)(-24), n);
                        this.method2947(n3, 134217728, (byte)(-107), n + 1);
                    }
                    if (n2 == 2) {
                        this.method2947(n3, 33554432, (byte)(-86), n);
                        this.method2947(1 + n3, 536870912, (byte)(-24), n);
                    }
                    if (~n2 == 0xFFFFFFFC) {
                        this.method2947(n3, 134217728, (byte)(-46), n);
                        this.method2947(n3, 8388608, (byte)(-47), n - 1);
                    }
                }
                if (n4 == 1 || ~n4 == 0xFFFFFFFC) {
                    if (~n2 == -1) {
                        this.method2947(n3, 4194304, (byte)(-50), n);
                        this.method2947(-1 + n3, 67108864, (byte)(-123), 1 + n);
                    }
                    if (n2 == 1) {
                        this.method2947(n3, 16777216, (byte)(-39), n);
                        this.method2947(1 + n3, 268435456, (byte)(-103), 1 + n);
                    }
                    if (n2 == 2) {
                        this.method2947(n3, 67108864, (byte)(-41), n);
                        this.method2947(n3 + 1, 4194304, (byte)(-34), n - 1);
                    }
                    if (~n2 == 0xFFFFFFFC) {
                        this.method2947(n3, 268435456, (byte)(-33), n);
                        this.method2947(n3 - 1, 16777216, (byte)(-79), -1 + n);
                    }
                }
                if (n4 == 2) {
                    if (~n2 == -1) {
                        this.method2947(n3, 545259520, (byte)(-70), n);
                        this.method2947(-1 + n3, 33554432, (byte)(-66), n);
                        this.method2947(n3, 134217728, (byte)(-36), 1 + n);
                    }
                    if (~n2 == 0xFFFFFFFE) {
                        this.method2947(n3, 41943040, (byte)(-29), n);
                        this.method2947(n3, 134217728, (byte)(-96), 1 + n);
                        this.method2947(1 + n3, 536870912, (byte)(-72), n);
                    }
                    if (~n2 == 0xFFFFFFFD) {
                        this.method2947(n3, 167772160, (byte)(-112), n);
                        this.method2947(n3 + 1, 536870912, (byte)(-93), n);
                        this.method2947(n3, 8388608, (byte)(-114), n - 1);
                    }
                    if (~n2 == 0xFFFFFFFC) {
                        this.method2947(n3, 671088640, (byte)(-66), n);
                        this.method2947(n3, 8388608, (byte)(-124), n - 1);
                        this.method2947(-1 + n3, 33554432, (byte)(-117), n);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pfa.K(" + n + ',' + b + ',' + b2 + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b3 + ')');
        }
    }
    
    final void method2946(int n, final byte b, int n2) {
        try {
            n2 -= this.anInt1855;
            if (b != -14) {
                this.method2944(92, -7, 34);
            }
            n -= this.anInt1854;
            this.anIntArrayArray1853[n][n2] = Class41.method366(this.anIntArrayArray1853[n][n2], 262144);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pfa.I(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    private final void method2947(final int n, final int n2, final byte b, final int n3) {
        try {
            if (b >= -17) {
                this.method2944(105, 35, 75);
            }
            this.anIntArrayArray1853[n][n3] = Class41.method366(this.anIntArrayArray1853[n][n3], n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pfa.G(" + n + ',' + n2 + ',' + b + ',' + n3 + ')');
        }
    }
    
    final void method2948(final boolean b, int n, int n2) {
        try {
            n2 -= this.anInt1855;
            n -= this.anInt1854;
            if (b) {
                this.method2945(0, false, false, 89, 74, -95, (byte)(-85));
            }
            this.anIntArrayArray1853[n][n2] = Class202.method2702(this.anIntArrayArray1853[n][n2], -262145);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pfa.M(" + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    final void method2949(final int n, final boolean b, int n2, final int n3, final int n4, final boolean b2, int n5) {
        try {
            int n6 = 256;
            if (b2) {
                n6 |= 0x20000;
            }
            n2 -= this.anInt1854;
            if (n != 0) {
                this.method2946(-43, (byte)16, 91);
            }
            if (b) {
                n6 |= 0x40000000;
            }
            n5 -= this.anInt1855;
            for (int n7 = n2; n2 - -n4 > n7; ++n7) {
                if (~n7 <= -1 && ~n7 > ~this.anInt1857) {
                    for (int n8 = n5; ~n8 > ~(n5 - -n3); ++n8) {
                        if (n8 >= 0 && n8 < this.anInt1856) {
                            this.method2947(n7, n6, (byte)(-51), n8);
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pfa.L(" + n + ',' + b + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b2 + ',' + n5 + ')');
        }
    }
    
    final void method2950(final byte b) {
        try {
            int n = 0;
            if (b == -99) {
                while (~this.anInt1857 < ~n) {
                    for (int i = 0; i < this.anInt1856; ++i) {
                        if (n != 0 && i != 0 && n < this.anInt1857 - 5 && ~i > ~(this.anInt1856 - 5)) {
                            this.anIntArrayArray1853[n][i] = 2097152;
                        }
                        else {
                            this.anIntArrayArray1853[n][i] = -1;
                        }
                    }
                    ++n;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pfa.Q(" + b + ')');
        }
    }
    
    final void method2951(int n, final boolean b, final int n2, int n3, int n4, final boolean b2, final int n5, int n6) {
        try {
            int n7 = 256;
            if (b) {
                n7 |= 0x20000;
            }
            if (b2) {
                n7 |= 0x40000000;
            }
            if (n2 == 1 || ~n2 == 0xFFFFFFFC) {
                final int n8 = n;
                n = n3;
                n3 = n8;
            }
            n4 -= this.anInt1855;
            int n9;
            int n10;
            for (n6 = (n9 = n6 - this.anInt1854); ~(n + n6) < ~n9; ++n9) {
                if (n9 >= 0 && ~n9 > ~this.anInt1857) {
                    for (n10 = n4; ~n10 > ~(n4 + n3); ++n10) {
                        if (~n10 <= -1 && ~this.anInt1856 < ~n10) {
                            this.method2940(n9, n10, n7, 75);
                        }
                    }
                }
            }
            if (n5 != 131072) {
                this.method2938(-71, -69, -92, -110, 104, -115, 29, -10);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pfa.J(" + n + ',' + b + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b2 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    final boolean method2952(int n, int n2, final byte b, final int n3, int n4, final int n5, int n6, final int n7) {
        try {
            if (~n3 != 0xFFFFFFFE) {
                if (n2 <= n6 && n6 <= -1 + (n2 + n3) && n <= n && n3 + n - 1 >= n) {
                    return true;
                }
            }
            else if (n2 == n6 && ~n4 == ~n) {
                return true;
            }
            n4 -= this.anInt1855;
            n2 -= this.anInt1854;
            n -= this.anInt1855;
            n6 -= this.anInt1854;
            if (~n3 != 0xFFFFFFFE) {
                final int n8 = n2 - -n3 - 1;
                final int n9 = -1 + n3 + n4;
                if (~n7 == -1) {
                    if (n5 == 0) {
                        if (~n2 == ~(n6 + -n3) && n4 <= n && ~n9 <= ~n) {
                            return true;
                        }
                        if (n6 >= n2 && ~n8 <= ~n6 && n + 1 == n4 && ~(this.anIntArrayArray1853[n6][n4] & 0x2C0120) == -1) {
                            return true;
                        }
                        if (n2 <= n6 && ~n6 >= ~n8 && n4 == -n3 + n && (this.anIntArrayArray1853[n6][n9] & 0x2C0102) == 0x0) {
                            return true;
                        }
                    }
                    else if (n5 == 1) {
                        if (n2 <= n6 && ~n8 <= ~n6 && ~(1 + n) == ~n4) {
                            return true;
                        }
                        if (n6 + -n3 == n2 && ~n <= ~n4 && ~n9 <= ~n && ~(this.anIntArrayArray1853[n8][n] & 0x2C0108) == -1) {
                            return true;
                        }
                        if (n6 + 1 == n2 && n >= n4 && n9 >= n && ~(this.anIntArrayArray1853[n2][n] & 0x2C0180) == -1) {
                            return true;
                        }
                    }
                    else if (~n5 != 0xFFFFFFFD) {
                        if (~n5 == 0xFFFFFFFC) {
                            if (~n6 <= ~n2 && n8 >= n6 && ~(-n3 + n) == ~n4) {
                                return true;
                            }
                            if (-n3 + n6 == n2 && n4 <= n && n <= n9 && ~(this.anIntArrayArray1853[n8][n] & 0x2C0108) == -1) {
                                return true;
                            }
                            if (n6 + 1 == n2 && n4 <= n && ~n >= ~n9 && ~(this.anIntArrayArray1853[n2][n] & 0x2C0180) == -1) {
                                return true;
                            }
                        }
                    }
                    else {
                        if (~n2 == ~(n6 + 1) && n >= n4 && ~n9 <= ~n) {
                            return true;
                        }
                        if (~n6 <= ~n2 && ~n8 <= ~n6 && n + 1 == n4 && (0x2C0120 & this.anIntArrayArray1853[n6][n4]) == 0x0) {
                            return true;
                        }
                        if (~n6 <= ~n2 && ~n6 >= ~n8 && ~n4 == ~(n - n3) && (0x2C0102 & this.anIntArrayArray1853[n6][n9]) == 0x0) {
                            return true;
                        }
                    }
                }
                if (~n7 == 0xFFFFFFFD) {
                    if (n5 != 0) {
                        if (n5 == 1) {
                            if (~n2 == ~(n6 + -n3) && n >= n4 && ~n >= ~n9 && ~(0x2C0108 & this.anIntArrayArray1853[n8][n]) == -1) {
                                return true;
                            }
                            if (n2 <= n6 && ~n8 <= ~n6 && ~n4 == ~(1 + n)) {
                                return true;
                            }
                            if (~(1 + n6) == ~n2 && n >= n4 && n <= n9) {
                                return true;
                            }
                            if (~n6 <= ~n2 && n6 <= n8 && n4 == n - n3 && (0x2C0102 & this.anIntArrayArray1853[n6][n9]) == 0x0) {
                                return true;
                            }
                        }
                        else if (~n5 == 0xFFFFFFFD) {
                            if (n2 == n6 - n3 && n >= n4 && ~n9 <= ~n && (this.anIntArrayArray1853[n8][n] & 0x2C0108) == 0x0) {
                                return true;
                            }
                            if (~n2 >= ~n6 && n6 <= n8 && n4 == n + 1 && ~(this.anIntArrayArray1853[n6][n4] & 0x2C0120) == -1) {
                                return true;
                            }
                            if (1 + n6 == n2 && ~n <= ~n4 && n <= n9) {
                                return true;
                            }
                            if (n6 >= n2 && n8 >= n6 && ~n4 == ~(-n3 + n)) {
                                return true;
                            }
                        }
                        else if (n5 == 3) {
                            if (~(-n3 + n6) == ~n2 && n >= n4 && n <= n9) {
                                return true;
                            }
                            if (~n2 >= ~n6 && ~n6 >= ~n8 && ~n4 == ~(n + 1) && ~(0x2C0120 & this.anIntArrayArray1853[n6][n4]) == -1) {
                                return true;
                            }
                            if (n2 == n6 + 1 && ~n4 >= ~n && n <= n9 && (this.anIntArrayArray1853[n2][n] & 0x2C0180) == 0x0) {
                                return true;
                            }
                            if (~n2 >= ~n6 && n8 >= n6 && ~n4 == ~(-n3 + n)) {
                                return true;
                            }
                        }
                    }
                    else {
                        if (n6 + -n3 == n2 && n >= n4 && ~n >= ~n9) {
                            return true;
                        }
                        if (~n2 >= ~n6 && n8 >= n6 && ~n4 == ~(1 + n)) {
                            return true;
                        }
                        if (~n2 == ~(1 + n6) && ~n4 >= ~n && n <= n9 && ~(this.anIntArrayArray1853[n2][n] & 0x2C0180) == -1) {
                            return true;
                        }
                        if (~n2 >= ~n6 && ~n6 >= ~n8 && ~(n + -n3) == ~n4 && (0x2C0102 & this.anIntArrayArray1853[n6][n9]) == 0x0) {
                            return true;
                        }
                    }
                }
                if (n7 == 9) {
                    if (n6 >= n2 && n8 >= n6 && 1 + n == n4 && (0x2C0120 & this.anIntArrayArray1853[n6][n4]) == 0x0) {
                        return true;
                    }
                    if (n6 >= n2 && ~n8 <= ~n6 && ~(n - n3) == ~n4 && (this.anIntArrayArray1853[n6][n9] & 0x2C0102) == 0x0) {
                        return true;
                    }
                    if (~(n6 - n3) == ~n2 && n4 <= n && n9 >= n && ~(this.anIntArrayArray1853[n8][n] & 0x2C0108) == -1) {
                        return true;
                    }
                    if (~n2 == ~(1 + n6) && ~n <= ~n4 && n <= n9 && (0x2C0180 & this.anIntArrayArray1853[n2][n]) == 0x0) {
                        return true;
                    }
                }
            }
            else {
                if (~n7 == -1) {
                    if (~n5 != -1) {
                        if (n5 == 1) {
                            if (~n2 == ~n6 && 1 + n == n4) {
                                return true;
                            }
                            if (~n2 == ~(n6 - 1) && n4 == n && ~(this.anIntArrayArray1853[n2][n4] & 0x2C0108) == -1) {
                                return true;
                            }
                            if (~n2 == ~(n6 + 1) && n == n4 && (this.anIntArrayArray1853[n2][n4] & 0x2C0180) == 0x0) {
                                return true;
                            }
                        }
                        else if (~n5 != 0xFFFFFFFD) {
                            if (n5 == 3) {
                                if (~n6 == ~n2 && -1 + n == n4) {
                                    return true;
                                }
                                if (n6 - 1 == n2 && ~n == ~n4 && (this.anIntArrayArray1853[n2][n4] & 0x2C0108) == 0x0) {
                                    return true;
                                }
                                if (n6 + 1 == n2 && ~n4 == ~n && (this.anIntArrayArray1853[n2][n4] & 0x2C0180) == 0x0) {
                                    return true;
                                }
                            }
                        }
                        else {
                            if (~(1 + n6) == ~n2 && ~n4 == ~n) {
                                return true;
                            }
                            if (n6 == n2 && ~n4 == ~(1 + n) && ~(0x2C0120 & this.anIntArrayArray1853[n2][n4]) == -1) {
                                return true;
                            }
                            if (~n2 == ~n6 && ~n4 == ~(n - 1) && (0x2C0102 & this.anIntArrayArray1853[n2][n4]) == 0x0) {
                                return true;
                            }
                        }
                    }
                    else {
                        if (n2 == n6 - 1 && n == n4) {
                            return true;
                        }
                        if (n2 == n6 && ~(1 + n) == ~n4 && (0x2C0120 & this.anIntArrayArray1853[n2][n4]) == 0x0) {
                            return true;
                        }
                        if (n6 == n2 && n4 == n - 1 && (0x2C0102 & this.anIntArrayArray1853[n2][n4]) == 0x0) {
                            return true;
                        }
                    }
                }
                if (n7 == 2) {
                    if (~n5 == -1) {
                        if (~(-1 + n6) == ~n2 && ~n == ~n4) {
                            return true;
                        }
                        if (n2 == n6 && 1 + n == n4) {
                            return true;
                        }
                        if (~n2 == ~(1 + n6) && n == n4 && (this.anIntArrayArray1853[n2][n4] & 0x2C0180) == 0x0) {
                            return true;
                        }
                        if (~n2 == ~n6 && ~n4 == ~(-1 + n) && ~(0x2C0102 & this.anIntArrayArray1853[n2][n4]) == -1) {
                            return true;
                        }
                    }
                    else if (~n5 != 0xFFFFFFFE) {
                        if (~n5 == 0xFFFFFFFD) {
                            if (~n2 == ~(n6 - 1) && n4 == n && (this.anIntArrayArray1853[n2][n4] & 0x2C0108) == 0x0) {
                                return true;
                            }
                            if (~n2 == ~n6 && n + 1 == n4 && (0x2C0120 & this.anIntArrayArray1853[n2][n4]) == 0x0) {
                                return true;
                            }
                            if (~n2 == ~(n6 + 1) && n4 == n) {
                                return true;
                            }
                            if (n2 == n6 && ~n4 == ~(-1 + n)) {
                                return true;
                            }
                        }
                        else if (n5 == 3) {
                            if (n2 == n6 - 1 && ~n4 == ~n) {
                                return true;
                            }
                            if (n2 == n6 && 1 + n == n4 && ~(this.anIntArrayArray1853[n2][n4] & 0x2C0120) == -1) {
                                return true;
                            }
                            if (~n2 == ~(n6 + 1) && n4 == n && ~(this.anIntArrayArray1853[n2][n4] & 0x2C0180) == -1) {
                                return true;
                            }
                            if (~n6 == ~n2 && n4 == -1 + n) {
                                return true;
                            }
                        }
                    }
                    else {
                        if (~n2 == ~(n6 - 1) && ~n4 == ~n && ~(this.anIntArrayArray1853[n2][n4] & 0x2C0108) == -1) {
                            return true;
                        }
                        if (n2 == n6 && n4 == 1 + n) {
                            return true;
                        }
                        if (n2 == n6 + 1 && ~n4 == ~n) {
                            return true;
                        }
                        if (~n2 == ~n6 && ~(-1 + n) == ~n4 && (0x2C0102 & this.anIntArrayArray1853[n2][n4]) == 0x0) {
                            return true;
                        }
                    }
                }
                if (~n7 == 0xFFFFFFF6) {
                    if (~n2 == ~n6 && n + 1 == n4 && (this.anIntArrayArray1853[n2][n4] & 0x20) == 0x0) {
                        return true;
                    }
                    if (~n6 == ~n2 && n - 1 == n4 && (this.anIntArrayArray1853[n2][n4] & 0x2) == 0x0) {
                        return true;
                    }
                    if (-1 + n6 == n2 && n == n4 && ~(this.anIntArrayArray1853[n2][n4] & 0x8) == -1) {
                        return true;
                    }
                    if (1 + n6 == n2 && n4 == n && (0x80 & this.anIntArrayArray1853[n2][n4]) == 0x0) {
                        return true;
                    }
                }
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pfa.F(" + n + ',' + n2 + ',' + b + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
}
