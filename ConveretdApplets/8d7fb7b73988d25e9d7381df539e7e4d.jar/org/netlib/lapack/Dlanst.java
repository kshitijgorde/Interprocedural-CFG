// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.util.doubleW;

public final class Dlanst
{
    public static double dlanst(final String s, final int n, final double[] array, final int n2, final double[] array2, final int n3) {
        double n4 = 0.0;
        final doubleW doubleW = new doubleW(0.0);
        final doubleW doubleW2 = new doubleW(0.0);
        if (n <= 0) {
            n4 = 0.0;
        }
        else if (Lsame.lsame(s, "M")) {
            n4 = Math.abs(array[n - 1 + n2]);
            int n5 = 1;
            for (int i = n - 1 - 1 + 1; i > 0; --i) {
                n4 = Math.max(Math.max(n4, Math.abs(array[n5 - 1 + n2])), Math.abs(array2[n5 - 1 + n3]));
                ++n5;
            }
        }
        else if (Lsame.lsame(s, "O") || s.regionMatches(0, "1", 0, 1) || Lsame.lsame(s, "I")) {
            if (n == 1) {
                n4 = Math.abs(array[1 - 1 + n2]);
            }
            else {
                n4 = Math.max(Math.abs(array[1 - 1 + n2]) + Math.abs(array2[1 - 1 + n3]), Math.abs(array2[n - 1 - 1 + n3]) + Math.abs(array[n - 1 + n2]));
                int n6 = 2;
                for (int j = n - 1 - 2 + 1; j > 0; --j) {
                    n4 = Math.max(n4, Math.abs(array[n6 - 1 + n2]) + Math.abs(array2[n6 - 1 + n3]) + Math.abs(array2[n6 - 1 - 1 + n3]));
                    ++n6;
                }
            }
        }
        else if (Lsame.lsame(s, "F") || Lsame.lsame(s, "E")) {
            doubleW.val = 0.0;
            doubleW2.val = 1.0;
            if (n > 1) {
                Dlassq.dlassq(n - 1, array2, n3, 1, doubleW, doubleW2);
                doubleW2.val *= 2;
            }
            Dlassq.dlassq(n, array, n2, 1, doubleW, doubleW2);
            n4 = doubleW.val * Math.sqrt(doubleW2.val);
        }
        return n4;
    }
}
