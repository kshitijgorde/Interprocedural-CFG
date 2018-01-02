// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class CERT_CONTEXT
{
    public int dwCertEncodingType;
    public int pbCertEncoded;
    public int cbCertEncoded;
    public int pCertInfo;
    public int hCertStore;
    public static final int sizeof;
    
    static {
        sizeof = OS.CERT_CONTEXT_sizeof();
    }
}
