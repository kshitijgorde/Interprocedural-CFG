// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class SynchronizedFloat extends SynchronizedVariable implements Comparable, Cloneable
{
    protected float value_;
    
    public SynchronizedFloat(final float value_) {
        this.value_ = value_;
    }
    
    public SynchronizedFloat(final float value_, final Object o) {
        super(o);
        this.value_ = value_;
    }
    
    public float add(final float n) {
        synchronized (super.lock_) {
            final float value_ = this.value_ + n;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public boolean commit(final float n, final float value_) {
        synchronized (super.lock_) {
            final boolean b = n == this.value_;
            if (b) {
                this.value_ = value_;
            }
            // monitorexit(super.lock_)
            return b;
        }
    }
    
    public int compareTo(final float n) {
        final float value = this.get();
        return (value < n) ? -1 : ((value == n) ? false : true);
    }
    
    public int compareTo(final SynchronizedFloat synchronizedFloat) {
        return this.compareTo(synchronizedFloat.get());
    }
    
    public int compareTo(final Object o) {
        return this.compareTo((SynchronizedFloat)o);
    }
    
    public float divide(final float n) {
        synchronized (super.lock_) {
            final float value_ = this.value_ / n;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public boolean equals(final Object o) {
        return o != null && o instanceof SynchronizedFloat && this.get() == ((SynchronizedFloat)o).get();
    }
    
    public final float get() {
        synchronized (super.lock_) {
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public int hashCode() {
        return Float.floatToIntBits(this.get());
    }
    
    public float multiply(final float n) {
        synchronized (super.lock_) {
            final float value_ = this.value_ * n;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public float set(final float value_) {
        synchronized (super.lock_) {
            final float value_2 = this.value_;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_2;
        }
    }
    
    public float subtract(final float n) {
        synchronized (super.lock_) {
            final float value_ = this.value_ - n;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public float swap(final SynchronizedFloat synchronizedFloat) {
        if (synchronizedFloat == this) {
            return this.get();
        }
        SynchronizedFloat synchronizedFloat2 = this;
        SynchronizedFloat synchronizedFloat3 = synchronizedFloat;
        if (System.identityHashCode(synchronizedFloat2) > System.identityHashCode(synchronizedFloat3)) {
            synchronizedFloat2 = synchronizedFloat;
            synchronizedFloat3 = this;
        }
        synchronized (synchronizedFloat2.lock_) {
            final Object lock_ = synchronizedFloat3.lock_;
            // monitorenter(lock_)
            try {
                synchronizedFloat2.set(synchronizedFloat3.set(synchronizedFloat2.get()));
                // monitorexit(lock_)
                // monitorexit(synchronizedFloat2.lock_)
                return this.get();
            }
            finally {}
        }
    }
    
    public String toString() {
        return String.valueOf(this.get());
    }
}
