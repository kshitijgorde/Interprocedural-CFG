// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class CERT_PUBLIC_KEY_INFO
{
    public CRYPT_ALGORITHM_IDENTIFIER Algorithm;
    public CRYPT_BIT_BLOB PublicKey;
    public static final int sizeof;
    
    static {
        sizeof = OS.CERT_PUBLIC_KEY_INFO_sizeof();
    }
    
    public CERT_PUBLIC_KEY_INFO() {
        this.Algorithm = new CRYPT_ALGORITHM_IDENTIFIER();
        this.PublicKey = new CRYPT_BIT_BLOB();
    }
}
