// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CancellationException;
import org.jruby.RubyThread;
import java.util.concurrent.Future;

public class FutureThread implements ThreadLike
{
    private volatile Future future;
    private RubyRunnable runnable;
    public RubyThread rubyThread;
    
    public FutureThread(final RubyThread rubyThread, final RubyRunnable runnable) {
        this.rubyThread = rubyThread;
        this.runnable = runnable;
    }
    
    public Future getFuture() {
        return this.future;
    }
    
    public void start() {
        this.future = this.rubyThread.getRuntime().getExecutor().submit(new Runnable() {
            public void run() {
                try {
                    FutureThread.this.runnable.run();
                }
                finally {
                    FutureThread.this.rubyThread.getRuntime().getThreadService().dissociateThread(FutureThread.this.future);
                }
            }
        });
    }
    
    public void interrupt() {
        if (this.runnable.getJavaThread() != null) {
            this.runnable.getJavaThread().interrupt();
        }
    }
    
    public boolean isAlive() {
        return this.future == null || !this.future.isDone();
    }
    
    public void join() throws InterruptedException, ExecutionException {
        try {
            this.future.get();
        }
        catch (CancellationException ex) {}
    }
    
    public void join(final long millis) throws InterruptedException, ExecutionException {
        if (millis == 0L) {
            this.join();
        }
        else {
            try {
                this.future.get(millis, TimeUnit.MILLISECONDS);
            }
            catch (CancellationException ce) {}
            catch (TimeoutException ex) {}
        }
    }
    
    public int getPriority() {
        return 1;
    }
    
    public void setPriority(final int priority) {
    }
    
    public boolean isCurrent() {
        return this.rubyThread == this.rubyThread.getRuntime().getCurrentContext().getThread();
    }
    
    public boolean isInterrupted() {
        return this.future.isCancelled();
    }
}
