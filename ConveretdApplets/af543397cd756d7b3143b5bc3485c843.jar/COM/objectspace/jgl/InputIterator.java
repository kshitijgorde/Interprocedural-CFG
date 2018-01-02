// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

import java.util.Enumeration;

public interface InputIterator extends Enumeration, Cloneable
{
    boolean atBegin();
    
    boolean atEnd();
    
    Object get();
    
    void advance();
    
    void advance(final int p0);
    
    Object clone();
}
