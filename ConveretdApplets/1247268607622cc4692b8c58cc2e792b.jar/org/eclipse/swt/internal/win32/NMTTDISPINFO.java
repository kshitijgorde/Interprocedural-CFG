// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public abstract class NMTTDISPINFO extends NMHDR
{
    public int lpszText;
    public int hinst;
    public int uFlags;
    public int lParam;
    public static final int sizeof;
    
    static {
        sizeof = (OS.IsUnicode ? OS.NMTTDISPINFOW_sizeof() : OS.NMTTDISPINFOA_sizeof());
    }
}
