// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.blas;

import org.netlib.err.Xerbla;

public final class Dtrmv
{
    public static void dtrmv(final String s, final String s2, final String s3, final int n, final double[] array, final int n2, final int n3, final double[] array2, final int n4, final int n5) {
        int n6 = 0;
        int n7 = 0;
        if ((Lsame.lsame(s, "U") ^ true) && (Lsame.lsame(s, "L") ^ true)) {
            n7 = 1;
        }
        else if ((Lsame.lsame(s2, "N") ^ true) && (Lsame.lsame(s2, "T") ^ true) && (Lsame.lsame(s2, "C") ^ true)) {
            n7 = 2;
        }
        else if ((Lsame.lsame(s3, "U") ^ true) && (Lsame.lsame(s3, "N") ^ true)) {
            n7 = 3;
        }
        else if (n < 0) {
            n7 = 4;
        }
        else if (n3 < Math.max(1, n)) {
            n7 = 6;
        }
        else if (n5 == 0) {
            n7 = 8;
        }
        if (n7 != 0) {
            Xerbla.xerbla("DTRMV ", n7);
            return;
        }
        if (n == 0) {
            return;
        }
        final boolean lsame = Lsame.lsame(s3, "N");
        if (n5 <= 0) {
            n6 = 1 - (n - 1) * n5;
        }
        else if (n5 != 1) {
            n6 = 1;
        }
        if (Lsame.lsame(s2, "N")) {
            if (Lsame.lsame(s, "U")) {
                if (n5 == 1) {
                    int n8 = 1;
                    for (int i = n - 1 + 1; i > 0; --i) {
                        if (array2[n8 - 1 + n4] != 0.0) {
                            final double n9 = array2[n8 - 1 + n4];
                            int n10 = 1;
                            for (int j = n8 - 1 - 1 + 1; j > 0; --j) {
                                array2[n10 - 1 + n4] += n9 * array[n10 - 1 + (n8 - 1) * n3 + n2];
                                ++n10;
                            }
                            if (lsame) {
                                array2[n8 - 1 + n4] *= array[n8 - 1 + (n8 - 1) * n3 + n2];
                            }
                        }
                        ++n8;
                    }
                }
                else {
                    int n11 = n6;
                    int n12 = 1;
                    for (int k = n - 1 + 1; k > 0; --k) {
                        if (array2[n11 - 1 + n4] != 0.0) {
                            final double n13 = array2[n11 - 1 + n4];
                            int n14 = n6;
                            int n15 = 1;
                            for (int l = n12 - 1 - 1 + 1; l > 0; --l) {
                                array2[n14 - 1 + n4] += n13 * array[n15 - 1 + (n12 - 1) * n3 + n2];
                                n14 += n5;
                                ++n15;
                            }
                            if (lsame) {
                                array2[n11 - 1 + n4] *= array[n12 - 1 + (n12 - 1) * n3 + n2];
                            }
                        }
                        n11 += n5;
                        ++n12;
                    }
                }
            }
            else if (n5 == 1) {
                int n16 = n;
                for (int n17 = (1 - n - 1) / -1; n17 > 0; --n17) {
                    if (array2[n16 - 1 + n4] != 0.0) {
                        final double n18 = array2[n16 - 1 + n4];
                        int n19 = n;
                        for (int n20 = (n16 + 1 - n - 1) / -1; n20 > 0; --n20) {
                            array2[n19 - 1 + n4] += n18 * array[n19 - 1 + (n16 - 1) * n3 + n2];
                            --n19;
                        }
                        if (lsame) {
                            array2[n16 - 1 + n4] *= array[n16 - 1 + (n16 - 1) * n3 + n2];
                        }
                    }
                    --n16;
                }
            }
            else {
                int n22;
                final int n21 = n22 = n6 + (n - 1) * n5;
                int n23 = n;
                for (int n24 = (1 - n - 1) / -1; n24 > 0; --n24) {
                    if (array2[n22 - 1 + n4] != 0.0) {
                        final double n25 = array2[n22 - 1 + n4];
                        int n26 = n21;
                        int n27 = n;
                        for (int n28 = (n23 + 1 - n - 1) / -1; n28 > 0; --n28) {
                            array2[n26 - 1 + n4] += n25 * array[n27 - 1 + (n23 - 1) * n3 + n2];
                            n26 -= n5;
                            --n27;
                        }
                        if (lsame) {
                            array2[n22 - 1 + n4] *= array[n23 - 1 + (n23 - 1) * n3 + n2];
                        }
                    }
                    n22 -= n5;
                    --n23;
                }
            }
        }
        else if (Lsame.lsame(s, "U")) {
            if (n5 == 1) {
                int n29 = n;
                for (int n30 = (1 - n - 1) / -1; n30 > 0; --n30) {
                    double n31 = array2[n29 - 1 + n4];
                    if (lsame) {
                        n31 *= array[n29 - 1 + (n29 - 1) * n3 + n2];
                    }
                    int n32 = n29 - 1;
                    for (int n33 = (1 - (n29 - 1) - 1) / -1; n33 > 0; --n33) {
                        n31 += array[n32 - 1 + (n29 - 1) * n3 + n2] * array2[n32 - 1 + n4];
                        --n32;
                    }
                    array2[n29 - 1 + n4] = n31;
                    --n29;
                }
            }
            else {
                int n34 = n6 + (n - 1) * n5;
                int n35 = n;
                for (int n36 = (1 - n - 1) / -1; n36 > 0; --n36) {
                    double n37 = array2[n34 - 1 + n4];
                    int n38 = n34;
                    if (lsame) {
                        n37 *= array[n35 - 1 + (n35 - 1) * n3 + n2];
                    }
                    int n39 = n35 - 1;
                    for (int n40 = (1 - (n35 - 1) - 1) / -1; n40 > 0; --n40) {
                        n38 -= n5;
                        n37 += array[n39 - 1 + (n35 - 1) * n3 + n2] * array2[n38 - 1 + n4];
                        --n39;
                    }
                    array2[n34 - 1 + n4] = n37;
                    n34 -= n5;
                    --n35;
                }
            }
        }
        else if (n5 == 1) {
            int n41 = 1;
            for (int n42 = n - 1 + 1; n42 > 0; --n42) {
                double n43 = array2[n41 - 1 + n4];
                if (lsame) {
                    n43 *= array[n41 - 1 + (n41 - 1) * n3 + n2];
                }
                int n44 = n41 + 1;
                for (int n45 = n - (n41 + 1) + 1; n45 > 0; --n45) {
                    n43 += array[n44 - 1 + (n41 - 1) * n3 + n2] * array2[n44 - 1 + n4];
                    ++n44;
                }
                array2[n41 - 1 + n4] = n43;
                ++n41;
            }
        }
        else {
            int n46 = n6;
            int n47 = 1;
            for (int n48 = n - 1 + 1; n48 > 0; --n48) {
                double n49 = array2[n46 - 1 + n4];
                int n50 = n46;
                if (lsame) {
                    n49 *= array[n47 - 1 + (n47 - 1) * n3 + n2];
                }
                int n51 = n47 + 1;
                for (int n52 = n - (n47 + 1) + 1; n52 > 0; --n52) {
                    n50 += n5;
                    n49 += array[n51 - 1 + (n47 - 1) * n3 + n2] * array2[n50 - 1 + n4];
                    ++n51;
                }
                array2[n46 - 1 + n4] = n49;
                n46 += n5;
                ++n47;
            }
        }
    }
}
