// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NMLVCUSTOMDRAW extends NMCUSTOMDRAW
{
    public int clrText;
    public int clrTextBk;
    public int iSubItem;
    public int dwItemType;
    public int clrFace;
    public int iIconEffect;
    public int iIconPhase;
    public int iPartId;
    public int iStateId;
    public int rcText_left;
    public int rcText_top;
    public int rcText_right;
    public int rcText_bottom;
    public int uAlign;
    public static final int sizeof;
    
    static {
        sizeof = ((!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(5, 1)) ? OS.NMLVCUSTOMDRAW_sizeof() : 60);
    }
}
