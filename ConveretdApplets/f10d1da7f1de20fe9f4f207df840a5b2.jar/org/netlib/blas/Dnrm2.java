// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.blas;

public final class Dnrm2
{
    public static double dnrm2(final int n, final double[] array, final int n2, final int n3) {
        double abs;
        if (n < 1 || n3 < 1) {
            abs = 0.0;
        }
        else if (n == 1) {
            abs = Math.abs(array[1 - 1 + n2]);
        }
        else {
            double n4 = 0.0;
            double n5 = 1.0;
            int n6 = 1;
            for (int i = (1 + (n - 1) * n3 - 1 + n3) / n3; i > 0; --i) {
                if (array[n6 - 1 + n2] != 0.0) {
                    final double abs2 = Math.abs(array[n6 - 1 + n2]);
                    if (n4 < abs2) {
                        n5 = 1.0 + n5 * Math.pow(n4 / abs2, 2);
                        n4 = abs2;
                    }
                    else {
                        n5 += Math.pow(abs2 / n4, 2);
                    }
                }
                n6 += n3;
            }
            abs = n4 * Math.sqrt(n5);
        }
        return abs;
    }
}
