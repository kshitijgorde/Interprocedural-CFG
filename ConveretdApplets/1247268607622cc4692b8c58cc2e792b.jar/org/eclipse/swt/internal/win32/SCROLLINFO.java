// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class SCROLLINFO
{
    public int cbSize;
    public int fMask;
    public int nMin;
    public int nMax;
    public int nPage;
    public int nPos;
    public int nTrackPos;
    public static final int sizeof;
    
    static {
        sizeof = OS.SCROLLINFO_sizeof();
    }
}
