// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.util;

import java.util.List;
import java.util.Collections;
import prefuse.data.Tuple;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Iterator;

public class SortedTupleIterator implements Iterator
{
    private ArrayList m_tuples;
    private Comparator m_cmp;
    private Iterator m_iter;
    
    public SortedTupleIterator(final Iterator iterator, final Comparator comparator) {
        this(iterator, 128, comparator);
    }
    
    public SortedTupleIterator(final Iterator iterator, final int n, final Comparator comparator) {
        this.m_tuples = new ArrayList(n);
        this.init(iterator, comparator);
    }
    
    public void init(final Iterator iterator, final Comparator cmp) {
        this.m_tuples.clear();
        this.m_cmp = cmp;
        while (iterator.hasNext()) {
            this.m_tuples.add(iterator.next());
        }
        Collections.sort((List<Object>)this.m_tuples, this.m_cmp);
        this.m_iter = this.m_tuples.iterator();
    }
    
    public boolean hasNext() {
        return this.m_iter.hasNext();
    }
    
    public Object next() {
        return this.m_iter.next();
    }
    
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
