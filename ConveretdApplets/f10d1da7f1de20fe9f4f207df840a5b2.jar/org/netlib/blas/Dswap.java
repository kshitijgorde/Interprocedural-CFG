// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.blas;

public final class Dswap
{
    public static void dswap(final int n, final double[] array, final int n2, final int n3, final double[] array2, final int n4, final int n5) {
        if (n <= 0) {
            return;
        }
        if (n3 == 1 && n5 == 1) {
            final int n6 = n % 3;
            if (n6 != 0) {
                int n7 = 1;
                for (int i = n6 - 1 + 1; i > 0; --i) {
                    final double n8 = array[n7 - 1 + n2];
                    array[n7 - 1 + n2] = array2[n7 - 1 + n4];
                    array2[n7 - 1 + n4] = n8;
                    ++n7;
                }
                if (n < 3) {
                    return;
                }
            }
            int n9;
            for (int j = (n - (n9 = n6 + 1) + 3) / 3; j > 0; --j) {
                final double n10 = array[n9 - 1 + n2];
                array[n9 - 1 + n2] = array2[n9 - 1 + n4];
                array2[n9 - 1 + n4] = n10;
                final double n11 = array[n9 + 1 - 1 + n2];
                array[n9 + 1 - 1 + n2] = array2[n9 + 1 - 1 + n4];
                array2[n9 + 1 - 1 + n4] = n11;
                final double n12 = array[n9 + 2 - 1 + n2];
                array[n9 + 2 - 1 + n2] = array2[n9 + 2 - 1 + n4];
                array2[n9 + 2 - 1 + n4] = n12;
                n9 += 3;
            }
            return;
        }
        int n13 = 1;
        int n14 = 1;
        if (n3 < 0) {
            n13 = (-n + 1) * n3 + 1;
        }
        if (n5 < 0) {
            n14 = (-n + 1) * n5 + 1;
        }
        int n15 = 1;
        for (int k = n - 1 + 1; k > 0; --k) {
            final double n16 = array[n13 - 1 + n2];
            array[n13 - 1 + n2] = array2[n14 - 1 + n4];
            array2[n14 - 1 + n4] = n16;
            n13 += n3;
            n14 += n5;
            ++n15;
        }
    }
}
