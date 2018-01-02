// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

public interface RandomAccessIterator extends BidirectionalIterator
{
    int index();
    
    boolean less(final RandomAccessIterator p0);
    
    Object clone();
}
