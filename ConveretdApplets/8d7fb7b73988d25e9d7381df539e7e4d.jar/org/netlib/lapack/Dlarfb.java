// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.blas.Dgemm;
import org.netlib.blas.Dtrmm;
import org.netlib.blas.Dcopy;

public final class Dlarfb
{
    public static void dlarfb(final String s, final String s2, final String s3, final String s4, final int n, final int n2, final int n3, final double[] array, final int n4, final int n5, final double[] array2, final int n6, final int n7, final double[] array3, final int n8, final int n9, final double[] array4, final int n10, final int n11) {
        final String s5 = new String(" ");
        if (n <= 0 || n2 <= 0) {
            return;
        }
        String s6;
        if (Lsame.lsame(s2, "N")) {
            s6 = "T";
        }
        else {
            s6 = "N";
        }
        if (Lsame.lsame(s4, "C")) {
            if (Lsame.lsame(s3, "F")) {
                if (Lsame.lsame(s, "L")) {
                    int n12 = 1;
                    for (int i = n3 - 1 + 1; i > 0; --i) {
                        Dcopy.dcopy(n2, array3, n12 - 1 + (1 - 1) * n9 + n8, n9, array4, 1 - 1 + (n12 - 1) * n11 + n10, 1);
                        ++n12;
                    }
                    Dtrmm.dtrmm("Right", "Lower", "No transpose", "Unit", n2, n3, 1.0, array, n4, n5, array4, n10, n11);
                    if (n > n3) {
                        Dgemm.dgemm("Transpose", "No transpose", n2, n3, n - n3, 1.0, array3, n3 + 1 - 1 + (1 - 1) * n9 + n8, n9, array, n3 + 1 - 1 + (1 - 1) * n5 + n4, n5, 1.0, array4, n10, n11);
                    }
                    Dtrmm.dtrmm("Right", "Upper", s6, "Non-unit", n2, n3, 1.0, array2, n6, n7, array4, n10, n11);
                    if (n > n3) {
                        Dgemm.dgemm("No transpose", "Transpose", n - n3, n2, n3, -1.0, array, n3 + 1 - 1 + (1 - 1) * n5 + n4, n5, array4, n10, n11, 1.0, array3, n3 + 1 - 1 + (1 - 1) * n9 + n8, n9);
                    }
                    Dtrmm.dtrmm("Right", "Lower", "Transpose", "Unit", n2, n3, 1.0, array, n4, n5, array4, n10, n11);
                    int n13 = 1;
                    for (int j = n3 - 1 + 1; j > 0; --j) {
                        int n14 = 1;
                        for (int k = n2 - 1 + 1; k > 0; --k) {
                            array3[n13 - 1 + (n14 - 1) * n9 + n8] -= array4[n14 - 1 + (n13 - 1) * n11 + n10];
                            ++n14;
                        }
                        ++n13;
                    }
                }
                else if (Lsame.lsame(s, "R")) {
                    int n15 = 1;
                    for (int l = n3 - 1 + 1; l > 0; --l) {
                        Dcopy.dcopy(n, array3, 1 - 1 + (n15 - 1) * n9 + n8, 1, array4, 1 - 1 + (n15 - 1) * n11 + n10, 1);
                        ++n15;
                    }
                    Dtrmm.dtrmm("Right", "Lower", "No transpose", "Unit", n, n3, 1.0, array, n4, n5, array4, n10, n11);
                    if (n2 > n3) {
                        Dgemm.dgemm("No transpose", "No transpose", n, n3, n2 - n3, 1.0, array3, 1 - 1 + (n3 + 1 - 1) * n9 + n8, n9, array, n3 + 1 - 1 + (1 - 1) * n5 + n4, n5, 1.0, array4, n10, n11);
                    }
                    Dtrmm.dtrmm("Right", "Upper", s2, "Non-unit", n, n3, 1.0, array2, n6, n7, array4, n10, n11);
                    if (n2 > n3) {
                        Dgemm.dgemm("No transpose", "Transpose", n, n2 - n3, n3, -1.0, array4, n10, n11, array, n3 + 1 - 1 + (1 - 1) * n5 + n4, n5, 1.0, array3, 1 - 1 + (n3 + 1 - 1) * n9 + n8, n9);
                    }
                    Dtrmm.dtrmm("Right", "Lower", "Transpose", "Unit", n, n3, 1.0, array, n4, n5, array4, n10, n11);
                    int n16 = 1;
                    for (int n17 = n3 - 1 + 1; n17 > 0; --n17) {
                        int n18 = 1;
                        for (int n19 = n - 1 + 1; n19 > 0; --n19) {
                            array3[n18 - 1 + (n16 - 1) * n9 + n8] -= array4[n18 - 1 + (n16 - 1) * n11 + n10];
                            ++n18;
                        }
                        ++n16;
                    }
                }
            }
            else if (Lsame.lsame(s, "L")) {
                int n20 = 1;
                for (int n21 = n3 - 1 + 1; n21 > 0; --n21) {
                    Dcopy.dcopy(n2, array3, n - n3 + n20 - 1 + (1 - 1) * n9 + n8, n9, array4, 1 - 1 + (n20 - 1) * n11 + n10, 1);
                    ++n20;
                }
                Dtrmm.dtrmm("Right", "Upper", "No transpose", "Unit", n2, n3, 1.0, array, n - n3 + 1 - 1 + (1 - 1) * n5 + n4, n5, array4, n10, n11);
                if (n > n3) {
                    Dgemm.dgemm("Transpose", "No transpose", n2, n3, n - n3, 1.0, array3, n8, n9, array, n4, n5, 1.0, array4, n10, n11);
                }
                Dtrmm.dtrmm("Right", "Lower", s6, "Non-unit", n2, n3, 1.0, array2, n6, n7, array4, n10, n11);
                if (n > n3) {
                    Dgemm.dgemm("No transpose", "Transpose", n - n3, n2, n3, -1.0, array, n4, n5, array4, n10, n11, 1.0, array3, n8, n9);
                }
                Dtrmm.dtrmm("Right", "Upper", "Transpose", "Unit", n2, n3, 1.0, array, n - n3 + 1 - 1 + (1 - 1) * n5 + n4, n5, array4, n10, n11);
                int n22 = 1;
                for (int n23 = n3 - 1 + 1; n23 > 0; --n23) {
                    int n24 = 1;
                    for (int n25 = n2 - 1 + 1; n25 > 0; --n25) {
                        array3[n - n3 + n22 - 1 + (n24 - 1) * n9 + n8] -= array4[n24 - 1 + (n22 - 1) * n11 + n10];
                        ++n24;
                    }
                    ++n22;
                }
            }
            else if (Lsame.lsame(s, "R")) {
                int n26 = 1;
                for (int n27 = n3 - 1 + 1; n27 > 0; --n27) {
                    Dcopy.dcopy(n, array3, 1 - 1 + (n2 - n3 + n26 - 1) * n9 + n8, 1, array4, 1 - 1 + (n26 - 1) * n11 + n10, 1);
                    ++n26;
                }
                Dtrmm.dtrmm("Right", "Upper", "No transpose", "Unit", n, n3, 1.0, array, n2 - n3 + 1 - 1 + (1 - 1) * n5 + n4, n5, array4, n10, n11);
                if (n2 > n3) {
                    Dgemm.dgemm("No transpose", "No transpose", n, n3, n2 - n3, 1.0, array3, n8, n9, array, n4, n5, 1.0, array4, n10, n11);
                }
                Dtrmm.dtrmm("Right", "Lower", s2, "Non-unit", n, n3, 1.0, array2, n6, n7, array4, n10, n11);
                if (n2 > n3) {
                    Dgemm.dgemm("No transpose", "Transpose", n, n2 - n3, n3, -1.0, array4, n10, n11, array, n4, n5, 1.0, array3, n8, n9);
                }
                Dtrmm.dtrmm("Right", "Upper", "Transpose", "Unit", n, n3, 1.0, array, n2 - n3 + 1 - 1 + (1 - 1) * n5 + n4, n5, array4, n10, n11);
                int n28 = 1;
                for (int n29 = n3 - 1 + 1; n29 > 0; --n29) {
                    int n30 = 1;
                    for (int n31 = n - 1 + 1; n31 > 0; --n31) {
                        array3[n30 - 1 + (n2 - n3 + n28 - 1) * n9 + n8] -= array4[n30 - 1 + (n28 - 1) * n11 + n10];
                        ++n30;
                    }
                    ++n28;
                }
            }
        }
        else if (Lsame.lsame(s4, "R")) {
            if (Lsame.lsame(s3, "F")) {
                if (Lsame.lsame(s, "L")) {
                    int n32 = 1;
                    for (int n33 = n3 - 1 + 1; n33 > 0; --n33) {
                        Dcopy.dcopy(n2, array3, n32 - 1 + (1 - 1) * n9 + n8, n9, array4, 1 - 1 + (n32 - 1) * n11 + n10, 1);
                        ++n32;
                    }
                    Dtrmm.dtrmm("Right", "Upper", "Transpose", "Unit", n2, n3, 1.0, array, n4, n5, array4, n10, n11);
                    if (n > n3) {
                        Dgemm.dgemm("Transpose", "Transpose", n2, n3, n - n3, 1.0, array3, n3 + 1 - 1 + (1 - 1) * n9 + n8, n9, array, 1 - 1 + (n3 + 1 - 1) * n5 + n4, n5, 1.0, array4, n10, n11);
                    }
                    Dtrmm.dtrmm("Right", "Upper", s6, "Non-unit", n2, n3, 1.0, array2, n6, n7, array4, n10, n11);
                    if (n > n3) {
                        Dgemm.dgemm("Transpose", "Transpose", n - n3, n2, n3, -1.0, array, 1 - 1 + (n3 + 1 - 1) * n5 + n4, n5, array4, n10, n11, 1.0, array3, n3 + 1 - 1 + (1 - 1) * n9 + n8, n9);
                    }
                    Dtrmm.dtrmm("Right", "Upper", "No transpose", "Unit", n2, n3, 1.0, array, n4, n5, array4, n10, n11);
                    int n34 = 1;
                    for (int n35 = n3 - 1 + 1; n35 > 0; --n35) {
                        int n36 = 1;
                        for (int n37 = n2 - 1 + 1; n37 > 0; --n37) {
                            array3[n34 - 1 + (n36 - 1) * n9 + n8] -= array4[n36 - 1 + (n34 - 1) * n11 + n10];
                            ++n36;
                        }
                        ++n34;
                    }
                }
                else if (Lsame.lsame(s, "R")) {
                    int n38 = 1;
                    for (int n39 = n3 - 1 + 1; n39 > 0; --n39) {
                        Dcopy.dcopy(n, array3, 1 - 1 + (n38 - 1) * n9 + n8, 1, array4, 1 - 1 + (n38 - 1) * n11 + n10, 1);
                        ++n38;
                    }
                    Dtrmm.dtrmm("Right", "Upper", "Transpose", "Unit", n, n3, 1.0, array, n4, n5, array4, n10, n11);
                    if (n2 > n3) {
                        Dgemm.dgemm("No transpose", "Transpose", n, n3, n2 - n3, 1.0, array3, 1 - 1 + (n3 + 1 - 1) * n9 + n8, n9, array, 1 - 1 + (n3 + 1 - 1) * n5 + n4, n5, 1.0, array4, n10, n11);
                    }
                    Dtrmm.dtrmm("Right", "Upper", s2, "Non-unit", n, n3, 1.0, array2, n6, n7, array4, n10, n11);
                    if (n2 > n3) {
                        Dgemm.dgemm("No transpose", "No transpose", n, n2 - n3, n3, -1.0, array4, n10, n11, array, 1 - 1 + (n3 + 1 - 1) * n5 + n4, n5, 1.0, array3, 1 - 1 + (n3 + 1 - 1) * n9 + n8, n9);
                    }
                    Dtrmm.dtrmm("Right", "Upper", "No transpose", "Unit", n, n3, 1.0, array, n4, n5, array4, n10, n11);
                    int n40 = 1;
                    for (int n41 = n3 - 1 + 1; n41 > 0; --n41) {
                        int n42 = 1;
                        for (int n43 = n - 1 + 1; n43 > 0; --n43) {
                            array3[n42 - 1 + (n40 - 1) * n9 + n8] -= array4[n42 - 1 + (n40 - 1) * n11 + n10];
                            ++n42;
                        }
                        ++n40;
                    }
                }
            }
            else if (Lsame.lsame(s, "L")) {
                int n44 = 1;
                for (int n45 = n3 - 1 + 1; n45 > 0; --n45) {
                    Dcopy.dcopy(n2, array3, n - n3 + n44 - 1 + (1 - 1) * n9 + n8, n9, array4, 1 - 1 + (n44 - 1) * n11 + n10, 1);
                    ++n44;
                }
                Dtrmm.dtrmm("Right", "Lower", "Transpose", "Unit", n2, n3, 1.0, array, 1 - 1 + (n - n3 + 1 - 1) * n5 + n4, n5, array4, n10, n11);
                if (n > n3) {
                    Dgemm.dgemm("Transpose", "Transpose", n2, n3, n - n3, 1.0, array3, n8, n9, array, n4, n5, 1.0, array4, n10, n11);
                }
                Dtrmm.dtrmm("Right", "Lower", s6, "Non-unit", n2, n3, 1.0, array2, n6, n7, array4, n10, n11);
                if (n > n3) {
                    Dgemm.dgemm("Transpose", "Transpose", n - n3, n2, n3, -1.0, array, n4, n5, array4, n10, n11, 1.0, array3, n8, n9);
                }
                Dtrmm.dtrmm("Right", "Lower", "No transpose", "Unit", n2, n3, 1.0, array, 1 - 1 + (n - n3 + 1 - 1) * n5 + n4, n5, array4, n10, n11);
                int n46 = 1;
                for (int n47 = n3 - 1 + 1; n47 > 0; --n47) {
                    int n48 = 1;
                    for (int n49 = n2 - 1 + 1; n49 > 0; --n49) {
                        array3[n - n3 + n46 - 1 + (n48 - 1) * n9 + n8] -= array4[n48 - 1 + (n46 - 1) * n11 + n10];
                        ++n48;
                    }
                    ++n46;
                }
            }
            else if (Lsame.lsame(s, "R")) {
                int n50 = 1;
                for (int n51 = n3 - 1 + 1; n51 > 0; --n51) {
                    Dcopy.dcopy(n, array3, 1 - 1 + (n2 - n3 + n50 - 1) * n9 + n8, 1, array4, 1 - 1 + (n50 - 1) * n11 + n10, 1);
                    ++n50;
                }
                Dtrmm.dtrmm("Right", "Lower", "Transpose", "Unit", n, n3, 1.0, array, 1 - 1 + (n2 - n3 + 1 - 1) * n5 + n4, n5, array4, n10, n11);
                if (n2 > n3) {
                    Dgemm.dgemm("No transpose", "Transpose", n, n3, n2 - n3, 1.0, array3, n8, n9, array, n4, n5, 1.0, array4, n10, n11);
                }
                Dtrmm.dtrmm("Right", "Lower", s2, "Non-unit", n, n3, 1.0, array2, n6, n7, array4, n10, n11);
                if (n2 > n3) {
                    Dgemm.dgemm("No transpose", "No transpose", n, n2 - n3, n3, -1.0, array4, n10, n11, array, n4, n5, 1.0, array3, n8, n9);
                }
                Dtrmm.dtrmm("Right", "Lower", "No transpose", "Unit", n, n3, 1.0, array, 1 - 1 + (n2 - n3 + 1 - 1) * n5 + n4, n5, array4, n10, n11);
                int n52 = 1;
                for (int n53 = n3 - 1 + 1; n53 > 0; --n53) {
                    int n54 = 1;
                    for (int n55 = n - 1 + 1; n55 > 0; --n55) {
                        array3[n54 - 1 + (n2 - n3 + n52 - 1) * n9 + n8] -= array4[n54 - 1 + (n52 - 1) * n11 + n10];
                        ++n54;
                    }
                    ++n52;
                }
            }
        }
    }
}
