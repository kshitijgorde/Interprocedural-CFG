// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.err.Xerbla;
import org.netlib.util.intW;

public final class Dorm2l
{
    public static void dorm2l(final String s, final String s2, final int n, final int n2, final int n3, final double[] array, final int n4, final int n5, final double[] array2, final int n6, final double[] array3, final int n7, final int n8, final double[] array4, final int n9, final intW intW) {
        int n10 = 0;
        int n11 = 0;
        intW.val = 0;
        final boolean lsame = Lsame.lsame(s, "L");
        final boolean lsame2 = Lsame.lsame(s2, "N");
        int n12;
        if (lsame) {
            n12 = n;
        }
        else {
            n12 = n2;
        }
        if ((lsame ^ true) && (Lsame.lsame(s, "R") ^ true)) {
            intW.val = -1;
        }
        else if ((lsame2 ^ true) && (Lsame.lsame(s2, "T") ^ true)) {
            intW.val = -2;
        }
        else if (n < 0) {
            intW.val = -3;
        }
        else if (n2 < 0) {
            intW.val = -4;
        }
        else if (n3 < 0 || n3 > n12) {
            intW.val = -5;
        }
        else if (n5 < Math.max(1, n12)) {
            intW.val = -7;
        }
        else if (n8 < Math.max(1, n)) {
            intW.val = -10;
        }
        if (intW.val != 0) {
            Xerbla.xerbla("DORM2L", -intW.val);
            return;
        }
        if (n == 0 || n2 == 0 || n3 == 0) {
            return;
        }
        int n13;
        int n14;
        int n15;
        if ((lsame && lsame2) || ((lsame ^ true) && (lsame2 ^ true))) {
            n13 = 1;
            n14 = n3;
            n15 = 1;
        }
        else {
            n13 = n3;
            n14 = 1;
            n15 = -1;
        }
        if (lsame) {
            n11 = n2;
        }
        else {
            n10 = n;
        }
        int n16 = n13;
        for (int i = (n14 - n13 + n15) / n15; i > 0; --i) {
            if (lsame) {
                n10 = n - n3 + n16;
            }
            else {
                n11 = n2 - n3 + n16;
            }
            final double n17 = array[n12 - n3 + n16 - 1 + (n16 - 1) * n5 + n4];
            array[n12 - n3 + n16 - 1 + (n16 - 1) * n5 + n4] = 1.0;
            Dlarf.dlarf(s, n10, n11, array, 1 - 1 + (n16 - 1) * n5 + n4, 1, array2[n16 - 1 + n6], array3, n7, n8, array4, n9);
            array[n12 - n3 + n16 - 1 + (n16 - 1) * n5 + n4] = n17;
            n16 += n15;
        }
    }
}
