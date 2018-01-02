// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NMTOOLBAR extends NMHDR
{
    public int iItem;
    public int iBitmap;
    public int idCommand;
    public byte fsState;
    public byte fsStyle;
    public int dwData;
    public int iString;
    public int cchText;
    public int pszText;
    public int left;
    public int top;
    public int right;
    public int bottom;
    public static final int sizeof;
    
    static {
        sizeof = OS.NMTOOLBAR_sizeof();
    }
}
