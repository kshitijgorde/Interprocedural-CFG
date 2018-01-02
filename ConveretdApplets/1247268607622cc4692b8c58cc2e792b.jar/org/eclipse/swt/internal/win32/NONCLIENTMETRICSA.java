// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class NONCLIENTMETRICSA extends NONCLIENTMETRICS
{
    public LOGFONTA lfCaptionFont;
    public LOGFONTA lfSmCaptionFont;
    public LOGFONTA lfMenuFont;
    public LOGFONTA lfStatusFont;
    public LOGFONTA lfMessageFont;
    public static final int sizeof;
    
    static {
        sizeof = OS.NONCLIENTMETRICSA_sizeof();
    }
    
    public NONCLIENTMETRICSA() {
        this.lfCaptionFont = new LOGFONTA();
        this.lfSmCaptionFont = new LOGFONTA();
        this.lfMenuFont = new LOGFONTA();
        this.lfStatusFont = new LOGFONTA();
        this.lfMessageFont = new LOGFONTA();
    }
}
