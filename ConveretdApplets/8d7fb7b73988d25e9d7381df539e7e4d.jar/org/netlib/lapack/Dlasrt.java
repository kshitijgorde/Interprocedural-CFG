// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.err.Xerbla;
import org.netlib.util.intW;

public final class Dlasrt
{
    public static void dlasrt(final String s, final int n, final double[] array, final int n2, final intW intW) {
        final int[] array2 = new int[2 * 32];
        intW.val = 0;
        int n3 = -1;
        if (Lsame.lsame(s, "D")) {
            n3 = 0;
        }
        else if (Lsame.lsame(s, "I")) {
            n3 = 1;
        }
        if (n3 == -1) {
            intW.val = -1;
        }
        else if (n < 0) {
            intW.val = -2;
        }
        if (intW.val != 0) {
            Xerbla.xerbla("DLASRT", -intW.val);
            return;
        }
        if (n <= 1) {
            return;
        }
        int n4 = 1;
        array2[2 - 1 + ((array2[1 - 1 + (1 - 1) * 2] = 1) - 1) * 2] = n;
        while (true) {
            final int n5 = array2[1 - 1 + (n4 - 1) * 2];
            final int n6 = array2[2 - 1 + (n4 - 1) * 2];
            --n4;
            if (n6 - n5 <= 20 && n6 - n5 > 0) {
                if (n3 == 0) {
                    int n7 = n5 + 1;
                    for (int i = n6 - (n5 + 1) + 1; i > 0; --i) {
                        for (int n8 = n7, n9 = (n5 + 1 - n7 - 1) / -1; n9 > 0 && array[n8 - 1 + n2] > array[n8 - 1 - 1 + n2]; --n8, --n9) {
                            final double n10 = array[n8 - 1 + n2];
                            array[n8 - 1 + n2] = array[n8 - 1 - 1 + n2];
                            array[n8 - 1 - 1 + n2] = n10;
                        }
                        ++n7;
                    }
                }
                else {
                    int n11 = n5 + 1;
                    for (int j = n6 - (n5 + 1) + 1; j > 0; --j) {
                        for (int n12 = n11, n13 = (n5 + 1 - n11 - 1) / -1; n13 > 0 && array[n12 - 1 + n2] < array[n12 - 1 - 1 + n2]; --n12, --n13) {
                            final double n14 = array[n12 - 1 + n2];
                            array[n12 - 1 + n2] = array[n12 - 1 - 1 + n2];
                            array[n12 - 1 - 1 + n2] = n14;
                        }
                        ++n11;
                    }
                }
            }
            else if (n6 - n5 > 20) {
                final double n15 = array[n5 - 1 + n2];
                final double n16 = array[n6 - 1 + n2];
                final double n17 = array[(n5 + n6) / 2 - 1 + n2];
                double n18;
                if (n15 < n16) {
                    if (n17 < n15) {
                        n18 = n15;
                    }
                    else if (n17 < n16) {
                        n18 = n17;
                    }
                    else {
                        n18 = n16;
                    }
                }
                else if (n17 < n16) {
                    n18 = n16;
                }
                else if (n17 < n15) {
                    n18 = n17;
                }
                else {
                    n18 = n15;
                }
                if (n3 == 0) {
                    int n19 = n5 - 1;
                    int n20 = n6 + 1;
                    while (true) {
                        --n20;
                        if (array[n20 - 1 + n2] < n18) {
                            continue;
                        }
                        do {
                            ++n19;
                        } while (array[n19 - 1 + n2] > n18);
                        if (n19 >= n20) {
                            break;
                        }
                        final double n21 = array[n19 - 1 + n2];
                        array[n19 - 1 + n2] = array[n20 - 1 + n2];
                        array[n20 - 1 + n2] = n21;
                    }
                    if (n20 - n5 > n6 - n20 - 1) {
                        final int n22 = n4 + 1;
                        array2[1 - 1 + (n22 - 1) * 2] = n5;
                        array2[2 - 1 + (n22 - 1) * 2] = n20;
                        n4 = n22 + 1;
                        array2[1 - 1 + (n4 - 1) * 2] = n20 + 1;
                        array2[2 - 1 + (n4 - 1) * 2] = n6;
                    }
                    else {
                        final int n23 = n4 + 1;
                        array2[1 - 1 + (n23 - 1) * 2] = n20 + 1;
                        array2[2 - 1 + (n23 - 1) * 2] = n6;
                        n4 = n23 + 1;
                        array2[1 - 1 + (n4 - 1) * 2] = n5;
                        array2[2 - 1 + (n4 - 1) * 2] = n20;
                    }
                }
                else {
                    int n24 = n5 - 1;
                    int n25 = n6 + 1;
                    while (true) {
                        --n25;
                        if (array[n25 - 1 + n2] > n18) {
                            continue;
                        }
                        do {
                            ++n24;
                        } while (array[n24 - 1 + n2] < n18);
                        if (n24 >= n25) {
                            break;
                        }
                        final double n26 = array[n24 - 1 + n2];
                        array[n24 - 1 + n2] = array[n25 - 1 + n2];
                        array[n25 - 1 + n2] = n26;
                    }
                    if (n25 - n5 > n6 - n25 - 1) {
                        final int n27 = n4 + 1;
                        array2[1 - 1 + (n27 - 1) * 2] = n5;
                        array2[2 - 1 + (n27 - 1) * 2] = n25;
                        n4 = n27 + 1;
                        array2[1 - 1 + (n4 - 1) * 2] = n25 + 1;
                        array2[2 - 1 + (n4 - 1) * 2] = n6;
                    }
                    else {
                        final int n28 = n4 + 1;
                        array2[1 - 1 + (n28 - 1) * 2] = n25 + 1;
                        array2[2 - 1 + (n28 - 1) * 2] = n6;
                        n4 = n28 + 1;
                        array2[1 - 1 + (n4 - 1) * 2] = n5;
                        array2[2 - 1 + (n4 - 1) * 2] = n25;
                    }
                }
            }
            if (n4 > 0) {
                continue;
            }
            break;
        }
    }
}
