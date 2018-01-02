// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.Node;

public class ParentWalker extends AxesWalker
{
    transient boolean m_gotParent;
    
    public ParentWalker(final LocPathIterator locPathIterator) {
        super(locPathIterator);
    }
    
    public Node firstChild() {
        if (!this.m_gotParent) {
            this.m_gotParent = true;
            final Node n = super.m_lpi.getDOMHelper().getParentOfNode(super.m_root);
            return this.setCurrentIfNotNull(n);
        }
        return null;
    }
    
    protected int getLevelMax() {
        return super.m_lpi.getDOMHelper().getLevel(super.m_root) - 1;
    }
    
    public void setRoot(final Node root) {
        this.m_gotParent = false;
        super.m_nextLevelAmount = 0;
        super.setRoot(root);
    }
}
