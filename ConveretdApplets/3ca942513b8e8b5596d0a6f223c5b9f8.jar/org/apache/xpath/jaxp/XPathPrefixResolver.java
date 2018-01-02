// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.jaxp;

import org.w3c.dom.Node;
import javax.xml.namespace.NamespaceContext;
import org.apache.xml.utils.PrefixResolver;

public class XPathPrefixResolver implements PrefixResolver
{
    private NamespaceContext m_nsContext;
    
    public XPathPrefixResolver(final NamespaceContext nsContext) {
        this.m_nsContext = nsContext;
    }
    
    public String getNamespaceForPrefix(final String prefix) {
        if (this.m_nsContext != null) {
            return this.m_nsContext.getNamespaceURI(prefix);
        }
        return null;
    }
    
    public String getNamespaceForPrefix(final String prefix, final Node context) {
        if (this.m_nsContext != null) {
            return this.m_nsContext.getNamespaceURI(prefix);
        }
        return null;
    }
    
    public boolean handlesNullPrefixes() {
        return false;
    }
    
    public String getBaseIdentifier() {
        return null;
    }
}
