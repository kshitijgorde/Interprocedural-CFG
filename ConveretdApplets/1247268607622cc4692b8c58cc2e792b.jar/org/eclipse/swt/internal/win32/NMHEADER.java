// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NMHEADER extends NMHDR
{
    public int iItem;
    public int iButton;
    public int pitem;
    public static int sizeof;
    
    static {
        NMHEADER.sizeof = OS.NMHEADER_sizeof();
    }
}
