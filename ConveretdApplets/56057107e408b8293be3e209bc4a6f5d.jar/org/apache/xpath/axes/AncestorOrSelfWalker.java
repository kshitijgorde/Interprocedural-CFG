// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xpath.DOMHelper;
import java.util.Stack;
import org.w3c.dom.Node;

public class AncestorOrSelfWalker extends AncestorWalker
{
    public AncestorOrSelfWalker(final LocPathIterator locPathIterator) {
        super(locPathIterator);
    }
    
    protected int getLevelMax() {
        return super.m_lpi.getDOMHelper().getLevel(super.m_root);
    }
    
    protected void pushAncestors(Node n) {
        (super.m_ancestors = new Stack()).push(n);
        final DOMHelper dh = super.m_lpi.getDOMHelper();
        while ((n = dh.getParentOfNode(n)) != null) {
            super.m_ancestors.push(n);
        }
        super.m_nextLevelAmount = (super.m_ancestors.isEmpty() ? 0 : 1);
        super.m_ancestorsPos = super.m_ancestors.size() - 1;
    }
}
