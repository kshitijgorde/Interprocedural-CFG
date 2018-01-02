// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.blas.Dcopy;
import org.netlib.err.Xerbla;
import org.netlib.util.intW;
import org.netlib.util.doubleW;

public final class Dlaed1
{
    public static void dlaed1(final int n, final double[] array, final int n2, final double[] array2, final int n3, final int n4, final int[] array3, final int n5, final doubleW doubleW, final int n6, final double[] array4, final int n7, final int[] array5, final int n8, final intW intW) {
        final intW intW2 = new intW(0);
        intW.val = 0;
        if (n < 0) {
            intW.val = -1;
        }
        else if (n4 < Math.max(1, n)) {
            intW.val = -4;
        }
        else if (Math.min(1, n / 2) > n6 || n / 2 < n6) {
            intW.val = -7;
        }
        if (intW.val != 0) {
            Xerbla.xerbla("DLAED1", -intW.val);
            return;
        }
        if (n == 0) {
            return;
        }
        final int n9 = 1;
        final int n10 = n9 + n;
        final int n11 = n10 + n;
        final int n12 = n11 + n;
        final int n13 = 1;
        final int n14 = n13 + n;
        final int n15 = n14 + n;
        final int n16 = n15 + n;
        Dcopy.dcopy(n6, array2, n6 - 1 + (1 - 1) * n4 + n3, n4, array4, n9 - 1 + n7, 1);
        final int n17 = n6 + 1;
        Dcopy.dcopy(n - n6, array2, n17 - 1 + (n17 - 1) * n4 + n3, n4, array4, n9 + n6 - 1 + n7, 1);
        Dlaed2.dlaed2(intW2, n, n6, array, n2, array2, n3, n4, array3, n5, doubleW, array4, n9 - 1 + n7, array4, n10 - 1 + n7, array4, n11 - 1 + n7, array4, n12 - 1 + n7, array5, n13 - 1 + n8, array5, n14 - 1 + n8, array5, n16 - 1 + n8, array5, n15 - 1 + n8, intW);
        if (intW.val == 0) {
            if (intW2.val != 0) {
                Dlaed3.dlaed3(intW2.val, n, n6, array, n2, array2, n3, n4, doubleW.val, array4, n10 - 1 + n7, array4, n12 - 1 + n7, array5, n14 - 1 + n8, array5, n15 - 1 + n8, array4, n11 - 1 + n7, array4, (array5[n15 - 1 + n8] + array5[n15 + 1 - 1 + n8]) * n6 + (array5[n15 + 1 - 1 + n8] + array5[n15 + 2 - 1 + n8]) * (n - n6) + n12 - 1 + n7, intW);
                if (intW.val == 0) {
                    Dlamrg.dlamrg(intW2.val, n - intW2.val, array, n2, 1, -1, array3, n5);
                }
            }
            else {
                int n18 = 1;
                for (int i = n - 1 + 1; i > 0; --i) {
                    array3[n18 - 1 + n5] = n18;
                    ++n18;
                }
            }
        }
    }
}
