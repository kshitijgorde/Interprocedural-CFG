// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class WINDOWPOS
{
    public int hwnd;
    public int hwndInsertAfter;
    public int x;
    public int y;
    public int cx;
    public int cy;
    public int flags;
    public static final int sizeof;
    
    static {
        sizeof = OS.WINDOWPOS_sizeof();
    }
}
