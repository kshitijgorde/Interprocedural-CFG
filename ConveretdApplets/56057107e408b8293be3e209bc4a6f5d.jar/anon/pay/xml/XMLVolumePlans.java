// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import java.util.Enumeration;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Document;
import java.util.Vector;
import anon.util.IXMLEncodable;

public class XMLVolumePlans implements IXMLEncodable
{
    public static final String XML_ELEMENT_NAME = "VolumePlans";
    private Vector m_volumePlans;
    private Document m_docTheVolumePlans;
    
    public XMLVolumePlans() {
        this.m_volumePlans = new Vector();
        this.m_docTheVolumePlans = XMLUtil.createDocument();
    }
    
    public XMLVolumePlans(final Vector volumePlans) {
        this.m_volumePlans = new Vector();
        this.m_volumePlans = volumePlans;
        (this.m_docTheVolumePlans = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheVolumePlans));
    }
    
    public XMLVolumePlans(final String s) throws Exception {
        this.m_volumePlans = new Vector();
        final Document xmlDocument = XMLUtil.toXMLDocument(s);
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docTheVolumePlans = xmlDocument;
    }
    
    public XMLVolumePlans(final Element values) throws Exception {
        this.m_volumePlans = new Vector();
        this.setValues(values);
        (this.m_docTheVolumePlans = XMLUtil.createDocument()).appendChild(XMLUtil.importNode(this.m_docTheVolumePlans, values, true));
    }
    
    public XMLVolumePlans(final Document docTheVolumePlans) throws Exception {
        this.m_volumePlans = new Vector();
        this.setValues(docTheVolumePlans.getDocumentElement());
        this.m_docTheVolumePlans = docTheVolumePlans;
    }
    
    public Element toXmlElement(final Document document) {
        try {
            return (Element)XMLUtil.importNode(document, this.m_docTheVolumePlans.getDocumentElement(), true);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private void setValues(final Element element) throws Exception {
        XMLUtil.assertNodeName(element, "VolumePlans");
        final NodeList elementsByTagName = element.getElementsByTagName("VolumePlan");
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            this.insertByPrice(new XMLVolumePlan((Element)elementsByTagName.item(i)));
        }
    }
    
    private void insertByPrice(final XMLVolumePlan xmlVolumePlan) {
        int n;
        for (n = 0; n < this.m_volumePlans.size() && ((XMLVolumePlan)this.m_volumePlans.elementAt(n)).getPrice() < xmlVolumePlan.getPrice(); ++n) {}
        this.m_volumePlans.insertElementAt(xmlVolumePlan, n);
    }
    
    private Element internal_toXmlElement(final Document document) {
        final Element element = document.createElement("VolumePlans");
        final Enumeration<XMLVolumePlan> elements = this.m_volumePlans.elements();
        while (elements.hasMoreElements()) {
            element.appendChild(elements.nextElement().toXmlElement(document));
        }
        return element;
    }
    
    public Vector getVolumePlans() {
        return this.m_volumePlans;
    }
    
    public XMLVolumePlan getVolumePlan(final String s) {
        final Enumeration<XMLVolumePlan> elements = this.m_volumePlans.elements();
        while (elements.hasMoreElements()) {
            final XMLVolumePlan xmlVolumePlan = elements.nextElement();
            if (xmlVolumePlan.getName().equalsIgnoreCase(s)) {
                return xmlVolumePlan;
            }
        }
        return null;
    }
    
    public XMLVolumePlan getVolumePlan(final int n) {
        return this.m_volumePlans.elementAt(n);
    }
    
    public int getNrOfPlans() {
        return this.m_volumePlans.size();
    }
    
    public void addVolumePlan(final XMLVolumePlan xmlVolumePlan) {
        this.insertByPrice(xmlVolumePlan);
        (this.m_docTheVolumePlans = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheVolumePlans));
    }
}
