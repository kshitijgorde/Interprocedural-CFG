// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm.ref;

import org.w3c.dom.Node;
import org.apache.xml.dtm.DTMIterator;

public class DTMNodeList extends DTMNodeListBase
{
    private DTMIterator m_iter;
    
    private DTMNodeList() {
    }
    
    public DTMNodeList(final DTMIterator dtmIterator) {
        if (dtmIterator != null) {
            final int pos = dtmIterator.getCurrentPos();
            try {
                this.m_iter = dtmIterator.cloneWithReset();
            }
            catch (CloneNotSupportedException cnse) {
                this.m_iter = dtmIterator;
            }
            this.m_iter.setShouldCacheNodes(true);
            this.m_iter.runTo(-1);
            this.m_iter.setCurrentPos(pos);
        }
    }
    
    public DTMIterator getDTMIterator() {
        return this.m_iter;
    }
    
    public Node item(final int index) {
        if (this.m_iter == null) {
            return null;
        }
        final int handle = this.m_iter.item(index);
        if (handle == -1) {
            return null;
        }
        return this.m_iter.getDTM(handle).getNode(handle);
    }
    
    public int getLength() {
        return (this.m_iter != null) ? this.m_iter.getLength() : 0;
    }
}
