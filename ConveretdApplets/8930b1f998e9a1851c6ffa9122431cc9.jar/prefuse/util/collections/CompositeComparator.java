// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

import java.util.Comparator;

public class CompositeComparator implements Comparator
{
    private static final int INCREMENT = 2;
    private Comparator[] m_cmp;
    private int m_rev;
    private int m_size;
    
    public CompositeComparator(final int n) {
        this(n, false);
    }
    
    public CompositeComparator(final int n, final boolean b) {
        this.m_rev = 1;
        this.m_size = 0;
        this.m_cmp = new Comparator[n];
        this.m_rev = (b ? -1 : 1);
    }
    
    public CompositeComparator(final Comparator[] array) {
        this(array, false);
    }
    
    public CompositeComparator(final Comparator[] array, final boolean b) {
        this(array.length, b);
        System.arraycopy(array, 0, this.m_cmp, 0, array.length);
        this.m_size = array.length;
    }
    
    public void add(final Comparator comparator) {
        if (comparator == null) {
            return;
        }
        if (this.m_cmp.length == this.m_size) {
            final Comparator[] cmp = new Comparator[this.m_size + 2];
            System.arraycopy(this.m_cmp, 0, cmp, 0, this.m_size);
            this.m_cmp = cmp;
        }
        this.m_cmp[this.m_size++] = comparator;
    }
    
    public boolean remove(final Comparator comparator) {
        for (int i = 0; i < this.m_size; ++i) {
            if (this.m_cmp[i].equals(comparator)) {
                System.arraycopy(this.m_cmp, i + 1, this.m_cmp, i, this.m_size - i);
                --this.m_size;
                return true;
            }
        }
        return false;
    }
    
    public int compare(final Object o, final Object o2) {
        for (int i = 0; i < this.m_cmp.length; ++i) {
            final int compare = this.m_cmp[i].compare(o, o2);
            if (compare != 0) {
                return this.m_rev * compare;
            }
        }
        return 0;
    }
}
