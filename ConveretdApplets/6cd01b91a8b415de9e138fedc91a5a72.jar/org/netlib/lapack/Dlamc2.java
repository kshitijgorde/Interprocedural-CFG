// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.util.Util;
import org.netlib.util.booleanW;
import org.netlib.util.doubleW;
import org.netlib.util.intW;

public final class Dlamc2
{
    public static boolean first;
    public static boolean iwarn;
    public static intW lbeta;
    public static intW lemax;
    public static int lemin;
    public static intW lt;
    public static double leps;
    public static doubleW lrmax;
    public static double lrmin;
    
    static {
        Dlamc2.lbeta = new intW(0);
        Dlamc2.lemax = new intW(0);
        Dlamc2.lemin = 0;
        Dlamc2.lt = new intW(0);
        Dlamc2.leps = 0.0;
        Dlamc2.lrmax = new doubleW(0.0);
        Dlamc2.lrmin = 0.0;
        Dlamc2.iwarn = false;
        Dlamc2.first = true;
    }
    
    public static void dlamc2(final intW intW, final intW intW2, final booleanW booleanW, final doubleW doubleW, final intW intW3, final doubleW doubleW2, final intW intW4, final doubleW doubleW3) {
        final booleanW booleanW2 = new booleanW(false);
        final booleanW booleanW3 = new booleanW(false);
        final intW intW5 = new intW(0);
        final intW intW6 = new intW(0);
        final intW intW7 = new intW(0);
        final intW intW8 = new intW(0);
        if (Dlamc2.first) {
            Dlamc2.first = false;
            final double n = 0;
            final double n2 = 1;
            final double n3 = 2;
            Dlamc1.dlamc1(Dlamc2.lbeta, Dlamc2.lt, booleanW3, booleanW2);
            final double leps = Dlamc2.leps = Math.pow(Dlamc2.lbeta.val, -Dlamc2.lt.val);
            final double n4 = n3 / 3;
            final double n5 = n2 / 2;
            final double dlamc3 = Dlamc3.dlamc3(n4, -n5);
            double leps2 = Math.abs(Dlamc3.dlamc3(Dlamc3.dlamc3(Dlamc3.dlamc3(dlamc3, dlamc3), -n5), dlamc3));
            if (leps2 < Dlamc2.leps) {
                leps2 = Dlamc2.leps;
            }
            Dlamc2.leps = 1;
            while (Dlamc2.leps > leps2 && leps2 > n) {
                Dlamc2.leps = leps2;
                leps2 = Dlamc3.dlamc3(n5, Dlamc3.dlamc3(n5, -Dlamc3.dlamc3(n5, Dlamc3.dlamc3(n5, -Dlamc3.dlamc3(n5 * Dlamc2.leps, Math.pow(n3, 5) * Math.pow(Dlamc2.leps, 2))))));
            }
            if (leps < Dlamc2.leps) {
                Dlamc2.leps = leps;
            }
            final double n6 = n2 / Dlamc2.lbeta.val;
            double dlamc4 = n2;
            int n7 = 1;
            for (int i = 3 - 1 + 1; i > 0; --i) {
                dlamc4 = Dlamc3.dlamc3(dlamc4 * n6, n);
                ++n7;
            }
            final double dlamc5 = Dlamc3.dlamc3(n2, dlamc4);
            Dlamc4.dlamc4(intW8, n2, Dlamc2.lbeta.val);
            Dlamc4.dlamc4(intW7, -n2, Dlamc2.lbeta.val);
            Dlamc4.dlamc4(intW6, dlamc5, Dlamc2.lbeta.val);
            Dlamc4.dlamc4(intW5, -dlamc5, Dlamc2.lbeta.val);
            boolean b = false;
            if (intW8.val == intW7.val && intW6.val == intW5.val) {
                if (intW8.val == intW6.val) {
                    Dlamc2.lemin = intW8.val;
                }
                else if (intW6.val - intW8.val == 3) {
                    Dlamc2.lemin = intW8.val - 1 + Dlamc2.lt.val;
                    b = true;
                }
                else {
                    Dlamc2.lemin = Math.min(intW8.val, intW6.val);
                    Dlamc2.iwarn = true;
                }
            }
            else if (intW8.val == intW6.val && intW7.val == intW5.val) {
                if (Math.abs(intW8.val - intW7.val) == 1) {
                    Dlamc2.lemin = Math.max(intW8.val, intW7.val);
                }
                else {
                    Dlamc2.lemin = Math.min(intW8.val, intW7.val);
                    Dlamc2.iwarn = true;
                }
            }
            else if (Math.abs(intW8.val - intW7.val) == 1 && intW6.val == intW5.val) {
                if (intW6.val - Math.min(intW8.val, intW7.val) == 3) {
                    Dlamc2.lemin = Math.max(intW8.val, intW7.val) - 1 + Dlamc2.lt.val;
                }
                else {
                    Dlamc2.lemin = Math.min(intW8.val, intW7.val);
                    Dlamc2.iwarn = true;
                }
            }
            else {
                Dlamc2.lemin = Math.min(Util.min(intW8.val, intW7.val, intW6.val), intW5.val);
                Dlamc2.iwarn = true;
            }
            if (Dlamc2.iwarn) {
                Dlamc2.first = true;
                System.out.println("\n\n " + " WARNING. The value EMIN may be incorrect:-" + "  EMIN = " + Dlamc2.lemin + " " + "\n " + " If, after inspection, the value EMIN looks" + " acceptable please comment out " + "\n " + " the IF block as marked within the code of routine" + " DLAMC2," + "\n " + " otherwise supply EMIN explicitly." + "\n ");
            }
            final boolean b2 = b || booleanW2.val;
            Dlamc2.lrmin = 1;
            int n8 = 1;
            for (int j = 1 - Dlamc2.lemin - 1 + 1; j > 0; --j) {
                Dlamc2.lrmin = Dlamc3.dlamc3(Dlamc2.lrmin * n6, n);
                ++n8;
            }
            Dlamc5.dlamc5(Dlamc2.lbeta.val, Dlamc2.lt.val, Dlamc2.lemin, b2, Dlamc2.lemax, Dlamc2.lrmax);
        }
        intW.val = Dlamc2.lbeta.val;
        intW2.val = Dlamc2.lt.val;
        booleanW.val = booleanW3.val;
        doubleW.val = Dlamc2.leps;
        intW3.val = Dlamc2.lemin;
        doubleW2.val = Dlamc2.lrmin;
        intW4.val = Dlamc2.lemax.val;
        doubleW3.val = Dlamc2.lrmax.val;
    }
}
