// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class FLICK_DATA
{
    public int iFlickActionCommandCode;
    public byte iFlickDirection;
    public boolean fControlModifier;
    public boolean fMenuModifier;
    public boolean fAltGRModifier;
    public boolean fWinModifier;
    public boolean fShiftModifier;
    public int iReserved;
    public boolean fOnInkingSurface;
    public int iActionArgument;
    public static final int sizeof;
    
    static {
        sizeof = OS.FLICK_DATA_sizeof();
    }
}
