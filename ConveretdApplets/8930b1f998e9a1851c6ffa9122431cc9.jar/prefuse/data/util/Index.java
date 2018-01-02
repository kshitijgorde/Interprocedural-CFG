// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.util;

import prefuse.util.collections.IntIterator;
import java.util.Comparator;

public interface Index
{
    public static final int TYPE_ASCENDING = 32;
    public static final int TYPE_DESCENDING = 16;
    public static final int TYPE_LEFT_INCLUSIVE = 8;
    public static final int TYPE_LEFT_EXCLUSIVE = 4;
    public static final int TYPE_RIGHT_INCLUSIVE = 2;
    public static final int TYPE_RIGHT_EXCLUSIVE = 1;
    public static final int TYPE_AII = 42;
    public static final int TYPE_DII = 26;
    public static final int TYPE_AEI = 38;
    public static final int TYPE_DEI = 22;
    public static final int TYPE_AIE = 41;
    public static final int TYPE_DIE = 25;
    public static final int TYPE_AEE = 37;
    public static final int TYPE_DEE = 21;
    
    void index();
    
    void dispose();
    
    Comparator getComparator();
    
    int minimum();
    
    int maximum();
    
    int median();
    
    int uniqueCount();
    
    int size();
    
    IntIterator allRows(final int p0);
    
    IntIterator rows(final Object p0, final Object p1, final int p2);
    
    IntIterator rows(final int p0, final int p1, final int p2);
    
    IntIterator rows(final long p0, final long p1, final int p2);
    
    IntIterator rows(final float p0, final float p1, final int p2);
    
    IntIterator rows(final double p0, final double p1, final int p2);
    
    IntIterator rows(final Object p0);
    
    IntIterator rows(final int p0);
    
    IntIterator rows(final long p0);
    
    IntIterator rows(final float p0);
    
    IntIterator rows(final double p0);
    
    IntIterator rows(final boolean p0);
    
    int get(final Object p0);
    
    int get(final int p0);
    
    int get(final long p0);
    
    int get(final float p0);
    
    int get(final double p0);
}
