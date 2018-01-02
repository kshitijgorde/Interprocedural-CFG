// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class SynchronizedChar extends SynchronizedVariable implements Comparable, Cloneable
{
    protected char value_;
    
    public SynchronizedChar(final char value_) {
        this.value_ = value_;
    }
    
    public SynchronizedChar(final char value_, final Object o) {
        super(o);
        this.value_ = value_;
    }
    
    public char add(final char c) {
        synchronized (super.lock_) {
            final char value_ = (char)(this.value_ + c);
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public boolean commit(final char c, final char value_) {
        synchronized (super.lock_) {
            final boolean b = c == this.value_;
            if (b) {
                this.value_ = value_;
            }
            // monitorexit(super.lock_)
            return b;
        }
    }
    
    public int compareTo(final char c) {
        final char value = this.get();
        return (value < c) ? -1 : ((value == c) ? false : true);
    }
    
    public int compareTo(final SynchronizedChar synchronizedChar) {
        return this.compareTo(synchronizedChar.get());
    }
    
    public int compareTo(final Object o) {
        return this.compareTo((SynchronizedChar)o);
    }
    
    public char divide(final char c) {
        synchronized (super.lock_) {
            final char value_ = (char)(this.value_ / c);
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public boolean equals(final Object o) {
        return o != null && o instanceof SynchronizedChar && this.get() == ((SynchronizedChar)o).get();
    }
    
    public final char get() {
        synchronized (super.lock_) {
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public int hashCode() {
        return this.get();
    }
    
    public synchronized char multiply(final char c) {
        synchronized (super.lock_) {
            final char value_ = (char)(this.value_ * c);
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public char set(final char value_) {
        synchronized (super.lock_) {
            final char value_2 = this.value_;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_2;
        }
    }
    
    public char subtract(final char c) {
        synchronized (super.lock_) {
            final char value_ = (char)(this.value_ - c);
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_;
        }
    }
    
    public char swap(final SynchronizedChar synchronizedChar) {
        if (synchronizedChar == this) {
            return this.get();
        }
        SynchronizedChar synchronizedChar2 = this;
        SynchronizedChar synchronizedChar3 = synchronizedChar;
        if (System.identityHashCode(synchronizedChar2) > System.identityHashCode(synchronizedChar3)) {
            synchronizedChar2 = synchronizedChar;
            synchronizedChar3 = this;
        }
        synchronized (synchronizedChar2.lock_) {
            final Object lock_ = synchronizedChar3.lock_;
            // monitorenter(lock_)
            try {
                synchronizedChar2.set(synchronizedChar3.set(synchronizedChar2.get()));
                // monitorexit(lock_)
                // monitorexit(synchronizedChar2.lock_)
                return this.get();
            }
            finally {}
        }
    }
    
    public String toString() {
        return String.valueOf(this.get());
    }
}
