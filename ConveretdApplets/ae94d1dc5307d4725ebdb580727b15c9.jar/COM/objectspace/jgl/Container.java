// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

import java.util.Enumeration;
import java.io.Serializable;

public interface Container extends Cloneable, Serializable
{
    Object clone();
    
    String toString();
    
    boolean equals(final Object p0);
    
    int size();
    
    int maxSize();
    
    boolean isEmpty();
    
    void clear();
    
    Enumeration elements();
    
    ForwardIterator start();
    
    ForwardIterator finish();
    
    Object add(final Object p0);
    
    Object remove(final Enumeration p0);
    
    int remove(final Enumeration p0, final Enumeration p1);
}
