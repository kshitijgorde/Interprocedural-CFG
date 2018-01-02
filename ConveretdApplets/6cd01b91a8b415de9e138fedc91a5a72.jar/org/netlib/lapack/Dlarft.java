// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.blas.Dtrmv;
import org.netlib.blas.Dgemv;

public final class Dlarft
{
    public static void dlarft(final String s, final String s2, final int n, final int n2, final double[] array, final int n3, final int n4, final double[] array2, final int n5, final double[] array3, final int n6, final int n7) {
        if (n == 0) {
            return;
        }
        if (Lsame.lsame(s, "F")) {
            int n8 = 1;
            for (int i = n2 - 1 + 1; i > 0; --i) {
                if (array2[n8 - 1 + n5] == 0.0) {
                    int n9 = 1;
                    for (int j = n8 - 1 + 1; j > 0; --j) {
                        array3[n9 - 1 + (n8 - 1) * n7 + n6] = 0.0;
                        ++n9;
                    }
                }
                else {
                    final double n10 = array[n8 - 1 + (n8 - 1) * n4 + n3];
                    array[n8 - 1 + (n8 - 1) * n4 + n3] = 1.0;
                    if (Lsame.lsame(s2, "C")) {
                        Dgemv.dgemv("Transpose", n - n8 + 1, n8 - 1, -array2[n8 - 1 + n5], array, n8 - 1 + (1 - 1) * n4 + n3, n4, array, n8 - 1 + (n8 - 1) * n4 + n3, 1, 0.0, array3, 1 - 1 + (n8 - 1) * n7 + n6, 1);
                    }
                    else {
                        Dgemv.dgemv("No transpose", n8 - 1, n - n8 + 1, -array2[n8 - 1 + n5], array, 1 - 1 + (n8 - 1) * n4 + n3, n4, array, n8 - 1 + (n8 - 1) * n4 + n3, n4, 0.0, array3, 1 - 1 + (n8 - 1) * n7 + n6, 1);
                    }
                    array[n8 - 1 + (n8 - 1) * n4 + n3] = n10;
                    Dtrmv.dtrmv("Upper", "No transpose", "Non-unit", n8 - 1, array3, n6, n7, array3, 1 - 1 + (n8 - 1) * n7 + n6, 1);
                    array3[n8 - 1 + (n8 - 1) * n7 + n6] = array2[n8 - 1 + n5];
                }
                ++n8;
            }
        }
        else {
            int n11 = n2;
            for (int k = (1 - n2 - 1) / -1; k > 0; --k) {
                if (array2[n11 - 1 + n5] == 0.0) {
                    int n12 = n11;
                    for (int l = n2 - n11 + 1; l > 0; --l) {
                        array3[n12 - 1 + (n11 - 1) * n7 + n6] = 0.0;
                        ++n12;
                    }
                }
                else {
                    if (n11 < n2) {
                        if (Lsame.lsame(s2, "C")) {
                            final double n13 = array[n - n2 + n11 - 1 + (n11 - 1) * n4 + n3];
                            array[n - n2 + n11 - 1 + (n11 - 1) * n4 + n3] = 1.0;
                            Dgemv.dgemv("Transpose", n - n2 + n11, n2 - n11, -array2[n11 - 1 + n5], array, 1 - 1 + (n11 + 1 - 1) * n4 + n3, n4, array, 1 - 1 + (n11 - 1) * n4 + n3, 1, 0.0, array3, n11 + 1 - 1 + (n11 - 1) * n7 + n6, 1);
                            array[n - n2 + n11 - 1 + (n11 - 1) * n4 + n3] = n13;
                        }
                        else {
                            final double n14 = array[n11 - 1 + (n - n2 + n11 - 1) * n4 + n3];
                            array[n11 - 1 + (n - n2 + n11 - 1) * n4 + n3] = 1.0;
                            Dgemv.dgemv("No transpose", n2 - n11, n - n2 + n11, -array2[n11 - 1 + n5], array, n11 + 1 - 1 + (1 - 1) * n4 + n3, n4, array, n11 - 1 + (1 - 1) * n4 + n3, n4, 0.0, array3, n11 + 1 - 1 + (n11 - 1) * n7 + n6, 1);
                            array[n11 - 1 + (n - n2 + n11 - 1) * n4 + n3] = n14;
                        }
                        Dtrmv.dtrmv("Lower", "No transpose", "Non-unit", n2 - n11, array3, n11 + 1 - 1 + (n11 + 1 - 1) * n7 + n6, n7, array3, n11 + 1 - 1 + (n11 - 1) * n7 + n6, 1);
                    }
                    array3[n11 - 1 + (n11 - 1) * n7 + n6] = array2[n11 - 1 + n5];
                }
                --n11;
            }
        }
    }
}
