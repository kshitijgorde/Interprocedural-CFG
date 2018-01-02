// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.blas;

import org.netlib.err.Xerbla;

public final class Dgemv
{
    public static void dgemv(final String s, final int n, final int n2, final double n3, final double[] array, final int n4, final int n5, final double[] array2, final int n6, final int n7, final double n8, final double[] array3, final int n9, final int n10) {
        int n11 = 0;
        if ((Lsame.lsame(s, "N") ^ true) && (Lsame.lsame(s, "T") ^ true) && (Lsame.lsame(s, "C") ^ true)) {
            n11 = 1;
        }
        else if (n < 0) {
            n11 = 2;
        }
        else if (n2 < 0) {
            n11 = 3;
        }
        else if (n5 < Math.max(1, n)) {
            n11 = 6;
        }
        else if (n7 == 0) {
            n11 = 8;
        }
        else if (n10 == 0) {
            n11 = 11;
        }
        if (n11 != 0) {
            Xerbla.xerbla("DGEMV ", n11);
            return;
        }
        if (n == 0 || n2 == 0 || (n3 == 0.0 && n8 == 1.0)) {
            return;
        }
        int n12;
        int n13;
        if (Lsame.lsame(s, "N")) {
            n12 = n2;
            n13 = n;
        }
        else {
            n12 = n;
            n13 = n2;
        }
        int n14;
        if (n7 > 0) {
            n14 = 1;
        }
        else {
            n14 = 1 - (n12 - 1) * n7;
        }
        int n15;
        if (n10 > 0) {
            n15 = 1;
        }
        else {
            n15 = 1 - (n13 - 1) * n10;
        }
        if (n8 != 1.0) {
            if (n10 == 1) {
                if (n8 == 0.0) {
                    int n16 = 1;
                    for (int i = n13 - 1 + 1; i > 0; --i) {
                        array3[n16 - 1 + n9] = 0.0;
                        ++n16;
                    }
                }
                else {
                    int n17 = 1;
                    for (int j = n13 - 1 + 1; j > 0; --j) {
                        array3[n17 - 1 + n9] *= n8;
                        ++n17;
                    }
                }
            }
            else {
                int n18 = n15;
                if (n8 == 0.0) {
                    int n19 = 1;
                    for (int k = n13 - 1 + 1; k > 0; --k) {
                        array3[n18 - 1 + n9] = 0.0;
                        n18 += n10;
                        ++n19;
                    }
                }
                else {
                    int n20 = 1;
                    for (int l = n13 - 1 + 1; l > 0; --l) {
                        array3[n18 - 1 + n9] *= n8;
                        n18 += n10;
                        ++n20;
                    }
                }
            }
        }
        if (n3 == 0.0) {
            return;
        }
        if (Lsame.lsame(s, "N")) {
            int n21 = n14;
            if (n10 == 1) {
                int n22 = 1;
                for (int n23 = n2 - 1 + 1; n23 > 0; --n23) {
                    if (array2[n21 - 1 + n6] != 0.0) {
                        final double n24 = n3 * array2[n21 - 1 + n6];
                        int n25 = 1;
                        for (int n26 = n - 1 + 1; n26 > 0; --n26) {
                            array3[n25 - 1 + n9] += n24 * array[n25 - 1 + (n22 - 1) * n5 + n4];
                            ++n25;
                        }
                    }
                    n21 += n7;
                    ++n22;
                }
            }
            else {
                int n27 = 1;
                for (int n28 = n2 - 1 + 1; n28 > 0; --n28) {
                    if (array2[n21 - 1 + n6] != 0.0) {
                        final double n29 = n3 * array2[n21 - 1 + n6];
                        int n30 = n15;
                        int n31 = 1;
                        for (int n32 = n - 1 + 1; n32 > 0; --n32) {
                            array3[n30 - 1 + n9] += n29 * array[n31 - 1 + (n27 - 1) * n5 + n4];
                            n30 += n10;
                            ++n31;
                        }
                    }
                    n21 += n7;
                    ++n27;
                }
            }
        }
        else {
            int n33 = n15;
            if (n7 == 1) {
                int n34 = 1;
                for (int n35 = n2 - 1 + 1; n35 > 0; --n35) {
                    double n36 = 0.0;
                    int n37 = 1;
                    for (int n38 = n - 1 + 1; n38 > 0; --n38) {
                        n36 += array[n37 - 1 + (n34 - 1) * n5 + n4] * array2[n37 - 1 + n6];
                        ++n37;
                    }
                    array3[n33 - 1 + n9] += n3 * n36;
                    n33 += n10;
                    ++n34;
                }
            }
            else {
                int n39 = 1;
                for (int n40 = n2 - 1 + 1; n40 > 0; --n40) {
                    double n41 = 0.0;
                    int n42 = n14;
                    int n43 = 1;
                    for (int n44 = n - 1 + 1; n44 > 0; --n44) {
                        n41 += array[n43 - 1 + (n39 - 1) * n5 + n4] * array2[n42 - 1 + n6];
                        n42 += n7;
                        ++n43;
                    }
                    array3[n33 - 1 + n9] += n3 * n41;
                    n33 += n10;
                    ++n39;
                }
            }
        }
    }
}
