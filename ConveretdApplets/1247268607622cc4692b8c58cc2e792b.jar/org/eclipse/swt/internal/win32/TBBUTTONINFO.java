// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class TBBUTTONINFO
{
    public int cbSize;
    public int dwMask;
    public int idCommand;
    public int iImage;
    public byte fsState;
    public byte fsStyle;
    public short cx;
    public int lParam;
    public int pszText;
    public int cchText;
    public static final int sizeof;
    
    static {
        sizeof = OS.TBBUTTONINFO_sizeof();
    }
}
