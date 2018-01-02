// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.blas;

public final class Daxpy
{
    public static void daxpy(final int n, final double n2, final double[] array, final int n3, final int n4, final double[] array2, final int n5, final int n6) {
        if (n <= 0) {
            return;
        }
        if (n2 == 0.0) {
            return;
        }
        if (n4 == 1 && n6 == 1) {
            final int n7 = n % 4;
            if (n7 != 0) {
                int n8 = 1;
                for (int i = n7 - 1 + 1; i > 0; --i) {
                    array2[n8 - 1 + n5] += n2 * array[n8 - 1 + n3];
                    ++n8;
                }
                if (n < 4) {
                    return;
                }
            }
            int n9;
            for (int j = (n - (n9 = n7 + 1) + 4) / 4; j > 0; --j) {
                array2[n9 - 1 + n5] += n2 * array[n9 - 1 + n3];
                array2[n9 + 1 - 1 + n5] += n2 * array[n9 + 1 - 1 + n3];
                array2[n9 + 2 - 1 + n5] += n2 * array[n9 + 2 - 1 + n3];
                array2[n9 + 3 - 1 + n5] += n2 * array[n9 + 3 - 1 + n3];
                n9 += 4;
            }
            return;
        }
        int n10 = 1;
        int n11 = 1;
        if (n4 < 0) {
            n10 = (-n + 1) * n4 + 1;
        }
        if (n6 < 0) {
            n11 = (-n + 1) * n6 + 1;
        }
        int n12 = 1;
        for (int k = n - 1 + 1; k > 0; --k) {
            array2[n11 - 1 + n5] += n2 * array[n10 - 1 + n3];
            n10 += n4;
            n11 += n6;
            ++n12;
        }
    }
}
