// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.java.util.collections;

public interface Collection
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
    
    boolean removeAll(final Collection p0);
    
    boolean retainAll(final Collection p0);
    
    void clear();
    
    boolean equals(final Object p0);
    
    int hashCode();
}
