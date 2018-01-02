// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.ReadWriteLock;

class RWLockRNG extends NoSynchRNG
{
    protected final ReadWriteLock lock_;
    
    public RWLockRNG(final ReadWriteLock lock_) {
        this.lock_ = lock_;
    }
    
    protected final void acquireR() throws InterruptedException {
        if (super.smode == 0) {
            this.lock_.readLock().acquire();
        }
        else {
            while (!this.lock_.readLock().attempt(super.waitTime)) {}
        }
    }
    
    protected final void acquireW() throws InterruptedException {
        if (super.smode == 0) {
            this.lock_.writeLock().acquire();
        }
        else {
            while (!this.lock_.writeLock().attempt(super.waitTime)) {}
        }
    }
    
    public long get() {
        try {
            this.acquireR();
            final long current_ = super.current_;
            this.lock_.readLock().release();
            return current_;
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return 0L;
        }
    }
    
    public long next() {
        long current_;
        try {
            this.acquireR();
            current_ = super.current_;
            this.lock_.readLock().release();
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return 0L;
        }
        final long compute = this.compute(current_);
        try {
            this.acquireW();
            this.set(compute);
            this.lock_.writeLock().release();
            return compute;
        }
        catch (InterruptedException ex2) {
            Thread.currentThread().interrupt();
            return 0L;
        }
    }
    
    public void update() {
        long current_;
        try {
            this.acquireR();
            current_ = super.current_;
            this.lock_.readLock().release();
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return;
        }
        final long compute = this.compute(current_);
        try {
            this.acquireW();
            this.set(compute);
            this.lock_.writeLock().release();
        }
        catch (InterruptedException ex2) {
            Thread.currentThread().interrupt();
        }
    }
}
