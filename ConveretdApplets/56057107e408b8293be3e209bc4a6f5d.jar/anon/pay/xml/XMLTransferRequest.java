// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import anon.util.XMLUtil;
import org.w3c.dom.Document;
import anon.util.IXMLEncodable;

public class XMLTransferRequest implements IXMLEncodable
{
    private int m_requested;
    private String m_operatorCert;
    private Document m_docTheRequest;
    private static String ms_strElemName;
    
    public XMLTransferRequest(final int requested) {
        this.m_requested = requested;
        (this.m_docTheRequest = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheRequest));
    }
    
    public XMLTransferRequest(final int requested, final String operatorCert) {
        this.m_requested = requested;
        this.m_operatorCert = operatorCert;
        (this.m_docTheRequest = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheRequest));
    }
    
    public XMLTransferRequest(final String s) throws Exception {
        final Document xmlDocument = XMLUtil.readXMLDocument(new ByteArrayInputStream(s.getBytes()));
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docTheRequest = xmlDocument;
    }
    
    public XMLTransferRequest(final char[] array) throws Exception {
        this(new String(array));
    }
    
    public XMLTransferRequest(final byte[] array) throws Exception {
        final Document xmlDocument = XMLUtil.readXMLDocument(new ByteArrayInputStream(array));
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docTheRequest = xmlDocument;
    }
    
    public XMLTransferRequest(final Element values) throws Exception {
        this.setValues(values);
        (this.m_docTheRequest = XMLUtil.createDocument()).appendChild(XMLUtil.importNode(this.m_docTheRequest, values, true));
    }
    
    public XMLTransferRequest(final Document docTheRequest) throws Exception {
        this.setValues(docTheRequest.getDocumentElement());
        this.m_docTheRequest = docTheRequest;
    }
    
    private void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals("TransferRequest")) {
            throw new Exception("XMLTransferRequest: cannot parse, wrong xml format!");
        }
        if (!element.getAttribute("version").equals("1.0")) {
            throw new Exception("XMLTransferRequest: cannot parse, cert version is " + element.getAttribute("version") + " but 1.0 was expected.");
        }
        this.m_requested = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Requested"), 0);
        if (this.m_requested == 0) {
            throw new Exception("XMLTransferRequest: cannot parse requested");
        }
        this.m_operatorCert = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Operator"), "none");
        if (this.m_operatorCert.equals("none")) {
            throw new Exception("no operator cert set in XMLTransferRequest");
        }
    }
    
    public int getRequested() {
        return this.m_requested;
    }
    
    public String getOperatorCert() {
        return this.m_operatorCert;
    }
    
    public Element toXmlElement(final Document document) {
        try {
            return (Element)XMLUtil.importNode(document, this.m_docTheRequest.getDocumentElement(), true);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private Node internal_toXmlElement(final Document document) {
        final Element element = document.createElement(XMLTransferRequest.ms_strElemName);
        element.setAttribute("version", "1.0");
        final Element element2 = document.createElement("Requested");
        XMLUtil.setValue(element2, this.m_requested);
        element.appendChild(element2);
        final Element element3 = document.createElement("Operator");
        XMLUtil.setValue(element3, this.m_operatorCert);
        element.appendChild(element3);
        return element;
    }
    
    static {
        XMLTransferRequest.ms_strElemName = "TransferRequest";
    }
}
