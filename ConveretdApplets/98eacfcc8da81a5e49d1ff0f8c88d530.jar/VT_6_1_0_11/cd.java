// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public final class cd extends bg
{
    private int a;
    private int b;
    private int[] c;
    private int d;
    private int e;
    private int f;
    private int[] g;
    private int[] h;
    private float[] i;
    private float[] j;
    private float[] k;
    private float[] l;
    private float[][] m;
    private float[][] n;
    private int[][] o;
    private int[][] p;
    
    public cd(final int n, final int a, final int b, final int[] c, final int e, final int f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.e = e;
        this.f = f;
        this.g = new int[b];
        this.h = new int[b];
        this.d = 1 << e;
        this.m = new float[10][40];
        this.n = new float[10][40];
        this.p = new int[10][b];
        this.o = new int[10][b];
        this.i = new float[40];
        this.j = new float[40];
        this.l = new float[40];
        this.k = new float[this.d];
    }
    
    public final void a(final float[] array, final float[] array2, final float[] array3, final float[] array4, final int n, final int n2, final float[] array5, final int n3, final float[] array6, final cH ch, int i) {
        int n4 = i;
        if (i > 10) {
            n4 = 10;
        }
        final float[] array7 = new float[this.d * this.a];
        final int[] array8 = new int[n4];
        final float[] array9 = new float[n4];
        final float[] array10 = new float[n4];
        final float[] array11 = new float[n4];
        int j;
        for (i = 0; i < n4; ++i) {
            for (j = 0; j < this.b; ++j) {
                this.o[i][j] = (this.p[i][j] = -1);
            }
        }
        for (int k = 0; k < n4; ++k) {
            for (i = 0; i < n2; ++i) {
                this.m[k][i] = array[i];
            }
        }
        int n5;
        int n6;
        int l;
        int n7;
        float[] array12;
        int n8;
        int n9;
        float[] m;
        int n10;
        for (i = 0; i < this.d; ++i) {
            n5 = i * this.a;
            n6 = i * this.a;
            for (l = 0; l < this.a; ++l) {
                array7[n5 + l] = 0.0f;
                for (n7 = 0; n7 <= l; ++n7) {
                    array12 = array7;
                    n8 = n5 + l;
                    array12[n8] += (float)(0.03125 * this.c[n6 + n7] * array6[l - n7]);
                }
            }
            this.k[i] = 0.0f;
            for (n9 = 0; n9 < this.a; ++n9) {
                m = this.k;
                n10 = i;
                m[n10] += array7[n5 + n9] * array7[n5 + n9];
            }
        }
        for (int n11 = 0; n11 < n4; ++n11) {
            array11[n11] = 0.0f;
        }
        int n12;
        int n13;
        int n14;
        float[] array13;
        int n15;
        float[] array14;
        int a;
        int d;
        float[] k2;
        int n16;
        int[] array15;
        float[] array16;
        int[] array17;
        int n17;
        float[] array18;
        int n18;
        int n19;
        float[] array19;
        int n20;
        float[] array20;
        int n21;
        int n22;
        int n23;
        float n24;
        int n25;
        boolean b;
        float n26;
        int n27;
        int[] array21;
        int n28;
        float[] array22;
        int n29;
        float[] array23;
        int a2;
        int d2;
        float[] k3;
        int n30;
        int[] array24;
        float[] array25;
        int[] array26;
        int n31;
        float[] array27;
        int n32;
        int n33;
        float[] array28;
        int n34;
        float[] array29;
        int n35;
        int n36;
        int n37;
        float n38;
        int n39;
        int n40;
        int n41;
        float[] array30;
        int n42;
        float n43;
        int n44;
        int n45;
        int n46;
        float[] i2;
        int n47;
        int n48;
        float[] i3;
        int n49;
        float n50;
        int n51;
        int n52;
        int n53;
        float n54;
        int n55;
        float n56;
        int n57;
        int n58;
        float[] i4;
        int n59;
        int n60;
        int n61;
        int n62;
        int n63;
        int n64;
        int n65;
        float[][] m2;
        int n66;
        int n67;
        int n68;
        for (i = 0; i < this.b; ++i) {
            n12 = i * this.a;
            for (n13 = 0; n13 < n4; ++n13) {
                array10[n13] = -1.0f;
            }
            for (n14 = 0; n14 < n4; ++n14) {
                if (this.f != 0) {
                    array13 = this.m[n14];
                    n15 = n12;
                    array14 = array7;
                    a = this.a;
                    d = this.d;
                    k2 = this.k;
                    n16 = n4;
                    array15 = array8;
                    array16 = array9;
                    array17 = array15;
                    n17 = n16;
                    array18 = k2;
                    n18 = d;
                    n19 = a;
                    array19 = array14;
                    n20 = n15;
                    array20 = array13;
                    n21 = 0;
                    n22 = 0;
                    for (n23 = 0; n23 < n18; ++n23) {
                        n24 = 0.0f;
                        for (n25 = 0; n25 < n19; ++n25) {
                            n24 -= array20[n20 + n25] * array19[n21++];
                        }
                        if (n24 > 0.0f) {
                            b = true;
                            n24 = -n24;
                        }
                        else {
                            b = false;
                        }
                        n26 = (float)(n24 + 0.5 * array18[n23]);
                        if (n23 < n17 || n26 < array16[n17 - 1]) {
                            for (n27 = n17 - 1; n27 > 0 && (n27 > n22 || n26 < array16[n27 - 1]); --n27) {
                                array16[n27] = array16[n27 - 1];
                                array17[n27] = array17[n27 - 1];
                            }
                            array16[n27] = n26;
                            array17[n27] = n23;
                            ++n22;
                            if (b) {
                                array21 = array17;
                                n28 = n27;
                                array21[n28] += n18;
                            }
                        }
                    }
                }
                else {
                    array22 = this.m[n14];
                    n29 = n12;
                    array23 = array7;
                    a2 = this.a;
                    d2 = this.d;
                    k3 = this.k;
                    n30 = n4;
                    array24 = array8;
                    array25 = array9;
                    array26 = array24;
                    n31 = n30;
                    array27 = k3;
                    n32 = d2;
                    n33 = a2;
                    array28 = array23;
                    n34 = n29;
                    array29 = array22;
                    n35 = 0;
                    n36 = 0;
                    for (n37 = 0; n37 < n32; ++n37) {
                        n38 = 0.5f * array27[n37];
                        for (n39 = 0; n39 < n33; ++n39) {
                            n38 -= array29[n34 + n39] * array28[n35++];
                        }
                        if (n37 < n31 || n38 < array25[n31 - 1]) {
                            for (n40 = n31 - 1; n40 > 0 && (n40 > n36 || n38 < array25[n40 - 1]); --n40) {
                                array25[n40] = array25[n40 - 1];
                                array26[n40] = array26[n40 - 1];
                            }
                            array25[n40] = n38;
                            array26[n40] = n37;
                            ++n36;
                        }
                    }
                }
                for (n41 = 0; n41 < n4; ++n41) {
                    array30 = this.m[n14];
                    for (n42 = n12; n42 < n12 + this.a; ++n42) {
                        this.i[n42] = array30[n42];
                    }
                    n43 = 1.0f;
                    if ((n44 = array8[n41]) >= this.d) {
                        n43 = -1.0f;
                        n44 -= this.d;
                    }
                    n45 = n44 * this.a;
                    if (n43 > 0.0f) {
                        for (n46 = 0; n46 < this.a; ++n46) {
                            i2 = this.i;
                            n47 = n12 + n46;
                            i2[n47] -= array7[n45 + n46];
                        }
                    }
                    else {
                        for (n48 = 0; n48 < this.a; ++n48) {
                            i3 = this.i;
                            n49 = n12 + n48;
                            i3[n49] += array7[n45 + n48];
                        }
                    }
                    n50 = array11[n14];
                    for (n51 = n12; n51 < n12 + this.a; ++n51) {
                        n50 += this.i[n51] * this.i[n51];
                    }
                    if (n50 < array10[n4 - 1] || array10[n4 - 1] < -0.5) {
                        for (n52 = n12 + this.a; n52 < n2; ++n52) {
                            this.i[n52] = array30[n52];
                        }
                        for (n53 = 0; n53 < this.a; ++n53) {
                            n54 = 1.0f;
                            if ((n55 = array8[n41]) >= this.d) {
                                n54 = -1.0f;
                                n55 -= this.d;
                            }
                            n56 = n54 * 0.03125f * this.c[n55 * this.a + n53];
                            for (n57 = this.a - n53, n58 = n12 + this.a; n58 < n2; ++n58, ++n57) {
                                i4 = this.i;
                                n59 = n58;
                                i4[n59] -= n56 * array6[n57];
                            }
                        }
                        for (n60 = 0; n60 < n4; ++n60) {
                            if (n50 < array10[n60] || array10[n60] < -0.5) {
                                for (n61 = n4 - 1; n61 > n60; --n61) {
                                    for (n62 = n12 + this.a; n62 < n2; ++n62) {
                                        this.n[n61][n62] = this.n[n61 - 1][n62];
                                    }
                                    for (n63 = 0; n63 < this.b; ++n63) {
                                        this.o[n61][n63] = this.o[n61 - 1][n63];
                                    }
                                    array10[n61] = array10[n61 - 1];
                                }
                                for (n64 = n12 + this.a; n64 < n2; ++n64) {
                                    this.n[n60][n64] = this.i[n64];
                                }
                                for (n65 = 0; n65 < this.b; ++n65) {
                                    this.o[n60][n65] = this.p[n14][n65];
                                }
                                this.o[n60][i] = array8[n41];
                                array10[n60] = n50;
                                break;
                            }
                        }
                    }
                }
                if (i == 0) {
                    break;
                }
            }
            m2 = this.m;
            this.m = this.n;
            this.n = m2;
            for (n66 = 0; n66 < n4; ++n66) {
                for (n67 = 0; n67 < this.b; ++n67) {
                    this.p[n66][n67] = this.o[n66][n67];
                }
            }
            for (n68 = 0; n68 < n4; ++n68) {
                array11[n68] = array10[n68];
            }
        }
        for (i = 0; i < this.b; ++i) {
            ch.a(this.g[i] = this.o[0][i], this.e + this.f);
        }
        float n69;
        int n70;
        int n71;
        for (i = 0; i < this.b; ++i) {
            n69 = 1.0f;
            if ((n70 = this.g[i]) >= this.d) {
                n69 = -1.0f;
                n70 -= this.d;
            }
            for (n71 = 0; n71 < this.a; ++n71) {
                this.j[this.a * i + n71] = n69 * 0.03125f * this.c[n70 * this.a + n71];
            }
        }
        for (int n72 = 0; n72 < n2; ++n72) {
            final int n73 = n3 + n72;
            array5[n73] += this.j[n72];
        }
        ba.a(this.j, 0, array2, array3, array4, this.l, n2, n);
        for (int n74 = 0; n74 < n2; ++n74) {
            final int n75 = n74;
            array[n75] -= this.l[n74];
        }
    }
    
    public final void a(final float[] array, final int n, int i, final cH ch) {
        for (i = 0; i < this.b; ++i) {
            if (this.f != 0) {
                this.h[i] = ch.b(1);
            }
            else {
                this.h[i] = 0;
            }
            this.g[i] = ch.b(this.e);
        }
        float n2;
        int j;
        int n3;
        for (i = 0; i < this.b; ++i) {
            n2 = 1.0f;
            if (this.h[i] != 0) {
                n2 = -1.0f;
            }
            for (j = 0; j < this.a; ++j) {
                n3 = n + this.a * i + j;
                array[n3] += n2 * 0.03125f * this.c[this.g[i] * this.a + j];
            }
        }
    }
}
