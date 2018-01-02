// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.java.util.collections;

public abstract class AbstractList extends AbstractCollection implements List
{
    protected transient int modCount;
    
    public boolean add(final Object o) {
        this.add(this.size(), o);
        return true;
    }
    
    public abstract Object get(final int p0);
    
    public Object set(final int n, final Object o) {
        throw new UnsupportedOperationException();
    }
    
    public void add(final int n, final Object o) {
        throw new UnsupportedOperationException();
    }
    
    public Object remove(final int n) {
        throw new UnsupportedOperationException();
    }
    
    public int indexOf(final Object o) {
        final ListIterator listIterator = this.listIterator();
        if (o == null) {
            while (listIterator.hasNext()) {
                if (listIterator.next() == null) {
                    return listIterator.previousIndex();
                }
            }
        }
        else {
            while (listIterator.hasNext()) {
                if (o.equals(listIterator.next())) {
                    return listIterator.previousIndex();
                }
            }
        }
        return -1;
    }
    
    public int lastIndexOf(final Object o) {
        final ListIterator listIterator = this.listIterator(this.size());
        if (o == null) {
            while (listIterator.hasPrevious()) {
                if (listIterator.previous() == null) {
                    return listIterator.nextIndex();
                }
            }
        }
        else {
            while (listIterator.hasPrevious()) {
                if (o.equals(listIterator.previous())) {
                    return listIterator.nextIndex();
                }
            }
        }
        return -1;
    }
    
    public void clear() {
        this.removeRange(0, this.size());
    }
    
    public boolean addAll(int n, final Collection collection) {
        boolean b = false;
        final Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            this.add(n++, iterator.next());
            b = true;
        }
        return b;
    }
    
    public Iterator iterator() {
        return new Itr();
    }
    
    public ListIterator listIterator() {
        return this.listIterator(0);
    }
    
    public ListIterator listIterator(final int n) {
        if (n < 0 || n > this.size()) {
            throw new IndexOutOfBoundsException("Index: " + n);
        }
        return new ListItr(n);
    }
    
    public List subList(final int n, final int n2) {
        return (List)new SubList(this, n, n2);
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof List)) {
            return false;
        }
        final ListIterator listIterator = this.listIterator();
        final ListIterator listIterator2 = ((List)o).listIterator();
        while (listIterator.hasNext() && listIterator2.hasNext()) {
            final Object next = listIterator.next();
            final Object next2 = listIterator2.next();
            boolean equals;
            if (next == null) {
                if (next2 == null) {
                    continue;
                }
                equals = false;
            }
            else {
                equals = next.equals(next2);
            }
            if (!equals) {
                return false;
            }
        }
        return !listIterator.hasNext() && !listIterator2.hasNext();
    }
    
    public int hashCode() {
        int n = 1;
        for (final Object next : this) {
            n = 31 * n + ((next == null) ? 0 : next.hashCode());
        }
        return n;
    }
    
    protected void removeRange(final int n, final int n2) {
        final ListIterator listIterator = this.listIterator(n);
        for (int i = 0; i < n2 - n; ++i) {
            listIterator.next();
            listIterator.remove();
        }
    }
    
    private class Itr implements Iterator
    {
        int cursor;
        int lastRet;
        int expectedModCount;
        
        public boolean hasNext() {
            return this.cursor != AbstractList.this.size();
        }
        
        public Object next() {
            try {
                final Object value = AbstractList.this.get(this.cursor);
                this.checkForComodification();
                this.lastRet = this.cursor++;
                return value;
            }
            catch (IndexOutOfBoundsException ex) {
                this.checkForComodification();
                throw new NoSuchElementException();
            }
        }
        
        public void remove() {
            if (this.lastRet == -1) {
                throw new IllegalStateException();
            }
            try {
                AbstractList.this.remove(this.lastRet);
                if (this.lastRet < this.cursor) {
                    --this.cursor;
                }
                this.lastRet = -1;
                final int modCount = AbstractList.this.modCount;
                if (modCount - this.expectedModCount > 1) {
                    throw new ConcurrentModificationException();
                }
                this.expectedModCount = modCount;
            }
            catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
        
        final void checkForComodification() {
            if (AbstractList.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
        
        Itr() {
            this.lastRet = -1;
            this.expectedModCount = AbstractList.this.modCount;
        }
    }
    
    private class ListItr extends Itr implements ListIterator
    {
        ListItr(final int cursor) {
            super.cursor = cursor;
        }
        
        public boolean hasPrevious() {
            return super.cursor != 0;
        }
        
        public Object previous() {
            try {
                final AbstractList this$0 = AbstractList.this;
                final int cursor = super.cursor - 1;
                super.cursor = cursor;
                final Object value = this$0.get(cursor);
                ((Itr)this).checkForComodification();
                super.lastRet = super.cursor;
                return value;
            }
            catch (IndexOutOfBoundsException ex) {
                ((Itr)this).checkForComodification();
                throw new NoSuchElementException();
            }
        }
        
        public int nextIndex() {
            return super.cursor;
        }
        
        public int previousIndex() {
            return super.cursor - 1;
        }
        
        public void set(final Object o) {
            if (super.lastRet == -1) {
                throw new IllegalStateException();
            }
            try {
                AbstractList.this.set(super.lastRet, o);
                final int modCount = AbstractList.this.modCount;
                if (modCount - super.expectedModCount > 1) {
                    throw new ConcurrentModificationException();
                }
                super.expectedModCount = modCount;
            }
            catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
        
        public void add(final Object o) {
            try {
                AbstractList.this.add(super.cursor++, o);
                super.lastRet = -1;
                final int modCount = AbstractList.this.modCount;
                if (modCount - super.expectedModCount > 1) {
                    throw new ConcurrentModificationException();
                }
                super.expectedModCount = modCount;
            }
            catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
