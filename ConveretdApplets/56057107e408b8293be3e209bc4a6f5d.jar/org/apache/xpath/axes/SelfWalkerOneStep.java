// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.Node;

public class SelfWalkerOneStep extends AxesWalker
{
    public SelfWalkerOneStep(final LocPathIterator locPathIterator) {
        super(locPathIterator);
    }
    
    public Node nextNode() {
        if (!super.m_isFresh) {
            return null;
        }
        super.m_isFresh = false;
        if (this.acceptNode(super.m_root) == 1) {
            return super.m_root;
        }
        return null;
    }
}
