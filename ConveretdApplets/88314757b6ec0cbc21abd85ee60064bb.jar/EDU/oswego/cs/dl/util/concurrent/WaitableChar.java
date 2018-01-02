// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class WaitableChar extends SynchronizedChar
{
    public WaitableChar(final char c) {
        super(c);
    }
    
    public WaitableChar(final char c, final Object o) {
        super(c, o);
    }
    
    public char add(final char c) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.add(c);
        }
    }
    
    public boolean commit(final char c, final char c2) {
        synchronized (super.lock_) {
            final boolean commit = super.commit(c, c2);
            if (commit) {
                super.lock_.notifyAll();
            }
            // monitorexit(super.lock_)
            return commit;
        }
    }
    
    public char divide(final char c) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.divide(c);
        }
    }
    
    public char multiply(final char c) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.multiply(c);
        }
    }
    
    public char set(final char c) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.set(c);
        }
    }
    
    public char subtract(final char c) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.subtract(c);
        }
    }
    
    public void whenEqual(final char c, final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ != c) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
    
    public void whenGreater(final char c, final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ <= c) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
    
    public void whenGreaterEqual(final char c, final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ < c) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
    
    public void whenLess(final char c, final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ >= c) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
    
    public void whenLessEqual(final char c, final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ > c) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
    
    public void whenNotEqual(final char c, final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ == c) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
}
