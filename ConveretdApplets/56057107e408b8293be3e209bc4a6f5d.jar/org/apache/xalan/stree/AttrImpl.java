// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

import org.w3c.dom.Element;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.ContentHandler;
import org.w3c.dom.Attr;

public class AttrImpl extends Child implements Attr
{
    private String m_name;
    private String m_value;
    private boolean m_specified;
    
    AttrImpl(final DocumentImpl doc, final String name, final String value) {
        super(doc);
        this.m_specified = true;
        this.m_name = name;
        this.m_value = value;
    }
    
    public void dispatchCharactersEvent(final ContentHandler ch) throws SAXException {
        ch.characters(this.m_value.toCharArray(), 0, this.m_value.length());
    }
    
    public String getLocalName() {
        return this.m_name;
    }
    
    public String getName() {
        return this.m_name;
    }
    
    public String getNamespaceURI() {
        return null;
    }
    
    public Node getNextSibling() {
        return null;
    }
    
    public String getNodeName() {
        return this.m_name;
    }
    
    public short getNodeType() {
        return 2;
    }
    
    public String getNodeValue() throws DOMException {
        return this.m_value;
    }
    
    public Element getOwnerElement() {
        return (Element)this.getParentNode();
    }
    
    public String getPrefix() {
        return null;
    }
    
    public Node getPreviousSibling() {
        return null;
    }
    
    public boolean getSpecified() {
        return this.m_specified;
    }
    
    public String getValue() {
        return this.m_value;
    }
    
    void setName(final String name) {
        this.m_name = name;
    }
    
    public void setValue(final String value) throws DOMException {
        this.m_value = value;
    }
}
