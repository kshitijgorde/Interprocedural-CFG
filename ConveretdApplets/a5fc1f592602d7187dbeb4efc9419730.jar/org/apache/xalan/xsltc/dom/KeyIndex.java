// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.dom;

import org.apache.xml.dtm.DTMAxisIterator;
import java.util.StringTokenizer;
import org.apache.xalan.xsltc.DOMEnhancedForDTM;
import org.apache.xalan.xsltc.DOM;
import org.apache.xalan.xsltc.util.IntegerArray;
import org.apache.xalan.xsltc.runtime.Hashtable;
import org.apache.xml.dtm.ref.DTMAxisIteratorBase;

public class KeyIndex extends DTMAxisIteratorBase
{
    private Hashtable _index;
    private IntegerArray _nodes;
    private DOM _dom;
    private DOMEnhancedForDTM _enhancedDOM;
    private int _markedPosition;
    
    public KeyIndex(final int dummy) {
        this._index = new Hashtable();
        this._nodes = null;
        this._markedPosition = 0;
    }
    
    public void setRestartable(final boolean flag) {
    }
    
    public void add(final Object value, final int node) {
        IntegerArray nodes;
        if ((nodes = (IntegerArray)this._index.get(value)) == null) {
            this._index.put(value, nodes = new IntegerArray());
        }
        nodes.add(node);
    }
    
    public void merge(final KeyIndex other) {
        if (other == null) {
            return;
        }
        if (other._nodes != null) {
            if (this._nodes == null) {
                this._nodes = other._nodes;
            }
            else {
                this._nodes.merge(other._nodes);
            }
        }
    }
    
    public void lookupId(final Object value) {
        this._nodes = null;
        final StringTokenizer values = new StringTokenizer((String)value);
        while (values.hasMoreElements()) {
            final String token = (String)values.nextElement();
            IntegerArray nodes = (IntegerArray)this._index.get(token);
            if (nodes == null && this._enhancedDOM != null && this._enhancedDOM.hasDOMSource()) {
                nodes = this.getDOMNodeById(token);
            }
            if (nodes == null) {
                continue;
            }
            if (this._nodes == null) {
                this._nodes = nodes;
            }
            else {
                this._nodes.merge(nodes);
            }
        }
    }
    
    public IntegerArray getDOMNodeById(final String id) {
        IntegerArray nodes = null;
        if (this._enhancedDOM != null) {
            final int ident = this._enhancedDOM.getElementById(id);
            if (ident != -1) {
                nodes = new IntegerArray();
                this._index.put(id, nodes);
                nodes.add(ident);
            }
        }
        return nodes;
    }
    
    public void lookupKey(final Object value) {
        this._nodes = (IntegerArray)this._index.get(value);
        super._position = 0;
    }
    
    public int next() {
        if (this._nodes == null) {
            return -1;
        }
        return (super._position < this._nodes.cardinality()) ? this._dom.getNodeHandle(this._nodes.at(super._position++)) : -1;
    }
    
    public int containsID(final int node, final Object value) {
        final String string = (String)value;
        if (string.indexOf(32) > -1) {
            final StringTokenizer values = new StringTokenizer(string);
            while (values.hasMoreElements()) {
                final String token = (String)values.nextElement();
                IntegerArray nodes = (IntegerArray)this._index.get(token);
                if (nodes == null && this._enhancedDOM != null && this._enhancedDOM.hasDOMSource()) {
                    nodes = this.getDOMNodeById(token);
                }
                if (nodes != null && nodes.indexOf(node) >= 0) {
                    return 1;
                }
            }
            return 0;
        }
        IntegerArray nodes2 = (IntegerArray)this._index.get(value);
        if (nodes2 == null && this._enhancedDOM != null && this._enhancedDOM.hasDOMSource()) {
            nodes2 = this.getDOMNodeById(string);
        }
        return (nodes2 != null && nodes2.indexOf(node) >= 0) ? 1 : 0;
    }
    
    public int containsKey(final int node, final Object value) {
        final IntegerArray nodes = (IntegerArray)this._index.get(value);
        return (nodes != null && nodes.indexOf(node) >= 0) ? 1 : 0;
    }
    
    public DTMAxisIterator reset() {
        super._position = 0;
        return this;
    }
    
    public int getLast() {
        return (this._nodes == null) ? 0 : this._nodes.cardinality();
    }
    
    public int getPosition() {
        return super._position;
    }
    
    public void setMark() {
        this._markedPosition = super._position;
    }
    
    public void gotoMark() {
        super._position = this._markedPosition;
    }
    
    public DTMAxisIterator setStartNode(final int start) {
        if (start == -1) {
            this._nodes = null;
        }
        else if (this._nodes != null) {
            super._position = 0;
        }
        return this;
    }
    
    public int getStartNode() {
        return 0;
    }
    
    public boolean isReverse() {
        return false;
    }
    
    public DTMAxisIterator cloneIterator() {
        final KeyIndex other = new KeyIndex(0);
        other._index = this._index;
        other._nodes = this._nodes;
        other._position = super._position;
        return other;
    }
    
    public void setDom(final DOM dom) {
        this._dom = dom;
        if (dom instanceof DOMEnhancedForDTM) {
            this._enhancedDOM = (DOMEnhancedForDTM)dom;
        }
        else if (dom instanceof DOMAdapter) {
            final DOM idom = ((DOMAdapter)dom).getDOMImpl();
            if (idom instanceof DOMEnhancedForDTM) {
                this._enhancedDOM = (DOMEnhancedForDTM)idom;
            }
        }
    }
}
