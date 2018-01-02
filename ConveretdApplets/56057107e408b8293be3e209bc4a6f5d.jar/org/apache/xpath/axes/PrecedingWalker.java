// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.Node;

public class PrecedingWalker extends ReverseAxesWalker
{
    transient Node m_doc;
    
    public PrecedingWalker(final LocPathIterator locPathIterator) {
        super(locPathIterator);
    }
    
    public Node firstChild() {
        if (super.m_root.equals(super.m_currentNode)) {
            return null;
        }
        Node nextNode = super.m_currentNode;
        while (nextNode != null) {
            final Node n = nextNode.getFirstChild();
            if (n != null) {
                nextNode = n;
            }
            else {
                nextNode = nextNode.getNextSibling();
                if (nextNode != null) {
                    if (super.m_root.equals(nextNode) || !this.isAncestorOfRootContext(nextNode)) {
                        return null;
                    }
                    continue;
                }
            }
            if (nextNode != null) {
                if (super.m_root.equals(nextNode)) {
                    return null;
                }
                if (!this.isAncestorOfRootContext(nextNode)) {
                    break;
                }
                continue;
            }
        }
        super.m_nextLevelAmount = ((nextNode != null && nextNode.hasChildNodes()) ? 1 : 0);
        return this.setCurrentIfNotNull(nextNode);
    }
    
    protected int getLevelMax() {
        return 32767;
    }
    
    public Node nextSibling() {
        if (super.m_root.equals(super.m_currentNode)) {
            return null;
        }
        final Node next = super.m_currentNode.getNextSibling();
        if (next == null || super.m_root.equals(next) || this.isAncestorOfRootContext(next)) {
            return null;
        }
        super.m_nextLevelAmount = ((next != null && next.hasChildNodes()) ? 1 : 0);
        return this.setCurrentIfNotNull(next);
    }
    
    public Node parentNode() {
        final Node next = super.m_currentNode.getParentNode();
        if (next == null || this.m_doc.equals(next)) {
            return null;
        }
        if (this.isAncestorOfRootContext(next)) {
            return null;
        }
        super.m_nextLevelAmount = 0;
        return this.setCurrentIfNotNull(next);
    }
    
    public void resetProximityPositions() {
    }
    
    public void setRoot(Node root) {
        if (root.getNodeType() == 2) {
            root = super.m_lpi.getDOMHelper().getParentOfNode(root);
        }
        super.setRoot(root);
        this.m_doc = super.m_lpi.getDOMHelper().getRootNode(root);
        super.m_currentNode = this.m_doc;
        super.m_nextLevelAmount = (root.hasChildNodes() ? 1 : 0);
        super.resetProximityPositions();
    }
}
