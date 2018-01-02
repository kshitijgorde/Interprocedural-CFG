// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class TVITEM
{
    public int mask;
    public int hItem;
    public int state;
    public int stateMask;
    public int pszText;
    public int cchTextMax;
    public int iImage;
    public int iSelectedImage;
    public int cChildren;
    public int lParam;
    public static final int sizeof;
    
    static {
        sizeof = OS.TVITEM_sizeof();
    }
}
