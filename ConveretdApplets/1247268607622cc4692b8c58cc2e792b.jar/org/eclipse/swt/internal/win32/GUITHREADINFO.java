// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class GUITHREADINFO
{
    public int cbSize;
    public int flags;
    public int hwndActive;
    public int hwndFocus;
    public int hwndCapture;
    public int hwndMenuOwner;
    public int hwndMoveSize;
    public int hwndCaret;
    public int left;
    public int top;
    public int right;
    public int bottom;
    public static int sizeof;
    
    static {
        GUITHREADINFO.sizeof = OS.GUITHREADINFO_sizeof();
    }
}
