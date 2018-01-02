// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class DOCHOSTUIINFO
{
    public int cbSize;
    public int dwFlags;
    public int dwDoubleClick;
    public int pchHostCss;
    public int pchHostNS;
    public static final int sizeof;
    
    static {
        sizeof = OS.DOCHOSTUIINFO_sizeof();
    }
}
