// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.blas;

import org.netlib.err.Xerbla;

public final class Dsyr2
{
    public static void dsyr2(final String s, final int n, final double n2, final double[] array, final int n3, final int n4, final double[] array2, final int n5, final int n6, final double[] array3, final int n7, final int n8) {
        int n9 = 0;
        int n10 = 0;
        int n11 = 0;
        int n12 = 0;
        int n13 = 0;
        if ((Lsame.lsame(s, "U") ^ true) && (Lsame.lsame(s, "L") ^ true)) {
            n13 = 1;
        }
        else if (n < 0) {
            n13 = 2;
        }
        else if (n4 == 0) {
            n13 = 5;
        }
        else if (n6 == 0) {
            n13 = 7;
        }
        else if (n8 < Math.max(1, n)) {
            n13 = 9;
        }
        if (n13 != 0) {
            Xerbla.xerbla("DSYR2 ", n13);
            return;
        }
        if (n == 0 || n2 == 0.0) {
            return;
        }
        if (n4 != 1 || n6 != 1) {
            if (n4 > 0) {
                n11 = 1;
            }
            else {
                n11 = 1 - (n - 1) * n4;
            }
            if (n6 > 0) {
                n12 = 1;
            }
            else {
                n12 = 1 - (n - 1) * n6;
            }
            n9 = n11;
            n10 = n12;
        }
        if (Lsame.lsame(s, "U")) {
            if (n4 == 1 && n6 == 1) {
                int n14 = 1;
                for (int i = n - 1 + 1; i > 0; --i) {
                    if (array[n14 - 1 + n3] != 0.0 || array2[n14 - 1 + n5] != 0.0) {
                        final double n15 = n2 * array2[n14 - 1 + n5];
                        final double n16 = n2 * array[n14 - 1 + n3];
                        int n17 = 1;
                        for (int j = n14 - 1 + 1; j > 0; --j) {
                            array3[n17 - 1 + (n14 - 1) * n8 + n7] = array3[n17 - 1 + (n14 - 1) * n8 + n7] + array[n17 - 1 + n3] * n15 + array2[n17 - 1 + n5] * n16;
                            ++n17;
                        }
                    }
                    ++n14;
                }
            }
            else {
                int n18 = 1;
                for (int k = n - 1 + 1; k > 0; --k) {
                    if (array[n9 - 1 + n3] != 0.0 || array2[n10 - 1 + n5] != 0.0) {
                        final double n19 = n2 * array2[n10 - 1 + n5];
                        final double n20 = n2 * array[n9 - 1 + n3];
                        int n21 = n11;
                        int n22 = n12;
                        int n23 = 1;
                        for (int l = n18 - 1 + 1; l > 0; --l) {
                            array3[n23 - 1 + (n18 - 1) * n8 + n7] = array3[n23 - 1 + (n18 - 1) * n8 + n7] + array[n21 - 1 + n3] * n19 + array2[n22 - 1 + n5] * n20;
                            n21 += n4;
                            n22 += n6;
                            ++n23;
                        }
                    }
                    n9 += n4;
                    n10 += n6;
                    ++n18;
                }
            }
        }
        else if (n4 == 1 && n6 == 1) {
            int n24 = 1;
            for (int n25 = n - 1 + 1; n25 > 0; --n25) {
                if (array[n24 - 1 + n3] != 0.0 || array2[n24 - 1 + n5] != 0.0) {
                    final double n26 = n2 * array2[n24 - 1 + n5];
                    final double n27 = n2 * array[n24 - 1 + n3];
                    int n28 = n24;
                    for (int n29 = n - n24 + 1; n29 > 0; --n29) {
                        array3[n28 - 1 + (n24 - 1) * n8 + n7] = array3[n28 - 1 + (n24 - 1) * n8 + n7] + array[n28 - 1 + n3] * n26 + array2[n28 - 1 + n5] * n27;
                        ++n28;
                    }
                }
                ++n24;
            }
        }
        else {
            int n30 = 1;
            for (int n31 = n - 1 + 1; n31 > 0; --n31) {
                if (array[n9 - 1 + n3] != 0.0 || array2[n10 - 1 + n5] != 0.0) {
                    final double n32 = n2 * array2[n10 - 1 + n5];
                    final double n33 = n2 * array[n9 - 1 + n3];
                    int n34 = n9;
                    int n35 = n10;
                    int n36 = n30;
                    for (int n37 = n - n30 + 1; n37 > 0; --n37) {
                        array3[n36 - 1 + (n30 - 1) * n8 + n7] = array3[n36 - 1 + (n30 - 1) * n8 + n7] + array[n34 - 1 + n3] * n32 + array2[n35 - 1 + n5] * n33;
                        n34 += n4;
                        n35 += n6;
                        ++n36;
                    }
                }
                n9 += n4;
                n10 += n6;
                ++n30;
            }
        }
    }
}
