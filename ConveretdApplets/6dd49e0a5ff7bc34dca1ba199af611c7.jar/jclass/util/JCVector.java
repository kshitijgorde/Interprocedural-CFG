// 
// Decompiled by Procyon v0.5.30
// 

package jclass.util;

import java.util.Vector;

public class JCVector extends Vector
{
    public JCVector(final int n) {
        super(n, 10);
    }
    
    public JCVector(final Vector vector) {
        if (vector != null) {
            for (int i = 0; i < vector.size(); ++i) {
                final Vector element = vector.elementAt(i);
                if (element != null && element instanceof Vector) {
                    this.addElement(new JCVector(element));
                }
                else {
                    this.addElement(vector.elementAt(i));
                }
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
    
    public boolean equals(final JCVector jcVector) {
        if (jcVector == null || super.elementCount != jcVector.size()) {
            return false;
        }
        for (int i = 0; i < super.elementCount; ++i) {
            if (super.elementData[i] == null) {
                if (jcVector.elementData[i] != null) {
                    return false;
                }
            }
            else if (!super.elementData[i].equals(jcVector.elementData[i])) {
                return false;
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
        }
        else if (n < super.elementCount) {
            super.elementData[n] = null;
        }
    }
    
    public final synchronized void setMinSize(final int elementCount) {
        if (elementCount > super.elementCount) {
            this.ensureCapacity(elementCount);
            super.elementCount = elementCount;
        }
    }
    
    public void strip() {
        int n;
        for (n = super.elementCount - 1; n >= 0 && super.elementData[n] == null; --n) {}
        super.elementCount = n + 1;
        this.trimToSize();
    }
    
    public Object[] getArrayCopy() {
        final Object[] array = new Object[super.elementCount];
        System.arraycopy(super.elementData, 0, array, 0, super.elementCount);
        return array;
    }
    
    public final synchronized void copyFrom(final Object[] array) {
        if (array == null) {
            this.removeAllElements();
        }
        else {
            this.ensureCapacity(array.length);
            super.elementCount = array.length;
            System.arraycopy(array, 0, super.elementData, 0, array.length);
        }
    }
    
    public final synchronized Object getFirst() {
        return (super.elementCount > 0) ? super.elementData[0] : null;
    }
    
    public final synchronized Object getLast() {
        return (super.elementCount > 0) ? super.elementData[super.elementCount - 1] : null;
    }
}
