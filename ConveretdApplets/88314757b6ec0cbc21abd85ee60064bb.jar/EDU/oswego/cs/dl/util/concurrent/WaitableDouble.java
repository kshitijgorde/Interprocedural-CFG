// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class WaitableDouble extends SynchronizedDouble
{
    public WaitableDouble(final double n) {
        super(n);
    }
    
    public WaitableDouble(final double n, final Object o) {
        super(n, o);
    }
    
    public double add(final double n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.add(n);
        }
    }
    
    public boolean commit(final double n, final double n2) {
        synchronized (super.lock_) {
            final boolean commit = super.commit(n, n2);
            if (commit) {
                super.lock_.notifyAll();
            }
            // monitorexit(super.lock_)
            return commit;
        }
    }
    
    public double divide(final double n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.divide(n);
        }
    }
    
    public double multiply(final double n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.multiply(n);
        }
    }
    
    public double set(final double n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.set(n);
        }
    }
    
    public double subtract(final double n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.subtract(n);
        }
    }
    
    public void whenEqual(final double n, final Runnable runnable) throws InterruptedException {
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
    
    public void whenGreater(final double n, final Runnable runnable) throws InterruptedException {
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
    
    public void whenGreaterEqual(final double n, final Runnable runnable) throws InterruptedException {
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
    
    public void whenLess(final double n, final Runnable runnable) throws InterruptedException {
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
    
    public void whenLessEqual(final double n, final Runnable runnable) throws InterruptedException {
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
    
    public void whenNotEqual(final double n, final Runnable runnable) throws InterruptedException {
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
