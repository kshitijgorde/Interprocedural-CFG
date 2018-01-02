// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.java.util.collections;

public interface SortedMap extends Map
{
    Comparator comparator();
    
    SortedMap subMap(final Object p0, final Object p1);
    
    SortedMap headMap(final Object p0);
    
    SortedMap tailMap(final Object p0);
    
    Object firstKey();
    
    Object lastKey();
}
