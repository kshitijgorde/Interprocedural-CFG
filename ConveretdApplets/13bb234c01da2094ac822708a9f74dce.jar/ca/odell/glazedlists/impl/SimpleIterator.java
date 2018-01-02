// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl;

import java.util.NoSuchElementException;
import java.util.List;
import java.util.Iterator;

public class SimpleIterator implements Iterator
{
    private final List a;
    private int b;
    
    public SimpleIterator(final List a) {
        this.b = 0;
        this.a = a;
    }
    
    public boolean hasNext() {
        return this.b < this.a.size();
    }
    
    public Object next() {
        if (this.b == this.a.size()) {
            throw new NoSuchElementException("Cannot retrieve element " + this.b + " on a list of size " + this.a.size());
        }
        return this.a.get(this.b++);
    }
    
    public void remove() {
        if (this.b == 0) {
            throw new IllegalStateException("Cannot remove() without a prior call to next() or previous()");
        }
        this.a.remove(--this.b);
    }
}
