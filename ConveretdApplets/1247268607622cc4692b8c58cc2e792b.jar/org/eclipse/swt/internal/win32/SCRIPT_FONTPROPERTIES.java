// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class SCRIPT_FONTPROPERTIES
{
    public int cBytes;
    public short wgBlank;
    public short wgDefault;
    public short wgInvalid;
    public short wgKashida;
    public int iKashidaWidth;
    public static final int sizeof;
    
    static {
        sizeof = OS.SCRIPT_FONTPROPERTIES_sizeof();
    }
}
