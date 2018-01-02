// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class HIGHCONTRAST
{
    public int cbSize;
    public int dwFlags;
    public int lpszDefaultScheme;
    public static final int sizeof;
    
    static {
        sizeof = OS.HIGHCONTRAST_sizeof();
    }
}
