// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NMTTDISPINFOA extends NMTTDISPINFO
{
    public byte[] szText;
    public static final int sizeof;
    
    static {
        sizeof = OS.NMTTDISPINFOA_sizeof();
    }
    
    public NMTTDISPINFOA() {
        this.szText = new byte[80];
    }
}
