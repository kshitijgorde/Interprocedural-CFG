// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class MEASUREITEMSTRUCT
{
    public int CtlType;
    public int CtlID;
    public int itemID;
    public int itemWidth;
    public int itemHeight;
    public int itemData;
    public static final int sizeof;
    
    static {
        sizeof = OS.MEASUREITEMSTRUCT_sizeof();
    }
}
