// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class OUTLINETEXTMETRIC
{
    public int otmSize;
    public byte otmFiller;
    public byte otmPanoseNumber_bFamilyType;
    public byte otmPanoseNumber_bSerifStyle;
    public byte otmPanoseNumber_bWeight;
    public byte otmPanoseNumber_bProportion;
    public byte otmPanoseNumber_bContrast;
    public byte otmPanoseNumber_bStrokeVariation;
    public byte otmPanoseNumber_bArmStyle;
    public byte otmPanoseNumber_bLetterform;
    public byte otmPanoseNumber_bMidline;
    public byte otmPanoseNumber_bXHeight;
    public int otmfsSelection;
    public int otmfsType;
    public int otmsCharSlopeRise;
    public int otmsCharSlopeRun;
    public int otmItalicAngle;
    public int otmEMSquare;
    public int otmAscent;
    public int otmDescent;
    public int otmLineGap;
    public int otmsCapEmHeight;
    public int otmsXHeight;
    public RECT otmrcFontBox;
    public int otmMacAscent;
    public int otmMacDescent;
    public int otmMacLineGap;
    public int otmusMinimumPPEM;
    public POINT otmptSubscriptSize;
    public POINT otmptSubscriptOffset;
    public POINT otmptSuperscriptSize;
    public POINT otmptSuperscriptOffset;
    public int otmsStrikeoutSize;
    public int otmsStrikeoutPosition;
    public int otmsUnderscoreSize;
    public int otmsUnderscorePosition;
    public int otmpFamilyName;
    public int otmpFaceName;
    public int otmpStyleName;
    public int otmpFullName;
    public static final int sizeof;
    
    static {
        sizeof = (OS.IsUnicode ? OS.OUTLINETEXTMETRICW_sizeof() : OS.OUTLINETEXTMETRICA_sizeof());
    }
    
    public OUTLINETEXTMETRIC() {
        this.otmrcFontBox = new RECT();
        this.otmptSubscriptSize = new POINT();
        this.otmptSubscriptOffset = new POINT();
        this.otmptSuperscriptSize = new POINT();
        this.otmptSuperscriptOffset = new POINT();
    }
}
