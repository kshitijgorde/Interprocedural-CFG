// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.java.util.collections;

public interface ListIterator extends Iterator
{
    boolean hasNext();
    
    Object next();
    
    boolean hasPrevious();
    
    Object previous();
    
    int nextIndex();
    
    int previousIndex();
    
    void remove();
    
    void set(final Object p0);
    
    void add(final Object p0);
}
