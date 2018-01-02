// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

import java.util.Comparator;

public interface IntSortedMap
{
    int getMinimum();
    
    int getMaximum();
    
    int getMedian();
    
    int getUniqueCount();
    
    boolean isAllowDuplicates();
    
    int size();
    
    boolean isEmpty();
    
    Comparator comparator();
    
    void clear();
    
    boolean containsValue(final int p0);
    
    IntIterator valueIterator(final boolean p0);
}
