// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.blas;

import org.netlib.err.Xerbla;

public final class Dtrmm
{
    public static void dtrmm(final String s, final String s2, final String s3, final String s4, final int n, final int n2, final double n3, final double[] array, final int n4, final int n5, final double[] array2, final int n6, final int n7) {
        final boolean lsame = Lsame.lsame(s, "L");
        int n8;
        if (lsame) {
            n8 = n;
        }
        else {
            n8 = n2;
        }
        final boolean lsame2 = Lsame.lsame(s4, "N");
        final boolean lsame3 = Lsame.lsame(s2, "U");
        int n9 = 0;
        if ((lsame ^ true) && (Lsame.lsame(s, "R") ^ true)) {
            n9 = 1;
        }
        else if ((lsame3 ^ true) && (Lsame.lsame(s2, "L") ^ true)) {
            n9 = 2;
        }
        else if ((Lsame.lsame(s3, "N") ^ true) && (Lsame.lsame(s3, "T") ^ true) && (Lsame.lsame(s3, "C") ^ true)) {
            n9 = 3;
        }
        else if ((Lsame.lsame(s4, "U") ^ true) && (Lsame.lsame(s4, "N") ^ true)) {
            n9 = 4;
        }
        else if (n < 0) {
            n9 = 5;
        }
        else if (n2 < 0) {
            n9 = 6;
        }
        else if (n5 < Math.max(1, n8)) {
            n9 = 9;
        }
        else if (n7 < Math.max(1, n)) {
            n9 = 11;
        }
        if (n9 != 0) {
            Xerbla.xerbla("DTRMM ", n9);
            return;
        }
        if (n2 == 0) {
            return;
        }
        if (n3 == 0.0) {
            int n10 = 1;
            for (int i = n2 - 1 + 1; i > 0; --i) {
                int n11 = 1;
                for (int j = n - 1 + 1; j > 0; --j) {
                    array2[n11 - 1 + (n10 - 1) * n7 + n6] = 0.0;
                    ++n11;
                }
                ++n10;
            }
            return;
        }
        if (lsame) {
            if (Lsame.lsame(s3, "N")) {
                if (lsame3) {
                    int n12 = 1;
                    for (int k = n2 - 1 + 1; k > 0; --k) {
                        int n13 = 1;
                        for (int l = n - 1 + 1; l > 0; --l) {
                            if (array2[n13 - 1 + (n12 - 1) * n7 + n6] != 0.0) {
                                double n14 = n3 * array2[n13 - 1 + (n12 - 1) * n7 + n6];
                                int n15 = 1;
                                for (int n16 = n13 - 1 - 1 + 1; n16 > 0; --n16) {
                                    array2[n15 - 1 + (n12 - 1) * n7 + n6] += n14 * array[n15 - 1 + (n13 - 1) * n5 + n4];
                                    ++n15;
                                }
                                if (lsame2) {
                                    n14 *= array[n13 - 1 + (n13 - 1) * n5 + n4];
                                }
                                array2[n13 - 1 + (n12 - 1) * n7 + n6] = n14;
                            }
                            ++n13;
                        }
                        ++n12;
                    }
                }
                else {
                    int n17 = 1;
                    for (int n18 = n2 - 1 + 1; n18 > 0; --n18) {
                        int n19 = n;
                        for (int n20 = (1 - n - 1) / -1; n20 > 0; --n20) {
                            if (array2[n19 - 1 + (n17 - 1) * n7 + n6] != 0.0) {
                                final double n21 = n3 * array2[n19 - 1 + (n17 - 1) * n7 + n6];
                                array2[n19 - 1 + (n17 - 1) * n7 + n6] = n21;
                                if (lsame2) {
                                    array2[n19 - 1 + (n17 - 1) * n7 + n6] *= array[n19 - 1 + (n19 - 1) * n5 + n4];
                                }
                                int n22 = n19 + 1;
                                for (int n23 = n - (n19 + 1) + 1; n23 > 0; --n23) {
                                    array2[n22 - 1 + (n17 - 1) * n7 + n6] += n21 * array[n22 - 1 + (n19 - 1) * n5 + n4];
                                    ++n22;
                                }
                            }
                            --n19;
                        }
                        ++n17;
                    }
                }
            }
            else if (lsame3) {
                int n24 = 1;
                for (int n25 = n2 - 1 + 1; n25 > 0; --n25) {
                    int n26 = n;
                    for (int n27 = (1 - n - 1) / -1; n27 > 0; --n27) {
                        double n28 = array2[n26 - 1 + (n24 - 1) * n7 + n6];
                        if (lsame2) {
                            n28 *= array[n26 - 1 + (n26 - 1) * n5 + n4];
                        }
                        int n29 = 1;
                        for (int n30 = n26 - 1 - 1 + 1; n30 > 0; --n30) {
                            n28 += array[n29 - 1 + (n26 - 1) * n5 + n4] * array2[n29 - 1 + (n24 - 1) * n7 + n6];
                            ++n29;
                        }
                        array2[n26 - 1 + (n24 - 1) * n7 + n6] = n3 * n28;
                        --n26;
                    }
                    ++n24;
                }
            }
            else {
                int n31 = 1;
                for (int n32 = n2 - 1 + 1; n32 > 0; --n32) {
                    int n33 = 1;
                    for (int n34 = n - 1 + 1; n34 > 0; --n34) {
                        double n35 = array2[n33 - 1 + (n31 - 1) * n7 + n6];
                        if (lsame2) {
                            n35 *= array[n33 - 1 + (n33 - 1) * n5 + n4];
                        }
                        int n36 = n33 + 1;
                        for (int n37 = n - (n33 + 1) + 1; n37 > 0; --n37) {
                            n35 += array[n36 - 1 + (n33 - 1) * n5 + n4] * array2[n36 - 1 + (n31 - 1) * n7 + n6];
                            ++n36;
                        }
                        array2[n33 - 1 + (n31 - 1) * n7 + n6] = n3 * n35;
                        ++n33;
                    }
                    ++n31;
                }
            }
        }
        else if (Lsame.lsame(s3, "N")) {
            if (lsame3) {
                int n38 = n2;
                for (int n39 = (1 - n2 - 1) / -1; n39 > 0; --n39) {
                    double n40 = n3;
                    if (lsame2) {
                        n40 *= array[n38 - 1 + (n38 - 1) * n5 + n4];
                    }
                    int n41 = 1;
                    for (int n42 = n - 1 + 1; n42 > 0; --n42) {
                        array2[n41 - 1 + (n38 - 1) * n7 + n6] *= n40;
                        ++n41;
                    }
                    int n43 = 1;
                    for (int n44 = n38 - 1 - 1 + 1; n44 > 0; --n44) {
                        if (array[n43 - 1 + (n38 - 1) * n5 + n4] != 0.0) {
                            final double n45 = n3 * array[n43 - 1 + (n38 - 1) * n5 + n4];
                            int n46 = 1;
                            for (int n47 = n - 1 + 1; n47 > 0; --n47) {
                                array2[n46 - 1 + (n38 - 1) * n7 + n6] += n45 * array2[n46 - 1 + (n43 - 1) * n7 + n6];
                                ++n46;
                            }
                        }
                        ++n43;
                    }
                    --n38;
                }
            }
            else {
                int n48 = 1;
                for (int n49 = n2 - 1 + 1; n49 > 0; --n49) {
                    double n50 = n3;
                    if (lsame2) {
                        n50 *= array[n48 - 1 + (n48 - 1) * n5 + n4];
                    }
                    int n51 = 1;
                    for (int n52 = n - 1 + 1; n52 > 0; --n52) {
                        array2[n51 - 1 + (n48 - 1) * n7 + n6] *= n50;
                        ++n51;
                    }
                    int n53 = n48 + 1;
                    for (int n54 = n2 - (n48 + 1) + 1; n54 > 0; --n54) {
                        if (array[n53 - 1 + (n48 - 1) * n5 + n4] != 0.0) {
                            final double n55 = n3 * array[n53 - 1 + (n48 - 1) * n5 + n4];
                            int n56 = 1;
                            for (int n57 = n - 1 + 1; n57 > 0; --n57) {
                                array2[n56 - 1 + (n48 - 1) * n7 + n6] += n55 * array2[n56 - 1 + (n53 - 1) * n7 + n6];
                                ++n56;
                            }
                        }
                        ++n53;
                    }
                    ++n48;
                }
            }
        }
        else if (lsame3) {
            int n58 = 1;
            for (int n59 = n2 - 1 + 1; n59 > 0; --n59) {
                int n60 = 1;
                for (int n61 = n58 - 1 - 1 + 1; n61 > 0; --n61) {
                    if (array[n60 - 1 + (n58 - 1) * n5 + n4] != 0.0) {
                        final double n62 = n3 * array[n60 - 1 + (n58 - 1) * n5 + n4];
                        int n63 = 1;
                        for (int n64 = n - 1 + 1; n64 > 0; --n64) {
                            array2[n63 - 1 + (n60 - 1) * n7 + n6] += n62 * array2[n63 - 1 + (n58 - 1) * n7 + n6];
                            ++n63;
                        }
                    }
                    ++n60;
                }
                double n65 = n3;
                if (lsame2) {
                    n65 *= array[n58 - 1 + (n58 - 1) * n5 + n4];
                }
                if (n65 != 1.0) {
                    int n66 = 1;
                    for (int n67 = n - 1 + 1; n67 > 0; --n67) {
                        array2[n66 - 1 + (n58 - 1) * n7 + n6] *= n65;
                        ++n66;
                    }
                }
                ++n58;
            }
        }
        else {
            int n68 = n2;
            for (int n69 = (1 - n2 - 1) / -1; n69 > 0; --n69) {
                int n70 = n68 + 1;
                for (int n71 = n2 - (n68 + 1) + 1; n71 > 0; --n71) {
                    if (array[n70 - 1 + (n68 - 1) * n5 + n4] != 0.0) {
                        final double n72 = n3 * array[n70 - 1 + (n68 - 1) * n5 + n4];
                        int n73 = 1;
                        for (int n74 = n - 1 + 1; n74 > 0; --n74) {
                            array2[n73 - 1 + (n70 - 1) * n7 + n6] += n72 * array2[n73 - 1 + (n68 - 1) * n7 + n6];
                            ++n73;
                        }
                    }
                    ++n70;
                }
                double n75 = n3;
                if (lsame2) {
                    n75 *= array[n68 - 1 + (n68 - 1) * n5 + n4];
                }
                if (n75 != 1.0) {
                    int n76 = 1;
                    for (int n77 = n - 1 + 1; n77 > 0; --n77) {
                        array2[n76 - 1 + (n68 - 1) * n7 + n6] *= n75;
                        ++n76;
                    }
                }
                --n68;
            }
        }
    }
}
