// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

import java.lang.reflect.InvocationTargetException;

public class FutureResult
{
    protected Object value_;
    protected boolean ready_;
    protected InvocationTargetException exception_;
    
    public FutureResult() {
        this.value_ = null;
        this.ready_ = false;
        this.exception_ = null;
    }
    
    public synchronized void clear() {
        this.value_ = null;
        this.exception_ = null;
        this.ready_ = false;
    }
    
    protected Object doGet() throws InvocationTargetException {
        if (this.exception_ != null) {
            throw this.exception_;
        }
        return this.value_;
    }
    
    public synchronized Object get() throws InterruptedException, InvocationTargetException {
        while (!this.ready_) {
            this.wait();
        }
        return this.doGet();
    }
    
    public synchronized InvocationTargetException getException() {
        return this.exception_;
    }
    
    public synchronized boolean isReady() {
        return this.ready_;
    }
    
    public synchronized Object peek() {
        return this.value_;
    }
    
    public synchronized void set(final Object value_) {
        this.value_ = value_;
        this.ready_ = true;
        this.notifyAll();
    }
    
    public synchronized void setException(final Throwable t) {
        this.exception_ = new InvocationTargetException(t);
        this.ready_ = true;
        this.notifyAll();
    }
    
    public Runnable setter(final Callable callable) {
        return new Runnable() {
            private final /* synthetic */ Callable val$function = val$function;
            
            public void run() {
                try {
                    FutureResult.this.set(this.val$function.call());
                }
                catch (Throwable exception) {
                    FutureResult.this.setException(exception);
                }
            }
        };
    }
    
    public synchronized Object timedGet(final long n) throws TimeoutException, InterruptedException, InvocationTargetException {
        final long n2 = (n <= 0L) ? 0L : System.currentTimeMillis();
        long n3 = n;
        if (this.ready_) {
            return this.doGet();
        }
        if (n3 <= 0L) {
            throw new TimeoutException(n);
        }
        do {
            this.wait(n3);
            if (this.ready_) {
                return this.doGet();
            }
            n3 = n - (System.currentTimeMillis() - n2);
        } while (n3 > 0L);
        throw new TimeoutException(n);
    }
}
