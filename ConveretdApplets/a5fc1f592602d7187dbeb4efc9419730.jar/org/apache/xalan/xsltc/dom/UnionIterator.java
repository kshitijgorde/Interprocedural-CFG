// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.dom;

import org.apache.xalan.xsltc.runtime.BasisLibrary;
import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xalan.xsltc.DOM;
import org.apache.xml.dtm.ref.DTMAxisIteratorBase;

public final class UnionIterator extends DTMAxisIteratorBase
{
    private final DOM _dom;
    private static final int InitSize = 8;
    private int _heapSize;
    private int _size;
    private LookAheadIterator[] _heap;
    private int _free;
    private int _returnedLast;
    private int _cachedReturnedLast;
    private int _cachedHeapSize;
    
    public UnionIterator(final DOM dom) {
        this._heapSize = 0;
        this._size = 8;
        this._heap = new LookAheadIterator[8];
        this._free = 0;
        this._cachedReturnedLast = -1;
        this._dom = dom;
    }
    
    public DTMAxisIterator cloneIterator() {
        super._isRestartable = false;
        final LookAheadIterator[] heapCopy = new LookAheadIterator[this._heap.length];
        try {
            final UnionIterator clone = (UnionIterator)super.clone();
            for (int i = 0; i < this._free; ++i) {
                heapCopy[i] = this._heap[i].cloneIterator();
            }
            clone.setRestartable(false);
            clone._heap = heapCopy;
            return clone.reset();
        }
        catch (CloneNotSupportedException e) {
            BasisLibrary.runTimeError("ITERATOR_CLONE_ERR", e.toString());
            return null;
        }
    }
    
    public UnionIterator addIterator(final DTMAxisIterator iterator) {
        if (this._free == this._size) {
            final int size = this._size * 2;
            this._size = size;
            final LookAheadIterator[] newArray = new LookAheadIterator[size];
            System.arraycopy(this._heap, 0, newArray, 0, this._free);
            this._heap = newArray;
        }
        ++this._heapSize;
        this._heap[this._free++] = new LookAheadIterator(iterator);
        return this;
    }
    
    public int next() {
        while (this._heapSize > 0) {
            final int smallest = this._heap[0].node;
            if (smallest == -1) {
                if (this._heapSize <= 1) {
                    return -1;
                }
                final LookAheadIterator temp = this._heap[0];
                final LookAheadIterator[] heap = this._heap;
                final int n = 0;
                final LookAheadIterator[] heap2 = this._heap;
                final int heapSize = this._heapSize - 1;
                this._heapSize = heapSize;
                heap[n] = heap2[heapSize];
                this._heap[this._heapSize] = temp;
            }
            else {
                if (smallest != this._returnedLast) {
                    this._heap[0].step();
                    this.heapify(0);
                    final int n2 = smallest;
                    this._returnedLast = n2;
                    return this.returnNode(n2);
                }
                this._heap[0].step();
            }
            this.heapify(0);
        }
        return -1;
    }
    
    public DTMAxisIterator setStartNode(final int node) {
        if (super._isRestartable) {
            super._startNode = node;
            for (int i = 0; i < this._free; ++i) {
                if (!this._heap[i].isStartSet) {
                    this._heap[i].iterator.setStartNode(node);
                    this._heap[i].step();
                    this._heap[i].isStartSet = true;
                }
            }
            final int free = this._free;
            this._heapSize = free;
            for (int j = free / 2; j >= 0; --j) {
                this.heapify(j);
            }
            this._returnedLast = -1;
            return this.resetPosition();
        }
        return this;
    }
    
    private void heapify(int i) {
        while (true) {
            final int r = i + 1 << 1;
            final int l = r - 1;
            int smallest = (l < this._heapSize && this._dom.lessThan(this._heap[l].node, this._heap[i].node)) ? l : i;
            if (r < this._heapSize && this._dom.lessThan(this._heap[r].node, this._heap[smallest].node)) {
                smallest = r;
            }
            if (smallest == i) {
                break;
            }
            final LookAheadIterator temp = this._heap[smallest];
            this._heap[smallest] = this._heap[i];
            this._heap[i] = temp;
            i = smallest;
        }
    }
    
    public void setMark() {
        for (int i = 0; i < this._free; ++i) {
            this._heap[i].setMark();
        }
        this._cachedReturnedLast = this._returnedLast;
        this._cachedHeapSize = this._heapSize;
    }
    
    public void gotoMark() {
        for (int i = 0; i < this._free; ++i) {
            this._heap[i].gotoMark();
        }
        final int cachedHeapSize = this._cachedHeapSize;
        this._heapSize = cachedHeapSize;
        for (int j = cachedHeapSize / 2; j >= 0; --j) {
            this.heapify(j);
        }
        this._returnedLast = this._cachedReturnedLast;
    }
    
    public DTMAxisIterator reset() {
        for (int i = 0; i < this._free; ++i) {
            this._heap[i].iterator.reset();
            this._heap[i].step();
        }
        final int free = this._free;
        this._heapSize = free;
        for (int j = free / 2; j >= 0; --j) {
            this.heapify(j);
        }
        this._returnedLast = -1;
        return this.resetPosition();
    }
    
    private static final class LookAheadIterator
    {
        public int node;
        public int markedNode;
        public DTMAxisIterator iterator;
        public boolean isStartSet;
        
        public LookAheadIterator(final DTMAxisIterator iterator) {
            this.isStartSet = false;
            this.iterator = iterator;
        }
        
        public int step() {
            return this.node = this.iterator.next();
        }
        
        public LookAheadIterator cloneIterator() {
            final LookAheadIterator clone = new LookAheadIterator(this.iterator.cloneIterator());
            clone.node = this.node;
            clone.markedNode = this.node;
            return clone;
        }
        
        public void setMark() {
            this.markedNode = this.node;
            this.iterator.setMark();
        }
        
        public void gotoMark() {
            this.node = this.markedNode;
            this.iterator.gotoMark();
        }
    }
}
