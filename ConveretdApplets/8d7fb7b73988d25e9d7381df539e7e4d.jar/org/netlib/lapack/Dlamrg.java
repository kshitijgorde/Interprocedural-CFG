// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

public final class Dlamrg
{
    public static void dlamrg(final int n, final int n2, final double[] array, final int n3, final int n4, final int n5, final int[] array2, final int n6) {
        int n7 = n;
        int n8 = n2;
        int n9;
        if (n4 > 0) {
            n9 = 1;
        }
        else {
            n9 = n;
        }
        int n10;
        if (n5 > 0) {
            n10 = 1 + n;
        }
        else {
            n10 = n + n2;
        }
        int n11 = 1;
        while (n7 > 0 && n8 > 0) {
            if (array[n9 - 1 + n3] <= array[n10 - 1 + n3]) {
                array2[n11 - 1 + n6] = n9;
                ++n11;
                n9 += n4;
                --n7;
            }
            else {
                array2[n11 - 1 + n6] = n10;
                ++n11;
                n10 += n5;
                --n8;
            }
        }
        if (n7 == 0) {
            int n12 = 1;
            for (int i = n8 - 1 + 1; i > 0; --i) {
                array2[n11 - 1 + n6] = n10;
                ++n11;
                n10 += n5;
                ++n12;
            }
        }
        else {
            int n13 = 1;
            for (int j = n7 - 1 + 1; j > 0; --j) {
                array2[n11 - 1 + n6] = n9;
                ++n11;
                n9 += n4;
                ++n13;
            }
        }
    }
}
