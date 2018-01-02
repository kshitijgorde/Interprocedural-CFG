// 
// Decompiled by Procyon v0.5.30
// 

public class circQueue
{
    qElement head;
    qElement tail;
    qElement current;
    int count;
    
    public synchronized void addElement(final Object o) {
        this.current = new qElement(o, this.tail, this.head);
        if (this.tail == null) {
            this.tail = this.current;
        }
        this.head = this.current;
        this.tail.prev = this.current;
        ++this.count;
    }
    
    public synchronized boolean removeElement(final Object o) {
        for (int i = 0; i < this.count; ++i) {
            if (o.equals(this.current.element)) {
                if (this.count > 1) {
                    this.current.prev.next = this.current.next;
                    this.current.next.prev = this.current.prev;
                    if (this.tail == this.current) {
                        this.tail = this.current.next;
                    }
                    if (this.head == this.current) {
                        this.head = this.current.prev;
                    }
                    --this.count;
                }
                else {
                    final qElement current = null;
                    this.tail = current;
                    this.head = current;
                    this.current = current;
                    this.count = 0;
                }
                this.resetQueue();
                return true;
            }
            this.getNext();
        }
        return false;
    }
    
    public int size() {
        return this.count;
    }
    
    public Object resetQueue() {
        this.current = this.tail;
        if (this.current == null) {
            return null;
        }
        return this.current.element;
    }
    
    public Object getCurrent() {
        return this.current.element;
    }
    
    public void setCurrent(final Object o) {
        for (int n = 0; n < this.count && !o.equals(this.current.element); ++n) {
            this.getNext();
        }
    }
    
    public boolean isElement(final Object o) {
        final qElement current = this.current;
        boolean b = false;
        for (int i = 0; i < this.count; ++i) {
            if (o.equals(this.current.element)) {
                b = true;
                break;
            }
            this.getNext();
        }
        this.current = current;
        return b;
    }
    
    public Object getNext() {
        this.current = this.current.next;
        return this.current.element;
    }
    
    public Object getPrev() {
        this.current = this.current.prev;
        return this.current.element;
    }
}
