// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class BoundedBuffer implements BoundedChannel
{
    protected final Object[] array_;
    protected int takePtr_;
    protected int putPtr_;
    protected int usedSlots_;
    protected int emptySlots_;
    protected final Object putMonitor_;
    
    public BoundedBuffer() {
        this(DefaultChannelCapacity.get());
    }
    
    public BoundedBuffer(final int emptySlots_) throws IllegalArgumentException {
        this.takePtr_ = 0;
        this.putPtr_ = 0;
        this.usedSlots_ = 0;
        this.putMonitor_ = new Object();
        if (emptySlots_ <= 0) {
            throw new IllegalArgumentException();
        }
        this.array_ = new Object[emptySlots_];
        this.emptySlots_ = emptySlots_;
    }
    
    public int capacity() {
        return this.array_.length;
    }
    
    protected final Object extract() {
        --this.usedSlots_;
        final Object o = this.array_[this.takePtr_];
        this.array_[this.takePtr_] = null;
        if (++this.takePtr_ >= this.array_.length) {
            this.takePtr_ = 0;
        }
        return o;
    }
    
    protected void incEmptySlots() {
        synchronized (this.putMonitor_) {
            ++this.emptySlots_;
            this.putMonitor_.notify();
        }
        // monitorexit(this.putMonitor_)
    }
    
    protected synchronized void incUsedSlots() {
        ++this.usedSlots_;
        this.notify();
    }
    
    protected final void insert(final Object o) {
        --this.emptySlots_;
        this.array_[this.putPtr_] = o;
        if (++this.putPtr_ >= this.array_.length) {
            this.putPtr_ = 0;
        }
    }
    
    public boolean offer(final Object o, final long n) throws InterruptedException {
        if (o == null) {
            throw new IllegalArgumentException();
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        synchronized (this.putMonitor_) {
            final long n2 = (n <= 0L) ? 0L : System.currentTimeMillis();
            long n3 = n;
            while (this.emptySlots_ <= 0) {
                if (n3 <= 0L) {
                    // monitorexit(this.putMonitor_)
                    return false;
                }
                try {
                    this.putMonitor_.wait(n3);
                }
                catch (InterruptedException ex) {
                    this.putMonitor_.notify();
                    throw ex;
                }
                n3 = n - (System.currentTimeMillis() - n2);
            }
            this.insert(o);
        }
        // monitorexit(this.putMonitor_)
        this.incUsedSlots();
        return true;
    }
    
    public Object peek() {
        synchronized (this) {
            if (this.usedSlots_ > 0) {
                // monitorexit(this)
                return this.array_[this.takePtr_];
            }
            return null;
        }
    }
    
    public Object poll(final long n) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object extract = null;
        synchronized (this) {
            final long n2 = (n <= 0L) ? 0L : System.currentTimeMillis();
            long n3 = n;
            while (this.usedSlots_ <= 0) {
                if (n3 <= 0L) {
                    // monitorexit(this)
                    return null;
                }
                try {
                    this.wait(n3);
                }
                catch (InterruptedException ex) {
                    this.notify();
                    throw ex;
                }
                n3 = n - (System.currentTimeMillis() - n2);
            }
            extract = this.extract();
        }
        this.incEmptySlots();
        return extract;
    }
    
    public void put(final Object o) throws InterruptedException {
        if (o == null) {
            throw new IllegalArgumentException();
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        synchronized (this.putMonitor_) {
            while (this.emptySlots_ <= 0) {
                try {
                    this.putMonitor_.wait();
                }
                catch (InterruptedException ex) {
                    this.putMonitor_.notify();
                    throw ex;
                }
            }
            this.insert(o);
        }
        // monitorexit(this.putMonitor_)
        this.incUsedSlots();
    }
    
    public synchronized int size() {
        return this.usedSlots_;
    }
    
    public Object take() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object extract = null;
        synchronized (this) {
            while (this.usedSlots_ <= 0) {
                try {
                    this.wait();
                }
                catch (InterruptedException ex) {
                    this.notify();
                    throw ex;
                }
            }
            extract = this.extract();
        }
        this.incEmptySlots();
        return extract;
    }
}
