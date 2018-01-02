// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class SynchronizedLong extends SynchronizedVariable implements Comparable, Cloneable
{
    protected long value_;
    
    public SynchronizedLong(final long value_) {
        this.value_ = value_;
    }
    
    public SynchronizedLong(final long value_, final Object o) {
        super(o);
        this.value_ = value_;
    }
    
    public long add(final long n) {
        synchronized (super.lock_) {
            final long value_ = this.value_ + n;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public long and(final long n) {
        synchronized (super.lock_) {
            this.value_ &= n;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public boolean commit(final long n, final long value_) {
        synchronized (super.lock_) {
            final boolean b = n == this.value_;
            if (b) {
                this.value_ = value_;
            }
            // monitorexit(super.lock_)
            return b;
        }
    }
    
    public int compareTo(final long n) {
        final long value = this.get();
        return (value < n) ? -1 : ((value == n) ? false : true);
    }
    
    public int compareTo(final SynchronizedLong synchronizedLong) {
        return this.compareTo(synchronizedLong.get());
    }
    
    public int compareTo(final Object o) {
        return this.compareTo((SynchronizedLong)o);
    }
    
    public long complement() {
        synchronized (super.lock_) {
            this.value_ ^= -1L;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public long decrement() {
        synchronized (super.lock_) {
            final long value_ = this.value_ - 1L;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public long divide(final long n) {
        synchronized (super.lock_) {
            final long value_ = this.value_ / n;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public boolean equals(final Object o) {
        return o != null && o instanceof SynchronizedLong && this.get() == ((SynchronizedLong)o).get();
    }
    
    public final long get() {
        synchronized (super.lock_) {
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public int hashCode() {
        final long value = this.get();
        return (int)(value ^ value >> 32);
    }
    
    public long increment() {
        synchronized (super.lock_) {
            final long value_ = this.value_ + 1L;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public long multiply(final long n) {
        synchronized (super.lock_) {
            final long value_ = this.value_ * n;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public long negate() {
        synchronized (super.lock_) {
            this.value_ = -this.value_;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public long or(final long n) {
        synchronized (super.lock_) {
            this.value_ |= n;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public long set(final long value_) {
        synchronized (super.lock_) {
            final long value_2 = this.value_;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_2;
        }
    }
    
    public long subtract(final long n) {
        synchronized (super.lock_) {
            final long value_ = this.value_ - n;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public long swap(final SynchronizedLong synchronizedLong) {
        if (synchronizedLong == this) {
            return this.get();
        }
        SynchronizedLong synchronizedLong2 = this;
        SynchronizedLong synchronizedLong3 = synchronizedLong;
        if (System.identityHashCode(synchronizedLong2) > System.identityHashCode(synchronizedLong3)) {
            synchronizedLong2 = synchronizedLong;
            synchronizedLong3 = this;
        }
        synchronized (synchronizedLong2.lock_) {
            final Object lock_ = synchronizedLong3.lock_;
            // monitorenter(lock_)
            try {
                synchronizedLong2.set(synchronizedLong3.set(synchronizedLong2.get()));
                // monitorexit(lock_)
                // monitorexit(synchronizedLong2.lock_)
                return this.get();
            }
            finally {}
        }
    }
    
    public String toString() {
        return String.valueOf(this.get());
    }
    
    public long xor(final long n) {
        synchronized (super.lock_) {
            this.value_ ^= n;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
}
