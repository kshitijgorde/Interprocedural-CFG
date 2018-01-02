// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class BLENDFUNCTION
{
    public byte BlendOp;
    public byte BlendFlags;
    public byte SourceConstantAlpha;
    public byte AlphaFormat;
    public static final int sizeof;
    
    static {
        sizeof = OS.BLENDFUNCTION_sizeof();
    }
}
