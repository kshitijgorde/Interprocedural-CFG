// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import java.util.LinkedList;

public class SimpleBankQueue
{
    private LinkedList allWritable;
    
    public SimpleBankQueue() {
        this.allWritable = new LinkedList();
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(" allWritable=" + this.size());
        return sb.toString();
    }
    
    public void add(final SimpleQueueItem simpleQueueItem) {
        synchronized (this.allWritable) {
            this.allWritable.add(simpleQueueItem);
        }
    }
    
    public int size() {
        final int size;
        synchronized (this.allWritable) {
            size = this.allWritable.size();
        }
        return size;
    }
    
    public SimpleQueueItem[] dequeueAll() {
        final SimpleQueueItem[] array;
        synchronized (this.allWritable) {
            array = this.allWritable.toArray(new SimpleQueueItem[0]);
            this.allWritable.clear();
        }
        return array;
    }
}
