// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

public class AttrImplNS extends AttrImpl
{
    private String m_localName;
    private String m_namespaceURI;
    
    AttrImplNS(final DocumentImpl doc, final String uri, final String name, final String value) {
        super(doc, name, value);
        this.m_namespaceURI = uri;
        final int index = name.indexOf(58);
        if (index > 0) {
            this.m_localName = name.substring(index + 1);
        }
        else {
            this.m_localName = name;
        }
    }
    
    public String getLocalName() {
        return this.m_localName;
    }
    
    public String getNamespaceURI() {
        return this.m_namespaceURI;
    }
    
    public String getPrefix() {
        final String m_name = this.getNodeName();
        final int indexOfNSSep = m_name.indexOf(58);
        return (indexOfNSSep >= 0) ? m_name.substring(0, indexOfNSSep) : null;
    }
}
