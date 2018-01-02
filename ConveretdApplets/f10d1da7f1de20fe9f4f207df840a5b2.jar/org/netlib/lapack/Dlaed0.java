// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.util.doubleW;
import org.netlib.blas.Dcopy;
import org.netlib.blas.Dgemm;
import org.netlib.err.Xerbla;
import org.netlib.util.intW;

public final class Dlaed0
{
    public static void dlaed0(final int n, final int n2, final int n3, final double[] array, final int n4, final double[] array2, final int n5, final double[] array3, final int n6, final int n7, final double[] array4, final int n8, final int n9, final double[] array5, final int n10, final int[] array6, final int n11, final intW intW) {
        int n12 = 0;
        int n13 = 0;
        int n14 = 0;
        int n15 = 0;
        int n16 = 0;
        int n17 = 0;
        int n18 = 0;
        int n19 = 0;
        int n20 = 0;
        intW.val = 0;
        if (n < 0 || n > 2) {
            intW.val = -1;
        }
        else if (n == 1 && n2 < Math.max(0, n3)) {
            intW.val = -2;
        }
        else if (n3 < 0) {
            intW.val = -3;
        }
        else if (n7 < Math.max(1, n3)) {
            intW.val = -7;
        }
        else if (n9 < Math.max(1, n3)) {
            intW.val = -9;
        }
        if (intW.val != 0) {
            Xerbla.xerbla("DLAED0", -intW.val);
            return;
        }
        if (n3 == 0) {
            return;
        }
        final int ilaenv = Ilaenv.ilaenv(9, "DLAED0", " ", 0, 0, 0, 0);
        array6[1 - 1 + n11] = n3;
        int i = 1;
        int n21 = 0;
        while (array6[i - 1 + n11] > ilaenv) {
            int n22 = i;
            for (int j = (1 - i - 1) / -1; j > 0; --j) {
                array6[2 * n22 - 1 + n11] = (array6[n22 - 1 + n11] + 1) / 2;
                array6[2 * n22 - 1 - 1 + n11] = array6[n22 - 1 + n11] / 2;
                --n22;
            }
            ++n21;
            i *= 2;
        }
        int n23 = 2;
        for (int k = i - 2 + 1; k > 0; --k) {
            array6[n23 - 1 + n11] += array6[n23 - 1 - 1 + n11];
            ++n23;
        }
        final int n24 = i - 1;
        int n25 = 1;
        for (int l = n24 - 1 + 1; l > 0; --l) {
            final int n26 = array6[n25 - 1 + n11] + 1;
            final int n27 = n26 - 1;
            array[n27 - 1 + n4] -= Math.abs(array2[n27 - 1 + n5]);
            array[n26 - 1 + n4] -= Math.abs(array2[n27 - 1 + n5]);
            ++n25;
        }
        final int n28 = 4 * n3 + 3;
        if (n != 2) {
            int n29 = (int)(Math.log(n3) / Math.log(2.0));
            if (Math.pow(2, n29) < n3) {
                ++n29;
            }
            if (Math.pow(2, n29) < n3) {
                ++n29;
            }
            n17 = n28 + n3 + 1;
            n16 = n17 + n3 * n29;
            n19 = n16 + n3 * n29;
            n15 = n19 + n3 + 2;
            n13 = n15 + n3 * n29;
            n14 = 1;
            n18 = n14 + 2 * n3 * n29;
            n20 = (int)(n18 + Math.pow(n3, 2) + 1);
            int n30 = 0;
            for (int n31 = i - 0 + 1; n31 > 0; --n31) {
                array6[n15 + n30 - 1 + n11] = (array6[n17 + n30 - 1 + n11] = 1);
                ++n30;
            }
            array6[n19 - 1 + n11] = 1;
        }
        int n32 = 0;
        int n33 = 0;
        int n34 = n24 - 0 + 1;
    Label_2135:
        while (true) {
            while (n34 > 0) {
                int n35;
                int n36;
                if (n33 == 0) {
                    n35 = 1;
                    n36 = array6[1 - 1 + n11];
                }
                else {
                    n35 = array6[n33 - 1 + n11] + 1;
                    n36 = array6[n33 + 1 - 1 + n11] - array6[n33 - 1 + n11];
                }
                if (n == 2) {
                    Dsteqr.dsteqr("I", n36, array, n35 - 1 + n4, array2, n35 - 1 + n5, array3, n35 - 1 + (n35 - 1) * n7 + n6, n7, array5, n10, intW);
                    if (intW.val != 0) {
                        break Label_2135;
                    }
                }
                else {
                    Dsteqr.dsteqr("I", n36, array, n35 - 1 + n4, array2, n35 - 1 + n5, array5, n18 - 1 + array6[n19 + n32 - 1 + n11] - 1 + n10, n36, array5, n10, intW);
                    if (intW.val != 0) {
                        break Label_2135;
                    }
                    if (n == 1) {
                        Dgemm.dgemm("N", "N", n2, n36, n36, 1.0, array3, 1 - 1 + (n35 - 1) * n7 + n6, n7, array5, n18 - 1 + array6[n19 + n32 - 1 + n11] - 1 + n10, n36, 0.0, array4, 1 - 1 + (n35 - 1) * n9 + n8, n9);
                    }
                    array6[n19 + n32 + 1 - 1 + n11] = (int)(array6[n19 + n32 - 1 + n11] + Math.pow(n36, 2));
                    ++n32;
                }
                int n37 = 1;
                int n38 = n35;
                for (int n39 = array6[n33 + 1 - 1 + n11] - n35 + 1; n39 > 0; --n39) {
                    array6[n28 + n38 - 1 + n11] = n37;
                    ++n37;
                    ++n38;
                }
                ++n33;
                --n34;
                continue;
                intW.val = n35 * (n3 + 1) + n35 + n36 - 1;
                return;
            }
            for (int n40 = 1; i > 1; i /= 2, ++n40) {
                final int n41 = i - 2;
                int n42 = 0;
                for (int n43 = (n41 - 0 + 2) / 2; n43 > 0; --n43) {
                    int n35;
                    int n36;
                    int n44;
                    if (n42 == 0) {
                        n35 = 1;
                        n36 = array6[2 - 1 + n11];
                        n44 = array6[1 - 1 + n11];
                        n12 = 0;
                    }
                    else {
                        n35 = array6[n42 - 1 + n11] + 1;
                        n36 = array6[n42 + 2 - 1 + n11] - array6[n42 - 1 + n11];
                        n44 = n36 / 2;
                        ++n12;
                    }
                    if (n == 2) {
                        dlaed1_adapter(n36, array, n35 - 1 + n4, array3, n35 - 1 + (n35 - 1) * n7 + n6, n7, array6, n28 + n35 - 1 + n11, array2, n35 + n44 - 1 - 1 + n5, n44, array5, n10, array6, i + 1 - 1 + n11, intW);
                    }
                    else {
                        dlaed7_adapter(n, n36, n2, n21, n40, n12, array, n35 - 1 + n4, array4, 1 - 1 + (n35 - 1) * n9 + n8, n9, array6, n28 + n35 - 1 + n11, array2, n35 + n44 - 1 - 1 + n5, n44, array5, n18 - 1 + n10, array6, n19 - 1 + n11, array6, n17 - 1 + n11, array6, n16 - 1 + n11, array6, n15 - 1 + n11, array6, n13 - 1 + n11, array5, n14 - 1 + n10, array5, n20 - 1 + n10, array6, i + 1 - 1 + n11, intW);
                    }
                    if (intW.val != 0) {
                        continue Label_2135;
                    }
                    array6[n42 / 2 + 1 - 1 + n11] = array6[n42 + 2 - 1 + n11];
                    n42 += 2;
                }
            }
            break;
        }
        if (n == 1) {
            int n45 = 1;
            for (int n46 = n3 - 1 + 1; n46 > 0; --n46) {
                final int n47 = array6[n28 + n45 - 1 + n11];
                array5[n45 - 1 + n10] = array[n47 - 1 + n4];
                Dcopy.dcopy(n2, array4, 1 - 1 + (n47 - 1) * n9 + n8, 1, array3, 1 - 1 + (n45 - 1) * n7 + n6, 1);
                ++n45;
            }
            Dcopy.dcopy(n3, array5, n10, 1, array, n4, 1);
        }
        else if (n == 2) {
            int n48 = 1;
            for (int n49 = n3 - 1 + 1; n49 > 0; --n49) {
                final int n50 = array6[n28 + n48 - 1 + n11];
                array5[n48 - 1 + n10] = array[n50 - 1 + n4];
                Dcopy.dcopy(n3, array3, 1 - 1 + (n50 - 1) * n7 + n6, 1, array5, n3 * n48 + 1 - 1 + n10, 1);
                ++n48;
            }
            Dcopy.dcopy(n3, array5, n10, 1, array, n4, 1);
            Dlacpy.dlacpy("A", n3, n3, array5, n3 + 1 - 1 + n10, n3, array3, n6, n7);
        }
        else {
            int n51 = 1;
            for (int n52 = n3 - 1 + 1; n52 > 0; --n52) {
                array5[n51 - 1 + n10] = array[array6[n28 + n51 - 1 + n11] - 1 + n4];
                ++n51;
            }
            Dcopy.dcopy(n3, array5, n10, 1, array, n4, 1);
        }
    }
    
    private static void dlaed1_adapter(final int n, final double[] array, final int n2, final double[] array2, final int n3, final int n4, final int[] array3, final int n5, final double[] array4, final int n6, final int n7, final double[] array5, final int n8, final int[] array6, final int n9, final intW intW) {
        final doubleW doubleW = new doubleW(array4[n6]);
        Dlaed1.dlaed1(n, array, n2, array2, n3, n4, array3, n5, doubleW, n7, array5, n8, array6, n9, intW);
        array4[n6] = doubleW.val;
    }
    
    private static void dlaed7_adapter(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final double[] array, final int n7, final double[] array2, final int n8, final int n9, final int[] array3, final int n10, final double[] array4, final int n11, final int n12, final double[] array5, final int n13, final int[] array6, final int n14, final int[] array7, final int n15, final int[] array8, final int n16, final int[] array9, final int n17, final int[] array10, final int n18, final double[] array11, final int n19, final double[] array12, final int n20, final int[] array13, final int n21, final intW intW) {
        final doubleW doubleW = new doubleW(array4[n11]);
        Dlaed7.dlaed7(n, n2, n3, n4, n5, n6, array, n7, array2, n8, n9, array3, n10, doubleW, n12, array5, n13, array6, n14, array7, n15, array8, n16, array9, n17, array10, n18, array11, n19, array12, n20, array13, n21, intW);
        array4[n11] = doubleW.val;
    }
}
