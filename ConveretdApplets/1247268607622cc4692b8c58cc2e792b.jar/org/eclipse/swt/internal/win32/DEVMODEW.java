// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class DEVMODEW extends DEVMODE
{
    public char[] dmDeviceName;
    public char[] dmFormName;
    public static final int sizeof;
    
    static {
        sizeof = OS.DEVMODEW_sizeof();
    }
    
    public DEVMODEW() {
        this.dmDeviceName = new char[32];
        this.dmFormName = new char[32];
    }
}
