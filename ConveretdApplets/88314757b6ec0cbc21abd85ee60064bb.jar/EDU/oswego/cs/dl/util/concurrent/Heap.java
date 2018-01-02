// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

import java.util.Comparator;

public class Heap
{
    protected Object[] nodes_;
    protected int count_;
    protected final Comparator cmp_;
    
    public Heap(final int n) {
        this(n, null);
    }
    
    public Heap(final int n, final Comparator cmp_) throws IllegalArgumentException {
        this.count_ = 0;
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.nodes_ = new Object[n];
        this.cmp_ = cmp_;
    }
    
    public synchronized void clear() {
        for (int i = 0; i < this.count_; ++i) {
            this.nodes_[i] = null;
        }
        this.count_ = 0;
    }
    
    protected int compare(final Object o, final Object o2) {
        if (this.cmp_ == null) {
            return ((Comparable)o).compareTo(o2);
        }
        return this.cmp_.compare(o, o2);
    }
    
    public synchronized Object extract() {
        if (this.count_ < 1) {
            return null;
        }
        int n = 0;
        final Object o = this.nodes_[n];
        --this.count_;
        final Object o2 = this.nodes_[this.count_];
        this.nodes_[this.count_] = null;
        while (true) {
            final int left = this.left(n);
            if (left >= this.count_) {
                break;
            }
            final int right = this.right(n);
            final int n2 = (right >= this.count_ || this.compare(this.nodes_[left], this.nodes_[right]) < 0) ? left : right;
            if (this.compare(o2, this.nodes_[n2]) <= 0) {
                break;
            }
            this.nodes_[n] = this.nodes_[n2];
            n = n2;
        }
        this.nodes_[n] = o2;
        return o;
    }
    
    public synchronized void insert(final Object o) {
        if (this.count_ >= this.nodes_.length) {
            final Object[] nodes_ = new Object[3 * this.nodes_.length / 2 + 1];
            System.arraycopy(this.nodes_, 0, nodes_, 0, this.nodes_.length);
            this.nodes_ = nodes_;
        }
        int i = this.count_;
        ++this.count_;
        while (i > 0) {
            final int parent = this.parent(i);
            if (this.compare(o, this.nodes_[parent]) >= 0) {
                break;
            }
            this.nodes_[i] = this.nodes_[parent];
            i = parent;
        }
        this.nodes_[i] = o;
    }
    
    protected final int left(final int n) {
        return 2 * n + 1;
    }
    
    protected final int parent(final int n) {
        return (n - 1) / 2;
    }
    
    public synchronized Object peek() {
        if (this.count_ > 0) {
            return this.nodes_[0];
        }
        return null;
    }
    
    protected final int right(final int n) {
        return 2 * (n + 1);
    }
    
    public synchronized int size() {
        return this.count_;
    }
}
