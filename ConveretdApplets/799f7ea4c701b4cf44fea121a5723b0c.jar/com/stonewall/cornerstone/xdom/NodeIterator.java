// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.xdom;

import java.util.NoSuchElementException;
import org.w3c.dom.Node;
import java.util.Iterator;

public class NodeIterator implements Iterator
{
    Node node;
    
    NodeIterator(final Node n) {
        this.node = n;
    }
    
    @Override
    public boolean hasNext() {
        return this.next(false) != null;
    }
    
    @Override
    public Object next() throws NoSuchElementException {
        final Object result = this.next(true);
        if (result == null) {
            throw new NoSuchElementException();
        }
        return result;
    }
    
    @Override
    public void remove() {
    }
    
    protected Object next(final boolean advance) {
        final Node result = this.node;
        if (advance) {
            Node next = this.node.getFirstChild();
            if (next == null) {
                next = this.node.getNextSibling();
            }
            if (next == null) {
                next = this.findParentNextSibling(this.node);
            }
            this.node = next;
        }
        return result;
    }
    
    protected Node findParentNextSibling(final Node n) {
        Node result = null;
        for (Node parent = n.getParentNode(); parent != null; parent = parent.getParentNode()) {
            result = parent.getNextSibling();
            if (result != null) {
                break;
            }
        }
        return result;
    }
}
