// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.graphics.codec;

class j
{
    public int e;
    public int f;
    public Object[] b;
    public Object[] c;
    public double[] a;
    public double[] d;
    
    public j(final int n) {
        this.e = 8;
        this.f = 80;
        this.b = new Object[2];
        this.c = new Object[2];
        this.a = new double[this.e * this.e];
        this.d = new double[this.e * this.e];
        this.a(n);
    }
    
    private void a(final int n) {
        final int[] array = { 16, 11, 10, 16, 24, 40, 51, 61, 12, 12, 14, 19, 26, 58, 60, 55, 14, 13, 16, 24, 40, 57, 69, 56, 14, 17, 22, 29, 51, 87, 80, 62, 18, 22, 37, 56, 68, 109, 103, 77, 24, 35, 55, 64, 81, 104, 113, 92, 49, 64, 78, 87, 103, 121, 120, 101, 72, 92, 95, 98, 112, 100, 103, 99 };
        final int[] array2 = { 17, 18, 24, 47, 99, 99, 99, 99, 18, 21, 26, 66, 99, 99, 99, 99, 24, 26, 56, 99, 99, 99, 99, 99, 47, 66, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99 };
        final double[] array3 = { 1.0, 1.387039845, 1.306562965, 1.175875602, 1.0, 0.785694958, 0.5411961, 0.275899379 };
        int n2 = n;
        if (n2 <= 0) {
            n2 = 1;
        }
        if (n2 > 100) {
            n2 = 100;
        }
        int n3;
        if (n2 < 50) {
            n3 = 5000 / n2;
        }
        else {
            n3 = 200 - n2 * 2;
        }
        for (int i = 0; i < 64; ++i) {
            int n4 = (array[i] * n3 + 50) / 100;
            if (n4 <= 0) {
                n4 = 1;
            }
            if (n4 > 255) {
                n4 = 255;
            }
            array[i] = n4;
        }
        int n5 = 0;
        for (int j = 0; j < 8; ++j) {
            for (int k = 0; k < 8; ++k) {
                this.a[n5] = 1.0 / (array[n5] * array3[j] * array3[k] * 8.0);
                ++n5;
            }
        }
        for (int l = 0; l < 64; ++l) {
            int n6 = (array2[l] * n3 + 50) / 100;
            if (n6 <= 0) {
                n6 = 1;
            }
            if (n6 >= 255) {
                n6 = 255;
            }
            array2[l] = n6;
        }
        int n7 = 0;
        for (int n8 = 0; n8 < 8; ++n8) {
            for (int n9 = 0; n9 < 8; ++n9) {
                this.d[n7] = 1.0 / (array2[n7] * array3[n8] * array3[n9] * 8.0);
                ++n7;
            }
        }
        this.b[0] = array;
        this.c[0] = this.a;
        this.b[1] = array2;
        this.c[1] = this.d;
    }
    
    public double[][] a(final float[][] array) {
        final double[][] array2 = new double[this.e][this.e];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                array2[i][j] = array[i][j] - 128.0;
            }
        }
        for (int k = 0; k < 8; ++k) {
            final double n = array2[k][0] + array2[k][7];
            final double n2 = array2[k][0] - array2[k][7];
            final double n3 = array2[k][1] + array2[k][6];
            final double n4 = array2[k][1] - array2[k][6];
            final double n5 = array2[k][2] + array2[k][5];
            final double n6 = array2[k][2] - array2[k][5];
            final double n7 = array2[k][3] + array2[k][4];
            final double n8 = array2[k][3] - array2[k][4];
            final double n9 = n + n7;
            final double n10 = n - n7;
            final double n11 = n3 + n5;
            final double n12 = n3 - n5;
            array2[k][0] = n9 + n11;
            array2[k][4] = n9 - n11;
            final double n13 = (n12 + n10) * 0.707106781;
            array2[k][2] = n10 + n13;
            array2[k][6] = n10 - n13;
            final double n14 = n8 + n6;
            final double n15 = n6 + n4;
            final double n16 = n4 + n2;
            final double n17 = (n14 - n16) * 0.382683433;
            final double n18 = 0.5411961 * n14 + n17;
            final double n19 = 1.306562965 * n16 + n17;
            final double n20 = n15 * 0.707106781;
            final double n21 = n2 + n20;
            final double n22 = n2 - n20;
            array2[k][5] = n22 + n18;
            array2[k][3] = n22 - n18;
            array2[k][1] = n21 + n19;
            array2[k][7] = n21 - n19;
        }
        for (int l = 0; l < 8; ++l) {
            final double n23 = array2[0][l] + array2[7][l];
            final double n24 = array2[0][l] - array2[7][l];
            final double n25 = array2[1][l] + array2[6][l];
            final double n26 = array2[1][l] - array2[6][l];
            final double n27 = array2[2][l] + array2[5][l];
            final double n28 = array2[2][l] - array2[5][l];
            final double n29 = array2[3][l] + array2[4][l];
            final double n30 = array2[3][l] - array2[4][l];
            final double n31 = n23 + n29;
            final double n32 = n23 - n29;
            final double n33 = n25 + n27;
            final double n34 = n25 - n27;
            array2[0][l] = n31 + n33;
            array2[4][l] = n31 - n33;
            final double n35 = (n34 + n32) * 0.707106781;
            array2[2][l] = n32 + n35;
            array2[6][l] = n32 - n35;
            final double n36 = n30 + n28;
            final double n37 = n28 + n26;
            final double n38 = n26 + n24;
            final double n39 = (n36 - n38) * 0.382683433;
            final double n40 = 0.5411961 * n36 + n39;
            final double n41 = 1.306562965 * n38 + n39;
            final double n42 = n37 * 0.707106781;
            final double n43 = n24 + n42;
            final double n44 = n24 - n42;
            array2[5][l] = n44 + n40;
            array2[3][l] = n44 - n40;
            array2[1][l] = n43 + n41;
            array2[7][l] = n43 - n41;
        }
        return array2;
    }
    
    public int[] a(final double[][] array, final int n) {
        final int[] array2 = new int[this.e * this.e];
        int n2 = 0;
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                array2[n2] = (int)Math.round(array[i][j] * ((double[])this.c[n])[n2]);
                ++n2;
            }
        }
        return array2;
    }
}
