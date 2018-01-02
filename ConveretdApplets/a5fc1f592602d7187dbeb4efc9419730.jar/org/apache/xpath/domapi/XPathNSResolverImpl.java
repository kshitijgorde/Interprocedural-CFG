// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.domapi;

import org.w3c.dom.Node;
import org.w3c.dom.xpath.XPathNSResolver;
import org.apache.xml.utils.PrefixResolverDefault;

public class XPathNSResolverImpl extends PrefixResolverDefault implements XPathNSResolver
{
    public XPathNSResolverImpl(final Node xpathExpressionContext) {
        super(xpathExpressionContext);
    }
    
    public String lookupNamespaceURI(final String prefix) {
        return super.getNamespaceForPrefix(prefix);
    }
}
