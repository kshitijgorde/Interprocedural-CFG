// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.dom;

import org.apache.xalan.xsltc.runtime.BasisLibrary;
import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xml.dtm.ref.DTMAxisIteratorBase;

public final class NthIterator extends DTMAxisIteratorBase
{
    private DTMAxisIterator _source;
    private final int _position;
    private boolean _ready;
    
    public NthIterator(final DTMAxisIterator source, final int n) {
        this._source = source;
        this._position = n;
    }
    
    public void setRestartable(final boolean isRestartable) {
        super._isRestartable = isRestartable;
        this._source.setRestartable(isRestartable);
    }
    
    public DTMAxisIterator cloneIterator() {
        try {
            final NthIterator clone = (NthIterator)super.clone();
            clone._source = this._source.cloneIterator();
            clone._isRestartable = false;
            return clone;
        }
        catch (CloneNotSupportedException e) {
            BasisLibrary.runTimeError("ITERATOR_CLONE_ERR", e.toString());
            return null;
        }
    }
    
    public int next() {
        if (this._ready) {
            this._ready = false;
            return this._source.getNodeByPosition(this._position);
        }
        return -1;
    }
    
    public DTMAxisIterator setStartNode(final int node) {
        if (super._isRestartable) {
            this._source.setStartNode(node);
            this._ready = true;
        }
        return this;
    }
    
    public DTMAxisIterator reset() {
        this._source.reset();
        this._ready = true;
        return this;
    }
    
    public int getLast() {
        return 1;
    }
    
    public int getPosition() {
        return 1;
    }
    
    public void setMark() {
        this._source.setMark();
    }
    
    public void gotoMark() {
        this._source.gotoMark();
    }
}
