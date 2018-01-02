// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer.utils;

import org.w3c.dom.Node;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.Attributes;

public final class AttList implements Attributes
{
    NamedNodeMap m_attrs;
    int m_lastIndex;
    DOM2Helper m_dh;
    
    public AttList(final NamedNodeMap attrs, final DOM2Helper dh) {
        this.m_attrs = attrs;
        this.m_lastIndex = this.m_attrs.getLength() - 1;
        this.m_dh = dh;
    }
    
    public int getLength() {
        return this.m_attrs.getLength();
    }
    
    public String getURI(final int index) {
        String ns = this.m_dh.getNamespaceOfNode(this.m_attrs.item(index));
        if (null == ns) {
            ns = "";
        }
        return ns;
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
    
    public String getValue(final int i) {
        return ((Attr)this.m_attrs.item(i)).getValue();
    }
    
    public String getType(final String name) {
        return "CDATA";
    }
    
    public String getType(final String uri, final String localName) {
        return "CDATA";
    }
    
    public String getValue(final String name) {
        final Attr attr = (Attr)this.m_attrs.getNamedItem(name);
        return (null != attr) ? attr.getValue() : null;
    }
    
    public String getValue(final String uri, final String localName) {
        final Node a = this.m_attrs.getNamedItemNS(uri, localName);
        return (a == null) ? null : a.getNodeValue();
    }
    
    public int getIndex(final String uri, final String localPart) {
        for (int i = this.m_attrs.getLength() - 1; i >= 0; --i) {
            final Node a = this.m_attrs.item(i);
            final String u = a.getNamespaceURI();
            if (((u == null) ? (uri == null) : u.equals(uri)) && a.getLocalName().equals(localPart)) {
                return i;
            }
        }
        return -1;
    }
    
    public int getIndex(final String qName) {
        for (int i = this.m_attrs.getLength() - 1; i >= 0; --i) {
            final Node a = this.m_attrs.item(i);
            if (a.getNodeName().equals(qName)) {
                return i;
            }
        }
        return -1;
    }
}
