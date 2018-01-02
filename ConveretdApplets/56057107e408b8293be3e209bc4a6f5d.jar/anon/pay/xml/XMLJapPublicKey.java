// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import org.w3c.dom.Document;
import anon.crypto.MyDSAPublicKey;
import org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import org.bouncycastle.crypto.params.DSAParameters;
import anon.crypto.MyRSAPublicKey;
import java.math.BigInteger;
import anon.util.Base64;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import anon.util.XMLParseException;
import anon.util.XMLUtil;
import anon.crypto.IMyPublicKey;
import anon.util.IXMLEncodable;

public class XMLJapPublicKey implements IXMLEncodable
{
    private IMyPublicKey m_publicKey;
    private static String ms_elemName;
    
    public static String getXMLElementName() {
        return XMLJapPublicKey.ms_elemName;
    }
    
    public XMLJapPublicKey(final IMyPublicKey publicKey) {
        this.m_publicKey = publicKey;
    }
    
    public XMLJapPublicKey(final byte[] array) throws Exception {
        this.setPubKey(XMLUtil.toXMLDocument(array).getDocumentElement());
    }
    
    public XMLJapPublicKey(final char[] array) throws Exception {
        this(new String(array));
    }
    
    public XMLJapPublicKey(final String s) throws XMLParseException {
        this.setPubKey(XMLUtil.toXMLDocument(s).getDocumentElement());
    }
    
    public XMLJapPublicKey(final Element pubKey) throws XMLParseException {
        this.setPubKey(pubKey);
    }
    
    public IMyPublicKey getPublicKey() {
        return this.m_publicKey;
    }
    
    private void setPubKey(final Element element) throws XMLParseException {
        if (!element.getTagName().equals(XMLJapPublicKey.ms_elemName)) {
            throw new XMLParseException("XMLJapPublicKey wrong xml structure. Tagname is" + element.getTagName());
        }
        final Element element2 = (Element)XMLUtil.getFirstChildByName(element, "RSAKeyValue");
        if (element2 != null) {
            this.m_publicKey = new MyRSAPublicKey(new BigInteger(Base64.decode(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element2, "Modulus"), ""))), new BigInteger(Base64.decode(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element2, "Exponent"), ""))));
            return;
        }
        final Element element3 = (Element)XMLUtil.getFirstChildByName(element, "DSAKeyValue");
        if (element3 != null) {
            this.m_publicKey = new MyDSAPublicKey(new DSAPublicKeyParameters(new BigInteger(Base64.decode(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element3, "Y"), ""))), new DSAParameters(new BigInteger(Base64.decode(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element3, "P"), ""))), new BigInteger(Base64.decode(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element3, "Q"), ""))), new BigInteger(Base64.decode(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element3, "G"), ""))))));
            return;
        }
        throw new XMLParseException("Wrong key format: Neither RSAKeyValue nor DSAKeyValue found!");
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement(XMLJapPublicKey.ms_elemName);
        element.setAttribute("version", "1.0");
        element.appendChild(this.m_publicKey.toXmlElement(document));
        return element;
    }
    
    public boolean equals(final XMLJapPublicKey xmlJapPublicKey) {
        if (xmlJapPublicKey == null) {
            return false;
        }
        final IMyPublicKey publicKey = xmlJapPublicKey.getPublicKey();
        final IMyPublicKey publicKey2 = this.getPublicKey();
        if (publicKey == null) {
            return publicKey2 == null;
        }
        return (!(publicKey instanceof MyRSAPublicKey) || publicKey2 instanceof MyRSAPublicKey) && (!(publicKey instanceof MyDSAPublicKey) || publicKey2 instanceof MyDSAPublicKey) && publicKey.equals(publicKey2);
    }
    
    static {
        XMLJapPublicKey.ms_elemName = "JapPublicKey";
    }
}
