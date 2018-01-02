// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class LOGFONTA extends LOGFONT
{
    public byte[] lfFaceName;
    public static final int sizeof;
    
    static {
        sizeof = OS.LOGFONTA_sizeof();
    }
    
    public LOGFONTA() {
        this.lfFaceName = new byte[32];
    }
}
