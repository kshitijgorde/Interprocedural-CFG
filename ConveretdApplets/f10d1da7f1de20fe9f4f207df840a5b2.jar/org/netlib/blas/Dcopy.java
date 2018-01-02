// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.blas;

public final class Dcopy
{
    public static void dcopy(final int n, final double[] array, final int n2, final int n3, final double[] array2, final int n4, final int n5) {
        if (n <= 0) {
            return;
        }
        if (n3 == 1 && n5 == 1) {
            final int n6 = n % 7;
            if (n6 != 0) {
                int n7 = 1;
                for (int i = n6 - 1 + 1; i > 0; --i) {
                    array2[n7 - 1 + n4] = array[n7 - 1 + n2];
                    ++n7;
                }
                if (n < 7) {
                    return;
                }
            }
            int n8;
            for (int j = (n - (n8 = n6 + 1) + 7) / 7; j > 0; --j) {
                array2[n8 - 1 + n4] = array[n8 - 1 + n2];
                array2[n8 + 1 - 1 + n4] = array[n8 + 1 - 1 + n2];
                array2[n8 + 2 - 1 + n4] = array[n8 + 2 - 1 + n2];
                array2[n8 + 3 - 1 + n4] = array[n8 + 3 - 1 + n2];
                array2[n8 + 4 - 1 + n4] = array[n8 + 4 - 1 + n2];
                array2[n8 + 5 - 1 + n4] = array[n8 + 5 - 1 + n2];
                array2[n8 + 6 - 1 + n4] = array[n8 + 6 - 1 + n2];
                n8 += 7;
            }
            return;
        }
        int n9 = 1;
        int n10 = 1;
        if (n3 < 0) {
            n9 = (-n + 1) * n3 + 1;
        }
        if (n5 < 0) {
            n10 = (-n + 1) * n5 + 1;
        }
        int n11 = 1;
        for (int k = n - 1 + 1; k > 0; --k) {
            array2[n10 - 1 + n4] = array[n9 - 1 + n2];
            n9 += n3;
            n10 += n5;
            ++n11;
        }
    }
}
