// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public abstract class OSVERSIONINFOEX extends OSVERSIONINFO
{
    public short wServicePackMajor;
    public short wServicePackMinor;
    public short wSuiteMask;
    public byte wProductType;
    public byte wReserved;
    public static int sizeof;
    
    static {
        OSVERSIONINFOEX.sizeof = (OS.IsUnicode ? OS.OSVERSIONINFOEXW_sizeof() : OS.OSVERSIONINFOEXA_sizeof());
    }
}
