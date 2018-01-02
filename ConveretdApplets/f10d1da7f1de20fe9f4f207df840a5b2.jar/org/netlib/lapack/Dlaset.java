// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

public final class Dlaset
{
    public static void dlaset(final String s, final int n, final int n2, final double n3, final double n4, final double[] array, final int n5, final int n6) {
        if (Lsame.lsame(s, "U")) {
            int n7 = 2;
            for (int i = n2 - 2 + 1; i > 0; --i) {
                int n8 = 1;
                for (int j = Math.min(n7 - 1, n) - 1 + 1; j > 0; --j) {
                    array[n8 - 1 + (n7 - 1) * n6 + n5] = n3;
                    ++n8;
                }
                ++n7;
            }
        }
        else if (Lsame.lsame(s, "L")) {
            int n9 = 1;
            for (int k = Math.min(n, n2) - 1 + 1; k > 0; --k) {
                int n10 = n9 + 1;
                for (int l = n - (n9 + 1) + 1; l > 0; --l) {
                    array[n10 - 1 + (n9 - 1) * n6 + n5] = n3;
                    ++n10;
                }
                ++n9;
            }
        }
        else {
            int n11 = 1;
            for (int n12 = n2 - 1 + 1; n12 > 0; --n12) {
                int n13 = 1;
                for (int n14 = n - 1 + 1; n14 > 0; --n14) {
                    array[n13 - 1 + (n11 - 1) * n6 + n5] = n3;
                    ++n13;
                }
                ++n11;
            }
        }
        int n15 = 1;
        for (int n16 = Math.min(n, n2) - 1 + 1; n16 > 0; --n16) {
            array[n15 - 1 + (n15 - 1) * n6 + n5] = n4;
            ++n15;
        }
    }
}
