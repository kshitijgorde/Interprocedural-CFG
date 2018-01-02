// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public abstract class NOTIFYICONDATA
{
    public int cbSize;
    public int hWnd;
    public int uID;
    public int uFlags;
    public int uCallbackMessage;
    public int hIcon;
    public int dwState;
    public int dwStateMask;
    public int uVersion;
    public int dwInfoFlags;
    public static final int sizeof;
    
    static {
        sizeof = OS.NOTIFYICONDATA_V2_SIZE;
    }
}
