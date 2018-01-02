// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class OUTLINETEXTMETRICA extends OUTLINETEXTMETRIC
{
    public TEXTMETRICA otmTextMetrics;
    public static final int sizeof;
    
    static {
        sizeof = OS.OUTLINETEXTMETRICA_sizeof();
    }
    
    public OUTLINETEXTMETRICA() {
        this.otmTextMetrics = new TEXTMETRICA();
    }
}
