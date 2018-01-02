// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

import java.util.Iterator;

public interface ObjectIntSortedMap extends IntSortedMap
{
    public static final Object MAX_KEY = new Object();
    public static final Object MIN_KEY = new Object();
    
    Object firstKey();
    
    Object lastKey();
    
    boolean containsKey(final Object p0);
    
    IntIterator valueRangeIterator(final Object p0, final boolean p1, final Object p2, final boolean p3);
    
    Iterator keyIterator();
    
    Iterator keyRangeIterator(final Object p0, final boolean p1, final Object p2, final boolean p3);
    
    int get(final Object p0);
    
    int remove(final Object p0);
    
    int remove(final Object p0, final int p1);
    
    int put(final Object p0, final int p1);
}
