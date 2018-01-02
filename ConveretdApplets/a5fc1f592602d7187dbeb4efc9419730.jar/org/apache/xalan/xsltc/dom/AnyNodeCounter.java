// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.dom;

import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xalan.xsltc.DOM;
import org.apache.xalan.xsltc.Translet;

public abstract class AnyNodeCounter extends NodeCounter
{
    public AnyNodeCounter(final Translet translet, final DOM document, final DTMAxisIterator iterator) {
        super(translet, document, iterator);
    }
    
    public NodeCounter setStartNode(final int node) {
        super._node = node;
        super._nodeType = super._document.getExpandedTypeID(node);
        return this;
    }
    
    public String getCounter() {
        int result;
        if (super._value != Integer.MIN_VALUE) {
            result = super._value;
        }
        else {
            int next = super._node;
            final int root = super._document.getDocument();
            result = 0;
            while (next >= root && !this.matchesFrom(next)) {
                if (this.matchesCount(next)) {
                    ++result;
                }
                --next;
            }
        }
        return this.formatNumbers(result);
    }
    
    public static NodeCounter getDefaultNodeCounter(final Translet translet, final DOM document, final DTMAxisIterator iterator) {
        return new DefaultAnyNodeCounter(translet, document, iterator);
    }
    
    static class DefaultAnyNodeCounter extends AnyNodeCounter
    {
        public DefaultAnyNodeCounter(final Translet translet, final DOM document, final DTMAxisIterator iterator) {
            super(translet, document, iterator);
        }
        
        public String getCounter() {
            int result;
            if (super._value != Integer.MIN_VALUE) {
                result = super._value;
            }
            else {
                int next = super._node;
                result = 0;
                final int ntype = super._document.getExpandedTypeID(super._node);
                final int root = super._document.getDocument();
                while (next >= 0) {
                    if (ntype == super._document.getExpandedTypeID(next)) {
                        ++result;
                    }
                    if (next == root) {
                        break;
                    }
                    --next;
                }
            }
            return this.formatNumbers(result);
        }
    }
}
