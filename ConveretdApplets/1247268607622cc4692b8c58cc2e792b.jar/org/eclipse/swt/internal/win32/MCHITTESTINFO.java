// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class MCHITTESTINFO
{
    public int cbSize;
    public POINT pt;
    public int uHit;
    public SYSTEMTIME st;
    public static final int sizeof;
    
    static {
        sizeof = OS.MCHITTESTINFO_sizeof();
    }
    
    public MCHITTESTINFO() {
        this.pt = new POINT();
        this.st = new SYSTEMTIME();
    }
}
