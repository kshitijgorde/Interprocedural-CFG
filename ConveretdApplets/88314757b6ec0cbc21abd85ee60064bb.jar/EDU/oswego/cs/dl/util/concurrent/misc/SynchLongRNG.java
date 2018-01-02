// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.SynchronizedLong;

class SynchLongRNG extends RNG
{
    protected final SynchronizedLong acurrent_;
    
    SynchLongRNG() {
        this.acurrent_ = new SynchronizedLong(RNG.nextSeed());
    }
    
    protected long internalGet() {
        return this.acurrent_.get();
    }
    
    protected void internalUpdate() {
        this.set(this.compute(this.internalGet()));
    }
    
    protected void set(final long n) {
        this.acurrent_.set(n);
    }
}
