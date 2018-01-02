// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.util.doubleW;
import org.netlib.util.intW;

public final class Dlamc5
{
    public static void dlamc5(final int n, final int n2, final int n3, final boolean b, final intW intW, final doubleW doubleW) {
        double n4 = 0.0;
        int n5 = 1;
        int n6 = 1;
        int n7;
        while (true) {
            n7 = n5 * 2;
            if (n7 > -n3) {
                break;
            }
            n5 = n7;
            ++n6;
        }
        int n8;
        if (n5 == -n3) {
            n8 = n5;
        }
        else {
            n8 = n7;
            ++n6;
        }
        int n9;
        if (n8 + n3 > -n5 - n3) {
            n9 = 2 * n5;
        }
        else {
            n9 = 2 * n8;
        }
        intW.val = n9 + n3 - 1;
        if ((1 + n6 + n2) % 2 == 1 && n == 2) {
            --intW.val;
        }
        if (b) {
            --intW.val;
        }
        final double n10 = 1.0 / n;
        double n11 = n - 1.0;
        double val = 0.0;
        int n12 = 1;
        for (int i = n2 - 1 + 1; i > 0; --i) {
            n11 *= n10;
            if (val < 1.0) {
                n4 = val;
            }
            val = Dlamc3.dlamc3(val, n11);
            ++n12;
        }
        if (val >= 1.0) {
            val = n4;
        }
        int n13 = 1;
        for (int j = intW.val - 1 + 1; j > 0; --j) {
            val = Dlamc3.dlamc3(val * n, 0.0);
            ++n13;
        }
        doubleW.val = val;
    }
}
