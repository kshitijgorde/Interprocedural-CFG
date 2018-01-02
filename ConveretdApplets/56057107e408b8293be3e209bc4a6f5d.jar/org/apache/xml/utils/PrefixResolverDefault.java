// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class PrefixResolverDefault implements PrefixResolver
{
    Node m_context;
    public static final String S_XMLNAMESPACEURI = "http://www.w3.org/XML/1998/namespace";
    
    public PrefixResolverDefault(final Node xpathExpressionContext) {
        this.m_context = xpathExpressionContext;
    }
    
    public String getBaseIdentifier() {
        return null;
    }
    
    public String getNamespaceForPrefix(final String prefix) {
        return this.getNamespaceForPrefix(prefix, this.m_context);
    }
    
    public String getNamespaceForPrefix(final String prefix, final Node namespaceContext) {
        Node parent = namespaceContext;
        String namespace = null;
        if (prefix.equals("xml")) {
            namespace = "http://www.w3.org/XML/1998/namespace";
        }
        else {
            int type;
            while (parent != null && namespace == null && ((type = parent.getNodeType()) == 1 || type == 5)) {
                if (type == 1) {
                    final NamedNodeMap nnm = parent.getAttributes();
                    for (int i = 0; i < nnm.getLength(); ++i) {
                        final Node attr = nnm.item(i);
                        final String aname = attr.getNodeName();
                        final boolean isPrefix = aname.startsWith("xmlns:");
                        if (isPrefix || aname.equals("xmlns")) {
                            final int index = aname.indexOf(58);
                            final String p = isPrefix ? aname.substring(index + 1) : "";
                            if (p.equals(prefix)) {
                                namespace = attr.getNodeValue();
                                break;
                            }
                        }
                    }
                }
                parent = parent.getParentNode();
            }
        }
        return namespace;
    }
}
