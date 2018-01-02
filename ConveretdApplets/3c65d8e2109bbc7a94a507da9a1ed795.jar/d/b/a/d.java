// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

public class d extends e
{
    private float[] if;
    private int[] int;
    private int for;
    private int a;
    private float[][] do;
    
    public d(final int[] int1, final int for1, final int a) {
        this.if = new float[3];
        this.int = int1;
        this.for = for1;
        this.a = a;
        this.do = new float[3][128];
    }
    
    public final int a(final float[] array, final float[] array2, final int n, final float[] array3, final float[] array4, final float[] array5, final float[] array6, final int n2, final int n3, final int n4, final float n5, final int n6, final int n7, final ad ad, final float[] array7, final int n8, final float[] array8, final int n9) {
        final int[] array9 = { 0 };
        int n10 = 0;
        int n11 = 0;
        int n12 = 0;
        float n13 = -1.0f;
        int n14 = n9;
        if (n14 > 10) {
            n14 = 10;
        }
        final int[] array10 = new int[n14];
        final float[] array11 = new float[n14];
        if (n14 == 0 || n4 < n3) {
            ad.a(0, this.a);
            ad.a(0, this.for);
            for (int i = 0; i < n7; ++i) {
                array6[n2 + i] = 0.0f;
            }
            return n3;
        }
        final float[] array12 = new float[n7];
        if (n14 > n4 - n3 + 1) {
            n14 = n4 - n3 + 1;
        }
        e.a(array2, n, n3, n4, n7, array10, array11, n14);
        for (int j = 0; j < n14; ++j) {
            n10 = array10[j];
            for (int k = 0; k < n7; ++k) {
                array6[n2 + k] = 0.0f;
            }
            final float a = this.a(array, array3, array4, array5, array6, n2, n10, n6, n7, ad, array7, n8, array8, array9);
            if (a < n13 || n13 < 0.0f) {
                for (int l = 0; l < n7; ++l) {
                    array12[l] = array6[n2 + l];
                }
                n13 = a;
                n12 = n10;
                n11 = array9[0];
            }
        }
        ad.a(n12 - n3, this.a);
        ad.a(n11, this.for);
        for (int n15 = 0; n15 < n7; ++n15) {
            array6[n2 + n15] = array12[n15];
        }
        return n10;
    }
    
    public final int a(final float[] array, final int n, final int n2, final float n3, final int n4, final float[] array2, final ad ad, final int n5, final int n6, final float n7) {
        final int n8 = ad.a(this.a) + n2;
        final int a = ad.a(this.for);
        this.if[0] = 0.015625f * this.int[a * 3] + 0.5f;
        this.if[1] = 0.015625f * this.int[a * 3 + 1] + 0.5f;
        this.if[2] = 0.015625f * this.int[a * 3 + 2] + 0.5f;
        if (n5 != 0 && n8 > n6) {
            final float abs = Math.abs(this.if[1]);
            float n9 = (n5 < 4) ? n7 : (0.4f * n7);
            if (n9 > 0.95f) {
                n9 = 0.95f;
            }
            float n10;
            if (this.if[0] > 0.0f) {
                n10 = abs + this.if[0];
            }
            else {
                n10 = abs - 0.5f * this.if[0];
            }
            float n11;
            if (this.if[2] > 0.0f) {
                n11 = n10 + this.if[2];
            }
            else {
                n11 = n10 - 0.5f * this.if[0];
            }
            if (n11 > n9) {
                final float n12 = n9 / n11;
                for (int i = 0; i < 3; ++i) {
                    final float[] if1 = this.if;
                    final int n13 = i;
                    if1[n13] *= n12;
                }
            }
        }
        array2[0] = this.if[0];
        array2[1] = this.if[1];
        array2[2] = this.if[2];
        for (int j = 0; j < 3; ++j) {
            final int n14 = n8 + 1 - j;
            int n15 = n4;
            if (n15 > n14) {
                n15 = n14;
            }
            int n16 = n4;
            if (n16 > n14 + n8) {
                n16 = n14 + n8;
            }
            for (int k = 0; k < n15; ++k) {
                this.do[j][k] = array[n + k - n14];
            }
            for (int l = n15; l < n16; ++l) {
                this.do[j][l] = array[n + l - n14 - n8];
            }
            for (int n17 = n16; n17 < n4; ++n17) {
                this.do[j][n17] = 0.0f;
            }
        }
        for (int n18 = 0; n18 < n4; ++n18) {
            array[n + n18] = this.if[0] * this.do[2][n18] + this.if[1] * this.do[1][n18] + this.if[2] * this.do[0][n18];
        }
        return n8;
    }
    
    private float a(final float[] array, final float[] array2, final float[] array3, final float[] array4, final float[] array5, final int n, final int n2, final int n3, final int n4, final ad ad, final float[] array6, final int n5, final float[] array7, final int[] array8) {
        final float[] array9 = new float[3];
        final float[][] array10 = new float[3][3];
        final int n6 = 1 << this.for;
        final float[][] array11 = new float[3][n4];
        this.do = new float[3][n4];
        for (int i = 2; i >= 0; --i) {
            final int n7 = n2 + 1 - i;
            for (int j = 0; j < n4; ++j) {
                if (j - n7 < 0) {
                    this.do[i][j] = array6[n5 + j - n7];
                }
                else if (j - n7 - n2 < 0) {
                    this.do[i][j] = array6[n5 + j - n7 - n2];
                }
                else {
                    this.do[i][j] = 0.0f;
                }
            }
            if (i == 2) {
                ae.a(this.do[i], 0, array2, array3, array4, array11[i], n4, n3);
            }
            else {
                for (int k = 0; k < n4 - 1; ++k) {
                    array11[i][k + 1] = array11[i + 1][k];
                }
                array11[i][0] = 0.0f;
                for (int l = 0; l < n4; ++l) {
                    final float[] array12 = array11[i];
                    final int n8 = l;
                    array12[n8] += this.do[i][0] * array7[l];
                }
            }
        }
        for (int n9 = 0; n9 < 3; ++n9) {
            array9[n9] = e.a(array11[n9], 0, array, 0, n4);
        }
        for (int n10 = 0; n10 < 3; ++n10) {
            for (int n11 = 0; n11 <= n10; ++n11) {
                array10[n10][n11] = (array10[n11][n10] = e.a(array11[n10], 0, array11[n11], 0, n4));
            }
        }
        final float[] array13 = new float[9];
        int n12 = 0;
        float n13 = 0.0f;
        array13[0] = array9[2];
        array13[1] = array9[1];
        array13[2] = array9[0];
        array13[3] = array10[1][2];
        array13[4] = array10[0][1];
        array13[5] = array10[0][2];
        array13[6] = array10[2][2];
        array13[7] = array10[1][1];
        array13[8] = array10[0][0];
        for (int n14 = 0; n14 < n6; ++n14) {
            final float n15 = 0.0f;
            final int n16 = 3 * n14;
            final float n17 = 0.015625f * this.int[n16] + 0.5f;
            final float n18 = 0.015625f * this.int[n16 + 1] + 0.5f;
            final float n19 = 0.015625f * this.int[n16 + 2] + 0.5f;
            final float n20 = n15 + array13[0] * n17 + array13[1] * n18 + array13[2] * n19 - array13[3] * n17 * n18 - array13[4] * n19 * n18 - array13[5] * n19 * n17 - 0.5f * array13[6] * n17 * n17 - 0.5f * array13[7] * n18 * n18 - 0.5f * array13[8] * n19 * n19;
            if (n20 > n13 || n14 == 0) {
                n13 = n20;
                n12 = n14;
            }
        }
        this.if[0] = 0.015625f * this.int[n12 * 3] + 0.5f;
        this.if[1] = 0.015625f * this.int[n12 * 3 + 1] + 0.5f;
        this.if[2] = 0.015625f * this.int[n12 * 3 + 2] + 0.5f;
        array8[0] = n12;
        for (int n21 = 0; n21 < n4; ++n21) {
            array5[n + n21] = this.if[0] * this.do[2][n21] + this.if[1] * this.do[1][n21] + this.if[2] * this.do[0][n21];
        }
        float n22 = 0.0f;
        float n23 = 0.0f;
        for (int n24 = 0; n24 < n4; ++n24) {
            n22 += array[n24] * array[n24];
        }
        for (int n25 = 0; n25 < n4; ++n25) {
            n23 += (array[n25] - this.if[2] * array11[0][n25] - this.if[1] * array11[1][n25] - this.if[0] * array11[2][n25]) * (array[n25] - this.if[2] * array11[0][n25] - this.if[1] * array11[1][n25] - this.if[0] * array11[2][n25]);
        }
        return n23;
    }
}
