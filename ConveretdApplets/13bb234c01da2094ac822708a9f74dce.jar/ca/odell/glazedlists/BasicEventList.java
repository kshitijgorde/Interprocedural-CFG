// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import ca.odell.glazedlists.event.ListEventPublisher;
import ca.odell.glazedlists.util.concurrent.ReadWriteLock;
import ca.odell.glazedlists.util.concurrent.LockFactory;
import java.util.List;
import java.util.RandomAccess;
import java.io.Serializable;

public final class BasicEventList extends AbstractEventList implements Serializable, RandomAccess
{
    private List a;
    
    public BasicEventList() {
        this(LockFactory.a.a());
    }
    
    public BasicEventList(final ReadWriteLock readWriteLock) {
        this(null, readWriteLock);
    }
    
    public BasicEventList(final ListEventPublisher listEventPublisher, final ReadWriteLock readWriteLock) {
        this(10, listEventPublisher, readWriteLock);
    }
    
    public BasicEventList(final int n, final ListEventPublisher listEventPublisher, final ReadWriteLock d) {
        super(listEventPublisher);
        this.a = new ArrayList(n);
        this.d = d;
    }
    
    public void add(final int n, final Object o) {
        this.c.b();
        this.c.a(n, o);
        this.a.add(n, o);
        this.c.c();
    }
    
    public boolean add(final Object o) {
        this.c.b();
        this.c.a(this.size(), o);
        final boolean add = this.a.add(o);
        this.c.c();
        return add;
    }
    
    public boolean addAll(final Collection collection) {
        return this.addAll(this.size(), collection);
    }
    
    public boolean addAll(int n, final Collection collection) {
        if (collection.size() == 0) {
            return false;
        }
        this.c.b();
        for (final Object next : collection) {
            this.c.a(n, next);
            this.a.add(n, next);
            ++n;
        }
        this.c.c();
        return !collection.isEmpty();
    }
    
    public Object remove(final int n) {
        this.c.b();
        final Object remove = this.a.remove(n);
        this.c.b(n, remove);
        this.c.c();
        return remove;
    }
    
    public boolean remove(final Object o) {
        final int index = this.a.indexOf(o);
        if (index == -1) {
            return false;
        }
        this.remove(index);
        return true;
    }
    
    public void clear() {
        if (this.isEmpty()) {
            return;
        }
        this.c.b();
        for (int i = 0; i < this.size(); ++i) {
            this.c.b(0, this.get(i));
        }
        this.a.clear();
        this.c.c();
    }
    
    public Object set(final int n, final Object o) {
        this.c.b();
        final Object set = this.a.set(n, o);
        this.c.c(n, set);
        this.c.c();
        return set;
    }
    
    public Object get(final int n) {
        return this.a.get(n);
    }
    
    public int size() {
        return this.a.size();
    }
    
    public boolean removeAll(final Collection collection) {
        boolean b = false;
        this.c.b();
        final Iterator<Object> iterator = collection.iterator();
        while (iterator.hasNext()) {
            int index;
            while ((index = this.indexOf(iterator.next())) != -1) {
                this.c.b(index, this.a.remove(index));
                b = true;
            }
        }
        this.c.c();
        return b;
    }
    
    public boolean retainAll(final Collection collection) {
        boolean b = false;
        this.c.b();
        int i = 0;
        while (i < this.a.size()) {
            if (collection.contains(this.a.get(i))) {
                ++i;
            }
            else {
                this.c.b(i, this.a.remove(i));
                b = true;
            }
        }
        this.c.c();
        return b;
    }
}
