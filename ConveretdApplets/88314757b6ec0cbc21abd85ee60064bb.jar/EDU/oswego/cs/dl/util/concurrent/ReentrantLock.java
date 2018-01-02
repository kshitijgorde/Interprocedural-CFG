// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class ReentrantLock implements Sync
{
    protected Thread owner_;
    protected long holds_;
    
    public ReentrantLock() {
        this.owner_ = null;
        this.holds_ = 0L;
    }
    
    public void acquire() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        final Thread currentThread = Thread.currentThread();
        synchronized (this) {
            if (currentThread == this.owner_) {
                ++this.holds_;
            }
            else {
                try {
                    while (this.owner_ != null) {
                        this.wait();
                    }
                    this.owner_ = currentThread;
                    this.holds_ = 1L;
                }
                catch (InterruptedException ex) {
                    this.notify();
                    throw ex;
                }
            }
        }
    }
    
    public boolean attempt(final long n) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        final Thread currentThread = Thread.currentThread();
        synchronized (this) {
            if (currentThread == this.owner_) {
                ++this.holds_;
                // monitorexit(this)
                return true;
            }
            if (this.owner_ == null) {
                this.owner_ = currentThread;
                this.holds_ = 1L;
                // monitorexit(this)
                return true;
            }
            if (n <= 0L) {
                // monitorexit(this)
                return false;
            }
            long n2 = n;
            final long currentTimeMillis = System.currentTimeMillis();
            try {
                do {
                    this.wait(n2);
                    if (currentThread == this.owner_) {
                        ++this.holds_;
                        // monitorexit(this)
                        return true;
                    }
                    if (this.owner_ == null) {
                        this.owner_ = currentThread;
                        this.holds_ = 1L;
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
    
    public synchronized long holds() {
        if (Thread.currentThread() != this.owner_) {
            return 0L;
        }
        return this.holds_;
    }
    
    public synchronized void release() {
        if (Thread.currentThread() != this.owner_) {
            throw new Error("Illegal Lock usage");
        }
        final long holds_ = this.holds_ - 1L;
        this.holds_ = holds_;
        if (holds_ == 0L) {
            this.owner_ = null;
            this.notify();
        }
    }
    
    public synchronized void release(final long n) {
        if (Thread.currentThread() != this.owner_ || n > this.holds_) {
            throw new Error("Illegal Lock usage");
        }
        this.holds_ -= n;
        if (this.holds_ == 0L) {
            this.owner_ = null;
            this.notify();
        }
    }
}
