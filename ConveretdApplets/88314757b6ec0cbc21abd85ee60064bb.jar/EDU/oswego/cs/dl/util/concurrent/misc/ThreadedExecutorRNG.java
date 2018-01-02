// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.Executor;
import EDU.oswego.cs.dl.util.concurrent.ThreadFactory;
import EDU.oswego.cs.dl.util.concurrent.ThreadedExecutor;

class ThreadedExecutorRNG extends ExecutorRNG
{
    static final ThreadedExecutor exec;
    
    static {
        (exec = new ThreadedExecutor()).setThreadFactory(Threads.factory);
    }
    
    public ThreadedExecutorRNG() {
        this.setDelegate(new PublicSynchRNG());
        this.setExecutor(ThreadedExecutorRNG.exec);
    }
}
