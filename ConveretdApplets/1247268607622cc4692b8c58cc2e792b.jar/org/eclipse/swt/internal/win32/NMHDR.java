// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NMHDR
{
    public int hwndFrom;
    public int idFrom;
    public int code;
    public static final int sizeof;
    
    static {
        sizeof = OS.NMHDR_sizeof();
    }
}
