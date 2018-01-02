// 
// Decompiled by Procyon v0.5.30
// 

final class Class175
{
    private int anInt1354;
    private int anInt1355;
    private static Class332 aClass332_1356;
    private static Class146 aClass146_1357;
    private int anInt1358;
    private int anInt1359;
    private int anInt1360;
    int anInt1361;
    private int anInt1362;
    private static Class332 aClass332_1363;
    private int anInt1364;
    private int anInt1365;
    private int anInt1366;
    private static int[] anIntArray1367;
    private Class332 aClass332_1368;
    private int anInt1369;
    private int anInt1370;
    private boolean aBoolean1371;
    
    private static final void method2568(final ha ha) {
        if (Class175.aClass332_1356 == null) {
            final int[] array = new int[16384];
            final int[] array2 = new int[16384];
            for (int i = 0; i < 64; ++i) {
                final int n = 64 - i;
                final int n2 = n * n;
                final int n3 = 128 - i - 1;
                final int n4 = i * 128;
                final int n5 = n3 * 128;
                for (int j = 0; j < 64; ++j) {
                    final int n6 = 64 - j;
                    final int n7 = n6 * n6;
                    final int n8 = 128 - j - 1;
                    int n9 = (256 - (n7 + n2 << 8) / 4096) * 16 * 192 / 1536;
                    if (n9 < 0) {
                        n9 = 0;
                    }
                    else if (n9 > 255) {
                        n9 = 255;
                    }
                    final int n10 = n9 / 2;
                    final int[] array3 = array2;
                    final int n11 = n4 + j;
                    final int[] array4 = array2;
                    final int n12 = n4 + n8;
                    final int[] array5 = array2;
                    final int n13 = n5 + j;
                    final int[] array6 = array2;
                    final int n14 = n5 + n8;
                    final int n15 = 0xFF000000 | n9 << 16;
                    array5[n13] = (array6[n14] = n15);
                    array3[n11] = (array4[n12] = n15);
                    final int[] array7 = array;
                    final int n16 = n4 + j;
                    final int[] array8 = array;
                    final int n17 = n4 + n8;
                    final int[] array9 = array;
                    final int n18 = n5 + j;
                    final int[] array10 = array;
                    final int n19 = n5 + n8;
                    final int n20 = 127 - n10 << 24 | 0xFFFFFF;
                    array9[n18] = (array10[n19] = n20);
                    array7[n16] = (array8[n17] = n20);
                }
            }
            Class175.aClass332_1356 = ha.method1748(-7962, 0, 128, 128, array2, 128);
            Class175.aClass332_1363 = ha.method1748(-7962, 0, 128, 128, array, 128);
        }
    }
    
    private final boolean method2569(final ha ha, final Class175 class175) {
        if (this.aClass332_1368 == null) {
            if (this.anInt1359 == 0) {
                if (Class98_Sub10_Sub8.aD5578.method8(119, this.anInt1366)) {
                    this.aClass332_1368 = ha.method1748(-7962, 0, this.anInt1354, this.anInt1354, Class98_Sub10_Sub8.aD5578.method13(117, this.anInt1354, this.anInt1366, 0.7f, false, this.anInt1354), this.anInt1354);
                }
            }
            else if (this.anInt1359 == 2) {
                this.method2572(ha, class175);
            }
            else if (this.anInt1359 == 1) {
                this.method2576(ha, class175);
            }
        }
        return this.aClass332_1368 != null;
    }
    
    final void method2570() {
        this.aClass332_1368 = null;
    }
    
    public static void method2571() {
        Class175.aClass146_1357 = null;
        Class175.aClass332_1363 = null;
        Class175.aClass332_1356 = null;
        Class175.anIntArray1367 = null;
    }
    
    private final void method2572(final ha ha, final Class175 class175) {
        final Class178 method981 = Class98_Sub6.method981(0, -9252, Class98_Sub10_Sub39.aClass207_5773, this.anInt1366);
        if (method981 != null) {
            ha.K(Class175.anIntArray1367);
            ha.KA(0, 0, this.anInt1354, this.anInt1354);
            ha.aa(0, 0, this.anInt1354, this.anInt1354, 0, 0);
            int n = 0;
            int n2 = 0;
            int n3 = 256;
            if (class175 != null) {
                if (class175.aBoolean1371) {
                    n = -class175.anInt1370;
                    n2 = -class175.anInt1355;
                    n3 = -class175.anInt1364;
                }
                else {
                    n = this.anInt1370 - class175.anInt1370;
                    n2 = this.anInt1355 - class175.anInt1355;
                    n3 = this.anInt1364 - class175.anInt1364;
                }
            }
            if (this.anInt1358 != 0) {
                final int n4 = -this.anInt1358 & 0x3FFF;
                final int n5 = Class284_Sub2_Sub2.anIntArray6200[n4];
                final int n6 = Class284_Sub2_Sub2.anIntArray6202[n4];
                final int n7 = n2 * n6 - n3 * n5 >> 14;
                n3 = n2 * n5 + n3 * n6 >> 14;
                n2 = n7;
            }
            if (this.anInt1362 != 0) {
                final int n8 = -this.anInt1362 & 0x3FFF;
                final int n9 = Class284_Sub2_Sub2.anIntArray6200[n8];
                final int n10 = Class284_Sub2_Sub2.anIntArray6202[n8];
                final int n11 = n3 * n9 + n * n10 >> 14;
                n3 = n3 * n10 - n * n9 >> 14;
                n = n11;
            }
            ha.xa(1.0f);
            ha.ZA(16777215, 1.0f, 1.0f, n, n2, n3);
            final Class146 method982 = ha.method1790(method981, 2048, 0, 64, 768);
            final int n12 = method982.RA() - method982.V();
            final int n13 = method982.EA() - method982.fa();
            final int n14 = method982.V() + n12 / 2;
            final int n15 = method982.fa() + n13 / 2;
            final int n16 = (n12 > n13) ? n12 : n13;
            ha.DA(n14, n15, n16, n16);
            ha.a(ha.method1821());
            final Class111 method983 = ha.method1793();
            method983.method2100(0, 0, ha.i() - method982.HA());
            method982.method2329(method983, null, ha.i(), 1);
            (this.aClass332_1368 = ha.method1797(0, 0, this.anInt1354, this.anInt1354, true)).method3742(0, 0, 3);
            ha.KA(Class175.anIntArray1367[0], Class175.anIntArray1367[1], Class175.anIntArray1367[2], Class175.anIntArray1367[3]);
        }
    }
    
    final void method2573(final ha ha, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        if (this.aClass332_1368 != null) {
            int n7 = this.anInt1358 - n5 & 0x3FFF;
            int n8 = this.anInt1362 - n6 & 0x3FFF;
            if (n8 > 8192) {
                n8 -= 16384;
            }
            if (n7 > 8192) {
                n7 -= 16384;
            }
            final int n9 = n7 * n4 / 4096 + (n4 - this.anInt1360) / 2;
            final int n10 = n8 * n4 / -4096 + (n3 - this.anInt1360) / 2;
            if (n9 < n4 && n9 + this.anInt1360 > 0 && n10 < n3 && n10 + this.anInt1360 > 0) {
                this.aClass332_1368.method3726(n10 + n, n9 + n2, this.anInt1360, this.anInt1360);
            }
        }
    }
    
    private static final void method2574(final ha ha) {
        if (Class175.aClass146_1357 == null) {
            final Class178 class178 = new Class178(580, 1104, 1);
            class178.method2601((byte)0, (byte)0, (short)0, (short)0, (short)1024, (short)1024, (short)1024, (byte)123, (short)32767, (byte)0);
            class178.method2599(14418, 0, 128, 0);
            class178.method2599(14418, 0, -128, 0);
            for (int i = 0; i <= 24; ++i) {
                final int n = i * 8192 / 24;
                final int n2 = Class284_Sub2_Sub2.anIntArray6200[n];
                final int n3 = Class284_Sub2_Sub2.anIntArray6202[n];
                for (int j = 1; j < 24; ++j) {
                    final int n4 = j * 8192 / 24;
                    class178.method2599(14418, Class284_Sub2_Sub2.anIntArray6200[n4] * n3 >> 21, Class284_Sub2_Sub2.anIntArray6202[n4] >> 7, -(Class284_Sub2_Sub2.anIntArray6200[n4] * n2 >> 21));
                }
                if (i > 0) {
                    int n5 = i * 23 + 2;
                    int n6 = n5 - 23;
                    class178.method2594((byte)0, (short)127, (short)0, (byte)0, false, n5, 0, (byte)0, n6);
                    for (int k = 1; k < 23; ++k) {
                        final int n7 = n6 + 1;
                        final int n8 = n5 + 1;
                        class178.method2594((byte)0, (short)127, (short)0, (byte)0, false, n5, n6, (byte)0, n7);
                        class178.method2594((byte)0, (short)127, (short)0, (byte)0, false, n5, n7, (byte)0, n8);
                        n6 = n7;
                        n5 = n8;
                    }
                    class178.method2594((byte)0, (short)127, (short)0, (byte)0, false, 1, n5, (byte)0, n6);
                }
            }
            class178.anInt1406 = class178.anInt1407;
            class178.anIntArray1395 = null;
            class178.anIntArray1417 = null;
            class178.aByteArray1402 = null;
            Class175.aClass146_1357 = ha.method1790(class178, 51200, 33, 64, 768);
        }
    }
    
    final boolean method2575(final int n, final int n2, final int n3, final int n4) {
        int anInt1370;
        int anInt1371;
        int anInt1372;
        if (!this.aBoolean1371) {
            final int n5 = this.anInt1370 - n;
            final int n6 = this.anInt1355 - n2;
            final int n7 = this.anInt1364 - n3;
            this.anInt1361 = (int)Math.sqrt(n5 * n5 + n6 * n6 + n7 * n7);
            if (this.anInt1361 == 0) {
                this.anInt1361 = 1;
            }
            anInt1370 = (n5 << 8) / this.anInt1361;
            anInt1371 = (n6 << 8) / this.anInt1361;
            anInt1372 = (n7 << 8) / this.anInt1361;
        }
        else {
            this.anInt1361 = 1073741823;
            anInt1370 = this.anInt1370;
            anInt1371 = this.anInt1355;
            anInt1372 = this.anInt1364;
        }
        final int n8 = (int)(Math.sqrt(anInt1370 * anInt1370 + anInt1371 * anInt1371 + anInt1372 * anInt1372) * 256.0);
        if (n8 > 128) {
            anInt1370 = (anInt1370 << 16) / n8;
            anInt1371 = (anInt1371 << 16) / n8;
            anInt1372 = (anInt1372 << 16) / n8;
            this.anInt1360 = this.anInt1365 * n4 / (this.aBoolean1371 ? 1024 : this.anInt1361);
        }
        else {
            this.anInt1360 = 0;
        }
        if (this.anInt1360 < 8) {
            this.aClass332_1368 = null;
            return false;
        }
        int anInt1373 = Class48.method453(423660257, this.anInt1360);
        if (anInt1373 > n4) {
            anInt1373 = Class23.method282(-123, n4);
        }
        if (anInt1373 > 512) {
            anInt1373 = 512;
        }
        if (anInt1373 != this.anInt1354) {
            this.anInt1354 = anInt1373;
        }
        this.anInt1358 = ((int)(Math.asin(anInt1371 / 256.0f) * 2607.5945876176133) & 0x3FFF);
        this.anInt1362 = ((int)(Math.atan2(anInt1370, -anInt1372) * 2607.5945876176133) & 0x3FFF);
        this.aClass332_1368 = null;
        return true;
    }
    
    private final void method2576(final ha ha, final Class175 class175) {
        method2574(ha);
        method2568(ha);
        ha.K(Class175.anIntArray1367);
        ha.KA(0, 0, this.anInt1354, this.anInt1354);
        ha.ya();
        ha.aa(0, 0, this.anInt1354, this.anInt1354, 0xFF000000 | this.anInt1369, 0);
        int n = 0;
        int n2 = 0;
        int n3 = 256;
        if (class175 != null) {
            if (class175.aBoolean1371) {
                n = -class175.anInt1370;
                n2 = -class175.anInt1355;
                n3 = -class175.anInt1364;
            }
            else {
                n = class175.anInt1370 - this.anInt1370;
                n2 = class175.anInt1355 - this.anInt1355;
                n3 = class175.anInt1364 - this.anInt1364;
            }
        }
        if (this.anInt1358 != 0) {
            final int n4 = Class284_Sub2_Sub2.anIntArray6200[this.anInt1358];
            final int n5 = Class284_Sub2_Sub2.anIntArray6202[this.anInt1358];
            final int n6 = n2 * n5 - n3 * n4 >> 14;
            n3 = n2 * n4 + n3 * n5 >> 14;
            n2 = n6;
        }
        if (this.anInt1362 != 0) {
            final int n7 = Class284_Sub2_Sub2.anIntArray6200[this.anInt1362];
            final int n8 = Class284_Sub2_Sub2.anIntArray6202[this.anInt1362];
            final int n9 = n3 * n7 + n * n8 >> 14;
            n3 = n3 * n8 - n * n7 >> 14;
            n = n9;
        }
        final Class146 method2341 = Class175.aClass146_1357.method2341((byte)0, 51200, true);
        method2341.aa((short)0, (short)this.anInt1366);
        ha.xa(1.0f);
        ha.ZA(16777215, 1.0f, 1.0f, n, n2, n3);
        int n10 = 1024 * this.anInt1354 / (method2341.RA() - method2341.V());
        if (this.anInt1369 != 0) {
            n10 = n10 * 13 / 16;
        }
        ha.DA(this.anInt1354 / 2, this.anInt1354 / 2, n10, n10);
        ha.a(ha.method1821());
        final Class111 method2342 = ha.method1821();
        method2342.method2100(0, 0, ha.i() - method2341.HA());
        method2341.method2329(method2342, null, 1024, 1);
        final int n11 = this.anInt1354 * 13 / 16;
        final int n12 = (this.anInt1354 - n11) / 2;
        Class175.aClass332_1363.method3727(n12, n12, n11, n11, 0, 0xFF000000 | this.anInt1369, 1);
        this.aClass332_1368 = ha.method1797(0, 0, this.anInt1354, this.anInt1354, true);
        ha.ya();
        ha.aa(0, 0, this.anInt1354, this.anInt1354, 0, 0);
        Class175.aClass332_1356.method3727(0, 0, this.anInt1354, this.anInt1354, 1, 0, 0);
        this.aClass332_1368.method3742(0, 0, 0);
        ha.KA(Class175.anIntArray1367[0], Class175.anIntArray1367[1], Class175.anIntArray1367[2], Class175.anIntArray1367[3]);
    }
    
    final boolean method2577(final ha ha, final Class175 class175) {
        return this.aClass332_1368 != null || this.method2569(ha, class175);
    }
    
    static final void method2578() {
        Class175.aClass146_1357 = null;
        Class175.aClass332_1356 = null;
    }
    
    Class175(final int anInt1359, final int anInt1360, final int anInt1361, final int anInt1362, final int anInt1363, final int anInt1364, final int anInt1365, final boolean aBoolean1371) {
        this.anInt1370 = anInt1361;
        this.anInt1355 = anInt1362;
        this.anInt1364 = anInt1363;
        this.aBoolean1371 = aBoolean1371;
        this.anInt1366 = anInt1360;
        this.anInt1369 = anInt1365;
        this.anInt1365 = anInt1364;
        this.anInt1359 = anInt1359;
    }
    
    static {
        Class175.anIntArray1367 = new int[4];
    }
}
