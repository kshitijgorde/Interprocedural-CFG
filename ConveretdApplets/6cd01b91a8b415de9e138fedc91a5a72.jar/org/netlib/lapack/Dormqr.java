// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.err.Xerbla;
import org.netlib.util.intW;

public final class Dormqr
{
    public static void dormqr(final String s, final String s2, final int n, final int n2, final int n3, final double[] array, final int n4, final int n5, final double[] array2, final int n6, final double[] array3, final int n7, final int n8, final double[] array4, final int n9, final int n10, final intW intW) {
        int n11 = 0;
        final intW intW2 = new intW(0);
        int n12 = 0;
        int n13 = 0;
        int n14 = 0;
        int min = 0;
        int n15 = 0;
        final double[] array5 = new double[65 * 64];
        intW.val = 0;
        final boolean lsame = Lsame.lsame(s, "L");
        final boolean lsame2 = Lsame.lsame(s2, "N");
        final boolean b = n10 == -1;
        int n16;
        int n17;
        if (lsame) {
            n16 = n;
            n17 = n2;
        }
        else {
            n16 = n2;
            n17 = n;
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
        else if (n3 < 0 || n3 > n16) {
            intW.val = -5;
        }
        else if (n5 < Math.max(1, n16)) {
            intW.val = -7;
        }
        else if (n8 < Math.max(1, n)) {
            intW.val = -10;
        }
        else if (n10 < Math.max(1, n17) && (b ^ true)) {
            intW.val = -12;
        }
        if (intW.val == 0) {
            min = Math.min(64, Ilaenv.ilaenv(1, "DORMQR", s + s2, n, n2, n3, -1));
            n13 = Math.max(1, n17) * min;
            array4[1 - 1 + n9] = n13;
        }
        if (intW.val != 0) {
            Xerbla.xerbla("DORMQR", -intW.val);
            return;
        }
        if (b) {
            return;
        }
        if (n == 0 || n2 == 0 || n3 == 0) {
            array4[1 - 1 + n9] = 1;
            return;
        }
        int max = 2;
        final int n18 = n17;
        if (min > 1 && min < n3) {
            if (n10 < n17 * min) {
                min = n10 / n18;
                max = Math.max(2, Ilaenv.ilaenv(2, "DORMQR", s + s2, n, n2, n3, -1));
            }
        }
        if (min < max || min >= n3) {
            Dorm2r.dorm2r(s, s2, n, n2, n3, array, n4, n5, array2, n6, array3, n7, n8, array4, n9, intW2);
        }
        else {
            int n19;
            int n20;
            int n21;
            if ((lsame && (lsame2 ^ true)) || ((lsame ^ true) && lsame2)) {
                n19 = 1;
                n20 = n3;
                n21 = min;
            }
            else {
                n19 = (n3 - 1) / min * min + 1;
                n20 = 1;
                n21 = -min;
            }
            if (lsame) {
                n15 = n2;
                n12 = 1;
            }
            else {
                n14 = n;
                n11 = 1;
            }
            int n22 = n19;
            for (int i = (n20 - n19 + n21) / n21; i > 0; --i) {
                final int min2 = Math.min(min, n3 - n22 + 1);
                Dlarft.dlarft("Forward", "Columnwise", n16 - n22 + 1, min2, array, n22 - 1 + (n22 - 1) * n5 + n4, n5, array2, n22 - 1 + n6, array5, 0, 65);
                if (lsame) {
                    n14 = n - n22 + 1;
                    n11 = n22;
                }
                else {
                    n15 = n2 - n22 + 1;
                    n12 = n22;
                }
                Dlarfb.dlarfb(s, s2, "Forward", "Columnwise", n14, n15, min2, array, n22 - 1 + (n22 - 1) * n5 + n4, n5, array5, 0, 65, array3, n11 - 1 + (n12 - 1) * n8 + n7, n8, array4, n9, n18);
                n22 += n21;
            }
        }
        array4[1 - 1 + n9] = n13;
    }
}
