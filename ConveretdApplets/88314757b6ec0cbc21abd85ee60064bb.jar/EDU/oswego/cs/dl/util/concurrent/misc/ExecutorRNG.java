// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import java.lang.reflect.InvocationTargetException;
import EDU.oswego.cs.dl.util.concurrent.FutureResult;
import EDU.oswego.cs.dl.util.concurrent.Callable;
import EDU.oswego.cs.dl.util.concurrent.Executor;

abstract class ExecutorRNG extends DelegatedRNG
{
    Executor executor_;
    Runnable delegatedUpdate_;
    Callable delegatedNext_;
    FutureResult nextResult_;
    
    ExecutorRNG() {
        this.delegatedUpdate_ = null;
        this.delegatedNext_ = null;
        this.nextResult_ = null;
    }
    
    synchronized Callable delegatedNextFunction() {
        if (this.delegatedNext_ == null) {
            this.delegatedNext_ = new NextFunction(this.getDelegate());
        }
        return this.delegatedNext_;
    }
    
    synchronized Runnable delegatedUpdateCommand() {
        if (this.delegatedUpdate_ == null) {
            this.delegatedUpdate_ = new UpdateCommand(this.getDelegate());
        }
        return this.delegatedUpdate_;
    }
    
    synchronized Executor getExecutor() {
        return this.executor_;
    }
    
    public synchronized long next() {
        long longValue = 0L;
        try {
            if (this.nextResult_ == null) {
                (this.nextResult_ = new FutureResult()).set(new Long(this.getDelegate().next()));
            }
            final FutureResult nextResult_ = this.nextResult_;
            this.nextResult_ = new FutureResult();
            this.getExecutor().execute(this.nextResult_.setter(this.delegatedNextFunction()));
            longValue = (long)nextResult_.get();
        }
        catch (InterruptedException ex2) {
            Thread.currentThread().interrupt();
        }
        catch (InvocationTargetException ex) {
            ex.printStackTrace();
            throw new Error("Bad Callable?");
        }
        return longValue;
    }
    
    synchronized void setExecutor(final Executor executor_) {
        this.executor_ = executor_;
    }
    
    public void update() {
        try {
            this.getExecutor().execute(this.delegatedUpdateCommand());
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
