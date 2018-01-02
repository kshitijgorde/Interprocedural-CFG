// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

public class ThreadControllerWrapper
{
    private static ThreadController m_tpool;
    
    public static Thread runThread(final Runnable runnable, final int priority) {
        return ThreadControllerWrapper.m_tpool.run(runnable, priority);
    }
    
    public static void waitThread(final Thread worker, final Runnable task) throws InterruptedException {
        ThreadControllerWrapper.m_tpool.waitThread(worker, task);
    }
    
    static {
        ThreadControllerWrapper.m_tpool = new ThreadController();
    }
    
    public static class ThreadController
    {
        public Thread run(final Runnable task, final int priority) {
            final Thread t = new Thread(task);
            t.start();
            return t;
        }
        
        public void waitThread(final Thread worker, final Runnable task) throws InterruptedException {
            worker.join();
        }
    }
}
