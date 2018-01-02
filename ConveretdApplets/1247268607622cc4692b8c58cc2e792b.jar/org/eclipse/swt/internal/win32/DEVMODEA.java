// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class DEVMODEA extends DEVMODE
{
    public byte[] dmDeviceName;
    public byte[] dmFormName;
    public static final int sizeof;
    
    static {
        sizeof = OS.DEVMODEA_sizeof();
    }
    
    public DEVMODEA() {
        this.dmDeviceName = new byte[32];
        this.dmFormName = new byte[32];
    }
}
