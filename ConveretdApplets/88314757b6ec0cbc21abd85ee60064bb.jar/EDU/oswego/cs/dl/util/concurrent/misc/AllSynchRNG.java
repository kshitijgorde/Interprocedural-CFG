// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

class AllSynchRNG extends PublicSynchRNG
{
    protected synchronized long internalGet() {
        return super.current_;
    }
    
    protected synchronized void internalUpdate() {
        this.set(this.compute(this.internalGet()));
    }
    
    protected synchronized void set(final long current_) {
        super.current_ = current_;
    }
}
