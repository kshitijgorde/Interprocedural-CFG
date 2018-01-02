// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.util.EmptyStackException;
import java.util.Arrays;
import java.io.Serializable;

public final class FastStack implements Serializable, Cloneable
{
    private Object[] contents;
    private int size;
    private int initialSize;
    
    public FastStack() {
        this.initialSize = 10;
    }
    
    public FastStack(final int size) {
        this.initialSize = Math.max(1, size);
    }
    
    public void clear() {
        this.size = 0;
        if (this.contents != null) {
            Arrays.fill(this.contents, null);
        }
    }
    
    public Object clone() {
        try {
            final FastStack stack = (FastStack)super.clone();
            if (this.contents != null) {
                stack.contents = this.contents.clone();
            }
            return stack;
        }
        catch (CloneNotSupportedException ex) {
            throw new IllegalStateException("Clone not supported? Why?");
        }
    }
    
    public Object get(final int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        return this.contents[index];
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public Object peek() {
        if (this.size == 0) {
            throw new EmptyStackException();
        }
        return this.contents[this.size - 1];
    }
    
    public Object pop() {
        if (this.size == 0) {
            throw new EmptyStackException();
        }
        --this.size;
        final Object retval = this.contents[this.size];
        this.contents[this.size] = null;
        return retval;
    }
    
    public void push(final Object o) {
        if (this.contents == null) {
            (this.contents = new Object[this.initialSize])[0] = o;
            this.size = 1;
            return;
        }
        final int oldSize = this.size;
        ++this.size;
        if (this.contents.length == this.size) {
            final Object[] newContents = new Object[this.size + this.initialSize];
            System.arraycopy(this.contents, 0, newContents, 0, this.size);
            this.contents = newContents;
        }
        this.contents[oldSize] = o;
    }
    
    public int size() {
        return this.size;
    }
}
