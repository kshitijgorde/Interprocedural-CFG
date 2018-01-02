// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

public interface BidirectionalIterator extends ForwardIterator
{
    Object clone();
    
    void retreat();
    
    void retreat(final int p0);
    
    Object get(final int p0);
    
    Object get();
    
    void put(final int p0, final Object p1);
}
