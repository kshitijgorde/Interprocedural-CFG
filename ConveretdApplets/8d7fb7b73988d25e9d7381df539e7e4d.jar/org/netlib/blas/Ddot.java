// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.blas;

public final class Ddot
{
    public static double ddot(final int n, final double[] array, final int n2, final int n3, final double[] array2, final int n4, final int n5) {
        final double n6 = 0.0;
        double n7 = 0.0;
        if (n <= 0) {
            return n6;
        }
        if (n3 == 1 && n5 == 1) {
            final int n8 = n % 5;
            if (n8 != 0) {
                int n9 = 1;
                for (int i = n8 - 1 + 1; i > 0; --i) {
                    n7 += array[n9 - 1 + n2] * array2[n9 - 1 + n4];
                    ++n9;
                }
                if (n < 5) {
                    return n7;
                }
            }
            int n10;
            for (int j = (n - (n10 = n8 + 1) + 5) / 5; j > 0; --j) {
                n7 = n7 + array[n10 - 1 + n2] * array2[n10 - 1 + n4] + array[n10 + 1 - 1 + n2] * array2[n10 + 1 - 1 + n4] + array[n10 + 2 - 1 + n2] * array2[n10 + 2 - 1 + n4] + array[n10 + 3 - 1 + n2] * array2[n10 + 3 - 1 + n4] + array[n10 + 4 - 1 + n2] * array2[n10 + 4 - 1 + n4];
                n10 += 5;
            }
            return n7;
        }
        int n11 = 1;
        int n12 = 1;
        if (n3 < 0) {
            n11 = (-n + 1) * n3 + 1;
        }
        if (n5 < 0) {
            n12 = (-n + 1) * n5 + 1;
        }
        int n13 = 1;
        for (int k = n - 1 + 1; k > 0; --k) {
            n7 += array[n11 - 1 + n2] * array2[n12 - 1 + n4];
            n11 += n3;
            n12 += n5;
            ++n13;
        }
        return n7;
    }
}
