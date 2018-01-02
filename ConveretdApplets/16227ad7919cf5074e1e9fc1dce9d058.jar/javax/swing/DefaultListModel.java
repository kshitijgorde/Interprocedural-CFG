// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.util.Enumeration;
import java.util.Vector;

public class DefaultListModel extends AbstractListModel
{
    private Vector delegate;
    
    public DefaultListModel() {
        this.delegate = new Vector();
    }
    
    public void add(final int n, final Object o) {
        this.delegate.insertElementAt(o, n);
        this.fireIntervalAdded(this, n, n);
    }
    
    public void addElement(final Object o) {
        final int size = this.delegate.size();
        this.delegate.addElement(o);
        this.fireIntervalAdded(this, size, size);
    }
    
    public int capacity() {
        return this.delegate.capacity();
    }
    
    public void clear() {
        final int n = this.delegate.size() - 1;
        this.delegate.removeAllElements();
        if (n >= 0) {
            this.fireIntervalRemoved(this, 0, n);
        }
    }
    
    public boolean contains(final Object o) {
        return this.delegate.contains(o);
    }
    
    public void copyInto(final Object[] array) {
        this.delegate.copyInto(array);
    }
    
    public Object elementAt(final int n) {
        return this.delegate.elementAt(n);
    }
    
    public Enumeration elements() {
        return this.delegate.elements();
    }
    
    public void ensureCapacity(final int n) {
        this.delegate.ensureCapacity(n);
    }
    
    public Object firstElement() {
        return this.delegate.firstElement();
    }
    
    public Object get(final int n) {
        return this.delegate.elementAt(n);
    }
    
    public Object getElementAt(final int n) {
        return this.delegate.elementAt(n);
    }
    
    public int getSize() {
        return this.delegate.size();
    }
    
    public int indexOf(final Object o) {
        return this.delegate.indexOf(o);
    }
    
    public int indexOf(final Object o, final int n) {
        return this.delegate.indexOf(o, n);
    }
    
    public void insertElementAt(final Object o, final int n) {
        this.delegate.insertElementAt(o, n);
        this.fireIntervalAdded(this, n, n);
    }
    
    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }
    
    public Object lastElement() {
        return this.delegate.lastElement();
    }
    
    public int lastIndexOf(final Object o) {
        return this.delegate.lastIndexOf(o);
    }
    
    public int lastIndexOf(final Object o, final int n) {
        return this.delegate.lastIndexOf(o, n);
    }
    
    public Object remove(final int n) {
        final Object element = this.delegate.elementAt(n);
        this.delegate.removeElementAt(n);
        this.fireIntervalRemoved(this, n, n);
        return element;
    }
    
    public void removeAllElements() {
        final int n = this.delegate.size() - 1;
        this.delegate.removeAllElements();
        if (n >= 0) {
            this.fireIntervalRemoved(this, 0, n);
        }
    }
    
    public boolean removeElement(final Object o) {
        final int index = this.indexOf(o);
        final boolean removeElement = this.delegate.removeElement(o);
        if (index >= 0) {
            this.fireIntervalRemoved(this, index, index);
        }
        return removeElement;
    }
    
    public void removeElementAt(final int n) {
        this.delegate.removeElementAt(n);
        this.fireIntervalRemoved(this, n, n);
    }
    
    public void removeRange(final int n, final int n2) {
        for (int i = n2; i >= n; --i) {
            this.delegate.removeElementAt(i);
        }
        this.fireIntervalRemoved(this, n, n2);
    }
    
    public Object set(final int n, final Object o) {
        final Object element = this.delegate.elementAt(n);
        this.delegate.setElementAt(o, n);
        this.fireContentsChanged(this, n, n);
        return element;
    }
    
    public void setElementAt(final Object o, final int n) {
        this.delegate.setElementAt(o, n);
        this.fireContentsChanged(this, n, n);
    }
    
    public void setSize(final int size) {
        final int size2 = this.delegate.size();
        this.delegate.setSize(size);
        if (size2 > size) {
            this.fireIntervalRemoved(this, size, size2 - 1);
        }
        else if (size2 < size) {
            this.fireIntervalAdded(this, size2, size - 1);
        }
    }
    
    public int size() {
        return this.delegate.size();
    }
    
    public Object[] toArray() {
        final Object[] array = new Object[this.delegate.size()];
        this.delegate.copyInto(array);
        return array;
    }
    
    public String toString() {
        return this.delegate.toString();
    }
    
    public void trimToSize() {
        this.delegate.trimToSize();
    }
}
