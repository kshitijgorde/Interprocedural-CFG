// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class CountDown implements Sync
{
    protected final int initialCount_;
    protected int count_;
    
    public CountDown(final int n) {
        this.initialCount_ = n;
        this.count_ = n;
    }
    
    public void acquire() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        synchronized (this) {
            while (this.count_ > 0) {
                this.wait();
            }
        }
    }
    
    public boolean attempt(final long n) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        synchronized (this) {
            if (this.count_ <= 0) {
                // monitorexit(this)
                return true;
            }
            if (n <= 0L) {
                // monitorexit(this)
                return false;
            }
            long n2 = n;
            final long currentTimeMillis = System.currentTimeMillis();
            do {
                this.wait(n2);
                if (this.count_ <= 0) {
                    // monitorexit(this)
                    return true;
                }
                n2 = n - (System.currentTimeMillis() - currentTimeMillis);
            } while (n2 > 0L);
            return false;
        }
    }
    
    public synchronized int currentCount() {
        return this.count_;
    }
    
    public int initialCount() {
        return this.initialCount_;
    }
    
    public synchronized void release() {
        final int count_ = this.count_ - 1;
        this.count_ = count_;
        if (count_ == 0) {
            this.notifyAll();
        }
    }
}
