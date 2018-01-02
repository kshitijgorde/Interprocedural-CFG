// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class SynchronizedVariable implements Executor
{
    protected final Object lock_;
    
    public SynchronizedVariable() {
        this.lock_ = this;
    }
    
    public SynchronizedVariable(final Object lock_) {
        this.lock_ = lock_;
    }
    
    public void execute(final Runnable runnable) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        synchronized (this.lock_) {
            runnable.run();
        }
        // monitorexit(this.lock_)
    }
    
    public Object getLock() {
        return this.lock_;
    }
}
