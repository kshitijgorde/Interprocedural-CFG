// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.util;

import java.util.Vector;

public class SortableVector extends Vector
{
    private int[] indexes;
    private Sortable compare;
    private SwapListener swapListener;
    
    public SortableVector() {
    }
    
    public SortableVector(final int initialCapacity) {
        super(initialCapacity);
    }
    
    public SortableVector(final int initialCapacity, final int capacityIncrement) {
        super(initialCapacity, capacityIncrement);
    }
    
    public void addSwapListener(final SwapListener newSwapListener) {
        this.swapListener = newSwapListener;
    }
    
    public SwapListener getSwapListener() {
        return this.swapListener;
    }
    
    public synchronized Object linearLookup(final Sortable item) {
        for (int i = 0; i < this.size(); ++i) {
            if (item.compare(item, this.elementAt(i)) == 0) {
                return this.elementAt(i);
            }
        }
        return null;
    }
    
    public synchronized int linearLookup(final Sortable item, final int startIndex) {
        for (int i = startIndex; i < this.size(); ++i) {
            if (item.compare(item, this.elementAt(i)) == 0) {
                return i;
            }
        }
        return -1;
    }
    
    private void sort(int start, final int end) {
        if (start >= end) {
            return;
        }
        this.swap(start, (start + end) / 2);
        if (this.indexes == null) {
            for (int i = start + 1; i <= end; ++i) {
                if (this.compare.compare(super.elementData[start], super.elementData[i]) > 0) {
                    this.swap(++start, i);
                }
            }
        }
        else {
            for (int i = start + 1; i <= end; ++i) {
                if (this.compare.compare(super.elementData[this.indexes[start]], super.elementData[this.indexes[i]]) > 0) {
                    this.swap(++start, i);
                }
            }
        }
        this.swap(start, start);
        this.sort(start, start - 1);
        this.sort(start + 1, end);
    }
    
    public synchronized void sort(final int start, final int end, final Sortable comp) {
        this.sort(start, end, comp, null);
    }
    
    public synchronized void sort(final int start, final int end, final Sortable comp, final int[] indexes) {
        this.compare = comp;
        this.indexes = indexes;
        if (indexes != null) {
            for (int i = start; i <= end; ++i) {
                indexes[i] = i;
            }
        }
        this.sort(start, end);
    }
    
    public synchronized void sort(final Sortable comp) {
        this.sort(0, this.size() - 1, comp);
    }
    
    protected final void swap(final int i, final int j) {
        if (this.indexes == null) {
            final Object tmp = super.elementData[i];
            super.elementData[i] = super.elementData[j];
            super.elementData[j] = tmp;
        }
        else {
            final int tmp2 = this.indexes[i];
            this.indexes[i] = this.indexes[j];
            this.indexes[j] = tmp2;
        }
        if (this.swapListener != null) {
            this.swapListener.swapped(i, j);
        }
    }
}
