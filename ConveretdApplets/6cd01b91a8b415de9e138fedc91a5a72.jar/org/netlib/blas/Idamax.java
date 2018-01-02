// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.blas;

public final class Idamax
{
    public static int idamax(final int n, final double[] array, final int n2, final int n3) {
        final boolean b = false;
        if (n < 1 || n3 <= 0) {
            return b ? 1 : 0;
        }
        int n4 = 1;
        if (n == 1) {
            return n4;
        }
        if (n3 == 1) {
            double n5 = Math.abs(array[1 - 1 + n2]);
            int n6 = 2;
            for (int i = n - 2 + 1; i > 0; --i) {
                if (Math.abs(array[n6 - 1 + n2]) > n5) {
                    n4 = n6;
                    n5 = Math.abs(array[n6 - 1 + n2]);
                }
                ++n6;
            }
            return n4;
        }
        final int n7 = 1;
        double n8 = Math.abs(array[1 - 1 + n2]);
        int n9 = n7 + n3;
        int n10 = 2;
        for (int j = n - 2 + 1; j > 0; --j) {
            if (Math.abs(array[n9 - 1 + n2]) > n8) {
                n4 = n10;
                n8 = Math.abs(array[n9 - 1 + n2]);
            }
            n9 += n3;
            ++n10;
        }
        return n4;
    }
}
