// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

import org.xml.sax.Attributes;

public class ElementImplWithNS extends ElementImpl
{
    private String m_localName;
    private String m_uri;
    
    ElementImplWithNS(final DocumentImpl doc, final String ns, final String name) {
        super(doc, name);
        final int index = name.indexOf(58);
        if (index > 0) {
            this.m_localName = name.substring(index + 1);
        }
        else {
            this.m_localName = name;
        }
        this.m_uri = ns;
    }
    
    ElementImplWithNS(final DocumentImpl doc, final String ns, final String localName, final String name, final Attributes atts) {
        super(doc, name, atts);
        this.m_localName = localName;
        this.m_uri = ns;
    }
    
    public String getLocalName() {
        return this.m_localName;
    }
    
    public String getNamespaceURI() {
        return this.m_uri;
    }
    
    public String getPrefix() {
        final String rawName = this.getNodeName();
        final int indexOfNSSep = rawName.indexOf(58);
        return (indexOfNSSep >= 0) ? rawName.substring(0, indexOfNSSep) : null;
    }
}
