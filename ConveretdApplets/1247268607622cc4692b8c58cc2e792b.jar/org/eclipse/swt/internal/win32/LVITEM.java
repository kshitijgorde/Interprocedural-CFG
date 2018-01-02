// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class LVITEM
{
    public int mask;
    public int iItem;
    public int iSubItem;
    public int state;
    public int stateMask;
    public int pszText;
    public int cchTextMax;
    public int iImage;
    public int lParam;
    public int iIndent;
    public int iGroupId;
    public int cColumns;
    public int puColumns;
    public static final int sizeof;
    
    static {
        sizeof = ((!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(5, 1)) ? OS.LVITEM_sizeof() : 40);
    }
}
