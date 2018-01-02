// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Document;
import anon.util.IXMLEncodable;

public class XMLEmail implements IXMLEncodable
{
    private String m_senderName;
    private String m_replyAddress;
    private String m_bodyText;
    private String m_receiverAddress;
    private String m_subject;
    private String m_senderIdentification;
    private Document m_docTheEmail;
    public static String ms_strElemName;
    
    public XMLEmail(final String senderName, final String replyAddress, final String bodyText, final String senderIdentification) {
        this.m_senderName = senderName;
        this.m_replyAddress = replyAddress;
        this.m_bodyText = bodyText;
        this.m_senderIdentification = senderIdentification;
        this.setDefaultValues();
        (this.m_docTheEmail = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheEmail));
    }
    
    public XMLEmail(final String senderName, final String replyAddress, final String bodyText, final String receiverAddress, final String subject, final String senderIdentification) {
        this.m_senderName = senderName;
        this.m_replyAddress = replyAddress;
        this.m_bodyText = bodyText;
        this.m_receiverAddress = receiverAddress;
        this.m_subject = subject;
        this.m_senderIdentification = senderIdentification;
        this.setDefaultValues();
        (this.m_docTheEmail = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheEmail));
    }
    
    private void setDefaultValues() {
        if (this.m_receiverAddress == null || this.m_receiverAddress.equals("")) {
            this.m_receiverAddress = "jap@inf.tu-dresden.de";
        }
        if (this.m_replyAddress == null || this.m_replyAddress.equals("")) {
            this.m_replyAddress = "no return";
        }
        if (this.m_senderName == null || this.m_senderName.equals("")) {
            this.m_senderName = "Unknown Sender";
        }
        if (this.m_subject == null || this.m_subject.equals("")) {
            this.m_subject = "AN.ON support request";
        }
        if (this.m_bodyText == null || this.m_bodyText.equals("")) {
            this.m_bodyText = "message is empty";
        }
    }
    
    private Node internal_toXmlElement(final Document document) {
        final Element element = document.createElement(XMLEmail.ms_strElemName);
        final Element element2 = document.createElement("SenderName");
        XMLUtil.setValue(element2, this.m_senderName);
        element.appendChild(element2);
        final Element element3 = document.createElement("ReplyAddress");
        XMLUtil.setValue(element3, this.m_replyAddress);
        element.appendChild(element3);
        final Element element4 = document.createElement("ReceiverAddress");
        XMLUtil.setValue(element4, this.m_receiverAddress);
        element.appendChild(element4);
        final Element element5 = document.createElement("Subject");
        XMLUtil.setValue(element5, this.m_subject);
        element.appendChild(element5);
        final Element element6 = document.createElement("BodyText");
        XMLUtil.setValue(element6, this.m_bodyText);
        element.appendChild(element6);
        final Element element7 = document.createElement("SenderIdentification");
        XMLUtil.setValue(element7, this.m_senderIdentification);
        element.appendChild(element7);
        return element;
    }
    
    public XMLEmail(final String s) throws Exception {
        final Document xmlDocument = XMLUtil.readXMLDocument(new ByteArrayInputStream(s.getBytes()));
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docTheEmail = xmlDocument;
    }
    
    public XMLEmail(final char[] array) throws Exception {
        this(new String(array));
    }
    
    public XMLEmail(final byte[] array) throws Exception {
        final Document xmlDocument = XMLUtil.readXMLDocument(new ByteArrayInputStream(array));
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docTheEmail = xmlDocument;
    }
    
    public XMLEmail(final Element values) throws Exception {
        this.setValues(values);
        (this.m_docTheEmail = XMLUtil.createDocument()).appendChild(XMLUtil.importNode(this.m_docTheEmail, values, true));
    }
    
    public XMLEmail(final Document docTheEmail) throws Exception {
        this.setValues(docTheEmail.getDocumentElement());
        this.m_docTheEmail = docTheEmail;
    }
    
    public String getSenderName() {
        return this.m_senderName;
    }
    
    public String getReplyAddress() {
        return this.m_replyAddress;
    }
    
    public String getReceiverAddress() {
        return this.m_receiverAddress;
    }
    
    public String getSubject() {
        return this.m_subject;
    }
    
    public String getBodyText() {
        return this.m_bodyText;
    }
    
    public String getSenderIdentification() {
        return this.m_senderIdentification;
    }
    
    private void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals("Email")) {
            throw new Exception("XMLEmail: cannot parse, wrong xml format!");
        }
        this.m_senderName = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "SenderName"), "");
        if (this.m_senderName.equals("")) {
            throw new Exception("XMLEmail: cannot parse the sender name");
        }
        this.m_receiverAddress = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "ReceiverAddress"), "");
        if (this.m_receiverAddress.equals("")) {
            throw new Exception("XMLEmail: cannot parse the receiver address");
        }
        this.m_replyAddress = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "ReplyAddress"), "");
        if (this.m_replyAddress.equals("")) {
            throw new Exception("XMLEmail: cannot parse the reply address");
        }
        this.m_subject = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Subject"), "");
        if (this.m_subject.equals("")) {
            throw new Exception("XMLEmail: cannot parse the Subject");
        }
        this.m_bodyText = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "BodyText"), "");
        if (this.m_bodyText.equals("")) {
            throw new Exception("XMLEmail: cannot parse the body text");
        }
        this.m_senderIdentification = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "SenderIdentification"), "");
    }
    
    public Element toXmlElement(final Document document) {
        try {
            return (Element)XMLUtil.importNode(document, this.m_docTheEmail.getDocumentElement(), true);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    static {
        XMLEmail.ms_strElemName = "Email";
    }
}
