// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class MSG
{
    public int hwnd;
    public int message;
    public int wParam;
    public int lParam;
    public int time;
    public int x;
    public int y;
    public static final int sizeof;
    
    static {
        sizeof = OS.MSG_sizeof();
    }
}