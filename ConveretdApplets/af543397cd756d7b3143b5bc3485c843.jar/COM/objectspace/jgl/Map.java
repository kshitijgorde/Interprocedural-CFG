// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

import java.util.Enumeration;
import java.util.Dictionary;

public abstract class Map extends Dictionary implements Container
{
    public abstract int count(final Object p0);
    
    public abstract int countValues(final Object p0);
    
    public abstract Enumeration keys(final Object p0);
    
    public abstract Enumeration values(final Object p0);
    
    public abstract Object clone();
    
    public abstract String toString();
    
    public abstract boolean equals(final Object p0);
    
    public abstract int size();
    
    public abstract int maxSize();
    
    public abstract boolean isEmpty();
    
    public abstract void clear();
    
    public abstract Enumeration elements();
    
    public abstract ForwardIterator start();
    
    public abstract ForwardIterator finish();
    
    public abstract Object add(final Object p0);
    
    public abstract int remove(final Enumeration p0, final Enumeration p1);
    
    public abstract Object remove(final Enumeration p0);
}
