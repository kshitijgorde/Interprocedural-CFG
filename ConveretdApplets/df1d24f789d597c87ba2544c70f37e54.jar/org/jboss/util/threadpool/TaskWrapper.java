// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.threadpool;

public interface TaskWrapper extends Runnable
{
    int getTaskWaitType();
    
    int getTaskPriority();
    
    long getTaskStartTimeout();
    
    long getTaskCompletionTimeout();
    
    void waitForTask();
    
    void stopTask();
    
    void acceptTask();
    
    void rejectTask(final RuntimeException p0);
    
    boolean isComplete();
}
