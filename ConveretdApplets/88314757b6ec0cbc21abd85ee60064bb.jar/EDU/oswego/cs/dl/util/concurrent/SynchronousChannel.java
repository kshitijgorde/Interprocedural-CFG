// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class SynchronousChannel implements BoundedChannel
{
    protected static final Object CANCELLED;
    protected final Queue waitingPuts;
    protected final Queue waitingTakes;
    
    static {
        CANCELLED = new Object();
    }
    
    public SynchronousChannel() {
        this.waitingPuts = new Queue();
        this.waitingTakes = new Queue();
    }
    
    public int capacity() {
        return 0;
    }
    
    public boolean offer(final Object value, final long n) throws InterruptedException {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        long n2 = n;
        long n3 = 0L;
        while (!Thread.interrupted()) {
            LinkedNode linkedNode = null;
            final LinkedNode deq;
            synchronized (this) {
                deq = this.waitingTakes.deq();
                if (deq == null) {
                    if (n2 <= 0L) {
                        // monitorexit(this)
                        return false;
                    }
                    this.waitingPuts.enq(linkedNode = new LinkedNode(value));
                }
            }
            if (deq != null) {
                synchronized (deq) {
                    if (deq.value != SynchronousChannel.CANCELLED) {
                        deq.value = value;
                        deq.notify();
                        // monitorexit(deq)
                        return true;
                    }
                }
                // monitorexit(deq)
            }
            final long currentTimeMillis = System.currentTimeMillis();
            if (n3 == 0L) {
                n3 = currentTimeMillis;
            }
            else {
                n2 = n - (currentTimeMillis - n3);
            }
            if (linkedNode != null) {
                synchronized (linkedNode) {
                    try {
                        while (linkedNode.value != null) {
                            if (n2 <= 0L) {
                                linkedNode.value = SynchronousChannel.CANCELLED;
                                // monitorexit(linkedNode)
                                return false;
                            }
                            linkedNode.wait(n2);
                            n2 = n - (System.currentTimeMillis() - n3);
                        }
                        // monitorexit(linkedNode)
                        return true;
                    }
                    catch (InterruptedException ex) {
                        if (linkedNode.value == null) {
                            Thread.currentThread().interrupt();
                            // monitorexit(linkedNode)
                            return true;
                        }
                        linkedNode.value = SynchronousChannel.CANCELLED;
                        throw ex;
                    }
                }
            }
        }
        throw new InterruptedException();
    }
    
    public Object peek() {
        return null;
    }
    
    public Object poll(final long n) throws InterruptedException {
        long n2 = n;
        long n3 = 0L;
        while (!Thread.interrupted()) {
            LinkedNode linkedNode = null;
            final LinkedNode deq;
            synchronized (this) {
                deq = this.waitingPuts.deq();
                if (deq == null) {
                    if (n2 <= 0L) {
                        // monitorexit(this)
                        return null;
                    }
                    this.waitingTakes.enq(linkedNode = new LinkedNode());
                }
            }
            if (deq != null) {
                synchronized (deq) {
                    final Object value = deq.value;
                    if (value != SynchronousChannel.CANCELLED) {
                        deq.value = null;
                        deq.next = null;
                        deq.notify();
                        // monitorexit(deq)
                        return value;
                    }
                }
                // monitorexit(deq)
            }
            final long currentTimeMillis = System.currentTimeMillis();
            if (n3 == 0L) {
                n3 = currentTimeMillis;
            }
            else {
                n2 = n - (currentTimeMillis - n3);
            }
            if (linkedNode != null) {
                synchronized (linkedNode) {
                    try {
                        while (true) {
                            final Object value2 = linkedNode.value;
                            if (value2 != null) {
                                linkedNode.value = null;
                                linkedNode.next = null;
                                // monitorexit(linkedNode)
                                return value2;
                            }
                            if (n2 <= 0L) {
                                linkedNode.value = SynchronousChannel.CANCELLED;
                                // monitorexit(linkedNode)
                                return null;
                            }
                            linkedNode.wait(n2);
                            n2 = n - (System.currentTimeMillis() - n3);
                        }
                    }
                    catch (InterruptedException ex) {
                        final Object value3 = linkedNode.value;
                        if (value3 != null) {
                            linkedNode.value = null;
                            linkedNode.next = null;
                            Thread.currentThread().interrupt();
                            // monitorexit(linkedNode)
                            return value3;
                        }
                        linkedNode.value = SynchronousChannel.CANCELLED;
                        throw ex;
                    }
                }
            }
        }
        throw new InterruptedException();
    }
    
    public void put(final Object value) throws InterruptedException {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        while (!Thread.interrupted()) {
            LinkedNode linkedNode = null;
            final LinkedNode deq;
            synchronized (this) {
                deq = this.waitingTakes.deq();
                if (deq == null) {
                    this.waitingPuts.enq(linkedNode = new LinkedNode(value));
                }
            }
            if (deq != null) {
                synchronized (deq) {
                    if (deq.value != SynchronousChannel.CANCELLED) {
                        deq.value = value;
                        deq.notify();
                        // monitorexit(deq)
                        return;
                    }
                    // monitorexit(deq)
                    continue;
                }
            }
            synchronized (linkedNode) {
                try {
                    while (linkedNode.value != null) {
                        linkedNode.wait();
                    }
                    return;
                }
                catch (InterruptedException ex) {
                    if (linkedNode.value == null) {
                        Thread.currentThread().interrupt();
                        // monitorexit(linkedNode)
                        return;
                    }
                    linkedNode.value = SynchronousChannel.CANCELLED;
                    throw ex;
                }
            }
        }
        throw new InterruptedException();
    }
    
    public Object take() throws InterruptedException {
        while (!Thread.interrupted()) {
            LinkedNode linkedNode = null;
            final LinkedNode deq;
            synchronized (this) {
                deq = this.waitingPuts.deq();
                if (deq == null) {
                    this.waitingTakes.enq(linkedNode = new LinkedNode());
                }
            }
            if (deq != null) {
                synchronized (deq) {
                    final Object value = deq.value;
                    if (value != SynchronousChannel.CANCELLED) {
                        deq.value = null;
                        deq.next = null;
                        deq.notify();
                        // monitorexit(deq)
                        return value;
                    }
                    // monitorexit(deq)
                    continue;
                }
            }
            synchronized (linkedNode) {
                try {
                    Object value2;
                    while (true) {
                        value2 = linkedNode.value;
                        if (value2 != null) {
                            break;
                        }
                        linkedNode.wait();
                    }
                    linkedNode.value = null;
                    linkedNode.next = null;
                    // monitorexit(linkedNode)
                    return value2;
                }
                catch (InterruptedException ex) {
                    final Object value3 = linkedNode.value;
                    if (value3 != null) {
                        linkedNode.value = null;
                        linkedNode.next = null;
                        Thread.currentThread().interrupt();
                        // monitorexit(linkedNode)
                        return value3;
                    }
                    linkedNode.value = SynchronousChannel.CANCELLED;
                    throw ex;
                }
            }
        }
        throw new InterruptedException();
    }
    
    protected static class Queue
    {
        protected LinkedNode head;
        protected LinkedNode last;
        
        protected LinkedNode deq() {
            final LinkedNode head = this.head;
            if (head != null && (this.head = head.next) == null) {
                this.last = null;
            }
            return head;
        }
        
        protected void enq(final LinkedNode linkedNode) {
            if (this.last == null) {
                this.head = linkedNode;
                this.last = linkedNode;
            }
            else {
                this.last.next = linkedNode;
                this.last = linkedNode;
            }
        }
    }
}
