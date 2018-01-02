// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class LOGBRUSH
{
    public int lbStyle;
    public int lbColor;
    public int lbHatch;
    public static final int sizeof;
    
    static {
        sizeof = OS.LOGBRUSH_sizeof();
    }
}
