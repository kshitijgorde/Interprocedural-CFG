// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class WaitFreeQueue implements Channel
{
    protected volatile Node head;
    protected volatile Node tail;
    protected final Object tailLock;
    
    public WaitFreeQueue() {
        this.head = new Node(null);
        this.tail = this.head;
        this.tailLock = new Object();
    }
    
    protected synchronized boolean CASHead(final Node node, final Node head) {
        if (this.head == node) {
            this.head = head;
            return true;
        }
        return false;
    }
    
    protected boolean CASTail(final Node node, final Node tail) {
        synchronized (this.tailLock) {
            if (this.tail == node) {
                this.tail = tail;
                // monitorexit(this.tailLock)
                return true;
            }
            // monitorexit(this.tailLock)
            return false;
        }
    }
    
    protected Object extract() throws InterruptedException {
        Node head;
        Node next;
        Object value;
        do {
            head = this.head;
            next = head.next;
            if (next == null) {
                return null;
            }
            value = next.value;
        } while (!this.CASHead(head, next));
        return value;
    }
    
    public boolean offer(final Object o, final long n) throws InterruptedException {
        this.put(o);
        return true;
    }
    
    public Object peek() {
        final Node next = this.head.next;
        if (next == null) {
            return null;
        }
        synchronized (this) {
            return next.value;
        }
    }
    
    public Object poll(final long n) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        if (n <= 0L) {
            return this.extract();
        }
        final long currentTimeMillis = System.currentTimeMillis();
        while (true) {
            final Object extract = this.extract();
            if (extract != null) {
                return extract;
            }
            if (System.currentTimeMillis() - currentTimeMillis >= n) {
                return null;
            }
            Thread.sleep(0L);
        }
    }
    
    public void put(final Object o) throws InterruptedException {
        if (o == null) {
            throw new IllegalArgumentException();
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        final Node node = new Node(o);
        Node tail;
        while (true) {
            tail = this.tail;
            if (tail.CASNext(null, node)) {
                break;
            }
            this.CASTail(tail, tail.next);
        }
        this.CASTail(tail, node);
    }
    
    public Object take() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object extract;
        while (true) {
            extract = this.extract();
            if (extract != null) {
                break;
            }
            Thread.sleep(0L);
        }
        return extract;
    }
    
    protected static final class Node
    {
        protected final Object value;
        protected volatile Node next;
        
        protected Node(final Object value) {
            this.value = value;
        }
        
        protected synchronized boolean CASNext(final Node node, final Node next) {
            if (this.next == node) {
                this.next = next;
                return true;
            }
            return false;
        }
    }
}
