// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graph;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface Map2D
{
    void clear();
    
    boolean containsKey(final Object p0, final Object p1);
    
    boolean containsKey(final Object p0);
    
    boolean containsValue(final Object p0);
    
    Set entrySet();
    
    Object get(final Object p0, final Object p1);
    
    Map get(final Object p0);
    
    boolean isEmpty();
    
    Set keySet();
    
    Object put(final Object p0, final Object p1, final Object p2);
    
    void putAll(final Map2D p0);
    
    void remove(final Object p0, final Object p1);
    
    void remove(final Object p0);
    
    int size();
    
    Collection values();
    
    public interface Entry
    {
        Object getValue();
        
        Object getKey1();
        
        Object getKey2();
    }
}
