// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class TVINSERTSTRUCT
{
    public int hParent;
    public int hInsertAfter;
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
    public int iIntegral;
    public static final int sizeof;
    
    static {
        sizeof = OS.TVINSERTSTRUCT_sizeof();
    }
}
