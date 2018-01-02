// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

public final class Ieeeck
{
    public static int ieeeck(final int n, final double n2, final double n3) {
        final boolean b = true;
        if (n3 / n2 <= n3) {
            return 0;
        }
        final double n4 = -(n3 / n2);
        if (n4 >= n2) {
            return 0;
        }
        final double n5 = n3 / (n4 + n3);
        if (n5 != n2) {
            return 0;
        }
        final double n6 = n3 / n5;
        if (n6 >= n2) {
            return 0;
        }
        final double n7 = n5 + n2;
        if (n7 != n2) {
            return 0;
        }
        final double n8 = n3 / n7;
        if (n8 <= n3) {
            return 0;
        }
        final double n9 = n6 * n8;
        if (n9 >= n2) {
            return 0;
        }
        final double n10 = n8 * n8;
        if (n10 <= n3) {
            return 0;
        }
        if (n == 0) {
            return b ? 1 : 0;
        }
        final double n11 = n10 + n9;
        final double n12 = n10 / n9;
        final double n13 = n10 / n10;
        final double n14 = n10 * n2;
        final double n15 = n9 * n5;
        final double n16 = n15 * 0.0;
        return (n11 != n11 && n12 != n12 && n13 != n13 && n14 != n14 && n15 != n15 && n16 != n16 && b) ? 1 : 0;
    }
}
