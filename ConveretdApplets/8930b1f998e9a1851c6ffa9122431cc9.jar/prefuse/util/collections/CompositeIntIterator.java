// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

import java.util.NoSuchElementException;

public class CompositeIntIterator extends IntIterator
{
    private IntIterator[] m_iters;
    private int m_cur;
    
    public CompositeIntIterator(final IntIterator intIterator, final IntIterator intIterator2) {
        this(new IntIterator[] { intIterator, intIterator2 });
    }
    
    public CompositeIntIterator(final IntIterator[] iters) {
        this.m_iters = iters;
        this.m_cur = 0;
    }
    
    public int nextInt() {
        if (this.hasNext()) {
            return this.m_iters[this.m_cur].nextInt();
        }
        throw new NoSuchElementException();
    }
    
    public boolean hasNext() {
        if (this.m_iters == null) {
            return false;
        }
        while (!this.m_iters[this.m_cur].hasNext()) {
            if (++this.m_cur >= this.m_iters.length) {
                this.m_iters = null;
                return false;
            }
        }
        return true;
    }
    
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
