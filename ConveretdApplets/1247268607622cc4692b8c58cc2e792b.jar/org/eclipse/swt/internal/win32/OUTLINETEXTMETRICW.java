// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class OUTLINETEXTMETRICW extends OUTLINETEXTMETRIC
{
    public TEXTMETRICW otmTextMetrics;
    public static final int sizeof;
    
    static {
        sizeof = OS.OUTLINETEXTMETRICW_sizeof();
    }
    
    public OUTLINETEXTMETRICW() {
        this.otmTextMetrics = new TEXTMETRICW();
    }
}
