// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NOTIFYICONDATAW extends NOTIFYICONDATA
{
    public char[] szTip;
    public char[] szInfo;
    public char[] szInfoTitle;
    public static final int sizeof;
    
    static {
        sizeof = OS.NOTIFYICONDATAW_V2_SIZE;
    }
    
    public NOTIFYICONDATAW() {
        this.szTip = new char[128];
        this.szInfo = new char[256];
        this.szInfoTitle = new char[64];
    }
}
