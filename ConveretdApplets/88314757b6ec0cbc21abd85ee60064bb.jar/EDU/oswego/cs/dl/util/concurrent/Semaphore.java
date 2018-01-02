// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class Semaphore implements Sync
{
    protected long permits_;
    
    public Semaphore(final long permits_) {
        this.permits_ = permits_;
    }
    
    public void acquire() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        synchronized (this) {
            try {
                while (this.permits_ <= 0L) {
                    this.wait();
                }
                --this.permits_;
            }
            catch (InterruptedException ex) {
                this.notify();
                throw ex;
            }
        }
    }
    
    public boolean attempt(final long n) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        synchronized (this) {
            if (this.permits_ > 0L) {
                --this.permits_;
                // monitorexit(this)
                return true;
            }
            if (n <= 0L) {
                // monitorexit(this)
                return false;
            }
            try {
                final long currentTimeMillis = System.currentTimeMillis();
                long n2 = n;
                do {
                    this.wait(n2);
                    if (this.permits_ > 0L) {
                        --this.permits_;
                        // monitorexit(this)
                        return true;
                    }
                    n2 = n - (System.currentTimeMillis() - currentTimeMillis);
                } while (n2 > 0L);
                return false;
            }
            catch (InterruptedException ex) {
                this.notify();
                throw ex;
            }
        }
    }
    
    public synchronized long permits() {
        return this.permits_;
    }
    
    public synchronized void release() {
        ++this.permits_;
        this.notify();
    }
    
    public synchronized void release(final long n) {
        if (n < 0L) {
            throw new IllegalArgumentException("Negative argument");
        }
        this.permits_ += n;
        for (long n2 = 0L; n2 < n; ++n2) {
            this.notify();
        }
    }
}
