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
import java.util.Date;
import java.sql.Timestamp;
import anon.util.IXMLEncodable;

public class XMLTransCert implements IXMLEncodable
{
    private Timestamp m_validTime;
    private Date m_receivedDate;
    private Date m_usedDate;
    private long m_accountNumber;
    private long m_transferNumber;
    private long m_deposit;
    private Document m_docTheTransCert;
    public static final String XML_ELEMENT_NAME_TRANSFER_CERTIFICATES = "TransferCertificates";
    public static final String XML_ELEMENT_NAME_TRANSFER_CERTIFICATE = "TransferCertificate";
    
    public XMLTransCert(final long accountNumber, final long transferNumber, final long deposit, final Timestamp validTime) {
        this.m_accountNumber = accountNumber;
        this.m_transferNumber = transferNumber;
        this.m_deposit = deposit;
        this.m_validTime = validTime;
        (this.m_docTheTransCert = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheTransCert));
    }
    
    public XMLTransCert(final String s) throws Exception {
        final Document xmlDocument = XMLUtil.toXMLDocument(s);
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docTheTransCert = xmlDocument;
    }
    
    public XMLTransCert(final Element values) throws Exception {
        this.setValues(values);
        (this.m_docTheTransCert = XMLUtil.createDocument()).appendChild(XMLUtil.importNode(this.m_docTheTransCert, values, true));
    }
    
    public XMLTransCert(final Document docTheTransCert) throws Exception {
        this.setValues(docTheTransCert.getDocumentElement());
        this.m_docTheTransCert = docTheTransCert;
    }
    
    public void setReceivedDate(final Date receivedDate) {
        this.m_receivedDate = receivedDate;
        (this.m_docTheTransCert = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheTransCert));
    }
    
    public void setUsedDate(final Date usedDate) {
        this.m_usedDate = usedDate;
        (this.m_docTheTransCert = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheTransCert));
    }
    
    public Date getReceivedDate() {
        return this.m_receivedDate;
    }
    
    public Date getUsedDate() {
        return this.m_usedDate;
    }
    
    public long getAccountNumber() {
        return this.m_accountNumber;
    }
    
    public long getTransferNumber() {
        return this.m_transferNumber;
    }
    
    public Timestamp getValidTime() {
        return this.m_validTime;
    }
    
    private void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals("TransferCertificate")) {
            throw new Exception("XMLTransCert wrong xml structure: " + XMLUtil.toString(element));
        }
        this.m_accountNumber = Long.parseLong(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "AccountNumber"), null));
        this.m_transferNumber = Long.parseLong(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "TransferNumber"), null));
        this.m_validTime = Timestamp.valueOf(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "ValidTime"), null));
        final String value = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "ReceivedDate"), null);
        if (value != null) {
            this.m_receivedDate = new Date(Long.parseLong(value));
        }
    }
    
    private Element internal_toXmlElement(final Document document) {
        final Element element = document.createElement("TransferCertificate");
        element.setAttribute("version", "1.2");
        final Element element2 = document.createElement("AccountNumber");
        XMLUtil.setValue(element2, Long.toString(this.m_accountNumber));
        element.appendChild(element2);
        final Element element3 = document.createElement("TransferNumber");
        XMLUtil.setValue(element3, Long.toString(this.m_transferNumber));
        element.appendChild(element3);
        final Element element4 = document.createElement("Deposit");
        XMLUtil.setValue(element4, Long.toString(this.m_deposit));
        element.appendChild(element4);
        final Element element5 = document.createElement("ValidTime");
        XMLUtil.setValue(element5, this.m_validTime.toString());
        element.appendChild(element5);
        final Element element6 = document.createElement("ReceivedDate");
        if (this.m_receivedDate != null) {
            XMLUtil.setValue(element6, this.m_receivedDate.getTime());
            element.appendChild(element6);
        }
        return element;
    }
    
    public Element toXmlElement(final Document document) {
        try {
            return (Element)XMLUtil.importNode(document, this.m_docTheTransCert.getDocumentElement(), true);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public boolean sign(final IMyPrivateKey myPrivateKey) {
        try {
            XMLSignature.sign(this.m_docTheTransCert, myPrivateKey);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
}
