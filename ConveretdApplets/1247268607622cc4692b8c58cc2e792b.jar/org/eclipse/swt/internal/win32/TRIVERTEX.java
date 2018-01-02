// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class TRIVERTEX
{
    public int x;
    public int y;
    public short Red;
    public short Green;
    public short Blue;
    public short Alpha;
    public static final int sizeof;
    
    static {
        sizeof = OS.TRIVERTEX_sizeof();
    }
}
