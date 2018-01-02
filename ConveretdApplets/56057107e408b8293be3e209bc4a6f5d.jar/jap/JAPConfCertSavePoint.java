// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import java.util.Enumeration;
import anon.crypto.CertificateInfoStructure;
import anon.crypto.SignatureVerifier;
import logging.LogHolder;
import logging.LogType;
import java.util.Vector;

public class JAPConfCertSavePoint implements IJAPConfSavePoint
{
    private Vector m_unverifiedPersisitentCertificates;
    
    public JAPConfCertSavePoint() {
        this.m_unverifiedPersisitentCertificates = new Vector();
    }
    
    public void createSavePoint() {
    }
    
    public void restoreSavePoint() {
    }
    
    public void restoreDefaults() {
        LogHolder.log(7, LogType.MISC, "JAPConfCertSavePoint: restoreDefaults: Restoring default certificate settings.");
        SignatureVerifier.getInstance().setCheckSignatures(true);
        final Enumeration<CertificateInfoStructure> elements = SignatureVerifier.getInstance().getVerificationCertificateStore().getAllCertificates().elements();
        while (elements.hasMoreElements()) {
            final CertificateInfoStructure certificateInfoStructure = elements.nextElement();
            if (!certificateInfoStructure.getCertificateNeedsVerification()) {
                SignatureVerifier.getInstance().getVerificationCertificateStore().removeCertificate(certificateInfoStructure);
            }
        }
        JAPController.addDefaultCertificates();
    }
}
