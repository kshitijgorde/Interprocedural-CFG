// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import anon.util.Util;
import org.bouncycastle.crypto.digests.SHA1Digest;
import java.util.Enumeration;
import org.w3c.dom.Document;
import anon.util.Base64;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import anon.util.XMLParseException;
import java.util.Vector;
import org.w3c.dom.Element;
import anon.util.IXMLEncodable;

public class XMLSignatureElement implements IXMLEncodable
{
    private static final String XML_ELEMENT_NAME = "Signature";
    private static final String ELEM_CANONICALIZATION_METHOD = "CanonicalizationMethod";
    private static final String ELEM_SIGNATURE_METHOD = "SignatureMethod";
    private static final String ELEM_SIGNATURE_VALUE = "SignatureValue";
    private static final String ELEM_KEY_INFO = "KeyInfo";
    private static final String ELEM_SIGNED_INFO = "SignedInfo";
    private static final String ELEM_REFERENCE = "Reference";
    private static final String ELEM_DIGEST_VALUE = "DigestValue";
    private static final String ELEM_DIGEST_METHOD = "DigestMethod";
    private static final String ATTR_URI = "URI";
    private static final String ATTR_ALGORITHM = "Algorithm";
    private static final String DIGEST_METHOD_ALGORITHM = "http://www.w3.org/2000/09/xmldsig#sha1";
    private XMLSignature m_parent;
    private Element m_elemSignature;
    private String m_signatureMethod;
    private String m_signatureValue;
    private String m_referenceURI;
    private String m_digestMethod;
    private String m_digestValue;
    private byte[] m_signedInfoCanonical;
    private Vector m_appendedCerts;
    private Vector m_appendedCertXMLElements;
    private CertPath m_certPath;
    
    protected XMLSignatureElement(final XMLSignature parent) {
        this.m_parent = parent;
        this.m_appendedCerts = new Vector();
        this.m_appendedCertXMLElements = new Vector();
    }
    
    protected XMLSignatureElement(final XMLSignature xmlSignature, final Element element, final IMyPrivateKey myPrivateKey, final byte[] array) throws Exception {
        this(xmlSignature);
        this.createSignatureElement(myPrivateKey, element, array);
    }
    
    protected XMLSignatureElement(final XMLSignature parent, final Element elemSignature) throws XMLParseException {
        if (elemSignature == null || !elemSignature.getNodeName().equals("Signature")) {
            throw new XMLParseException("##__root__##", "This is no signature element!");
        }
        this.m_parent = parent;
        this.findCertificates(this.m_elemSignature = elemSignature);
        final Node firstChildByName = XMLUtil.getFirstChildByName(this.m_elemSignature, "SignedInfo");
        if (firstChildByName == null) {
            this.m_signedInfoCanonical = XMLSignature.toCanonicalDeprecated(this.m_elemSignature);
            if (this.m_signedInfoCanonical == null) {
                throw new XMLParseException("SignedInfo");
            }
        }
        else {
            this.m_signedInfoCanonical = XMLSignature.toCanonical(firstChildByName);
            this.m_signatureMethod = XMLUtil.parseValue(XMLUtil.getFirstChildByName(firstChildByName, "SignatureMethod"), "");
            final Node firstChildByName2 = XMLUtil.getFirstChildByName(firstChildByName, "Reference");
            if (firstChildByName2 == null) {
                throw new XMLParseException("Reference");
            }
            this.m_referenceURI = XMLUtil.parseAttribute(firstChildByName2, "URI", "");
            this.m_digestMethod = XMLUtil.parseValue(XMLUtil.getFirstChildByName(firstChildByName2, "DigestMethod"), "");
            final Node firstChildByName3 = XMLUtil.getFirstChildByName(firstChildByName2, "DigestValue");
            if (firstChildByName3 == null) {
                throw new XMLParseException("DigestValue");
            }
            this.m_digestValue = XMLUtil.parseValue(firstChildByName3, "");
        }
        final Node firstChildByName4 = XMLUtil.getFirstChildByName(this.m_elemSignature, "SignatureValue");
        if (firstChildByName4 == null) {
            throw new XMLParseException("SignatureValue");
        }
        this.m_signatureValue = XMLUtil.parseValue(firstChildByName4, "");
    }
    
    private void createSignatureElement(final IMyPrivateKey myPrivateKey, final Element element, final byte[] array) throws Exception {
        this.m_referenceURI = "";
        this.m_digestMethod = "http://www.w3.org/2000/09/xmldsig#sha1";
        this.m_digestValue = new String(Base64.encode(array, false));
        final Document ownerDocument = element.getOwnerDocument();
        final Element element2 = ownerDocument.createElement("SignedInfo");
        final Element element3 = ownerDocument.createElement("CanonicalizationMethod");
        final Element element4 = ownerDocument.createElement("SignatureMethod");
        final String xmlSignatureAlgorithmReference = myPrivateKey.getSignatureAlgorithm().getXMLSignatureAlgorithmReference();
        if (xmlSignatureAlgorithmReference != null) {
            XMLUtil.setAttribute(element4, "Algorithm", this.m_signatureMethod = xmlSignatureAlgorithmReference);
        }
        else {
            this.m_signatureMethod = "";
        }
        final Element element5 = ownerDocument.createElement("Reference");
        if (this.m_referenceURI.length() > 0) {
            element5.setAttribute("URI", this.m_referenceURI);
        }
        final Element element6 = ownerDocument.createElement("DigestMethod");
        XMLUtil.setAttribute(element6, "Algorithm", "http://www.w3.org/2000/09/xmldsig#sha1");
        final Element element7 = ownerDocument.createElement("DigestValue");
        XMLUtil.setValue(element7, this.m_digestValue);
        element5.appendChild(element6);
        element5.appendChild(element7);
        element2.appendChild(element3);
        element2.appendChild(element4);
        element2.appendChild(element5);
        this.m_signedInfoCanonical = XMLSignature.toCanonical(element2);
        final byte[] encodeForXMLSignature = myPrivateKey.getSignatureAlgorithm().encodeForXMLSignature(ByteSignature.sign(this.m_signedInfoCanonical, myPrivateKey));
        if (encodeForXMLSignature == null) {
            throw new Exception();
        }
        this.m_signatureValue = new String(Base64.encode(encodeForXMLSignature, false));
        final Element element8 = ownerDocument.createElement("SignatureValue");
        element8.appendChild(ownerDocument.createTextNode(this.m_signatureValue));
        final Element element9 = ownerDocument.createElement("Signature");
        element9.appendChild(element2);
        element9.appendChild(element8);
        element.appendChild(element9);
        this.m_elemSignature = element9;
    }
    
    private synchronized void findCertificates(final Element element) {
        this.m_appendedCerts = new Vector();
        this.m_appendedCertXMLElements = new Vector();
        final Element element2 = (Element)XMLUtil.getFirstChildByName(element, "KeyInfo");
        if (element2 == null) {
            return;
        }
        final Element element3 = (Element)XMLUtil.getFirstChildByName(element2, "X509Data");
        if (element3 == null) {
            return;
        }
        for (Node node = XMLUtil.getFirstChildByName(element3, "X509Certificate"); node != null; node = node.getNextSibling()) {
            try {
                final JAPCertificate instance = JAPCertificate.getInstance(node);
                if (instance != null) {
                    this.m_appendedCerts.addElement(instance);
                    this.m_appendedCertXMLElements.addElement(node);
                }
            }
            catch (ClassCastException ex) {}
        }
    }
    
    public boolean verifyFast(final Node node, final IMyPublicKey myPublicKey) throws XMLParseException {
        final boolean verify = this.verify(node, myPublicKey);
        if (XMLUtil.getStorageMode() == 2) {
            this.m_elemSignature = null;
            this.m_signedInfoCanonical = null;
        }
        return verify;
    }
    
    public boolean verify(final Node node, final int n, final Vector vector) throws XMLParseException {
        if (this.m_appendedCerts.size() > 0) {
            final Enumeration<JAPCertificate> elements = this.m_appendedCerts.elements();
            while (elements.hasMoreElements()) {
                final JAPCertificate japCertificate = elements.nextElement();
                if (this.verify(node, japCertificate.getPublicKey())) {
                    final Vector vector2 = (Vector)this.getCertificates().clone();
                    vector2.removeElement(japCertificate);
                    this.m_certPath = CertPath.getInstance(japCertificate, n, vector2);
                    if (XMLUtil.getStorageMode() == 2) {
                        this.m_elemSignature = null;
                        this.m_signedInfoCanonical = null;
                    }
                    return true;
                }
            }
        }
        else {
            final Enumeration<CertPath> elements2 = vector.elements();
            while (elements2.hasMoreElements()) {
                final CertPath certPath = elements2.nextElement();
                if (this.verify(node, certPath.getFirstCertificate().getPublicKey())) {
                    this.m_certPath = certPath;
                    if (XMLUtil.getStorageMode() == 2) {
                        this.m_elemSignature = null;
                        this.m_signedInfoCanonical = null;
                    }
                    return true;
                }
            }
        }
        if (XMLUtil.getStorageMode() == 2) {
            this.m_elemSignature = null;
            this.m_signedInfoCanonical = null;
        }
        return false;
    }
    
    private boolean verify(final Node node, final IMyPublicKey myPublicKey) throws XMLParseException {
        return myPublicKey != null && node != null && this.checkMessageDigest(node) && this.checkSignature(myPublicKey);
    }
    
    private boolean checkSignature(final IMyPublicKey myPublicKey) {
        final byte[] decodeForXMLSignature = myPublicKey.getSignatureAlgorithm().decodeForXMLSignature(Base64.decode(this.m_signatureValue));
        return decodeForXMLSignature != null && ByteSignature.verify(this.m_signedInfoCanonical, decodeForXMLSignature, myPublicKey);
    }
    
    private boolean checkMessageDigest(final Node node) throws XMLParseException {
        if (this.m_digestMethod == null) {
            return true;
        }
        final SHA1Digest sha1Digest = new SHA1Digest();
        final byte[] array = new byte[sha1Digest.getDigestSize()];
        final byte[] canonical = XMLSignature.toCanonical(node, this.m_parent.getSignatureElements());
        sha1Digest.update(canonical, 0, canonical.length);
        sha1Digest.doFinal(array, 0);
        return Util.arraysEqual(Base64.decode(this.m_digestValue), array);
    }
    
    protected Element getSignatureElement() {
        return this.m_elemSignature;
    }
    
    public String getSignatureMethod() {
        return this.m_signatureMethod;
    }
    
    public String getDigestMethod() {
        return this.m_digestMethod;
    }
    
    public String getReferenceURI() {
        return this.m_referenceURI.trim();
    }
    
    public CertPath getCertPath() {
        return this.m_certPath;
    }
    
    private synchronized Vector getCertificates() {
        final Vector<Object> vector = new Vector<Object>(this.m_appendedCerts.size());
        final Enumeration<Object> elements = this.m_appendedCerts.elements();
        while (elements.hasMoreElements()) {
            vector.addElement(elements.nextElement());
        }
        return vector;
    }
    
    public synchronized boolean containsCertificate(final JAPCertificate japCertificate) {
        return this.m_appendedCerts.contains(japCertificate);
    }
    
    public synchronized int countCertificates() {
        return this.m_appendedCerts.size();
    }
    
    public synchronized void clearCertificates() {
        final Enumeration<Element> elements = this.m_appendedCertXMLElements.elements();
        while (elements.hasMoreElements()) {
            final Element element = elements.nextElement();
            final Node parentNode = element.getParentNode();
            if (parentNode != null) {
                parentNode.removeChild(element);
            }
        }
        this.m_appendedCertXMLElements.removeAllElements();
        this.m_appendedCerts.removeAllElements();
    }
    
    public synchronized boolean removeCertificate(final JAPCertificate japCertificate) {
        final int index = this.m_appendedCerts.indexOf(japCertificate);
        if (index >= 0) {
            this.m_appendedCerts.removeElementAt(index);
            if (index >= this.m_appendedCertXMLElements.size()) {
                this.m_appendedCertXMLElements.removeElementAt(index);
                return true;
            }
        }
        return false;
    }
    
    public synchronized boolean addCertificate(final JAPCertificate japCertificate) {
        if (japCertificate == null) {
            return false;
        }
        Node node = XMLUtil.getFirstChildByName(this.m_elemSignature, "KeyInfo");
        if (node == null) {
            node = this.m_elemSignature.getOwnerDocument().createElement("KeyInfo");
            this.m_elemSignature.appendChild(node);
        }
        Node node2 = XMLUtil.getFirstChildByName(node, "X509Data");
        if (node2 == null) {
            node2 = this.m_elemSignature.getOwnerDocument().createElement("X509Data");
            node.appendChild(node2);
        }
        if (this.m_appendedCerts.contains(japCertificate) || !this.checkSignature(japCertificate.getPublicKey())) {
            return false;
        }
        final Element xmlElement = japCertificate.toXmlElement(this.m_elemSignature.getOwnerDocument());
        this.m_appendedCerts.addElement(japCertificate);
        this.m_appendedCertXMLElements.addElement(xmlElement);
        node2.appendChild(xmlElement);
        return true;
    }
    
    public Element toXmlElement(final Document document) {
        Element xmlElementInternal = this.toXmlElementInternal(document);
        if (this.m_elemSignature == xmlElementInternal) {
            xmlElementInternal = (Element)xmlElementInternal.cloneNode(true);
        }
        return xmlElementInternal;
    }
    
    private Element toXmlElementInternal(final Document document) {
        if (this.m_elemSignature.getOwnerDocument() == document) {
            return this.m_elemSignature;
        }
        try {
            return (Element)XMLUtil.importNode(document, this.m_elemSignature, true);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
