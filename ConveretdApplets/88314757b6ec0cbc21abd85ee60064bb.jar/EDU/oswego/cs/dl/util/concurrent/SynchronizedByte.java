// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class SynchronizedByte extends SynchronizedVariable implements Comparable, Cloneable
{
    protected byte value_;
    
    public SynchronizedByte(final byte value_) {
        this.value_ = value_;
    }
    
    public SynchronizedByte(final byte value_, final Object o) {
        super(o);
        this.value_ = value_;
    }
    
    public byte add(final byte b) {
        synchronized (super.lock_) {
            final byte value_ = (byte)(this.value_ + b);
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public byte and(final byte b) {
        synchronized (super.lock_) {
            this.value_ &= b;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public boolean commit(final byte b, final byte value_) {
        synchronized (super.lock_) {
            final boolean b2 = b == this.value_;
            if (b2) {
                this.value_ = value_;
            }
            // monitorexit(super.lock_)
            return b2;
        }
    }
    
    public int compareTo(final byte b) {
        final byte value = this.get();
        return (value < b) ? -1 : ((value == b) ? false : true);
    }
    
    public int compareTo(final SynchronizedByte synchronizedByte) {
        return this.compareTo(synchronizedByte.get());
    }
    
    public int compareTo(final Object o) {
        return this.compareTo((SynchronizedByte)o);
    }
    
    public byte complement() {
        synchronized (super.lock_) {
            this.value_ ^= -1;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public byte decrement() {
        synchronized (super.lock_) {
            final byte value_ = (byte)(this.value_ - 1);
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public byte divide(final byte b) {
        synchronized (super.lock_) {
            final byte value_ = (byte)(this.value_ / b);
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public boolean equals(final Object o) {
        return o != null && o instanceof SynchronizedByte && this.get() == ((SynchronizedByte)o).get();
    }
    
    public final byte get() {
        synchronized (super.lock_) {
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public int hashCode() {
        return this.get();
    }
    
    public byte increment() {
        synchronized (super.lock_) {
            final byte value_ = (byte)(this.value_ + 1);
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public byte multiply(final byte b) {
        synchronized (super.lock_) {
            final byte value_ = (byte)(this.value_ * b);
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public byte negate() {
        synchronized (super.lock_) {
            this.value_ = (byte)(-this.value_);
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public byte or(final byte b) {
        synchronized (super.lock_) {
            this.value_ |= b;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public byte set(final byte value_) {
        synchronized (super.lock_) {
            final byte value_2 = this.value_;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_2;
        }
    }
    
    public byte subtract(final byte b) {
        synchronized (super.lock_) {
            final byte value_ = (byte)(this.value_ - b);
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public byte swap(final SynchronizedByte synchronizedByte) {
        if (synchronizedByte == this) {
            return this.get();
        }
        SynchronizedByte synchronizedByte2 = this;
        SynchronizedByte synchronizedByte3 = synchronizedByte;
        if (System.identityHashCode(synchronizedByte2) > System.identityHashCode(synchronizedByte3)) {
            synchronizedByte2 = synchronizedByte;
            synchronizedByte3 = this;
        }
        synchronized (synchronizedByte2.lock_) {
            final Object lock_ = synchronizedByte3.lock_;
            // monitorenter(lock_)
            try {
                synchronizedByte2.set(synchronizedByte3.set(synchronizedByte2.get()));
                // monitorexit(lock_)
                // monitorexit(synchronizedByte2.lock_)
                return this.get();
            }
            finally {}
        }
    }
    
    public String toString() {
        return Byte.toString(this.get());
    }
    
    public byte xor(final byte b) {
        synchronized (super.lock_) {
            this.value_ ^= b;
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
}
