// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.util;

import java.util.NoSuchElementException;
import java.util.BitSet;
import prefuse.util.collections.IntIterator;

public class UniqueRowIterator extends IntIterator
{
    private IntIterator m_iter;
    private int m_next;
    private BitSet m_visited;
    
    public UniqueRowIterator(final IntIterator iter) {
        this.m_iter = iter;
        this.m_visited = new BitSet();
        this.advance();
    }
    
    private void advance() {
        int nextInt;
        for (nextInt = -1; nextInt == -1 && this.m_iter.hasNext(); nextInt = -1) {
            nextInt = this.m_iter.nextInt();
            if (this.visit(nextInt)) {}
        }
        this.m_next = nextInt;
    }
    
    private boolean visit(final int n) {
        if (this.m_visited.get(n)) {
            return true;
        }
        this.m_visited.set(n);
        return false;
    }
    
    public boolean hasNext() {
        return this.m_next != -1;
    }
    
    public int nextInt() {
        if (this.m_next == -1) {
            throw new NoSuchElementException();
        }
        final int next = this.m_next;
        this.advance();
        return next;
    }
    
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
