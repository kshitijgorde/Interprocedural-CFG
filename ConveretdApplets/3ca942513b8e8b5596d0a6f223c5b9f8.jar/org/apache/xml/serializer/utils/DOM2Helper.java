// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer.utils;

import org.w3c.dom.Node;

public final class DOM2Helper
{
    public String getLocalNameOfNode(final Node n) {
        final String name = n.getLocalName();
        return (null == name) ? this.getLocalNameOfNodeFallback(n) : name;
    }
    
    private String getLocalNameOfNodeFallback(final Node n) {
        final String qname = n.getNodeName();
        final int index = qname.indexOf(58);
        return (index < 0) ? qname : qname.substring(index + 1);
    }
    
    public String getNamespaceOfNode(final Node n) {
        return n.getNamespaceURI();
    }
}
