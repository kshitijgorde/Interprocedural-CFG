// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.blas.Dsyr2k;
import org.netlib.err.Xerbla;
import org.netlib.util.intW;

public final class Dsytrd
{
    public static void dsytrd(final String s, final int n, final double[] array, final int n2, final int n3, final double[] array2, final int n4, final double[] array3, final int n5, final double[] array4, final int n6, final double[] array5, final int n7, final int n8, final intW intW) {
        final intW intW2 = new intW(0);
        int n9 = 0;
        int n10 = 0;
        int n11 = 0;
        intW.val = 0;
        final boolean lsame = Lsame.lsame(s, "U");
        final boolean b = n8 == -1;
        if ((lsame ^ true) && (Lsame.lsame(s, "L") ^ true)) {
            intW.val = -1;
        }
        else if (n < 0) {
            intW.val = -2;
        }
        else if (n3 < Math.max(1, n)) {
            intW.val = -4;
        }
        else if (n8 < 1 && (b ^ true)) {
            intW.val = -9;
        }
        if (intW.val == 0) {
            n11 = Ilaenv.ilaenv(1, "DSYTRD", s, n, -1, -1, -1);
            n10 = n * n11;
            array5[1 - 1 + n7] = n10;
        }
        if (intW.val != 0) {
            Xerbla.xerbla("DSYTRD", -intW.val);
            return;
        }
        if (b) {
            return;
        }
        if (n == 0) {
            array5[1 - 1 + n7] = 1;
            return;
        }
        int max = n;
        if (n11 > 1 && n11 < n) {
            max = Math.max(n11, Ilaenv.ilaenv(3, "DSYTRD", s, n, -1, -1, -1));
            if (max < n) {
                n9 = n;
                if (n8 < n9 * n11) {
                    n11 = Math.max(n8 / n9, 1);
                    if (n11 < Ilaenv.ilaenv(2, "DSYTRD", s, n, -1, -1, -1)) {
                        max = n;
                    }
                }
            }
            else {
                max = n;
            }
        }
        else {
            n11 = 1;
        }
        if (lsame) {
            final int n12 = n - (n - max + n11 - 1) / n11 * n11;
            int n13 = n - n11 + 1;
            for (int i = (n12 + 1 - (n - n11 + 1) + -n11) / -n11; i > 0; --i) {
                Dlatrd.dlatrd(s, n13 + n11 - 1, n11, array, n2, n3, array3, n5, array4, n6, array5, n7, n9);
                Dsyr2k.dsyr2k(s, "No transpose", n13 - 1, n11, -1.0, array, 1 - 1 + (n13 - 1) * n3 + n2, n3, array5, n7, n9, 1.0, array, n2, n3);
                int n14 = n13;
                for (int j = n13 + n11 - 1 - n13 + 1; j > 0; --j) {
                    array[n14 - 1 - 1 + (n14 - 1) * n3 + n2] = array3[n14 - 1 - 1 + n5];
                    array2[n14 - 1 + n4] = array[n14 - 1 + (n14 - 1) * n3 + n2];
                    ++n14;
                }
                n13 += -n11;
            }
            Dsytd2.dsytd2(s, n12, array, n2, n3, array2, n4, array3, n5, array4, n6, intW2);
        }
        else {
            int n15 = 1;
            for (int k = (n - max - 1 + n11) / n11; k > 0; --k) {
                Dlatrd.dlatrd(s, n - n15 + 1, n11, array, n15 - 1 + (n15 - 1) * n3 + n2, n3, array3, n15 - 1 + n5, array4, n15 - 1 + n6, array5, n7, n9);
                Dsyr2k.dsyr2k(s, "No transpose", n - n15 - n11 + 1, n11, -1.0, array, n15 + n11 - 1 + (n15 - 1) * n3 + n2, n3, array5, n11 + 1 - 1 + n7, n9, 1.0, array, n15 + n11 - 1 + (n15 + n11 - 1) * n3 + n2, n3);
                int n16 = n15;
                for (int l = n15 + n11 - 1 - n15 + 1; l > 0; --l) {
                    array[n16 + 1 - 1 + (n16 - 1) * n3 + n2] = array3[n16 - 1 + n5];
                    array2[n16 - 1 + n4] = array[n16 - 1 + (n16 - 1) * n3 + n2];
                    ++n16;
                }
                n15 += n11;
            }
            Dsytd2.dsytd2(s, n - n15 + 1, array, n15 - 1 + (n15 - 1) * n3 + n2, n3, array2, n15 - 1 + n4, array3, n15 - 1 + n5, array4, n15 - 1 + n6, intW2);
        }
        array5[1 - 1 + n7] = n10;
    }
}
