// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class EXTLOGFONTW
{
    public LOGFONTW elfLogFont;
    public char[] elfFullName;
    public char[] elfStyle;
    public int elfVersion;
    public int elfStyleSize;
    public int elfMatch;
    public int elfReserved;
    public byte[] elfVendorId;
    public int elfCulture;
    public PANOSE elfPanose;
    public static final int sizeof;
    
    static {
        sizeof = OS.EXTLOGFONTW_sizeof();
    }
    
    public EXTLOGFONTW() {
        this.elfLogFont = new LOGFONTW();
        this.elfFullName = new char[64];
        this.elfStyle = new char[32];
        this.elfVendorId = new byte[4];
        this.elfPanose = new PANOSE();
    }
}
