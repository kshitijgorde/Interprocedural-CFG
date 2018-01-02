// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import anon.crypto.XMLSignature;
import anon.crypto.IMyPrivateKey;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Document;
import java.sql.Timestamp;
import anon.util.IXMLEncodable;

public class XMLMixAccountBalance implements IXMLEncodable
{
    private int m_balance;
    private Timestamp m_lastUpdate;
    private Document m_docTheMixAccountBalance;
    public static String ms_strElemName;
    
    public XMLMixAccountBalance(final int balance, final Timestamp lastUpdate) {
        this.m_balance = balance;
        this.m_lastUpdate = lastUpdate;
        (this.m_docTheMixAccountBalance = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheMixAccountBalance));
    }
    
    private Node internal_toXmlElement(final Document document) {
        final Element element = document.createElement(XMLMixAccountBalance.ms_strElemName);
        element.setAttribute("version", "1.0");
        final Element element2 = document.createElement("Balance");
        XMLUtil.setValue(element2, this.m_balance);
        element.appendChild(element2);
        final Element element3 = document.createElement("LastUpdate");
        XMLUtil.setValue(element3, this.m_lastUpdate.toString());
        element.appendChild(element3);
        return element;
    }
    
    public XMLMixAccountBalance(final String s) throws Exception {
        final Document xmlDocument = XMLUtil.readXMLDocument(new ByteArrayInputStream(s.getBytes()));
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docTheMixAccountBalance = xmlDocument;
    }
    
    public XMLMixAccountBalance(final byte[] array) throws Exception {
        final Document xmlDocument = XMLUtil.readXMLDocument(new ByteArrayInputStream(array));
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docTheMixAccountBalance = xmlDocument;
    }
    
    public XMLMixAccountBalance(final Element values) throws Exception {
        this.setValues(values);
        (this.m_docTheMixAccountBalance = XMLUtil.createDocument()).appendChild(XMLUtil.importNode(this.m_docTheMixAccountBalance, values, true));
    }
    
    public XMLMixAccountBalance(final Document document) throws Exception {
        this.setValues(document.getDocumentElement());
    }
    
    private void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals("MixAccountBalance")) {
            throw new Exception("XMLMixAccountBalance: cannot parse, wrong xml format!");
        }
        if (!element.getAttribute("version").equals("1.0")) {
            throw new Exception("XMLMixAccountBalance: cannot parse, cert version is " + element.getAttribute("version") + " but 1.0 was expected.");
        }
        this.m_balance = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Balance"), -1000);
        if (this.m_balance == -1000) {
            throw new Exception("XMLMixAccountBalance: cannot parse the balance");
        }
        this.m_lastUpdate = Timestamp.valueOf(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "LastUpdate"), "0"));
    }
    
    public boolean sign(final IMyPrivateKey myPrivateKey) {
        try {
            XMLSignature.sign(this.m_docTheMixAccountBalance, myPrivateKey);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public int getBalance() {
        return this.m_balance;
    }
    
    public Timestamp getLastUpdate() {
        return this.m_lastUpdate;
    }
    
    public Element toXmlElement(final Document document) {
        try {
            return (Element)XMLUtil.importNode(document, this.m_docTheMixAccountBalance.getDocumentElement(), true);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    static {
        XMLMixAccountBalance.ms_strElemName = "MixAccountBalance";
    }
}
