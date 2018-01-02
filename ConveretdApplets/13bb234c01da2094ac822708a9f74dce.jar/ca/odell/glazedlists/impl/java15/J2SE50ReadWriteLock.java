// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.java15;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import ca.odell.glazedlists.util.concurrent.Lock;
import java.io.Serializable;
import ca.odell.glazedlists.util.concurrent.ReadWriteLock;

final class J2SE50ReadWriteLock implements ReadWriteLock, Serializable
{
    private final transient Lock a;
    private final transient Lock b;
    
    J2SE50ReadWriteLock() {
        final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.a = new LockAdapter(reentrantReadWriteLock.readLock());
        this.b = new LockAdapter(reentrantReadWriteLock.writeLock());
    }
    
    public Lock b() {
        return this.a;
    }
    
    public Lock a() {
        return this.b;
    }
}
