// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.Node;

public class ChildWalkerOneStep extends AxesWalker
{
    public ChildWalkerOneStep(final LocPathIterator locPathIterator) {
        super(locPathIterator);
    }
    
    protected int getLevelMax() {
        return super.m_lpi.getDOMHelper().getLevel(super.m_root);
    }
    
    public Node nextNode() {
        Node next;
        if (super.m_root == super.m_currentNode) {
            next = super.m_currentNode.getFirstChild();
            super.m_isFresh = false;
        }
        else {
            next = super.m_currentNode.getNextSibling();
        }
        if (next != null) {
            super.m_currentNode = next;
            while (this.acceptNode(next) != 1) {
                next = next.getNextSibling();
                if (next == null) {
                    super.m_isDone = true;
                    break;
                }
                super.m_currentNode = next;
            }
        }
        else {
            super.m_isDone = true;
        }
        return next;
    }
}
