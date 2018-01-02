// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class WaitableByte extends SynchronizedByte
{
    public WaitableByte(final byte b) {
        super(b);
    }
    
    public WaitableByte(final byte b, final Object o) {
        super(b, o);
    }
    
    public byte add(final byte b) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.add(b);
        }
    }
    
    public byte and(final byte b) {
        synchronized (super.lock_) {
            super.value_ &= b;
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.value_;
        }
    }
    
    public boolean commit(final byte b, final byte b2) {
        synchronized (super.lock_) {
            final boolean commit = super.commit(b, b2);
            if (commit) {
                super.lock_.notifyAll();
            }
            // monitorexit(super.lock_)
            return commit;
        }
    }
    
    public byte complement() {
        synchronized (super.lock_) {
            super.value_ ^= -1;
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.value_;
        }
    }
    
    public byte decrement() {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.decrement();
        }
    }
    
    public byte divide(final byte b) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.divide(b);
        }
    }
    
    public byte increment() {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.increment();
        }
    }
    
    public byte multiply(final byte b) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.multiply(b);
        }
    }
    
    public byte or(final byte b) {
        synchronized (super.lock_) {
            super.value_ |= b;
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.value_;
        }
    }
    
    public byte set(final byte b) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.set(b);
        }
    }
    
    public byte subtract(final byte b) {
        synchronized (super.lock_) {
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.subtract(b);
        }
    }
    
    public void whenEqual(final byte b, final Runnable runnable) throws InterruptedException {
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
    
    public void whenGreater(final byte b, final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ <= b) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
    
    public void whenGreaterEqual(final byte b, final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ < b) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
    
    public void whenLess(final byte b, final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ >= b) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
    
    public void whenLessEqual(final byte b, final Runnable runnable) throws InterruptedException {
        synchronized (super.lock_) {
            while (super.value_ > b) {
                super.lock_.wait();
            }
            if (runnable != null) {
                runnable.run();
            }
        }
        // monitorexit(super.lock_)
    }
    
    public void whenNotEqual(final byte b, final Runnable runnable) throws InterruptedException {
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
    
    public byte xor(final byte b) {
        synchronized (super.lock_) {
            super.value_ ^= b;
            super.lock_.notifyAll();
            // monitorexit(super.lock_)
            return super.value_;
        }
    }
}
