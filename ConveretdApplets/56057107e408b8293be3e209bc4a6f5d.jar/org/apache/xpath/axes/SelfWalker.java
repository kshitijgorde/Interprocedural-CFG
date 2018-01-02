// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.Node;
import java.io.Serializable;

public class SelfWalker extends AxesWalker implements Serializable
{
    transient boolean m_processedSelf;
    
    public SelfWalker(final LocPathIterator locPathIterator) {
        super(locPathIterator);
        super.m_nextLevelAmount = 0;
    }
    
    public Node firstChild() {
        if (!this.m_processedSelf) {
            this.m_processedSelf = true;
            return this.setCurrentIfNotNull(super.m_root);
        }
        return null;
    }
    
    protected int getLevelMax() {
        return super.m_lpi.getDOMHelper().getLevel(super.m_root);
    }
    
    public void setRoot(final Node root) {
        this.m_processedSelf = false;
        super.setRoot(root);
    }
}
