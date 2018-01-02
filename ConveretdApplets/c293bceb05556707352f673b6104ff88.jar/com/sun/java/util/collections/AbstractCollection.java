// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.java.util.collections;

import java.lang.reflect.Array;

public abstract class AbstractCollection implements Collection
{
    public abstract Iterator iterator();
    
    public abstract int size();
    
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    public boolean contains(final Object o) {
        final Iterator iterator = this.iterator();
        if (o == null) {
            while (iterator.hasNext()) {
                if (iterator.next() == null) {
                    return true;
                }
            }
        }
        else {
            while (iterator.hasNext()) {
                if (o.equals(iterator.next())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Object[] toArray() {
        final Object[] array = new Object[this.size()];
        final Iterator iterator = this.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            array[n] = iterator.next();
            ++n;
        }
        return array;
    }
    
    public Object[] toArray(Object[] array) {
        final int size = this.size();
        if (array.length < size) {
            array = (Object[])Array.newInstance(array.getClass().getComponentType(), size);
        }
        final Iterator iterator = this.iterator();
        for (int i = 0; i < size; ++i) {
            array[i] = iterator.next();
        }
        if (array.length > size) {
            array[size] = null;
        }
        return array;
    }
    
    public boolean add(final Object o) {
        throw new UnsupportedOperationException();
    }
    
    public boolean remove(final Object o) {
        final Iterator iterator = this.iterator();
        if (o == null) {
            while (iterator.hasNext()) {
                if (iterator.next() == null) {
                    iterator.remove();
                    return true;
                }
            }
        }
        else {
            while (iterator.hasNext()) {
                if (o.equals(iterator.next())) {
                    iterator.remove();
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean containsAll(final Collection collection) {
        final Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (!this.contains(iterator.next())) {
                return false;
            }
        }
        return true;
    }
    
    public boolean addAll(final Collection collection) {
        boolean b = false;
        final Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (this.add(iterator.next())) {
                b = true;
            }
        }
        return b;
    }
    
    public boolean removeAll(final Collection collection) {
        boolean b = false;
        final Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            if (collection.contains(iterator.next())) {
                iterator.remove();
                b = true;
            }
        }
        return b;
    }
    
    public boolean retainAll(final Collection collection) {
        boolean b = false;
        final Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            if (!collection.contains(iterator.next())) {
                iterator.remove();
                b = true;
            }
        }
        return b;
    }
    
    public void clear() {
        final Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        final Iterator iterator = this.iterator();
        sb.append("[");
        for (int n = this.size() - 1, i = 0; i <= n; ++i) {
            sb.append(String.valueOf(iterator.next()));
            if (i < n) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
