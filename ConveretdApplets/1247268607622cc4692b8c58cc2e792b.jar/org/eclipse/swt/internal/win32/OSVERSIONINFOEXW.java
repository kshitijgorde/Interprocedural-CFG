// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class OSVERSIONINFOEXW extends OSVERSIONINFOEX
{
    public char[] szCSDVersion;
    public static final int sizeof;
    
    static {
        sizeof = OS.OSVERSIONINFOEXW_sizeof();
    }
    
    public OSVERSIONINFOEXW() {
        this.szCSDVersion = new char[128];
    }
}
