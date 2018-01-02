// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class INITCOMMONCONTROLSEX
{
    public int dwSize;
    public int dwICC;
    public static final int sizeof;
    
    static {
        sizeof = OS.INITCOMMONCONTROLSEX_sizeof();
    }
}
