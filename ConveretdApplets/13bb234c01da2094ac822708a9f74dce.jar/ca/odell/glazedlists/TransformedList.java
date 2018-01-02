// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists;

import java.util.Collection;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;

public abstract class TransformedList extends AbstractEventList implements ListEventListener
{
    protected EventList a;
    
    protected TransformedList(final EventList a) {
        super(a.c());
        this.a = a;
        this.d = a.b();
    }
    
    protected int a(final int n) {
        return n;
    }
    
    protected abstract boolean a();
    
    public abstract void a(final ListEvent p0);
    
    public void add(final int n, final Object o) {
        if (!this.a()) {
            throw new IllegalStateException("Non-writable List cannot be modified");
        }
        if (n < 0 || n > this.size()) {
            throw new IndexOutOfBoundsException("Cannot add at " + n + " on list of size " + this.size());
        }
        this.a.add((n < this.size()) ? this.a(n) : this.a.size(), o);
    }
    
    public boolean addAll(final int n, final Collection collection) {
        this.c.a(true);
        try {
            return super.addAll(n, collection);
        }
        finally {
            this.c.c();
        }
    }
    
    public void clear() {
        this.c.a(true);
        try {
            super.clear();
        }
        finally {
            this.c.c();
        }
    }
    
    public Object get(final int n) {
        if (n < 0 || n >= this.size()) {
            throw new IndexOutOfBoundsException("Cannot get at " + n + " on list of size " + this.size());
        }
        return this.a.get(this.a(n));
    }
    
    public Object remove(final int n) {
        if (!this.a()) {
            throw new IllegalStateException("Non-writable List cannot be modified");
        }
        if (n < 0 || n >= this.size()) {
            throw new IndexOutOfBoundsException("Cannot remove at " + n + " on list of size " + this.size());
        }
        return this.a.remove(this.a(n));
    }
    
    public boolean removeAll(final Collection collection) {
        this.c.a(true);
        try {
            return super.removeAll(collection);
        }
        finally {
            this.c.c();
        }
    }
    
    public boolean retainAll(final Collection collection) {
        this.c.a(true);
        try {
            return super.retainAll(collection);
        }
        finally {
            this.c.c();
        }
    }
    
    public Object set(final int n, final Object o) {
        if (!this.a()) {
            throw new IllegalStateException("List " + this.getClass().getName() + " cannot be modified in the current state");
        }
        if (n < 0 || n >= this.size()) {
            throw new IndexOutOfBoundsException("Cannot set at " + n + " on list of size " + this.size());
        }
        return this.a.set(this.a(n), o);
    }
    
    public int size() {
        return this.a.size();
    }
}
