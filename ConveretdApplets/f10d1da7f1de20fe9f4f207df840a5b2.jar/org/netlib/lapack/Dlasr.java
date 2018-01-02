// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.err.Xerbla;

public final class Dlasr
{
    public static void dlasr(final String s, final String s2, final String s3, final int n, final int n2, final double[] array, final int n3, final double[] array2, final int n4, final double[] array3, final int n5, final int n6) {
        int n7 = 0;
        if ((Lsame.lsame(s, "L") || Lsame.lsame(s, "R")) ^ true) {
            n7 = 1;
        }
        else if ((Lsame.lsame(s2, "V") || Lsame.lsame(s2, "T") || Lsame.lsame(s2, "B")) ^ true) {
            n7 = 2;
        }
        else if ((Lsame.lsame(s3, "F") || Lsame.lsame(s3, "B")) ^ true) {
            n7 = 3;
        }
        else if (n < 0) {
            n7 = 4;
        }
        else if (n2 < 0) {
            n7 = 5;
        }
        else if (n6 < Math.max(1, n)) {
            n7 = 9;
        }
        if (n7 != 0) {
            Xerbla.xerbla("DLASR ", n7);
            return;
        }
        if (n == 0 || n2 == 0) {
            return;
        }
        if (Lsame.lsame(s, "L")) {
            if (Lsame.lsame(s2, "V")) {
                if (Lsame.lsame(s3, "F")) {
                    int n8 = 1;
                    for (int i = n - 1 - 1 + 1; i > 0; --i) {
                        final double n9 = array[n8 - 1 + n3];
                        final double n10 = array2[n8 - 1 + n4];
                        if (n9 != 1.0 || n10 != 0.0) {
                            int n11 = 1;
                            for (int j = n2 - 1 + 1; j > 0; --j) {
                                final double n12 = array3[n8 + 1 - 1 + (n11 - 1) * n6 + n5];
                                array3[n8 + 1 - 1 + (n11 - 1) * n6 + n5] = n9 * n12 - n10 * array3[n8 - 1 + (n11 - 1) * n6 + n5];
                                array3[n8 - 1 + (n11 - 1) * n6 + n5] = n10 * n12 + n9 * array3[n8 - 1 + (n11 - 1) * n6 + n5];
                                ++n11;
                            }
                        }
                        ++n8;
                    }
                }
                else if (Lsame.lsame(s3, "B")) {
                    int n13 = n - 1;
                    for (int k = (1 - (n - 1) - 1) / -1; k > 0; --k) {
                        final double n14 = array[n13 - 1 + n3];
                        final double n15 = array2[n13 - 1 + n4];
                        if (n14 != 1.0 || n15 != 0.0) {
                            int n16 = 1;
                            for (int l = n2 - 1 + 1; l > 0; --l) {
                                final double n17 = array3[n13 + 1 - 1 + (n16 - 1) * n6 + n5];
                                array3[n13 + 1 - 1 + (n16 - 1) * n6 + n5] = n14 * n17 - n15 * array3[n13 - 1 + (n16 - 1) * n6 + n5];
                                array3[n13 - 1 + (n16 - 1) * n6 + n5] = n15 * n17 + n14 * array3[n13 - 1 + (n16 - 1) * n6 + n5];
                                ++n16;
                            }
                        }
                        --n13;
                    }
                }
            }
            else if (Lsame.lsame(s2, "T")) {
                if (Lsame.lsame(s3, "F")) {
                    int n18 = 2;
                    for (int n19 = n - 2 + 1; n19 > 0; --n19) {
                        final double n20 = array[n18 - 1 - 1 + n3];
                        final double n21 = array2[n18 - 1 - 1 + n4];
                        if (n20 != 1.0 || n21 != 0.0) {
                            int n22 = 1;
                            for (int n23 = n2 - 1 + 1; n23 > 0; --n23) {
                                final double n24 = array3[n18 - 1 + (n22 - 1) * n6 + n5];
                                array3[n18 - 1 + (n22 - 1) * n6 + n5] = n20 * n24 - n21 * array3[1 - 1 + (n22 - 1) * n6 + n5];
                                array3[1 - 1 + (n22 - 1) * n6 + n5] = n21 * n24 + n20 * array3[1 - 1 + (n22 - 1) * n6 + n5];
                                ++n22;
                            }
                        }
                        ++n18;
                    }
                }
                else if (Lsame.lsame(s3, "B")) {
                    int n25 = n;
                    for (int n26 = (2 - n - 1) / -1; n26 > 0; --n26) {
                        final double n27 = array[n25 - 1 - 1 + n3];
                        final double n28 = array2[n25 - 1 - 1 + n4];
                        if (n27 != 1.0 || n28 != 0.0) {
                            int n29 = 1;
                            for (int n30 = n2 - 1 + 1; n30 > 0; --n30) {
                                final double n31 = array3[n25 - 1 + (n29 - 1) * n6 + n5];
                                array3[n25 - 1 + (n29 - 1) * n6 + n5] = n27 * n31 - n28 * array3[1 - 1 + (n29 - 1) * n6 + n5];
                                array3[1 - 1 + (n29 - 1) * n6 + n5] = n28 * n31 + n27 * array3[1 - 1 + (n29 - 1) * n6 + n5];
                                ++n29;
                            }
                        }
                        --n25;
                    }
                }
            }
            else if (Lsame.lsame(s2, "B")) {
                if (Lsame.lsame(s3, "F")) {
                    int n32 = 1;
                    for (int n33 = n - 1 - 1 + 1; n33 > 0; --n33) {
                        final double n34 = array[n32 - 1 + n3];
                        final double n35 = array2[n32 - 1 + n4];
                        if (n34 != 1.0 || n35 != 0.0) {
                            int n36 = 1;
                            for (int n37 = n2 - 1 + 1; n37 > 0; --n37) {
                                final double n38 = array3[n32 - 1 + (n36 - 1) * n6 + n5];
                                array3[n32 - 1 + (n36 - 1) * n6 + n5] = n35 * array3[n - 1 + (n36 - 1) * n6 + n5] + n34 * n38;
                                array3[n - 1 + (n36 - 1) * n6 + n5] = n34 * array3[n - 1 + (n36 - 1) * n6 + n5] - n35 * n38;
                                ++n36;
                            }
                        }
                        ++n32;
                    }
                }
                else if (Lsame.lsame(s3, "B")) {
                    int n39 = n - 1;
                    for (int n40 = (1 - (n - 1) - 1) / -1; n40 > 0; --n40) {
                        final double n41 = array[n39 - 1 + n3];
                        final double n42 = array2[n39 - 1 + n4];
                        if (n41 != 1.0 || n42 != 0.0) {
                            int n43 = 1;
                            for (int n44 = n2 - 1 + 1; n44 > 0; --n44) {
                                final double n45 = array3[n39 - 1 + (n43 - 1) * n6 + n5];
                                array3[n39 - 1 + (n43 - 1) * n6 + n5] = n42 * array3[n - 1 + (n43 - 1) * n6 + n5] + n41 * n45;
                                array3[n - 1 + (n43 - 1) * n6 + n5] = n41 * array3[n - 1 + (n43 - 1) * n6 + n5] - n42 * n45;
                                ++n43;
                            }
                        }
                        --n39;
                    }
                }
            }
        }
        else if (Lsame.lsame(s, "R")) {
            if (Lsame.lsame(s2, "V")) {
                if (Lsame.lsame(s3, "F")) {
                    int n46 = 1;
                    for (int n47 = n2 - 1 - 1 + 1; n47 > 0; --n47) {
                        final double n48 = array[n46 - 1 + n3];
                        final double n49 = array2[n46 - 1 + n4];
                        if (n48 != 1.0 || n49 != 0.0) {
                            int n50 = 1;
                            for (int n51 = n - 1 + 1; n51 > 0; --n51) {
                                final double n52 = array3[n50 - 1 + (n46 + 1 - 1) * n6 + n5];
                                array3[n50 - 1 + (n46 + 1 - 1) * n6 + n5] = n48 * n52 - n49 * array3[n50 - 1 + (n46 - 1) * n6 + n5];
                                array3[n50 - 1 + (n46 - 1) * n6 + n5] = n49 * n52 + n48 * array3[n50 - 1 + (n46 - 1) * n6 + n5];
                                ++n50;
                            }
                        }
                        ++n46;
                    }
                }
                else if (Lsame.lsame(s3, "B")) {
                    int n53 = n2 - 1;
                    for (int n54 = (1 - (n2 - 1) - 1) / -1; n54 > 0; --n54) {
                        final double n55 = array[n53 - 1 + n3];
                        final double n56 = array2[n53 - 1 + n4];
                        if (n55 != 1.0 || n56 != 0.0) {
                            int n57 = 1;
                            for (int n58 = n - 1 + 1; n58 > 0; --n58) {
                                final double n59 = array3[n57 - 1 + (n53 + 1 - 1) * n6 + n5];
                                array3[n57 - 1 + (n53 + 1 - 1) * n6 + n5] = n55 * n59 - n56 * array3[n57 - 1 + (n53 - 1) * n6 + n5];
                                array3[n57 - 1 + (n53 - 1) * n6 + n5] = n56 * n59 + n55 * array3[n57 - 1 + (n53 - 1) * n6 + n5];
                                ++n57;
                            }
                        }
                        --n53;
                    }
                }
            }
            else if (Lsame.lsame(s2, "T")) {
                if (Lsame.lsame(s3, "F")) {
                    int n60 = 2;
                    for (int n61 = n2 - 2 + 1; n61 > 0; --n61) {
                        final double n62 = array[n60 - 1 - 1 + n3];
                        final double n63 = array2[n60 - 1 - 1 + n4];
                        if (n62 != 1.0 || n63 != 0.0) {
                            int n64 = 1;
                            for (int n65 = n - 1 + 1; n65 > 0; --n65) {
                                final double n66 = array3[n64 - 1 + (n60 - 1) * n6 + n5];
                                array3[n64 - 1 + (n60 - 1) * n6 + n5] = n62 * n66 - n63 * array3[n64 - 1 + (1 - 1) * n6 + n5];
                                array3[n64 - 1 + (1 - 1) * n6 + n5] = n63 * n66 + n62 * array3[n64 - 1 + (1 - 1) * n6 + n5];
                                ++n64;
                            }
                        }
                        ++n60;
                    }
                }
                else if (Lsame.lsame(s3, "B")) {
                    int n67 = n2;
                    for (int n68 = (2 - n2 - 1) / -1; n68 > 0; --n68) {
                        final double n69 = array[n67 - 1 - 1 + n3];
                        final double n70 = array2[n67 - 1 - 1 + n4];
                        if (n69 != 1.0 || n70 != 0.0) {
                            int n71 = 1;
                            for (int n72 = n - 1 + 1; n72 > 0; --n72) {
                                final double n73 = array3[n71 - 1 + (n67 - 1) * n6 + n5];
                                array3[n71 - 1 + (n67 - 1) * n6 + n5] = n69 * n73 - n70 * array3[n71 - 1 + (1 - 1) * n6 + n5];
                                array3[n71 - 1 + (1 - 1) * n6 + n5] = n70 * n73 + n69 * array3[n71 - 1 + (1 - 1) * n6 + n5];
                                ++n71;
                            }
                        }
                        --n67;
                    }
                }
            }
            else if (Lsame.lsame(s2, "B")) {
                if (Lsame.lsame(s3, "F")) {
                    int n74 = 1;
                    for (int n75 = n2 - 1 - 1 + 1; n75 > 0; --n75) {
                        final double n76 = array[n74 - 1 + n3];
                        final double n77 = array2[n74 - 1 + n4];
                        if (n76 != 1.0 || n77 != 0.0) {
                            int n78 = 1;
                            for (int n79 = n - 1 + 1; n79 > 0; --n79) {
                                final double n80 = array3[n78 - 1 + (n74 - 1) * n6 + n5];
                                array3[n78 - 1 + (n74 - 1) * n6 + n5] = n77 * array3[n78 - 1 + (n2 - 1) * n6 + n5] + n76 * n80;
                                array3[n78 - 1 + (n2 - 1) * n6 + n5] = n76 * array3[n78 - 1 + (n2 - 1) * n6 + n5] - n77 * n80;
                                ++n78;
                            }
                        }
                        ++n74;
                    }
                }
                else if (Lsame.lsame(s3, "B")) {
                    int n81 = n2 - 1;
                    for (int n82 = (1 - (n2 - 1) - 1) / -1; n82 > 0; --n82) {
                        final double n83 = array[n81 - 1 + n3];
                        final double n84 = array2[n81 - 1 + n4];
                        if (n83 != 1.0 || n84 != 0.0) {
                            int n85 = 1;
                            for (int n86 = n - 1 + 1; n86 > 0; --n86) {
                                final double n87 = array3[n85 - 1 + (n81 - 1) * n6 + n5];
                                array3[n85 - 1 + (n81 - 1) * n6 + n5] = n84 * array3[n85 - 1 + (n2 - 1) * n6 + n5] + n83 * n87;
                                array3[n85 - 1 + (n2 - 1) * n6 + n5] = n83 * array3[n85 - 1 + (n2 - 1) * n6 + n5] - n84 * n87;
                                ++n85;
                            }
                        }
                        --n81;
                    }
                }
            }
        }
    }
}
