// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

import java.lang.reflect.InvocationTargetException;

public class Slot extends SemaphoreControlledChannel
{
    protected Object item_;
    
    public Slot() {
        super(1);
        this.item_ = null;
    }
    
    public Slot(final Class clazz) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException {
        super(1, clazz);
        this.item_ = null;
    }
    
    protected synchronized Object extract() {
        final Object item_ = this.item_;
        this.item_ = null;
        return item_;
    }
    
    protected synchronized void insert(final Object item_) {
        this.item_ = item_;
    }
    
    public synchronized Object peek() {
        return this.item_;
    }
}
