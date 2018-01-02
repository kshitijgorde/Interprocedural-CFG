// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import java.util.Enumeration;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import anon.util.XMLParseException;
import anon.util.XMLUtil;
import java.util.Hashtable;
import anon.util.IXMLEncodable;

public class XMLPassivePayment implements IXMLEncodable
{
    public static final String XML_ELEMENT_NAME = "PassivePayment";
    private static final String XML_DOCUMENT_VERSION = "1.0";
    private static final String VERSION = "version";
    private static final String TRANSFER_NUM = "TransferNumber";
    private static final String AMOUNT = "Amount";
    private static final String CURRENCY = "Currency";
    private static final String CHARGED = "Charged";
    private static final String PAYMENT_DATA = "PaymentData";
    private static final String REF = "ref";
    private static final String PAYMENT_NAME = "PaymentName";
    private static final String ERRORCODE = "ErrorCode";
    private static final String ERRORMSG = "ErrorMessage";
    private static final String IP = "IPAdress";
    private Hashtable m_paymentData;
    private long m_transferNumber;
    private String m_currency;
    private long m_centAmount;
    private String m_paymentName;
    private boolean m_charged;
    private String m_sErrorCode;
    private String m_sErrorMessage;
    private String m_sIP;
    public static final String KEY_COUPONCODE = "code";
    public static final String KEY_ACCOUNTNUMBER = "accountnumber";
    public static final String KEY_TRANSFERNUMBER = "transfernumber";
    public static final String KEY_VOLUMEPLAN = "volumeplan";
    public static final String KEY_MERCHANT_ID = "merchant_id";
    public static final String KEY_TRANSACTION_ID = "transaction_id";
    public static final String KEY_ERRORCODE = "errorcode";
    public static final String KEY_ERRORMESSAGE = "errormessage";
    public static final String KEY_IPADRESS = "IPAdress";
    
    public XMLPassivePayment() {
        this.m_paymentData = new Hashtable();
        this.m_sErrorCode = "";
        this.m_sErrorMessage = "";
        this.m_sIP = "";
    }
    
    public XMLPassivePayment(final String s) throws XMLParseException {
        this.m_paymentData = new Hashtable();
        this.m_sErrorCode = "";
        this.m_sErrorMessage = "";
        this.m_sIP = "";
        this.setValues(XMLUtil.toXMLDocument(s).getDocumentElement());
    }
    
    public XMLPassivePayment(final char[] array) throws XMLParseException {
        this(new String(array));
    }
    
    public XMLPassivePayment(final byte[] array) throws XMLParseException {
        this.m_paymentData = new Hashtable();
        this.m_sErrorCode = "";
        this.m_sErrorMessage = "";
        this.m_sIP = "";
        this.setValues(XMLUtil.toXMLDocument(array).getDocumentElement());
    }
    
    public XMLPassivePayment(final Document document) throws XMLParseException {
        this.m_paymentData = new Hashtable();
        this.m_sErrorCode = "";
        this.m_sErrorMessage = "";
        this.m_sIP = "";
        this.setValues(document.getDocumentElement());
    }
    
    public XMLPassivePayment(final Element values) throws XMLParseException {
        this.m_paymentData = new Hashtable();
        this.m_sErrorCode = "";
        this.m_sErrorMessage = "";
        this.m_sIP = "";
        this.setValues(values);
    }
    
    private void setValues(final Element element) throws XMLParseException {
        if (!element.getTagName().equals("PassivePayment") || !element.getAttribute("version").equals("1.0")) {
            throw new XMLParseException("PassivePayment wrong format or wrong version number");
        }
        this.m_paymentData = new Hashtable();
        final NodeList elementsByTagName = element.getElementsByTagName("PaymentData");
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            this.m_paymentData.put(XMLUtil.parseAttribute(elementsByTagName.item(i), "ref", null), XMLUtil.parseValue(elementsByTagName.item(i), null));
        }
        this.m_transferNumber = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "TransferNumber"), 0L);
        this.m_centAmount = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Amount"), 0L);
        this.m_currency = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Currency"), null);
        this.m_paymentName = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "PaymentName"), null);
        this.m_charged = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Charged"), false);
        this.m_sErrorCode = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "ErrorCode"), "0");
        this.m_sErrorMessage = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "ErrorMessage"), "");
    }
    
    public void setIP(final String sip) {
        this.m_sIP = sip;
    }
    
    public String getIP() {
        return this.m_sIP;
    }
    
    public void setErrorMessage(final String sErrorMessage) {
        this.m_sErrorMessage = sErrorMessage;
    }
    
    public String getErrorMessage() {
        return this.m_sErrorMessage;
    }
    
    public void setErrorCode(final String sErrorCode) {
        this.m_sErrorCode = sErrorCode;
    }
    
    public String getErrorCode() {
        return this.m_sErrorCode;
    }
    
    public void setPaymentName(final String paymentName) {
        this.m_paymentName = paymentName;
    }
    
    public String getPaymentName() {
        return this.m_paymentName;
    }
    
    public void setAmount(final long centAmount) {
        this.m_centAmount = centAmount;
    }
    
    public void setCurrency(final String currency) {
        this.m_currency = currency;
    }
    
    public void setCharged(final boolean charged) {
        this.m_charged = charged;
    }
    
    public void setTransferNumber(final long transferNumber) {
        this.m_transferNumber = transferNumber;
    }
    
    public void addData(final String s, final String s2) {
        this.m_paymentData.put(s, s2);
    }
    
    public long getAmount() {
        return this.m_centAmount;
    }
    
    public long getTransferNumber() {
        return this.m_transferNumber;
    }
    
    public String getCurrency() {
        return this.m_currency;
    }
    
    public boolean isCharged() {
        return this.m_charged;
    }
    
    public Enumeration getReferences() {
        return this.m_paymentData.keys();
    }
    
    public String getPaymentData(final String s) {
        return this.m_paymentData.get(s);
    }
    
    public String getAllPaymentData() {
        String s = "";
        final Enumeration<String> keys = (Enumeration<String>)this.m_paymentData.keys();
        while (keys.hasMoreElements()) {
            final String s2 = keys.nextElement();
            s = s + s2 + " = " + (String)this.m_paymentData.get(s2);
            if (keys.hasMoreElements()) {
                s += "\n";
            }
        }
        return s;
    }
    
    public Enumeration getPaymentDataKeys() {
        return this.m_paymentData.keys();
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("PassivePayment");
        element.setAttribute("version", "1.0");
        final Element element2 = document.createElement("TransferNumber");
        XMLUtil.setValue(element2, this.m_transferNumber);
        element.appendChild(element2);
        final Element element3 = document.createElement("PaymentName");
        XMLUtil.setValue(element3, this.m_paymentName);
        element.appendChild(element3);
        final Element element4 = document.createElement("Amount");
        XMLUtil.setValue(element4, String.valueOf(this.m_centAmount));
        element.appendChild(element4);
        final Element element5 = document.createElement("Currency");
        XMLUtil.setValue(element5, this.m_currency);
        element.appendChild(element5);
        final Element element6 = document.createElement("Charged");
        XMLUtil.setValue(element6, this.m_charged);
        element.appendChild(element6);
        final Element element7 = document.createElement("ErrorCode");
        XMLUtil.setValue(element7, this.m_sErrorCode);
        element.appendChild(element7);
        final Element element8 = document.createElement("ErrorMessage");
        XMLUtil.setValue(element8, this.m_sErrorMessage);
        element.appendChild(element8);
        final Element element9 = document.createElement("IPAdress");
        XMLUtil.setValue(element9, this.m_sIP);
        element.appendChild(element9);
        final Enumeration<String> keys = this.m_paymentData.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final Element element10 = document.createElement("PaymentData");
            XMLUtil.setAttribute(element10, "ref", s);
            XMLUtil.setValue(element10, this.m_paymentData.get(s));
            element.appendChild(element10);
        }
        return element;
    }
}
