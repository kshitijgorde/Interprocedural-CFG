// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.Sync;
import EDU.oswego.cs.dl.util.concurrent.DefaultChannelCapacity;
import EDU.oswego.cs.dl.util.concurrent.CondVar;
import EDU.oswego.cs.dl.util.concurrent.Mutex;
import EDU.oswego.cs.dl.util.concurrent.BoundedChannel;

public class CVBuffer implements BoundedChannel
{
    private final Mutex mutex;
    private final CondVar notFull;
    private final CondVar notEmpty;
    private int count;
    private int takePtr;
    private int putPtr;
    private final Object[] array;
    
    public CVBuffer() {
        this(DefaultChannelCapacity.get());
    }
    
    public CVBuffer(final int n) {
        this.count = 0;
        this.takePtr = 0;
        this.putPtr = 0;
        this.array = new Object[n];
        this.mutex = new Mutex();
        this.notFull = new CondVar(this.mutex);
        this.notEmpty = new CondVar(this.mutex);
    }
    
    public int capacity() {
        return this.array.length;
    }
    
    public boolean offer(final Object o, final long n) throws InterruptedException {
        this.mutex.acquire();
        try {
            if (this.count == this.array.length) {
                this.notFull.timedwait(n);
                if (this.count == this.array.length) {
                    return false;
                }
            }
            this.array[this.putPtr] = o;
            this.putPtr = (this.putPtr + 1) % this.array.length;
            ++this.count;
            this.notEmpty.signal();
            return true;
        }
        finally {
            this.mutex.release();
        }
    }
    
    public Object peek() {
        try {
            this.mutex.acquire();
            try {
                if (this.count == 0) {
                    return null;
                }
                return this.array[this.takePtr];
            }
            finally {
                this.mutex.release();
            }
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return null;
        }
    }
    
    public Object poll(final long n) throws InterruptedException {
        Object o = null;
        this.mutex.acquire();
        try {
            if (this.count == 0) {
                this.notEmpty.timedwait(n);
                if (this.count == 0) {
                    return null;
                }
            }
            o = this.array[this.takePtr];
            this.array[this.takePtr] = null;
            this.takePtr = (this.takePtr + 1) % this.array.length;
            --this.count;
            this.notFull.signal();
        }
        finally {
            this.mutex.release();
        }
        return o;
    }
    
    public void put(final Object o) throws InterruptedException {
        this.mutex.acquire();
        try {
            while (this.count == this.array.length) {
                this.notFull.await();
            }
            this.array[this.putPtr] = o;
            this.putPtr = (this.putPtr + 1) % this.array.length;
            ++this.count;
            this.notEmpty.signal();
        }
        finally {
            this.mutex.release();
        }
    }
    
    public Object take() throws InterruptedException {
        Object o = null;
        this.mutex.acquire();
        try {
            while (this.count == 0) {
                this.notEmpty.await();
            }
            o = this.array[this.takePtr];
            this.array[this.takePtr] = null;
            this.takePtr = (this.takePtr + 1) % this.array.length;
            --this.count;
            this.notFull.signal();
        }
        finally {
            this.mutex.release();
        }
        return o;
    }
}
