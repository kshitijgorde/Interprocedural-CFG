// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.util.doubleW;

public final class Dlaed5
{
    public static void dlaed5(final int n, final double[] array, final int n2, final double[] array2, final int n3, final double[] array3, final int n4, final double n5, final doubleW doubleW) {
        final double n6 = array[2 - 1 + n2] - array[1 - 1 + n2];
        if (n == 1) {
            if (1.0 + 2.0 * n5 * (array2[2 - 1 + n3] * array2[2 - 1 + n3] - array2[1 - 1 + n3] * array2[1 - 1 + n3]) / n6 > 0.0) {
                final double n7 = n6 + n5 * (array2[1 - 1 + n3] * array2[1 - 1 + n3] + array2[2 - 1 + n3] * array2[2 - 1 + n3]);
                final double n8 = n5 * array2[1 - 1 + n3] * array2[1 - 1 + n3] * n6;
                final double n9 = 2.0 * n8 / (n7 + Math.sqrt(Math.abs(n7 * n7 - 4.0 * n8)));
                doubleW.val = array[1 - 1 + n2] + n9;
                array3[1 - 1 + n4] = -(array2[1 - 1 + n3] / n9);
                array3[2 - 1 + n4] = array2[2 - 1 + n3] / (n6 - n9);
            }
            else {
                final double n10 = -n6 + n5 * (array2[1 - 1 + n3] * array2[1 - 1 + n3] + array2[2 - 1 + n3] * array2[2 - 1 + n3]);
                final double n11 = n5 * array2[2 - 1 + n3] * array2[2 - 1 + n3] * n6;
                double n12;
                if (n10 > 0.0) {
                    n12 = -(2.0 * n11 / (n10 + Math.sqrt(n10 * n10 + 4.0 * n11)));
                }
                else {
                    n12 = (n10 - Math.sqrt(n10 * n10 + 4.0 * n11)) / 2.0;
                }
                doubleW.val = array[2 - 1 + n2] + n12;
                array3[1 - 1 + n4] = -(array2[1 - 1 + n3] / (n6 + n12));
                array3[2 - 1 + n4] = -(array2[2 - 1 + n3] / n12);
            }
            final double sqrt = Math.sqrt(array3[1 - 1 + n4] * array3[1 - 1 + n4] + array3[2 - 1 + n4] * array3[2 - 1 + n4]);
            array3[1 - 1 + n4] /= sqrt;
            array3[2 - 1 + n4] /= sqrt;
        }
        else {
            final double n13 = -n6 + n5 * (array2[1 - 1 + n3] * array2[1 - 1 + n3] + array2[2 - 1 + n3] * array2[2 - 1 + n3]);
            final double n14 = n5 * array2[2 - 1 + n3] * array2[2 - 1 + n3] * n6;
            double n15;
            if (n13 > 0.0) {
                n15 = (n13 + Math.sqrt(n13 * n13 + 4.0 * n14)) / 2.0;
            }
            else {
                n15 = 2.0 * n14 / (-n13 + Math.sqrt(n13 * n13 + 4.0 * n14));
            }
            doubleW.val = array[2 - 1 + n2] + n15;
            array3[1 - 1 + n4] = -(array2[1 - 1 + n3] / (n6 + n15));
            array3[2 - 1 + n4] = -(array2[2 - 1 + n3] / n15);
            final double sqrt2 = Math.sqrt(array3[1 - 1 + n4] * array3[1 - 1 + n4] + array3[2 - 1 + n4] * array3[2 - 1 + n4]);
            array3[1 - 1 + n4] /= sqrt2;
            array3[2 - 1 + n4] /= sqrt2;
        }
    }
}
