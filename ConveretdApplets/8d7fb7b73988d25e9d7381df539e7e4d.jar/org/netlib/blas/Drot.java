// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.blas;

public final class Drot
{
    public static void drot(final int n, final double[] array, final int n2, final int n3, final double[] array2, final int n4, final int n5, final double n6, final double n7) {
        if (n <= 0) {
            return;
        }
        if (n3 == 1 && n5 == 1) {
            int n8 = 1;
            for (int i = n - 1 + 1; i > 0; --i) {
                final double n9 = n6 * array[n8 - 1 + n2] + n7 * array2[n8 - 1 + n4];
                array2[n8 - 1 + n4] = n6 * array2[n8 - 1 + n4] - n7 * array[n8 - 1 + n2];
                array[n8 - 1 + n2] = n9;
                ++n8;
            }
            return;
        }
        int n10 = 1;
        int n11 = 1;
        if (n3 < 0) {
            n10 = (-n + 1) * n3 + 1;
        }
        if (n5 < 0) {
            n11 = (-n + 1) * n5 + 1;
        }
        int n12 = 1;
        for (int j = n - 1 + 1; j > 0; --j) {
            final double n13 = n6 * array[n10 - 1 + n2] + n7 * array2[n11 - 1 + n4];
            array2[n11 - 1 + n4] = n6 * array2[n11 - 1 + n4] - n7 * array[n10 - 1 + n2];
            array[n10 - 1 + n2] = n13;
            n10 += n3;
            n11 += n5;
            ++n12;
        }
    }
}
