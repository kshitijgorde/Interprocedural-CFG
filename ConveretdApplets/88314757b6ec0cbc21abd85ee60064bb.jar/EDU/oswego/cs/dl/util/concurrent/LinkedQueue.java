// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class LinkedQueue implements Channel
{
    protected LinkedNode head_;
    protected final Object putLock_;
    protected LinkedNode last_;
    protected int waitingForTake_;
    
    public LinkedQueue() {
        this.putLock_ = new Object();
        this.waitingForTake_ = 0;
        this.head_ = new LinkedNode(null);
        this.last_ = this.head_;
    }
    
    protected synchronized Object extract() {
        synchronized (this.head_) {
            Object value = null;
            final LinkedNode next = this.head_.next;
            if (next != null) {
                value = next.value;
                next.value = null;
                this.head_ = next;
            }
            // monitorexit(this.head_)
            return value;
        }
    }
    
    protected void insert(final Object o) {
        synchronized (this.putLock_) {
            final LinkedNode linkedNode = new LinkedNode(o);
            final LinkedNode last_ = this.last_;
            // monitorenter(last_)
            try {
                this.last_.next = linkedNode;
                this.last_ = linkedNode;
            }
            // monitorexit(last_)
            finally {}
            if (this.waitingForTake_ > 0) {
                this.putLock_.notify();
            }
        }
        // monitorexit(this.putLock_)
    }
    
    public boolean isEmpty() {
        synchronized (this.head_) {
            // monitorexit(this.head_)
            return this.head_.next == null;
        }
    }
    
    public boolean offer(final Object o, final long n) throws InterruptedException {
        if (o == null) {
            throw new IllegalArgumentException();
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        this.insert(o);
        return true;
    }
    
    public Object peek() {
        synchronized (this.head_) {
            final LinkedNode next = this.head_.next;
            if (next != null) {
                // monitorexit(this.head_)
                return next.value;
            }
            // monitorexit(this.head_)
            return null;
        }
    }
    
    public Object poll(final long n) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        final Object extract = this.extract();
        if (extract != null) {
            return extract;
        }
        synchronized (this.putLock_) {
            try {
                long n2 = n;
                final long n3 = (n <= 0L) ? 0L : System.currentTimeMillis();
                ++this.waitingForTake_;
                Object extract2;
                while (true) {
                    extract2 = this.extract();
                    if (extract2 != null || n2 <= 0L) {
                        break;
                    }
                    this.putLock_.wait(n2);
                    n2 = n - (System.currentTimeMillis() - n3);
                }
                --this.waitingForTake_;
                // monitorexit(this.putLock_)
                return extract2;
            }
            catch (InterruptedException ex) {
                --this.waitingForTake_;
                this.putLock_.notify();
                throw ex;
            }
        }
    }
    
    public void put(final Object o) throws InterruptedException {
        if (o == null) {
            throw new IllegalArgumentException();
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        this.insert(o);
    }
    
    public Object take() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        final Object extract = this.extract();
        if (extract != null) {
            return extract;
        }
        synchronized (this.putLock_) {
            try {
                ++this.waitingForTake_;
                Object extract2;
                while (true) {
                    extract2 = this.extract();
                    if (extract2 != null) {
                        break;
                    }
                    this.putLock_.wait();
                }
                --this.waitingForTake_;
                // monitorexit(this.putLock_)
                return extract2;
            }
            catch (InterruptedException ex) {
                --this.waitingForTake_;
                this.putLock_.notify();
                throw ex;
            }
        }
    }
}
