// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class MENUINFO
{
    public int cbSize;
    public int fMask;
    public int dwStyle;
    public int cyMax;
    public int hbrBack;
    public int dwContextHelpID;
    public int dwMenuData;
    public static final int sizeof;
    
    static {
        sizeof = OS.MENUINFO_sizeof();
    }
}
