// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class HDHITTESTINFO
{
    public int x;
    public int y;
    public int flags;
    public int iItem;
    public static int sizeof;
    
    static {
        HDHITTESTINFO.sizeof = OS.HDHITTESTINFO_sizeof();
    }
}
