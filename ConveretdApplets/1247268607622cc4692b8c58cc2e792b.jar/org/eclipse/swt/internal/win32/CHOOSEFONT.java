// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class CHOOSEFONT
{
    public int lStructSize;
    public int hwndOwner;
    public int hDC;
    public int lpLogFont;
    public int iPointSize;
    public int Flags;
    public int rgbColors;
    public int lCustData;
    public int lpfnHook;
    public int lpTemplateName;
    public int hInstance;
    public int lpszStyle;
    public short nFontType;
    public int nSizeMin;
    public int nSizeMax;
    public static final int sizeof;
    
    static {
        sizeof = OS.CHOOSEFONT_sizeof();
    }
}
