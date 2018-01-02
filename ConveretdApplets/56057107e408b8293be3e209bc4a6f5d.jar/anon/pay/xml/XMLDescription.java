// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import anon.util.XMLUtil;
import anon.util.IXMLEncodable;

public class XMLDescription implements IXMLEncodable
{
    private String m_strDescription;
    
    public XMLDescription(final byte[] array) throws Exception {
        this.setValues(XMLUtil.toXMLDocument(array));
    }
    
    public XMLDescription(final char[] array) throws Exception {
        this.setValues(XMLUtil.toXMLDocument(array));
    }
    
    public XMLDescription(final String strDescription) throws Exception {
        this.m_strDescription = strDescription;
    }
    
    private void setValues(final Document document) throws Exception {
        final Element documentElement = document.getDocumentElement();
        if (!documentElement.getTagName().equals("Description")) {
            throw new Exception("XMLDescription wrong xml structure");
        }
        this.m_strDescription = ((CharacterData)documentElement.getFirstChild()).getData();
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("Description");
        XMLUtil.setValue(element, this.m_strDescription);
        return element;
    }
    
    public String getDescription() {
        return this.m_strDescription;
    }
}
