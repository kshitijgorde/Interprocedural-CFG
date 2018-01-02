// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class GCP_RESULTS
{
    public int lStructSize;
    public int lpOutString;
    public int lpOrder;
    public int lpDx;
    public int lpCaretPos;
    public int lpClass;
    public int lpGlyphs;
    public int nGlyphs;
    public int nMaxFit;
    public static final int sizeof;
    
    static {
        sizeof = OS.GCP_RESULTS_sizeof();
    }
}
