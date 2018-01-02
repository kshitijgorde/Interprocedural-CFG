// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class OSVERSIONINFOW extends OSVERSIONINFO
{
    public char[] szCSDVersion;
    public static final int sizeof;
    
    static {
        sizeof = OS.OSVERSIONINFOW_sizeof();
    }
    
    public OSVERSIONINFOW() {
        this.szCSDVersion = new char[128];
    }
}
