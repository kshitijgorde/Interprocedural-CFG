// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists;

import ca.odell.glazedlists.impl.SubEventList;
import ca.odell.glazedlists.impl.EventListIterator;
import java.util.ListIterator;
import java.util.Collection;
import java.lang.reflect.Array;
import java.util.List;
import ca.odell.glazedlists.impl.SimpleIterator;
import java.util.Iterator;
import ca.odell.glazedlists.impl.GlazedListsImpl;
import ca.odell.glazedlists.event.ListEventListener;
import ca.odell.glazedlists.event.ListEventPublisher;
import ca.odell.glazedlists.util.concurrent.ReadWriteLock;
import ca.odell.glazedlists.event.ListEventAssembler;

public abstract class AbstractEventList implements EventList
{
    protected ListEventAssembler c;
    protected ReadWriteLock d;
    protected ListEventPublisher e;
    
    protected AbstractEventList(ListEventPublisher a) {
        this.c = null;
        this.d = null;
        this.e = null;
        if (a == null) {
            a = ListEventAssembler.a();
        }
        this.e = a;
        this.c = new ListEventAssembler(this, a);
    }
    
    protected AbstractEventList() {
        this(null);
    }
    
    public ListEventPublisher c() {
        return this.e;
    }
    
    public ReadWriteLock b() {
        return this.d;
    }
    
    public void a(final ListEventListener listEventListener) {
        this.c.a(listEventListener);
    }
    
    public void b(final ListEventListener listEventListener) {
        this.c.b(listEventListener);
    }
    
    public abstract int size();
    
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    public boolean contains(final Object o) {
        final Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            if (GlazedListsImpl.a(o, iterator.next())) {
                return true;
            }
        }
        return false;
    }
    
    public Iterator iterator() {
        return new SimpleIterator(this);
    }
    
    public Object[] toArray() {
        final Object[] array = new Object[this.size()];
        int n = 0;
        final Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            array[n] = iterator.next();
            ++n;
        }
        return array;
    }
    
    public Object[] toArray(Object[] array) {
        if (array.length < this.size()) {
            array = (Object[])Array.newInstance(array.getClass().getComponentType(), this.size());
        }
        else if (array.length > this.size()) {
            array[this.size()] = null;
        }
        int n = 0;
        final Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            array[n] = iterator.next();
            ++n;
        }
        return array;
    }
    
    public boolean add(final Object o) {
        final int size = this.size();
        this.add(this.size(), o);
        return this.size() != size;
    }
    
    public boolean remove(final Object o) {
        final int index = this.indexOf(o);
        if (index == -1) {
            return false;
        }
        this.remove(index);
        return true;
    }
    
    public boolean containsAll(final Collection collection) {
        final Iterator<Object> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (!this.contains(iterator.next())) {
                return false;
            }
        }
        return true;
    }
    
    public boolean addAll(final Collection collection) {
        return this.addAll(this.size(), collection);
    }
    
    public boolean addAll(int n, final Collection collection) {
        if (n < 0 || n > this.size()) {
            throw new IndexOutOfBoundsException("Cannot add at " + n + " on list of size " + this.size());
        }
        if (collection.size() == 0) {
            return false;
        }
        final int size = this.size();
        final Iterator<Object> iterator = collection.iterator();
        while (iterator.hasNext()) {
            this.add(n, iterator.next());
            if (n < this.size()) {
                ++n;
            }
        }
        return this.size() != size;
    }
    
    public boolean removeAll(final Collection collection) {
        boolean b = false;
        final Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            if (collection.contains(iterator.next())) {
                iterator.remove();
                b = true;
            }
        }
        return b;
    }
    
    public boolean retainAll(final Collection collection) {
        boolean b = false;
        final Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            if (!collection.contains(iterator.next())) {
                iterator.remove();
                b = true;
            }
        }
        return b;
    }
    
    public void clear() {
        final Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof List)) {
            return false;
        }
        final List list = (List)o;
        if (list.size() != this.size()) {
            return false;
        }
        final Iterator iterator = this.iterator();
        final Iterator<Object> iterator2 = list.iterator();
        while (iterator.hasNext() && iterator2.hasNext()) {
            if (!GlazedListsImpl.a(iterator.next(), iterator2.next())) {
                return false;
            }
        }
        return true;
    }
    
    public int hashCode() {
        int n = 1;
        for (final Object next : this) {
            n = 31 * n + ((next == null) ? 0 : next.hashCode());
        }
        return n;
    }
    
    public abstract Object get(final int p0);
    
    public Object set(final int n, final Object o) {
        throw new UnsupportedOperationException("this list does not support set()");
    }
    
    public void add(final int n, final Object o) {
        throw new UnsupportedOperationException("this list does not support add()");
    }
    
    public Object remove(final int n) {
        throw new UnsupportedOperationException("this list does not support remove()");
    }
    
    public int indexOf(final Object o) {
        int n = 0;
        final Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            if (GlazedListsImpl.a(o, iterator.next())) {
                return n;
            }
            ++n;
        }
        return -1;
    }
    
    public int lastIndexOf(final Object o) {
        for (int i = this.size() - 1; i >= 0; --i) {
            if (GlazedListsImpl.a(o, this.get(i))) {
                return i;
            }
        }
        return -1;
    }
    
    public ListIterator listIterator() {
        return this.listIterator(0);
    }
    
    public ListIterator listIterator(final int n) {
        return new EventListIterator(this, n);
    }
    
    public List subList(final int n, final int n2) {
        return new SubEventList(this, n, n2, true);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("[");
        final Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            sb.append(String.valueOf(iterator.next()));
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
