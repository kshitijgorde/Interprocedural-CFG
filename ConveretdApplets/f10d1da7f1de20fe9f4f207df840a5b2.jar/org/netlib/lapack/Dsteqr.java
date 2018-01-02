// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.blas.Dswap;
import org.netlib.util.Util;
import org.netlib.err.Xerbla;
import org.netlib.util.doubleW;
import org.netlib.util.intW;

public final class Dsteqr
{
    public static void dsteqr(final String s, final int n, final double[] array, final int n2, final double[] array2, final int n3, final double[] array3, final int n4, final int n5, final double[] array4, final int n6, final intW intW) {
        final doubleW doubleW = new doubleW(0.0);
        final doubleW doubleW2 = new doubleW(0.0);
        final doubleW doubleW3 = new doubleW(0.0);
        final doubleW doubleW4 = new doubleW(0.0);
        final doubleW doubleW5 = new doubleW(0.0);
        intW.val = 0;
        int n7;
        if (Lsame.lsame(s, "N")) {
            n7 = 0;
        }
        else if (Lsame.lsame(s, "V")) {
            n7 = 1;
        }
        else if (Lsame.lsame(s, "I")) {
            n7 = 2;
        }
        else {
            n7 = -1;
        }
        if (n7 < 0) {
            intW.val = -1;
        }
        else if (n < 0) {
            intW.val = -2;
        }
        else if (n5 < 1 || (n7 > 0 && n5 < Math.max(1, n))) {
            intW.val = -6;
        }
        if (intW.val != 0) {
            Xerbla.xerbla("DSTEQR", -intW.val);
            return;
        }
        if (n == 0) {
            return;
        }
        if (n == 1) {
            if (n7 == 2) {
                array3[1 - 1 + (1 - 1) * n5 + n4] = 1.0;
            }
            return;
        }
        final double dlamch = Dlamch.dlamch("E");
        final double pow = Math.pow(dlamch, 2);
        final double dlamch2 = Dlamch.dlamch("S");
        final double n8 = Math.sqrt(1.0 / dlamch2) / 3.0;
        final double n9 = Math.sqrt(dlamch2) / pow;
        if (n7 == 2) {
            Dlaset.dlaset("Full", n, n, 0.0, 1.0, array3, n4, n5);
        }
        final int n10 = n * 30;
        int n11 = 0;
        int i = 1;
        final int n12 = n - 1;
        while (i <= n) {
            if (i > 1) {
                array2[i - 1 - 1 + n3] = 0.0;
            }
            int n13 = 0;
            Label_0689: {
                if (i <= n12) {
                    n13 = i;
                    for (int j = n12 - i + 1; j > 0; --j) {
                        final double abs = Math.abs(array2[n13 - 1 + n3]);
                        if (abs == 0.0) {
                            break Label_0689;
                        }
                        if (abs <= Math.sqrt(Math.abs(array[n13 - 1 + n2])) * Math.sqrt(Math.abs(array[n13 + 1 - 1 + n2])) * dlamch) {
                            array2[n13 - 1 + n3] = 0.0;
                            break Label_0689;
                        }
                        ++n13;
                    }
                }
                n13 = n;
            }
            final int n15;
            int n14 = n15 = i;
            final int n17;
            int n16 = n17 = n13;
            i = n13 + 1;
            if (n16 == n14) {
                continue;
            }
            final double dlanst = Dlanst.dlanst("I", n16 - n14 + 1, array, n14 - 1 + n2, array2, n14 - 1 + n3);
            int n18 = 0;
            if (dlanst == 0.0) {
                continue;
            }
            if (dlanst > n8) {
                n18 = 1;
                Dlascl.dlascl("G", 0, 0, dlanst, n8, n16 - n14 + 1, 1, array, n14 - 1 + n2, n, intW);
                Dlascl.dlascl("G", 0, 0, dlanst, n8, n16 - n14, 1, array2, n14 - 1 + n3, n, intW);
            }
            else if (dlanst < n9) {
                n18 = 2;
                Dlascl.dlascl("G", 0, 0, dlanst, n9, n16 - n14 + 1, 1, array, n14 - 1 + n2, n, intW);
                Dlascl.dlascl("G", 0, 0, dlanst, n9, n16 - n14, 1, array2, n14 - 1 + n3, n, intW);
            }
            if (Math.abs(array[n16 - 1 + n2]) < Math.abs(array[n14 - 1 + n2])) {
                n16 = n15;
                n14 = n17;
            }
            if (n16 > n14) {
                while (true) {
                    int n20 = 0;
                    Label_1120: {
                        if (n14 != n16) {
                            final int n19 = n16 - 1;
                            n20 = n14;
                            for (int k = n19 - n14 + 1; k > 0; --k) {
                                if (Math.pow(Math.abs(array2[n20 - 1 + n3]), 2) <= pow * Math.abs(array[n20 - 1 + n2]) * Math.abs(array[n20 + 1 - 1 + n2]) + dlamch2) {
                                    break Label_1120;
                                }
                                ++n20;
                            }
                        }
                        n20 = n16;
                    }
                    if (n20 < n16) {
                        array2[n20 - 1 + n3] = 0.0;
                    }
                    final double n21 = array[n14 - 1 + n2];
                    if (n20 == n14) {
                        array[n14 - 1 + n2] = n21;
                        ++n14;
                        if (n14 <= n16) {
                            continue;
                        }
                        break;
                    }
                    else if (n20 == n14 + 1) {
                        if (n7 > 0) {
                            Dlaev2.dlaev2(array[n14 - 1 + n2], array2[n14 - 1 + n3], array[n14 + 1 - 1 + n2], doubleW3, doubleW4, doubleW, doubleW5);
                            array4[n14 - 1 + n6] = doubleW.val;
                            array4[n - 1 + n14 - 1 + n6] = doubleW5.val;
                            Dlasr.dlasr("R", "V", "B", n, 2, array4, n14 - 1 + n6, array4, n - 1 + n14 - 1 + n6, array3, 1 - 1 + (n14 - 1) * n5 + n4, n5);
                        }
                        else {
                            Dlae2.dlae2(array[n14 - 1 + n2], array2[n14 - 1 + n3], array[n14 + 1 - 1 + n2], doubleW3, doubleW4);
                        }
                        array[n14 - 1 + n2] = doubleW3.val;
                        array[n14 + 1 - 1 + n2] = doubleW4.val;
                        array2[n14 - 1 + n3] = 0.0;
                        n14 += 2;
                        if (n14 <= n16) {
                            continue;
                        }
                        break;
                    }
                    else {
                        if (n11 == n10) {
                            break;
                        }
                        ++n11;
                        final double n22 = (array[n14 + 1 - 1 + n2] - n21) / (2.0 * array2[n14 - 1 + n3]);
                        doubleW2.val = Dlapy2.dlapy2(n22, 1.0);
                        double n23 = array[n20 - 1 + n2] - n21 + array2[n14 - 1 + n3] / (n22 + Util.dsign(doubleW2.val, n22));
                        doubleW5.val = 1.0;
                        doubleW.val = 1.0;
                        double n24 = 0.0;
                        int n25;
                        for (int l = (n14 - (n25 = n20 - 1) - 1) / -1; l > 0; --l) {
                            final double n26 = doubleW5.val * array2[n25 - 1 + n3];
                            final double n27 = doubleW.val * array2[n25 - 1 + n3];
                            Dlartg.dlartg(n23, n26, doubleW, doubleW5, doubleW2);
                            if (n25 != n20 - 1) {
                                array2[n25 + 1 - 1 + n3] = doubleW2.val;
                            }
                            final double n28 = array[n25 + 1 - 1 + n2] - n24;
                            doubleW2.val = (array[n25 - 1 + n2] - n28) * doubleW5.val + 2.0 * doubleW.val * n27;
                            n24 = doubleW5.val * doubleW2.val;
                            array[n25 + 1 - 1 + n2] = n28 + n24;
                            n23 = doubleW.val * doubleW2.val - n27;
                            if (n7 > 0) {
                                array4[n25 - 1 + n6] = doubleW.val;
                                array4[n - 1 + n25 - 1 + n6] = -doubleW5.val;
                            }
                            --n25;
                        }
                        if (n7 > 0) {
                            Dlasr.dlasr("R", "V", "B", n, n20 - n14 + 1, array4, n14 - 1 + n6, array4, n - 1 + n14 - 1 + n6, array3, 1 - 1 + (n14 - 1) * n5 + n4, n5);
                        }
                        array[n14 - 1 + n2] -= n24;
                        array2[n14 - 1 + n3] = n23;
                    }
                }
            }
            else {
                while (true) {
                    int n30 = 0;
                    Label_2091: {
                        if (n14 != n16) {
                            final int n29 = n16 + 1;
                            n30 = n14;
                            for (int n31 = (n29 - n14 - 1) / -1; n31 > 0; --n31) {
                                if (Math.pow(Math.abs(array2[n30 - 1 - 1 + n3]), 2) <= pow * Math.abs(array[n30 - 1 + n2]) * Math.abs(array[n30 - 1 - 1 + n2]) + dlamch2) {
                                    break Label_2091;
                                }
                                --n30;
                            }
                        }
                        n30 = n16;
                    }
                    if (n30 > n16) {
                        array2[n30 - 1 - 1 + n3] = 0.0;
                    }
                    final double n32 = array[n14 - 1 + n2];
                    if (n30 == n14) {
                        array[n14 - 1 + n2] = n32;
                        --n14;
                        if (n14 >= n16) {
                            continue;
                        }
                        break;
                    }
                    else if (n30 == n14 - 1) {
                        if (n7 > 0) {
                            Dlaev2.dlaev2(array[n14 - 1 - 1 + n2], array2[n14 - 1 - 1 + n3], array[n14 - 1 + n2], doubleW3, doubleW4, doubleW, doubleW5);
                            array4[n30 - 1 + n6] = doubleW.val;
                            array4[n - 1 + n30 - 1 + n6] = doubleW5.val;
                            Dlasr.dlasr("R", "V", "F", n, 2, array4, n30 - 1 + n6, array4, n - 1 + n30 - 1 + n6, array3, 1 - 1 + (n14 - 1 - 1) * n5 + n4, n5);
                        }
                        else {
                            Dlae2.dlae2(array[n14 - 1 - 1 + n2], array2[n14 - 1 - 1 + n3], array[n14 - 1 + n2], doubleW3, doubleW4);
                        }
                        array[n14 - 1 - 1 + n2] = doubleW3.val;
                        array[n14 - 1 + n2] = doubleW4.val;
                        array2[n14 - 1 - 1 + n3] = 0.0;
                        n14 -= 2;
                        if (n14 >= n16) {
                            continue;
                        }
                        break;
                    }
                    else {
                        if (n11 == n10) {
                            break;
                        }
                        ++n11;
                        final double n33 = (array[n14 - 1 - 1 + n2] - n32) / (2.0 * array2[n14 - 1 - 1 + n3]);
                        doubleW2.val = Dlapy2.dlapy2(n33, 1.0);
                        double n34 = array[n30 - 1 + n2] - n32 + array2[n14 - 1 - 1 + n3] / (n33 + Util.dsign(doubleW2.val, n33));
                        doubleW5.val = 1.0;
                        doubleW.val = 1.0;
                        double n35 = 0.0;
                        final int n36 = n14 - 1;
                        int n37 = n30;
                        for (int n38 = n36 - n30 + 1; n38 > 0; --n38) {
                            final double n39 = doubleW5.val * array2[n37 - 1 + n3];
                            final double n40 = doubleW.val * array2[n37 - 1 + n3];
                            Dlartg.dlartg(n34, n39, doubleW, doubleW5, doubleW2);
                            if (n37 != n30) {
                                array2[n37 - 1 - 1 + n3] = doubleW2.val;
                            }
                            final double n41 = array[n37 - 1 + n2] - n35;
                            doubleW2.val = (array[n37 + 1 - 1 + n2] - n41) * doubleW5.val + 2.0 * doubleW.val * n40;
                            n35 = doubleW5.val * doubleW2.val;
                            array[n37 - 1 + n2] = n41 + n35;
                            n34 = doubleW.val * doubleW2.val - n40;
                            if (n7 > 0) {
                                array4[n37 - 1 + n6] = doubleW.val;
                                array4[n - 1 + n37 - 1 + n6] = doubleW5.val;
                            }
                            ++n37;
                        }
                        if (n7 > 0) {
                            Dlasr.dlasr("R", "V", "F", n, n14 - n30 + 1, array4, n30 - 1 + n6, array4, n - 1 + n30 - 1 + n6, array3, 1 - 1 + (n30 - 1) * n5 + n4, n5);
                        }
                        array[n14 - 1 + n2] -= n35;
                        array2[n36 - 1 + n3] = n34;
                    }
                }
            }
            if (n18 == 1) {
                Dlascl.dlascl("G", 0, 0, n8, dlanst, n17 - n15 + 1, 1, array, n15 - 1 + n2, n, intW);
                Dlascl.dlascl("G", 0, 0, n8, dlanst, n17 - n15, 1, array2, n15 - 1 + n3, n, intW);
            }
            else if (n18 == 2) {
                Dlascl.dlascl("G", 0, 0, n9, dlanst, n17 - n15 + 1, 1, array, n15 - 1 + n2, n, intW);
                Dlascl.dlascl("G", 0, 0, n9, dlanst, n17 - n15, 1, array2, n15 - 1 + n3, n, intW);
            }
            if (n11 < n10) {
                continue;
            }
            int n42 = 1;
            for (int n43 = n - 1 - 1 + 1; n43 > 0; --n43) {
                if (array2[n42 - 1 + n3] != 0.0) {
                    ++intW.val;
                }
                ++n42;
            }
            return;
        }
        if (n7 == 0) {
            Dlasrt.dlasrt("I", n, array, n2, intW);
            return;
        }
        int n44 = 2;
        for (int n45 = n - 2 + 1; n45 > 0; --n45) {
            int n47;
            final int n46 = n47 = n44 - 1;
            double n48 = array[n46 - 1 + n2];
            int n49 = n44;
            for (int n50 = n - n44 + 1; n50 > 0; --n50) {
                if (array[n49 - 1 + n2] < n48) {
                    n47 = n49;
                    n48 = array[n49 - 1 + n2];
                }
                ++n49;
            }
            if (n47 != n46) {
                array[n47 - 1 + n2] = array[n46 - 1 + n2];
                array[n46 - 1 + n2] = n48;
                Dswap.dswap(n, array3, 1 - 1 + (n46 - 1) * n5 + n4, 1, array3, 1 - 1 + (n47 - 1) * n5 + n4, 1);
            }
            ++n44;
        }
    }
}
