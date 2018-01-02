// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl;

import ca.odell.glazedlists.event.ListEvent;
import java.util.NoSuchElementException;
import ca.odell.glazedlists.EventList;
import java.util.ListIterator;
import ca.odell.glazedlists.event.ListEventListener;

public class EventListIterator implements ListEventListener, ListIterator
{
    private EventList a;
    private int b;
    private int c;
    
    public EventListIterator(final EventList list, final int n) {
        this(list, n, true);
    }
    
    public EventListIterator(final EventList a, final int b, final boolean b2) {
        this.c = -1;
        this.a = a;
        this.b = b;
        if (b2) {
            final WeakReferenceProxy weakReferenceProxy = new WeakReferenceProxy(a, this);
            a.a(weakReferenceProxy);
            a.c().a(a, weakReferenceProxy);
        }
        else {
            a.a(this);
            a.c().a(a, this);
        }
    }
    
    public boolean hasNext() {
        return this.b < this.a.size();
    }
    
    public Object next() {
        if (this.b == this.a.size()) {
            throw new NoSuchElementException("Cannot retrieve element " + this.b + " on a list of size " + this.a.size());
        }
        this.c = this.b;
        ++this.b;
        return this.a.get(this.c);
    }
    
    public int nextIndex() {
        return this.b;
    }
    
    public boolean hasPrevious() {
        return this.b > 0;
    }
    
    public Object previous() {
        if (this.b == 0) {
            throw new NoSuchElementException("Cannot retrieve element " + this.b + " on a list of size " + this.a.size());
        }
        --this.b;
        this.c = this.b;
        return this.a.get(this.b);
    }
    
    public int previousIndex() {
        return this.b - 1;
    }
    
    public void add(final Object o) {
        this.a.add(this.b, o);
    }
    
    public void remove() {
        if (this.c == -1) {
            throw new IllegalStateException("Cannot remove() without a prior call to next() or previous()");
        }
        this.a.remove(this.c);
    }
    
    public void set(final Object o) {
        if (this.c == -1) {
            throw new IllegalStateException("Cannot set() without a prior call to next() or previous()");
        }
        this.a.set(this.c, o);
    }
    
    public void a(final ListEvent listEvent) {
        while (listEvent.b()) {
            final int f = listEvent.f();
            final int i = listEvent.i();
            if (i == 2) {
                if (f <= this.b) {
                    ++this.b;
                }
                if (this.c == -1 || f > this.c) {
                    continue;
                }
                ++this.c;
            }
            else {
                if (i != 0) {
                    continue;
                }
                if (f < this.b) {
                    --this.b;
                }
                if (this.c != -1 && f < this.c) {
                    --this.c;
                }
                else {
                    if (this.c == -1 || f != this.c) {
                        continue;
                    }
                    this.c = -1;
                }
            }
        }
    }
}
