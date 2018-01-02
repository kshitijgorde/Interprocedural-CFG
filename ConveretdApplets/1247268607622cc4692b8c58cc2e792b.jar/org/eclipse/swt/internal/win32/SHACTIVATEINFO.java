// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class SHACTIVATEINFO
{
    public int cbSize;
    public int hwndLastFocus;
    public int fSipUp;
    public int fSipOnDeactivation;
    public int fActive;
    public int fReserved;
    public static final int sizeof;
    
    static {
        sizeof = OS.SHACTIVATEINFO_sizeof();
    }
}
