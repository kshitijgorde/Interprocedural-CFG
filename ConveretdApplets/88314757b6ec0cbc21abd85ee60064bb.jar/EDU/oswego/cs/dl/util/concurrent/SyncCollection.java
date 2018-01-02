// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

import java.util.Iterator;
import java.util.Collection;

public class SyncCollection implements Collection
{
    protected final Collection c_;
    protected final Sync rd_;
    protected final Sync wr_;
    protected final SynchronizedLong syncFailures_;
    
    public SyncCollection(final Collection collection, final ReadWriteLock readWriteLock) {
        this(collection, readWriteLock.readLock(), readWriteLock.writeLock());
    }
    
    public SyncCollection(final Collection collection, final Sync sync) {
        this(collection, sync, sync);
    }
    
    public SyncCollection(final Collection c_, final Sync rd_, final Sync wr_) {
        this.syncFailures_ = new SynchronizedLong(0L);
        this.c_ = c_;
        this.rd_ = rd_;
        this.wr_ = wr_;
    }
    
    public boolean add(final Object o) {
        try {
            this.wr_.acquire();
            try {
                return this.c_.add(o);
            }
            finally {
                this.wr_.release();
            }
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new UnsupportedOperationException();
        }
    }
    
    public boolean addAll(final Collection collection) {
        try {
            this.wr_.acquire();
            try {
                return this.c_.addAll(collection);
            }
            finally {
                this.wr_.release();
            }
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new UnsupportedOperationException();
        }
    }
    
    protected void afterRead(final boolean b) {
        if (b) {
            Thread.currentThread().interrupt();
        }
        else {
            this.rd_.release();
        }
    }
    
    protected boolean beforeRead() {
        try {
            this.rd_.acquire();
            return false;
        }
        catch (InterruptedException ex) {
            this.syncFailures_.increment();
            return true;
        }
    }
    
    public void clear() {
        try {
            this.wr_.acquire();
            try {
                this.c_.clear();
            }
            finally {
                this.wr_.release();
            }
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new UnsupportedOperationException();
        }
    }
    
    public boolean contains(final Object o) {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.c_.contains(o);
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public boolean containsAll(final Collection collection) {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.c_.containsAll(collection);
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public boolean isEmpty() {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.c_.isEmpty();
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public Iterator iterator() {
        final boolean beforeRead = this.beforeRead();
        try {
            return new SyncCollectionIterator(this.c_.iterator());
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public Sync readerSync() {
        return this.rd_;
    }
    
    public boolean remove(final Object o) {
        try {
            this.wr_.acquire();
            try {
                return this.c_.remove(o);
            }
            finally {
                this.wr_.release();
            }
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new UnsupportedOperationException();
        }
    }
    
    public boolean removeAll(final Collection collection) {
        try {
            this.wr_.acquire();
            try {
                return this.c_.removeAll(collection);
            }
            finally {
                this.wr_.release();
            }
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new UnsupportedOperationException();
        }
    }
    
    public boolean retainAll(final Collection collection) {
        try {
            this.wr_.acquire();
            try {
                return this.c_.retainAll(collection);
            }
            finally {
                this.wr_.release();
            }
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new UnsupportedOperationException();
        }
    }
    
    public int size() {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.c_.size();
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public long syncFailures() {
        return this.syncFailures_.get();
    }
    
    public Object[] toArray() {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.c_.toArray();
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public Object[] toArray(final Object[] array) {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.c_.toArray(array);
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public Iterator unprotectedIterator() {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.c_.iterator();
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public Sync writerSync() {
        return this.wr_;
    }
    
    public class SyncCollectionIterator implements Iterator
    {
        protected final Iterator baseIterator_;
        
        SyncCollectionIterator(final Iterator baseIterator_) {
            this.baseIterator_ = baseIterator_;
        }
        
        public boolean hasNext() {
            final boolean beforeRead = SyncCollection.this.beforeRead();
            try {
                return this.baseIterator_.hasNext();
            }
            finally {
                SyncCollection.this.afterRead(beforeRead);
            }
        }
        
        public Object next() {
            final boolean beforeRead = SyncCollection.this.beforeRead();
            try {
                return this.baseIterator_.next();
            }
            finally {
                SyncCollection.this.afterRead(beforeRead);
            }
        }
        
        public void remove() {
            try {
                SyncCollection.this.wr_.acquire();
                try {
                    this.baseIterator_.remove();
                }
                finally {
                    SyncCollection.this.wr_.release();
                }
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new UnsupportedOperationException();
            }
        }
    }
}
