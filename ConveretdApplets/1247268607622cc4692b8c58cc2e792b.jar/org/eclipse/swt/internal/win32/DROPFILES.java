// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class DROPFILES
{
    public int pFiles;
    public int pt_x;
    public int pt_y;
    public int fNC;
    public int fWide;
    public static final int sizeof;
    
    static {
        sizeof = OS.DROPFILES_sizeof();
    }
}
