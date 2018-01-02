// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.util.Util;
import org.netlib.util.intW;
import org.netlib.util.doubleW;

public final class Dlaed6
{
    public static boolean first;
    public static double eps;
    public static double small1;
    public static double small2;
    public static double sminv1;
    public static double sminv2;
    
    static {
        Dlaed6.eps = 0.0;
        Dlaed6.small1 = 0.0;
        Dlaed6.small2 = 0.0;
        Dlaed6.sminv1 = 0.0;
        Dlaed6.sminv2 = 0.0;
        Dlaed6.first = true;
    }
    
    public static void dlaed6(final int n, final boolean b, final double n2, final double[] array, final int n3, final double[] array2, final int n4, final double n5, final doubleW doubleW, final intW intW) {
        final double[] array3 = new double[3];
        final double[] array4 = new double[3];
        double n6 = 0.0;
        intW.val = 0;
        final int n7 = 1;
        doubleW.val = 0.0;
        if (n == 2) {
            double n8;
            double n9;
            double n10;
            if (b) {
                n8 = n2 + array2[1 - 1 + n4] / (array[1 - 1 + n3] - array[2 - 1 + n3] - (array[3 - 1 + n3] - array[2 - 1 + n3]) / 2.0);
                n9 = n8 * (array[2 - 1 + n3] + array[3 - 1 + n3]) + array2[2 - 1 + n4] + array2[3 - 1 + n4];
                n10 = n8 * array[2 - 1 + n3] * array[3 - 1 + n3] + array2[2 - 1 + n4] * array[3 - 1 + n3] + array2[3 - 1 + n4] * array[2 - 1 + n3];
            }
            else {
                n8 = n2 + array2[3 - 1 + n4] / (array[3 - 1 + n3] - array[2 - 1 + n3] - (array[1 - 1 + n3] - array[2 - 1 + n3]) / 2.0);
                n9 = n8 * (array[1 - 1 + n3] + array[2 - 1 + n3]) + array2[1 - 1 + n4] + array2[2 - 1 + n4];
                n10 = n8 * array[1 - 1 + n3] * array[2 - 1 + n3] + array2[1 - 1 + n4] * array[2 - 1 + n3] + array2[2 - 1 + n4] * array[1 - 1 + n3];
            }
            final double max = Util.max(Math.abs(n9), Math.abs(n10), Math.abs(n8));
            final double n11 = n9 / max;
            final double n12 = n10 / max;
            final double n13 = n8 / max;
            if (n13 == 0.0) {
                doubleW.val = n12 / n11;
            }
            else if (n11 <= 0.0) {
                doubleW.val = (n11 - Math.sqrt(Math.abs(n11 * n11 - 4.0 * n12 * n13))) / (2.0 * n13);
            }
            else {
                doubleW.val = 2.0 * n12 / (n11 + Math.sqrt(Math.abs(n11 * n11 - 4.0 * n12 * n13)));
            }
            if (Math.abs(n5) <= Math.abs(n2 + array2[1 - 1 + n4] / (array[1 - 1 + n3] - doubleW.val) + array2[2 - 1 + n4] / (array[2 - 1 + n3] - doubleW.val) + array2[3 - 1 + n4] / (array[3 - 1 + n3] - doubleW.val))) {
                doubleW.val = 0.0;
            }
        }
        if (Dlaed6.first) {
            Dlaed6.eps = Dlamch.dlamch("Epsilon");
            final double dlamch = Dlamch.dlamch("Base");
            Dlaed6.small1 = Math.pow(dlamch, (int)(Math.log(Dlamch.dlamch("SafMin")) / Math.log(dlamch) / 3.0));
            Dlaed6.sminv1 = 1.0 / Dlaed6.small1;
            Dlaed6.small2 = Dlaed6.small1 * Dlaed6.small1;
            Dlaed6.sminv2 = Dlaed6.sminv1 * Dlaed6.sminv1;
            Dlaed6.first = false;
        }
        double n14;
        if (b) {
            n14 = Math.min(Math.abs(array[2 - 1 + n3] - doubleW.val), Math.abs(array[3 - 1 + n3] - doubleW.val));
        }
        else {
            n14 = Math.min(Math.abs(array[1 - 1 + n3] - doubleW.val), Math.abs(array[2 - 1 + n3] - doubleW.val));
        }
        boolean b2 = false;
        if (n14 <= Dlaed6.small1) {
            b2 = true;
            double n15;
            if (n14 <= Dlaed6.small2) {
                n15 = Dlaed6.sminv2;
                n6 = Dlaed6.small2;
            }
            else {
                n15 = Dlaed6.sminv1;
                n6 = Dlaed6.small1;
            }
            int n16 = 1;
            for (int i = 3 - 1 + 1; i > 0; --i) {
                array3[n16 - 1] = array[n16 - 1 + n3] * n15;
                array4[n16 - 1] = array2[n16 - 1 + n4] * n15;
                ++n16;
            }
            doubleW.val *= n15;
        }
        else {
            int n17 = 1;
            for (int j = 3 - 1 + 1; j > 0; --j) {
                array3[n17 - 1] = array[n17 - 1 + n3];
                array4[n17 - 1] = array2[n17 - 1 + n4];
                ++n17;
            }
        }
        double n18 = 0.0;
        double n19 = 0.0;
        double n20 = 0.0;
        int n21 = 1;
        for (int k = 3 - 1 + 1; k > 0; --k) {
            final double n22 = 1.0 / (array3[n21 - 1] - doubleW.val);
            final double n23 = array4[n21 - 1] * n22;
            final double n24 = n23 * n22;
            final double n25 = n24 * n22;
            n18 += n23 / array3[n21 - 1];
            n19 += n24;
            n20 += n25;
            ++n21;
        }
        double n26 = n5 + doubleW.val * n18;
        Label_2038: {
            if (Math.abs(n26) > 0.0) {
                int n27;
                for (int l = 20 - (n27 = n7 + 1) + 1; l > 0; --l) {
                    double n28;
                    double n29;
                    if (b) {
                        n28 = array3[2 - 1] - doubleW.val;
                        n29 = array3[3 - 1] - doubleW.val;
                    }
                    else {
                        n28 = array3[1 - 1] - doubleW.val;
                        n29 = array3[2 - 1] - doubleW.val;
                    }
                    final double n30 = (n28 + n29) * n26 - n28 * n29 * n19;
                    final double n31 = n28 * n29 * n26;
                    final double n32 = n26 - (n28 + n29) * n19 + n28 * n29 * n20;
                    final double max2 = Util.max(Math.abs(n30), Math.abs(n31), Math.abs(n32));
                    final double n33 = n30 / max2;
                    final double n34 = n31 / max2;
                    final double n35 = n32 / max2;
                    double n36;
                    if (n35 == 0.0) {
                        n36 = n34 / n33;
                    }
                    else if (n33 <= 0.0) {
                        n36 = (n33 - Math.sqrt(Math.abs(n33 * n33 - 4.0 * n34 * n35))) / (2.0 * n35);
                    }
                    else {
                        n36 = 2.0 * n34 / (n33 + Math.sqrt(Math.abs(n33 * n33 - 4.0 * n34 * n35)));
                    }
                    if (n26 * n36 >= 0.0) {
                        n36 = -(n26 / n19);
                    }
                    final double n37 = n36 + doubleW.val;
                    if (b) {
                        if (n36 > 0.0 && n37 >= array3[3 - 1]) {
                            n36 = (array3[3 - 1] - doubleW.val) / 2.0;
                        }
                        if (n36 < 0.0 && n37 <= array3[2 - 1]) {
                            n36 = (array3[2 - 1] - doubleW.val) / 2.0;
                        }
                    }
                    else {
                        if (n36 > 0.0 && n37 >= array3[2 - 1]) {
                            n36 = (array3[2 - 1] - doubleW.val) / 2.0;
                        }
                        if (n36 < 0.0 && n37 <= array3[1 - 1]) {
                            n36 = (array3[1 - 1] - doubleW.val) / 2.0;
                        }
                    }
                    doubleW.val += n36;
                    double n38 = 0.0;
                    double n39 = 0.0;
                    n19 = 0.0;
                    n20 = 0.0;
                    int n40 = 1;
                    for (int n41 = 3 - 1 + 1; n41 > 0; --n41) {
                        final double n42 = 1.0 / (array3[n40 - 1] - doubleW.val);
                        final double n43 = array4[n40 - 1] * n42;
                        final double n44 = n43 * n42;
                        final double n45 = n44 * n42;
                        final double n46 = n43 / array3[n40 - 1];
                        n38 += n46;
                        n39 += Math.abs(n46);
                        n19 += n44;
                        n20 += n45;
                        ++n40;
                    }
                    n26 = n5 + doubleW.val * n38;
                    if (Math.abs(n26) <= Dlaed6.eps * (8.0 * (Math.abs(n5) + Math.abs(doubleW.val) * n39) + Math.abs(doubleW.val) * n19)) {
                        break Label_2038;
                    }
                    ++n27;
                }
                intW.val = 1;
            }
        }
        if (b2) {
            doubleW.val *= n6;
        }
    }
}
