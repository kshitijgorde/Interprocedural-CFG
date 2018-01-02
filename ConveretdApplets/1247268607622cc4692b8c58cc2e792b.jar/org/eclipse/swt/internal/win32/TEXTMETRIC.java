// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public abstract class TEXTMETRIC
{
    public int tmHeight;
    public int tmAscent;
    public int tmDescent;
    public int tmInternalLeading;
    public int tmExternalLeading;
    public int tmAveCharWidth;
    public int tmMaxCharWidth;
    public int tmWeight;
    public int tmOverhang;
    public int tmDigitizedAspectX;
    public int tmDigitizedAspectY;
    public byte tmItalic;
    public byte tmUnderlined;
    public byte tmStruckOut;
    public byte tmPitchAndFamily;
    public byte tmCharSet;
    public static final int sizeof;
    
    static {
        sizeof = (OS.IsUnicode ? OS.TEXTMETRICW_sizeof() : OS.TEXTMETRICA_sizeof());
    }
}
