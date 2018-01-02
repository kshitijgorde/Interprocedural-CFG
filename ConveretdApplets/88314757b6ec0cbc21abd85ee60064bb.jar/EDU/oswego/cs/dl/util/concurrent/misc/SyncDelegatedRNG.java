// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.Sync;

class SyncDelegatedRNG extends DelegatedRNG
{
    protected final Sync cond_;
    
    public SyncDelegatedRNG(final Sync cond_) {
        this.cond_ = cond_;
        this.setDelegate(new NoSynchRNG());
    }
    
    protected final void acquire() throws InterruptedException {
        if (super.smode == 0) {
            this.cond_.acquire();
        }
        else {
            while (!this.cond_.attempt(super.waitTime)) {}
        }
    }
    
    public long get() {
        try {
            this.acquire();
            final long value = this.getDelegate().get();
            this.cond_.release();
            return value;
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return 0L;
        }
    }
    
    public long next() {
        try {
            this.acquire();
            this.getDelegate().update();
            final long value = this.getDelegate().get();
            this.cond_.release();
            return value;
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return 0L;
        }
    }
    
    public void update() {
        try {
            this.acquire();
            this.getDelegate().update();
            this.cond_.release();
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
