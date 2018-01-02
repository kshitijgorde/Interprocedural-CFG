// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

public class BoundedPriorityQueue extends SemaphoreControlledChannel
{
    protected final Heap heap_;
    
    public BoundedPriorityQueue() {
        this(DefaultChannelCapacity.get(), (Comparator)null);
    }
    
    public BoundedPriorityQueue(final int n) {
        this(n, (Comparator)null);
    }
    
    public BoundedPriorityQueue(final int n, final Comparator comparator) throws IllegalArgumentException {
        super(n);
        this.heap_ = new Heap(n, comparator);
    }
    
    public BoundedPriorityQueue(final int n, final Comparator comparator, final Class clazz) throws IllegalArgumentException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException {
        super(n, clazz);
        this.heap_ = new Heap(n, comparator);
    }
    
    public BoundedPriorityQueue(final Comparator comparator) {
        this(DefaultChannelCapacity.get(), comparator);
    }
    
    protected Object extract() {
        return this.heap_.extract();
    }
    
    protected void insert(final Object o) {
        this.heap_.insert(o);
    }
    
    public Object peek() {
        return this.heap_.peek();
    }
}
