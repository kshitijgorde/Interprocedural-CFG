// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

import java.util.Comparator;

public interface LiteralComparator extends Comparator
{
    int compare(final byte p0, final byte p1);
    
    int compare(final int p0, final int p1);
    
    int compare(final long p0, final long p1);
    
    int compare(final float p0, final float p1);
    
    int compare(final double p0, final double p1);
    
    int compare(final boolean p0, final boolean p1);
}
