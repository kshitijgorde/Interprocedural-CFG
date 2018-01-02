// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.java.util.collections;

public interface SortedSet extends Set
{
    Comparator comparator();
    
    SortedSet subSet(final Object p0, final Object p1);
    
    SortedSet headSet(final Object p0);
    
    SortedSet tailSet(final Object p0);
    
    Object first();
    
    Object last();
}
