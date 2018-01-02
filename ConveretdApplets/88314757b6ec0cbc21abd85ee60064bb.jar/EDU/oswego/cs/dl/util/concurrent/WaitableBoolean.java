// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class WaitableBoolean extends SynchronizedBoolean
{
    public WaitableBoolean(final boolean b) {
        super(b);
    }
    
    public WaitableBoolean(final boolean b, final Object o) {
        super(b, o);
    }
    
    public boolean and(final boolean b) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.and(b);
        }
    }
    
    public boolean commit(final boolean b, final boolean b2) {
        synchronized (super.lock_) {
            final boolean commit = super.commit(b, b2);
            if (commit) {
                super.lock_.notifyAll();
            }
            // monitorexit(super.lock_)
            return commit;
        }
    }
    
    public boolean complement() {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.complement();
        }
    }
    
    public boolean or(final boolean b) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.or(b);
        }
    }
    
    public boolean set(final boolean b) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.set(b);
        }
    }
    
    public void whenEqual(final boolean b, final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ != b) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
    
    public void whenFalse(final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
    
    public void whenNotEqual(final boolean b, final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ == b) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
    
    public void whenTrue(final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (!super.value_) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
    
    public boolean xor(final boolean b) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.xor(b);
        }
    }
}
