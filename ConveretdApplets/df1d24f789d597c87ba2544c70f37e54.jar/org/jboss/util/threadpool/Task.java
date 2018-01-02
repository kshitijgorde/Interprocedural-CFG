// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.threadpool;

public interface Task
{
    public static final int WAIT_NONE = 0;
    public static final int WAIT_FOR_START = 1;
    public static final int WAIT_FOR_COMPLETE = 2;
    
    int getWaitType();
    
    int getPriority();
    
    long getStartTimeout();
    
    long getCompletionTimeout();
    
    void execute();
    
    void stop();
    
    void accepted(final long p0);
    
    void rejected(final long p0, final Throwable p1);
    
    void started(final long p0);
    
    void completed(final long p0, final Throwable p1);
}
