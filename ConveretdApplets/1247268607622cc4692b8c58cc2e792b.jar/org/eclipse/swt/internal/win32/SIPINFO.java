// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class SIPINFO
{
    public int cbSize;
    public int fdwFlags;
    public int rcVisibleDesktop_left;
    public int rcVisibleDesktop_top;
    public int rcVisibleDesktop_right;
    public int rcVisibleDesktop_bottom;
    public int rcSipRect_left;
    public int rcSipRect_top;
    public int rcSipRect_right;
    public int rcSipRect_bottom;
    public int dwImDataSize;
    public int pvImData;
    public static final int sizeof;
    
    static {
        sizeof = OS.SIPINFO_sizeof();
    }
}
