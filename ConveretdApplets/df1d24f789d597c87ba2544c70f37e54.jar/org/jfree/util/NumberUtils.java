// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

public abstract class NumberUtils
{
    private static double doubleEpsilon;
    
    public static boolean equal(final double n, final double n2) {
        return Math.abs(n - n2) < NumberUtils.doubleEpsilon;
    }
    
    static {
        NumberUtils.doubleEpsilon = 1.0E-10;
    }
}
