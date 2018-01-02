// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.err.Xerbla;
import org.netlib.util.intW;

public final class Dormql
{
    public static void dormql(final String s, final String s2, final int n, final int n2, final int n3, final double[] array, final int n4, final int n5, final double[] array2, final int n6, final double[] array3, final int n7, final int n8, final double[] array4, final int n9, final int n10, final intW intW) {
        final intW intW2 = new intW(0);
        int n11 = 0;
        int n12 = 0;
        int min = 0;
        int n13 = 0;
        final double[] array5 = new double[65 * 64];
        intW.val = 0;
        final boolean lsame = Lsame.lsame(s, "L");
        final boolean lsame2 = Lsame.lsame(s2, "N");
        final boolean b = n10 == -1;
        int n14;
        int n15;
        if (lsame) {
            n14 = n;
            n15 = n2;
        }
        else {
            n14 = n2;
            n15 = n;
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
        else if (n3 < 0 || n3 > n14) {
            intW.val = -5;
        }
        else if (n5 < Math.max(1, n14)) {
            intW.val = -7;
        }
        else if (n8 < Math.max(1, n)) {
            intW.val = -10;
        }
        else if (n10 < Math.max(1, n15) && (b ^ true)) {
            intW.val = -12;
        }
        if (intW.val == 0) {
            min = Math.min(64, Ilaenv.ilaenv(1, "DORMQL", s + s2, n, n2, n3, -1));
            n11 = Math.max(1, n15) * min;
            array4[1 - 1 + n9] = n11;
        }
        if (intW.val != 0) {
            Xerbla.xerbla("DORMQL", -intW.val);
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
        final int n16 = n15;
        if (min > 1 && min < n3) {
            if (n10 < n15 * min) {
                min = n10 / n16;
                max = Math.max(2, Ilaenv.ilaenv(2, "DORMQL", s + s2, n, n2, n3, -1));
            }
        }
        if (min < max || min >= n3) {
            Dorm2l.dorm2l(s, s2, n, n2, n3, array, n4, n5, array2, n6, array3, n7, n8, array4, n9, intW2);
        }
        else {
            int n17;
            int n18;
            int n19;
            if ((lsame && lsame2) || ((lsame ^ true) && (lsame2 ^ true))) {
                n17 = 1;
                n18 = n3;
                n19 = min;
            }
            else {
                n17 = (n3 - 1) / min * min + 1;
                n18 = 1;
                n19 = -min;
            }
            if (lsame) {
                n13 = n2;
            }
            else {
                n12 = n;
            }
            int n20 = n17;
            for (int i = (n18 - n17 + n19) / n19; i > 0; --i) {
                final int min2 = Math.min(min, n3 - n20 + 1);
                Dlarft.dlarft("Backward", "Columnwise", n14 - n3 + n20 + min2 - 1, min2, array, 1 - 1 + (n20 - 1) * n5 + n4, n5, array2, n20 - 1 + n6, array5, 0, 65);
                if (lsame) {
                    n12 = n - n3 + n20 + min2 - 1;
                }
                else {
                    n13 = n2 - n3 + n20 + min2 - 1;
                }
                Dlarfb.dlarfb(s, s2, "Backward", "Columnwise", n12, n13, min2, array, 1 - 1 + (n20 - 1) * n5 + n4, n5, array5, 0, 65, array3, n7, n8, array4, n9, n16);
                n20 += n19;
            }
        }
        array4[1 - 1 + n9] = n11;
    }
}
