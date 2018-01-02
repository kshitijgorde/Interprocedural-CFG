// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.util.doubleW;
import org.netlib.blas.Daxpy;
import org.netlib.blas.Ddot;
import org.netlib.blas.Dscal;
import org.netlib.blas.Dsymv;
import org.netlib.blas.Dgemv;

public final class Dlatrd
{
    public static void dlatrd(final String s, final int n, final int n2, final double[] array, final int n3, final int n4, final double[] array2, final int n5, final double[] array3, final int n6, final double[] array4, final int n7, final int n8) {
        if (n <= 0) {
            return;
        }
        if (Lsame.lsame(s, "U")) {
            int n9 = n;
            for (int i = (n - n2 + 1 - n - 1) / -1; i > 0; --i) {
                final int n10 = n9 - n + n2;
                if (n9 < n) {
                    Dgemv.dgemv("No transpose", n9, n - n9, -1.0, array, 1 - 1 + (n9 + 1 - 1) * n4 + n3, n4, array4, n9 - 1 + (n10 + 1 - 1) * n8 + n7, n8, 1.0, array, 1 - 1 + (n9 - 1) * n4 + n3, 1);
                    Dgemv.dgemv("No transpose", n9, n - n9, -1.0, array4, 1 - 1 + (n10 + 1 - 1) * n8 + n7, n8, array, n9 - 1 + (n9 + 1 - 1) * n4 + n3, n4, 1.0, array, 1 - 1 + (n9 - 1) * n4 + n3, 1);
                }
                if (n9 > 1) {
                    dlarfg_adapter(n9 - 1, array, n9 - 1 - 1 + (n9 - 1) * n4 + n3, array, 1 - 1 + (n9 - 1) * n4 + n3, 1, array3, n9 - 1 - 1 + n6);
                    array2[n9 - 1 - 1 + n5] = array[n9 - 1 - 1 + (n9 - 1) * n4 + n3];
                    Dsymv.dsymv("Upper", n9 - 1, array[n9 - 1 - 1 + (n9 - 1) * n4 + n3] = 1.0, array, n3, n4, array, 1 - 1 + (n9 - 1) * n4 + n3, 1, 0.0, array4, 1 - 1 + (n10 - 1) * n8 + n7, 1);
                    if (n9 < n) {
                        Dgemv.dgemv("Transpose", n9 - 1, n - n9, 1.0, array4, 1 - 1 + (n10 + 1 - 1) * n8 + n7, n8, array, 1 - 1 + (n9 - 1) * n4 + n3, 1, 0.0, array4, n9 + 1 - 1 + (n10 - 1) * n8 + n7, 1);
                        Dgemv.dgemv("No transpose", n9 - 1, n - n9, -1.0, array, 1 - 1 + (n9 + 1 - 1) * n4 + n3, n4, array4, n9 + 1 - 1 + (n10 - 1) * n8 + n7, 1, 1.0, array4, 1 - 1 + (n10 - 1) * n8 + n7, 1);
                        Dgemv.dgemv("Transpose", n9 - 1, n - n9, 1.0, array, 1 - 1 + (n9 + 1 - 1) * n4 + n3, n4, array, 1 - 1 + (n9 - 1) * n4 + n3, 1, 0.0, array4, n9 + 1 - 1 + (n10 - 1) * n8 + n7, 1);
                        Dgemv.dgemv("No transpose", n9 - 1, n - n9, -1.0, array4, 1 - 1 + (n10 + 1 - 1) * n8 + n7, n8, array4, n9 + 1 - 1 + (n10 - 1) * n8 + n7, 1, 1.0, array4, 1 - 1 + (n10 - 1) * n8 + n7, 1);
                    }
                    Dscal.dscal(n9 - 1, array3[n9 - 1 - 1 + n6], array4, 1 - 1 + (n10 - 1) * n8 + n7, 1);
                    Daxpy.daxpy(n9 - 1, -(0.5 * array3[n9 - 1 - 1 + n6] * Ddot.ddot(n9 - 1, array4, 1 - 1 + (n10 - 1) * n8 + n7, 1, array, 1 - 1 + (n9 - 1) * n4 + n3, 1)), array, 1 - 1 + (n9 - 1) * n4 + n3, 1, array4, 1 - 1 + (n10 - 1) * n8 + n7, 1);
                }
                --n9;
            }
        }
        else {
            int n11 = 1;
            for (int j = n2 - 1 + 1; j > 0; --j) {
                Dgemv.dgemv("No transpose", n - n11 + 1, n11 - 1, -1.0, array, n11 - 1 + (1 - 1) * n4 + n3, n4, array4, n11 - 1 + (1 - 1) * n8 + n7, n8, 1.0, array, n11 - 1 + (n11 - 1) * n4 + n3, 1);
                Dgemv.dgemv("No transpose", n - n11 + 1, n11 - 1, -1.0, array4, n11 - 1 + (1 - 1) * n8 + n7, n8, array, n11 - 1 + (1 - 1) * n4 + n3, n4, 1.0, array, n11 - 1 + (n11 - 1) * n4 + n3, 1);
                if (n11 < n) {
                    dlarfg_adapter(n - n11, array, n11 + 1 - 1 + (n11 - 1) * n4 + n3, array, Math.min(n11 + 2, n) - 1 + (n11 - 1) * n4 + n3, 1, array3, n11 - 1 + n6);
                    array2[n11 - 1 + n5] = array[n11 + 1 - 1 + (n11 - 1) * n4 + n3];
                    Dsymv.dsymv("Lower", n - n11, array[n11 + 1 - 1 + (n11 - 1) * n4 + n3] = 1.0, array, n11 + 1 - 1 + (n11 + 1 - 1) * n4 + n3, n4, array, n11 + 1 - 1 + (n11 - 1) * n4 + n3, 1, 0.0, array4, n11 + 1 - 1 + (n11 - 1) * n8 + n7, 1);
                    Dgemv.dgemv("Transpose", n - n11, n11 - 1, 1.0, array4, n11 + 1 - 1 + (1 - 1) * n8 + n7, n8, array, n11 + 1 - 1 + (n11 - 1) * n4 + n3, 1, 0.0, array4, 1 - 1 + (n11 - 1) * n8 + n7, 1);
                    Dgemv.dgemv("No transpose", n - n11, n11 - 1, -1.0, array, n11 + 1 - 1 + (1 - 1) * n4 + n3, n4, array4, 1 - 1 + (n11 - 1) * n8 + n7, 1, 1.0, array4, n11 + 1 - 1 + (n11 - 1) * n8 + n7, 1);
                    Dgemv.dgemv("Transpose", n - n11, n11 - 1, 1.0, array, n11 + 1 - 1 + (1 - 1) * n4 + n3, n4, array, n11 + 1 - 1 + (n11 - 1) * n4 + n3, 1, 0.0, array4, 1 - 1 + (n11 - 1) * n8 + n7, 1);
                    Dgemv.dgemv("No transpose", n - n11, n11 - 1, -1.0, array4, n11 + 1 - 1 + (1 - 1) * n8 + n7, n8, array4, 1 - 1 + (n11 - 1) * n8 + n7, 1, 1.0, array4, n11 + 1 - 1 + (n11 - 1) * n8 + n7, 1);
                    Dscal.dscal(n - n11, array3[n11 - 1 + n6], array4, n11 + 1 - 1 + (n11 - 1) * n8 + n7, 1);
                    Daxpy.daxpy(n - n11, -(0.5 * array3[n11 - 1 + n6] * Ddot.ddot(n - n11, array4, n11 + 1 - 1 + (n11 - 1) * n8 + n7, 1, array, n11 + 1 - 1 + (n11 - 1) * n4 + n3, 1)), array, n11 + 1 - 1 + (n11 - 1) * n4 + n3, 1, array4, n11 + 1 - 1 + (n11 - 1) * n8 + n7, 1);
                }
                ++n11;
            }
        }
    }
    
    private static void dlarfg_adapter(final int n, final double[] array, final int n2, final double[] array2, final int n3, final int n4, final double[] array3, final int n5) {
        final doubleW doubleW = new doubleW(array[n2]);
        final doubleW doubleW2 = new doubleW(array3[n5]);
        Dlarfg.dlarfg(n, doubleW, array2, n3, n4, doubleW2);
        array[n2] = doubleW.val;
        array3[n5] = doubleW2.val;
    }
}
