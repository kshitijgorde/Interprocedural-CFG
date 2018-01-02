// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.SynchronizedLong;

class AClongRNG extends RNG
{
    protected final SynchronizedLong acurrent_;
    
    AClongRNG() {
        this.acurrent_ = new SynchronizedLong(RNG.nextSeed());
    }
    
    protected long internalGet() {
        return this.acurrent_.get();
    }
    
    protected void internalUpdate() {
        final int n = 100;
        final int n2 = 100;
        int n3 = 0;
        while (true) {
            final long internalGet = this.internalGet();
            final long compute = this.compute(internalGet);
            if (this.acurrent_.commit(internalGet, compute)) {
                break;
            }
            if (++n3 < n) {
                continue;
            }
            try {
                Thread.sleep(compute % n2);
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            n3 = 0;
        }
    }
    
    protected void set(final long n) {
        throw new Error("No set allowed");
    }
}
