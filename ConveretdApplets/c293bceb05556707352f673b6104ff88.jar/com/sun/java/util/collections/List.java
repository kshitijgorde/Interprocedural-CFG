// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.java.util.collections;

public interface List extends Collection
{
    int size();
    
    boolean isEmpty();
    
    boolean contains(final Object p0);
    
    Iterator iterator();
    
    Object[] toArray();
    
    Object[] toArray(final Object[] p0);
    
    boolean add(final Object p0);
    
    boolean remove(final Object p0);
    
    boolean containsAll(final Collection p0);
    
    boolean addAll(final Collection p0);
    
    boolean addAll(final int p0, final Collection p1);
    
    boolean removeAll(final Collection p0);
    
    boolean retainAll(final Collection p0);
    
    void clear();
    
    boolean equals(final Object p0);
    
    int hashCode();
    
    Object get(final int p0);
    
    Object set(final int p0, final Object p1);
    
    void add(final int p0, final Object p1);
    
    Object remove(final int p0);
    
    int indexOf(final Object p0);
    
    int lastIndexOf(final Object p0);
    
    ListIterator listIterator();
    
    ListIterator listIterator(final int p0);
    
    List subList(final int p0, final int p1);
}
