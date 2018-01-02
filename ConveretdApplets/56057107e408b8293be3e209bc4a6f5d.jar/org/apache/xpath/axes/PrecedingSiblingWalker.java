// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.Node;

public class PrecedingSiblingWalker extends ReverseAxesWalker
{
    public PrecedingSiblingWalker(final LocPathIterator locPathIterator) {
        super(locPathIterator);
        super.m_nextLevelAmount = 0;
    }
    
    protected int getLevelMax() {
        return super.m_lpi.getDOMHelper().getLevel(super.m_root);
    }
    
    public Node nextSibling() {
        Node next;
        if (super.m_currentNode == super.m_root) {
            if (super.m_currentNode.getNodeType() == 2) {
                next = null;
            }
            else {
                final Node parent = super.m_lpi.getDOMHelper().getParentOfNode(super.m_currentNode);
                if (parent == null) {
                    return null;
                }
                next = parent.getFirstChild();
            }
        }
        else {
            next = super.m_currentNode.getNextSibling();
        }
        if (next != null && next.equals(super.m_root)) {
            next = null;
        }
        return this.setCurrentIfNotNull(next);
    }
}
