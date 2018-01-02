// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class TCITEM
{
    public int mask;
    public int dwState;
    public int dwStateMask;
    public int pszText;
    public int cchTextMax;
    public int iImage;
    public int lParam;
    public static final int sizeof;
    
    static {
        sizeof = OS.TCITEM_sizeof();
    }
}
