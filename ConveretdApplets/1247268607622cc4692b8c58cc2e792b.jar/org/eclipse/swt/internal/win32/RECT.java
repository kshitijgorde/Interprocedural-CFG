// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class RECT
{
    public int left;
    public int top;
    public int right;
    public int bottom;
    public static final int sizeof;
    
    static {
        sizeof = OS.RECT_sizeof();
    }
}
