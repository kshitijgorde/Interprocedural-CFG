// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

abstract class DelegatedRNG extends RNG
{
    protected RNG delegate_;
    
    DelegatedRNG() {
        this.delegate_ = null;
    }
    
    public long get() {
        return this.getDelegate().get();
    }
    
    protected synchronized RNG getDelegate() {
        return this.delegate_;
    }
    
    protected long internalGet() {
        throw new Error();
    }
    
    protected void internalUpdate() {
        throw new Error();
    }
    
    public long next() {
        return this.getDelegate().next();
    }
    
    protected void set(final long n) {
        throw new Error();
    }
    
    public synchronized void setDelegate(final RNG delegate_) {
        this.delegate_ = delegate_;
    }
    
    public void update() {
        this.getDelegate().update();
    }
}
