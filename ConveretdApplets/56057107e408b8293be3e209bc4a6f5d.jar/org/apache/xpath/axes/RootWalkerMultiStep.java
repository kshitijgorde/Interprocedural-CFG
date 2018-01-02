// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xpath.patterns.NodeTest;
import org.apache.xpath.patterns.NodeTestFilter;
import org.w3c.dom.Node;

public class RootWalkerMultiStep extends ChildWalkerMultiStep
{
    transient boolean m_processedRoot;
    
    public RootWalkerMultiStep(final LocPathIterator locPathIterator) {
        super(locPathIterator);
        this.m_processedRoot = false;
    }
    
    protected Node getNextNode() {
        if (super.m_isFresh) {
            super.m_isFresh = false;
        }
        final Node current = this.getCurrentNode();
        if (current.isSupported("NodeTestFilter", "1.0")) {
            ((NodeTestFilter)current).setNodeTest(this);
        }
        Node next;
        if (!this.m_processedRoot) {
            this.m_processedRoot = true;
            next = super.m_lpi.getDOMHelper().getRootNode(super.m_currentNode);
        }
        else {
            next = null;
        }
        if (next != null) {
            super.m_currentNode = next;
            if (this.acceptNode(next) != 1) {
                next = null;
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
    
    public void setRoot(final Node root) {
        super.setRoot(root);
        this.m_processedRoot = false;
    }
}
