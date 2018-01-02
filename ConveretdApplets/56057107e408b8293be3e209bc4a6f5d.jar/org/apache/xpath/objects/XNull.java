// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

import org.w3c.dom.DocumentFragment;
import org.apache.xpath.XPathContext;
import org.w3c.dom.traversal.NodeIterator;

public class XNull extends XObject
{
    public boolean bool() {
        return false;
    }
    
    public boolean equals(final XObject obj2) {
        return obj2.getType() == -1;
    }
    
    public int getType() {
        return -1;
    }
    
    public String getTypeString() {
        return "#CLASS_NULL";
    }
    
    public NodeIterator nodeset() {
        return null;
    }
    
    public double num() {
        return 0.0;
    }
    
    public DocumentFragment rtree(final XPathContext support) {
        final DocumentFragment result = support.getDOMHelper().getDOMFactory().createDocumentFragment();
        return result;
    }
    
    public String str() {
        return "";
    }
}
