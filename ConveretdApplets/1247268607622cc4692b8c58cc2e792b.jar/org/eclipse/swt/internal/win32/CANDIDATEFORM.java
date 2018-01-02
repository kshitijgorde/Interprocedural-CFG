// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class CANDIDATEFORM
{
    public int dwIndex;
    public int dwStyle;
    public POINT ptCurrentPos;
    public RECT rcArea;
    public static final int sizeof;
    
    static {
        sizeof = OS.CANDIDATEFORM_sizeof();
    }
    
    public CANDIDATEFORM() {
        this.ptCurrentPos = new POINT();
        this.rcArea = new RECT();
    }
}
