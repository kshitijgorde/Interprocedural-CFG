// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.Node;

public class FollowingSiblingWalker extends AxesWalker
{
    public FollowingSiblingWalker(final LocPathIterator locPathIterator) {
        super(locPathIterator);
        super.m_nextLevelAmount = 0;
    }
    
    protected int getLevelMax() {
        return super.m_lpi.getDOMHelper().getLevel(super.m_root);
    }
    
    public Node nextSibling() {
        final Node n = super.m_currentNode.getNextSibling();
        return this.setCurrentIfNotNull(n);
    }
}
