// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.Node;

public class DescendantOrSelfWalker extends DescendantWalker
{
    transient boolean m_processedSelf;
    
    public DescendantOrSelfWalker(final LocPathIterator locPathIterator) {
        super(locPathIterator);
    }
    
    public Node firstChild() {
        if (!this.m_processedSelf) {
            this.m_processedSelf = true;
            return this.setCurrentIfNotNull(super.m_root);
        }
        return super.firstChild();
    }
    
    public void setRoot(final Node root) {
        this.m_processedSelf = false;
        super.setRoot(root);
    }
}
