// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.w3c.dom.NamedNodeMap;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import anon.util.XMLUtil;
import java.security.SignatureException;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.w3c.dom.Document;
import anon.util.Base64;
import logging.LogHolder;
import logging.LogType;
import java.security.MessageDigest;
import anon.util.XMLParseException;
import anon.util.Util;
import org.w3c.dom.Node;
import java.util.Enumeration;
import org.w3c.dom.Element;
import java.util.Vector;

public final class XMLSignature
{
    private static final String XML_ELEMENT_NAME = "Signature";
    private Vector m_signatureElements;
    private MultiCertPath m_multiCertPath;
    private String m_xoredID;
    
    private XMLSignature() {
        this.m_signatureElements = new Vector();
    }
    
    public int countSignatures() {
        return this.m_signatureElements.size();
    }
    
    protected Vector getSignatureElements() {
        final Vector<Element> vector = new Vector<Element>();
        final Enumeration<XMLSignatureElement> elements = this.m_signatureElements.elements();
        while (elements.hasMoreElements()) {
            vector.addElement(elements.nextElement().getSignatureElement());
        }
        return vector;
    }
    
    public MultiCertPath getMultiCertPath() {
        return this.m_multiCertPath;
    }
    
    private CertPath[] getCertPaths() {
        final CertPath[] array = new CertPath[this.m_signatureElements.size()];
        for (int i = 0; i < this.m_signatureElements.size(); ++i) {
            array[i] = ((XMLSignatureElement)this.m_signatureElements.elementAt(i)).getCertPath();
        }
        return array;
    }
    
    public String getXORofSKIs() {
        return this.m_xoredID;
    }
    
    private void calculateXORofSKIs() {
        final Vector<JAPCertificate> vector = new Vector<JAPCertificate>();
        final Enumeration<XMLSignatureElement> elements = this.m_signatureElements.elements();
        while (elements.hasMoreElements()) {
            vector.addElement(elements.nextElement().getCertPath().getFirstCertificate());
        }
        this.m_xoredID = JAPCertificate.calculateXORofSKIs(vector);
    }
    
    public boolean isVerified() {
        return this.m_multiCertPath.isVerified();
    }
    
    public static XMLSignature sign(final Node node, final PKCS12 pkcs12) throws XMLParseException {
        return signInternal(node, Util.toVector(pkcs12));
    }
    
    public synchronized boolean addCertificate(final JAPCertificate japCertificate) {
        if (japCertificate != null) {
            final Enumeration<XMLSignatureElement> elements = this.m_signatureElements.elements();
            while (elements.hasMoreElements()) {
                if (elements.nextElement().addCertificate(japCertificate)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static XMLSignature sign(final Node node, final IMyPrivateKey myPrivateKey) throws XMLParseException {
        return signInternal(node, Util.toVector(myPrivateKey));
    }
    
    public static XMLSignature multiSign(final Node node, final Vector vector) throws XMLParseException {
        return signInternal(node, vector);
    }
    
    public static String getHashValueOfElement(final Node node) {
        byte[] digest;
        try {
            digest = MessageDigest.getInstance("SHA-1").digest(toCanonical(node));
        }
        catch (Exception ex) {
            LogHolder.log(4, LogType.PAY, "could not create hash value of node");
            return null;
        }
        return Base64.encode(digest, false);
    }
    
    public static String getEncodedHashValue(final Element element) {
        return getHashValueOfElement(element);
    }
    
    private static XMLSignature signInternal(final Node node, final Vector vector) throws XMLParseException {
        if (node == null || vector == null || vector.size() == 0) {
            return null;
        }
        Element documentElement;
        if (node instanceof Document) {
            documentElement = ((Document)node).getDocumentElement();
        }
        else {
            if (!(node instanceof Element)) {
                return null;
            }
            documentElement = (Element)node;
        }
        final XMLSignature xmlSignature = new XMLSignature();
        final Vector removeSignatureFromInternal = removeSignatureFromInternal(documentElement);
        final byte[] canonical = toCanonical(documentElement);
        final SHA1Digest sha1Digest = new SHA1Digest();
        sha1Digest.update(canonical, 0, canonical.length);
        final byte[] array = new byte[sha1Digest.getDigestSize()];
        sha1Digest.doFinal(array, 0);
        final Enumeration<IMyPrivateKey> elements = vector.elements();
        try {
            while (elements.hasMoreElements()) {
                final IMyPrivateKey nextElement = elements.nextElement();
                PKCS12 pkcs12;
                IMyPrivateKey privateKey;
                if (nextElement instanceof IMyPrivateKey) {
                    pkcs12 = null;
                    privateKey = nextElement;
                }
                else {
                    pkcs12 = (PKCS12)nextElement;
                    privateKey = pkcs12.getPrivateKey();
                }
                final XMLSignatureElement xmlSignatureElement = new XMLSignatureElement(xmlSignature, documentElement, privateKey, array);
                if (pkcs12 != null) {
                    xmlSignatureElement.addCertificate(pkcs12.getX509Certificate());
                }
                xmlSignature.m_signatureElements.addElement(xmlSignatureElement);
            }
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.CRYPTO, "Could not sign XML document!", ex);
            if (xmlSignature.countSignatures() != 0) {
                removeSignatureFromInternal(documentElement);
            }
            if (removeSignatureFromInternal != null) {
                final Enumeration<Element> elements2 = removeSignatureFromInternal.elements();
                while (elements2.hasMoreElements()) {
                    documentElement.appendChild(elements2.nextElement());
                }
            }
            return null;
        }
        return xmlSignature;
    }
    
    public static XMLSignature getVerified(final Node node, final int n, final Vector vector) throws XMLParseException, SignatureException {
        final XMLSignature xmlSignature = findXMLSignature(node);
        if (xmlSignature == null) {
            LogHolder.log(7, LogType.CRYPTO, "Could not find the <Signature> node!");
            return null;
        }
        final Enumeration elements = xmlSignature.m_signatureElements.elements();
        while (elements.hasMoreElements()) {
            if (!elements.nextElement().verify(node, n, vector)) {
                throw new SignatureException("No verifier for a Signature found!");
            }
        }
        try {
            xmlSignature.m_multiCertPath = new MultiCertPath(xmlSignature.getCertPaths(), n);
        }
        catch (IllegalArgumentException ex) {
            LogHolder.log(6, LogType.CRYPTO, ex);
            return null;
        }
        xmlSignature.calculateXORofSKIs();
        return xmlSignature;
    }
    
    public static boolean verifyFast(final Node node, final Vector vector) {
        final Enumeration<IMyPublicKey> elements = vector.elements();
        while (elements.hasMoreElements()) {
            if (verifyFast(node, elements.nextElement())) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean verifyFast(final Node node, final IMyPublicKey myPublicKey) {
        try {
            return verify(node, myPublicKey) != null;
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    public static XMLSignature verify(final Node node, final IMyPublicKey myPublicKey) throws XMLParseException {
        final XMLSignature xmlSignature = findXMLSignature(node);
        final Enumeration elements = xmlSignature.m_signatureElements.elements();
        while (elements.hasMoreElements()) {
            try {
                if (elements.nextElement().verifyFast(node, myPublicKey)) {
                    return xmlSignature;
                }
                continue;
            }
            catch (Throwable t) {}
        }
        return null;
    }
    
    public static XMLSignature getUnverified(final Node node) throws XMLParseException {
        if (node == null) {
            return null;
        }
        return findXMLSignature(node);
    }
    
    public static boolean removeSignatureFrom(final Node node) {
        return removeSignatureFromInternal(node) != null;
    }
    
    private static Vector removeSignatureFromInternal(final Node node) {
        final Vector vector = new Vector<Element>();
        Element documentElement;
        if (node instanceof Document) {
            documentElement = ((Document)node).getDocumentElement();
        }
        else {
            if (!(node instanceof Element)) {
                return null;
            }
            documentElement = (Element)node;
        }
        Node firstChildByName;
        while ((firstChildByName = XMLUtil.getFirstChildByName(documentElement, "Signature")) != null) {
            try {
                vector.addElement((Element)documentElement.removeChild(firstChildByName));
            }
            catch (ClassCastException ex) {}
        }
        if (vector.size() == 0) {
            return null;
        }
        return vector;
    }
    
    private static XMLSignature findXMLSignature(final Node node) throws XMLParseException {
        if (node == null) {
            throw new XMLParseException("##__null__##");
        }
        Element documentElement;
        if (node instanceof Document) {
            documentElement = ((Document)node).getDocumentElement();
        }
        else {
            if (!(node instanceof Element)) {
                return null;
            }
            documentElement = (Element)node;
        }
        Node node2 = XMLUtil.getFirstChildByName(documentElement, "Signature");
        final XMLSignature xmlSignature = new XMLSignature();
        while (node2 != null) {
            try {
                xmlSignature.m_signatureElements.addElement(new XMLSignatureElement(xmlSignature, (Element)node2));
            }
            catch (ClassCastException ex) {}
            node2 = XMLUtil.getNextSiblingByName(node2, "Signature");
        }
        if (xmlSignature.m_signatureElements.size() == 0) {
            return null;
        }
        return xmlSignature;
    }
    
    public void clearCertificates() {
        final Enumeration<XMLSignatureElement> elements = this.m_signatureElements.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().clearCertificates();
        }
    }
    
    public static byte[] toCanonical(final Node node, final Vector vector) throws XMLParseException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (makeCanonical(node, byteArrayOutputStream, false, vector, false, "UTF8") == -1) {
            throw new XMLParseException(node.getNodeName(), "Could not make the node canonical!");
        }
        try {
            byteArrayOutputStream.flush();
        }
        catch (IOException ex) {}
        return byteArrayOutputStream.toByteArray();
    }
    
    public static byte[] toCanonicalDeprecated(final Node node) {
        if (node == null || node.getPreviousSibling() == null) {
            return null;
        }
        final Node parentNode = node.getParentNode();
        parentNode.removeChild(node);
        final byte[] byteArray = XMLUtil.toByteArray(parentNode.getOwnerDocument());
        parentNode.appendChild(node);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeShort(byteArray.length);
            dataOutputStream.flush();
            byteArrayOutputStream.write(byteArray);
            byteArrayOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        }
        catch (IOException ex) {
            LogHolder.log(5, LogType.CRYPTO, "Could not make xml data canonical!", ex);
            return null;
        }
    }
    
    public static byte[] toCanonical(final Node node) throws XMLParseException {
        return toCanonical(node, false);
    }
    
    public static byte[] toCanonical(final Node node, final boolean b) throws XMLParseException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (makeCanonical(node, byteArrayOutputStream, false, null, b) == -1) {
            throw new XMLParseException(node.getNodeName(), "Could not make the node canonical!");
        }
        try {
            byteArrayOutputStream.flush();
        }
        catch (IOException ex) {}
        return byteArrayOutputStream.toByteArray();
    }
    
    public static String toCanonicalString(final Element element) {
        try {
            return new String(toCanonical(element));
        }
        catch (Exception ex) {
            return "canonicalization error";
        }
    }
    
    private static int makeCanonical(final Node node, final OutputStream outputStream, final boolean b, final Node node2) {
        return makeCanonical(node, outputStream, b, node2, false);
    }
    
    private static int makeCanonical(final Node node, final OutputStream outputStream, final boolean b, final Node node2, final boolean b2) {
        return makeCanonical(node, outputStream, b, Util.toVector(node2), b2, "UTF8");
    }
    
    private static int makeCanonical(Node documentElement, final OutputStream outputStream, final boolean b, final Vector vector, final boolean b2, final String s) {
        try {
            if (documentElement == null) {
                return 0;
            }
            if (documentElement instanceof Document) {
                if (b2) {
                    outputStream.write(XMLUtil.createDocumentStructure());
                    outputStream.write(10);
                }
                documentElement = ((Document)documentElement).getDocumentElement();
            }
            if (vector != null && vector.contains(documentElement)) {
                return 0;
            }
            if (documentElement.getNodeType() == 1) {
                final Element element = (Element)documentElement;
                outputStream.write(60);
                if (s != null) {
                    outputStream.write(element.getNodeName().getBytes(s));
                }
                else {
                    outputStream.write(element.getNodeName().getBytes());
                }
                final NamedNodeMap attributes = element.getAttributes();
                if (attributes.getLength() > 0) {
                    final String[] array = new String[attributes.getLength()];
                    final String[] array2 = new String[attributes.getLength()];
                    for (int i = 0; i < attributes.getLength(); ++i) {
                        array[i] = attributes.item(i).getNodeName();
                        array2[i] = attributes.item(i).getNodeValue();
                    }
                    Util.sort(array, array2);
                    for (int j = 0; j < attributes.getLength(); ++j) {
                        outputStream.write(32);
                        if (s != null) {
                            outputStream.write(array[j].getBytes(s));
                        }
                        else {
                            outputStream.write(array[j].getBytes());
                        }
                        outputStream.write(61);
                        outputStream.write(34);
                        if (s != null) {
                            outputStream.write(array2[j].getBytes(s));
                        }
                        else {
                            outputStream.write(array2[j].getBytes());
                        }
                        outputStream.write(34);
                    }
                }
                outputStream.write(62);
                if (element.hasChildNodes() && makeCanonical(element.getFirstChild(), outputStream, true, vector, b2, s) == -1) {
                    return -1;
                }
                outputStream.write(60);
                outputStream.write(47);
                if (s != null) {
                    outputStream.write(element.getNodeName().getBytes(s));
                }
                else {
                    outputStream.write(element.getNodeName().getBytes());
                }
                outputStream.write(62);
                if (b && makeCanonical(element.getNextSibling(), outputStream, true, vector, b2, s) == -1) {
                    return -1;
                }
                return 0;
            }
            else if (documentElement.getNodeType() == 3) {
                String s2 = documentElement.getNodeValue();
                if (!b2) {
                    s2 = s2.trim();
                }
                for (int k = 0; k < XMLUtil.SPECIAL_CHARS.length; ++k) {
                    s2 = Util.replaceAll(s2, XMLUtil.SPECIAL_CHARS[k], XMLUtil.ENTITIES[k], (String[])(XMLUtil.SPECIAL_CHARS[k].equals("&") ? XMLUtil.ENTITIES : null));
                }
                if (s != null) {
                    outputStream.write(s2.getBytes(s));
                }
                else {
                    outputStream.write(s2.getBytes());
                }
                if (makeCanonical(documentElement.getNextSibling(), outputStream, true, vector, b2, s) == -1) {
                    return -1;
                }
                return 0;
            }
            else {
                if (documentElement.getNodeType() != 8) {
                    return -1;
                }
                if (b2) {
                    if (s != null) {
                        outputStream.write("<!--".getBytes(s));
                        outputStream.write(documentElement.getNodeValue().getBytes(s));
                        outputStream.write("-->\n".getBytes(s));
                    }
                    else {
                        outputStream.write("<!--".getBytes());
                        outputStream.write(documentElement.getNodeValue().getBytes());
                        outputStream.write("-->\n".getBytes());
                    }
                }
                if (makeCanonical(documentElement.getNextSibling(), outputStream, true, vector, b2, s) == -1) {
                    return -1;
                }
                return 0;
            }
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.MISC, "Error while making canonical XML", ex);
            return -1;
        }
    }
    
    public Element[] getXMLElements(final Document document) {
        final Element[] array = new Element[this.m_signatureElements.size()];
        for (int i = 0; i < this.m_signatureElements.size(); ++i) {
            array[i] = ((XMLSignatureElement)this.m_signatureElements.elementAt(i)).toXmlElement(document);
        }
        return array;
    }
}
