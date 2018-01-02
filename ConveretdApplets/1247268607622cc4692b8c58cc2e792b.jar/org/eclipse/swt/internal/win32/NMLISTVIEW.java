// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NMLISTVIEW extends NMHDR
{
    public int iItem;
    public int iSubItem;
    public int uNewState;
    public int uOldState;
    public int uChanged;
    public int x;
    public int y;
    public int lParam;
    public static int sizeof;
    
    static {
        NMLISTVIEW.sizeof = OS.NMLISTVIEW_sizeof();
    }
}
