// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.util;

import java.util.NoSuchElementException;
import prefuse.data.Tuple;
import prefuse.data.expression.Predicate;
import java.util.Iterator;

public class FilterIterator implements Iterator
{
    private Predicate predicate;
    private Iterator tuples;
    private Tuple next;
    
    public FilterIterator(final Iterator tuples, final Predicate predicate) {
        this.predicate = predicate;
        this.tuples = tuples;
        this.next = this.advance();
    }
    
    private Tuple advance() {
        while (this.tuples.hasNext()) {
            final Tuple tuple = this.tuples.next();
            if (this.predicate.getBoolean(tuple)) {
                return tuple;
            }
        }
        this.tuples = null;
        return this.next = null;
    }
    
    public Object next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException("No more elements");
        }
        final Tuple next = this.next;
        this.next = this.advance();
        return next;
    }
    
    public boolean hasNext() {
        return this.tuples != null;
    }
    
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
