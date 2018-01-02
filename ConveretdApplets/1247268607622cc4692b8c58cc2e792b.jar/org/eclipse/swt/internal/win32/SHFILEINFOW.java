// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class SHFILEINFOW extends SHFILEINFO
{
    public char[] szDisplayName;
    public char[] szTypeName;
    public static int sizeof;
    
    static {
        SHFILEINFOW.sizeof = OS.SHFILEINFOW_sizeof();
    }
    
    public SHFILEINFOW() {
        this.szDisplayName = new char[260];
        this.szTypeName = new char[80];
    }
}
