// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class PANOSE
{
    public byte bFamilyType;
    public byte bSerifStyle;
    public byte bWeight;
    public byte bProportion;
    public byte bContrast;
    public byte bStrokeVariation;
    public byte bArmStyle;
    public byte bLetterform;
    public byte bMidline;
    public byte bXHeight;
    public static final int sizeof;
    
    static {
        sizeof = OS.PANOSE_sizeof();
    }
}
