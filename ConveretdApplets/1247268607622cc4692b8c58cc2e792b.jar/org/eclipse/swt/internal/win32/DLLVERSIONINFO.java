// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class DLLVERSIONINFO
{
    public int cbSize;
    public int dwMajorVersion;
    public int dwMinorVersion;
    public int dwBuildNumber;
    public int dwPlatformID;
    public static final int sizeof;
    
    static {
        sizeof = OS.DLLVERSIONINFO_sizeof();
    }
}
