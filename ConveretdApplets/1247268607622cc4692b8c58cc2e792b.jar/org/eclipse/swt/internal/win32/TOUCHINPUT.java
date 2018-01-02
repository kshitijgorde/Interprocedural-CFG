// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class TOUCHINPUT
{
    public int x;
    public int y;
    public int hSource;
    public int dwID;
    public int dwFlags;
    public int dwMask;
    public int dwTime;
    public int dwExtraInfo;
    public int cxContact;
    public int cyContact;
    public static final int sizeof;
    
    static {
        sizeof = OS.TOUCHINPUT_sizeof();
    }
}
