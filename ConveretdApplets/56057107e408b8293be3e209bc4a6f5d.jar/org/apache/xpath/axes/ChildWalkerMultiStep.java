// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xpath.patterns.NodeTest;
import org.apache.xpath.patterns.NodeTestFilter;
import org.w3c.dom.Node;

public class ChildWalkerMultiStep extends AxesWalker
{
    public ChildWalkerMultiStep(final LocPathIterator locPathIterator) {
        super(locPathIterator);
    }
    
    protected Node getNextNode() {
        if (super.m_isFresh) {
            super.m_isFresh = false;
        }
        final Node current = this.getCurrentNode();
        if (current.isSupported("NodeTestFilter", "1.0")) {
            ((NodeTestFilter)current).setNodeTest(this);
        }
        Node next = (super.m_root == super.m_currentNode) ? super.m_currentNode.getFirstChild() : super.m_currentNode.getNextSibling();
        if (next != null) {
            super.m_currentNode = next;
            while (this.acceptNode(next) != 1) {
                next = next.getNextSibling();
                if (next == null) {
                    break;
                }
                super.m_currentNode = next;
            }
            if (next == null) {
                super.m_currentNode = current;
            }
        }
        if (next == null) {
            super.m_isDone = true;
        }
        return next;
    }
    
    protected boolean isFastWalker() {
        return true;
    }
    
    public Node nextNode() {
        AxesWalker walker = super.m_lpi.getLastUsedWalker();
        boolean fast = walker.isFastWalker();
        while (walker != null) {
            Node next;
            if (fast) {
                next = walker.getNextNode();
            }
            else {
                next = walker.nextNode();
                if (next != null) {
                    return next;
                }
            }
            if (next != null) {
                if (walker.m_nextWalker == null) {
                    return next;
                }
                walker = walker.m_nextWalker;
                walker.setRoot(next);
                super.m_lpi.setLastUsedWalker(walker);
                fast = walker.isFastWalker();
            }
            else {
                walker = walker.m_prevWalker;
                if (walker != null) {
                    fast = walker.isFastWalker();
                }
                super.m_lpi.setLastUsedWalker(walker);
            }
        }
        return null;
    }
}
