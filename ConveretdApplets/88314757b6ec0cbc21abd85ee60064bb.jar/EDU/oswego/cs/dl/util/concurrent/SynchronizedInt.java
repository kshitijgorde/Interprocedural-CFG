// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class SynchronizedInt extends SynchronizedVariable implements Comparable, Cloneable
{
    protected int value_;
    
    public SynchronizedInt(final int value_) {
        this.value_ = value_;
    }
    
    public SynchronizedInt(final int value_, final Object o) {
        super(o);
        this.value_ = value_;
    }
    
    public int add(final int n) {
        synchronized (super.lock_) {
            final int value_ = this.value_ + n;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public int and(final int n) {
        synchronized (super.lock_) {
            this.value_ &= n;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public boolean commit(final int n, final int value_) {
        synchronized (super.lock_) {
            final boolean b = n == this.value_;
            if (b) {
                this.value_ = value_;
            }
            // monitorexit(super.lock_)
            return b;
        }
    }
    
    public int compareTo(final int n) {
        final int value = this.get();
        return (value < n) ? -1 : ((value == n) ? false : true);
    }
    
    public int compareTo(final SynchronizedInt synchronizedInt) {
        return this.compareTo(synchronizedInt.get());
    }
    
    public int compareTo(final Object o) {
        return this.compareTo((SynchronizedInt)o);
    }
    
    public int complement() {
        synchronized (super.lock_) {
            this.value_ ^= -1;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public int decrement() {
        synchronized (super.lock_) {
            final int value_ = this.value_ - 1;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public int divide(final int n) {
        synchronized (super.lock_) {
            final int value_ = this.value_ / n;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public boolean equals(final Object o) {
        return o != null && o instanceof SynchronizedInt && this.get() == ((SynchronizedInt)o).get();
    }
    
    public final int get() {
        synchronized (super.lock_) {
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public int hashCode() {
        return this.get();
    }
    
    public int increment() {
        synchronized (super.lock_) {
            // monitorexit(super.lock_)
            return ++this.value_;
        }
    }
    
    public int multiply(final int n) {
        synchronized (super.lock_) {
            final int value_ = this.value_ * n;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public int negate() {
        synchronized (super.lock_) {
            this.value_ = -this.value_;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public int or(final int n) {
        synchronized (super.lock_) {
            this.value_ |= n;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public int set(final int value_) {
        synchronized (super.lock_) {
            final int value_2 = this.value_;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_2;
        }
    }
    
    public int subtract(final int n) {
        synchronized (super.lock_) {
            final int value_ = this.value_ - n;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public int swap(final SynchronizedInt synchronizedInt) {
        if (synchronizedInt == this) {
            return this.get();
        }
        SynchronizedInt synchronizedInt2 = this;
        SynchronizedInt synchronizedInt3 = synchronizedInt;
        if (System.identityHashCode(synchronizedInt2) > System.identityHashCode(synchronizedInt3)) {
            synchronizedInt2 = synchronizedInt;
            synchronizedInt3 = this;
        }
        synchronized (synchronizedInt2.lock_) {
            final Object lock_ = synchronizedInt3.lock_;
            // monitorenter(lock_)
            try {
                synchronizedInt2.set(synchronizedInt3.set(synchronizedInt2.get()));
                // monitorexit(lock_)
                // monitorexit(synchronizedInt2.lock_)
                return this.get();
            }
            finally {}
        }
    }
    
    public String toString() {
        return String.valueOf(this.get());
    }
    
    public int xor(final int n) {
        synchronized (super.lock_) {
            this.value_ ^= n;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
}
