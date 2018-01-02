// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.java.util.collections;

public abstract class AbstractSequentialList extends AbstractList
{
    public Object get(final int n) {
        final ListIterator listIterator = this.listIterator(n);
        try {
            return listIterator.next();
        }
        catch (NoSuchElementException ex) {
            throw new IndexOutOfBoundsException("Index: " + n);
        }
    }
    
    public Object set(final int n, final Object o) {
        final ListIterator listIterator = this.listIterator(n);
        try {
            final Object next = listIterator.next();
            listIterator.set(o);
            return next;
        }
        catch (NoSuchElementException ex) {
            throw new IndexOutOfBoundsException("Index: " + n);
        }
    }
    
    public void add(final int n, final Object o) {
        this.listIterator(n).add(o);
    }
    
    public Object remove(final int n) {
        final ListIterator listIterator = this.listIterator(n);
        Object next;
        try {
            next = listIterator.next();
        }
        catch (NoSuchElementException ex) {
            throw new IndexOutOfBoundsException("Index: " + n);
        }
        listIterator.remove();
        return next;
    }
    
    public boolean addAll(final int n, final Collection collection) {
        boolean b = false;
        final ListIterator listIterator = this.listIterator(n);
        final Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            listIterator.add(iterator.next());
            listIterator.next();
            b = true;
        }
        return b;
    }
    
    public Iterator iterator() {
        return this.listIterator();
    }
    
    public abstract ListIterator listIterator(final int p0);
}
