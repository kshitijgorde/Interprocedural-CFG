// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class TCHITTESTINFO
{
    public int x;
    public int y;
    public int flags;
    public static int sizeof;
    
    static {
        TCHITTESTINFO.sizeof = OS.TCHITTESTINFO_sizeof();
    }
}
