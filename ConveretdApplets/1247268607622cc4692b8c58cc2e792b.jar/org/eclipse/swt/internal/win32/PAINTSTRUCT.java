// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class PAINTSTRUCT
{
    public int hdc;
    public boolean fErase;
    public int left;
    public int top;
    public int right;
    public int bottom;
    public boolean fRestore;
    public boolean fIncUpdate;
    public byte[] rgbReserved;
    public static final int sizeof;
    
    static {
        sizeof = OS.PAINTSTRUCT_sizeof();
    }
    
    public PAINTSTRUCT() {
        this.rgbReserved = new byte[32];
    }
}
