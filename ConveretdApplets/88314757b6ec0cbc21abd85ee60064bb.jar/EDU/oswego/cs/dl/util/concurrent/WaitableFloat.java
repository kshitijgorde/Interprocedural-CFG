// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class WaitableFloat extends SynchronizedFloat
{
    public WaitableFloat(final float n) {
        super(n);
    }
    
    public WaitableFloat(final float n, final Object o) {
        super(n, o);
    }
    
    public float add(final float n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.add(n);
        }
    }
    
    public boolean commit(final float n, final float n2) {
        synchronized (super.lock_) {
            final boolean commit = super.commit(n, n2);
            if (commit) {
                super.lock_.notifyAll();
            }
            // monitorexit(super.lock_)
            return commit;
        }
    }
    
    public float divide(final float n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.divide(n);
        }
    }
    
    public float multiply(final float n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.multiply(n);
        }
    }
    
    public float set(final float n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.set(n);
        }
    }
    
    public float subtract(final float n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.subtract(n);
        }
    }
    
    public void whenEqual(final float n, final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ != n) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
    
    public void whenGreater(final float n, final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ <= n) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
    
    public void whenGreaterEqual(final float n, final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ < n) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
    
    public void whenLess(final float n, final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ >= n) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
    
    public void whenLessEqual(final float n, final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ > n) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
    
    public void whenNotEqual(final float n, final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ == n) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
}
