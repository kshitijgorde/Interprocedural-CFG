// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.blas;

import org.netlib.err.Xerbla;

public final class Dsyr2k
{
    public static void dsyr2k(final String s, final String s2, final int n, final int n2, final double n3, final double[] array, final int n4, final int n5, final double[] array2, final int n6, final int n7, final double n8, final double[] array3, final int n9, final int n10) {
        int n11;
        if (Lsame.lsame(s2, "N")) {
            n11 = n;
        }
        else {
            n11 = n2;
        }
        final boolean lsame = Lsame.lsame(s, "U");
        int n12 = 0;
        if ((lsame ^ true) && (Lsame.lsame(s, "L") ^ true)) {
            n12 = 1;
        }
        else if ((Lsame.lsame(s2, "N") ^ true) && (Lsame.lsame(s2, "T") ^ true) && (Lsame.lsame(s2, "C") ^ true)) {
            n12 = 2;
        }
        else if (n < 0) {
            n12 = 3;
        }
        else if (n2 < 0) {
            n12 = 4;
        }
        else if (n5 < Math.max(1, n11)) {
            n12 = 7;
        }
        else if (n7 < Math.max(1, n11)) {
            n12 = 9;
        }
        else if (n10 < Math.max(1, n)) {
            n12 = 12;
        }
        if (n12 != 0) {
            Xerbla.xerbla("DSYR2K", n12);
            return;
        }
        if (n == 0 || ((n3 == 0.0 || n2 == 0) && n8 == 1.0)) {
            return;
        }
        if (n3 == 0.0) {
            if (lsame) {
                if (n8 == 0.0) {
                    int n13 = 1;
                    for (int i = n - 1 + 1; i > 0; --i) {
                        int n14 = 1;
                        for (int j = n13 - 1 + 1; j > 0; --j) {
                            array3[n14 - 1 + (n13 - 1) * n10 + n9] = 0.0;
                            ++n14;
                        }
                        ++n13;
                    }
                }
                else {
                    int n15 = 1;
                    for (int k = n - 1 + 1; k > 0; --k) {
                        int n16 = 1;
                        for (int l = n15 - 1 + 1; l > 0; --l) {
                            array3[n16 - 1 + (n15 - 1) * n10 + n9] *= n8;
                            ++n16;
                        }
                        ++n15;
                    }
                }
            }
            else if (n8 == 0.0) {
                int n17 = 1;
                for (int n18 = n - 1 + 1; n18 > 0; --n18) {
                    int n19 = n17;
                    for (int n20 = n - n17 + 1; n20 > 0; --n20) {
                        array3[n19 - 1 + (n17 - 1) * n10 + n9] = 0.0;
                        ++n19;
                    }
                    ++n17;
                }
            }
            else {
                int n21 = 1;
                for (int n22 = n - 1 + 1; n22 > 0; --n22) {
                    int n23 = n21;
                    for (int n24 = n - n21 + 1; n24 > 0; --n24) {
                        array3[n23 - 1 + (n21 - 1) * n10 + n9] *= n8;
                        ++n23;
                    }
                    ++n21;
                }
            }
            return;
        }
        if (Lsame.lsame(s2, "N")) {
            if (lsame) {
                int n25 = 1;
                for (int n26 = n - 1 + 1; n26 > 0; --n26) {
                    if (n8 == 0.0) {
                        int n27 = 1;
                        for (int n28 = n25 - 1 + 1; n28 > 0; --n28) {
                            array3[n27 - 1 + (n25 - 1) * n10 + n9] = 0.0;
                            ++n27;
                        }
                    }
                    else if (n8 != 1.0) {
                        int n29 = 1;
                        for (int n30 = n25 - 1 + 1; n30 > 0; --n30) {
                            array3[n29 - 1 + (n25 - 1) * n10 + n9] *= n8;
                            ++n29;
                        }
                    }
                    int n31 = 1;
                    for (int n32 = n2 - 1 + 1; n32 > 0; --n32) {
                        if (array[n25 - 1 + (n31 - 1) * n5 + n4] != 0.0 || array2[n25 - 1 + (n31 - 1) * n7 + n6] != 0.0) {
                            final double n33 = n3 * array2[n25 - 1 + (n31 - 1) * n7 + n6];
                            final double n34 = n3 * array[n25 - 1 + (n31 - 1) * n5 + n4];
                            int n35 = 1;
                            for (int n36 = n25 - 1 + 1; n36 > 0; --n36) {
                                array3[n35 - 1 + (n25 - 1) * n10 + n9] = array3[n35 - 1 + (n25 - 1) * n10 + n9] + array[n35 - 1 + (n31 - 1) * n5 + n4] * n33 + array2[n35 - 1 + (n31 - 1) * n7 + n6] * n34;
                                ++n35;
                            }
                        }
                        ++n31;
                    }
                    ++n25;
                }
            }
            else {
                int n37 = 1;
                for (int n38 = n - 1 + 1; n38 > 0; --n38) {
                    if (n8 == 0.0) {
                        int n39 = n37;
                        for (int n40 = n - n37 + 1; n40 > 0; --n40) {
                            array3[n39 - 1 + (n37 - 1) * n10 + n9] = 0.0;
                            ++n39;
                        }
                    }
                    else if (n8 != 1.0) {
                        int n41 = n37;
                        for (int n42 = n - n37 + 1; n42 > 0; --n42) {
                            array3[n41 - 1 + (n37 - 1) * n10 + n9] *= n8;
                            ++n41;
                        }
                    }
                    int n43 = 1;
                    for (int n44 = n2 - 1 + 1; n44 > 0; --n44) {
                        if (array[n37 - 1 + (n43 - 1) * n5 + n4] != 0.0 || array2[n37 - 1 + (n43 - 1) * n7 + n6] != 0.0) {
                            final double n45 = n3 * array2[n37 - 1 + (n43 - 1) * n7 + n6];
                            final double n46 = n3 * array[n37 - 1 + (n43 - 1) * n5 + n4];
                            int n47 = n37;
                            for (int n48 = n - n37 + 1; n48 > 0; --n48) {
                                array3[n47 - 1 + (n37 - 1) * n10 + n9] = array3[n47 - 1 + (n37 - 1) * n10 + n9] + array[n47 - 1 + (n43 - 1) * n5 + n4] * n45 + array2[n47 - 1 + (n43 - 1) * n7 + n6] * n46;
                                ++n47;
                            }
                        }
                        ++n43;
                    }
                    ++n37;
                }
            }
        }
        else if (lsame) {
            int n49 = 1;
            for (int n50 = n - 1 + 1; n50 > 0; --n50) {
                int n51 = 1;
                for (int n52 = n49 - 1 + 1; n52 > 0; --n52) {
                    double n53 = 0.0;
                    double n54 = 0.0;
                    int n55 = 1;
                    for (int n56 = n2 - 1 + 1; n56 > 0; --n56) {
                        n53 += array[n55 - 1 + (n51 - 1) * n5 + n4] * array2[n55 - 1 + (n49 - 1) * n7 + n6];
                        n54 += array2[n55 - 1 + (n51 - 1) * n7 + n6] * array[n55 - 1 + (n49 - 1) * n5 + n4];
                        ++n55;
                    }
                    if (n8 == 0.0) {
                        array3[n51 - 1 + (n49 - 1) * n10 + n9] = n3 * n53 + n3 * n54;
                    }
                    else {
                        array3[n51 - 1 + (n49 - 1) * n10 + n9] = n8 * array3[n51 - 1 + (n49 - 1) * n10 + n9] + n3 * n53 + n3 * n54;
                    }
                    ++n51;
                }
                ++n49;
            }
        }
        else {
            int n57 = 1;
            for (int n58 = n - 1 + 1; n58 > 0; --n58) {
                int n59 = n57;
                for (int n60 = n - n57 + 1; n60 > 0; --n60) {
                    double n61 = 0.0;
                    double n62 = 0.0;
                    int n63 = 1;
                    for (int n64 = n2 - 1 + 1; n64 > 0; --n64) {
                        n61 += array[n63 - 1 + (n59 - 1) * n5 + n4] * array2[n63 - 1 + (n57 - 1) * n7 + n6];
                        n62 += array2[n63 - 1 + (n59 - 1) * n7 + n6] * array[n63 - 1 + (n57 - 1) * n5 + n4];
                        ++n63;
                    }
                    if (n8 == 0.0) {
                        array3[n59 - 1 + (n57 - 1) * n10 + n9] = n3 * n61 + n3 * n62;
                    }
                    else {
                        array3[n59 - 1 + (n57 - 1) * n10 + n9] = n8 * array3[n59 - 1 + (n57 - 1) * n10 + n9] + n3 * n61 + n3 * n62;
                    }
                    ++n59;
                }
                ++n57;
            }
        }
    }
}
