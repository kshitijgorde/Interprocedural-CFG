// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

public interface BooleanIntSortedMap extends IntSortedMap
{
    boolean firstKey();
    
    boolean lastKey();
    
    boolean containsKey(final boolean p0);
    
    IntIterator valueRangeIterator(final boolean p0, final boolean p1, final boolean p2, final boolean p3);
    
    LiteralIterator keyIterator();
    
    LiteralIterator keyRangeIterator(final boolean p0, final boolean p1, final boolean p2, final boolean p3);
    
    int get(final boolean p0);
    
    int remove(final boolean p0);
    
    int remove(final boolean p0, final int p1);
    
    int put(final boolean p0, final int p1);
}
