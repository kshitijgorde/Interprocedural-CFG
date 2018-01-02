// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class KEYBDINPUT
{
    public short wVk;
    public short wScan;
    public int dwFlags;
    public int time;
    public int dwExtraInfo;
    public static final int sizeof;
    
    static {
        sizeof = OS.KEYBDINPUT_sizeof();
    }
}
