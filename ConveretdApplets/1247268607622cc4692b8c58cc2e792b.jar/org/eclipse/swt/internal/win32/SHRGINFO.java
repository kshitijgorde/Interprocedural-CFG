// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class SHRGINFO
{
    public int cbSize;
    public int hwndClient;
    public int ptDown_x;
    public int ptDown_y;
    public int dwFlags;
    public static final int sizeof;
    
    static {
        sizeof = OS.SHRGINFO_sizeof();
    }
}
