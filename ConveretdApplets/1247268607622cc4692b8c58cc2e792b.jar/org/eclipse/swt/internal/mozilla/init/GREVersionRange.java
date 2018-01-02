// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla.init;

public class GREVersionRange
{
    public int lower;
    public boolean lowerInclusive;
    public int upper;
    public boolean upperInclusive;
    public static final int sizeof;
    
    static {
        sizeof = XPCOMInit.GREVersionRange_sizeof();
    }
}
