// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class Mutex implements Sync
{
    protected boolean inuse_;
    
    public Mutex() {
        this.inuse_ = false;
    }
    
    public void acquire() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        synchronized (this) {
            try {
                while (this.inuse_) {
                    this.wait();
                }
                this.inuse_ = true;
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
            if (!this.inuse_) {
                this.inuse_ = true;
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
                    if (!this.inuse_) {
                        this.inuse_ = true;
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
    
    public synchronized void release() {
        this.inuse_ = false;
        this.notify();
    }
}
