// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class LVHITTESTINFO
{
    public int x;
    public int y;
    public int flags;
    public int iItem;
    public int iSubItem;
    public static int sizeof;
    
    static {
        LVHITTESTINFO.sizeof = OS.LVHITTESTINFO_sizeof();
    }
}
