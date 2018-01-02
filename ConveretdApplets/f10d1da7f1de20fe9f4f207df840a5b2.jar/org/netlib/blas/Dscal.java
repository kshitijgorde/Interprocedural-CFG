// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.blas;

public final class Dscal
{
    public static void dscal(final int n, final double n2, final double[] array, final int n3, final int n4) {
        if (n <= 0 || n4 <= 0) {
            return;
        }
        if (n4 == 1) {
            final int n5 = n % 5;
            if (n5 != 0) {
                int n6 = 1;
                for (int i = n5 - 1 + 1; i > 0; --i) {
                    array[n6 - 1 + n3] *= n2;
                    ++n6;
                }
                if (n < 5) {
                    return;
                }
            }
            int n7;
            for (int j = (n - (n7 = n5 + 1) + 5) / 5; j > 0; --j) {
                array[n7 - 1 + n3] *= n2;
                array[n7 + 1 - 1 + n3] *= n2;
                array[n7 + 2 - 1 + n3] *= n2;
                array[n7 + 3 - 1 + n3] *= n2;
                array[n7 + 4 - 1 + n3] *= n2;
                n7 += 5;
            }
            return;
        }
        final int n8 = n * n4;
        int n9 = 1;
        for (int k = (n8 - 1 + n4) / n4; k > 0; --k) {
            array[n9 - 1 + n3] *= n2;
            n9 += n4;
        }
    }
}
