// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NONCLIENTMETRICSW extends NONCLIENTMETRICS
{
    public LOGFONTW lfCaptionFont;
    public LOGFONTW lfSmCaptionFont;
    public LOGFONTW lfMenuFont;
    public LOGFONTW lfStatusFont;
    public LOGFONTW lfMessageFont;
    public static final int sizeof;
    
    static {
        sizeof = OS.NONCLIENTMETRICSW_sizeof();
    }
    
    public NONCLIENTMETRICSW() {
        this.lfCaptionFont = new LOGFONTW();
        this.lfSmCaptionFont = new LOGFONTW();
        this.lfMenuFont = new LOGFONTW();
        this.lfStatusFont = new LOGFONTW();
        this.lfMessageFont = new LOGFONTW();
    }
}
