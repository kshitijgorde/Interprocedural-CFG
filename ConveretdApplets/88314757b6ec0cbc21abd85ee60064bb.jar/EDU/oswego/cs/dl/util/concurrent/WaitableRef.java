// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class WaitableRef extends SynchronizedRef
{
    public WaitableRef(final Object o) {
        super(o);
    }
    
    public WaitableRef(final Object o, final Object o2) {
        super(o, o2);
    }
    
    public boolean commit(final Object o, final Object o2) {
        synchronized (super.lock_) {
            final boolean commit = super.commit(o, o2);
            if (commit) {
                super.lock_.notifyAll();
            }
            // monitorexit(super.lock_)
            return commit;
        }
    }
    
    public Object set(final Object o) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.set(o);
        }
    }
    
    public void whenEqual(final Object o, final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ != o) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
    
    public void whenNotEqual(final Object o, final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ == o) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
    
    public void whenNotNull(final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ == null) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
    
    public void whenNull(final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ != null) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
}
