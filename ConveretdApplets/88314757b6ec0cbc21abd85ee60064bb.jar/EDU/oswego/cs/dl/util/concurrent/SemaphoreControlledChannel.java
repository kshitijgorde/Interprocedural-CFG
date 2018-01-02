// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;

public abstract class SemaphoreControlledChannel implements BoundedChannel
{
    protected final Semaphore putGuard_;
    protected final Semaphore takeGuard_;
    protected int capacity_;
    
    public SemaphoreControlledChannel(final int capacity_) throws IllegalArgumentException {
        if (capacity_ <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity_ = capacity_;
        this.putGuard_ = new Semaphore(capacity_);
        this.takeGuard_ = new Semaphore(0L);
    }
    
    public SemaphoreControlledChannel(final int capacity_, final Class clazz) throws IllegalArgumentException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException {
        if (capacity_ <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity_ = capacity_;
        final Constructor<Semaphore> declaredConstructor = clazz.getDeclaredConstructor(Long.TYPE);
        this.putGuard_ = declaredConstructor.newInstance(new Long(capacity_));
        this.takeGuard_ = declaredConstructor.newInstance(new Long(0L));
    }
    
    public int capacity() {
        return this.capacity_;
    }
    
    protected abstract Object extract();
    
    protected abstract void insert(final Object p0);
    
    public boolean offer(final Object o, final long n) throws InterruptedException {
        if (o == null) {
            throw new IllegalArgumentException();
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        if (!this.putGuard_.attempt(n)) {
            return false;
        }
        try {
            this.insert(o);
            this.takeGuard_.release();
            return true;
        }
        catch (ClassCastException ex) {
            this.putGuard_.release();
            throw ex;
        }
    }
    
    public abstract Object peek();
    
    public Object poll(final long n) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        if (!this.takeGuard_.attempt(n)) {
            return null;
        }
        try {
            final Object extract = this.extract();
            this.putGuard_.release();
            return extract;
        }
        catch (ClassCastException ex) {
            this.takeGuard_.release();
            throw ex;
        }
    }
    
    public void put(final Object o) throws InterruptedException {
        if (o == null) {
            throw new IllegalArgumentException();
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        this.putGuard_.acquire();
        try {
            this.insert(o);
            this.takeGuard_.release();
        }
        catch (ClassCastException ex) {
            this.putGuard_.release();
            throw ex;
        }
    }
    
    public int size() {
        return (int)this.takeGuard_.permits();
    }
    
    public Object take() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        this.takeGuard_.acquire();
        try {
            final Object extract = this.extract();
            this.putGuard_.release();
            return extract;
        }
        catch (ClassCastException ex) {
            this.takeGuard_.release();
            throw ex;
        }
    }
}
