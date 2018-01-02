// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

public class ObjectQueue
{
    private QueueItem m_head;
    private QueueItem m_foot;
    private int m_size;
    
    public ObjectQueue() {
        this.m_head = null;
        this.m_foot = null;
        this.m_size = 0;
    }
    
    public int getSize() {
        return this.m_size;
    }
    
    public synchronized void push(final Object o) {
        final QueueItem head = new QueueItem(o);
        ++this.m_size;
        if (this.m_head == null) {
            this.m_head = head;
            this.m_foot = head;
        }
        else {
            this.m_head.m_previous = head;
            this.m_head = head;
        }
    }
    
    public synchronized Object pop() {
        if (this.m_head == null) {
            return null;
        }
        Object o;
        if (this.m_head == this.m_foot) {
            o = this.m_foot.m_object;
            this.m_head = null;
            this.m_foot = null;
        }
        else {
            o = this.m_foot.m_object;
            this.m_foot = this.m_foot.m_previous;
        }
        --this.m_size;
        return o;
    }
    
    public Object take() throws InterruptedException {
        Object pop;
        while (true) {
            pop = this.pop();
            if (pop != null) {
                break;
            }
            Thread.sleep(100L);
        }
        return pop;
    }
    
    public Object poll(final int n) throws InterruptedException {
        final Object pop = this.pop();
        if (pop != null) {
            return pop;
        }
        Thread.sleep(n);
        return this.pop();
    }
    
    public synchronized boolean isEmpty() {
        return this.m_size == 0;
    }
    
    private final class QueueItem
    {
        private Object m_object;
        private QueueItem m_previous;
        
        public QueueItem(final Object object) {
            this.m_previous = null;
            this.m_object = object;
        }
    }
}
