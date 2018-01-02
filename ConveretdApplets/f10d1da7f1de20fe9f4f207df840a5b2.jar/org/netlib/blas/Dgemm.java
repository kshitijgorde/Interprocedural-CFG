// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.blas;

import org.netlib.err.Xerbla;

public final class Dgemm
{
    public static void dgemm(final String s, final String s2, final int n, final int n2, final int n3, final double n4, final double[] array, final int n5, final int n6, final double[] array2, final int n7, final int n8, final double n9, final double[] array3, final int n10, final int n11) {
        final boolean lsame = Lsame.lsame(s, "N");
        final boolean lsame2 = Lsame.lsame(s2, "N");
        int n12;
        if (lsame) {
            n12 = n;
        }
        else {
            n12 = n3;
        }
        int n13;
        if (lsame2) {
            n13 = n3;
        }
        else {
            n13 = n2;
        }
        int n14 = 0;
        if ((lsame ^ true) && (Lsame.lsame(s, "C") ^ true) && (Lsame.lsame(s, "T") ^ true)) {
            n14 = 1;
        }
        else if ((lsame2 ^ true) && (Lsame.lsame(s2, "C") ^ true) && (Lsame.lsame(s2, "T") ^ true)) {
            n14 = 2;
        }
        else if (n < 0) {
            n14 = 3;
        }
        else if (n2 < 0) {
            n14 = 4;
        }
        else if (n3 < 0) {
            n14 = 5;
        }
        else if (n6 < Math.max(1, n12)) {
            n14 = 8;
        }
        else if (n8 < Math.max(1, n13)) {
            n14 = 10;
        }
        else if (n11 < Math.max(1, n)) {
            n14 = 13;
        }
        if (n14 != 0) {
            Xerbla.xerbla("DGEMM ", n14);
            return;
        }
        if (n == 0 || n2 == 0 || ((n4 == 0.0 || n3 == 0) && n9 == 1.0)) {
            return;
        }
        if (n4 == 0.0) {
            if (n9 == 0.0) {
                int n15 = 1;
                for (int i = n2 - 1 + 1; i > 0; --i) {
                    int n16 = 1;
                    for (int j = n - 1 + 1; j > 0; --j) {
                        array3[n16 - 1 + (n15 - 1) * n11 + n10] = 0.0;
                        ++n16;
                    }
                    ++n15;
                }
            }
            else {
                int n17 = 1;
                for (int k = n2 - 1 + 1; k > 0; --k) {
                    int n18 = 1;
                    for (int l = n - 1 + 1; l > 0; --l) {
                        array3[n18 - 1 + (n17 - 1) * n11 + n10] *= n9;
                        ++n18;
                    }
                    ++n17;
                }
            }
            return;
        }
        if (lsame2) {
            if (lsame) {
                int n19 = 1;
                for (int n20 = n2 - 1 + 1; n20 > 0; --n20) {
                    if (n9 == 0.0) {
                        int n21 = 1;
                        for (int n22 = n - 1 + 1; n22 > 0; --n22) {
                            array3[n21 - 1 + (n19 - 1) * n11 + n10] = 0.0;
                            ++n21;
                        }
                    }
                    else if (n9 != 1.0) {
                        int n23 = 1;
                        for (int n24 = n - 1 + 1; n24 > 0; --n24) {
                            array3[n23 - 1 + (n19 - 1) * n11 + n10] *= n9;
                            ++n23;
                        }
                    }
                    int n25 = 1;
                    for (int n26 = n3 - 1 + 1; n26 > 0; --n26) {
                        if (array2[n25 - 1 + (n19 - 1) * n8 + n7] != 0.0) {
                            final double n27 = n4 * array2[n25 - 1 + (n19 - 1) * n8 + n7];
                            int n28 = 1;
                            for (int n29 = n - 1 + 1; n29 > 0; --n29) {
                                array3[n28 - 1 + (n19 - 1) * n11 + n10] += n27 * array[n28 - 1 + (n25 - 1) * n6 + n5];
                                ++n28;
                            }
                        }
                        ++n25;
                    }
                    ++n19;
                }
            }
            else {
                int n30 = 1;
                for (int n31 = n2 - 1 + 1; n31 > 0; --n31) {
                    int n32 = 1;
                    for (int n33 = n - 1 + 1; n33 > 0; --n33) {
                        double n34 = 0.0;
                        int n35 = 1;
                        for (int n36 = n3 - 1 + 1; n36 > 0; --n36) {
                            n34 += array[n35 - 1 + (n32 - 1) * n6 + n5] * array2[n35 - 1 + (n30 - 1) * n8 + n7];
                            ++n35;
                        }
                        if (n9 == 0.0) {
                            array3[n32 - 1 + (n30 - 1) * n11 + n10] = n4 * n34;
                        }
                        else {
                            array3[n32 - 1 + (n30 - 1) * n11 + n10] = n4 * n34 + n9 * array3[n32 - 1 + (n30 - 1) * n11 + n10];
                        }
                        ++n32;
                    }
                    ++n30;
                }
            }
        }
        else if (lsame) {
            int n37 = 1;
            for (int n38 = n2 - 1 + 1; n38 > 0; --n38) {
                if (n9 == 0.0) {
                    int n39 = 1;
                    for (int n40 = n - 1 + 1; n40 > 0; --n40) {
                        array3[n39 - 1 + (n37 - 1) * n11 + n10] = 0.0;
                        ++n39;
                    }
                }
                else if (n9 != 1.0) {
                    int n41 = 1;
                    for (int n42 = n - 1 + 1; n42 > 0; --n42) {
                        array3[n41 - 1 + (n37 - 1) * n11 + n10] *= n9;
                        ++n41;
                    }
                }
                int n43 = 1;
                for (int n44 = n3 - 1 + 1; n44 > 0; --n44) {
                    if (array2[n37 - 1 + (n43 - 1) * n8 + n7] != 0.0) {
                        final double n45 = n4 * array2[n37 - 1 + (n43 - 1) * n8 + n7];
                        int n46 = 1;
                        for (int n47 = n - 1 + 1; n47 > 0; --n47) {
                            array3[n46 - 1 + (n37 - 1) * n11 + n10] += n45 * array[n46 - 1 + (n43 - 1) * n6 + n5];
                            ++n46;
                        }
                    }
                    ++n43;
                }
                ++n37;
            }
        }
        else {
            int n48 = 1;
            for (int n49 = n2 - 1 + 1; n49 > 0; --n49) {
                int n50 = 1;
                for (int n51 = n - 1 + 1; n51 > 0; --n51) {
                    double n52 = 0.0;
                    int n53 = 1;
                    for (int n54 = n3 - 1 + 1; n54 > 0; --n54) {
                        n52 += array[n53 - 1 + (n50 - 1) * n6 + n5] * array2[n48 - 1 + (n53 - 1) * n8 + n7];
                        ++n53;
                    }
                    if (n9 == 0.0) {
                        array3[n50 - 1 + (n48 - 1) * n11 + n10] = n4 * n52;
                    }
                    else {
                        array3[n50 - 1 + (n48 - 1) * n11 + n10] = n4 * n52 + n9 * array3[n50 - 1 + (n48 - 1) * n11 + n10];
                    }
                    ++n50;
                }
                ++n48;
            }
        }
    }
}
