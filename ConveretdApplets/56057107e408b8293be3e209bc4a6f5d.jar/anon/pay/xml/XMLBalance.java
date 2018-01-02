// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import java.net.MalformedURLException;
import anon.util.Base64;
import org.w3c.dom.Element;
import anon.pay.PayMessage;
import anon.util.XMLParseException;
import logging.LogHolder;
import logging.LogType;
import anon.crypto.XMLSignature;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import anon.crypto.IMyPrivateKey;
import org.w3c.dom.Document;
import java.net.URL;
import java.sql.Timestamp;
import anon.util.IXMLEncodable;

public class XMLBalance implements IXMLEncodable
{
    private static final String DEFAULT_RATE_ENDDATE = "3000-01-01 00:00:00.00000000";
    private long m_lAccountNumber;
    private Timestamp m_Timestamp;
    private Timestamp m_ValidTime;
    private long m_lDeposit;
    private long m_lSpent;
    private Timestamp m_flatEnddate;
    private long m_volumeKBytesleft;
    private long m_volumeBytesleft;
    private String m_message;
    private String m_messageText;
    private URL m_messageLink;
    private Document m_docTheBalance;
    
    public XMLBalance(final long lAccountNumber, final long lDeposit, final long lSpent, final Timestamp timestamp, final Timestamp validTime, final long volumeBytesleft, final Timestamp flatEnddate, final IMyPrivateKey myPrivateKey) {
        this.m_docTheBalance = null;
        this.m_lDeposit = lDeposit;
        this.m_lSpent = lSpent;
        this.m_Timestamp = timestamp;
        if (this.m_Timestamp == null) {
            this.m_Timestamp = new Timestamp(System.currentTimeMillis());
        }
        this.m_ValidTime = validTime;
        if (this.m_ValidTime == null) {
            this.m_ValidTime = Timestamp.valueOf("3000-01-01 00:00:00.00000000");
        }
        this.m_lAccountNumber = lAccountNumber;
        this.m_volumeKBytesleft = volumeBytesleft / 1000L;
        this.m_volumeBytesleft = volumeBytesleft;
        this.m_flatEnddate = flatEnddate;
        if (this.m_flatEnddate == null) {
            this.m_flatEnddate = Timestamp.valueOf("3000-01-01 00:00:00.00000000");
        }
        (this.m_docTheBalance = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheBalance));
        if (myPrivateKey != null) {
            this.sign(myPrivateKey);
        }
    }
    
    public void sign(final IMyPrivateKey myPrivateKey) {
        try {
            XMLSignature.sign(this.m_docTheBalance, myPrivateKey);
        }
        catch (XMLParseException ex) {
            LogHolder.log(7, LogType.PAY, "Could not sign XMLBalance");
        }
    }
    
    public void setMessage(final PayMessage payMessage) {
        if (payMessage == null) {
            this.m_message = null;
            this.m_messageLink = null;
            this.m_messageText = null;
        }
        else {
            this.m_message = payMessage.getShortMessage();
            this.m_messageLink = payMessage.getMessageLink();
            this.m_messageText = payMessage.getMessageText();
        }
        (this.m_docTheBalance = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheBalance));
    }
    
    public XMLBalance(final Document docTheBalance) throws Exception {
        this.m_docTheBalance = null;
        this.setValues(docTheBalance.getDocumentElement());
        this.m_docTheBalance = docTheBalance;
    }
    
    public XMLBalance(final String s) throws Exception {
        this.m_docTheBalance = null;
        final Document xmlDocument = XMLUtil.toXMLDocument(s);
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docTheBalance = xmlDocument;
    }
    
    public XMLBalance(final Element values) throws Exception {
        this.m_docTheBalance = null;
        this.setValues(values);
        (this.m_docTheBalance = XMLUtil.createDocument()).appendChild(XMLUtil.importNode(this.m_docTheBalance, values, true));
    }
    
    private void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals("Balance") || !element.getAttribute("version").equals("1.0")) {
            throw new Exception("Balance wrong XML format");
        }
        this.m_lAccountNumber = Long.parseLong(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "AccountNumber"), null));
        this.m_lDeposit = Long.parseLong(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Deposit"), null));
        this.m_lSpent = Long.parseLong(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Spent"), null));
        XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "BalanceInCent"), "0");
        this.m_flatEnddate = Timestamp.valueOf(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "FlatrateEnddate"), "3000-01-01 00:00:00.00000000"));
        this.m_volumeKBytesleft = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "VolumeBytesLeft"), 0);
        this.m_volumeBytesleft = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "BytesLeft"), 0);
        if (this.m_volumeBytesleft == 0L) {
            this.m_volumeBytesleft = this.m_volumeKBytesleft * 1000L;
        }
        final String value = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Timestamp"), null);
        if (value != null) {
            this.m_Timestamp = Timestamp.valueOf(value);
        }
        else {
            this.m_Timestamp = new Timestamp(System.currentTimeMillis());
        }
        String s = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Validtime"), "3000-01-01 00:00:00.00000000");
        this.m_ValidTime = Timestamp.valueOf(s);
        final Element element2 = (Element)XMLUtil.getFirstChildByName(element, "Message");
        if (element2 != null) {
            if (XMLUtil.parseAttribute(element2, "encoded", false)) {
                try {
                    s = XMLUtil.parseValue(element2, "");
                    if (!s.equals("")) {
                        this.m_message = Base64.decodeToString(s);
                    }
                    else {
                        this.m_message = "";
                    }
                }
                catch (Exception ex) {
                    LogHolder.log(7, LogType.PAY, "Error while reading message: " + ex + ", message (Base64) was" + s + "decoded message was" + this.m_message);
                }
            }
            else {
                this.m_message = XMLUtil.parseValue(element2, "");
            }
        }
        final Element element3 = (Element)XMLUtil.getFirstChildByName(element, "MessageLink");
        if (element3 != null) {
            s = XMLUtil.parseValue(element3, "");
            if (!s.equals("")) {
                try {
                    this.m_messageLink = new URL(s);
                }
                catch (MalformedURLException ex2) {
                    LogHolder.log(7, LogType.PAY, "Could not get URL from messagelink string: " + s + ", reason: " + ex2);
                }
            }
        }
        final Element element4 = (Element)XMLUtil.getFirstChildByName(element, "MessageText");
        if (element4 != null) {
            if (XMLUtil.parseAttribute(element4, "encoded", false)) {
                try {
                    s = XMLUtil.parseValue(element4, "");
                    if (!s.equals("")) {
                        this.m_messageText = Base64.decodeToString(s);
                    }
                    else {
                        this.m_messageText = "";
                    }
                }
                catch (Exception ex3) {
                    LogHolder.log(7, LogType.PAY, "Error while reading message: " + ex3 + ", message (Base64) was" + s + "decoded message was" + this.m_message);
                }
            }
            else {
                this.m_messageText = XMLUtil.parseValue(element4, "");
            }
        }
    }
    
    private Element internal_toXmlElement(final Document document) {
        final Element element = document.createElement("Balance");
        element.setAttribute("version", "1.0");
        final Element element2 = document.createElement("AccountNumber");
        XMLUtil.setValue(element2, this.m_lAccountNumber);
        element.appendChild(element2);
        final Element element3 = document.createElement("Deposit");
        XMLUtil.setValue(element3, this.m_lDeposit);
        element.appendChild(element3);
        final Element element4 = document.createElement("Spent");
        XMLUtil.setValue(element4, this.m_lSpent);
        element.appendChild(element4);
        final Element element5 = document.createElement("FlatrateEnddate");
        XMLUtil.setValue(element5, this.m_flatEnddate.toString());
        element.appendChild(element5);
        final Element element6 = document.createElement("VolumeBytesLeft");
        XMLUtil.setValue(element6, this.m_volumeKBytesleft);
        element.appendChild(element6);
        final Element element7 = document.createElement("BytesLeft");
        XMLUtil.setValue(element7, this.m_volumeBytesleft);
        element.appendChild(element7);
        final Element element8 = document.createElement("Timestamp");
        XMLUtil.setValue(element8, this.m_Timestamp.toString());
        element.appendChild(element8);
        final Element element9 = document.createElement("Validtime");
        XMLUtil.setValue(element9, this.m_ValidTime.toString());
        element.appendChild(element9);
        final Element element10 = document.createElement("Message");
        if (this.m_message != null) {
            XMLUtil.setValue(element10, Base64.encodeString(this.m_message));
            XMLUtil.setAttribute(element10, "encoded", true);
        }
        element.appendChild(element10);
        final Element element11 = document.createElement("MessageText");
        if (this.m_messageText != null) {
            final String encodeString = Base64.encodeString(this.m_messageText);
            XMLUtil.setAttribute(element11, "encoded", true);
            XMLUtil.setValue(element11, encodeString);
        }
        element.appendChild(element11);
        final Element element12 = document.createElement("MessageLink");
        if (this.m_messageLink != null) {
            XMLUtil.setValue(element12, this.m_messageLink.toString());
        }
        element.appendChild(element12);
        return element;
    }
    
    public long getAccountNumber() {
        return this.m_lAccountNumber;
    }
    
    public long getDeposit() {
        return this.m_lDeposit;
    }
    
    public long getSpent() {
        return this.m_lSpent;
    }
    
    public long getVolumeBytesLeft() {
        return this.m_volumeBytesleft;
    }
    
    public Timestamp getFlatEnddate() {
        return this.m_flatEnddate;
    }
    
    public Timestamp getTimestamp() {
        return this.m_Timestamp;
    }
    
    public Timestamp getValidTime() {
        return this.m_ValidTime;
    }
    
    public PayMessage getMessage() {
        if (this.m_message == null || this.m_message.equals("")) {
            return null;
        }
        return new PayMessage(this.m_message, this.m_messageText, this.m_messageLink);
    }
    
    public Element toXmlElement(final Document document) {
        try {
            return (Element)XMLUtil.importNode(document, this.m_docTheBalance.getDocumentElement(), true);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
