// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.utils;

import java.util.Vector;

public class SortableVector extends Vector
{
    private int compareToken;
    
    public SortableVector(final int n, final int n2, final int compareToken) {
        super(n, n2);
        this.compareToken = compareToken;
    }
    
    public void sort() {
        SortableHandle sortableHandle;
        int n;
        for (sortableHandle = this.mergeSort(0, this.size() - 1), n = 0; sortableHandle != null && n < this.size(); sortableHandle = sortableHandle.getNext(), ++n) {
            this.setElementAt(sortableHandle, n);
            sortableHandle.setIndex(n);
        }
        if (n < this.size()) {
            this.setSize(n);
            this.trimToSize();
        }
    }
    
    private SortableHandle mergeSort(final int n, final int n2) {
        SortableHandle sortableHandle = null;
        final int n3 = n2 - n + 1;
        if (n3 == 0) {
            return null;
        }
        final SortableHandle obj = this.getObj(n);
        final SortableHandle obj2 = this.getObj(n2);
        switch (n3) {
            case 1: {
                if (obj != null) {
                    obj.setNext(null);
                }
                sortableHandle = obj;
                break;
            }
            case 2: {
                if (obj == null) {
                    sortableHandle = obj2;
                    break;
                }
                if (obj2 == null) {
                    sortableHandle = obj;
                    break;
                }
                if (obj.compareTo(obj2, this.compareToken) > 0) {
                    obj2.setNext(obj);
                    obj.setNext(null);
                    sortableHandle = obj2;
                    break;
                }
                obj.setNext(obj2);
                obj2.setNext(null);
                sortableHandle = obj;
                break;
            }
            default: {
                SortableHandle sortableHandle2 = null;
                SortableHandle sortableHandle3 = this.mergeSort(n, (n + n2) / 2);
                SortableHandle sortableHandle4 = this.mergeSort((n + n2) / 2 + 1, n2);
                while (sortableHandle3 != null && sortableHandle4 != null) {
                    if (sortableHandle3.compareTo(sortableHandle4, this.compareToken) <= 0) {
                        if (sortableHandle == null) {
                            sortableHandle2 = (sortableHandle = sortableHandle3);
                        }
                        else {
                            sortableHandle2.setNext(sortableHandle3);
                            sortableHandle2 = sortableHandle2.getNext();
                        }
                        sortableHandle3 = sortableHandle3.getNext();
                    }
                    else {
                        if (sortableHandle == null) {
                            sortableHandle2 = (sortableHandle = sortableHandle4);
                        }
                        else {
                            sortableHandle2.setNext(sortableHandle4);
                            sortableHandle2 = sortableHandle2.getNext();
                        }
                        sortableHandle4 = sortableHandle4.getNext();
                    }
                }
                if (sortableHandle3 == null) {
                    if (sortableHandle2 != null) {
                        sortableHandle2.setNext(sortableHandle4);
                    }
                    else {
                        sortableHandle2 = sortableHandle4;
                    }
                }
                else if (sortableHandle2 != null) {
                    sortableHandle2.setNext(sortableHandle3);
                }
                else {
                    sortableHandle2 = sortableHandle3;
                }
                if (sortableHandle == null) {
                    sortableHandle = sortableHandle2;
                    break;
                }
                break;
            }
        }
        return sortableHandle;
    }
    
    private SortableHandle getObj(final int n) {
        return this.elementAt(n);
    }
}
