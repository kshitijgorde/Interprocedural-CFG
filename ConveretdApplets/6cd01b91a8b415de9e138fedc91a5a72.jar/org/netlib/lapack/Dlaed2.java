// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.blas.Drot;
import org.netlib.blas.Dcopy;
import org.netlib.blas.Idamax;
import org.netlib.blas.Dscal;
import org.netlib.err.Xerbla;
import org.netlib.util.doubleW;
import org.netlib.util.intW;

public final class Dlaed2
{
    public static void dlaed2(final intW intW, final int n, final int n2, final double[] array, final int n3, final double[] array2, final int n4, final int n5, final int[] array3, final int n6, final doubleW doubleW, final double[] array4, final int n7, final double[] array5, final int n8, final double[] array6, final int n9, final double[] array7, final int n10, final int[] array8, final int n11, final int[] array9, final int n12, final int[] array10, final int n13, final int[] array11, final int n14, final intW intW2) {
        final int[] array12 = new int[4];
        final int[] array13 = new int[4];
        int n15 = 0;
        intW2.val = 0;
        if (n < 0) {
            intW2.val = -2;
        }
        else if (n5 < Math.max(1, n)) {
            intW2.val = -6;
        }
        else if (Math.min(1, n / 2) > n2 || n / 2 < n2) {
            intW2.val = -3;
        }
        if (intW2.val != 0) {
            Xerbla.xerbla("DLAED2", -intW2.val);
            return;
        }
        if (n == 0) {
            return;
        }
        final int n16 = n - n2;
        final int n17 = n2 + 1;
        if (doubleW.val < 0.0) {
            Dscal.dscal(n16, -1.0, array4, n17 - 1 + n7, 1);
        }
        Dscal.dscal(n, 1.0 / Math.sqrt(2.0), array4, n7, 1);
        doubleW.val = Math.abs(2.0 * doubleW.val);
        int n18 = n17;
        for (int i = n - n17 + 1; i > 0; --i) {
            array3[n18 - 1 + n6] += n2;
            ++n18;
        }
        int n19 = 1;
        for (int j = n - 1 + 1; j > 0; --j) {
            array5[n19 - 1 + n8] = array[array3[n19 - 1 + n6] - 1 + n3];
            ++n19;
        }
        Dlamrg.dlamrg(n2, n16, array5, n8, 1, 1, array9, n12);
        int n20 = 1;
        for (int k = n - 1 + 1; k > 0; --k) {
            array8[n20 - 1 + n11] = array3[array9[n20 - 1 + n12] - 1 + n6];
            ++n20;
        }
        final int idamax = Idamax.idamax(n, array4, n7, 1);
        final double n21 = 8.0 * Dlamch.dlamch("Epsilon") * Math.max(Math.abs(array[Idamax.idamax(n, array, n3, 1) - 1 + n3]), Math.abs(array4[idamax - 1 + n7]));
        if (doubleW.val * Math.abs(array4[idamax - 1 + n7]) > n21) {
            int n22 = 1;
            for (int l = n2 - 1 + 1; l > 0; --l) {
                array11[n22 - 1 + n14] = 1;
                ++n22;
            }
            int n23 = n17;
            for (int n24 = n - n17 + 1; n24 > 0; --n24) {
                array11[n23 - 1 + n14] = 3;
                ++n23;
            }
            intW.val = 0;
            int n25 = n + 1;
            int n26 = 1;
            while (true) {
                for (int n27 = n - 1 + 1; n27 > 0; --n27) {
                    final int n28 = array8[n26 - 1 + n11];
                    if (doubleW.val * Math.abs(array4[n28 - 1 + n7]) > n21) {
                        n15 = n28;
                        break;
                    }
                    --n25;
                    array11[n28 - 1 + n14] = 4;
                    array10[n25 - 1 + n13] = n28;
                    if (n26 == n) {
                        ++intW.val;
                        array5[intW.val - 1 + n8] = array[n15 - 1 + n3];
                        array6[intW.val - 1 + n9] = array4[n15 - 1 + n7];
                        array10[intW.val - 1 + n13] = n15;
                        int n29 = 1;
                        for (int n30 = 4 - 1 + 1; n30 > 0; --n30) {
                            array12[n29 - 1] = 0;
                            ++n29;
                        }
                        int n31 = 1;
                        for (int n32 = n - 1 + 1; n32 > 0; --n32) {
                            final int n33 = array11[n31 - 1 + n14];
                            ++array12[n33 - 1];
                            ++n31;
                        }
                        array13[1 - 1] = 1;
                        array13[2 - 1] = 1 + array12[1 - 1];
                        array13[3 - 1] = array13[2 - 1] + array12[2 - 1];
                        array13[4 - 1] = array13[3 - 1] + array12[3 - 1];
                        intW.val = n - array12[4 - 1];
                        int n34 = 1;
                        for (int n35 = n - 1 + 1; n35 > 0; --n35) {
                            final int n36 = array10[n34 - 1 + n13];
                            final int n37 = array11[n36 - 1 + n14];
                            array8[array13[n37 - 1] - 1 + n11] = n36;
                            array9[array13[n37 - 1] - 1 + n12] = n34;
                            ++array13[n37 - 1];
                            ++n34;
                        }
                        int n38 = 1;
                        int n39 = 1;
                        int n40 = 1 + (array12[1 - 1] + array12[2 - 1]) * n2;
                        int n41 = 1;
                        for (int n42 = array12[1 - 1] - 1 + 1; n42 > 0; --n42) {
                            final int n43 = array8[n38 - 1 + n11];
                            Dcopy.dcopy(n2, array2, 1 - 1 + (n43 - 1) * n5 + n4, 1, array7, n39 - 1 + n10, 1);
                            array4[n38 - 1 + n7] = array[n43 - 1 + n3];
                            ++n38;
                            n39 += n2;
                            ++n41;
                        }
                        int n44 = 1;
                        for (int n45 = array12[2 - 1] - 1 + 1; n45 > 0; --n45) {
                            final int n46 = array8[n38 - 1 + n11];
                            Dcopy.dcopy(n2, array2, 1 - 1 + (n46 - 1) * n5 + n4, 1, array7, n39 - 1 + n10, 1);
                            Dcopy.dcopy(n16, array2, n2 + 1 - 1 + (n46 - 1) * n5 + n4, 1, array7, n40 - 1 + n10, 1);
                            array4[n38 - 1 + n7] = array[n46 - 1 + n3];
                            ++n38;
                            n39 += n2;
                            n40 += n16;
                            ++n44;
                        }
                        int n47 = 1;
                        for (int n48 = array12[3 - 1] - 1 + 1; n48 > 0; --n48) {
                            final int n49 = array8[n38 - 1 + n11];
                            Dcopy.dcopy(n16, array2, n2 + 1 - 1 + (n49 - 1) * n5 + n4, 1, array7, n40 - 1 + n10, 1);
                            array4[n38 - 1 + n7] = array[n49 - 1 + n3];
                            ++n38;
                            n40 += n16;
                            ++n47;
                        }
                        final int n50 = n40;
                        int n51 = 1;
                        for (int n52 = array12[4 - 1] - 1 + 1; n52 > 0; --n52) {
                            final int n53 = array8[n38 - 1 + n11];
                            Dcopy.dcopy(n, array2, 1 - 1 + (n53 - 1) * n5 + n4, 1, array7, n40 - 1 + n10, 1);
                            n40 += n;
                            array4[n38 - 1 + n7] = array[n53 - 1 + n3];
                            ++n38;
                            ++n51;
                        }
                        Dlacpy.dlacpy("A", n, array12[4 - 1], array7, n50 - 1 + n10, n, array2, 1 - 1 + (intW.val + 1 - 1) * n5 + n4, n5);
                        Dcopy.dcopy(n - intW.val, array4, intW.val + 1 - 1 + n7, 1, array, intW.val + 1 - 1 + n3, 1);
                        int n54 = 1;
                        for (int n55 = 4 - 1 + 1; n55 > 0; --n55) {
                            array11[n54 - 1 + n14] = array12[n54 - 1];
                            ++n54;
                        }
                        return;
                    }
                    ++n26;
                }
                while (true) {
                    ++n26;
                    final int n56 = array8[n26 - 1 + n11];
                    if (n26 > n) {
                        break;
                    }
                    if (doubleW.val * Math.abs(array4[n56 - 1 + n7]) <= n21) {
                        --n25;
                        array11[n56 - 1 + n14] = 4;
                        array10[n25 - 1 + n13] = n56;
                    }
                    else {
                        final double n57 = array4[n15 - 1 + n7];
                        final double n58 = array4[n56 - 1 + n7];
                        final double dlapy2 = Dlapy2.dlapy2(n58, n57);
                        final double n59 = array[n56 - 1 + n3] - array[n15 - 1 + n3];
                        final double n60 = n58 / dlapy2;
                        final double n61 = -(n57 / dlapy2);
                        if (Math.abs(n59 * n60 * n61) <= n21) {
                            array4[n56 - 1 + n7] = dlapy2;
                            array4[n15 - 1 + n7] = 0.0;
                            if (array11[n56 - 1 + n14] != array11[n15 - 1 + n14]) {
                                array11[n56 - 1 + n14] = 2;
                            }
                            array11[n15 - 1 + n14] = 4;
                            Drot.drot(n, array2, 1 - 1 + (n15 - 1) * n5 + n4, 1, array2, 1 - 1 + (n56 - 1) * n5 + n4, 1, n60, n61);
                            final double n62 = array[n15 - 1 + n3] * Math.pow(n60, 2) + array[n56 - 1 + n3] * Math.pow(n61, 2);
                            array[n56 - 1 + n3] = array[n15 - 1 + n3] * Math.pow(n61, 2) + array[n56 - 1 + n3] * Math.pow(n60, 2);
                            array[n15 - 1 + n3] = n62;
                            while (true) {
                                int n63;
                                for (--n25, n63 = 1; n25 + n63 <= n; ++n63) {
                                    if (array[n15 - 1 + n3] >= array[array10[n25 + n63 - 1 + n13] - 1 + n3]) {
                                        array10[n25 + n63 - 1 - 1 + n13] = n15;
                                        n15 = n56;
                                        continue Label_1571;
                                    }
                                    array10[n25 + n63 - 1 - 1 + n13] = array10[n25 + n63 - 1 + n13];
                                    array10[n25 + n63 - 1 + n13] = n15;
                                }
                                array10[n25 + n63 - 1 - 1 + n13] = n15;
                                continue;
                            }
                        }
                        ++intW.val;
                        array5[intW.val - 1 + n8] = array[n15 - 1 + n3];
                        array6[intW.val - 1 + n9] = array4[n15 - 1 + n7];
                        array10[intW.val - 1 + n13] = n15;
                        n15 = n56;
                    }
                    Label_1571:;
                }
                continue;
            }
        }
        intW.val = 0;
        int n64 = 1;
        int n65 = 1;
        for (int n66 = n - 1 + 1; n66 > 0; --n66) {
            final int n67 = array8[n65 - 1 + n11];
            Dcopy.dcopy(n, array2, 1 - 1 + (n67 - 1) * n5 + n4, 1, array7, n64 - 1 + n10, 1);
            array5[n65 - 1 + n8] = array[n67 - 1 + n3];
            n64 += n;
            ++n65;
        }
        Dlacpy.dlacpy("A", n, n, array7, n10, n, array2, n4, n5);
        Dcopy.dcopy(n, array5, n8, 1, array, n3, 1);
    }
}
