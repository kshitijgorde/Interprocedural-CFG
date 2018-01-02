// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class CERT_NAME_BLOB
{
    public int cbData;
    public int pbData;
    public static final int sizeof;
    
    static {
        sizeof = OS.CERT_NAME_BLOB_sizeof();
    }
}
