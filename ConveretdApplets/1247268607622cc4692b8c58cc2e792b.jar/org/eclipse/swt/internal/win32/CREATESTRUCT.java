// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class CREATESTRUCT
{
    public int lpCreateParams;
    public int hInstance;
    public int hMenu;
    public int hwndParent;
    public int cy;
    public int cx;
    public int y;
    public int x;
    public int style;
    public int lpszName;
    public int lpszClass;
    public int dwExStyle;
    public static final int sizeof;
    
    static {
        sizeof = OS.CREATESTRUCT_sizeof();
    }
}
