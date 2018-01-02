// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class TimeoutSync implements Sync
{
    protected final Sync sync_;
    protected final long timeout_;
    
    public TimeoutSync(final Sync sync_, final long timeout_) {
        this.sync_ = sync_;
        this.timeout_ = timeout_;
    }
    
    public void acquire() throws InterruptedException {
        if (!this.sync_.attempt(this.timeout_)) {
            throw new TimeoutException(this.timeout_);
        }
    }
    
    public boolean attempt(final long n) throws InterruptedException {
        return this.sync_.attempt(n);
    }
    
    public void release() {
        this.sync_.release();
    }
}
