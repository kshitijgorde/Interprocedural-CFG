// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class WNDCLASS
{
    public int style;
    public int lpfnWndProc;
    public int cbClsExtra;
    public int cbWndExtra;
    public int hInstance;
    public int hIcon;
    public int hCursor;
    public int hbrBackground;
    public int lpszMenuName;
    public int lpszClassName;
    public static final int sizeof;
    
    static {
        sizeof = OS.WNDCLASS_sizeof();
    }
}
