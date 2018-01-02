// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import org.w3c.dom.DOMException;
import org.apache.crimson.util.XmlNames;

abstract class NamespacedNode extends ParentNode
{
    protected String name;
    protected String namespaceURI;
    
    public String getNamespaceURI() {
        return this.namespaceURI;
    }
    
    public String getPrefix() {
        return XmlNames.getPrefix(this.name);
    }
    
    public void setPrefix(final String prefix) throws DOMException {
        if (super.readonly) {
            throw new DomEx((short)7);
        }
        final int index = this.name.indexOf(58);
        if (prefix == null) {
            if (index >= 0) {
                this.name = this.name.substring(index + 1);
            }
            return;
        }
        if (!XmlNames.isUnqualifiedName(prefix)) {
            throw new DomEx((short)5);
        }
        if (this.namespaceURI == null || ("xml".equals(prefix) && !"http://www.w3.org/XML/1998/namespace".equals(this.namespaceURI))) {
            throw new DomEx((short)14);
        }
        if (this.getNodeType() == 2 && (("xmlns".equals(prefix) && !"http://www.w3.org/2000/xmlns/".equals(this.namespaceURI)) || "xmlns".equals(this.name))) {
            throw new DomEx((short)14);
        }
        final StringBuffer tmp = new StringBuffer(prefix);
        tmp.append(':');
        if (index < 0) {
            tmp.append(this.name);
        }
        else {
            tmp.append(this.name.substring(index + 1));
        }
        this.name = tmp.toString();
    }
    
    public String getLocalName() {
        return XmlNames.getLocalPart(this.name);
    }
}
