// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.dom;

import org.apache.xalan.xsltc.util.IntegerArray;
import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xml.dtm.ref.DTMAxisIteratorBase;

public final class CachedNodeListIterator extends DTMAxisIteratorBase
{
    private DTMAxisIterator _source;
    private IntegerArray _nodes;
    private int _numCachedNodes;
    private int _index;
    private boolean _isEnded;
    
    public CachedNodeListIterator(final DTMAxisIterator source) {
        this._nodes = new IntegerArray();
        this._numCachedNodes = 0;
        this._index = 0;
        this._isEnded = false;
        this._source = source;
    }
    
    public void setRestartable(final boolean isRestartable) {
    }
    
    public DTMAxisIterator setStartNode(final int node) {
        if (super._isRestartable) {
            super._startNode = node;
            this._source.setStartNode(node);
            this.resetPosition();
            super._isRestartable = false;
        }
        return this;
    }
    
    public int next() {
        return this.getNode(this._index++);
    }
    
    public int getPosition() {
        return (this._index == 0) ? 1 : this._index;
    }
    
    public int getNodeByPosition(final int pos) {
        return this.getNode(pos);
    }
    
    public int getNode(final int index) {
        if (index < this._numCachedNodes) {
            return this._nodes.at(index);
        }
        if (!this._isEnded) {
            final int node = this._source.next();
            if (node != -1) {
                this._nodes.add(node);
                ++this._numCachedNodes;
            }
            else {
                this._isEnded = true;
            }
            return node;
        }
        return -1;
    }
    
    public DTMAxisIterator cloneIterator() {
        final ClonedNodeListIterator clone = new ClonedNodeListIterator(this);
        return clone;
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
