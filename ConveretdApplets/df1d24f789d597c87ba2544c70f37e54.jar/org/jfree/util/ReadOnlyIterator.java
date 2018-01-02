// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.util.Iterator;

public class ReadOnlyIterator implements Iterator
{
    private Iterator base;
    
    public ReadOnlyIterator(final Iterator base) {
        if (base == null) {
            throw new NullPointerException("Base iterator is null.");
        }
        this.base = base;
    }
    
    public boolean hasNext() {
        return this.base.hasNext();
    }
    
    public Object next() {
        return this.base.next();
    }
    
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
