// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class TF_DISPLAYATTRIBUTE
{
    public TF_DA_COLOR crText;
    public TF_DA_COLOR crBk;
    public int lsStyle;
    public boolean fBoldLine;
    public TF_DA_COLOR crLine;
    public int bAttr;
    public static final int sizeof;
    
    static {
        sizeof = OS.TF_DISPLAYATTRIBUTE_sizeof();
    }
    
    public TF_DISPLAYATTRIBUTE() {
        this.crText = new TF_DA_COLOR();
        this.crBk = new TF_DA_COLOR();
        this.crLine = new TF_DA_COLOR();
    }
}
