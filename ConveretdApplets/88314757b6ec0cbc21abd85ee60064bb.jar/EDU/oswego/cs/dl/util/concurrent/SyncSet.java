// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

import java.util.Collection;
import java.util.Set;

public class SyncSet extends SyncCollection implements Set
{
    public SyncSet(final Set set, final ReadWriteLock readWriteLock) {
        super(set, readWriteLock.readLock(), readWriteLock.writeLock());
    }
    
    public SyncSet(final Set set, final Sync sync) {
        super(set, sync);
    }
    
    public SyncSet(final Set set, final Sync sync, final Sync sync2) {
        super(set, sync, sync2);
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
    
    public int hashCode() {
        final boolean beforeRead = this.beforeRead();
        try {
            return super.c_.hashCode();
        }
        finally {
            this.afterRead(beforeRead);
        }
    }
}
