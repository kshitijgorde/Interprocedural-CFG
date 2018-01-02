// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class CHOOSECOLOR
{
    public int lStructSize;
    public int hwndOwner;
    public int hInstance;
    public int rgbResult;
    public int lpCustColors;
    public int Flags;
    public int lCustData;
    public int lpfnHook;
    public int lpTemplateName;
    public static final int sizeof;
    
    static {
        sizeof = OS.CHOOSECOLOR_sizeof();
    }
}
