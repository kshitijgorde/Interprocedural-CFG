// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

import java.util.Iterator;
import java.util.Collection;
import java.io.Serializable;
import java.util.AbstractSet;

public class CopyOnWriteArraySet extends AbstractSet implements Cloneable, Serializable
{
    protected final CopyOnWriteArrayList al;
    
    public CopyOnWriteArraySet() {
        this.al = new CopyOnWriteArrayList();
    }
    
    public CopyOnWriteArraySet(final Collection collection) {
        (this.al = new CopyOnWriteArrayList()).addAllAbsent(collection);
    }
    
    public boolean add(final Object o) {
        return this.al.addIfAbsent(o);
    }
    
    public boolean addAll(final Collection collection) {
        return this.al.addAllAbsent(collection) > 0;
    }
    
    public void clear() {
        this.al.clear();
    }
    
    public boolean contains(final Object o) {
        return this.al.contains(o);
    }
    
    public boolean containsAll(final Collection collection) {
        return this.al.containsAll(collection);
    }
    
    public boolean isEmpty() {
        return this.al.isEmpty();
    }
    
    public Iterator iterator() {
        return this.al.iterator();
    }
    
    public boolean remove(final Object o) {
        return this.al.remove(o);
    }
    
    public boolean removeAll(final Collection collection) {
        return this.al.removeAll(collection);
    }
    
    public boolean retainAll(final Collection collection) {
        return this.al.retainAll(collection);
    }
    
    public int size() {
        return this.al.size();
    }
    
    public Object[] toArray() {
        return this.al.toArray();
    }
    
    public Object[] toArray(final Object[] array) {
        return this.al.toArray(array);
    }
}
