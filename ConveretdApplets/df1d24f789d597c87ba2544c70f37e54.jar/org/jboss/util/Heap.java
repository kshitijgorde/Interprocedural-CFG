// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.util.Comparator;

public class Heap
{
    private Comparator m_comparator;
    private int m_count;
    private Object[] m_nodes;
    
    public Heap() {
        this(null);
    }
    
    public Heap(final Comparator comparator) {
        this.m_comparator = comparator;
        this.clear();
    }
    
    public void insert(final Object obj) {
        final int length = this.m_nodes.length;
        if (this.m_count == length) {
            final Object[] newNodes = new Object[length + length];
            System.arraycopy(this.m_nodes, 0, newNodes, 0, length);
            this.m_nodes = newNodes;
        }
        int k;
        int par;
        for (k = this.m_count; k > 0; k = par) {
            par = this.parent(k);
            if (this.compare(obj, this.m_nodes[par]) >= 0) {
                break;
            }
            this.m_nodes[k] = this.m_nodes[par];
        }
        this.m_nodes[k] = obj;
        ++this.m_count;
    }
    
    public Object extract() {
        if (this.m_count < 1) {
            return null;
        }
        final int length = this.m_nodes.length >> 1;
        if (length > 5 && this.m_count < length >> 1) {
            final Object[] newNodes = new Object[length];
            System.arraycopy(this.m_nodes, 0, newNodes, 0, length);
            this.m_nodes = newNodes;
        }
        int k = 0;
        final Object ret = this.m_nodes[k];
        --this.m_count;
        final Object last = this.m_nodes[this.m_count];
        while (true) {
            final int l = this.left(k);
            if (l >= this.m_count) {
                break;
            }
            final int r = this.right(k);
            final int child = (r >= this.m_count || this.compare(this.m_nodes[l], this.m_nodes[r]) < 0) ? l : r;
            if (this.compare(last, this.m_nodes[child]) <= 0) {
                break;
            }
            this.m_nodes[k] = this.m_nodes[child];
            k = child;
        }
        this.m_nodes[k] = last;
        this.m_nodes[this.m_count] = null;
        return ret;
    }
    
    public Object peek() {
        if (this.m_count < 1) {
            return null;
        }
        return this.m_nodes[0];
    }
    
    public void clear() {
        this.m_count = 0;
        this.m_nodes = new Object[10];
    }
    
    protected int compare(final Object o1, final Object o2) {
        if (this.m_comparator != null) {
            return this.m_comparator.compare(o1, o2);
        }
        if (o1 != null) {
            return ((Comparable)o1).compareTo(o2);
        }
        if (o2 == null) {
            return 0;
        }
        return -((Comparable)o2).compareTo(o1);
    }
    
    protected int parent(final int index) {
        return index - 1 >> 1;
    }
    
    protected int left(final int index) {
        return index + index + 1;
    }
    
    protected int right(final int index) {
        return index + index + 2;
    }
}
