// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class SCRIPT_ANALYSIS
{
    public short eScript;
    public boolean fRTL;
    public boolean fLayoutRTL;
    public boolean fLinkBefore;
    public boolean fLinkAfter;
    public boolean fLogicalOrder;
    public boolean fNoGlyphIndex;
    public SCRIPT_STATE s;
    public static final int sizeof;
    
    static {
        sizeof = OS.SCRIPT_ANALYSIS_sizeof();
    }
    
    public SCRIPT_ANALYSIS() {
        this.s = new SCRIPT_STATE();
    }
}
