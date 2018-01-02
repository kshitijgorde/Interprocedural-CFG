// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public class STATSTG
{
    public int pwcsName;
    public int type;
    public long cbSize;
    public int mtime_dwLowDateTime;
    public int mtime_dwHighDateTime;
    public int ctime_dwLowDateTime;
    public int ctime_dwHighDateTime;
    public int atime_dwLowDateTime;
    public int atime_dwHighDateTime;
    public int grfMode;
    public int grfLocksSupported;
    public int clsid_Data1;
    public short clsid_Data2;
    public short clsid_Data3;
    public byte[] clsid_Data4;
    public int grfStateBits;
    public int reserved;
    public static final int sizeof;
    
    static {
        sizeof = COM.STATSTG_sizeof();
    }
    
    public STATSTG() {
        this.clsid_Data4 = new byte[8];
    }
}
