// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public final class CONTROLINFO
{
    public int cb;
    public int hAccel;
    public short cAccel;
    public int dwFlags;
    public static final int sizeof;
    
    static {
        sizeof = COM.CONTROLINFO_sizeof();
    }
}
