// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NMTTDISPINFOW extends NMTTDISPINFO
{
    public char[] szText;
    public static final int sizeof;
    
    static {
        sizeof = OS.NMTTDISPINFOW_sizeof();
    }
    
    public NMTTDISPINFOW() {
        this.szText = new char[80];
    }
}
