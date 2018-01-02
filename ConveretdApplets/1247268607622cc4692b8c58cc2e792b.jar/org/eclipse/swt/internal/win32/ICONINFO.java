// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class ICONINFO
{
    public boolean fIcon;
    public int xHotspot;
    public int yHotspot;
    public int hbmMask;
    public int hbmColor;
    public static final int sizeof;
    
    static {
        sizeof = OS.ICONINFO_sizeof();
    }
}
