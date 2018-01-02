// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import anon.crypto.IMyPublicKey;
import anon.crypto.IMyPrivateKey;
import anon.util.Util;
import java.util.Enumeration;
import org.w3c.dom.NodeList;
import logging.LogHolder;
import logging.LogType;
import anon.infoservice.MixPosition;
import org.w3c.dom.Element;
import anon.util.XMLParseException;
import anon.crypto.XMLSignature;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import anon.crypto.PKCS12;
import org.w3c.dom.Document;
import java.util.Hashtable;
import anon.util.IXMLEncodable;

public class XMLEasyCC implements IXMLEncodable
{
    public static final String XML_ELEMENT_NAME = "CC";
    private long m_lTransferredBytes;
    private long m_lAccountNumber;
    private int m_id;
    private Hashtable m_priceCerts;
    private String m_cascadeID;
    private Document m_docTheEasyCC;
    private String m_priceCertHashesConcatenated;
    private boolean m_bOldHashFormat;
    private String m_strPIID;
    
    public XMLEasyCC(final long lAccountNumber, final long lTransferredBytes, final PKCS12 pkcs12, final Hashtable priceCerts, final String cascadeID, final String strPIID) throws XMLParseException {
        this.m_id = 0;
        this.m_priceCerts = new Hashtable();
        this.m_bOldHashFormat = false;
        this.m_priceCerts = priceCerts;
        this.m_priceCertHashesConcatenated = createConcatenatedPriceCertHashes(priceCerts, true);
        this.m_lTransferredBytes = lTransferredBytes;
        this.m_lAccountNumber = lAccountNumber;
        this.m_cascadeID = cascadeID;
        this.m_strPIID = strPIID;
        (this.m_docTheEasyCC = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheEasyCC));
        if (pkcs12 != null) {
            XMLSignature.sign(this.m_docTheEasyCC, pkcs12);
        }
    }
    
    public XMLEasyCC(final byte[] array) throws Exception {
        this.m_id = 0;
        this.m_priceCerts = new Hashtable();
        this.m_bOldHashFormat = false;
        final Document xmlDocument = XMLUtil.toXMLDocument(array);
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docTheEasyCC = xmlDocument;
    }
    
    public XMLEasyCC(final String s) throws XMLParseException {
        this.m_id = 0;
        this.m_priceCerts = new Hashtable();
        this.m_bOldHashFormat = false;
        final Document xmlDocument = XMLUtil.toXMLDocument(s);
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docTheEasyCC = xmlDocument;
    }
    
    public XMLEasyCC(final char[] array) throws XMLParseException {
        this(new String(array));
    }
    
    public XMLEasyCC(final Element values) throws Exception {
        this.m_id = 0;
        this.m_priceCerts = new Hashtable();
        this.m_bOldHashFormat = false;
        this.setValues(values);
        (this.m_docTheEasyCC = XMLUtil.createDocument()).appendChild(XMLUtil.importNode(this.m_docTheEasyCC, values, true));
    }
    
    public XMLEasyCC(final XMLEasyCC xmlEasyCC) throws XMLParseException {
        this.m_id = 0;
        this.m_priceCerts = new Hashtable();
        this.m_bOldHashFormat = false;
        this.m_lTransferredBytes = xmlEasyCC.m_lTransferredBytes;
        this.m_lAccountNumber = xmlEasyCC.m_lAccountNumber;
        final boolean b = false;
        xmlEasyCC.m_id = (b ? 1 : 0);
        this.m_id = (b ? 1 : 0);
        this.m_priceCerts = (Hashtable)xmlEasyCC.m_priceCerts.clone();
        this.m_cascadeID = xmlEasyCC.m_cascadeID;
        (this.m_docTheEasyCC = XMLUtil.createDocument()).appendChild(XMLUtil.importNode(this.m_docTheEasyCC, xmlEasyCC.m_docTheEasyCC.getDocumentElement(), true));
        this.m_priceCertHashesConcatenated = xmlEasyCC.m_priceCertHashesConcatenated;
        this.m_strPIID = xmlEasyCC.m_strPIID;
    }
    
    private void setValues(final Element element) throws XMLParseException {
        if (!element.getTagName().equals("CC")) {
            throw new XMLParseException("XMLEasyCC wrong xml root element name");
        }
        final String attribute = XMLUtil.parseAttribute(element, "version", null);
        if (attribute == null || (!attribute.equals("1.2") && !attribute.equals("1.1"))) {
            throw new XMLParseException("XMLEasyCC wrong version");
        }
        this.m_lAccountNumber = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "AccountNumber"), 0L);
        this.m_lTransferredBytes = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "TransferredBytes"), -1L);
        this.m_strPIID = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "PIID"), null);
        this.m_cascadeID = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Cascade"), null);
        final Element element2 = (Element)XMLUtil.getFirstChildByName(element, "PriceCertificates");
        if (element2 != null) {
            final NodeList elementsByTagName = element2.getElementsByTagName("PriceCertHash");
            for (int i = 0; i < elementsByTagName.getLength(); ++i) {
                final Element element3 = (Element)elementsByTagName.item(i);
                final String value = XMLUtil.parseValue(element3, "abc");
                final String attribute2 = XMLUtil.parseAttribute(element3, "id", "abc");
                if (attribute2.equals("abc")) {
                    throw new XMLParseException("wrong or missing id of price certificate");
                }
                final int attribute3 = XMLUtil.parseAttribute(element3, "position", -1);
                if (attribute3 < 0) {
                    this.m_bOldHashFormat = true;
                }
                this.m_priceCerts.put(new MixPosition(attribute3, attribute2), value);
            }
        }
        this.m_priceCertHashesConcatenated = createConcatenatedPriceCertHashes(this.m_priceCerts, !this.m_bOldHashFormat);
        if (this.m_bOldHashFormat) {
            LogHolder.log(4, LogType.PAY, "Found old hash format for CC: " + this.m_priceCertHashesConcatenated);
        }
    }
    
    private Element internal_toXmlElement(final Document document) {
        final Element element = document.createElement("CC");
        element.setAttribute("version", "1.2");
        final Element element2 = document.createElement("TransferredBytes");
        XMLUtil.setValue(element2, Long.toString(this.m_lTransferredBytes));
        element.appendChild(element2);
        final Element element3 = document.createElement("AccountNumber");
        XMLUtil.setValue(element3, Long.toString(this.m_lAccountNumber));
        element.appendChild(element3);
        final Element element4 = document.createElement("PIID");
        if (this.m_strPIID != null) {
            XMLUtil.setValue(element4, this.m_strPIID);
        }
        element.appendChild(element4);
        final Element element5 = document.createElement("Cascade");
        if (this.m_cascadeID != null) {
            XMLUtil.setValue(element5, this.m_cascadeID);
        }
        element.appendChild(element5);
        final Element element6 = document.createElement("PriceCertificates");
        element.appendChild(element6);
        final Enumeration<MixPosition> keys = (Enumeration<MixPosition>)this.m_priceCerts.keys();
        while (keys.hasMoreElements()) {
            final MixPosition mixPosition = keys.nextElement();
            final String s = this.m_priceCerts.get(mixPosition);
            final Element element7 = document.createElement("PriceCertHash");
            XMLUtil.setValue(element7, s);
            XMLUtil.setAttribute(element7, "id", mixPosition.getId());
            if (!this.m_bOldHashFormat) {
                XMLUtil.setAttribute(element7, "position", mixPosition.getPosition());
            }
            element6.appendChild(element7);
        }
        return element;
    }
    
    public String getPIID() {
        return this.m_strPIID;
    }
    
    public synchronized void setPIID(final String strPIID) {
        this.m_strPIID = strPIID;
        (this.m_docTheEasyCC = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheEasyCC));
    }
    
    public int getId() {
        return this.m_id;
    }
    
    public void setId(final int id) {
        this.m_id = id;
    }
    
    public void setCascadeID(final String cascadeID) {
        this.m_cascadeID = cascadeID;
        (this.m_docTheEasyCC = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheEasyCC));
    }
    
    public long getAccountNumber() {
        return this.m_lAccountNumber;
    }
    
    public long getTransferredBytes() {
        return this.m_lTransferredBytes;
    }
    
    public Enumeration getMixIds() {
        final Enumeration<MixPosition> keys = this.m_priceCerts.keys();
        final Hashtable<String, Object> hashtable = new Hashtable<String, Object>();
        while (keys.hasMoreElements()) {
            final MixPosition mixPosition = keys.nextElement();
            hashtable.put(mixPosition.getId(), this.m_priceCerts.get(mixPosition));
        }
        return hashtable.keys();
    }
    
    public String getCascadeID() {
        return this.m_cascadeID;
    }
    
    public Hashtable getPriceCertHashes() {
        return (Hashtable)this.m_priceCerts.clone();
    }
    
    public String getConcatenatedPriceCertHashes() {
        return this.m_priceCertHashesConcatenated;
    }
    
    public static String createConcatenatedPriceCertHashes(final Hashtable hashtable, final boolean b) {
        final StringBuffer sb = new StringBuffer();
        if (hashtable != null) {
            synchronized (hashtable) {
                final String[] array = new String[hashtable.size()];
                final String[] array2 = new String[hashtable.size()];
                final Enumeration<MixPosition> keys = hashtable.keys();
                for (int i = 0; i < hashtable.size(); ++i) {
                    final MixPosition nextElement = keys.nextElement();
                    if (!b) {
                        array[i] = nextElement.getId();
                    }
                    else {
                        array[i] = Integer.toString(nextElement.getPosition());
                    }
                    array2[i] = hashtable.get(nextElement).toString();
                }
                if (!b) {
                    Util.sort(array2, array);
                }
                else {
                    Util.sort(array, array2);
                }
                for (int j = 0; j < array2.length; ++j) {
                    sb.append(array2[j]);
                }
            }
        }
        return sb.toString();
    }
    
    public int getNrOfPriceCerts() {
        return this.m_priceCerts.size();
    }
    
    public void setPriceCerts(final Hashtable priceCerts) {
        this.m_bOldHashFormat = false;
        this.m_priceCerts = priceCerts;
        this.m_priceCertHashesConcatenated = createConcatenatedPriceCertHashes(this.m_priceCerts, !this.m_bOldHashFormat);
        if (this.m_bOldHashFormat) {
            LogHolder.log(4, LogType.PAY, "Found old hash format for CC: " + this.m_priceCertHashesConcatenated);
        }
        (this.m_docTheEasyCC = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheEasyCC));
    }
    
    public synchronized void addTransferredBytes(final long n) {
        this.m_lTransferredBytes += n;
        (this.m_docTheEasyCC = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheEasyCC));
    }
    
    public synchronized void setTransferredBytes(final long lTransferredBytes) {
        this.m_lTransferredBytes = lTransferredBytes;
        (this.m_docTheEasyCC = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheEasyCC));
    }
    
    public boolean sign(final IMyPrivateKey myPrivateKey) {
        try {
            XMLSignature.sign(this.m_docTheEasyCC, myPrivateKey);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public boolean verify(final IMyPublicKey myPublicKey) {
        try {
            return XMLSignature.verifyFast(this.m_docTheEasyCC, myPublicKey);
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    public Document getDocument() {
        return this.m_docTheEasyCC;
    }
    
    public synchronized Element toXmlElement(final Document document) {
        try {
            return (Element)XMLUtil.importNode(document, this.m_docTheEasyCC.getDocumentElement(), true);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public int hashCode() {
        return 31 * (31 * (31 * (31 * (31 * (31 * 1 + (this.m_bOldHashFormat ? 1231 : 1237)) + ((this.m_cascadeID == null) ? 0 : this.m_cascadeID.hashCode())) + (int)(this.m_lAccountNumber ^ this.m_lAccountNumber >>> 32)) + (int)(this.m_lTransferredBytes ^ this.m_lTransferredBytes >>> 32)) + ((this.m_priceCertHashesConcatenated == null) ? 0 : this.m_priceCertHashesConcatenated.hashCode())) + ((this.m_strPIID == null) ? 0 : this.m_strPIID.hashCode());
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        final XMLEasyCC xmlEasyCC = (XMLEasyCC)o;
        if (this.m_bOldHashFormat != xmlEasyCC.m_bOldHashFormat) {
            return false;
        }
        if (this.m_cascadeID == null) {
            if (xmlEasyCC.m_cascadeID != null) {
                return false;
            }
        }
        else if (!this.m_cascadeID.equals(xmlEasyCC.m_cascadeID)) {
            return false;
        }
        if (this.m_lAccountNumber != xmlEasyCC.m_lAccountNumber) {
            return false;
        }
        if (this.m_lTransferredBytes != xmlEasyCC.m_lTransferredBytes) {
            return false;
        }
        if (this.m_priceCertHashesConcatenated == null) {
            if (xmlEasyCC.m_priceCertHashesConcatenated != null) {
                return false;
            }
        }
        else if (!this.m_priceCertHashesConcatenated.equals(xmlEasyCC.m_priceCertHashesConcatenated)) {
            return false;
        }
        if (this.m_strPIID == null) {
            if (xmlEasyCC.m_strPIID != null) {
                return false;
            }
        }
        else if (!this.m_strPIID.equals(xmlEasyCC.m_strPIID)) {
            return false;
        }
        return true;
    }
}
