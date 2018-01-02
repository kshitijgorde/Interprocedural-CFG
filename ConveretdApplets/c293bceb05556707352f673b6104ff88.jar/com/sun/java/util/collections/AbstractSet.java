// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.java.util.collections;

public abstract class AbstractSet extends AbstractCollection implements Set
{
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Set)) {
            return false;
        }
        final Collection collection = (Collection)o;
        return collection.size() == this.size() && this.containsAll(collection);
    }
    
    public int hashCode() {
        int n = 0;
        for (final Object next : this) {
            if (next != null) {
                n += next.hashCode();
            }
        }
        return n;
    }
}
