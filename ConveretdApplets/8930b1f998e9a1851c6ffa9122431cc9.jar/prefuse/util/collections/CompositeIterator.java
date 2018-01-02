// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class CompositeIterator implements Iterator
{
    private Iterator[] m_iters;
    private int m_cur;
    
    public CompositeIterator(final int n) {
        this.m_iters = new Iterator[n];
    }
    
    public CompositeIterator(final Iterator iterator, final Iterator iterator2) {
        this(new Iterator[] { iterator, iterator2 });
    }
    
    public CompositeIterator(final Iterator[] iters) {
        this.m_iters = iters;
        this.m_cur = 0;
    }
    
    public void setIterator(final int n, final Iterator iterator) {
        this.m_iters[n] = iterator;
    }
    
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
    public Object next() {
        if (this.hasNext()) {
            return this.m_iters[this.m_cur].next();
        }
        throw new NoSuchElementException();
    }
    
    public boolean hasNext() {
        if (this.m_iters == null) {
            return false;
        }
        while (this.m_cur < this.m_iters.length) {
            if (this.m_iters[this.m_cur] == null) {
                ++this.m_cur;
            }
            else {
                if (this.m_iters[this.m_cur].hasNext()) {
                    return true;
                }
                ++this.m_cur;
            }
        }
        this.m_iters = null;
        return false;
    }
}
