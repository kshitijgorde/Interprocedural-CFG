// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public class COSERVERINFO
{
    public int dwReserved1;
    public int pwszName;
    public int pAuthInfo;
    public int dwReserved2;
    public static final int sizeof;
    
    static {
        sizeof = COM.COSERVERINFO_sizeof();
    }
}
