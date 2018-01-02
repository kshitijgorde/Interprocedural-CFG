// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.Node;
import java.util.Stack;

public class FollowingWalker extends AxesWalker
{
    protected transient Stack m_ancestors;
    transient Node m_currentAncestor;
    
    public FollowingWalker(final LocPathIterator locPathIterator) {
        super(locPathIterator);
        this.m_ancestors = new Stack();
    }
    
    public Node firstChild() {
        Node n;
        if (this.m_currentAncestor == super.m_currentNode) {
            n = super.m_currentNode.getNextSibling();
        }
        else {
            n = super.m_currentNode.getFirstChild();
        }
        super.m_nextLevelAmount = ((n != null && n.hasChildNodes()) ? 1 : 0);
        return this.setCurrentIfNotNull(n);
    }
    
    protected int getLevelMax() {
        return 32767;
    }
    
    public Node nextSibling() {
        final Node n = super.m_currentNode.getNextSibling();
        super.m_nextLevelAmount = ((n != null && n.hasChildNodes()) ? 1 : 0);
        return this.setCurrentIfNotNull(n);
    }
    
    public Node parentNode() {
        final Node nextAncestor = (this.m_currentAncestor != null) ? this.m_currentAncestor.getParentNode() : null;
        final Node nextParent = super.m_currentNode.getParentNode();
        Node n;
        if (nextParent == nextAncestor) {
            n = null;
            Node ancestor = this.m_currentAncestor;
            while (ancestor != null && (ancestor = ancestor.getParentNode()) != null) {
                n = ancestor.getNextSibling();
                if (n != null) {
                    break;
                }
                if (ancestor == null) {
                    break;
                }
            }
            this.m_currentAncestor = ancestor;
        }
        else {
            n = nextParent;
        }
        return this.setCurrentIfNotNull(n);
    }
    
    public void setRoot(final Node root) {
        super.setRoot(root);
        if (root.getNodeType() == 2) {
            final Node e = super.m_lpi.getDOMHelper().getParentOfNode(root);
            super.m_currentNode = e;
            this.m_currentAncestor = e.getOwnerDocument();
        }
        else {
            this.m_currentAncestor = root;
        }
        super.m_nextLevelAmount = 0;
    }
}
