// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class SynchronizedShort extends SynchronizedVariable implements Comparable, Cloneable
{
    protected short value_;
    
    public SynchronizedShort(final short value_) {
        this.value_ = value_;
    }
    
    public SynchronizedShort(final short value_, final Object o) {
        super(o);
        this.value_ = value_;
    }
    
    public short add(final short n) {
        synchronized (super.lock_) {
            final short value_ = (short)(this.value_ + n);
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public short and(final short n) {
        synchronized (super.lock_) {
            this.value_ &= n;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public boolean commit(final short n, final short value_) {
        synchronized (super.lock_) {
            final boolean b = n == this.value_;
            if (b) {
                this.value_ = value_;
            }
            // monitorexit(super.lock_)
            return b;
        }
    }
    
    public int compareTo(final SynchronizedShort synchronizedShort) {
        return this.compareTo(synchronizedShort.get());
    }
    
    public int compareTo(final Object o) {
        return this.compareTo((SynchronizedShort)o);
    }
    
    public int compareTo(final short n) {
        final short value = this.get();
        return (value < n) ? -1 : ((value == n) ? false : true);
    }
    
    public short complement() {
        synchronized (super.lock_) {
            this.value_ ^= -1;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public short decrement() {
        synchronized (super.lock_) {
            final short value_ = (short)(this.value_ - 1);
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public short divide(final short n) {
        synchronized (super.lock_) {
            final short value_ = (short)(this.value_ / n);
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public boolean equals(final Object o) {
        return o != null && o instanceof SynchronizedShort && this.get() == ((SynchronizedShort)o).get();
    }
    
    public final short get() {
        synchronized (super.lock_) {
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public int hashCode() {
        return this.get();
    }
    
    public short increment() {
        synchronized (super.lock_) {
            final short value_ = (short)(this.value_ + 1);
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public short multiply(final short n) {
        synchronized (super.lock_) {
            final short value_ = (short)(this.value_ * n);
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public short negate() {
        synchronized (super.lock_) {
            this.value_ = (short)(-this.value_);
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public short or(final short n) {
        synchronized (super.lock_) {
            this.value_ |= n;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public short set(final short value_) {
        synchronized (super.lock_) {
            final short value_2 = this.value_;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_2;
        }
    }
    
    public short subtract(final short n) {
        synchronized (super.lock_) {
            final short value_ = (short)(this.value_ - n);
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public short swap(final SynchronizedShort synchronizedShort) {
        if (synchronizedShort == this) {
            return this.get();
        }
        SynchronizedShort synchronizedShort2 = this;
        SynchronizedShort synchronizedShort3 = synchronizedShort;
        if (System.identityHashCode(synchronizedShort2) > System.identityHashCode(synchronizedShort3)) {
            synchronizedShort2 = synchronizedShort;
            synchronizedShort3 = this;
        }
        synchronized (synchronizedShort2.lock_) {
            final Object lock_ = synchronizedShort3.lock_;
            // monitorenter(lock_)
            try {
                synchronizedShort2.set(synchronizedShort3.set(synchronizedShort2.get()));
                // monitorexit(lock_)
                // monitorexit(synchronizedShort2.lock_)
                return this.get();
            }
            finally {}
        }
    }
    
    public String toString() {
        return String.valueOf(this.get());
    }
    
    public short xor(final short n) {
        synchronized (super.lock_) {
            this.value_ ^= n;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
}
