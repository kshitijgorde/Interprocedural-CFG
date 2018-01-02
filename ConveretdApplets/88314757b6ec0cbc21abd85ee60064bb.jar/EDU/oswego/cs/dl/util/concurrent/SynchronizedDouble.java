// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class SynchronizedDouble extends SynchronizedVariable implements Comparable, Cloneable
{
    protected double value_;
    
    public SynchronizedDouble(final double value_) {
        this.value_ = value_;
    }
    
    public SynchronizedDouble(final double value_, final Object o) {
        super(o);
        this.value_ = value_;
    }
    
    public double add(final double n) {
        synchronized (super.lock_) {
            final double value_ = this.value_ + n;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public boolean commit(final double n, final double value_) {
        synchronized (super.lock_) {
            final boolean b = n == this.value_;
            if (b) {
                this.value_ = value_;
            }
            // monitorexit(super.lock_)
            return b;
        }
    }
    
    public int compareTo(final double n) {
        final double value = this.get();
        return (value < n) ? -1 : ((value == n) ? false : true);
    }
    
    public int compareTo(final SynchronizedDouble synchronizedDouble) {
        return this.compareTo(synchronizedDouble.get());
    }
    
    public int compareTo(final Object o) {
        return this.compareTo((SynchronizedDouble)o);
    }
    
    public double divide(final double n) {
        synchronized (super.lock_) {
            final double value_ = this.value_ / n;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public boolean equals(final Object o) {
        return o != null && o instanceof SynchronizedDouble && this.get() == ((SynchronizedDouble)o).get();
    }
    
    public final double get() {
        synchronized (super.lock_) {
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public int hashCode() {
        final long doubleToLongBits = Double.doubleToLongBits(this.get());
        return (int)(doubleToLongBits ^ doubleToLongBits >> 32);
    }
    
    public double multiply(final double n) {
        synchronized (super.lock_) {
            final double value_ = this.value_ * n;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public double set(final double value_) {
        synchronized (super.lock_) {
            final double value_2 = this.value_;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_2;
        }
    }
    
    public double subtract(final double n) {
        synchronized (super.lock_) {
            final double value_ = this.value_ - n;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public double swap(final SynchronizedDouble synchronizedDouble) {
        if (synchronizedDouble == this) {
            return this.get();
        }
        SynchronizedDouble synchronizedDouble2 = this;
        SynchronizedDouble synchronizedDouble3 = synchronizedDouble;
        if (System.identityHashCode(synchronizedDouble2) > System.identityHashCode(synchronizedDouble3)) {
            synchronizedDouble2 = synchronizedDouble;
            synchronizedDouble3 = this;
        }
        synchronized (synchronizedDouble2.lock_) {
            final Object lock_ = synchronizedDouble3.lock_;
            // monitorenter(lock_)
            try {
                synchronizedDouble2.set(synchronizedDouble3.set(synchronizedDouble2.get()));
                // monitorexit(lock_)
                // monitorexit(synchronizedDouble2.lock_)
                return this.get();
            }
            finally {}
        }
    }
    
    public String toString() {
        return String.valueOf(this.get());
    }
}
