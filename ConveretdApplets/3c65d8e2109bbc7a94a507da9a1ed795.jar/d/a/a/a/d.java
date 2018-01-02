// 
// Decompiled by Procyon v0.5.30
// 

package d.a.a.a;

public class d
{
    private int[] a;
    
    public d() {
        this.a = new int[9];
    }
    
    public void a(final short[] array, final short[] array2) {
        this.a(array);
        this.for(array2);
        this.if(array2);
        this.do(array2);
    }
    
    private void a(final short[] array) throws IllegalArgumentException {
        int n = 0;
        short n2 = 0;
        for (int i = 0; i <= 159; ++i) {
            final short new1 = j.new(array[i], (short)0);
            if (new1 > n2) {
                n2 = new1;
            }
        }
        int n3;
        if (n2 == 0) {
            n3 = 0;
        }
        else {
            if (n2 <= 0) {
                throw new IllegalArgumentException("Autocorrelation: smax = " + n2 + " should be > 0.");
            }
            n3 = (short)(4 - j.if(n2 << 16));
        }
        if (n3 > 0) {
            if (n3 > 4) {
                throw new IllegalArgumentException("Autocorrelation: scalauto = " + n3 + " should be <= 4.");
            }
            switch (n3) {
                case 1: {
                    for (int j = 0; j <= 159; ++j) {
                        array[j] = j.do(array[j], (short)16384);
                    }
                    break;
                }
                case 2: {
                    for (int k = 0; k <= 159; ++k) {
                        array[k] = j.do(array[k], (short)8192);
                    }
                    break;
                }
                case 3: {
                    for (int l = 0; l <= 159; ++l) {
                        array[l] = j.do(array[l], (short)4096);
                    }
                    break;
                }
                case 4: {
                    for (int n4 = 0; n4 <= 159; ++n4) {
                        array[n4] = j.do(array[n4], (short)2048);
                    }
                    break;
                }
            }
        }
        final short n5 = array[n];
        System.arraycopy(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 0, this.a, 0, this.a.length);
        final int[] a = this.a;
        final int n6 = 0;
        a[n6] += n5 * array[n - 0];
        final short n7 = array[++n];
        final int[] a2 = this.a;
        final int n8 = 0;
        a2[n8] += n7 * array[n - 0];
        final int[] a3 = this.a;
        final int n9 = 1;
        a3[n9] += n7 * array[n - 1];
        final short n10 = array[++n];
        final int[] a4 = this.a;
        final int n11 = 0;
        a4[n11] += n10 * array[n - 0];
        final int[] a5 = this.a;
        final int n12 = 1;
        a5[n12] += n10 * array[n - 1];
        final int[] a6 = this.a;
        final int n13 = 2;
        a6[n13] += n10 * array[n - 2];
        final short n14 = array[++n];
        final int[] a7 = this.a;
        final int n15 = 0;
        a7[n15] += n14 * array[n - 0];
        final int[] a8 = this.a;
        final int n16 = 1;
        a8[n16] += n14 * array[n - 1];
        final int[] a9 = this.a;
        final int n17 = 2;
        a9[n17] += n14 * array[n - 2];
        final int[] a10 = this.a;
        final int n18 = 3;
        a10[n18] += n14 * array[n - 3];
        final short n19 = array[++n];
        final int[] a11 = this.a;
        final int n20 = 0;
        a11[n20] += n19 * array[n - 0];
        final int[] a12 = this.a;
        final int n21 = 1;
        a12[n21] += n19 * array[n - 1];
        final int[] a13 = this.a;
        final int n22 = 2;
        a13[n22] += n19 * array[n - 2];
        final int[] a14 = this.a;
        final int n23 = 3;
        a14[n23] += n19 * array[n - 3];
        final int[] a15 = this.a;
        final int n24 = 4;
        a15[n24] += n19 * array[n - 4];
        final short n25 = array[++n];
        final int[] a16 = this.a;
        final int n26 = 0;
        a16[n26] += n25 * array[n - 0];
        final int[] a17 = this.a;
        final int n27 = 1;
        a17[n27] += n25 * array[n - 1];
        final int[] a18 = this.a;
        final int n28 = 2;
        a18[n28] += n25 * array[n - 2];
        final int[] a19 = this.a;
        final int n29 = 3;
        a19[n29] += n25 * array[n - 3];
        final int[] a20 = this.a;
        final int n30 = 4;
        a20[n30] += n25 * array[n - 4];
        final int[] a21 = this.a;
        final int n31 = 5;
        a21[n31] += n25 * array[n - 5];
        final short n32 = array[++n];
        final int[] a22 = this.a;
        final int n33 = 0;
        a22[n33] += n32 * array[n - 0];
        final int[] a23 = this.a;
        final int n34 = 1;
        a23[n34] += n32 * array[n - 1];
        final int[] a24 = this.a;
        final int n35 = 2;
        a24[n35] += n32 * array[n - 2];
        final int[] a25 = this.a;
        final int n36 = 3;
        a25[n36] += n32 * array[n - 3];
        final int[] a26 = this.a;
        final int n37 = 4;
        a26[n37] += n32 * array[n - 4];
        final int[] a27 = this.a;
        final int n38 = 5;
        a27[n38] += n32 * array[n - 5];
        final int[] a28 = this.a;
        final int n39 = 6;
        a28[n39] += n32 * array[n - 6];
        final short n40 = array[++n];
        final int[] a29 = this.a;
        final int n41 = 0;
        a29[n41] += n40 * array[n - 0];
        final int[] a30 = this.a;
        final int n42 = 1;
        a30[n42] += n40 * array[n - 1];
        final int[] a31 = this.a;
        final int n43 = 2;
        a31[n43] += n40 * array[n - 2];
        final int[] a32 = this.a;
        final int n44 = 3;
        a32[n44] += n40 * array[n - 3];
        final int[] a33 = this.a;
        final int n45 = 4;
        a33[n45] += n40 * array[n - 4];
        final int[] a34 = this.a;
        final int n46 = 5;
        a34[n46] += n40 * array[n - 5];
        final int[] a35 = this.a;
        final int n47 = 6;
        a35[n47] += n40 * array[n - 6];
        final int[] a36 = this.a;
        final int n48 = 7;
        a36[n48] += n40 * array[n - 7];
        final short n49 = array[++n];
        for (int n50 = n; n50 < 160; ++n50) {
            final short n51 = array[n50];
            final int[] a37 = this.a;
            final int n52 = 0;
            a37[n52] += n51 * array[n50 - 0];
            final int[] a38 = this.a;
            final int n53 = 1;
            a38[n53] += n51 * array[n50 - 1];
            final int[] a39 = this.a;
            final int n54 = 2;
            a39[n54] += n51 * array[n50 - 2];
            final int[] a40 = this.a;
            final int n55 = 3;
            a40[n55] += n51 * array[n50 - 3];
            final int[] a41 = this.a;
            final int n56 = 4;
            a41[n56] += n51 * array[n50 - 4];
            final int[] a42 = this.a;
            final int n57 = 5;
            a42[n57] += n51 * array[n50 - 5];
            final int[] a43 = this.a;
            final int n58 = 6;
            a43[n58] += n51 * array[n50 - 6];
            final int[] a44 = this.a;
            final int n59 = 7;
            a44[n59] += n51 * array[n50 - 7];
            final int[] a45 = this.a;
            final int n60 = 8;
            a45[n60] += n51 * array[n50 - 8];
        }
        for (int n61 = 0; n61 < 9; ++n61) {
            final int[] a46 = this.a;
            final int n62 = n61;
            a46[n62] <<= 1;
        }
        if (n3 > 0) {
            if (n3 > 4) {
                throw new IllegalArgumentException("Autocorrelation: scalauto = " + n3 + " should be <= 4.");
            }
            for (int n63 = 0; n63 < 160; ++n63) {
                final int n64 = n63;
                array[n64] <<= (short)n3;
            }
        }
    }
    
    private void for(final short[] array) throws IllegalArgumentException {
        int n = 0;
        final short[] array2 = new short[9];
        final short[] array3 = new short[9];
        final short[] array4 = new short[9];
        if (this.a[0] == 0) {
            for (int i = 0; i < 8; ++i) {
                array[i] = 0;
            }
            return;
        }
        if (this.a[0] == 0) {
            throw new IllegalArgumentException("Reflection_coefficients: L_ACF[0] = " + this.a[0] + " should not = 0.");
        }
        final short if1 = j.if(this.a[0]);
        if (if1 < 0 || if1 >= 32) {
            throw new IllegalArgumentException("Reflection_coefficients: temp = " + if1 + " should be >= 0 and < 32.");
        }
        for (int j = 0; j <= 8; ++j) {
            array2[j] = j.if(this.a[j] << if1, 16);
        }
        System.arraycopy(array2, 0, array4, 0, 7);
        System.arraycopy(array2, 0, array3, 0, 8);
        for (int k = 1; k <= 8; ++k, ++n) {
            final short a = j.a(array3[1]);
            if (array3[0] < a) {
                for (int l = k; l < 8; ++l) {
                    array[l] = 0;
                }
                return;
            }
            array[n] = j.a(a, array3[0]);
            if (array[n] < 0) {
                throw new IllegalArgumentException("Reflection_coefficients: r[" + n + "] = " + array[n] + " should be >= 0");
            }
            if (array3[1] > 0) {
                array[n] = (short)(-array[n]);
            }
            if (array[n] == -32768) {
                throw new IllegalArgumentException("Reflection_coefficients: r[" + n + "] = " + array[n] + " should not be " + -32768);
            }
            if (k == 8) {
                return;
            }
            array3[0] = j.new(array3[0], j.do(array3[1], array[n]));
            for (int n2 = 1; n2 <= 8 - k; ++n2) {
                array3[n2] = j.new(array3[n2 + 1], j.do(array4[n2], array[n]));
                array4[n2] = j.new(array4[n2], j.do(array3[n2 + 1], array[n]));
            }
        }
    }
    
    private void if(final short[] array) throws IllegalArgumentException {
        for (int i = 0; i < 8; ++i) {
            final short a = j.a(array[i]);
            if (a < 0) {
                throw new IllegalArgumentException("Transformation_to_Log_Area_Ratios: temp = " + a + " should be >= 0 ");
            }
            short n;
            if (a < 22118) {
                n = (short)(a >> 1);
            }
            else if (a < 31130) {
                if (a < 11059) {
                    throw new IllegalArgumentException("Transformation_to_Log_Area_Ratios: temp = " + a + " should be >= 11059 ");
                }
                n = (short)(a - 11059);
            }
            else {
                if (a < 26112) {
                    throw new IllegalArgumentException("Transformation_to_Log_Area_Ratios: temp = " + a + " should be >= 26112 ");
                }
                n = (short)((short)(a - 26112) << 2);
            }
            array[i] = (short)((array[i] < 0) ? (-n) : n);
            if (array[i] == -32768) {
                throw new IllegalArgumentException("Transformation_to_Log_Area_Ratios: r[" + i + "] = " + array[i] + " should not be = " + -32768);
            }
        }
    }
    
    private void do(final short[] array) {
        int n = 0;
        this.a(20480, 0, 31, -32, array, n++);
        this.a(20480, 0, 31, -32, array, n++);
        this.a(20480, 2048, 15, -16, array, n++);
        this.a(20480, -2560, 15, -16, array, n++);
        this.a(13964, 94, 7, -8, array, n++);
        this.a(15360, -1792, 7, -8, array, n++);
        this.a(8534, -341, 3, -4, array, n++);
        this.a(9036, -1144, 3, -4, array, n++);
    }
    
    private void a(final int n, final int n2, final int n3, final int n4, final short[] array, final int n5) {
        final short if1 = j.if((int)j.new(j.new(j.for((short)n, array[n5]), (short)n2), (short)256), 9);
        array[n5] = (short)((if1 > n3) ? (n3 - n4) : ((if1 < n4) ? 0 : (if1 - n4)));
    }
}
