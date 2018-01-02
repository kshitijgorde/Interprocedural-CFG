// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public final class SHDRAGIMAGE
{
    public SIZE sizeDragImage;
    public POINT ptOffset;
    public int hbmpDragImage;
    public int crColorKey;
    public static final int sizeof;
    
    static {
        sizeof = OS.SHDRAGIMAGE_sizeof();
    }
    
    public SHDRAGIMAGE() {
        this.sizeDragImage = new SIZE();
        this.ptOffset = new POINT();
    }
}
