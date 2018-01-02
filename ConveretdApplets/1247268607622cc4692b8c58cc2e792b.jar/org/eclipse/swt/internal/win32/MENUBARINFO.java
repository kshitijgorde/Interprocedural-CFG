// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class MENUBARINFO
{
    public int cbSize;
    public int left;
    public int top;
    public int right;
    public int bottom;
    public int hMenu;
    public int hwndMenu;
    public boolean fBarFocused;
    public boolean fFocused;
    public static final int sizeof;
    
    static {
        sizeof = OS.MENUBARINFO_sizeof();
    }
}
