// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NMTVCUSTOMDRAW extends NMCUSTOMDRAW
{
    public int clrText;
    public int clrTextBk;
    public int iLevel;
    public static final int sizeof;
    
    static {
        sizeof = OS.NMTVCUSTOMDRAW_sizeof();
    }
}
