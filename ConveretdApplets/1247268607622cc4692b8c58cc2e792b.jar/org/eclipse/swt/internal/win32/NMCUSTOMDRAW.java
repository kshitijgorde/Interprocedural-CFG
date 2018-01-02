// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NMCUSTOMDRAW extends NMHDR
{
    public int dwDrawStage;
    public int hdc;
    public int left;
    public int top;
    public int right;
    public int bottom;
    public int dwItemSpec;
    public int uItemState;
    public int lItemlParam;
    public static final int sizeof;
    
    static {
        sizeof = OS.NMCUSTOMDRAW_sizeof();
    }
}
