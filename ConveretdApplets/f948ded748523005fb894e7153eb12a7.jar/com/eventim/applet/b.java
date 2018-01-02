// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet;

import java.util.Comparator;

final class b implements Comparator
{
    private final double[] a;
    
    b(final double[] a) {
        this.a = a;
    }
    
    public final int compare(final Object o, final Object o2) {
        final int intValue = (int)o;
        final int intValue2 = (int)o2;
        final double n = this.a[intValue];
        final double n2 = this.a[intValue2];
        if (n < n2) {
            return 1;
        }
        if (n > n2) {
            return -1;
        }
        return 0;
    }
}
