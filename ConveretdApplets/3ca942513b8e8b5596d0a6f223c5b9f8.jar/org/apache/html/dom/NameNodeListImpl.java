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
    public NameNodeListImpl(final NodeImpl nodeImpl, final String s) {
        super(nodeImpl, s);
    }
    
    protected Node nextMatchingElementAfter(Node node) {
        while (node != null) {
            if (node.hasChildNodes()) {
                node = node.getFirstChild();
            }
            else {
                final Node nextSibling;
                if (node != super.rootNode && null != (nextSibling = node.getNextSibling())) {
                    node = nextSibling;
                }
                else {
                    Node nextSibling2 = null;
                    while (node != super.rootNode) {
                        nextSibling2 = node.getNextSibling();
                        if (nextSibling2 != null) {
                            break;
                        }
                        node = node.getParentNode();
                    }
                    node = nextSibling2;
                }
            }
            if (node != super.rootNode && node != null && node.getNodeType() == 1) {
                final String attribute = ((ElementImpl)node).getAttribute("name");
                if (attribute.equals("*") || attribute.equals(super.tagName)) {
                    return node;
                }
                continue;
            }
        }
        return null;
    }
}
