// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class WaitableLong extends SynchronizedLong
{
    public WaitableLong(final long n) {
        super(n);
    }
    
    public WaitableLong(final long n, final Object o) {
        super(n, o);
    }
    
    public long add(final long n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.add(n);
        }
    }
    
    public long and(final long n) {
        synchronized (super.lock_) {
            super.value_ &= n;
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.value_;
        }
    }
    
    public boolean commit(final long n, final long n2) {
        synchronized (super.lock_) {
            final boolean commit = super.commit(n, n2);
            if (commit) {
                super.lock_.notifyAll();
            }
            // monitorexit(super.lock_)
            return commit;
        }
    }
    
    public long complement() {
        synchronized (super.lock_) {
            super.value_ ^= -1L;
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.value_;
        }
    }
    
    public long decrement() {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.decrement();
        }
    }
    
    public long divide(final long n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.divide(n);
        }
    }
    
    public long increment() {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.increment();
        }
    }
    
    public long multiply(final long n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.multiply(n);
        }
    }
    
    public long or(final long n) {
        synchronized (super.lock_) {
            super.value_ |= n;
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.value_;
        }
    }
    
    public long set(final long n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.set(n);
        }
    }
    
    public long subtract(final long n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.subtract(n);
        }
    }
    
    public void whenEqual(final long n, final Runnable runnable) throws InterruptedException {
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
    
    public void whenGreater(final long n, final Runnable runnable) throws InterruptedException {
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
    
    public void whenGreaterEqual(final long n, final Runnable runnable) throws InterruptedException {
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
    
    public void whenLess(final long n, final Runnable runnable) throws InterruptedException {
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
    
    public void whenLessEqual(final long n, final Runnable runnable) throws InterruptedException {
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
    
    public void whenNotEqual(final long n, final Runnable runnable) throws InterruptedException {
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
    
    public long xor(final long n) {
        synchronized (super.lock_) {
            super.value_ ^= n;
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.value_;
        }
    }
}
