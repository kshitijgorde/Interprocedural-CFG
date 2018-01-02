// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class CondVar
{
    protected final Sync mutex_;
    
    public CondVar(final Sync mutex_) {
        this.mutex_ = mutex_;
    }
    
    public void await() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        try {
            synchronized (this) {
                this.mutex_.release();
                try {
                    this.wait();
                }
                catch (InterruptedException ex) {
                    this.notify();
                    throw ex;
                }
            }
        }
        finally {
            boolean b = false;
            while (true) {
                try {
                    this.mutex_.acquire();
                }
                catch (InterruptedException ex2) {
                    b = true;
                    continue;
                }
                break;
            }
            if (b) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    public synchronized void broadcast() {
        this.notifyAll();
    }
    
    public synchronized void signal() {
        this.notify();
    }
    
    public boolean timedwait(final long n) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        boolean b = false;
        try {
            synchronized (this) {
                this.mutex_.release();
                try {
                    if (n > 0L) {
                        final long currentTimeMillis = System.currentTimeMillis();
                        this.wait(n);
                        b = (System.currentTimeMillis() - currentTimeMillis <= n);
                    }
                }
                catch (InterruptedException ex) {
                    this.notify();
                    throw ex;
                }
            }
        }
        finally {
            boolean b2 = false;
            while (true) {
                try {
                    this.mutex_.acquire();
                }
                catch (InterruptedException ex2) {
                    b2 = true;
                    continue;
                }
                break;
            }
            if (b2) {
                Thread.currentThread().interrupt();
            }
        }
        return b;
    }
}
