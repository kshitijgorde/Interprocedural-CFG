// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.dom;

import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xml.dtm.ref.DTMAxisIteratorBase;

public class SingletonIterator extends DTMAxisIteratorBase
{
    private int _node;
    private final boolean _isConstant;
    
    public SingletonIterator() {
        this(Integer.MIN_VALUE, false);
    }
    
    public SingletonIterator(final int node) {
        this(node, false);
    }
    
    public SingletonIterator(final int node, final boolean constant) {
        super._startNode = node;
        this._node = node;
        this._isConstant = constant;
    }
    
    public DTMAxisIterator setStartNode(final int node) {
        if (this._isConstant) {
            this._node = super._startNode;
            return this.resetPosition();
        }
        if (super._isRestartable) {
            if (this._node <= 0) {
                super._startNode = node;
                this._node = node;
            }
            return this.resetPosition();
        }
        return this;
    }
    
    public DTMAxisIterator reset() {
        if (this._isConstant) {
            this._node = super._startNode;
            return this.resetPosition();
        }
        final boolean temp = super._isRestartable;
        super._isRestartable = true;
        this.setStartNode(super._startNode);
        super._isRestartable = temp;
        return this;
    }
    
    public int next() {
        final int result = this._node;
        this._node = -1;
        return this.returnNode(result);
    }
    
    public void setMark() {
        super._markedNode = this._node;
    }
    
    public void gotoMark() {
        this._node = super._markedNode;
    }
}
