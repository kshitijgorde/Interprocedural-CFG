// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import anon.crypto.XMLSignature;
import anon.crypto.IMyPrivateKey;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Document;
import java.sql.Timestamp;
import anon.crypto.IMyPublicKey;
import anon.util.IXMLEncodable;

public class XMLAccountCertificate implements IXMLEncodable
{
    private IMyPublicKey m_publicKey;
    private Timestamp m_creationTime;
    private long m_accountNumber;
    private String m_biID;
    private Document m_docTheAccountCert;
    
    public XMLAccountCertificate(final IMyPublicKey publicKey, final long accountNumber, final Timestamp creationTime, final String biID) {
        this.m_publicKey = publicKey;
        this.m_accountNumber = accountNumber;
        this.m_creationTime = creationTime;
        this.m_biID = biID;
        (this.m_docTheAccountCert = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheAccountCert));
    }
    
    public XMLAccountCertificate(final String s) throws Exception {
        final Document xmlDocument = XMLUtil.toXMLDocument(s);
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docTheAccountCert = xmlDocument;
    }
    
    public XMLAccountCertificate(final byte[] array) throws Exception {
        final Document xmlDocument = XMLUtil.toXMLDocument(array);
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docTheAccountCert = xmlDocument;
    }
    
    public XMLAccountCertificate(final Element values) throws Exception {
        this.setValues(values);
        (this.m_docTheAccountCert = XMLUtil.createDocument()).appendChild(XMLUtil.importNode(this.m_docTheAccountCert, values, true));
    }
    
    private void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals("AccountCertificate")) {
            throw new Exception("XMLAccountCertificate: cannot parse, wrong xml format!");
        }
        if (!element.getAttribute("version").equals("1.0")) {
            throw new Exception("XMLAccountCertificate: cannot parse, cert version is " + element.getAttribute("version") + " but 1.0 was expected.");
        }
        this.m_accountNumber = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "AccountNumber"), 0L);
        if (this.m_accountNumber == 0L) {
            throw new Exception("XMLAccountCertificate: cannot parse accountnumber");
        }
        this.m_biID = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "BiID"), "");
        if (this.m_biID.equals("")) {
            throw new Exception("XMLAccountCertificate: cannot parse BiID");
        }
        this.m_creationTime = Timestamp.valueOf(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "CreationTime"), "0"));
        final Element element2 = (Element)XMLUtil.getFirstChildByName(element, "JapPublicKey");
        if (element2 == null) {
            throw new Exception("XMLAccountCertificate: cannot parse public key");
        }
        this.m_publicKey = new XMLJapPublicKey(element2).getPublicKey();
    }
    
    private Element internal_toXmlElement(final Document document) {
        final Element element = document.createElement("AccountCertificate");
        element.setAttribute("version", "1.0");
        final Element element2 = document.createElement("AccountNumber");
        XMLUtil.setValue(element2, Long.toString(this.m_accountNumber));
        element.appendChild(element2);
        final Element element3 = document.createElement("BiID");
        XMLUtil.setValue(element3, this.m_biID);
        element.appendChild(element3);
        final Element element4 = document.createElement("CreationTime");
        XMLUtil.setValue(element4, this.m_creationTime.toString());
        element.appendChild(element4);
        final Element element5 = document.createElement("JapPublicKey");
        element.appendChild(element5);
        element5.setAttribute("version", "1.0");
        element5.appendChild(this.m_publicKey.toXmlElement(document));
        return element;
    }
    
    public long getAccountNumber() {
        return this.m_accountNumber;
    }
    
    public Timestamp getCreationTime() {
        return this.m_creationTime;
    }
    
    public IMyPublicKey getPublicKey() {
        return this.m_publicKey;
    }
    
    public boolean sign(final IMyPrivateKey myPrivateKey) {
        try {
            XMLSignature.sign(this.m_docTheAccountCert, myPrivateKey);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public Element toXmlElement(final Document document) {
        try {
            return (Element)XMLUtil.importNode(document, this.m_docTheAccountCert.getDocumentElement(), true);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public String getPIID() {
        return this.m_biID;
    }
}
