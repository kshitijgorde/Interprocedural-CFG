// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class Latch implements Sync
{
    protected boolean latched_;
    
    public Latch() {
        this.latched_ = false;
    }
    
    public void acquire() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        synchronized (this) {
            while (!this.latched_) {
                this.wait();
            }
        }
    }
    
    public boolean attempt(final long n) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        synchronized (this) {
            if (this.latched_) {
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
                if (this.latched_) {
                    // monitorexit(this)
                    return true;
                }
                n2 = n - (System.currentTimeMillis() - currentTimeMillis);
            } while (n2 > 0L);
            return false;
        }
    }
    
    public synchronized void release() {
        this.latched_ = true;
        this.notifyAll();
    }
}
