// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.filter;

import org.jdom.Element;
import org.jdom.Namespace;

public class ElementFilter implements Filter
{
    private static final String CVS_ID = "@(#) $RCSfile: ElementFilter.java,v $ $Revision: 1.2 $ $Date: 2002/03/13 06:25:33 $ $Name: jdom_1_0_b8 $";
    protected String name;
    protected Namespace namespace;
    
    public ElementFilter() {
    }
    
    public ElementFilter(final String name) {
        this.name = name;
    }
    
    public ElementFilter(final Namespace namespace) {
        this.namespace = namespace;
    }
    
    public ElementFilter(final String name, final Namespace namespace) {
        this.name = name;
        this.namespace = namespace;
    }
    
    public boolean canAdd(final Object obj) {
        return this.matches(obj);
    }
    
    public boolean canRemove(final Object obj) {
        return obj instanceof Element;
    }
    
    public boolean matches(final Object obj) {
        if (obj instanceof Element) {
            final Element element = (Element)obj;
            if (this.name == null) {
                return this.namespace == null || this.namespace.equals(element.getNamespace());
            }
            if (this.name.equals(element.getName())) {
                return this.namespace == null || this.namespace.equals(element.getNamespace());
            }
        }
        return false;
    }
    
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ElementFilter) {
            final ElementFilter filter = (ElementFilter)obj;
            if (this.name == filter.name && this.namespace == filter.namespace) {
                return true;
            }
        }
        return false;
    }
}
