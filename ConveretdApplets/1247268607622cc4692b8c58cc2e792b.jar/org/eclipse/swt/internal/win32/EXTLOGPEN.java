// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class EXTLOGPEN
{
    public int elpPenStyle;
    public int elpWidth;
    public int elpBrushStyle;
    public int elpColor;
    public int elpHatch;
    public int elpNumEntries;
    public int[] elpStyleEntry;
    public static final int sizeof;
    
    static {
        sizeof = OS.EXTLOGPEN_sizeof();
    }
    
    public EXTLOGPEN() {
        this.elpStyleEntry = new int[1];
    }
}
