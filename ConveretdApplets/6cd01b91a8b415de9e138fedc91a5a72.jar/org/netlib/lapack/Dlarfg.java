// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.blas.Dscal;
import org.netlib.util.Util;
import org.netlib.blas.Dnrm2;
import org.netlib.util.doubleW;

public final class Dlarfg
{
    public static void dlarfg(final int n, final doubleW doubleW, final double[] array, final int n2, final int n3, final doubleW doubleW2) {
        if (n <= 1) {
            doubleW2.val = 0.0;
            return;
        }
        final double dnrm2 = Dnrm2.dnrm2(n - 1, array, n2, n3);
        if (dnrm2 == 0.0) {
            doubleW2.val = 0.0;
        }
        else {
            double val = -Util.dsign(Dlapy2.dlapy2(doubleW.val, dnrm2), doubleW.val);
            final double n4 = Dlamch.dlamch("S") / Dlamch.dlamch("E");
            if (Math.abs(val) < n4) {
                final double n5 = 1.0 / n4;
                int n6 = 0;
                do {
                    ++n6;
                    Dscal.dscal(n - 1, n5, array, n2, n3);
                    val *= n5;
                    doubleW.val *= n5;
                } while (Math.abs(val) < n4);
                final double val2 = -Util.dsign(Dlapy2.dlapy2(doubleW.val, Dnrm2.dnrm2(n - 1, array, n2, n3)), doubleW.val);
                doubleW2.val = (val2 - doubleW.val) / val2;
                Dscal.dscal(n - 1, 1.0 / (doubleW.val - val2), array, n2, n3);
                doubleW.val = val2;
                int n7 = 1;
                for (int i = n6 - 1 + 1; i > 0; --i) {
                    doubleW.val *= n4;
                    ++n7;
                }
            }
            else {
                doubleW2.val = (val - doubleW.val) / val;
                Dscal.dscal(n - 1, 1.0 / (doubleW.val - val), array, n2, n3);
                doubleW.val = val;
            }
        }
    }
}
