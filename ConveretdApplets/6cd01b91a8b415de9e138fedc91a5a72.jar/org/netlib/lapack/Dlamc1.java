// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.util.booleanW;
import org.netlib.util.intW;

public final class Dlamc1
{
    public static boolean first;
    public static boolean lieee1;
    public static boolean lrnd;
    public static int lbeta;
    public static int lt;
    
    static {
        Dlamc1.lieee1 = false;
        Dlamc1.lrnd = false;
        Dlamc1.lbeta = 0;
        Dlamc1.lt = 0;
        Dlamc1.first = true;
    }
    
    public static void dlamc1(final intW intW, final intW intW2, final booleanW booleanW, final booleanW booleanW2) {
        if (Dlamc1.first) {
            Dlamc1.first = false;
            final double n = 1;
            double n2 = 1;
            for (double dlamc3 = 1; dlamc3 == n; dlamc3 = Dlamc3.dlamc3(Dlamc3.dlamc3(n2, n), -n2)) {
                n2 *= 2;
            }
            double n3;
            double n4;
            for (n3 = 1, n4 = Dlamc3.dlamc3(n2, n3); n4 == n2; n4 = Dlamc3.dlamc3(n2, n3)) {
                n3 *= 2;
            }
            final double n5 = n / 4;
            final double n6 = n4;
            Dlamc1.lbeta = (int)(Dlamc3.dlamc3(n4, -n2) + n5);
            final double n7 = Dlamc1.lbeta;
            if (Dlamc3.dlamc3(Dlamc3.dlamc3(n7 / 2, -(n7 / 100)), n2) == n2) {
                Dlamc1.lrnd = true;
            }
            else {
                Dlamc1.lrnd = false;
            }
            final double dlamc4 = Dlamc3.dlamc3(Dlamc3.dlamc3(n7 / 2, n7 / 100), n2);
            if (Dlamc1.lrnd && dlamc4 == n2) {
                Dlamc1.lrnd = false;
            }
            final double dlamc5 = Dlamc3.dlamc3(n7 / 2, n2);
            final double dlamc6 = Dlamc3.dlamc3(n7 / 2, n6);
            Dlamc1.lieee1 = (dlamc5 == n2 && dlamc6 > n6 && Dlamc1.lrnd);
            Dlamc1.lt = 0;
            for (double n8 = 1, dlamc7 = 1; dlamc7 == n; dlamc7 = Dlamc3.dlamc3(Dlamc3.dlamc3(n8, n), -n8)) {
                ++Dlamc1.lt;
                n8 *= Dlamc1.lbeta;
            }
        }
        intW.val = Dlamc1.lbeta;
        intW2.val = Dlamc1.lt;
        booleanW.val = Dlamc1.lrnd;
        booleanW2.val = Dlamc1.lieee1;
    }
}
