// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.util.doubleW;

public final class Dlae2
{
    public static void dlae2(final double n, final double n2, final double n3, final doubleW doubleW, final doubleW doubleW2) {
        final double n4 = n + n3;
        final double abs = Math.abs(n - n3);
        final double abs2 = Math.abs(n2 + n2);
        double n5;
        double n6;
        if (Math.abs(n) > Math.abs(n3)) {
            n5 = n;
            n6 = n3;
        }
        else {
            n5 = n3;
            n6 = n;
        }
        double n7;
        if (abs > abs2) {
            n7 = abs * Math.sqrt(1.0 + Math.pow(abs2 / abs, 2));
        }
        else if (abs < abs2) {
            n7 = abs2 * Math.sqrt(1.0 + Math.pow(abs / abs2, 2));
        }
        else {
            n7 = abs2 * Math.sqrt(2.0);
        }
        if (n4 < 0.0) {
            doubleW.val = 0.5 * (n4 - n7);
            doubleW2.val = n5 / doubleW.val * n6 - n2 / doubleW.val * n2;
        }
        else if (n4 > 0.0) {
            doubleW.val = 0.5 * (n4 + n7);
            doubleW2.val = n5 / doubleW.val * n6 - n2 / doubleW.val * n2;
        }
        else {
            doubleW.val = 0.5 * n7;
            doubleW2.val = -(0.5 * n7);
        }
    }
}
