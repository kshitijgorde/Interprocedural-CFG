// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class COMBOBOXINFO
{
    public int cbSize;
    public int itemLeft;
    public int itemTop;
    public int itemRight;
    public int itemBottom;
    public int buttonLeft;
    public int buttonTop;
    public int buttonRight;
    public int buttonBottom;
    public int stateButton;
    public int hwndCombo;
    public int hwndItem;
    public int hwndList;
    public static final int sizeof;
    
    static {
        sizeof = OS.COMBOBOXINFO_sizeof();
    }
}
