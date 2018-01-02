// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

import java.util.NoSuchElementException;

public class IntArrayIterator extends IntIterator
{
    private int[] m_array;
    private int m_cur;
    private int m_end;
    
    public IntArrayIterator(final int[] array, final int cur, final int n) {
        this.m_array = array;
        this.m_cur = cur;
        this.m_end = cur + n;
    }
    
    public int nextInt() {
        if (this.m_cur >= this.m_end) {
            throw new NoSuchElementException();
        }
        return this.m_array[this.m_cur++];
    }
    
    public boolean hasNext() {
        return this.m_cur < this.m_end;
    }
    
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
