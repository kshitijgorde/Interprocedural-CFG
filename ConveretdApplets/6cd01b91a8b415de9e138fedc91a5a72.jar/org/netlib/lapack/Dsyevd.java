// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.blas.Dscal;
import org.netlib.err.Xerbla;
import org.netlib.util.intW;

public final class Dsyevd
{
    public static void dsyevd(final String s, final String s2, final int n, final double[] array, final int n2, final int n3, final double[] array2, final int n4, final double[] array3, final int n5, final int n6, final int[] array4, final int n7, final int n8, final intW intW) {
        final intW intW2 = new intW(0);
        double n9 = 0.0;
        final boolean lsame = Lsame.lsame(s, "V");
        final boolean lsame2 = Lsame.lsame(s2, "L");
        final boolean b = n6 == -1 || n8 == -1;
        intW.val = 0;
        int n10;
        int n12;
        int n11;
        int n13;
        if (n <= 1) {
            n10 = 1;
            n11 = (n12 = 1);
            n13 = n10;
        }
        else {
            if (lsame) {
                n10 = 3 + 5 * n;
                n11 = (int)(1 + 6 * n + 2 * Math.pow(n, 2));
            }
            else {
                n10 = 1;
                n11 = 2 * n + 1;
            }
            n12 = n11;
            n13 = n10;
        }
        if ((lsame || Lsame.lsame(s, "N")) ^ true) {
            intW.val = -1;
        }
        else if ((lsame2 || Lsame.lsame(s2, "U")) ^ true) {
            intW.val = -2;
        }
        else if (n < 0) {
            intW.val = -3;
        }
        else if (n3 < Math.max(1, n)) {
            intW.val = -5;
        }
        else if (n6 < n11 && (b ^ true)) {
            intW.val = -8;
        }
        else if (n8 < n10 && (b ^ true)) {
            intW.val = -10;
        }
        if (intW.val == 0) {
            array3[1 - 1 + n5] = n12;
            array4[1 - 1 + n7] = n13;
        }
        if (intW.val != 0) {
            Xerbla.xerbla("DSYEVD", -intW.val);
            return;
        }
        if (b) {
            return;
        }
        if (n == 0) {
            return;
        }
        if (n == 1) {
            array2[1 - 1 + n4] = array[1 - 1 + (1 - 1) * n3 + n2];
            if (lsame) {
                array[1 - 1 + (1 - 1) * n3 + n2] = 1.0;
            }
            return;
        }
        final double n14 = Dlamch.dlamch("Safe minimum") / Dlamch.dlamch("Precision");
        final double n15 = 1.0 / n14;
        final double sqrt = Math.sqrt(n14);
        final double sqrt2 = Math.sqrt(n15);
        final double dlansy = Dlansy.dlansy("M", s2, n, array, n2, n3, array3, n5);
        boolean b2 = false;
        if (dlansy > 0.0 && dlansy < sqrt) {
            b2 = true;
            n9 = sqrt / dlansy;
        }
        else if (dlansy > sqrt2) {
            b2 = true;
            n9 = sqrt2 / dlansy;
        }
        if (b2) {
            Dlascl.dlascl(s2, 0, 0, 1.0, n9, n, n, array, n2, n3, intW);
        }
        final int n16 = 1;
        final int n17 = n16 + n;
        final int n18 = n17 + n;
        final int n19 = n6 - n18 + 1;
        final int n20 = n18 + n * n;
        final int n21 = n6 - n20 + 1;
        Dsytrd.dsytrd(s2, n, array, n2, n3, array2, n4, array3, n16 - 1 + n5, array3, n17 - 1 + n5, array3, n18 - 1 + n5, n19, intW2);
        int n22 = (int)(2 * n + array3[n18 - 1 + n5]);
        if (lsame ^ true) {
            Dsterf.dsterf(n, array2, n4, array3, n16 - 1 + n5, intW);
        }
        else {
            Dstedc.dstedc("I", n, array2, n4, array3, n16 - 1 + n5, array3, n18 - 1 + n5, n, array3, n20 - 1 + n5, n21, array4, n7, n8, intW);
            Dormtr.dormtr("L", s2, "N", n, n, array, n2, n3, array3, n17 - 1 + n5, array3, n18 - 1 + n5, n, array3, n20 - 1 + n5, n21, intW2);
            Dlacpy.dlacpy("A", n, n, array3, n18 - 1 + n5, n, array, n2, n3);
            n22 = (int)Math.max(n22, 1 + 6 * n + 2 * Math.pow(n, 2));
        }
        if (b2) {
            Dscal.dscal(n, 1.0 / n9, array2, n4, 1);
        }
        array3[1 - 1 + n5] = n22;
        array4[1 - 1 + n7] = n13;
    }
}
