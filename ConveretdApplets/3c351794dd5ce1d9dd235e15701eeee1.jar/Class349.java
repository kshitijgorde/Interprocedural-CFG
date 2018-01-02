// 
// Decompiled by Procyon v0.5.30
// 

final class Class349
{
    int[] anIntArray2916;
    String[] aStringArray2917;
    Class98_Sub46_Sub4 aClass98_Sub46_Sub4_2918;
    int anInt2919;
    
    Class349() {
        this.anInt2919 = -1;
    }
    
    static final void method3838(final int n, final int n2) {
        try {
            Class292.method3446(3);
            final int anInt1283 = Class134.aClass139_3465.method2282(n, 16).anInt1283;
            if (anInt1283 != 0) {
                final int n3 = Class75.aClass140_584.anIntArray3244[n];
                if (n2 == 1864) {
                    if (~anInt1283 == 0xFFFFFFF9) {
                        Class129.anInt1026 = n3;
                    }
                    if (~anInt1283 == 0xFFFFFFFA) {
                        Class305_Sub1.anInt5303 = n3;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vf.F(" + n + ',' + n2 + ')');
        }
    }
    
    static final float[] method3839(final byte b, final int n, final float n2, final float n3, final int n4, final int n5, final float n6, final int n7) {
        try {
            final float[] array = new float[9];
            float[] array2 = new float[9];
            final float n8 = (float)Math.cos(n * 0.024543693f);
            final float n9 = (float)Math.sin(n * 0.024543693f);
            array[4] = 1.0f;
            array[1] = 0.0f;
            array[8] = (array[0] = n8);
            array[5] = 0.0f;
            array[6] = -(array[2] = n9);
            array[3] = (array[7] = 0.0f);
            final float[] array3 = new float[9];
            float n10 = 1.0f;
            final float n11 = n5 / 32767.0f;
            float n12 = 0.0f;
            final float n13 = -(float)Math.sqrt(-(n11 * n11) + 1.0f);
            final float n14 = 1.0f - n11;
            final float n15 = (float)Math.sqrt(n4 * n4 + n7 * n7);
            Label_0625: {
                if (n15 != 0.0f || n11 != 0.0f) {
                    if (n15 != 0.0f) {
                        n10 = -n4 / n15;
                        n12 = n7 / n15;
                    }
                    array3[7] = n13 * -n10;
                    array3[3] = n13 * -n12;
                    array3[8] = n11 + n14 * (n12 * n12);
                    array3[0] = n11 + n10 * n10 * n14;
                    array3[1] = n12 * n13;
                    array3[2] = (array3[6] = n14 * (n10 * n12));
                    array3[5] = n13 * n10;
                    array3[4] = n11;
                    array2[0] = array[1] * array3[3] + array[0] * array3[0] + array3[6] * array[2];
                    array2[1] = array3[7] * array[2] + (array3[1] * array[0] + array3[4] * array[1]);
                    array2[2] = array[2] * array3[8] + (array3[2] * array[0] + array3[5] * array[1]);
                    array2[3] = array3[6] * array[5] + (array[4] * array3[3] + array[3] * array3[0]);
                    array2[4] = array[5] * array3[7] + (array[4] * array3[4] + array[3] * array3[1]);
                    array2[6] = array[8] * array3[6] + (array3[3] * array[7] + array[6] * array3[0]);
                    array2[5] = array[5] * array3[8] + (array3[2] * array[3] + array[4] * array3[5]);
                    array2[7] = array[6] * array3[1] + array[7] * array3[4] + array[8] * array3[7];
                    array2[8] = array3[8] * array[8] + (array3[2] * array[6] + array3[5] * array[7]);
                    if (!client.aBoolean3553) {
                        break Label_0625;
                    }
                }
                array2 = array;
            }
            final float[] array4 = array2;
            final int n16 = 5;
            array4[n16] *= n3;
            final float[] array5 = array2;
            final int n17 = 6;
            array5[n17] *= n6;
            final float[] array6 = array2;
            final int n18 = 1;
            array6[n18] *= n2;
            final float[] array7 = array2;
            final int n19 = 3;
            array7[n19] *= n3;
            final float[] array8 = array2;
            final int n20 = 7;
            array8[n20] *= n6;
            final float[] array9 = array2;
            final int n21 = 2;
            array9[n21] *= n2;
            final float[] array10 = array2;
            final int n22 = 0;
            array10[n22] *= n2;
            final float[] array11 = array2;
            final int n23 = 4;
            array11[n23] *= n3;
            final float[] array12 = array2;
            final int n24 = 8;
            array12[n24] *= n6;
            return array2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vf.A(" + b + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    static final void method3840(final byte b, final Class246_Sub3 class246_Sub3, final int n, final Class97 class97) {
        try {
            if (~Class306.anInt2566 > -51 && class97 != null && class97.anIntArrayArray822 != null && ~class97.anIntArrayArray822.length < ~n && class97.anIntArrayArray822[n] != null) {
                final int n2 = class97.anIntArrayArray822[n][0];
                int n3 = n2 >> -2042241208;
                final int n4 = (n2 & 0xEC) >> 515939717;
                final int n5 = n2 & 0x1F;
                if (~class97.anIntArrayArray822[n].length < -2) {
                    final int n6 = (int)(Math.random() * class97.anIntArrayArray822[n].length);
                    if (~n6 < -1) {
                        n3 = class97.anIntArrayArray822[n][n6];
                    }
                }
                int n7 = 256;
                if (class97.anIntArray810 != null && class97.anIntArray815 != null) {
                    n7 = class97.anIntArray810[n] + (int)(Math.random() * (class97.anIntArray815[n] - class97.anIntArray810[n]));
                }
                final int n8 = (class97.anIntArray808 == null) ? 255 : class97.anIntArray808[n];
                if (~n5 == -1) {
                    if (class246_Sub3 == Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660) {
                        if (!class97.aBoolean812) {
                            Class301.method3537(n7, (byte)1, n3, n4, 0, n8);
                        }
                        else {
                            Class98_Sub10_Sub9.method1036(-1962608884, n8, n4, n3, false, 0, n7);
                        }
                    }
                }
                else if (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4051.method641((byte)126) != -1) {
                    final int n9 = class246_Sub3.anInt5084 - 256 >> -1601312791;
                    if (b < -125) {
                        final int n10 = class246_Sub3.anInt5079 - 256 >> -1198437175;
                        Class245.aClass338Array1865[Class306.anInt2566++] = new Class338((byte)(class97.aBoolean812 ? 2 : 1), n3, n4, 0, n8, (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660 != class246_Sub3) ? (n5 + (n9 << -746666992) + ((class246_Sub3.aByte5088 << -1286442696) + (n10 << -86127768))) : 0, n7, class246_Sub3);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vf.C(" + b + ',' + ((class246_Sub3 != null) ? "{...}" : "null") + ',' + n + ',' + ((class97 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method3841(final boolean b, final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            if (n2 == 3113) {
                final long n6 = (b ? Integer.MIN_VALUE : 0) | n3;
                Class98_Sub3 class98_Sub3 = (Class98_Sub3)Class142.aClass377_1157.method3990(n6, -1);
                if (class98_Sub3 == null) {
                    class98_Sub3 = new Class98_Sub3();
                    Class142.aClass377_1157.method3996(class98_Sub3, n6, -1);
                }
                if (class98_Sub3.anIntArray3824.length <= n5) {
                    final int[] anIntArray3824 = new int[n5 + 1];
                    final int[] anIntArray3825 = new int[1 + n5];
                    for (int n7 = 0; class98_Sub3.anIntArray3824.length > n7; ++n7) {
                        anIntArray3824[n7] = class98_Sub3.anIntArray3824[n7];
                        anIntArray3825[n7] = class98_Sub3.anIntArray3823[n7];
                    }
                    for (int i = class98_Sub3.anIntArray3824.length; i < n5; ++i) {
                        anIntArray3824[i] = -1;
                        anIntArray3825[i] = 0;
                    }
                    class98_Sub3.anIntArray3824 = anIntArray3824;
                    class98_Sub3.anIntArray3823 = anIntArray3825;
                }
                class98_Sub3.anIntArray3824[n5] = n4;
                class98_Sub3.anIntArray3823[n5] = n;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vf.E(" + b + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    static final boolean method3842(final int n, final int n2, final int n3) {
        try {
            return n3 == -18021 && (0x84080 & n2) != 0x0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vf.D(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final byte method3843(final byte b, final char c) {
        try {
            if (b != 88) {
                method3841(false, -74, -7, 18, 107, -123);
            }
            byte b2;
            if ((c <= '\0' || ~c <= -129) && (c < ' ' || ~c < -256)) {
                if (c != '\u20ac') {
                    if (~c == 0xFFFFDFE5) {
                        b2 = -126;
                    }
                    else if (c != '\u0192') {
                        if (~c == 0xFFFFDFE1) {
                            b2 = -124;
                        }
                        else if (~c == 0xFFFFDFD9) {
                            b2 = -123;
                        }
                        else if (c == '\u2020') {
                            b2 = -122;
                        }
                        else if (~c == 0xFFFFDFDE) {
                            b2 = -121;
                        }
                        else if (c == '\u02c6') {
                            b2 = -120;
                        }
                        else if (c != '\u2030') {
                            if (c == '\u0160') {
                                b2 = -118;
                            }
                            else if (~c == 0xFFFFDFC6) {
                                b2 = -117;
                            }
                            else if (~c == 0xFFFFFEAD) {
                                b2 = -116;
                            }
                            else if (~c == 0xFFFFFE82) {
                                b2 = -114;
                            }
                            else if (~c != 0xFFFFDFE7) {
                                if (c != '\u2019') {
                                    if (c == '\u201c') {
                                        b2 = -109;
                                    }
                                    else if (~c != 0xFFFFDFE2) {
                                        if (~c == 0xFFFFDFDD) {
                                            b2 = -107;
                                        }
                                        else if (c == '\u2013') {
                                            b2 = -106;
                                        }
                                        else if (c == '\u2014') {
                                            b2 = -105;
                                        }
                                        else if (c != '\u02dc') {
                                            if (~c != 0xFFFFDEDD) {
                                                if (c != '\u0161') {
                                                    if (c != '\u203a') {
                                                        if (~c != 0xFFFFFEAC) {
                                                            if (~c != 0xFFFFFE81) {
                                                                if (c != '\u0178') {
                                                                    b2 = 63;
                                                                }
                                                                else {
                                                                    b2 = -97;
                                                                }
                                                            }
                                                            else {
                                                                b2 = -98;
                                                            }
                                                        }
                                                        else {
                                                            b2 = -100;
                                                        }
                                                    }
                                                    else {
                                                        b2 = -101;
                                                    }
                                                }
                                                else {
                                                    b2 = -102;
                                                }
                                            }
                                            else {
                                                b2 = -103;
                                            }
                                        }
                                        else {
                                            b2 = -104;
                                        }
                                    }
                                    else {
                                        b2 = -108;
                                    }
                                }
                                else {
                                    b2 = -110;
                                }
                            }
                            else {
                                b2 = -111;
                            }
                        }
                        else {
                            b2 = -119;
                        }
                    }
                    else {
                        b2 = -125;
                    }
                }
                else {
                    b2 = -128;
                }
            }
            else {
                b2 = (byte)c;
            }
            return b2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vf.B(" + b + ',' + c + ')');
        }
    }
}
