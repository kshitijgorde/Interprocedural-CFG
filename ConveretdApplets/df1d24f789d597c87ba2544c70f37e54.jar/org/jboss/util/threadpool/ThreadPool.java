// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.threadpool;

public interface ThreadPool
{
    void stop(final boolean p0);
    
    void waitForTasks() throws InterruptedException;
    
    void waitForTasks(final long p0) throws InterruptedException;
    
    void runTaskWrapper(final TaskWrapper p0);
    
    void runTask(final Task p0);
    
    void run(final Runnable p0);
    
    void run(final Runnable p0, final long p1, final long p2);
}
