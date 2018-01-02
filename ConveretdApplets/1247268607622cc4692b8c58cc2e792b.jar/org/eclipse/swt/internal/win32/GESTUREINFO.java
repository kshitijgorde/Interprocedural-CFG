// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class GESTUREINFO
{
    public int cbSize;
    public int dwFlags;
    public int dwID;
    public int hwndTarget;
    public short x;
    public short y;
    public int dwInstanceID;
    public int dwSequenceID;
    public long ullArguments;
    public int cbExtraArgs;
    public static final int sizeof;
    
    static {
        sizeof = OS.GESTUREINFO_sizeof();
    }
}
