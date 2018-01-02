// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public final class WaiterPreferenceSemaphore extends Semaphore
{
    protected long waits_;
    
    public WaiterPreferenceSemaphore(final long n) {
        super(n);
        this.waits_ = 0L;
    }
    
    public void acquire() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        synchronized (this) {
            if (super.permits_ > this.waits_) {
                --super.permits_;
                // monitorexit(this)
                return;
            }
            ++this.waits_;
            try {
                do {
                    this.wait();
                } while (super.permits_ <= 0L);
                --this.waits_;
                --super.permits_;
            }
            catch (InterruptedException ex) {
                --this.waits_;
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
            if (super.permits_ > this.waits_) {
                --super.permits_;
                // monitorexit(this)
                return true;
            }
            if (n <= 0L) {
                // monitorexit(this)
                return false;
            }
            ++this.waits_;
            final long currentTimeMillis = System.currentTimeMillis();
            long n2 = n;
            try {
                do {
                    this.wait(n2);
                    if (super.permits_ > 0L) {
                        --this.waits_;
                        --super.permits_;
                        // monitorexit(this)
                        return true;
                    }
                    n2 = n - (System.currentTimeMillis() - currentTimeMillis);
                } while (n2 > 0L);
                --this.waits_;
                return false;
            }
            catch (InterruptedException ex) {
                --this.waits_;
                this.notify();
                throw ex;
            }
        }
    }
    
    public synchronized void release() {
        ++super.permits_;
        this.notify();
    }
    
    public synchronized void release(final long n) {
        super.permits_ += n;
        for (long n2 = 0L; n2 < n; ++n2) {
            this.notify();
        }
    }
}
