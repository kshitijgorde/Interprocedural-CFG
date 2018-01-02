// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class LockedExecutor implements Executor
{
    protected final Sync mutex_;
    
    public LockedExecutor(final Sync mutex_) {
        this.mutex_ = mutex_;
    }
    
    public void execute(final Runnable runnable) throws InterruptedException {
        this.mutex_.acquire();
        try {
            runnable.run();
        }
        finally {
            this.mutex_.release();
        }
    }
}
