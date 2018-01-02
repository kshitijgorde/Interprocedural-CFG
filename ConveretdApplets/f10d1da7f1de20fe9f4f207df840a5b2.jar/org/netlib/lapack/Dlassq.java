// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.util.doubleW;

public final class Dlassq
{
    public static void dlassq(final int n, final double[] array, final int n2, final int n3, final doubleW doubleW, final doubleW doubleW2) {
        if (n > 0) {
            int n4 = 1;
            for (int i = (1 + (n - 1) * n3 - 1 + n3) / n3; i > 0; --i) {
                if (array[n4 - 1 + n2] != 0.0) {
                    final double abs = Math.abs(array[n4 - 1 + n2]);
                    if (doubleW.val < abs) {
                        doubleW2.val = 1 + doubleW2.val * Math.pow(doubleW.val / abs, 2);
                        doubleW.val = abs;
                    }
                    else {
                        doubleW2.val += Math.pow(abs / doubleW.val, 2);
                    }
                }
                n4 += n3;
            }
        }
    }
}
