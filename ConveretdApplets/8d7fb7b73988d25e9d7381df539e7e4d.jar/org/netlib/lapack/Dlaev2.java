// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.util.doubleW;

public final class Dlaev2
{
    public static void dlaev2(final double n, final double n2, final double n3, final doubleW doubleW, final doubleW doubleW2, final doubleW doubleW3, final doubleW doubleW4) {
        final double n4 = n + n3;
        final double n5 = n - n3;
        final double abs = Math.abs(n5);
        final double n6 = n2 + n2;
        final double abs2 = Math.abs(n6);
        double n7;
        double n8;
        if (Math.abs(n) > Math.abs(n3)) {
            n7 = n;
            n8 = n3;
        }
        else {
            n7 = n3;
            n8 = n;
        }
        double n9;
        if (abs > abs2) {
            n9 = abs * Math.sqrt(1.0 + Math.pow(abs2 / abs, 2));
        }
        else if (abs < abs2) {
            n9 = abs2 * Math.sqrt(1.0 + Math.pow(abs / abs2, 2));
        }
        else {
            n9 = abs2 * Math.sqrt(2.0);
        }
        int n10;
        if (n4 < 0.0) {
            doubleW.val = 0.5 * (n4 - n9);
            n10 = -1;
            doubleW2.val = n7 / doubleW.val * n8 - n2 / doubleW.val * n2;
        }
        else if (n4 > 0.0) {
            doubleW.val = 0.5 * (n4 + n9);
            n10 = 1;
            doubleW2.val = n7 / doubleW.val * n8 - n2 / doubleW.val * n2;
        }
        else {
            doubleW.val = 0.5 * n9;
            doubleW2.val = -(0.5 * n9);
            n10 = 1;
        }
        double n11;
        int n12;
        if (n5 >= 0.0) {
            n11 = n5 + n9;
            n12 = 1;
        }
        else {
            n11 = n5 - n9;
            n12 = -1;
        }
        if (Math.abs(n11) > abs2) {
            final double n13 = -(n6 / n11);
            doubleW4.val = 1.0 / Math.sqrt(1.0 + n13 * n13);
            doubleW3.val = n13 * doubleW4.val;
        }
        else if (abs2 == 0.0) {
            doubleW3.val = 1.0;
            doubleW4.val = 0.0;
        }
        else {
            final double n14 = -(n11 / n6);
            doubleW3.val = 1.0 / Math.sqrt(1.0 + n14 * n14);
            doubleW4.val = n14 * doubleW3.val;
        }
        if (n10 == n12) {
            final double val = doubleW3.val;
            doubleW3.val = -doubleW4.val;
            doubleW4.val = val;
        }
    }
}
