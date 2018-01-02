// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class CRYPT_BIT_BLOB
{
    public int cbData;
    public int pbData;
    public int cUnusedBits;
    public static final int sizeof;
    
    static {
        sizeof = OS.CRYPT_BIT_BLOB_sizeof();
    }
}
