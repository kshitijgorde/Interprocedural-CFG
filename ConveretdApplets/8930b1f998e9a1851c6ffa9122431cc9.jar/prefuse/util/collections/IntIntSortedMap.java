// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

public interface IntIntSortedMap extends IntSortedMap
{
    int firstKey();
    
    int lastKey();
    
    boolean containsKey(final int p0);
    
    IntIterator valueRangeIterator(final int p0, final boolean p1, final int p2, final boolean p3);
    
    LiteralIterator keyIterator();
    
    LiteralIterator keyRangeIterator(final int p0, final boolean p1, final int p2, final boolean p3);
    
    int get(final int p0);
    
    int remove(final int p0);
    
    int remove(final int p0, final int p1);
    
    int put(final int p0, final int p1);
    
    int getLast(final int p0);
    
    int getNextValue(final int p0, final int p1);
    
    int getPreviousValue(final int p0, final int p1);
}
