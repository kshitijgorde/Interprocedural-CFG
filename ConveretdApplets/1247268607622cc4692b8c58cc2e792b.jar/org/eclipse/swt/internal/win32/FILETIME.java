// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class FILETIME
{
    public int dwLowDateTime;
    public int dwHighDateTime;
    public static final int sizeof;
    
    static {
        sizeof = OS.FILETIME_sizeof();
    }
}
