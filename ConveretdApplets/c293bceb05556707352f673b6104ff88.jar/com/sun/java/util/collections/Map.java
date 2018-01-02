// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.java.util.collections;

public interface Map
{
    int size();
    
    boolean isEmpty();
    
    boolean containsKey(final Object p0);
    
    boolean containsValue(final Object p0);
    
    Object get(final Object p0);
    
    Object put(final Object p0, final Object p1);
    
    Object remove(final Object p0);
    
    void putAll(final Map p0);
    
    void clear();
    
    Set keySet();
    
    Collection values();
    
    Set entrySet();
    
    boolean equals(final Object p0);
    
    int hashCode();
    
    public interface Entry
    {
        Object getKey();
        
        Object getValue();
        
        Object setValue(final Object p0);
        
        boolean equals(final Object p0);
        
        int hashCode();
    }
}
