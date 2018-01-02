// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class TRACKMOUSEEVENT
{
    public int cbSize;
    public int dwFlags;
    public int hwndTrack;
    public int dwHoverTime;
    public static final int sizeof;
    
    static {
        sizeof = OS.TRACKMOUSEEVENT_sizeof();
    }
}
