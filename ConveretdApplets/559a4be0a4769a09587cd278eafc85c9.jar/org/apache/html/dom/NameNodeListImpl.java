// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.apache.xerces.dom.ElementImpl;
import org.w3c.dom.Node;
import org.apache.xerces.dom.NodeImpl;
import org.w3c.dom.NodeList;
import org.apache.xerces.dom.DeepNodeListImpl;

public class NameNodeListImpl extends DeepNodeListImpl implements NodeList
{
    public NameNodeListImpl(final NodeImpl rootNode, final String tagName) {
        super(rootNode, tagName);
    }
    
    protected Node nextMatchingElementAfter(Node current) {
        while (current != null) {
            if (current.hasChildNodes()) {
                current = current.getFirstChild();
            }
            else {
                Node next;
                if (current != super.rootNode && null != (next = current.getNextSibling())) {
                    current = next;
                }
                else {
                    next = null;
                    while (current != super.rootNode) {
                        next = current.getNextSibling();
                        if (next != null) {
                            break;
                        }
                        current = current.getParentNode();
                    }
                    current = next;
                }
            }
            if (current != super.rootNode && current != null && current.getNodeType() == 1) {
                final String name = ((ElementImpl)current).getAttribute("name");
                if (name.equals("*") || name.equals(super.tagName)) {
                    return current;
                }
                continue;
            }
        }
        return null;
    }
}
