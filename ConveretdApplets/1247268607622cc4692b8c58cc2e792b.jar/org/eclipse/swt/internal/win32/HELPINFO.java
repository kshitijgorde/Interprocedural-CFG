// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class HELPINFO
{
    public int cbSize;
    public int iContextType;
    public int iCtrlId;
    public int hItemHandle;
    public int dwContextId;
    public int x;
    public int y;
    public static final int sizeof;
    
    static {
        sizeof = OS.HELPINFO_sizeof();
    }
}
