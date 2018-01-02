// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class CyclicBarrier implements Barrier
{
    protected final int parties_;
    protected boolean broken_;
    protected Runnable barrierCommand_;
    protected int count_;
    protected int resets_;
    
    public CyclicBarrier(final int n) {
        this(n, null);
    }
    
    public CyclicBarrier(final int n, final Runnable barrierCommand_) {
        this.broken_ = false;
        this.barrierCommand_ = null;
        this.resets_ = 0;
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.parties_ = n;
        this.count_ = n;
        this.barrierCommand_ = barrierCommand_;
    }
    
    public int attemptBarrier(final long n) throws InterruptedException, TimeoutException, BrokenBarrierException {
        return this.doBarrier(true, n);
    }
    
    public int barrier() throws InterruptedException, BrokenBarrierException {
        return this.doBarrier(false, 0L);
    }
    
    public synchronized boolean broken() {
        return this.broken_;
    }
    
    protected synchronized int doBarrier(final boolean b, final long n) throws InterruptedException, TimeoutException, BrokenBarrierException {
        final int count_ = this.count_ - 1;
        this.count_ = count_;
        final int n2 = count_;
        if (this.broken_) {
            throw new BrokenBarrierException(n2);
        }
        if (Thread.interrupted()) {
            this.broken_ = true;
            this.notifyAll();
            throw new InterruptedException();
        }
        if (n2 == 0) {
            this.count_ = this.parties_;
            ++this.resets_;
            this.notifyAll();
            try {
                if (this.barrierCommand_ != null) {
                    this.barrierCommand_.run();
                }
                return 0;
            }
            catch (RuntimeException ex2) {
                this.broken_ = true;
                return 0;
            }
        }
        if (b && n <= 0L) {
            this.broken_ = true;
            this.notifyAll();
            throw new TimeoutException(n);
        }
        final int resets_ = this.resets_;
        final long n3 = b ? System.currentTimeMillis() : 0L;
        long n4 = n;
        while (true) {
            try {
                this.wait(n4);
            }
            catch (InterruptedException ex) {
                if (this.resets_ == resets_) {
                    this.broken_ = true;
                    this.notifyAll();
                    throw ex;
                }
                Thread.currentThread().interrupt();
            }
            if (this.broken_) {
                throw new BrokenBarrierException(n2);
            }
            if (resets_ != this.resets_) {
                return n2;
            }
            if (!b) {
                continue;
            }
            n4 = n - (System.currentTimeMillis() - n3);
            if (n4 <= 0L) {
                this.broken_ = true;
                this.notifyAll();
                throw new TimeoutException(n);
            }
        }
    }
    
    public int parties() {
        return this.parties_;
    }
    
    public synchronized void restart() {
        this.broken_ = false;
        ++this.resets_;
        this.count_ = this.parties_;
        this.notifyAll();
    }
    
    public synchronized Runnable setBarrierCommand(final Runnable barrierCommand_) {
        final Runnable barrierCommand_2 = this.barrierCommand_;
        this.barrierCommand_ = barrierCommand_;
        return barrierCommand_2;
    }
}
