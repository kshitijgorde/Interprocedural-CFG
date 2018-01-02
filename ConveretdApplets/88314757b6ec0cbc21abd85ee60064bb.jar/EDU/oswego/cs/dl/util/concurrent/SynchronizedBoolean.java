// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class SynchronizedBoolean extends SynchronizedVariable implements Comparable, Cloneable
{
    protected boolean value_;
    
    public SynchronizedBoolean(final boolean value_) {
        this.value_ = value_;
    }
    
    public SynchronizedBoolean(final boolean value_, final Object o) {
        super(o);
        this.value_ = value_;
    }
    
    public boolean and(final boolean b) {
        synchronized (super.lock_) {
            this.value_ &= b;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public boolean commit(final boolean b, final boolean value_) {
        synchronized (super.lock_) {
            final boolean b2 = b == this.value_;
            if (b2) {
                this.value_ = value_;
            }
            // monitorexit(super.lock_)
            return b2;
        }
    }
    
    public int compareTo(final SynchronizedBoolean synchronizedBoolean) {
        return this.compareTo(synchronizedBoolean.get());
    }
    
    public int compareTo(final Object o) {
        return this.compareTo((SynchronizedBoolean)o);
    }
    
    public int compareTo(final boolean b) {
        final boolean value = this.get();
        return (value == b) ? 0 : (value ? 1 : -1);
    }
    
    public boolean complement() {
        synchronized (super.lock_) {
            this.value_ ^= true;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public boolean equals(final Object o) {
        return o != null && o instanceof SynchronizedBoolean && this.get() == ((SynchronizedBoolean)o).get();
    }
    
    public final boolean get() {
        synchronized (super.lock_) {
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public int hashCode() {
        return this.get() ? 3412688 : 8319343;
    }
    
    public boolean or(final boolean b) {
        synchronized (super.lock_) {
            this.value_ |= b;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public boolean set(final boolean value_) {
        synchronized (super.lock_) {
            final boolean value_2 = this.value_;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_2;
        }
    }
    
    public boolean swap(final SynchronizedBoolean synchronizedBoolean) {
        if (synchronizedBoolean == this) {
            return this.get();
        }
        SynchronizedBoolean synchronizedBoolean2 = this;
        SynchronizedBoolean synchronizedBoolean3 = synchronizedBoolean;
        if (System.identityHashCode(synchronizedBoolean2) > System.identityHashCode(synchronizedBoolean3)) {
            synchronizedBoolean2 = synchronizedBoolean;
            synchronizedBoolean3 = this;
        }
        synchronized (synchronizedBoolean2.lock_) {
            final Object lock_ = synchronizedBoolean3.lock_;
            // monitorenter(lock_)
            try {
                synchronizedBoolean2.set(synchronizedBoolean3.set(synchronizedBoolean2.get()));
                // monitorexit(lock_)
                // monitorexit(synchronizedBoolean2.lock_)
                return this.get();
            }
            finally {}
        }
    }
    
    public String toString() {
        return String.valueOf(this.get());
    }
    
    public boolean xor(final boolean b) {
        synchronized (super.lock_) {
            this.value_ ^= b;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
}
