// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class EMREXTCREATEFONTINDIRECTW
{
    public EMR emr;
    public int ihFont;
    public EXTLOGFONTW elfw;
    public static final int sizeof;
    
    static {
        sizeof = OS.EMREXTCREATEFONTINDIRECTW_sizeof();
    }
    
    public EMREXTCREATEFONTINDIRECTW() {
        this.emr = new EMR();
        this.elfw = new EXTLOGFONTW();
    }
}
