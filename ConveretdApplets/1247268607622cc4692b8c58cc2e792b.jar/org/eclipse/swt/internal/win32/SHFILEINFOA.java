// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class SHFILEINFOA extends SHFILEINFO
{
    public byte[] szDisplayName;
    public byte[] szTypeName;
    public static int sizeof;
    
    static {
        SHFILEINFOA.sizeof = OS.SHFILEINFOA_sizeof();
    }
    
    public SHFILEINFOA() {
        this.szDisplayName = new byte[260];
        this.szTypeName = new byte[80];
    }
}
