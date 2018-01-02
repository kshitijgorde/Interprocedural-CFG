// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.Node;

public class RootWalker extends AxesWalker
{
    transient boolean m_processedRoot;
    
    public RootWalker(final LocPathIterator locPathIterator) {
        super(locPathIterator);
        this.m_processedRoot = false;
        super.m_nextLevelAmount = 0;
    }
    
    public Node firstChild() {
        if (!this.m_processedRoot) {
            this.m_processedRoot = true;
            final Node n = super.m_lpi.getDOMHelper().getRootNode(super.m_currentNode);
            return this.setCurrentIfNotNull(n);
        }
        return null;
    }
    
    protected int getLevelMax() {
        return 1;
    }
    
    public void setRoot(final Node root) {
        super.setRoot(root);
        this.m_processedRoot = false;
    }
}
