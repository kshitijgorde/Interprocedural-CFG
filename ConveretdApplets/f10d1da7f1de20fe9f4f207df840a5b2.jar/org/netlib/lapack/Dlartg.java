// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.util.doubleW;

public final class Dlartg
{
    public static boolean first;
    public static double safmin;
    public static double safmn2;
    public static double safmx2;
    
    static {
        Dlartg.safmin = 0.0;
        Dlartg.safmn2 = 0.0;
        Dlartg.safmx2 = 0.0;
        Dlartg.first = true;
    }
    
    public static void dlartg(final double val, final double val2, final doubleW doubleW, final doubleW doubleW2, final doubleW doubleW3) {
        if (Dlartg.first) {
            Dlartg.first = false;
            Dlartg.safmin = Dlamch.dlamch("S");
            Dlartg.safmn2 = Math.pow(Dlamch.dlamch("B"), (int)(Math.log(Dlartg.safmin / Dlamch.dlamch("E")) / Math.log(Dlamch.dlamch("B")) / 2.0));
            Dlartg.safmx2 = 1.0 / Dlartg.safmn2;
        }
        if (val2 == 0.0) {
            doubleW.val = 1.0;
            doubleW2.val = 0.0;
            doubleW3.val = val;
        }
        else if (val == 0.0) {
            doubleW.val = 0.0;
            doubleW2.val = 1.0;
            doubleW3.val = val2;
        }
        else {
            double n = val;
            double n2 = val2;
            final double max = Math.max(Math.abs(n), Math.abs(n2));
            if (max >= Dlartg.safmx2) {
                int n3 = 0;
                do {
                    ++n3;
                    n *= Dlartg.safmn2;
                    n2 *= Dlartg.safmn2;
                } while (Math.max(Math.abs(n), Math.abs(n2)) >= Dlartg.safmx2);
                doubleW3.val = Math.sqrt(Math.pow(n, 2) + Math.pow(n2, 2));
                doubleW.val = n / doubleW3.val;
                doubleW2.val = n2 / doubleW3.val;
                int n4 = 1;
                for (int i = n3 - 1 + 1; i > 0; --i) {
                    doubleW3.val *= Dlartg.safmx2;
                    ++n4;
                }
            }
            else if (max <= Dlartg.safmn2) {
                int n5 = 0;
                do {
                    ++n5;
                    n *= Dlartg.safmx2;
                    n2 *= Dlartg.safmx2;
                } while (Math.max(Math.abs(n), Math.abs(n2)) <= Dlartg.safmn2);
                doubleW3.val = Math.sqrt(Math.pow(n, 2) + Math.pow(n2, 2));
                doubleW.val = n / doubleW3.val;
                doubleW2.val = n2 / doubleW3.val;
                int n6 = 1;
                for (int j = n5 - 1 + 1; j > 0; --j) {
                    doubleW3.val *= Dlartg.safmn2;
                    ++n6;
                }
            }
            else {
                doubleW3.val = Math.sqrt(Math.pow(n, 2) + Math.pow(n2, 2));
                doubleW.val = n / doubleW3.val;
                doubleW2.val = n2 / doubleW3.val;
            }
            if (Math.abs(val) > Math.abs(val2) && doubleW.val < 0.0) {
                doubleW.val = -doubleW.val;
                doubleW2.val = -doubleW2.val;
                doubleW3.val = -doubleW3.val;
            }
        }
    }
}
