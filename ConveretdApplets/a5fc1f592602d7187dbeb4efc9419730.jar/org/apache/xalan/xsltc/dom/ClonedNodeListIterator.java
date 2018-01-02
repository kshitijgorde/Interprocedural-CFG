// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.dom;

import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xml.dtm.ref.DTMAxisIteratorBase;

public final class ClonedNodeListIterator extends DTMAxisIteratorBase
{
    private CachedNodeListIterator _source;
    private int _index;
    
    public ClonedNodeListIterator(final CachedNodeListIterator source) {
        this._index = 0;
        this._source = source;
    }
    
    public void setRestartable(final boolean isRestartable) {
    }
    
    public DTMAxisIterator setStartNode(final int node) {
        return this;
    }
    
    public int next() {
        return this._source.getNode(this._index++);
    }
    
    public int getPosition() {
        return (this._index == 0) ? 1 : this._index;
    }
    
    public int getNodeByPosition(final int pos) {
        return this._source.getNode(pos);
    }
    
    public DTMAxisIterator cloneIterator() {
        return this._source.cloneIterator();
    }
    
    public DTMAxisIterator reset() {
        this._index = 0;
        return this;
    }
    
    public void setMark() {
        this._source.setMark();
    }
    
    public void gotoMark() {
        this._source.gotoMark();
    }
}
