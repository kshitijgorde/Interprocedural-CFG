// 
// Decompiled by Procyon v0.5.30
// 

package microtex.TeletextOnWeb;

import java.awt.Dimension;
import java.awt.Panel;

class ScrollableCanvas extends Panel
{
    int m_nTx;
    int m_nTy;
    int m_nOldTx;
    int m_nOldTy;
    Dimension m_dimPreferredSize;
    
    public Dimension minimumSize() {
        return this.preferredSize();
    }
    
    public Dimension preferredSize() {
        return this.m_dimPreferredSize;
    }
    
    public void scroll() {
        this.reshape(this.location().x - (this.m_nTx - this.m_nOldTx), this.location().y - (this.m_nTy - this.m_nOldTy), this.size().width + (this.m_nTx - this.m_nOldTx), this.size().height + (this.m_nTy - this.m_nOldTy));
        this.m_nOldTx = this.m_nTx;
        this.m_nOldTy = this.m_nTy;
    }
    
    ScrollableCanvas(final Dimension prefSize) {
        this.m_nTx = 0;
        this.m_nTy = 0;
        this.m_nOldTx = 0;
        this.m_nOldTy = 0;
        this.m_dimPreferredSize = prefSize;
    }
}
