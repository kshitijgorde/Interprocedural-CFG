// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NOTIFYICONDATAA extends NOTIFYICONDATA
{
    public byte[] szTip;
    public byte[] szInfo;
    public byte[] szInfoTitle;
    public static final int sizeof;
    
    static {
        sizeof = OS.NOTIFYICONDATAA_V2_SIZE;
    }
    
    public NOTIFYICONDATAA() {
        this.szTip = new byte[128];
        this.szInfo = new byte[256];
        this.szInfoTitle = new byte[64];
    }
}
