// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.Node;
import java.util.Vector;
import org.w3c.dom.NodeList;

public class DeepNodeListImpl implements NodeList
{
    protected NodeImpl rootNode;
    protected String tagName;
    protected int changes;
    protected Vector nodes;
    protected String nsName;
    protected boolean enableNS;
    
    public DeepNodeListImpl(final NodeImpl rootNode, final String tagName) {
        this.enableNS = false;
        this.rootNode = rootNode;
        this.tagName = tagName;
        this.nodes = new Vector();
    }
    
    public DeepNodeListImpl(final NodeImpl nodeImpl, final String nsName, final String s) {
        this(nodeImpl, s);
        this.nsName = nsName;
        this.enableNS = (nsName != null && !nsName.equals("") && !nsName.equals("*"));
    }
    
    public int getLength() {
        this.item(Integer.MAX_VALUE);
        return this.nodes.size();
    }
    
    public Node item(final int n) {
        if (this.rootNode.changes != this.changes) {
            this.nodes = new Vector();
            this.changes = this.rootNode.changes;
        }
        if (n < this.nodes.size()) {
            return this.nodes.elementAt(n);
        }
        Node node;
        if (this.nodes.size() == 0) {
            node = this.rootNode;
        }
        else {
            node = this.nodes.lastElement();
        }
        while (node != null && n >= this.nodes.size()) {
            node = this.nextMatchingElementAfter(node);
            if (node != null) {
                this.nodes.addElement(node);
            }
        }
        return node;
    }
    
    private Node nextMatchingElementAfter(Node node) {
        while (node != null) {
            if (node.hasChildNodes()) {
                node = node.getFirstChild();
            }
            else {
                final Node nextSibling;
                if (node != this.rootNode && (nextSibling = node.getNextSibling()) != null) {
                    node = nextSibling;
                }
                else {
                    Node nextSibling2 = null;
                    while (node != this.rootNode) {
                        nextSibling2 = node.getNextSibling();
                        if (nextSibling2 != null) {
                            break;
                        }
                        node = node.getParentNode();
                    }
                    node = nextSibling2;
                }
            }
            if (node != this.rootNode && node != null && node.getNodeType() == 1 && (this.tagName.equals("*") || ((ElementImpl)node).getTagName().equals(this.tagName)) && (!this.enableNS || (this.enableNS && ((ElementImpl)node).getNamespaceURI() != null && ((ElementImpl)node).getNamespaceURI().equals(this.nsName)))) {
                return node;
            }
        }
        return null;
    }
}
