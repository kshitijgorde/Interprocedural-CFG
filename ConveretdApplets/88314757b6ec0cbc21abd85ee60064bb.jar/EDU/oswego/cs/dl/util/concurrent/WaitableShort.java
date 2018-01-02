// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class WaitableShort extends SynchronizedShort
{
    public WaitableShort(final short n) {
        super(n);
    }
    
    public WaitableShort(final short n, final Object o) {
        super(n, o);
    }
    
    public short add(final short n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.add(n);
        }
    }
    
    public short and(final short n) {
        synchronized (super.lock_) {
            super.value_ &= n;
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.value_;
        }
    }
    
    public boolean commit(final short n, final short n2) {
        synchronized (super.lock_) {
            final boolean commit = super.commit(n, n2);
            if (commit) {
                super.lock_.notifyAll();
            }
            // monitorexit(super.lock_)
            return commit;
        }
    }
    
    public short complement() {
        synchronized (super.lock_) {
            super.value_ ^= -1;
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.value_;
        }
    }
    
    public short decrement() {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.decrement();
        }
    }
    
    public short divide(final short n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.divide(n);
        }
    }
    
    public short increment() {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.increment();
        }
    }
    
    public short multiply(final short n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.multiply(n);
        }
    }
    
    public short or(final short n) {
        synchronized (super.lock_) {
            super.value_ |= n;
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.value_;
        }
    }
    
    public short set(final short n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.set(n);
        }
    }
    
    public short subtract(final short n) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.subtract(n);
        }
    }
    
    public void whenEqual(final short n, final Runnable runnable) throws InterruptedException {
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
    
    public void whenGreater(final short n, final Runnable runnable) throws InterruptedException {
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
    
    public void whenGreaterEqual(final short n, final Runnable runnable) throws InterruptedException {
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
    
    public void whenLess(final short n, final Runnable runnable) throws InterruptedException {
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
    
    public void whenLessEqual(final short n, final Runnable runnable) throws InterruptedException {
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
    
    public void whenNotEqual(final short n, final Runnable runnable) throws InterruptedException {
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
    
    public short xor(final short n) {
        synchronized (super.lock_) {
            super.value_ ^= n;
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.value_;
        }
    }
}
