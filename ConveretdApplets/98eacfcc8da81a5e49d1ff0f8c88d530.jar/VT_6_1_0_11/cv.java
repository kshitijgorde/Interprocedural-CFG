// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public final class cv extends bI
{
    private float[] a;
    private int[] b;
    private int c;
    private int d;
    private float[][] e;
    
    public cv(final int[] b, final int c, final int d) {
        this.a = new float[3];
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = new float[3][128];
    }
    
    public final int a(final float[] array, final float[] array2, int i, final float[] array3, final float[] array4, final float[] array5, final float[] array6, final int n, final int n2, final int n3, final float n4, final int n5, final int n6, final cH ch, final float[] array7, final int n7, final float[] array8, final int n8) {
        final int[] array9 = { 0 };
        int n9 = 0;
        int n10 = 0;
        int n11 = 0;
        float n12 = -1.0f;
        int n13 = n8;
        if (n8 > 10) {
            n13 = 10;
        }
        final int[] array10 = new int[n13];
        final float[] array11 = new float[n13];
        if (n13 == 0 || n3 < n2) {
            ch.a(0, this.d);
            ch.a(0, this.c);
            for (int j = 0; j < n6; ++j) {
                array6[n + j] = 0.0f;
            }
            return n2;
        }
        final float[] array12 = new float[n6];
        if (n13 > n3 - n2 + 1) {
            n13 = n3 - n2 + 1;
        }
        bI.a(array2, i, n2, n3, n6, array10, array11, n13);
        for (int k = 0; k < n13; ++k) {
            n9 = array10[k];
            for (i = 0; i < n6; ++i) {
                array6[n + i] = 0.0f;
            }
            final int n14 = n9;
            final int[] array13 = array9;
            final int n15 = n14;
            final float[] array14 = new float[3];
            final float[][] array15 = new float[3][3];
            final int n16 = 1 << this.c;
            final float[][] array16 = new float[3][n6];
            this.e = new float[3][n6];
            for (int l = 2; l >= 0; --l) {
                final int n17 = n15 + 1 - l;
                for (int n18 = 0; n18 < n6; ++n18) {
                    if (n18 - n17 < 0) {
                        this.e[l][n18] = array7[n7 + n18 - n17];
                    }
                    else if (n18 - n17 - n15 < 0) {
                        this.e[l][n18] = array7[n7 + n18 - n17 - n15];
                    }
                    else {
                        this.e[l][n18] = 0.0f;
                    }
                }
                if (l == 2) {
                    ba.a(this.e[l], 0, array3, array4, array5, array16[l], n6, n5);
                }
                else {
                    for (int n19 = 0; n19 < n6 - 1; ++n19) {
                        array16[l][n19 + 1] = array16[l + 1][n19];
                    }
                    array16[l][0] = 0.0f;
                    for (int n20 = 0; n20 < n6; ++n20) {
                        final float[] array17 = array16[l];
                        final int n21 = n20;
                        array17[n21] += this.e[l][0] * array8[n20];
                    }
                }
            }
            for (int n22 = 0; n22 < 3; ++n22) {
                array14[n22] = bI.a(array16[n22], 0, array, 0, n6);
            }
            for (int n23 = 0; n23 < 3; ++n23) {
                for (int n24 = 0; n24 <= n23; ++n24) {
                    array15[n23][n24] = (array15[n24][n23] = bI.a(array16[n23], 0, array16[n24], 0, n6));
                }
            }
            final float[] array18 = new float[9];
            int n25 = 0;
            float n26 = 0.0f;
            array18[0] = array14[2];
            array18[1] = array14[1];
            array18[2] = array14[0];
            array18[3] = array15[1][2];
            array18[4] = array15[0][1];
            array18[5] = array15[0][2];
            array18[6] = array15[2][2];
            array18[7] = array15[1][1];
            array18[8] = array15[0][0];
            for (int n27 = 0; n27 < n16; ++n27) {
                final int n28 = n27 * 3;
                final float n29 = 0.015625f * this.b[n28] + 0.5f;
                final float n30 = 0.015625f * this.b[n28 + 1] + 0.5f;
                final float n31 = 0.015625f * this.b[n28 + 2] + 0.5f;
                final float n32;
                if ((n32 = 0.0f + array18[0] * n29 + array18[1] * n30 + array18[2] * n31 - array18[3] * n29 * n30 - array18[4] * n31 * n30 - array18[5] * n31 * n29 - 0.5f * array18[6] * n29 * n29 - 0.5f * array18[7] * n30 * n30 - 0.5f * array18[8] * n31 * n31) > n26 || n27 == 0) {
                    n26 = n32;
                    n25 = n27;
                }
            }
            this.a[0] = 0.015625f * this.b[n25 * 3] + 0.5f;
            this.a[1] = 0.015625f * this.b[n25 * 3 + 1] + 0.5f;
            this.a[2] = 0.015625f * this.b[n25 * 3 + 2] + 0.5f;
            array13[0] = n25;
            for (int n33 = 0; n33 < n6; ++n33) {
                array6[n + n33] = this.a[0] * this.e[2][n33] + this.a[1] * this.e[1][n33] + this.a[2] * this.e[0][n33];
            }
            float n34 = 0.0f;
            for (int n35 = 0; n35 < n6; ++n35) {
                n34 += (array[n35] - this.a[2] * array16[0][n35] - this.a[1] * array16[1][n35] - this.a[0] * array16[2][n35]) * (array[n35] - this.a[2] * array16[0][n35] - this.a[1] * array16[1][n35] - this.a[0] * array16[2][n35]);
            }
            final float n36;
            if ((n36 = n34) < n12 || n12 < 0.0f) {
                for (i = 0; i < n6; ++i) {
                    array12[i] = array6[n + i];
                }
                n12 = n36;
                n11 = n9;
                n10 = array9[0];
            }
        }
        ch.a(n11 - n2, this.d);
        ch.a(n10, this.c);
        for (int n37 = 0; n37 < n6; ++n37) {
            array6[n + n37] = array12[n37];
        }
        return n9;
    }
    
    public final int a(final float[] array, final int n, int i, final float n2, final int n3, final float[] array2, final cH ch, int n4, int n5, final float n6) {
        final int n7 = ch.b(this.d) + i;
        i = ch.b(this.c);
        this.a[0] = 0.015625f * this.b[i * 3] + 0.5f;
        this.a[1] = 0.015625f * this.b[i * 3 + 1] + 0.5f;
        this.a[2] = 0.015625f * this.b[i * 3 + 2] + 0.5f;
        if (n4 != 0 && n7 > n5) {
            final float abs = Math.abs(this.a[1]);
            float n8;
            float n9;
            if (n4 < 4) {
                n8 = n6;
                n9 = n6;
            }
            else {
                n8 = (n9 = 0.4f * n6);
            }
            float n10 = n9;
            if (n8 > 0.95f) {
                n10 = 0.95f;
            }
            float n11;
            if (this.a[0] > 0.0f) {
                n11 = abs + this.a[0];
            }
            else {
                n11 = abs - 0.5f * this.a[0];
            }
            float n12;
            if (this.a[2] > 0.0f) {
                n12 = n11 + this.a[2];
            }
            else {
                n12 = n11 - 0.5f * this.a[0];
            }
            if (n12 > n10) {
                final float n13 = n10 / n12;
                float[] a;
                int n14;
                for (i = 0; i < 3; ++i) {
                    a = this.a;
                    n14 = i;
                    a[n14] *= n13;
                }
            }
        }
        array2[0] = this.a[0];
        array2[1] = this.a[1];
        array2[2] = this.a[2];
        int n15;
        int j;
        int k;
        int l;
        for (i = 0; i < 3; ++i) {
            n15 = n7 + 1 - i;
            if ((n4 = n3) > n15) {
                n4 = n15;
            }
            if ((n5 = n3) > n15 + n7) {
                n5 = n15 + n7;
            }
            for (j = 0; j < n4; ++j) {
                this.e[i][j] = array[n + j - n15];
            }
            for (k = n4; k < n5; ++k) {
                this.e[i][k] = array[n + k - n15 - n7];
            }
            for (l = n5; l < n3; ++l) {
                this.e[i][l] = 0.0f;
            }
        }
        for (i = 0; i < n3; ++i) {
            array[n + i] = this.a[0] * this.e[2][i] + this.a[1] * this.e[1][i] + this.a[2] * this.e[0][i];
        }
        return n7;
    }
}
