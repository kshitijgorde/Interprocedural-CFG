// 
// Decompiled by Procyon v0.5.30
// 

final class Class314
{
    private int anInt2686;
    private int[][] anIntArrayArray2687;
    static Class110 aClass110_2688;
    private int anInt2689;
    static int anInt2690;
    static int[] anIntArray2691;
    static int anInt2692;
    
    static final int method3637(final int n, final int n2) {
        try {
            if (n != -14982) {
                Class314.aClass110_2688 = null;
            }
            return 0xFF & n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tg.A(" + n + ',' + n2 + ')');
        }
    }
    
    final int method3638(final int n, int n2) {
        try {
            if (this.anIntArrayArray2687 != null) {
                n2 = 6 + n2 * this.anInt2686 / this.anInt2689;
            }
            if (n != 6) {
                this.method3642(false, null);
            }
            return n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tg.E(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method3639(final int n, final String s, final boolean b) {
        try {
            final int i = Class2.anInt71;
            final int[] anIntArray2705 = Class319.anIntArray2705;
            boolean b2 = b;
            int n2 = 0;
            while (i > n2) {
                final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[anIntArray2705[n2]];
                if (class246_Sub3_Sub4_Sub2_Sub2 != null && Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660 != class246_Sub3_Sub4_Sub2_Sub2 && class246_Sub3_Sub4_Sub2_Sub2.aString6537 != null && class246_Sub3_Sub4_Sub2_Sub2.aString6537.equalsIgnoreCase(s)) {
                    b2 = true;
                    if (n == 1) {
                        ++Class98_Sub46_Sub16.anInt6044;
                        final Class98_Sub11 method3023 = Class246_Sub3_Sub4.method3023(260, Class64_Sub8.aClass171_3661, Class331.aClass117_2811);
                        method3023.aClass98_Sub22_Sub1_3865.writeLEShort(anIntArray2705[n2], 17624);
                        method3023.aClass98_Sub22_Sub1_3865.writeByteS(0, -65);
                        Class98_Sub10_Sub29.sendPacket(false, method3023);
                        break;
                    }
                    if (~n == 0xFFFFFFFB) {
                        ++Class65.anInt498;
                        final Class98_Sub11 method3024 = Class246_Sub3_Sub4.method3023(260, Class302.aClass171_2520, Class331.aClass117_2811);
                        method3024.aClass98_Sub22_Sub1_3865.writeByteS(0, -24);
                        method3024.aClass98_Sub22_Sub1_3865.writeShort(anIntArray2705[n2], 1571862888);
                        Class98_Sub10_Sub29.sendPacket(false, method3024);
                        break;
                    }
                    if (~n == 0xFFFFFFFA) {
                        ++Class98_Sub23.anInt4001;
                        final Class98_Sub11 method3025 = Class246_Sub3_Sub4.method3023(260, Class121.aClass171_1001, Class331.aClass117_2811);
                        method3025.aClass98_Sub22_Sub1_3865.writeLEShortA(anIntArray2705[n2], 128);
                        method3025.aClass98_Sub22_Sub1_3865.method1231(0, (byte)110);
                        Class98_Sub10_Sub29.sendPacket(false, method3025);
                        break;
                    }
                    if (n == 6) {
                        ++Class98_Sub43.anInt4242;
                        final Class98_Sub11 method3026 = Class246_Sub3_Sub4.method3023(260, OutputStream_Sub1.aClass171_34, Class331.aClass117_2811);
                        method3026.aClass98_Sub22_Sub1_3865.method1244(0, (byte)112);
                        method3026.aClass98_Sub22_Sub1_3865.writeShort(anIntArray2705[n2], 1571862888);
                        Class98_Sub10_Sub29.sendPacket(false, method3026);
                        break;
                    }
                    if (~n == 0xFFFFFFF8) {
                        ++Class366.anInt3111;
                        final Class98_Sub11 method3027 = Class246_Sub3_Sub4.method3023(260, Class277.aClass171_2051, Class331.aClass117_2811);
                        method3027.aClass98_Sub22_Sub1_3865.writeByteS(0, -105);
                        method3027.aClass98_Sub22_Sub1_3865.writeShort(anIntArray2705[n2], 1571862888);
                        Class98_Sub10_Sub29.sendPacket(false, method3027);
                        break;
                    }
                    break;
                }
                else {
                    ++n2;
                }
            }
            if (!b2) {
                za_Sub2.method1684(Class309.aClass309_2607.method3615(Class374.anInt3159, (byte)25) + s, 4, (byte)57);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tg.D(" + n + ',' + ((s != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public static void method3640(final int n) {
        try {
            Class314.anIntArray2691 = null;
            Class314.aClass110_2688 = null;
            if (n != 0) {
                Class314.anInt2690 = -4;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tg.B(" + n + ')');
        }
    }
    
    final short[] method3641(final int n, short[] array) {
        try {
            if (n != 6) {
                Class314.aClass110_2688 = null;
            }
            if (this.anIntArrayArray2687 != null) {
                final int n2 = this.anInt2686 * array.length / this.anInt2689 + 14;
                final int[] array2 = new int[n2];
                int n3 = 0;
                int n4 = 0;
                for (int i = 0; i < array.length; ++i) {
                    final short n5 = array[i];
                    final int[] array3 = this.anIntArrayArray2687[n4];
                    for (int n6 = 0; ~n6 > -15; ++n6) {
                        final int[] array4 = array2;
                        final int n7 = n3 - -n6;
                        array4[n7] += array3[n6] * n5 >> 1476810210;
                    }
                    final int n8 = n4 + this.anInt2686;
                    final int n9 = n8 / this.anInt2689;
                    n3 += n9;
                    n4 = n8 - n9 * this.anInt2689;
                }
                array = new short[n2];
                for (int j = 0; j < n2; ++j) {
                    final int n10 = 8192 + array2[j] >> -2136199730;
                    if (n10 >= -32768) {
                        if (n10 <= 32767) {
                            array[j] = (short)n10;
                        }
                        else {
                            array[j] = 32767;
                        }
                    }
                    else {
                        array[j] = -32768;
                    }
                }
            }
            return array;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tg.G(" + n + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    final byte[] method3642(final boolean b, byte[] array) {
        try {
            if (!b) {
                this.method3641(-23, null);
            }
            if (this.anIntArrayArray2687 != null) {
                final int n = array.length * this.anInt2686 / this.anInt2689 + 14;
                final int[] array2 = new int[n];
                int n2 = 0;
                int n3 = 0;
                for (int n4 = 0; ~array.length < ~n4; ++n4) {
                    final byte b2 = array[n4];
                    final int[] array3 = this.anIntArrayArray2687[n3];
                    for (int i = 0; i < 14; ++i) {
                        final int[] array4 = array2;
                        final int n5 = n2 + i;
                        array4[n5] += array3[i] * b2;
                    }
                    final int n6 = n3 + this.anInt2686;
                    final int n7 = n6 / this.anInt2689;
                    n2 += n7;
                    n3 = n6 - this.anInt2689 * n7;
                }
                array = new byte[n];
                for (int n8 = 0; ~n < ~n8; ++n8) {
                    final int n9 = 32768 + array2[n8] >> -107504688;
                    if (~n9 <= 127) {
                        if (n9 <= 127) {
                            array[n8] = (byte)n9;
                        }
                        else {
                            array[n8] = 127;
                        }
                    }
                    else {
                        array[n8] = -128;
                    }
                }
            }
            return array;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tg.C(" + b + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    final int method3643(int n, final int n2) {
        try {
            if (n2 != 1) {
                this.anInt2686 = -77;
            }
            if (this.anIntArrayArray2687 != null) {
                n = n * this.anInt2686 / this.anInt2689;
            }
            return n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tg.H(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method3644(final int n, final int n2) {
        r fa = null;
        for (int i = n; i < n2; ++i) {
            final s s = Class98_Sub46_Sub2_Sub2.aSArray6298[i];
            if (s != null) {
                for (int j = 0; j < Class64_Sub9.anInt3662; ++j) {
                    for (int k = 0; k < Class366.anInt3112; ++k) {
                        fa = s.fa(k, j, fa);
                        if (fa != null) {
                            final int n3 = k << Class151_Sub8.anInt5015;
                            final int n4 = j << Class151_Sub8.anInt5015;
                            for (int l = i - 1; l >= 0; --l) {
                                final s s2 = Class98_Sub46_Sub2_Sub2.aSArray6298[l];
                                if (s2 != null) {
                                    s2.CA(fa, n3, (s.method3420(j, -12639, k) - s2.method3420(j, -12639, k) + (s.method3420(j, -12639, k + 1) - s2.method3420(j, -12639, k + 1)) + (s.method3420(j + 1, -12639, k + 1) - s2.method3420(j + 1, -12639, k + 1)) + (s.method3420(j + 1, -12639, k) - s2.method3420(j + 1, -12639, k))) / 4, n4, 0, false);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    Class314(int anInt2689, int anInt2690) {
        try {
            if (~anInt2689 != ~anInt2690) {
                final int method2216 = Class126.method2216(anInt2689, 111, anInt2690);
                anInt2690 /= method2216;
                anInt2689 /= method2216;
                this.anInt2686 = anInt2690;
                this.anIntArrayArray2687 = new int[anInt2689][14];
                this.anInt2689 = anInt2689;
                for (int i = 0; i < anInt2689; ++i) {
                    final int[] array = this.anIntArrayArray2687[i];
                    final double n = 6.0 + i / anInt2689;
                    int n2 = (int)Math.floor(n - 7.0 + 1.0);
                    if (n2 < 0) {
                        n2 = 0;
                    }
                    int n3 = (int)Math.ceil(7.0 + n);
                    if (~n3 < -15) {
                        n3 = 14;
                    }
                    final double n4 = anInt2690 / anInt2689;
                    while (~n2 > ~n3) {
                        final double n5 = (-n + n2) * 3.141592653589793;
                        double n6 = n4;
                        if (n5 < -1.0E-4 || n5 > 1.0E-4) {
                            n6 *= Math.sin(n5) / n5;
                        }
                        array[n2] = (int)Math.floor(0.5 + n6 * (Math.cos(0.2243994752564138 * (n2 - n)) * 0.46 + 0.54) * 65536.0);
                        ++n2;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tg.<init>(" + anInt2689 + ',' + anInt2690 + ')');
        }
    }
    
    static {
        Class314.aClass110_2688 = new Class110();
        Class314.anInt2690 = -1;
        Class314.anIntArray2691 = new int[3];
        Class314.anInt2692 = 0;
    }
}
