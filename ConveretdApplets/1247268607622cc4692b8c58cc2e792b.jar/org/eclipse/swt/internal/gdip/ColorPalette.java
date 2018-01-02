// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.gdip;

public class ColorPalette
{
    public int Flags;
    public int Count;
    public int[] Entries;
    public static final int sizeof;
    
    static {
        sizeof = Gdip.ColorPalette_sizeof();
    }
    
    public ColorPalette() {
        this.Entries = new int[1];
    }
}
