// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;

public class SyncSortedMap extends SyncMap implements SortedMap
{
    public SyncSortedMap(final SortedMap sortedMap, final ReadWriteLock readWriteLock) {
        super(sortedMap, readWriteLock.readLock(), readWriteLock.writeLock());
    }
    
    public SyncSortedMap(final SortedMap sortedMap, final Sync sync) {
        this(sortedMap, sync, sync);
    }
    
    public SyncSortedMap(final SortedMap sortedMap, final Sync sync, final Sync sync2) {
        super(sortedMap, sync, sync2);
    }
    
    protected SortedMap baseSortedMap() {
        return (SortedMap)super.c_;
    }
    
    public Comparator comparator() {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.baseSortedMap().comparator();
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public Object firstKey() {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.baseSortedMap().firstKey();
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public SortedMap headMap(final Object o) {
        final boolean beforeRead = this.beforeRead();
        try {
            return new SyncSortedMap(this.baseSortedMap().headMap(o), super.rd_, super.wr_);
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public Object lastKey() {
        final boolean beforeRead = this.beforeRead();
        try {
            return this.baseSortedMap().lastKey();
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public SortedMap subMap(final Object o, final Object o2) {
        final boolean beforeRead = this.beforeRead();
        try {
            return new SyncSortedMap(this.baseSortedMap().subMap(o, o2), super.rd_, super.wr_);
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
    
    public SortedMap tailMap(final Object o) {
        final boolean beforeRead = this.beforeRead();
        try {
            return new SyncSortedMap(this.baseSortedMap().tailMap(o), super.rd_, super.wr_);
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
}
