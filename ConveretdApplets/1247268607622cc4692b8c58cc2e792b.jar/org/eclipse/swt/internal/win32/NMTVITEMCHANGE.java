// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NMTVITEMCHANGE extends NMHDR
{
    public int uChanged;
    public int hItem;
    public int uStateNew;
    public int uStateOld;
    public int lParam;
    public static int sizeof;
    
    static {
        NMTVITEMCHANGE.sizeof = OS.NMTVITEMCHANGE_sizeof();
    }
}
