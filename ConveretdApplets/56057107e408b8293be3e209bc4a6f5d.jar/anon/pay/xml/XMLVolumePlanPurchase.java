// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import org.w3c.dom.Element;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Document;
import anon.util.IXMLEncodable;

public class XMLVolumePlanPurchase implements IXMLEncodable
{
    private Document m_docTheVolumePlanPurchase;
    private long m_accountNumber;
    private String m_planName;
    
    public XMLVolumePlanPurchase(final long accountNumber, final String planName) {
        this.m_accountNumber = accountNumber;
        this.m_planName = planName;
        (this.m_docTheVolumePlanPurchase = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheVolumePlanPurchase));
    }
    
    public XMLVolumePlanPurchase(final String s) throws Exception {
        final Document xmlDocument = XMLUtil.readXMLDocument(new ByteArrayInputStream(s.getBytes()));
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docTheVolumePlanPurchase = xmlDocument;
    }
    
    public String getPlanName() {
        return this.m_planName;
    }
    
    public long getAccountNumber() {
        return this.m_accountNumber;
    }
    
    public Element internal_toXmlElement(final Document document) {
        final Element element = document.createElement("VolumePlanPurchase");
        final Element element2 = document.createElement("AccountNumber");
        XMLUtil.setValue(element2, this.m_accountNumber);
        element.appendChild(element2);
        final Element element3 = document.createElement("VolumePlanName");
        XMLUtil.setValue(element3, this.m_planName);
        element.appendChild(element3);
        return element;
    }
    
    public Element toXmlElement(final Document document) {
        try {
            return (Element)XMLUtil.importNode(document, this.m_docTheVolumePlanPurchase.getDocumentElement(), true);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    protected void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals("VolumePlanPurchase")) {
            throw new Exception("XMLVolumePlan: wrong XML structure");
        }
        final long n = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "AccountNumber"), 0);
        XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "VolumePlanName"), null);
    }
}
