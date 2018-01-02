// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

public interface FloatIntSortedMap extends IntSortedMap
{
    float firstKey();
    
    float lastKey();
    
    boolean containsKey(final float p0);
    
    IntIterator valueRangeIterator(final float p0, final boolean p1, final float p2, final boolean p3);
    
    LiteralIterator keyIterator();
    
    LiteralIterator keyRangeIterator(final float p0, final boolean p1, final float p2, final boolean p3);
    
    int get(final float p0);
    
    int remove(final float p0);
    
    int remove(final float p0, final int p1);
    
    int put(final float p0, final int p1);
}
