// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.Executor;
import EDU.oswego.cs.dl.util.concurrent.ThreadFactory;
import EDU.oswego.cs.dl.util.concurrent.QueuedExecutor;

class QueuedExecutorRNG extends ExecutorRNG
{
    static final QueuedExecutor exec;
    
    static {
        (exec = new QueuedExecutor()).setThreadFactory(Threads.factory);
    }
    
    public QueuedExecutorRNG() {
        this.setDelegate(new PublicSynchRNG());
        this.setExecutor(QueuedExecutorRNG.exec);
    }
}
