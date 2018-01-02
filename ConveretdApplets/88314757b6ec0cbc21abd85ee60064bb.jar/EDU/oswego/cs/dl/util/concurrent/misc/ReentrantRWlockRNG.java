// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.ReadWriteLock;
import EDU.oswego.cs.dl.util.concurrent.ReentrantWriterPreferenceReadWriteLock;

class ReentrantRWlockRNG extends RWLockRNG
{
    public ReentrantRWlockRNG() {
        super(new ReentrantWriterPreferenceReadWriteLock());
    }
    
    public void update() {
        try {
            this.acquireW();
            long current_;
            try {
                this.acquireR();
                current_ = super.current_;
                super.lock_.readLock().release();
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return;
            }
            this.set(this.compute(current_));
            super.lock_.writeLock().release();
        }
        catch (InterruptedException ex2) {
            Thread.currentThread().interrupt();
        }
    }
}
