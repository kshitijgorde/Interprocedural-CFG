// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.w3c.dom.NodeList;
import anon.util.XMLUtil;
import java.util.Enumeration;
import org.w3c.dom.Node;
import java.util.Vector;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import java.util.Hashtable;
import anon.util.IXMLEncodable;

public class SignatureVerifier implements IXMLEncodable
{
    public static final int DOCUMENT_CLASS_NONE = 0;
    public static final int DOCUMENT_CLASS_MIX = 1;
    public static final int DOCUMENT_CLASS_INFOSERVICE = 2;
    public static final int DOCUMENT_CLASS_UPDATE = 3;
    public static final int DOCUMENT_CLASS_PAYMENT = 4;
    public static final int DOCUMENT_CLASS_TERMS = 5;
    public static final String XML_ELEMENT_NAME = "SignatureVerification";
    private static final String XML_ATTR_CHECK = "check";
    private static final String XML_DOCUMENT_CLASS = "DocumentClass";
    private static final String XML_ATTR_CLASS = "class";
    private static SignatureVerifier ms_svInstance;
    private Hashtable m_hashSignatureChecks;
    private CertificateStore m_trustedCertificates;
    private boolean m_checkSignatures;
    static /* synthetic */ Class class$anon$crypto$SignatureVerifier;
    
    private SignatureVerifier() {
        this.m_trustedCertificates = new CertificateStore();
        this.m_hashSignatureChecks = new Hashtable();
        this.m_checkSignatures = true;
    }
    
    public static SignatureVerifier getInstance() {
        final Class clazz = (SignatureVerifier.class$anon$crypto$SignatureVerifier == null) ? (SignatureVerifier.class$anon$crypto$SignatureVerifier = class$("anon.crypto.SignatureVerifier")) : SignatureVerifier.class$anon$crypto$SignatureVerifier;
        synchronized (clazz) {
            if (SignatureVerifier.ms_svInstance == null) {
                SignatureVerifier.ms_svInstance = new SignatureVerifier();
            }
        }
        return SignatureVerifier.ms_svInstance;
    }
    
    public static String getXmlSettingsRootNodeName() {
        return "SignatureVerification";
    }
    
    public synchronized void setCheckSignatures(final boolean checkSignatures) {
        if (this.m_checkSignatures != checkSignatures) {
            this.m_checkSignatures = checkSignatures;
            this.m_trustedCertificates.reset();
        }
    }
    
    public void setCheckSignatures(final int n, final boolean b) {
        this.m_hashSignatureChecks.put(new Integer(n), new Boolean(b));
    }
    
    public boolean isCheckSignatures(final int n) {
        final Boolean b = this.m_hashSignatureChecks.get(new Integer(n));
        return b == null || b;
    }
    
    public boolean isCheckSignatures() {
        return this.m_checkSignatures;
    }
    
    public CertificateStore getVerificationCertificateStore() {
        return this.m_trustedCertificates;
    }
    
    public boolean verifyXml(final Document document, final int n) {
        return !this.m_checkSignatures || !this.isCheckSignatures(n) || (document != null && this.verifyXml(document.getDocumentElement(), n));
    }
    
    public boolean verifyXml(final Element element, final int n) {
        if (!this.m_checkSignatures || !this.isCheckSignatures(n)) {
            return true;
        }
        if (element == null) {
            return false;
        }
        final XMLSignature verifiedXml = this.getVerifiedXml(element, n);
        return verifiedXml != null && verifiedXml.isVerified();
    }
    
    public XMLSignature getVerifiedXml(final Element element, final int n) {
        XMLSignature verified = null;
        synchronized (this.m_trustedCertificates) {
            Vector<CertificateInfoStructure> vector = new Vector<CertificateInfoStructure>();
            switch (n) {
                case 1: {
                    vector = (Vector<CertificateInfoStructure>)this.m_trustedCertificates.getAvailableCertificatesByType(2);
                    break;
                }
                case 2: {
                    vector = (Vector<CertificateInfoStructure>)this.m_trustedCertificates.getAvailableCertificatesByType(3);
                    break;
                }
                case 3: {
                    vector = (Vector<CertificateInfoStructure>)this.m_trustedCertificates.getAvailableCertificatesByType(4);
                    break;
                }
                case 4: {
                    vector = (Vector<CertificateInfoStructure>)this.m_trustedCertificates.getAvailableCertificatesByType(7);
                    break;
                }
                case 5: {
                    vector = (Vector<CertificateInfoStructure>)this.m_trustedCertificates.getAvailableCertificatesByType(9);
                    break;
                }
            }
            final Vector<CertPath> vector2 = new Vector<CertPath>();
            final Enumeration<CertificateInfoStructure> elements = vector.elements();
            while (elements.hasMoreElements()) {
                final CertificateInfoStructure certificateInfoStructure = elements.nextElement();
                if (certificateInfoStructure.isAvailable()) {
                    vector2.addElement(certificateInfoStructure.getCertPath());
                }
            }
            try {
                verified = XMLSignature.getVerified(element, n, vector2);
            }
            catch (Exception ex) {}
        }
        return verified;
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("SignatureVerification");
        synchronized (this.m_trustedCertificates) {
            final Element element2 = document.createElement("CheckSignatures");
            XMLUtil.setAttribute(element2, "check", this.m_checkSignatures);
            synchronized (this.m_hashSignatureChecks) {
                final Enumeration<Integer> keys = (Enumeration<Integer>)this.m_hashSignatureChecks.keys();
                while (keys.hasMoreElements()) {
                    final Integer n = keys.nextElement();
                    final boolean booleanValue = this.m_hashSignatureChecks.get(n);
                    final Element element3 = document.createElement("DocumentClass");
                    XMLUtil.setAttribute(element3, "class", n);
                    XMLUtil.setAttribute(element3, "check", booleanValue);
                    element2.appendChild(element3);
                }
            }
            final Element xmlElement = this.m_trustedCertificates.toXmlElement(document);
            element.appendChild(element2);
            element.appendChild(xmlElement);
        }
        return element;
    }
    
    public void loadSettingsFromXml(final Element element) throws Exception {
        this.loadSettingsFromXml(element, null);
    }
    
    public void loadSettingsFromXml(final Element element, final Hashtable hashtable) throws Exception {
        synchronized (this.m_trustedCertificates) {
            final Element element2 = (Element)XMLUtil.getFirstChildByName(element, "CheckSignatures");
            if (element2 == null) {
                throw new Exception("No CheckSignatures node found.");
            }
            this.m_checkSignatures = XMLUtil.parseAttribute(element2, "check", true);
            final NodeList elementsByTagName = element2.getElementsByTagName("DocumentClass");
            for (int i = 0; i < elementsByTagName.getLength(); ++i) {
                final int attribute = XMLUtil.parseAttribute(elementsByTagName.item(i), "class", -1);
                if (attribute >= 0 && (hashtable == null || !hashtable.containsKey(new Integer(attribute)))) {
                    this.m_hashSignatureChecks.put(new Integer(attribute), new Boolean(XMLUtil.parseAttribute(elementsByTagName.item(i), "check", true)));
                }
            }
            final Element element3 = (Element)XMLUtil.getFirstChildByName(element, CertificateStore.getXmlSettingsRootNodeName());
            if (element3 == null) {
                throw new Exception("No TrustedCertificates node found.");
            }
            this.m_trustedCertificates.loadSettingsFromXml(element3);
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
