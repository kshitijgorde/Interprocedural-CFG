// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

public interface LongIntSortedMap extends IntSortedMap
{
    long firstKey();
    
    long lastKey();
    
    boolean containsKey(final long p0);
    
    IntIterator valueRangeIterator(final long p0, final boolean p1, final long p2, final boolean p3);
    
    LiteralIterator keyIterator();
    
    LiteralIterator keyRangeIterator(final long p0, final boolean p1, final long p2, final boolean p3);
    
    int get(final long p0);
    
    int remove(final long p0);
    
    int remove(final long p0, final int p1);
    
    int put(final long p0, final int p1);
}
