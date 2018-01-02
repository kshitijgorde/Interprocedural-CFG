// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import org.w3c.dom.Node;
import org.w3c.dom.Attr;
import org.apache.xpath.DOM2Helper;
import org.apache.xpath.DOMHelper;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.Attributes;

public class AttList implements Attributes
{
    NamedNodeMap m_attrs;
    int m_lastIndex;
    DOMHelper m_dh;
    
    public AttList(final NamedNodeMap attrs) {
        this.m_attrs = attrs;
        this.m_lastIndex = this.m_attrs.getLength() - 1;
        this.m_dh = new DOM2Helper();
    }
    
    public AttList(final NamedNodeMap attrs, final DOMHelper dh) {
        this.m_attrs = attrs;
        this.m_lastIndex = this.m_attrs.getLength() - 1;
        this.m_dh = dh;
    }
    
    public int getIndex(final String rawName) {
        return 0;
    }
    
    public int getIndex(final String uri, final String localPart) {
        return 0;
    }
    
    public int getLength() {
        return this.m_attrs.getLength();
    }
    
    public String getLocalName(final int index) {
        return this.m_dh.getLocalNameOfNode(this.m_attrs.item(index));
    }
    
    public String getQName(final int i) {
        return ((Attr)this.m_attrs.item(i)).getName();
    }
    
    public String getType(final int i) {
        return "CDATA";
    }
    
    public String getType(final String name) {
        return "CDATA";
    }
    
    public String getType(final String uri, final String localName) {
        return "CDATA";
    }
    
    public String getURI(final int index) {
        String ns = this.m_dh.getNamespaceOfNode(this.m_attrs.item(index));
        if (ns == null) {
            ns = "";
        }
        return ns;
    }
    
    public String getValue(final int i) {
        return ((Attr)this.m_attrs.item(i)).getValue();
    }
    
    public String getValue(final String name) {
        final Attr attr = (Attr)this.m_attrs.getNamedItem(name);
        return (attr != null) ? attr.getValue() : null;
    }
    
    public String getValue(final String uri, final String localName) {
        return ((Attr)this.m_attrs.getNamedItem(localName)).getValue();
    }
}
