// 
// Decompiled by Procyon v0.5.30
// 

final class Class48_Sub1_Sub1 extends Class48_Sub1
{
    private int anInt5501;
    private int anInt5502;
    static short[][] aShortArrayArray5503;
    private Class42_Sub2 aClass42_Sub2_5504;
    static float aFloat5505;
    private static short[] aShortArray5506;
    private int anInt5507;
    private int anInt5508;
    private int anInt5509;
    private ha_Sub1 aHa_Sub1_5510;
    private int anInt5511;
    private static short[] aShortArray5512;
    private static short[] aShortArray5513;
    
    @Override
    final Class42_Sub2 method456(final byte b) {
        try {
            if (this.aClass42_Sub2_5504 == null) {
                Class63.anIntArray491[2] = this.anInt5501;
                Class63.anIntArray491[0] = this.anInt5509;
                Class63.anIntArray491[3] = this.anInt5502;
                Class63.anIntArray491[1] = this.anInt5511;
                final d ad938 = this.aHa_Sub1_5510.aD938;
                Class63.anIntArray491[5] = this.anInt5507;
                Class63.anIntArray491[4] = this.anInt5508;
                boolean b2 = false;
                int n = 0;
                for (int i = 0; i < 6; ++i) {
                    if (!ad938.method8(-119, Class63.anIntArray491[i])) {
                        return null;
                    }
                    final Class238 method11 = ad938.method11(Class63.anIntArray491[i], -28755);
                    final int n2 = method11.aBoolean1822 ? 64 : 128;
                    if (method11.aByte1832 > 0) {
                        b2 = true;
                    }
                    if (~n2 < ~n) {
                        n = n2;
                    }
                }
                for (int n3 = 0; ~n3 > -7; ++n3) {
                    Class248.anIntArrayArray1895[n3] = ad938.method9(Class63.anIntArray491[n3], (byte)(-126), n, 1.0f, false, n);
                }
                this.aClass42_Sub2_5504 = new Class42_Sub2(this.aHa_Sub1_5510, 6407, n, ~(b2 ? 1 : 0) != -1, Class248.anIntArrayArray1895);
            }
            if (b < 119) {
                Class48_Sub1_Sub1.aShortArray5512 = null;
            }
            return this.aClass42_Sub2_5504;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bja.E(" + b + ')');
        }
    }
    
    public static void method460(final boolean b) {
        try {
            if (!b) {
                Class48_Sub1_Sub1.aShortArray5513 = null;
            }
            Class48_Sub1_Sub1.aShortArray5512 = null;
            Class48_Sub1_Sub1.aShortArray5506 = null;
            Class48_Sub1_Sub1.aShortArray5513 = null;
            Class48_Sub1_Sub1.aShortArrayArray5503 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bja.G(" + b + ')');
        }
    }
    
    static final void method461(int n, final float n2, final int n3, final int n4, final float n5, final int n6, final float n7, final float[] array, final int n8, final float[] array2, final int n9, int n10, final int n11, int n12) {
        try {
            n10 -= n9;
            n -= n4;
            n12 -= n11;
            final float n13 = n10 * array[2] + (n12 * array[0] + n * array[1]);
            if (n6 < 109) {
                method460(false);
            }
            final float n14 = n12 * array[3] + array[4] * n + n10 * array[5];
            final float n15 = array[8] * n10 + (n * array[7] + n12 * array[6]);
            float n16;
            float n17;
            if (n8 != 0) {
                if (~n8 != 0xFFFFFFFE) {
                    if (n8 != 2) {
                        if (~n8 == 0xFFFFFFFC) {
                            n16 = 0.5f + (n13 + n5);
                            n17 = -n14 + n7 + 0.5f;
                        }
                        else if (~n8 != 0xFFFFFFFB) {
                            n16 = n2 + -n15 + 0.5f;
                            n17 = n7 + -n14 + 0.5f;
                        }
                        else {
                            n17 = -n14 + n7 + 0.5f;
                            n16 = 0.5f + (n15 + n2);
                        }
                    }
                    else {
                        n17 = n7 + -n14 + 0.5f;
                        n16 = n5 + -n13 + 0.5f;
                    }
                }
                else {
                    n16 = n13 + n5 + 0.5f;
                    n17 = 0.5f + (n15 + n2);
                }
            }
            else {
                n17 = 0.5f + (-n15 + n2);
                n16 = n13 + n5 + 0.5f;
            }
            if (n3 != 1) {
                if (~n3 == 0xFFFFFFFD) {
                    n16 = -n16;
                    n17 = -n17;
                }
                else if (~n3 == 0xFFFFFFFC) {
                    final float n18 = n16;
                    n16 = n17;
                    n17 = -n18;
                }
            }
            else {
                final float n19 = n16;
                n16 = -n17;
                n17 = n19;
            }
            array2[0] = n16;
            array2[1] = n17;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bja.I(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + ((array != null) ? "{...}" : "null") + ',' + n8 + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n9 + ',' + n10 + ',' + n11 + ',' + n12 + ')');
        }
    }
    
    static final boolean method462(final int n, final int n2) {
        try {
            if (n2 < 125) {
                Class48_Sub1_Sub1.aShortArrayArray5503 = null;
            }
            return ~n == -1 || ~n == 0xFFFFFFFE || ~n == 0xFFFFFFFD;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bja.H(" + n + ',' + n2 + ')');
        }
    }
    
    static final boolean method463(final int n, final String s) {
        try {
            if (n != -1) {
                Class48_Sub1_Sub1.aFloat5505 = 1.2700646f;
            }
            return Class124.aHashtable1015.containsKey(s);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bja.F(" + n + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class48_Sub1_Sub1(final ha_Sub1 aHa_Sub1_5510, final int anInt5509, final int anInt5510, final int anInt5511, final int anInt5512, final int anInt5513, final int anInt5514) {
        try {
            this.anInt5502 = anInt5512;
            this.anInt5511 = anInt5510;
            this.anInt5507 = anInt5514;
            this.anInt5509 = anInt5509;
            this.anInt5508 = anInt5513;
            this.anInt5501 = anInt5511;
            this.aHa_Sub1_5510 = aHa_Sub1_5510;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bja.<init>(" + ((aHa_Sub1_5510 != null) ? "{...}" : "null") + ',' + anInt5509 + ',' + anInt5510 + ',' + anInt5511 + ',' + anInt5512 + ',' + anInt5513 + ',' + anInt5514 + ')');
        }
    }
    
    static {
        Class48_Sub1_Sub1.aShortArray5512 = new short[] { 6798, 8741, 25238, 4626, 4550 };
        Class48_Sub1_Sub1.aShortArray5506 = new short[] { -1, -1, -1, -1, -1 };
        Class48_Sub1_Sub1.aShortArray5513 = new short[] { -1, -1, -1, -1, -1 };
        Class48_Sub1_Sub1.aShortArrayArray5503 = new short[][] { Class48_Sub1_Sub1.aShortArray5512, Class48_Sub1_Sub1.aShortArray5513, Class48_Sub1_Sub1.aShortArray5506 };
    }
}
