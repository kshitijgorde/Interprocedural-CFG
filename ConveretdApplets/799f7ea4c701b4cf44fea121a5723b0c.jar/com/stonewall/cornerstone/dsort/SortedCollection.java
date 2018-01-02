// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsort;

import java.util.Iterator;
import java.util.Collection;
import java.util.HashMap;
import java.util.Stack;
import java.util.Map;
import java.util.Comparator;
import java.util.ArrayList;

public class SortedCollection<E> extends ArrayList<E>
{
    private Comparator<E> comparator;
    private final Map<E, Comparator<E>> comparators;
    private final Stack<E> stack;
    private final ArrayList<E> result;
    public static final long serialVersionUID = 0L;
    
    public SortedCollection() {
        this.comparator = null;
        this.comparators = new HashMap<E, Comparator<E>>();
        this.stack = new Stack<E>();
        this.result = new ArrayList<E>();
    }
    
    public SortedCollection(final int size) {
        super(size);
        this.comparator = null;
        this.comparators = new HashMap<E, Comparator<E>>();
        this.stack = new Stack<E>();
        this.result = new ArrayList<E>();
    }
    
    public void add(final E item, final Comparator<E> c) {
        this.add(item);
        this.comparators.put(item, c);
    }
    
    public void setComparator(final Comparator<E> c) {
        this.comparator = c;
    }
    
    public void sort() {
        this.result.clear();
        while (this.notEmpty()) {
            this.push(this.get(0));
            while (this.runStack()) {}
        }
        this.clear();
        this.addAll((Collection<? extends E>)this.result);
    }
    
    @Override
    public void sort(final Comparator<E> c) {
        this.setComparator(c);
        this.sort();
    }
    
    private boolean runStack() {
        final E a = this.stack.peek();
        final Comparator<E> c = this.comparator(a);
        if (c != null) {
            for (final E b : this) {
                if (c.compare(b, a) < 0) {
                    this.push(b);
                    return true;
                }
            }
        }
        this.pop();
        return !this.stack.empty();
    }
    
    private void pop() {
        final E item = this.stack.pop();
        this.result.add(item);
    }
    
    private void push(final E item) {
        this.stack.push(item);
        this.remove(item);
    }
    
    private boolean notEmpty() {
        return !this.isEmpty();
    }
    
    private Comparator<E> comparator(final E item) {
        Comparator<E> result = this.comparators.get(item);
        if (result == null) {
            result = this.comparator;
        }
        return result;
    }
}
