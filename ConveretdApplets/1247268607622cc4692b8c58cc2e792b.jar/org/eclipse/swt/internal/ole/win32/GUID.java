// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public final class GUID
{
    public int Data1;
    public short Data2;
    public short Data3;
    public byte[] Data4;
    public static final int sizeof;
    static final String zeros = "00000000";
    
    static {
        sizeof = COM.GUID_sizeof();
    }
    
    public GUID() {
        this.Data4 = new byte[8];
    }
    
    static String toHex(final int n, final int n2) {
        String s = Integer.toHexString(n).toUpperCase();
        final int length = s.length();
        if (length > n2) {
            s = s.substring(length - n2);
        }
        return String.valueOf("00000000".substring(0, Math.max(0, n2 - length))) + s;
    }
    
    public String toString() {
        return String.valueOf('{') + toHex(this.Data1, 8) + '-' + toHex(this.Data2, 4) + '-' + toHex(this.Data3, 4) + '-' + toHex(this.Data4[0], 2) + toHex(this.Data4[1], 2) + '-' + toHex(this.Data4[2], 2) + toHex(this.Data4[3], 2) + toHex(this.Data4[4], 2) + toHex(this.Data4[5], 2) + toHex(this.Data4[6], 2) + toHex(this.Data4[7], 2) + '}';
    }
}
