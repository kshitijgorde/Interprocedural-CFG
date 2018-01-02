// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NMTTCUSTOMDRAW extends NMCUSTOMDRAW
{
    public int uDrawFlags;
    public static final int sizeof;
    
    static {
        sizeof = OS.NMTTCUSTOMDRAW_sizeof();
    }
}
