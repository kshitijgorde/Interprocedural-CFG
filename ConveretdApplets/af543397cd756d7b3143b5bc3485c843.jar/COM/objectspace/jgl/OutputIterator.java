// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

public interface OutputIterator extends Cloneable
{
    void put(final Object p0);
    
    void advance();
    
    void advance(final int p0);
    
    Object clone();
}
