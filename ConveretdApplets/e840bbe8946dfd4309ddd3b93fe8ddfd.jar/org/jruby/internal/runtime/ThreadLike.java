// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime;

import java.util.concurrent.ExecutionException;

public interface ThreadLike
{
    void start();
    
    void interrupt();
    
    boolean isAlive();
    
    void join() throws InterruptedException, ExecutionException;
    
    void join(final long p0) throws InterruptedException, ExecutionException;
    
    int getPriority();
    
    void setPriority(final int p0);
    
    boolean isCurrent();
    
    boolean isInterrupted();
}
