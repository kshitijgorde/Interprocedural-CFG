// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class PRINTDLG
{
    public int lStructSize;
    public int hwndOwner;
    public int hDevMode;
    public int hDevNames;
    public int hDC;
    public int Flags;
    public short nFromPage;
    public short nToPage;
    public short nMinPage;
    public short nMaxPage;
    public short nCopies;
    public int hInstance;
    public int lCustData;
    public int lpfnPrintHook;
    public int lpfnSetupHook;
    public int lpPrintTemplateName;
    public int lpSetupTemplateName;
    public int hPrintTemplate;
    public int hSetupTemplate;
    public static final int sizeof;
    
    static {
        sizeof = OS.PRINTDLG_sizeof();
    }
}
