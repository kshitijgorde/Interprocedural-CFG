// 
// Decompiled by Procyon v0.5.30
// 

final class s_Sub3 extends s
{
    private float aFloat5255;
    private int anInt5256;
    private byte[][] aByteArrayArray5257;
    private Class285[][] aClass285ArrayArray5258;
    private byte[][] aByteArrayArray5259;
    private float aFloat5260;
    private Class328[][] aClass328ArrayArray5261;
    private int anInt5262;
    private float aFloat5263;
    private float aFloat5264;
    private Class327[][] aClass327ArrayArray5265;
    private Class120[][] aClass120ArrayArray5266;
    private float aFloat5267;
    private float aFloat5268;
    private ha_Sub2 aHa_Sub2_5269;
    private float aFloat5270;
    private float aFloat5271;
    private Class193[][] aClass193ArrayArray5272;
    private float aFloat5273;
    private float aFloat5274;
    private float aFloat5275;
    private float aFloat5276;
    
    @Override
    final void ka(final int n, final int n2, final int n3) {
        if (this.aByteArrayArray5259[n][n2] < n3) {
            this.aByteArrayArray5259[n][n2] = (byte)n3;
        }
    }
    
    @Override
    final void method3422(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final boolean[][] array) {
        final Class235 method1921 = this.aHa_Sub2_5269.method1921(Thread.currentThread());
        final Class12 aClass12_1767 = method1921.aClass12_1767;
        aClass12_1767.anInt137 = 0;
        aClass12_1767.aBoolean135 = true;
        this.aHa_Sub2_5269.ya();
        if (this.aClass193ArrayArray5272 != null || this.aClass120ArrayArray5266 != null) {
            this.method3444(n, n2, n3, n4, n5, n6, n7, array, method1921, aClass12_1767, method1921.anIntArray1795, method1921.anIntArray1788);
        }
        else if (this.aClass327ArrayArray5265 != null) {
            this.method3437(n, n2, n3, n4, n5, n6, n7, array, method1921, aClass12_1767, method1921.anIntArray1795, method1921.anIntArray1788);
        }
    }
    
    s_Sub3(final ha_Sub2 aHa_Sub2_5269, final int n, final int anInt5256, final int n2, final int n3, final int[][] array, final int[][] array2, final int n4) {
        super(n2, n3, n4, array);
        this.anInt5262 = -1;
        this.aHa_Sub2_5269 = aHa_Sub2_5269;
        this.anInt5256 = anInt5256;
        this.aByteArrayArray5257 = new byte[n2 + 1][n3 + 1];
        final int n5 = this.aHa_Sub2_5269.anInt4516 >> 9;
        for (int i = 1; i < n3; ++i) {
            for (int j = 1; j < n2; ++j) {
                final int n6 = n5;
                final int n7 = array2[j + 1][i] - array2[j - 1][i];
                final int n8 = array2[j][i + 1] - array2[j][i - 1];
                final int n9 = (int)Math.sqrt(n7 * n7 + 512 * n4 + n8 * n8);
                int n10 = n6 + (this.aHa_Sub2_5269.anInt4481 * ((n7 << 8) / n9) + this.aHa_Sub2_5269.anInt4515 * (-512 * n4 / n9) + this.aHa_Sub2_5269.anInt4500 * ((n8 << 8) / n9) >> 17) >> 1;
                if (n10 < 2) {
                    n10 = 2;
                }
                else if (n10 > 126) {
                    n10 = 126;
                }
                this.aByteArrayArray5257[j][i] = (byte)n10;
            }
        }
        this.aByteArrayArray5259 = new byte[n2 + 1][n3 + 1];
    }
    
    @Override
    final r fa(final int n, final int n2, final r r) {
        return null;
    }
    
    private final void method3437(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final boolean[][] array, final Class235 class235, final Class12 class236, final int[] array2, final int[] array3) {
        final int n8 = (n7 - n5) * n3 / 256;
        final int n9 = n3 >> 8;
        final boolean aBoolean1762 = class235.aBoolean1762;
        this.aHa_Sub2_5269.C(false);
        class236.aBoolean134 = false;
        class236.aBoolean130 = false;
        int n10 = n;
        int n11 = n2 + n8;
        for (int i = n4; i < n6; ++i) {
            for (int j = n5; j < n7; ++j) {
                if (array[i - n4][j - n5]) {
                    if (this.aClass327ArrayArray5265[i][j] != null) {
                        final Class327 class237 = this.aClass327ArrayArray5265[i][j];
                        if (class237.aShort2744 != -1 && (class237.aByte2740 & 0x2) == 0x0 && class237.anInt2743 == -1) {
                            final int method1926 = this.aHa_Sub2_5269.method1926(class237.aShort2744);
                            class236.method216(n11 - n9, n11 - n9, n11, n10 + n9, n10, n10 + n9, 100.0f, 100.0f, 100.0f, Class246_Sub7.method3132(class237.aShort2745 & 0xFFFF, (byte)118, method1926), Class246_Sub7.method3132(class237.aShort2742 & 0xFFFF, (byte)118, method1926), Class246_Sub7.method3132(class237.aShort2739 & 0xFFFF, (byte)118, method1926));
                            class236.method216(n11, n11, n11 - n9, n10, n10 + n9, n10, 100.0f, 100.0f, 100.0f, Class246_Sub7.method3132(class237.aShort2741 & 0xFFFF, (byte)118, method1926), Class246_Sub7.method3132(class237.aShort2739 & 0xFFFF, (byte)118, method1926), Class246_Sub7.method3132(class237.aShort2742 & 0xFFFF, (byte)118, method1926));
                        }
                        else if (class237.anInt2743 == -1) {
                            class236.method216(n11 - n9, n11 - n9, n11, n10 + n9, n10, n10 + n9, 100.0f, 100.0f, 100.0f, class237.aShort2745 & 0xFFFF, class237.aShort2742 & 0xFFFF, class237.aShort2739 & 0xFFFF);
                            class236.method216(n11, n11, n11 - n9, n10, n10 + n9, n10, 100.0f, 100.0f, 100.0f, class237.aShort2741 & 0xFFFF, class237.aShort2739 & 0xFFFF, class237.aShort2742 & 0xFFFF);
                        }
                        else {
                            final int anInt2743 = class237.anInt2743;
                            class236.method216(n11 - n9, n11 - n9, n11, n10 + n9, n10, n10 + n9, 100.0f, 100.0f, 100.0f, anInt2743, anInt2743, anInt2743);
                            class236.method216(n11, n11, n11 - n9, n10, n10 + n9, n10, 100.0f, 100.0f, 100.0f, anInt2743, anInt2743, anInt2743);
                        }
                    }
                    else if (this.aClass328ArrayArray5261[i][j] != null) {
                        final Class328 class238 = this.aClass328ArrayArray5261[i][j];
                        for (short n12 = 0; n12 < class238.aShort2748; ++n12) {
                            array2[n12] = n10 + class238.aShortArray2755[n12] * n9 / super.anInt2206;
                            array3[n12] = n11 - class238.aShortArray2757[n12] * n9 / super.anInt2206;
                        }
                        for (short n13 = 0; n13 < class238.aShort2746; ++n13) {
                            final short n14 = class238.aShortArray2758[n13];
                            final short n15 = class238.aShortArray2751[n13];
                            final short n16 = class238.aShortArray2759[n13];
                            final int n17 = array2[n14];
                            final int n18 = array2[n15];
                            final int n19 = array2[n16];
                            final int n20 = array3[n14];
                            final int n21 = array3[n15];
                            final int n22 = array3[n16];
                            if (class238.anIntArray2756 != null && class238.anIntArray2756[n13] != -1) {
                                final int n23 = class238.anIntArray2756[n13];
                                class236.method216(n20, n21, n22, n17, n18, n19, 100.0f, 100.0f, 100.0f, Class246_Sub7.method3132(class238.aShortArray2750[n14], (byte)118, n23), Class246_Sub7.method3132(class238.aShortArray2750[n15], (byte)118, n23), Class246_Sub7.method3132(class238.aShortArray2750[n16], (byte)118, n23));
                            }
                            else if (class238.aShortArray2752 != null && class238.aShortArray2752[n13] != -1) {
                                final int method1927 = this.aHa_Sub2_5269.method1926(class238.aShortArray2752[n13]);
                                class236.method216(n20, n21, n22, n17, n18, n19, 100.0f, 100.0f, 100.0f, Class246_Sub7.method3132(class238.aShortArray2750[n14], (byte)118, method1927), Class246_Sub7.method3132(class238.aShortArray2750[n15], (byte)118, method1927), Class246_Sub7.method3132(class238.aShortArray2750[n16], (byte)118, method1927));
                            }
                            else {
                                final int n24 = class238.anIntArray2749[n13];
                                class236.method216(n20, n21, n22, n17, n18, n19, 100.0f, 100.0f, 100.0f, Class246_Sub7.method3132(class238.aShortArray2750[n14], (byte)118, n24), Class246_Sub7.method3132(class238.aShortArray2750[n15], (byte)118, n24), Class246_Sub7.method3132(class238.aShortArray2750[n16], (byte)118, n24));
                            }
                        }
                    }
                }
                n11 -= n9;
            }
            n11 = n2 + n8;
            n10 += n9;
        }
        class236.aBoolean134 = true;
        this.aHa_Sub2_5269.C(aBoolean1762);
    }
    
    @Override
    final void method3421(final Class98_Sub5 class98_Sub5, final int[] array) {
    }
    
    private final void method3438(final int n, final int n2, final boolean b, final Class235 class235, final Class12 class236, final int[] array, final int[] array2, final int[] array3, final int[] array4, final int n3) {
        final Class120 class237 = this.aClass120ArrayArray5266[n][n2];
        if ((n3 == 0 || (n3 & 0x2) == 0x0) && class237 != null) {
            if (this.anInt5262 == -1) {
                for (short n4 = 0; n4 < class237.aShort994; ++n4) {
                    final int n5 = class237.aShortArray995[n4] + (n << super.anInt2200);
                    int n6 = class237.aShortArray996[n4];
                    final int n7 = class237.aShortArray992[n4] + (n2 << super.anInt2200);
                    final float n8 = this.aFloat5274 + (this.aFloat5260 * n5 + this.aFloat5270 * n6 + this.aFloat5263 * n7);
                    if (n8 <= this.aHa_Sub2_5269.anInt4502) {
                        return;
                    }
                    array4[n4] = 0;
                    if (b) {
                        int n9 = (int)(n8 - class235.anInt1761);
                        if (n9 > 255) {
                            n9 = 255;
                        }
                        if (n9 > 0) {
                            array4[n4] = n9;
                            final short n10 = (short)(class237.aShortArray993[n4] * n9 / 255);
                            if (n10 > 0) {
                                n6 -= n10;
                            }
                        }
                    }
                    else if (class235.aBoolean1758) {
                        final int n11 = (int)(n8 - class235.anInt1761);
                        if (n11 > 0) {
                            array4[n4] = n11;
                            if (array4[n4] > 255) {
                                array4[n4] = 255;
                            }
                        }
                    }
                    final float n12 = this.aFloat5268 + (this.aFloat5275 * n5 + this.aFloat5271 * n6 + this.aFloat5276 * n7);
                    final float n13 = this.aFloat5255 + (this.aFloat5264 * n5 + this.aFloat5273 * n6 + this.aFloat5267 * n7);
                    array[n4] = class236.anInt136 + (int)(n12 * this.aHa_Sub2_5269.anInt4514 / n8);
                    array2[n4] = class236.anInt128 + (int)(n13 * this.aHa_Sub2_5269.anInt4490 / n8);
                    array3[n4] = (int)n8;
                }
            }
            else {
                for (short n14 = 0; n14 < class237.aShort994; ++n14) {
                    final int n15 = class237.aShortArray995[n14] + (n << super.anInt2200);
                    int n16 = class237.aShortArray996[n14];
                    final int n17 = class237.aShortArray992[n14] + (n2 << super.anInt2200);
                    final float n18 = this.aFloat5274 + (this.aFloat5260 * n15 + this.aFloat5270 * n16 + this.aFloat5263 * n17);
                    array4[n14] = 0;
                    if (b) {
                        int n19 = this.anInt5262 - class235.anInt1761;
                        if (n19 > 255) {
                            n19 = 255;
                        }
                        if (n19 > 0) {
                            array4[n14] = n19;
                            final short n20 = (short)(class237.aShortArray993[n14] * n19 / 255);
                            if (n20 > 0) {
                                n16 -= n20;
                            }
                        }
                    }
                    else if (class235.aBoolean1758) {
                        final int n21 = this.anInt5262 - class235.anInt1761;
                        if (n21 > 0) {
                            array4[n14] = n21;
                            if (array4[n14] > 255) {
                                array4[n14] = 255;
                            }
                        }
                    }
                    final float n22 = this.aFloat5268 + (this.aFloat5275 * n15 + this.aFloat5271 * n16 + this.aFloat5276 * n17);
                    final float n23 = this.aFloat5255 + (this.aFloat5264 * n15 + this.aFloat5273 * n16 + this.aFloat5267 * n17);
                    array[n14] = class236.anInt136 + (int)(n22 * this.aHa_Sub2_5269.anInt4514 / this.anInt5262);
                    array2[n14] = class236.anInt128 + (int)(n23 * this.aHa_Sub2_5269.anInt4490 / this.anInt5262);
                    array3[n14] = (int)n18;
                }
            }
            final float n24 = super.anInt2206;
            for (short n25 = 0; n25 < class237.aShort991; ++n25) {
                final short n26 = (short)(n25 * 3);
                final short n27 = (short)(n26 + 1);
                final short n28 = (short)(n27 + 1);
                final int n29 = array[n26];
                final int n30 = array[n27];
                final int n31 = array[n28];
                final int n32 = array2[n26];
                final int n33 = array2[n27];
                final int n34 = array2[n28];
                if ((n29 - n30) * (n34 - n33) - (n32 - n33) * (n31 - n30) > 0) {
                    class236.aBoolean135 = (n29 < 0 || n30 < 0 || n31 < 0 || n29 > class236.anInt141 || n30 > class236.anInt141 || n31 > class236.anInt141);
                    if (array4[n26] + array4[n27] + array4[n28] < 765) {
                        final int n35 = n << super.anInt2200;
                        final int n36 = n2 << super.anInt2200;
                        if ((class237.anIntArray999[n26] & 0xFFFFFF) != 0x0) {
                            if (class237.aShortArray998[n26] == class237.aShortArray998[n27] && class237.aShortArray998[n26] == class237.aShortArray998[n28] && class237.aShortArray990[n26] == class237.aShortArray990[n27] && class237.aShortArray990[n26] == class237.aShortArray990[n28]) {
                                class236.method212(n32, n33, n34, n29, n30, n31, array3[n26], array3[n27], array3[n28], (n35 + class237.aShortArray995[n26]) / class237.aShortArray990[n26], (n35 + class237.aShortArray995[n27]) / class237.aShortArray990[n27], (n35 + class237.aShortArray995[n28]) / class237.aShortArray990[n28], (n36 + class237.aShortArray992[n26]) / class237.aShortArray990[n26], (n36 + class237.aShortArray992[n27]) / class237.aShortArray990[n27], (n36 + class237.aShortArray992[n28]) / class237.aShortArray990[n28], class237.anIntArray999[n26], class237.anIntArray999[n27], class237.anIntArray999[n28], class235.anInt1763, array4[n26], array4[n27], array4[n28], class237.aShortArray998[n26]);
                            }
                            else {
                                class236.method209(n32, n33, n34, n29, n30, n31, array3[n26], array3[n27], array3[n28], (n35 + class237.aShortArray995[n26]) / n24, (n35 + class237.aShortArray995[n27]) / n24, (n35 + class237.aShortArray995[n28]) / n24, (n36 + class237.aShortArray992[n26]) / n24, (n36 + class237.aShortArray992[n27]) / n24, (n36 + class237.aShortArray992[n28]) / n24, class237.anIntArray999[n26], class237.anIntArray999[n27], class237.anIntArray999[n28], class235.anInt1763, array4[n26], array4[n27], array4[n28], class237.aShortArray998[n26], n24 / class237.aShortArray990[n26], class237.aShortArray998[n27], n24 / class237.aShortArray990[n27], class237.aShortArray998[n28], n24 / class237.aShortArray990[n28]);
                            }
                        }
                    }
                    else {
                        class236.method208(n32, n33, n34, n29, n30, n31, array3[n26], array3[n27], array3[n28], class235.anInt1763);
                    }
                }
            }
        }
    }
    
    private static final int method3439(final int n, final int n2) {
        int n3 = (n & 0xFF0000) * n2 >> 23;
        if (n3 < 2) {
            n3 = 2;
        }
        else if (n3 > 253) {
            n3 = 253;
        }
        int n4 = (n & 0xFF00) * n2 >> 15;
        if (n4 < 2) {
            n4 = 2;
        }
        else if (n4 > 253) {
            n4 = 253;
        }
        int n5 = (n & 0xFF) * n2 >> 7;
        if (n5 < 2) {
            n5 = 2;
        }
        else if (n5 > 253) {
            n5 = 253;
        }
        return n3 << 16 | n4 << 8 | n5;
    }
    
    @Override
    final void YA() {
        this.aByteArrayArray5257 = null;
        this.aByteArrayArray5259 = null;
    }
    
    private final void method3440(final int n, final int n2, final Class12 class12, final int[] array, final int[] array2, final int[] array3, final int[] array4, final int n3) {
        final Class327 class13 = this.aClass327ArrayArray5265[n][n2];
        if (class13 != null) {
            if ((class13.aByte2740 & 0x2) == 0x0) {
                if (n3 != 0) {
                    if ((class13.aByte2740 & 0x4) != 0x0) {
                        if ((n3 & 0x1) != 0x0) {
                            return;
                        }
                    }
                    else if ((n3 & 0x2) != 0x0) {
                        return;
                    }
                }
                final int n4 = n * super.anInt2206;
                final int n5 = n4 + super.anInt2206;
                final int n6 = n2 * super.anInt2206;
                final int n7 = n6 + super.anInt2206;
                float n10;
                float n11;
                float n12;
                float n13;
                int n16;
                int n17;
                int n18;
                int n19;
                int n20;
                int n21;
                int n22;
                int n23;
                if ((class13.aByte2740 & 0x1) != 0x0) {
                    final int n8 = super.anIntArrayArray2201[n][n2];
                    final float n9 = this.aFloat5270 * n8;
                    if (this.anInt5262 == -1) {
                        n10 = this.aFloat5274 + (this.aFloat5260 * n4 + n9 + this.aFloat5263 * n6);
                        if (n10 <= this.aHa_Sub2_5269.anInt4502) {
                            return;
                        }
                        n11 = this.aFloat5274 + (this.aFloat5260 * n5 + n9 + this.aFloat5263 * n6);
                        if (n11 <= this.aHa_Sub2_5269.anInt4502) {
                            return;
                        }
                        n12 = this.aFloat5274 + (this.aFloat5260 * n5 + n9 + this.aFloat5263 * n7);
                        if (n12 <= this.aHa_Sub2_5269.anInt4502) {
                            return;
                        }
                        n13 = this.aFloat5274 + (this.aFloat5260 * n4 + n9 + this.aFloat5263 * n7);
                        if (n13 <= this.aHa_Sub2_5269.anInt4502) {
                            return;
                        }
                    }
                    else {
                        n10 = this.aFloat5274 + (this.aFloat5260 * n4 + n9 + this.aFloat5263 * n6);
                        n11 = this.aFloat5274 + (this.aFloat5260 * n5 + n9 + this.aFloat5263 * n6);
                        n12 = this.aFloat5274 + (this.aFloat5260 * n5 + n9 + this.aFloat5263 * n7);
                        n13 = this.aFloat5274 + (this.aFloat5260 * n4 + n9 + this.aFloat5263 * n7);
                    }
                    final float n14 = this.aFloat5271 * n8;
                    final float n15 = this.aFloat5273 * n8;
                    if (this.anInt5262 == -1) {
                        n16 = class12.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n4 + n14 + this.aFloat5276 * n6)) * this.aHa_Sub2_5269.anInt4514 / n10);
                        n17 = class12.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n4 + n15 + this.aFloat5267 * n6)) * this.aHa_Sub2_5269.anInt4490 / n10);
                        n18 = class12.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n5 + n14 + this.aFloat5276 * n6)) * this.aHa_Sub2_5269.anInt4514 / n11);
                        n19 = class12.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n5 + n15 + this.aFloat5267 * n6)) * this.aHa_Sub2_5269.anInt4490 / n11);
                        n20 = class12.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n5 + n14 + this.aFloat5276 * n7)) * this.aHa_Sub2_5269.anInt4514 / n12);
                        n21 = class12.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n5 + n15 + this.aFloat5267 * n7)) * this.aHa_Sub2_5269.anInt4490 / n12);
                        n22 = class12.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n4 + n14 + this.aFloat5276 * n7)) * this.aHa_Sub2_5269.anInt4514 / n13);
                        n23 = class12.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n4 + n15 + this.aFloat5267 * n7)) * this.aHa_Sub2_5269.anInt4490 / n13);
                    }
                    else {
                        n16 = class12.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n4 + n14 + this.aFloat5276 * n6)) * this.aHa_Sub2_5269.anInt4514 / this.anInt5262);
                        n17 = class12.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n4 + n15 + this.aFloat5267 * n6)) * this.aHa_Sub2_5269.anInt4490 / this.anInt5262);
                        n18 = class12.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n5 + n14 + this.aFloat5276 * n6)) * this.aHa_Sub2_5269.anInt4514 / this.anInt5262);
                        n19 = class12.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n5 + n15 + this.aFloat5267 * n6)) * this.aHa_Sub2_5269.anInt4490 / this.anInt5262);
                        n20 = class12.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n5 + n14 + this.aFloat5276 * n7)) * this.aHa_Sub2_5269.anInt4514 / this.anInt5262);
                        n21 = class12.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n5 + n15 + this.aFloat5267 * n7)) * this.aHa_Sub2_5269.anInt4490 / this.anInt5262);
                        n22 = class12.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n4 + n14 + this.aFloat5276 * n7)) * this.aHa_Sub2_5269.anInt4514 / this.anInt5262);
                        n23 = class12.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n4 + n15 + this.aFloat5267 * n7)) * this.aHa_Sub2_5269.anInt4490 / this.anInt5262);
                    }
                }
                else {
                    final int n24 = super.anIntArrayArray2201[n][n2];
                    final int n25 = super.anIntArrayArray2201[n + 1][n2];
                    final int n26 = super.anIntArrayArray2201[n + 1][n2 + 1];
                    final int n27 = super.anIntArrayArray2201[n][n2 + 1];
                    if (this.anInt5262 == -1) {
                        n10 = this.aFloat5274 + (this.aFloat5260 * n4 + this.aFloat5270 * n24 + this.aFloat5263 * n6);
                        if (n10 <= this.aHa_Sub2_5269.anInt4502) {
                            return;
                        }
                        n11 = this.aFloat5274 + (this.aFloat5260 * n5 + this.aFloat5270 * n25 + this.aFloat5263 * n6);
                        if (n11 <= this.aHa_Sub2_5269.anInt4502) {
                            return;
                        }
                        n12 = this.aFloat5274 + (this.aFloat5260 * n5 + this.aFloat5270 * n26 + this.aFloat5263 * n7);
                        if (n12 <= this.aHa_Sub2_5269.anInt4502) {
                            return;
                        }
                        n13 = this.aFloat5274 + (this.aFloat5260 * n4 + this.aFloat5270 * n27 + this.aFloat5263 * n7);
                        if (n13 <= this.aHa_Sub2_5269.anInt4502) {
                            return;
                        }
                        n16 = class12.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n4 + this.aFloat5271 * n24 + this.aFloat5276 * n6)) * this.aHa_Sub2_5269.anInt4514 / n10);
                        n17 = class12.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n4 + this.aFloat5273 * n24 + this.aFloat5267 * n6)) * this.aHa_Sub2_5269.anInt4490 / n10);
                        n18 = class12.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n5 + this.aFloat5271 * n25 + this.aFloat5276 * n6)) * this.aHa_Sub2_5269.anInt4514 / n11);
                        n19 = class12.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n5 + this.aFloat5273 * n25 + this.aFloat5267 * n6)) * this.aHa_Sub2_5269.anInt4490 / n11);
                        n20 = class12.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n5 + this.aFloat5271 * n26 + this.aFloat5276 * n7)) * this.aHa_Sub2_5269.anInt4514 / n12);
                        n21 = class12.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n5 + this.aFloat5273 * n26 + this.aFloat5267 * n7)) * this.aHa_Sub2_5269.anInt4490 / n12);
                        n22 = class12.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n4 + this.aFloat5271 * n27 + this.aFloat5276 * n7)) * this.aHa_Sub2_5269.anInt4514 / n13);
                        n23 = class12.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n4 + this.aFloat5273 * n27 + this.aFloat5267 * n7)) * this.aHa_Sub2_5269.anInt4490 / n13);
                    }
                    else {
                        n10 = this.aFloat5274 + (this.aFloat5260 * n4 + this.aFloat5270 * n24 + this.aFloat5263 * n6);
                        n11 = this.aFloat5274 + (this.aFloat5260 * n5 + this.aFloat5270 * n25 + this.aFloat5263 * n6);
                        n12 = this.aFloat5274 + (this.aFloat5260 * n5 + this.aFloat5270 * n26 + this.aFloat5263 * n7);
                        n13 = this.aFloat5274 + (this.aFloat5260 * n4 + this.aFloat5270 * n27 + this.aFloat5263 * n7);
                        n16 = class12.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n4 + this.aFloat5271 * n24 + this.aFloat5276 * n6)) * this.aHa_Sub2_5269.anInt4514 / this.anInt5262);
                        n17 = class12.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n4 + this.aFloat5273 * n24 + this.aFloat5267 * n6)) * this.aHa_Sub2_5269.anInt4490 / this.anInt5262);
                        n18 = class12.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n5 + this.aFloat5271 * n25 + this.aFloat5276 * n6)) * this.aHa_Sub2_5269.anInt4514 / this.anInt5262);
                        n19 = class12.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n5 + this.aFloat5273 * n25 + this.aFloat5267 * n6)) * this.aHa_Sub2_5269.anInt4490 / this.anInt5262);
                        n20 = class12.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n5 + this.aFloat5271 * n26 + this.aFloat5276 * n7)) * this.aHa_Sub2_5269.anInt4514 / this.anInt5262);
                        n21 = class12.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n5 + this.aFloat5273 * n26 + this.aFloat5267 * n7)) * this.aHa_Sub2_5269.anInt4490 / this.anInt5262);
                        n22 = class12.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n4 + this.aFloat5271 * n27 + this.aFloat5276 * n7)) * this.aHa_Sub2_5269.anInt4514 / this.anInt5262);
                        n23 = class12.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n4 + this.aFloat5273 * n27 + this.aFloat5267 * n7)) * this.aHa_Sub2_5269.anInt4490 / this.anInt5262);
                    }
                }
                if (this.anInt5262 == -1) {
                    if ((n20 - n22) * (n19 - n23) - (n21 - n23) * (n18 - n22) > 0) {
                        class12.aBoolean135 = (n20 < 0 || n22 < 0 || n18 < 0 || n20 > class12.anInt141 || n22 > class12.anInt141 || n18 > class12.anInt141);
                        if (class13.aShort2744 >= 0) {
                            class12.method212(n21, n23, n19, n20, n22, n18, n12, n13, n11, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0xFF000000 | (Class221.anIntArray1665[class13.aShort2745 & 0xFFFF] & 0xFFFFFF), 0xFF000000 | (Class221.anIntArray1665[class13.aShort2742 & 0xFFFF] & 0xFFFFFF), 0xFF000000 | (Class221.anIntArray1665[class13.aShort2739 & 0xFFFF] & 0xFFFFFF), 0, 0, 0, 0, class13.aShort2744);
                        }
                        else {
                            class12.method216(n21, n23, n19, n20, n22, n18, (int)n12, (int)n13, (int)n11, class13.aShort2745 & 0xFFFF, class13.aShort2742 & 0xFFFF, class13.aShort2739 & 0xFFFF);
                        }
                    }
                    if ((n16 - n18) * (n23 - n19) - (n17 - n19) * (n22 - n18) > 0) {
                        class12.aBoolean135 = (n16 < 0 || n18 < 0 || n22 < 0 || n16 > class12.anInt141 || n18 > class12.anInt141 || n22 > class12.anInt141);
                        if (class13.aShort2744 >= 0) {
                            class12.method212(n17, n19, n23, n16, n18, n22, n10, n11, n13, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0xFF000000 | (Class221.anIntArray1665[class13.aShort2741 & 0xFFFF] & 0xFFFFFF), 0xFF000000 | (Class221.anIntArray1665[class13.aShort2739 & 0xFFFF] & 0xFFFFFF), 0xFF000000 | (Class221.anIntArray1665[class13.aShort2742 & 0xFFFF] & 0xFFFFFF), 0, 0, 0, 0, class13.aShort2744);
                        }
                        else {
                            class12.method216(n17, n19, n23, n16, n18, n22, (int)n10, (int)n11, (int)n13, class13.aShort2741 & 0xFFFF, class13.aShort2739 & 0xFFFF, class13.aShort2742 & 0xFFFF);
                        }
                    }
                }
                else {
                    if ((n20 - n22) * (n19 - n23) - (n21 - n23) * (n18 - n22) > 0) {
                        class12.aBoolean135 = (n20 < 0 || n22 < 0 || n18 < 0 || n20 > class12.anInt141 || n22 > class12.anInt141 || n18 > class12.anInt141);
                        if (class13.aShort2744 >= 0) {
                            class12.method212(n21, n23, n19, n20, n22, n18, n12, n13, n11, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0xFF000000 | (Class221.anIntArray1665[class13.aShort2745 & 0xFFFF] & 0xFFFFFF), 0xFF000000 | (Class221.anIntArray1665[class13.aShort2742 & 0xFFFF] & 0xFFFFFF), 0xFF000000 | (Class221.anIntArray1665[class13.aShort2739 & 0xFFFF] & 0xFFFFFF), 0, 0, 0, 0, class13.aShort2744);
                        }
                        else {
                            class12.method216(n21, n23, n19, n20, n22, n18, (int)n12, (int)n13, (int)n11, class13.aShort2745 & 0xFFFF, class13.aShort2742 & 0xFFFF, class13.aShort2739 & 0xFFFF);
                        }
                    }
                    if ((n16 - n18) * (n23 - n19) - (n17 - n19) * (n22 - n18) > 0) {
                        class12.aBoolean135 = (n16 < 0 || n18 < 0 || n22 < 0 || n16 > class12.anInt141 || n18 > class12.anInt141 || n22 > class12.anInt141);
                        if (class13.aShort2744 >= 0) {
                            class12.method212(n17, n19, n23, n16, n18, n22, n10, n11, n13, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0xFF000000 | (Class221.anIntArray1665[class13.aShort2741 & 0xFFFF] & 0xFFFFFF), 0xFF000000 | (Class221.anIntArray1665[class13.aShort2739 & 0xFFFF] & 0xFFFFFF), 0xFF000000 | (Class221.anIntArray1665[class13.aShort2742 & 0xFFFF] & 0xFFFFFF), 0, 0, 0, 0, class13.aShort2744);
                        }
                        else {
                            class12.method216(n17, n19, n23, n16, n18, n22, (int)n10, (int)n11, (int)n13, class13.aShort2741 & 0xFFFF, class13.aShort2739 & 0xFFFF, class13.aShort2742 & 0xFFFF);
                        }
                    }
                }
            }
        }
        else {
            final Class328 class14 = this.aClass328ArrayArray5261[n][n2];
            if (class14 != null) {
                if (n3 != 0) {
                    if ((class14.aByte2747 & 0x4) != 0x0) {
                        if ((n3 & 0x1) != 0x0) {
                            return;
                        }
                    }
                    else if ((n3 & 0x2) != 0x0) {
                        return;
                    }
                }
                if (this.anInt5262 == -1) {
                    for (short n28 = 0; n28 < class14.aShort2748; ++n28) {
                        final int n29 = class14.aShortArray2755[n28] + (n << super.anInt2200);
                        final short n30 = class14.aShortArray2754[n28];
                        final int n31 = class14.aShortArray2757[n28] + (n2 << super.anInt2200);
                        final float n32 = this.aFloat5274 + (this.aFloat5260 * n29 + this.aFloat5270 * n30 + this.aFloat5263 * n31);
                        if (n32 <= this.aHa_Sub2_5269.anInt4502) {
                            return;
                        }
                        final float n33 = this.aFloat5268 + (this.aFloat5275 * n29 + this.aFloat5271 * n30 + this.aFloat5276 * n31);
                        final float n34 = this.aFloat5255 + (this.aFloat5264 * n29 + this.aFloat5273 * n30 + this.aFloat5267 * n31);
                        array[n28] = class12.anInt136 + (int)(n33 * this.aHa_Sub2_5269.anInt4514 / n32);
                        array2[n28] = class12.anInt128 + (int)(n34 * this.aHa_Sub2_5269.anInt4490 / n32);
                        array3[n28] = (int)n32;
                    }
                }
                else {
                    for (short n35 = 0; n35 < class14.aShort2748; ++n35) {
                        final int n36 = class14.aShortArray2755[n35] + (n << super.anInt2200);
                        final short n37 = class14.aShortArray2754[n35];
                        final int n38 = class14.aShortArray2757[n35] + (n2 << super.anInt2200);
                        final float n39 = this.aFloat5274 + (this.aFloat5260 * n36 + this.aFloat5270 * n37 + this.aFloat5263 * n38);
                        final float n40 = this.aFloat5268 + (this.aFloat5275 * n36 + this.aFloat5271 * n37 + this.aFloat5276 * n38);
                        final float n41 = this.aFloat5255 + (this.aFloat5264 * n36 + this.aFloat5273 * n37 + this.aFloat5267 * n38);
                        array[n35] = class12.anInt136 + (int)(n40 * this.aHa_Sub2_5269.anInt4514 / this.anInt5262);
                        array2[n35] = class12.anInt128 + (int)(n41 * this.aHa_Sub2_5269.anInt4490 / this.anInt5262);
                        array3[n35] = (int)n39;
                    }
                }
                if (class14.aShortArray2752 != null) {
                    if (this.anInt5262 == -1) {
                        for (short n42 = 0; n42 < class14.aShort2746; ++n42) {
                            final short n43 = class14.aShortArray2758[n42];
                            final short n44 = class14.aShortArray2751[n42];
                            final short n45 = class14.aShortArray2759[n42];
                            final int n46 = array[n43];
                            final int n47 = array[n44];
                            final int n48 = array[n45];
                            final int n49 = array2[n43];
                            final int n50 = array2[n44];
                            final int n51 = array2[n45];
                            if ((n46 - n47) * (n51 - n50) - (n49 - n50) * (n48 - n47) > 0) {
                                class12.aBoolean135 = (n46 < 0 || n47 < 0 || n48 < 0 || n46 > class12.anInt141 || n47 > class12.anInt141 || n48 > class12.anInt141);
                                final short n52 = class14.aShortArray2752[n42];
                                if (n52 != -1) {
                                    class12.method212(n49, n50, n51, n46, n47, n48, array3[n43], array3[n44], array3[n45], class14.aShortArray2755[n43] / super.anInt2206, class14.aShortArray2755[n44] / super.anInt2206, class14.aShortArray2755[n45] / super.anInt2206, class14.aShortArray2757[n43] / super.anInt2206, class14.aShortArray2757[n44] / super.anInt2206, class14.aShortArray2757[n45] / super.anInt2206, 0xFF000000 | (Class221.anIntArray1665[class14.aShortArray2750[n43] & 0xFFFF] & 0xFFFFFF), 0xFF000000 | (Class221.anIntArray1665[class14.aShortArray2750[n44] & 0xFFFF] & 0xFFFFFF), 0xFF000000 | (Class221.anIntArray1665[class14.aShortArray2750[n45] & 0xFFFF] & 0xFFFFFF), 0, 0, 0, 0, n52);
                                }
                                else {
                                    final int n53 = class14.anIntArray2749[n42];
                                    if (n53 != -1) {
                                        class12.method216(n49, n50, n51, n46, n47, n48, array3[n43], array3[n44], array3[n45], Class246_Sub7.method3132(class14.aShortArray2750[n43], (byte)118, n53), Class246_Sub7.method3132(class14.aShortArray2750[n44], (byte)118, n53), Class246_Sub7.method3132(class14.aShortArray2750[n45], (byte)118, n53));
                                    }
                                }
                            }
                        }
                    }
                    else {
                        for (short n54 = 0; n54 < class14.aShort2746; ++n54) {
                            final short n55 = class14.aShortArray2758[n54];
                            final short n56 = class14.aShortArray2751[n54];
                            final short n57 = class14.aShortArray2759[n54];
                            final int n58 = array[n55];
                            final int n59 = array[n56];
                            final int n60 = array[n57];
                            final int n61 = array2[n55];
                            final int n62 = array2[n56];
                            final int n63 = array2[n57];
                            if ((n58 - n59) * (n63 - n62) - (n61 - n62) * (n60 - n59) > 0) {
                                class12.aBoolean135 = (n58 < 0 || n59 < 0 || n60 < 0 || n58 > class12.anInt141 || n59 > class12.anInt141 || n60 > class12.anInt141);
                                final short n64 = class14.aShortArray2752[n54];
                                if (n64 != -1) {
                                    class12.method212(n61, n62, n63, n58, n59, n60, array3[n55], array3[n56], array3[n57], class14.aShortArray2755[n55] / super.anInt2206, class14.aShortArray2755[n56] / super.anInt2206, class14.aShortArray2755[n57] / super.anInt2206, class14.aShortArray2757[n55] / super.anInt2206, class14.aShortArray2757[n56] / super.anInt2206, class14.aShortArray2757[n57] / super.anInt2206, 0xFF000000 | (Class221.anIntArray1665[class14.aShortArray2750[n55] & 0xFFFF] & 0xFFFFFF), 0xFF000000 | (Class221.anIntArray1665[class14.aShortArray2750[n56] & 0xFFFF] & 0xFFFFFF), 0xFF000000 | (Class221.anIntArray1665[class14.aShortArray2750[n57] & 0xFFFF] & 0xFFFFFF), 0, 0, 0, 0, n64);
                                }
                                else {
                                    final int n65 = class14.anIntArray2749[n54];
                                    if (n65 != -1) {
                                        class12.method216(n61, n62, n63, n58, n59, n60, array3[n55], array3[n56], array3[n57], Class246_Sub7.method3132(class14.aShortArray2750[n55], (byte)118, n65), Class246_Sub7.method3132(class14.aShortArray2750[n56], (byte)118, n65), Class246_Sub7.method3132(class14.aShortArray2750[n57], (byte)118, n65));
                                    }
                                }
                            }
                        }
                    }
                }
                else {
                    for (short n66 = 0; n66 < class14.aShort2746; ++n66) {
                        final short n67 = class14.aShortArray2758[n66];
                        final short n68 = class14.aShortArray2751[n66];
                        final short n69 = class14.aShortArray2759[n66];
                        final int n70 = array[n67];
                        final int n71 = array[n68];
                        final int n72 = array[n69];
                        final int n73 = array2[n67];
                        final int n74 = array2[n68];
                        final int n75 = array2[n69];
                        if ((n70 - n71) * (n75 - n74) - (n73 - n74) * (n72 - n71) > 0) {
                            final int n76 = class14.anIntArray2749[n66];
                            if (n76 != -1) {
                                class12.aBoolean135 = (n70 < 0 || n71 < 0 || n72 < 0 || n70 > class12.anInt141 || n71 > class12.anInt141 || n72 > class12.anInt141);
                                class12.method216(n73, n74, n75, n70, n71, n72, array3[n67], array3[n68], array3[n69], Class246_Sub7.method3132(class14.aShortArray2750[n67], (byte)118, n76), Class246_Sub7.method3132(class14.aShortArray2750[n68], (byte)118, n76), Class246_Sub7.method3132(class14.aShortArray2750[n69], (byte)118, n76));
                            }
                        }
                    }
                }
            }
        }
    }
    
    @Override
    final boolean method3418(final r r, final int n, final int n2, final int n3, final int n4, final boolean b) {
        return false;
    }
    
    private final boolean method3441(final int n) {
        return (this.anInt5256 & 0x8) != 0x0 && (n == 4 || n == 8 || n == 9);
    }
    
    @Override
    final void method3425(final int n, final int n2) {
        this.method3443(n, n2, 0);
    }
    
    @Override
    final void U(final int n, final int n2, final int[] array, final int[] array2, final int[] array3, final int[] array4, final int[] array5, final int[] array6, final int[] array7, final int[] array8, final int n3, final int n4, final int n5, final boolean b) {
        final boolean b2 = (this.anInt5256 & 0x20) == 0x0;
        if (this.aClass193ArrayArray5272 == null && !b2) {
            this.aClass193ArrayArray5272 = new Class193[super.anInt2203][super.anInt2204];
            this.aClass285ArrayArray5258 = new Class285[super.anInt2203][super.anInt2204];
        }
        else if (this.aClass120ArrayArray5266 == null && b2) {
            this.aClass120ArrayArray5266 = new Class120[super.anInt2203][super.anInt2204];
        }
        else if (this.aClass327ArrayArray5265 != null) {
            throw new IllegalStateException();
        }
        if (array != null && array.length != 0) {
            for (int i = 0; i < array5.length; ++i) {
                if (array5[i] == -1) {
                    array5[i] = 0;
                }
                else {
                    array5[i] = (Class221.anIntArray1665[Class111_Sub2.method2117(array5[i], -67) & 0xFFFF] << 8 | 0xFF);
                }
            }
            if (array6 != null) {
                for (int j = 0; j < array6.length; ++j) {
                    if (array6[j] == -1) {
                        array6[j] = 0;
                    }
                    else {
                        array6[j] = (Class221.anIntArray1665[Class111_Sub2.method2117(array6[j], 57) & 0xFFFF] << 8 | 0xFF);
                    }
                }
            }
            if (b2) {
                final Class120 class120 = new Class120();
                class120.aShort994 = (short)array.length;
                class120.aShort991 = (short)(array.length / 3);
                class120.aShortArray995 = new short[class120.aShort994];
                class120.aShortArray996 = new short[class120.aShort994];
                class120.aShortArray992 = new short[class120.aShort994];
                class120.anIntArray999 = new int[class120.aShort994];
                class120.aShortArray998 = new short[class120.aShort994];
                class120.aShortArray990 = new short[class120.aShort994];
                class120.aByteArray1000 = new byte[class120.aShort994];
                if (array4 != null) {
                    class120.aShortArray993 = new short[class120.aShort994];
                }
                for (short n6 = 0; n6 < class120.aShort994; ++n6) {
                    final int n7 = array[n6];
                    final int n8 = array3[n6];
                    int n9;
                    if (n7 == 0 && n8 == 0) {
                        n9 = this.aByteArrayArray5257[n][n2] - this.aByteArrayArray5259[n][n2];
                    }
                    else if (n7 == 0 && n8 == super.anInt2206) {
                        n9 = this.aByteArrayArray5257[n][n2 + 1] - this.aByteArrayArray5259[n][n2 + 1];
                    }
                    else if (n7 == super.anInt2206 && n8 == super.anInt2206) {
                        n9 = this.aByteArrayArray5257[n + 1][n2 + 1] - this.aByteArrayArray5259[n + 1][n2 + 1];
                    }
                    else if (n7 == super.anInt2206 && n8 == 0) {
                        n9 = this.aByteArrayArray5257[n + 1][n2] - this.aByteArrayArray5259[n + 1][n2];
                    }
                    else {
                        n9 = ((this.aByteArrayArray5257[n][n2] - this.aByteArrayArray5259[n][n2]) * (super.anInt2206 - n7) + (this.aByteArrayArray5257[n + 1][n2] - this.aByteArrayArray5259[n + 1][n2]) * n7) * (super.anInt2206 - n8) + ((this.aByteArrayArray5257[n][n2 + 1] - this.aByteArrayArray5259[n][n2 + 1]) * (super.anInt2206 - n7) + (this.aByteArrayArray5257[n + 1][n2 + 1] - this.aByteArrayArray5259[n + 1][n2 + 1]) * n7) * n8 >> 2 * super.anInt2200;
                    }
                    final int n10 = (n << super.anInt2200) + n7;
                    final int n11 = (n2 << super.anInt2200) + n8;
                    class120.aShortArray995[n6] = (short)n7;
                    class120.aShortArray992[n6] = (short)n8;
                    class120.aShortArray996[n6] = (short)(this.method3417(n10, n11, true) + ((array2 != null) ? array2[n6] : 0));
                    if (n9 < 0) {
                        n9 = 0;
                    }
                    if (array5[n6] == 0) {
                        class120.anIntArray999[n6] = 0;
                        if (array6 != null) {
                            class120.aByteArray1000[n6] = (byte)n9;
                        }
                    }
                    else {
                        int n12 = 0;
                        if (array4 != null) {
                            final short[] aShortArray993 = class120.aShortArray993;
                            final short n13 = n6;
                            final short n14 = (short)array4[n6];
                            aShortArray993[n13] = n14;
                            final short n15 = n14;
                            if (n4 != 0) {
                                n12 = 255 * n15 / n4;
                                if (n12 < 0) {
                                    n12 = 0;
                                }
                                else if (n12 > 255) {
                                    n12 = 255;
                                }
                            }
                        }
                        int n16 = -16777216;
                        if (array7[n6] != -1 && this.method3441(this.aHa_Sub2_5269.aD938.method11(array7[n6], -28755).aByte1820)) {
                            n16 = -1694498816;
                        }
                        class120.anIntArray999[n6] = (n16 | Class215.method2790(127, n12, method3439(array5[n6] >> 8, n9), n3));
                        if (array6 != null) {
                            class120.aByteArray1000[n6] = (byte)n9;
                        }
                    }
                    class120.aShortArray998[n6] = (short)array7[n6];
                    class120.aShortArray990[n6] = (short)array8[n6];
                }
                if (array6 != null) {
                    class120.anIntArray997 = new int[class120.aShort991];
                }
                for (short n17 = 0; n17 < class120.aShort991; ++n17) {
                    final short n18 = (short)(n17 * 3);
                    if (array6 != null && array6[n18] != 0) {
                        class120.anIntArray997[n17] = (0xFF000000 | array6[n18] >> 8);
                    }
                }
                this.aClass120ArrayArray5266[n][n2] = class120;
            }
            else {
                int n19 = 1;
                int n20 = -1;
                int n21 = -1;
                int n22 = -1;
                int n23 = -1;
                if (array.length == 6) {
                    for (int k = 0; k < 6; ++k) {
                        if (array[k] == 0 && array3[k] == 0) {
                            if (n20 != -1 && array5[n20] != array5[k]) {
                                n19 = 0;
                                break;
                            }
                            n20 = k;
                        }
                        else if (array[k] == super.anInt2206 && array3[k] == 0) {
                            if (n21 != -1 && array5[n21] != array5[k]) {
                                n19 = 0;
                                break;
                            }
                            n21 = k;
                        }
                        else if (array[k] == super.anInt2206 && array3[k] == super.anInt2206) {
                            if (n22 != -1 && array5[n22] != array5[k]) {
                                n19 = 0;
                                break;
                            }
                            n22 = k;
                        }
                        else if (array[k] == 0 && array3[k] == super.anInt2206) {
                            if (n23 != -1 && array5[n23] != array5[k]) {
                                n19 = 0;
                                break;
                            }
                            n23 = k;
                        }
                    }
                    if (n20 == -1 || n21 == -1 || n22 == -1 || n23 == -1) {
                        n19 = 0;
                    }
                    if (n19 != 0) {
                        if (array2 != null) {
                            for (int l = 0; l < 4; ++l) {
                                if (array2[l] != 0) {
                                    n19 = 0;
                                    break;
                                }
                            }
                        }
                        if (n19 != 0) {
                            for (int n24 = 1; n24 < 4; ++n24) {
                                if (array[n24] != array[0] && array[n24] != array[0] + super.anInt2206 && array[n24] != array[0] - super.anInt2206) {
                                    n19 = 0;
                                    break;
                                }
                                if (array3[n24] != array3[0] && array3[n24] != array3[0] + super.anInt2206 && array3[n24] != array3[0] - super.anInt2206) {
                                    n19 = 0;
                                    break;
                                }
                            }
                        }
                    }
                }
                else {
                    n19 = 0;
                }
                if (n19 != 0) {
                    final Class193 class121 = new Class193();
                    final int n25 = array5[0];
                    final int n26 = array7[0];
                    if (array6 != null) {
                        class121.anInt1494 = array6[0] >> 8;
                        if (n25 == 0) {
                            final Class193 class122 = class121;
                            class122.aByte1492 |= 0x2;
                        }
                    }
                    else if (n25 == 0) {
                        return;
                    }
                    if (super.anIntArrayArray2201[n][n2] == super.anIntArrayArray2201[n + 1][n2] && super.anIntArrayArray2201[n][n2] == super.anIntArrayArray2201[n + 1][n2 + 1] && super.anIntArrayArray2201[n][n2] == super.anIntArrayArray2201[n][n2 + 1]) {
                        final Class193 class123 = class121;
                        class123.aByte1492 |= 0x1;
                    }
                    if (n26 != -1 && (class121.aByte1492 & 0x2) == 0x0 && !this.aHa_Sub2_5269.aD938.method11(n26, -28755).aBoolean1825) {
                        int n27;
                        if (array4 != null && n4 != 0) {
                            n27 = 255 * array4[n20] / n4;
                            if (n27 < 0) {
                                n27 = 0;
                            }
                            else if (n27 > 255) {
                                n27 = 255;
                            }
                        }
                        else {
                            n27 = 0;
                        }
                        class121.anInt1490 = Class215.method2790(127, n27, method3439(array5[n20] >> 8, this.aByteArrayArray5257[n][n2] - this.aByteArrayArray5259[n][n2]), n3);
                        if (class121.anInt1494 != 0) {
                            final Class193 class124 = class121;
                            class124.anInt1490 |= 255 - (this.aByteArrayArray5257[n][n2] - this.aByteArrayArray5259[n][n2]) << 25;
                        }
                        int n28;
                        if (array4 != null && n4 != 0) {
                            n28 = 255 * array4[n21] / n4;
                            if (n28 < 0) {
                                n28 = 0;
                            }
                            else if (n28 > 255) {
                                n28 = 255;
                            }
                        }
                        else {
                            n28 = 0;
                        }
                        class121.anInt1491 = Class215.method2790(126, n28, method3439(array5[n21] >> 8, this.aByteArrayArray5257[n + 1][n2] - this.aByteArrayArray5259[n + 1][n2]), n3);
                        if (class121.anInt1494 != 0) {
                            final Class193 class125 = class121;
                            class125.anInt1491 |= 255 - (this.aByteArrayArray5257[n + 1][n2] - this.aByteArrayArray5259[n + 1][n2]) << 25;
                        }
                        int n29;
                        if (array4 != null && n4 != 0) {
                            n29 = 255 * array4[n22] / n4;
                            if (n29 < 0) {
                                n29 = 0;
                            }
                            else if (n29 > 255) {
                                n29 = 255;
                            }
                        }
                        else {
                            n29 = 0;
                        }
                        class121.anInt1493 = Class215.method2790(127, n29, method3439(array5[n22] >> 8, this.aByteArrayArray5257[n + 1][n2 + 1] - this.aByteArrayArray5259[n + 1][n2 + 1]), n3);
                        if (class121.anInt1494 != 0) {
                            final Class193 class126 = class121;
                            class126.anInt1493 |= 255 - (this.aByteArrayArray5257[n + 1][n2 + 1] - this.aByteArrayArray5259[n + 1][n2 + 1]) << 25;
                        }
                        int n30;
                        if (array4 != null && n4 != 0) {
                            n30 = 255 * array4[n23] / n4;
                            if (n30 < 0) {
                                n30 = 0;
                            }
                            else if (n30 > 255) {
                                n30 = 255;
                            }
                        }
                        else {
                            n30 = 0;
                        }
                        class121.anInt1484 = Class215.method2790(126, n30, method3439(array5[n23] >> 8, this.aByteArrayArray5257[n][n2 + 1] - this.aByteArrayArray5259[n][n2 + 1]), n3);
                        class121.aShort1487 = (short)n26;
                    }
                    else {
                        int n31;
                        if (array4 != null && n4 != 0) {
                            n31 = 255 * array4[n20] / n4;
                            if (n31 < 0) {
                                n31 = 0;
                            }
                            else if (n31 > 255) {
                                n31 = 255;
                            }
                        }
                        else {
                            n31 = 0;
                        }
                        class121.anInt1490 = Class215.method2790(126, n31, method3439(array5[n20] >> 8, this.aByteArrayArray5257[n][n2] - this.aByteArrayArray5259[n][n2]), n3);
                        if (class121.anInt1494 != 0) {
                            final Class193 class127 = class121;
                            class127.anInt1490 |= 255 - (this.aByteArrayArray5257[n][n2] - this.aByteArrayArray5259[n][n2]) << 25;
                        }
                        int n32;
                        if (array4 != null && n4 != 0) {
                            n32 = 255 * array4[n21] / n4;
                            if (n32 < 0) {
                                n32 = 0;
                            }
                            else if (n32 > 255) {
                                n32 = 255;
                            }
                        }
                        else {
                            n32 = 0;
                        }
                        class121.anInt1491 = Class215.method2790(126, n32, method3439(array5[n21] >> 8, this.aByteArrayArray5257[n + 1][n2] - this.aByteArrayArray5259[n + 1][n2]), n3);
                        if (class121.anInt1494 != 0) {
                            final Class193 class128 = class121;
                            class128.anInt1491 |= 255 - (this.aByteArrayArray5257[n + 1][n2] - this.aByteArrayArray5259[n + 1][n2]) << 25;
                        }
                        int n33;
                        if (array4 != null && n4 != 0) {
                            n33 = 255 * array4[n22] / n4;
                            if (n33 < 0) {
                                n33 = 0;
                            }
                            else if (n33 > 255) {
                                n33 = 255;
                            }
                        }
                        else {
                            n33 = 0;
                        }
                        class121.anInt1493 = Class215.method2790(126, n33, method3439(array5[n22] >> 8, this.aByteArrayArray5257[n + 1][n2 + 1] - this.aByteArrayArray5259[n + 1][n2 + 1]), n3);
                        if (class121.anInt1494 != 0) {
                            final Class193 class129 = class121;
                            class129.anInt1493 |= 255 - (this.aByteArrayArray5257[n + 1][n2 + 1] - this.aByteArrayArray5259[n + 1][n2 + 1]) << 25;
                        }
                        int n34;
                        if (array4 != null && n4 != 0) {
                            n34 = 255 * array4[n23] / n4;
                            if (n34 < 0) {
                                n34 = 0;
                            }
                            else if (n34 > 255) {
                                n34 = 255;
                            }
                        }
                        else {
                            n34 = 0;
                        }
                        class121.anInt1484 = Class215.method2790(127, n34, method3439(array5[n23] >> 8, this.aByteArrayArray5257[n][n2 + 1] - this.aByteArrayArray5259[n][n2 + 1]), n3);
                        if (class121.anInt1494 != 0) {
                            final Class193 class130 = class121;
                            class130.anInt1484 |= 255 - (this.aByteArrayArray5257[n][n2 + 1] - this.aByteArrayArray5259[n][n2 + 1]) << 25;
                        }
                        class121.aShort1487 = -1;
                    }
                    if (array4 != null) {
                        class121.aShort1486 = (short)array4[n22];
                        class121.aShort1488 = (short)array4[n23];
                        class121.aShort1485 = (short)array4[n21];
                        class121.aShort1489 = (short)array4[n20];
                    }
                    this.aClass193ArrayArray5272[n][n2] = class121;
                }
                else {
                    final Class285 class131 = new Class285();
                    class131.aShort2174 = (short)array.length;
                    class131.aShort2171 = (short)(array.length / 3);
                    class131.aShortArray2176 = new short[class131.aShort2174];
                    class131.aShortArray2179 = new short[class131.aShort2174];
                    class131.aShortArray2170 = new short[class131.aShort2174];
                    class131.anIntArray2173 = new int[class131.aShort2174];
                    if (array4 != null) {
                        class131.aShortArray2178 = new short[class131.aShort2174];
                    }
                    for (short n35 = 0; n35 < class131.aShort2174; ++n35) {
                        final int n36 = array[n35];
                        final int n37 = array3[n35];
                        int n38;
                        if (n36 == 0 && n37 == 0) {
                            n38 = this.aByteArrayArray5257[n][n2] - this.aByteArrayArray5259[n][n2];
                        }
                        else if (n36 == 0 && n37 == super.anInt2206) {
                            n38 = this.aByteArrayArray5257[n][n2 + 1] - this.aByteArrayArray5259[n][n2 + 1];
                        }
                        else if (n36 == super.anInt2206 && n37 == super.anInt2206) {
                            n38 = this.aByteArrayArray5257[n + 1][n2 + 1] - this.aByteArrayArray5259[n + 1][n2 + 1];
                        }
                        else if (n36 == super.anInt2206 && n37 == 0) {
                            n38 = this.aByteArrayArray5257[n + 1][n2] - this.aByteArrayArray5259[n + 1][n2];
                        }
                        else {
                            n38 = ((this.aByteArrayArray5257[n][n2] - this.aByteArrayArray5259[n][n2]) * (super.anInt2206 - n36) + (this.aByteArrayArray5257[n + 1][n2] - this.aByteArrayArray5259[n + 1][n2]) * n36) * (super.anInt2206 - n37) + ((this.aByteArrayArray5257[n][n2 + 1] - this.aByteArrayArray5259[n][n2 + 1]) * (super.anInt2206 - n36) + (this.aByteArrayArray5257[n + 1][n2 + 1] - this.aByteArrayArray5259[n + 1][n2 + 1]) * n36) * n37 >> 2 * super.anInt2200;
                        }
                        final int n39 = (n << super.anInt2200) + n36;
                        final int n40 = (n2 << super.anInt2200) + n37;
                        class131.aShortArray2176[n35] = (short)n36;
                        class131.aShortArray2170[n35] = (short)n37;
                        class131.aShortArray2179[n35] = (short)(this.method3417(n39, n40, true) + ((array2 != null) ? array2[n35] : 0));
                        if (n38 < 0) {
                            n38 = 0;
                        }
                        if (array5[n35] == 0) {
                            if (array6 != null) {
                                class131.anIntArray2173[n35] = n38 << 25;
                            }
                            else {
                                class131.anIntArray2173[n35] = 0;
                            }
                        }
                        else {
                            int n41 = 0;
                            if (array4 != null) {
                                final short[] aShortArray994 = class131.aShortArray2178;
                                final short n42 = n35;
                                final short n43 = (short)array4[n35];
                                aShortArray994[n42] = n43;
                                final short n44 = n43;
                                if (n4 != 0) {
                                    n41 = 255 * n44 / n4;
                                    if (n41 < 0) {
                                        n41 = 0;
                                    }
                                    else if (n41 > 255) {
                                        n41 = 255;
                                    }
                                }
                            }
                            class131.anIntArray2173[n35] = Class215.method2790(126, n41, method3439(array5[n35] >> 8, n38), n3);
                            if (array6 != null) {
                                final int[] anIntArray2173 = class131.anIntArray2173;
                                final short n45 = n35;
                                anIntArray2173[n45] |= n38 << 25;
                            }
                        }
                    }
                    boolean b3 = false;
                    for (short n46 = 0; n46 < class131.aShort2171; ++n46) {
                        if (array7[n46 * 3] != -1 && !this.aHa_Sub2_5269.aD938.method11(array7[n46 * 3], -28755).aBoolean1825) {
                            b3 = true;
                        }
                    }
                    if (array6 != null) {
                        class131.anIntArray2175 = new int[class131.aShort2171];
                    }
                    if (b3) {
                        class131.aShortArray2169 = new short[class131.aShort2171];
                        class131.aShortArray2177 = new short[class131.aShort2171];
                    }
                    for (short n47 = 0; n47 < class131.aShort2171; ++n47) {
                        final short n48 = (short)(n47 * 3);
                        if (array6 != null && array6[n48] != 0) {
                            class131.anIntArray2175[n47] = array6[n48] >> 8;
                        }
                        if (b3) {
                            final short n49 = (short)(n48 + 1);
                            final short n50 = (short)(n49 + 1);
                            boolean b4 = false;
                            boolean b5 = true;
                            final int n51 = array7[n48];
                            if (n51 == -1 || this.aHa_Sub2_5269.aD938.method11(n51, -28755).aBoolean1825) {
                                b5 = false;
                            }
                            else {
                                b4 = true;
                            }
                            final int n52 = array7[n49];
                            if (n52 == -1 || this.aHa_Sub2_5269.aD938.method11(n52, -28755).aBoolean1825) {
                                b5 = false;
                            }
                            else {
                                b4 = true;
                            }
                            final int n53 = array7[n50];
                            if (n53 == -1 || this.aHa_Sub2_5269.aD938.method11(n53, -28755).aBoolean1825) {
                                b5 = false;
                            }
                            else {
                                b4 = true;
                            }
                            if (b5) {
                                class131.aShortArray2169[n47] = (short)n53;
                                class131.aShortArray2177[n47] = (short)array8[n48];
                            }
                            else {
                                if (b4) {
                                    final int n54 = array7[n48];
                                    if (n54 != -1 && !this.aHa_Sub2_5269.aD938.method11(n54, -28755).aBoolean1825) {
                                        class131.anIntArray2173[n48] = Class221.anIntArray1665[Class111_Sub2.method2117(this.aHa_Sub2_5269.aD938.method11(n54, -28755).aShort1831 & 0xFFFF, 109) & 0xFFFF];
                                    }
                                    final int n55 = array7[n49];
                                    if (n55 != -1 && !this.aHa_Sub2_5269.aD938.method11(n55, -28755).aBoolean1825) {
                                        class131.anIntArray2173[n49] = Class221.anIntArray1665[Class111_Sub2.method2117(this.aHa_Sub2_5269.aD938.method11(n55, -28755).aShort1831 & 0xFFFF, -51) & 0xFFFF];
                                    }
                                    final int n56 = array7[n50];
                                    if (n56 != -1 && !this.aHa_Sub2_5269.aD938.method11(n56, -28755).aBoolean1825) {
                                        class131.anIntArray2173[n50] = Class221.anIntArray1665[Class111_Sub2.method2117(this.aHa_Sub2_5269.aD938.method11(n56, -28755).aShort1831 & 0xFFFF, 115) & 0xFFFF];
                                    }
                                }
                                class131.aShortArray2169[n47] = -1;
                            }
                        }
                    }
                    this.aClass285ArrayArray5258[n][n2] = class131;
                }
            }
        }
    }
    
    private final void method3442(final int n, final int n2, final boolean b, final Class235 class235, final Class12 class236, final int[] array, final int[] array2, final int[] array3, final int[] array4, final int n3) {
        final Class193 class237 = this.aClass193ArrayArray5272[n][n2];
        if (class237 != null) {
            if ((class237.aByte1492 & 0x2) == 0x0) {
                if (n3 != 0) {
                    if ((class237.aByte1492 & 0x4) != 0x0) {
                        if ((n3 & 0x1) != 0x0) {
                            return;
                        }
                    }
                    else if ((n3 & 0x2) != 0x0) {
                        return;
                    }
                }
                final int n4 = n * super.anInt2206;
                final int n5 = n4 + super.anInt2206;
                final int n6 = n2 * super.anInt2206;
                final int n7 = n6 + super.anInt2206;
                int n8 = 0;
                int n9 = 0;
                int n10 = 0;
                int n11 = 0;
                float n14;
                float n15;
                float n16;
                float n17;
                int n24;
                int n25;
                int n26;
                int n27;
                int n28;
                int n29;
                int n30;
                int n31;
                if ((class237.aByte1492 & 0x1) != 0x0 && !b) {
                    final int n12 = super.anIntArrayArray2201[n][n2];
                    final float n13 = this.aFloat5270 * n12;
                    if (this.anInt5262 == -1) {
                        n14 = this.aFloat5274 + (this.aFloat5260 * n4 + n13 + this.aFloat5263 * n6);
                        if (n14 <= this.aHa_Sub2_5269.anInt4502) {
                            return;
                        }
                        n15 = this.aFloat5274 + (this.aFloat5260 * n5 + n13 + this.aFloat5263 * n6);
                        if (n15 <= this.aHa_Sub2_5269.anInt4502) {
                            return;
                        }
                        n16 = this.aFloat5274 + (this.aFloat5260 * n5 + n13 + this.aFloat5263 * n7);
                        if (n16 <= this.aHa_Sub2_5269.anInt4502) {
                            return;
                        }
                        n17 = this.aFloat5274 + (this.aFloat5260 * n4 + n13 + this.aFloat5263 * n7);
                        if (n17 <= this.aHa_Sub2_5269.anInt4502) {
                            return;
                        }
                    }
                    else {
                        n14 = this.aFloat5274 + (this.aFloat5260 * n4 + n13 + this.aFloat5263 * n6);
                        n15 = this.aFloat5274 + (this.aFloat5260 * n5 + n13 + this.aFloat5263 * n6);
                        n16 = this.aFloat5274 + (this.aFloat5260 * n5 + n13 + this.aFloat5263 * n7);
                        n17 = this.aFloat5274 + (this.aFloat5260 * n4 + n13 + this.aFloat5263 * n7);
                    }
                    if (class235.aBoolean1758) {
                        final int n18 = (int)(n14 - class235.anInt1761);
                        if (n18 > 0) {
                            n8 = n18;
                            if (n8 > 255) {
                                n8 = 255;
                            }
                        }
                        final int n19 = (int)(n15 - class235.anInt1761);
                        if (n19 > 0) {
                            n9 = n19;
                            if (n9 > 255) {
                                n9 = 255;
                            }
                        }
                        final int n20 = (int)(n16 - class235.anInt1761);
                        if (n20 > 0) {
                            n10 = n20;
                            if (n10 > 255) {
                                n10 = 255;
                            }
                        }
                        final int n21 = (int)(n17 - class235.anInt1761);
                        if (n21 > 0) {
                            n11 = n21;
                            if (n11 > 255) {
                                n11 = 255;
                            }
                        }
                    }
                    final float n22 = this.aFloat5271 * n12;
                    final float n23 = this.aFloat5273 * n12;
                    if (this.anInt5262 == -1) {
                        n24 = class236.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n4 + n22 + this.aFloat5276 * n6)) * this.aHa_Sub2_5269.anInt4514 / n14);
                        n25 = class236.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n4 + n23 + this.aFloat5267 * n6)) * this.aHa_Sub2_5269.anInt4490 / n14);
                        n26 = class236.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n5 + n22 + this.aFloat5276 * n6)) * this.aHa_Sub2_5269.anInt4514 / n15);
                        n27 = class236.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n5 + n23 + this.aFloat5267 * n6)) * this.aHa_Sub2_5269.anInt4490 / n15);
                        n28 = class236.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n5 + n22 + this.aFloat5276 * n7)) * this.aHa_Sub2_5269.anInt4514 / n16);
                        n29 = class236.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n5 + n23 + this.aFloat5267 * n7)) * this.aHa_Sub2_5269.anInt4490 / n16);
                        n30 = class236.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n4 + n22 + this.aFloat5276 * n7)) * this.aHa_Sub2_5269.anInt4514 / n17);
                        n31 = class236.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n4 + n23 + this.aFloat5267 * n7)) * this.aHa_Sub2_5269.anInt4490 / n17);
                    }
                    else {
                        n24 = class236.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n4 + n22 + this.aFloat5276 * n6)) * this.aHa_Sub2_5269.anInt4514 / this.anInt5262);
                        n25 = class236.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n4 + n23 + this.aFloat5267 * n6)) * this.aHa_Sub2_5269.anInt4490 / this.anInt5262);
                        n26 = class236.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n5 + n22 + this.aFloat5276 * n6)) * this.aHa_Sub2_5269.anInt4514 / this.anInt5262);
                        n27 = class236.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n5 + n23 + this.aFloat5267 * n6)) * this.aHa_Sub2_5269.anInt4490 / this.anInt5262);
                        n28 = class236.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n5 + n22 + this.aFloat5276 * n7)) * this.aHa_Sub2_5269.anInt4514 / this.anInt5262);
                        n29 = class236.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n5 + n23 + this.aFloat5267 * n7)) * this.aHa_Sub2_5269.anInt4490 / this.anInt5262);
                        n30 = class236.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n4 + n22 + this.aFloat5276 * n7)) * this.aHa_Sub2_5269.anInt4514 / this.anInt5262);
                        n31 = class236.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n4 + n23 + this.aFloat5267 * n7)) * this.aHa_Sub2_5269.anInt4490 / this.anInt5262);
                    }
                }
                else {
                    int n32 = super.anIntArrayArray2201[n][n2];
                    int n33 = super.anIntArrayArray2201[n + 1][n2];
                    int n34 = super.anIntArrayArray2201[n + 1][n2 + 1];
                    int n35 = super.anIntArrayArray2201[n][n2 + 1];
                    if (this.anInt5262 == -1) {
                        n14 = this.aFloat5274 + (this.aFloat5260 * n4 + this.aFloat5270 * n32 + this.aFloat5263 * n6);
                        if (n14 <= this.aHa_Sub2_5269.anInt4502) {
                            return;
                        }
                        n15 = this.aFloat5274 + (this.aFloat5260 * n5 + this.aFloat5270 * n33 + this.aFloat5263 * n6);
                        if (n15 <= this.aHa_Sub2_5269.anInt4502) {
                            return;
                        }
                        n16 = this.aFloat5274 + (this.aFloat5260 * n5 + this.aFloat5270 * n34 + this.aFloat5263 * n7);
                        if (n16 <= this.aHa_Sub2_5269.anInt4502) {
                            return;
                        }
                        n17 = this.aFloat5274 + (this.aFloat5260 * n4 + this.aFloat5270 * n35 + this.aFloat5263 * n7);
                        if (n17 <= this.aHa_Sub2_5269.anInt4502) {
                            return;
                        }
                    }
                    else {
                        n14 = this.aFloat5274 + (this.aFloat5260 * n4 + this.aFloat5270 * n32 + this.aFloat5263 * n6);
                        n15 = this.aFloat5274 + (this.aFloat5260 * n5 + this.aFloat5270 * n33 + this.aFloat5263 * n6);
                        n16 = this.aFloat5274 + (this.aFloat5260 * n5 + this.aFloat5270 * n34 + this.aFloat5263 * n7);
                        n17 = this.aFloat5274 + (this.aFloat5260 * n4 + this.aFloat5270 * n35 + this.aFloat5263 * n7);
                    }
                    if (b) {
                        int n36 = (int)(n14 - class235.anInt1761);
                        if (n36 > 255) {
                            n36 = 255;
                        }
                        if (n36 > 0) {
                            n8 = n36;
                            final short n37 = (short)(class237.aShort1489 * n36 / 255);
                            if (n37 > 0) {
                                n32 -= n37;
                            }
                        }
                        int n38 = (int)(n15 - class235.anInt1761);
                        if (n38 > 255) {
                            n38 = 255;
                        }
                        if (n38 > 0) {
                            n9 = n38;
                            final short n39 = (short)(class237.aShort1485 * n38 / 255);
                            if (n39 > 0) {
                                n33 -= n39;
                            }
                        }
                        int n40 = (int)(n16 - class235.anInt1761);
                        if (n40 > 255) {
                            n40 = 255;
                        }
                        if (n40 > 0) {
                            n10 = n40;
                            final short n41 = (short)(class237.aShort1486 * n40 / 255);
                            if (n41 > 0) {
                                n34 -= n41;
                            }
                        }
                        int n42 = (int)(n17 - class235.anInt1761);
                        if (n42 > 255) {
                            n42 = 255;
                        }
                        if (n42 > 0) {
                            n11 = n42;
                            final short n43 = (short)(class237.aShort1488 * n42 / 255);
                            if (n43 > 0) {
                                n35 -= n43;
                            }
                        }
                    }
                    else if (class235.aBoolean1758) {
                        final int n44 = (int)(n14 - class235.anInt1761);
                        if (n44 > 0) {
                            n8 = n44;
                            if (n8 > 255) {
                                n8 = 255;
                            }
                        }
                        final int n45 = (int)(n15 - class235.anInt1761);
                        if (n45 > 0) {
                            n9 = n45;
                            if (n9 > 255) {
                                n9 = 255;
                            }
                        }
                        final int n46 = (int)(n16 - class235.anInt1761);
                        if (n46 > 0) {
                            n10 = n46;
                            if (n10 > 255) {
                                n10 = 255;
                            }
                        }
                        final int n47 = (int)(n17 - class235.anInt1761);
                        if (n47 > 0) {
                            n11 = n47;
                            if (n11 > 255) {
                                n11 = 255;
                            }
                        }
                    }
                    if (this.anInt5262 == -1) {
                        n24 = class236.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n4 + this.aFloat5271 * n32 + this.aFloat5276 * n6)) * this.aHa_Sub2_5269.anInt4514 / n14);
                        n25 = class236.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n4 + this.aFloat5273 * n32 + this.aFloat5267 * n6)) * this.aHa_Sub2_5269.anInt4490 / n14);
                        n26 = class236.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n5 + this.aFloat5271 * n33 + this.aFloat5276 * n6)) * this.aHa_Sub2_5269.anInt4514 / n15);
                        n27 = class236.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n5 + this.aFloat5273 * n33 + this.aFloat5267 * n6)) * this.aHa_Sub2_5269.anInt4490 / n15);
                        n28 = class236.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n5 + this.aFloat5271 * n34 + this.aFloat5276 * n7)) * this.aHa_Sub2_5269.anInt4514 / n16);
                        n29 = class236.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n5 + this.aFloat5273 * n34 + this.aFloat5267 * n7)) * this.aHa_Sub2_5269.anInt4490 / n16);
                        n30 = class236.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n4 + this.aFloat5271 * n35 + this.aFloat5276 * n7)) * this.aHa_Sub2_5269.anInt4514 / n17);
                        n31 = class236.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n4 + this.aFloat5273 * n35 + this.aFloat5267 * n7)) * this.aHa_Sub2_5269.anInt4490 / n17);
                    }
                    else {
                        n24 = class236.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n4 + this.aFloat5271 * n32 + this.aFloat5276 * n6)) * this.aHa_Sub2_5269.anInt4514 / this.anInt5262);
                        n25 = class236.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n4 + this.aFloat5273 * n32 + this.aFloat5267 * n6)) * this.aHa_Sub2_5269.anInt4490 / this.anInt5262);
                        n26 = class236.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n5 + this.aFloat5271 * n33 + this.aFloat5276 * n6)) * this.aHa_Sub2_5269.anInt4514 / this.anInt5262);
                        n27 = class236.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n5 + this.aFloat5273 * n33 + this.aFloat5267 * n6)) * this.aHa_Sub2_5269.anInt4490 / this.anInt5262);
                        n28 = class236.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n5 + this.aFloat5271 * n34 + this.aFloat5276 * n7)) * this.aHa_Sub2_5269.anInt4514 / this.anInt5262);
                        n29 = class236.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n5 + this.aFloat5273 * n34 + this.aFloat5267 * n7)) * this.aHa_Sub2_5269.anInt4490 / this.anInt5262);
                        n30 = class236.anInt136 + (int)((this.aFloat5268 + (this.aFloat5275 * n4 + this.aFloat5271 * n35 + this.aFloat5276 * n7)) * this.aHa_Sub2_5269.anInt4514 / this.anInt5262);
                        n31 = class236.anInt128 + (int)((this.aFloat5255 + (this.aFloat5264 * n4 + this.aFloat5273 * n35 + this.aFloat5267 * n7)) * this.aHa_Sub2_5269.anInt4490 / this.anInt5262);
                    }
                }
                final boolean b2 = class237.aShort1487 != -1 && this.method3441(this.aHa_Sub2_5269.aD938.method11(class237.aShort1487, -28755).aByte1820);
                if (this.anInt5262 == -1) {
                    final int n48 = n9 + n10 + n11;
                    if ((n28 - n30) * (n27 - n31) - (n29 - n31) * (n26 - n30) > 0) {
                        class236.aBoolean135 = (n28 < 0 || n30 < 0 || n26 < 0 || n28 > class236.anInt141 || n30 > class236.anInt141 || n26 > class236.anInt141);
                        if (n48 < 765) {
                            if (n48 > 0) {
                                if (class237.aShort1487 >= 0) {
                                    int n49 = -16777216;
                                    if (b2) {
                                        n49 = -1694498816;
                                    }
                                    class236.method212(n29, n31, n27, n28, n30, n26, n16, n17, n15, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, n49 | (class237.anInt1493 & 0xFFFFFF), n49 | (class237.anInt1484 & 0xFFFFFF), n49 | (class237.anInt1491 & 0xFFFFFF), class235.anInt1763, n10, n11, n9, class237.aShort1487);
                                }
                                else {
                                    if (b2) {
                                        class236.anInt137 = 100;
                                    }
                                    class236.method206(n29, n31, n27, n28, n30, n26, (int)n16, (int)n17, (int)n15, Class98_Sub46_Sub5.method1544(n10 << 24 | class235.anInt1763, (byte)104, class237.anInt1493), Class98_Sub46_Sub5.method1544(n11 << 24 | class235.anInt1763, (byte)109, class237.anInt1484), Class98_Sub46_Sub5.method1544(n9 << 24 | class235.anInt1763, (byte)107, class237.anInt1491));
                                    class236.anInt137 = 0;
                                }
                            }
                            else if (class237.aShort1487 >= 0) {
                                int n50 = -16777216;
                                if (b2) {
                                    n50 = -1694498816;
                                }
                                class236.method212(n29, n31, n27, n28, n30, n26, n16, n17, n15, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, n50 | (class237.anInt1493 & 0xFFFFFF), n50 | (class237.anInt1484 & 0xFFFFFF), n50 | (class237.anInt1491 & 0xFFFFFF), 0, 0, 0, 0, class237.aShort1487);
                            }
                            else {
                                if (b2) {
                                    class236.anInt137 = 100;
                                }
                                class236.method206(n29, n31, n27, n28, n30, n26, (int)n16, (int)n17, (int)n15, class237.anInt1493, class237.anInt1484, class237.anInt1491);
                                class236.anInt137 = 0;
                            }
                        }
                        else {
                            class236.method208(n29, n31, n27, n28, n30, n26, (int)n16, (int)n17, (int)n15, class235.anInt1763);
                        }
                    }
                    final int n51 = n8 + n9 + n11;
                    if ((n24 - n26) * (n31 - n27) - (n25 - n27) * (n30 - n26) > 0) {
                        class236.aBoolean135 = (n24 < 0 || n26 < 0 || n30 < 0 || n24 > class236.anInt141 || n26 > class236.anInt141 || n30 > class236.anInt141);
                        if (n51 < 765) {
                            if (b2) {
                                class236.anInt137 = -1694498816;
                            }
                            if (n51 > 0) {
                                if (class237.aShort1487 >= 0) {
                                    int n52 = -16777216;
                                    if (b2) {
                                        n52 = -1694498816;
                                    }
                                    class236.method212(n25, n27, n31, n24, n26, n30, n14, n15, n17, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, n52 | (class237.anInt1490 & 0xFFFFFF), n52 | (class237.anInt1491 & 0xFFFFFF), n52 | (class237.anInt1484 & 0xFFFFFF), class235.anInt1763, n8, n9, n11, class237.aShort1487);
                                }
                                else {
                                    if (b2) {
                                        class236.anInt137 = 100;
                                    }
                                    class236.method206(n25, n27, n31, n24, n26, n30, (int)n14, (int)n15, (int)n17, Class98_Sub46_Sub5.method1544(n8 << 24 | class235.anInt1763, (byte)112, class237.anInt1490), Class98_Sub46_Sub5.method1544(n9 << 24 | class235.anInt1763, (byte)122, class237.anInt1491), Class98_Sub46_Sub5.method1544(n11 << 24 | class235.anInt1763, (byte)121, class237.anInt1484));
                                    class236.anInt137 = 0;
                                }
                            }
                            else if (class237.aShort1487 >= 0) {
                                int n53 = -16777216;
                                if (b2) {
                                    n53 = -1694498816;
                                }
                                class236.method212(n25, n27, n31, n24, n26, n30, n14, n15, n17, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, n53 | (class237.anInt1490 & 0xFFFFFF), n53 | (class237.anInt1491 & 0xFFFFFF), n53 | (class237.anInt1484 & 0xFFFFFF), 0, 0, 0, 0, class237.aShort1487);
                            }
                            else {
                                if (b2) {
                                    class236.anInt137 = 100;
                                }
                                class236.method206(n25, n27, n31, n24, n26, n30, (int)n14, (int)n15, (int)n17, class237.anInt1490, class237.anInt1491, class237.anInt1484);
                                class236.anInt137 = 0;
                            }
                        }
                        else {
                            class236.method208(n25, n27, n31, n24, n26, n30, (int)n14, (int)n15, (int)n17, class235.anInt1763);
                        }
                    }
                }
                else {
                    final int n54 = n9 + n10 + n11;
                    if ((n28 - n30) * (n27 - n31) - (n29 - n31) * (n26 - n30) > 0) {
                        class236.aBoolean135 = (n28 < 0 || n30 < 0 || n26 < 0 || n28 > class236.anInt141 || n30 > class236.anInt141 || n26 > class236.anInt141);
                        if (n54 < 765) {
                            if (b2) {
                                class236.anInt137 = -1694498816;
                            }
                            if (n54 > 0) {
                                if (class237.aShort1487 >= 0) {
                                    int n55 = -16777216;
                                    if (b2) {
                                        n55 = -1694498816;
                                    }
                                    class236.method212(n29, n31, n27, n28, n30, n26, n16, n17, n15, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, n55 | (class237.anInt1493 & 0xFFFFFF), n55 | (class237.anInt1484 & 0xFFFFFF), n55 | (class237.anInt1491 & 0xFFFFFF), class235.anInt1763, n10, n11, n9, class237.aShort1487);
                                }
                                else {
                                    if (b2) {
                                        class236.anInt137 = 100;
                                    }
                                    class236.method206(n29, n31, n27, n28, n30, n26, (int)n16, (int)n17, (int)n15, Class98_Sub46_Sub5.method1544(n10 << 24 | class235.anInt1763, (byte)127, class237.anInt1493), Class98_Sub46_Sub5.method1544(n11 << 24 | class235.anInt1763, (byte)124, class237.anInt1484), Class98_Sub46_Sub5.method1544(n9 << 24 | class235.anInt1763, (byte)114, class237.anInt1491));
                                    class236.anInt137 = 0;
                                }
                            }
                            else if (class237.aShort1487 >= 0) {
                                int n56 = -16777216;
                                if (b2) {
                                    n56 = -1694498816;
                                }
                                class236.method212(n29, n31, n27, n28, n30, n26, n16, n17, n15, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, n56 | (class237.anInt1493 & 0xFFFFFF), n56 | (class237.anInt1484 & 0xFFFFFF), n56 | (class237.anInt1491 & 0xFFFFFF), 0, 0, 0, 0, class237.aShort1487);
                            }
                            else {
                                if (b2) {
                                    class236.anInt137 = 100;
                                }
                                class236.method206(n29, n31, n27, n28, n30, n26, (int)n16, (int)n17, (int)n15, class237.anInt1493, class237.anInt1484, class237.anInt1491);
                                class236.anInt137 = 0;
                            }
                        }
                        else {
                            class236.method208(n29, n31, n27, n28, n30, n26, (int)n16, (int)n17, (int)n15, class235.anInt1763);
                        }
                    }
                    final int n57 = n8 + n9 + n11;
                    if ((n24 - n26) * (n31 - n27) - (n25 - n27) * (n30 - n26) > 0) {
                        class236.aBoolean135 = (n24 < 0 || n26 < 0 || n30 < 0 || n24 > class236.anInt141 || n26 > class236.anInt141 || n30 > class236.anInt141);
                        if (n57 < 765) {
                            if (b2) {
                                class236.anInt137 = -1694498816;
                            }
                            if (n57 > 0) {
                                if (class237.aShort1487 >= 0) {
                                    int n58 = -16777216;
                                    if (b2) {
                                        n58 = -1694498816;
                                    }
                                    class236.method212(n25, n27, n31, n24, n26, n30, n14, n15, n17, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, n58 | (class237.anInt1490 & 0xFFFFFF), n58 | (class237.anInt1491 & 0xFFFFFF), n58 | (class237.anInt1484 & 0xFFFFFF), class235.anInt1763, n8, n9, n11, class237.aShort1487);
                                }
                                else {
                                    if (b2) {
                                        class236.anInt137 = 100;
                                    }
                                    class236.method206(n25, n27, n31, n24, n26, n30, (int)n14, (int)n15, (int)n17, Class98_Sub46_Sub5.method1544(n8 << 24 | class235.anInt1763, (byte)105, class237.anInt1490), Class98_Sub46_Sub5.method1544(n9 << 24 | class235.anInt1763, (byte)118, class237.anInt1491), Class98_Sub46_Sub5.method1544(n11 << 24 | class235.anInt1763, (byte)105, class237.anInt1484));
                                    class236.anInt137 = 0;
                                }
                            }
                            else if (class237.aShort1487 >= 0) {
                                int n59 = -16777216;
                                if (b2) {
                                    n59 = -1694498816;
                                }
                                class236.method212(n25, n27, n31, n24, n26, n30, n14, n15, n17, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, n59 | (class237.anInt1490 & 0xFFFFFF), n59 | (class237.anInt1491 & 0xFFFFFF), n59 | (class237.anInt1484 & 0xFFFFFF), 0, 0, 0, 0, class237.aShort1487);
                            }
                            else {
                                if (b2) {
                                    class236.anInt137 = 100;
                                }
                                class236.method206(n25, n27, n31, n24, n26, n30, (int)n14, (int)n15, (int)n17, class237.anInt1490, class237.anInt1491, class237.anInt1484);
                                class236.anInt137 = 0;
                            }
                        }
                        else {
                            class236.method208(n25, n27, n31, n24, n26, n30, (int)n14, (int)n15, (int)n17, class235.anInt1763);
                        }
                    }
                }
            }
        }
        else {
            final Class285 class238 = this.aClass285ArrayArray5258[n][n2];
            if (class238 != null) {
                if (n3 != 0) {
                    if ((class238.aByte2172 & 0x4) != 0x0) {
                        if ((n3 & 0x1) != 0x0) {
                            return;
                        }
                    }
                    else if ((n3 & 0x2) != 0x0) {
                        return;
                    }
                }
                if (this.anInt5262 == -1) {
                    for (short n60 = 0; n60 < class238.aShort2174; ++n60) {
                        final int n61 = class238.aShortArray2176[n60] + (n << super.anInt2200);
                        int n62 = class238.aShortArray2179[n60];
                        final int n63 = class238.aShortArray2170[n60] + (n2 << super.anInt2200);
                        final float n64 = this.aFloat5274 + (this.aFloat5260 * n61 + this.aFloat5270 * n62 + this.aFloat5263 * n63);
                        if (n64 <= this.aHa_Sub2_5269.anInt4502) {
                            return;
                        }
                        array4[n60] = 0;
                        if (b) {
                            int n65 = (int)(n64 - class235.anInt1761);
                            if (n65 > 255) {
                                n65 = 255;
                            }
                            if (n65 > 0) {
                                array4[n60] = n65;
                                final short n66 = (short)(class238.aShortArray2178[n60] * n65 / 255);
                                if (n66 > 0) {
                                    n62 -= n66;
                                }
                            }
                        }
                        else if (class235.aBoolean1758) {
                            final int n67 = (int)(n64 - class235.anInt1761);
                            if (n67 > 0) {
                                array4[n60] = n67;
                                if (array4[n60] > 255) {
                                    array4[n60] = 255;
                                }
                            }
                        }
                        final float n68 = this.aFloat5268 + (this.aFloat5275 * n61 + this.aFloat5271 * n62 + this.aFloat5276 * n63);
                        final float n69 = this.aFloat5255 + (this.aFloat5264 * n61 + this.aFloat5273 * n62 + this.aFloat5267 * n63);
                        array[n60] = class236.anInt136 + (int)(n68 * this.aHa_Sub2_5269.anInt4514 / n64);
                        array2[n60] = class236.anInt128 + (int)(n69 * this.aHa_Sub2_5269.anInt4490 / n64);
                        array3[n60] = (int)n64;
                    }
                }
                else {
                    for (short n70 = 0; n70 < class238.aShort2174; ++n70) {
                        final int n71 = class238.aShortArray2176[n70] + (n << super.anInt2200);
                        int n72 = class238.aShortArray2179[n70];
                        final int n73 = class238.aShortArray2170[n70] + (n2 << super.anInt2200);
                        final float n74 = this.aFloat5274 + (this.aFloat5260 * n71 + this.aFloat5270 * n72 + this.aFloat5263 * n73);
                        array4[n70] = 0;
                        if (b) {
                            int n75 = this.anInt5262 - class235.anInt1761;
                            if (n75 > 255) {
                                n75 = 255;
                            }
                            if (n75 > 0) {
                                array4[n70] = n75;
                                final short n76 = (short)(class238.aShortArray2178[n70] * n75 / 255);
                                if (n76 > 0) {
                                    n72 -= n76;
                                }
                            }
                        }
                        else if (class235.aBoolean1758) {
                            final int n77 = this.anInt5262 - class235.anInt1761;
                            if (n77 > 0) {
                                array4[n70] = n77;
                                if (array4[n70] > 255) {
                                    array4[n70] = 255;
                                }
                            }
                        }
                        final float n78 = this.aFloat5268 + (this.aFloat5275 * n71 + this.aFloat5271 * n72 + this.aFloat5276 * n73);
                        final float n79 = this.aFloat5255 + (this.aFloat5264 * n71 + this.aFloat5273 * n72 + this.aFloat5267 * n73);
                        array[n70] = class236.anInt136 + (int)(n78 * this.aHa_Sub2_5269.anInt4514 / this.anInt5262);
                        array2[n70] = class236.anInt128 + (int)(n79 * this.aHa_Sub2_5269.anInt4490 / this.anInt5262);
                        array3[n70] = (int)n74;
                    }
                }
                if (class238.aShortArray2169 != null) {
                    if (this.anInt5262 == -1) {
                        for (short n80 = 0; n80 < class238.aShort2171; ++n80) {
                            final short n81 = (short)(n80 * 3);
                            final short n82 = (short)(n81 + 1);
                            final short n83 = (short)(n82 + 1);
                            final int n84 = array[n81];
                            final int n85 = array[n82];
                            final int n86 = array[n83];
                            final int n87 = array2[n81];
                            final int n88 = array2[n82];
                            final int n89 = array2[n83];
                            final int n90 = array4[n81] + array4[n82] + array4[n83];
                            if ((n84 - n85) * (n89 - n88) - (n87 - n88) * (n86 - n85) > 0) {
                                class236.aBoolean135 = (n84 < 0 || n85 < 0 || n86 < 0 || n84 > class236.anInt141 || n85 > class236.anInt141 || n86 > class236.anInt141);
                                final short n91 = class238.aShortArray2169[n80];
                                if (n90 < 765) {
                                    if (n90 > 0) {
                                        if (n91 != -1) {
                                            int n92 = -16777216;
                                            if (n91 != -1 && this.method3441(this.aHa_Sub2_5269.aD938.method11(n91, -28755).aByte1820)) {
                                                n92 = -1694498816;
                                            }
                                            class236.method212(n87, n88, n89, n84, n85, n86, array3[n81], array3[n82], array3[n83], class238.aShortArray2176[n81] / super.anInt2206, class238.aShortArray2176[n82] / super.anInt2206, class238.aShortArray2176[n83] / super.anInt2206, class238.aShortArray2170[n81] / super.anInt2206, class238.aShortArray2170[n82] / super.anInt2206, class238.aShortArray2170[n83] / super.anInt2206, n92 | (class238.anIntArray2173[n81] & 0xFFFFFF), n92 | (class238.anIntArray2173[n82] & 0xFFFFFF), n92 | (class238.anIntArray2173[n83] & 0xFFFFFF), class235.anInt1763, array4[n81], array4[n82], array4[n83], n91);
                                        }
                                        else if ((class238.anIntArray2173[n81] & 0xFFFFFF) != 0x0) {
                                            if (n91 != -1 && this.method3441(this.aHa_Sub2_5269.aD938.method11(n91, -28755).aByte1820)) {
                                                class236.anInt137 = -1694498816;
                                            }
                                            class236.method206(n87, n88, n89, n84, n85, n86, array3[n81], array3[n82], array3[n83], Class98_Sub46_Sub5.method1544(array4[n81] << 24 | class235.anInt1763, (byte)119, class238.anIntArray2173[n81]), Class98_Sub46_Sub5.method1544(array4[n82] << 24 | class235.anInt1763, (byte)120, class238.anIntArray2173[n82]), Class98_Sub46_Sub5.method1544(array4[n83] << 24 | class235.anInt1763, (byte)122, class238.anIntArray2173[n83]));
                                            class236.anInt137 = 0;
                                        }
                                    }
                                    else if (n91 != -1) {
                                        int n93 = -16777216;
                                        if (n91 != -1 && this.method3441(this.aHa_Sub2_5269.aD938.method11(n91, -28755).aByte1820)) {
                                            n93 = -1694498816;
                                        }
                                        class236.method212(n87, n88, n89, n84, n85, n86, array3[n81], array3[n82], array3[n83], class238.aShortArray2176[n81] / super.anInt2206, class238.aShortArray2176[n82] / super.anInt2206, class238.aShortArray2176[n83] / super.anInt2206, class238.aShortArray2170[n81] / super.anInt2206, class238.aShortArray2170[n82] / super.anInt2206, class238.aShortArray2170[n83] / super.anInt2206, n93 | (class238.anIntArray2173[n81] & 0xFFFFFF), n93 | (class238.anIntArray2173[n82] & 0xFFFFFF), n93 | (class238.anIntArray2173[n83] & 0xFFFFFF), 0, 0, 0, 0, n91);
                                    }
                                    else if ((class238.anIntArray2173[n81] & 0xFFFFFF) != 0x0) {
                                        if (n91 != -1 && this.method3441(this.aHa_Sub2_5269.aD938.method11(n91, -28755).aByte1820)) {
                                            class236.anInt137 = -1694498816;
                                        }
                                        class236.method206(n87, n88, n89, n84, n85, n86, array3[n81], array3[n82], array3[n83], class238.anIntArray2173[n81], class238.anIntArray2173[n82], class238.anIntArray2173[n83]);
                                        class236.anInt137 = 0;
                                    }
                                }
                                else {
                                    class236.method208(n87, n88, n89, n84, n85, n86, array3[n81], array3[n82], array3[n83], class235.anInt1763);
                                }
                            }
                        }
                    }
                    else {
                        for (short n94 = 0; n94 < class238.aShort2171; ++n94) {
                            final short n95 = (short)(n94 * 3);
                            final short n96 = (short)(n95 + 1);
                            final short n97 = (short)(n96 + 1);
                            final int n98 = array[n95];
                            final int n99 = array[n96];
                            final int n100 = array[n97];
                            final int n101 = array2[n95];
                            final int n102 = array2[n96];
                            final int n103 = array2[n97];
                            final int n104 = array4[n95] + array4[n96] + array4[n97];
                            if ((n98 - n99) * (n103 - n102) - (n101 - n102) * (n100 - n99) > 0) {
                                class236.aBoolean135 = (n98 < 0 || n99 < 0 || n100 < 0 || n98 > class236.anInt141 || n99 > class236.anInt141 || n100 > class236.anInt141);
                                final short n105 = class238.aShortArray2169[n94];
                                if (n104 < 765) {
                                    if (n105 != -1 && this.method3441(this.aHa_Sub2_5269.aD938.method11(n105, -28755).aByte1820)) {
                                        class236.anInt137 = -1694498816;
                                    }
                                    if (n104 > 0) {
                                        if (n105 != -1) {
                                            int n106 = -16777216;
                                            if (n105 != -1 && this.method3441(this.aHa_Sub2_5269.aD938.method11(n105, -28755).aByte1820)) {
                                                n106 = -1694498816;
                                            }
                                            class236.method212(n101, n102, n103, n98, n99, n100, array3[n95], array3[n96], array3[n97], class238.aShortArray2176[n95] / super.anInt2206, class238.aShortArray2176[n96] / super.anInt2206, class238.aShortArray2176[n97] / super.anInt2206, class238.aShortArray2170[n95] / super.anInt2206, class238.aShortArray2170[n96] / super.anInt2206, class238.aShortArray2170[n97] / super.anInt2206, n106 | (class238.anIntArray2173[n95] & 0xFFFFFF), n106 | (class238.anIntArray2173[n96] & 0xFFFFFF), n106 | (class238.anIntArray2173[n97] & 0xFFFFFF), class235.anInt1763, array4[n95], array4[n96], array4[n97], n105);
                                        }
                                        else if ((class238.anIntArray2173[n95] & 0xFFFFFF) != 0x0) {
                                            if (n105 != -1 && this.method3441(this.aHa_Sub2_5269.aD938.method11(n105, -28755).aByte1820)) {
                                                class236.anInt137 = -1694498816;
                                            }
                                            class236.method206(n101, n102, n103, n98, n99, n100, array3[n95], array3[n96], array3[n97], Class98_Sub46_Sub5.method1544(array4[n95] << 24 | class235.anInt1763, (byte)117, class238.anIntArray2173[n95]), Class98_Sub46_Sub5.method1544(array4[n96] << 24 | class235.anInt1763, (byte)104, class238.anIntArray2173[n96]), Class98_Sub46_Sub5.method1544(array4[n97] << 24 | class235.anInt1763, (byte)123, class238.anIntArray2173[n97]));
                                            class236.anInt137 = 0;
                                        }
                                    }
                                    else if (n105 != -1) {
                                        int n107 = -16777216;
                                        if (n105 != -1 && this.method3441(this.aHa_Sub2_5269.aD938.method11(n105, -28755).aByte1820)) {
                                            n107 = -1694498816;
                                        }
                                        class236.method212(n101, n102, n103, n98, n99, n100, array3[n95], array3[n96], array3[n97], class238.aShortArray2176[n95] / super.anInt2206, class238.aShortArray2176[n96] / super.anInt2206, class238.aShortArray2176[n97] / super.anInt2206, class238.aShortArray2170[n95] / super.anInt2206, class238.aShortArray2170[n96] / super.anInt2206, class238.aShortArray2170[n97] / super.anInt2206, n107 | (class238.anIntArray2173[n95] & 0xFFFFFF), n107 | (class238.anIntArray2173[n96] & 0xFFFFFF), n107 | (class238.anIntArray2173[n97] & 0xFFFFFF), 0, 0, 0, 0, n105);
                                    }
                                    else if ((class238.anIntArray2173[n95] & 0xFFFFFF) != 0x0) {
                                        if (n105 != -1 && this.method3441(this.aHa_Sub2_5269.aD938.method11(n105, -28755).aByte1820)) {
                                            class236.anInt137 = -1694498816;
                                        }
                                        class236.method206(n101, n102, n103, n98, n99, n100, array3[n95], array3[n96], array3[n97], class238.anIntArray2173[n95], class238.anIntArray2173[n96], class238.anIntArray2173[n97]);
                                        class236.anInt137 = 0;
                                    }
                                    class236.anInt137 = 0;
                                }
                                else {
                                    class236.method208(n101, n102, n103, n98, n99, n100, array3[n95], array3[n96], array3[n97], class235.anInt1763);
                                }
                            }
                        }
                    }
                }
                else {
                    for (short n108 = 0; n108 < class238.aShort2171; ++n108) {
                        final short n109 = (short)(n108 * 3);
                        final short n110 = (short)(n109 + 1);
                        final short n111 = (short)(n110 + 1);
                        final int n112 = array[n109];
                        final int n113 = array[n110];
                        final int n114 = array[n111];
                        final int n115 = array2[n109];
                        final int n116 = array2[n110];
                        final int n117 = array2[n111];
                        final int n118 = array4[n109] + array4[n110] + array4[n111];
                        if ((n112 - n113) * (n117 - n116) - (n115 - n116) * (n114 - n113) > 0) {
                            class236.aBoolean135 = (n112 < 0 || n113 < 0 || n114 < 0 || n112 > class236.anInt141 || n113 > class236.anInt141 || n114 > class236.anInt141);
                            if (n118 < 765) {
                                if (n118 > 0) {
                                    if ((class238.anIntArray2173[n109] & 0xFFFFFF) != 0x0) {
                                        class236.method206(n115, n116, n117, n112, n113, n114, array3[n109], array3[n110], array3[n111], Class215.method2790(126, array4[n109], class238.anIntArray2173[n109], class235.anInt1763), Class215.method2790(126, array4[n110], class238.anIntArray2173[n110], class235.anInt1763), Class215.method2790(127, array4[n111], class238.anIntArray2173[n111], class235.anInt1763));
                                    }
                                }
                                else if ((class238.anIntArray2173[n109] & 0xFFFFFF) != 0x0) {
                                    class236.method206(n115, n116, n117, n112, n113, n114, array3[n109], array3[n110], array3[n111], class238.anIntArray2173[n109], class238.anIntArray2173[n110], class238.anIntArray2173[n111]);
                                }
                            }
                            else {
                                class236.method208(n115, n116, n117, n112, n113, n114, array3[n109], array3[n110], array3[n111], class235.anInt1763);
                            }
                        }
                    }
                }
            }
        }
    }
    
    @Override
    final void method3426(final int n, final int n2, final int n3, final boolean[][] array, final boolean b, final int n4) {
        final Class111_Sub2 aClass111_Sub2_4513 = this.aHa_Sub2_5269.aClass111_Sub2_4513;
        this.anInt5262 = -1;
        this.aFloat5275 = aClass111_Sub2_4513.aFloat4700;
        this.aFloat5271 = aClass111_Sub2_4513.aFloat4699;
        this.aFloat5276 = aClass111_Sub2_4513.aFloat4690;
        this.aFloat5268 = aClass111_Sub2_4513.aFloat4697;
        this.aFloat5264 = aClass111_Sub2_4513.aFloat4692;
        this.aFloat5273 = aClass111_Sub2_4513.aFloat4688;
        this.aFloat5267 = aClass111_Sub2_4513.aFloat4696;
        this.aFloat5255 = aClass111_Sub2_4513.aFloat4691;
        this.aFloat5260 = aClass111_Sub2_4513.aFloat4693;
        this.aFloat5270 = aClass111_Sub2_4513.aFloat4698;
        this.aFloat5263 = aClass111_Sub2_4513.aFloat4694;
        this.aFloat5274 = aClass111_Sub2_4513.aFloat4689;
        for (int i = 0; i < n3 + n3; ++i) {
            for (int j = 0; j < n3 + n3; ++j) {
                if (array[i][j]) {
                    final int n5 = n - n3 + i;
                    final int n6 = n2 - n3 + j;
                    if (n5 >= 0 && n5 < super.anInt2203 && n6 >= 0 && n6 < super.anInt2204) {
                        this.method3443(n5, n6, n4);
                    }
                }
            }
        }
    }
    
    private final void method3443(final int n, final int n2, final int n3) {
        final Class235 method1921 = this.aHa_Sub2_5269.method1921(Thread.currentThread());
        method1921.aClass12_1767.anInt137 = 0;
        if (this.aClass193ArrayArray5272 != null) {
            this.method3442(n, n2, method1921.aBoolean1759, method1921, method1921.aClass12_1767, method1921.anIntArray1795, method1921.anIntArray1788, method1921.anIntArray1784, method1921.anIntArray1781, n3);
        }
        else if (this.aClass327ArrayArray5265 != null) {
            this.method3440(n, n2, method1921.aClass12_1767, method1921.anIntArray1795, method1921.anIntArray1788, method1921.anIntArray1784, method1921.anIntArray1781, n3);
        }
        else if (this.aClass120ArrayArray5266 != null) {
            this.method3438(n, n2, method1921.aBoolean1759, method1921, method1921.aClass12_1767, method1921.anIntArray1795, method1921.anIntArray1788, method1921.anIntArray1784, method1921.anIntArray1781, n3);
        }
    }
    
    @Override
    final void method3416(final int n, final int n2, final int n3, final boolean[][] array, final boolean b, final int anInt5262, final int n4) {
        final Class111_Sub2 aClass111_Sub2_4513 = this.aHa_Sub2_5269.aClass111_Sub2_4513;
        this.anInt5262 = anInt5262;
        this.aFloat5275 = aClass111_Sub2_4513.aFloat4700;
        this.aFloat5271 = aClass111_Sub2_4513.aFloat4699;
        this.aFloat5276 = aClass111_Sub2_4513.aFloat4690;
        this.aFloat5268 = aClass111_Sub2_4513.aFloat4697;
        this.aFloat5264 = aClass111_Sub2_4513.aFloat4692;
        this.aFloat5273 = aClass111_Sub2_4513.aFloat4688;
        this.aFloat5267 = aClass111_Sub2_4513.aFloat4696;
        this.aFloat5255 = aClass111_Sub2_4513.aFloat4691;
        this.aFloat5260 = aClass111_Sub2_4513.aFloat4693;
        this.aFloat5270 = aClass111_Sub2_4513.aFloat4698;
        this.aFloat5263 = aClass111_Sub2_4513.aFloat4694;
        this.aFloat5274 = aClass111_Sub2_4513.aFloat4689;
        for (int i = 0; i < n3 + n3; ++i) {
            for (int j = 0; j < n3 + n3; ++j) {
                if (array[i][j]) {
                    final int n5 = n - n3 + i;
                    final int n6 = n2 - n3 + j;
                    if (n5 >= 0 && n5 < super.anInt2203 && n6 >= 0 && n6 < super.anInt2204) {
                        this.method3443(n5, n6, n4);
                    }
                }
            }
        }
    }
    
    @Override
    final void method3424(final int n, final int n2, final int[] array, final int[] array2, final int[] array3, final int[] array4, final int[] array5, final int[] array6, final int[] array7, final int[] array8, final int[] array9, final int[] array10, final int[] array11, final int n3, final int n4, final int n5, final boolean b) {
        if (this.aClass327ArrayArray5265 == null) {
            this.aClass327ArrayArray5265 = new Class327[super.anInt2203][super.anInt2204];
            this.aClass328ArrayArray5261 = new Class328[super.anInt2203][super.anInt2204];
        }
        else if (this.aClass193ArrayArray5272 != null || this.aClass120ArrayArray5266 != null) {
            throw new IllegalStateException();
        }
        boolean b2 = false;
        if (array8.length == 2 && array5.length == 2 && (array8[0] == array8[1] || (array10[0] != -1 && array10[0] == array10[1]))) {
            b2 = true;
            for (int i = 1; i < 2; ++i) {
                final int n6 = array[array5[i]];
                final int n7 = array3[array5[i]];
                if ((n6 != 0 && n6 != super.anInt2206) || (n7 != 0 && n7 != super.anInt2206)) {
                    b2 = false;
                    break;
                }
            }
        }
        if (!b2) {
            final Class328 class328 = new Class328();
            final short aShort2748 = (short)array.length;
            final short n8 = (short)array8.length;
            class328.aShort2748 = aShort2748;
            class328.aShortArray2750 = new short[aShort2748];
            class328.aShortArray2755 = new short[aShort2748];
            class328.aShortArray2754 = new short[aShort2748];
            class328.aShortArray2757 = new short[aShort2748];
            for (short n9 = 0; n9 < aShort2748; ++n9) {
                final int n10 = array[n9];
                final int n11 = array3[n9];
                if (n10 == 0 && n11 == 0) {
                    class328.aShortArray2750[n9] = (short)(this.aByteArrayArray5257[n][n2] - this.aByteArrayArray5259[n][n2]);
                }
                else if (n10 == 0 && n11 == super.anInt2206) {
                    class328.aShortArray2750[n9] = (short)(this.aByteArrayArray5257[n][n2 + 1] - this.aByteArrayArray5259[n][n2 + 1]);
                }
                else if (n10 == super.anInt2206 && n11 == super.anInt2206) {
                    class328.aShortArray2750[n9] = (short)(this.aByteArrayArray5257[n + 1][n2 + 1] - this.aByteArrayArray5259[n + 1][n2 + 1]);
                }
                else if (n10 == super.anInt2206 && n11 == 0) {
                    class328.aShortArray2750[n9] = (short)(this.aByteArrayArray5257[n + 1][n2] - this.aByteArrayArray5259[n + 1][n2]);
                }
                else {
                    class328.aShortArray2750[n9] = (short)(((this.aByteArrayArray5257[n][n2] - this.aByteArrayArray5259[n][n2]) * (super.anInt2206 - n10) + (this.aByteArrayArray5257[n + 1][n2] - this.aByteArrayArray5259[n + 1][n2]) * n10) * (super.anInt2206 - n11) + ((this.aByteArrayArray5257[n][n2 + 1] - this.aByteArrayArray5259[n][n2 + 1]) * (super.anInt2206 - n10) + (this.aByteArrayArray5257[n + 1][n2 + 1] - this.aByteArrayArray5259[n + 1][n2 + 1]) * n10) * n11 >> 2 * super.anInt2200);
                }
                final int n12 = (n << super.anInt2200) + n10;
                final int n13 = (n2 << super.anInt2200) + n11;
                class328.aShortArray2755[n9] = (short)n10;
                class328.aShortArray2757[n9] = (short)n11;
                class328.aShortArray2754[n9] = (short)(this.method3417(n12, n13, true) + ((array2 != null) ? array2[n9] : 0));
                if (class328.aShortArray2750[n9] < 2) {
                    class328.aShortArray2750[n9] = 2;
                }
            }
            boolean b3 = false;
            int n14 = 0;
            for (short n15 = 0; n15 < n8; ++n15) {
                if (array8[n15] >= 0 || (array9 != null && array9[n15] >= 0)) {
                    ++n14;
                }
                final int n16 = array10[n15];
                if (n16 != -1) {
                    final Class238 method11 = this.aHa_Sub2_5269.aD938.method11(n16, -28755);
                    if (!method11.aBoolean1825) {
                        b3 = true;
                        if (this.method3441(method11.aByte1820) || method11.aByte1823 != 0 || method11.aByte1837 != 0) {
                            final Class328 class329 = class328;
                            class329.aByte2747 |= 0x4;
                        }
                    }
                }
            }
            class328.anIntArray2749 = new int[n14];
            if (array9 != null) {
                class328.anIntArray2756 = new int[n14];
            }
            class328.aShortArray2758 = new short[n14];
            class328.aShortArray2751 = new short[n14];
            class328.aShortArray2759 = new short[n14];
            if (b3) {
                class328.aShortArray2752 = new short[n14];
                class328.aShortArray2753 = new short[n14];
            }
            for (short n17 = 0; n17 < n8; ++n17) {
                if (array8[n17] >= 0 || (array9 != null && array9[n17] >= 0)) {
                    if (array8[n17] >= 0) {
                        class328.anIntArray2749[class328.aShort2746] = Class111_Sub2.method2117(array8[n17], -107);
                    }
                    else {
                        class328.anIntArray2749[class328.aShort2746] = -1;
                    }
                    if (array9 != null) {
                        if (array9[n17] != -1) {
                            class328.anIntArray2756[class328.aShort2746] = Class111_Sub2.method2117(array9[n17], 110);
                        }
                        else {
                            class328.anIntArray2756[class328.aShort2746] = -1;
                        }
                    }
                    class328.aShortArray2758[class328.aShort2746] = (short)array5[n17];
                    class328.aShortArray2751[class328.aShort2746] = (short)array6[n17];
                    class328.aShortArray2759[class328.aShort2746] = (short)array7[n17];
                    if (b3) {
                        if (array10[n17] != -1 && !this.aHa_Sub2_5269.aD938.method11(array10[n17], -28755).aBoolean1825) {
                            class328.aShortArray2752[class328.aShort2746] = (short)array10[n17];
                            class328.aShortArray2753[class328.aShort2746] = (short)array11[n17];
                        }
                        else {
                            class328.aShortArray2752[class328.aShort2746] = -1;
                        }
                    }
                    final Class328 class330 = class328;
                    ++class330.aShort2746;
                }
            }
            this.aClass328ArrayArray5261[n][n2] = class328;
        }
        else if (array8[0] >= 0 || (array9 != null && array9[0] >= 0)) {
            final Class327 class331 = new Class327();
            final int n18 = array8[0];
            final int n19 = array10[0];
            if (array9 != null) {
                class331.anInt2743 = Class246_Sub7.method3132(this.aByteArrayArray5257[n][n2] - this.aByteArrayArray5259[n][n2], (byte)118, Class111_Sub2.method2117(array9[0], -112));
                if (n18 == -1) {
                    final Class327 class332 = class331;
                    class332.aByte2740 |= 0x2;
                }
            }
            if (super.anIntArrayArray2201[n][n2] == super.anIntArrayArray2201[n + 1][n2] && super.anIntArrayArray2201[n][n2] == super.anIntArrayArray2201[n + 1][n2 + 1] && super.anIntArrayArray2201[n][n2] == super.anIntArrayArray2201[n][n2 + 1]) {
                final Class327 class333 = class331;
                class333.aByte2740 |= 0x1;
            }
            Class238 method12 = null;
            if (n19 != -1) {
                method12 = this.aHa_Sub2_5269.aD938.method11(n19, -28755);
            }
            if (method12 != null && (class331.aByte2740 & 0x2) == 0x0 && !method12.aBoolean1825) {
                class331.aShort2741 = (short)(this.aByteArrayArray5257[n][n2] - this.aByteArrayArray5259[n][n2]);
                class331.aShort2739 = (short)(this.aByteArrayArray5257[n + 1][n2] - this.aByteArrayArray5259[n + 1][n2]);
                class331.aShort2745 = (short)(this.aByteArrayArray5257[n + 1][n2 + 1] - this.aByteArrayArray5259[n + 1][n2 + 1]);
                class331.aShort2742 = (short)(this.aByteArrayArray5257[n][n2 + 1] - this.aByteArrayArray5259[n][n2 + 1]);
                class331.aShort2744 = (short)n19;
                if (this.method3441(method12.aByte1820) || method12.aByte1823 != 0 || method12.aByte1837 != 0) {
                    final Class327 class334 = class331;
                    class334.aByte2740 |= 0x4;
                }
            }
            else {
                final short method13 = Class111_Sub2.method2117(n18, -105);
                class331.aShort2741 = (short)Class246_Sub7.method3132(this.aByteArrayArray5257[n][n2] - this.aByteArrayArray5259[n][n2], (byte)118, method13);
                class331.aShort2739 = (short)Class246_Sub7.method3132(this.aByteArrayArray5257[n + 1][n2] - this.aByteArrayArray5259[n + 1][n2], (byte)118, method13);
                class331.aShort2745 = (short)Class246_Sub7.method3132(this.aByteArrayArray5257[n + 1][n2 + 1] - this.aByteArrayArray5259[n + 1][n2 + 1], (byte)118, method13);
                class331.aShort2742 = (short)Class246_Sub7.method3132(this.aByteArrayArray5257[n][n2 + 1] - this.aByteArrayArray5259[n][n2 + 1], (byte)118, method13);
                class331.aShort2744 = -1;
            }
            this.aClass327ArrayArray5265[n][n2] = class331;
        }
    }
    
    @Override
    final void wa(final r r, final int n, final int n2, final int n3, final int n4, final boolean b) {
    }
    
    private final void method3444(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final boolean[][] array, final Class235 class235, final Class12 class236, final int[] array2, final int[] array3) {
        final int n8 = (n7 - n5) * n3 / 256;
        final int n9 = n3 >> 8;
        final boolean aBoolean1762 = class235.aBoolean1762;
        this.aHa_Sub2_5269.C(false);
        class236.aBoolean134 = false;
        class236.aBoolean130 = false;
        int n10 = n;
        int n11 = n2 + n8;
        for (int i = n4; i < n6; ++i) {
            for (int j = n5; j < n7; ++j) {
                if (array[i - n4][j - n5]) {
                    if (this.aClass193ArrayArray5272 != null) {
                        if (this.aClass193ArrayArray5272[i][j] != null) {
                            final Class193 class237 = this.aClass193ArrayArray5272[i][j];
                            if (class237.aShort1487 != -1 && (class237.aByte1492 & 0x2) == 0x0 && class237.anInt1494 == 0) {
                                final int method1926 = this.aHa_Sub2_5269.method1926(class237.aShort1487);
                                class236.method216(n11 - n9, n11 - n9, n11, n10 + n9, n10, n10 + n9, 100.0f, 100.0f, 100.0f, Class246_Sub7.method3132(class237.anInt1493, (byte)118, method1926), Class246_Sub7.method3132(class237.anInt1484, (byte)118, method1926), Class246_Sub7.method3132(class237.anInt1491, (byte)118, method1926));
                                class236.method216(n11, n11, n11 - n9, n10, n10 + n9, n10, 100.0f, 100.0f, 100.0f, Class246_Sub7.method3132(class237.anInt1490, (byte)118, method1926), Class246_Sub7.method3132(class237.anInt1491, (byte)118, method1926), Class246_Sub7.method3132(class237.anInt1484, (byte)118, method1926));
                            }
                            else if (class237.anInt1494 == 0) {
                                class236.method206(n11 - n9, n11 - n9, n11, n10 + n9, n10, n10 + n9, 100.0f, 100.0f, 100.0f, class237.anInt1493, class237.anInt1484, class237.anInt1491);
                                class236.method206(n11, n11, n11 - n9, n10, n10 + n9, n10, 100.0f, 100.0f, 100.0f, class237.anInt1490, class237.anInt1491, class237.anInt1484);
                            }
                            else {
                                final int anInt1494 = class237.anInt1494;
                                class236.method206(n11 - n9, n11 - n9, n11, n10 + n9, n10, n10 + n9, 100.0f, 100.0f, 100.0f, Class98_Sub46_Sub5.method1544(class237.anInt1493 & 0xFF000000, (byte)105, anInt1494), Class98_Sub46_Sub5.method1544(class237.anInt1484 & 0xFF000000, (byte)120, anInt1494), Class98_Sub46_Sub5.method1544(class237.anInt1491 & 0xFF000000, (byte)120, anInt1494));
                                class236.method206(n11, n11, n11 - n9, n10, n10 + n9, n10, 100.0f, 100.0f, 100.0f, Class98_Sub46_Sub5.method1544(class237.anInt1490 & 0xFF000000, (byte)117, anInt1494), Class98_Sub46_Sub5.method1544(class237.anInt1491 & 0xFF000000, (byte)115, anInt1494), Class98_Sub46_Sub5.method1544(class237.anInt1484 & 0xFF000000, (byte)113, anInt1494));
                            }
                        }
                        else if (this.aClass285ArrayArray5258[i][j] != null) {
                            final Class285 class238 = this.aClass285ArrayArray5258[i][j];
                            for (short n12 = 0; n12 < class238.aShort2174; ++n12) {
                                array2[n12] = n10 + class238.aShortArray2176[n12] * n9 / super.anInt2206;
                                array3[n12] = n11 - class238.aShortArray2170[n12] * n9 / super.anInt2206;
                            }
                            for (short n13 = 0; n13 < class238.aShort2171; ++n13) {
                                final short n14 = (short)(n13 * 3);
                                final short n15 = (short)(n14 + 1);
                                final short n16 = (short)(n15 + 1);
                                final int n17 = array2[n14];
                                final int n18 = array2[n15];
                                final int n19 = array2[n16];
                                final int n20 = array3[n14];
                                final int n21 = array3[n15];
                                final int n22 = array3[n16];
                                if (class238.anIntArray2175 != null && class238.anIntArray2175[n13] != 0 && (class238.aShortArray2169 == null || (class238.aShortArray2169 != null && class238.aShortArray2169[n13] == -1))) {
                                    final int n23 = class238.anIntArray2175[n13];
                                    class236.method206(n20, n21, n22, n17, n18, n19, 100.0f, 100.0f, 100.0f, Class98_Sub46_Sub5.method1544(-16777216 - (class238.anIntArray2173[n14] & 0xFF000000), (byte)125, n23), Class98_Sub46_Sub5.method1544(-16777216 - (class238.anIntArray2173[n15] & 0xFF000000), (byte)124, n23), Class98_Sub46_Sub5.method1544(-16777216 - (class238.anIntArray2173[n16] & 0xFF000000), (byte)111, n23));
                                }
                                else if (class238.aShortArray2169 != null && class238.aShortArray2169[n13] != -1) {
                                    final int method1927 = this.aHa_Sub2_5269.method1926(class238.aShortArray2169[n13]);
                                    class236.method216(n20, n21, n22, n17, n18, n19, 100.0f, 100.0f, 100.0f, method1927, method1927, method1927);
                                }
                                else {
                                    class236.method206(n20, n21, n22, n17, n18, n19, 100.0f, 100.0f, 100.0f, class238.anIntArray2173[n14], class238.anIntArray2173[n15], class238.anIntArray2173[n16]);
                                }
                            }
                        }
                    }
                    else if (this.aClass120ArrayArray5266[i][j] != null) {
                        final Class120 class239 = this.aClass120ArrayArray5266[i][j];
                        for (short n24 = 0; n24 < class239.aShort994; ++n24) {
                            array2[n24] = n10 + class239.aShortArray995[n24] * n9 / super.anInt2206;
                            array3[n24] = n11 - class239.aShortArray992[n24] * n9 / super.anInt2206;
                        }
                        for (short n25 = 0; n25 < class239.aShort991; ++n25) {
                            final short n26 = (short)(n25 * 3);
                            final short n27 = (short)(n26 + 1);
                            final short n28 = (short)(n27 + 1);
                            final int n29 = array2[n26];
                            final int n30 = array2[n27];
                            final int n31 = array2[n28];
                            final int n32 = array3[n26];
                            final int n33 = array3[n27];
                            final int n34 = array3[n28];
                            if (class239.anIntArray997 != null && class239.anIntArray997[n25] != 0) {
                                final int n35 = class239.anIntArray997[n25];
                                class236.method206(n32, n33, n34, n29, n30, n31, 100.0f, 100.0f, 100.0f, n35, n35, n35);
                            }
                            else {
                                class236.method206(n32, n33, n34, n29, n30, n31, 100.0f, 100.0f, 100.0f, class239.anIntArray999[n26], class239.anIntArray999[n27], class239.anIntArray999[n28]);
                            }
                        }
                    }
                }
                n11 -= n9;
            }
            n11 = n2 + n8;
            n10 += n9;
        }
        class236.aBoolean134 = true;
        this.aHa_Sub2_5269.C(aBoolean1762);
    }
    
    @Override
    final void CA(final r r, final int n, final int n2, final int n3, final int n4, final boolean b) {
    }
}
