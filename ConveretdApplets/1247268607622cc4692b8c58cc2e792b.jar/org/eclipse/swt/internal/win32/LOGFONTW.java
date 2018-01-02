// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class LOGFONTW extends LOGFONT
{
    public char[] lfFaceName;
    public static final int sizeof;
    
    static {
        sizeof = OS.LOGFONTW_sizeof();
    }
    
    public LOGFONTW() {
        this.lfFaceName = new char[32];
    }
}
