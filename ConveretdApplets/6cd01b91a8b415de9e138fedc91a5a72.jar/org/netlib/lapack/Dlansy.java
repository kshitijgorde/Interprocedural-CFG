// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.util.doubleW;

public final class Dlansy
{
    public static double dlansy(final String s, final String s2, final int n, final double[] array, final int n2, final int n3, final double[] array2, final int n4) {
        final doubleW doubleW = new doubleW(0.0);
        final doubleW doubleW2 = new doubleW(0.0);
        double n5 = 0.0;
        if (n == 0) {
            n5 = 0.0;
        }
        else if (Lsame.lsame(s, "M")) {
            n5 = 0.0;
            if (Lsame.lsame(s2, "U")) {
                int n6 = 1;
                for (int i = n - 1 + 1; i > 0; --i) {
                    int n7 = 1;
                    for (int j = n6 - 1 + 1; j > 0; --j) {
                        n5 = Math.max(n5, Math.abs(array[n7 - 1 + (n6 - 1) * n3 + n2]));
                        ++n7;
                    }
                    ++n6;
                }
            }
            else {
                int n8 = 1;
                for (int k = n - 1 + 1; k > 0; --k) {
                    int n9 = n8;
                    for (int l = n - n8 + 1; l > 0; --l) {
                        n5 = Math.max(n5, Math.abs(array[n9 - 1 + (n8 - 1) * n3 + n2]));
                        ++n9;
                    }
                    ++n8;
                }
            }
        }
        else if (Lsame.lsame(s, "I") || Lsame.lsame(s, "O") || s.regionMatches(0, "1", 0, 1)) {
            n5 = 0.0;
            if (Lsame.lsame(s2, "U")) {
                int n10 = 1;
                for (int n11 = n - 1 + 1; n11 > 0; --n11) {
                    doubleW2.val = 0.0;
                    int n12 = 1;
                    for (int n13 = n10 - 1 - 1 + 1; n13 > 0; --n13) {
                        final double abs = Math.abs(array[n12 - 1 + (n10 - 1) * n3 + n2]);
                        doubleW2.val += abs;
                        array2[n12 - 1 + n4] += abs;
                        ++n12;
                    }
                    array2[n10 - 1 + n4] = doubleW2.val + Math.abs(array[n10 - 1 + (n10 - 1) * n3 + n2]);
                    ++n10;
                }
                int n14 = 1;
                for (int n15 = n - 1 + 1; n15 > 0; --n15) {
                    n5 = Math.max(n5, array2[n14 - 1 + n4]);
                    ++n14;
                }
            }
            else {
                int n16 = 1;
                for (int n17 = n - 1 + 1; n17 > 0; --n17) {
                    array2[n16 - 1 + n4] = 0.0;
                    ++n16;
                }
                int n18 = 1;
                for (int n19 = n - 1 + 1; n19 > 0; --n19) {
                    doubleW2.val = array2[n18 - 1 + n4] + Math.abs(array[n18 - 1 + (n18 - 1) * n3 + n2]);
                    int n20 = n18 + 1;
                    for (int n21 = n - (n18 + 1) + 1; n21 > 0; --n21) {
                        final double abs2 = Math.abs(array[n20 - 1 + (n18 - 1) * n3 + n2]);
                        doubleW2.val += abs2;
                        array2[n20 - 1 + n4] += abs2;
                        ++n20;
                    }
                    n5 = Math.max(n5, doubleW2.val);
                    ++n18;
                }
            }
        }
        else if (Lsame.lsame(s, "F") || Lsame.lsame(s, "E")) {
            doubleW.val = 0.0;
            doubleW2.val = 1.0;
            if (Lsame.lsame(s2, "U")) {
                int n22 = 2;
                for (int n23 = n - 2 + 1; n23 > 0; --n23) {
                    Dlassq.dlassq(n22 - 1, array, 1 - 1 + (n22 - 1) * n3 + n2, 1, doubleW, doubleW2);
                    ++n22;
                }
            }
            else {
                int n24 = 1;
                for (int n25 = n - 1 - 1 + 1; n25 > 0; --n25) {
                    Dlassq.dlassq(n - n24, array, n24 + 1 - 1 + (n24 - 1) * n3 + n2, 1, doubleW, doubleW2);
                    ++n24;
                }
            }
            doubleW2.val *= 2;
            Dlassq.dlassq(n, array, n2, n3 + 1, doubleW, doubleW2);
            n5 = doubleW.val * Math.sqrt(doubleW2.val);
        }
        return n5;
    }
}
