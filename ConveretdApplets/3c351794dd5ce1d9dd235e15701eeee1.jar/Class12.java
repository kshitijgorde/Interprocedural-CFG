// 
// Decompiled by Procyon v0.5.30
// 

final class Class12
{
    private int anInt127;
    int anInt128;
    int anInt129;
    boolean aBoolean130;
    private ha_Sub2 aHa_Sub2_131;
    private float[] aFloatArray132;
    int[] anIntArray133;
    boolean aBoolean134;
    boolean aBoolean135;
    int anInt136;
    int anInt137;
    private Class235 aClass235_138;
    private int[] anIntArray139;
    private boolean aBoolean140;
    int anInt141;
    private boolean aBoolean142;
    private int[] anIntArray143;
    private int anInt144;
    private int[] anIntArray145;
    private int anInt146;
    private int anInt147;
    private int anInt148;
    private float aFloat149;
    private float aFloat150;
    private int anInt151;
    private int anInt152;
    private int anInt153;
    private boolean aBoolean154;
    private int anInt155;
    private int[] anIntArray156;
    private float aFloat157;
    private int anInt158;
    private int anInt159;
    private int anInt160;
    
    private final void method204(final int[] array, final float[] array2, int n, int n2, int n3, int n4, int anInt141, float n5, float n6, float n7, final float n8) {
        if (this.aBoolean135) {
            if (anInt141 > this.anInt141) {
                anInt141 = this.anInt141;
            }
            if (n4 < 0) {
                n4 = 0;
            }
        }
        if (n4 < anInt141) {
            n += n4 - 1;
            n5 += n6 * n4;
            n7 += n8 * n4;
            if (this.aClass235_138.aBoolean1762) {
                if (this.aBoolean134) {
                    n3 = anInt141 - n4 >> 2;
                    n6 *= 4.0f;
                    if (this.anInt137 == 0) {
                        if (n3 > 0) {
                            do {
                                n2 = Class221.anIntArray1665[(int)n5];
                                n5 += n6;
                                if (n7 < array2[++n]) {
                                    array[n] = n2;
                                    array2[n] = n7;
                                }
                                n7 += n8;
                                if (n7 < array2[++n]) {
                                    array[n] = n2;
                                    array2[n] = n7;
                                }
                                n7 += n8;
                                if (n7 < array2[++n]) {
                                    array[n] = n2;
                                    array2[n] = n7;
                                }
                                n7 += n8;
                                if (n7 < array2[++n]) {
                                    array[n] = n2;
                                    array2[n] = n7;
                                }
                                n7 += n8;
                            } while (--n3 > 0);
                        }
                        n3 = (anInt141 - n4 & 0x3);
                        if (n3 > 0) {
                            n2 = Class221.anIntArray1665[(int)n5];
                            do {
                                if (n7 < array2[++n]) {
                                    array[n] = n2;
                                    array2[n] = n7;
                                }
                                n7 += n8;
                            } while (--n3 > 0);
                        }
                    }
                    else {
                        final int anInt142 = this.anInt137;
                        final int n9 = 256 - this.anInt137;
                        if (n3 > 0) {
                            do {
                                n2 = Class221.anIntArray1665[(int)n5];
                                n5 += n6;
                                n2 = ((n2 & 0xFF00FF) * n9 >> 8 & 0xFF00FF) + ((n2 & 0xFF00) * n9 >> 8 & 0xFF00);
                                if (n7 < array2[++n]) {
                                    final int n10 = array[n];
                                    array[n] = n2 + ((n10 & 0xFF00FF) * anInt142 >> 8 & 0xFF00FF) + ((n10 & 0xFF00) * anInt142 >> 8 & 0xFF00);
                                    array2[n] = n7;
                                }
                                n7 += n8;
                                if (n7 < array2[++n]) {
                                    final int n11 = array[n];
                                    array[n] = n2 + ((n11 & 0xFF00FF) * anInt142 >> 8 & 0xFF00FF) + ((n11 & 0xFF00) * anInt142 >> 8 & 0xFF00);
                                    array2[n] = n7;
                                }
                                n7 += n8;
                                if (n7 < array2[++n]) {
                                    final int n12 = array[n];
                                    array[n] = n2 + ((n12 & 0xFF00FF) * anInt142 >> 8 & 0xFF00FF) + ((n12 & 0xFF00) * anInt142 >> 8 & 0xFF00);
                                    array2[n] = n7;
                                }
                                n7 += n8;
                                if (n7 < array2[++n]) {
                                    final int n13 = array[n];
                                    array[n] = n2 + ((n13 & 0xFF00FF) * anInt142 >> 8 & 0xFF00FF) + ((n13 & 0xFF00) * anInt142 >> 8 & 0xFF00);
                                    array2[n] = n7;
                                }
                                n7 += n8;
                            } while (--n3 > 0);
                        }
                        n3 = (anInt141 - n4 & 0x3);
                        if (n3 > 0) {
                            n2 = Class221.anIntArray1665[(int)n5];
                            n2 = ((n2 & 0xFF00FF) * n9 >> 8 & 0xFF00FF) + ((n2 & 0xFF00) * n9 >> 8 & 0xFF00);
                            do {
                                if (n7 < array2[++n]) {
                                    final int n14 = array[n];
                                    array[n] = n2 + ((n14 & 0xFF00FF) * anInt142 >> 8 & 0xFF00FF) + ((n14 & 0xFF00) * anInt142 >> 8 & 0xFF00);
                                    array2[n] = n7;
                                }
                                n7 += n8;
                            } while (--n3 > 0);
                        }
                    }
                }
                else {
                    n3 = anInt141 - n4;
                    if (this.anInt137 == 0) {
                        do {
                            if (n7 < array2[++n]) {
                                array[n] = Class221.anIntArray1665[(int)n5];
                                array2[n] = n7;
                            }
                            n7 += n8;
                            n5 += n6;
                        } while (--n3 > 0);
                    }
                    else {
                        final int anInt143 = this.anInt137;
                        final int n15 = 256 - this.anInt137;
                        do {
                            if (n7 < array2[++n]) {
                                n2 = Class221.anIntArray1665[(int)n5];
                                n2 = ((n2 & 0xFF00FF) * n15 >> 8 & 0xFF00FF) + ((n2 & 0xFF00) * n15 >> 8 & 0xFF00);
                                final int n16 = array[n];
                                array[n] = n2 + ((n16 & 0xFF00FF) * anInt143 >> 8 & 0xFF00FF) + ((n16 & 0xFF00) * anInt143 >> 8 & 0xFF00);
                                array2[n] = n7;
                            }
                            n5 += n6;
                            n7 += n8;
                        } while (--n3 > 0);
                    }
                }
            }
            else if (this.aBoolean134) {
                n3 = anInt141 - n4 >> 2;
                n6 *= 4.0f;
                if (this.anInt137 == 0) {
                    if (n3 > 0) {
                        do {
                            n2 = Class221.anIntArray1665[(int)n5];
                            n5 += n6;
                            if (n7 < array2[++n]) {
                                array[n] = n2;
                            }
                            n7 += n8;
                            if (n7 < array2[++n]) {
                                array[n] = n2;
                            }
                            n7 += n8;
                            if (n7 < array2[++n]) {
                                array[n] = n2;
                            }
                            n7 += n8;
                            if (n7 < array2[++n]) {
                                array[n] = n2;
                            }
                            n7 += n8;
                        } while (--n3 > 0);
                    }
                    n3 = (anInt141 - n4 & 0x3);
                    if (n3 > 0) {
                        n2 = Class221.anIntArray1665[(int)n5];
                        do {
                            if (n7 < array2[++n]) {
                                array[n] = n2;
                            }
                            n7 += n8;
                        } while (--n3 > 0);
                    }
                }
                else {
                    final int anInt144 = this.anInt137;
                    final int n17 = 256 - this.anInt137;
                    if (n3 > 0) {
                        do {
                            n2 = Class221.anIntArray1665[(int)n5];
                            n5 += n6;
                            n2 = ((n2 & 0xFF00FF) * n17 >> 8 & 0xFF00FF) + ((n2 & 0xFF00) * n17 >> 8 & 0xFF00);
                            if (n7 < array2[++n]) {
                                final int n18 = array[n];
                                array[n] = n2 + ((n18 & 0xFF00FF) * anInt144 >> 8 & 0xFF00FF) + ((n18 & 0xFF00) * anInt144 >> 8 & 0xFF00);
                            }
                            n7 += n8;
                            if (n7 < array2[++n]) {
                                final int n19 = array[n];
                                array[n] = n2 + ((n19 & 0xFF00FF) * anInt144 >> 8 & 0xFF00FF) + ((n19 & 0xFF00) * anInt144 >> 8 & 0xFF00);
                            }
                            n7 += n8;
                            if (n7 < array2[++n]) {
                                final int n20 = array[n];
                                array[n] = n2 + ((n20 & 0xFF00FF) * anInt144 >> 8 & 0xFF00FF) + ((n20 & 0xFF00) * anInt144 >> 8 & 0xFF00);
                            }
                            n7 += n8;
                            if (n7 < array2[++n]) {
                                final int n21 = array[n];
                                array[n] = n2 + ((n21 & 0xFF00FF) * anInt144 >> 8 & 0xFF00FF) + ((n21 & 0xFF00) * anInt144 >> 8 & 0xFF00);
                            }
                            n7 += n8;
                        } while (--n3 > 0);
                    }
                    n3 = (anInt141 - n4 & 0x3);
                    if (n3 > 0) {
                        n2 = Class221.anIntArray1665[(int)n5];
                        n2 = ((n2 & 0xFF00FF) * n17 >> 8 & 0xFF00FF) + ((n2 & 0xFF00) * n17 >> 8 & 0xFF00);
                        do {
                            if (n7 < array2[++n]) {
                                final int n22 = array[n];
                                array[n] = n2 + ((n22 & 0xFF00FF) * anInt144 >> 8 & 0xFF00FF) + ((n22 & 0xFF00) * anInt144 >> 8 & 0xFF00);
                            }
                            n7 += n8;
                        } while (--n3 > 0);
                    }
                }
            }
            else {
                n3 = anInt141 - n4;
                if (this.anInt137 == 0) {
                    do {
                        if (n7 < array2[++n]) {
                            array[n] = Class221.anIntArray1665[(int)n5];
                        }
                        n7 += n8;
                        n5 += n6;
                    } while (--n3 > 0);
                }
                else {
                    final int anInt145 = this.anInt137;
                    final int n23 = 256 - this.anInt137;
                    do {
                        if (n7 < array2[++n]) {
                            n2 = Class221.anIntArray1665[(int)n5];
                            n2 = ((n2 & 0xFF00FF) * n23 >> 8 & 0xFF00FF) + ((n2 & 0xFF00) * n23 >> 8 & 0xFF00);
                            final int n24 = array[n];
                            array[n] = n2 + ((n24 & 0xFF00FF) * anInt145 >> 8 & 0xFF00FF) + ((n24 & 0xFF00) * anInt145 >> 8 & 0xFF00);
                        }
                        n5 += n6;
                        n7 += n8;
                    } while (--n3 > 0);
                }
            }
        }
    }
    
    private final void method205(final int[] array, final int[] array2, int n, int n2, int anInt141, float n3, final float n4, float n5, final float n6, float n7, final float n8, float n9, final float n10, float n11, final float n12, float n13, final float n14, float n15, final float n16, float n17, final float n18) {
        final float n19 = 1.0f / (anInt141 - n2);
        final float n20 = (n4 - n3) * n19;
        final float n21 = (n6 - n5) * n19;
        final float n22 = (n8 - n7) * n19;
        final float n23 = (n10 - n9) * n19;
        final float n24 = (n12 - n11) * n19;
        final float n25 = (n14 - n13) * n19;
        final float n26 = (n16 - n15) * n19;
        final float n27 = (n18 - n17) * n19;
        if (this.aBoolean135) {
            if (anInt141 > this.anInt141) {
                anInt141 = this.anInt141;
            }
            if (n2 < 0) {
                n3 -= n20 * n2;
                n5 -= n21 * n2;
                n7 -= n22 * n2;
                n9 -= n23 * n2;
                n11 -= n24 * n2;
                n13 -= n25 * n2;
                n15 -= n26 * n2;
                n17 -= n27 * n2;
                n2 = 0;
            }
        }
        if (n2 < anInt141) {
            int n28 = anInt141 - n2;
            n += n2;
            while (n28-- > 0) {
                final float n29 = 1.0f / n3;
                if (n29 < this.aFloatArray132[n]) {
                    int anInt142 = (int)(n5 * n29 * this.anInt151);
                    if (this.aBoolean154) {
                        anInt142 &= this.anInt146;
                    }
                    else if (anInt142 < 0) {
                        anInt142 = 0;
                    }
                    else if (anInt142 > this.anInt146) {
                        anInt142 = this.anInt146;
                    }
                    int anInt143 = (int)(n7 * n29 * this.anInt151);
                    if (this.aBoolean154) {
                        anInt143 &= this.anInt146;
                    }
                    else if (anInt143 < 0) {
                        anInt143 = 0;
                    }
                    else if (anInt143 > this.anInt146) {
                        anInt143 = this.anInt146;
                    }
                    final int n30 = this.anIntArray143[anInt143 * this.anInt151 + anInt142];
                    int n31;
                    if (this.anInt147 == 2) {
                        n31 = (n30 >> 24 & 0xFF);
                    }
                    else if (this.anInt147 == 1) {
                        n31 = ((n30 == 0) ? 0 : 255);
                    }
                    else {
                        n31 = (int)n11;
                    }
                    if (n31 != 0) {
                        if (n31 != 255) {
                            int n32 = 0xFF000000 | ((int)(n13 * (n30 >> 16 & 0xFF)) << 8 & 0xFF0000) | ((int)(n15 * (n30 >> 8 & 0xFF)) & 0xFF00) | (int)(n17 * (n30 & 0xFF)) >> 8;
                            if (n9 != 0.0f) {
                                final int n33 = (int)(255.0f - n9);
                                n32 = ((((n32 & 0xFF00FF) * n33 & 0xFF00FF00) | ((n32 & 0xFF00) * n33 & 0xFF0000)) >>> 8) + ((((this.anInt155 & 0xFF00FF) * (int)n9 & 0xFF00FF00) | ((this.anInt155 & 0xFF00) * (int)n9 & 0xFF0000)) >>> 8);
                            }
                            final int n34 = array[n];
                            final int n35 = 255 - n31;
                            array[n] = ((n34 & 0xFF00FF) * n35 + (n32 & 0xFF00FF) * n31 & 0xFF00FF00) + ((n34 & 0xFF00) * n35 + (n32 & 0xFF00) * n31 & 0xFF0000) >> 8;
                            this.aFloatArray132[n] = n29;
                        }
                        else {
                            int n36 = 0xFF000000 | ((int)(n13 * (n30 >> 16 & 0xFF)) << 8 & 0xFF0000) | ((int)(n15 * (n30 >> 8 & 0xFF)) & 0xFF00) | (int)(n17 * (n30 & 0xFF)) >> 8;
                            if (n9 != 0.0f) {
                                final int n37 = (int)(255.0f - n9);
                                n36 = ((((n36 & 0xFF00FF) * n37 & 0xFF00FF00) | ((n36 & 0xFF00) * n37 & 0xFF0000)) >>> 8) + ((((this.anInt155 & 0xFF00FF) * (int)n9 & 0xFF00FF00) | ((this.anInt155 & 0xFF00) * (int)n9 & 0xFF0000)) >>> 8);
                            }
                            array[n] = n36;
                            this.aFloatArray132[n] = n29;
                        }
                    }
                }
                ++n;
                n3 += n20;
                n5 += n21;
                n7 += n22;
                n9 += n23;
                n11 += n24;
                n13 += n25;
                n15 += n26;
                n17 += n27;
            }
        }
    }
    
    final void method206(float n, float n2, float n3, float n4, float n5, float n6, float n7, float n8, float n9, final int n10, final int n11, final int n12) {
        if (this.aBoolean142) {
            this.aHa_Sub2_131.method1789((int)n, 0xFF000000 | n10, (int)n2, (int)n5, -10550, (int)n4);
            this.aHa_Sub2_131.method1789((int)n2, 0xFF000000 | n10, (int)n3, (int)n6, -10550, (int)n5);
            this.aHa_Sub2_131.method1789((int)n3, 0xFF000000 | n10, (int)n, (int)n4, -10550, (int)n6);
        }
        else {
            final float n13 = n5 - n4;
            final float n14 = n2 - n;
            final float n15 = n6 - n4;
            final float n16 = n3 - n;
            final float n17 = n8 - n7;
            final float n18 = n9 - n7;
            final float n19 = (n11 & 0xFF0000) - (n10 & 0xFF0000);
            final float n20 = (n12 & 0xFF0000) - (n10 & 0xFF0000);
            final float n21 = (n11 & 0xFF00) - (n10 & 0xFF00);
            final float n22 = (n12 & 0xFF00) - (n10 & 0xFF00);
            final float n23 = (n11 & 0xFF) - (n10 & 0xFF);
            final float n24 = (n12 & 0xFF) - (n10 & 0xFF);
            float n25;
            if (n3 != n2) {
                n25 = (n6 - n5) / (n3 - n2);
            }
            else {
                n25 = 0.0f;
            }
            float n26;
            if (n2 != n) {
                n26 = n13 / n14;
            }
            else {
                n26 = 0.0f;
            }
            float n27;
            if (n3 != n) {
                n27 = n15 / n16;
            }
            else {
                n27 = 0.0f;
            }
            final float n28 = n13 * n16 - n15 * n14;
            if (n28 != 0.0f) {
                final float n29 = (n17 * n16 - n18 * n14) / n28;
                final float n30 = (n18 * n13 - n17 * n15) / n28;
                final float n31 = (n19 * n16 - n20 * n14) / n28;
                final float n32 = (n20 * n13 - n19 * n15) / n28;
                final float n33 = (n21 * n16 - n22 * n14) / n28;
                final float n34 = (n22 * n13 - n21 * n15) / n28;
                final float n35 = (n23 * n16 - n24 * n14) / n28;
                final float n36 = (n24 * n13 - n23 * n15) / n28;
                if (n <= n2 && n <= n3) {
                    if (n < this.anInt129) {
                        if (n2 > this.anInt129) {
                            n2 = this.anInt129;
                        }
                        if (n3 > this.anInt129) {
                            n3 = this.anInt129;
                        }
                        n7 = n7 - n29 * n4 + n29;
                        float n37 = (n10 & 0xFF0000) - n31 * n4 + n31;
                        float n38 = (n10 & 0xFF00) - n33 * n4 + n33;
                        float n39 = (n10 & 0xFF) - n35 * n4 + n35;
                        if (n2 < n3) {
                            n6 = n4;
                            if (n < 0.0f) {
                                n6 -= n27 * n;
                                n4 -= n26 * n;
                                n7 -= n30 * n;
                                n37 -= n32 * n;
                                n38 -= n34 * n;
                                n39 -= n36 * n;
                                n = 0.0f;
                            }
                            if (n2 < 0.0f) {
                                n5 -= n25 * n2;
                                n2 = 0.0f;
                            }
                            if ((n != n2 && n27 < n26) || (n == n2 && n27 > n25)) {
                                n3 -= n2;
                                n2 -= n;
                                n = this.anIntArray133[(int)n];
                                while (--n2 >= 0.0f) {
                                    this.method214(this.anIntArray139, this.aFloatArray132, (int)n, 0, 0, (int)n6, (int)n4, n7, n29, n37, n31, n38, n33, n39, n35);
                                    n6 += n27;
                                    n4 += n26;
                                    n7 += n30;
                                    n37 += n32;
                                    n38 += n34;
                                    n39 += n36;
                                    n += this.anInt127;
                                }
                                while (--n3 >= 0.0f) {
                                    this.method214(this.anIntArray139, this.aFloatArray132, (int)n, 0, 0, (int)n6, (int)n5, n7, n29, n37, n31, n38, n33, n39, n35);
                                    n6 += n27;
                                    n5 += n25;
                                    n7 += n30;
                                    n37 += n32;
                                    n38 += n34;
                                    n39 += n36;
                                    n += this.anInt127;
                                }
                            }
                            else {
                                n3 -= n2;
                                n2 -= n;
                                n = this.anIntArray133[(int)n];
                                while (--n2 >= 0.0f) {
                                    this.method214(this.anIntArray139, this.aFloatArray132, (int)n, 0, 0, (int)n4, (int)n6, n7, n29, n37, n31, n38, n33, n39, n35);
                                    n6 += n27;
                                    n4 += n26;
                                    n7 += n30;
                                    n37 += n32;
                                    n38 += n34;
                                    n39 += n36;
                                    n += this.anInt127;
                                }
                                while (--n3 >= 0.0f) {
                                    this.method214(this.anIntArray139, this.aFloatArray132, (int)n, 0, 0, (int)n5, (int)n6, n7, n29, n37, n31, n38, n33, n39, n35);
                                    n6 += n27;
                                    n5 += n25;
                                    n7 += n30;
                                    n37 += n32;
                                    n38 += n34;
                                    n39 += n36;
                                    n += this.anInt127;
                                }
                            }
                        }
                        else {
                            n5 = n4;
                            if (n < 0.0f) {
                                n5 -= n27 * n;
                                n4 -= n26 * n;
                                n7 -= n30 * n;
                                n37 -= n32 * n;
                                n38 -= n34 * n;
                                n39 -= n36 * n;
                                n = 0.0f;
                            }
                            if (n3 < 0.0f) {
                                n6 -= n25 * n3;
                                n3 = 0.0f;
                            }
                            if ((n != n3 && n27 < n26) || (n == n3 && n25 > n26)) {
                                n2 -= n3;
                                n3 -= n;
                                n = this.anIntArray133[(int)n];
                                while (--n3 >= 0.0f) {
                                    this.method214(this.anIntArray139, this.aFloatArray132, (int)n, 0, 0, (int)n5, (int)n4, n7, n29, n37, n31, n38, n33, n39, n35);
                                    n5 += n27;
                                    n4 += n26;
                                    n7 += n30;
                                    n37 += n32;
                                    n38 += n34;
                                    n39 += n36;
                                    n += this.anInt127;
                                }
                                while (--n2 >= 0.0f) {
                                    this.method214(this.anIntArray139, this.aFloatArray132, (int)n, 0, 0, (int)n6, (int)n4, n7, n29, n37, n31, n38, n33, n39, n35);
                                    n6 += n25;
                                    n4 += n26;
                                    n7 += n30;
                                    n37 += n32;
                                    n38 += n34;
                                    n39 += n36;
                                    n += this.anInt127;
                                }
                            }
                            else {
                                n2 -= n3;
                                n3 -= n;
                                n = this.anIntArray133[(int)n];
                                while (--n3 >= 0.0f) {
                                    this.method214(this.anIntArray139, this.aFloatArray132, (int)n, 0, 0, (int)n4, (int)n5, n7, n29, n37, n31, n38, n33, n39, n35);
                                    n5 += n27;
                                    n4 += n26;
                                    n7 += n30;
                                    n37 += n32;
                                    n38 += n34;
                                    n39 += n36;
                                    n += this.anInt127;
                                }
                                while (--n2 >= 0.0f) {
                                    this.method214(this.anIntArray139, this.aFloatArray132, (int)n, 0, 0, (int)n4, (int)n6, n7, n29, n37, n31, n38, n33, n39, n35);
                                    n6 += n25;
                                    n4 += n26;
                                    n7 += n30;
                                    n37 += n32;
                                    n38 += n34;
                                    n39 += n36;
                                    n += this.anInt127;
                                }
                            }
                        }
                    }
                }
                else if (n2 <= n3) {
                    if (n2 < this.anInt129) {
                        if (n3 > this.anInt129) {
                            n3 = this.anInt129;
                        }
                        if (n > this.anInt129) {
                            n = this.anInt129;
                        }
                        n8 = n8 - n29 * n5 + n29;
                        float n40 = (n11 & 0xFF0000) - n31 * n5 + n31;
                        float n41 = (n11 & 0xFF00) - n33 * n5 + n33;
                        float n42 = (n11 & 0xFF) - n35 * n5 + n35;
                        if (n3 < n) {
                            n4 = n5;
                            if (n2 < 0.0f) {
                                n4 -= n26 * n2;
                                n5 -= n25 * n2;
                                n8 -= n30 * n2;
                                n40 -= n32 * n2;
                                n41 -= n34 * n2;
                                n42 -= n36 * n2;
                                n2 = 0.0f;
                            }
                            if (n3 < 0.0f) {
                                n6 -= n27 * n3;
                                n3 = 0.0f;
                            }
                            if ((n2 != n3 && n26 < n25) || (n2 == n3 && n26 > n27)) {
                                n -= n3;
                                n3 -= n2;
                                n2 = this.anIntArray133[(int)n2];
                                while (--n3 >= 0.0f) {
                                    this.method214(this.anIntArray139, this.aFloatArray132, (int)n2, 0, 0, (int)n4, (int)n5, n8, n29, n40, n31, n41, n33, n42, n35);
                                    n4 += n26;
                                    n5 += n25;
                                    n8 += n30;
                                    n40 += n32;
                                    n41 += n34;
                                    n42 += n36;
                                    n2 += this.anInt127;
                                }
                                while (--n >= 0.0f) {
                                    this.method214(this.anIntArray139, this.aFloatArray132, (int)n2, 0, 0, (int)n4, (int)n6, n8, n29, n40, n31, n41, n33, n42, n35);
                                    n4 += n26;
                                    n6 += n27;
                                    n8 += n30;
                                    n40 += n32;
                                    n41 += n34;
                                    n42 += n36;
                                    n2 += this.anInt127;
                                }
                            }
                            else {
                                n -= n3;
                                n3 -= n2;
                                n2 = this.anIntArray133[(int)n2];
                                while (--n3 >= 0.0f) {
                                    this.method214(this.anIntArray139, this.aFloatArray132, (int)n2, 0, 0, (int)n5, (int)n4, n8, n29, n40, n31, n41, n33, n42, n35);
                                    n4 += n26;
                                    n5 += n25;
                                    n8 += n30;
                                    n40 += n32;
                                    n41 += n34;
                                    n42 += n36;
                                    n2 += this.anInt127;
                                }
                                while (--n >= 0.0f) {
                                    this.method214(this.anIntArray139, this.aFloatArray132, (int)n2, 0, 0, (int)n6, (int)n4, n8, n29, n40, n31, n41, n33, n42, n35);
                                    n4 += n26;
                                    n6 += n27;
                                    n8 += n30;
                                    n40 += n32;
                                    n41 += n34;
                                    n42 += n36;
                                    n2 += this.anInt127;
                                }
                            }
                        }
                        else {
                            n6 = n5;
                            if (n2 < 0.0f) {
                                n6 -= n26 * n2;
                                n5 -= n25 * n2;
                                n8 -= n30 * n2;
                                n40 -= n32 * n2;
                                n41 -= n34 * n2;
                                n42 -= n36 * n2;
                                n2 = 0.0f;
                            }
                            if (n < 0.0f) {
                                n4 -= n27 * n;
                                n = 0.0f;
                            }
                            if (n26 < n25) {
                                n3 -= n;
                                n -= n2;
                                n2 = this.anIntArray133[(int)n2];
                                while (--n >= 0.0f) {
                                    this.method214(this.anIntArray139, this.aFloatArray132, (int)n2, 0, 0, (int)n6, (int)n5, n8, n29, n40, n31, n41, n33, n42, n35);
                                    n6 += n26;
                                    n5 += n25;
                                    n8 += n30;
                                    n40 += n32;
                                    n41 += n34;
                                    n42 += n36;
                                    n2 += this.anInt127;
                                }
                                while (--n3 >= 0.0f) {
                                    this.method214(this.anIntArray139, this.aFloatArray132, (int)n2, 0, 0, (int)n4, (int)n5, n8, n29, n40, n31, n41, n33, n42, n35);
                                    n4 += n27;
                                    n5 += n25;
                                    n8 += n30;
                                    n40 += n32;
                                    n41 += n34;
                                    n42 += n36;
                                    n2 += this.anInt127;
                                }
                            }
                            else {
                                n3 -= n;
                                n -= n2;
                                n2 = this.anIntArray133[(int)n2];
                                while (--n >= 0.0f) {
                                    this.method214(this.anIntArray139, this.aFloatArray132, (int)n2, 0, 0, (int)n5, (int)n6, n8, n29, n40, n31, n41, n33, n42, n35);
                                    n6 += n26;
                                    n5 += n25;
                                    n8 += n30;
                                    n40 += n32;
                                    n41 += n34;
                                    n42 += n36;
                                    n2 += this.anInt127;
                                }
                                while (--n3 >= 0.0f) {
                                    this.method214(this.anIntArray139, this.aFloatArray132, (int)n2, 0, 0, (int)n5, (int)n4, n8, n29, n40, n31, n41, n33, n42, n35);
                                    n4 += n27;
                                    n5 += n25;
                                    n8 += n30;
                                    n40 += n32;
                                    n41 += n34;
                                    n42 += n36;
                                    n2 += this.anInt127;
                                }
                            }
                        }
                    }
                }
                else if (n3 < this.anInt129) {
                    if (n > this.anInt129) {
                        n = this.anInt129;
                    }
                    if (n2 > this.anInt129) {
                        n2 = this.anInt129;
                    }
                    n9 = n9 - n29 * n6 + n29;
                    float n43 = (n12 & 0xFF0000) - n31 * n6 + n31;
                    float n44 = (n12 & 0xFF00) - n33 * n6 + n33;
                    float n45 = (n12 & 0xFF) - n35 * n6 + n35;
                    if (n < n2) {
                        n5 = n6;
                        if (n3 < 0.0f) {
                            n5 -= n25 * n3;
                            n6 -= n27 * n3;
                            n9 -= n30 * n3;
                            n43 -= n32 * n3;
                            n44 -= n34 * n3;
                            n45 -= n36 * n3;
                            n3 = 0.0f;
                        }
                        if (n < 0.0f) {
                            n4 -= n26 * n;
                            n = 0.0f;
                        }
                        if (n25 < n27) {
                            n2 -= n;
                            n -= n3;
                            n3 = this.anIntArray133[(int)n3];
                            while (--n >= 0.0f) {
                                this.method214(this.anIntArray139, this.aFloatArray132, (int)n3, 0, 0, (int)n5, (int)n6, n9, n29, n43, n31, n44, n33, n45, n35);
                                n5 += n25;
                                n6 += n27;
                                n9 += n30;
                                n43 += n32;
                                n44 += n34;
                                n45 += n36;
                                n3 += this.anInt127;
                            }
                            while (--n2 >= 0.0f) {
                                this.method214(this.anIntArray139, this.aFloatArray132, (int)n3, 0, 0, (int)n5, (int)n4, n9, n29, n43, n31, n44, n33, n45, n35);
                                n5 += n25;
                                n4 += n26;
                                n9 += n30;
                                n43 += n32;
                                n44 += n34;
                                n45 += n36;
                                n3 += this.anInt127;
                            }
                        }
                        else {
                            n2 -= n;
                            n -= n3;
                            n3 = this.anIntArray133[(int)n3];
                            while (--n >= 0.0f) {
                                this.method214(this.anIntArray139, this.aFloatArray132, (int)n3, 0, 0, (int)n6, (int)n5, n9, n29, n43, n31, n44, n33, n45, n35);
                                n5 += n25;
                                n6 += n27;
                                n9 += n30;
                                n43 += n32;
                                n44 += n34;
                                n45 += n36;
                                n3 += this.anInt127;
                            }
                            while (--n2 >= 0.0f) {
                                this.method214(this.anIntArray139, this.aFloatArray132, (int)n3, 0, 0, (int)n4, (int)n5, n9, n29, n43, n31, n44, n33, n45, n35);
                                n5 += n25;
                                n4 += n26;
                                n9 += n30;
                                n43 += n32;
                                n44 += n34;
                                n45 += n36;
                                n3 += this.anInt127;
                            }
                        }
                    }
                    else {
                        n4 = n6;
                        if (n3 < 0.0f) {
                            n4 -= n25 * n3;
                            n6 -= n27 * n3;
                            n9 -= n30 * n3;
                            n43 -= n32 * n3;
                            n44 -= n34 * n3;
                            n45 -= n36 * n3;
                            n3 = 0.0f;
                        }
                        if (n2 < 0.0f) {
                            n5 -= n26 * n2;
                            n2 = 0.0f;
                        }
                        if (n25 < n27) {
                            n -= n2;
                            n2 -= n3;
                            n3 = this.anIntArray133[(int)n3];
                            while (--n2 >= 0.0f) {
                                this.method214(this.anIntArray139, this.aFloatArray132, (int)n3, 0, 0, (int)n4, (int)n6, n9, n29, n43, n31, n44, n33, n45, n35);
                                n4 += n25;
                                n6 += n27;
                                n9 += n30;
                                n43 += n32;
                                n44 += n34;
                                n45 += n36;
                                n3 += this.anInt127;
                            }
                            while (--n >= 0.0f) {
                                this.method214(this.anIntArray139, this.aFloatArray132, (int)n3, 0, 0, (int)n5, (int)n6, n9, n29, n43, n31, n44, n33, n45, n35);
                                n5 += n26;
                                n6 += n27;
                                n9 += n30;
                                n43 += n32;
                                n44 += n34;
                                n45 += n36;
                                n3 += this.anInt127;
                            }
                        }
                        else {
                            n -= n2;
                            n2 -= n3;
                            n3 = this.anIntArray133[(int)n3];
                            while (--n2 >= 0.0f) {
                                this.method214(this.anIntArray139, this.aFloatArray132, (int)n3, 0, 0, (int)n6, (int)n4, n9, n29, n43, n31, n44, n33, n45, n35);
                                n4 += n25;
                                n6 += n27;
                                n9 += n30;
                                n43 += n32;
                                n44 += n34;
                                n45 += n36;
                                n3 += this.anInt127;
                            }
                            while (--n >= 0.0f) {
                                this.method214(this.anIntArray139, this.aFloatArray132, (int)n3, 0, 0, (int)n6, (int)n5, n9, n29, n43, n31, n44, n33, n45, n35);
                                n5 += n26;
                                n6 += n27;
                                n9 += n30;
                                n43 += n32;
                                n44 += n34;
                                n45 += n36;
                                n3 += this.anInt127;
                            }
                        }
                    }
                }
            }
        }
    }
    
    final int method207() {
        return this.anIntArray133[0] % this.anInt127;
    }
    
    final void method208(float n, float n2, float n3, float n4, float n5, float n6, float n7, float n8, float n9, final int n10) {
        if (this.aBoolean142) {
            this.aHa_Sub2_131.method1789((int)n, n10, (int)n2, (int)n5, -10550, (int)n4);
            this.aHa_Sub2_131.method1789((int)n2, n10, (int)n3, (int)n6, -10550, (int)n5);
            this.aHa_Sub2_131.method1789((int)n3, n10, (int)n, (int)n4, -10550, (int)n6);
        }
        else {
            final float n11 = n5 - n4;
            final float n12 = n2 - n;
            final float n13 = n6 - n4;
            final float n14 = n3 - n;
            final float n15 = n8 - n7;
            final float n16 = n9 - n7;
            float n17 = 0.0f;
            if (n2 != n) {
                n17 = (n5 - n4) / (n2 - n);
            }
            float n18 = 0.0f;
            if (n3 != n2) {
                n18 = (n6 - n5) / (n3 - n2);
            }
            float n19 = 0.0f;
            if (n3 != n) {
                n19 = (n4 - n6) / (n - n3);
            }
            final float n20 = n11 * n14 - n13 * n12;
            if (n20 != 0.0f) {
                final float n21 = (n15 * n14 - n16 * n12) / n20;
                final float n22 = (n16 * n11 - n15 * n13) / n20;
                if (n <= n2 && n <= n3) {
                    if (n < this.anInt129) {
                        if (n2 > this.anInt129) {
                            n2 = this.anInt129;
                        }
                        if (n3 > this.anInt129) {
                            n3 = this.anInt129;
                        }
                        n7 = n7 - n21 * n4 + n21;
                        if (n2 < n3) {
                            n6 = n4;
                            if (n < 0.0f) {
                                n6 -= n19 * n;
                                n4 -= n17 * n;
                                n7 -= n22 * n;
                                n = 0.0f;
                            }
                            if (n2 < 0.0f) {
                                n5 -= n18 * n2;
                                n2 = 0.0f;
                            }
                            if ((n != n2 && n19 < n17) || (n == n2 && n19 > n18)) {
                                n3 -= n2;
                                n2 -= n;
                                n = this.anIntArray133[(int)n];
                                while (--n2 >= 0.0f) {
                                    this.method211(this.anIntArray139, this.aFloatArray132, (int)n, n10, 0, (int)n6, (int)n4, n7, n21);
                                    n6 += n19;
                                    n4 += n17;
                                    n7 += n22;
                                    n += this.anInt127;
                                }
                                while (--n3 >= 0.0f) {
                                    this.method211(this.anIntArray139, this.aFloatArray132, (int)n, n10, 0, (int)n6, (int)n5, n7, n21);
                                    n6 += n19;
                                    n5 += n18;
                                    n7 += n22;
                                    n += this.anInt127;
                                }
                            }
                            else {
                                n3 -= n2;
                                n2 -= n;
                                n = this.anIntArray133[(int)n];
                                while (--n2 >= 0.0f) {
                                    this.method211(this.anIntArray139, this.aFloatArray132, (int)n, n10, 0, (int)n4, (int)n6, n7, n21);
                                    n6 += n19;
                                    n4 += n17;
                                    n7 += n22;
                                    n += this.anInt127;
                                }
                                while (--n3 >= 0.0f) {
                                    this.method211(this.anIntArray139, this.aFloatArray132, (int)n, n10, 0, (int)n5, (int)n6, n7, n21);
                                    n6 += n19;
                                    n5 += n18;
                                    n7 += n22;
                                    n += this.anInt127;
                                }
                            }
                        }
                        else {
                            n5 = n4;
                            if (n < 0.0f) {
                                n5 -= n19 * n;
                                n4 -= n17 * n;
                                n7 -= n22 * n;
                                n = 0.0f;
                            }
                            if (n3 < 0.0f) {
                                n6 -= n18 * n3;
                                n3 = 0.0f;
                            }
                            if ((n != n3 && n19 < n17) || (n == n3 && n18 > n17)) {
                                n2 -= n3;
                                n3 -= n;
                                n = this.anIntArray133[(int)n];
                                while (--n3 >= 0.0f) {
                                    this.method211(this.anIntArray139, this.aFloatArray132, (int)n, n10, 0, (int)n5, (int)n4, n7, n21);
                                    n5 += n19;
                                    n4 += n17;
                                    n7 += n22;
                                    n += this.anInt127;
                                }
                                while (--n2 >= 0.0f) {
                                    this.method211(this.anIntArray139, this.aFloatArray132, (int)n, n10, 0, (int)n6, (int)n4, n7, n21);
                                    n6 += n18;
                                    n4 += n17;
                                    n7 += n22;
                                    n += this.anInt127;
                                }
                            }
                            else {
                                n2 -= n3;
                                n3 -= n;
                                n = this.anIntArray133[(int)n];
                                while (--n3 >= 0.0f) {
                                    this.method211(this.anIntArray139, this.aFloatArray132, (int)n, n10, 0, (int)n4, (int)n5, n7, n21);
                                    n5 += n19;
                                    n4 += n17;
                                    n7 += n22;
                                    n += this.anInt127;
                                }
                                while (--n2 >= 0.0f) {
                                    this.method211(this.anIntArray139, this.aFloatArray132, (int)n, n10, 0, (int)n4, (int)n6, n7, n21);
                                    n6 += n18;
                                    n4 += n17;
                                    n7 += n22;
                                    n += this.anInt127;
                                }
                            }
                        }
                    }
                }
                else if (n2 <= n3) {
                    if (n2 < this.anInt129) {
                        if (n3 > this.anInt129) {
                            n3 = this.anInt129;
                        }
                        if (n > this.anInt129) {
                            n = this.anInt129;
                        }
                        n8 = n8 - n21 * n5 + n21;
                        if (n3 < n) {
                            n4 = n5;
                            if (n2 < 0.0f) {
                                n4 -= n17 * n2;
                                n5 -= n18 * n2;
                                n8 -= n22 * n2;
                                n2 = 0.0f;
                            }
                            if (n3 < 0.0f) {
                                n6 -= n19 * n3;
                                n3 = 0.0f;
                            }
                            if ((n2 != n3 && n17 < n18) || (n2 == n3 && n17 > n19)) {
                                n -= n3;
                                n3 -= n2;
                                n2 = this.anIntArray133[(int)n2];
                                while (--n3 >= 0.0f) {
                                    this.method211(this.anIntArray139, this.aFloatArray132, (int)n2, n10, 0, (int)n4, (int)n5, n8, n21);
                                    n4 += n17;
                                    n5 += n18;
                                    n8 += n22;
                                    n2 += this.anInt127;
                                }
                                while (--n >= 0.0f) {
                                    this.method211(this.anIntArray139, this.aFloatArray132, (int)n2, n10, 0, (int)n4, (int)n6, n8, n21);
                                    n4 += n17;
                                    n6 += n19;
                                    n8 += n22;
                                    n2 += this.anInt127;
                                }
                            }
                            else {
                                n -= n3;
                                n3 -= n2;
                                n2 = this.anIntArray133[(int)n2];
                                while (--n3 >= 0.0f) {
                                    this.method211(this.anIntArray139, this.aFloatArray132, (int)n2, n10, 0, (int)n5, (int)n4, n8, n21);
                                    n4 += n17;
                                    n5 += n18;
                                    n8 += n22;
                                    n2 += this.anInt127;
                                }
                                while (--n >= 0.0f) {
                                    this.method211(this.anIntArray139, this.aFloatArray132, (int)n2, n10, 0, (int)n6, (int)n4, n8, n21);
                                    n4 += n17;
                                    n6 += n19;
                                    n8 += n22;
                                    n2 += this.anInt127;
                                }
                            }
                        }
                        else {
                            n6 = n5;
                            if (n2 < 0.0f) {
                                n6 -= n17 * n2;
                                n5 -= n18 * n2;
                                n8 -= n22 * n2;
                                n2 = 0.0f;
                            }
                            if (n < 0.0f) {
                                n4 -= n19 * n;
                                n = 0.0f;
                            }
                            if (n17 < n18) {
                                n3 -= n;
                                n -= n2;
                                n2 = this.anIntArray133[(int)n2];
                                while (--n >= 0.0f) {
                                    this.method211(this.anIntArray139, this.aFloatArray132, (int)n2, n10, 0, (int)n6, (int)n5, n8, n21);
                                    n6 += n17;
                                    n5 += n18;
                                    n8 += n22;
                                    n2 += this.anInt127;
                                }
                                while (--n3 >= 0.0f) {
                                    this.method211(this.anIntArray139, this.aFloatArray132, (int)n2, n10, 0, (int)n4, (int)n5, n8, n21);
                                    n4 += n19;
                                    n5 += n18;
                                    n8 += n22;
                                    n2 += this.anInt127;
                                }
                            }
                            else {
                                n3 -= n;
                                n -= n2;
                                n2 = this.anIntArray133[(int)n2];
                                while (--n >= 0.0f) {
                                    this.method211(this.anIntArray139, this.aFloatArray132, (int)n2, n10, 0, (int)n5, (int)n6, n8, n21);
                                    n6 += n17;
                                    n5 += n18;
                                    n8 += n22;
                                    n2 += this.anInt127;
                                }
                                while (--n3 >= 0.0f) {
                                    this.method211(this.anIntArray139, this.aFloatArray132, (int)n2, n10, 0, (int)n5, (int)n4, n8, n21);
                                    n4 += n19;
                                    n5 += n18;
                                    n8 += n22;
                                    n2 += this.anInt127;
                                }
                            }
                        }
                    }
                }
                else if (n3 < this.anInt129) {
                    if (n > this.anInt129) {
                        n = this.anInt129;
                    }
                    if (n2 > this.anInt129) {
                        n2 = this.anInt129;
                    }
                    n9 = n9 - n21 * n6 + n21;
                    if (n < n2) {
                        n5 = n6;
                        if (n3 < 0.0f) {
                            n5 -= n18 * n3;
                            n6 -= n19 * n3;
                            n9 -= n22 * n3;
                            n3 = 0.0f;
                        }
                        if (n < 0.0f) {
                            n4 -= n17 * n;
                            n = 0.0f;
                        }
                        if (n18 < n19) {
                            n2 -= n;
                            n -= n3;
                            n3 = this.anIntArray133[(int)n3];
                            while (--n >= 0.0f) {
                                this.method211(this.anIntArray139, this.aFloatArray132, (int)n3, n10, 0, (int)n5, (int)n6, n9, n21);
                                n5 += n18;
                                n6 += n19;
                                n9 += n22;
                                n3 += this.anInt127;
                            }
                            while (--n2 >= 0.0f) {
                                this.method211(this.anIntArray139, this.aFloatArray132, (int)n3, n10, 0, (int)n5, (int)n4, n9, n21);
                                n5 += n18;
                                n4 += n17;
                                n9 += n22;
                                n3 += this.anInt127;
                            }
                        }
                        else {
                            n2 -= n;
                            n -= n3;
                            n3 = this.anIntArray133[(int)n3];
                            while (--n >= 0.0f) {
                                this.method211(this.anIntArray139, this.aFloatArray132, (int)n3, n10, 0, (int)n6, (int)n5, n9, n21);
                                n5 += n18;
                                n6 += n19;
                                n9 += n22;
                                n3 += this.anInt127;
                            }
                            while (--n2 >= 0.0f) {
                                this.method211(this.anIntArray139, this.aFloatArray132, (int)n3, n10, 0, (int)n4, (int)n5, n9, n21);
                                n5 += n18;
                                n4 += n17;
                                n9 += n22;
                                n3 += this.anInt127;
                            }
                        }
                    }
                    else {
                        n4 = n6;
                        if (n3 < 0.0f) {
                            n4 -= n18 * n3;
                            n6 -= n19 * n3;
                            n9 -= n22 * n3;
                            n3 = 0.0f;
                        }
                        if (n2 < 0.0f) {
                            n5 -= n17 * n2;
                            n2 = 0.0f;
                        }
                        if (n18 < n19) {
                            n -= n2;
                            n2 -= n3;
                            n3 = this.anIntArray133[(int)n3];
                            while (--n2 >= 0.0f) {
                                this.method211(this.anIntArray139, this.aFloatArray132, (int)n3, n10, 0, (int)n4, (int)n6, n9, n21);
                                n4 += n18;
                                n6 += n19;
                                n9 += n22;
                                n3 += this.anInt127;
                            }
                            while (--n >= 0.0f) {
                                this.method211(this.anIntArray139, this.aFloatArray132, (int)n3, n10, 0, (int)n5, (int)n6, n9, n21);
                                n5 += n17;
                                n6 += n19;
                                n9 += n22;
                                n3 += this.anInt127;
                            }
                        }
                        else {
                            n -= n2;
                            n2 -= n3;
                            n3 = this.anIntArray133[(int)n3];
                            while (--n2 >= 0.0f) {
                                this.method211(this.anIntArray139, this.aFloatArray132, (int)n3, n10, 0, (int)n6, (int)n4, n9, n21);
                                n4 += n18;
                                n6 += n19;
                                n9 += n22;
                                n3 += this.anInt127;
                            }
                            while (--n >= 0.0f) {
                                this.method211(this.anIntArray139, this.aFloatArray132, (int)n3, n10, 0, (int)n6, (int)n5, n9, n21);
                                n5 += n17;
                                n6 += n19;
                                n9 += n22;
                                n3 += this.anInt127;
                            }
                        }
                    }
                }
            }
        }
    }
    
    final void method209(float n, float n2, float n3, float n4, float n5, float n6, float n7, float n8, float n9, float n10, float n11, float n12, float n13, float n14, float n15, final int n16, final int n17, final int n18, final int anInt155, int n19, int n20, int n21, final int n22, final float aFloat157, final int n23, final float aFloat158, final int n24, final float aFloat159) {
        if (n22 != this.anInt158) {
            this.anIntArray143 = this.aHa_Sub2_131.method1919(n22);
            if (this.anIntArray143 == null) {
                this.method206((int)n, (int)n2, (int)n3, (int)n4, (int)n5, (int)n6, (int)n7, (int)n8, (int)n9, Class98_Sub46_Sub5.method1544(anInt155 | n19 << 24, (byte)117, n16), Class98_Sub46_Sub5.method1544(anInt155 | n20 << 24, (byte)122, n17), Class98_Sub46_Sub5.method1544(anInt155 | n21 << 24, (byte)125, n18));
                return;
            }
            this.anInt151 = (this.aHa_Sub2_131.method1925(n22) ? 64 : this.aHa_Sub2_131.anInt4482);
            this.anInt146 = this.anInt151 - 1;
            this.anInt147 = this.aHa_Sub2_131.method1912(n22);
        }
        this.aFloat157 = aFloat157;
        if (n23 != this.anInt144) {
            this.anIntArray145 = this.aHa_Sub2_131.method1919(n23);
            if (this.anIntArray145 == null) {
                this.method206((int)n, (int)n2, (int)n3, (int)n4, (int)n5, (int)n6, (int)n7, (int)n8, (int)n9, Class98_Sub46_Sub5.method1544(anInt155 | n19 << 24, (byte)104, n16), Class98_Sub46_Sub5.method1544(anInt155 | n20 << 24, (byte)115, n17), Class98_Sub46_Sub5.method1544(anInt155 | n21 << 24, (byte)110, n18));
                return;
            }
            this.anInt153 = (this.aHa_Sub2_131.method1925(n23) ? 64 : this.aHa_Sub2_131.anInt4482);
            this.anInt152 = this.anInt153 - 1;
        }
        this.aFloat149 = aFloat158;
        if (n24 != this.anInt159) {
            this.anIntArray156 = this.aHa_Sub2_131.method1919(n24);
            if (this.anIntArray156 == null) {
                this.method206((int)n, (int)n2, (int)n3, (int)n4, (int)n5, (int)n6, (int)n7, (int)n8, (int)n9, Class98_Sub46_Sub5.method1544(anInt155 | n19 << 24, (byte)117, n16), Class98_Sub46_Sub5.method1544(anInt155 | n20 << 24, (byte)122, n17), Class98_Sub46_Sub5.method1544(anInt155 | n21 << 24, (byte)105, n18));
                return;
            }
            this.anInt160 = (this.aHa_Sub2_131.method1925(n24) ? 64 : this.aHa_Sub2_131.anInt4482);
            this.anInt148 = this.anInt160 - 1;
        }
        this.aFloat150 = aFloat159;
        this.anInt155 = anInt155;
        float n25 = n16 >> 24 & 0xFF;
        float n26 = n17 >> 24 & 0xFF;
        float n27 = n18 >> 24 & 0xFF;
        float n28 = n16 >> 16 & 0xFF;
        float n29 = n17 >> 16 & 0xFF;
        float n30 = n18 >> 16 & 0xFF;
        float n31 = n16 >> 8 & 0xFF;
        float n32 = n17 >> 8 & 0xFF;
        float n33 = n18 >> 8 & 0xFF;
        float n34 = n16 & 0xFF;
        float n35 = n17 & 0xFF;
        float n36 = n18 & 0xFF;
        n10 /= n7;
        n11 /= n8;
        n12 /= n9;
        n13 /= n7;
        n14 /= n8;
        n15 /= n9;
        n7 = 1.0f / n7;
        n8 = 1.0f / n8;
        n9 = 1.0f / n9;
        float n37 = 1.0f;
        float n38 = 0.0f;
        float n39 = 0.0f;
        float n40 = 0.0f;
        float n41 = 1.0f;
        float n42 = 0.0f;
        float n43 = 0.0f;
        float n44 = 0.0f;
        float n45 = 0.0f;
        float n46 = 0.0f;
        float n47 = 0.0f;
        float n48 = 0.0f;
        float n49 = 0.0f;
        float n50 = 0.0f;
        float n51 = 0.0f;
        float n52 = 0.0f;
        float n53 = 0.0f;
        if (n2 != n) {
            final float n54 = n2 - n;
            n43 = (n5 - n4) / n54;
            n44 = (n8 - n7) / n54;
            n45 = (n11 - n10) / n54;
            n46 = (n14 - n13) / n54;
            n47 = (n20 - n19) / n54;
            n48 = (n26 - n25) / n54;
            n49 = (n29 - n28) / n54;
            n50 = (n32 - n31) / n54;
            n51 = (n35 - n34) / n54;
            n52 = (n38 - n37) / n54;
            n53 = (n41 - n40) / n54;
        }
        float n55 = 0.0f;
        float n56 = 0.0f;
        float n57 = 0.0f;
        float n58 = 0.0f;
        float n59 = 0.0f;
        float n60 = 0.0f;
        float n61 = 0.0f;
        float n62 = 0.0f;
        float n63 = 0.0f;
        float n64 = 0.0f;
        float n65 = 0.0f;
        if (n3 != n2) {
            final float n66 = n3 - n2;
            n55 = (n6 - n5) / n66;
            n56 = (n9 - n8) / n66;
            n57 = (n12 - n11) / n66;
            n58 = (n15 - n14) / n66;
            n59 = (n21 - n20) / n66;
            n60 = (n27 - n26) / n66;
            n61 = (n30 - n29) / n66;
            n62 = (n33 - n32) / n66;
            n63 = (n36 - n35) / n66;
            n64 = (n39 - n38) / n66;
            n65 = (n42 - n41) / n66;
        }
        float n67 = 0.0f;
        float n68 = 0.0f;
        float n69 = 0.0f;
        float n70 = 0.0f;
        float n71 = 0.0f;
        float n72 = 0.0f;
        float n73 = 0.0f;
        float n74 = 0.0f;
        float n75 = 0.0f;
        float n76 = 0.0f;
        float n77 = 0.0f;
        if (n != n3) {
            final float n78 = n - n3;
            n67 = (n4 - n6) / n78;
            n68 = (n7 - n9) / n78;
            n69 = (n10 - n12) / n78;
            n70 = (n13 - n15) / n78;
            n71 = (n19 - n21) / n78;
            n72 = (n25 - n27) / n78;
            n73 = (n28 - n30) / n78;
            n74 = (n31 - n33) / n78;
            n75 = (n34 - n36) / n78;
            n76 = (n37 - n39) / n78;
            n77 = (n40 - n42) / n78;
        }
        if (n <= n2 && n <= n3) {
            if (n < this.anInt129) {
                if (n2 > this.anInt129) {
                    n2 = this.anInt129;
                }
                if (n3 > this.anInt129) {
                    n3 = this.anInt129;
                }
                if (n2 < n3) {
                    n6 = n4;
                    n9 = n7;
                    n12 = n10;
                    n15 = n13;
                    n21 = n19;
                    float n79 = n25;
                    float n80 = n28;
                    float n81 = n31;
                    float n82 = n34;
                    float n83 = n37;
                    float n84 = n40;
                    if (n < 0.0f) {
                        n4 -= n43 * n;
                        n6 -= n67 * n;
                        n7 -= n44 * n;
                        n9 -= n68 * n;
                        n10 -= n45 * n;
                        n12 -= n69 * n;
                        n13 -= n46 * n;
                        n15 -= n70 * n;
                        n19 -= (int)(n47 * n);
                        n21 -= (int)(n71 * n);
                        n25 -= n48 * n;
                        n79 -= n72 * n;
                        n28 -= n49 * n;
                        n80 -= n73 * n;
                        n31 -= n50 * n;
                        n81 -= n74 * n;
                        n34 -= n51 * n;
                        n82 -= n75 * n;
                        n37 -= n52 * n;
                        n83 -= n76 * n;
                        n40 -= n53 * n;
                        n84 -= n77 * n;
                        n = 0.0f;
                    }
                    if (n2 < 0.0f) {
                        n5 -= n55 * n2;
                        n8 -= n56 * n2;
                        n11 -= n57 * n2;
                        n14 -= n58 * n2;
                        n20 -= (int)(n59 * n2);
                        n26 -= n60 * n2;
                        n29 -= n61 * n2;
                        n32 -= n62 * n2;
                        n35 -= n63 * n2;
                        n38 -= n64 * n2;
                        n41 -= n65 * n2;
                        n2 = 0.0f;
                    }
                    if ((n != n2 && n67 < n43) || (n == n2 && n67 > n55)) {
                        n3 -= n2;
                        n2 -= n;
                        n = this.anIntArray133[(int)n];
                        while (--n2 >= 0.0f) {
                            this.method215(this.anIntArray139, this.anIntArray143, (int)n, (int)n6, (int)n4, n9, n7, n12, n10, n15, n13, n21, n19, n79, n25, n80, n28, n81, n31, n82, n34, n83, n37, n84, n40);
                            n4 += n43;
                            n6 += n67;
                            n7 += n44;
                            n9 += n68;
                            n10 += n45;
                            n12 += n69;
                            n13 += n46;
                            n15 += n70;
                            n19 += (int)n47;
                            n21 += (int)n71;
                            n25 += n48;
                            n79 += n72;
                            n28 += n49;
                            n80 += n73;
                            n31 += n50;
                            n81 += n74;
                            n34 += n51;
                            n82 += n75;
                            n37 += n52;
                            n83 += n76;
                            n40 += n53;
                            n84 += n53;
                            n += this.anInt127;
                        }
                        while (--n3 >= 0.0f) {
                            this.method215(this.anIntArray139, this.anIntArray143, (int)n, (int)n6, (int)n5, n9, n8, n12, n11, n15, n14, n21, n20, n79, n26, n80, n29, n81, n32, n82, n35, n83, n38, n84, n41);
                            n5 += n55;
                            n6 += n67;
                            n8 += n56;
                            n9 += n68;
                            n11 += n57;
                            n12 += n69;
                            n14 += n58;
                            n15 += n70;
                            n20 += (int)n59;
                            n21 += (int)n71;
                            n26 += n60;
                            n79 += n72;
                            n29 += n61;
                            n80 += n73;
                            n32 += n62;
                            n81 += n74;
                            n35 += n63;
                            n82 += n75;
                            n38 += n64;
                            n83 += n76;
                            n41 += n65;
                            n84 += n77;
                            n += this.anInt127;
                        }
                    }
                    else {
                        n3 -= n2;
                        n2 -= n;
                        n = this.anIntArray133[(int)n];
                        while (--n2 >= 0.0f) {
                            this.method215(this.anIntArray139, this.anIntArray143, (int)n, (int)n4, (int)n6, n7, n9, n10, n12, n13, n15, n19, n21, n25, n79, n28, n80, n31, n81, n34, n82, n37, n83, n40, n84);
                            n4 += n43;
                            n6 += n67;
                            n7 += n44;
                            n9 += n68;
                            n10 += n45;
                            n12 += n69;
                            n13 += n46;
                            n15 += n70;
                            n19 += (int)n47;
                            n21 += (int)n71;
                            n25 += n48;
                            n79 += n72;
                            n28 += n49;
                            n80 += n73;
                            n31 += n50;
                            n81 += n74;
                            n34 += n51;
                            n82 += n75;
                            n37 += n52;
                            n83 += n76;
                            n40 += n53;
                            n84 += n77;
                            n += this.anInt127;
                        }
                        while (--n3 >= 0.0f) {
                            this.method215(this.anIntArray139, this.anIntArray143, (int)n, (int)n5, (int)n6, n8, n9, n11, n12, n14, n15, n20, n21, n26, n79, n29, n80, n32, n81, n35, n82, n38, n83, n41, n84);
                            n5 += n55;
                            n6 += n67;
                            n8 += n56;
                            n9 += n68;
                            n11 += n57;
                            n12 += n69;
                            n14 += n58;
                            n15 += n70;
                            n20 += (int)n59;
                            n21 += (int)n71;
                            n26 += n60;
                            n79 += n72;
                            n29 += n61;
                            n80 += n73;
                            n32 += n62;
                            n81 += n74;
                            n35 += n63;
                            n82 += n75;
                            n38 += n64;
                            n83 += n76;
                            n41 += n65;
                            n84 += n77;
                            n += this.anInt127;
                        }
                    }
                }
                else {
                    n5 = n4;
                    n8 = n7;
                    n11 = n10;
                    n14 = n13;
                    n20 = n19;
                    float n85 = n25;
                    float n86 = n28;
                    float n87 = n31;
                    float n88 = n34;
                    float n89 = n37;
                    float n90 = n40;
                    if (n < 0.0f) {
                        n4 -= n43 * n;
                        n5 -= n67 * n;
                        n7 -= n44 * n;
                        n8 -= n68 * n;
                        n10 -= n45 * n;
                        n11 -= n69 * n;
                        n13 -= n46 * n;
                        n14 -= n70 * n;
                        n19 -= (int)(n47 * n);
                        n20 -= (int)(n71 * n);
                        n25 -= n48 * n;
                        n85 -= n72 * n;
                        n28 -= n49 * n;
                        n86 -= n73 * n;
                        n31 -= n50 * n;
                        n87 -= n74 * n;
                        n34 -= n51 * n;
                        n88 -= n75 * n;
                        n37 -= n52 * n;
                        n89 -= n76 * n;
                        n40 -= n53 * n;
                        n90 -= n77 * n;
                        n = 0.0f;
                    }
                    if (n3 < 0.0f) {
                        n6 -= n55 * n3;
                        n9 -= n56 * n3;
                        n12 -= n57 * n3;
                        n15 -= n58 * n3;
                        n21 -= (int)(n59 * n3);
                        n27 -= n60 * n3;
                        n30 -= n61 * n3;
                        n33 -= n62 * n3;
                        n36 -= n63 * n3;
                        n39 -= n64 * n3;
                        n42 -= n65 * n3;
                        n3 = 0.0f;
                    }
                    if ((n != n3 && n67 < n43) || (n == n3 && n55 > n43)) {
                        n2 -= n3;
                        n3 -= n;
                        n = this.anIntArray133[(int)n];
                        while (--n3 >= 0.0f) {
                            this.method215(this.anIntArray139, this.anIntArray143, (int)n, (int)n5, (int)n4, n8, n7, n11, n10, n14, n13, n20, n19, n85, n25, n86, n28, n87, n31, n88, n34, n89, n37, n90, n40);
                            n4 += n43;
                            n5 += n67;
                            n7 += n44;
                            n8 += n68;
                            n10 += n45;
                            n11 += n69;
                            n13 += n46;
                            n14 += n70;
                            n19 += (int)n47;
                            n20 += (int)n71;
                            n25 += n48;
                            n85 += n72;
                            n28 += n49;
                            n86 += n73;
                            n31 += n50;
                            n87 += n74;
                            n34 += n51;
                            n88 += n75;
                            n37 += n52;
                            n89 += n76;
                            n40 += n53;
                            n90 += n77;
                            n += this.anInt127;
                        }
                        while (--n2 >= 0.0f) {
                            this.method215(this.anIntArray139, this.anIntArray143, (int)n, (int)n6, (int)n4, n9, n7, n12, n10, n15, n13, n21, n19, n27, n25, n30, n28, n33, n31, n36, n34, n39, n37, n42, n40);
                            n6 += n55;
                            n4 += n43;
                            n9 += n56;
                            n7 += n44;
                            n12 += n57;
                            n10 += n45;
                            n15 += n58;
                            n13 += n46;
                            n21 += (int)n59;
                            n19 += (int)n47;
                            n27 += n60;
                            n25 += n48;
                            n30 += n61;
                            n28 += n49;
                            n33 += n62;
                            n31 += n50;
                            n36 += n63;
                            n34 += n51;
                            n39 += n64;
                            n37 += n52;
                            n42 += n65;
                            n40 += n53;
                            n += this.anInt127;
                        }
                    }
                    else {
                        n2 -= n3;
                        n3 -= n;
                        n = this.anIntArray133[(int)n];
                        while (--n3 >= 0.0f) {
                            this.method215(this.anIntArray139, this.anIntArray143, (int)n, (int)n4, (int)n5, n7, n8, n10, n11, n13, n14, n19, n20, n25, n85, n28, n86, n31, n87, n34, n88, n37, n89, n40, n90);
                            n5 += n67;
                            n4 += n43;
                            n8 += n68;
                            n7 += n44;
                            n11 += n69;
                            n10 += n45;
                            n14 += n70;
                            n13 += n46;
                            n20 += (int)n71;
                            n19 += (int)n47;
                            n85 += n72;
                            n25 += n48;
                            n86 += n73;
                            n28 += n49;
                            n87 += n74;
                            n31 += n50;
                            n88 += n75;
                            n34 += n51;
                            n89 += n76;
                            n37 += n52;
                            n90 += n77;
                            n40 += n53;
                            n += this.anInt127;
                        }
                        while (--n2 >= 0.0f) {
                            this.method215(this.anIntArray139, this.anIntArray143, (int)n, (int)n4, (int)n6, n7, n9, n10, n12, n13, n15, n19, n21, n25, n27, n28, n30, n31, n33, n34, n36, n37, n39, n40, n42);
                            n4 += n43;
                            n6 += n55;
                            n7 += n44;
                            n9 += n56;
                            n10 += n45;
                            n12 += n57;
                            n13 += n46;
                            n15 += n58;
                            n19 += (int)n47;
                            n21 += (int)n59;
                            n25 += n48;
                            n27 += n60;
                            n28 += n49;
                            n30 += n61;
                            n31 += n50;
                            n33 += n62;
                            n34 += n51;
                            n36 += n63;
                            n37 += n52;
                            n39 += n64;
                            n40 += n53;
                            n42 += n65;
                            n += this.anInt127;
                        }
                    }
                }
            }
        }
        else if (n2 <= n3) {
            if (n2 < this.anInt129) {
                if (n3 > this.anInt129) {
                    n3 = this.anInt129;
                }
                if (n > this.anInt129) {
                    n = this.anInt129;
                }
                if (n3 < n) {
                    n4 = n5;
                    n7 = n8;
                    n10 = n11;
                    n13 = n14;
                    n19 = n20;
                    float n91 = n26;
                    float n92 = n29;
                    float n93 = n32;
                    float n94 = n35;
                    float n95 = n38;
                    float n96 = n41;
                    if (n2 < 0.0f) {
                        n4 -= n43 * n2;
                        n5 -= n55 * n2;
                        n7 -= n44 * n2;
                        n8 -= n56 * n2;
                        n10 -= n45 * n2;
                        n11 -= n57 * n2;
                        n13 -= n46 * n2;
                        n14 -= n58 * n2;
                        n19 -= (int)(n47 * n2);
                        n20 -= (int)(n59 * n2);
                        n91 -= n48 * n2;
                        n26 -= n60 * n2;
                        n92 -= n49 * n2;
                        n29 -= n61 * n2;
                        n93 -= n50 * n2;
                        n32 -= n62 * n2;
                        n94 -= n51 * n2;
                        n35 -= n63 * n2;
                        n95 -= n52 * n2;
                        n38 -= n64 * n2;
                        n96 -= n53 * n2;
                        n41 -= n65 * n2;
                        n2 = 0.0f;
                    }
                    if (n3 < 0.0f) {
                        n6 -= n67 * n3;
                        n9 -= n68 * n3;
                        n12 -= n69 * n3;
                        n15 -= n70 * n3;
                        n21 -= (int)(n71 * n3);
                        n27 -= n72 * n3;
                        n30 -= n73 * n3;
                        n33 -= n74 * n3;
                        n36 -= n75 * n3;
                        n39 -= n76 * n3;
                        n42 -= n77 * n3;
                        n3 = 0.0f;
                    }
                    if ((n2 != n3 && n43 < n55) || (n2 == n3 && n43 > n67)) {
                        n -= n3;
                        n3 -= n2;
                        n2 = this.anIntArray133[(int)n2];
                        while (--n3 >= 0.0f) {
                            this.method215(this.anIntArray139, this.anIntArray143, (int)n2, (int)n4, (int)n5, n7, n8, n10, n11, n13, n14, n19, n20, n91, n26, n92, n29, n93, n32, n94, n35, n95, n38, n96, n41);
                            n4 += n43;
                            n5 += n55;
                            n7 += n44;
                            n8 += n56;
                            n10 += n45;
                            n11 += n57;
                            n13 += n46;
                            n14 += n58;
                            n19 += (int)n47;
                            n20 += (int)n59;
                            n91 += n48;
                            n26 += n60;
                            n92 += n49;
                            n29 += n61;
                            n93 += n50;
                            n32 += n62;
                            n94 += n51;
                            n35 += n63;
                            n95 += n52;
                            n38 += n64;
                            n96 += n53;
                            n41 += n65;
                            n2 += this.anInt127;
                        }
                        while (--n >= 0.0f) {
                            this.method215(this.anIntArray139, this.anIntArray143, (int)n2, (int)n4, (int)n6, n7, n9, n10, n12, n13, n15, n19, n21, n91, n27, n92, n30, n93, n33, n94, n36, n95, n39, n96, n42);
                            n4 += n43;
                            n6 += n67;
                            n7 += n44;
                            n9 += n68;
                            n10 += n45;
                            n12 += n69;
                            n13 += n46;
                            n15 += n70;
                            n19 += (int)n47;
                            n21 += (int)n71;
                            n91 += n48;
                            n27 += n72;
                            n92 += n49;
                            n30 += n73;
                            n93 += n50;
                            n33 += n74;
                            n94 += n51;
                            n36 += n75;
                            n95 += n52;
                            n39 += n76;
                            n96 += n53;
                            n42 += n77;
                            n2 += this.anInt127;
                        }
                    }
                    else {
                        n -= n3;
                        n3 -= n2;
                        n2 = this.anIntArray133[(int)n2];
                        while (--n3 >= 0.0f) {
                            this.method215(this.anIntArray139, this.anIntArray143, (int)n2, (int)n5, (int)n4, n8, n7, n11, n10, n14, n13, n20, n19, n26, n91, n29, n92, n32, n93, n35, n94, n38, n95, n41, n96);
                            n5 += n55;
                            n4 += n43;
                            n8 += n56;
                            n7 += n44;
                            n11 += n57;
                            n10 += n45;
                            n14 += n58;
                            n13 += n46;
                            n20 += (int)n59;
                            n19 += (int)n47;
                            n26 += n60;
                            n91 += n48;
                            n29 += n61;
                            n92 += n49;
                            n32 += n62;
                            n93 += n50;
                            n35 += n63;
                            n94 += n51;
                            n39 += n64;
                            n95 += n52;
                            n41 += n65;
                            n96 += n53;
                            n2 += this.anInt127;
                        }
                        while (--n >= 0.0f) {
                            this.method215(this.anIntArray139, this.anIntArray143, (int)n2, (int)n6, (int)n4, n9, n7, n12, n10, n15, n13, n21, n19, n27, n91, n30, n92, n33, n93, n36, n94, n39, n95, n42, n96);
                            n6 += n67;
                            n4 += n43;
                            n9 += n68;
                            n7 += n44;
                            n12 += n69;
                            n10 += n45;
                            n15 += n70;
                            n13 += n46;
                            n21 += (int)n71;
                            n19 += (int)n47;
                            n27 += n72;
                            n91 += n48;
                            n30 += n73;
                            n92 += n49;
                            n33 += n74;
                            n93 += n50;
                            n36 += n75;
                            n94 += n51;
                            n39 += n76;
                            n95 += n52;
                            n42 += n77;
                            n96 += n53;
                            n2 += this.anInt127;
                        }
                    }
                }
                else {
                    n6 = n5;
                    n9 = n8;
                    n12 = n11;
                    n15 = n14;
                    n21 = n20;
                    float n97 = n26;
                    float n98 = n29;
                    float n99 = n32;
                    float n100 = n35;
                    float n101 = n38;
                    float n102 = n41;
                    if (n2 < 0.0f) {
                        n6 -= n43 * n2;
                        n5 -= n55 * n2;
                        n9 -= n44 * n2;
                        n8 -= n56 * n2;
                        n12 -= n45 * n2;
                        n11 -= n57 * n2;
                        n15 -= n46 * n2;
                        n14 -= n58 * n2;
                        n21 -= (int)(n47 * n2);
                        n20 -= (int)(n59 * n2);
                        n97 -= n48 * n2;
                        n26 -= n60 * n2;
                        n98 -= n49 * n2;
                        n29 -= n61 * n2;
                        n99 -= n50 * n2;
                        n32 -= n62 * n2;
                        n100 -= n51 * n2;
                        n35 -= n63 * n2;
                        n101 -= n52 * n2;
                        n38 -= n64 * n2;
                        n102 -= n53 * n2;
                        n41 -= n65 * n2;
                        n2 = 0.0f;
                    }
                    if (n < 0.0f) {
                        n4 -= n67 * n;
                        n7 -= n68 * n;
                        n10 -= n69 * n;
                        n13 -= n70 * n;
                        n19 -= (int)(n71 * n);
                        n25 -= n72 * n;
                        n28 -= n73 * n;
                        n31 -= n74 * n;
                        n34 -= n75 * n;
                        n37 -= n76 * n;
                        n40 -= n77 * n;
                        n = 0.0f;
                    }
                    n3 -= n;
                    n -= n2;
                    n2 = this.anIntArray133[(int)n2];
                    if (n43 < n55) {
                        while (--n >= 0.0f) {
                            this.method215(this.anIntArray139, this.anIntArray143, (int)n2, (int)n6, (int)n5, n9, n8, n12, n11, n15, n14, n21, n20, n97, n26, n98, n29, n99, n32, n100, n35, n101, n38, n102, n41);
                            n6 += n43;
                            n5 += n55;
                            n9 += n44;
                            n8 += n56;
                            n12 += n45;
                            n11 += n57;
                            n15 += n46;
                            n14 += n58;
                            n21 += (int)n47;
                            n20 += (int)n59;
                            n97 += n48;
                            n26 += n60;
                            n98 += n49;
                            n29 += n61;
                            n99 += n50;
                            n32 += n62;
                            n100 += n51;
                            n35 += n63;
                            n101 += n52;
                            n38 += n64;
                            n102 += n53;
                            n41 += n65;
                            n2 += this.anInt127;
                        }
                        while (--n3 >= 0.0f) {
                            this.method215(this.anIntArray139, this.anIntArray143, (int)n2, (int)n4, (int)n5, n7, n8, n10, n11, n13, n14, n19, n20, n25, n26, n28, n29, n31, n32, n34, n35, n37, n38, n40, n41);
                            n4 += n67;
                            n5 += n55;
                            n7 += n68;
                            n8 += n56;
                            n10 += n69;
                            n11 += n57;
                            n13 += n70;
                            n14 += n58;
                            n19 += (int)n71;
                            n20 += (int)n59;
                            n25 += n72;
                            n26 += n60;
                            n28 += n73;
                            n29 += n61;
                            n31 += n74;
                            n32 += n62;
                            n34 += n75;
                            n35 += n63;
                            n37 += n76;
                            n38 += n64;
                            n40 += n77;
                            n41 += n65;
                            n2 += this.anInt127;
                        }
                    }
                    else {
                        while (--n >= 0.0f) {
                            this.method215(this.anIntArray139, this.anIntArray143, (int)n2, (int)n5, (int)n6, n8, n9, n11, n12, n14, n15, n20, n21, n26, n97, n29, n98, n32, n99, n35, n100, n38, n101, n41, n102);
                            n5 += n55;
                            n6 += n43;
                            n8 += n56;
                            n9 += n44;
                            n11 += n57;
                            n12 += n45;
                            n14 += n58;
                            n15 += n46;
                            n20 += (int)n59;
                            n21 += (int)n47;
                            n26 += n60;
                            n97 += n48;
                            n29 += n61;
                            n98 += n49;
                            n32 += n62;
                            n99 += n50;
                            n35 += n63;
                            n100 += n51;
                            n38 += n64;
                            n101 += n52;
                            n41 += n65;
                            n102 += n53;
                            n2 += this.anInt127;
                        }
                        while (--n3 >= 0.0f) {
                            this.method215(this.anIntArray139, this.anIntArray143, (int)n2, (int)n5, (int)n4, n8, n7, n11, n10, n14, n13, n20, n19, n26, n25, n29, n28, n32, n31, n35, n34, n38, n37, n41, n40);
                            n5 += n55;
                            n4 += n67;
                            n8 += n56;
                            n7 += n68;
                            n11 += n57;
                            n10 += n69;
                            n14 += n58;
                            n13 += n70;
                            n20 += (int)n59;
                            n19 += (int)n71;
                            n26 += n60;
                            n25 += n72;
                            n29 += n61;
                            n28 += n73;
                            n32 += n62;
                            n31 += n74;
                            n35 += n63;
                            n34 += n75;
                            n38 += n64;
                            n37 += n76;
                            n41 += n65;
                            n40 += n77;
                            n2 += this.anInt127;
                        }
                    }
                }
            }
        }
        else if (n3 < this.anInt129) {
            if (n > this.anInt129) {
                n = this.anInt129;
            }
            if (n2 > this.anInt129) {
                n2 = this.anInt129;
            }
            if (n < n2) {
                n5 = n6;
                n8 = n9;
                n11 = n12;
                n14 = n15;
                n20 = n21;
                float n103 = n27;
                float n104 = n30;
                float n105 = n33;
                float n106 = n36;
                float n107 = n39;
                float n108 = n42;
                if (n3 < 0.0f) {
                    n6 -= n67 * n3;
                    n5 -= n55 * n3;
                    n9 -= n68 * n3;
                    n8 -= n56 * n3;
                    n12 -= n69 * n3;
                    n11 -= n57 * n3;
                    n15 -= n70 * n3;
                    n14 -= n58 * n3;
                    n21 -= (int)(n71 * 3.0f);
                    n20 -= (int)(n59 * n3);
                    n27 -= n72 * n3;
                    n103 -= n60 * n3;
                    n30 -= n73 * n3;
                    n104 -= n61 * n3;
                    n33 -= n74 * n3;
                    n105 -= n62 * n3;
                    n36 -= n75 * n3;
                    n106 -= n63 * n3;
                    n39 -= n76 * n3;
                    n107 -= n64 * n3;
                    n42 -= n77 * n3;
                    n108 -= n65 * n3;
                    n3 = 0.0f;
                }
                if (n < 0.0f) {
                    n4 -= n43 * n;
                    n7 -= n44 * n;
                    n10 -= n45 * n;
                    n13 -= n46 * n;
                    n19 -= (int)(n47 * n);
                    n25 -= n48 * n;
                    n28 -= n49 * n;
                    n31 -= n50 * n;
                    n34 -= n51 * n;
                    n37 -= n52 * n;
                    n40 -= n53 * n;
                    n = 0.0f;
                }
                if (n55 < n67) {
                    n2 -= n;
                    n -= n3;
                    n3 = this.anIntArray133[(int)n3];
                    while (--n >= 0.0f) {
                        this.method215(this.anIntArray139, this.anIntArray143, (int)n3, (int)n5, (int)n6, n8, n9, n11, n12, n14, n15, n20, n21, n103, n27, n104, n30, n105, n33, n106, n36, n107, n39, n108, n42);
                        n5 += n55;
                        n6 += n67;
                        n8 += n56;
                        n9 += n68;
                        n11 += n57;
                        n12 += n69;
                        n14 += n58;
                        n15 += n70;
                        n20 += (int)n59;
                        n21 += (int)n71;
                        n103 += n60;
                        n27 += n72;
                        n104 += n61;
                        n30 += n73;
                        n105 += n62;
                        n33 += n74;
                        n106 += n63;
                        n36 += n75;
                        n107 += n64;
                        n39 += n76;
                        n108 += n65;
                        n42 += n77;
                        n3 += this.anInt127;
                    }
                    while (--n2 >= 0.0f) {
                        this.method215(this.anIntArray139, this.anIntArray143, (int)n3, (int)n5, (int)n4, n8, n7, n11, n10, n14, n13, n20, n19, n103, n25, n104, n28, n105, n31, n106, n34, n107, n37, n108, n40);
                        n5 += n55;
                        n4 += n43;
                        n8 += n56;
                        n7 += n44;
                        n11 += n57;
                        n10 += n45;
                        n14 += n58;
                        n13 += n46;
                        n20 += (int)n59;
                        n19 += (int)n47;
                        n103 += n60;
                        n25 += n48;
                        n104 += n61;
                        n28 += n49;
                        n105 += n62;
                        n31 += n50;
                        n106 += n63;
                        n34 += n51;
                        n107 += n64;
                        n37 += n52;
                        n108 += n65;
                        n40 += n53;
                        n3 += this.anInt127;
                    }
                }
                else {
                    n2 -= n;
                    n -= n3;
                    n3 = this.anIntArray133[(int)n3];
                    while (--n >= 0.0f) {
                        this.method215(this.anIntArray139, this.anIntArray143, (int)n3, (int)n6, (int)n5, n9, n8, n12, n11, n15, n14, n21, n20, n27, n103, n30, n104, n33, n105, n36, n106, n39, n107, n42, n108);
                        n6 += n67;
                        n5 += n55;
                        n9 += n68;
                        n8 += n56;
                        n12 += n69;
                        n11 += n57;
                        n15 += n70;
                        n14 += n58;
                        n21 += (int)n71;
                        n20 += (int)n59;
                        n27 += n72;
                        n103 += n60;
                        n30 += n73;
                        n104 += n61;
                        n33 += n74;
                        n105 += n62;
                        n36 += n75;
                        n106 += n63;
                        n39 += n76;
                        n107 += n64;
                        n42 += n77;
                        n108 += n65;
                        n3 += this.anInt127;
                    }
                    while (--n2 >= 0.0f) {
                        this.method215(this.anIntArray139, this.anIntArray143, (int)n3, (int)n4, (int)n5, n7, n8, n10, n11, n13, n14, n19, n20, n25, n103, n28, n104, n31, n105, n34, n106, n37, n107, n40, n108);
                        n4 += n43;
                        n5 += n55;
                        n7 += n44;
                        n8 += n56;
                        n10 += n45;
                        n11 += n57;
                        n13 += n46;
                        n14 += n58;
                        n19 += (int)n47;
                        n20 += (int)n59;
                        n25 += n48;
                        n103 += n60;
                        n28 += n49;
                        n104 += n61;
                        n31 += n50;
                        n105 += n62;
                        n34 += n51;
                        n106 += n63;
                        n37 += n52;
                        n107 += n64;
                        n40 += n53;
                        n108 += n65;
                        n3 += this.anInt127;
                    }
                }
            }
            else {
                n4 = n6;
                n7 = n9;
                n10 = n12;
                n13 = n15;
                n19 = n21;
                float n109 = n27;
                float n110 = n30;
                float n111 = n33;
                float n112 = n36;
                float n113 = n39;
                float n114 = n42;
                if (n3 < 0.0f) {
                    n6 -= n67 * n3;
                    n4 -= n55 * n3;
                    n9 -= n68 * n3;
                    n7 -= n56 * n3;
                    n12 -= n69 * n3;
                    n10 -= n57 * n3;
                    n15 -= n70 * n3;
                    n13 -= n58 * n3;
                    n21 -= (int)(n71 * 3.0f);
                    n19 -= (int)(n59 * n3);
                    n27 -= n72 * n3;
                    n109 -= n60 * n3;
                    n30 -= n73 * n3;
                    n110 -= n61 * n3;
                    n33 -= n74 * n3;
                    n111 -= n62 * n3;
                    n36 -= n75 * n3;
                    n112 -= n63 * n3;
                    n39 -= n76 * n3;
                    n113 -= n64 * n3;
                    n42 -= n77 * n3;
                    n114 -= n65 * n3;
                    n3 = 0.0f;
                }
                if (n2 < 0.0f) {
                    n5 -= n43 * n2;
                    n8 -= n44 * n2;
                    n11 -= n45 * n2;
                    n14 -= n46 * n2;
                    n20 -= (int)(n47 * n2);
                    n26 -= n48 * n2;
                    n29 -= n49 * n2;
                    n32 -= n50 * n2;
                    n35 -= n51 * n2;
                    n38 -= n52 * n2;
                    n41 -= n53 * n2;
                    n2 = 0.0f;
                }
                if (n55 < n67) {
                    n -= n2;
                    n2 -= n3;
                    n3 = this.anIntArray133[(int)n3];
                    while (--n2 >= 0.0f) {
                        this.method215(this.anIntArray139, this.anIntArray143, (int)n3, (int)n4, (int)n6, n7, n9, n10, n12, n13, n15, n19, n21, n109, n27, n110, n30, n111, n33, n112, n36, n113, n39, n114, n42);
                        n4 += n55;
                        n6 += n67;
                        n7 += n56;
                        n9 += n68;
                        n10 += n57;
                        n12 += n69;
                        n13 += n58;
                        n15 += n70;
                        n19 += (int)n59;
                        n21 += (int)n71;
                        n109 += n60;
                        n27 += n72;
                        n110 += n61;
                        n30 += n73;
                        n111 += n62;
                        n33 += n74;
                        n112 += n63;
                        n36 += n75;
                        n113 += n64;
                        n39 += n76;
                        n114 += n65;
                        n42 += n77;
                        n3 += this.anInt127;
                    }
                    while (--n >= 0.0f) {
                        this.method215(this.anIntArray139, this.anIntArray143, (int)n3, (int)n5, (int)n6, n8, n9, n11, n12, n14, n15, n20, n21, n26, n27, n29, n30, n32, n33, n35, n36, n38, n39, n41, n42);
                        n5 += n43;
                        n6 += n67;
                        n8 += n44;
                        n9 += n68;
                        n11 += n45;
                        n12 += n69;
                        n14 += n46;
                        n15 += n70;
                        n20 += (int)n47;
                        n21 += (int)n71;
                        n26 += n48;
                        n27 += n72;
                        n29 += n49;
                        n30 += n73;
                        n32 += n50;
                        n33 += n74;
                        n35 += n51;
                        n36 += n75;
                        n38 += n52;
                        n39 += n76;
                        n41 += n53;
                        n42 += n77;
                        n3 += this.anInt127;
                    }
                }
                else {
                    n -= n2;
                    n2 -= n3;
                    n3 = this.anIntArray133[(int)n3];
                    while (--n2 >= 0.0f) {
                        this.method215(this.anIntArray139, this.anIntArray143, (int)n3, (int)n6, (int)n4, n9, n7, n12, n10, n15, n13, n21, n19, n27, n109, n30, n110, n33, n111, n36, n112, n39, n113, n42, n114);
                        n6 += n67;
                        n4 += n55;
                        n9 += n68;
                        n7 += n56;
                        n12 += n69;
                        n10 += n57;
                        n15 += n70;
                        n13 += n58;
                        n21 += (int)n71;
                        n19 += (int)n59;
                        n27 += n72;
                        n109 += n60;
                        n30 += n73;
                        n110 += n61;
                        n33 += n74;
                        n111 += n62;
                        n36 += n75;
                        n112 += n63;
                        n39 += n76;
                        n113 += n64;
                        n42 += n77;
                        n114 += n65;
                        n3 += this.anInt127;
                    }
                    while (--n >= 0.0f) {
                        this.method215(this.anIntArray139, this.anIntArray143, (int)n3, (int)n6, (int)n5, n9, n8, n12, n11, n15, n14, n21, n20, n27, n26, n30, n29, n33, n32, n36, n35, n39, n38, n42, n41);
                        n6 += n67;
                        n5 += n43;
                        n9 += n68;
                        n8 += n44;
                        n12 += n69;
                        n11 += n45;
                        n15 += n70;
                        n14 += n46;
                        n21 += (int)n71;
                        n20 += (int)n47;
                        n27 += n72;
                        n26 += n48;
                        n30 += n73;
                        n29 += n49;
                        n33 += n74;
                        n32 += n50;
                        n36 += n75;
                        n35 += n51;
                        n39 += n76;
                        n38 += n52;
                        n42 += n77;
                        n41 += n53;
                        n3 += this.anInt127;
                    }
                }
            }
        }
    }
    
    final int method210() {
        return this.anIntArray133[0] / this.anInt127;
    }
    
    private final void method211(final int[] array, final float[] array2, int n, int n2, int n3, int n4, int anInt141, float n5, final float n6) {
        if (this.aBoolean135) {
            if (anInt141 > this.anInt141) {
                anInt141 = this.anInt141;
            }
            if (n4 < 0) {
                n4 = 0;
            }
        }
        if (n4 < anInt141) {
            n += n4 - 1;
            n3 = anInt141 - n4 >> 2;
            n5 += n6 * n4;
            if (this.aClass235_138.aBoolean1762) {
                if (this.anInt137 == 0) {
                    while (--n3 >= 0) {
                        if (n5 < array2[++n]) {
                            array[n] = n2;
                            array2[n] = n5;
                        }
                        n5 += n6;
                        if (n5 < array2[++n]) {
                            array[n] = n2;
                            array2[n] = n5;
                        }
                        n5 += n6;
                        if (n5 < array2[++n]) {
                            array[n] = n2;
                            array2[n] = n5;
                        }
                        n5 += n6;
                        if (n5 < array2[++n]) {
                            array[n] = n2;
                            array2[n] = n5;
                        }
                        n5 += n6;
                    }
                    n3 = (anInt141 - n4 & 0x3);
                    while (--n3 >= 0) {
                        if (n5 < array2[++n]) {
                            array[n] = n2;
                            array2[n] = n5;
                        }
                        n5 += n6;
                    }
                }
                else if (this.anInt137 == 254) {
                    if (n4 != 0 && anInt141 <= this.anInt141 - 1) {
                        while (--n3 >= 0) {
                            if (n5 < array2[++n]) {
                                array[n - 1] = array[n];
                            }
                            n5 += n6;
                            if (n5 < array2[++n]) {
                                array[n - 1] = array[n];
                            }
                            n5 += n6;
                            if (n5 < array2[++n]) {
                                array[n - 1] = array[n];
                            }
                            n5 += n6;
                            if (n5 < array2[++n]) {
                                array[n - 1] = array[n];
                            }
                            n5 += n6;
                        }
                        n3 = (anInt141 - n4 & 0x3);
                        while (--n3 >= 0) {
                            if (n5 < array2[++n]) {
                                array[n - 1] = array[n];
                            }
                            n5 += n6;
                        }
                    }
                }
                else {
                    final int anInt142 = this.anInt137;
                    final int n7 = 256 - this.anInt137;
                    n2 = ((n2 & 0xFF00FF) * n7 >> 8 & 0xFF00FF) + ((n2 & 0xFF00) * n7 >> 8 & 0xFF00);
                    while (--n3 >= 0) {
                        if (n5 < array2[++n]) {
                            final int n8 = array[n];
                            array[n] = n2 + ((n8 & 0xFF00FF) * anInt142 >> 8 & 0xFF00FF) + ((n8 & 0xFF00) * anInt142 >> 8 & 0xFF00);
                            array2[n] = n5;
                        }
                        n5 += n6;
                        if (n5 < array2[++n]) {
                            final int n9 = array[n];
                            array[n] = n2 + ((n9 & 0xFF00FF) * anInt142 >> 8 & 0xFF00FF) + ((n9 & 0xFF00) * anInt142 >> 8 & 0xFF00);
                            array2[n] = n5;
                        }
                        n5 += n6;
                        if (n5 < array2[++n]) {
                            final int n10 = array[n];
                            array[n] = n2 + ((n10 & 0xFF00FF) * anInt142 >> 8 & 0xFF00FF) + ((n10 & 0xFF00) * anInt142 >> 8 & 0xFF00);
                            array2[n] = n5;
                        }
                        n5 += n6;
                        if (n5 < array2[++n]) {
                            final int n11 = array[n];
                            array[n] = n2 + ((n11 & 0xFF00FF) * anInt142 >> 8 & 0xFF00FF) + ((n11 & 0xFF00) * anInt142 >> 8 & 0xFF00);
                            array2[n] = n5;
                        }
                        n5 += n6;
                    }
                    n3 = (anInt141 - n4 & 0x3);
                    while (--n3 >= 0) {
                        if (n5 < array2[++n]) {
                            final int n12 = array[n];
                            array[n] = n2 + ((n12 & 0xFF00FF) * anInt142 >> 8 & 0xFF00FF) + ((n12 & 0xFF00) * anInt142 >> 8 & 0xFF00);
                            array2[n] = n5;
                        }
                        n5 += n6;
                    }
                }
            }
            else if (this.anInt137 == 0) {
                while (--n3 >= 0) {
                    if (n5 < array2[++n]) {
                        array[n] = n2;
                    }
                    n5 += n6;
                    if (n5 < array2[++n]) {
                        array[n] = n2;
                    }
                    n5 += n6;
                    if (n5 < array2[++n]) {
                        array[n] = n2;
                    }
                    n5 += n6;
                    if (n5 < array2[++n]) {
                        array[n] = n2;
                    }
                    n5 += n6;
                }
                n3 = (anInt141 - n4 & 0x3);
                while (--n3 >= 0) {
                    if (n5 < array2[++n]) {
                        array[n] = n2;
                    }
                    n5 += n6;
                }
            }
            else if (this.anInt137 == 254) {
                if (n4 != 0 && anInt141 <= this.anInt141 - 1) {
                    while (--n3 >= 0) {
                        if (n5 < array2[++n]) {
                            array[n - 1] = array[n];
                        }
                        n5 += n6;
                        if (n5 < array2[++n]) {
                            array[n - 1] = array[n];
                        }
                        n5 += n6;
                        if (n5 < array2[++n]) {
                            array[n - 1] = array[n];
                        }
                        n5 += n6;
                        if (n5 < array2[++n]) {
                            array[n - 1] = array[n];
                        }
                        n5 += n6;
                    }
                    n3 = (anInt141 - n4 & 0x3);
                    while (--n3 >= 0) {
                        if (n5 < array2[++n]) {
                            array[n - 1] = array[n];
                        }
                        n5 += n6;
                    }
                }
            }
            else {
                final int anInt143 = this.anInt137;
                final int n13 = 256 - this.anInt137;
                n2 = ((n2 & 0xFF00FF) * n13 >> 8 & 0xFF00FF) + ((n2 & 0xFF00) * n13 >> 8 & 0xFF00);
                while (--n3 >= 0) {
                    if (n5 < array2[++n]) {
                        final int n14 = array[n];
                        array[n] = n2 + ((n14 & 0xFF00FF) * anInt143 >> 8 & 0xFF00FF) + ((n14 & 0xFF00) * anInt143 >> 8 & 0xFF00);
                    }
                    n5 += n6;
                    if (n5 < array2[++n]) {
                        final int n15 = array[n];
                        array[n] = n2 + ((n15 & 0xFF00FF) * anInt143 >> 8 & 0xFF00FF) + ((n15 & 0xFF00) * anInt143 >> 8 & 0xFF00);
                    }
                    n5 += n6;
                    if (n5 < array2[++n]) {
                        final int n16 = array[n];
                        array[n] = n2 + ((n16 & 0xFF00FF) * anInt143 >> 8 & 0xFF00FF) + ((n16 & 0xFF00) * anInt143 >> 8 & 0xFF00);
                    }
                    n5 += n6;
                    if (n5 < array2[++n]) {
                        final int n17 = array[n];
                        array[n] = n2 + ((n17 & 0xFF00FF) * anInt143 >> 8 & 0xFF00FF) + ((n17 & 0xFF00) * anInt143 >> 8 & 0xFF00);
                    }
                    n5 += n6;
                }
                n3 = (anInt141 - n4 & 0x3);
                while (--n3 >= 0) {
                    if (n5 < array2[++n]) {
                        final int n18 = array[n];
                        array[n] = n2 + ((n18 & 0xFF00FF) * anInt143 >> 8 & 0xFF00FF) + ((n18 & 0xFF00) * anInt143 >> 8 & 0xFF00);
                    }
                    n5 += n6;
                }
            }
        }
    }
    
    final void method212(float n, float n2, float n3, float n4, float n5, float n6, float n7, float n8, float n9, float n10, float n11, float n12, float n13, float n14, float n15, final int n16, final int n17, final int n18, final int anInt155, int n19, int n20, int n21, final int n22) {
        if (n22 != this.anInt158) {
            this.anIntArray143 = this.aHa_Sub2_131.method1915(n22);
            if (this.anIntArray143 == null) {
                this.method206((int)n, (int)n2, (int)n3, (int)n4, (int)n5, (int)n6, (int)n7, (int)n8, (int)n9, Class98_Sub46_Sub5.method1544(anInt155 | n19 << 24, (byte)114, n16), Class98_Sub46_Sub5.method1544(anInt155 | n20 << 24, (byte)110, n17), Class98_Sub46_Sub5.method1544(anInt155 | n21 << 24, (byte)108, n18));
                return;
            }
            this.anInt151 = (this.aHa_Sub2_131.method1925(n22) ? 64 : this.aHa_Sub2_131.anInt4482);
            this.anInt146 = this.anInt151 - 1;
            this.anInt147 = this.aHa_Sub2_131.method1912(n22);
            this.aBoolean154 = this.aHa_Sub2_131.method1922(n22);
        }
        this.anInt155 = anInt155;
        float n23 = n16 >> 24 & 0xFF;
        float n24 = n17 >> 24 & 0xFF;
        float n25 = n18 >> 24 & 0xFF;
        float n26 = n16 >> 16 & 0xFF;
        float n27 = n17 >> 16 & 0xFF;
        float n28 = n18 >> 16 & 0xFF;
        float n29 = n16 >> 8 & 0xFF;
        float n30 = n17 >> 8 & 0xFF;
        float n31 = n18 >> 8 & 0xFF;
        float n32 = n16 & 0xFF;
        float n33 = n17 & 0xFF;
        float n34 = n18 & 0xFF;
        n10 /= n7;
        n11 /= n8;
        n12 /= n9;
        n13 /= n7;
        n14 /= n8;
        n15 /= n9;
        n7 = 1.0f / n7;
        n8 = 1.0f / n8;
        n9 = 1.0f / n9;
        float n35 = 0.0f;
        float n36 = 0.0f;
        float n37 = 0.0f;
        float n38 = 0.0f;
        float n39 = 0.0f;
        float n40 = 0.0f;
        float n41 = 0.0f;
        float n42 = 0.0f;
        float n43 = 0.0f;
        if (n2 != n) {
            final float n44 = n2 - n;
            n35 = (n5 - n4) / n44;
            n36 = (n8 - n7) / n44;
            n37 = (n11 - n10) / n44;
            n38 = (n14 - n13) / n44;
            n39 = (n20 - n19) / n44;
            n40 = (n24 - n23) / n44;
            n41 = (n27 - n26) / n44;
            n42 = (n30 - n29) / n44;
            n43 = (n33 - n32) / n44;
        }
        float n45 = 0.0f;
        float n46 = 0.0f;
        float n47 = 0.0f;
        float n48 = 0.0f;
        float n49 = 0.0f;
        float n50 = 0.0f;
        float n51 = 0.0f;
        float n52 = 0.0f;
        float n53 = 0.0f;
        if (n3 != n2) {
            final float n54 = n3 - n2;
            n45 = (n6 - n5) / n54;
            n46 = (n9 - n8) / n54;
            n47 = (n12 - n11) / n54;
            n48 = (n15 - n14) / n54;
            n49 = (n21 - n20) / n54;
            n50 = (n25 - n24) / n54;
            n51 = (n28 - n27) / n54;
            n52 = (n31 - n30) / n54;
            n53 = (n34 - n33) / n54;
        }
        float n55 = 0.0f;
        float n56 = 0.0f;
        float n57 = 0.0f;
        float n58 = 0.0f;
        float n59 = 0.0f;
        float n60 = 0.0f;
        float n61 = 0.0f;
        float n62 = 0.0f;
        float n63 = 0.0f;
        if (n != n3) {
            final float n64 = n - n3;
            n55 = (n4 - n6) / n64;
            n56 = (n7 - n9) / n64;
            n57 = (n10 - n12) / n64;
            n58 = (n13 - n15) / n64;
            n59 = (n19 - n21) / n64;
            n60 = (n23 - n25) / n64;
            n61 = (n26 - n28) / n64;
            n62 = (n29 - n31) / n64;
            n63 = (n32 - n34) / n64;
        }
        if (n <= n2 && n <= n3) {
            if (n < this.anInt129) {
                if (n2 > this.anInt129) {
                    n2 = this.anInt129;
                }
                if (n3 > this.anInt129) {
                    n3 = this.anInt129;
                }
                if (n2 < n3) {
                    n6 = n4;
                    n9 = n7;
                    n12 = n10;
                    n15 = n13;
                    n21 = n19;
                    float n65 = n23;
                    float n66 = n26;
                    float n67 = n29;
                    float n68 = n32;
                    if (n < 0.0f) {
                        n4 -= n35 * n;
                        n6 -= n55 * n;
                        n7 -= n36 * n;
                        n9 -= n56 * n;
                        n10 -= n37 * n;
                        n12 -= n57 * n;
                        n13 -= n38 * n;
                        n15 -= n58 * n;
                        n19 -= (int)(n39 * n);
                        n21 -= (int)(n59 * n);
                        n23 -= n40 * n;
                        n65 -= n60 * n;
                        n26 -= n40 * n;
                        n66 -= n60 * n;
                        n29 -= n40 * n;
                        n67 -= n60 * n;
                        n32 -= n40 * n;
                        n68 -= n60 * n;
                        n = 0.0f;
                    }
                    if (n2 < 0.0f) {
                        n5 -= n45 * n2;
                        n8 -= n46 * n2;
                        n11 -= n47 * n2;
                        n14 -= n48 * n2;
                        n20 -= (int)(n49 * n2);
                        n24 -= n50 * n2;
                        n27 -= n51 * n2;
                        n30 -= n52 * n2;
                        n33 -= n53 * n2;
                        n2 = 0.0f;
                    }
                    if ((n != n2 && n55 < n35) || (n == n2 && n55 > n45)) {
                        n3 -= n2;
                        n2 -= n;
                        n = this.anIntArray133[(int)n];
                        while (--n2 >= 0.0f) {
                            this.method205(this.anIntArray139, this.anIntArray143, (int)n, (int)n6, (int)n4, n9, n7, n12, n10, n15, n13, n21, n19, n65, n23, n66, n26, n67, n29, n68, n32);
                            n4 += n35;
                            n6 += n55;
                            n7 += n36;
                            n9 += n56;
                            n10 += n37;
                            n12 += n57;
                            n13 += n38;
                            n15 += n58;
                            n19 += (int)n39;
                            n21 += (int)n59;
                            n23 += n40;
                            n65 += n60;
                            n26 += n41;
                            n66 += n61;
                            n29 += n42;
                            n67 += n62;
                            n32 += n43;
                            n68 += n63;
                            n += this.anInt127;
                        }
                        while (--n3 >= 0.0f) {
                            this.method205(this.anIntArray139, this.anIntArray143, (int)n, (int)n6, (int)n5, n9, n8, n12, n11, n15, n14, n21, n20, n65, n24, n66, n27, n67, n30, n68, n33);
                            n5 += n45;
                            n6 += n55;
                            n8 += n46;
                            n9 += n56;
                            n11 += n47;
                            n12 += n57;
                            n14 += n48;
                            n15 += n58;
                            n20 += (int)n49;
                            n21 += (int)n59;
                            n24 += n50;
                            n65 += n60;
                            n27 += n51;
                            n66 += n61;
                            n30 += n52;
                            n67 += n62;
                            n33 += n53;
                            n68 += n63;
                            n += this.anInt127;
                        }
                    }
                    else {
                        n3 -= n2;
                        n2 -= n;
                        n = this.anIntArray133[(int)n];
                        while (--n2 >= 0.0f) {
                            this.method205(this.anIntArray139, this.anIntArray143, (int)n, (int)n4, (int)n6, n7, n9, n10, n12, n13, n15, n19, n21, n23, n65, n26, n66, n29, n67, n32, n68);
                            n4 += n35;
                            n6 += n55;
                            n7 += n36;
                            n9 += n56;
                            n10 += n37;
                            n12 += n57;
                            n13 += n38;
                            n15 += n58;
                            n19 += (int)n39;
                            n21 += (int)n59;
                            n23 += n40;
                            n65 += n60;
                            n26 += n41;
                            n66 += n61;
                            n29 += n42;
                            n67 += n62;
                            n32 += n43;
                            n68 += n63;
                            n += this.anInt127;
                        }
                        while (--n3 >= 0.0f) {
                            this.method205(this.anIntArray139, this.anIntArray143, (int)n, (int)n5, (int)n6, n8, n9, n11, n12, n14, n15, n20, n21, n24, n65, n27, n66, n30, n67, n33, n68);
                            n5 += n45;
                            n6 += n55;
                            n8 += n46;
                            n9 += n56;
                            n11 += n47;
                            n12 += n57;
                            n14 += n48;
                            n15 += n58;
                            n20 += (int)n49;
                            n21 += (int)n59;
                            n24 += n50;
                            n65 += n60;
                            n27 += n51;
                            n66 += n61;
                            n30 += n52;
                            n67 += n62;
                            n33 += n53;
                            n68 += n63;
                            n += this.anInt127;
                        }
                    }
                }
                else {
                    n5 = n4;
                    n8 = n7;
                    n11 = n10;
                    n14 = n13;
                    n20 = n19;
                    float n69 = n23;
                    float n70 = n26;
                    float n71 = n29;
                    float n72 = n32;
                    if (n < 0.0f) {
                        n4 -= n35 * n;
                        n5 -= n55 * n;
                        n7 -= n36 * n;
                        n8 -= n56 * n;
                        n10 -= n37 * n;
                        n11 -= n57 * n;
                        n13 -= n38 * n;
                        n14 -= n58 * n;
                        n19 -= (int)(n39 * n);
                        n20 -= (int)(n59 * n);
                        n23 -= n40 * n;
                        n69 -= n60 * n;
                        n26 -= n40 * n;
                        n70 -= n60 * n;
                        n29 -= n40 * n;
                        n71 -= n60 * n;
                        n32 -= n40 * n;
                        n72 -= n60 * n;
                        n = 0.0f;
                    }
                    if (n3 < 0.0f) {
                        n6 -= n45 * n3;
                        n9 -= n46 * n3;
                        n12 -= n47 * n3;
                        n15 -= n48 * n3;
                        n21 -= (int)(n49 * n3);
                        n25 -= n50 * n3;
                        n28 -= n51 * n3;
                        n31 -= n52 * n3;
                        n34 -= n53 * n3;
                        n3 = 0.0f;
                    }
                    if ((n != n3 && n55 < n35) || (n == n3 && n45 > n35)) {
                        n2 -= n3;
                        n3 -= n;
                        n = this.anIntArray133[(int)n];
                        while (--n3 >= 0.0f) {
                            this.method205(this.anIntArray139, this.anIntArray143, (int)n, (int)n5, (int)n4, n8, n7, n11, n10, n14, n13, n20, n19, n69, n23, n70, n26, n71, n29, n72, n32);
                            n4 += n35;
                            n5 += n55;
                            n7 += n36;
                            n8 += n56;
                            n10 += n37;
                            n11 += n57;
                            n13 += n38;
                            n14 += n58;
                            n19 += (int)n39;
                            n20 += (int)n59;
                            n23 += n40;
                            n69 += n60;
                            n26 += n41;
                            n70 += n61;
                            n29 += n42;
                            n71 += n62;
                            n32 += n43;
                            n72 += n63;
                            n += this.anInt127;
                        }
                        while (--n2 >= 0.0f) {
                            this.method205(this.anIntArray139, this.anIntArray143, (int)n, (int)n6, (int)n4, n9, n7, n12, n10, n15, n13, n21, n19, n25, n23, n28, n26, n31, n29, n34, n32);
                            n6 += n45;
                            n4 += n35;
                            n9 += n46;
                            n7 += n36;
                            n12 += n47;
                            n10 += n37;
                            n15 += n48;
                            n13 += n38;
                            n21 += (int)n49;
                            n19 += (int)n39;
                            n25 += n50;
                            n23 += n40;
                            n28 += n51;
                            n26 += n41;
                            n31 += n52;
                            n29 += n42;
                            n34 += n53;
                            n32 += n43;
                            n += this.anInt127;
                        }
                    }
                    else {
                        n2 -= n3;
                        n3 -= n;
                        n = this.anIntArray133[(int)n];
                        while (--n3 >= 0.0f) {
                            this.method205(this.anIntArray139, this.anIntArray143, (int)n, (int)n4, (int)n5, n7, n8, n10, n11, n13, n14, n19, n20, n23, n69, n26, n70, n29, n71, n32, n72);
                            n5 += n55;
                            n4 += n35;
                            n8 += n56;
                            n7 += n36;
                            n11 += n57;
                            n10 += n37;
                            n14 += n58;
                            n13 += n38;
                            n20 += (int)n59;
                            n19 += (int)n39;
                            n69 += n60;
                            n23 += n40;
                            n70 += n61;
                            n26 += n41;
                            n71 += n62;
                            n29 += n42;
                            n72 += n63;
                            n32 += n43;
                            n += this.anInt127;
                        }
                        while (--n2 >= 0.0f) {
                            this.method205(this.anIntArray139, this.anIntArray143, (int)n, (int)n4, (int)n6, n7, n9, n10, n12, n13, n15, n19, n21, n23, n25, n26, n28, n29, n31, n32, n34);
                            n4 += n35;
                            n6 += n45;
                            n7 += n36;
                            n9 += n46;
                            n10 += n37;
                            n12 += n47;
                            n13 += n38;
                            n15 += n48;
                            n19 += (int)n39;
                            n21 += (int)n49;
                            n23 += n40;
                            n25 += n50;
                            n26 += n41;
                            n28 += n51;
                            n29 += n42;
                            n31 += n52;
                            n32 += n43;
                            n34 += n53;
                            n += this.anInt127;
                        }
                    }
                }
            }
        }
        else if (n2 <= n3) {
            if (n2 < this.anInt129) {
                if (n3 > this.anInt129) {
                    n3 = this.anInt129;
                }
                if (n > this.anInt129) {
                    n = this.anInt129;
                }
                if (n3 < n) {
                    n4 = n5;
                    n7 = n8;
                    n10 = n11;
                    n13 = n14;
                    n19 = n20;
                    float n73 = n24;
                    float n74 = n27;
                    float n75 = n30;
                    float n76 = n33;
                    if (n2 < 0.0f) {
                        n4 -= n35 * n2;
                        n5 -= n45 * n2;
                        n7 -= n36 * n2;
                        n8 -= n46 * n2;
                        n10 -= n37 * n2;
                        n11 -= n47 * n2;
                        n13 -= n38 * n2;
                        n14 -= n48 * n2;
                        n19 -= (int)(n39 * n2);
                        n20 -= (int)(n49 * n2);
                        n73 -= n40 * n2;
                        n24 -= n50 * n2;
                        n74 -= n41 * n2;
                        n27 -= n51 * n2;
                        n75 -= n42 * n2;
                        n30 -= n52 * n2;
                        n76 -= n43 * n2;
                        n33 -= n53 * n2;
                        n2 = 0.0f;
                    }
                    if (n3 < 0.0f) {
                        n6 -= n55 * n3;
                        n9 -= n56 * n3;
                        n12 -= n57 * n3;
                        n15 -= n58 * n3;
                        n21 -= (int)(n59 * n3);
                        n25 -= n60 * n3;
                        n28 -= n61 * n3;
                        n31 -= n62 * n3;
                        n34 -= n63 * n3;
                        n3 = 0.0f;
                    }
                    if ((n2 != n3 && n35 < n45) || (n2 == n3 && n35 > n55)) {
                        n -= n3;
                        n3 -= n2;
                        n2 = this.anIntArray133[(int)n2];
                        while (--n3 >= 0.0f) {
                            this.method205(this.anIntArray139, this.anIntArray143, (int)n2, (int)n4, (int)n5, n7, n8, n10, n11, n13, n14, n19, n20, n73, n24, n74, n27, n75, n30, n76, n33);
                            n4 += n35;
                            n5 += n45;
                            n7 += n36;
                            n8 += n46;
                            n10 += n37;
                            n11 += n47;
                            n13 += n38;
                            n14 += n48;
                            n19 += (int)n39;
                            n20 += (int)n49;
                            n73 += n40;
                            n24 += n50;
                            n74 += n41;
                            n27 += n51;
                            n75 += n42;
                            n30 += n52;
                            n76 += n43;
                            n33 += n53;
                            n2 += this.anInt127;
                        }
                        while (--n >= 0.0f) {
                            this.method205(this.anIntArray139, this.anIntArray143, (int)n2, (int)n4, (int)n6, n7, n9, n10, n12, n13, n15, n19, n21, n73, n25, n74, n28, n75, n31, n76, n34);
                            n4 += n35;
                            n6 += n55;
                            n7 += n36;
                            n9 += n56;
                            n10 += n37;
                            n12 += n57;
                            n13 += n38;
                            n15 += n58;
                            n19 += (int)n39;
                            n21 += (int)n59;
                            n73 += n40;
                            n25 += n60;
                            n74 += n41;
                            n28 += n61;
                            n75 += n42;
                            n31 += n62;
                            n76 += n43;
                            n34 += n63;
                            n2 += this.anInt127;
                        }
                    }
                    else {
                        n -= n3;
                        n3 -= n2;
                        n2 = this.anIntArray133[(int)n2];
                        while (--n3 >= 0.0f) {
                            this.method205(this.anIntArray139, this.anIntArray143, (int)n2, (int)n5, (int)n4, n8, n7, n11, n10, n14, n13, n20, n19, n24, n73, n27, n74, n30, n75, n33, n76);
                            n5 += n45;
                            n4 += n35;
                            n8 += n46;
                            n7 += n36;
                            n11 += n47;
                            n10 += n37;
                            n14 += n48;
                            n13 += n38;
                            n20 += (int)n49;
                            n19 += (int)n39;
                            n24 += n50;
                            n73 += n40;
                            n27 += n51;
                            n74 += n41;
                            n30 += n52;
                            n75 += n42;
                            n33 += n53;
                            n76 += n43;
                            n2 += this.anInt127;
                        }
                        while (--n >= 0.0f) {
                            this.method205(this.anIntArray139, this.anIntArray143, (int)n2, (int)n6, (int)n4, n9, n7, n12, n10, n15, n13, n21, n19, n25, n73, n28, n74, n31, n75, n34, n76);
                            n6 += n55;
                            n4 += n35;
                            n9 += n56;
                            n7 += n36;
                            n12 += n57;
                            n10 += n37;
                            n15 += n58;
                            n13 += n38;
                            n21 += (int)n59;
                            n19 += (int)n39;
                            n25 += n60;
                            n73 += n40;
                            n28 += n61;
                            n74 += n41;
                            n31 += n62;
                            n75 += n42;
                            n34 += n63;
                            n76 += n43;
                            n2 += this.anInt127;
                        }
                    }
                }
                else {
                    n6 = n5;
                    n9 = n8;
                    n12 = n11;
                    n15 = n14;
                    n21 = n20;
                    float n77 = n24;
                    float n78 = n27;
                    float n79 = n30;
                    float n80 = n33;
                    if (n2 < 0.0f) {
                        n6 -= n35 * n2;
                        n5 -= n45 * n2;
                        n9 -= n36 * n2;
                        n8 -= n46 * n2;
                        n12 -= n37 * n2;
                        n11 -= n47 * n2;
                        n15 -= n38 * n2;
                        n14 -= n48 * n2;
                        n21 -= (int)(n39 * n2);
                        n20 -= (int)(n49 * n2);
                        n77 -= n40 * n2;
                        n24 -= n50 * n2;
                        n78 -= n41 * n2;
                        n27 -= n51 * n2;
                        n79 -= n42 * n2;
                        n30 -= n52 * n2;
                        n80 -= n43 * n2;
                        n33 -= n53 * n2;
                        n2 = 0.0f;
                    }
                    if (n < 0.0f) {
                        n4 -= n55 * n;
                        n7 -= n56 * n;
                        n10 -= n57 * n;
                        n13 -= n58 * n;
                        n19 -= (int)(n59 * n);
                        n23 -= n60 * n;
                        n26 -= n61 * n;
                        n29 -= n62 * n;
                        n32 -= n63 * n;
                        n = 0.0f;
                    }
                    n3 -= n;
                    n -= n2;
                    n2 = this.anIntArray133[(int)n2];
                    if (n35 < n45) {
                        while (--n >= 0.0f) {
                            this.method205(this.anIntArray139, this.anIntArray143, (int)n2, (int)n6, (int)n5, n9, n8, n12, n11, n15, n14, n21, n20, n77, n24, n78, n27, n79, n30, n80, n33);
                            n6 += n35;
                            n5 += n45;
                            n9 += n36;
                            n8 += n46;
                            n12 += n37;
                            n11 += n47;
                            n15 += n38;
                            n14 += n48;
                            n21 += (int)n39;
                            n20 += (int)n49;
                            n77 += n40;
                            n24 += n50;
                            n78 += n41;
                            n27 += n51;
                            n79 += n42;
                            n30 += n52;
                            n80 += n43;
                            n33 += n53;
                            n2 += this.anInt127;
                        }
                        while (--n3 >= 0.0f) {
                            this.method205(this.anIntArray139, this.anIntArray143, (int)n2, (int)n4, (int)n5, n7, n8, n10, n11, n13, n14, n19, n20, n23, n24, n26, n27, n29, n30, n32, n33);
                            n4 += n55;
                            n5 += n45;
                            n7 += n56;
                            n8 += n46;
                            n10 += n57;
                            n11 += n47;
                            n13 += n58;
                            n14 += n48;
                            n19 += (int)n59;
                            n20 += (int)n49;
                            n23 += n60;
                            n24 += n50;
                            n26 += n61;
                            n27 += n51;
                            n29 += n62;
                            n30 += n52;
                            n32 += n63;
                            n33 += n53;
                            n2 += this.anInt127;
                        }
                    }
                    else {
                        while (--n >= 0.0f) {
                            this.method205(this.anIntArray139, this.anIntArray143, (int)n2, (int)n5, (int)n6, n8, n9, n11, n12, n14, n15, n20, n21, n24, n77, n27, n78, n30, n79, n33, n80);
                            n5 += n45;
                            n6 += n35;
                            n8 += n46;
                            n9 += n36;
                            n11 += n47;
                            n12 += n37;
                            n14 += n48;
                            n15 += n38;
                            n20 += (int)n49;
                            n21 += (int)n39;
                            n24 += n50;
                            n77 += n40;
                            n27 += n51;
                            n78 += n41;
                            n30 += n52;
                            n79 += n42;
                            n33 += n53;
                            n80 += n43;
                            n2 += this.anInt127;
                        }
                        while (--n3 >= 0.0f) {
                            this.method205(this.anIntArray139, this.anIntArray143, (int)n2, (int)n5, (int)n4, n8, n7, n11, n10, n14, n13, n20, n19, n24, n23, n27, n26, n30, n29, n33, n32);
                            n5 += n45;
                            n4 += n55;
                            n8 += n46;
                            n7 += n56;
                            n11 += n47;
                            n10 += n57;
                            n14 += n48;
                            n13 += n58;
                            n20 += (int)n49;
                            n19 += (int)n59;
                            n24 += n50;
                            n23 += n60;
                            n27 += n51;
                            n26 += n61;
                            n30 += n52;
                            n29 += n62;
                            n33 += n53;
                            n32 += n63;
                            n2 += this.anInt127;
                        }
                    }
                }
            }
        }
        else if (n3 < this.anInt129) {
            if (n > this.anInt129) {
                n = this.anInt129;
            }
            if (n2 > this.anInt129) {
                n2 = this.anInt129;
            }
            if (n < n2) {
                n5 = n6;
                n8 = n9;
                n11 = n12;
                n14 = n15;
                n20 = n21;
                float n81 = n25;
                float n82 = n28;
                float n83 = n31;
                float n84 = n34;
                if (n3 < 0.0f) {
                    n6 -= n55 * n3;
                    n5 -= n45 * n3;
                    n9 -= n56 * n3;
                    n8 -= n46 * n3;
                    n12 -= n57 * n3;
                    n11 -= n47 * n3;
                    n15 -= n58 * n3;
                    n14 -= n48 * n3;
                    n21 -= (int)(n59 * 3.0f);
                    n20 -= (int)(n49 * n3);
                    n25 -= n60 * n3;
                    n81 -= n50 * n3;
                    n28 -= n61 * n3;
                    n82 -= n51 * n3;
                    n31 -= n62 * n3;
                    n83 -= n52 * n3;
                    n34 -= n63 * n3;
                    n84 -= n53 * n3;
                    n3 = 0.0f;
                }
                if (n < 0.0f) {
                    n4 -= n35 * n;
                    n7 -= n36 * n;
                    n10 -= n37 * n;
                    n13 -= n38 * n;
                    n19 -= (int)(n39 * n);
                    n23 -= n40 * n;
                    n26 -= n41 * n;
                    n29 -= n42 * n;
                    n32 -= n43 * n;
                    n = 0.0f;
                }
                if (n45 < n55) {
                    n2 -= n;
                    n -= n3;
                    n3 = this.anIntArray133[(int)n3];
                    while (--n >= 0.0f) {
                        this.method205(this.anIntArray139, this.anIntArray143, (int)n3, (int)n5, (int)n6, n8, n9, n11, n12, n14, n15, n20, n21, n81, n25, n82, n28, n83, n31, n84, n34);
                        n5 += n45;
                        n6 += n55;
                        n8 += n46;
                        n9 += n56;
                        n11 += n47;
                        n12 += n57;
                        n14 += n48;
                        n15 += n58;
                        n20 += (int)n49;
                        n21 += (int)n59;
                        n81 += n50;
                        n25 += n60;
                        n82 += n51;
                        n28 += n61;
                        n83 += n52;
                        n31 += n62;
                        n84 += n53;
                        n34 += n63;
                        n3 += this.anInt127;
                    }
                    while (--n2 >= 0.0f) {
                        this.method205(this.anIntArray139, this.anIntArray143, (int)n3, (int)n5, (int)n4, n8, n7, n11, n10, n14, n13, n20, n19, n81, n23, n82, n26, n83, n29, n84, n32);
                        n5 += n45;
                        n4 += n35;
                        n8 += n46;
                        n7 += n36;
                        n11 += n47;
                        n10 += n37;
                        n14 += n48;
                        n13 += n38;
                        n20 += (int)n49;
                        n19 += (int)n39;
                        n81 += n50;
                        n23 += n40;
                        n82 += n51;
                        n26 += n41;
                        n83 += n52;
                        n29 += n42;
                        n84 += n53;
                        n32 += n43;
                        n3 += this.anInt127;
                    }
                }
                else {
                    n2 -= n;
                    n -= n3;
                    n3 = this.anIntArray133[(int)n3];
                    while (--n >= 0.0f) {
                        this.method205(this.anIntArray139, this.anIntArray143, (int)n3, (int)n6, (int)n5, n9, n8, n12, n11, n15, n14, n21, n20, n25, n81, n28, n82, n31, n83, n34, n84);
                        n6 += n55;
                        n5 += n45;
                        n9 += n56;
                        n8 += n46;
                        n12 += n57;
                        n11 += n47;
                        n15 += n58;
                        n14 += n48;
                        n21 += (int)n59;
                        n20 += (int)n49;
                        n25 += n60;
                        n81 += n50;
                        n28 += n61;
                        n82 += n51;
                        n31 += n62;
                        n83 += n52;
                        n34 += n63;
                        n84 += n53;
                        n3 += this.anInt127;
                    }
                    while (--n2 >= 0.0f) {
                        this.method205(this.anIntArray139, this.anIntArray143, (int)n3, (int)n4, (int)n5, n7, n8, n10, n11, n13, n14, n19, n20, n23, n81, n26, n82, n29, n83, n32, n84);
                        n4 += n35;
                        n5 += n45;
                        n7 += n36;
                        n8 += n46;
                        n10 += n37;
                        n11 += n47;
                        n13 += n38;
                        n14 += n48;
                        n19 += (int)n39;
                        n20 += (int)n49;
                        n23 += n40;
                        n81 += n50;
                        n26 += n41;
                        n82 += n51;
                        n29 += n42;
                        n83 += n52;
                        n32 += n43;
                        n84 += n53;
                        n3 += this.anInt127;
                    }
                }
            }
            else {
                n4 = n6;
                n7 = n9;
                n10 = n12;
                n13 = n15;
                n19 = n21;
                float n85 = n25;
                float n86 = n28;
                float n87 = n31;
                float n88 = n34;
                if (n3 < 0.0f) {
                    n6 -= n55 * n3;
                    n4 -= n45 * n3;
                    n9 -= n56 * n3;
                    n7 -= n46 * n3;
                    n12 -= n57 * n3;
                    n10 -= n47 * n3;
                    n15 -= n58 * n3;
                    n13 -= n48 * n3;
                    n21 -= (int)(n59 * 3.0f);
                    n19 -= (int)(n49 * n3);
                    n25 -= n60 * n3;
                    n85 -= n50 * n3;
                    n28 -= n61 * n3;
                    n86 -= n51 * n3;
                    n31 -= n62 * n3;
                    n87 -= n52 * n3;
                    n34 -= n63 * n3;
                    n88 -= n53 * n3;
                    n3 = 0.0f;
                }
                if (n2 < 0.0f) {
                    n5 -= n35 * n2;
                    n8 -= n36 * n2;
                    n11 -= n37 * n2;
                    n14 -= n38 * n2;
                    n20 -= (int)(n39 * n2);
                    n24 -= n40 * n2;
                    n27 -= n41 * n2;
                    n30 -= n42 * n2;
                    n33 -= n43 * n2;
                    n2 = 0.0f;
                }
                if (n45 < n55) {
                    n -= n2;
                    n2 -= n3;
                    n3 = this.anIntArray133[(int)n3];
                    while (--n2 >= 0.0f) {
                        this.method205(this.anIntArray139, this.anIntArray143, (int)n3, (int)n4, (int)n6, n7, n9, n10, n12, n13, n15, n19, n21, n85, n25, n86, n28, n87, n31, n88, n34);
                        n4 += n45;
                        n6 += n55;
                        n7 += n46;
                        n9 += n56;
                        n10 += n47;
                        n12 += n57;
                        n13 += n48;
                        n15 += n58;
                        n19 += (int)n49;
                        n21 += (int)n59;
                        n85 += n50;
                        n25 += n60;
                        n86 += n51;
                        n28 += n61;
                        n87 += n52;
                        n31 += n62;
                        n88 += n53;
                        n34 += n63;
                        n3 += this.anInt127;
                    }
                    while (--n >= 0.0f) {
                        this.method205(this.anIntArray139, this.anIntArray143, (int)n3, (int)n5, (int)n6, n8, n9, n11, n12, n14, n15, n20, n21, n24, n25, n27, n28, n30, n31, n33, n34);
                        n5 += n35;
                        n6 += n55;
                        n8 += n36;
                        n9 += n56;
                        n11 += n37;
                        n12 += n57;
                        n14 += n38;
                        n15 += n58;
                        n20 += (int)n39;
                        n21 += (int)n59;
                        n24 += n40;
                        n25 += n60;
                        n27 += n41;
                        n28 += n61;
                        n30 += n42;
                        n31 += n62;
                        n33 += n43;
                        n34 += n63;
                        n3 += this.anInt127;
                    }
                }
                else {
                    n -= n2;
                    n2 -= n3;
                    n3 = this.anIntArray133[(int)n3];
                    while (--n2 >= 0.0f) {
                        this.method205(this.anIntArray139, this.anIntArray143, (int)n3, (int)n6, (int)n4, n9, n7, n12, n10, n15, n13, n21, n19, n25, n85, n28, n86, n31, n87, n34, n88);
                        n6 += n55;
                        n4 += n45;
                        n9 += n56;
                        n7 += n46;
                        n12 += n57;
                        n10 += n47;
                        n15 += n58;
                        n13 += n48;
                        n21 += (int)n59;
                        n19 += (int)n49;
                        n25 += n60;
                        n85 += n50;
                        n28 += n61;
                        n86 += n51;
                        n31 += n62;
                        n87 += n52;
                        n34 += n63;
                        n88 += n53;
                        n3 += this.anInt127;
                    }
                    while (--n >= 0.0f) {
                        this.method205(this.anIntArray139, this.anIntArray143, (int)n3, (int)n6, (int)n5, n9, n8, n12, n11, n15, n14, n21, n20, n25, n24, n28, n27, n31, n30, n34, n33);
                        n6 += n55;
                        n5 += n35;
                        n9 += n56;
                        n8 += n36;
                        n12 += n57;
                        n11 += n37;
                        n15 += n58;
                        n14 += n38;
                        n21 += (int)n59;
                        n20 += (int)n39;
                        n25 += n60;
                        n24 += n40;
                        n28 += n61;
                        n27 += n41;
                        n31 += n62;
                        n30 += n42;
                        n34 += n63;
                        n33 += n43;
                        n3 += this.anInt127;
                    }
                }
            }
        }
    }
    
    final void method213(final boolean aBoolean142) {
        this.aBoolean142 = aBoolean142;
    }
    
    private final void method214(final int[] array, final float[] array2, int n, int n2, int n3, int n4, int anInt141, float n5, final float n6, float n7, float n8, float n9, float n10, float n11, float n12) {
        if (this.aBoolean135) {
            if (anInt141 > this.anInt141) {
                anInt141 = this.anInt141;
            }
            if (n4 < 0) {
                n4 = 0;
            }
        }
        if (n4 < anInt141) {
            if (this.aBoolean140) {
                n += n4;
                n7 += n8 * n4;
                n9 += n10 * n4;
                n11 += n12 * n4;
                if (this.aBoolean134) {
                    n3 = anInt141 - n4 >> 2;
                    n8 *= 4.0f;
                    n10 *= 4.0f;
                    n12 *= 4.0f;
                    if (this.anInt137 == 0) {
                        if (n3 > 0) {
                            do {
                                n2 = (0xFF000000 | (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF)));
                                n7 += n8;
                                n9 += n10;
                                n11 += n12;
                                array[n++] = n2;
                                array[n++] = n2;
                                array[n++] = n2;
                                array[n++] = n2;
                            } while (--n3 > 0);
                        }
                        n3 = (anInt141 - n4 & 0x3);
                        if (n3 > 0) {
                            n2 = (0xFF000000 | (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF)));
                            do {
                                array[n++] = n2;
                            } while (--n3 > 0);
                        }
                    }
                    else if (!this.aBoolean130) {
                        final int anInt142 = this.anInt137;
                        final int n13 = 256 - this.anInt137;
                        if (n3 > 0) {
                            do {
                                n2 = (0xFF000000 | (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF)));
                                n7 += n8;
                                n9 += n10;
                                n11 += n12;
                                n2 = ((n2 & 0xFF00FF) * n13 >> 8 & 0xFF00FF) + ((n2 & 0xFF00) * n13 >> 8 & 0xFF00);
                                final int n14 = array[n];
                                array[n++] = n2 + ((n14 & 0xFF00FF) * anInt142 >> 8 & 0xFF00FF) + ((n14 & 0xFF00) * anInt142 >> 8 & 0xFF00);
                                final int n15 = array[n];
                                array[n++] = n2 + ((n15 & 0xFF00FF) * anInt142 >> 8 & 0xFF00FF) + ((n15 & 0xFF00) * anInt142 >> 8 & 0xFF00);
                                final int n16 = array[n];
                                array[n++] = n2 + ((n16 & 0xFF00FF) * anInt142 >> 8 & 0xFF00FF) + ((n16 & 0xFF00) * anInt142 >> 8 & 0xFF00);
                                final int n17 = array[n];
                                array[n++] = n2 + ((n17 & 0xFF00FF) * anInt142 >> 8 & 0xFF00FF) + ((n17 & 0xFF00) * anInt142 >> 8 & 0xFF00);
                            } while (--n3 > 0);
                        }
                        n3 = (anInt141 - n4 & 0x3);
                        if (n3 > 0) {
                            n2 = (0xFF000000 | (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF)));
                            n2 = ((n2 & 0xFF00FF) * n13 >> 8 & 0xFF00FF) + ((n2 & 0xFF00) * n13 >> 8 & 0xFF00);
                            do {
                                final int n18 = array[n];
                                array[n++] = n2 + ((n18 & 0xFF00FF) * anInt142 >> 8 & 0xFF00FF) + ((n18 & 0xFF00) * anInt142 >> 8 & 0xFF00);
                            } while (--n3 > 0);
                        }
                    }
                    else {
                        if (n3 > 0) {
                            do {
                                n2 = (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF));
                                n7 += n8;
                                n9 += n10;
                                n11 += n12;
                                final int n19 = n++;
                                final int n20 = n2;
                                final int n21 = array[n19];
                                final int n22 = n20 + n21;
                                final int n23 = (n20 & 0xFF00FF) + (n21 & 0xFF00FF);
                                final int n24 = (n23 & 0x1000100) + (n22 - n23 & 0x10000);
                                array[n19] = (0xFF000000 | n22 - n24 | n24 - (n24 >>> 8));
                                final int n25 = n++;
                                final int n26 = n2;
                                final int n27 = array[n25];
                                final int n28 = n26 + n27;
                                final int n29 = (n26 & 0xFF00FF) + (n27 & 0xFF00FF);
                                final int n30 = (n29 & 0x1000100) + (n28 - n29 & 0x10000);
                                array[n25] = (0xFF000000 | n28 - n30 | n30 - (n30 >>> 8));
                                final int n31 = n++;
                                final int n32 = n2;
                                final int n33 = array[n31];
                                final int n34 = n32 + n33;
                                final int n35 = (n32 & 0xFF00FF) + (n33 & 0xFF00FF);
                                final int n36 = (n35 & 0x1000100) + (n34 - n35 & 0x10000);
                                array[n31] = (0xFF000000 | n34 - n36 | n36 - (n36 >>> 8));
                                final int n37 = n++;
                                final int n38 = n2;
                                final int n39 = array[n37];
                                final int n40 = n38 + n39;
                                final int n41 = (n38 & 0xFF00FF) + (n39 & 0xFF00FF);
                                final int n42 = (n41 & 0x1000100) + (n40 - n41 & 0x10000);
                                array[n37] = (0xFF000000 | n40 - n42 | n42 - (n42 >>> 8));
                            } while (--n3 > 0);
                        }
                        n3 = (anInt141 - n4 & 0x3);
                        if (n3 > 0) {
                            n2 = (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF));
                            do {
                                final int n43 = n++;
                                final int n44 = n2;
                                final int n45 = array[n43];
                                final int n46 = n44 + n45;
                                final int n47 = (n44 & 0xFF00FF) + (n45 & 0xFF00FF);
                                final int n48 = (n47 & 0x1000100) + (n46 - n47 & 0x10000);
                                array[n43] = (0xFF000000 | n46 - n48 | n48 - (n48 >>> 8));
                            } while (--n3 > 0);
                        }
                    }
                }
                else {
                    n3 = anInt141 - n4;
                    if (this.anInt137 == 0) {
                        do {
                            array[n++] = (0xFF000000 | (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF)));
                            n7 += n8;
                            n9 += n10;
                            n11 += n12;
                        } while (--n3 > 0);
                    }
                    else if (!this.aBoolean130) {
                        final int anInt143 = this.anInt137;
                        final int n49 = 256 - this.anInt137;
                        do {
                            n2 = (0xFF000000 | (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF)));
                            n7 += n8;
                            n9 += n10;
                            n11 += n12;
                            n2 = ((n2 & 0xFF00FF) * n49 >> 8 & 0xFF00FF) + ((n2 & 0xFF00) * n49 >> 8 & 0xFF00);
                            final int n50 = array[n];
                            array[n++] = n2 + ((n50 & 0xFF00FF) * anInt143 >> 8 & 0xFF00FF) + ((n50 & 0xFF00) * anInt143 >> 8 & 0xFF00);
                        } while (--n3 > 0);
                    }
                    else {
                        do {
                            final int n51 = n++;
                            final int n52 = ((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF);
                            final int n53 = array[n51];
                            final int n54 = n52 + n53;
                            final int n55 = (n52 & 0xFF00FF) + (n53 & 0xFF00FF);
                            final int n56 = (n55 & 0x1000100) + (n54 - n55 & 0x10000);
                            array[n51] = (0xFF000000 | n54 - n56 | n56 - (n56 >>> 8));
                            n7 += n8;
                            n9 += n10;
                            n11 += n12;
                        } while (--n3 > 0);
                    }
                }
            }
            else {
                n += n4 - 1;
                n5 += n6 * n4;
                n7 += n8 * n4;
                n9 += n10 * n4;
                n11 += n12 * n4;
                if (this.aClass235_138.aBoolean1762) {
                    if (this.aBoolean134) {
                        n3 = anInt141 - n4 >> 2;
                        n8 *= 4.0f;
                        n10 *= 4.0f;
                        n12 *= 4.0f;
                        if (this.anInt137 == 0) {
                            if (n3 > 0) {
                                do {
                                    n2 = (0xFF000000 | (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF)));
                                    n7 += n8;
                                    n9 += n10;
                                    n11 += n12;
                                    if (n5 < array2[++n]) {
                                        array[n] = n2;
                                        array2[n] = n5;
                                    }
                                    n5 += n6;
                                    if (n5 < array2[++n]) {
                                        array[n] = n2;
                                        array2[n] = n5;
                                    }
                                    n5 += n6;
                                    if (n5 < array2[++n]) {
                                        array[n] = n2;
                                        array2[n] = n5;
                                    }
                                    n5 += n6;
                                    if (n5 < array2[++n]) {
                                        array[n] = n2;
                                        array2[n] = n5;
                                    }
                                    n5 += n6;
                                } while (--n3 > 0);
                            }
                            n3 = (anInt141 - n4 & 0x3);
                            if (n3 > 0) {
                                n2 = (0xFF000000 | (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF)));
                                do {
                                    if (n5 < array2[++n]) {
                                        array[n] = n2;
                                        array2[n] = n5;
                                    }
                                    n5 += n6;
                                } while (--n3 > 0);
                            }
                        }
                        else if (!this.aBoolean130) {
                            final int anInt144 = this.anInt137;
                            final int n57 = 256 - this.anInt137;
                            if (n3 > 0) {
                                do {
                                    n2 = (0xFF000000 | (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF)));
                                    n7 += n8;
                                    n9 += n10;
                                    n11 += n12;
                                    n2 = ((n2 & 0xFF00FF) * n57 >> 8 & 0xFF00FF) + ((n2 & 0xFF00) * n57 >> 8 & 0xFF00);
                                    if (n5 < array2[++n]) {
                                        final int n58 = array[n];
                                        array[n] = n2 + ((n58 & 0xFF00FF) * anInt144 >> 8 & 0xFF00FF) + ((n58 & 0xFF00) * anInt144 >> 8 & 0xFF00);
                                        array2[n] = n5;
                                    }
                                    n5 += n6;
                                    if (n5 < array2[++n]) {
                                        final int n59 = array[n];
                                        array[n] = n2 + ((n59 & 0xFF00FF) * anInt144 >> 8 & 0xFF00FF) + ((n59 & 0xFF00) * anInt144 >> 8 & 0xFF00);
                                        array2[n] = n5;
                                    }
                                    n5 += n6;
                                    if (n5 < array2[++n]) {
                                        final int n60 = array[n];
                                        array[n] = n2 + ((n60 & 0xFF00FF) * anInt144 >> 8 & 0xFF00FF) + ((n60 & 0xFF00) * anInt144 >> 8 & 0xFF00);
                                        array2[n] = n5;
                                    }
                                    n5 += n6;
                                    if (n5 < array2[++n]) {
                                        final int n61 = array[n];
                                        array[n] = n2 + ((n61 & 0xFF00FF) * anInt144 >> 8 & 0xFF00FF) + ((n61 & 0xFF00) * anInt144 >> 8 & 0xFF00);
                                        array2[n] = n5;
                                    }
                                    n5 += n6;
                                } while (--n3 > 0);
                            }
                            n3 = (anInt141 - n4 & 0x3);
                            if (n3 > 0) {
                                n2 = (0xFF000000 | (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF)));
                                n2 = ((n2 & 0xFF00FF) * n57 >> 8 & 0xFF00FF) + ((n2 & 0xFF00) * n57 >> 8 & 0xFF00);
                                do {
                                    if (n5 < array2[++n]) {
                                        final int n62 = array[n];
                                        array[n] = n2 + ((n62 & 0xFF00FF) * anInt144 >> 8 & 0xFF00FF) + ((n62 & 0xFF00) * anInt144 >> 8 & 0xFF00);
                                        array2[n] = n5;
                                    }
                                    n5 += n6;
                                } while (--n3 > 0);
                            }
                        }
                        else {
                            if (n3 > 0) {
                                do {
                                    n2 = (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF));
                                    n7 += n8;
                                    n9 += n10;
                                    n11 += n12;
                                    if (n5 < array2[++n]) {
                                        final int n63 = n;
                                        final int n64 = n2;
                                        final int n65 = array[n63];
                                        final int n66 = n64 + n65;
                                        final int n67 = (n64 & 0xFF00FF) + (n65 & 0xFF00FF);
                                        final int n68 = (n67 & 0x1000100) + (n66 - n67 & 0x10000);
                                        array[n63] = (0xFF000000 | n66 - n68 | n68 - (n68 >>> 8));
                                        array2[n] = n5;
                                    }
                                    n5 += n6;
                                    if (n5 < array2[++n]) {
                                        final int n69 = n;
                                        final int n70 = n2;
                                        final int n71 = array[n69];
                                        final int n72 = n70 + n71;
                                        final int n73 = (n70 & 0xFF00FF) + (n71 & 0xFF00FF);
                                        final int n74 = (n73 & 0x1000100) + (n72 - n73 & 0x10000);
                                        array[n69] = (0xFF000000 | n72 - n74 | n74 - (n74 >>> 8));
                                        array2[n] = n5;
                                    }
                                    n5 += n6;
                                    if (n5 < array2[++n]) {
                                        final int n75 = n;
                                        final int n76 = n2;
                                        final int n77 = array[n75];
                                        final int n78 = n76 + n77;
                                        final int n79 = (n76 & 0xFF00FF) + (n77 & 0xFF00FF);
                                        final int n80 = (n79 & 0x1000100) + (n78 - n79 & 0x10000);
                                        array[n75] = (0xFF000000 | n78 - n80 | n80 - (n80 >>> 8));
                                        array2[n] = n5;
                                    }
                                    n5 += n6;
                                    if (n5 < array2[++n]) {
                                        final int n81 = n;
                                        final int n82 = n2;
                                        final int n83 = array[n81];
                                        final int n84 = n82 + n83;
                                        final int n85 = (n82 & 0xFF00FF) + (n83 & 0xFF00FF);
                                        final int n86 = (n85 & 0x1000100) + (n84 - n85 & 0x10000);
                                        array[n81] = (0xFF000000 | n84 - n86 | n86 - (n86 >>> 8));
                                        array2[n] = n5;
                                    }
                                    n5 += n6;
                                } while (--n3 > 0);
                            }
                            n3 = (anInt141 - n4 & 0x3);
                            if (n3 > 0) {
                                n2 = (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF));
                                do {
                                    if (n5 < array2[++n]) {
                                        final int n87 = n;
                                        final int n88 = n2;
                                        final int n89 = array[n87];
                                        final int n90 = n88 + n89;
                                        final int n91 = (n88 & 0xFF00FF) + (n89 & 0xFF00FF);
                                        final int n92 = (n91 & 0x1000100) + (n90 - n91 & 0x10000);
                                        array[n87] = (0xFF000000 | n90 - n92 | n92 - (n92 >>> 8));
                                        array2[n] = n5;
                                    }
                                    n5 += n6;
                                } while (--n3 > 0);
                            }
                        }
                    }
                    else {
                        n3 = anInt141 - n4;
                        if (this.anInt137 == 0) {
                            do {
                                if (n5 < array2[++n]) {
                                    array[n] = (0xFF000000 | (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF)));
                                    array2[n] = n5;
                                }
                                n5 += n6;
                                n7 += n8;
                                n9 += n10;
                                n11 += n12;
                            } while (--n3 > 0);
                        }
                        else if (!this.aBoolean130) {
                            final int anInt145 = this.anInt137;
                            final int n93 = 256 - this.anInt137;
                            do {
                                if (n5 < array2[++n]) {
                                    n2 = (0xFF000000 | (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF)));
                                    n2 = ((n2 & 0xFF00FF) * n93 >> 8 & 0xFF00FF) + ((n2 & 0xFF00) * n93 >> 8 & 0xFF00);
                                    final int n94 = array[n];
                                    array[n] = n2 + ((n94 & 0xFF00FF) * anInt145 >> 8 & 0xFF00FF) + ((n94 & 0xFF00) * anInt145 >> 8 & 0xFF00);
                                    array2[n] = n5;
                                }
                                n5 += n6;
                                n7 += n8;
                                n9 += n10;
                                n11 += n12;
                            } while (--n3 > 0);
                        }
                        else {
                            do {
                                if (n5 < array2[++n]) {
                                    final int n95 = n;
                                    final int n96 = ((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF);
                                    final int n97 = array[n95];
                                    final int n98 = n96 + n97;
                                    final int n99 = (n96 & 0xFF00FF) + (n97 & 0xFF00FF);
                                    final int n100 = (n99 & 0x1000100) + (n98 - n99 & 0x10000);
                                    array[n95] = (0xFF000000 | n98 - n100 | n100 - (n100 >>> 8));
                                    array2[n] = n5;
                                }
                                n5 += n6;
                                n7 += n8;
                                n9 += n10;
                                n11 += n12;
                            } while (--n3 > 0);
                        }
                    }
                }
                else if (this.aBoolean134) {
                    n3 = anInt141 - n4 >> 2;
                    n8 *= 4.0f;
                    n10 *= 4.0f;
                    n12 *= 4.0f;
                    if (this.anInt137 == 0) {
                        if (n3 > 0) {
                            do {
                                n2 = (0xFF000000 | (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF)));
                                n7 += n8;
                                n9 += n10;
                                n11 += n12;
                                if (n5 < array2[++n]) {
                                    array[n] = n2;
                                }
                                n5 += n6;
                                if (n5 < array2[++n]) {
                                    array[n] = n2;
                                }
                                n5 += n6;
                                if (n5 < array2[++n]) {
                                    array[n] = n2;
                                }
                                n5 += n6;
                                if (n5 < array2[++n]) {
                                    array[n] = n2;
                                }
                                n5 += n6;
                            } while (--n3 > 0);
                        }
                        n3 = (anInt141 - n4 & 0x3);
                        if (n3 > 0) {
                            n2 = (0xFF000000 | (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF)));
                            do {
                                if (n5 < array2[++n]) {
                                    array[n] = n2;
                                }
                                n5 += n6;
                            } while (--n3 > 0);
                        }
                    }
                    else if (!this.aBoolean130) {
                        final int anInt146 = this.anInt137;
                        final int n101 = 256 - this.anInt137;
                        if (n3 > 0) {
                            do {
                                n2 = (0xFF000000 | (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF)));
                                n7 += n8;
                                n9 += n10;
                                n11 += n12;
                                n2 = ((n2 & 0xFF00FF) * n101 >> 8 & 0xFF00FF) + ((n2 & 0xFF00) * n101 >> 8 & 0xFF00);
                                if (n5 < array2[++n]) {
                                    final int n102 = array[n];
                                    array[n] = n2 + ((n102 & 0xFF00FF) * anInt146 >> 8 & 0xFF00FF) + ((n102 & 0xFF00) * anInt146 >> 8 & 0xFF00);
                                }
                                n5 += n6;
                                if (n5 < array2[++n]) {
                                    final int n103 = array[n];
                                    array[n] = n2 + ((n103 & 0xFF00FF) * anInt146 >> 8 & 0xFF00FF) + ((n103 & 0xFF00) * anInt146 >> 8 & 0xFF00);
                                }
                                n5 += n6;
                                if (n5 < array2[++n]) {
                                    final int n104 = array[n];
                                    array[n] = n2 + ((n104 & 0xFF00FF) * anInt146 >> 8 & 0xFF00FF) + ((n104 & 0xFF00) * anInt146 >> 8 & 0xFF00);
                                }
                                n5 += n6;
                                if (n5 < array2[++n]) {
                                    final int n105 = array[n];
                                    array[n] = n2 + ((n105 & 0xFF00FF) * anInt146 >> 8 & 0xFF00FF) + ((n105 & 0xFF00) * anInt146 >> 8 & 0xFF00);
                                }
                                n5 += n6;
                            } while (--n3 > 0);
                        }
                        n3 = (anInt141 - n4 & 0x3);
                        if (n3 > 0) {
                            n2 = (0xFF000000 | (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF)));
                            n2 = ((n2 & 0xFF00FF) * n101 >> 8 & 0xFF00FF) + ((n2 & 0xFF00) * n101 >> 8 & 0xFF00);
                            do {
                                if (n5 < array2[++n]) {
                                    final int n106 = array[n];
                                    array[n] = n2 + ((n106 & 0xFF00FF) * anInt146 >> 8 & 0xFF00FF) + ((n106 & 0xFF00) * anInt146 >> 8 & 0xFF00);
                                }
                                n5 += n6;
                            } while (--n3 > 0);
                        }
                    }
                    else {
                        if (n3 > 0) {
                            do {
                                n2 = (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF));
                                n7 += n8;
                                n9 += n10;
                                n11 += n12;
                                if (n5 < array2[++n]) {
                                    final int n107 = n;
                                    final int n108 = n2;
                                    final int n109 = array[n107];
                                    final int n110 = n108 + n109;
                                    final int n111 = (n108 & 0xFF00FF) + (n109 & 0xFF00FF);
                                    final int n112 = (n111 & 0x1000100) + (n110 - n111 & 0x10000);
                                    array[n107] = (0xFF000000 | n110 - n112 | n112 - (n112 >>> 8));
                                }
                                n5 += n6;
                                if (n5 < array2[++n]) {
                                    final int n113 = n;
                                    final int n114 = n2;
                                    final int n115 = array[n113];
                                    final int n116 = n114 + n115;
                                    final int n117 = (n114 & 0xFF00FF) + (n115 & 0xFF00FF);
                                    final int n118 = (n117 & 0x1000100) + (n116 - n117 & 0x10000);
                                    array[n113] = (0xFF000000 | n116 - n118 | n118 - (n118 >>> 8));
                                }
                                n5 += n6;
                                if (n5 < array2[++n]) {
                                    final int n119 = n;
                                    final int n120 = n2;
                                    final int n121 = array[n119];
                                    final int n122 = n120 + n121;
                                    final int n123 = (n120 & 0xFF00FF) + (n121 & 0xFF00FF);
                                    final int n124 = (n123 & 0x1000100) + (n122 - n123 & 0x10000);
                                    array[n119] = (0xFF000000 | n122 - n124 | n124 - (n124 >>> 8));
                                }
                                n5 += n6;
                                if (n5 < array2[++n]) {
                                    final int n125 = n;
                                    final int n126 = n2;
                                    final int n127 = array[n125];
                                    final int n128 = n126 + n127;
                                    final int n129 = (n126 & 0xFF00FF) + (n127 & 0xFF00FF);
                                    final int n130 = (n129 & 0x1000100) + (n128 - n129 & 0x10000);
                                    array[n125] = (0xFF000000 | n128 - n130 | n130 - (n130 >>> 8));
                                }
                                n5 += n6;
                            } while (--n3 > 0);
                        }
                        n3 = (anInt141 - n4 & 0x3);
                        if (n3 > 0) {
                            n2 = (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF));
                            do {
                                if (n5 < array2[++n]) {
                                    final int n131 = n;
                                    final int n132 = n2;
                                    final int n133 = array[n131];
                                    final int n134 = n132 + n133;
                                    final int n135 = (n132 & 0xFF00FF) + (n133 & 0xFF00FF);
                                    final int n136 = (n135 & 0x1000100) + (n134 - n135 & 0x10000);
                                    array[n131] = (0xFF000000 | n134 - n136 | n136 - (n136 >>> 8));
                                }
                                n5 += n6;
                            } while (--n3 > 0);
                        }
                    }
                }
                else {
                    n3 = anInt141 - n4;
                    if (this.anInt137 == 0) {
                        do {
                            if (n5 < array2[++n]) {
                                array[n] = (0xFF000000 | (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF)));
                            }
                            n5 += n6;
                            n7 += n8;
                            n9 += n10;
                            n11 += n12;
                        } while (--n3 > 0);
                    }
                    else if (!this.aBoolean130) {
                        final int anInt147 = this.anInt137;
                        final int n137 = 256 - this.anInt137;
                        do {
                            if (n5 < array2[++n]) {
                                n2 = (0xFF000000 | (((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF)));
                                n2 = ((n2 & 0xFF00FF) * n137 >> 8 & 0xFF00FF) + ((n2 & 0xFF00) * n137 >> 8 & 0xFF00);
                                final int n138 = array[n];
                                array[n] = n2 + ((n138 & 0xFF00FF) * anInt147 >> 8 & 0xFF00FF) + ((n138 & 0xFF00) * anInt147 >> 8 & 0xFF00);
                            }
                            n5 += n6;
                            n7 += n8;
                            n9 += n10;
                            n11 += n12;
                        } while (--n3 > 0);
                    }
                    else {
                        do {
                            if (n5 < array2[++n]) {
                                final int n139 = n;
                                final int n140 = ((int)n7 & 0xFF0000) | ((int)n9 & 0xFF00) | ((int)n11 & 0xFF);
                                final int n141 = array[n139];
                                final int n142 = n140 + n141;
                                final int n143 = (n140 & 0xFF00FF) + (n141 & 0xFF00FF);
                                final int n144 = (n143 & 0x1000100) + (n142 - n143 & 0x10000);
                                array[n139] = (0xFF000000 | n142 - n144 | n144 - (n144 >>> 8));
                            }
                            n5 += n6;
                            n7 += n8;
                            n9 += n10;
                            n11 += n12;
                        } while (--n3 > 0);
                    }
                }
            }
        }
    }
    
    private final void method215(final int[] array, final int[] array2, int n, int n2, int anInt141, float n3, final float n4, float n5, final float n6, float n7, final float n8, float n9, final float n10, final float n11, final float n12, float n13, final float n14, float n15, final float n16, float n17, final float n18, float n19, final float n20, float n21, final float n22) {
        final float n23 = 1.0f / (anInt141 - n2);
        final float n24 = (n4 - n3) * n23;
        final float n25 = (n6 - n5) * n23;
        final float n26 = (n8 - n7) * n23;
        final float n27 = (n10 - n9) * n23;
        final float n28 = (n14 - n13) * n23;
        final float n29 = (n16 - n15) * n23;
        final float n30 = (n18 - n17) * n23;
        final float n31 = (n20 - n19) * n23;
        final float n32 = (n22 - n21) * n23;
        if (this.aBoolean135) {
            if (anInt141 > this.anInt141) {
                anInt141 = this.anInt141;
            }
            if (n2 < 0) {
                n3 -= n24 * n2;
                n5 -= n25 * n2;
                n7 -= n26 * n2;
                n9 -= n27 * n2;
                n13 -= n28 * n2;
                n15 -= n29 * n2;
                n17 -= n30 * n2;
                n19 -= n31 * n2;
                n21 -= n32 * n2;
                n2 = 0;
            }
        }
        if (n2 < anInt141) {
            int n33 = anInt141 - n2;
            n += n2;
            while (n33-- > 0) {
                final float n34 = 1.0f / n3;
                if (n34 < this.aFloatArray132[n]) {
                    final float n35 = n5 * n34;
                    final float n36 = n7 * n34;
                    final int n37 = this.anIntArray143[((int)(n36 * this.anInt151 * this.aFloat157) & this.anInt146) * this.anInt151 + ((int)(n35 * this.anInt151 * this.aFloat157) & this.anInt146)];
                    final int n38 = this.anIntArray145[((int)(n36 * this.anInt153 * this.aFloat149) & this.anInt152) * this.anInt153 + ((int)(n35 * this.anInt153 * this.aFloat149) & this.anInt152)];
                    final int n39 = this.anIntArray156[((int)(n36 * this.anInt160 * this.aFloat150) & this.anInt148) * this.anInt160 + ((int)(n35 * this.anInt160 * this.aFloat150) & this.anInt148)];
                    final float n40 = 1.0f - (n19 + n21);
                    final int n41 = (0xFF000000 | (int)(n19 * (n37 >> 16 & 0xFF)) << 16 | (int)(n19 * (n37 >> 8 & 0xFF)) << 8 | (int)(n19 * (n37 & 0xFF))) + (0xFF000000 | (int)(n21 * (n38 >> 16 & 0xFF)) << 16 | (int)(n21 * (n38 >> 8 & 0xFF)) << 8 | (int)(n21 * (n38 & 0xFF))) + (0xFF000000 | (int)(n40 * (n39 >> 16 & 0xFF)) << 16 | (int)(n40 * (n39 >> 8 & 0xFF)) << 8 | (int)(n40 * (n39 & 0xFF)));
                    int n42 = 0xFF000000 | ((int)(n13 * (n41 >> 16 & 0xFF)) << 8 & 0xFF0000) | ((int)(n15 * (n41 >> 8 & 0xFF)) & 0xFF00) | (int)(n17 * (n41 & 0xFF)) >> 8;
                    if (n9 != 0.0f) {
                        final int n43 = (int)(255.0f - n9);
                        n42 = ((((n42 & 0xFF00FF) * n43 & 0xFF00FF00) | ((n42 & 0xFF00) * n43 & 0xFF0000)) >>> 8) + ((((this.anInt155 & 0xFF00FF) * (int)n9 & 0xFF00FF00) | ((this.anInt155 & 0xFF00) * (int)n9 & 0xFF0000)) >>> 8);
                    }
                    array[n] = n42;
                    this.aFloatArray132[n] = n34;
                }
                ++n;
                n3 += n24;
                n5 += n25;
                n7 += n26;
                n9 += n27;
                n13 += n28;
                n15 += n29;
                n17 += n30;
                n19 += n31;
                n21 += n32;
            }
        }
    }
    
    final void method216(float n, float n2, float n3, float n4, float n5, float n6, float n7, float n8, float n9, float n10, float n11, float n12) {
        if (this.aBoolean142) {
            this.aHa_Sub2_131.method1789((int)n, Class221.anIntArray1665[(int)n10], (int)n2, (int)n5, -10550, (int)n4);
            this.aHa_Sub2_131.method1789((int)n2, Class221.anIntArray1665[(int)n10], (int)n3, (int)n6, -10550, (int)n5);
            this.aHa_Sub2_131.method1789((int)n3, Class221.anIntArray1665[(int)n10], (int)n, (int)n4, -10550, (int)n6);
        }
        else {
            final float n13 = n5 - n4;
            final float n14 = n2 - n;
            final float n15 = n6 - n4;
            final float n16 = n3 - n;
            final float n17 = n11 - n10;
            final float n18 = n12 - n10;
            final float n19 = n8 - n7;
            final float n20 = n9 - n7;
            float n21;
            if (n3 != n2) {
                n21 = (n6 - n5) / (n3 - n2);
            }
            else {
                n21 = 0.0f;
            }
            float n22;
            if (n2 != n) {
                n22 = n13 / n14;
            }
            else {
                n22 = 0.0f;
            }
            float n23;
            if (n3 != n) {
                n23 = n15 / n16;
            }
            else {
                n23 = 0.0f;
            }
            final float n24 = n13 * n16 - n15 * n14;
            if (n24 != 0.0f) {
                final float n25 = (n17 * n16 - n18 * n14) / n24;
                final float n26 = (n18 * n13 - n17 * n15) / n24;
                final float n27 = (n19 * n16 - n20 * n14) / n24;
                final float n28 = (n20 * n13 - n19 * n15) / n24;
                if (n <= n2 && n <= n3) {
                    if (n < this.anInt129) {
                        if (n2 > this.anInt129) {
                            n2 = this.anInt129;
                        }
                        if (n3 > this.anInt129) {
                            n3 = this.anInt129;
                        }
                        n10 = n10 - n25 * n4 + n25;
                        n7 = n7 - n27 * n4 + n27;
                        if (n2 < n3) {
                            n6 = n4;
                            if (n < 0.0f) {
                                n6 -= n23 * n;
                                n4 -= n22 * n;
                                n10 -= n26 * n;
                                n7 -= n28 * n;
                                n = 0.0f;
                            }
                            if (n2 < 0.0f) {
                                n5 -= n21 * n2;
                                n2 = 0.0f;
                            }
                            if ((n != n2 && n23 < n22) || (n == n2 && n23 > n21)) {
                                n3 -= n2;
                                n2 -= n;
                                n = this.anIntArray133[(int)n];
                                while (--n2 >= 0.0f) {
                                    this.method204(this.anIntArray139, this.aFloatArray132, (int)n, 0, 0, (int)n6, (int)n4, n10, n25, n7, n27);
                                    n6 += n23;
                                    n4 += n22;
                                    n10 += n26;
                                    n7 += n28;
                                    n += this.anInt127;
                                }
                                while (--n3 >= 0.0f) {
                                    this.method204(this.anIntArray139, this.aFloatArray132, (int)n, 0, 0, (int)n6, (int)n5, n10, n25, n7, n27);
                                    n6 += n23;
                                    n5 += n21;
                                    n10 += n26;
                                    n7 += n28;
                                    n += this.anInt127;
                                }
                            }
                            else {
                                n3 -= n2;
                                n2 -= n;
                                n = this.anIntArray133[(int)n];
                                while (--n2 >= 0.0f) {
                                    this.method204(this.anIntArray139, this.aFloatArray132, (int)n, 0, 0, (int)n4, (int)n6, n10, n25, n7, n27);
                                    n6 += n23;
                                    n4 += n22;
                                    n10 += n26;
                                    n7 += n28;
                                    n += this.anInt127;
                                }
                                while (--n3 >= 0.0f) {
                                    this.method204(this.anIntArray139, this.aFloatArray132, (int)n, 0, 0, (int)n5, (int)n6, n10, n25, n7, n27);
                                    n6 += n23;
                                    n5 += n21;
                                    n10 += n26;
                                    n7 += n28;
                                    n += this.anInt127;
                                }
                            }
                        }
                        else {
                            n5 = n4;
                            if (n < 0.0f) {
                                n5 -= n23 * n;
                                n4 -= n22 * n;
                                n10 -= n26 * n;
                                n7 -= n28 * n;
                                n = 0.0f;
                            }
                            if (n3 < 0.0f) {
                                n6 -= n21 * n3;
                                n3 = 0.0f;
                            }
                            if ((n != n3 && n23 < n22) || (n == n3 && n21 > n22)) {
                                n2 -= n3;
                                n3 -= n;
                                n = this.anIntArray133[(int)n];
                                while (--n3 >= 0.0f) {
                                    this.method204(this.anIntArray139, this.aFloatArray132, (int)n, 0, 0, (int)n5, (int)n4, n10, n25, n7, n27);
                                    n5 += n23;
                                    n4 += n22;
                                    n10 += n26;
                                    n7 += n28;
                                    n += this.anInt127;
                                }
                                while (--n2 >= 0.0f) {
                                    this.method204(this.anIntArray139, this.aFloatArray132, (int)n, 0, 0, (int)n6, (int)n4, n10, n25, n7, n27);
                                    n6 += n21;
                                    n4 += n22;
                                    n10 += n26;
                                    n7 += n28;
                                    n += this.anInt127;
                                }
                            }
                            else {
                                n2 -= n3;
                                n3 -= n;
                                n = this.anIntArray133[(int)n];
                                while (--n3 >= 0.0f) {
                                    this.method204(this.anIntArray139, this.aFloatArray132, (int)n, 0, 0, (int)n4, (int)n5, n10, n25, n7, n27);
                                    n5 += n23;
                                    n4 += n22;
                                    n10 += n26;
                                    n7 += n28;
                                    n += this.anInt127;
                                }
                                while (--n2 >= 0.0f) {
                                    this.method204(this.anIntArray139, this.aFloatArray132, (int)n, 0, 0, (int)n4, (int)n6, n10, n25, n7, n27);
                                    n6 += n21;
                                    n4 += n22;
                                    n10 += n26;
                                    n7 += n28;
                                    n += this.anInt127;
                                }
                            }
                        }
                    }
                }
                else if (n2 <= n3) {
                    if (n2 < this.anInt129) {
                        if (n3 > this.anInt129) {
                            n3 = this.anInt129;
                        }
                        if (n > this.anInt129) {
                            n = this.anInt129;
                        }
                        n11 = n11 - n25 * n5 + n25;
                        n8 = n8 - n27 * n5 + n27;
                        if (n3 < n) {
                            n4 = n5;
                            if (n2 < 0.0f) {
                                n4 -= n22 * n2;
                                n5 -= n21 * n2;
                                n11 -= n26 * n2;
                                n8 -= n28 * n2;
                                n2 = 0.0f;
                            }
                            if (n3 < 0.0f) {
                                n6 -= n23 * n3;
                                n3 = 0.0f;
                            }
                            if ((n2 != n3 && n22 < n21) || (n2 == n3 && n22 > n23)) {
                                n -= n3;
                                n3 -= n2;
                                n2 = this.anIntArray133[(int)n2];
                                while (--n3 >= 0.0f) {
                                    this.method204(this.anIntArray139, this.aFloatArray132, (int)n2, 0, 0, (int)n4, (int)n5, n11, n25, n8, n27);
                                    n4 += n22;
                                    n5 += n21;
                                    n11 += n26;
                                    n8 += n28;
                                    n2 += this.anInt127;
                                }
                                while (--n >= 0.0f) {
                                    this.method204(this.anIntArray139, this.aFloatArray132, (int)n2, 0, 0, (int)n4, (int)n6, n11, n25, n8, n27);
                                    n4 += n22;
                                    n6 += n23;
                                    n11 += n26;
                                    n8 += n28;
                                    n2 += this.anInt127;
                                }
                            }
                            else {
                                n -= n3;
                                n3 -= n2;
                                n2 = this.anIntArray133[(int)n2];
                                while (--n3 >= 0.0f) {
                                    this.method204(this.anIntArray139, this.aFloatArray132, (int)n2, 0, 0, (int)n5, (int)n4, n11, n25, n8, n27);
                                    n4 += n22;
                                    n5 += n21;
                                    n11 += n26;
                                    n8 += n28;
                                    n2 += this.anInt127;
                                }
                                while (--n >= 0.0f) {
                                    this.method204(this.anIntArray139, this.aFloatArray132, (int)n2, 0, 0, (int)n6, (int)n4, n11, n25, n8, n27);
                                    n4 += n22;
                                    n6 += n23;
                                    n11 += n26;
                                    n8 += n28;
                                    n2 += this.anInt127;
                                }
                            }
                        }
                        else {
                            n6 = n5;
                            if (n2 < 0.0f) {
                                n6 -= n22 * n2;
                                n5 -= n21 * n2;
                                n11 -= n26 * n2;
                                n8 -= n28 * n2;
                                n2 = 0.0f;
                            }
                            if (n < 0.0f) {
                                n4 -= n23 * n;
                                n = 0.0f;
                            }
                            if (n22 < n21) {
                                n3 -= n;
                                n -= n2;
                                n2 = this.anIntArray133[(int)n2];
                                while (--n >= 0.0f) {
                                    this.method204(this.anIntArray139, this.aFloatArray132, (int)n2, 0, 0, (int)n6, (int)n5, n11, n25, n8, n27);
                                    n6 += n22;
                                    n5 += n21;
                                    n11 += n26;
                                    n8 += n28;
                                    n2 += this.anInt127;
                                }
                                while (--n3 >= 0.0f) {
                                    this.method204(this.anIntArray139, this.aFloatArray132, (int)n2, 0, 0, (int)n4, (int)n5, n11, n25, n8, n27);
                                    n4 += n23;
                                    n5 += n21;
                                    n11 += n26;
                                    n8 += n28;
                                    n2 += this.anInt127;
                                }
                            }
                            else {
                                n3 -= n;
                                n -= n2;
                                n2 = this.anIntArray133[(int)n2];
                                while (--n >= 0.0f) {
                                    this.method204(this.anIntArray139, this.aFloatArray132, (int)n2, 0, 0, (int)n5, (int)n6, n11, n25, n8, n27);
                                    n6 += n22;
                                    n5 += n21;
                                    n11 += n26;
                                    n8 += n28;
                                    n2 += this.anInt127;
                                }
                                while (--n3 >= 0.0f) {
                                    this.method204(this.anIntArray139, this.aFloatArray132, (int)n2, 0, 0, (int)n5, (int)n4, n11, n25, n8, n27);
                                    n4 += n23;
                                    n5 += n21;
                                    n11 += n26;
                                    n8 += n28;
                                    n2 += this.anInt127;
                                }
                            }
                        }
                    }
                }
                else if (n3 < this.anInt129) {
                    if (n > this.anInt129) {
                        n = this.anInt129;
                    }
                    if (n2 > this.anInt129) {
                        n2 = this.anInt129;
                    }
                    n12 = n12 - n25 * n6 + n25;
                    n9 = n9 - n27 * n6 + n27;
                    if (n < n2) {
                        n5 = n6;
                        if (n3 < 0.0f) {
                            n5 -= n21 * n3;
                            n6 -= n23 * n3;
                            n12 -= n26 * n3;
                            n9 -= n28 * n3;
                            n3 = 0.0f;
                        }
                        if (n < 0.0f) {
                            n4 -= n22 * n;
                            n = 0.0f;
                        }
                        if (n21 < n23) {
                            n2 -= n;
                            n -= n3;
                            n3 = this.anIntArray133[(int)n3];
                            while (--n >= 0.0f) {
                                this.method204(this.anIntArray139, this.aFloatArray132, (int)n3, 0, 0, (int)n5, (int)n6, n12, n25, n9, n27);
                                n5 += n21;
                                n6 += n23;
                                n12 += n26;
                                n9 += n28;
                                n3 += this.anInt127;
                            }
                            while (--n2 >= 0.0f) {
                                this.method204(this.anIntArray139, this.aFloatArray132, (int)n3, 0, 0, (int)n5, (int)n4, n12, n25, n9, n27);
                                n5 += n21;
                                n4 += n22;
                                n12 += n26;
                                n9 += n28;
                                n3 += this.anInt127;
                            }
                        }
                        else {
                            n2 -= n;
                            n -= n3;
                            n3 = this.anIntArray133[(int)n3];
                            while (--n >= 0.0f) {
                                this.method204(this.anIntArray139, this.aFloatArray132, (int)n3, 0, 0, (int)n6, (int)n5, n12, n25, n9, n27);
                                n5 += n21;
                                n6 += n23;
                                n12 += n26;
                                n9 += n28;
                                n3 += this.anInt127;
                            }
                            while (--n2 >= 0.0f) {
                                this.method204(this.anIntArray139, this.aFloatArray132, (int)n3, 0, 0, (int)n4, (int)n5, n12, n25, n9, n27);
                                n5 += n21;
                                n4 += n22;
                                n12 += n26;
                                n9 += n28;
                                n3 += this.anInt127;
                            }
                        }
                    }
                    else {
                        n4 = n6;
                        if (n3 < 0.0f) {
                            n4 -= n21 * n3;
                            n6 -= n23 * n3;
                            n12 -= n26 * n3;
                            n9 -= n28 * n3;
                            n3 = 0.0f;
                        }
                        if (n2 < 0.0f) {
                            n5 -= n22 * n2;
                            n2 = 0.0f;
                        }
                        if (n21 < n23) {
                            n -= n2;
                            n2 -= n3;
                            n3 = this.anIntArray133[(int)n3];
                            while (--n2 >= 0.0f) {
                                this.method204(this.anIntArray139, this.aFloatArray132, (int)n3, 0, 0, (int)n4, (int)n6, n12, n25, n9, n27);
                                n4 += n21;
                                n6 += n23;
                                n12 += n26;
                                n9 += n28;
                                n3 += this.anInt127;
                            }
                            while (--n >= 0.0f) {
                                this.method204(this.anIntArray139, this.aFloatArray132, (int)n3, 0, 0, (int)n5, (int)n6, n12, n25, n9, n27);
                                n5 += n22;
                                n6 += n23;
                                n12 += n26;
                                n9 += n28;
                                n3 += this.anInt127;
                            }
                        }
                        else {
                            n -= n2;
                            n2 -= n3;
                            n3 = this.anIntArray133[(int)n3];
                            while (--n2 >= 0.0f) {
                                this.method204(this.anIntArray139, this.aFloatArray132, (int)n3, 0, 0, (int)n6, (int)n4, n12, n25, n9, n27);
                                n4 += n21;
                                n6 += n23;
                                n12 += n26;
                                n9 += n28;
                                n3 += this.anInt127;
                            }
                            while (--n >= 0.0f) {
                                this.method204(this.anIntArray139, this.aFloatArray132, (int)n3, 0, 0, (int)n6, (int)n5, n12, n25, n9, n27);
                                n5 += n22;
                                n6 += n23;
                                n12 += n26;
                                n9 += n28;
                                n3 += this.anInt127;
                            }
                        }
                    }
                }
            }
        }
    }
    
    Class12(final ha_Sub2 aHa_Sub2_131, final Class235 aClass235_138) {
        this.aBoolean130 = false;
        this.anIntArray133 = new int[4096];
        this.anInt137 = 0;
        this.aBoolean134 = true;
        this.aBoolean140 = false;
        this.aBoolean135 = false;
        this.aBoolean142 = false;
        this.anInt144 = -1;
        this.anInt146 = 0;
        this.aFloat149 = 0.0f;
        this.anIntArray145 = null;
        this.anInt147 = 0;
        this.aFloat150 = 0.0f;
        this.anIntArray143 = null;
        this.anInt148 = 0;
        this.anInt151 = 0;
        this.anInt152 = 0;
        this.anInt153 = 0;
        this.anIntArray156 = null;
        this.aFloat157 = 0.0f;
        this.aBoolean154 = true;
        this.anInt158 = -1;
        this.anInt159 = -1;
        this.anInt160 = 0;
        this.aHa_Sub2_131 = aHa_Sub2_131;
        this.aClass235_138 = aClass235_138;
        this.anInt127 = this.aHa_Sub2_131.anInt4505;
        this.anIntArray139 = this.aHa_Sub2_131.anIntArray4504;
        this.aFloatArray132 = this.aHa_Sub2_131.aFloatArray4487;
    }
}
