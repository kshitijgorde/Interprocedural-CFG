// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

public interface ForwardIterator extends InputIterator, OutputIterator
{
    void advance();
    
    void advance(final int p0);
    
    int distance(final ForwardIterator p0);
    
    Object clone();
    
    Container getContainer();
}
