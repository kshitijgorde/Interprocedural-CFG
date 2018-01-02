// 
// Decompiled by Procyon v0.5.30
// 

class Class93 implements Interface21
{
    static boolean aBoolean3503;
    int anInt3504;
    int anInt3505;
    int anInt3506;
    int anInt3507;
    int anInt3508;
    Class63 aClass63_3509;
    static int[][] anIntArrayArray3510;
    Class110 aClass110_3511;
    static Class332[] aClass332Array3512;
    int anInt3513;
    int anInt3514;
    
    public static void method899(final int n) {
        try {
            Class93.anIntArrayArray3510 = null;
            if (n > 104) {
                Class93.aClass332Array3512 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fq.K(" + n + ')');
        }
    }
    
    static final void method900(final int n, final byte[][] array, final Class305_Sub1 class305_Sub1) {
        try {
            final int[] array2 = { -1, 0, 0, 0, 0 };
            int i = 0;
            if (n != 11948) {
                Class93.aBoolean3503 = false;
            }
            while (i < class305_Sub1.anInt2547) {
                Class128.method2224(22696);
                for (int j = 0; j < Class165.anInt1276 >> 2130796515; ++j) {
                    for (int n2 = 0; ~(Class98_Sub10_Sub7.anInt5572 >> 2020537603) < ~n2; ++n2) {
                        final int n3 = Class170.anIntArrayArrayArray1311[i][j][n2];
                        if (n3 != -1) {
                            final int n4 = (n3 & 0x3FE0511) >> -79784296;
                            if (!class305_Sub1.aBoolean2544 || ~n4 == -1) {
                                final int n5 = (0x7 & n3) >> 2079355553;
                                final int n6 = (n3 & 0xFFE196) >> -797997746;
                                final int n7 = 0x7FF & n3 >> 1649106819;
                                final int n8 = (n6 / 8 << -1183468824) + n7 / 8;
                                for (int n9 = 0; Class121.anIntArray1006.length > n9; ++n9) {
                                    if (Class121.anIntArray1006[n9] == n8 && array[n9] != null) {
                                        final Class98_Sub22 class98_Sub22 = new Class98_Sub22(array[n9]);
                                        class305_Sub1.method3575(n7, n2 * 8, class98_Sub22, n5, n4, 8 * j, n - 11948, n6, Class167.aClass243Array1281, i);
                                        class305_Sub1.method3586(n6, n7, (int[])((~array2[0] != 0x0) ? null : array2), j * 8, 8 * n2, class98_Sub22, Class265.aHa1974, n5, true, n4, i);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                ++i;
            }
            for (int n10 = 0; class305_Sub1.anInt2547 > n10; ++n10) {
                Class128.method2224(22696);
                for (int n11 = 0; ~(Class165.anInt1276 >> -1820522237) < ~n11; ++n11) {
                    for (int n12 = 0; ~(Class98_Sub10_Sub7.anInt5572 >> 101337859) < ~n12; ++n12) {
                        if (~Class170.anIntArrayArrayArray1311[n10][n11][n12] == 0x0) {
                            class305_Sub1.method3567(8, n10, n11 * 8, 8, 8 * n12, n ^ 0xFFFFD153);
                        }
                    }
                }
            }
            if (~array2[0] != 0x0) {
                s_Sub1.aClass346_5202 = Class373_Sub2.aClass59_5470.method528(0, array2[0], array2[2], array2[1], Class101.aClass115_857, array2[3]);
                Class156_Sub1.anInt3278 = array2[4];
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fq.J(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + ((class305_Sub1 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final boolean method901(final int n, final int n2, final int n3) {
        try {
            return ~(0x800 & n2) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fq.L(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    Class93(final Class63 aClass63_3509, final Class110 aClass110_3511, final int anInt3505, final int anInt3506, final int anInt3507, final int anInt3508, final int anInt3509, final int anInt3510, final int anInt3511) {
        try {
            this.anInt3507 = anInt3506;
            this.anInt3505 = anInt3505;
            this.anInt3504 = anInt3508;
            this.anInt3506 = anInt3510;
            this.anInt3508 = anInt3509;
            this.aClass63_3509 = aClass63_3509;
            this.anInt3514 = anInt3507;
            this.anInt3513 = anInt3511;
            this.aClass110_3511 = aClass110_3511;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fq.<init>(" + ((aClass63_3509 != null) ? "{...}" : "null") + ',' + ((aClass110_3511 != null) ? "{...}" : "null") + ',' + anInt3505 + ',' + anInt3506 + ',' + anInt3507 + ',' + anInt3508 + ',' + anInt3509 + ',' + anInt3510 + ',' + anInt3511 + ')');
        }
    }
    
    @Override
    public Class113 method70(final int n) {
        try {
            if (n != 30778) {
                this.aClass63_3509 = null;
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fq.A(" + n + ')');
        }
    }
    
    static {
        Class93.aBoolean3503 = false;
        Class93.anIntArrayArray3510 = new int[][] { { 0, 1, 2, 3 }, { 1, -1, -1, 0 }, { -1, 2, -1, 0 }, { -1, 0, -1, 2 }, { 0, 1, -1, 2 }, { 1, 2, -1, 0 }, { -1, 4, -1, 1 }, { -1, 3, 4, -1 }, { -1, 0, 2, -1 }, { -1, -1, 2, 0 }, { 0, 2, 5, 3 }, { 0, -1, 6, -1 }, { 0, 1, 2, 3 } };
    }
}
