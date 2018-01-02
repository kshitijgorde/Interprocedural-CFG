// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.util;

import java.util.NoSuchElementException;
import prefuse.data.Table;
import prefuse.data.expression.Predicate;
import prefuse.util.collections.IntIterator;

public class FilterRowIterator extends IntIterator
{
    private Predicate predicate;
    private IntIterator rows;
    private Table t;
    private int next;
    
    public FilterRowIterator(final IntIterator rows, final Table t, final Predicate predicate) {
        this.predicate = predicate;
        this.rows = rows;
        this.t = t;
        this.next = this.advance();
    }
    
    private int advance() {
        while (this.rows.hasNext()) {
            final int nextInt = this.rows.nextInt();
            if (this.predicate.getBoolean(this.t.getTuple(nextInt))) {
                return nextInt;
            }
        }
        this.rows = null;
        return this.next = -1;
    }
    
    public int nextInt() {
        if (!this.hasNext()) {
            throw new NoSuchElementException("No more elements");
        }
        final int next = this.next;
        this.next = this.advance();
        return next;
    }
    
    public boolean hasNext() {
        return this.rows != null;
    }
    
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
