// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class CERT_INFO
{
    public int dwVersion;
    public CRYPT_INTEGER_BLOB SerialNumber;
    public CRYPT_ALGORITHM_IDENTIFIER SignatureAlgorithm;
    public CERT_NAME_BLOB Issuer;
    public FILETIME NotBefore;
    public FILETIME NotAfter;
    public CERT_NAME_BLOB Subject;
    public CERT_PUBLIC_KEY_INFO SubjectPublicKeyInfo;
    public CRYPT_BIT_BLOB IssuerUniqueId;
    public CRYPT_BIT_BLOB SubjectUniqueId;
    public int cExtension;
    public int rgExtension;
    public static final int sizeof;
    
    static {
        sizeof = OS.CERT_INFO_sizeof();
    }
    
    public CERT_INFO() {
        this.SerialNumber = new CRYPT_INTEGER_BLOB();
        this.SignatureAlgorithm = new CRYPT_ALGORITHM_IDENTIFIER();
        this.Issuer = new CERT_NAME_BLOB();
        this.NotBefore = new FILETIME();
        this.NotAfter = new FILETIME();
        this.Subject = new CERT_NAME_BLOB();
        this.SubjectPublicKeyInfo = new CERT_PUBLIC_KEY_INFO();
        this.IssuerUniqueId = new CRYPT_BIT_BLOB();
        this.SubjectUniqueId = new CRYPT_BIT_BLOB();
    }
}
