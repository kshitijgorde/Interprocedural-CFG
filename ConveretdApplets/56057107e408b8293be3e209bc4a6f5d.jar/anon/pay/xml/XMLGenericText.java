// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Document;
import anon.util.IXMLEncodable;

public class XMLGenericText implements IXMLEncodable
{
    public static final int TYPE_PLAINTEXT = 1;
    public static final int TYPE_XML = 2;
    private String m_text;
    private Document m_docTheText;
    public static final String XML_ELEMENT_NAME = "GenericText";
    
    public XMLGenericText() {
        this.m_text = "";
        (this.m_docTheText = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheText));
    }
    
    public XMLGenericText(final String text) {
        this.m_text = text;
        (this.m_docTheText = XMLUtil.createDocument()).appendChild(this.internal_toXmlElement(this.m_docTheText));
    }
    
    public String getText() {
        return this.m_text;
    }
    
    public String toString() {
        return this.getText();
    }
    
    public XMLGenericText(final Element values) throws Exception {
        this.setValues(values);
        (this.m_docTheText = XMLUtil.createDocument()).appendChild(XMLUtil.importNode(this.m_docTheText, values, true));
    }
    
    public XMLGenericText(final Document docTheText) throws Exception {
        this.setValues(docTheText.getDocumentElement());
        this.m_docTheText = docTheText;
    }
    
    public Element toXmlElement(final Document document) {
        try {
            return (Element)XMLUtil.importNode(document, this.m_docTheText.getDocumentElement(), true);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private Element internal_toXmlElement(final Document document) {
        final Element element = document.createElement("GenericText");
        XMLUtil.setValue(element, this.m_text);
        return element;
    }
    
    private void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals("GenericText")) {
            throw new Exception("XMLGenericText: cannot parse, wrong xml format!");
        }
        this.m_text = XMLUtil.parseValue(element, "");
        if (this.m_text == null) {
            this.m_text = XMLUtil.toString(element.getFirstChild());
        }
    }
    
    public int hashCode() {
        return 31 * 1 + ((this.m_text == null) ? 0 : this.m_text.hashCode());
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
        final XMLGenericText xmlGenericText = (XMLGenericText)o;
        if (this.m_text == null) {
            if (xmlGenericText.m_text != null) {
                return false;
            }
        }
        else if (!this.m_text.equals(xmlGenericText.m_text)) {
            return false;
        }
        return true;
    }
}
