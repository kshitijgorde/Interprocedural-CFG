// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import java.util.Vector;

public class CertPathInfo
{
    private JAPCertificate m_firstCert;
    private JAPCertificate m_secondCert;
    private JAPCertificate m_rootCert;
    private Vector m_subCACerts;
    private boolean m_verified;
    private boolean m_valid;
    private int m_errorCode;
    private int m_errorPosition;
    private int m_docType;
    
    public CertPathInfo(final JAPCertificate firstCert, final JAPCertificate secondCert, final JAPCertificate rootCert, final Vector subCACerts, final int n) {
        this.m_firstCert = firstCert;
        this.m_secondCert = secondCert;
        this.m_rootCert = rootCert;
        this.m_subCACerts = subCACerts;
    }
    
    public void setVerified(final boolean verified) {
        this.m_verified = verified;
    }
    
    public boolean isVerified() {
        return this.m_verified;
    }
    
    public JAPCertificate getFirstCertificate() {
        return this.m_firstCert;
    }
    
    public JAPCertificate getSecondCertificate() {
        return this.m_secondCert;
    }
    
    public JAPCertificate getRootCertificate() {
        return this.m_rootCert;
    }
    
    public Vector getSubCACerts() {
        return this.m_subCACerts;
    }
    
    public int getDocType() {
        return this.m_docType;
    }
    
    public int getlength() {
        int n = 0;
        if (this.m_firstCert != null) {
            ++n;
        }
        if (this.m_secondCert != null) {
            ++n;
        }
        if (this.m_rootCert != null) {
            ++n;
        }
        if (this.m_subCACerts != null) {
            n += this.m_subCACerts.size();
        }
        return n;
    }
    
    public String toString() {
        String s = new String();
        String s2 = "\t";
        if (this.m_rootCert != null) {
            s = s + this.m_rootCert.getSubject().getCommonName() + "\n";
        }
        if (this.m_subCACerts != null) {
            for (int i = this.m_subCACerts.size() - 1; i >= 0; --i) {
                s = s + s2 + ((JAPCertificate)this.m_subCACerts.elementAt(i)).getSubject().getCommonName() + "\n";
                s2 += s2;
            }
        }
        if (this.m_secondCert != null) {
            s = s + s2 + this.m_secondCert.getSubject().getCommonName() + "\n";
            s2 += s2;
        }
        if (this.m_firstCert != null) {
            s = s + s2 + this.m_firstCert.getSubject().getCommonName() + "\n";
        }
        return s;
    }
}
