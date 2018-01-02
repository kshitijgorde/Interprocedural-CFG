// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NMREBARCHEVRON extends NMHDR
{
    public int uBand;
    public int wID;
    public int lParam;
    public int left;
    public int top;
    public int right;
    public int bottom;
    public int lParamNM;
    public static int sizeof;
    
    static {
        NMREBARCHEVRON.sizeof = OS.NMREBARCHEVRON_sizeof();
    }
}
