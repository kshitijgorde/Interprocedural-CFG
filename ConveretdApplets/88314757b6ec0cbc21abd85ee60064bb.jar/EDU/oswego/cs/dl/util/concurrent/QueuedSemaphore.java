// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public abstract class QueuedSemaphore extends Semaphore
{
    protected final WaitQueue wq_;
    
    QueuedSemaphore(final WaitQueue wq_, final long n) {
        super(n);
        this.wq_ = wq_;
    }
    
    public void acquire() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        if (this.precheck()) {
            return;
        }
        new WaitQueue.WaitNode().doWait(this);
    }
    
    public boolean attempt(final long n) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        return this.precheck() || (n > 0L && new WaitQueue.WaitNode().doTimedWait(this, n));
    }
    
    protected synchronized WaitQueue.WaitNode getSignallee() {
        final WaitQueue.WaitNode extract = this.wq_.extract();
        if (extract == null) {
            ++super.permits_;
        }
        return extract;
    }
    
    protected synchronized boolean precheck() {
        final boolean b = super.permits_ > 0L;
        if (b) {
            --super.permits_;
        }
        return b;
    }
    
    protected synchronized boolean recheck(final WaitQueue.WaitNode waitNode) {
        final boolean b = super.permits_ > 0L;
        if (b) {
            --super.permits_;
        }
        else {
            this.wq_.insert(waitNode);
        }
        return b;
    }
    
    public void release() {
        WaitQueue.WaitNode signallee;
        do {
            signallee = this.getSignallee();
            if (signallee == null) {
                return;
            }
        } while (!signallee.signal());
    }
    
    public void release(final long n) {
        if (n < 0L) {
            throw new IllegalArgumentException("Negative argument");
        }
        for (long n2 = 0L; n2 < n; ++n2) {
            this.release();
        }
    }
    
    protected abstract static class WaitQueue
    {
        protected abstract WaitNode extract();
        
        protected abstract void insert(final WaitNode p0);
        
        protected static class WaitNode
        {
            boolean waiting;
            WaitNode next;
            
            protected WaitNode() {
                this.waiting = true;
                this.next = null;
            }
            
            protected synchronized boolean doTimedWait(final QueuedSemaphore queuedSemaphore, final long n) throws InterruptedException {
                if (queuedSemaphore.recheck(this) || !this.waiting) {
                    return true;
                }
                if (n <= 0L) {
                    return this.waiting = false;
                }
                long n2 = n;
                final long currentTimeMillis = System.currentTimeMillis();
                try {
                    do {
                        this.wait(n2);
                        if (!this.waiting) {
                            return true;
                        }
                        n2 = n - (System.currentTimeMillis() - currentTimeMillis);
                    } while (n2 > 0L);
                    return this.waiting = false;
                }
                catch (InterruptedException ex) {
                    if (this.waiting) {
                        this.waiting = false;
                        throw ex;
                    }
                    Thread.currentThread().interrupt();
                    return true;
                }
            }
            
            protected synchronized void doWait(final QueuedSemaphore queuedSemaphore) throws InterruptedException {
                if (!queuedSemaphore.recheck(this)) {
                    try {
                        while (this.waiting) {
                            this.wait();
                        }
                    }
                    catch (InterruptedException ex) {
                        if (this.waiting) {
                            this.waiting = false;
                            throw ex;
                        }
                        Thread.currentThread().interrupt();
                    }
                }
            }
            
            protected synchronized boolean signal() {
                final boolean waiting = this.waiting;
                if (waiting) {
                    this.waiting = false;
                    this.notify();
                }
                return waiting;
            }
        }
    }
}
