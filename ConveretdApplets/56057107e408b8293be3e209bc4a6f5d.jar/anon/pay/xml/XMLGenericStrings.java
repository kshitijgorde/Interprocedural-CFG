// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import org.w3c.dom.NodeList;
import java.util.Enumeration;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import java.util.Hashtable;
import org.w3c.dom.Document;
import anon.util.IXMLEncodable;

public class XMLGenericStrings implements IXMLEncodable
{
    private Document m_doc;
    public static String ms_strElemName;
    Hashtable m_strings;
    
    public XMLGenericStrings() {
        this.m_strings = new Hashtable();
        (this.m_doc = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_doc));
    }
    
    public XMLGenericStrings(final String s) throws Exception {
        this.setValues(XMLUtil.toXMLDocument(s).getDocumentElement());
    }
    
    public XMLGenericStrings(final String s, final String s2) {
        (this.m_strings = new Hashtable()).put(s, s2);
        (this.m_doc = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_doc));
    }
    
    public XMLGenericStrings(final Hashtable strings) {
        this.m_strings = strings;
        (this.m_doc = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_doc));
    }
    
    public void addEntry(final String s, final String s2) {
        this.m_strings.put(s, s2);
        (this.m_doc = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_doc));
    }
    
    public Hashtable getStrings() {
        return (Hashtable)this.m_strings.clone();
    }
    
    public String getValue(final String s) {
        return this.m_strings.get(s);
    }
    
    public XMLGenericStrings(final Element values) throws Exception {
        this.setValues(values);
        (this.m_doc = XMLUtil.createDocument()).appendChild(XMLUtil.importNode(this.m_doc, values, true));
    }
    
    public XMLGenericStrings(final Document doc) throws Exception {
        this.setValues(doc.getDocumentElement());
        this.m_doc = doc;
    }
    
    public Element toXmlElement(final Document document) {
        try {
            return (Element)XMLUtil.importNode(document, this.m_doc.getDocumentElement(), true);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private Element internal_toXmlElement(final Document document) {
        final Element element = document.createElement(XMLGenericStrings.ms_strElemName);
        final Enumeration<String> keys = this.m_strings.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final String s2 = this.m_strings.get(s);
            final Element element2 = document.createElement("Entry");
            element2.setAttribute("name", s);
            element2.appendChild(document.createTextNode(s2));
            element.appendChild(element2);
        }
        return element;
    }
    
    private void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals(XMLGenericStrings.ms_strElemName)) {
            throw new Exception("XMLGenericStrings: cannot parse, wrong xml format!");
        }
        final NodeList elementsByTagName = element.getElementsByTagName("Entry");
        this.m_strings = new Hashtable();
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            final Node item = elementsByTagName.item(i);
            this.m_strings.put(XMLUtil.parseAttribute(item, "name", ""), XMLUtil.parseValue(item, ""));
        }
    }
    
    static {
        XMLGenericStrings.ms_strElemName = "GenericStrings";
    }
}
