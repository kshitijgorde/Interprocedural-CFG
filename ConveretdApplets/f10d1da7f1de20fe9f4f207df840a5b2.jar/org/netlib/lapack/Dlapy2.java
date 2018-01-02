// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

public final class Dlapy2
{
    public static double dlapy2(final double n, final double n2) {
        final double abs = Math.abs(n);
        final double abs2 = Math.abs(n2);
        final double max = Math.max(abs, abs2);
        final double min = Math.min(abs, abs2);
        double n3;
        if (min == 0.0) {
            n3 = max;
        }
        else {
            n3 = max * Math.sqrt(1.0 + Math.pow(min / max, 2));
        }
        return n3;
    }
}
