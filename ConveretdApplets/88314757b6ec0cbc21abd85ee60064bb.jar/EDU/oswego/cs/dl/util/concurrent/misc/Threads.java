// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.PooledExecutor;
import EDU.oswego.cs.dl.util.concurrent.SynchronizedInt;
import EDU.oswego.cs.dl.util.concurrent.ThreadFactory;

class Threads implements ThreadFactory
{
    static final SynchronizedInt activeThreads;
    static final Threads factory;
    static final PooledExecutor pool;
    
    static {
        activeThreads = new SynchronizedInt(0);
        factory = new Threads();
        (pool = new PooledExecutor()).setKeepAliveTime(10000L);
        Threads.pool.setThreadFactory(Threads.factory);
    }
    
    public Thread newThread(final Runnable runnable) {
        return new MyThread(runnable);
    }
    
    static class MyThread extends Thread
    {
        public MyThread(final Runnable runnable) {
            super(runnable);
        }
        
        public void run() {
            Threads.activeThreads.increment();
            try {
                super.run();
            }
            finally {
                Threads.activeThreads.decrement();
            }
        }
    }
}
