// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.Node;

public class DescendantWalker extends AxesWalker
{
    public DescendantWalker(final LocPathIterator locPathIterator) {
        super(locPathIterator);
    }
    
    public Node firstChild() {
        final Node next = (super.m_currentNode.getNodeType() != 2) ? super.m_currentNode.getFirstChild() : null;
        super.m_nextLevelAmount = ((next != null && next.hasChildNodes()) ? 1 : 0);
        return this.setCurrentIfNotNull(next);
    }
    
    protected int getLevelMax() {
        return 32767;
    }
    
    public Node nextSibling() {
        final Node next = super.m_root.equals(super.m_currentNode) ? null : super.m_currentNode.getNextSibling();
        super.m_nextLevelAmount = ((next != null && next.hasChildNodes()) ? 1 : 0);
        return this.setCurrentIfNotNull(next);
    }
    
    public Node parentNode() {
        Node n;
        if (super.m_root.equals(super.m_currentNode)) {
            n = null;
        }
        else {
            final Node p = super.m_currentNode.getParentNode();
            n = (super.m_root.equals(p) ? null : p);
        }
        super.m_nextLevelAmount = 0;
        return this.setCurrentIfNotNull(n);
    }
    
    public void setRoot(final Node root) {
        super.m_nextLevelAmount = (root.hasChildNodes() ? 1 : 0);
        super.setRoot(root);
    }
}
