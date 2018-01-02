// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.util.intW;
import org.netlib.util.booleanW;
import org.netlib.util.doubleW;

public final class Dlamch
{
    public static boolean first;
    public static double base;
    public static double emax;
    public static double emin;
    public static doubleW eps;
    public static double prec;
    public static doubleW rmax;
    public static doubleW rmin;
    public static double rnd;
    public static double sfmin;
    public static double t;
    
    static {
        Dlamch.base = 0.0;
        Dlamch.emax = 0.0;
        Dlamch.emin = 0.0;
        Dlamch.eps = new doubleW(0.0);
        Dlamch.prec = 0.0;
        Dlamch.rmax = new doubleW(0.0);
        Dlamch.rmin = new doubleW(0.0);
        Dlamch.rnd = 0.0;
        Dlamch.sfmin = 0.0;
        Dlamch.t = 0.0;
        Dlamch.first = true;
    }
    
    public static double dlamch(final String s) {
        final booleanW booleanW = new booleanW(false);
        final intW intW = new intW(0);
        final intW intW2 = new intW(0);
        final intW intW3 = new intW(0);
        final intW intW4 = new intW(0);
        double n = 0.0;
        if (Dlamch.first) {
            Dlamch.first = false;
            Dlamc2.dlamc2(intW, intW4, booleanW, Dlamch.eps, intW3, Dlamch.rmin, intW2, Dlamch.rmax);
            Dlamch.base = intW.val;
            Dlamch.t = intW4.val;
            if (booleanW.val) {
                Dlamch.rnd = 1.0;
                Dlamch.eps.val = Math.pow(Dlamch.base, 1 - intW4.val) / 2;
            }
            else {
                Dlamch.rnd = 0.0;
                Dlamch.eps.val = Math.pow(Dlamch.base, 1 - intW4.val);
            }
            Dlamch.prec = Dlamch.eps.val * Dlamch.base;
            Dlamch.emin = intW3.val;
            Dlamch.emax = intW2.val;
            Dlamch.sfmin = Dlamch.rmin.val;
            final double n2 = 1.0 / Dlamch.rmax.val;
            if (n2 >= Dlamch.sfmin) {
                Dlamch.sfmin = n2 * (1.0 + Dlamch.eps.val);
            }
        }
        if (Lsame.lsame(s, "E")) {
            n = Dlamch.eps.val;
        }
        else if (Lsame.lsame(s, "S")) {
            n = Dlamch.sfmin;
        }
        else if (Lsame.lsame(s, "B")) {
            n = Dlamch.base;
        }
        else if (Lsame.lsame(s, "P")) {
            n = Dlamch.prec;
        }
        else if (Lsame.lsame(s, "N")) {
            n = Dlamch.t;
        }
        else if (Lsame.lsame(s, "R")) {
            n = Dlamch.rnd;
        }
        else if (Lsame.lsame(s, "M")) {
            n = Dlamch.emin;
        }
        else if (Lsame.lsame(s, "U")) {
            n = Dlamch.rmin.val;
        }
        else if (Lsame.lsame(s, "L")) {
            n = Dlamch.emax;
        }
        else if (Lsame.lsame(s, "O")) {
            n = Dlamch.rmax.val;
        }
        return n;
    }
}
