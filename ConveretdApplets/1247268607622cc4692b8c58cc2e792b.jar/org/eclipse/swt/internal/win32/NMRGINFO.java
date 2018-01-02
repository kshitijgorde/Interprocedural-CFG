// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NMRGINFO extends NMHDR
{
    public int x;
    public int y;
    public int dwItemSpec;
    public static int sizeof;
    
    static {
        NMRGINFO.sizeof = OS.NMRGINFO_sizeof();
    }
}
