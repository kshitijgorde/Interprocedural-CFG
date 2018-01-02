// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class LVINSERTMARK
{
    public int cbSize;
    public int dwFlags;
    public int iItem;
    public int dwReserved;
    public static final int sizeof;
    
    static {
        sizeof = OS.LVINSERTMARK_sizeof();
    }
}
