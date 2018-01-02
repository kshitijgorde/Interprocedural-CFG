// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.util;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;

public class Fifo<T> extends ArrayList<T>
{
    public void push(final T t) {
        this.add(t);
    }
    
    public void push(final Collection<T> collection) {
        final Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            this.push(iterator.next());
        }
    }
    
    public T pop() {
        if (this.size() == 0) {
            throw new EmptyStackException();
        }
        return this.remove(0);
    }
    
    public T peek() {
        return this.get(0);
    }
    
    public boolean empty() {
        return this.size() == 0;
    }
}
