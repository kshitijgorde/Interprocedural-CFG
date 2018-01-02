// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

import java.util.Collection;
import java.util.Set;
import java.util.Map;

public class SyncMap implements Map
{
    protected final Map c_;
    protected final Sync rd_;
    protected final Sync wr_;
    protected final SynchronizedLong syncFailures_;
    private transient Set keySet_;
    private transient Set entrySet_;
    private transient Collection values_;
    
    public SyncMap(final Map map, final ReadWriteLock readWriteLock) {
        this(map, readWriteLock.readLock(), readWriteLock.writeLock());
    }
    
    public SyncMap(final Map map, final Sync sync) {
        this(map, sync, sync);
    }
    
    public SyncMap(final Map c_, final Sync rd_, final Sync wr_) {
        this.syncFailures_ = new SynchronizedLong(0L);
        this.keySet_ = null;
        this.entrySet_ = null;
        this.values_ = null;
        this.c_ = c_;
        this.rd_ = rd_;
        this.wr_ = wr_;
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
    
    public boolean containsKey(final Object o) {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.c_.containsKey(o);
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public boolean containsValue(final Object o) {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.c_.containsValue(o);
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public Set entrySet() {
        final boolean beforeRead = this.beforeRead();
        try {
            if (this.entrySet_ == null) {
                this.entrySet_ = new SyncSet(this.c_.entrySet(), this.rd_, this.wr_);
            }
            return this.entrySet_;
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public boolean equals(final Object o) {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.c_.equals(o);
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public Object get(final Object o) {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.c_.get(o);
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public int hashCode() {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.c_.hashCode();
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
    
    public Set keySet() {
        final boolean beforeRead = this.beforeRead();
        try {
            if (this.keySet_ == null) {
                this.keySet_ = new SyncSet(this.c_.keySet(), this.rd_, this.wr_);
            }
            return this.keySet_;
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public Object put(final Object o, final Object o2) {
        try {
            this.wr_.acquire();
            try {
                return this.c_.put(o, o2);
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
    
    public void putAll(final Map map) {
        try {
            this.wr_.acquire();
            try {
                this.c_.putAll(map);
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
    
    public Sync readerSync() {
        return this.rd_;
    }
    
    public Object remove(final Object o) {
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
    
    public Collection values() {
        final boolean beforeRead = this.beforeRead();
        try {
            if (this.values_ == null) {
                this.values_ = new SyncCollection(this.c_.values(), this.rd_, this.wr_);
            }
            return this.values_;
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public Sync writerSync() {
        return this.wr_;
    }
}
