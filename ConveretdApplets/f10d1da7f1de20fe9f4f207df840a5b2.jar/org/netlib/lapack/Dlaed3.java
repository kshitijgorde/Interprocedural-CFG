// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.util.doubleW;
import org.netlib.blas.Dgemm;
import org.netlib.blas.Dnrm2;
import org.netlib.util.Util;
import org.netlib.blas.Dcopy;
import org.netlib.err.Xerbla;
import org.netlib.util.intW;

public final class Dlaed3
{
    public static void dlaed3(final int n, final int n2, final int n3, final double[] array, final int n4, final double[] array2, final int n5, final int n6, final double n7, final double[] array3, final int n8, final double[] array4, final int n9, final int[] array5, final int n10, final int[] array6, final int n11, final double[] array7, final int n12, final double[] array8, final int n13, final intW intW) {
        intW.val = 0;
        if (n < 0) {
            intW.val = -1;
        }
        else if (n2 < n) {
            intW.val = -2;
        }
        else if (n6 < Math.max(1, n2)) {
            intW.val = -6;
        }
        if (intW.val != 0) {
            Xerbla.xerbla("DLAED3", -intW.val);
            return;
        }
        if (n == 0) {
            return;
        }
        int n14 = 1;
        for (int i = n - 1 + 1; i > 0; --i) {
            array3[n14 - 1 + n8] = Dlamc3.dlamc3(array3[n14 - 1 + n8], array3[n14 - 1 + n8]) - array3[n14 - 1 + n8];
            ++n14;
        }
        int n15 = 1;
        for (int j = n - 1 + 1; j > 0; --j) {
            dlaed4_adapter(n, n15, array3, n8, array7, n12, array2, 1 - 1 + (n15 - 1) * n6 + n5, n7, array, n15 - 1 + n4, intW);
            if (intW.val != 0) {
                return;
            }
            ++n15;
        }
        if (n != 1) {
            if (n == 2) {
                int n16 = 1;
                for (int k = n - 1 + 1; k > 0; --k) {
                    array7[1 - 1 + n12] = array2[1 - 1 + (n16 - 1) * n6 + n5];
                    array7[2 - 1 + n12] = array2[2 - 1 + (n16 - 1) * n6 + n5];
                    array2[1 - 1 + (n16 - 1) * n6 + n5] = array7[array5[1 - 1 + n10] - 1 + n12];
                    array2[2 - 1 + (n16 - 1) * n6 + n5] = array7[array5[2 - 1 + n10] - 1 + n12];
                    ++n16;
                }
            }
            else {
                Dcopy.dcopy(n, array7, n12, 1, array8, n13, 1);
                Dcopy.dcopy(n, array2, n5, n6 + 1, array7, n12, 1);
                int n17 = 1;
                for (int l = n - 1 + 1; l > 0; --l) {
                    int n18 = 1;
                    for (int n19 = n17 - 1 - 1 + 1; n19 > 0; --n19) {
                        array7[n18 - 1 + n12] *= array2[n18 - 1 + (n17 - 1) * n6 + n5] / (array3[n18 - 1 + n8] - array3[n17 - 1 + n8]);
                        ++n18;
                    }
                    int n20 = n17 + 1;
                    for (int n21 = n - (n17 + 1) + 1; n21 > 0; --n21) {
                        array7[n20 - 1 + n12] *= array2[n20 - 1 + (n17 - 1) * n6 + n5] / (array3[n20 - 1 + n8] - array3[n17 - 1 + n8]);
                        ++n20;
                    }
                    ++n17;
                }
                int n22 = 1;
                for (int n23 = n - 1 + 1; n23 > 0; --n23) {
                    array7[n22 - 1 + n12] = Util.dsign(Math.sqrt(-array7[n22 - 1 + n12]), array8[n22 - 1 + n13]);
                    ++n22;
                }
                int n24 = 1;
                for (int n25 = n - 1 + 1; n25 > 0; --n25) {
                    int n26 = 1;
                    for (int n27 = n - 1 + 1; n27 > 0; --n27) {
                        array8[n26 - 1 + n13] = array7[n26 - 1 + n12] / array2[n26 - 1 + (n24 - 1) * n6 + n5];
                        ++n26;
                    }
                    final double dnrm2 = Dnrm2.dnrm2(n, array8, n13, 1);
                    int n28 = 1;
                    for (int n29 = n - 1 + 1; n29 > 0; --n29) {
                        array2[n28 - 1 + (n24 - 1) * n6 + n5] = array8[array5[n28 - 1 + n10] - 1 + n13] / dnrm2;
                        ++n28;
                    }
                    ++n24;
                }
            }
        }
        final int n30 = n2 - n3;
        final int n31 = array6[1 - 1 + n11] + array6[2 - 1 + n11];
        final int n32 = array6[2 - 1 + n11] + array6[3 - 1 + n11];
        Dlacpy.dlacpy("A", n32, n, array2, array6[1 - 1 + n11] + 1 - 1 + (1 - 1) * n6 + n5, n6, array8, n13, n32);
        final int n33 = n3 * n31 + 1;
        if (n32 != 0) {
            Dgemm.dgemm("N", "N", n30, n, n32, 1.0, array4, n33 - 1 + n9, n30, array8, n13, n32, 0.0, array2, n3 + 1 - 1 + (1 - 1) * n6 + n5, n6);
        }
        else {
            Dlaset.dlaset("A", n30, n, 0.0, 0.0, array2, n3 + 1 - 1 + (1 - 1) * n6 + n5, n6);
        }
        Dlacpy.dlacpy("A", n31, n, array2, n5, n6, array8, n13, n31);
        if (n31 != 0) {
            Dgemm.dgemm("N", "N", n3, n, n31, 1.0, array4, n9, n3, array8, n13, n31, 0.0, array2, n5, n6);
            return;
        }
        Dlaset.dlaset("A", n3, n, 0.0, 0.0, array2, 1 - 1 + (1 - 1) * n6 + n5, n6);
    }
    
    private static void dlaed4_adapter(final int n, final int n2, final double[] array, final int n3, final double[] array2, final int n4, final double[] array3, final int n5, final double n6, final double[] array4, final int n7, final intW intW) {
        final doubleW doubleW = new doubleW(array4[n7]);
        Dlaed4.dlaed4(n, n2, array, n3, array2, n4, array3, n5, n6, doubleW, intW);
        array4[n7] = doubleW.val;
    }
}
