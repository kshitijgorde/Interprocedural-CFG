// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.blas.Dswap;
import org.netlib.blas.Dgemm;
import org.netlib.err.Xerbla;
import org.netlib.util.intW;

public final class Dstedc
{
    public static void dstedc(final String s, final int n, final double[] array, final int n2, final double[] array2, final int n3, final double[] array3, final int n4, final int n5, final double[] array4, final int n6, final int n7, final int[] array5, final int n8, final int n9, final intW intW) {
        int n10 = 0;
        int n11 = 0;
        int n12 = 0;
        intW.val = 0;
        final boolean b = n7 == -1 || n9 == -1;
        int n13;
        if (Lsame.lsame(s, "N")) {
            n13 = 0;
        }
        else if (Lsame.lsame(s, "V")) {
            n13 = 1;
        }
        else if (Lsame.lsame(s, "I")) {
            n13 = 2;
        }
        else {
            n13 = -1;
        }
        if (n <= 1 || n13 <= 0) {
            n10 = 1;
            n11 = 1;
        }
        else {
            int n14 = (int)(Math.log(n) / Math.log(2.0));
            if (Math.pow(2, n14) < n) {
                ++n14;
            }
            if (Math.pow(2, n14) < n) {
                ++n14;
            }
            if (n13 == 1) {
                n11 = (int)(1 + 3 * n + 2 * n * n14 + 3 * Math.pow(n, 2));
                n10 = 6 + 6 * n + 5 * n * n14;
            }
            else if (n13 == 2) {
                n11 = (int)(1 + 4 * n + Math.pow(n, 2));
                n10 = 3 + 5 * n;
            }
        }
        if (n13 < 0) {
            intW.val = -1;
        }
        else if (n < 0) {
            intW.val = -2;
        }
        else if (n5 < 1 || (n13 > 0 && n5 < Math.max(1, n))) {
            intW.val = -6;
        }
        else if (n7 < n11 && (b ^ true)) {
            intW.val = -8;
        }
        else if (n9 < n10 && (b ^ true)) {
            intW.val = -10;
        }
        if (intW.val == 0) {
            array4[1 - 1 + n6] = n11;
            array5[1 - 1 + n8] = n10;
        }
        if (intW.val != 0) {
            Xerbla.xerbla("DSTEDC", -intW.val);
            return;
        }
        if (b) {
            return;
        }
        if (n == 0) {
            return;
        }
        if (n == 1) {
            if (n13 != 0) {
                array3[1 - 1 + (1 - 1) * n5 + n4] = 1.0;
            }
            return;
        }
        final int ilaenv = Ilaenv.ilaenv(9, "DSTEDC", " ", 0, 0, 0, 0);
        if (n13 == 0) {
            Dsterf.dsterf(n, array, n2, array2, n3, intW);
            return;
        }
        if (n <= ilaenv) {
            if (n13 == 0) {
                Dsterf.dsterf(n, array, n2, array2, n3, intW);
                return;
            }
            if (n13 == 2) {
                Dsteqr.dsteqr("I", n, array, n2, array2, n3, array3, n4, n5, array4, n6, intW);
                return;
            }
            Dsteqr.dsteqr("V", n, array, n2, array2, n3, array3, n4, n5, array4, n6, intW);
        }
        else {
            int n15;
            if (n13 == 1) {
                n15 = 1 + n * n;
            }
            else {
                n15 = 1;
            }
            if (n13 == 2) {
                Dlaset.dlaset("Full", n, n, 0.0, 1.0, array3, n4, n5);
            }
            if (Dlanst.dlanst("M", n, array, n2, array2, n3) == 0.0) {
                return;
            }
            final double dlamch = Dlamch.dlamch("Epsilon");
            int n16;
            for (int i = 1; i <= n; i = n16 + 1) {
                for (n16 = i; n16 < n && Math.abs(array2[n16 - 1 + n3]) > dlamch * Math.sqrt(Math.abs(array[n16 - 1 + n2])) * Math.sqrt(Math.abs(array[n16 + 1 - 1 + n2])); ++n16) {}
                n12 = n16 - i + 1;
                if (n12 != 1) {
                    if (n12 > ilaenv) {
                        intW.val = ilaenv;
                        final double dlanst = Dlanst.dlanst("M", n12, array, i - 1 + n2, array2, i - 1 + n3);
                        Dlascl.dlascl("G", 0, 0, dlanst, 1.0, n12, 1, array, i - 1 + n2, n12, intW);
                        Dlascl.dlascl("G", 0, 0, dlanst, 1.0, n12 - 1, 1, array2, i - 1 + n3, n12 - 1, intW);
                        int n17;
                        if (n13 == 1) {
                            n17 = 1;
                        }
                        else {
                            n17 = i;
                        }
                        Dlaed0.dlaed0(n13, n, n12, array, i - 1 + n2, array2, i - 1 + n3, array3, n17 - 1 + (i - 1) * n5 + n4, n5, array4, 1 - 1 + n6, n, array4, n15 - 1 + n6, array5, n8, intW);
                        if (intW.val != 0) {
                            intW.val = (intW.val / (n12 + 1) + i - 1) * (n + 1) + intW.val % (n12 + 1) + i - 1;
                            return;
                        }
                        Dlascl.dlascl("G", 0, 0, 1.0, dlanst, n12, 1, array, i - 1 + n2, n12, intW);
                    }
                    else {
                        if (n13 == 1) {
                            Dsteqr.dsteqr("I", n12, array, i - 1 + n2, array2, i - 1 + n3, array4, n6, n12, array4, n12 * n12 + 1 - 1 + n6, intW);
                            Dlacpy.dlacpy("A", n, n12, array3, 1 - 1 + (i - 1) * n5 + n4, n5, array4, n15 - 1 + n6, n);
                            Dgemm.dgemm("N", "N", n, n12, n12, 1.0, array4, n15 - 1 + n6, n5, array4, n6, n12, 0.0, array3, 1 - 1 + (i - 1) * n5 + n4, n5);
                        }
                        else if (n13 == 2) {
                            Dsteqr.dsteqr("I", n12, array, i - 1 + n2, array2, i - 1 + n3, array3, i - 1 + (i - 1) * n5 + n4, n5, array4, n6, intW);
                        }
                        else {
                            Dsterf.dsterf(n12, array, i - 1 + n2, array2, i - 1 + n3, intW);
                        }
                        if (intW.val != 0) {
                            intW.val = i * (n + 1) + n16;
                            return;
                        }
                    }
                }
            }
            if (n12 != n) {
                if (n13 == 0) {
                    Dlasrt.dlasrt("I", n, array, n2, intW);
                }
                else {
                    int n18 = 2;
                    for (int j = n - 2 + 1; j > 0; --j) {
                        int n20;
                        final int n19 = n20 = n18 - 1;
                        double n21 = array[n19 - 1 + n2];
                        int n22 = n18;
                        for (int k = n - n18 + 1; k > 0; --k) {
                            if (array[n22 - 1 + n2] < n21) {
                                n20 = n22;
                                n21 = array[n22 - 1 + n2];
                            }
                            ++n22;
                        }
                        if (n20 != n19) {
                            array[n20 - 1 + n2] = array[n19 - 1 + n2];
                            array[n19 - 1 + n2] = n21;
                            Dswap.dswap(n, array3, 1 - 1 + (n19 - 1) * n5 + n4, 1, array3, 1 - 1 + (n20 - 1) * n5 + n4, 1);
                        }
                        ++n18;
                    }
                }
            }
            array4[1 - 1 + n6] = n11;
            array5[1 - 1 + n8] = n10;
        }
    }
}
