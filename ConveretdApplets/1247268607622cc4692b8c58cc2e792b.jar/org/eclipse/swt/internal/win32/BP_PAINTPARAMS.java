// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class BP_PAINTPARAMS
{
    public int cbSize;
    public int dwFlags;
    public int prcExclude;
    public int pBlendFunction;
    public static final int sizeof;
    
    static {
        sizeof = OS.BP_PAINTPARAMS_sizeof();
    }
}
