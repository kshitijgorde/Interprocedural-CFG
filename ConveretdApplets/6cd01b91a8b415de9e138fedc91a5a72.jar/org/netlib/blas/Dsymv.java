// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.blas;

import org.netlib.err.Xerbla;

public final class Dsymv
{
    public static void dsymv(final String s, final int n, final double n2, final double[] array, final int n3, final int n4, final double[] array2, final int n5, final int n6, final double n7, final double[] array3, final int n8, final int n9) {
        int n10 = 0;
        if ((Lsame.lsame(s, "U") ^ true) && (Lsame.lsame(s, "L") ^ true)) {
            n10 = 1;
        }
        else if (n < 0) {
            n10 = 2;
        }
        else if (n4 < Math.max(1, n)) {
            n10 = 5;
        }
        else if (n6 == 0) {
            n10 = 7;
        }
        else if (n9 == 0) {
            n10 = 10;
        }
        if (n10 != 0) {
            Xerbla.xerbla("DSYMV ", n10);
            return;
        }
        if (n == 0 || (n2 == 0.0 && n7 == 1.0)) {
            return;
        }
        int n11;
        if (n6 > 0) {
            n11 = 1;
        }
        else {
            n11 = 1 - (n - 1) * n6;
        }
        int n12;
        if (n9 > 0) {
            n12 = 1;
        }
        else {
            n12 = 1 - (n - 1) * n9;
        }
        if (n7 != 1.0) {
            if (n9 == 1) {
                if (n7 == 0.0) {
                    int n13 = 1;
                    for (int i = n - 1 + 1; i > 0; --i) {
                        array3[n13 - 1 + n8] = 0.0;
                        ++n13;
                    }
                }
                else {
                    int n14 = 1;
                    for (int j = n - 1 + 1; j > 0; --j) {
                        array3[n14 - 1 + n8] *= n7;
                        ++n14;
                    }
                }
            }
            else {
                int n15 = n12;
                if (n7 == 0.0) {
                    int n16 = 1;
                    for (int k = n - 1 + 1; k > 0; --k) {
                        array3[n15 - 1 + n8] = 0.0;
                        n15 += n9;
                        ++n16;
                    }
                }
                else {
                    int n17 = 1;
                    for (int l = n - 1 + 1; l > 0; --l) {
                        array3[n15 - 1 + n8] *= n7;
                        n15 += n9;
                        ++n17;
                    }
                }
            }
        }
        if (n2 == 0.0) {
            return;
        }
        if (Lsame.lsame(s, "U")) {
            if (n6 == 1 && n9 == 1) {
                int n18 = 1;
                for (int n19 = n - 1 + 1; n19 > 0; --n19) {
                    final double n20 = n2 * array2[n18 - 1 + n5];
                    double n21 = 0.0;
                    int n22 = 1;
                    for (int n23 = n18 - 1 - 1 + 1; n23 > 0; --n23) {
                        array3[n22 - 1 + n8] += n20 * array[n22 - 1 + (n18 - 1) * n4 + n3];
                        n21 += array[n22 - 1 + (n18 - 1) * n4 + n3] * array2[n22 - 1 + n5];
                        ++n22;
                    }
                    array3[n18 - 1 + n8] = array3[n18 - 1 + n8] + n20 * array[n18 - 1 + (n18 - 1) * n4 + n3] + n2 * n21;
                    ++n18;
                }
            }
            else {
                int n24 = n11;
                int n25 = n12;
                int n26 = 1;
                for (int n27 = n - 1 + 1; n27 > 0; --n27) {
                    final double n28 = n2 * array2[n24 - 1 + n5];
                    double n29 = 0.0;
                    int n30 = n11;
                    int n31 = n12;
                    int n32 = 1;
                    for (int n33 = n26 - 1 - 1 + 1; n33 > 0; --n33) {
                        array3[n31 - 1 + n8] += n28 * array[n32 - 1 + (n26 - 1) * n4 + n3];
                        n29 += array[n32 - 1 + (n26 - 1) * n4 + n3] * array2[n30 - 1 + n5];
                        n30 += n6;
                        n31 += n9;
                        ++n32;
                    }
                    array3[n25 - 1 + n8] = array3[n25 - 1 + n8] + n28 * array[n26 - 1 + (n26 - 1) * n4 + n3] + n2 * n29;
                    n24 += n6;
                    n25 += n9;
                    ++n26;
                }
            }
        }
        else if (n6 == 1 && n9 == 1) {
            int n34 = 1;
            for (int n35 = n - 1 + 1; n35 > 0; --n35) {
                final double n36 = n2 * array2[n34 - 1 + n5];
                double n37 = 0.0;
                array3[n34 - 1 + n8] += n36 * array[n34 - 1 + (n34 - 1) * n4 + n3];
                int n38 = n34 + 1;
                for (int n39 = n - (n34 + 1) + 1; n39 > 0; --n39) {
                    array3[n38 - 1 + n8] += n36 * array[n38 - 1 + (n34 - 1) * n4 + n3];
                    n37 += array[n38 - 1 + (n34 - 1) * n4 + n3] * array2[n38 - 1 + n5];
                    ++n38;
                }
                array3[n34 - 1 + n8] += n2 * n37;
                ++n34;
            }
        }
        else {
            int n40 = n11;
            int n41 = n12;
            int n42 = 1;
            for (int n43 = n - 1 + 1; n43 > 0; --n43) {
                final double n44 = n2 * array2[n40 - 1 + n5];
                double n45 = 0.0;
                array3[n41 - 1 + n8] += n44 * array[n42 - 1 + (n42 - 1) * n4 + n3];
                int n46 = n40;
                int n47 = n41;
                int n48 = n42 + 1;
                for (int n49 = n - (n42 + 1) + 1; n49 > 0; --n49) {
                    n46 += n6;
                    n47 += n9;
                    array3[n47 - 1 + n8] += n44 * array[n48 - 1 + (n42 - 1) * n4 + n3];
                    n45 += array[n48 - 1 + (n42 - 1) * n4 + n3] * array2[n46 - 1 + n5];
                    ++n48;
                }
                array3[n41 - 1 + n8] += n2 * n45;
                n40 += n6;
                n41 += n9;
                ++n42;
            }
        }
    }
}
