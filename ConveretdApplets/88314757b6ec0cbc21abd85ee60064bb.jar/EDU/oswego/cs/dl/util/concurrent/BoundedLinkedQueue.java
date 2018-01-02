// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class BoundedLinkedQueue implements BoundedChannel
{
    protected LinkedNode head_;
    protected LinkedNode last_;
    protected final Object putGuard_;
    protected final Object takeGuard_;
    protected int capacity_;
    protected int putSidePutPermits_;
    protected int takeSidePutPermits_;
    
    public BoundedLinkedQueue() {
        this(DefaultChannelCapacity.get());
    }
    
    public BoundedLinkedQueue(final int n) {
        this.putGuard_ = new Object();
        this.takeGuard_ = new Object();
        this.takeSidePutPermits_ = 0;
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity_ = n;
        this.putSidePutPermits_ = n;
        this.head_ = new LinkedNode(null);
        this.last_ = this.head_;
    }
    
    protected final void allowTake() {
        synchronized (this.takeGuard_) {
            this.takeGuard_.notify();
        }
        // monitorexit(this.takeGuard_)
    }
    
    public synchronized int capacity() {
        return this.capacity_;
    }
    
    protected synchronized Object extract() {
        synchronized (this.head_) {
            Object value = null;
            final LinkedNode next = this.head_.next;
            if (next != null) {
                value = next.value;
                next.value = null;
                this.head_ = next;
                ++this.takeSidePutPermits_;
                this.notify();
            }
            // monitorexit(this.head_)
            return value;
        }
    }
    
    protected void insert(final Object o) {
        --this.putSidePutPermits_;
        final LinkedNode linkedNode = new LinkedNode(o);
        synchronized (this.last_) {
            this.last_.next = linkedNode;
        }
        // monitorexit(this.last_ = linkedNode)
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
        synchronized (this.putGuard_) {
            if (this.putSidePutPermits_ <= 0) {
                // monitorenter(this)
                try {
                    Label_0151: {
                        if (this.reconcilePutPermits() <= 0) {
                            if (n <= 0L) {
                                // monitorexit(this)
                                // monitorexit(this.putGuard_)
                                return false;
                            }
                            try {
                                long n2 = n;
                                final long currentTimeMillis = System.currentTimeMillis();
                                do {
                                    this.wait(n2);
                                    if (this.reconcilePutPermits() > 0) {
                                        break Label_0151;
                                    }
                                    n2 = n - (System.currentTimeMillis() - currentTimeMillis);
                                } while (n2 > 0L);
                                // monitorexit(this)
                                // monitorexit(this.putGuard_)
                                return false;
                            }
                            catch (InterruptedException ex) {
                                this.notify();
                            }
                        }
                    }
                }
                // monitorexit(this)
                finally {}
            }
            this.insert(o);
        }
        // monitorexit(this.putGuard_)
        this.allowTake();
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
        synchronized (this.takeGuard_) {
            try {
                long n2 = n;
                final long n3 = (n <= 0L) ? 0L : System.currentTimeMillis();
                Object extract2;
                while (true) {
                    extract2 = this.extract();
                    if (extract2 != null || n2 <= 0L) {
                        break;
                    }
                    this.takeGuard_.wait(n2);
                    n2 = n - (System.currentTimeMillis() - n3);
                }
                // monitorexit(this.takeGuard_)
                return extract2;
            }
            catch (InterruptedException ex) {
                this.takeGuard_.notify();
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
        synchronized (this.putGuard_) {
            if (this.putSidePutPermits_ <= 0) {
                // monitorenter(this)
                try {
                    if (this.reconcilePutPermits() <= 0) {
                        try {
                            do {
                                this.wait();
                            } while (this.reconcilePutPermits() <= 0);
                        }
                        catch (InterruptedException ex) {
                            this.notify();
                        }
                    }
                }
                // monitorexit(this)
                finally {}
            }
            this.insert(o);
        }
        // monitorexit(this.putGuard_)
        this.allowTake();
    }
    
    protected final int reconcilePutPermits() {
        this.putSidePutPermits_ += this.takeSidePutPermits_;
        this.takeSidePutPermits_ = 0;
        return this.putSidePutPermits_;
    }
    
    public void setCapacity(final int capacity_) {
        if (capacity_ <= 0) {
            throw new IllegalArgumentException();
        }
        synchronized (this.putGuard_) {
            // monitorenter(this)
            try {
                this.takeSidePutPermits_ += capacity_ - this.capacity_;
                this.capacity_ = capacity_;
                this.reconcilePutPermits();
                this.notifyAll();
            }
            // monitorexit(this)
            finally {}
        }
        // monitorexit(this.putGuard_)
    }
    
    public synchronized int size() {
        return this.capacity_ - (this.takeSidePutPermits_ + this.putSidePutPermits_);
    }
    
    public Object take() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        final Object extract = this.extract();
        if (extract != null) {
            return extract;
        }
        synchronized (this.takeGuard_) {
            try {
                Object extract2;
                while (true) {
                    extract2 = this.extract();
                    if (extract2 != null) {
                        break;
                    }
                    this.takeGuard_.wait();
                }
                // monitorexit(this.takeGuard_)
                return extract2;
            }
            catch (InterruptedException ex) {
                this.takeGuard_.notify();
                throw ex;
            }
        }
    }
}
