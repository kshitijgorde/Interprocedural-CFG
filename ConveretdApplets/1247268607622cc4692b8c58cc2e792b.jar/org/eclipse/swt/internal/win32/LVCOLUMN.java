// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class LVCOLUMN
{
    public int mask;
    public int fmt;
    public int cx;
    public int pszText;
    public int cchTextMax;
    public int iSubItem;
    public int iImage;
    public int iOrder;
    public static final int sizeof;
    
    static {
        sizeof = OS.LVCOLUMN_sizeof();
    }
}