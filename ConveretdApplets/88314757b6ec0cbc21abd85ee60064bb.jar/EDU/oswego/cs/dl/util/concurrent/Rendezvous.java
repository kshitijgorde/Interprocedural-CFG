// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class Rendezvous implements Barrier
{
    protected final int parties_;
    protected boolean broken_;
    protected int entries_;
    protected long departures_;
    protected final Semaphore entryGate_;
    protected final Object[] slots_;
    protected RendezvousFunction rendezvousFunction_;
    
    public Rendezvous(final int n) {
        this(n, (RendezvousFunction)new Rotator());
    }
    
    public Rendezvous(final int parties_, final RendezvousFunction rendezvousFunction_) {
        this.broken_ = false;
        this.entries_ = 0;
        this.departures_ = 0L;
        if (parties_ <= 0) {
            throw new IllegalArgumentException();
        }
        this.parties_ = parties_;
        this.rendezvousFunction_ = rendezvousFunction_;
        this.entryGate_ = new WaiterPreferenceSemaphore(parties_);
        this.slots_ = new Object[parties_];
    }
    
    public Object attemptRendezvous(final Object o, final long n) throws InterruptedException, TimeoutException, BrokenBarrierException {
        return this.doRendezvous(o, true, n);
    }
    
    public synchronized boolean broken() {
        return this.broken_;
    }
    
    protected Object doRendezvous(final Object o, final boolean b, final long n) throws InterruptedException, TimeoutException, BrokenBarrierException {
        long currentTimeMillis;
        if (b) {
            currentTimeMillis = System.currentTimeMillis();
            if (!this.entryGate_.attempt(n)) {
                throw new TimeoutException(n);
            }
        }
        else {
            currentTimeMillis = 0L;
            this.entryGate_.acquire();
        }
        synchronized (this) {
            final int n2 = this.entries_++;
            this.slots_[n2] = o;
            Object o2 = null;
            try {
                if (this.entries_ == this.parties_) {
                    this.departures_ = this.entries_;
                    this.notifyAll();
                    try {
                        if (!this.broken_ && this.rendezvousFunction_ != null) {
                            this.rendezvousFunction_.rendezvousFunction(this.slots_);
                        }
                    }
                    catch (RuntimeException ex2) {
                        this.broken_ = true;
                    }
                }
                else {
                    while (!this.broken_ && this.departures_ < 1L) {
                        long n3 = 0L;
                        if (b) {
                            n3 = n - (System.currentTimeMillis() - currentTimeMillis);
                            if (n3 <= 0L) {
                                this.broken_ = true;
                                this.departures_ = this.entries_;
                                this.notifyAll();
                                throw new TimeoutException(n);
                            }
                        }
                        try {
                            this.wait(n3);
                        }
                        catch (InterruptedException ex) {
                            if (this.broken_ || this.departures_ > 0L) {
                                Thread.currentThread().interrupt();
                                break;
                            }
                            this.broken_ = true;
                            this.departures_ = this.entries_;
                            this.notifyAll();
                            throw ex;
                        }
                    }
                }
            }
            finally {
                o2 = this.slots_[n2];
                final long departures_ = this.departures_ - 1L;
                this.departures_ = departures_;
                if (departures_ <= 0L) {
                    for (int i = 0; i < this.slots_.length; ++i) {
                        this.slots_[i] = null;
                    }
                    this.entryGate_.release(this.entries_);
                    this.entries_ = 0;
                }
            }
            if (this.broken_) {
                throw new BrokenBarrierException(n2);
            }
            return o2;
        }
    }
    
    public int parties() {
        return this.parties_;
    }
    
    public Object rendezvous(final Object o) throws InterruptedException, BrokenBarrierException {
        return this.doRendezvous(o, false, 0L);
    }
    
    public void restart() {
        while (true) {
            synchronized (this) {
                if (this.entries_ == 0) {
                    this.broken_ = false;
                    // monitorexit(this)
                    return;
                }
                this.notifyAll();
            }
            Thread.yield();
        }
    }
    
    public synchronized RendezvousFunction setRendezvousFunction(final RendezvousFunction rendezvousFunction_) {
        final RendezvousFunction rendezvousFunction_2 = this.rendezvousFunction_;
        this.rendezvousFunction_ = rendezvousFunction_;
        return rendezvousFunction_2;
    }
    
    public interface RendezvousFunction
    {
        void rendezvousFunction(final Object[] p0);
    }
    
    public static class Rotator implements RendezvousFunction
    {
        public void rendezvousFunction(final Object[] array) {
            final int n = array.length - 1;
            final Object o = array[0];
            for (int i = 0; i < n; ++i) {
                array[i] = array[i + 1];
            }
            array[n] = o;
        }
    }
}
