// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class SYSTEMTIME
{
    public short wYear;
    public short wMonth;
    public short wDayOfWeek;
    public short wDay;
    public short wHour;
    public short wMinute;
    public short wSecond;
    public short wMilliseconds;
    public static final int sizeof;
    
    static {
        sizeof = OS.SYSTEMTIME_sizeof();
    }
}
