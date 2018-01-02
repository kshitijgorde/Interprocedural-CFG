// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.dom4j.tree;

import org.jboss.dom4j.Namespace;
import org.jboss.dom4j.QName;
import org.jboss.dom4j.Element;

public class DefaultAttribute extends FlyweightAttribute
{
    private Element parent;
    
    public DefaultAttribute(final QName qname) {
        super(qname);
    }
    
    public DefaultAttribute(final QName qname, final String value) {
        super(qname, value);
    }
    
    public DefaultAttribute(final Element parent, final QName qname, final String value) {
        super(qname, value);
        this.parent = parent;
    }
    
    public DefaultAttribute(final String name, final String value) {
        super(name, value);
    }
    
    public DefaultAttribute(final String name, final String value, final Namespace namespace) {
        super(name, value, namespace);
    }
    
    public DefaultAttribute(final Element parent, final String name, final String value, final Namespace namespace) {
        super(name, value, namespace);
        this.parent = parent;
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
    
    public Element getParent() {
        return this.parent;
    }
    
    public void setParent(final Element parent) {
        this.parent = parent;
    }
    
    public boolean supportsParent() {
        return true;
    }
    
    public boolean isReadOnly() {
        return false;
    }
}
