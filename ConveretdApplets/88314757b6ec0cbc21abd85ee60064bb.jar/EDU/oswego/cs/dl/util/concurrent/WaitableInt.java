// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class WaitableInt extends SynchronizedInt
{
    public WaitableInt(final int n) {
        super(n);
    }
    
    public WaitableInt(final int n, final Object o) {
        super(n, o);
    }
    
    public int add(final int n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.add(n);
        }
    }
    
    public int and(final int n) {
        synchronized (super.lock_) {
            super.value_ &= n;
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.value_;
        }
    }
    
    public boolean commit(final int n, final int n2) {
        synchronized (super.lock_) {
            final boolean commit = super.commit(n, n2);
            if (commit) {
                super.lock_.notifyAll();
            }
            // monitorexit(super.lock_)
            return commit;
        }
    }
    
    public int complement() {
        synchronized (super.lock_) {
            super.value_ ^= -1;
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.value_;
        }
    }
    
    public int decrement() {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.decrement();
        }
    }
    
    public int divide(final int n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.divide(n);
        }
    }
    
    public int increment() {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.increment();
        }
    }
    
    public int multiply(final int n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.multiply(n);
        }
    }
    
    public int or(final int n) {
        synchronized (super.lock_) {
            super.value_ |= n;
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.value_;
        }
    }
    
    public int set(final int n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.set(n);
        }
    }
    
    public int subtract(final int n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.subtract(n);
        }
    }
    
    public void whenEqual(final int n, final Runnable runnable) throws InterruptedException {
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
    
    public void whenGreater(final int n, final Runnable runnable) throws InterruptedException {
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
    
    public void whenGreaterEqual(final int n, final Runnable runnable) throws InterruptedException {
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
    
    public void whenLess(final int n, final Runnable runnable) throws InterruptedException {
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
    
    public void whenLessEqual(final int n, final Runnable runnable) throws InterruptedException {
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
    
    public void whenNotEqual(final int n, final Runnable runnable) throws InterruptedException {
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
    
    public int xor(final int n) {
        synchronized (super.lock_) {
            super.value_ ^= n;
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.value_;
        }
    }
}
