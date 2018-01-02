// 
// Decompiled by Procyon v0.5.30
// 

package jclass.util;

import java.util.Vector;

public class JCVector extends Vector
{
    JCSortInterface compare;
    int[] indexes;
    
    public JCVector(final int n) {
        super(n, 10);
    }
    
    public JCVector(final Vector vector) {
        if (vector == null) {
            return;
        }
        for (int i = 0; i < vector.size(); ++i) {
            final Vector element = vector.elementAt(i);
            if (element != null && element instanceof Vector && !element.getClass().getName().equals("jclass.util.JCString")) {
                this.addElement(new JCVector(element));
            }
            else {
                this.addElement(vector.elementAt(i));
            }
        }
    }
    
    public JCVector() {
        super(1, 10);
    }
    
    public JCVector(final Object[] array) {
        this.copyFrom(array);
    }
    
    public synchronized void add(final Object o) {
        if (!this.contains(o)) {
            this.addElement(o);
        }
    }
    
    public final synchronized Object at(final int n) {
        return this.elementAt(n);
    }
    
    public boolean equals(final JCVector jcVector) {
        if (jcVector == null || super.elementCount != jcVector.size()) {
            return false;
        }
        for (int i = 0; i < super.elementCount; ++i) {
            if (super.elementData[i] == null) {
                if (jcVector.elementData[i] != null) {
                    return false;
                }
                if (!super.elementData[i].equals(jcVector.elementData[i])) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public final synchronized void removeElementsAt(final int n, final int n2) {
        for (int n3 = n; n3 < n + n2 && n < super.elementCount; ++n3) {
            this.removeElementAt(n);
        }
    }
    
    public synchronized void setElementAt(final int n, final Object o) {
        if (o != null) {
            if (super.elementCount < n + 1) {
                this.setSize(n + 1);
            }
            super.elementData[n] = o;
            return;
        }
        if (n < super.elementCount) {
            super.elementData[n] = null;
        }
    }
    
    public final synchronized void setMinSize(final int elementCount) {
        if (elementCount > super.elementCount) {
            this.ensureCapacity(elementCount);
            super.elementCount = elementCount;
        }
    }
    
    public final synchronized void strip() {
        int n;
        for (n = super.elementCount - 1; n >= 0 && super.elementData[n] == null; --n) {}
        super.elementCount = n + 1;
        this.trimToSize();
    }
    
    public final synchronized Object[] getArrayCopy() {
        final Object[] array = new Object[super.elementCount];
        System.arraycopy(super.elementData, 0, array, 0, super.elementCount);
        return array;
    }
    
    public final synchronized void copyFrom(final Object[] array) {
        if (array == null) {
            this.removeAllElements();
            return;
        }
        this.ensureCapacity(array.length);
        super.elementCount = array.length;
        System.arraycopy(array, 0, super.elementData, 0, array.length);
    }
    
    public final synchronized Object getFirst() {
        if (super.elementCount > 0) {
            return super.elementData[0];
        }
        return null;
    }
    
    public final synchronized Object getLast() {
        if (super.elementCount > 0) {
            return super.elementData[super.elementCount - 1];
        }
        return null;
    }
    
    public synchronized void sort(final int n, final int n2, final JCSortInterface compare, final int[] indexes) {
        this.compare = compare;
        this.indexes = indexes;
        if (indexes != null) {
            for (int i = n; i <= n2; ++i) {
                indexes[i] = i;
            }
        }
        this.sort(n, n2);
    }
    
    public synchronized void sort(final int n, final int n2, final JCSortInterface jcSortInterface) {
        this.sort(n, n2, jcSortInterface, null);
    }
    
    public synchronized void sort(final JCSortInterface jcSortInterface) {
        this.sort(0, this.size() - 1, jcSortInterface);
    }
    
    void sort(int n, final int n2) {
        if (n >= n2) {
            return;
        }
        this.swap(n, (n + n2) / 2);
        if (this.indexes == null) {
            for (int i = n + 1; i <= n2; ++i) {
                if (this.compare.compare(super.elementData[n], super.elementData[i])) {
                    this.swap(++n, i);
                }
            }
        }
        else {
            for (int j = n + 1; j <= n2; ++j) {
                if (this.compare.compare(super.elementData[this.indexes[n]], super.elementData[this.indexes[j]])) {
                    this.swap(++n, j);
                }
            }
        }
        this.swap(n, n);
        this.sort(n, n - 1);
        this.sort(n + 1, n2);
    }
    
    private final void swap(final int n, final int n2) {
        if (this.indexes == null) {
            final Object o = super.elementData[n];
            super.elementData[n] = super.elementData[n2];
            super.elementData[n2] = o;
            return;
        }
        final int n3 = this.indexes[n];
        this.indexes[n] = this.indexes[n2];
        this.indexes[n2] = n3;
    }
}
