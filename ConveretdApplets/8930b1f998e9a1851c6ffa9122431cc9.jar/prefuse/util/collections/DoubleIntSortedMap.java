// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

public interface DoubleIntSortedMap extends IntSortedMap
{
    double firstKey();
    
    double lastKey();
    
    boolean containsKey(final double p0);
    
    IntIterator valueRangeIterator(final double p0, final boolean p1, final double p2, final boolean p3);
    
    LiteralIterator keyIterator();
    
    LiteralIterator keyRangeIterator(final double p0, final boolean p1, final double p2, final boolean p3);
    
    int get(final double p0);
    
    int remove(final double p0);
    
    int remove(final double p0, final int p1);
    
    int put(final double p0, final int p1);
}
