// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Collection;
import java.util.List;

public class SyncList extends SyncCollection implements List
{
    public SyncList(final List list, final ReadWriteLock readWriteLock) {
        super(list, readWriteLock.readLock(), readWriteLock.writeLock());
    }
    
    public SyncList(final List list, final Sync sync) {
        super(list, sync);
    }
    
    public SyncList(final List list, final Sync sync, final Sync sync2) {
        super(list, sync, sync2);
    }
    
    public void add(final int n, final Object o) {
        try {
            super.wr_.acquire();
            try {
                this.baseList().add(n, o);
            }
            finally {
                super.wr_.release();
            }
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new UnsupportedOperationException();
        }
    }
    
    public boolean addAll(final int n, final Collection collection) {
        try {
            super.wr_.acquire();
            try {
                return this.baseList().addAll(n, collection);
            }
            finally {
                super.wr_.release();
            }
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new UnsupportedOperationException();
        }
    }
    
    protected List baseList() {
        return (List)super.c_;
    }
    
    public boolean equals(final Object o) {
        final boolean beforeRead = this.beforeRead();
        try {
            return super.c_.equals(o);
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public Object get(final int n) {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.baseList().get(n);
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public int hashCode() {
        final boolean beforeRead = this.beforeRead();
        try {
            return super.c_.hashCode();
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public int indexOf(final Object o) {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.baseList().indexOf(o);
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public int lastIndexOf(final Object o) {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.baseList().lastIndexOf(o);
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public ListIterator listIterator() {
        final boolean beforeRead = this.beforeRead();
        try {
            return new SyncCollectionListIterator(this.baseList().listIterator());
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public ListIterator listIterator(final int n) {
        final boolean beforeRead = this.beforeRead();
        try {
            return new SyncCollectionListIterator(this.baseList().listIterator(n));
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public Object remove(final int n) {
        try {
            super.wr_.acquire();
            try {
                return this.baseList().remove(n);
            }
            finally {
                super.wr_.release();
            }
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new UnsupportedOperationException();
        }
    }
    
    public Object set(final int n, final Object o) {
        try {
            super.wr_.acquire();
            try {
                return this.baseList().set(n, o);
            }
            finally {
                super.wr_.release();
            }
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new UnsupportedOperationException();
        }
    }
    
    public List subList(final int n, final int n2) {
        final boolean beforeRead = this.beforeRead();
        try {
            return new SyncList(this.baseList().subList(n, n2), super.rd_, super.wr_);
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public ListIterator unprotectedListIterator() {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.baseList().listIterator();
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public ListIterator unprotectedListIterator(final int n) {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.baseList().listIterator(n);
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public class SyncCollectionListIterator extends SyncCollectionIterator implements ListIterator
    {
        SyncCollectionListIterator(final Iterator iterator) {
            super(iterator);
        }
        
        public void add(final Object o) {
            try {
                SyncList.this.wr_.acquire();
                try {
                    this.baseListIterator().add(o);
                }
                finally {
                    SyncList.this.wr_.release();
                }
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new UnsupportedOperationException();
            }
        }
        
        protected ListIterator baseListIterator() {
            return (ListIterator)super.baseIterator_;
        }
        
        public boolean hasPrevious() {
            final boolean beforeRead = SyncList.this.beforeRead();
            try {
                return this.baseListIterator().hasPrevious();
            }
            finally {
                SyncList.this.afterRead(beforeRead);
            }
        }
        
        public int nextIndex() {
            final boolean beforeRead = SyncList.this.beforeRead();
            try {
                return this.baseListIterator().nextIndex();
            }
            finally {
                SyncList.this.afterRead(beforeRead);
            }
        }
        
        public Object previous() {
            final boolean beforeRead = SyncList.this.beforeRead();
            try {
                return this.baseListIterator().previous();
            }
            finally {
                SyncList.this.afterRead(beforeRead);
            }
        }
        
        public int previousIndex() {
            final boolean beforeRead = SyncList.this.beforeRead();
            try {
                return this.baseListIterator().previousIndex();
            }
            finally {
                SyncList.this.afterRead(beforeRead);
            }
        }
        
        public void set(final Object o) {
            try {
                SyncList.this.wr_.acquire();
                try {
                    this.baseListIterator().set(o);
                }
                finally {
                    SyncList.this.wr_.release();
                }
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new UnsupportedOperationException();
            }
        }
    }
}
