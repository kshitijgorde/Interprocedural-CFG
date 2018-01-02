// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class OSVERSIONINFOA extends OSVERSIONINFO
{
    public byte[] szCSDVersion;
    public static final int sizeof;
    
    static {
        sizeof = OS.OSVERSIONINFOA_sizeof();
    }
    
    public OSVERSIONINFOA() {
        this.szCSDVersion = new byte[128];
    }
}
