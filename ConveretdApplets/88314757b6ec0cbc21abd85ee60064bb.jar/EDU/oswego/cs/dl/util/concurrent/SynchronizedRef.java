// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class SynchronizedRef extends SynchronizedVariable
{
    protected Object value_;
    
    public SynchronizedRef(final Object value_) {
        this.value_ = value_;
    }
    
    public SynchronizedRef(final Object value_, final Object o) {
        super(o);
        this.value_ = value_;
    }
    
    public boolean commit(final Object o, final Object value_) {
        synchronized (super.lock_) {
            final boolean b = o == this.value_;
            if (b) {
                this.value_ = value_;
            }
            // monitorexit(super.lock_)
            return b;
        }
    }
    
    public final Object get() {
        synchronized (super.lock_) {
            // monitorexit(super.lock_)
            return this.value_;
        }
    }
    
    public Object set(final Object value_) {
        synchronized (super.lock_) {
            final Object value_2 = this.value_;
            this.value_ = value_;
            // monitorexit(super.lock_)
            return value_2;
        }
    }
    
    public Object swap(final SynchronizedRef synchronizedRef) {
        if (synchronizedRef == this) {
            return this.get();
        }
        SynchronizedRef synchronizedRef2 = this;
        SynchronizedRef synchronizedRef3 = synchronizedRef;
        if (System.identityHashCode(synchronizedRef2) > System.identityHashCode(synchronizedRef3)) {
            synchronizedRef2 = synchronizedRef;
            synchronizedRef3 = this;
        }
        synchronized (synchronizedRef2.lock_) {
            final Object lock_ = synchronizedRef3.lock_;
            // monitorenter(lock_)
            try {
                synchronizedRef2.set(synchronizedRef3.set(synchronizedRef2.get()));
                // monitorexit(lock_)
                // monitorexit(synchronizedRef2.lock_)
                return this.get();
            }
            finally {}
        }
    }
}
