// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class CRYPT_OBJID_BLOB
{
    public int cbData;
    public int pbData;
    public static final int sizeof;
    
    static {
        sizeof = OS.CRYPT_OBJID_BLOB_sizeof();
    }
}
