// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.util.intW;

public final class Dlamc4
{
    public static void dlamc4(final intW intW, final double n, final int n2) {
        double n3 = n;
        final double n4 = 1 / n2;
        final double n5 = 0;
        intW.val = 1;
        double n6 = Dlamc3.dlamc3(n3 * n4, n5);
        double dlamc3 = n3;
        double dlamc4 = n3;
        double n7 = n3;
        double n8 = n3;
        while (dlamc3 == n3 && dlamc4 == n3 && n7 == n3 && n8 == n3) {
            --intW.val;
            n3 = n6;
            n6 = Dlamc3.dlamc3(n3 / n2, n5);
            dlamc3 = Dlamc3.dlamc3(n6 * n2, n5);
            n7 = n5;
            int n9 = 1;
            for (int i = n2 - 1 + 1; i > 0; --i) {
                n7 += n6;
                ++n9;
            }
            final double dlamc5 = Dlamc3.dlamc3(n3 * n4, n5);
            dlamc4 = Dlamc3.dlamc3(dlamc5 / n4, n5);
            n8 = n5;
            int n10 = 1;
            for (int j = n2 - 1 + 1; j > 0; --j) {
                n8 += dlamc5;
                ++n10;
            }
        }
    }
}
