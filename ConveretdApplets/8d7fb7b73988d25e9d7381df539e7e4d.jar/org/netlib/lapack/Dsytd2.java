// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.blas.Dsyr2;
import org.netlib.blas.Daxpy;
import org.netlib.blas.Ddot;
import org.netlib.blas.Dsymv;
import org.netlib.err.Xerbla;
import org.netlib.util.doubleW;
import org.netlib.util.intW;

public final class Dsytd2
{
    public static void dsytd2(final String s, final int n, final double[] array, final int n2, final int n3, final double[] array2, final int n4, final double[] array3, final int n5, final double[] array4, final int n6, final intW intW) {
        final doubleW doubleW = new doubleW(0.0);
        intW.val = 0;
        final boolean lsame = Lsame.lsame(s, "U");
        if ((lsame ^ true) && (Lsame.lsame(s, "L") ^ true)) {
            intW.val = -1;
        }
        else if (n < 0) {
            intW.val = -2;
        }
        else if (n3 < Math.max(1, n)) {
            intW.val = -4;
        }
        if (intW.val != 0) {
            Xerbla.xerbla("DSYTD2", -intW.val);
            return;
        }
        if (n <= 0) {
            return;
        }
        if (lsame) {
            int n7 = n - 1;
            for (int i = (1 - (n - 1) - 1) / -1; i > 0; --i) {
                dlarfg_adapter(n7, array, n7 - 1 + (n7 + 1 - 1) * n3 + n2, array, 1 - 1 + (n7 + 1 - 1) * n3 + n2, 1, doubleW);
                array3[n7 - 1 + n5] = array[n7 - 1 + (n7 + 1 - 1) * n3 + n2];
                if (doubleW.val != 0.0) {
                    array[n7 - 1 + (n7 + 1 - 1) * n3 + n2] = 1.0;
                    Dsymv.dsymv(s, n7, doubleW.val, array, n2, n3, array, 1 - 1 + (n7 + 1 - 1) * n3 + n2, 1, 0.0, array4, n6, 1);
                    Daxpy.daxpy(n7, -(0.5 * doubleW.val * Ddot.ddot(n7, array4, n6, 1, array, 1 - 1 + (n7 + 1 - 1) * n3 + n2, 1)), array, 1 - 1 + (n7 + 1 - 1) * n3 + n2, 1, array4, n6, 1);
                    Dsyr2.dsyr2(s, n7, -1.0, array, 1 - 1 + (n7 + 1 - 1) * n3 + n2, 1, array4, n6, 1, array, n2, n3);
                    array[n7 - 1 + (n7 + 1 - 1) * n3 + n2] = array3[n7 - 1 + n5];
                }
                array2[n7 + 1 - 1 + n4] = array[n7 + 1 - 1 + (n7 + 1 - 1) * n3 + n2];
                array4[n7 - 1 + n6] = doubleW.val;
                --n7;
            }
            array2[1 - 1 + n4] = array[1 - 1 + (1 - 1) * n3 + n2];
        }
        else {
            int n8 = 1;
            for (int j = n - 1 - 1 + 1; j > 0; --j) {
                dlarfg_adapter(n - n8, array, n8 + 1 - 1 + (n8 - 1) * n3 + n2, array, Math.min(n8 + 2, n) - 1 + (n8 - 1) * n3 + n2, 1, doubleW);
                array3[n8 - 1 + n5] = array[n8 + 1 - 1 + (n8 - 1) * n3 + n2];
                if (doubleW.val != 0.0) {
                    array[n8 + 1 - 1 + (n8 - 1) * n3 + n2] = 1.0;
                    Dsymv.dsymv(s, n - n8, doubleW.val, array, n8 + 1 - 1 + (n8 + 1 - 1) * n3 + n2, n3, array, n8 + 1 - 1 + (n8 - 1) * n3 + n2, 1, 0.0, array4, n8 - 1 + n6, 1);
                    Daxpy.daxpy(n - n8, -(0.5 * doubleW.val * Ddot.ddot(n - n8, array4, n8 - 1 + n6, 1, array, n8 + 1 - 1 + (n8 - 1) * n3 + n2, 1)), array, n8 + 1 - 1 + (n8 - 1) * n3 + n2, 1, array4, n8 - 1 + n6, 1);
                    Dsyr2.dsyr2(s, n - n8, -1.0, array, n8 + 1 - 1 + (n8 - 1) * n3 + n2, 1, array4, n8 - 1 + n6, 1, array, n8 + 1 - 1 + (n8 + 1 - 1) * n3 + n2, n3);
                    array[n8 + 1 - 1 + (n8 - 1) * n3 + n2] = array3[n8 - 1 + n5];
                }
                array2[n8 - 1 + n4] = array[n8 - 1 + (n8 - 1) * n3 + n2];
                array4[n8 - 1 + n6] = doubleW.val;
                ++n8;
            }
            array2[n - 1 + n4] = array[n - 1 + (n - 1) * n3 + n2];
        }
    }
    
    private static void dlarfg_adapter(final int n, final double[] array, final int n2, final double[] array2, final int n3, final int n4, final doubleW doubleW) {
        final doubleW doubleW2 = new doubleW(array[n2]);
        Dlarfg.dlarfg(n, doubleW2, array2, n3, n4, doubleW);
        array[n2] = doubleW2.val;
    }
}
