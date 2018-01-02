// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

class DescendantIterator implements Iterator
{
    private Iterator iterator;
    private Iterator nextIterator;
    private List stack;
    private static final String CVS_ID = "@(#) $RCSfile: DescendantIterator.java,v $ $Revision: 1.6 $ $Date: 2007/11/10 05:28:58 $ $Name: jdom_1_1_1 $";
    
    DescendantIterator(final Parent parent) {
        this.stack = new ArrayList();
        if (parent == null) {
            throw new IllegalArgumentException("parent parameter was null");
        }
        this.iterator = parent.getContent().iterator();
    }
    
    public boolean hasNext() {
        return (this.iterator != null && this.iterator.hasNext()) || (this.nextIterator != null && this.nextIterator.hasNext()) || this.stackHasAnyNext();
    }
    
    public Object next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        if (this.nextIterator != null) {
            this.push(this.iterator);
            this.iterator = this.nextIterator;
            this.nextIterator = null;
        }
        while (!this.iterator.hasNext()) {
            if (this.stack.size() <= 0) {
                throw new NoSuchElementException("Somehow we lost our iterator");
            }
            this.iterator = this.pop();
        }
        final Content child = this.iterator.next();
        if (child instanceof Element) {
            this.nextIterator = ((Element)child).getContent().iterator();
        }
        return child;
    }
    
    public void remove() {
        this.iterator.remove();
    }
    
    private Iterator pop() {
        final int stackSize = this.stack.size();
        if (stackSize == 0) {
            throw new NoSuchElementException("empty stack");
        }
        return this.stack.remove(stackSize - 1);
    }
    
    private void push(final Iterator itr) {
        this.stack.add(itr);
    }
    
    private boolean stackHasAnyNext() {
        for (int size = this.stack.size(), i = 0; i < size; ++i) {
            final Iterator itr = this.stack.get(i);
            if (itr.hasNext()) {
                return true;
            }
        }
        return false;
    }
}
