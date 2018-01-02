// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class OSVERSIONINFOEXA extends OSVERSIONINFOEX
{
    public byte[] szCSDVersion;
    public static final int sizeof;
    
    static {
        sizeof = OS.OSVERSIONINFOEXA_sizeof();
    }
    
    public OSVERSIONINFOEXA() {
        this.szCSDVersion = new byte[128];
    }
}
