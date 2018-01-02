// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class SCROLLBARINFO
{
    public int cbSize;
    public RECT rcScrollBar;
    public int dxyLineButton;
    public int xyThumbTop;
    public int xyThumbBottom;
    public int reserved;
    public int[] rgstate;
    public static final int sizeof;
    
    static {
        sizeof = OS.SCROLLBARINFO_sizeof();
    }
    
    public SCROLLBARINFO() {
        this.rcScrollBar = new RECT();
        this.rgstate = new int[6];
    }
}
