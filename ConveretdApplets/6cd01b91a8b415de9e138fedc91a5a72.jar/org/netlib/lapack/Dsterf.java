// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.util.Util;
import org.netlib.err.Xerbla;
import org.netlib.util.doubleW;
import org.netlib.util.intW;

public final class Dsterf
{
    public static void dsterf(final int n, final double[] array, final int n2, final double[] array2, final int n3, final intW intW) {
        final doubleW doubleW = new doubleW(0.0);
        final doubleW doubleW2 = new doubleW(0.0);
        intW.val = 0;
        if (n < 0) {
            intW.val = -1;
            Xerbla.xerbla("DSTERF", -intW.val);
            return;
        }
        if (n <= 1) {
            return;
        }
        final double dlamch = Dlamch.dlamch("E");
        final double pow = Math.pow(dlamch, 2);
        final double dlamch2 = Dlamch.dlamch("S");
        final double n4 = Math.sqrt(1.0 / dlamch2) / 3.0;
        final double n5 = Math.sqrt(dlamch2) / pow;
        final int n6 = n * 30;
        int n7 = 0;
        int i = 1;
    Label_0368_Outer:
        while (i <= n) {
            if (i > 1) {
                array2[i - 1 - 1 + n3] = 0.0;
            }
            int n8 = i;
            int j = n - 1 - i + 1;
            while (true) {
                while (j > 0) {
                    if (Math.abs(array2[n8 - 1 + n3]) <= Math.sqrt(Math.abs(array[n8 - 1 + n2])) * Math.sqrt(Math.abs(array[n8 + 1 - 1 + n2])) * dlamch) {
                        array2[n8 - 1 + n3] = 0.0;
                        final int n10;
                        int n9 = n10 = i;
                        final int n12;
                        int n11 = n12 = n8;
                        i = n8 + 1;
                        if (n11 == n9) {
                            continue Label_0368_Outer;
                        }
                        final double dlanst = Dlanst.dlanst("I", n11 - n9 + 1, array, n9 - 1 + n2, array2, n9 - 1 + n3);
                        int n13 = 0;
                        if (dlanst > n4) {
                            n13 = 1;
                            Dlascl.dlascl("G", 0, 0, dlanst, n4, n11 - n9 + 1, 1, array, n9 - 1 + n2, n, intW);
                            Dlascl.dlascl("G", 0, 0, dlanst, n4, n11 - n9, 1, array2, n9 - 1 + n3, n, intW);
                        }
                        else if (dlanst < n5) {
                            n13 = 2;
                            Dlascl.dlascl("G", 0, 0, dlanst, n5, n11 - n9 + 1, 1, array, n9 - 1 + n2, n, intW);
                            Dlascl.dlascl("G", 0, 0, dlanst, n5, n11 - n9, 1, array2, n9 - 1 + n3, n, intW);
                        }
                        int n14 = n9;
                        for (int k = n11 - 1 - n9 + 1; k > 0; --k) {
                            array2[n14 - 1 + n3] = Math.pow(array2[n14 - 1 + n3], 2);
                            ++n14;
                        }
                        if (Math.abs(array[n11 - 1 + n2]) < Math.abs(array[n9 - 1 + n2])) {
                            n11 = n10;
                            n9 = n12;
                        }
                        Label_1998: {
                            if (n11 >= n9) {
                                while (true) {
                                    int n15 = 0;
                                    Label_0813: {
                                        if (n9 != n11) {
                                            n15 = n9;
                                            for (int l = n11 - 1 - n9 + 1; l > 0; --l) {
                                                if (Math.abs(array2[n15 - 1 + n3]) <= pow * Math.abs(array[n15 - 1 + n2] * array[n15 + 1 - 1 + n2])) {
                                                    break Label_0813;
                                                }
                                                ++n15;
                                            }
                                        }
                                        n15 = n11;
                                    }
                                    if (n15 < n11) {
                                        array2[n15 - 1 + n3] = 0.0;
                                    }
                                    final double n16 = array[n9 - 1 + n2];
                                    if (n15 == n9) {
                                        array[n9 - 1 + n2] = n16;
                                        ++n9;
                                        if (n9 <= n11) {
                                            continue Label_0368_Outer;
                                        }
                                        break;
                                    }
                                    else if (n15 == n9 + 1) {
                                        Dlae2.dlae2(array[n9 - 1 + n2], Math.sqrt(array2[n9 - 1 + n3]), array[n9 + 1 - 1 + n2], doubleW, doubleW2);
                                        array[n9 - 1 + n2] = doubleW.val;
                                        array[n9 + 1 - 1 + n2] = doubleW2.val;
                                        array2[n9 - 1 + n3] = 0.0;
                                        n9 += 2;
                                        if (n9 <= n11) {
                                            continue Label_0368_Outer;
                                        }
                                        break;
                                    }
                                    else {
                                        if (n7 == n6) {
                                            break;
                                        }
                                        ++n7;
                                        final double sqrt = Math.sqrt(array2[n9 - 1 + n3]);
                                        final double n17 = (array[n9 + 1 - 1 + n2] - n16) / (2.0 * sqrt);
                                        final double n18 = n16 - sqrt / (n17 + Util.dsign(Dlapy2.dlapy2(n17, 1.0), n17));
                                        double n19 = 1.0;
                                        double n20 = 0.0;
                                        double n21 = array[n15 - 1 + n2] - n18;
                                        double n22 = n21 * n21;
                                        int n23 = n15 - 1;
                                        for (int n24 = (n9 - (n15 - 1) - 1) / -1; n24 > 0; --n24) {
                                            final double n25 = array2[n23 - 1 + n3];
                                            final double n26 = n22 + n25;
                                            if (n23 != n15 - 1) {
                                                array2[n23 + 1 - 1 + n3] = n20 * n26;
                                            }
                                            final double n27 = n19;
                                            n19 = n22 / n26;
                                            n20 = n25 / n26;
                                            final double n28 = n21;
                                            final double n29 = array[n23 - 1 + n2];
                                            n21 = n19 * (n29 - n18) - n20 * n28;
                                            array[n23 + 1 - 1 + n2] = n28 + (n29 - n21);
                                            if (n19 != 0.0) {
                                                n22 = n21 * n21 / n19;
                                            }
                                            else {
                                                n22 = n27 * n25;
                                            }
                                            --n23;
                                        }
                                        array2[n9 - 1 + n3] = n20 * n22;
                                        array[n9 - 1 + n2] = n18 + n21;
                                    }
                                }
                            }
                            else {
                            Label_1359:
                                while (true) {
                                    int n30 = n9;
                                    int n31 = (n11 + 1 - n9 - 1) / -1;
                                    while (true) {
                                        while (n31 > 0) {
                                            if (Math.abs(array2[n30 - 1 - 1 + n3]) <= pow * Math.abs(array[n30 - 1 + n2] * array[n30 - 1 - 1 + n2])) {
                                                if (n30 > n11) {
                                                    array2[n30 - 1 - 1 + n3] = 0.0;
                                                }
                                                final double n32 = array[n9 - 1 + n2];
                                                if (n30 == n9) {
                                                    array[n9 - 1 + n2] = n32;
                                                    --n9;
                                                    if (n9 >= n11) {
                                                        continue Label_1359;
                                                    }
                                                    break Label_1998;
                                                }
                                                else if (n30 == n9 - 1) {
                                                    Dlae2.dlae2(array[n9 - 1 + n2], Math.sqrt(array2[n9 - 1 - 1 + n3]), array[n9 - 1 - 1 + n2], doubleW, doubleW2);
                                                    array[n9 - 1 + n2] = doubleW.val;
                                                    array[n9 - 1 - 1 + n2] = doubleW2.val;
                                                    array2[n9 - 1 - 1 + n3] = 0.0;
                                                    n9 -= 2;
                                                    if (n9 >= n11) {
                                                        continue Label_1359;
                                                    }
                                                    break Label_1998;
                                                }
                                                else {
                                                    if (n7 == n6) {
                                                        break Label_1998;
                                                    }
                                                    ++n7;
                                                    final double sqrt2 = Math.sqrt(array2[n9 - 1 - 1 + n3]);
                                                    final double n33 = (array[n9 - 1 - 1 + n2] - n32) / (2.0 * sqrt2);
                                                    final double n34 = n32 - sqrt2 / (n33 + Util.dsign(Dlapy2.dlapy2(n33, 1.0), n33));
                                                    double n35 = 1.0;
                                                    double n36 = 0.0;
                                                    double n37 = array[n30 - 1 + n2] - n34;
                                                    double n38 = n37 * n37;
                                                    int n39 = n30;
                                                    for (int n40 = n9 - 1 - n30 + 1; n40 > 0; --n40) {
                                                        final double n41 = array2[n39 - 1 + n3];
                                                        final double n42 = n38 + n41;
                                                        if (n39 != n30) {
                                                            array2[n39 - 1 - 1 + n3] = n36 * n42;
                                                        }
                                                        final double n43 = n35;
                                                        n35 = n38 / n42;
                                                        n36 = n41 / n42;
                                                        final double n44 = n37;
                                                        final double n45 = array[n39 + 1 - 1 + n2];
                                                        n37 = n35 * (n45 - n34) - n36 * n44;
                                                        array[n39 - 1 + n2] = n44 + (n45 - n37);
                                                        if (n35 != 0.0) {
                                                            n38 = n37 * n37 / n35;
                                                        }
                                                        else {
                                                            n38 = n43 * n41;
                                                        }
                                                        ++n39;
                                                    }
                                                    array2[n9 - 1 - 1 + n3] = n36 * n38;
                                                    array[n9 - 1 + n2] = n34 + n37;
                                                    continue Label_1359;
                                                }
                                            }
                                            else {
                                                --n30;
                                                --n31;
                                            }
                                        }
                                        n30 = n11;
                                        continue;
                                    }
                                }
                            }
                        }
                        if (n13 == 1) {
                            Dlascl.dlascl("G", 0, 0, n4, dlanst, n12 - n10 + 1, 1, array, n10 - 1 + n2, n, intW);
                        }
                        if (n13 == 2) {
                            Dlascl.dlascl("G", 0, 0, n5, dlanst, n12 - n10 + 1, 1, array, n10 - 1 + n2, n, intW);
                        }
                        if (n7 < n6) {
                            continue Label_0368_Outer;
                        }
                        int n46 = 1;
                        for (int n47 = n - 1 - 1 + 1; n47 > 0; --n47) {
                            if (array2[n46 - 1 + n3] != 0.0) {
                                ++intW.val;
                            }
                            ++n46;
                        }
                        return;
                    }
                    else {
                        ++n8;
                        --j;
                    }
                }
                n8 = n;
                continue;
            }
        }
        Dlasrt.dlasrt("I", n, array, n2, intW);
    }
}
