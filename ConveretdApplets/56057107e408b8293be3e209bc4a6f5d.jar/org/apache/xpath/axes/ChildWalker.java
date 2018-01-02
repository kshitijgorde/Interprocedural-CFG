// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.Node;

public class ChildWalker extends AxesWalker
{
    public ChildWalker(final LocPathIterator locPathIterator) {
        super(locPathIterator);
    }
    
    public Node firstChild() {
        super.m_nextLevelAmount = 0;
        if (super.m_root == super.m_currentNode) {
            return this.setCurrentIfNotNull(super.m_root.getFirstChild());
        }
        return null;
    }
    
    protected int getLevelMax() {
        return super.m_lpi.getDOMHelper().getLevel(super.m_root);
    }
    
    public Node nextSibling() {
        if (super.m_root != super.m_currentNode) {
            return this.setCurrentIfNotNull(super.m_currentNode.getNextSibling());
        }
        return null;
    }
    
    public void setRoot(final Node root) {
        super.m_nextLevelAmount = (root.hasChildNodes() ? 1 : 0);
        super.setRoot(root);
    }
}
