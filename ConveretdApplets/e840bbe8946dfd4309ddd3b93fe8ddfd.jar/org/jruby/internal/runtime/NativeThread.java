// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime;

import java.lang.ref.WeakReference;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyThread;
import java.lang.ref.Reference;

public class NativeThread implements ThreadLike
{
    private Reference<Thread> nativeThread;
    public RubyThread rubyThread;
    
    public NativeThread(final RubyThread rubyThread, final IRubyObject[] args, final Block block) {
        throw new RuntimeException();
    }
    
    public NativeThread(final RubyThread rubyThread, final Thread nativeThread) {
        this.rubyThread = rubyThread;
        this.nativeThread = new WeakReference<Thread>(nativeThread);
    }
    
    public void start() {
        final Thread thread = this.getThread();
        if (thread != null) {
            thread.start();
        }
    }
    
    public void interrupt() {
        final Thread thread = this.getThread();
        if (thread != null) {
            thread.interrupt();
        }
    }
    
    public boolean isAlive() {
        final Thread thread = this.getThread();
        return thread != null && thread.isAlive();
    }
    
    public void join() throws InterruptedException {
        final Thread thread = this.getThread();
        if (thread != null) {
            thread.join();
        }
    }
    
    public void join(final long timeoutMillis) throws InterruptedException {
        final Thread thread = this.getThread();
        if (thread != null) {
            thread.join(timeoutMillis);
        }
    }
    
    public int getPriority() {
        final Thread thread = this.getThread();
        if (thread != null) {
            return thread.getPriority();
        }
        return 0;
    }
    
    public void setPriority(final int priority) {
        final Thread thread = this.getThread();
        if (thread != null) {
            thread.setPriority(priority);
        }
    }
    
    public boolean isCurrent() {
        return this.getThread() == Thread.currentThread();
    }
    
    public boolean isInterrupted() {
        final Thread thread = this.getThread();
        return thread != null && thread.isInterrupted();
    }
    
    public Thread getThread() {
        return this.nativeThread.get();
    }
    
    public String toString() {
        return "" + this.getThread();
    }
}
