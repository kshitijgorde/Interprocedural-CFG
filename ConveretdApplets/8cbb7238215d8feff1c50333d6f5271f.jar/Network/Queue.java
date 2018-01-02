// 
// Decompiled by Procyon v0.5.30
// 

package Network;

import java.io.IOException;
import java.util.Vector;

public class Queue
{
    protected Vector queue;
    private boolean toterminate;
    
    public void terminateNow() {
        this.toterminate = true;
        synchronized (this.queue) {
            this.queue.addElement(new Integer(23));
            this.queue.notify();
        }
        // monitorexit(this.queue)
    }
    
    public Queue() {
        this.toterminate = false;
        this.queue = new Vector();
    }
    
    public void add(final Object item) {
        synchronized (this.queue) {
            this.queue.addElement(item);
            this.queue.notify();
        }
        // monitorexit(this.queue)
    }
    
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }
    
    public Object remove() throws IOException {
        synchronized (this.queue) {
            while (this.queue.isEmpty()) {
                try {
                    this.queue.wait();
                }
                catch (InterruptedException ex) {}
            }
            if (this.toterminate) {
                throw new IOException("Queue told to terminate");
            }
            final Object item = this.queue.firstElement();
            this.queue.removeElement(item);
            // monitorexit(this.queue)
            return item;
        }
    }
}
