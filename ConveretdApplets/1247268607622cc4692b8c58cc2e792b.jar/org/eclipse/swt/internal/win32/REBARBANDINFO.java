// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class REBARBANDINFO
{
    public int cbSize;
    public int fMask;
    public int fStyle;
    public int clrFore;
    public int clrBack;
    public int lpText;
    public int cch;
    public int iImage;
    public int hwndChild;
    public int cxMinChild;
    public int cyMinChild;
    public int cx;
    public int hbmBack;
    public int wID;
    public int cyChild;
    public int cyMaxChild;
    public int cyIntegral;
    public int cxIdeal;
    public int lParam;
    public int cxHeader;
    public static final int sizeof;
    
    static {
        sizeof = OS.REBARBANDINFO_sizeof();
    }
}
