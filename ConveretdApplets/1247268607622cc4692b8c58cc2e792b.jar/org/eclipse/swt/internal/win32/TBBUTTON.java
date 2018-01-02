// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class TBBUTTON
{
    public int iBitmap;
    public int idCommand;
    public byte fsState;
    public byte fsStyle;
    public int dwData;
    public int iString;
    public static final int sizeof;
    
    static {
        sizeof = OS.TBBUTTON_sizeof();
    }
}
