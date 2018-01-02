// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public abstract class OSVERSIONINFO
{
    public int dwOSVersionInfoSize;
    public int dwMajorVersion;
    public int dwMinorVersion;
    public int dwBuildNumber;
    public int dwPlatformId;
    public static int sizeof;
    
    static {
        OSVERSIONINFO.sizeof = (OS.IsUnicode ? OS.OSVERSIONINFOW_sizeof() : OS.OSVERSIONINFOA_sizeof());
    }
}
