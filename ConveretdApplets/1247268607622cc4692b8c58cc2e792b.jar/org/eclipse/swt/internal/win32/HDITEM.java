// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class HDITEM
{
    public int mask;
    public int cxy;
    public int pszText;
    public int hbm;
    public int cchTextMax;
    public int fmt;
    public int lParam;
    public int iImage;
    public int iOrder;
    public int type;
    public int pvFilter;
    public static int sizeof;
    
    static {
        HDITEM.sizeof = OS.HDITEM_sizeof();
    }
}
