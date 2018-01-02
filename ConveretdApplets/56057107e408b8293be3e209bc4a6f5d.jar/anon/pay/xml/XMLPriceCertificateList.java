// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import org.w3c.dom.NodeList;
import anon.crypto.XMLSignature;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.Enumeration;
import org.w3c.dom.Element;
import org.w3c.dom.DOMException;
import logging.LogHolder;
import logging.LogType;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Document;
import java.util.Vector;
import anon.util.IXMLEncodable;

public class XMLPriceCertificateList implements IXMLEncodable
{
    private Vector m_thePriceCerts;
    private Document m_docThePriceCerts;
    private static final String ms_strElemName = "PriceCertificateList";
    
    public XMLPriceCertificateList(final Vector thePriceCerts) {
        this.m_thePriceCerts = thePriceCerts;
        this.m_docThePriceCerts = XMLUtil.createDocument();
        this.m_docThePriceCerts = this.internal_toXmlElement(this.m_docThePriceCerts);
    }
    
    private Document internal_toXmlElement(final Document document) {
        final Element element = document.createElement("PriceCertificateList");
        element.setAttribute("version", "1.0");
        document.appendChild(element);
        final Enumeration<XMLPriceCertificate> elements = this.m_thePriceCerts.elements();
        while (elements.hasMoreElements()) {
            final XMLPriceCertificate xmlPriceCertificate = elements.nextElement();
            try {
                element.appendChild(xmlPriceCertificate.toXmlElement(document));
            }
            catch (DOMException ex) {
                LogHolder.log(7, LogType.PAY, ex.getMessage());
            }
        }
        return document;
    }
    
    public XMLPriceCertificateList(final String s) throws Exception {
        final Document xmlDocument = XMLUtil.readXMLDocument(new ByteArrayInputStream(s.getBytes()));
        this.m_thePriceCerts = new Vector();
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docThePriceCerts = xmlDocument;
    }
    
    public XMLPriceCertificateList(final byte[] array) throws Exception {
        final Document xmlDocument = XMLUtil.readXMLDocument(new ByteArrayInputStream(array));
        this.m_thePriceCerts = new Vector();
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docThePriceCerts = xmlDocument;
    }
    
    public XMLPriceCertificateList(final Element values) throws Exception {
        this.m_thePriceCerts = new Vector();
        this.setValues(values);
        (this.m_docThePriceCerts = XMLUtil.createDocument()).appendChild(XMLUtil.importNode(this.m_docThePriceCerts, values, true));
    }
    
    public XMLPriceCertificateList(final Document docThePriceCerts) throws Exception {
        final Element documentElement = docThePriceCerts.getDocumentElement();
        this.m_thePriceCerts = new Vector();
        this.setValues(documentElement);
        this.m_docThePriceCerts = docThePriceCerts;
    }
    
    public Vector getPriceCerts() {
        return this.m_thePriceCerts;
    }
    
    public static String getXMLElementName() {
        return "PriceCertificateList";
    }
    
    public Vector getPriceCertHashes() {
        final Vector<String> vector = new Vector<String>();
        final Enumeration<XMLPriceCertificate> elements = this.m_thePriceCerts.elements();
        while (elements.hasMoreElements()) {
            vector.addElement(XMLSignature.getHashValueOfElement(elements.nextElement().getDocument()));
        }
        return vector;
    }
    
    private void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals("PriceCertificateList")) {
            throw new Exception("XMLPriceCertificateList: cannot parse, wrong xml format!");
        }
        if (!element.getAttribute("version").equals("1.0")) {
            throw new Exception("XMLPriceCertificate: cannot parse, cert version is " + element.getAttribute("version") + " but 1.0 was expected.");
        }
        final NodeList elementsByTagName = element.getElementsByTagName("PriceCertificate");
        if (elementsByTagName == null) {
            throw new Exception("XMLPriceCertificate: cannot parse price certificates");
        }
        for (int n = 0; elementsByTagName.item(n) != null; ++n) {
            this.m_thePriceCerts.addElement(new XMLPriceCertificate((Element)elementsByTagName.item(n)));
        }
    }
    
    public Element toXmlElement(final Document document) {
        try {
            return (Element)XMLUtil.importNode(document, this.m_docThePriceCerts.getDocumentElement(), true);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
