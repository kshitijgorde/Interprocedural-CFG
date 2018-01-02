// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import java.text.NumberFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import anon.pay.PaymentInstanceDBEntry;
import anon.crypto.IMyPrivateKey;
import anon.util.XMLParseException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import org.w3c.dom.Element;
import logging.LogHolder;
import logging.LogType;
import org.w3c.dom.Node;
import anon.crypto.XMLSignature;
import anon.util.XMLUtil;
import org.w3c.dom.Document;
import java.sql.Timestamp;
import anon.util.IXMLEncodable;

public class XMLPriceCertificate implements IXMLEncodable
{
    public static final String XML_ELEMENT_NAME = "PriceCertificate";
    private String m_subjectKeyIdentifier;
    private double m_rate;
    private Timestamp m_signatureTime;
    private String m_biID;
    private String m_hashValue;
    private Document m_docThePriceCert;
    private static final String XML_ELEM_SUBJECT_KEY_IDENTIFIER = "SubjectKeyIdentifier";
    private static final String XML_ELEM_RATE = "Rate";
    private static final String XML_ELEM_SIG_TIME = "SignatureTime";
    private static final String XML_ELEM_BIID = "BiID";
    
    public XMLPriceCertificate(final String subjectKeyIdentifier, final double rate, final Timestamp signatureTime, final String biID) {
        this.m_subjectKeyIdentifier = subjectKeyIdentifier;
        this.m_signatureTime = signatureTime;
        this.m_rate = rate;
        this.m_biID = biID;
        (this.m_docThePriceCert = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docThePriceCert));
        this.m_hashValue = XMLSignature.getHashValueOfElement(this.m_docThePriceCert);
    }
    
    public XMLPriceCertificate(final String subjectKeyIdentifier, final double rate, final String biID) {
        this.m_subjectKeyIdentifier = subjectKeyIdentifier;
        this.m_signatureTime = null;
        this.m_rate = rate;
        this.m_biID = biID;
        (this.m_docThePriceCert = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docThePriceCert));
        this.m_hashValue = XMLSignature.getHashValueOfElement(this.m_docThePriceCert);
    }
    
    public XMLPriceCertificate(final String subjectKeyIdentifier, final double rate, final Timestamp signatureTime, final String biID, final String s) {
        this.m_subjectKeyIdentifier = subjectKeyIdentifier;
        this.m_signatureTime = signatureTime;
        this.m_rate = rate;
        this.m_biID = biID;
        (this.m_docThePriceCert = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docThePriceCert));
        this.addSignatureNode(this.m_docThePriceCert, s);
        this.m_hashValue = XMLSignature.getHashValueOfElement(this.m_docThePriceCert);
    }
    
    public void addSignature(final String s) {
        this.addSignatureNode(this.m_docThePriceCert, s);
    }
    
    private void addSignatureNode(final Document document, final String s) {
        try {
            XMLUtil.importNode(document, XMLUtil.toXMLDocument(s).getDocumentElement(), true);
        }
        catch (Exception ex) {
            LogHolder.log(7, LogType.PAY, "Could not parse signature node from string");
            LogHolder.log(7, LogType.PAY, ex.getMessage());
        }
    }
    
    private Node internal_toXmlElement(final Document document) {
        final Element element = document.createElement("PriceCertificate");
        element.setAttribute("version", "1.1");
        final Element element2 = document.createElement("SubjectKeyIdentifier");
        XMLUtil.setValue(element2, this.m_subjectKeyIdentifier);
        element.appendChild(element2);
        final Element element3 = document.createElement("Rate");
        XMLUtil.setValue(element3, "0.0");
        element.appendChild(element3);
        final Element element4 = document.createElement("SignatureTime");
        String string = "";
        if (this.m_signatureTime != null) {
            string = this.m_signatureTime.toString();
        }
        XMLUtil.setValue(element4, string);
        element.appendChild(element4);
        final Element element5 = document.createElement("BiID");
        XMLUtil.setValue(element5, this.m_biID);
        element.appendChild(element5);
        return element;
    }
    
    public XMLPriceCertificate(final String s) throws Exception {
        final Document xmlDocument = XMLUtil.readXMLDocument(new ByteArrayInputStream(s.getBytes()));
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docThePriceCert = xmlDocument;
        this.m_hashValue = XMLSignature.getHashValueOfElement(this.m_docThePriceCert);
    }
    
    public XMLPriceCertificate(final String s, final String hashValue, final double rate) throws Exception {
        this(s);
        this.m_rate = rate;
        this.m_hashValue = hashValue;
    }
    
    public XMLPriceCertificate(final char[] array) throws Exception {
        this(new String(array));
    }
    
    public XMLPriceCertificate(final byte[] array) throws Exception {
        final Document xmlDocument = XMLUtil.readXMLDocument(new ByteArrayInputStream(array));
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docThePriceCert = xmlDocument;
        this.m_hashValue = XMLSignature.getHashValueOfElement(this.m_docThePriceCert);
    }
    
    public XMLPriceCertificate(final Element values) throws XMLParseException {
        this.setValues(values);
        (this.m_docThePriceCert = XMLUtil.createDocument()).appendChild(XMLUtil.importNode(this.m_docThePriceCert, values, true));
        this.m_hashValue = XMLSignature.getHashValueOfElement(this.m_docThePriceCert);
    }
    
    public XMLPriceCertificate(final Document docThePriceCert) throws Exception {
        this.setValues(docThePriceCert.getDocumentElement());
        this.m_docThePriceCert = docThePriceCert;
        this.m_hashValue = XMLSignature.getHashValueOfElement(this.m_docThePriceCert);
    }
    
    public boolean sign(final IMyPrivateKey myPrivateKey) {
        try {
            XMLSignature.sign(this.m_docThePriceCert, myPrivateKey).clearCertificates();
            this.m_hashValue = XMLSignature.getHashValueOfElement(this.m_docThePriceCert);
            return true;
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.PAY, "Error signing the certificate: ", ex);
            return false;
        }
    }
    
    public boolean verify(final PaymentInstanceDBEntry paymentInstanceDBEntry) {
        return paymentInstanceDBEntry != null && XMLSignature.verifyFast(this.m_docThePriceCert, paymentInstanceDBEntry.getCertPath().getEndEntityKeys());
    }
    
    private void setValues(final Element element) throws XMLParseException {
        XMLUtil.assertNodeName(element, "PriceCertificate");
        final Element element2 = (Element)XMLUtil.getFirstChildByName(element, "SubjectKeyIdentifier");
        XMLUtil.assertNotNull(element2);
        this.m_subjectKeyIdentifier = XMLUtil.parseValue(element2, (String)null);
        if (this.m_subjectKeyIdentifier == null) {
            throw new XMLParseException("SubjectKeyIdentifier");
        }
        this.m_rate = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Rate"), -9999.99);
        if (this.m_rate == -9999.99) {
            throw new XMLParseException("Rate");
        }
        final Element element3 = (Element)XMLUtil.getFirstChildByName(element, "SignatureTime");
        if (element3 != null) {
            final String value = XMLUtil.parseValue(element3, "0");
            if (!value.equals("0")) {
                this.m_signatureTime = Timestamp.valueOf(value);
            }
        }
        this.m_biID = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "BiID"), "unknown");
        if (this.m_biID.equals("unknown")) {
            throw new XMLParseException("BiID");
        }
    }
    
    public Timestamp getSignatureTime() {
        return this.m_signatureTime;
    }
    
    public double getRate() {
        return this.m_rate;
    }
    
    public String getSubjectKeyIdentifier() {
        return this.m_subjectKeyIdentifier;
    }
    
    public String getBiID() {
        return this.m_biID;
    }
    
    public String getHashValue() {
        return this.m_hashValue;
    }
    
    public Document getDocument() {
        return this.m_docThePriceCert;
    }
    
    public Element toXmlElement(final Document document) {
        try {
            return (Element)XMLUtil.importNode(document, this.m_docThePriceCert.getDocumentElement(), true);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public String toString() {
        final String s = new String("Price: ");
        final String formatEuroCentValue = formatEuroCentValue(this.getRate());
        String string;
        if (this.getSignatureTime() == null) {
            string = "Not signed";
        }
        else {
            string = "Signed on : " + new SimpleDateFormat("dd/MM/yy").format(this.getSignatureTime());
        }
        return s + formatEuroCentValue + ", " + string;
    }
    
    private static String formatEuroCentValue(final double n) {
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(2);
        instance.setMinimumFractionDigits(2);
        return instance.format(n) + " Eurocent";
    }
}
