// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class SHFILEINFO
{
    public int hIcon;
    public int iIcon;
    public int dwAttributes;
    public static int sizeof;
    
    static {
        SHFILEINFO.sizeof = (OS.IsUnicode ? OS.SHFILEINFOW_sizeof() : OS.SHFILEINFOA_sizeof());
    }
}
