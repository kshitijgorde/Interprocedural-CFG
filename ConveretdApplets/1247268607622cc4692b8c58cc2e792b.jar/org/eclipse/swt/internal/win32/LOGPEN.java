// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class LOGPEN
{
    public int lopnStyle;
    public int x;
    public int y;
    public int lopnColor;
    public static final int sizeof;
    
    static {
        sizeof = OS.LOGPEN_sizeof();
    }
}
