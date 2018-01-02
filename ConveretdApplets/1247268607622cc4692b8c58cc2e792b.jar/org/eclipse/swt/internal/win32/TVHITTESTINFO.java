// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class TVHITTESTINFO
{
    public int x;
    public int y;
    public int flags;
    public int hItem;
    public static int sizeof;
    
    static {
        TVHITTESTINFO.sizeof = OS.TVHITTESTINFO_sizeof();
    }
}
