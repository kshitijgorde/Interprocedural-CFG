// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import java.util.Enumeration;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import java.util.Vector;
import java.util.Date;
import anon.util.IXMLEncodable;

public class MultiCertPath implements IXMLEncodable
{
    public static final String XML_ELEMENT_NAME = "MultiCertPath";
    private CertPath[] m_certPaths;
    private X509DistinguishedName m_subject;
    private X509DistinguishedName m_issuer;
    private int m_documentType;
    
    protected MultiCertPath(final CertPath[] certPaths, final int documentType) {
        if (certPaths.length != 0 && certPaths[0] != null) {
            this.m_subject = certPaths[0].getFirstCertificate().getSubject();
            this.m_issuer = certPaths[0].getFirstCertificate().getIssuer();
            for (int i = 1; i < certPaths.length; ++i) {
                if (!this.m_subject.equals(certPaths[i].getFirstCertificate().getSubject())) {
                    throw new IllegalArgumentException("Wrong subject in MultiCertPath!");
                }
                if (!this.m_issuer.equals(certPaths[i].getFirstCertificate().getIssuer())) {
                    throw new IllegalArgumentException("Wrong issuer in MultiCertPath!");
                }
            }
        }
        this.m_documentType = documentType;
        this.m_certPaths = certPaths;
    }
    
    public boolean isValid(final Date date) {
        if (!this.needsVerification()) {
            return true;
        }
        synchronized (this.m_certPaths) {
            final boolean b = this.getFirstVerifiedPath() != null;
            for (int i = 0; i < this.m_certPaths.length; ++i) {
                if (((b && this.m_certPaths[i].verify()) || !b) && this.m_certPaths[i].checkValidity(date)) {
                    return true;
                }
            }
            return false;
        }
    }
    
    private boolean needsVerification() {
        return SignatureVerifier.getInstance().isCheckSignatures() && SignatureVerifier.getInstance().isCheckSignatures(this.m_documentType);
    }
    
    public boolean isVerified() {
        return !this.needsVerification() || this.getFirstVerifiedPath() != null;
    }
    
    public CertPath getPath() {
        synchronized (this.m_certPaths) {
            CertPath firstVerifiedPath = this.getFirstVerifiedPath();
            if (firstVerifiedPath == null) {
                firstVerifiedPath = this.m_certPaths[0];
            }
            return firstVerifiedPath;
        }
    }
    
    public Vector getPaths() {
        final Vector<CertPath> vector = new Vector<CertPath>();
        for (int i = 0; i < this.m_certPaths.length; ++i) {
            vector.addElement(this.m_certPaths[i]);
        }
        return vector;
    }
    
    public CertPath getFirstVerifiedPath() {
        synchronized (this.m_certPaths) {
            for (int i = 0; i < this.m_certPaths.length; ++i) {
                if (this.m_certPaths[i] != null && this.m_certPaths[i].verify()) {
                    return this.m_certPaths[i];
                }
            }
            return null;
        }
    }
    
    public Vector getEndEntityKeys() {
        synchronized (this.m_certPaths) {
            final Vector vector = new Vector<IMyPublicKey>();
            for (int i = 0; i < this.m_certPaths.length; ++i) {
                if (!this.needsVerification() || this.m_certPaths[i].verify()) {
                    vector.addElement(this.m_certPaths[i].getFirstCertificate().getPublicKey());
                }
            }
            if (vector.size() != 0) {
                return vector;
            }
            return null;
        }
    }
    
    public X509DistinguishedName getSubject() {
        return this.m_subject;
    }
    
    public X509DistinguishedName getIssuer() {
        return this.m_issuer;
    }
    
    public int countPaths() {
        synchronized (this.m_certPaths) {
            return this.m_certPaths.length;
        }
    }
    
    public int countVerifiedPaths() {
        int n = 0;
        if (!this.needsVerification()) {
            return this.countPaths();
        }
        synchronized (this.m_certPaths) {
            for (int i = 0; i < this.m_certPaths.length; ++i) {
                if (this.m_certPaths[i].verify()) {
                    ++n;
                }
            }
            return n;
        }
    }
    
    public int countVerifiedAndValidPaths() {
        int n = 0;
        synchronized (this.m_certPaths) {
            for (int i = 0; i < this.m_certPaths.length; ++i) {
                if ((!this.needsVerification() || this.m_certPaths[i].verify()) && this.m_certPaths[i].checkValidity(new Date())) {
                    ++n;
                }
            }
        }
        return n;
    }
    
    public int getMaxLength() {
        int length = 0;
        synchronized (this.m_certPaths) {
            for (int i = 0; i < this.m_certPaths.length; ++i) {
                if (this.m_certPaths[i].length() > length) {
                    length = this.m_certPaths[i].length();
                }
            }
            return length;
        }
    }
    
    public CertPathInfo[] getPathInfos() {
        synchronized (this.m_certPaths) {
            final CertPathInfo[] array = new CertPathInfo[this.m_certPaths.length];
            for (int i = 0; i < this.m_certPaths.length; ++i) {
                array[i] = this.m_certPaths[i].getPathInfo();
                if (!this.needsVerification()) {
                    array[i].setVerified(true);
                }
            }
            return array;
        }
    }
    
    public Element toXmlElement(final Document document) {
        if (document == null) {
            return null;
        }
        final Element element = document.createElement("MultiCertPath");
        synchronized (this.m_certPaths) {
            for (int i = 0; i < this.m_certPaths.length; ++i) {
                final Enumeration elements = this.m_certPaths[i].getCertificates().elements();
                while (elements.hasMoreElements()) {
                    element.appendChild(elements.nextElement().toXmlElement(document));
                }
            }
        }
        return element;
    }
}
