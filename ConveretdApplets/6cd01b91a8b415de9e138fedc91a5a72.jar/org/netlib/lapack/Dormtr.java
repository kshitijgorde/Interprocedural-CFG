// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.err.Xerbla;
import org.netlib.util.intW;

public final class Dormtr
{
    public static void dormtr(final String s, final String s2, final String s3, final int n, final int n2, final double[] array, final int n3, final int n4, final double[] array2, final int n5, final double[] array3, final int n6, final int n7, final double[] array4, final int n8, final int n9, final intW intW) {
        final intW intW2 = new intW(0);
        int n10 = 0;
        intW.val = 0;
        final boolean lsame = Lsame.lsame(s, "L");
        final boolean lsame2 = Lsame.lsame(s2, "U");
        final boolean b = n9 == -1;
        int n11;
        int n12;
        if (lsame) {
            n11 = n;
            n12 = n2;
        }
        else {
            n11 = n2;
            n12 = n;
        }
        if ((lsame ^ true) && (Lsame.lsame(s, "R") ^ true)) {
            intW.val = -1;
        }
        else if ((lsame2 ^ true) && (Lsame.lsame(s2, "L") ^ true)) {
            intW.val = -2;
        }
        else if ((Lsame.lsame(s3, "N") ^ true) && (Lsame.lsame(s3, "T") ^ true)) {
            intW.val = -3;
        }
        else if (n < 0) {
            intW.val = -4;
        }
        else if (n2 < 0) {
            intW.val = -5;
        }
        else if (n4 < Math.max(1, n11)) {
            intW.val = -7;
        }
        else if (n7 < Math.max(1, n)) {
            intW.val = -10;
        }
        else if (n9 < Math.max(1, n12) && (b ^ true)) {
            intW.val = -12;
        }
        if (intW.val == 0) {
            int n13;
            if (lsame2) {
                if (lsame) {
                    n13 = Ilaenv.ilaenv(1, "DORMQL", s + s3, n - 1, n2, n - 1, -1);
                }
                else {
                    n13 = Ilaenv.ilaenv(1, "DORMQL", s + s3, n, n2 - 1, n2 - 1, -1);
                }
            }
            else if (lsame) {
                n13 = Ilaenv.ilaenv(1, "DORMQR", s + s3, n - 1, n2, n - 1, -1);
            }
            else {
                n13 = Ilaenv.ilaenv(1, "DORMQR", s + s3, n, n2 - 1, n2 - 1, -1);
            }
            n10 = Math.max(1, n12) * n13;
            array4[1 - 1 + n8] = n10;
        }
        if (intW.val != 0) {
            Xerbla.xerbla("DORMTR", -intW.val);
            return;
        }
        if (b) {
            return;
        }
        if (n == 0 || n2 == 0 || n11 == 1) {
            array4[1 - 1 + n8] = 1;
            return;
        }
        int n14;
        int n15;
        if (lsame) {
            n14 = n - 1;
            n15 = n2;
        }
        else {
            n14 = n;
            n15 = n2 - 1;
        }
        if (lsame2) {
            Dormql.dormql(s, s3, n14, n15, n11 - 1, array, 1 - 1 + (2 - 1) * n4 + n3, n4, array2, n5, array3, n6, n7, array4, n8, n9, intW2);
        }
        else {
            int n16;
            int n17;
            if (lsame) {
                n16 = 2;
                n17 = 1;
            }
            else {
                n16 = 1;
                n17 = 2;
            }
            Dormqr.dormqr(s, s3, n14, n15, n11 - 1, array, 2 - 1 + (1 - 1) * n4 + n3, n4, array2, n5, array3, n16 - 1 + (n17 - 1) * n7 + n6, n7, array4, n8, n9, intW2);
        }
        array4[1 - 1 + n8] = n10;
    }
}
