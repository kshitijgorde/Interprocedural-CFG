// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.dom;

import org.apache.xalan.xsltc.util.IntegerArray;
import org.apache.xalan.xsltc.DOM;
import org.apache.xalan.xsltc.Translet;
import org.apache.xml.dtm.DTMAxisIterator;

public abstract class MultipleNodeCounter extends NodeCounter
{
    private DTMAxisIterator _precSiblings;
    
    public MultipleNodeCounter(final Translet translet, final DOM document, final DTMAxisIterator iterator) {
        super(translet, document, iterator);
        this._precSiblings = null;
    }
    
    public NodeCounter setStartNode(final int node) {
        super._node = node;
        super._nodeType = super._document.getExpandedTypeID(node);
        this._precSiblings = super._document.getAxisIterator(12);
        return this;
    }
    
    public String getCounter() {
        if (super._value == -2.147483648E9) {
            final IntegerArray ancestors = new IntegerArray();
            int next = super._node;
            ancestors.add(next);
            while ((next = super._document.getParent(next)) > -1 && !this.matchesFrom(next)) {
                ancestors.add(next);
            }
            final int nAncestors = ancestors.cardinality();
            final int[] counters = new int[nAncestors];
            for (int i = 0; i < nAncestors; ++i) {
                counters[i] = Integer.MIN_VALUE;
            }
            for (int j = 0, k = nAncestors - 1; k >= 0; --k, ++j) {
                final int counter = counters[j];
                final int ancestor = ancestors.at(k);
                if (this.matchesCount(ancestor)) {
                    this._precSiblings.setStartNode(ancestor);
                    while ((next = this._precSiblings.next()) != -1) {
                        if (this.matchesCount(next)) {
                            counters[j] = ((counters[j] == Integer.MIN_VALUE) ? 1 : (counters[j] + 1));
                        }
                    }
                    counters[j] = ((counters[j] == Integer.MIN_VALUE) ? 1 : (counters[j] + 1));
                }
            }
            return this.formatNumbers(counters);
        }
        if (super._value == 0.0) {
            return "0";
        }
        if (Double.isNaN(super._value)) {
            return "NaN";
        }
        if (super._value < 0.0 && Double.isInfinite(super._value)) {
            return "-Infinity";
        }
        if (Double.isInfinite(super._value)) {
            return "Infinity";
        }
        return this.formatNumbers((int)super._value);
    }
    
    public static NodeCounter getDefaultNodeCounter(final Translet translet, final DOM document, final DTMAxisIterator iterator) {
        return new DefaultMultipleNodeCounter(translet, document, iterator);
    }
    
    static class DefaultMultipleNodeCounter extends MultipleNodeCounter
    {
        public DefaultMultipleNodeCounter(final Translet translet, final DOM document, final DTMAxisIterator iterator) {
            super(translet, document, iterator);
        }
    }
}
