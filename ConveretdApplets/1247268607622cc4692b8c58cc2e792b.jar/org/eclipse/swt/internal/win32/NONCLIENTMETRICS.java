// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public abstract class NONCLIENTMETRICS
{
    public int cbSize;
    public int iBorderWidth;
    public int iScrollWidth;
    public int iScrollHeight;
    public int iCaptionWidth;
    public int iCaptionHeight;
    public int iSmCaptionWidth;
    public int iSmCaptionHeight;
    public int iMenuWidth;
    public int iMenuHeight;
    public static final int sizeof;
    
    static {
        sizeof = (OS.IsUnicode ? OS.NONCLIENTMETRICSW_sizeof() : OS.NONCLIENTMETRICSA_sizeof());
    }
}
