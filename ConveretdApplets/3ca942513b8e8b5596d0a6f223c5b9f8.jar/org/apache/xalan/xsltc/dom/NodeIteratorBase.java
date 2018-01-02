// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.dom;

import org.apache.xalan.xsltc.runtime.BasisLibrary;
import org.apache.xalan.xsltc.NodeIterator;

public abstract class NodeIteratorBase implements NodeIterator
{
    protected int _last;
    protected int _position;
    protected int _markedNode;
    protected int _startNode;
    protected boolean _includeSelf;
    protected boolean _isRestartable;
    
    public NodeIteratorBase() {
        this._last = -1;
        this._position = 0;
        this._startNode = -1;
        this._includeSelf = false;
        this._isRestartable = true;
    }
    
    public void setRestartable(final boolean isRestartable) {
        this._isRestartable = isRestartable;
    }
    
    public abstract NodeIterator setStartNode(final int p0);
    
    public NodeIterator reset() {
        final boolean temp = this._isRestartable;
        this._isRestartable = true;
        this.setStartNode(this._includeSelf ? (this._startNode + 1) : this._startNode);
        this._isRestartable = temp;
        return this;
    }
    
    public NodeIterator includeSelf() {
        this._includeSelf = true;
        return this;
    }
    
    public int getLast() {
        if (this._last == -1) {
            final int temp = this._position;
            this.setMark();
            this.reset();
            do {
                ++this._last;
            } while (this.next() != -1);
            this.gotoMark();
            this._position = temp;
        }
        return this._last;
    }
    
    public int getPosition() {
        return (this._position == 0) ? 1 : this._position;
    }
    
    public boolean isReverse() {
        return false;
    }
    
    public NodeIterator cloneIterator() {
        try {
            final NodeIteratorBase clone = (NodeIteratorBase)super.clone();
            clone._isRestartable = false;
            return clone.reset();
        }
        catch (CloneNotSupportedException e) {
            BasisLibrary.runTimeError("ITERATOR_CLONE_ERR", e.toString());
            return null;
        }
    }
    
    protected final int returnNode(final int node) {
        ++this._position;
        return node;
    }
    
    protected final NodeIterator resetPosition() {
        this._position = 0;
        return this;
    }
    
    public abstract void gotoMark();
    
    public abstract void setMark();
    
    public abstract int next();
}
