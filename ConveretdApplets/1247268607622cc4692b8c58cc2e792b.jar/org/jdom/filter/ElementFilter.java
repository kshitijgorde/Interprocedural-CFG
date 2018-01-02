// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.filter;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.jdom.Element;
import org.jdom.Namespace;

public class ElementFilter extends AbstractFilter
{
    private static final String CVS_ID = "@(#) $RCSfile: ElementFilter.java,v $ $Revision: 1.20 $ $Date: 2007/11/10 05:29:00 $ $Name: jdom_1_1_1 $";
    private String name;
    private transient Namespace namespace;
    
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
    
    public boolean matches(final Object obj) {
        if (obj instanceof Element) {
            final Element el = (Element)obj;
            return (this.name == null || this.name.equals(el.getName())) && (this.namespace == null || this.namespace.equals(el.getNamespace()));
        }
        return false;
    }
    
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ElementFilter)) {
            return false;
        }
        final ElementFilter filter = (ElementFilter)obj;
        Label_0054: {
            if (this.name != null) {
                if (this.name.equals(filter.name)) {
                    break Label_0054;
                }
            }
            else if (filter.name == null) {
                break Label_0054;
            }
            return false;
        }
        if (this.namespace != null) {
            if (this.namespace.equals(filter.namespace)) {
                return true;
            }
        }
        else if (filter.namespace == null) {
            return true;
        }
        return false;
    }
    
    public int hashCode() {
        int result = (this.name != null) ? this.name.hashCode() : 0;
        result = 29 * result + ((this.namespace != null) ? this.namespace.hashCode() : 0);
        return result;
    }
    
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        if (this.namespace != null) {
            out.writeObject(this.namespace.getPrefix());
            out.writeObject(this.namespace.getURI());
        }
        else {
            out.writeObject(null);
            out.writeObject(null);
        }
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        final Object prefix = in.readObject();
        final Object uri = in.readObject();
        if (prefix != null) {
            this.namespace = Namespace.getNamespace((String)prefix, (String)uri);
        }
    }
}
