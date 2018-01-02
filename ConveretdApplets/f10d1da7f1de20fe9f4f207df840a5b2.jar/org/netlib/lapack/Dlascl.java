// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.err.Xerbla;
import org.netlib.util.intW;

public final class Dlascl
{
    public static void dlascl(final String s, final int n, final int n2, final double n3, final double n4, final int n5, final int n6, final double[] array, final int n7, final int n8, final intW intW) {
        intW.val = 0;
        int n9;
        if (Lsame.lsame(s, "G")) {
            n9 = 0;
        }
        else if (Lsame.lsame(s, "L")) {
            n9 = 1;
        }
        else if (Lsame.lsame(s, "U")) {
            n9 = 2;
        }
        else if (Lsame.lsame(s, "H")) {
            n9 = 3;
        }
        else if (Lsame.lsame(s, "B")) {
            n9 = 4;
        }
        else if (Lsame.lsame(s, "Q")) {
            n9 = 5;
        }
        else if (Lsame.lsame(s, "Z")) {
            n9 = 6;
        }
        else {
            n9 = -1;
        }
        if (n9 == -1) {
            intW.val = -1;
        }
        else if (n3 == 0.0) {
            intW.val = -4;
        }
        else if (n5 < 0) {
            intW.val = -6;
        }
        else if (n6 < 0 || (n9 == 4 && n6 != n5) || (n9 == 5 && n6 != n5)) {
            intW.val = -7;
        }
        else if (n9 <= 3 && n8 < Math.max(1, n5)) {
            intW.val = -9;
        }
        else if (n9 >= 4) {
            if (n < 0 || n > Math.max(n5 - 1, 0)) {
                intW.val = -2;
            }
            else if (n2 < 0 || n2 > Math.max(n6 - 1, 0) || ((n9 == 4 || n9 == 5) && n != n2)) {
                intW.val = -3;
            }
            else if ((n9 == 4 && n8 < n + 1) || (n9 == 5 && n8 < n2 + 1) || (n9 == 6 && n8 < 2 * n + n2 + 1)) {
                intW.val = -9;
            }
        }
        if (intW.val != 0) {
            Xerbla.xerbla("DLASCL", -intW.val);
            return;
        }
        if (n6 == 0 || n5 == 0) {
            return;
        }
        final double dlamch = Dlamch.dlamch("S");
        final double n10 = 1.0 / dlamch;
        double n11 = n3;
        double n12 = n4;
        int n16;
        do {
            final double n13 = n11 * dlamch;
            final double n14 = n12 / n10;
            double n15;
            if (Math.abs(n13) > Math.abs(n12) && n12 != 0.0) {
                n15 = dlamch;
                n16 = 0;
                n11 = n13;
            }
            else if (Math.abs(n14) > Math.abs(n11)) {
                n15 = n10;
                n16 = 0;
                n12 = n14;
            }
            else {
                n15 = n12 / n11;
                n16 = 1;
            }
            if (n9 == 0) {
                int n17 = 1;
                for (int i = n6 - 1 + 1; i > 0; --i) {
                    int n18 = 1;
                    for (int j = n5 - 1 + 1; j > 0; --j) {
                        array[n18 - 1 + (n17 - 1) * n8 + n7] *= n15;
                        ++n18;
                    }
                    ++n17;
                }
            }
            else if (n9 == 1) {
                int n19 = 1;
                for (int k = n6 - 1 + 1; k > 0; --k) {
                    int n20 = n19;
                    for (int l = n5 - n19 + 1; l > 0; --l) {
                        array[n20 - 1 + (n19 - 1) * n8 + n7] *= n15;
                        ++n20;
                    }
                    ++n19;
                }
            }
            else if (n9 == 2) {
                int n21 = 1;
                for (int n22 = n6 - 1 + 1; n22 > 0; --n22) {
                    int n23 = 1;
                    for (int n24 = Math.min(n21, n5) - 1 + 1; n24 > 0; --n24) {
                        array[n23 - 1 + (n21 - 1) * n8 + n7] *= n15;
                        ++n23;
                    }
                    ++n21;
                }
            }
            else if (n9 == 3) {
                int n25 = 1;
                for (int n26 = n6 - 1 + 1; n26 > 0; --n26) {
                    int n27 = 1;
                    for (int n28 = Math.min(n25 + 1, n5) - 1 + 1; n28 > 0; --n28) {
                        array[n27 - 1 + (n25 - 1) * n8 + n7] *= n15;
                        ++n27;
                    }
                    ++n25;
                }
            }
            else if (n9 == 4) {
                final int n29 = n + 1;
                final int n30 = n6 + 1;
                int n31 = 1;
                for (int n32 = n6 - 1 + 1; n32 > 0; --n32) {
                    int n33 = 1;
                    for (int n34 = Math.min(n29, n30 - n31) - 1 + 1; n34 > 0; --n34) {
                        array[n33 - 1 + (n31 - 1) * n8 + n7] *= n15;
                        ++n33;
                    }
                    ++n31;
                }
            }
            else if (n9 == 5) {
                final int n35 = n2 + 2;
                final int n36 = n2 + 1;
                int n37 = 1;
                for (int n38 = n6 - 1 + 1; n38 > 0; --n38) {
                    int max = Math.max(n35 - n37, 1);
                    for (int n39 = n36 - Math.max(n35 - n37, 1) + 1; n39 > 0; --n39) {
                        array[max - 1 + (n37 - 1) * n8 + n7] *= n15;
                        ++max;
                    }
                    ++n37;
                }
            }
            else if (n9 == 6) {
                final int n40 = n + n2 + 2;
                final int n41 = n + 1;
                final int n42 = 2 * n + n2 + 1;
                final int n43 = n + n2 + 1 + n5;
                int n44 = 1;
                for (int n45 = n6 - 1 + 1; n45 > 0; --n45) {
                    int max2 = Math.max(n40 - n44, n41);
                    for (int n46 = Math.min(n42, n43 - n44) - Math.max(n40 - n44, n41) + 1; n46 > 0; --n46) {
                        array[max2 - 1 + (n44 - 1) * n8 + n7] *= n15;
                        ++max2;
                    }
                    ++n44;
                }
            }
        } while ((n16 ^ 0x1) != 0x0);
    }
}
