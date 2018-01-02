// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class DRAWITEMSTRUCT
{
    public int CtlType;
    public int CtlID;
    public int itemID;
    public int itemAction;
    public int itemState;
    public int hwndItem;
    public int hDC;
    public int left;
    public int top;
    public int bottom;
    public int right;
    public int itemData;
    public static final int sizeof;
    
    static {
        sizeof = OS.DRAWITEMSTRUCT_sizeof();
    }
}
