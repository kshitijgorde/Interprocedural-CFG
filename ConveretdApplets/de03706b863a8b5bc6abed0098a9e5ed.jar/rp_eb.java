import java.util.concurrent.ThreadFactory;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ThreadPoolExecutor;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_eb extends ThreadPoolExecutor
{
    private final ReentrantLock a;
    private final Condition a;
    private boolean a;
    private final ReentrantLock b;
    
    rp_eb(final int n, final int n2, final long n3, final TimeUnit timeUnit, final BlockingQueue blockingQueue, final ThreadFactory threadFactory) {
        super(0, 10, n3, timeUnit, blockingQueue, threadFactory);
        this.a = new ReentrantLock();
        this.a = this.a.newCondition();
        this.a = false;
        this.b = new ReentrantLock();
    }
    
    public final void execute(final Runnable runnable) {
        this.b.lock();
        try {
            this.a.lock();
            try {
                this.a = true;
            }
            finally {
                this.a.unlock();
            }
            this.setCorePoolSize(10);
            final Runnable runnable2;
            super.execute(runnable2);
            this.setCorePoolSize(0);
            this.a.lock();
            try {
                ((Condition)(this.a = false)).signalAll();
            }
            finally {
                this.a.unlock();
            }
        }
        finally {
            this.b.unlock();
        }
    }
    
    protected final void afterExecute(final Runnable runnable, final Throwable t) {
        super.afterExecute(runnable, t);
        this.a.lock();
        try {
            while (this.a) {
                this.a.await();
            }
        }
        catch (InterruptedException ex) {}
        finally {
            this.a.unlock();
        }
    }
}
