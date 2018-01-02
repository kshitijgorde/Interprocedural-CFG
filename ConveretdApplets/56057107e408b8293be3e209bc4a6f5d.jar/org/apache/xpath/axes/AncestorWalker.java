// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xpath.DOMHelper;
import org.w3c.dom.Node;
import java.util.Stack;

public class AncestorWalker extends ReverseAxesWalker
{
    protected transient Stack m_ancestors;
    protected transient int m_ancestorsPos;
    
    public AncestorWalker(final LocPathIterator locPathIterator) {
        super(locPathIterator);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final AncestorWalker clone = (AncestorWalker)super.clone();
        return clone;
    }
    
    public Node firstChild() {
        final Node next = (this.m_ancestorsPos < 0) ? null : ((Node)this.m_ancestors.elementAt(this.m_ancestorsPos--));
        super.m_nextLevelAmount = ((this.m_ancestorsPos >= 0) ? 1 : 0);
        return this.setCurrentIfNotNull(next);
    }
    
    protected int getLevelMax() {
        final DOMHelper dh = super.m_lpi.getDOMHelper();
        final Node p = dh.getParentOfNode(super.m_root);
        return (p == null) ? 1 : dh.getLevel(p);
    }
    
    protected void pushAncestors(Node n) {
        this.m_ancestors = new Stack();
        final DOMHelper dh = super.m_lpi.getDOMHelper();
        while ((n = dh.getParentOfNode(n)) != null) {
            this.m_ancestors.push(n);
        }
        super.m_nextLevelAmount = (this.m_ancestors.isEmpty() ? 0 : 1);
        this.m_ancestorsPos = this.m_ancestors.size() - 1;
    }
    
    public void setRoot(final Node root) {
        this.pushAncestors(root);
        super.setRoot(root);
    }
}
