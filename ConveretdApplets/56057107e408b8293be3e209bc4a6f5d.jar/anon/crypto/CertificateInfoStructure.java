// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

public class CertificateInfoStructure
{
    private CertPath m_certPath;
    private JAPCertificate m_parentCertificate;
    private int m_certificateType;
    private boolean m_enabled;
    private boolean m_certificateNeedsVerification;
    private boolean m_onlyHardRemovable;
    private boolean m_bNotRemovable;
    
    public CertificateInfoStructure(final CertPath certPath, final JAPCertificate parentCertificate, final int certificateType, final boolean enabled, final boolean certificateNeedsVerification, final boolean onlyHardRemovable, final boolean bNotRemovable) {
        if (certPath == null) {
            throw new IllegalArgumentException("CertPath may not be null");
        }
        this.m_certPath = certPath;
        this.m_parentCertificate = parentCertificate;
        this.m_certificateType = certificateType;
        this.m_enabled = enabled;
        this.m_certificateNeedsVerification = certificateNeedsVerification;
        this.m_onlyHardRemovable = onlyHardRemovable;
        this.m_bNotRemovable = bNotRemovable;
    }
    
    public JAPCertificate getCertificate() {
        return this.m_certPath.getFirstCertificate();
    }
    
    public JAPCertificate getParentCertificate() {
        return this.m_parentCertificate;
    }
    
    public CertPath getCertPath() {
        return this.m_certPath;
    }
    
    public int getCertificateType() {
        return this.m_certificateType;
    }
    
    public boolean getCertificateNeedsVerification() {
        return this.m_certificateNeedsVerification;
    }
    
    public boolean isAvailable() {
        boolean b = false;
        synchronized (this) {
            b = ((!this.m_certificateNeedsVerification || this.m_parentCertificate != null) && this.m_enabled);
        }
        return b;
    }
    
    public boolean isOnlyHardRemovable() {
        return this.m_onlyHardRemovable;
    }
    
    public boolean isNotRemovable() {
        return this.m_bNotRemovable;
    }
    
    public boolean isEnabled() {
        return this.m_enabled;
    }
    
    public void setEnabled(final boolean enabled) {
        this.m_enabled = enabled;
    }
}
