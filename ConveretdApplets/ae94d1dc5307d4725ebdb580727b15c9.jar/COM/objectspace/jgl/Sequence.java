// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

public interface Sequence extends Container
{
    Object clone();
    
    Object at(final int p0);
    
    void put(final int p0, final Object p1);
    
    Object back();
    
    Object front();
    
    void pushFront(final Object p0);
    
    Object popFront();
    
    void pushBack(final Object p0);
    
    Object popBack();
    
    int remove(final Object p0);
    
    int remove(final Object p0, final int p1);
    
    int remove(final int p0, final int p1, final Object p2);
    
    int count(final Object p0);
    
    int count(final int p0, final int p1, final Object p2);
    
    int replace(final Object p0, final Object p1);
    
    int replace(final int p0, final int p1, final Object p2, final Object p3);
    
    boolean contains(final Object p0);
    
    int indexOf(final Object p0);
    
    int indexOf(final int p0, final int p1, final Object p2);
}
