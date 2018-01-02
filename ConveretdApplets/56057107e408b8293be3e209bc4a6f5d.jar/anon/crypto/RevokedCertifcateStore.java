// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import java.util.Date;
import java.util.Enumeration;
import logging.LogHolder;
import logging.LogType;
import java.util.Hashtable;

public class RevokedCertifcateStore
{
    private static RevokedCertifcateStore m_instance;
    private static final String CRL_PATH = "crls/";
    private Hashtable m_revokedCerts;
    static /* synthetic */ Class class$anon$crypto$RevokedCertifcateStore;
    
    private RevokedCertifcateStore() {
        CertificateRevocationList list = null;
        this.m_revokedCerts = new Hashtable();
        final Enumeration<CertificateRevocationList> elements = CertificateRevocationList.getInstance("crls/", true, null).elements();
        while (elements.hasMoreElements()) {
            list = elements.nextElement();
            this.addRevocations(list);
        }
        if (list == null) {
            LogHolder.log(4, LogType.CRYPTO, "Could not load default CRLs!");
        }
    }
    
    private void addRevocations(final CertificateRevocationList list) {
        final Enumeration<RevokedCertificate> elements = list.getRevokedCertificates().elements();
        final boolean indirectCRL = list.isIndirectCRL();
        synchronized (this.m_revokedCerts) {
            while (elements.hasMoreElements()) {
                final RevokedCertificate revokedCertificate = elements.nextElement();
                X509DistinguishedName x509DistinguishedName = null;
                if (indirectCRL) {
                    x509DistinguishedName = revokedCertificate.getCertificateIssuer();
                }
                if (x509DistinguishedName == null) {
                    x509DistinguishedName = list.getIssuer();
                }
                this.m_revokedCerts.put(x509DistinguishedName.toString() + revokedCertificate.getSerialNumber().toString(), revokedCertificate);
            }
        }
    }
    
    public static RevokedCertifcateStore getInstance() {
        final Class clazz = (RevokedCertifcateStore.class$anon$crypto$RevokedCertifcateStore == null) ? (RevokedCertifcateStore.class$anon$crypto$RevokedCertifcateStore = class$("anon.crypto.RevokedCertifcateStore")) : RevokedCertifcateStore.class$anon$crypto$RevokedCertifcateStore;
        synchronized (clazz) {
            if (RevokedCertifcateStore.m_instance == null) {
                RevokedCertifcateStore.m_instance = new RevokedCertifcateStore();
            }
            return RevokedCertifcateStore.m_instance;
        }
    }
    
    private static String keyValue(final JAPCertificate japCertificate) {
        return japCertificate.getIssuer().toString() + RevokedCertificate.getUniqueSerial(japCertificate).toString();
    }
    
    public boolean isCertificateRevoked(final JAPCertificate japCertificate) {
        synchronized (this.m_revokedCerts) {
            return this.m_revokedCerts.containsKey(keyValue(japCertificate));
        }
    }
    
    public Date getRevocationDate(final JAPCertificate japCertificate) {
        synchronized (this.m_revokedCerts) {
            if (this.isCertificateRevoked(japCertificate)) {
                return this.m_revokedCerts.get(keyValue(japCertificate)).getRevocationDate();
            }
            return null;
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
