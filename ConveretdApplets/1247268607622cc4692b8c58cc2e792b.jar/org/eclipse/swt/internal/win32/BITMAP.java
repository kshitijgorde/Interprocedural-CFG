// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class BITMAP
{
    public int bmType;
    public int bmWidth;
    public int bmHeight;
    public int bmWidthBytes;
    public short bmPlanes;
    public short bmBitsPixel;
    public int bmBits;
    public static final int sizeof;
    
    static {
        sizeof = OS.BITMAP_sizeof();
    }
}
