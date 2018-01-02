// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.dom;

import org.apache.xalan.xsltc.DOM;
import org.apache.xalan.xsltc.Translet;
import org.apache.xml.dtm.DTMAxisIterator;

public abstract class SingleNodeCounter extends NodeCounter
{
    private static final int[] EmptyArray;
    DTMAxisIterator _countSiblings;
    
    public SingleNodeCounter(final Translet translet, final DOM document, final DTMAxisIterator iterator) {
        super(translet, document, iterator);
        this._countSiblings = null;
    }
    
    public NodeCounter setStartNode(final int node) {
        super._node = node;
        super._nodeType = super._document.getExpandedTypeID(node);
        this._countSiblings = super._document.getAxisIterator(12);
        return this;
    }
    
    public String getCounter() {
        int result;
        if (super._value != Integer.MIN_VALUE) {
            result = super._value;
        }
        else {
            int next = super._node;
            result = 0;
            if (!this.matchesCount(next)) {
                while ((next = super._document.getParent(next)) > -1) {
                    if (this.matchesCount(next)) {
                        break;
                    }
                    if (this.matchesFrom(next)) {
                        next = -1;
                        break;
                    }
                }
            }
            if (next == -1) {
                return this.formatNumbers(SingleNodeCounter.EmptyArray);
            }
            this._countSiblings.setStartNode(next);
            do {
                if (this.matchesCount(next)) {
                    ++result;
                }
            } while ((next = this._countSiblings.next()) != -1);
        }
        return this.formatNumbers(result);
    }
    
    public static NodeCounter getDefaultNodeCounter(final Translet translet, final DOM document, final DTMAxisIterator iterator) {
        return new DefaultSingleNodeCounter(translet, document, iterator);
    }
    
    static {
        EmptyArray = new int[0];
    }
    
    static class DefaultSingleNodeCounter extends SingleNodeCounter
    {
        public DefaultSingleNodeCounter(final Translet translet, final DOM document, final DTMAxisIterator iterator) {
            super(translet, document, iterator);
        }
        
        public NodeCounter setStartNode(final int node) {
            super._node = node;
            super._nodeType = super._document.getExpandedTypeID(node);
            super._countSiblings = super._document.getTypedAxisIterator(12, super._document.getExpandedTypeID(node));
            return this;
        }
        
        public String getCounter() {
            int result;
            if (super._value != Integer.MIN_VALUE) {
                result = super._value;
            }
            else {
                result = 1;
                super._countSiblings.setStartNode(super._node);
                int next;
                while ((next = super._countSiblings.next()) != -1) {
                    ++result;
                }
            }
            return this.formatNumbers(result);
        }
    }
}
