// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class MOUSEINPUT
{
    public int dx;
    public int dy;
    public int mouseData;
    public int dwFlags;
    public int time;
    public int dwExtraInfo;
    public static final int sizeof;
    
    static {
        sizeof = OS.MOUSEINPUT_sizeof();
    }
}
