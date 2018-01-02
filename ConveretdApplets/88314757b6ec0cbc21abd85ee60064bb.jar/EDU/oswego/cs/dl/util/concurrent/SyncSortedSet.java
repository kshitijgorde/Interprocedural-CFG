// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;

public class SyncSortedSet extends SyncSet implements SortedSet
{
    public SyncSortedSet(final SortedSet set, final ReadWriteLock readWriteLock) {
        super(set, readWriteLock.readLock(), readWriteLock.writeLock());
    }
    
    public SyncSortedSet(final SortedSet set, final Sync sync) {
        super(set, sync);
    }
    
    public SyncSortedSet(final SortedSet set, final Sync sync, final Sync sync2) {
        super(set, sync, sync2);
    }
    
    protected SortedSet baseSortedSet() {
        return (SortedSet)super.c_;
    }
    
    public Comparator comparator() {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.baseSortedSet().comparator();
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public Object first() {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.baseSortedSet().first();
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public SortedSet headSet(final Object o) {
        final boolean beforeRead = this.beforeRead();
        try {
            return new SyncSortedSet(this.baseSortedSet().headSet(o), super.rd_, super.wr_);
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public Object last() {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.baseSortedSet().last();
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public SortedSet subSet(final Object o, final Object o2) {
        final boolean beforeRead = this.beforeRead();
        try {
            return new SyncSortedSet(this.baseSortedSet().subSet(o, o2), super.rd_, super.wr_);
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public SortedSet tailSet(final Object o) {
        final boolean beforeRead = this.beforeRead();
        try {
            return new SyncSortedSet(this.baseSortedSet().tailSet(o), super.rd_, super.wr_);
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
}
