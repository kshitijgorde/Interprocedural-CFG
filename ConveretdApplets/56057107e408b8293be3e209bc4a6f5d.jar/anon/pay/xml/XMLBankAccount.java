// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import logging.LogHolder;
import logging.LogType;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Document;
import anon.util.IXMLEncodable;

public class XMLBankAccount implements IXMLEncodable
{
    private String m_type;
    private String m_details;
    private String m_operatorCert;
    private Document m_docTheBankAccount;
    public static String ms_strElemName;
    
    public XMLBankAccount(final String type, final String details) {
        this.m_operatorCert = "none";
        this.m_type = type;
        this.m_details = details;
        (this.m_docTheBankAccount = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheBankAccount));
    }
    
    public XMLBankAccount(final String type, final String details, final String operatorCert) {
        this.m_operatorCert = "none";
        this.m_type = type;
        this.m_details = details;
        this.m_operatorCert = operatorCert;
        (this.m_docTheBankAccount = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheBankAccount));
    }
    
    private Node internal_toXmlElement(final Document document) {
        final Element element = document.createElement(XMLBankAccount.ms_strElemName);
        element.setAttribute("version", "1.0");
        final Element element2 = document.createElement("Type");
        XMLUtil.setValue(element2, this.m_type);
        element.appendChild(element2);
        final Element element3 = document.createElement("Details");
        XMLUtil.setValue(element3, this.m_details);
        element.appendChild(element3);
        if (!this.m_operatorCert.equals("none")) {
            final Element element4 = document.createElement("OperatorCert");
            XMLUtil.setValue(element4, this.m_operatorCert);
            element.appendChild(element4);
        }
        return element;
    }
    
    public XMLBankAccount(final String s) throws Exception {
        this.m_operatorCert = "none";
        final Document xmlDocument = XMLUtil.readXMLDocument(new ByteArrayInputStream(s.getBytes()));
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docTheBankAccount = xmlDocument;
    }
    
    public XMLBankAccount(final char[] array) throws Exception {
        this(new String(array));
    }
    
    public XMLBankAccount(final byte[] array) throws Exception {
        this.m_operatorCert = "none";
        final Document xmlDocument = XMLUtil.readXMLDocument(new ByteArrayInputStream(array));
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docTheBankAccount = xmlDocument;
    }
    
    public XMLBankAccount(final Element values) throws Exception {
        this.m_operatorCert = "none";
        this.setValues(values);
        (this.m_docTheBankAccount = XMLUtil.createDocument()).appendChild(XMLUtil.importNode(this.m_docTheBankAccount, values, true));
    }
    
    public XMLBankAccount(final Document docTheBankAccount) throws Exception {
        this.m_operatorCert = "none";
        this.setValues(docTheBankAccount.getDocumentElement());
        this.m_docTheBankAccount = docTheBankAccount;
    }
    
    private void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals("BankAccount")) {
            throw new Exception("XMLBankAccount: cannot parse, wrong xml format!");
        }
        this.m_type = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Type"), "error");
        if (this.m_type.equals("error")) {
            throw new Exception("XMLBankAccount: cannot parse the account type");
        }
        this.m_details = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Details"), "error");
        if (this.m_details.equals("error")) {
            throw new Exception("XMLBankAccount: cannot parse the account details");
        }
        this.m_operatorCert = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "OperatorCert"), "none");
        if (this.m_operatorCert.equals("error")) {
            LogHolder.log(3, LogType.PAY, "XMLBankAccount: no operator cert set");
        }
    }
    
    public String getType() {
        return this.m_type;
    }
    
    public String getDetails() {
        return this.m_details;
    }
    
    public String getOperatorCert() {
        return this.m_operatorCert;
    }
    
    public Element toXmlElement(final Document document) {
        try {
            return (Element)XMLUtil.importNode(document, this.m_docTheBankAccount.getDocumentElement(), true);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    static {
        XMLBankAccount.ms_strElemName = "BankAccount";
    }
}
