// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

class NoSynchRNG extends RNG
{
    protected long current_;
    
    NoSynchRNG() {
        this.current_ = RNG.nextSeed();
    }
    
    protected long internalGet() {
        return this.current_;
    }
    
    protected void internalUpdate() {
        this.set(this.compute(this.internalGet()));
    }
    
    protected void set(final long current_) {
        this.current_ = current_;
    }
}
